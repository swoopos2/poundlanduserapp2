package com.poundland.retail.activity;

import android.content.Intent;
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
import com.poundland.retail.databinding.ActivityCreatePinBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.PinRequestModel;
import com.poundland.retail.model.responseModel.PinResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.appUtils.HelperClass.toast;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_ORDER;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.PIN_CREATE_ACTIVITY_CALLBACK;


public class CreatePinActivity extends AppCompatActivity {
    private ActivityCreatePinBinding binding;
    private PrefManager prefManager;
    private CreatePinActivity instance = null;
    private String TYPE = "1";
    private String fromWhere = "";
    private int PIN_CREATED = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_create_pin);
        instance = this;

        prefManager = PrefManager.getInstance(this);

        InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInputFromWindow(binding.etOtp.getApplicationWindowToken(), InputMethodManager.SHOW_FORCED, 0);
        binding.etOtp.requestFocus();  /// if above 3 line doesn,t open keyboard then make changes in manifest stateVisible

        binding.llEnterPin.setVisibility(View.VISIBLE);
        binding.llConfirmPin.setVisibility(View.GONE);
        fromWhere = getIntent().getStringExtra(FROM_WHERE);

        if (binding.etOtp != null) {
            binding.etOtp.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    binding.etReOtp.requestFocus();
                    binding.llEnterPin.setVisibility(View.GONE);
                    binding.llConfirmPin.setVisibility(View.VISIBLE);
                }
            });
        }

        if (binding.etReOtp != null) {
            binding.etReOtp.setOnPinEnteredListener(new PinEntryEditText.OnPinEnteredListener() {
                @Override
                public void onPinEntered(CharSequence str) {
                    if (isValid()) {
                         createPin();
                    }
                }
            });
        }

        binding.rlGoBack.setOnClickListener(v -> {
            finish();
        });
        binding.ivBack.setOnClickListener(v -> {
            finish();
        });
    }

    private void createPin() {

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
                        if (response.isSuccessful()) {
                            PinResponseModel pinResponseModel = response.body();
                            if (pinResponseModel.isStatus()) {
                                prefManager.savePreference(CUSTOMER_ORDER, PIN_CREATED);

                                DialogUtils.dialogGeneratePinSuccess(instance, pinResponseModel.getMessage(), getString(R.string.sucess), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {
                                        if (fromWhere.equals(getString(R.string.cart))) {
                                            Intent intent = new Intent();
                                            intent.putExtra(PIN_CREATE_ACTIVITY_CALLBACK, true);
                                            setResult(RESULT_OK, intent);
                                            finish();
                                        } else {
                                            finish();
                                        }
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private boolean isValid() {
        if (binding.etOtp.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etOtp.requestFocus();
            //showSnackBar(binding.getRoot(), getString(R.string.please_enter_pin));
            toast(instance,getString(R.string.please_enter_pin));
            return false;
        } else if (binding.etReOtp.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etReOtp.requestFocus();
            //showSnackBar(binding.getRoot(), getString(R.string.please_confirm_pin));
            toast(instance,getString(R.string.please_confirm_pin));
            return false;
        } else if (!binding.etOtp.getText().toString().trim().equals(binding.etReOtp.getText().toString().trim())) {
            clearFocus();
            binding.etReOtp.setText("");
            binding.etOtp.setText("");

            binding.etOtp.requestFocus();
            binding.llEnterPin.setVisibility(View.VISIBLE);
            binding.llConfirmPin.setVisibility(View.GONE);


           // showSnackBar(binding.getRoot(), getString(R.string.pin_mismatch));
            toast(instance,getString(R.string.pin_mismatch));
            return false;
        }
        return true;
    }

    private void clearFocus() {
        binding.etOtp.clearFocus();
        binding.etReOtp.clearFocus();
    }

}
