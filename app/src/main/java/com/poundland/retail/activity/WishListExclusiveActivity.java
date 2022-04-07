package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.SeekBar;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.adapter.WishListExclusiveAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityWishListBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.AddressRequestModel;
import com.poundland.retail.model.responseModel.GetMatchWishlistProductResponseModel;
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
import static com.poundland.retail.interfaces.Constants.BARCODE_ID;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.HOME_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class WishListExclusiveActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {
    private ActivityWishListBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String userId;
    private List<GetMatchWishlistProductResponseModel.MatchProductsBean.DataBean> matchWishListBean;
    private WishListExclusiveAdapter wishlistAdapter;
    private String productId;
    private WishListExclusiveActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wish_list);
        init();
        setListeners();
    }

    private void init() {
        instance = this;
        binding.llTop.setVisibility(View.GONE);
        binding.ivNotification.setVisibility(View.GONE);
        binding.ivCart.setVisibility(View.GONE);
        binding.tvCartCount.setVisibility(View.GONE);
        matchWishListBean = new ArrayList<>();
        productId = getIntent().getStringExtra(PRODUCT_ID);
        isLoading = true;
        pageNumber = 1;
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.title.setText(getString(R.string.wishlist_match));
        getMatchWishLists(false);
//        setTabs();

        binding.rangeSeekbar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                binding.textMin2.setText(String.valueOf(progress));
                binding.textMax2.setText(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.ivNotification.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
    }


    private void setAdapter() {
        wishlistAdapter = new WishListExclusiveAdapter(this, matchWishListBean, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvWishlist.setLayoutManager(layoutManager);
        binding.rvWishlist.setAdapter(wishlistAdapter);
        binding.rvWishlist.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            getMatchWishLists(true);
                        }
                    }
                }
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


        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getMatchWishLists(final boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");
            final AddressRequestModel requestModel = new AddressRequestModel(productId, latitude, longitude);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<GetMatchWishlistProductResponseModel> call = apiInterface.getMatchWishlistProduct(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel, String.valueOf(pageNumber++));
            call.enqueue(new Callback<GetMatchWishlistProductResponseModel>() {
                @Override
                public void onResponse(Call<GetMatchWishlistProductResponseModel> call, Response<GetMatchWishlistProductResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            GetMatchWishlistProductResponseModel responseModel = response.body();
                            if (responseModel != null) {
                                if (responseModel.isStatus()) {
                                    isLoading = false;
                                    totalPage = responseModel.getMatch_products().getTotal();
                                    if (responseModel.getMatch_products().getData() != null && responseModel.getMatch_products().getData().size() > 0) {
                                        binding.tvNoDataFound.setVisibility(View.GONE);
                                        matchWishListBean.addAll(responseModel.getMatch_products().getData());
                                        if (isMore) {
                                            if (wishlistAdapter != null)
                                                wishlistAdapter.notifyDataSetChanged();
                                        } else {
                                            setAdapter();
                                        }
                                    } else {
                                        if (!isMore) {
                                            matchWishListBean.clear();
                                            binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                            setAdapter();
                                        }
                                    }
                                } else {
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());
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
                public void onFailure(Call<GetMatchWishlistProductResponseModel> call, Throwable t) {
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
        matchWishListBean.clear();
        pageNumber = 1;
        getMatchWishLists(false);
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        if (matchWishListBean.get(position).getProduct_type() == 1) {
            Intent product = new Intent(this, ProductDetailActivity.class);
            product.putExtra(STORE_ID, matchWishListBean.get(position).getVenue_id());
            product.putExtra(PRODUCT_ID, String.valueOf(matchWishListBean.get(position).getId()));
            product.putExtra(OFFER_ID, "");
            product.putExtra(BARCODE_ID, "");
            startActivity(product);
        } else if (matchWishListBean.get(position).getProduct_type() == 2) {
            Intent topStore = new Intent(instance, VenueDetailActivity.class);
            topStore.putExtra(STORE_ID, matchWishListBean.get(position).getVenue_id());
            topStore.putExtra(FROM_WHERE, HOME_HOSPITALITY);
            topStore.putExtra(CATEGORY_ID, "");
            startActivity(topStore);
        }
    }


    public void setTab(int tabPosition) {
        if (binding.tabLayout != null) {
            binding.tabLayout.getTabAt(tabPosition).select();
        } else {
            // selectedTab = tabPosition;
        }
    }

   /* private void setTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.product)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.store)));
        //  binding.tabLayout.getTabAt(selectedTab).select();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //  selectedTab = tab.getPosition();
                switch (binding.tabLayout.getSelectedTabPosition()) {
                    case 0:


                        break;
                    case 1:

                        break;
                    case 2:

                        break;
                    case 3:

                        break;
                    case 4:

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

    }*/

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_wish_list;
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
