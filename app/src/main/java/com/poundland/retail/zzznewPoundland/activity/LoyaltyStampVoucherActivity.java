package com.poundland.retail.zzznewPoundland.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.BaseActivity;
import com.poundland.retail.activity.MyCartActivity;
import com.poundland.retail.activity.MyOrderActivity;
import com.poundland.retail.activity.NearByDealsActivity;
import com.poundland.retail.activity.NotificationActivity;
import com.poundland.retail.activity.SpecialOfferDetailsActivity;
import com.poundland.retail.activity.VenueDetailActivity;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.adapter.LoyaltyAdapter;
import com.poundland.retail.adapter.StampInnerCardAdapter;
import com.poundland.retail.adapter.VoucherAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityLoyaltyStampVoucherBinding;
import com.poundland.retail.databinding.LayoutTabLoyaltyBinding;
import com.poundland.retail.databinding.LayoutTabStampBinding;
import com.poundland.retail.databinding.LayoutTabVoucherBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.AllVenuesRequestModel;
import com.poundland.retail.model.responseModel.GetCustomerLoyaltyResponseModel;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.notificationService.NotificationModel;
import com.poundland.retail.zzznewPoundland.model.StampDataModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_LOYALTY;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.HOME_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.HOME_RETAIL;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.LOYALTY;
import static com.poundland.retail.interfaces.Constants.LOYA_STAMP_VOUC_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.RESERVATION_ID;
import static com.poundland.retail.interfaces.Constants.SHOP_NOW;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STAMP;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VOUCHER;
import static com.poundland.retail.interfaces.Constants.isLoyaltyApplied;

public class LoyaltyStampVoucherActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {
    private ActivityLoyaltyStampVoucherBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String userId;
    private ArrayList<GetCustomerLoyaltyResponseModel.LoyaltyBean.DataBean> list;
    private LayoutTabLoyaltyBinding bindingLoyalty;
    private LayoutTabStampBinding bindingStamp;
    private LayoutTabVoucherBinding bindingvoucher;
    private String venueId = "";
    private LoyaltyAdapter loyaltyAdapter;

    private LoyaltyStampVoucherActivity instance = null;
    private String fromWhere = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loyalty_stamp_voucher);
        init();
        list = new ArrayList<GetCustomerLoyaltyResponseModel.LoyaltyBean.DataBean>();
        setListeners();
        setTabs();
        binding.llStampContent.setVisibility(View.GONE);
        getLoyalty(false);
    }

    private void init() {
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        bindingLoyalty = DataBindingUtil.bind(LayoutInflater.from(this).inflate(R.layout.layout_tab_loyalty, null));
        bindingStamp = DataBindingUtil.bind(LayoutInflater.from(this).inflate(R.layout.layout_tab_stamp, null));
        bindingvoucher = DataBindingUtil.bind(LayoutInflater.from(this).inflate(R.layout.layout_tab_voucher, null));

        //   binding.tabLayout.setVisibility(View.GONE);
        instance = this;
        isLoading = true;
        pageNumber = 1;
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.title.setText(getString(R.string.loyalty_point));
    }


    private void setLoyaltyAdapter() {
        loyaltyAdapter = new LoyaltyAdapter(this, list, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvLsv.setLayoutManager(layoutManager);
        binding.rvLsv.setAdapter(loyaltyAdapter);
        binding.rvLsv.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && pageNumber <= totalPage) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            isLoading = true;
                            getLoyalty(true);
                        }
                    }
                }
            }
        });
    }

    private void setStampAdapter() {
        //    StampAdapter stampAdapter = new StampAdapter(this, list, this);
        //    final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //    binding.rvLsv.setLayoutManager(layoutManager);
        //    binding.rvLsv.setAdapter(stampAdapter);

        StampDataModel stampDataModel = new Gson().fromJson(HelperClass.stamp_content, StampDataModel.class);

        StampInnerCardAdapter loyaltyAdapter = new StampInnerCardAdapter(this, stampDataModel.getListData(), this);
        final GridLayoutManager layoutManager = new GridLayoutManager(this, 4);
        binding.rvLsv.setLayoutManager(layoutManager);
        binding.rvLsv.setAdapter(loyaltyAdapter);

    }

    private void setVoucherAdapter() {
        VoucherAdapter loyaltyAdapter = new VoucherAdapter(this, list, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvLsv.setLayoutManager(layoutManager);
        binding.rvLsv.setAdapter(loyaltyAdapter);
    }

    private void setListeners() {
        /*binding.cvLoyalty.setOnClickListener(this);
        binding.cvStamp.setOnClickListener(this);
        binding.cvVoucher.setOnClickListener(this);*/
        binding.ivBack.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.ivNotification.setOnClickListener(this);
    }

    public void setTab(int tabPosition) {
        if (binding.tabLayout != null) {
            binding.tabLayout.getTabAt(tabPosition).select();
        } else {
            // selectedTab = tabPosition;
        }
    }

    private void setTabs() {
        int[] tabIcons = {
                R.drawable.ic_loyalty_stamp_voucher,
                R.drawable.ic_card_giftcard,
                R.drawable.ic_nearby_deals
        };
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.points_earnd)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.loyalty_voucher)));
        // binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.stamp)));
        binding.tabLayout.getTabAt(0).setCustomView(R.layout.layout_tab_loyalty);
        binding.tabLayout.getTabAt(1).setCustomView(R.layout.layout_tab_voucher);
        //  binding.tabLayout.getTabAt(2).setCustomView(R.layout.layout_tab_stamp);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //  selectedTab = tab.getPosition();
                switch (binding.tabLayout.getSelectedTabPosition()) {
                    case 0:
                        binding.llStampContent.setVisibility(View.GONE);
                        setLoyaltyAdapter();
                       /* bindingLoyalty.llMain.setBackgroundColor(getResources().getColor(R.color.color_light_red));
                        bindingStamp.llMain.setBackgroundColor(getResources().getColor(R.color.color_et_bg));
                        bindingvoucher.llMain.setBackgroundColor(getResources().getColor(R.color.color_et_bg));
                        ImageViewCompat.setImageTintList(bindingLoyalty.ivTab, ColorStateList.valueOf(Color.parseColor("#8f8f8f"))); */
                        break;
                    case 1:
                        binding.llStampContent.setVisibility(View.VISIBLE);
                        setStampAdapter();
                        break;
                    case 2:
                        setVoucherAdapter();
                        break;
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // lastSelectedPosition = -1;

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_cart:
                if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                    Intent intentH = new Intent(this, MyCartHospitalityActivity.class);
                    startActivity(intentH);
                } else {
                    Intent intentR = new Intent(this, MyCartActivity.class);
                    startActivity(intentR);
                }
                break;
            case R.id.iv_notification:
                Intent noti = new Intent(this, NotificationActivity.class);
                startActivity(noti);
                break;
        }
    }


    private void getLoyalty(final boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();
            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");
            AllVenuesRequestModel allVenuesRequestModel = new AllVenuesRequestModel(venueId, latitude, longitude);


            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<GetCustomerLoyaltyResponseModel> call = apiInterface.getLoyalty(authToken, prefManager.getPreference(EMAIL, ""),
                    allVenuesRequestModel, String.valueOf(pageNumber++));
            call.enqueue(new Callback<GetCustomerLoyaltyResponseModel>() {
                @Override
                public void onResponse(Call<GetCustomerLoyaltyResponseModel> call, Response<GetCustomerLoyaltyResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            GetCustomerLoyaltyResponseModel loyaltyResponseModel = response.body();
                            if (loyaltyResponseModel != null) {
                                if (loyaltyResponseModel.isStatus()) {
                                    isLoading = false;
                                    totalPage = loyaltyResponseModel.getLoyalty().getTotal();
                                    if (loyaltyResponseModel.getLoyalty().getData() != null && loyaltyResponseModel.getLoyalty().getData().size() > 0) {
                                        binding.llNoDataFound.setVisibility(View.GONE);

                                        // list.clear();
                                        list.addAll(loyaltyResponseModel.getLoyalty().getData());
                                        if (isMore) {
                                            if (loyaltyAdapter != null)
                                                loyaltyAdapter.notifyDataSetChanged();
                                        } else {
                                            setLoyaltyAdapter();
                                        }
                                    } else {
                                        if (!isMore)
                                            binding.llNoDataFound.setVisibility(View.VISIBLE);
                                    }
                                } else {
                                    showSnackBar(binding.getRoot(), loyaltyResponseModel.getMessage());
                                }
                            }

                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(instance);
                                    }
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetCustomerLoyaltyResponseModel> call, Throwable t) {
                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    @Override
    public void onRefresh() {
        isLoading = true;
        pageNumber = 1;
        list.clear();
        getLoyalty(false);
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        switch (clickType) {

            case LOYALTY:
                if (fromWhere.equals(getString(R.string.cart))) {
                    Intent loyalty = new Intent();
                    loyalty.putExtra(LOYA_STAMP_VOUC_ACTIVITY_CALLBACK, list.get(position).getLoyalty_point_value());
                    setResult(RESULT_OK, loyalty);
                    finish();
                } else {
                    prefManager.savePreference(isLoyaltyApplied, true);
                    Intent topStore = new Intent(instance, VenueDetailActivity.class);
                    topStore.putExtra(STORE_ID, list.get(position).getVenue_id());
                    topStore.putExtra(FROM_WHERE, FROM_LOYALTY);
                    startActivity(topStore);
                }
                break;

            case STAMP:
                Intent stamp = new Intent();
                stamp.putExtra(LOYA_STAMP_VOUC_ACTIVITY_CALLBACK, list.get(position).getLoyalty_point_value());
                setResult(RESULT_OK, stamp);
                finish();
                break;

            case VOUCHER:
                Intent voucher = new Intent();
                voucher.putExtra(LOYA_STAMP_VOUC_ACTIVITY_CALLBACK, list.get(position).getLoyalty_point_value());
                setResult(RESULT_OK, voucher);
                finish();
                break;

            case SHOP_NOW:
                prefManager.savePreference(isLoyaltyApplied, true);

                Intent store = new Intent(this, VenueDetailActivity.class);
                store.putExtra(STORE_ID, list.get(position).getVenue_id());

                if (list.get(position).getVenue_type() == 1) {
                    store.putExtra(FROM_WHERE, HOME_RETAIL);
                } else if (list.get(position).getVenue_type() == 2) {
                    store.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                }
                store.putExtra(RESERVATION_ID, "");
                store.putExtra(CATEGORY_ID, "");
                startActivity(store);

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTotalCartCount();
    }

    private void getTotalCartCount() {
        if (isInternetOn(this)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<GetTotalCartResponseModel> call = apiInterface.getTotalCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""));
            call.enqueue(new Callback<GetTotalCartResponseModel>() {
                @Override
                public void onResponse(Call<GetTotalCartResponseModel> call, Response<GetTotalCartResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            GetTotalCartResponseModel totalCartResponseModel = response.body();
                            if (totalCartResponseModel.isStatus()) {
                                if (totalCartResponseModel.getTotal_carts() > 0)
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
                                binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
                            } else {
                                showSnackBar(binding.getRoot(), totalCartResponseModel.getMessage());
                            }
                        } else {
                            //  showSnackBar(binding.getRoot(), getString(R.string.total_cart_item_not_found));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetTotalCartResponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_my_cart;
    }

    @Override
    protected void onNotificationReceived(Intent intent) {
        try {
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String timestamp = extras.getString("timestamp");
                String title = extras.getString("title");
                String message = extras.getString("message");
                String image = extras.getString("image");
                String data = extras.getString("data");

                if (data.equals("DISC_OFFER_NOTIFY")) {
                    DialogUtils.offerNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent topStore = new Intent(this, NearByDealsActivity.class);
                        topStore.putExtra(TOP_OFFER_TITLE, getString(R.string.nearby_deals));
                        startActivity(topStore);
                    });
                } else if (!TextUtils.isEmpty(data)) {
                    NotificationModel model = new Gson().fromJson(data, NotificationModel.class);
                    DialogUtils.offerNotificationDialog(this, title, message, image, new Gson().fromJson(data, NotificationModel.class), (position, clickId) -> {
                        Intent topProduct = new Intent(this, SpecialOfferDetailsActivity.class);
                        topProduct.putExtra(PRODUCT_ID, "");
                        topProduct.putExtra(OFFER_ID, "");
                        topProduct.putExtra(SPECIAL_OFFER_ID, String.valueOf(model.getSpecial_offer_id()));
                        startActivity(topProduct);
                    });
                } else {
                    DialogUtils.simpleNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent myOrder = new Intent(this, MyOrderActivity.class);
                        startActivity(myOrder);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
