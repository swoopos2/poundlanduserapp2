package com.poundland.retail.activityHospitality;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.BaseActivity;
import com.poundland.retail.activity.MyOrderActivity;
import com.poundland.retail.activity.NearByDealsActivity;
import com.poundland.retail.activity.NotificationActivity;
import com.poundland.retail.activity.VenueDetailActivity;
import com.poundland.retail.activity.SpecialOfferDetailsActivity;
import com.poundland.retail.activityHospitality.adapter.ReservationVenueAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.XxActivityFavoRestoBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.ReservationVenuesRequestModel;
import com.poundland.retail.model.responseModel.ReservationVenuesResponseModel;
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
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.DATE_INTENT;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.HOME_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PERSON_INTENT;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TIME_INTENT;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE_INTENT;

public class AllVenueReservationActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {

    private XxActivityFavoRestoBinding binding;
    private PrefManager prefManager;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String search = "";
    private AllVenueReservationActivity instance = null;
    private List<ReservationVenuesResponseModel.VenuesBean.DataBean> allVenueList;
    private String category_id = "";
    private ReservationVenueAdapter venueHospitalityAdapter;
    private String searchDate, searchTime, searchItem;
    private int searchGuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.xx_activity_favo_resto);
        // binding.rlTopBanner.setVisibility(View.GONE);
        init();
        setListeners();
        getReservationVenue(false);

    }

    private void init() {

        searchDate = getIntent().getStringExtra(DATE_INTENT);
        searchTime = getIntent().getStringExtra(TIME_INTENT);
        searchGuest = getIntent().getIntExtra(PERSON_INTENT, 1);
        searchItem = getIntent().getStringExtra(VENUE_INTENT);

        instance = this;
        isLoading = true;
        pageNumber = 1;
        allVenueList = new ArrayList<>();
        // category_id = getIntent().getStringExtra(CATEGORY_ID);
        prefManager = PrefManager.getInstance(this);
        binding.title.setText(getString(R.string.venue_list));
        // binding.tvTopOffersNearYouLocation.setText(prefManager.getPreference(FUSED_LOCATION, ""));
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.ivNotification.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.iv_search_now_header:
                allVenueList.clear();
                isLoading = true;
                pageNumber = 1;
                getReservationVenue(false);
                break;

            case R.id.iv_cart:
                Intent cart = new Intent(this, MyCartReservationActivity.class);
                startActivity(cart);
                break;
            case R.id.iv_notification:
                Intent noti = new Intent(this, NotificationActivity.class);
                startActivity(noti);

                break;
        }
    }

    private void setAllVenueAdapter() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        venueHospitalityAdapter = new ReservationVenueAdapter(this,this, allVenueList, searchDate, searchGuest, this);
        binding.rvRestoList.setLayoutManager(layoutManager);
        binding.rvRestoList.setAdapter(venueHospitalityAdapter);
        binding.rvRestoList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            getReservationVenue(true);
                        }
                    }
                }
            }
        });

    }

    @Override
    public void onRefresh() {
        allVenueList.clear();
        isLoading = true;
        pageNumber = 1;
        getReservationVenue(false);
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        Intent topStore = new Intent(this, VenueDetailActivity.class);
        topStore.putExtra(STORE_ID, allVenueList.get(position).getVenue_id());
        topStore.putExtra(FROM_WHERE, HOME_HOSPITALITY);
        topStore.putExtra(CATEGORY_ID, "");
        startActivity(topStore);
    }


    private void getReservationVenue(boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");

            ReservationVenuesRequestModel allVenuesRequestModel = new ReservationVenuesRequestModel(latitude, longitude, String.valueOf(prefManager.getPreference(LOGIN_ID, 0)),
                    searchItem, String.valueOf(searchGuest), searchTime, searchDate, 2);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<ReservationVenuesResponseModel> call = apiInterface.getReservationVenue(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), allVenuesRequestModel, String.valueOf(pageNumber++));
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<ReservationVenuesResponseModel>() {
                @Override
                public void onResponse(Call<ReservationVenuesResponseModel> call, Response<ReservationVenuesResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            ReservationVenuesResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {
                                isLoading = false;
                                totalPage = responseModel.getVenues().getLast_page();
                                binding.tvStoreFound.setText("Found " + responseModel.getVenues().getTotal() + " " + getString(R.string.restaurants_bars));

                                if (responseModel.getVenues().getData() != null &&
                                        responseModel.getVenues().getData().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);
                                    // allVenueList.clear();
                                    allVenueList.addAll(responseModel.getVenues().getData());

                                    if (isMore) {
                                        if (venueHospitalityAdapter != null)
                                            venueHospitalityAdapter.notifyDataSetChanged();
                                    } else {
                                        setAllVenueAdapter();
                                    }

                                } else {
                                    if (!isMore) {
                                        binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    } else {
                                        binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                        allVenueList.clear();

                                        setAllVenueAdapter();
                                    }
                                }

                            } else {
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
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
                public void onFailure(Call<ReservationVenuesResponseModel> call, Throwable t) {
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
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        //  getTotalCartCount();
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

                               /* if (totalCartResponseModel.getTotal_carts() > 0) {
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
                                } else
                                    binding.tvCartCount.setVisibility(View.INVISIBLE);
                                binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));*/

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
}
