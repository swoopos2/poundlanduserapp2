package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.widget.ListPopupWindow;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.PosOrderCartAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityScanPaymentQRBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.FetchPOSorderRequestModel;
import com.poundland.retail.model.requestModel.QRCodePayRequestModel;
import com.poundland.retail.model.requestModel.StripeAddCardRequestModel;
import com.poundland.retail.model.responseModel.FetchPOSorderResponseModel;
import com.poundland.retail.model.responseModel.GetStripeCardDBModel;
import com.poundland.retail.model.responseModel.QRCodePayResponseModel;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.log;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.ACTIVE_PAYMENT_TYPE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ONE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_THREE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_TWO;
import static com.poundland.retail.interfaces.Constants.ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CARD_CVV;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_MONTH;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_YEAR;
import static com.poundland.retail.interfaces.Constants.CARD_NUMBER;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_ORDER;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.IS_SAVE_CARD;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PAYMENT_DETAIL_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_CREATE_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_VERIFY_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.PAYMENT_DETAIL_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.PAYMENT_GATEWAY_ACTIVE_SAVED_CARD;
import static com.poundland.retail.interfaces.Constants.PIN_CREATE_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.POSTAL_CODE;
import static com.poundland.retail.interfaces.Constants.QR_URL;

public class ScanPaymentQRActivity extends BaseActivity implements View.OnClickListener {

    private String data;
    private String posOrderId;
    private String orderNo;
    private String orderAmt;
    private ActivityScanPaymentQRBinding binding;
    private PrefManager prefManager;
    private PosOrderCartAdapter posOrderCartAdapter;
    private FetchPOSorderResponseModel poSorderResponseModel;
    private ScanPaymentQRActivity instance;
    private List<GetStripeCardDBModel.CardsBean> myCardList;
    private String cardId = "";
    private String payment_gatway = "";
    private ListPopupWindow popupWindow;
    private boolean isPopup;



    public String cardNumber = "";
    public String cardExpiryMonth = "";
    public String cardExpiryYear = "";
    public String cardCVV = "";
    public String address_one = "";
    public String address_two = "";
    public String address_three = "";
    private String postal_code;
    public boolean isSaveCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(getLayoutResourceId());
        binding = DataBindingUtil.setContentView(this, R.layout.activity_scan_payment_q_r);
        instance = this;
        myCardList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);

        if (getIntent().getStringExtra(QR_URL) != null) {
            data = getIntent().getStringExtra(QR_URL);
        } else {
            Intent intent = getIntent();
            data = intent.getData().toString();
            String action = intent.getAction();
        }


        String[] array = data.split("@");
        String[] orderNoArray = data.split("/");
        posOrderId = array[array.length - 2];
        orderAmt = array[array.length - 3];
        String lastChar = array[array.length - 1];
        String url = array[array.length - 4];

        orderNo = orderNoArray[orderNoArray.length - 1];


        binding.tvOrderAmount.setText(String.format("%.2f", Double.parseDouble(orderAmt)));
        binding.tvPosOrderId.setText(posOrderId);


        getPosOrderDetails();
        setCartAdapter();
        binding.ivBack.setOnClickListener(this);
        binding.rlCards.setOnClickListener(this);
        binding.rlAddCard.setOnClickListener(this);
        binding.tvCancel.setOnClickListener(this);
        binding.tvPay.setOnClickListener(this);
    }


    private void setCartAdapter() {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        posOrderCartAdapter = new PosOrderCartAdapter(this);
        binding.rvProductList.setLayoutManager(layoutManager);
        binding.rvProductList.setAdapter(posOrderCartAdapter);
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

            case R.id.rl_add_card:
                addNewCard();
                break;
            case R.id.tv_cancel:
                finish();
                break;
            case R.id.tv_pay:
                if (validate())
                    qrCodePayment();
                break;
        }
    }


    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_scan_payment_q_r;

    }

    @Override
    protected void onNotificationReceived(Intent intent) {

    }


    private void getPosOrderDetails() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            FetchPOSorderRequestModel requestModel = new FetchPOSorderRequestModel(posOrderId, orderNo, orderAmt);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<FetchPOSorderResponseModel> call = apiInterface.fetchOrdersByPosOrderId(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<FetchPOSorderResponseModel>() {
                @Override
                public void onResponse(Call<FetchPOSorderResponseModel> call, Response<FetchPOSorderResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            poSorderResponseModel = response.body();
                            if (poSorderResponseModel.isStatus()) {

                                if (poSorderResponseModel.getOrders() != null) {
                                    binding.tvVenueName.setText(poSorderResponseModel.getOrders().getVenue().getVenue_name());
                                    binding.tvOrderId.setText(String.valueOf(poSorderResponseModel.getOrders().getId()));

                                    getStripeCard();

                                    if (poSorderResponseModel.getOrders().getOrder_details() != null
                                            && poSorderResponseModel.getOrders().getOrder_details().size() > 0) {

                                        if (posOrderCartAdapter != null) {
                                            posOrderCartAdapter.clearData();
                                            posOrderCartAdapter.addItems(poSorderResponseModel.getOrders().getOrder_details());
                                        }
                                    }
                                } else {
                                    DialogUtils.showAlertDialogWithSingleButton(ScanPaymentQRActivity.this, getString(R.string.order_not_found), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            finish();
                                        }

                                        @Override
                                        public void onNegativeClick() {
                                        }
                                    });
                                }


                            } else {

                                final int httpCode = response.code();
                                DialogUtils.showAlertDialogWithSingleButton(ScanPaymentQRActivity.this, httpCode == 401 ? getString(R.string.season_expired) : poSorderResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                        if (poSorderResponseModel.getMessage().equals("Payment already paid.")) {
                                            finish();
                                        }

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
                            }

                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(ScanPaymentQRActivity.this, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

                                    if (httpCode == 401) {
                                        HelperClass.logOut(ScanPaymentQRActivity.this);
                                    }

                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        log("Exceee", e.getLocalizedMessage().toString());
                    }
                }

                @Override
                public void onFailure(Call<FetchPOSorderResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void getStripeCard() {  /// From saved card in DB
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();


            StripeAddCardRequestModel requestModel = new StripeAddCardRequestModel(poSorderResponseModel.getOrders().getVenue().getPayment_gatway());

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<GetStripeCardDBModel> call = apiInterface.getStripeCardSwoope(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<GetStripeCardDBModel>() {
                @Override
                public void onResponse(Call<GetStripeCardDBModel> call, Response<GetStripeCardDBModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            GetStripeCardDBModel responseModel = response.body();

                            if (responseModel.getCards() != null && responseModel.getCards().size() > 0) {
                                myCardList.clear();
                                myCardList.addAll(responseModel.getCards());

                            } else {
                                myCardList.clear();

                            }

                        } else {
                            try {
                                JSONObject jObjError = new JSONObject(response.errorBody().string());
                                Toast.makeText(instance, jObjError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                            } catch (Exception e) {
                                Toast.makeText(instance, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetStripeCardDBModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }


    public void setSpinnerForCards() {

        List<String> cards = new ArrayList<>();
        for (int i = 0; i < myCardList.size(); i++) {
            cards.add(myCardList.get(i).getCard_number());
        }

        popupWindow = new ListPopupWindow(instance);
        ArrayAdapter<String> adapter = new ArrayAdapter(instance, android.R.layout.simple_spinner_dropdown_item, cards);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.rlCards);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                setCardDetail(myCardList.get(position));

                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }

    private void setCardDetail(GetStripeCardDBModel.CardsBean defaultCardsBean) {
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

    private void qrCodePayment() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            QRCodePayRequestModel requestModel = new QRCodePayRequestModel(payment_gatway, orderNo, String.valueOf(poSorderResponseModel.getOrders().getId()), "",
                    "", "", "", cardId, "0", "", "", "",
                    "", "", "Card", orderAmt);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<QRCodePayResponseModel> call = apiInterface.qrCodePayment(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<QRCodePayResponseModel>() {
                @Override
                public void onResponse(Call<QRCodePayResponseModel> call, Response<QRCodePayResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            QRCodePayResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {

                                DialogUtils.orderSuccesDialog(instance, responseModel.getMessage(), true,
                                        responseModel.getOrder().getNet_amount(),
                                        prefManager.getPreference(EMAIL, ""), responseModel.getOrder().getUnique_code(),
                                        responseModel.getVenueData().getVenue_images(), 0, new OnDialogClickListener() {
                                            @Override
                                            public void onPositiveClick() {
                                                finish();
                                            }

                                            @Override
                                            public void onNegativeClick() {

                                            }
                                        },
                                        TextUtils.isEmpty(responseModel.getOrder().getLoyelty_consumed()) ? 0 : Double.parseDouble(responseModel.getOrder().getLoyelty_consumed()),
                                        TextUtils.isEmpty(responseModel.getOrder().getLoyelty_used_amount()) ? 0 : Double.parseDouble(responseModel.getOrder().getLoyelty_used_amount()));

                            } else {
                                DialogUtils.orderSuccesDialog(instance, responseModel.getMessage(), false,
                                        orderAmt, prefManager.getPreference(EMAIL, ""), "", "", 0, new OnDialogClickListener() {
                                            @Override
                                            public void onPositiveClick() {

                                            }

                                            @Override
                                            public void onNegativeClick() {

                                            }
                                        }, TextUtils.isEmpty(responseModel.getOrder().getLoyelty_consumed()) ? 0 : Double.parseDouble(responseModel.getOrder().getLoyelty_consumed()),
                                        TextUtils.isEmpty(responseModel.getOrder().getLoyelty_used_amount()) ? 0 : Double.parseDouble(responseModel.getOrder().getLoyelty_used_amount()));
                            }

                        } else {

                            final int httpCode = response.code();
                            DialogUtils.showAlertDialogWithSingleButton(ScanPaymentQRActivity.this, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

                                    if (httpCode == 401) {
                                        HelperClass.logOut(ScanPaymentQRActivity.this);
                                    }

                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                        log("Exceee", e.getLocalizedMessage().toString());
                    }
                }

                @Override
                public void onFailure(Call<QRCodePayResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });


        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private boolean validate() {
        if (payment_gatway.equals("")) {
            showSnackBar(binding.getRoot(), getString(R.string.select_payment_method));
            return false;
        } else if (cardId.equals("")) {
            showSnackBar(binding.getRoot(), getString(R.string.select_your_card_for_payment));
            return false;
        }
        return true;
    }

    //////////////////////////////////////////////////////////////////

    private void addNewCard() {

        if (prefManager.getPreference(CUSTOMER_ORDER, -1) == 0) {   // check whether pin is already set or not
            Intent setPin = new Intent(instance, CreatePinActivity.class);
            setPin.putExtra(FROM_WHERE, getString(R.string.cart));
            startActivityForResult(setPin, OPEN_PIN_CREATE_ACTIVITY);
        } else {
            Intent stamp = new Intent(instance, AddNewCardOnCheckoutActivity.class);
            if (poSorderResponseModel.getOrders().getVenue().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_STRIPE)) {
                stamp.putExtra(FROM_WHERE, getString(R.string.stripe));
            } else if (poSorderResponseModel.getOrders().getVenue().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
                stamp.putExtra(FROM_WHERE, getString(R.string.active));
            } else if (poSorderResponseModel.getOrders().getVenue().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_PAYDOO)) {
                showSnackBar(binding.getRoot(), "PAYDOO PAYMENT GATEWAY IS NOT SUPPORTED BY APP YET!");
                return;
            }
            startActivityForResult(stamp, ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_PAYMENT_DETAIL_ACTIVITY && resultCode == RESULT_OK && data != null) {
            Intent dataOpenPaymentDetail = data;
          //  getcallback_OPEN_PAYMENT_DETAIL_ACTIVITY(dataOpenPaymentDetail);

        } else if (requestCode == OPEN_PIN_CREATE_ACTIVITY && resultCode == RESULT_OK && data != null) {
            if (data.getBooleanExtra(PIN_CREATE_ACTIVITY_CALLBACK, false)) {
                Intent dataOpenPinCreat = data;
             //   getcallback_OPEN_PIN_CREATE_ACTIVITY(dataOpenPinCreat);
            }

        } else if (requestCode == OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY && resultCode == RESULT_OK && data != null) {
            Intent dataActivePayList = data;
          //  getcallback_OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY(dataActivePayList);

        } else if (requestCode == OPEN_PIN_VERIFY_ACTIVITY && resultCode == RESULT_OK && data != null) {
            Toast.makeText(this, "OPEN PIN VERIFY", Toast.LENGTH_SHORT).show();
        }
        //////////////////////////////////////////////
        else if (requestCode == ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE && resultCode == RESULT_OK && data != null) {

            Intent mData = data;
          //  getcallback_add_card_ACTIVITY(mData);


        }
        //////////////////////////////////////////////

    }

    public void getcallback_OPEN_PAYMENT_DETAIL_ACTIVITY(Intent dataOpenPaymentDetail) {

        cardId = dataOpenPaymentDetail.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
/*
        cardNumberToShow = dataOpenPaymentDetail.getStringExtra(CARD_NUMBER_TO_SHOW);
        cardBrand = dataOpenPaymentDetail.getStringExtra(CARD_BRAND_NAME);
        binding.tvCards.setText(cardBrand + " " + cardNumberToShow);  //////////////
        binding.tvBuyNow.setText("Proceed To Pay");  //////////////*/

    }


    public void getcallback_OPEN_PIN_CREATE_ACTIVITY(Intent dataOpenPinCreat) {

        if (poSorderResponseModel.getOrders().getVenue().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
            Intent stamp = new Intent(instance, ActiveCardActivity.class);
            stamp.putExtra(FROM_WHERE, getString(R.string.cart));
            startActivityForResult(stamp, OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY);
        } else {
            Intent stamp = new Intent(instance, CardActivity.class);
            stamp.putExtra(FROM_WHERE, getString(R.string.cart));
            startActivityForResult(stamp, OPEN_PAYMENT_DETAIL_ACTIVITY);
        }

    }


    public void getcallback_OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY(Intent dataActivePayList) {
        cardId = dataActivePayList.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
        /*payment_gatway = dataActivePayList.getStringExtra(ACTIVE_PAYMENT_TYPE);
        cardNumber = dataActivePayList.getStringExtra(CARD_NUMBER);
        cardExpiryMonth = String.valueOf(dataActivePayList.getIntExtra(CARD_EXPIRY_MONTH, 22));
        cardExpiryYear = String.valueOf(dataActivePayList.getIntExtra(CARD_EXPIRY_YEAR, 11));
        cardCVV = dataActivePayList.getStringExtra(CARD_CVV);
        cardNumberToShow = dataActivePayList.getStringExtra(CARD_NUMBER_TO_SHOW);
        cardBrand = dataActivePayList.getStringExtra(CARD_BRAND_NAME);
        binding.tvCards.setText(fromBase64ToStr(cardBrand) + " " + fromBase64ToStr(cardNumberToShow));  //////////////
        binding.tvBuyNow.setText("proceed To Pay");
        binding.tvBuyNow.setBackgroundColor(ContextCompat.getColor(context, R.color.light_green));  //////////////
        binding.ivItemImage.setBackgroundColor(ContextCompat.getColor(context, R.color.light_green_726));  //////////////


        address_one = dataActivePayList.getStringExtra(ADDRESS_ONE);
        address_two = dataActivePayList.getStringExtra(ADDRESS_TWO);
        address_three = dataActivePayList.getStringExtra(ADDRESS_THREE);
        if (!dataActivePayList.getStringExtra(POSTAL_CODE).isEmpty()) {
            postal_code = dataActivePayList.getStringExtra(POSTAL_CODE);
        }
        isSaveCard = dataActivePayList.getBooleanExtra(IS_SAVE_CARD, false);
*/
    }


    public void getcallback_add_card_ACTIVITY(Intent data) {
        isSaveCard = data.getBooleanExtra(IS_SAVE_CARD, false);
        if (poSorderResponseModel.getOrders().getVenue().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
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
            cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
            cardNumber = data.getStringExtra(CARD_NUMBER);
        }
        binding.tvCards.setText( cardNumber);

    }

}