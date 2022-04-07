package com.poundland.retail.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.databinding.DialogWriteReviewsBinding;
import com.poundland.retail.model.requestModel.SaveReviewRatingRequestModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.model.requestModel.MessageDetailRequestModel;
import com.poundland.retail.model.responseModel.GetOrderByIdResponseModel;
import com.poundland.retail.model.responseModel.NotifyMeResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.appUtils.HelperClass.toast;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;


public class DialogWriteReviews extends Dialog {
    private Context context;
    private LayoutInflater inflater;
    private DialogWriteReviewsBinding binding;
    private String orderId;
    private String productId;
    private PrefManager prefManager;
    private Float rating = 0.0f;
    private String review;
    private String productName;
    private String productDetailId;
    private String imageLink;
    private int orderDetailId;
    private boolean isOverAllRate;
    private GetOrderByIdResponseModel responseModel;

    public DialogWriteReviews(Context context, String orderId, String productId, Float rating, String review,
                              String productName, String imageLink, boolean isOverAllRate, String productDetailId, int orderDetailId) { // pos is auto incr id
        super(context);
        this.context = context;
        this.orderId = orderId;
        this.productId = productId;
        this.rating = rating;
        this.review = review;
        this.productName = productName;
        this.productDetailId = productDetailId;
        this.imageLink = imageLink;
        this.isOverAllRate = isOverAllRate;
        this.orderDetailId = orderDetailId;
        inflater = LayoutInflater.from(context);
        prefManager = PrefManager.getInstance(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().getAttributes().windowAnimations = R.style.AnimationCenterPopUp;
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_write_reviews, null, false);
        setContentView(binding.getRoot());
        setCancelable(false);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 10);
        getWindow().setBackgroundDrawable(inset);

        binding.tvProductName.setText(productName);

        Glide.with(context).load(/*ApiRequestUrl.BASE_URL +*/ imageLink).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(binding.ivProductImg);


        fetchProductRatting();

        binding.tvSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (responseModel == null) {
                    if (binding.rbRateProduct.getRating() > 0 || binding.rbRateStaffQuality.getRating() > 0 ||
                            binding.rbRateRecommendation.getRating() > 0 || binding.rbRateTime.getRating() > 0 ||
                            binding.rbRateOverAll.getRating() > 0 || binding.etWriteReview.getText().toString().length() > 0) {

                        rateNow(true, binding.rbRateProduct.getRating(), binding.rbRateStaffQuality.getRating(),
                                binding.rbRateRecommendation.getRating(), binding.rbRateTime.getRating(),
                                binding.rbRateOverAll.getRating(), binding.etWriteReview.getText().toString());
                    } else {
                        showSnackBar(binding.rlOverAllRatingView, context.getString(R.string.add_review_message));
                    }
                } else if (responseModel.getProductRating() == null) {
                    if (binding.rbRateProduct.getRating() > 0 || binding.rbRateStaffQuality.getRating() > 0 ||
                            binding.rbRateRecommendation.getRating() > 0 || binding.rbRateTime.getRating() > 0 ||
                            binding.rbRateOverAll.getRating() > 0 || binding.etWriteReview.getText().toString().length() > 0) {

                        rateNow(true, binding.rbRateProduct.getRating(), binding.rbRateStaffQuality.getRating(),
                                binding.rbRateRecommendation.getRating(), binding.rbRateTime.getRating(),
                                binding.rbRateOverAll.getRating(), binding.etWriteReview.getText().toString());
                    } else {
                        showSnackBar(binding.rlOverAllRatingView, context.getString(R.string.add_review_message));
                    }
                } else {
                    if (isValid())
                        rateNow(true, binding.rbRateProduct.getRating(), binding.rbRateStaffQuality.getRating(),
                                binding.rbRateRecommendation.getRating(), binding.rbRateTime.getRating(),
                                binding.rbRateOverAll.getRating(), binding.etWriteReview.getText().toString());
                }


            }
        });

        binding.ivCloseDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }

    private void rateNow(boolean isOverAllRate, float productRating, float rating, float recomRating, float collectioTimeRating,
                         float overAllRating, String ratingText) {
        if (isInternetOn(context)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(context);
            if (dialog != null)
                dialog.show();
            SaveReviewRatingRequestModel requestModel;
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<NotifyMeResponseModel> call;
            if (isOverAllRate) {

                SaveReviewRatingRequestModel.ProductBean productBean = new SaveReviewRatingRequestModel.ProductBean();
                productBean.setOrder_detail_id(String.valueOf(orderDetailId));
                productBean.setRating(productRating);
                productBean.setReview(ratingText);
                List<SaveReviewRatingRequestModel.ProductBean> productBeanList = new ArrayList<>();
                productBeanList.add(productBean);

                requestModel = new SaveReviewRatingRequestModel(orderId, recomRating, rating, collectioTimeRating,
                        overAllRating, ratingText, productBeanList);

                call = apiInterface.saveRatingOverAll(prefManager.getPreference(AUTH_TOKEN, ""),
                        prefManager.getPreference(EMAIL, ""), requestModel);
            } else {
                requestModel = new SaveReviewRatingRequestModel(orderId,
                        Integer.parseInt(productId), rating, ratingText);

                call = apiInterface.saveReviewRating(prefManager.getPreference(AUTH_TOKEN, ""),
                        prefManager.getPreference(EMAIL, ""), requestModel);
            }
            call.enqueue(new Callback<NotifyMeResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<NotifyMeResponseModel> call, @NonNull Response<NotifyMeResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            NotifyMeResponseModel userResponseBean = response.body();
                            if (userResponseBean.isStatus()) {
                                toast(context, userResponseBean.getMessage());
                                dismiss();
                            } else {
                                toast(context, userResponseBean.getMessage());
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<NotifyMeResponseModel> call, @NonNull Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), context.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), context.getString(R.string.no_internet_available_msg));
        }
    }

    private void fetchProductRatting() {
        if (isInternetOn(context)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            MessageDetailRequestModel requestModel = new MessageDetailRequestModel(String.valueOf(orderDetailId));

            Call<GetOrderByIdResponseModel> call = apiInterface.fetchProductRatting(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<GetOrderByIdResponseModel>() {
                @Override
                public void onResponse(Call<GetOrderByIdResponseModel> call, Response<GetOrderByIdResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            responseModel = response.body();
                            if (responseModel.isStatus()) {
                                if (responseModel.getProductRating() != null)
                                    setData(responseModel);

                            } else {
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
                            }
                        } else {
                            showSnackBar(binding.getRoot(), context.getString(R.string.msg_please_try_later));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetOrderByIdResponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), context.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), context.getString(R.string.no_internet_available_msg));

        }
    }

    private void setData(GetOrderByIdResponseModel totalCartResponseModel) {
        binding.rbRateProduct.setRating(totalCartResponseModel.getProductRating().getProduct_rattings());
        binding.etWriteReview.setText(totalCartResponseModel.getProductRating().getProduct_review());

        binding.rbRateStaffQuality.setRating(totalCartResponseModel.getProductRating().getStaff_rattings());
        binding.rbRateRecommendation.setRating(totalCartResponseModel.getProductRating().getRecomend_ratting());
        binding.rbRateTime.setRating(totalCartResponseModel.getProductRating().getDelivery_ratting());
        binding.rbRateOverAll.setRating(totalCartResponseModel.getProductRating().getOverall_ratting());
    }


    private boolean isValid() {
        if (responseModel.getProductRating().getProduct_rattings() > binding.rbRateProduct.getRating()) {
            showSnackBar(binding.getRoot(), context.getString(R.string.product_rating_msg) + " " + responseModel.getProductRating().getProduct_rattings());
            return false;
        } else if (responseModel.getProductRating().getStaff_rattings() > binding.rbRateStaffQuality.getRating()) {
            showSnackBar(binding.getRoot(), context.getString(R.string.staff_rating_msg) + " " + responseModel.getProductRating().getStaff_rattings());
            return false;
        } else if (responseModel.getProductRating().getRecomend_ratting() > binding.rbRateRecommendation.getRating()) {
            showSnackBar(binding.getRoot(), context.getString(R.string.recomm_rating_msg) + " " + responseModel.getProductRating().getRecomend_ratting());
            return false;
        } else if (responseModel.getProductRating().getDelivery_ratting() > binding.rbRateTime.getRating()) {
            showSnackBar(binding.getRoot(), context.getString(R.string.time_rating_msg) + " " + responseModel.getProductRating().getDelivery_ratting());
            return false;
        } else if (responseModel.getProductRating().getOverall_ratting() > binding.rbRateOverAll.getRating()) {
            showSnackBar(binding.getRoot(), context.getString(R.string.overall_rating_msg) + " " + responseModel.getProductRating().getOverall_ratting());
            return false;
        } else if (TextUtils.isEmpty(binding.etWriteReview.getText().toString().trim())) {
            showSnackBar(binding.getRoot(), context.getString(R.string.please_enter_review));
            return false;
        }
        return true;
    }
}

