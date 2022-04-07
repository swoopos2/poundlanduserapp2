package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.widget.ListPopupWindow;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityCardAddInstantPayBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.NotificationDeleteRequestModel;
import com.poundland.retail.model.responseModel.GetUserLocationResponseModel;
import com.poundland.retail.notificationService.NotificationModel;
import com.stripe.android.model.Card;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
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
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.IS_SAVE_CARD;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.POSTAL_CODE;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class CardAddInstantPayActivity extends BaseActivity implements View.OnClickListener {

    private ActivityCardAddInstantPayBinding binding;
    private CardAddInstantPayActivity instance = null;
    private PrefManager prefManager;
    private String fromWhere;
    private List<GetUserLocationResponseModel.DataBean> lookUpList;
    private ListPopupWindow popupWindow;
    private boolean isPopup;
    private boolean isSaveCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card_add_instant_pay);
        //  binding.tvSubmit.setAlpha(.5f);
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        instance = this;
        binding.rlSaveCard.setVisibility(View.VISIBLE);
        lookUpList = new ArrayList<>();
        prefManager = PrefManager.getInstance(instance);
        binding.ivBack.setOnClickListener(this);
        binding.tvSubmit.setOnClickListener(this);
        binding.tvLookUp.setOnClickListener(this);
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

                if (!TextUtils.isEmpty(binding.etPostCode.getText().toString())) {
                    if (!TextUtils.isEmpty(binding.etAddressLineOne.getText().toString())) {
                      //  if (!TextUtils.isEmpty(binding.etAddressLineThree.getText().toString())) {

                            Card card = binding.cardInputWidget.getCard();
                            if (card != null && card.validateCard()) {

                                Intent intent = new Intent();
                                intent.putExtra(CARD_NUMBER, card.getNumber());
                                intent.putExtra(CARD_EXPIRY_MONTH, card.getExpMonth());
                                intent.putExtra(CARD_EXPIRY_YEAR, card.getExpYear());
                                intent.putExtra(CARD_CVV, card.getCvc());

                                intent.putExtra(ADDRESS_ONE, binding.etAddressLineOne.getText().toString().trim());
                                intent.putExtra(ADDRESS_TWO, binding.etAddressLineTwo.getText().toString().trim());
                                intent.putExtra(ADDRESS_THREE, ""/*binding.etAddressLineThree.getText().toString().trim()*/);
                                intent.putExtra(POSTAL_CODE, binding.etPostCode.getText().toString().trim());
                                intent.putExtra(IS_SAVE_CARD, isSaveCard);


                                ////////////////////////// FOR SWIPE TO BUY ONLY ////////////////////////////////////////////////
                                intent.putExtra(CARD_NUMBER_TO_SHOW, "************"+card.getNumber());
                                intent.putExtra(CARD_BRAND_NAME, card.getBrand());
                                ////////////////////////////////////////////////////////////////////////////////////////

                                setResult(RESULT_OK, intent);
                                finish();

                            } else showSnackBar(binding.cardInputWidget, getString(R.string.invalid_card_details));
                       // } else showSnackBar(binding.cardInputWidget, getString(R.string.enter_country));
                    } else showSnackBar(binding.cardInputWidget, getString(R.string.enter_address_one));
                } else showSnackBar(binding.cardInputWidget, getString(R.string.enter_post_code));

                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
         /*if (requestCode == OPEN_LOOKUP_ACTIVITY && resultCode == RESULT_OK && data != null) {

        }*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent1 = new Intent();
        setResult(RESULT_CANCELED, intent1);
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

                if (data.equals("DISC_OFFER_NOTIFY")){
                    DialogUtils.offerNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent topStore = new Intent(this, NearByDealsActivity.class);
                        topStore.putExtra(TOP_OFFER_TITLE, getString(R.string.nearby_deals));
                        startActivity(topStore);
                    });
                } else if (!TextUtils.isEmpty(data)){
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
}