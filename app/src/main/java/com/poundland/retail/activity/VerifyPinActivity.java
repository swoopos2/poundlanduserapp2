package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityPinVerificationBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.fingerPrint.Helper.BiometricCallback;
import com.poundland.retail.fingerPrint.Helper.BiometricManager;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.PinRequestModel;
import com.poundland.retail.model.responseModel.PinResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.POSITION;


public class VerifyPinActivity extends AppCompatActivity implements BiometricCallback {
    private ActivityPinVerificationBinding binding;
    private PrefManager prefManager;
    private int position;
    private VerifyPinActivity instance = null;
    private String TYPE = "2";
    private String fromWhere = "";
    ////////////////////////////
    BiometricManager mBiometricManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_pin_verification);

       /* InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(binding.etOtp.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        binding.etOtp.requestFocus();*/

        instance = this;
        fromWhere = getIntent().getStringExtra(FROM_WHERE);

        if (fromWhere.equals(getString(R.string.setting))) {
            binding.tvContent.setVisibility(View.GONE);
        } else if (fromWhere.equals(getString(R.string.card)) || fromWhere.equals(getString(R.string.payment_details)) || fromWhere.equals(getString(R.string.cart)) ) {
            binding.tvContent.setVisibility(View.GONE);
        } else {
            binding.tvForgotPin.setVisibility(View.GONE);
            binding.tvTitle.setVisibility(View.GONE);
            binding.ivBack.setVisibility(View.GONE);
        }
        // binding.ivLogo.setVisibility(View.GONE);
        prefManager = PrefManager.getInstance(this);
        position = getIntent().getIntExtra(POSITION, -1);
        if (binding.etOtp != null) {
            binding.etOtp.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (isValid()) {
                        verifyPin();

                    }
                }
            });
        }

        binding.rlGoBack.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(POSITION, position);
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        binding.ivBack.setOnClickListener(v -> {
            Intent intent = new Intent();
            intent.putExtra(POSITION, position);
            setResult(RESULT_CANCELED, intent);
            finish();
        });

        binding.tvForgotPin.setOnClickListener(v -> {
            sendPinToMail();
        });

        mBiometricManager = new BiometricManager.BiometricBuilder(instance)
                .setTitle("Authentication!")
                .setSubtitle("Unlock Swoope Local")
                .setDescription("Place your finger on the device sensor to verify your identity")
                .setNegativeButtonText("CANCEL")
                .build();

        //start authentication
        mBiometricManager.authenticate(instance);


        binding.rlFingerprint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mBiometricManager = new BiometricManager.BiometricBuilder(instance)
                        .setTitle("Authentication!")
                        .setSubtitle("Unlock Swoope Local")
                        .setDescription("Place your finger on the device sensor to verify your identity")
                        .setNegativeButtonText("CANCEL")
                        .build();

                //start authentication
                mBiometricManager.authenticate(instance);
            }
        });

    }

    private void verifyPin() {

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
                                if (fromWhere.equals(getString(R.string.setting)) || fromWhere.equals(getString(R.string.card))) {
                                    Intent resetPin = new Intent(instance, ResetPinActivity.class);
                                    resetPin.putExtra(FROM_WHERE, getString(R.string.setting));
                                    startActivity(resetPin);
                                    finish();
                                } else {
                                    Intent intent = new Intent();
                                    intent.putExtra(POSITION, position);
                                    setResult(RESULT_OK, intent);
                                    finish();
                                }
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
                            //response.code() == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong)
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
        return true;
    }

    private void clearFocus() {
        binding.etOtp.clearFocus();
    }

    private void sendPinToMail() {

        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<PinResponseModel> call = apiInterface.sendCustPinToEmail(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""));

            call.enqueue(new Callback<PinResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<PinResponseModel> call, @NonNull Response<PinResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            PinResponseModel pinResponseModel = response.body();
                            if (pinResponseModel.isStatus()) {

                                DialogUtils.dialogGeneratePinSuccess(instance, pinResponseModel.getMessage(), getString(R.string.sucess), new OnDialogClickListener() {
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

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });


                            }
                        } else {
                            showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
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

    ////////////////////////// BIOMETRIC ///////////////////////////////////////////////
    @Override
    public void onSdkVersionNotSupported() {
        Toast.makeText(getApplicationContext(), "Biometric not supported", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationNotSupported() {
        Toast.makeText(getApplicationContext(), "Biometric hardware not supported", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationNotAvailable() {
        Toast.makeText(getApplicationContext(), "Biometric error fingerprint not available", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationPermissionNotGranted() {
        Toast.makeText(getApplicationContext(), "Biometric error permission not granted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onBiometricAuthenticationInternalError(String error) {
        Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationFailed() {
//        Toast.makeText(getApplicationContext(), getString(R.string.Biometric failure), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationCancelled() {
        Toast.makeText(getApplicationContext(), "Biometric cancelled", Toast.LENGTH_LONG).show();
        mBiometricManager.cancelAuthentication();
    }

    @Override
    public void onAuthenticationSuccessful() {
        if (fromWhere.equals(getString(R.string.setting)) || fromWhere.equals(getString(R.string.card))) {
            Intent resetPin = new Intent(instance, ResetPinActivity.class);
            resetPin.putExtra(FROM_WHERE, getString(R.string.setting));
            startActivity(resetPin);
            finish();
        } if (fromWhere.equals(getString(R.string.cart))) {
            Intent intent = new Intent();
            intent.putExtra(POSITION, position);
            setResult(RESULT_OK, intent);
            finish();
        } else {
            Intent intent = new Intent();
            intent.putExtra(POSITION, position);
            setResult(RESULT_OK, intent);
            finish();
        }
       // Toast.makeText(getApplicationContext(), "Biometric success", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationHelp(int helpCode, CharSequence helpString) {
//        Toast.makeText(getApplicationContext(), helpString, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onAuthenticationError(int errorCode, CharSequence errString) {
//        Toast.makeText(getApplicationContext(), errString, Toast.LENGTH_LONG).show();
    }
}
