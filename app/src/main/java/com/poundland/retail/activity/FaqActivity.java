package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.FaqListAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityFaqBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.ChangePasswordRequestModel;
import com.poundland.retail.model.responseModel.AboutResponseModel;
import com.poundland.retail.model.responseModel.FaqResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class FaqActivity extends BaseActivity implements View.OnClickListener, DrawerListner {

    private ActivityFaqBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private List<FaqResponseModel.FaqBean> faqBeanList;
    private  FaqActivity instance = null;
    private String DELETE_ACCOUNT = "2";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_faq);
        //getIntentData();
        init();
        setListeners();
        getAddressList();
    }

    private void init() {
        instance = this;
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.tvTitle.setText(getString(R.string.faq_support));
        faqBeanList = new ArrayList<>();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.rlDeleteAccount.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_delete_account:
                DialogUtils.dialogLogOut(this, getString(R.string.delete_account_message), getString(R.string.delete_account), new OnDialogClickListener() {
                    @Override
                    public void onPositiveClick() {
                        deleteAccount();
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });
                break;
        }
    }

    private void setAdapter() {
        FaqListAdapter addressAdapter = new FaqListAdapter(this, faqBeanList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvFaqList.setLayoutManager(layoutManager);
        binding.rvFaqList.setAdapter(addressAdapter);
    }
    private void deleteAccount() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ChangePasswordRequestModel requestModel = new ChangePasswordRequestModel(DELETE_ACCOUNT);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<AboutResponseModel> call = apiInterface.deleteAccount(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
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

                                    DialogUtils.showAlertDialogWithSingleButton(instance, staticResponseBean.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            logOut(instance);
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });


                                } else {
                                    DialogUtils.showAlertDialogWithSingleButton(instance, staticResponseBean.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {

                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                                }
                            }

                        } else
                            showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
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
    private void getAddressList() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<FaqResponseModel> call = apiInterface.getFaq();
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<FaqResponseModel>() {
                @Override
                public void onResponse(Call<FaqResponseModel> call, Response<FaqResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            FaqResponseModel homeResponseModel = response.body();
                            if (homeResponseModel.isStatus()) {

                                if (homeResponseModel.getFaq() != null && homeResponseModel.getFaq().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);
                                    faqBeanList.addAll(homeResponseModel.getFaq());
                                    setAdapter();
                                } else {
                                    binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    faqBeanList.clear();
                                    setAdapter();
                                }

                            } else {
                                showSnackBar(binding.getRoot(), homeResponseModel.getMessage());
                            }
                        } else {
                            final int httpCode =response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode==401? getString(R.string.season_expired): getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode==401){
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
                        Log.e("onException ", " - " + e);
                    }
                }

                @Override
                public void onFailure(Call<FaqResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    if (!call.isCanceled()) {
                        showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                    } else if (call.isCanceled()) {
                        Log.e("onFailure ", " - " + t);
                    }
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    @Override
    public void onDrawerSelect(int position, int clickId) {

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
