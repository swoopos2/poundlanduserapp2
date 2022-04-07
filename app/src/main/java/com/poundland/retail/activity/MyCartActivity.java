package com.poundland.retail.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.MyCartAdapter;
import com.poundland.retail.adapter.ShippingMethodAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityMyCartBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.UpdateCartListner;
import com.poundland.retail.model.requestModel.AddMobileCartQuantityRequestModel;
import com.poundland.retail.model.requestModel.CheckOutRequestModel;
import com.poundland.retail.model.requestModel.CouponRequestModel;
import com.poundland.retail.model.requestModel.GetCartWithSummaryRequestModel;
import com.poundland.retail.model.requestModel.ShippingMethodRequestModel;
import com.poundland.retail.model.responseModel.CheckOutResponseModel;
import com.poundland.retail.model.responseModel.CouponResponseModel;
import com.poundland.retail.model.responseModel.GetCartWithSummaryResponseModel;
import com.poundland.retail.model.responseModel.ShippingMethodResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.log;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.appUtils.HelperClass.toBase64;
import static com.poundland.retail.appUtils.HelperClass.toast;
import static com.poundland.retail.interfaces.Constants.ACTIVE_PAYMENT_TYPE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ID;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ONE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_THREE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_TWO;
import static com.poundland.retail.interfaces.Constants.ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.BARCODE_ID;
import static com.poundland.retail.interfaces.Constants.CARD;
import static com.poundland.retail.interfaces.Constants.CARD_CVV;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_MONTH;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_YEAR;
import static com.poundland.retail.interfaces.Constants.CARD_NUMBER;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_ORDER;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_TYPE;
import static com.poundland.retail.interfaces.Constants.DD_MMM_YYYY_dash_in_middle;
import static com.poundland.retail.interfaces.Constants.DELETE;
import static com.poundland.retail.interfaces.Constants.EEEE;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_MY_CART;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.HHmm;
import static com.poundland.retail.interfaces.Constants.HOME_RETAIL;
import static com.poundland.retail.interfaces.Constants.IS_SAVE_CARD;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.LOYA_STAMP_VOUC_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.NONE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVITY_COUPON_CALLBACK_CODE;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVITY_COUPON_CALLBACK_DATA;
import static com.poundland.retail.interfaces.Constants.OPEN_ADD_ADDRESS_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_CHANGE_ADDRESS_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_LOYALTY_STAMP_VOUCHER_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PAYMENT_DETAIL_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_CREATE_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_VERIFY_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.PAYMENT_DETAIL_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.PAYMENT_GATEWAY_ACTIVE_SAVED_CARD;
import static com.poundland.retail.interfaces.Constants.PAYMENT_GATEWAY_STRIPE;
import static com.poundland.retail.interfaces.Constants.PIN_CREATE_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.POSITION;
import static com.poundland.retail.interfaces.Constants.POSTAL_CODE;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.RESERVATION_ID;
import static com.poundland.retail.interfaces.Constants.SHIPPING_METHOD;
import static com.poundland.retail.interfaces.Constants.SHIPPING_METHOD_UNCHEKED;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.UPDATE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;

public class MyCartActivity extends BaseActivity implements View.OnClickListener, UpdateCartListner, SwipeRefreshLayout.OnRefreshListener, RadioGroup.OnCheckedChangeListener, DrawerListner {
    private final int ONLOAD = 303;
    private final int FREE_HOME_DELIVERY = 305;
    private final int PAID_HOME_DELIVERY = 306;
    private final int RB_CLICK_COLLECT = 307;
    private ActivityMyCartBinding binding;
    private PrefManager prefManager;
    private String venue_id = "";
    private MyCartActivity instance = null;
    private String deliveryMethod = "";
    private String venueLatitude = "";
    private String venueLongitude = "";
    private double totalLoyaltyValue;
    private String PAYMENT_MODE = CARD;
    private int REORDER_STATUS = 0;
    private String addressId;
    private String cardId = "";
    private String payment_gatway = PAYMENT_GATEWAY_STRIPE;
    private String cardNumber = "";
    private String cardExpiryMonth = "";
    private String cardExpiryYear = "";
    private String cardCVV = "";
    private String IS_GIFT = "0";
    private GetCartWithSummaryResponseModel responseModel;
    private GetCartWithSummaryResponseModel.CustAddressBean customerAddress;
    private List<GetCartWithSummaryResponseModel.CartsBean> myCartList;
    private List<GetCartWithSummaryResponseModel.DefaultCardsBean> myCardList;
    private String totalAmountPayable = "0.00";
    private String couponCode;
    private double enteredLoyaltyPoint = 0.0;
    private double couponPrice = 0.0;
    private String DEFAULT_DELIVERY_CHARGE = "0.00";
    private String deliveryCharge = DEFAULT_DELIVERY_CHARGE;
    private String LOYALTY = "loyalty";

    private String address_one = "";
    private String address_two = "";
    private String address_three = "";
    private String postal_code = "";
    private boolean isSaveCard;
    private boolean isAddNewCard;
    private String format = "HH:mm:ss";
    private SimpleDateFormat sdf = new SimpleDateFormat(format);
    private String currentTime;
    private String shippingId = "";
    private KProgressHUD dialogGlobal = null;
    private String app_type = "LOCAL";
    private int IS_HOSPITALITY = 0;
    private Calendar calendar;
    private SimpleDateFormat sendDateFormat;
    private SimpleDateFormat dateFormaterClickNcollect;
    private DatePickerDialog datePickerDialog;
    private String startDate;
    private int maxDayShowInCalendar;
    private boolean isPopup;
    private ListPopupWindow popupWindow;
    private List<ShippingMethodResponseModel.ShippingDetailsBean> shippingList = new ArrayList<>();
    private ShippingMethodAdapter shippingMethodAdapter;
    private MyCartAdapter myCartAdapter;

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            calendar.set(year, monthOfYear, dayOfMonth);
            startDate = sendDateFormat.format(calendar.getTime());

            if (responseModel.getVenue_week_times() != null && responseModel.getVenue_week_times().size() > 0) {
                for (int i = 0; i < responseModel.getVenue_week_times().size(); i++) {
                    if (responseModel.getVenue_week_times().get(i).getDay_name().contains(dateFormaterClickNcollect.format(calendar.getTime()))) {
                        binding.tvSpecificDate.setText(startDate);

                        try {
                            SimpleDateFormat df = new SimpleDateFormat(HHmm);
                            Date d = df.parse(HelperClass.getHH_mm_from_YYYY_MM_dd_HH_mm(responseModel.getVenue_week_times().get(i).getOpening_time()));
                            Calendar cal = Calendar.getInstance();

                            if (HelperClass.getComparedDate(HelperClass.formatDD_MMM_YYYY_to_YYYY_MM_DD(startDate))) {
                                // Calendar now = Calendar.getInstance();
                                cal.add(Calendar.MINUTE, TextUtils.isEmpty(responseModel.getCarts_summary().getCollection_time()) ? 30 : Integer.parseInt(responseModel.getCarts_summary().getCollection_time()));
                            } else {
                                cal.setTime(d);
                                cal.add(Calendar.MINUTE, TextUtils.isEmpty(responseModel.getCarts_summary().getCollection_time()) ? 30 : Integer.parseInt(responseModel.getCarts_summary().getCollection_time()));

                            }
                            if (responseModel.getVenue_week_times().get(i).getIs_venue_open() == 1) {
                                binding.tvSpecificDateMessage.setText(getString(R.string.specific_date_message) + " " + df.format(cal.getTime()));
                                binding.tvSpecificDateMessage.setTextColor(ContextCompat.getColor(instance, R.color.app_header_color_dark));
                            } else {
                                binding.tvSpecificDateMessage.setText("Venue is closed on " + startDate);
                                binding.tvSpecificDateMessage.setTextColor(ContextCompat.getColor(instance, R.color.color_light_red));
                                binding.tvSpecificDate.setText(getString(R.string.specific_date));
                            }
                        } catch (ParseException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Log.e("Day_Found", " Not");
                    }
                }
            }
        }
    };
    private boolean isOutOfStock;
    private boolean isItemInStock;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_cart);
        dialogGlobal = DialogUtils.getCustomDialog(this);
        sendDateFormat = new SimpleDateFormat(DD_MMM_YYYY_dash_in_middle, Locale.getDefault());
        dateFormaterClickNcollect = new SimpleDateFormat(EEEE, Locale.getDefault());
        calendar = Calendar.getInstance();
        init();
        getCartWithSummaryApi(NONE, "", "");
        setListeners();

    }

    private void init() {
        instance = this;
        prefManager = PrefManager.getInstance(this);
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        myCartList = new ArrayList<>();
        myCardList = new ArrayList<>();
        binding.tbMyCart.tvTitle.setText(getString(R.string.cart));


        binding.tvSpecificDate.setVisibility(View.GONE);
        binding.tvSpecificDateMessage.setVisibility(View.GONE);
        // binding.cbSpecificDate.setVisibility(View.GONE);

        binding.tvCouponAppliedLbl.setVisibility(View.GONE);
        binding.tvCouponAppliedAmount.setVisibility(View.GONE);
        binding.ivCancelCouponApplied.setVisibility(View.GONE);

        binding.tbMyCart.ivCart.setVisibility(View.GONE);
        binding.tvSelectedValue.setVisibility(View.GONE);
        binding.ivCancelLoyalty.setVisibility(View.GONE);
        binding.rlMyCartAddress.setVisibility(View.GONE);
        binding.tvCheckout.setVisibility(View.INVISIBLE);
        binding.llInfo.setVisibility(View.GONE);
        binding.tvProductDetails.setVisibility(View.GONE);
        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.tbMyCart.ivNotification.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.LEFT_OF, R.id.iv_notification);

        binding.tbMyCart.ivNotification.setLayoutParams(params); //function for adjust view if you want some view gone visible
        binding.tbMyCart.ivNotification.setVisibility(View.GONE);
    }

    private void setListeners() {
        binding.tbMyCart.tvTitle.setOnClickListener(this);
        binding.btnSelectCoupon.setOnClickListener(this);
        binding.btnApplyCoupon.setOnClickListener(this);
        binding.rlCards.setOnClickListener(this);
        binding.tbMyCart.ivBack.setOnClickListener(this);
        binding.tbMyCart.ivNotification.setOnClickListener(this);
        binding.tvChangeAddress.setOnClickListener(this);
        binding.ivCancelCouponApplied.setOnClickListener(this);
        binding.tvInputLoyalty.setOnClickListener(this);
        binding.tvUseLoyalty.setOnClickListener(this);
        binding.tvCheckout.setOnClickListener(this);
        binding.tvStartShopping.setOnClickListener(this);
        binding.ivCancelLoyalty.setOnClickListener(this);
        binding.tvSpecificDate.setOnClickListener(this);
        binding.radioGroup.setOnCheckedChangeListener(this);
        binding.tvAddMore.setOnClickListener(this);
    }

    private void setCartDataAdapter() {
        myCartAdapter = new MyCartAdapter(this, myCartList, this, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvMyCart.setLayoutManager(layoutManager);
        binding.rvMyCart.setAdapter(myCartAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_cancel_coupon_applied:

                cancelCoupon();
                break;

            case R.id.tv_add_more:

                if (myCartList.size() > 0)
                    openVenue(0);
                break;

            case R.id.rl_cards:
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    setSpinnerForCards();
                    isPopup = true;
                }
                break;
            case R.id.btn_select_coupon:
                if (myCartList != null && myCartList.size() > 0) {
                    Intent coupon = new Intent(this, CouponActivity.class);
                    coupon.putExtra(FROM_WHERE, getString(R.string.cart));
                    coupon.putExtra(VENUE_ID, String.valueOf(myCartList.get(0).getVenue_id()));
                    coupon.putExtra(RESERVATION_ID, "0");
                    startActivityForResult(coupon, OPEN_ACTIVITY_COUPON_CALLBACK_CODE);
                }

                break;

            case R.id.btn_apply_coupon:
                if (binding.etCouponCode.getText().toString().length() > 3) {
                    applyCoupon("", String.valueOf(myCartList.get(0).getVenue_id()), binding.etCouponCode.getText().toString(),
                            String.valueOf(prefManager.getPreference(LOGIN_ID, 0)), "0", deliveryCharge);
                }

                break;


            case R.id.tv_specific_date:
                showDatePickerDialog(calendar);
                break;

            case R.id.tv_change_address:
                if (binding.tvChangeAddress.getText().toString().equals(getString(R.string.change_address))) {
                    Intent intent = new Intent(this, AddressActivity.class);
                    intent.putExtra(FROM_WHERE, getString(R.string.cart));
                    startActivityForResult(intent, OPEN_CHANGE_ADDRESS_ACTIVITY);
                } else if (binding.tvChangeAddress.getText().toString().equals(getString(R.string.add_address))) {
                    Intent intent = new Intent(this, AddAddressActivity.class);
                    intent.putExtra(FROM_WHERE, FROM_MY_CART);
                    startActivityForResult(intent, OPEN_ADD_ADDRESS_ACTIVITY);
                } else {
                    Uri gmmIntentUri = Uri.parse("geo:" + venueLatitude + "," + venueLongitude);
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    if (mapIntent.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent);
                    }
                }
                break;

            case R.id.tv_use_loyalty:
                if (isItemInStock) {
                    showLoyaltyDialog();
                } else showOutOfStockDialog(getString(R.string.cart_item_out_of_stock));

                break;

            case R.id.iv_notification:
                Intent noti = new Intent(this, NotificationActivity.class);
                startActivity(noti);
                break;

            case R.id.iv_cancel_loyalty:
                closeLoyalty();

                break;
            case R.id.tv_checkout:
                if (validateDeliveryMethod()) {
                    if (Double.parseDouble(totalAmountPayable) > 0) {

                        if (isValid()) {

                            Intent stamp = new Intent(instance, VerifyPinActivity.class);
                            stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                            stamp.putExtra(POSITION, -1);
                            startActivityForResult(stamp, OPEN_PIN_VERIFY_ACTIVITY);
                        }

                    } else {
                        payment_gatway = "zero";   /// if totalAmountPayable==0 then it will be blank
                        PAYMENT_MODE = LOYALTY;
                        checkOut();
                    }
                }
                break;
            case R.id.tv_start_shopping:
                if (dialogGlobal != null)
                    dialogGlobal.show();
                //finish();
                finishAffinity();
                Intent stamp = new Intent(instance, MainActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.finishAffinity));
                startActivity(stamp);
                break;
        }

    }

    private void isCardPresent() {
        if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_STRIPE)) {
            if (prefManager.getPreference(CUSTOMER_ORDER, -1) == 0) {
                Intent setPin = new Intent(this, CreatePinActivity.class);
                setPin.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(setPin, OPEN_PIN_CREATE_ACTIVITY);
            } else {
                Intent stamp = new Intent(this, CardActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(stamp, OPEN_PAYMENT_DETAIL_ACTIVITY);
            }
        } else if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
            if (prefManager.getPreference(CUSTOMER_ORDER, -1) == 0) {   // check whether pin is already set or not
                Intent setPin = new Intent(instance, CreatePinActivity.class);
                setPin.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(setPin, OPEN_PIN_CREATE_ACTIVITY);
            } else {
                Intent stamp = new Intent(this, ActiveCardActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(stamp, OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY);
            }
        } else if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_PAYDOO)) {
            showSnackBar(binding.getRoot(), "PAYDOO PAYMENT GATEWAY IS NOT SUPPORTED BY APP YET!");
        }else {
            showSnackBar(binding.getRoot(), myCartList.get(0).getPayment_gatway()+" :Unsupported Payment Gateway Detected!");
        }
    }

    public void setSpinnerForCards() {

        List<String> cards = new ArrayList<>();
        for (int i = 0; i < myCardList.size(); i++) {
            cards.add(myCardList.get(i).getCard_number());
        }
        cards.add("ADD NEW CARD");

        popupWindow = new ListPopupWindow(instance);
        ArrayAdapter<String> adapter = new ArrayAdapter(instance, android.R.layout.simple_spinner_dropdown_item, cards);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.rlCards);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == cards.size() - 1) {
                    cardId = "";
                    addNewCard();
                    //  isAddNewCard = true;
                } else {
                    //   isAddNewCard = false;
                    setCardDetail(myCardList.get(position));
                }


                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }

    private void setCardDetail(GetCartWithSummaryResponseModel.DefaultCardsBean defaultCardsBean) {
        binding.tvCards.setText(defaultCardsBean.getCard_number());
        if (defaultCardsBean.getGateway().equals("active")) {
            payment_gatway = PAYMENT_GATEWAY_ACTIVE_SAVED_CARD;
            cardId = HelperClass.toBase64(defaultCardsBean.getStripe_card_id());
        } else {
            payment_gatway = defaultCardsBean.getGateway();
            cardId = defaultCardsBean.getStripe_card_id();
        }
        if (defaultCardsBean.getCard_type().equalsIgnoreCase(getString(R.string.visa))) {


            Glide.with(instance).load(R.drawable.ic_visa).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);

        } else if (defaultCardsBean.getCard_type().equalsIgnoreCase(getString(R.string.Visa_Credit))) {
            Glide.with(instance).load(R.drawable.ic_visa).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);

        } else if (defaultCardsBean.getCard_type().equalsIgnoreCase(getString(R.string.mastercard))) {
            Glide.with(instance).load(R.drawable.cio_ic_mastercard).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);
        } else if (defaultCardsBean.getCard_type().equalsIgnoreCase(getString(R.string.maestro_card))) {
            Glide.with(instance).load(R.drawable.ic_maestro).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);
        }

    }


    private void addNewCard() {

        if (prefManager.getPreference(CUSTOMER_ORDER, -1) == 0) {   // check whether pin is already set or not
            Intent setPin = new Intent(instance, CreatePinActivity.class);
            setPin.putExtra(FROM_WHERE, getString(R.string.cart));
            startActivityForResult(setPin, OPEN_PIN_CREATE_ACTIVITY);
        } else {
            if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_STRIPE)) {
                Intent stamp = new Intent(this, AddNewCardOnCheckoutActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.stripe));
                startActivityForResult(stamp, ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE);
            } else if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
                Intent stamp = new Intent(this, AddNewCardOnCheckoutActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.active));
                startActivityForResult(stamp, ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE);

            } else if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_PAYDOO)) {
                showSnackBar(binding.getRoot(), "PAYDOO PAYMENT GATEWAY IS NOT SUPPORTED BY APP YET!");
            }
        }


    }


    private void showDatePickerDialog(Calendar mCal) {
        //////////////////////////////////////////////////   if we hide, it will set today date
        final Calendar calendar = Calendar.getInstance();
        int yy = calendar.get(Calendar.YEAR);
        int mm = calendar.get(Calendar.MONTH);
        int dd = calendar.get(Calendar.DAY_OF_MONTH);
        //Min date setting part
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.MONTH, mm);
        cal.set(Calendar.DAY_OF_MONTH, dd);
        cal.set(Calendar.YEAR, yy);
        //Maximum date setting part
        Calendar calen = Calendar.getInstance();
        calen.set(Calendar.MONTH, mm);
        calen.set(Calendar.DAY_OF_MONTH, dd + maxDayShowInCalendar);   /// TODO : set max date here
        calen.set(Calendar.YEAR, yy);
        /////////////////////////////////////////////////////
        long now = System.currentTimeMillis() - 1000;  // This code also work perfectly
        now = now + (1000 * 60 * 60 * 24 * 7);  // set now in max day  7 is days   set this in setMinDate(now)/setMaxDate(now)

        datePickerDialog = new DatePickerDialog(this, dateSetListener, mCal.get(Calendar.YEAR), mCal.get(Calendar.MONTH), mCal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(calen.getTimeInMillis());  /// for the date prev to today date
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());  /// cal.getTimeInMillis() set min date (from date)
        datePickerDialog.show();
    }

    private boolean isValid() {
        try {
            if (customerAddress == null && deliveryMethod.equals(getString(R.string.home_delivery))) {
                Intent intent = new Intent(this, AddressActivity.class);
                intent.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(intent, OPEN_CHANGE_ADDRESS_ACTIVITY);
                return false;
            } else if (cardId.equals("")) {
                isCardPresent();
                return false;
            }

        } catch (Exception exc) {
            log("Validation exc", "" + exc.getMessage());
        }
        return true;
    }

    private boolean validateDeliveryMethod() {
        if (deliveryMethod.equals("")) {
            showSnackBar(binding.getRoot(), getString(R.string.select_delivery_type));
            return false;
        } else if (isOutOfStock) {
            // showSnackBar(binding.getRoot(), getString(R.string.cart_item_out_of_stock));

            showOutOfStockDialog(getString(R.string.cart_item_out_of_stock));

            return false;
        } else if (deliveryMethod.equals(getString(R.string.home_delivery))) {
            if (Double.parseDouble(responseModel.getCarts_summary().getGrand_total()) < Double.parseDouble(responseModel.getCarts_summary().getHome_delivery_mov())) {
                showSnackBar(binding.getRoot(), getString(R.string.cart_value_minimum));
                return false;
            }
        }
        return true;
    }

    private void showOutOfStockDialog(String message) {

        DialogUtils.showAlwayAlertDialogWithSingleButton(instance, message, new OnDialogClickListener() {
            @Override
            public void onPositiveClick() {

            }

            @Override
            public void onNegativeClick() {

            }
        });
    }

    private void getCartWithSummaryApi(String type, String cartId, String quantity) {

        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            final String latitude = prefManager.getPreference(LATITUDE, "");
            final String longitude = prefManager.getPreference(LONGITUDE, "");

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<GetCartWithSummaryResponseModel> call = null;
            if (type.equals(NONE)) {
                final GetCartWithSummaryRequestModel requestModel = new GetCartWithSummaryRequestModel(venue_id, latitude, longitude);
                call = apiInterface.getCartWithSummary(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            } else if (type.equals(UPDATE)) {
                final AddMobileCartQuantityRequestModel requestModel = new AddMobileCartQuantityRequestModel(cartId, quantity);
                call = apiInterface.addMobileCartQuantity(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
                // myCartList.clear();
            } else if (type.equals(DELETE)) {
                final AddMobileCartQuantityRequestModel requestModel = new AddMobileCartQuantityRequestModel(cartId, "");
                call = apiInterface.deleteMobileCartQuantity(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
                //  myCartList.clear();
            }

            binding.llNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<GetCartWithSummaryResponseModel>() {
                @Override
                public void onResponse(Call<GetCartWithSummaryResponseModel> call, Response<GetCartWithSummaryResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            if (response.body().isStatus()) {
                                responseModel = response.body();

                                if (responseModel.getVenue_timing().getIsClose() != null && responseModel.getVenue_timing().getIsClose().equals("1")) {
                                    binding.rbClickNCollect.setChecked(false);
                                    binding.rbClickNCollect.setClickable(false);
                                    binding.rbClickNCollect.setEnabled(false);
                                    binding.rbClickNCollect.setAlpha(.5f);
                                    binding.tvCollectionTime.setAlpha(.5f);
                                }

                                /////////////////////////////////////////////////////////////
                                if (!type.equals(NONE)) {
                                    if (binding.rbClickNCollect.isChecked() || binding.rbStandard.isChecked()) {
                                        ////////////////////////////
                                        if (binding.rbStandard.isChecked()) {
                                            if (Double.parseDouble(responseModel.getCarts_summary().getGrand_total()) <= Double.parseDouble(responseModel.getCarts_summary().getFree_delivery())) {
                                                deliveryCharge = responseModel.getCarts_summary().getDelivery_charges();
                                            } else {
                                                deliveryCharge = DEFAULT_DELIVERY_CHARGE;
                                            }
                                        } else if (binding.rbClickNCollect.isChecked()) {
                                            deliveryCharge = DEFAULT_DELIVERY_CHARGE;
                                        }
                                    }
                                }

                                ////////////////////////////////////////////////////////
                                if (responseModel.getCarts() != null)
                                    for (int i = 0; i < responseModel.getCarts().size(); i++) {
                                        if (responseModel.getCarts().get(i).getAvl_quantity() == 0) {
                                            isOutOfStock = true;
                                            showOutOfStockDialog(getString(R.string.cart_item_out_of_stock));
                                        } else {
                                            isItemInStock = true;
                                        }

                                    }
                                ///////////////////////////////////////////////////////////////
                                if (responseModel.getCarts_summary() != null) {
                                    showCarts_summary(responseModel.getCarts_summary());
                                }

                                customerAddress = responseModel.getCust_address();
                                showCustAddress(customerAddress);


                                if (responseModel.getCarts() != null && responseModel.getCarts().size() > 0) {
                                    binding.llNoDataFound.setVisibility(View.GONE);
                                    binding.llInfo.setVisibility(View.VISIBLE);
                                    binding.tvCheckout.setVisibility(View.VISIBLE);
                                    binding.tvProductDetails.setVisibility(View.VISIBLE);
                                    binding.rlAddMoreItem.setVisibility(View.VISIBLE);
                                    myCartList.clear();
                                    if (myCartAdapter != null)
                                        myCartAdapter.notifyDataSetChanged();
                                    myCartList.addAll(responseModel.getCarts());
                                    setCartDataAdapter();
                                    totalLoyaltyValue = myCartList.get(0).getTotal_loyalty_amount();
//                                    showVenueAddress(myCartList);
                                    getTodayCoupon();
                                } else {
                                    prefManager.savePreference(VENUE_ID_IN_CART, "");
                                    binding.llNoDataFound.setVisibility(View.VISIBLE);
                                    binding.llInfo.setVisibility(View.GONE);
                                    binding.tvProductDetails.setVisibility(View.GONE);
                                    binding.tvCheckout.setVisibility(View.GONE);
                                    binding.rlAddMoreItem.setVisibility(View.GONE);
                                    myCartList.clear();
                                    if (myCartAdapter != null)
                                        myCartAdapter.notifyDataSetChanged();
                                    setCartDataAdapter();
                                }

                                if (responseModel.getDefaultCards() != null && responseModel.getDefaultCards().size() > 0) {
                                    myCardList.addAll(responseModel.getDefaultCards());
                                    //  setSpinnerForCards();
                                    setCardDetail(myCardList.get(0));
                                } else {
                                    binding.rlCards.setVisibility(View.GONE);
                                    //   binding.rlAddNewCard.setVisibility(View.GONE);
                                }

                            } else {
                                showSnackBar(binding.getRoot(), response.body().getMessage());
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
                    }
                }

                @Override
                public void onFailure(Call<GetCartWithSummaryResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }

    }

    private void showCustAddress(GetCartWithSummaryResponseModel.CustAddressBean cust_address) {
        if (cust_address != null) {
            addressId = String.valueOf(cust_address.getId());
            String address = "" + cust_address.getArea() + ", " + cust_address.getLandmark() + ", " + cust_address.getCity() + ", " + cust_address.getPincode();
            address = address.replace("null", "");
            address = address.replace(",,", ",");
            binding.tvDeliverAddress.setText(getString(R.string.deliver_address));
            binding.tvChangeAddress.setText(getString(R.string.change_address));
            binding.cbDeliveryAddress.setText(address);
            postal_code = cust_address.getPincode();

        } else {
            binding.tvDeliverAddress.setText(getString(R.string.deliver_address));
            binding.tvChangeAddress.setText(getString(R.string.add_address));
            binding.cbDeliveryAddress.setText("");
        }
    }

    private void showVenueAddress() {
        String address = getString(R.string.not_given);
        if (myCartList != null) {
            address = "" + myCartList.get(0).getAddress_1();

            venueLatitude = myCartList.get(0).getLatitude();
            venueLongitude = myCartList.get(0).getLongitude();
        }
        binding.tvChangeAddress.setText(getString(R.string.get_direction));
        binding.tvDeliverAddress.setText(getString(R.string.venue_address));
        binding.cbDeliveryAddress.setText(address);
    }


    private void showCarts_summary(GetCartWithSummaryResponseModel.CartsSummaryBean carts_summary) {
        maxDayShowInCalendar = carts_summary.getDays_for_click_collect();
        if (carts_summary.getCollection().equals("0")) {
            binding.rbClickNCollect.setChecked(false);
            binding.rbClickNCollect.setClickable(false);
            binding.rbClickNCollect.setEnabled(false);
            binding.rbClickNCollect.setAlpha(.5f);
            binding.tvCollectionTime.setAlpha(.5f);
        }

        calculateTotalAmount(ONLOAD);

        if (carts_summary.getDelivery().equals("0")) {
            binding.rbStandard.setChecked(false);
            binding.rbStandard.setClickable(false);
            binding.rbStandard.setEnabled(false);
            binding.rbStandard.setAlpha(.5f);
            binding.tvStandardDeliveryTime.setAlpha(.5f);
            deliveryCharge = DEFAULT_DELIVERY_CHARGE;

        } else {
            if (Double.parseDouble(carts_summary.getHome_delivery_mov()) > Double.parseDouble(carts_summary.getGrand_total())) {
                binding.rbStandard.setChecked(true);
                binding.rbStandard.setClickable(true);
                binding.rbStandard.setEnabled(true);
                binding.rlMyCartAddress.setVisibility(View.VISIBLE);
                deliveryMethod = getString(R.string.home_delivery);
                binding.rvShippingList.setVisibility(View.VISIBLE);

                ////////////////////////////////////////////////////////////////

                if (Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                        <= Double.parseDouble(responseModel.getCarts_summary().getFree_delivery())) {

                    if (isItemInStock)
                        totalAmountPayable = String.valueOf(Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                                + Double.parseDouble(responseModel.getCarts_summary().getDelivery_charges())
                                - enteredLoyaltyPoint - couponPrice);
                    deliveryCharge = responseModel.getCarts_summary().getDelivery_charges();

                } else {
                    totalAmountPayable = String.valueOf(Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                            - enteredLoyaltyPoint - couponPrice);
                    deliveryCharge = DEFAULT_DELIVERY_CHARGE;
                }

                ////////////////////////////////////////////////////////////////

            } else {
                binding.rbStandard.setChecked(true);
                binding.rbStandard.setClickable(true);
                binding.rbStandard.setEnabled(true);
                binding.rlMyCartAddress.setVisibility(View.VISIBLE);
                deliveryMethod = getString(R.string.home_delivery);
                binding.rvShippingList.setVisibility(View.VISIBLE);

                ////////////////////////////////////////////////////////////////

                if (Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                        <= Double.parseDouble(responseModel.getCarts_summary().getFree_delivery())) {
                    if (isItemInStock)
                        totalAmountPayable = String.valueOf(Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                                + Double.parseDouble(responseModel.getCarts_summary().getDelivery_charges())
                                - enteredLoyaltyPoint - couponPrice);
                    deliveryCharge = responseModel.getCarts_summary().getDelivery_charges();

                } else {
                    totalAmountPayable = String.valueOf(Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                            - enteredLoyaltyPoint - couponPrice);
                    deliveryCharge = DEFAULT_DELIVERY_CHARGE;
                }
                ////////////////////////////////////////////////////////////////
            }
        }

        binding.tvStandardDeliveryTime.setText(getString(R.string.est_delivery) + " " + carts_summary.getDelivery_start_days() + "-" + carts_summary.getDelivery_end_days() + " days)");
        binding.tvCollectionTime.setText(getString(R.string.est_collection_time) + " " + carts_summary.getCollection_time() == null ? "" : getString(R.string.est_collection_time) + " " + carts_summary.getCollection_time() + " Mins)");

        binding.tvCartTotalAmount.setText(getString(R.string.pound) + carts_summary.getCart_total());

        if (!TextUtils.isEmpty(carts_summary.getDiscount()) && Double.parseDouble(carts_summary.getDiscount()) > 0) {
            binding.tvDiscountAmount.setVisibility(View.VISIBLE);
            binding.tvDiscount.setVisibility(View.VISIBLE);
            binding.tvDiscountAmount.setText(String.format("%s%s", getString(R.string.pound), carts_summary.getDiscount()));
        } else {
            binding.tvDiscountAmount.setVisibility(View.GONE);
            binding.tvDiscount.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(carts_summary.getTax()) && Double.parseDouble(carts_summary.getTax()) > 0) {
            binding.tvTaxAmount.setVisibility(View.VISIBLE);
            binding.tvTax.setVisibility(View.VISIBLE);
            binding.tvTaxAmount.setText(getString(R.string.pound) + carts_summary.getTax());
        } else {
            binding.tvTaxAmount.setVisibility(View.GONE);
            binding.tvTax.setVisibility(View.GONE);
        }

        setDeliveryCharge();
        setTransactionCharge();


        if (isItemInStock) {
            binding.tvSubTotalAmount.setText(getString(R.string.pound) + carts_summary.getSub_total());
        } else binding.tvSubTotalAmount.setText(getString(R.string.pound) + "0.00");


        binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
        binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));

        if (carts_summary.getCollection().equals("0") && carts_summary.getDelivery().equals("0")) {
            binding.tvCheckout.setText(R.string.order_closed);
            binding.tvCheckout.setBackground(ContextCompat.getDrawable(instance, R.drawable.light_red_filled_rounded_rect));
            binding.tvCheckout.setAlpha(.5f);
            binding.tvCheckout.setClickable(false);
            binding.tvCheckout.setEnabled(false);
        }
    }

    private void setTransactionCharge() {
        if (!TextUtils.isEmpty(responseModel.getCarts_summary().getTransaction_charge_amt()) && Double.parseDouble(responseModel.getCarts_summary().getTransaction_charge_amt()) > 0 && isItemInStock) {
            binding.tvTvTransactionAmt.setVisibility(View.VISIBLE);
            binding.tvTransactionAmtLabel.setVisibility(View.VISIBLE);
            binding.tvTvTransactionAmt.setText(getString(R.string.pound) + responseModel.getCarts_summary().getTransaction_charge_amt());
        } else {
            binding.tvTvTransactionAmt.setVisibility(View.GONE);
            binding.tvTransactionAmtLabel.setVisibility(View.GONE);
        }

    }

    private void setDeliveryCharge() {
        if (!TextUtils.isEmpty(deliveryCharge) && Double.parseDouble(deliveryCharge) > 0 && isItemInStock) {
            binding.tvHandlingChargeAmount.setVisibility(View.VISIBLE);
            binding.tvHandlingCharge.setVisibility(View.VISIBLE);
            binding.tvHandlingChargeAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(deliveryCharge)));
        } else {
            binding.tvHandlingChargeAmount.setVisibility(View.GONE);
            binding.tvHandlingCharge.setVisibility(View.GONE);
        }
    }

    private void calculateTotalAmount(int type) {

        if (!isItemInStock) {
            return;
        }

        switch (type) {
            case ONLOAD:
                totalAmountPayable = String.valueOf(Double.parseDouble(responseModel.getCarts_summary().getGrand_total()));
                break;
            case PAID_HOME_DELIVERY:
                totalAmountPayable = String.valueOf(Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                        + Double.parseDouble(responseModel.getCarts_summary().getDelivery_charges())
                        - enteredLoyaltyPoint - couponPrice);
                deliveryCharge = responseModel.getCarts_summary().getDelivery_charges();
                break;
            case FREE_HOME_DELIVERY:
                totalAmountPayable = String.valueOf(Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                        - enteredLoyaltyPoint - couponPrice);
                deliveryCharge = DEFAULT_DELIVERY_CHARGE;
                break;
            case RB_CLICK_COLLECT:
                deliveryCharge = DEFAULT_DELIVERY_CHARGE;
                totalAmountPayable = String.valueOf(Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                        - enteredLoyaltyPoint - couponPrice);
                break;
        }

    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_standard:

                if (responseModel != null) {
                    double movAmt = Double.parseDouble(responseModel.getCarts_summary().getHome_delivery_mov()) - Double.parseDouble(responseModel.getCarts_summary().getGrand_total());
                    double freeAmt = Double.parseDouble(responseModel.getCarts_summary().getFree_delivery()) - Double.parseDouble(responseModel.getCarts_summary().getGrand_total());

                    if (Double.parseDouble(responseModel.getCarts_summary().getHome_delivery_mov()) >
                            Double.parseDouble(responseModel.getCarts_summary().getGrand_total())) {
                        binding.rbStandard.setChecked(false);
                        DialogUtils.showMinValueDialog(instance, "Spent " + getString(R.string.pound) + String.format("%.2f", movAmt) + " " + getString(R.string.more_for_home_deliver), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                            }

                            @Override
                            public void onNegativeClick() {
                            }
                        });
                    } else {
                        binding.rbStandard.setTextColor(getResources().getColor(R.color.app_header_color));
                        binding.rbClickNCollect.setTextColor(getResources().getColor(R.color.color_black));

                        binding.rbStandard.setChecked(true);
                        binding.rbStandard.setClickable(true);
                        binding.rbStandard.setEnabled(true);
                        binding.rlMyCartAddress.setVisibility(View.VISIBLE);
                        binding.tvSpecificDate.setText(getString(R.string.specific_date));
                        binding.tvSpecificDateMessage.setText("");
                        binding.tvSpecificDate.setVisibility(View.GONE);
                        binding.tvSpecificDateMessage.setVisibility(View.GONE);
                        //  binding.cbSpecificDate.setVisibility(View.GONE);
                        deliveryMethod = getString(R.string.home_delivery);
                        binding.rvShippingList.setVisibility(View.VISIBLE);
                        if (responseModel.getCarts() != null && responseModel.getCarts().size() > 0)
                            getAllShippingMethods();
                        if (myCartList != null && myCartList.size() > 0) {
                            showCustAddress(customerAddress);

                            if (Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                                    <= Double.parseDouble(responseModel.getCarts_summary().getFree_delivery())) {
                                if (isItemInStock)

                                    calculateTotalAmount(PAID_HOME_DELIVERY);

                                DialogUtils.showMinValueDialog(instance, "Spent " + getString(R.string.pound) + String.format("%.2f", freeAmt) + " " + getString(R.string.more_for_free_deliver), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });

                            } else {

                                calculateTotalAmount(FREE_HOME_DELIVERY);

                            }

                            binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
                            binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));

                            setDeliveryCharge();
                        }
                    }

                }

                break;

            case R.id.rb_click_n_collect:

                if (shippingMethodAdapter != null) {
                    shippingMethodAdapter.clearCheck();
                    shippingId = "";
                    binding.rvShippingList.setVisibility(View.GONE);
                }

                if (maxDayShowInCalendar == 0) {
                    binding.tvSpecificDate.setVisibility(View.GONE);
                    binding.tvSpecificDateMessage.setVisibility(View.GONE);
                    //  binding.cbSpecificDate.setVisibility(View.GONE);
                } else {
                    binding.tvSpecificDate.setVisibility(View.VISIBLE);
                    binding.tvSpecificDateMessage.setVisibility(View.VISIBLE);
                    // binding.cbSpecificDate.setVisibility(View.VISIBLE);
                }

                binding.rlMyCartAddress.setVisibility(View.VISIBLE);
                binding.rbClickNCollect.setTextColor(getResources().getColor(R.color.app_header_color));
                binding.rbStandard.setTextColor(getResources().getColor(R.color.color_black));

                deliveryMethod = getString(R.string.click_n_collect);
                if (myCartList != null && myCartList.size() > 0) {
                    showVenueAddress();
                    calculateTotalAmount(RB_CLICK_COLLECT);

                    binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
                    binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));

                    setDeliveryCharge();
                }
                break;
        }
    }

    public void showLoyaltyDialog() {
        final Dialog dialog = new Dialog(instance, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_enter_loyalty);

        final EditText etLoyalty = dialog.findViewById(R.id.et_loyalty);
        etLoyalty.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        // imm.showSoftInput(etLoyalty, InputMethodManager.SHOW_FORCED);

        TextView tvMessaage = dialog.findViewById(R.id.tv_total_loyalty);

        tvMessaage.setText(getString(R.string.total_loyalty_amount) + String.format("%.2f", totalLoyaltyValue));

        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialog.show();

        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (myCartList.size() > 0 && !TextUtils.isEmpty(etLoyalty.getText().toString()))
                    if (totalLoyaltyValue > 0 && totalLoyaltyValue >= Double.parseDouble(etLoyalty.getText().toString())) {

                        if (Double.parseDouble(etLoyalty.getText().toString()) > 0.09 && Double.parseDouble(etLoyalty.getText().toString())
                                <= Double.parseDouble(String.format("%.2f", (Double.parseDouble(responseModel.getCarts_summary().getGrand_total()))))) {
                            enteredLoyaltyPoint = Double.parseDouble(etLoyalty.getText().toString());
                            binding.tvSelectedValue.setVisibility(View.VISIBLE);
                            binding.ivCancelLoyalty.setVisibility(View.VISIBLE);
                            binding.tvSelectedValue.setText(getString(R.string.loyalty_value) + String.format("%.2f", enteredLoyaltyPoint));
                            binding.tvAppliedLoyaltyVal.setVisibility(View.VISIBLE);
                            binding.tvAppliedLoyalty.setVisibility(View.VISIBLE);
                            binding.tvAppliedLoyaltyVal.setText(String.format("%.2f", enteredLoyaltyPoint));
                            //////////////////////////////////////////////////////////////////////
                            if (deliveryMethod.equals(getString(R.string.home_delivery))) {
                                if (Double.parseDouble(responseModel.getCarts_summary().getGrand_total()) <= Double.parseDouble(responseModel.getCarts_summary().getFree_delivery())) {

                                    if (shippingId.equals("")) {
                                        deliveryCharge = responseModel.getCarts_summary().getDelivery_charges();
                                    }

                                    totalAmountPayable = String.valueOf(Double.parseDouble(String.format("%.2f", Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                                            + Double.parseDouble(deliveryCharge) - enteredLoyaltyPoint - couponPrice)));


                                } else {

                                    if (shippingId.equals("")) {
                                        deliveryCharge = DEFAULT_DELIVERY_CHARGE;
                                        totalAmountPayable = String.valueOf(Double.parseDouble(String.format("%.2f", Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                                                - enteredLoyaltyPoint - couponPrice)));
                                    } else {
                                        totalAmountPayable = String.valueOf(Double.parseDouble(String.format("%.2f", Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                                                + Double.parseDouble(deliveryCharge)
                                                - enteredLoyaltyPoint - couponPrice)));
                                    }

                                }

                                binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
                                binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
                                setDeliveryCharge();

                            } else if (deliveryMethod.equals(getString(R.string.click_n_collect))) {
                                deliveryCharge = DEFAULT_DELIVERY_CHARGE;
                                totalAmountPayable = String.valueOf(Double.parseDouble(String.format("%.2f", Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                                        - enteredLoyaltyPoint - couponPrice)));
                                binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
                                binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
                                setDeliveryCharge();
                            } else {
                                totalAmountPayable = String.valueOf(Double.parseDouble(String.format("%.2f", Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                                        - enteredLoyaltyPoint - couponPrice)));
                                binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
                                binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));

                            }
                            //////////////////////////////////////////////////////////////////////////////

                            dialog.dismiss();
                        } else toast(instance, getString(R.string.loyalty_validation));

                    } else toast(instance, getString(R.string.enter_valid_loyalty));
            }
        });

        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void closeLoyalty() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.tvSelectedValue.setVisibility(View.GONE);
                binding.ivCancelLoyalty.setVisibility(View.GONE);
                binding.tvAppliedLoyaltyVal.setVisibility(View.GONE);
                binding.tvAppliedLoyaltyVal.setText("");
                binding.tvAppliedLoyalty.setVisibility(View.GONE);
                totalAmountPayable = String.valueOf(Double.parseDouble(totalAmountPayable) + enteredLoyaltyPoint);
                enteredLoyaltyPoint = 0.0;
                binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
                binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
            }
        });

    }

    private void cancelCoupon() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.tvCouponAppliedLbl.setVisibility(View.GONE);
                binding.tvCouponAppliedAmount.setVisibility(View.GONE);
                binding.ivCancelCouponApplied.setVisibility(View.GONE);

                totalAmountPayable = String.valueOf(Double.parseDouble(totalAmountPayable) + couponPrice);
                couponPrice = 0.0;
                couponCode = "";
                binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
                binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));

            }
        });

    }

    private void calculatePriceAfterCoupon(double couponPrice) {
        totalAmountPayable = String.valueOf(Double.parseDouble(totalAmountPayable) - couponPrice);
        binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
        binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
    }

    private void AddCoupon(CouponResponseModel.CouponDataBean model) {
        if (Double.parseDouble(model.getMinimum_spend()) > Double.parseDouble(responseModel.getCarts_summary().getCart_total())) {
            Toast.makeText(instance, "Cart amount should be " + getString(R.string.pound) + model.getMinimum_spend() + " to use this coupon", Toast.LENGTH_SHORT).show();
            return;
        }
        DialogUtils.dialogCouponApply(instance, model.getCoupon_code(), new OnDialogClickListener() {
            @Override
            public void onPositiveClick() {
                binding.tvCouponAppliedLbl.setVisibility(View.VISIBLE);
                binding.tvCouponAppliedAmount.setVisibility(View.VISIBLE);
                binding.ivCancelCouponApplied.setVisibility(View.VISIBLE);

                if (model.getOffer_type() == 1) {  // percent
                    couponPrice = (Double.parseDouble(responseModel.getCarts_summary().getCart_total()) * Double.parseDouble(model.getOffer_amount())) / 100;

                } else {  // pound
                    couponPrice = Double.parseDouble(model.getOffer_amount());
                }

                if (Double.parseDouble(model.getMax_discount()) >= couponPrice) {
                    calculatePriceAfterCoupon(couponPrice);
                } else {
                    couponPrice = Double.parseDouble(model.getMax_discount());
                    calculatePriceAfterCoupon(couponPrice);
                }
                binding.tvCouponAppliedAmount.setText(getString(R.string.pound) + String.format("%.2f", couponPrice));
                binding.tvCouponAppliedLbl.setText(getString(R.string.coupon_applied) + " (" + model.getCoupon_code() + ")");
                couponCode = model.getCoupon_code();
            }

            @Override
            public void onNegativeClick() {

            }
        });
    }

    private void AddManualyCoupon(double coupon_amount, String coupon_code) {

        DialogUtils.dialogCouponApply(instance, coupon_code, new OnDialogClickListener() {
            @Override
            public void onPositiveClick() {
                binding.tvCouponAppliedLbl.setVisibility(View.VISIBLE);
                binding.tvCouponAppliedAmount.setVisibility(View.VISIBLE);
                binding.ivCancelCouponApplied.setVisibility(View.VISIBLE);

                couponPrice = coupon_amount;
                binding.tvCouponAppliedAmount.setText(getString(R.string.pound) + String.format("%.2f", couponPrice));
                binding.tvCouponAppliedLbl.setText(getString(R.string.coupon_applied) + " (" + coupon_code + ")");
                calculatePriceAfterCoupon(couponPrice);
                couponCode = coupon_code;
            }

            @Override
            public void onNegativeClick() {

            }
        });
    }

    private void applyCoupon(String reservation_id, String venueId, String coupon_code, String customer_id, String is_hospitality, String deliveryCharge) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            CouponRequestModel couponRequestModel = new CouponRequestModel(reservation_id, venueId, coupon_code, customer_id, is_hospitality, deliveryCharge);


            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CouponResponseModel> call = apiInterface.applyCoupon(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""),
                    couponRequestModel);
            call.enqueue(new Callback<CouponResponseModel>() {
                @Override
                public void onResponse(Call<CouponResponseModel> call, Response<CouponResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            CouponResponseModel loyaltyResponseModel = response.body();
                            if (loyaltyResponseModel != null) {
                                if (loyaltyResponseModel.isStatus()) {
                                    cancelCoupon();
                                    binding.etCouponCode.setText("");
                                    AddManualyCoupon(loyaltyResponseModel.getCoupon_amount(), coupon_code);
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
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    public void checkOut() {
        CheckOutRequestModel requestModel = null;
        List<CheckOutRequestModel.ProductsBean> productList = new ArrayList<>();
        CheckOutRequestModel.ProductsBean productsBean;
        for (int i = 0; i < myCartList.size(); i++) {

            productsBean = new CheckOutRequestModel.ProductsBean(myCartList.get(i).getQuantities(), myCartList.get(i).getProduct_id(),
                    String.valueOf(myCartList.get(i).getModifier_id()), binding.cbDeliveryAddress.getText().toString(),
                    binding.cbDeliveryAddress.getText().toString(), myCartList.get(i).getMerchant_id(),
                    String.valueOf(myCartList.get(i).getVenue_id()),
                    myCartList.get(i).getModifier_name(), Double.parseDouble(myCartList.get(i).getSelling_price()),   ///selling price
                    Double.parseDouble(myCartList.get(i).getBuy_price()),
                    Double.parseDouble(myCartList.get(i).getDiscount_applied()),
                    Long.parseLong("" + myCartList.get(i).getOffer_id()),
                    Double.parseDouble("" + myCartList.get(i).getTax_applied()),
                    TextUtils.isEmpty(myCartList.get(i).getTax_id()) ? 0 : Integer.parseInt(myCartList.get(i).getTax_id()),
                    myCartList.get(i).getNet_amount(), "", deliveryMethod, myCartList.get(i).getReturn_day(), 0, myCartList.get(i).getCaseqty(), myCartList.get(i).getBulk_selling_price());
            productList.add(productsBean);
        }
        requestModel = new CheckOutRequestModel(payment_gatway, toBase64(cardNumber), toBase64(cardExpiryMonth), toBase64(cardExpiryYear), toBase64(cardCVV), String.valueOf(myCartList.get(0).getMerchant_id()),
                String.valueOf(myCartList.get(0).getVenue_id()), CUSTOMER_TYPE, PAYMENT_MODE, cardId, addressId, myCartList.size(),
                Double.parseDouble("" + responseModel.getCarts_summary().getCart_total()),
                Double.parseDouble(responseModel.getCarts_summary().getDiscount()),
                Double.parseDouble(responseModel.getCarts_summary().getTax()),
                String.valueOf(Double.parseDouble(totalAmountPayable) + enteredLoyaltyPoint), deliveryCharge, enteredLoyaltyPoint, IS_GIFT, REORDER_STATUS, deliveryMethod, "0",
                responseModel.getCarts_summary().getTransaction_charge_amt(), productList, postal_code,
                address_one, address_two, address_three, isSaveCard, "0", responseModel.getCarts_summary().getTransaction_charge_amt(), shippingId,
                IS_HOSPITALITY, app_type, binding.etMessage.getText().toString().trim(),
                binding.tvSpecificDate.getText().toString().trim().equals(getString(R.string.specific_date)) ? null : binding.tvSpecificDate.getText().toString().trim(),
                couponCode, String.valueOf(couponPrice));

        final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
        if (dialog != null)
            dialog.show();
        if (isInternetOn(this)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CheckOutResponseModel> call = apiInterface.saveEcommOrder(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<CheckOutResponseModel>() {
                @Override
                public void onResponse(Call<CheckOutResponseModel> call, Response<CheckOutResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            cardId = "";
                            CheckOutResponseModel checkOutResponseModel = response.body();
                            if (checkOutResponseModel.isStatus()) {

                                DialogUtils.orderSuccesDialog(MyCartActivity.this, checkOutResponseModel.getMessage(), true,
                                        String.valueOf(checkOutResponseModel.getOrder().getNet_amount()),
                                        prefManager.getPreference(EMAIL, ""), checkOutResponseModel.getOrder().getUnique_code(),
                                        myCartList.get(0).getVenue_images(), couponPrice, new OnDialogClickListener() {
                                            @Override
                                            public void onPositiveClick() {
                                                prefManager.savePreference(VENUE_ID_IN_CART, "");
                                                finish();
                                                Intent intent = new Intent(instance, MyOrderActivity.class);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onNegativeClick() {

                                            }
                                        }, checkOutResponseModel.getOrder().getLoyelty_consumed(), enteredLoyaltyPoint);

                            }
                            else {
                                DialogUtils.orderSuccesDialog(MyCartActivity.this, checkOutResponseModel.getMessage(), false,
                                        totalAmountPayable, prefManager.getPreference(EMAIL, ""), "", "", couponPrice, new OnDialogClickListener() {
                                            @Override
                                            public void onPositiveClick() {

                                            }

                                            @Override
                                            public void onNegativeClick() {

                                            }
                                        }, checkOutResponseModel.getOrder().getLoyelty_consumed(), enteredLoyaltyPoint);
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
                public void onFailure(Call<CheckOutResponseModel> call, Throwable t) {
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
    public void onDrawerSelect(int position, int clickId) {
        if (clickId == SHIPPING_METHOD) {
            if (shippingList.get(position).getType() == 1) {
                deliveryCharge = shippingList.get(position).getAmount();
            } else {

                double cartTotal = Double.parseDouble(responseModel.getCarts_summary().getCart_total());
                double percent = Double.parseDouble(shippingList.get(position).getAmount());
                double deliCharge = (cartTotal * percent) / 100;
                deliveryCharge = String.valueOf(deliCharge);
            }
            totalAmountPayable = String.valueOf(Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                    + Double.parseDouble(deliveryCharge)
                    - enteredLoyaltyPoint - couponPrice);

            binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
            binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));

            shippingId = String.valueOf(shippingList.get(position).getId());

            setDeliveryCharge();


        } else if (clickId == SHIPPING_METHOD_UNCHEKED) {
            shippingId = "";
        } else if (clickId == STORE) {
            openVenue(position);
        } else {
            Intent product = new Intent(this, ProductDetailActivity.class);
            product.putExtra(STORE_ID, myCartList.get(position).getVenue_id());
            product.putExtra(PRODUCT_ID, String.valueOf(myCartList.get(position).getProduct_id()));
            product.putExtra(OFFER_ID, String.valueOf(myCartList.get(position).getOffer_id()));
            product.putExtra(BARCODE_ID, "");
            startActivity(product);
        }
    }

    private void openVenue(int pos) {
        Intent topStore = new Intent(this, VenueDetailActivity.class);
        topStore.putExtra(STORE_ID, String.valueOf(myCartList.get(pos).getVenue_id()));
        topStore.putExtra(CATEGORY_ID, "");
        topStore.putExtra(FROM_WHERE, HOME_RETAIL);
        startActivity(topStore);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onRefresh() {
        //  myCartList.clear();
        // getCartList(false);
    }

    @Override
    public void onIncrease(int position, String count) {
        closeLoyalty();
        cancelCoupon();
        isOutOfStock = false;
        isItemInStock = false;
        getCartWithSummaryApi(UPDATE, String.valueOf(myCartList.get(position).getId()), count);

        if (binding.rbStandard.isChecked() && myCartList.size() > 0) {
            getAllShippingMethods();
        }
    }

    @Override
    public void onDecrease(int position, String count) {
        closeLoyalty();
        cancelCoupon();
        if (count.equals("0")) {
            int cartId = myCartList.get(position).getId();
            isOutOfStock = false;
            isItemInStock = false;
            getCartWithSummaryApi(DELETE, String.valueOf(myCartList.get(position).getId()), "");

        } else {
            isOutOfStock = false;
            isItemInStock = false;
            getCartWithSummaryApi(UPDATE, String.valueOf(myCartList.get(position).getId()), count);
        }
        if (binding.rbStandard.isChecked() && myCartList.size() > 0) {
            getAllShippingMethods();
        }
    }

    @Override
    public void onDelete(int position) {
        cancelCoupon();
        isOutOfStock = false;
        isItemInStock = false;
        getCartWithSummaryApi(DELETE, String.valueOf(myCartList.get(position).getId()), "");
        if (binding.rbStandard.isChecked() && myCartList.size() > 0) {
            getAllShippingMethods();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_CHANGE_ADDRESS_ACTIVITY && resultCode == RESULT_OK && data != null) {

            customerAddress = new GetCartWithSummaryResponseModel.CustAddressBean();
            addressId = String.valueOf(data.getIntExtra(ADDRESS_ID, 0));
            customerAddress.setId(data.getIntExtra(ADDRESS_ID, 0));
            customerAddress.setCity(data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));

            binding.cbDeliveryAddress.setText(data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));

        } else if (requestCode == OPEN_LOYALTY_STAMP_VOUCHER_ACTIVITY && resultCode == RESULT_OK && data != null) {

            String coupon = data.getStringExtra(LOYA_STAMP_VOUC_ACTIVITY_CALLBACK);
            binding.llLoyalty.setVisibility(View.VISIBLE);
            binding.tvSelectedValue.setText(getString(R.string.loyalty_value) + coupon);
            binding.tvAppliedLoyaltyVal.setVisibility(View.VISIBLE);
            binding.tvAppliedLoyalty.setVisibility(View.VISIBLE);
            binding.tvAppliedLoyaltyVal.setText(coupon);

        } else if (requestCode == OPEN_PIN_CREATE_ACTIVITY && resultCode == RESULT_OK && data != null) {
            boolean isOK = data.getBooleanExtra(PIN_CREATE_ACTIVITY_CALLBACK, false);
            if (isOK) {
                if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
                    Intent stamp = new Intent(this, ActiveCardActivity.class);
                    stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                    startActivityForResult(stamp, OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY);
                } else {
                    Intent stamp = new Intent(this, CardActivity.class);
                    stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                    startActivityForResult(stamp, OPEN_PAYMENT_DETAIL_ACTIVITY);
                }

            }
        } else if (requestCode == OPEN_PAYMENT_DETAIL_ACTIVITY && resultCode == RESULT_OK && data != null) { // STRIPE
            cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
            if (isValid())
                checkOut();
        } else if (requestCode == OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY && resultCode == RESULT_OK && data != null) {
            cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
            payment_gatway = data.getStringExtra(ACTIVE_PAYMENT_TYPE);
            cardNumber = data.getStringExtra(CARD_NUMBER);
            cardExpiryMonth = String.valueOf(data.getIntExtra(CARD_EXPIRY_MONTH, 22));
            cardExpiryYear = String.valueOf(data.getIntExtra(CARD_EXPIRY_YEAR, 11));
            cardCVV = data.getStringExtra(CARD_CVV);

            address_one = data.getStringExtra(ADDRESS_ONE);
            address_two = data.getStringExtra(ADDRESS_TWO);
            address_three = data.getStringExtra(ADDRESS_THREE);
            if (!data.getStringExtra(POSTAL_CODE).isEmpty()) {
                postal_code = data.getStringExtra(POSTAL_CODE);
            }
            isSaveCard = data.getBooleanExtra(IS_SAVE_CARD, false);
            checkOut();

        } else if (requestCode == OPEN_ADD_ADDRESS_ACTIVITY && resultCode == RESULT_OK && data != null) {
            try {
                binding.cbDeliveryAddress.setText(data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));
                addressId = String.valueOf(data.getIntExtra(ADDRESS_ID, 0));
                customerAddress = new GetCartWithSummaryResponseModel.CustAddressBean();
                customerAddress.setId(data.getIntExtra(ADDRESS_ID, 0));
                customerAddress.setCity(data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));
            } catch (Exception e) {
                e.printStackTrace();
                log("bbb", e.getMessage());
            }
        } else if (requestCode == OPEN_ACTIVITY_COUPON_CALLBACK_CODE && resultCode == RESULT_OK && data != null) {
            CouponResponseModel.CouponDataBean model = data.getExtras().getParcelable(OPEN_ACTIVITY_COUPON_CALLBACK_DATA);
            AddCoupon(model);
        } else if (requestCode == OPEN_PIN_VERIFY_ACTIVITY && resultCode == RESULT_OK && data != null) {
            checkOut();
        } else if (requestCode == ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE && resultCode == RESULT_OK && data != null) {
            isSaveCard = data.getBooleanExtra(IS_SAVE_CARD, false);
            if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
                // cardId = HelperClass.toBase64(defaultCardsBean.getStripe_card_id());
                cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
                payment_gatway = data.getStringExtra(ACTIVE_PAYMENT_TYPE);
                cardNumber = data.getStringExtra(CARD_NUMBER);
                cardExpiryMonth = String.valueOf(data.getIntExtra(CARD_EXPIRY_MONTH, 22));
                cardExpiryYear = String.valueOf(data.getIntExtra(CARD_EXPIRY_YEAR, 11));
                cardCVV = data.getStringExtra(CARD_CVV);

                address_one = data.getStringExtra(ADDRESS_ONE);
                address_two = data.getStringExtra(ADDRESS_TWO);
                address_three = data.getStringExtra(ADDRESS_THREE);
                if (!data.getStringExtra(POSTAL_CODE).isEmpty()) {
                    postal_code = data.getStringExtra(POSTAL_CODE);
                }
            } else {
                cardNumber = data.getStringExtra(CARD_NUMBER);
                cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
            }

            binding.tvCards.setText(/*data.getStringExtra(CARD_BRAND_NAME) +*/cardNumber);

        } else if (requestCode == OPEN_ACTIVITY_COUPON_CALLBACK_CODE && resultCode == RESULT_CANCELED) {

        }
    }


    @Override
    protected void onStop() {
        super.onStop();
        if (dialogGlobal != null)
            dialogGlobal.dismiss();
        DialogUtils.dismissAll();
        DialogUtils.dismissCountDownTimer();
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
                } /*else {
                    DialogUtils.simpleNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent myOrder = new Intent(this, MyOrderActivity.class);
                        startActivity(myOrder);
                    });
                }*/

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Snackbar showSnackbar(View coordinatorLayout, int duration, String g_total, String
            home_delivery, String free_delivery) { // Create the Snackbar
        Snackbar snackbar = Snackbar.make(coordinatorLayout, "", duration);

        int marginFromSides = 35; // margin from all the sides
        float height = 250;
        //inflate view
        View snackView = getLayoutInflater().inflate(R.layout.snackbar, null);
        TextView mov = (TextView) snackView.findViewById(R.id.tv_mov);
        TextView free = (TextView) snackView.findViewById(R.id.tv_free);
        double movAmt = Double.parseDouble(home_delivery) - Double.parseDouble(g_total);
        double freeAmt = Double.parseDouble(free_delivery) - Double.parseDouble(g_total);

        mov.setText("Spent " + getString(R.string.pound) + String.format("%.2f", movAmt) + " " + getString(R.string.more_for_home_deliver));
        free.setText("Spent " + getString(R.string.pound) + String.format("%.2f", freeAmt) + " " + getString(R.string.more_for_free_deliver));
        // White background
        snackbar.getView().setBackgroundColor(Color.TRANSPARENT);
        // for rounded edges
        //snackbar.getView().setBackground(getResources().getDrawable(R.drawable.white_filled_rect));
        Snackbar.SnackbarLayout snackBarView = (Snackbar.SnackbarLayout) snackbar.getView();
        FrameLayout.LayoutParams parentParams = (FrameLayout.LayoutParams) snackBarView.getLayoutParams();
        parentParams.setMargins(marginFromSides, marginFromSides, marginFromSides, marginFromSides);
        parentParams.height = (int) height;
        parentParams.width = FrameLayout.LayoutParams.MATCH_PARENT;
        snackBarView.setLayoutParams(parentParams);

        snackBarView.addView(snackView, 0);

        return snackbar;
    }


    private void getAllShippingMethods() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ShippingMethodRequestModel allVenuesRequestModel = new ShippingMethodRequestModel(responseModel.getCarts().get(0).getDistance(),
                    "" + responseModel.getCarts().get(0).getVenue_id(), responseModel.getCarts_summary().getMax_weight(), responseModel.getCarts_summary().getCart_total());
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<ShippingMethodResponseModel> call = apiInterface.getShippingMethods(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), allVenuesRequestModel);
            call.enqueue(new Callback<ShippingMethodResponseModel>() {
                @Override
                public void onResponse(Call<ShippingMethodResponseModel> call, Response<ShippingMethodResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            ShippingMethodResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {

                                if (responseModel.getShipping_details() != null && responseModel.getShipping_details().size() > 0) {
                                    shippingList.clear();
                                    shippingList.addAll(responseModel.getShipping_details());
                                    binding.tvStandardDeliveryTime.setText(getString(R.string.choose_your_delivery_option));
                                    setShippingAdapter();
                                } else {
                                    shippingList.clear();
                                    setShippingAdapter();
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

                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        log("Exceee", e.getLocalizedMessage().toString());
                    }
                }

                @Override
                public void onFailure(Call<ShippingMethodResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void setShippingAdapter() {
        shippingMethodAdapter = new ShippingMethodAdapter(this, shippingList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvShippingList.setLayoutManager(layoutManager);
        binding.rvShippingList.setAdapter(shippingMethodAdapter);
    }


    private void getTodayCoupon() {
        if (isInternetOn(this)) {
            /*final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();*/
            CouponRequestModel couponRequestModel = new CouponRequestModel("", String.valueOf(myCartList.get(0).getVenue_id()));


            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CouponResponseModel> call = apiInterface.getTodayCoupon(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""),
                    couponRequestModel);
            call.enqueue(new Callback<CouponResponseModel>() {
                @Override
                public void onResponse(Call<CouponResponseModel> call, Response<CouponResponseModel> response) {
                    try {
                        /*binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();*/

                        if (response.isSuccessful()) {
                            CouponResponseModel loyaltyResponseModel = response.body();
                            if (loyaltyResponseModel != null) {
                                if (loyaltyResponseModel.isStatus()) {
                                    //   isLoading = false;
                                    if (loyaltyResponseModel.getCouponData() != null && loyaltyResponseModel.getCouponData().size() > 0) {
                                        binding.btnSelectCoupon.setVisibility(View.VISIBLE);

                                    } else {
                                        binding.btnSelectCoupon.setVisibility(View.GONE);
                                    }
                                } else {
                                    //   showSnackBar(binding.getRoot(), loyaltyResponseModel.getMessage());
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
                    /*binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();*/
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            // binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }
}




