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

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.adapter.WishlistProductAdapter;
import com.poundland.retail.adapter.WishlistVenueAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityWishListBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.GetCartWithSummaryRequestModel;
import com.poundland.retail.model.responseModel.GetWishListResponseModel;
import com.poundland.retail.model.responseModel.WishlistedProductResponseModel;
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
import static com.poundland.retail.interfaces.Constants.HOME_RETAIL;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.TOP_VENUE;

public class WishListActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {
    private ActivityWishListBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String userId;
    private WishListActivity instance = null;
    private List<GetWishListResponseModel.LikesBean.DataBean> productWishListBean;
    private List<WishlistedProductResponseModel.VenuesBean.DataBean> venueWishListBean;
    private WishlistProductAdapter wishlistProductAdapter;
    private WishlistVenueAdapter wishlistVenueAdapter;
    private String CLICK_TYPE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_wish_list);
        init();
        setListeners();
        getWishListProduct(false);

    }

    private void init() {
        instance = this;
        CLICK_TYPE = getString(R.string.products);
        productWishListBean = new ArrayList<>();
        venueWishListBean = new ArrayList<>();
        isLoading = true;
        pageNumber = 1;
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.title.setText(getString(R.string.wishlist));

        setTabs();

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
        binding.ivNotification.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
    }

    private void setProductAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        wishlistProductAdapter = new WishlistProductAdapter(this, productWishListBean, this);
        binding.rvWishlist.setLayoutManager(layoutManager);
        binding.rvWishlist.setAdapter(wishlistProductAdapter);
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
                            getWishListProduct(true);
                        }
                    }
                }
            }
        });


    }

    private void setVenueAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        wishlistVenueAdapter = new WishlistVenueAdapter(this, venueWishListBean, this);
        binding.rvWishlist.setLayoutManager(layoutManager);
        binding.rvWishlist.setAdapter(wishlistVenueAdapter);
        binding.rvWishlist.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && pageNumber <= totalPage) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            isLoading = true;
                            getWishListVenue(true);
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

                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getWishListProduct(final boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");
            final GetCartWithSummaryRequestModel requestModel = new GetCartWithSummaryRequestModel("", latitude, longitude);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<GetWishListResponseModel> call = apiInterface.getWishlists(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel, String.valueOf(pageNumber++));
            call.enqueue(new Callback<GetWishListResponseModel>() {
                @Override
                public void onResponse(Call<GetWishListResponseModel> call, Response<GetWishListResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            GetWishListResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {
                                isLoading = false;
                                totalPage = responseModel.getLikes().getTotal();
                                if (responseModel.getLikes().getData() != null && responseModel.getLikes().getData().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);
                                    productWishListBean.addAll(responseModel.getLikes().getData());
                                    if (isMore) {
                                        if (wishlistProductAdapter != null)
                                            wishlistProductAdapter.notifyDataSetChanged();
                                    } else {

                                        setProductAdapter();
                                    }
                                } else {
                                    if (!isMore) {
                                        productWishListBean.clear();
                                        if (wishlistProductAdapter != null)
                                            wishlistProductAdapter.notifyDataSetChanged();
                                        binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                        setProductAdapter();
                                    }
                                }
                            } else
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
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
                public void onFailure(Call<GetWishListResponseModel> call, Throwable t) {
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

    private void getWishListVenue(final boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");
            final GetCartWithSummaryRequestModel requestModel = new GetCartWithSummaryRequestModel(latitude, longitude);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<WishlistedProductResponseModel> call = apiInterface.getWishlistVenues(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel, String.valueOf(pageNumber++));
            call.enqueue(new Callback<WishlistedProductResponseModel>() {
                @Override
                public void onResponse(Call<WishlistedProductResponseModel> call, Response<WishlistedProductResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            WishlistedProductResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {
                                isLoading = false;
                                totalPage = responseModel.getVenues().getTotal();
                                if (responseModel.getVenues().getData() != null && responseModel.getVenues().getData().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);

                                    venueWishListBean.addAll(responseModel.getVenues().getData());
                                    if (isMore) {
                                        if (wishlistVenueAdapter != null)
                                            wishlistVenueAdapter.notifyDataSetChanged();
                                    } else {
                                        setVenueAdapter();
                                    }
                                } else {
                                    if (!isMore) {
                                        venueWishListBean.clear();
                                        if (wishlistVenueAdapter != null)
                                            wishlistVenueAdapter.notifyDataSetChanged();
                                        binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                        setVenueAdapter();
                                    }
                                }
                            } else
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
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
                public void onFailure(Call<WishlistedProductResponseModel> call, Throwable t) {
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
        pageNumber = 1;
        if (CLICK_TYPE.equals(getString(R.string.products))) {
            venueWishListBean.clear();
            productWishListBean.clear();
            if (wishlistProductAdapter != null)
                wishlistProductAdapter.notifyDataSetChanged();
            getWishListProduct(false);
        } else {
            productWishListBean.clear();
            venueWishListBean.clear();
            if (wishlistVenueAdapter != null)
                wishlistVenueAdapter.notifyDataSetChanged();
            getWishListVenue(false);
        }

    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        if (clickType == TOP_VENUE) {
            if (venueWishListBean.size() > 0) {
                Intent topStore = new Intent(instance, VenueDetailActivity.class);
                topStore.putExtra(STORE_ID, venueWishListBean.get(position).getVenue_id());
                if (venueWishListBean.get(position).getVenue().getVenue_type() == 1) {
                    topStore.putExtra(FROM_WHERE, HOME_RETAIL);
                } else if (venueWishListBean.get(position).getVenue().getVenue_type() == 2) {
                    topStore.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                }
                topStore.putExtra(CATEGORY_ID, "");
                startActivity(topStore);
            }
        } else {
            if (productWishListBean.size() > 0) {
                if (productWishListBean.get(position).getMatch_count() > 1) {
                    Intent wish = new Intent(this, WishListExclusiveActivity.class);
                    wish.putExtra(PRODUCT_ID, String.valueOf(productWishListBean.get(position).getProduct_id()));
                    startActivity(wish);
                } else {
                    if (productWishListBean.get(position).getProduct_type() == 1) {
                        Intent product = new Intent(this, ProductDetailActivity.class);
                        product.putExtra(STORE_ID, productWishListBean.get(position).getVenue_id());
                        product.putExtra(PRODUCT_ID, String.valueOf(productWishListBean.get(position).getProduct_id()));
                        product.putExtra(OFFER_ID, String.valueOf(productWishListBean.get(position).getOffer_id()));
                        product.putExtra(BARCODE_ID, "");
                        startActivity(product);
                    } else if (productWishListBean.get(position).getProduct_type() == 2) {
                        Intent topStore = new Intent(instance, VenueDetailActivity.class);
                        topStore.putExtra(STORE_ID, productWishListBean.get(position).getVenue_id());
                        topStore.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                        topStore.putExtra(CATEGORY_ID, "");
                        startActivity(topStore);
                    }
                }
            }
        }

    }

    public void setTab(int tabPosition) {
        if (binding.tabLayout != null) {
            binding.tabLayout.getTabAt(tabPosition).select();
        } else {
            // selectedTab = tabPosition;
        }
    }

    private void setTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.products)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.venues)));
        //  binding.tabLayout.getTabAt(selectedTab).select();

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //  selectedTab = tab.getPosition();
                switch (binding.tabLayout.getSelectedTabPosition()) {
                    case 0:
                        CLICK_TYPE = getString(R.string.products);
                        binding.title.setText(getString(R.string.products));
                        pageNumber = 1;
                        venueWishListBean.clear();
                        productWishListBean.clear();
                        if (wishlistProductAdapter != null)
                            wishlistProductAdapter.notifyDataSetChanged();
                        getWishListProduct(false);
                        break;
                    case 1:
                        CLICK_TYPE = getString(R.string.venues);
                        binding.title.setText(getString(R.string.venues));
                        pageNumber = 1;
                        productWishListBean.clear();
                        venueWishListBean.clear();
                        if (wishlistVenueAdapter != null)
                            wishlistVenueAdapter.notifyDataSetChanged();
                        getWishListVenue(false);
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
                                if (totalCartResponseModel.getTotal_carts() > 0) {
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
                                } else binding.tvCartCount.setVisibility(View.INVISIBLE);
                                binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
                            } else {
                                showSnackBar(binding.getRoot(), totalCartResponseModel.getMessage());
                            }
                        } else {
                            // showSnackBar(binding.getRoot(), getString(R.string.total_cart_item_not_found));
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
