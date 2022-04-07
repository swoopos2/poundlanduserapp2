package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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
import com.poundland.retail.adapter.NearByDealsAdapter;
import com.poundland.retail.adapter.SpecialOffersAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityNearByDealsBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.ProductBySearchRequestModel;
import com.poundland.retail.model.responseModel.NearByDealsResponseModel;
import com.poundland.retail.model.responseModel.SpecialOffersResponseModel;
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
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FUSED_LOCATION;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFERS;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class NearByDealsActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {

    private ActivityNearByDealsBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String search = "";
    private List<NearByDealsResponseModel.ProductOffersBean.DataBean> nearByDealsList;
    private List<SpecialOffersResponseModel.ProductOffersBean.DataBean> specialOffersList;
    private NearByDealsActivity instance = null;
    private NearByDealsAdapter nearByDealsAdapter;
    private SpecialOffersAdapter specialOffersAdapter;
    private String CLICK_TYPE;
    private NearByDealsResponseModel nearByDealsResponseModel;
    private SpecialOffersResponseModel offersResponseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_near_by_deals);
        init();
        setListeners();
        getNearByDeals(false);
    }

    private void init() {
        instance = this;
        CLICK_TYPE = getString(R.string.nearby_deals);
        setTabs();
        isLoading = true;
        pageNumber = 1;
        nearByDealsList = new ArrayList<>();
        specialOffersList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.title.setText(getIntent().getStringExtra(TOP_OFFER_TITLE));
        binding.tvTopOffersNearYouLocation.setText(prefManager.getPreference(FUSED_LOCATION, ""));
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
        //  binding.ivSearchNowHeader.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
//        binding.ivNotification.setOnClickListener(this);
    }

    private void setTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.nearby_deals)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.special_offers)));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //  selectedTab = tab.getPosition();
                switch (binding.tabLayout.getSelectedTabPosition()) {
                    case 0:
                        CLICK_TYPE = getString(R.string.nearby_deals);
                        binding.title.setText(getString(R.string.nearby_deals));
                        specialOffersList.clear();
                        if (specialOffersAdapter!=null)
                        specialOffersAdapter.notifyDataSetChanged();
                        pageNumber = 1;
                        getNearByDeals(false);
                        //    binding.tvTopOffersText.setText(nearByDealsResponseModel.getProductOffers().getTotal() + " " + getString(R.string.top_offers_near_you));
                        break;
                    case 1:
                        CLICK_TYPE = getString(R.string.special_offers);
                        binding.title.setText(getString(R.string.special_offers));
                        pageNumber = 1;
                        nearByDealsList.clear();
                        if (nearByDealsAdapter!=null)
                        nearByDealsAdapter.notifyDataSetChanged();
                        getSpecialOffers(false);
                        //   binding.tvTopOffersText.setText(offersResponseModel.getProductOffers().getTotal() + " " + getString(R.string.top_offers_near_you));
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

    private void setNearByOffersAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        nearByDealsAdapter = new NearByDealsAdapter(this, nearByDealsList, this);
        binding.rvTopOffers.setLayoutManager(layoutManager);
        binding.rvTopOffers.setAdapter(nearByDealsAdapter);
        binding.rvTopOffers.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            getNearByDeals(true);
                        }
                    }
                }
            }
        });
    }

    private void setSpecialOffersAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);

        specialOffersAdapter = new SpecialOffersAdapter(this, specialOffersList, this);
        binding.rvTopOffers.setLayoutManager(layoutManager);
        binding.rvTopOffers.setAdapter(specialOffersAdapter);
        binding.rvTopOffers.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            getSpecialOffers(true);
                        }
                    }
                }
            }
        });
    }


    @Override
    public void onDrawerSelect(int position, int clickType) {  /// clickType ==> TOP_OFFER
        if (clickType == SPECIAL_OFFERS) {
            if (specialOffersList.size() > 0) {
                Intent topProduct = new Intent(this, SpecialOfferDetailsActivity.class);
                topProduct.putExtra(PRODUCT_ID, String.valueOf(specialOffersList.get(position).getProduct_id()));
                topProduct.putExtra(OFFER_ID, String.valueOf(specialOffersList.get(position).getOffer_id()));
                topProduct.putExtra(SPECIAL_OFFER_ID, String.valueOf(specialOffersList.get(position).getSpecial_offer_id()));
                startActivity(topProduct);
            }
        } else {
            if (nearByDealsList.size() > 0) {
                Intent topProduct = new Intent(this, ProductDetailActivity.class);
                topProduct.putExtra(STORE_ID, nearByDealsList.get(position).getVenue_id());
                topProduct.putExtra(PRODUCT_ID, String.valueOf(nearByDealsList.get(position).getProduct_id()));
                topProduct.putExtra(OFFER_ID, String.valueOf(nearByDealsList.get(position).getOffer_id()));
                topProduct.putExtra(BARCODE_ID, "");
                startActivity(topProduct);
            }
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            /*case R.id.iv_search_now_header:
                offerList.clear();
                isLoading = true;
                pageNumber = 1;
                search = binding.etSearchNowHeader.getText().toString();
                getNearByDeals(false);
                break;*/

            case R.id.iv_cart:
                if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                    Intent intentH = new Intent(this, MyCartHospitalityActivity.class);
                    startActivity(intentH);
                } else {
                    Intent intentR = new Intent(this, MyCartActivity.class);
                    startActivity(intentR);
                }

                break;
           /* case R.id.iv_notification:
                Intent noti = new Intent(this, NotificationActivity.class);
                startActivity(noti);
                break;*/
        }
    }


    @Override
    public void onRefresh() {

        isLoading = true;
        pageNumber = 1;
        if (CLICK_TYPE.equals(getString(R.string.nearby_deals))) {
            nearByDealsList.clear();
            if (nearByDealsAdapter != null)
                nearByDealsAdapter.notifyDataSetChanged();
            getNearByDeals(false);
        } else {
            specialOffersList.clear();
            if (specialOffersAdapter != null)
                specialOffersAdapter.notifyDataSetChanged();
            getSpecialOffers(false);
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

    private void getNearByDeals(boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();
            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");
            ProductBySearchRequestModel productBySearchRequestModel = new ProductBySearchRequestModel(search, latitude, longitude);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<NearByDealsResponseModel> call = apiInterface.getAllProductOffers(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), productBySearchRequestModel, String.valueOf(pageNumber++));
            call.enqueue(new Callback<NearByDealsResponseModel>() {
                @Override
                public void onResponse(Call<NearByDealsResponseModel> call, Response<NearByDealsResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            nearByDealsResponseModel = response.body();
                            if (nearByDealsResponseModel.isStatus()) {
                                isLoading = false;
                                totalPage = nearByDealsResponseModel.getProductOffers().getLast_page();
                                binding.tvTopOffersText.setText(nearByDealsResponseModel.getProductOffers().getTotal() + " " + getString(R.string.top_offers_near_you));
                                if (nearByDealsResponseModel.getProductOffers().getData() != null && nearByDealsResponseModel.getProductOffers().getData().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);
                                    binding.rlTopOfferNearYou.setVisibility(View.VISIBLE);
                                    nearByDealsList.addAll(nearByDealsResponseModel.getProductOffers().getData());


                                    if (isMore) {
                                        if (nearByDealsAdapter != null)
                                            nearByDealsAdapter.notifyDataSetChanged();
                                    } else {
                                        setNearByOffersAdapter();
                                    }
                                } else {
                                    if (!isMore) {
                                        nearByDealsList.clear();
                                        if (nearByDealsAdapter != null)
                                            nearByDealsAdapter.notifyDataSetChanged();
                                        setNearByOffersAdapter();
                                        binding.rlTopOfferNearYou.setVisibility(View.GONE);
                                        binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    } /*else {
                                        offerList.clear();
                                        binding.tvTopOffersText.setText(offerList.size() + " " + getString(R.string.top_offers_near_you));
                                        setNearByOffersAdapter();
                                    }*/


                                }
                            } else {
                                showSnackBar(binding.getRoot(), nearByDealsResponseModel.getMessage());
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
                public void onFailure(Call<NearByDealsResponseModel> call, Throwable t) {
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

    private void getSpecialOffers(boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();
            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");
            // String longitude = "-2.138813";
            ProductBySearchRequestModel productBySearchRequestModel = new ProductBySearchRequestModel(latitude, longitude);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<SpecialOffersResponseModel> call = apiInterface.getSPecialOffers(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), productBySearchRequestModel, String.valueOf(pageNumber++));
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<SpecialOffersResponseModel>() {
                @Override
                public void onResponse(Call<SpecialOffersResponseModel> call, Response<SpecialOffersResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            offersResponseModel = response.body();
                            if (offersResponseModel.isStatus()) {
                                isLoading = false;
                                totalPage = offersResponseModel.getProductOffers().getLast_page();
                                binding.tvTopOffersText.setText(offersResponseModel.getProductOffers().getTotal() + " " + getString(R.string.special_offers_for_you));

                                if (offersResponseModel.getProductOffers().getData() != null && offersResponseModel.getProductOffers().getData().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);
                                    specialOffersList.addAll(offersResponseModel.getProductOffers().getData());
                                    if (isMore) {
                                        if (specialOffersAdapter != null)
                                            specialOffersAdapter.notifyDataSetChanged();
                                    } else {
                                        setSpecialOffersAdapter();
                                    }


                                } else {
                                    if (!isMore) {
                                        specialOffersList.clear();
                                        if (specialOffersAdapter != null)
                                        specialOffersAdapter.notifyDataSetChanged();
                                        setSpecialOffersAdapter();
                                        binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    } /*else {
                                        offerList.clear();
                                        binding.tvTopOffersText.setText(offerList.size() + " " + getString(R.string.top_offers_near_you));
                                        setNearByOffersAdapter();
                                    }*/


                                }
                            } else {
                                showSnackBar(binding.getRoot(), getString(R.string.something_went_wrong));
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
                public void onFailure(Call<SpecialOffersResponseModel> call, Throwable t) {
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
                        // startActivity(topStore);
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
