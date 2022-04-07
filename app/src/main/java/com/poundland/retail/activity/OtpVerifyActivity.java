package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityOtpVerificationBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.SignUpRequestModel;
import com.poundland.retail.model.responseModel.LoginResponseModel;
import com.poundland.retail.model.responseModel.SendOtpResponseModel;
import com.poundland.retail.model.responseModel.SignUpResponseModel;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.DOB;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.FROM_REGISTER;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.GENDER;
import static com.poundland.retail.interfaces.Constants.LAST_NAME;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.MY_FILE;
import static com.poundland.retail.interfaces.Constants.PROFILE_IMAGE;
import static com.poundland.retail.interfaces.Constants.SIGN_UP_REQU_MODEL;
import static com.poundland.retail.interfaces.Constants.TOKEN_TYPE;


public class OtpVerifyActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityOtpVerificationBinding binding;
    private boolean isResendClickable = false;
    private PrefManager prefManager;
    private boolean isLogout = false;
    private String mobileNo;
    private boolean isRegister;
    private String userId;
    private String fromWhere;
    private SignUpRequestModel signUpRequestModel;


    String F_NAME;
    String L_NAME;
    String Email;
    String Contact_NO;
    File myFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_otp_verification);
        init();

        InputMethodManager inputMethodManager =  (InputMethodManager)getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(binding.etOtp.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        binding.etOtp.requestFocus();

        if (binding.etOtp != null) {
            binding.etOtp.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (isValid())

                        if (fromWhere.equals(FROM_REGISTER)) {
                            verifyOTPwithServer();
                        } else {
                            updateProfile(binding.etOtp.getText().toString());
                        }
                }
            });
        }
    }

    private void init() {
        prefManager = PrefManager.getInstance(this);

        fromWhere = getIntent().getExtras().getString(FROM_WHERE);
        if (fromWhere.equals(FROM_REGISTER)) {
            signUpRequestModel = getIntent().getExtras().getParcelable(SIGN_UP_REQU_MODEL);
        } else {
            F_NAME = getIntent().getExtras().getString(FIRST_NAME);
            L_NAME = getIntent().getExtras().getString(LAST_NAME);
            Email = getIntent().getExtras().getString(EMAIL);
            Contact_NO = getIntent().getExtras().getString(CONTACT_NO);
            myFile = (File) getIntent().getSerializableExtra(MY_FILE);
            myFile = MyProfileActivity.profileImages;
        }

        binding.btnOtpResend.setOnClickListener(this);
        binding.btnOtpResend.setAlpha(.5f);
        showCounter();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            /*case R.id.btn_change_no:
                Intent intent = new Intent(this, SignUpActivity.class);
                startActivity(intent);
                finish();
                break;*/
            case R.id.btn_otp_resend:
                if (isResendClickable)
                    if (fromWhere.equals(FROM_REGISTER)) {
                        resendOtp();
                    } else {
                        updateProfile("");
                    }
                break;
        }
    }

    private void verifyOTPwithServer() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            signUpRequestModel.setOtp(binding.etOtp.getText().toString());
            Call<SignUpResponseModel> call = apiInterface.userSignUpApi(signUpRequestModel);

            call.enqueue(new Callback<SignUpResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<SignUpResponseModel> call, @NonNull Response<SignUpResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            SignUpResponseModel userResponsebean = response.body();
                            if (userResponsebean.isStatus()) {
                                saveDataInPrefernece(userResponsebean.getSuccess_data());
                                showSnackBar(binding.getRoot(), userResponsebean.getData());
                                Intent intent = new Intent(OtpVerifyActivity.this, MainActivity.class);
                                startActivity(intent);
                                finishAffinity();
                            } else {
                                binding.etOtp.setText("");
                                showSnackBar(binding.getRoot(), userResponsebean.getMessage() == null ? getString(R.string.something_went_wrong) : userResponsebean.getMessage());
                                // data == null ? 0 : data.length;
                            }
                        } else {
                            DialogUtils.showAlertDialogWithSingleButton(OtpVerifyActivity.this, getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    binding.etOtp.setText("");
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
                public void onFailure(@NonNull Call<SignUpResponseModel> call, @NonNull Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void resendOtp() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            signUpRequestModel.setOtp("");
            Call<SendOtpResponseModel> call = apiInterface.OtpApi(signUpRequestModel);
            call.enqueue(new Callback<SendOtpResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<SendOtpResponseModel> call, @NonNull Response<SendOtpResponseModel> response) {
                    try {

                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            SendOtpResponseModel userResponseBean = response.body();
                            if (userResponseBean.isStatus()) {
                                binding.btnOtpResend.setAlpha(.5f);
                                showCounter();
                                isResendClickable=false;
                                showSnackBar(binding.getRoot(), userResponseBean.getMessage());
                            } else showSnackBar(binding.getRoot(), userResponseBean.getMessage());
                        } else
                            showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<SendOtpResponseModel> call, @NonNull Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void showCounter() {
        CountDownTimer mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //this will be called every second.
                binding.tvTimer.setText("" + getDurationString((int) millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                isResendClickable = true;
                binding.btnOtpResend.setAlpha(1f);
            }
        };
        mCountDownTimer.start();
    }

    public String getDurationString(int seconds) {
        //   int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        return twoDigitString(minutes) + " : " + twoDigitString(seconds);
    }

    private String twoDigitString(int number) {
        if (number == 0) {
            return "00";
        }
        if (number / 10 == 0) {
            return "0" + number;
        }
        return String.valueOf(number);
    }

    private void saveDataInPrefernece(SignUpResponseModel.SuccessDataBean success_data) {
        prefManager.savePreference(TOKEN_TYPE, success_data.getToken_type());
        // prefManager.savePreference(EXPIRE_IN, success_data.getExpires_in());
        prefManager.savePreference(AUTH_TOKEN, success_data.getAccess_token());
        prefManager.savePreference(LOGIN_ID, success_data.getId());
        prefManager.savePreference(FIRST_NAME, success_data.getFirst_name());
        prefManager.savePreference(LAST_NAME, success_data.getLast_name());
        prefManager.savePreference(DOB, success_data.getDob());
        prefManager.savePreference(EMAIL, success_data.getEmail());
        prefManager.savePreference(CONTACT_NO, success_data.getContact_no());
        prefManager.savePreference(GENDER, success_data.getGender());
        prefManager.savePreference(PROFILE_IMAGE, success_data.getImage());
        prefManager.savePreference(CART_ENTRY_TYPE, success_data.getCart());
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private boolean isValid() {
        if (binding.etOtp.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etOtp.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_otp));
            return false;
        } else if (binding.etOtp.getText().toString().length() < 4) {
            clearFocus();
            binding.etOtp.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.enter_valid_otp));
        }
        return true;
    }

   /* private boolean isLogoutValid() {
        if (binding.etPassword.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_the_password));
            return false;
        } else if (binding.etPassword.getText().toString().length() < 8) {
            clearFocus();
            binding.etPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.is_password_valid));
            return false;
        }
        return true;
    }*/

    private void clearFocus() {
        binding.etOtp.clearFocus();
    }


    private void updateProfile(String otp_no) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            Call<LoginResponseModel> call;
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            MultipartBody.Part body = null;
            if (myFile == null) {
                body = null;
            } else {
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), myFile);
                body = MultipartBody.Part.createFormData("image", myFile.getName(), requestFile);
            }

            RequestBody bodyFirstName = RequestBody.create(MediaType.parse("text/plain"), F_NAME);
            RequestBody bodyLasttName = RequestBody.create(MediaType.parse("text/plain"), L_NAME);
            RequestBody bodyEmail = RequestBody.create(MediaType.parse("text/plain"), Email);
            RequestBody bodyPhoneNumber = RequestBody.create(MediaType.parse("text/plain"), Contact_NO);
            RequestBody otp = RequestBody.create(MediaType.parse("text/plain"), otp_no);

            call = apiInterface.updateProfile(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""),
                    body, bodyFirstName, bodyLasttName, bodyEmail, bodyPhoneNumber, otp);

            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            final LoginResponseModel getProfileModel = response.body();
                            if (getProfileModel.isStatus()) {
                                binding.btnOtpResend.setAlpha(.5f);
                                showCounter();
                                isResendClickable=false;
                                if (!getProfileModel.isIs_otp()) {

                                    DialogUtils.showAlertDialogWithSingleButton(OtpVerifyActivity.this, getProfileModel.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            saveDataInPrefernece(getProfileModel);
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                                }else {
                                    DialogUtils.showAlertDialogWithSingleButton(OtpVerifyActivity.this, getProfileModel.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {

                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                                }

                            } else {
                                showSnackBar(binding.getRoot(), getProfileModel.getMessage());
                            }
                        } else {
                            DialogUtils.showAlertDialogWithSingleButton(OtpVerifyActivity.this, getString(R.string.something_went_wrong), new OnDialogClickListener() {
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
                public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void saveDataInPrefernece(LoginResponseModel loginResponseModel) {
        prefManager.savePreference(TOKEN_TYPE, loginResponseModel.getToken_type());
        // prefManager.savePreference(EXPIRE_IN, loginResponseModel.getExpires_in());
        prefManager.savePreference(AUTH_TOKEN, loginResponseModel.getAccess_token());
        prefManager.savePreference(LOGIN_ID, loginResponseModel.getId());
        prefManager.savePreference(FIRST_NAME, loginResponseModel.getFirst_name());
        prefManager.savePreference(LAST_NAME, loginResponseModel.getLast_name());
        prefManager.savePreference(DOB, loginResponseModel.getDob());
        prefManager.savePreference(EMAIL, loginResponseModel.getEmail());
        prefManager.savePreference(CONTACT_NO, loginResponseModel.getContact_no());
        prefManager.savePreference(GENDER, loginResponseModel.getGender());
        prefManager.savePreference(PROFILE_IMAGE, loginResponseModel.getImage());
        prefManager.savePreference(CART_ENTRY_TYPE, loginResponseModel.getCart());
        finish();
    }

}
