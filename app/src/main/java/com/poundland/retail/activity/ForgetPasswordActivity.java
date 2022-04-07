package com.poundland.retail.activity;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.databinding.ActivityForgetPasswordBinding;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.LoginRequestModel;
import com.poundland.retail.model.responseModel.CommonResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.isValidEmail;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;

public class ForgetPasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityForgetPasswordBinding binding;
    private PrefManager prefManager;
    private final String SWOOPE_LOCAL_USER="2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_forget_password);
        init();
        setListeners();
    }

    private void init() {
        prefManager = PrefManager.getInstance(this);
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.cvSubmit.setOnClickListener(this);
        binding.tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.cv_submit:
                if (isValid()) {
                    forgetPassword();
                }
                break;
            case R.id.tv_login:
                finish();
                break;
        }
    }

    private boolean isValid() {
        if (binding.etEmail.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etEmail.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.enter_email_id));
            return false;
        }else if (!isValidEmail(binding.etEmail.getText().toString())) {
            clearFocus();
            binding.etEmail.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_email));
            return false;
        }
        return true;
    }

    private void clearFocus() {
        binding.etEmail.clearFocus();
    }

    private void forgetPassword() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            LoginRequestModel loginRequestModel = new LoginRequestModel(binding.etEmail.getText().toString(),SWOOPE_LOCAL_USER);

            Call<CommonResponseModel> call = apiInterface.forgotPassword(loginRequestModel);
            call.enqueue(new Callback<CommonResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<CommonResponseModel> call, @NonNull Response<CommonResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            CommonResponseModel forgotPasswordModel = response.body();
                            if (forgotPasswordModel.isStatus()) {
                                DialogUtils.showAlertDialogWithSingleButton(ForgetPasswordActivity.this, forgotPasswordModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {
                                        finish();
                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
                            } else {
                                showSnackBar(binding.getRoot(), forgotPasswordModel.getMessage());
                            }
                        }else {
                            DialogUtils.showAlertDialogWithSingleButton(ForgetPasswordActivity.this, getString(R.string.wronge_email), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

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
                public void onFailure(@NonNull Call<CommonResponseModel> call, @NonNull Throwable t) {
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