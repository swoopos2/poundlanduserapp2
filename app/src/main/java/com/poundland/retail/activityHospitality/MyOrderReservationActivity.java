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
import com.poundland.retail.activity.MyCartActivity;
import com.poundland.retail.activity.NearByDealsActivity;
import com.poundland.retail.activity.NotificationActivity;
import com.poundland.retail.activity.VenueDetailActivity;
import com.poundland.retail.activity.SpecialOfferDetailsActivity;
import com.poundland.retail.activityHospitality.adapter.MyOrderReservationAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityMyOrderReservationBinding;
import com.poundland.retail.dialog.DialogReservatioTimeDateEntry;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.OnSeasonExpireListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.CancelreservationRequestModel;
import com.poundland.retail.model.responseModel.MyOrderReservationResponseModel;
import com.poundland.retail.model.responseModel.MyOrderResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CANCEL_ORDER;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CART_RESERVATION;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.DD_MMM_YYYY_dash_in_middle;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_RESERVATION;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.NEW_BOOKING;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.ORDER_FOOD;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.RESERVATION_ID;
import static com.poundland.retail.interfaces.Constants.SELF_CHECKIN;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class MyOrderReservationActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner, SuccessActionListner, OnSeasonExpireListener {
    private ActivityMyOrderReservationBinding binding;
    private PrefManager prefManager;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private MyOrderReservationActivity instance = null;
    private List<MyOrderReservationResponseModel.ReservationsBean.DataBean> orderList;
    private DateFormat formate_HHmm = new SimpleDateFormat("HH:mm");
    private MyOrderReservationAdapter myOrderReservationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order_reservation);
        init();

    }

    @Override
    protected void onResume() {
        super.onResume();
        getTotalCartCount();
    }

    private void init() {
        instance = this;
        isLoading = true;
        pageNumber = 1;
        prefManager = PrefManager.getInstance(this);
        orderList = new ArrayList<>();
        binding.title.setText(getString(R.string.my_bookings));
        setListeners();
        setOrderListAdapter();
        myOrder(false);
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.ivAppLogo.setOnClickListener(this);
    }

    private void setOrderListAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        myOrderReservationAdapter = new MyOrderReservationAdapter(instance, orderList, this);
        binding.rvMyOrderReservation.setLayoutManager(layoutManager);
        binding.rvMyOrderReservation.setAdapter(myOrderReservationAdapter);
        binding.rvMyOrderReservation.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            myOrder(true);
                        }
                    }
                }
            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_app_logo:
                finish();
                break;
            case R.id.iv_cart:

                if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                    Intent intent = new Intent(this, MyCartHospitalityActivity.class);
                    intent.putExtra(RESERVATION_ID, "");
                    startActivity(intent);
                } else if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_RESERVATION) {
                    Intent intent = new Intent(this, MyCartReservationActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, MyCartActivity.class);
                    startActivity(intent);
                }


                break;
            case R.id.iv_notification:
                Intent noti = new Intent(this, NotificationActivity.class);
                startActivity(noti);

                break;
        }
    }

    private void myOrder(final boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<MyOrderReservationResponseModel> call = apiInterface.getMyReservationOrder(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), String.valueOf(pageNumber++));
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<MyOrderReservationResponseModel>() {
                @Override
                public void onResponse(Call<MyOrderReservationResponseModel> call, Response<MyOrderReservationResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            MyOrderReservationResponseModel responseModel = response.body();
                            if (responseModel != null) {
                                if (responseModel.isStatus()) {
                                    isLoading = false;
                                    totalPage = responseModel.getReservations().getLast_page();
                                    if (responseModel.getReservations().getData() != null && responseModel.getReservations().getData().size() > 0) {
                                        binding.tvNoDataFound.setVisibility(View.GONE);

                                        orderList.addAll(responseModel.getReservations().getData());
                                        if (isMore) {
                                            if (myOrderReservationAdapter != null)
                                                myOrderReservationAdapter.notifyDataSetChanged();
                                        } else {
                                            setOrderListAdapter();
                                        }

                                    } else {
                                        if (!isMore) {
                                            binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                        }
                                    }
                                } else {
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());
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

                    } catch (
                            Exception e) {
                        e.printStackTrace();
                        HelperClass.log("EXCC", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<MyOrderReservationResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }

    }

    @Override
    public void onRefresh() {

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
        return R.layout.activity_my_order_reservation;
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
                        Intent myOrder = new Intent(this, MyOrderReservationActivity.class);
                        //  startActivity(myOrder);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDrawerSelect(int position, int clickId) {

        if (clickId == ORDER_FOOD) {
            Intent orderFood = new Intent(instance, VenueDetailActivity.class);
            orderFood.putExtra(STORE_ID, orderList.get(position).getVenue_id());
            orderFood.putExtra(FROM_WHERE, FROM_RESERVATION);
            if (orderList.get(position).ismOrderStatus()) {
                orderFood.putExtra(RESERVATION_ID, orderList.get(position).getReservation_id());
            } else orderFood.putExtra(RESERVATION_ID, "");
            orderFood.putExtra(CATEGORY_ID, "");
            startActivity(orderFood);
        } else if (clickId == NEW_BOOKING) {

           /* new DialogReservatioGuestEntry(instance, formate_HHmm.format(Calendar.getInstance().getTime())
                    , orderList.get(position).getVenue_id(), HelperClass.getCurrentDate(DD_MMM_YYYY_dash_in_middle), 1, 0,
                    this).show();*/

            DialogReservatioTimeDateEntry bottomSheetDialog = DialogReservatioTimeDateEntry.newInstance(instance, instance,
                    formate_HHmm.format(Calendar.getInstance().getTime()),
                    orderList.get(position).getVenue_id(), HelperClass.getCurrentDate(DD_MMM_YYYY_dash_in_middle),
                    2, 0, orderList.size()> 0 ? ApiRequestUrl.BASE_URL_VENUE + orderList.get(position).getVenue().getVenue_images() : "",
                    orderList.size()> 0 ?  orderList.get(position).getVenue().getAddress_1() : "",
                    orderList.size()> 0 ? orderList.get(position).getVenue().getVenue_name() : "", new OnSeasonExpireListener() {
                        @Override
                        public void onSeasonExpire() {

                        }
                    });
            bottomSheetDialog.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");


        } else if (clickId == SELF_CHECKIN) {
            CancelreservationRequestModel requestModel = new CancelreservationRequestModel(orderList.get(position).getId());
            selfCheckIn(requestModel);
        }
        else if (clickId == CANCEL_ORDER) {

            DialogUtils.showAlertDialog(instance, getString(R.string.cancel_booking_message), new OnDialogClickListener() {
                @Override
                public void onPositiveClick() {
                    CancelreservationRequestModel requestModel = new CancelreservationRequestModel(orderList.get(position).getId());
                    cancelOrder(requestModel);
                }

                @Override
                public void onNegativeClick() {

                }
            });
        }
    }

    private void selfCheckIn(CancelreservationRequestModel requestModel) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<MyOrderResponseModel> call = apiInterface.selfCheckIn(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<MyOrderResponseModel>() {
                @Override
                public void onResponse(Call<MyOrderResponseModel> call, Response<MyOrderResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            MyOrderResponseModel responseModel = response.body();
                            if (responseModel != null) {
                                if (responseModel.isStatus()) {
                                    DialogUtils.showAlertDialogWithSingleButton(instance, responseModel.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {

                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });


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
                        HelperClass.log("", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<MyOrderResponseModel> call, Throwable t) {
                    //   binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            //  binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void cancelOrder(CancelreservationRequestModel requestModel) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<MyOrderResponseModel> call = apiInterface.cancelBooking(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<MyOrderResponseModel>() {
                @Override
                public void onResponse(Call<MyOrderResponseModel> call, Response<MyOrderResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            MyOrderResponseModel responseModel = response.body();
                            if (responseModel != null) {
                                if (responseModel.isStatus()) {
                                    DialogUtils.showAlertDialogWithSingleButton(instance, responseModel.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            pageNumber = 1;
                                            orderList.clear();
                                            myOrder(false);
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });


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
                        HelperClass.log("", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<MyOrderResponseModel> call, Throwable t) {
                    //   binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            //  binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    @Override
    public void onSuccessActionListner() {
        getTotalCartCount();
    }

    @Override
    public void onSeasonExpire() {
        logOut(instance);
    }
}
