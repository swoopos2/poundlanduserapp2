package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.ReviewActivityAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityReviewListBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.CancelOrderRequestModel;
import com.poundland.retail.model.responseModel.ReviewResponseModel;
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

public class ReviewListActivity extends BaseActivity implements View.OnClickListener, DrawerListner {

    private ActivityReviewListBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private List<ReviewResponseModel.ProductRatingBean.ReviewsBean.DataBean> reviewList;
    private String productId;
private ReviewListActivity instance=null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_review_list);
        init();
        setListeners();
    }

    private void init() {
        instance= this;
        reviewList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        productId=getIntent().getStringExtra(PRODUCT_ID);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.title.setText(getString(R.string.product_review));
        getReview(productId);
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
        }
    }

    private void setReviewAdaper() {
        ReviewActivityAdapter reviewAdapter = new ReviewActivityAdapter(this, reviewList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvTopReview.setLayoutManager(layoutManager);
        binding.rvTopReview.setAdapter(reviewAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getReview(String productId) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            CancelOrderRequestModel requestModel = new CancelOrderRequestModel(productId);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<ReviewResponseModel> call = apiInterface.getReview(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<ReviewResponseModel>() {
                @Override
                public void onResponse(Call<ReviewResponseModel> call, Response<ReviewResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            ReviewResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {

                                if (responseModel.getProductRating().getReviews().getData() != null && responseModel.getProductRating().getReviews().getData().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);

                                    reviewList.addAll(responseModel.getProductRating().getReviews().getData());
                                    setReviewAdaper();
                                } else {
                                    binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    reviewList.clear();
                                    setReviewAdaper();
                                }

                            } else
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
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
                    }
                }

                @Override
                public void onFailure(Call<ReviewResponseModel> call, Throwable t) {
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

    @Override
    public void onDrawerSelect(int position, int clickId) {

    }
    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_review_list;
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
