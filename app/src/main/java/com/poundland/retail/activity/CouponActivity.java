package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.adapter.CouponAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityCouponBinding;
import com.poundland.retail.databinding.LayoutTabLoyaltyBinding;
import com.poundland.retail.databinding.LayoutTabStampBinding;
import com.poundland.retail.databinding.LayoutTabVoucherBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.CouponRequestModel;
import com.poundland.retail.model.responseModel.CouponResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVITY_COUPON_CALLBACK_DATA;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.RESERVATION_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID;

public class CouponActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {
    private ActivityCouponBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private List<CouponResponseModel.CouponDataBean> couponList;
    private LayoutTabLoyaltyBinding bindingLoyalty;
    private LayoutTabStampBinding bindingStamp;
    private LayoutTabVoucherBinding bindingvoucher;
    private CouponActivity instance = null;
    private String fromWhere = "";
    private String venueId;
    private String reservationId;
    private CouponAdapter couponAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_coupon);
        init();
        couponList = new ArrayList<>();
        setListeners();
        setTabs();
        getTodayCoupon(false);
    }

    private void init() {
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        venueId = getIntent().getStringExtra(VENUE_ID);
        reservationId = getIntent().getStringExtra(RESERVATION_ID);


        bindingLoyalty = DataBindingUtil.bind(LayoutInflater.from(this).inflate(R.layout.layout_tab_loyalty, null));
        bindingStamp = DataBindingUtil.bind(LayoutInflater.from(this).inflate(R.layout.layout_tab_stamp, null));
        bindingvoucher = DataBindingUtil.bind(LayoutInflater.from(this).inflate(R.layout.layout_tab_voucher, null));

        binding.tabLayout.setVisibility(View.GONE);
        instance = this;
        isLoading = true;
        pageNumber = 1;
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.title.setText(getString(R.string.coupon));
    }

    private void setCouponAdapter() {
        couponAdapter = new CouponAdapter(this, couponList, this);
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        binding.rvLsv.setLayoutManager(layoutManager);
        binding.rvLsv.setAdapter(couponAdapter);
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
                            getTodayCoupon(true);
                        }
                    }
                }
            }
        });
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
        //binding.ivCart.setOnClickListener(this);
        // binding.ivNotification.setOnClickListener(this);
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
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.loyalty)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.stamp)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.voucher)));
        binding.tabLayout.getTabAt(0).setCustomView(R.layout.layout_tab_loyalty);
        binding.tabLayout.getTabAt(1).setCustomView(R.layout.layout_tab_stamp);
        binding.tabLayout.getTabAt(2).setCustomView(R.layout.layout_tab_voucher);
        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //  selectedTab = tab.getPosition();
                switch (binding.tabLayout.getSelectedTabPosition()) {
                    case 0:
                        setCouponAdapter();
                       /* bindingLoyalty.llMain.setBackgroundColor(getResources().getColor(R.color.color_light_red));
                        bindingStamp.llMain.setBackgroundColor(getResources().getColor(R.color.color_et_bg));
                        bindingvoucher.llMain.setBackgroundColor(getResources().getColor(R.color.color_et_bg));
                        ImageViewCompat.setImageTintList(bindingLoyalty.ivTab, ColorStateList.valueOf(Color.parseColor("#8f8f8f"))); */
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

    private void getTodayCoupon(final boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            CouponRequestModel couponRequestModel = new CouponRequestModel(reservationId, venueId);


            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CouponResponseModel> call = apiInterface.getTodayCoupon(authToken, prefManager.getPreference(EMAIL, ""),
                    couponRequestModel);
            call.enqueue(new Callback<CouponResponseModel>() {
                @Override
                public void onResponse(Call<CouponResponseModel> call, Response<CouponResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            CouponResponseModel loyaltyResponseModel = response.body();
                            if (loyaltyResponseModel != null) {
                                if (loyaltyResponseModel.isStatus()) {
                                    isLoading = false;
                                    if (loyaltyResponseModel.getCouponData() != null && loyaltyResponseModel.getCouponData().size() > 0) {
                                        binding.tvNoDataFound.setVisibility(View.GONE);
                                        couponList.addAll(loyaltyResponseModel.getCouponData());
                                        if (isMore) {
                                            if (couponAdapter != null)
                                                couponAdapter.notifyDataSetChanged();
                                        } else {
                                            setCouponAdapter();
                                        }
                                    } else {
                                        if (!isMore)
                                            binding.tvNoDataFound.setVisibility(View.VISIBLE);
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
                public void onFailure(Call<CouponResponseModel> call, Throwable t) {
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
        couponList.clear();
        if (couponAdapter != null)
            couponAdapter.notifyDataSetChanged();
        getTodayCoupon(false);
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {

        if (fromWhere.equals(getString(R.string.cart))) {
            Intent intent = new Intent();
            intent.putExtra(OPEN_ACTIVITY_COUPON_CALLBACK_DATA, couponList.get(position)/*.getOffer_amount()*/);
            setResult(RESULT_OK, intent);
            finish();
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
                                //if (totalCartResponseModel.getTotal_carts() > 0)
                                //    binding.tvCartCount.setVisibility(View.VISIBLE);
                                //  binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
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
        return R.layout.activity_coupon;
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }
}
