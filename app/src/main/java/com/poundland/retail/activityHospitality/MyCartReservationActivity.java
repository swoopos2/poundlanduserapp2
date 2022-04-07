package com.poundland.retail.activityHospitality;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RelativeLayout;

import androidx.appcompat.widget.ListPopupWindow;
import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.ActiveCardActivity;
import com.poundland.retail.activity.AddNewCardOnCheckoutActivity;
import com.poundland.retail.activity.BaseActivity;
import com.poundland.retail.activity.CardActivity;
import com.poundland.retail.activity.CreatePinActivity;
import com.poundland.retail.activity.NearByDealsActivity;
import com.poundland.retail.activity.NotificationActivity;
import com.poundland.retail.activity.SpecialOfferDetailsActivity;
import com.poundland.retail.activity.VerifyPinActivity;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityMyCartReservationBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.UpdateCartListner;
import com.poundland.retail.model.requestModel.GetCartWithSummaryRequestModel;
import com.poundland.retail.model.requestModel.ReservationPaymentRequestModel;
import com.poundland.retail.model.responseModel.ReservationCheckoutResponseModel;
import com.poundland.retail.model.responseModel.ReservationPaymentResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.formatDateMM;
import static com.poundland.retail.appUtils.HelperClass.formatDateTimeTO_Time;
import static com.poundland.retail.appUtils.HelperClass.getDaysNameFromYYYY_MM_DD;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.log;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.ACTIVE_PAYMENT_TYPE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ONE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_THREE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_TWO;
import static com.poundland.retail.interfaces.Constants.ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CARD;
import static com.poundland.retail.interfaces.Constants.CARD_CVV;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_MONTH;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_YEAR;
import static com.poundland.retail.interfaces.Constants.CARD_NUMBER;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_ORDER;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.IS_SAVE_CARD;
import static com.poundland.retail.interfaces.Constants.LAST_NAME;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.NONE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_ADD_ADDRESS_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_CHANGE_ADDRESS_ACTIVITY;
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
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;


public class MyCartReservationActivity extends BaseActivity implements View.OnClickListener, UpdateCartListner, SwipeRefreshLayout.OnRefreshListener, DrawerListner {
    private ActivityMyCartReservationBinding binding;
    private PrefManager prefManager;
    private String venue_id = "";
    private MyCartReservationActivity instance = null;
    private String PAYMENT_MODE = CARD;
    private String cardId = "";
    private String payment_gatway = PAYMENT_GATEWAY_STRIPE;
    private String cardNumber = "";
    private String cardExpiryMonth = "";
    private String cardExpiryYear = "";
    private String cardCVV = "";
    private String totalAmountPayable;
    private String DEFAULT_DELIVERY_CHARGE = "0.00";
    private String deliveryCharge = DEFAULT_DELIVERY_CHARGE;
    private String LOYALTY = "loyalty";

    private String address_one = "";
    private String address_two = "";
    private String address_three = "";
    private String postal_code = "";
    private boolean isSaveCard;

    private String format = "HH:mm:ss";
    private SimpleDateFormat sdf = new SimpleDateFormat(format);
    private String currentTime;
    private String shippingId = "";
    private KProgressHUD dialogGlobal = null;
    private ReservationCheckoutResponseModel responseModel;
    private int isCard = 1;
    private boolean isPopup;
    private ListPopupWindow popupWindow;
    private List<ReservationCheckoutResponseModel.DefaultCardsBean> myCardList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // binding = DataBindingUtil.setContentView(this, R.layout.activity_my_cart);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_cart_reservation);
        dialogGlobal = DialogUtils.getCustomDialog(this);
        init();
        getReservationCartData(NONE, "", "");
        setListeners();
    }

    private void init() {
        instance = this;
        myCardList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        binding.tbMyCart.tvTitle.setText(getString(R.string.cart));
        binding.tbMyCart.ivCart.setVisibility(View.GONE);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.tbMyCart.ivNotification.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.LEFT_OF, R.id.iv_notification);

        binding.tbMyCart.ivNotification.setLayoutParams(params); //function for adjust view if you want some view gone visible
        binding.tbMyCart.ivNotification.setVisibility(View.GONE);
    }

    private void setListeners() {
        binding.tbMyCart.tvTitle.setOnClickListener(this);
        binding.tbMyCart.ivBack.setOnClickListener(this);
        binding.tbMyCart.ivNotification.setOnClickListener(this);
        binding.tvCheckoutForPayment.setOnClickListener(this);
        binding.tvStartShopping.setOnClickListener(this);
        binding.rlCards.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
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

            case R.id.iv_notification:
                Intent noti = new Intent(this, NotificationActivity.class);
                startActivity(noti);
                break;


            case R.id.tv_checkout_for_payment:
                // if (Double.parseDouble(totalAmountPayable) > 0) {
                if (isValid()) {
                    Intent stamp = new Intent(instance, VerifyPinActivity.class);
                    stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                    stamp.putExtra(POSITION, -1);
                    startActivityForResult(stamp, OPEN_PIN_VERIFY_ACTIVITY);
                }
                break;
            case R.id.tv_start_shopping:
                if (dialogGlobal != null)
                    dialogGlobal.show();
                finish();
                break;
        }
    }

    private void isCardPresent() {
        if (responseModel.getData().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_STRIPE)) {
            if (prefManager.getPreference(CUSTOMER_ORDER, -1) == 0) {
                Intent setPin = new Intent(this, CreatePinActivity.class);
                setPin.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(setPin, OPEN_PIN_CREATE_ACTIVITY);
            } else {
                Intent stamp = new Intent(this, CardActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(stamp, OPEN_PAYMENT_DETAIL_ACTIVITY);
            }
        } else if (responseModel.getData().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
            if (prefManager.getPreference(CUSTOMER_ORDER, -1) == 0) {   // check whether pin is already set or not
                Intent setPin = new Intent(instance, CreatePinActivity.class);
                setPin.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(setPin, OPEN_PIN_CREATE_ACTIVITY);
            } else {
                Intent stamp = new Intent(this, ActiveCardActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(stamp, OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY);
            }
        } else if (responseModel.getData().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_PAYDOO)) {
            showSnackBar(binding.getRoot(), "PAYDOO PAYMENT GATEWAY IS NOT SUPPORTED BY APP YET!");
        }
    }

    private boolean isValid() {
        try {
            if (cardId.equals("")) {
                isCardPresent();
                return false;
            }

        } catch (Exception exc) {
            log("Validation exc", "" + exc.getMessage());
        }
        return true;
    }

    private void getReservationCartData(String type, String cartId, String quantity) {

        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            final GetCartWithSummaryRequestModel requestModel = new GetCartWithSummaryRequestModel(prefManager.getPreference(LOGIN_ID, 0));
            Call<ReservationCheckoutResponseModel> call = apiInterface.reservation_checkout(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            binding.llNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<ReservationCheckoutResponseModel>() {
                @Override
                public void onResponse(Call<ReservationCheckoutResponseModel> call, Response<ReservationCheckoutResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            if (response.body().isStatus()) {
                                responseModel = response.body();
                                if (responseModel.getData() != null) {
                                    binding.llNoDataFound.setVisibility(View.GONE);
                                    binding.layoutCart.setVisibility(View.VISIBLE);
                                    binding.tvCheckoutForPayment.setVisibility(View.VISIBLE);
                                    setData(responseModel);
                                } else {
                                    prefManager.savePreference(VENUE_ID_IN_CART, "");
                                    binding.llNoDataFound.setVisibility(View.VISIBLE);
                                    binding.layoutCart.setVisibility(View.GONE);
                                    binding.tvCheckoutForPayment.setVisibility(View.GONE);
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

                    } catch (
                            Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<ReservationCheckoutResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }

    }

    private void setData(ReservationCheckoutResponseModel responseModel) {

        if (responseModel.getDefaultCards() != null && responseModel.getDefaultCards().size() > 0) {
            myCardList.addAll(responseModel.getDefaultCards());
            //  setSpinnerForCards();
            setCardDetail(myCardList.get(0));
        } else {
            binding.rlCards.setVisibility(View.GONE);
            //   binding.rlAddNewCard.setVisibility(View.GONE);
        }


        binding.tvVenueName.setText(responseModel.getData().getVenue_name());
        binding.tvVenueAddress.setText(responseModel.getData().getAddress_1());
        binding.tvBookingDate.setText(getDaysNameFromYYYY_MM_DD(responseModel.getData().getDate()) + ", " + HelperClass.formatddMMyyyy(responseModel.getData().getDate()));

        binding.tvCheckinTime.setText(formatDateTimeTO_Time(responseModel.getData().getCheck_in()));
        binding.tvCheckinDay.setText(getDaysNameFromYYYY_MM_DD(responseModel.getData().getCheck_in()));
        binding.tvDateMonth.setText(formatDateMM(responseModel.getData().getCheck_in()));

        binding.tvCheckoutTime.setText(formatDateTimeTO_Time(responseModel.getData().getCheck_out()));
        binding.tvCheckoutDay.setText(getDaysNameFromYYYY_MM_DD(responseModel.getData().getCheck_out()));
        binding.tvCheckoutDateMonth.setText(formatDateMM(responseModel.getData().getCheck_out()));

        binding.tvNoOfGuest.setText(String.valueOf(responseModel.getData().getTotal_guest()) + " Guest");
        binding.tvPayableAmt.setText(getString(R.string.pound) + responseModel.getData().getAmount());
        binding.tvCheckoutForPayment.setText(getString(R.string.checkout) + "  " + getString(R.string.pound) + responseModel.getData().getAmount());

        Glide.with(instance).load(ApiRequestUrl.BASE_URL_VENUE + responseModel.getData().getVenue_images()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(binding.ivVenueImage);

        Glide.with(instance).load(ApiRequestUrl.BASE_URL + responseModel.getData().getQr_code()).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(binding.ivQrImage);
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


    private void setCardDetail(ReservationCheckoutResponseModel.DefaultCardsBean defaultCardsBean) {
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
            if (responseModel.getData().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_STRIPE)) {
                Intent stamp = new Intent(this, AddNewCardOnCheckoutActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.stripe));
                startActivityForResult(stamp, ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE);
            } else if (responseModel.getData().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
                Intent stamp = new Intent(this, AddNewCardOnCheckoutActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.active));
                startActivityForResult(stamp, ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE);

            } else if (responseModel.getData().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_PAYDOO)) {
                showSnackBar(binding.getRoot(), "PAYDOO PAYMENT GATEWAY IS NOT SUPPORTED BY APP YET!");
            }
        }


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_CHANGE_ADDRESS_ACTIVITY && resultCode == RESULT_OK && data != null) {

            // addressId = String.valueOf(data.getIntExtra(ADDRESS_ID, 0));
            address_three = data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK);

        }
        else if (requestCode == OPEN_PAYMENT_DETAIL_ACTIVITY && resultCode == RESULT_OK && data != null) {
            cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
            isCard = 1;
            if (isValid())
                checkOut();
        }
        else if (requestCode == OPEN_PIN_CREATE_ACTIVITY && resultCode == RESULT_OK && data != null) {
            boolean isOK = data.getBooleanExtra(PIN_CREATE_ACTIVITY_CALLBACK, false);
            if (isOK) {
                Intent stamp = new Intent(this, CardActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(stamp, OPEN_PAYMENT_DETAIL_ACTIVITY);

            }
        }
        else if (requestCode == OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY && resultCode == RESULT_OK && data != null) {
            cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
            payment_gatway = data.getStringExtra(Constants.ACTIVE_PAYMENT_TYPE);
            cardNumber = data.getStringExtra(CARD_NUMBER);
            cardExpiryMonth = String.valueOf(data.getIntExtra(CARD_EXPIRY_MONTH, 00));
            cardExpiryYear = String.valueOf(data.getIntExtra(CARD_EXPIRY_YEAR, 00));
            cardCVV = data.getStringExtra(CARD_CVV);

            address_one = data.getStringExtra(ADDRESS_ONE);
            address_two = data.getStringExtra(ADDRESS_TWO);
            address_three = data.getStringExtra(ADDRESS_THREE);
            if (!data.getStringExtra(POSTAL_CODE).isEmpty()) {
                postal_code = data.getStringExtra(POSTAL_CODE);
            }
            isSaveCard = data.getBooleanExtra(IS_SAVE_CARD, false);
            checkOut();

        }
        else if (requestCode == OPEN_ADD_ADDRESS_ACTIVITY && resultCode == RESULT_OK && data != null) {
            try {
                // addressId = String.valueOf(data.getIntExtra(ADDRESS_ID, 0));
                address_three = data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK);
            } catch (Exception e) {
                e.printStackTrace();
                log("bbb", e.getMessage());
            }
        }
        else if (requestCode == OPEN_PIN_VERIFY_ACTIVITY && resultCode == RESULT_OK && data != null) {
            checkOut();
        }
        else if (requestCode == ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE && resultCode == RESULT_OK && data != null) {
            isSaveCard = data.getBooleanExtra(IS_SAVE_CARD, false);
            if (responseModel.getData().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
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

        }
    }

    public void checkOut() {

        ReservationPaymentRequestModel requestModel = new ReservationPaymentRequestModel(responseModel.getData().getId(),
                prefManager.getPreference(FIRST_NAME, "") + " " + prefManager.getPreference(LAST_NAME, ""),
                prefManager.getPreference(EMAIL, ""), prefManager.getPreference(LOGIN_ID, 0), isCard, cardId
                , cardExpiryMonth, cardExpiryYear, cardCVV, cardNumber, address_three, postal_code, isSaveCard);

        final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
        if (dialog != null)
            dialog.show();
        if (isInternetOn(this)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<ReservationPaymentResponseModel> call = apiInterface.reservation_payment(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<ReservationPaymentResponseModel>() {
                @Override
                public void onResponse(Call<ReservationPaymentResponseModel> call, Response<ReservationPaymentResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            cardId = "";
                            ReservationPaymentResponseModel checkOutResponseModel = response.body();
                            if (checkOutResponseModel.isStatus()) {

                                DialogUtils.reservationOrderSuccesDialog(MyCartReservationActivity.this, checkOutResponseModel.getMessage(),
                                        true, checkOutResponseModel.getData().getAmount(), prefManager.getPreference(EMAIL, ""),
                                        String.valueOf(checkOutResponseModel.getData().getReservation_id()), ApiRequestUrl.BASE_URL_VENUE + responseModel.getData().getVenue_images(), checkOutResponseModel.getData().getCheck_in(), checkOutResponseModel.getData().getTotal_guest(),
                                        checkOutResponseModel.getData().getReservation_id(), new OnDialogClickListener() {
                                            @Override
                                            public void onPositiveClick() {
                                                prefManager.savePreference(VENUE_ID_IN_CART, "");
                                                finish();
                                                Intent intent = new Intent(instance, MyOrderReservationActivity.class);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onNegativeClick() {

                                            }
                                        });

                            } else {

                                DialogUtils.reservationOrderSuccesDialog(MyCartReservationActivity.this, checkOutResponseModel.getMessage(), false,
                                        totalAmountPayable, prefManager.getPreference(EMAIL, ""), "", ApiRequestUrl.BASE_URL_VENUE + responseModel.getData().getVenue_images(), "", 0, "N/A", new OnDialogClickListener() {
                                            @Override
                                            public void onPositiveClick() {

                                            }

                                            @Override
                                            public void onNegativeClick() {

                                            }
                                        });
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
                public void onFailure(Call<ReservationPaymentResponseModel> call, Throwable t) {
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

    @Override
    public void onIncrease(int position, String count) {

    }

    @Override
    public void onDecrease(int position, String count) {


    }

    @Override
    public void onDelete(int position) {
    }

    @Override
    public void onDrawerSelect(int position, int clickId) {

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
}



