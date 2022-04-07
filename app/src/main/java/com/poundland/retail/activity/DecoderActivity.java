package com.poundland.retail.activity;

import android.content.Intent;
import android.graphics.PointF;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PermissionsUtil;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityDecoderBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.zzznewPoundland.ApiClientPLAND;
import com.poundland.retail.zzznewPoundland.EncryptDecryptAESUtils;
import com.poundland.retail.zzznewPoundland.LoyaltyPointsActivity;
import com.poundland.retail.zzznewPoundland.OnSuccess;
import com.poundland.retail.zzznewPoundland.dialog.LoyaltyEarnMessageDialog;
import com.poundland.retail.zzznewPoundland.model.UpdateLoyaltyPointsRequest;
import com.poundland.retail.zzznewPoundland.model.UpdateLoyaltyPointsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.USER_ID;

public class DecoderActivity extends AppCompatActivity implements QRCodeReaderView.OnQRCodeReadListener, OnSuccess {
    private ActivityDecoderBinding binding;
    // private QRCodeReaderView qrCodeReaderView;
    private KProgressHUD dialog;
    private PrefManager prefManager;
    private DecoderActivity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_decoder);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_decoder);
        dialog = new KProgressHUD(this);
        activity = this;
        prefManager = PrefManager.getInstance(this);
        binding.ivBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.qrdecoderview.stopCamera();
                finish();
            }
        });
        PermissionsUtil.askPermission(this, PermissionsUtil.CAMERA, new PermissionsUtil.PermissionListener() {
            @Override
            public void onPermissionResult(boolean isGranted) {
                if (isGranted) {
                    // binding.qrdecoderview.startCamera();

                    binding.qrdecoderview.setOnQRCodeReadListener(DecoderActivity.this);
                    // Use this function to enable/disable decoding
                    binding.qrdecoderview.setQRDecodingEnabled(true);
                    // Use this function to change the autofocus interval (default is 5 secs)
                    binding.qrdecoderview.setAutofocusInterval(2000L);
                    // Use this function to enable/disable Torch
                    binding.qrdecoderview.setTorchEnabled(true);
                    // Use this function to set front camera preview
                    binding.qrdecoderview.setFrontCamera();
                    // Use this function to set back camera preview
                    binding.qrdecoderview.setBackCamera();
                } else {
                    Log.d("Permission ", "Permission Denie" + isGranted);
                    DialogUtils.showAlertDialogWithSingleButton(DecoderActivity.this, "Camera Permission Denie : \n Please grant camera permission for Pay & Go.", new OnDialogClickListener() {
                        @Override
                        public void onPositiveClick() {

                            finish();
                        }

                        @Override
                        public void onNegativeClick() {

                        }
                    });
                }
            }
        });


    }

    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        HelperClass.log("SCANNED", text);


        if (!TextUtils.isEmpty(text)) {
            binding.qrdecoderview.stopCamera();
            //  finish();
            addLoyaltyPoints(text);


        } else {
            binding.qrdecoderview.stopCamera();
            DialogUtils.showAlertDialogWithSingleButton(this, "Scanned result : " + text, new OnDialogClickListener() {
                @Override
                public void onPositiveClick() {

                    finish();
                }

                @Override
                public void onNegativeClick() {
                }
            });
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        binding.qrdecoderview.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        binding.qrdecoderview.stopCamera();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    public void onDrawerSelect(int type, UpdateLoyaltyPointsResponse pointsResponse) {
        finish();
        if (type == 2)
            return;

        Intent intent = new Intent(this, LoyaltyPointsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putParcelable(Constants.SHOW_RECENT_ACTIVITY, pointsResponse);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    private void addLoyaltyPoints(String scanResult) {

        String decrypted = null;
        try {
            decrypted = EncryptDecryptAESUtils.decrypt(scanResult);
            Log.d("TEST", "decrypted:" + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        UpdateLoyaltyPointsRequest decryptedRequest = null;
        if (decrypted != null) {
            decryptedRequest = new Gson().fromJson(decrypted, UpdateLoyaltyPointsRequest.class);
        }

        if (decryptedRequest == null) {
            decryptedRequest = new UpdateLoyaltyPointsRequest();
        }
        if (isInternetOn(this)) {
            if (dialog != null)
                dialog.show();


            if (decryptedRequest != null && decryptedRequest.getUserId() == null) {
                decryptedRequest.setUserId(prefManager.getPreference(USER_ID));
            }

            UpdateLoyaltyPointsRequest finalRequest = new UpdateLoyaltyPointsRequest();
            finalRequest.setUserId(prefManager.getPreference(USER_ID));
            finalRequest.setOrderId(decryptedRequest.getOrderId());

            ApiInterface apiInterface = ApiClientPLAND.getClient().create(ApiInterface.class);
            Call<UpdateLoyaltyPointsResponse> call = apiInterface.userLoyaltyearns(finalRequest);
            call.enqueue(new Callback<UpdateLoyaltyPointsResponse>() {
                @Override
                public void onResponse(Call<UpdateLoyaltyPointsResponse> call, Response<UpdateLoyaltyPointsResponse> response) {
                    if (dialog != null)
                        dialog.dismiss();
                    Log.e("ANAND____", response.isSuccessful() + " ::: " + response.body().isStatus());

                    try {
                        if (response.isSuccessful() && response.body().isStatus()) {

                            LoyaltyEarnMessageDialog mDialog = new LoyaltyEarnMessageDialog(activity, activity, activity, finalRequest, response.body());
                            mDialog.show();
                        } else {
                            LoyaltyEarnMessageDialog mDialog = new LoyaltyEarnMessageDialog(activity, activity, activity, finalRequest, response.body());
                            mDialog.show();
                            //Toast.makeText(activity, "Oops Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<UpdateLoyaltyPointsResponse> call, Throwable t) {
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
