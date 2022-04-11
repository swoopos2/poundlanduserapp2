package com.poundland.retail.zzznewPoundland.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.DialogLoyaltyEarnMessageBinding;
import com.poundland.retail.zzznewPoundland.ApiClientPLAND;
import com.poundland.retail.zzznewPoundland.callbacks.OnSuccess;
import com.poundland.retail.zzznewPoundland.model.UpdateLoyaltyPointsRequest;
import com.poundland.retail.zzznewPoundland.model.UpdateLoyaltyPointsResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.USER_ID;

public class LoyaltyEarnMessageDialog extends Dialog implements View.OnClickListener {
    private static final String TAG = LoyaltyEarnMessageDialog.class.getName();
    private Context mContext;
    private Activity mActivity;
    private PrefManager prefManager;
    private DialogLoyaltyEarnMessageBinding mBinding;
    private KProgressHUD dialog;
    UpdateLoyaltyPointsRequest request;
    private String text = "";
    private OnSuccess onDialogClickListener;
    UpdateLoyaltyPointsRequest finalRequest;
    UpdateLoyaltyPointsResponse body;

    public LoyaltyEarnMessageDialog(Activity mActivity, @NonNull Context context, OnSuccess onSuccess, UpdateLoyaltyPointsRequest finalRequest, UpdateLoyaltyPointsResponse body) {
        super(context);
        dialog = new KProgressHUD(mActivity);
        prefManager = PrefManager.getInstance(mContext);
        this.mActivity = mActivity;
        this.onDialogClickListener = onSuccess;
        this.finalRequest = finalRequest;
        this.body = body;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getContext();
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().getAttributes().windowAnimations = R.style.AnimationCenterPopUp;
        mBinding = DataBindingUtil.inflate(LayoutInflater.from(mContext), R.layout.dialog_loyalty_earn_message, null, false);
        setContentView(mBinding.getRoot());
        setCancelable(true);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 10);
        getWindow().setBackgroundDrawable(inset);


        //   mBinding.btnYes.setOnClickListener(this);
        mBinding.btnNo.setOnClickListener(this);

        /*String decrypted = null;
        try {
            decrypted = EncryptDecryptAESUtils.decrypt(text);
            Log.d("TEST", "decrypted:" + decrypted);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (decrypted != null) {
            request = new Gson().fromJson(decrypted, UpdateLoyaltyPointsRequest.class);
            mBinding.message.setText(getContext().getString(R.string.loyalty_earn, HelperClass.decimalFormatReturnString(request.getLoyaltyPoints())));
        }*/

        mBinding.message.setText(body.getMessage());

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_no:
                dismiss();
                if (body.isStatus()) {
                    onDialogClickListener.onDrawerSelect(1, body);
                    dismiss();
                } else
                    onDialogClickListener.onDrawerSelect(2, null);
                break;
            case R.id.btn_yes:
                // addLoyaltyPoints();
                //dismiss();
                break;


        }
    }

    private void addLoyaltyPoints() {
        if (request == null) {
            request = new UpdateLoyaltyPointsRequest();
        }
        if (isInternetOn(mContext)) {
            if (dialog != null)
                dialog.show();


            if (request != null && request.getUserId() == null) {
                request.setUserId(prefManager.getPreference(USER_ID));
            }

            ApiInterface apiInterface = ApiClientPLAND.getClient().create(ApiInterface.class);
            // AddGuestRequestModel requestModel = new AddGuestRequestModel(mItems);
            Call<UpdateLoyaltyPointsResponse> call = apiInterface.updateLoyaltyPoints(request);
            call.enqueue(new Callback<UpdateLoyaltyPointsResponse>() {
                @Override
                public void onResponse(Call<UpdateLoyaltyPointsResponse> call, Response<UpdateLoyaltyPointsResponse> response) {
                    if (dialog != null)
                        dialog.dismiss();
                    Log.e("ANAND____", response.isSuccessful() + " ::: " + response.body().isStatus());

                    try {
                        if (response.isSuccessful() && response.body().isStatus()) {
                            // onDialogClickListener.onDrawerSelect(1, request);
                            dismiss();
                        } else {
                            Toast.makeText(getContext(), "Oops Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<UpdateLoyaltyPointsResponse> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(mBinding.getRoot(), mContext.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(mBinding.getRoot(), mContext.getString(R.string.no_internet_available_msg));

        }
    }


}
