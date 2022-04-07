package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.LookUpAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityLookUpBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.NotificationDeleteRequestModel;
import com.poundland.retail.model.responseModel.GetUserLocationResponseModel;
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
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.FUSED_LOCATION;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.LOOKUP_ACTIVITY_CALLBACK_DATA;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.POSTAL_CODE;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class LookUpActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {
    private ActivityLookUpBinding binding;
    private PrefManager prefManager;
    private LookUpAdapter lookUpAdapter;
    private LookUpActivity instance = null;
    private List<GetUserLocationResponseModel.DataBean> lookUpList;
    private String fromWhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_look_up);
        init();
        setListeners();
    }

    private void init() {
        instance = this;
        lookUpList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        binding.title.setText(getString(R.string.location));
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.ivLookup.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_lookup:
                if (!TextUtils.isEmpty(binding.etSearchPostalcode.getText().toString().trim()))
                    getUserLocation(binding.etSearchPostalcode.getText().toString().trim());
                break;
        }
    }

    @Override
    public void onRefresh() {
        if (!TextUtils.isEmpty(binding.etSearchPostalcode.getText().toString().trim()))
            getUserLocation(binding.etSearchPostalcode.getText().toString().trim());
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        Intent intent = new Intent();
        intent.putExtra(LATITUDE, lookUpList.get(position).getLatitude());
        intent.putExtra(LONGITUDE, lookUpList.get(position).getLongitude());
        intent.putExtra(LOOKUP_ACTIVITY_CALLBACK_DATA, lookUpList.get(position).getLine_1());
        intent.putExtra(POSTAL_CODE, binding.etSearchPostalcode.getText().toString().trim());

        if (!fromWhere.equals(getString(R.string.add_address))){
            prefManager.savePreference(LATITUDE, String.valueOf(lookUpList.get(position).getLatitude()));
            prefManager.savePreference(LONGITUDE, String.valueOf(lookUpList.get(position).getLongitude()));
            prefManager.savePreference(FUSED_LOCATION, lookUpList.get(position).getLine_1());
        }

        setResult(RESULT_OK, intent);
        finish();
    }

    private void setAdapter() {
        lookUpAdapter = new LookUpAdapter(this, lookUpList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvLookup.setLayoutManager(layoutManager);
        binding.rvLookup.setItemAnimator(new DefaultItemAnimator());
        binding.rvLookup.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        binding.rvLookup.setAdapter(lookUpAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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

    /// Look Up
    private void getUserLocation(String postal) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            NotificationDeleteRequestModel beanX = new NotificationDeleteRequestModel(postal, "");
            Call<GetUserLocationResponseModel> call = apiInterface.get_user_location
                    (prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), beanX);
            call.enqueue(new Callback<GetUserLocationResponseModel>() {
                @Override
                public void onResponse(Call<GetUserLocationResponseModel> call, Response<GetUserLocationResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            GetUserLocationResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {
                                if (responseModel.getData() != null && responseModel.getData().size() > 0) {
                                    lookUpList.clear();
                                    lookUpList.addAll(responseModel.getData());
                                    setAdapter();
                                } else {
                                    lookUpList.clear();
                                }
                            } else {
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
                            }
                        } else {
                            final int httpCode = response.code();
                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
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
                public void onFailure(Call<GetUserLocationResponseModel> call, Throwable t) {
                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }
}
