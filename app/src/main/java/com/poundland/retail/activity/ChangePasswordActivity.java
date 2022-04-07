package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityChangePasswordBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.ChangePasswordRequestModel;
import com.poundland.retail.model.responseModel.AboutResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.isValidPassword;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {
    private ActivityChangePasswordBinding binding;
    private PrefManager prefManager;
    private String authToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_change_password);
        init();
         setListeners();
    }


    private void init() {
        binding.tvTitle.setText(getString(R.string.change_password));
        binding.tvNext.setText(getString(R.string.change_password));
        binding.etCurrentPassword.setHint(getString(R.string.please_enter_yoour_current_password));
        binding.etNewPassword.setHint(getString(R.string.please_enter_new_password));
        binding.etConfirmPassword.setHint(getString(R.string.please_confirm_your_password));
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.btnSave.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.btn_save:
                HelperClass.hideKeyboard(this);
                if (isValid()) {
                    changePassword();
                }
                break;
        }
    }


    private boolean isValid() {
        if (binding.etCurrentPassword.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etCurrentPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_yoour_current_password));
            return false;
        } else if (!isValidPassword(binding.etCurrentPassword.getText().toString().trim())) {
       // } else if (isValidPassword(binding.etCurrentPassword.getText().toString())) {
            clearFocus();
            binding.etCurrentPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.password_must_contain));
            return false;
        } else if (binding.etNewPassword.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etNewPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_new_password));
            return false;
        } else if (!isValidPassword(binding.etNewPassword.getText().toString().trim())) {
            clearFocus();
            binding.etNewPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.password_must_contain));
            return false;
        } else if (binding.etConfirmPassword.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etConfirmPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_confirm_your_password));
            return false;
        } else if (!binding.etNewPassword.getText().toString().equals(binding.etConfirmPassword.getText().toString())) {
            clearFocus();
            binding.etConfirmPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.password_mismatch));
            return false;
        }
        return true;
    }

    private void clearFocus() {
        binding.etNewPassword.clearFocus();
        binding.etConfirmPassword.clearFocus();
    }

    private void changePassword() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ChangePasswordRequestModel requestModel = new ChangePasswordRequestModel(binding.etCurrentPassword.getText().toString(),
                    binding.etNewPassword.getText().toString());
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<AboutResponseModel> call = apiInterface.changePassword(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""),requestModel);
            call.enqueue(new Callback<AboutResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<AboutResponseModel> call, @NonNull Response<AboutResponseModel> response) {
                    try {

                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            AboutResponseModel staticResponseBean = response.body();
                            if (staticResponseBean != null) {
                                if (staticResponseBean.isStatus()) {

                                    DialogUtils.showAlertDialogWithSingleButton(ChangePasswordActivity.this, staticResponseBean.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
finish();
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });


                                }  else {
                                    DialogUtils.showAlertDialogWithSingleButton(ChangePasswordActivity.this, staticResponseBean.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {

                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                                }
                            }

                        }else {
                            showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<AboutResponseModel> call, Throwable t) {
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
}
