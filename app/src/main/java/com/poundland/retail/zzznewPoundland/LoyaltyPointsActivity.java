package com.poundland.retail.zzznewPoundland;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.BaseActivity;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityLoyalityPointsBinding;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.zzznewPoundland.model.FetchCustomerRequest;
import com.poundland.retail.zzznewPoundland.model.UpdateLoyaltyPointsRequest;
import com.poundland.retail.zzznewPoundland.model.UpdateLoyaltyPointsResponse;
import com.poundland.retail.zzznewPoundland.model.UserLoginResponseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.USER_ID;

public class LoyaltyPointsActivity extends BaseActivity implements View.OnClickListener {
    private String TAG = LoyaltyPointsActivity.class.getName();
    private ActivityLoyalityPointsBinding binding;
    private LoyaltyPointsActivity activity;
    private KProgressHUD dialog;
    private PrefManager prefManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activity = this;
        binding = DataBindingUtil.setContentView(this, R.layout.activity_loyality_points);
        prefManager = PrefManager.getInstance(this);
        dialog = new KProgressHUD(this);
        setListeners();
        getLoyaltyPoints();
        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(Constants.SHOW_RECENT_ACTIVITY)) {
            UpdateLoyaltyPointsResponse loyaltyPoints = bundle.getParcelable(Constants.SHOW_RECENT_ACTIVITY);
            if (loyaltyPoints.getEarn_loyalty_points() > 0) {
                binding.recentEarnLoyaltyPoint.setText(getResources().getString(R.string.you_earn, HelperClass.decimalFormatReturnString(loyaltyPoints.getEarn_loyalty_points())));
                binding.earnLoyaltyPoint.setText("+" + HelperClass.decimalFormatReturnString(loyaltyPoints.getEarn_loyalty_points()));
                binding.recentOrderAmount.setText(getResources().getString(R.string.recent_order_amount, HelperClass.decimalFormat(this, (float) loyaltyPoints.getEarn_loyalty_points())));
            }
        } else {
            binding.recentEarnLoyaltyPoint.setText("");
        }
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
    }


    private void initObserve(UserLoginResponseModel.UserDataBean userDataBean) {
        if (userDataBean != null) {
            binding.loyaltyPoints.setText(userDataBean.getLoyalty_points() + "Pts");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_loyality_points;
    }

    @Override
    protected void onNotificationReceived(Intent intent) {

    }


    public void getLoyaltyPoints() {
        FetchCustomerRequest request = new FetchCustomerRequest();
        request.setUserId(prefManager.getPreference(USER_ID));
        ApiInterface apiInterface = ApiClientPLAND.getClient().create(ApiInterface.class);
        Call<UserLoginResponseModel> call = apiInterface.fetchCustomerDetails(request);
        call.enqueue(new Callback<UserLoginResponseModel>() {
            @Override
            public void onResponse(Call<UserLoginResponseModel> call, Response<UserLoginResponseModel> response) {
                Log.e("CALLLL", ">>>>> " + response.isSuccessful());
                if (response.isSuccessful() && response.body().getStatus()) {

                    initObserve(response.body().getCustomer());

                } else {
                    showSnackBar(binding.getRoot(), getString(R.string.something_went_wrong));
                }

            }

            @Override
            public void onFailure(Call<UserLoginResponseModel> call, Throwable t) {
                showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
            }
        });
    }


}
