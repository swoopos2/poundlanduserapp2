package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityTrackPackageBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.CancelOrderRequestModel;
import com.poundland.retail.model.responseModel.MyOrderResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.formatDateMonthHr;
import static com.poundland.retail.appUtils.HelperClass.formatddMMMMyyyy;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.ADDRESS_GET;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CANCEL_DATE;
import static com.poundland.retail.interfaces.Constants.CANCEL_PRODUCT_MESSAGE;
import static com.poundland.retail.interfaces.Constants.CANCEL_STATUS;
import static com.poundland.retail.interfaces.Constants.COLLECT_TIME;
import static com.poundland.retail.interfaces.Constants.DELIVERY_TYPE;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.ESTIMATED_DELIVERY;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.IMAGE_LINK;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.ORDER_DATE;
import static com.poundland.retail.interfaces.Constants.ORDER_DETAIL_ID;
import static com.poundland.retail.interfaces.Constants.ORDER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.QUANTITY;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.TRACK_DATA;
import static com.poundland.retail.interfaces.Constants.TRACK_STATUS;
import static com.poundland.retail.interfaces.Constants.VENUE_ADDRESS;
import static com.poundland.retail.interfaces.Constants.VENUE_ID;
import static com.poundland.retail.interfaces.Constants.VENUE_TYPE;

public class TrackPackageActivity extends BaseActivity implements View.OnClickListener {

    private static final int VENUE_HOSPITALITY = 1;
    private static final int VENUE_RETAIL = 0;
    private ActivityTrackPackageBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private String image_link;
    private String venueId;
    private String offerId = "";
    private String productId;
    private String orderDate = "";
    private String cancelDate = "";
    private int trackStatus;
    private int quantity;
    private int orderId;
    private int orderDetailId;
    private String deliveryType = "";
    private String cancelStatus = "";
    private String NEW_ORDER = "0";
    private String CANCELED_ORDER = "1";
    private String COMPLETED_ORDER = "4";
    private String ORDER_TYPE;
    private MyOrderResponseModel.OrdersBean.DataBean.CustomerAddressBean customer_address;
    private String venueAdress;
    private TrackPackageActivity instance = null;
    private String collectTime;
    private List<MyOrderResponseModel.OrdersBean.DataBean.TrackingDataBean> trackingData;
    private int venueType;
    private String estiDeliveryDays;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_track_package);

        init();
        setListeners();
    }

    private void init() {
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        instance = this;
        trackingData = new ArrayList<>();
        quantity = Integer.parseInt(getIntent().getStringExtra(QUANTITY));
        image_link = getIntent().getStringExtra(IMAGE_LINK);
        venueId = getIntent().getStringExtra(VENUE_ID);
        venueAdress = getIntent().getStringExtra(VENUE_ADDRESS);
        offerId = getIntent().getStringExtra(OFFER_ID);
        productId = getIntent().getStringExtra(PRODUCT_ID);
        customer_address = getIntent().getExtras().getParcelable(ADDRESS_GET);
        orderId = Integer.parseInt(getIntent().getStringExtra(ORDER_ID));
        orderDetailId = Integer.parseInt(getIntent().getStringExtra(ORDER_DETAIL_ID));
        trackStatus = Integer.parseInt(getIntent().getStringExtra(TRACK_STATUS));
        orderDate = getIntent().getStringExtra(ORDER_DATE);
        cancelDate = getIntent().getStringExtra(CANCEL_DATE);
        deliveryType = getIntent().getStringExtra(DELIVERY_TYPE);
        estiDeliveryDays = getIntent().getStringExtra(ESTIMATED_DELIVERY);
        venueType = getIntent().getIntExtra(VENUE_TYPE, 2212);
        ORDER_TYPE = getIntent().getStringExtra(FROM_WHERE);
        collectTime = getIntent().getStringExtra(COLLECT_TIME);
        cancelStatus = getIntent().getStringExtra(CANCEL_STATUS);
        trackingData = getIntent().getExtras().getParcelableArrayList(TRACK_DATA);


        if (ORDER_TYPE.equals(CANCELED_ORDER)) {
            binding.vDiv.setVisibility(View.GONE);
            binding.tvAcknowledge.setVisibility(View.GONE);
            binding.vDivid.setVisibility(View.GONE);
            binding.tvShiped.setVisibility(View.GONE);
            binding.vDiv1.setVisibility(View.GONE);
            binding.tvCompleted.setVisibility(View.GONE);
            binding.vRefunded.setVisibility(View.GONE);
            binding.tvRefunded.setVisibility(View.GONE);
            binding.vCancel.setVisibility(View.VISIBLE);
            binding.tvCancelled.setVisibility(View.VISIBLE);
            binding.tvStatus.setText(getString(R.string.cancelled) + " " + HelperClass.formatddMMMMyyyy(cancelDate));
            binding.tvCancelled.setText(getString(R.string.cancelled) + " At " + formatDateMonthHr(cancelDate));
            binding.tvCancel.setVisibility(View.GONE);
        } else if (ORDER_TYPE.equals(COMPLETED_ORDER)) {          /// ORDER COMPLETED
            binding.vCancel.setVisibility(View.GONE);
            binding.tvCancelled.setVisibility(View.GONE);
            if (trackStatus == 9) {
                binding.tvStatus.setText(cancelStatus);
                binding.vDiv.setVisibility(View.GONE);
                binding.tvAcknowledge.setVisibility(View.GONE);
                binding.vDivid.setVisibility(View.GONE);
                binding.tvShiped.setVisibility(View.GONE);
                binding.vDiv1.setVisibility(View.GONE);
                binding.tvCompleted.setVisibility(View.GONE);
            } else if (trackStatus == 4) {
                binding.vDiv.setVisibility(View.VISIBLE);
                binding.tvAcknowledge.setVisibility(View.VISIBLE);
                binding.vDivid.setVisibility(View.VISIBLE);
                binding.tvShiped.setVisibility(View.VISIBLE);
                binding.vDiv1.setVisibility(View.VISIBLE);
                binding.tvCompleted.setVisibility(View.VISIBLE);
                binding.vRefunded.setVisibility(View.GONE);
                binding.tvRefunded.setVisibility(View.GONE);
                binding.tvStatus.setText(getString(R.string.delivered) + " " + cancelDate);
            } else if (trackStatus == 0 && cancelStatus.equals(getString(R.string.return_refund))) {
                binding.vDiv.setVisibility(View.VISIBLE);
                binding.tvAcknowledge.setVisibility(View.VISIBLE);
                binding.vDivid.setVisibility(View.VISIBLE);
                binding.tvShiped.setVisibility(View.VISIBLE);
                binding.vDiv1.setVisibility(View.VISIBLE);
                binding.tvCompleted.setVisibility(View.VISIBLE);
                binding.vRefunded.setVisibility(View.GONE);
                binding.tvRefunded.setVisibility(View.GONE);
                binding.tvStatus.setText(getString(R.string.delivered) + " " + cancelDate);
            } else if (trackStatus == 8 && cancelStatus.equals(getString(R.string.return_refund))) {
                binding.vDiv.setVisibility(View.VISIBLE);
                binding.tvAcknowledge.setVisibility(View.VISIBLE);
                binding.vDivid.setVisibility(View.VISIBLE);
                binding.tvShiped.setVisibility(View.VISIBLE);
                binding.vDiv1.setVisibility(View.VISIBLE);
                binding.tvCompleted.setVisibility(View.VISIBLE);
                binding.vRefunded.setVisibility(View.GONE);
                binding.tvRefunded.setVisibility(View.GONE);
                binding.tvStatus.setText(getString(R.string.delivered) + " " + cancelDate);
            } else if (trackStatus == 5 && cancelStatus.equals(getString(R.string.refunded))) {
                binding.tvStatus.setText(getString(R.string.refund_completed));
                binding.vDiv.setVisibility(View.VISIBLE);
                binding.tvAcknowledge.setVisibility(View.VISIBLE);
                binding.vDivid.setVisibility(View.VISIBLE);
                binding.tvShiped.setVisibility(View.VISIBLE);
                binding.vDiv1.setVisibility(View.VISIBLE);
                binding.tvCompleted.setVisibility(View.VISIBLE);
            } else {
                binding.tvStatus.setText(getString(R.string.refund_completed));
                binding.vDiv.setVisibility(View.GONE);
                binding.tvAcknowledge.setVisibility(View.GONE);
                binding.vDivid.setVisibility(View.GONE);
                binding.tvShiped.setVisibility(View.GONE);
                binding.vDiv1.setVisibility(View.GONE);
                binding.tvCompleted.setVisibility(View.GONE);
            }
            binding.tvCancel.setVisibility(View.GONE);
        } else {                           ////////  New Order
            if (venueType == VENUE_RETAIL) {
                if (trackStatus == 0 || trackStatus == 8 && !TextUtils.isEmpty(cancelStatus)) {
                    binding.tvCancel.setVisibility(View.VISIBLE);
                } else binding.tvCancel.setVisibility(View.GONE);
            } else if (venueType == VENUE_HOSPITALITY) {
                binding.tvCancel.setVisibility(View.GONE);
            }

            binding.vRefunded.setVisibility(View.GONE);
            binding.tvRefunded.setVisibility(View.GONE);
            binding.vCancel.setVisibility(View.GONE);
            binding.tvCancelled.setVisibility(View.GONE);
            if (deliveryType.equalsIgnoreCase(getString(R.string.home_delivery))) {
                binding.tvStatus.setText(getString(R.string.arriving) + " till " + formatddMMMMyyyy(estiDeliveryDays));
            }else if (deliveryType.equalsIgnoreCase(getString(R.string.reservation))) {
                binding.tvStatus.setText(getString(R.string.table_order) + " " + formatddMMMMyyyy(estiDeliveryDays));
            } else {
                if (TextUtils.isEmpty(estiDeliveryDays)){
                    binding.tvStatus.setText(getString(R.string.collect_after) + " " + collectTime);
                }else binding.tvStatus.setText((getString(R.string.collect) + " on " + formatddMMMMyyyy(estiDeliveryDays)));

            }

            binding.tvCancel.setText(cancelStatus);
        }

        binding.tvQuantity.setText(" Qty : " + quantity + " ");
        Glide.with(this).load(image_link).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(binding.ivImageProduct);
        // if 0 >> ordered today        1 >>  reject      2 >> acknowledge        3 >> out for delivered/shipped         4 >>  deliverd
        switch (trackStatus) {
            case 0:
                binding.tvDayOfOrder.setChecked(true);
                binding.tvDayOfOrder.setTextColor(getResources().getColor(R.color.color_pink));
                if (trackStatus == 0 && cancelStatus.equals(getString(R.string.return_refund))) {
                    binding.tvAcknowledge.setChecked(true);
                    binding.tvAcknowledge.setTextColor(getResources().getColor(R.color.color_saffron));
                    binding.tvShiped.setChecked(true);
                    binding.tvShiped.setTextColor(getResources().getColor(R.color.light_green));
                    binding.tvCompleted.setChecked(true);
                    binding.tvCompleted.setTextColor(getResources().getColor(R.color.app_header_color));
                } else if (cancelStatus.equals(getString(R.string.cancel_pending))) {
                    binding.vDiv.setVisibility(View.GONE);
                    binding.tvAcknowledge.setVisibility(View.GONE);
                    binding.vDivid.setVisibility(View.GONE);
                    binding.tvShiped.setVisibility(View.GONE);
                    binding.vDiv1.setVisibility(View.GONE);
                    binding.tvCompleted.setVisibility(View.GONE);
                    binding.vRefunded.setVisibility(View.GONE);
                    binding.tvRefunded.setVisibility(View.GONE);
                    binding.vCancel.setVisibility(View.VISIBLE);
                    binding.tvCancelled.setVisibility(View.VISIBLE);
                    binding.tvStatus.setText(getString(R.string.cancel_process));
                }
                break;
            case 1:
                binding.tvDayOfOrder.setChecked(true);
                binding.tvDayOfOrder.setTextColor(getResources().getColor(R.color.color_pink));
                binding.tvCancelled.setChecked(true);
                binding.tvCancelled.setTextColor(getResources().getColor(R.color.color_red));
                break;
            case 2:
                binding.tvDayOfOrder.setChecked(true);
                binding.tvDayOfOrder.setTextColor(getResources().getColor(R.color.color_pink));
                binding.tvAcknowledge.setChecked(true);
                binding.tvAcknowledge.setTextColor(getResources().getColor(R.color.color_saffron));
                break;
            case 3:
                binding.tvDayOfOrder.setChecked(true);
                binding.tvDayOfOrder.setTextColor(getResources().getColor(R.color.color_pink));
                binding.tvAcknowledge.setChecked(true);
                binding.tvAcknowledge.setTextColor(getResources().getColor(R.color.color_saffron));
                binding.tvShiped.setChecked(true);
                binding.tvShiped.setTextColor(getResources().getColor(R.color.light_green));
                break;
            case 4:
                binding.tvDayOfOrder.setChecked(true);
                binding.tvDayOfOrder.setTextColor(getResources().getColor(R.color.color_pink));
                binding.tvAcknowledge.setChecked(true);
                binding.tvAcknowledge.setTextColor(getResources().getColor(R.color.color_saffron));
                binding.tvShiped.setChecked(true);
                binding.tvShiped.setTextColor(getResources().getColor(R.color.light_green));
                binding.tvCompleted.setChecked(true);
                binding.tvCompleted.setTextColor(getResources().getColor(R.color.app_header_color));
                break;
            case 5:
                binding.tvDayOfOrder.setChecked(true);
                binding.tvDayOfOrder.setTextColor(getResources().getColor(R.color.color_pink));
                binding.tvAcknowledge.setChecked(true);
                binding.tvAcknowledge.setTextColor(getResources().getColor(R.color.color_saffron));
                binding.tvShiped.setChecked(true);
                binding.tvShiped.setTextColor(getResources().getColor(R.color.light_green));
                binding.tvCompleted.setChecked(true);
                binding.tvCompleted.setTextColor(getResources().getColor(R.color.app_header_color));
                binding.tvRefunded.setChecked(true);
                binding.tvRefunded.setTextColor(getResources().getColor(R.color.color_red));
                break;
            case 8:
                binding.tvDayOfOrder.setChecked(true);
                binding.tvDayOfOrder.setTextColor(getResources().getColor(R.color.color_pink));
                if (cancelStatus.equals(getString(R.string.cancel_pending))) {
                    binding.vDiv.setVisibility(View.GONE);
                    binding.tvAcknowledge.setVisibility(View.GONE);
                    binding.vDivid.setVisibility(View.GONE);
                    binding.tvShiped.setVisibility(View.GONE);
                    binding.vDiv1.setVisibility(View.GONE);
                    binding.tvCompleted.setVisibility(View.GONE);
                    binding.vRefunded.setVisibility(View.GONE);
                    binding.tvRefunded.setVisibility(View.GONE);
                    binding.vCancel.setVisibility(View.VISIBLE);
                    binding.tvCancelled.setVisibility(View.VISIBLE);
                    binding.tvStatus.setText(getString(R.string.cancel_process));
                } else if (trackStatus == 8 && cancelStatus.equals(getString(R.string.return_refund))) {
                    binding.tvAcknowledge.setChecked(true);
                    binding.tvAcknowledge.setTextColor(getResources().getColor(R.color.color_saffron));
                    binding.tvShiped.setChecked(true);
                    binding.tvShiped.setTextColor(getResources().getColor(R.color.light_green));
                    binding.tvCompleted.setChecked(true);
                    binding.tvCompleted.setTextColor(getResources().getColor(R.color.app_header_color));
                }
                break;
            case 9:
                binding.tvDayOfOrder.setChecked(true);
                binding.tvDayOfOrder.setTextColor(getResources().getColor(R.color.color_pink));
                if (cancelStatus.equals(getString(R.string.return_rejected))) {
                    binding.tvStatus.setText(String.format("%s %s", getString(R.string.delivered), cancelDate));
                    binding.vDiv.setVisibility(View.VISIBLE);
                    binding.tvAcknowledge.setVisibility(View.VISIBLE);
                    binding.vDivid.setVisibility(View.VISIBLE);
                    binding.tvShiped.setVisibility(View.VISIBLE);
                    binding.vDiv1.setVisibility(View.VISIBLE);
                    binding.tvCompleted.setVisibility(View.VISIBLE);
                    binding.vRefunded.setVisibility(View.GONE);
                    binding.tvRefunded.setVisibility(View.GONE);

                    binding.tvAcknowledge.setChecked(true);
                    binding.tvAcknowledge.setTextColor(getResources().getColor(R.color.color_saffron));
                    binding.tvShiped.setChecked(true);
                    binding.tvShiped.setTextColor(getResources().getColor(R.color.light_green));
                    binding.tvCompleted.setChecked(true);
                    binding.tvCompleted.setTextColor(getResources().getColor(R.color.app_header_color));
                }

                break;

        }
        if (deliveryType.equalsIgnoreCase(getString(R.string.home_delivery))) {
            if (customer_address != null) {
                binding.tvShiped.setText(getString(R.string.shiped));
                binding.tvShippingAddressLabel.setText(getString(R.string.shipping_address));
                String address = "" + customer_address.getArea() + "," + customer_address.getLandmark() + "," + customer_address.getCity() + "," + customer_address.getCountry() + "," + customer_address.getPincode();
                address = address.replace("null", "");
                binding.tvShippingAddress.setText(address);

            } else {
                binding.tvShiped.setText(getString(R.string.packed));
                binding.tvShippingAddressLabel.setText(getString(R.string.venue_address));
                binding.tvShippingAddress.setText(venueAdress);
            }
        } else {
            binding.tvShiped.setText(getString(R.string.ready));
            binding.tvShippingAddressLabel.setText(getString(R.string.venue_address));
            binding.tvShippingAddress.setText(venueAdress);
        }

        binding.tvDayOfOrder.setText(getString(R.string.placed) + " At " + formatDateMonthHr(orderDate));

        for (int i = 0; i < trackingData.size(); i++) {
            if (trackingData.get(i).getStatus() == 2) {
                binding.tvAcknowledge.setText(getString(R.string.acknowledged) + " At " + formatDateMonthHr(trackingData.get(i).getCreated_at()));
            } else if (trackingData.get(i).getStatus() == 3) {
                if (deliveryType.equalsIgnoreCase(getString(R.string.home_delivery))) {
                    if (customer_address != null) {
                        binding.tvShiped.setText(getString(R.string.shiped) + " At " + formatDateMonthHr(trackingData.get(i).getCreated_at()));
                    } else {
                        binding.tvShiped.setText(getString(R.string.packed) + " At " + formatDateMonthHr(trackingData.get(i).getCreated_at()));
                    }
                } else {
                    binding.tvShiped.setText(getString(R.string.ready) + " At " + formatDateMonthHr(trackingData.get(i).getCreated_at()));
                }

            } else if (trackingData.get(i).getStatus() == 4) {
                binding.tvCompleted.setText(getString(R.string.completed) + " At " + formatDateMonthHr(trackingData.get(i).getCreated_at()));
            } else if (trackingData.get(i).getStatus() == 5) {
                if (!cancelStatus.equals(getString(R.string.return_pending)))
                    binding.tvRefunded.setText(getString(R.string.refunded) + " At " + formatDateMonthHr(trackingData.get(i).getCreated_at()));
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.tvViewOrderDetail.setOnClickListener(this);
        binding.tvCancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_view_order_detail:
                /*Intent topProduct = new Intent(this, SingleProductDetailActivity.class); opProduct.putExtra(STORE_ID, venueId);  topProduct.putExtra(PRODUCT_ID, productId);  topProduct.putExtra(OFFER_ID, offerId);  startActivity(topProduct);*/
                finish();
                break;

            case R.id.tv_cancel:

                if (cancelStatus.equals(getString(R.string.cancel))) {

                    DialogUtils.cancelOrderDialog(instance, getString(R.string.cancel_order), new OnDialogClickListener() {
                        @Override
                        public void onPositiveClick() {
                            List<Integer> integers = new ArrayList<>();
                            integers.add(orderId);
                            CancelOrderRequestModel requestModel = new CancelOrderRequestModel(orderDetailId, CANCEL_PRODUCT_MESSAGE, integers);
                            cancelOrder(requestModel);
                        }

                        @Override
                        public void onNegativeClick() {

                        }
                    });


                } else if (cancelStatus.equals(getString(R.string.cancel_rejected))) {

                    DialogUtils.cancelOrderDialog(instance, getString(R.string.cancel_order_again),
                            new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    List<Integer> integers = new ArrayList<>();
                                    integers.add(orderId);
                                    CancelOrderRequestModel requestModel = new CancelOrderRequestModel(orderDetailId, CANCEL_PRODUCT_MESSAGE, integers);
                                    cancelOrder(requestModel);
                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });

                }
                break;
        }
    }


    private void cancelOrder(CancelOrderRequestModel requestModel) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<MyOrderResponseModel> call = apiInterface.cancelOrder(prefManager.getPreference(AUTH_TOKEN, ""),
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
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());
                                    cancelStatus = "Cancel ";
                                    binding.tvCancel.setAlpha(.5f);
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

    private void setMargins(int left, int top, int right, int bottom) {
        int sizeInDP = 50;

        int marginInDp = (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, sizeInDP, getResources()
                        .getDisplayMetrics());

        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        params.setMargins(left, marginInDp, right, bottom);
        binding.llShippingAddress.setLayoutParams(params);
    }

}