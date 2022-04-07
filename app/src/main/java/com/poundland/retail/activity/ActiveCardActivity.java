package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.ActivePayListAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityCardBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.model.responseModel.ActivePayResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

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
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVE_PAYMENT_DETAIL_ADD_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PAYMENT_DETAIL_ADD_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_VERIFY_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.PAYMENT_DETAIL_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.PAYMENT_GATEWAY_ACTIVE;
import static com.poundland.retail.interfaces.Constants.PAYMENT_GATEWAY_ACTIVE_SAVED_CARD;
import static com.poundland.retail.interfaces.Constants.POSITION;
import static com.poundland.retail.interfaces.Constants.POSTAL_CODE;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.UNIVERSAL_CODE;

public class ActiveCardActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener,
        DrawerListner {
    private ActivityCardBinding binding;
    private PrefManager prefManager;
    private List<ActivePayResponseModel.DataBean> cardList;
    private ActiveCardActivity instance = null;
    private ActivePayListAdapter activePayListAdapter;
    private String fromWhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_card);
        init();
        setListeners();
        getCard();

    }


    private void init() {
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        instance = this;
        cardList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        binding.title.setText(getString(R.string.payment_details));
        binding.tvResetPin.setVisibility(View.VISIBLE);
    }

    private void setAdapter() {
        activePayListAdapter = new ActivePayListAdapter(this, cardList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvPaymentDetailsList.setLayoutManager(layoutManager);
        binding.rvPaymentDetailsList.setAdapter(activePayListAdapter);
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.fabAddPayment.setOnClickListener(this);
        binding.ivAddAnother.setOnClickListener(this);
        binding.tvResetPin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.fab_add_payment:

                Intent in = new Intent(instance, CardAddInstantPayActivity.class);
                startActivityForResult(in, OPEN_ACTIVE_PAYMENT_DETAIL_ADD_ACTIVITY);
                break;
            case R.id.iv_add_another:

                Intent inn = new Intent(instance, CardAddInstantPayActivity.class);
                startActivityForResult(inn, OPEN_ACTIVE_PAYMENT_DETAIL_ADD_ACTIVITY);
                break;
            case R.id.tv_reset_pin:
                Intent resetPin = new Intent(this, VerifyPinActivity.class);
                resetPin.putExtra(FROM_WHERE, getString(R.string.card));
                resetPin.putExtra(POSITION, -1);
                startActivity(resetPin);

                break;

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getCard() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<ActivePayResponseModel> call = apiInterface.getActiveCard(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""));
            call.enqueue(new Callback<ActivePayResponseModel>() {
                @Override
                public void onResponse(Call<ActivePayResponseModel> call, Response<ActivePayResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            ActivePayResponseModel responseModel = response.body();

                            if (responseModel.getData() != null && responseModel.getData().size() > 0) {
                                binding.tvNoDataFound.setVisibility(View.GONE);
                                cardList.clear();
                                cardList.addAll(responseModel.getData());
                                setAdapter();
                            } else {
                                binding.tvNoDataFound.setVisibility(View.GONE);
                                cardList.clear();
                                setAdapter();

                                Intent in = new Intent(instance, CardAddInstantPayActivity.class);
                                startActivityForResult(in, OPEN_ACTIVE_PAYMENT_DETAIL_ADD_ACTIVITY);
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
                public void onFailure(Call<ActivePayResponseModel> call, Throwable t) {
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

    @Override
    public void onDrawerSelect(int position, int clickType) {

        if (clickType == UNIVERSAL_CODE) {
            if (fromWhere != null && fromWhere.equals(getString(R.string.cart))) {
                /*DialogUtils.dialogLogOut(instance, getString(R.string.use_card_message), getString(R.string.card), new OnDialogClickListener() {
                    @Override
                    public void onPositiveClick() {*/
                        Intent stamp = new Intent(instance, VerifyPinActivity.class);
                        stamp.putExtra(FROM_WHERE, getString(R.string.payment_details));
                        stamp.putExtra(POSITION, position);
                        startActivityForResult(stamp, OPEN_PIN_VERIFY_ACTIVITY);
                    /*}

                    @Override
                    public void onNegativeClick() {
                    }
                });*/

            }
        }// else deleteCard(position);
    }

    /*private void deleteCard(int position) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            DeleteSavedCardRequestModel requestModel = new DeleteSavedCardRequestModel(cardList.get(position).getId());
            Call<SaveUserCardDetailsResponseModel> call = apiInterface.deleteUserCardDetails(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
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


                                    DialogUtils.showAlertDialogWithSingleButton(instance, responseModel.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            activePayListAdapter.removeCard(position);
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });

                                } else {
                                    DialogUtils.showAlertDialogWithSingleButton(instance, responseModel.getMessage(), new OnDialogClickListener() {
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
                            showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later) + response.code());
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
    }*/

    @Override
    protected void onResume() {
        super.onResume();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_ACTIVE_PAYMENT_DETAIL_ADD_ACTIVITY && resultCode == RESULT_OK && data != null) {

            Intent intent = new Intent();
            intent.putExtra(ACTIVE_PAYMENT_TYPE, PAYMENT_GATEWAY_ACTIVE);
            intent.putExtra(CARD_NUMBER, data.getStringExtra(CARD_NUMBER));
            intent.putExtra(CARD_EXPIRY_MONTH, data.getIntExtra(CARD_EXPIRY_MONTH, 88));
            intent.putExtra(CARD_EXPIRY_YEAR, data.getIntExtra(CARD_EXPIRY_YEAR, 99));
            intent.putExtra(CARD_CVV, data.getStringExtra(CARD_CVV));

            intent.putExtra(ADDRESS_ONE, data.getStringExtra(ADDRESS_ONE));
            intent.putExtra(ADDRESS_TWO, data.getStringExtra(ADDRESS_TWO));
            intent.putExtra(ADDRESS_THREE, data.getStringExtra(ADDRESS_THREE));
            intent.putExtra(POSTAL_CODE, data.getStringExtra(POSTAL_CODE));

            ////////////////////////// FOR SWIPE TO BUY ONLY ////////////////////////////////////////////////
            intent.putExtra(CARD_NUMBER_TO_SHOW, "************" + CARD_NUMBER_TO_SHOW);
            intent.putExtra(CARD_BRAND_NAME, CARD_BRAND_NAME);
            ////////////////////////////////////////////////////////////////////////////////////////

            intent.putExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK, "");

            intent.putExtra(IS_SAVE_CARD, data.getBooleanExtra(IS_SAVE_CARD, false));
            setResult(RESULT_OK, intent);
            finish();
        } else if (requestCode == OPEN_PAYMENT_DETAIL_ADD_ACTIVITY && resultCode == RESULT_CANCELED) {
            log("", "");
        } else if (requestCode == OPEN_PIN_VERIFY_ACTIVITY && resultCode == RESULT_OK && data != null) {
            int pos = data.getIntExtra(POSITION, -1);
            Intent intent = new Intent();
            intent.putExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK, cardList.get(pos).getCardToken());
            intent.putExtra(ACTIVE_PAYMENT_TYPE, PAYMENT_GATEWAY_ACTIVE_SAVED_CARD);
            intent.putExtra(CARD_NUMBER, "");
            intent.putExtra(CARD_EXPIRY_MONTH, "");
            intent.putExtra(CARD_EXPIRY_YEAR, "");
            intent.putExtra(CARD_CVV, "");
            ////////////////////////// FOR SWIPE TO BUY ONLY ////////////////////////////////////////////////
            intent.putExtra(CARD_NUMBER_TO_SHOW, cardList.get(pos).getCard_number());
            intent.putExtra(CARD_BRAND_NAME, cardList.get(pos).getCard_type());
            ////////////////////////////////////////////////////////////////////////////////////////
            intent.putExtra(ADDRESS_ONE, "");
            intent.putExtra(ADDRESS_TWO, "");
            intent.putExtra(ADDRESS_THREE, "");
            intent.putExtra(POSTAL_CODE, "");
            intent.putExtra(IS_SAVE_CARD, false);


            setResult(RESULT_OK, intent);
            finish();
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
                      //  topProduct.putExtra(SPECIAL_OFFER_ID, String.valueOf(model.getSpecial_offer_id()));
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
