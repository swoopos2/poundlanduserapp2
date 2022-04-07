package com.poundland.retail.activity;

import android.os.Bundle;
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
import com.poundland.retail.databinding.ActivityResetPinBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.ChangePasswordRequestModel;
import com.poundland.retail.model.requestModel.PinRequestModel;
import com.poundland.retail.model.requestModel.SignUpRequestModel;
import com.poundland.retail.model.responseModel.PinResponseModel;

import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.Unprocessable_Entity;

public class ResetPinActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityResetPinBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private SignUpRequestModel signUpRequestModel;
    private boolean isResendClickable = true;
    private ResetPinActivity instance = null;
    private String fromWhere = "";
    private String TYPE = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_pin);
        fromWhere = getIntent().getStringExtra(FROM_WHERE);

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(binding.etOtp.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        binding.etOtp.requestFocus();


        init();
        setListeners();
        if (binding.etOtp != null) {
            binding.etOtp.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (isValid()) {
                        verifyPinWithoutOtp();
                    }
                }
            });

        }
    }

    private void init() {
        instance = this;
        prefManager = PrefManager.getInstance(this);
        binding.tvTitle.setText(getString(R.string.reset_pin_scr));
        // binding.tvContent.setVisibility(View.GONE);
        binding.tvForgotPin.setVisibility(View.GONE);
        // binding.tvNext.setText(getString(R.string.reset_pin));
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        // binding.btnSave.setOnClickListener(this);
        // binding.tvGetOtp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

           /* case R.id.btn_save:
                HelperClass.hideKeyboard(this);
                if (isValid()) {
                    resetPin();
                }
                break;*/
           /* case R.id.tv_get_otp:
                if (isResendClickable)
                    sendOTP();

                break;*/
        }
    }


    private boolean isValid() {

        if (binding.etOtp.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etOtp.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_pin));
            return false;
        } else if (binding.etOtp.getText().toString().length() < 4) {
            clearFocus();
            binding.etOtp.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.enter_valid_pin));
        }

      /*  if (binding.etNewPin.getText().toString().length() < 4) {
            clearFocus();
            binding.etNewPin.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.pin_must_contain));
            return false;
        } else if (binding.etOtp.getText().toString().length() < 4) {
            clearFocus();
            binding.etOtp.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.enter_valid_otp));
            return false;
        }*/
        return true;
    }

    private void clearFocus() {
        // binding.etNewPin.clearFocus();
        binding.etOtp.clearFocus();
    }

    private void verifyPinWithoutOtp() {

        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            PinRequestModel requestModel = new PinRequestModel(binding.etOtp.getText().toString(), TYPE);
            Call<PinResponseModel> call = apiInterface.generateCustPin(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);

            call.enqueue(new Callback<PinResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<PinResponseModel> call, @NonNull Response<PinResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        PinResponseModel pinResponseModel = response.body();
                        if (response.isSuccessful()) {

                            if (pinResponseModel.isStatus()) {

                                DialogUtils.dialogGeneratePinSuccess(ResetPinActivity.this, pinResponseModel.getMessage(), getString(R.string.sucess), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {
                                        finish();
                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });


                            } else {
                                DialogUtils.showAlertDialogWithSingleButton(instance, pinResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {
                                        binding.etOtp.setText("");
                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });


                            }
                        } else {
                            binding.etOtp.setText("");
                            showSnackBar(binding.getRoot(), pinResponseModel == null ? getString(R.string.something_went_wrong) : pinResponseModel.getMessage());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<PinResponseModel> call, @NonNull Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }


    private void resetPin() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ChangePasswordRequestModel requestModel = new ChangePasswordRequestModel(Integer.parseInt(binding.etOtp.getText().toString()), binding.etOtp.getText().toString());
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<PinResponseModel> call = apiInterface.updatePin(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<PinResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<PinResponseModel> call, @NonNull Response<PinResponseModel> response) {
                    try {

                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            PinResponseModel staticResponseBean = response.body();
                            if (staticResponseBean != null) {
                                if (staticResponseBean.isStatus()) {

                                    DialogUtils.dialogGeneratePinSuccess(ResetPinActivity.this, staticResponseBean.getMessage(), getString(R.string.sucess), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            finish();
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });


                                } else {
                                    DialogUtils.showAlertDialogWithSingleButton(ResetPinActivity.this, staticResponseBean.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {

                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                                }
                            }

                        } else if (response.code() == Unprocessable_Entity) {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            // Toast.makeText(RegisterActivity.this, jObjError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                            DialogUtils.showAlertDialogWithSingleButton(instance, jObjError.getJSONObject("error").getString("message").toString(), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });

                        } else {
                            DialogUtils.showAlertDialogWithSingleButton(instance, response.code() == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (response.code() == 401) {
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
                public void onFailure(Call<PinResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

  /*  private void showCounter() {
        CountDownTimer mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //this will be called every second.
                isResendClickable = false;
                binding.tvGetOtp.setText("" + getDurationString((int) millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                isResendClickable = true;
                binding.tvGetOtp.setAlpha(1f);
                binding.tvGetOtp.setText(getString(R.string.get_otp));
            }
        };
        mCountDownTimer.start();
    }*/

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

    /*private void sendOTP() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<PinResponseModel> call = apiInterface.sendOTP(BASE_URL + ApiRequestUrl.SEND_OTP + prefManager.getPreference(CONTACT_NO, ""));
            call.enqueue(new Callback<PinResponseModel>() {
                @Override
                public void onResponse(Call<PinResponseModel> call, Response<PinResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            PinResponseModel pinResponseModel = response.body();
                            if (pinResponseModel.isStatus()) {
                                showSnackBar(binding.getRoot(), pinResponseModel.getMessage());
                                showCounter();
                            } else {
                                showSnackBar(binding.getRoot(), pinResponseModel.getMessage());
                            }
                        } else {
                            final int httpCode = response.code();
                            DialogUtils.showAlertDialogWithSingleButton(ResetPinActivity.this, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(ResetPinActivity.this);
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
                public void onFailure(Call<PinResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }*/
}

