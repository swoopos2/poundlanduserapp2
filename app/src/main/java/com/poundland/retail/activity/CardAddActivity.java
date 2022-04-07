package com.poundland.retail.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.stripe.android.ApiResultCallback;
import com.stripe.android.PaymentConfiguration;
import com.stripe.android.SetupIntentResult;
import com.stripe.android.Stripe;
import com.stripe.android.model.Card;
import com.stripe.android.model.SetupIntent;
import com.stripe.android.model.Token;
import com.stripe.android.view.CardInputWidget;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiClientForStripe;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityCardAddBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.NotificationDeleteRequestModel;
import com.poundland.retail.model.requestModel.StripeAddCardRequestModel;
import com.poundland.retail.model.responseModel.GetUserLocationResponseModel;
import com.poundland.retail.model.responseModel.SaveUserCardDetailsResponseModel;
import com.poundland.retail.model.responseModel.StripeAddCardResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import io.card.payment.CardIOActivity;
import io.card.payment.CreditCard;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STRIPE_CUST_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class CardAddActivity extends BaseActivity implements View.OnClickListener {

    private static final int MY_SCAN_REQUEST_CODE = 122;
    private ActivityCardAddBinding binding;
    private CardAddActivity instance = null;
    private String paymentIntentClientSecret = "sk_live_CtZj9LK0Wc29Iaa0V9UadPON";
    private List<GetUserLocationResponseModel.DataBean> lookUpList;
    private Stripe stripe;
    Card card;
    private PrefManager prefManager;
    private String tokenId;
    private ListPopupWindow popupWindow;
    private String address;
    private boolean isPopup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_add);
//        binding.tvSubmit.setAlpha(.5f);
        instance = this;
        prefManager = PrefManager.getInstance(instance);
        if (ApiRequestUrl.BASE_URL.equals("https://swoopelocaltesting.com/admin/public/")) {
            stripe = new Stripe(instance, getString(R.string.strip_publish_key_testing));
            paymentIntentClientSecret = getString(R.string.paymentIntentClientSecret_testing);
            Log.e("STRIPE TEST", "" + paymentIntentClientSecret);
        } else {
            stripe = new Stripe(instance, getString(R.string.strip_publish_key));
            paymentIntentClientSecret = getString(R.string.paymentIntentClientSecret);
            Log.e("STRIPE TEST", "" + paymentIntentClientSecret);
        }


        lookUpList = new ArrayList<>();
        binding.ivBack.setOnClickListener(this);
        binding.tvSubmit.setOnClickListener(this);
        binding.llScanCard.setOnClickListener(this);
        binding.tvLookUp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
                break;
            case R.id.tv_look_up:
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    if (!TextUtils.isEmpty(binding.etPostCode.getText().toString().trim())) {
                        getUserLocation(binding.etPostCode.getText().toString().trim());
                        isPopup = true;

                    } else {
                        showSnackBar(binding.cardInputWidget, getString(R.string.enter_post_code));
                    }
                }
                break;

            case R.id.ll_scan_card:
                Intent scanIntent = new Intent(this, CardIOActivity.class);

                // customize these values to suit your needs.
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_EXPIRY, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_SCAN_EXPIRY, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CVV, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_CARDHOLDER_NAME, false); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_LANGUAGE_OR_LOCALE, "en"); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_GUIDE_COLOR, getResources().getColor(R.color.app_header_color)); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_REQUIRE_POSTAL_CODE, false); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_RETURN_CARD_IMAGE, true); // default: false
                scanIntent.putExtra(CardIOActivity.EXTRA_HIDE_CARDIO_LOGO, true); // default: false

                startActivityForResult(scanIntent, MY_SCAN_REQUEST_CODE);

                break;

            case R.id.tv_submit:
                if (!TextUtils.isEmpty(binding.etPostCode.getText().toString())) {
                    if (!TextUtils.isEmpty(binding.etAddressLineOne.getText().toString())) {

                        WeakReference<CardAddActivity> weakActivity = new WeakReference<>(this);
                        // Get the card details from the card widget
                        Card card = binding.cardInputWidget.getCard();
               /* Address address = new Address();
                address.getLine1();
                address.getLine2();
                address.getCountry();*/

                        if (card != null && card.validateCard()) {
                            // Create a Stripe token from the card details
                            stripe = new Stripe(getApplicationContext(), PaymentConfiguration.getInstance(getApplicationContext()).getPublishableKey());
                            stripe.createToken(card, new ApiResultCallback<Token>() {
                                @Override
                                public void onSuccess(@NonNull Token token) {
                                    Log.e("inisde id", token.getId());
                                    tokenId = token.getId();
                                    // Send the token identifier to the server...
//                                    new DoPayment(token).execute();
                                    addCard();
                                }

                                @Override
                                public void onError(@NonNull Exception error) {
                                    Log.e("inisde ex", error.toString());
                                    Toast.makeText(getApplicationContext(),
                                            error.getLocalizedMessage(),
                                            Toast.LENGTH_LONG).show();
                                    // Handle error
                                }
                            });
                        } else
                            showSnackBar(binding.cardInputWidget, getString(R.string.invalid_card_details));


                   /* PaymentMethodCreateParams params = binding.cardInputWidget.getPaymentMethodCreateParams();
                    if (params != null) {
                        ConfirmPaymentIntentParams confirmParams = ConfirmPaymentIntentParams
                                .createWithPaymentMethodCreateParams(params, paymentIntentClientSecret,"",true);

                        stripe = new Stripe(instance, PaymentConfiguration.getInstance(instance).getPublishableKey());
                        stripe.confirmPayment(instance, confirmParams);
                    }*/
                                   /*
        // Collect card details
        PaymentMethodCreateParams.Card card = binding.cardInputWidget.getPaymentMethodCard();

        // Later, you will need to attach the PaymentMethod to the Customer it belongs to.
        // This example collects the customer's email to know which customer the PaymentMethod belongs to, but your app might use an account id, session cookie, etc.
        PaymentMethod.BillingDetails billingDetails = (new PaymentMethod.BillingDetails.Builder())
                .setEmail(prefManager.getPreference(EMAIL))
                .setName(prefManager.getPreference(FIRST_NAME))
                .setPhone(prefManager.getPreference(CONTACT_NO))
                .build();
        if (card != null) {
            // Create SetupIntent confirm parameters with the above
            PaymentMethodCreateParams paymentMethodParams = PaymentMethodCreateParams
                    .create(card, billingDetails);
            ConfirmSetupIntentParams confirmParams = ConfirmSetupIntentParams
                    .create(paymentMethodParams, paymentIntentClientSecret);
            stripe.confirmSetupIntent(this, confirmParams);
        }*/

                    } else
                        showSnackBar(binding.cardInputWidget, getString(R.string.enter_address_one));


                } else showSnackBar(binding.cardInputWidget, getString(R.string.enter_post_code));


                ////////////////////////////////////////////
                break;

        }
    }

    public void setAddressSpinner() {
        try {
            popupWindow = new ListPopupWindow(this);
            final ArrayList<String> stringArrayList = new ArrayList<>();
            for (int i = 0; i < lookUpList.size() - 1; i++) {
                stringArrayList.add(lookUpList.get(i).getLine_1());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringArrayList);
            popupWindow.setAdapter(adapter);
            popupWindow.setAnchorView(binding.tvLookUpData);
            popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    // postal_code = binding.etPostCode.getText().toString().trim();
                    //  address = lookUpList.get(position).getLine_1();
                    binding.etAddressLineOne.setText(lookUpList.get(position).getLine_1());
                    binding.etAddressLineTwo.setText(lookUpList.get(position).getLine_2());
                    binding.etAddressLineThree.setText(lookUpList.get(position).getDistrict());
                    popupWindow.dismiss();
                    isPopup = false;
                }
            });
            popupWindow.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        WeakReference<Activity> weakActivity = new WeakReference<>(this);

        // Handle the result of stripe.confirmSetupIntent
        stripe.onSetupResult(requestCode, data, new ApiResultCallback<SetupIntentResult>() {
            @Override
            public void onSuccess(@NonNull SetupIntentResult result) {
                SetupIntent setupIntent = result.getIntent();
                SetupIntent.Status status = setupIntent.getStatus();
                if (status == SetupIntent.Status.Succeeded) {
                    // Setup completed successfully
                    runOnUiThread(() -> {
                        if (weakActivity.get() != null) {
                            Activity activity = weakActivity.get();
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setTitle("Setup completed");
                            Gson gson = new GsonBuilder().setPrettyPrinting().create();
                            builder.setMessage(gson.toJson(setupIntent));
                            builder.setPositiveButton("Restart demo", (DialogInterface dialog, int index) -> {
                                CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
                                cardInputWidget.clear();


                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                } else if (status == SetupIntent.Status.RequiresPaymentMethod) {
                    // Setup failed – allow retrying using a different payment method
                    runOnUiThread(() -> {
                        Activity activity = weakActivity.get();
                        if (activity != null) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                            builder.setTitle("Setup failed");
                            builder.setMessage(setupIntent.getLastSetupError().getMessage());
                            builder.setPositiveButton("Ok", (DialogInterface dialog, int index) -> {
                                CardInputWidget cardInputWidget = findViewById(R.id.cardInputWidget);
                                cardInputWidget.clear();
                            });
                            AlertDialog dialog = builder.create();
                            dialog.show();
                        }
                    });
                }
            }

            @Override
            public void onError(@NonNull Exception e) {
                // Setup request failed – allow retrying using the same payment method
                runOnUiThread(() -> {
                    Activity activity = weakActivity.get();
                    if (activity != null) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
                        builder.setMessage(e.toString());
                        builder.setPositiveButton("Ok", null);
                        AlertDialog dialog = builder.create();
                        dialog.show();
                    }
                });
            }
        });
        ////////////////////////////////////////////////////////////////////////////
        if (requestCode == MY_SCAN_REQUEST_CODE) {
            String resultDisplayStr;
            if (data != null && data.hasExtra(CardIOActivity.EXTRA_SCAN_RESULT)) {
                CreditCard scanResult = data.getParcelableExtra(CardIOActivity.EXTRA_SCAN_RESULT);

                // Never log a raw card number. Avoid displaying it, but if necessary use getFormattedCardNumber()
                resultDisplayStr = "Card Number: " + scanResult.getRedactedCardNumber() + "\n";
                binding.cardInputWidget.setCardNumber(scanResult.getFormattedCardNumber());
                if (scanResult.isExpiryValid()) {
                    resultDisplayStr += "Expiration Date: " + scanResult.expiryMonth + "/" + scanResult.expiryYear + "\n";
                    binding.cardInputWidget.setExpiryDate(scanResult.expiryMonth, scanResult.expiryYear);
                }

                if (scanResult.cvv != null) {
                    resultDisplayStr += "CVV has " + scanResult.cvv.length() + " digits.\n";
                    binding.cardInputWidget.setCvcCode(scanResult.cvv.toString());
                }

                if (scanResult.postalCode != null) {
                    resultDisplayStr += "Postal Code: " + scanResult.postalCode + "\n";
                }
            } else {
                resultDisplayStr = "Scan was canceled.";
            }
            // do something with resultDisplayStr, maybe display it in a textView
//            binding.tvScanCard.setText(resultDisplayStr);
        }
        // else handle other activity results

        ////////////////////////////////////////////////////////////////////////////

    }

    private void addCard() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            String stripeCustId = prefManager.getPreference(STRIPE_CUST_ID, "");
            ApiInterface apiInterface = ApiClientForStripe.getClientForStripe().create(ApiInterface.class);
            Call<StripeAddCardResponseModel> call = apiInterface.addStripeCard("Bearer " + paymentIntentClientSecret, stripeCustId, tokenId);
            call.enqueue(new Callback<StripeAddCardResponseModel>() {
                @Override
                public void onResponse(Call<StripeAddCardResponseModel> call, Response<StripeAddCardResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            StripeAddCardResponseModel cardResponseModel = response.body();
                            if (cardResponseModel != null)
                                saveCardToServer(cardResponseModel);

                        } else {
                            final int httpCode = response.code();
                            assert response.errorBody() != null;
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            String errMsg = getResources().getString(R.string.something_went_wrong);
                            if (httpCode == 401) {
                                errMsg = getResources().getString(R.string.season_expired);
                            } else if (httpCode == 402) {
                                errMsg = jObjError.getJSONObject("error").getString("message");
                            } else if (httpCode == 400) {
                                errMsg = jObjError.getJSONObject("error").getString("message");
                            }
                            DialogUtils.showAlertDialogWithSingleButton(instance, errMsg, new OnDialogClickListener() {
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
                public void onFailure(Call<StripeAddCardResponseModel> call, Throwable t) {
//                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
//            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void saveCardToServer(StripeAddCardResponseModel cardResponseModel) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            StripeAddCardRequestModel requestModel = new StripeAddCardRequestModel(prefManager.getPreference(STRIPE_CUST_ID, "")
                    , prefManager.getPreference(FIRST_NAME, ""), cardResponseModel.getId(),
                    "************" + cardResponseModel.getLast4(),
                    cardResponseModel.getBrand(), binding.cbDefaultCard.isChecked() ? "1" : "0", String.valueOf(cardResponseModel.getExp_month()).length() == 2 ? String.valueOf(cardResponseModel.getExp_month()) : "0" + String.valueOf(cardResponseModel.getExp_month()),
                    String.valueOf(cardResponseModel.getExp_year()), "stripe", binding.etAddressLineOne.getText().toString().trim(),
                    binding.etAddressLineTwo.getText().toString().trim(), binding.etAddressLineThree.getText().toString().trim(), binding.etPostCode.getText().toString().trim());

            Call<SaveUserCardDetailsResponseModel> call = apiInterface.saveUserCardDetails(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<SaveUserCardDetailsResponseModel>() {
                @Override
                public void onResponse(Call<SaveUserCardDetailsResponseModel> call, Response<SaveUserCardDetailsResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            SaveUserCardDetailsResponseModel responseModel = response.body();
                            if (responseModel != null) {
                                if (responseModel.isStatus()) {

                                    DialogUtils.dialogGeneratePinSuccess(instance, responseModel.getMessage(), getString(R.string.add_card), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            Intent intent = new Intent();
                                            setResult(RESULT_OK, intent);
                                            finish();
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });

                                } else {
                                    DialogUtils.dialogGeneratePinSuccess(instance, responseModel.getMessage(), getString(R.string.add_card), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {

                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });

                                }

                            }
                        } else {
                            showSnackBar(binding.cardInputWidget, "" + response.message());
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<SaveUserCardDetailsResponseModel> call, Throwable t) {
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
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
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

    /// Look Up
    private void getUserLocation(String postal) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            NotificationDeleteRequestModel beanX = new NotificationDeleteRequestModel(postal, "");
            Call<GetUserLocationResponseModel> call = apiInterface.get_user_location
                    (prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), beanX);
            call.enqueue(new Callback<GetUserLocationResponseModel>() {
                @Override
                public void onResponse(Call<GetUserLocationResponseModel> call, Response<GetUserLocationResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            GetUserLocationResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {
                                if (responseModel.getData() != null && responseModel.getData().size() > 0) {
                                    lookUpList.clear();
                                    lookUpList.addAll(responseModel.getData());
                                    setAddressSpinner();
                                } else {
                                    lookUpList.clear();
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
                public void onFailure(Call<GetUserLocationResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }
}




/*  @OnTextChanged(value = R.id.cardNumberEditText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    protected void onCardNumberTextChanged(Editable s) {
        if (!isInputCorrect(s, CARD_NUMBER_TOTAL_SYMBOLS, CARD_NUMBER_DIVIDER_MODULO, CARD_NUMBER_DIVIDER)) {
            s.replace(0, s.length(), concatString(getDigitArray(s, CARD_NUMBER_TOTAL_DIGITS), CARD_NUMBER_DIVIDER_POSITION, CARD_NUMBER_DIVIDER));
        }
    }

    @OnTextChanged(value = R.id.cardDateEditText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    protected void onCardDateTextChanged(Editable s) {
        if (!isInputCorrect(s, CARD_DATE_TOTAL_SYMBOLS, CARD_DATE_DIVIDER_MODULO, CARD_DATE_DIVIDER)) {
            s.replace(0, s.length(), concatString(getDigitArray(s, CARD_DATE_TOTAL_DIGITS), CARD_DATE_DIVIDER_POSITION, CARD_DATE_DIVIDER));
        }
    }

    @OnTextChanged(value = R.id.cardCVCEditText, callback = OnTextChanged.Callback.AFTER_TEXT_CHANGED)
    protected void onCardCVCTextChanged(Editable s) {
        if (s.length() > CARD_CVC_TOTAL_SYMBOLS) {
            s.delete(CARD_CVC_TOTAL_SYMBOLS, s.length());
        }
    }*/

   /* private boolean isInputCorrect(Editable s, int size, int dividerPosition, char divider) {
        boolean isCorrect = s.length() <= size;
        for (int i = 0; i < s.length(); i++) {
            if (i > 0 && (i + 1) % dividerPosition == 0) {
                isCorrect &= divider == s.charAt(i);
            } else {
                isCorrect &= Character.isDigit(s.charAt(i));
            }
        }
        return isCorrect;
    }

    private String concatString(char[] digits, int dividerPosition, char divider) {
        final StringBuilder formatted = new StringBuilder();

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] != 0) {
                formatted.append(digits[i]);
                if ((i > 0) && (i < (digits.length - 1)) && (((i + 1) % dividerPosition) == 0)) {
                    formatted.append(divider);
                }
            }
        }

        return formatted.toString();
    }

    private char[] getDigitArray(final Editable s, final int size) {
        char[] digits = new char[size];
        int index = 0;
        for (int i = 0; i < s.length() && index < size; i++) {
            char current = s.charAt(i);
            if (Character.isDigit(current)) {
                digits[index] = current;
                index++;
            }
        }
        return digits;
    }*/