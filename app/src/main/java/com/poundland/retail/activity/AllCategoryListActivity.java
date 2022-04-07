package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.AllCategoryListAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityAllCategoryListBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.ProductBySearchRequestModel;
import com.poundland.retail.model.responseModel.AllCategoryResponseModel;
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
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.CATEGORY_NAME;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_CATEGORY_TAP;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class AllCategoryListActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {
    private ActivityAllCategoryListBinding binding;
    private PrefManager prefManager;
    private List<AllCategoryResponseModel.CategoriesBean> categoriesList;
    private Call<AllCategoryResponseModel> call;
    private String search = "";
    private AllCategoryListActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_category_list);
        init();
        getCategoriesList(false);
        setListeners();

        binding.etSearh.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    binding.ivBtnClose.setVisibility(View.VISIBLE);
                } else {
                    binding.ivBtnClose.setVisibility(View.INVISIBLE);
                }
                search = s.toString();
                categoriesList.clear();
                if (call != null)
                    call.cancel();
                getCategoriesList(true);
            }
        });
    }

    private void init() {
        instance = this;
        categoriesList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        binding.title.setText(getString(R.string.all_category));
    }


    private void setAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        AllCategoryListAdapter loyaltyStampVoucherAdapter = new AllCategoryListAdapter(this, categoriesList, this);
        binding.rvCategoryList.setLayoutManager(layoutManager);
        binding.rvCategoryList.setAdapter(loyaltyStampVoucherAdapter);
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.ivBtnClose.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_btn_close:
                if (binding.etSearh.getText().length() > 0)
                    binding.etSearh.setText("");
                break;
        }
    }


    private void getCategoriesList(boolean isClose) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isClose)
                dialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");
            // String longitude = "-2.138813";
            ProductBySearchRequestModel productBySearchRequestModel = new ProductBySearchRequestModel(search, latitude, longitude);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            call = apiInterface.getAllCatWithSearch(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), productBySearchRequestModel);
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<AllCategoryResponseModel>() {
                @Override
                public void onResponse(Call<AllCategoryResponseModel> call, Response<AllCategoryResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            AllCategoryResponseModel homeResponseModel = response.body();
                            if (homeResponseModel.isStatus()) {

                                if (homeResponseModel.getCategories() != null && homeResponseModel.getCategories().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);
                                    categoriesList.addAll(homeResponseModel.getCategories());
                                    setAdapter();
                                } else {
                                    binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    categoriesList.clear();
                                    setAdapter();
                                }

                            } else {
                                showSnackBar(binding.getRoot(), homeResponseModel.getMessage());
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
                        Log.e("onException ", " - " + e);
                    }
                }

                @Override
                public void onFailure(Call<AllCategoryResponseModel> call, Throwable t) {
                    binding.swipeRefresh.setRefreshing(false);
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
            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    @Override
    public void onRefresh() {
        categoriesList.clear();
        if (binding.etSearh.getText().length() > 0) {
            binding.etSearh.setText("");
        } else
            getCategoriesList(false);
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        Intent allShow = new Intent(this, CategoryDetailsActivity.class);
        allShow.putExtra(FROM_WHERE, FROM_CATEGORY_TAP);
        allShow.putExtra(CATEGORY_NAME, categoriesList.get(position).getCat_name());
        allShow.putExtra(CATEGORY_ID, String.valueOf(categoriesList.get(position).getId()));
        startActivity(allShow);
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
}
