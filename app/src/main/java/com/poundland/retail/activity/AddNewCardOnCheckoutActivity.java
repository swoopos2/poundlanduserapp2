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
import com.poundland.retail.databinding.ActivityAddNewCardOnCheckoutBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.NotificationDeleteRequestModel;
import com.poundland.retail.model.requestModel.StripeAddCardRequestModel;
import com.poundland.retail.model.responseModel.GetUserLocationResponseModel;
import com.poundland.retail.model.responseModel.SaveUserCardDetailsResponseModel;
import com.poundland.retail.model.responseModel.StripeAddCardResponseModel;

import org.json.JSONObject;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.ACTIVE_PAYMENT_TYPE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ONE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_THREE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_TWO;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CARD_BRAND_NAME;
import static com.poundland.retail.interfaces.Constants.CARD_CVV;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_MONTH;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_YEAR;
import static com.poundland.retail.interfaces.Constants.CARD_NUMBER;
import static com.poundland.retail.interfaces.Constants.CARD_NUMBER_TO_SHOW;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.IS_SAVE_CARD;
import static com.poundland.retail.interfaces.Constants.PAYMENT_DETAIL_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.PAYMENT_GATEWAY_ACTIVE;
import static com.poundland.retail.interfaces.Constants.POSTAL_CODE;
import static com.poundland.retail.interfaces.Constants.STRIPE_CUST_ID;

public class AddNewCardOnCheckoutActivity extends BaseActivity implements View.OnClickListener {

    private ActivityAddNewCardOnCheckoutBinding binding;
    private AddNewCardOnCheckoutActivity instance = null;
    private PrefManager prefManager;
    private String fromWhere;
    private List<GetUserLocationResponseModel.DataBean> lookUpList;
    private ListPopupWindow popupWindow;
    private boolean isPopup;
    private boolean isSaveCard;
    ///////////////stripe////////////////////////////
    private Stripe stripe;
    private String paymentIntentClientSecret = "sk_live_CtZj9LK0Wc29Iaa0V9UadPON";
    private String tokenId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_new_card_on_checkout);
        instance = this;
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        binding.rlSaveCard.setVisibility(View.VISIBLE);
        lookUpList = new ArrayList<>();
        prefManager = PrefManager.getInstance(instance);
        binding.ivBack.setOnClickListener(this);
        binding.tvSubmit.setOnClickListener(this);
        binding.tvLookUp.setOnClickListener(this);

        if (ApiRequestUrl.BASE_URL.equals("https://swoopelocaltesting.com/admin/public/")) {
            stripe = new Stripe(instance, getString(R.string.strip_publish_key_testing));
            paymentIntentClientSecret = getString(R.string.paymentIntentClientSecret_testing);
        } else {
            stripe = new Stripe(instance, getString(R.string.strip_publish_key));
            paymentIntentClientSecret = getString(R.string.paymentIntentClientSecret);
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                Intent intent1 = new Intent();
                setResult(RESULT_CANCELED, intent1);
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
            case R.id.tv_submit:
                isSaveCard = binding.cbSaveCard.isChecked();
                if (fromWhere.equals(getString(R.string.active))) {
                    activeSubmit();
                } else {
                    stripeSubmit();
                }


                break;
        }
    }

    private void stripeSubmit() {
        if (!TextUtils.isEmpty(binding.etPostCode.getText().toString())) {
            if (!TextUtils.isEmpty(binding.etAddressLineOne.getText().toString())) {

                WeakReference<AddNewCardOnCheckoutActivity> weakActivity = new WeakReference<>(this);
                Card card = binding.cardInputWidget.getCard();
                if (card != null && card.validateCard()) {
                    // Create a Stripe token from the card details
                    stripe = new Stripe(getApplicationContext(), PaymentConfiguration.getInstance(getApplicationContext()).getPublishableKey());
                    stripe.createToken(card, new ApiResultCallback<Token>() {
                        @Override
                        public void onSuccess(@NonNull Token token) {
                            Log.e("inisde id", token.getId());
                            tokenId = token.getId();
                            addCard();
                        }

                        @Override
                        public void onError(@NonNull Exception error) {
                            Log.e("inisde ex", error.toString());
                            Toast.makeText(getApplicationContext(), error.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    });
                } else
                    showSnackBar(binding.cardInputWidget, getString(R.string.invalid_card_details));

            } else
                showSnackBar(binding.cardInputWidget, getString(R.string.enter_address_one));


        } else showSnackBar(binding.cardInputWidget, getString(R.string.enter_post_code));

    }

    private void activeSubmit() {

        if (!TextUtils.isEmpty(binding.etPostCode.getText().toString())) {
            if (!TextUtils.isEmpty(binding.etAddressLineOne.getText().toString())) {
                Card card = binding.cardInputWidget.getCard();
                if (card != null && card.validateCard()) {

                    Intent intent = new Intent();
                    intent.putExtra(CARD_NUMBER, card.getNumber());
                    intent.putExtra(CARD_EXPIRY_MONTH, card.getExpMonth());
                    intent.putExtra(CARD_EXPIRY_YEAR, card.getExpYear());
                    intent.putExtra(CARD_CVV, card.getCvc());
                    intent.putExtra(ACTIVE_PAYMENT_TYPE, PAYMENT_GATEWAY_ACTIVE);

                    intent.putExtra(ADDRESS_ONE, binding.etAddressLineOne.getText().toString().trim());
                    intent.putExtra(ADDRESS_TWO, binding.etAddressLineTwo.getText().toString().trim());
                    intent.putExtra(ADDRESS_THREE, ""/*binding.etAddressLineThree.getText().toString().trim()*/);
                    intent.putExtra(POSTAL_CODE, binding.etPostCode.getText().toString().trim());
                    intent.putExtra(IS_SAVE_CARD, isSaveCard);


                    ////////////////////////// FOR SWIPE TO BUY ONLY ////////////////////////////////////////////////
                    intent.putExtra(CARD_NUMBER_TO_SHOW, "************" + card.getNumber());
                    intent.putExtra(CARD_BRAND_NAME, card.getBrand());
                    ////////////////////////////////////////////////////////////////////////////////////////

                    setResult(RESULT_OK, intent);
                    finish();

                } else
                    showSnackBar(binding.cardInputWidget, getString(R.string.invalid_card_details));
                // } else showSnackBar(binding.cardInputWidget, getString(R.string.enter_country));
            } else
                showSnackBar(binding.cardInputWidget, getString(R.string.enter_address_one));
        } else showSnackBar(binding.cardInputWidget, getString(R.string.enter_post_code));

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
                    cardResponseModel.getBrand(), binding.cbSaveCard.isChecked() ? "1" : "0",
                    String.valueOf(cardResponseModel.getExp_month()).length() == 2 ? String.valueOf(cardResponseModel.getExp_month()) : "0" + String.valueOf(cardResponseModel.getExp_month()),
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

                                    Intent intent = new Intent();
                                    intent.putExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK, responseModel.getCard_details().getStripe_card_id());
                                    intent.putExtra(CARD_NUMBER, responseModel.getCard_details().getCard_number());
                                    intent.putExtra(CARD_EXPIRY_MONTH, responseModel.getCard_details().getExp_month());
                                    intent.putExtra(CARD_EXPIRY_YEAR, responseModel.getCard_details().getExp_year());
                                    intent.putExtra(CARD_CVV, ""); ///////
                                    intent.putExtra(CARD_BRAND_NAME, responseModel.getCard_details().getCard_type());
                                    intent.putExtra(ADDRESS_ONE, binding.etAddressLineOne.getText().toString().trim());
                                    intent.putExtra(ADDRESS_TWO, binding.etAddressLineTwo.getText().toString().trim());
                                    intent.putExtra(ADDRESS_THREE, ""/*binding.etAddressLineThree.getText().toString().trim()*/);
                                    intent.putExtra(POSTAL_CODE, binding.etPostCode.getText().toString().trim());
                                    intent.putExtra(IS_SAVE_CARD, isSaveCard);


                                    ////////////////////////// FOR SWIPE TO BUY ONLY ////////////////////////////////////////////////
                                    intent.putExtra(CARD_NUMBER_TO_SHOW, responseModel.getCard_details().getCard_number());
                                    intent.putExtra(CARD_BRAND_NAME, responseModel.getCard_details().getCard_type());
                                    ////////////////////////////////////////////////////////////////////////////////////////

                                    setResult(RESULT_OK, intent);
                                    finish();


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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent();
        setResult(RESULT_CANCELED, intent1);
        finish();
    }

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
    protected int getLayoutResourceId() {
        return R.layout.activity_add_new_card_on_checkout;
    }

    @Override
    protected void onNotificationReceived(Intent intent) {

    }
}