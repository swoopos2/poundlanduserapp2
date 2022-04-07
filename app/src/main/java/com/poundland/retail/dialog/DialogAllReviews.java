package com.poundland.retail.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.InsetDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.RattingListAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.DialogAllReviewsBinding;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.GetRattingRequestModel;
import com.poundland.retail.model.requestModel.SaveReviewRatingRequestModel;
import com.poundland.retail.model.responseModel.GetRattingResponseModel;
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


public class DialogAllReviews extends Dialog {
    private Context context;
    private LayoutInflater inflater;
    private DialogAllReviewsBinding binding;
    private PrefManager prefManager;
    private String orderId;
    private SuccessActionListner successActionListner;
    private GetRattingResponseModel responseModel;

    public DialogAllReviews(Context context, String orderId,SuccessActionListner successActionListner) {
        super(context);
        this.context = context;
        this.orderId = orderId;
        this.successActionListner = successActionListner;
        inflater = LayoutInflater.from(context);
        prefManager = PrefManager.getInstance(context);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().getAttributes().windowAnimations = R.style.AnimationCenterPopUp;
        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_all_reviews, null, false);
        setContentView(binding.getRoot());
        setCancelable(false);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        ColorDrawable back = new ColorDrawable(Color.TRANSPARENT);
        InsetDrawable inset = new InsetDrawable(back, 10);
        getWindow().setBackgroundDrawable(inset);

        fetchProductRatting();

        binding.tvSubmitReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if (binding.rbRateStaffQuality.getRating() > 0 ||
                        binding.rbRateRecommendation.getRating() > 0 || binding.rbRateTime.getRating() > 0 ||
                        binding.rbRateOverAll.getRating() > 0 || binding.etWriteReview.getText().toString().length() > 0) {

                    rateNow(binding.rbRateStaffQuality.getRating(),
                            binding.rbRateRecommendation.getRating(), binding.rbRateTime.getRating(),
                            binding.rbRateOverAll.getRating(), binding.etWriteReview.getText().toString());
                } else {
                    showSnackBar(binding.rlOverAllRatingView, context.getString(R.string.add_review_message));
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

    private void fetchProductRatting() {
        if (isInternetOn(context)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            GetRattingRequestModel requestModel = new GetRattingRequestModel(String.valueOf(orderId));

            Call<GetRattingResponseModel> call = apiInterface.getOrderDetailsById(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<GetRattingResponseModel>() {
                @Override
                public void onResponse(Call<GetRattingResponseModel> call, Response<GetRattingResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            responseModel = response.body();
                            if (responseModel.isStatus()) {
                                if (responseModel.getReview() != null)
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
                public void onFailure(Call<GetRattingResponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), context.getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), context.getString(R.string.no_internet_available_msg));

        }
    }

    private void setData(GetRattingResponseModel totalCartResponseModel) {
        // binding.rbRateProduct.setRating(totalCartResponseModel.getProductRating().getProduct_rattings());
        binding.etWriteReview.setText(totalCartResponseModel.getReview().getOverall_review());
        binding.rbRateStaffQuality.setRating(Float.parseFloat(totalCartResponseModel.getReview().getStaff_rattings()));
        binding.rbRateRecommendation.setRating(Float.parseFloat(totalCartResponseModel.getReview().getRecomend_ratting()));
        binding.rbRateTime.setRating(Float.parseFloat(totalCartResponseModel.getReview().getDelivery_ratting()));
        binding.rbRateOverAll.setRating(Float.parseFloat(totalCartResponseModel.getReview().getOverall_ratting()));


        RattingListAdapter myOrderNewAdapter = new RattingListAdapter(context, totalCartResponseModel.getReview().getProducts(), new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {

            }
        });
        binding.rvProducts.setLayoutManager(new WrapContentLinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        binding.rvProducts.setAdapter(myOrderNewAdapter);
    }

    private void rateNow(float rating, float recomRating, float collectioTimeRating,
                         float overAllRating, String ratingText) {
        if (isInternetOn(context)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(context);
            if (dialog != null)
                dialog.show();
            SaveReviewRatingRequestModel requestModel;
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            List<SaveReviewRatingRequestModel.ProductBean> productBeanList = new ArrayList<>();
            for (int i = 0; i < responseModel.getReview().getProducts().size(); i++) {
                SaveReviewRatingRequestModel.ProductBean productBean = new SaveReviewRatingRequestModel.ProductBean();
                productBean.setOrder_detail_id(String.valueOf(responseModel.getReview().getProducts().get(i).getId()));
                productBean.setRating(Float.parseFloat(responseModel.getReview().getProducts().get(i).getRatings()));
                productBean.setReview(ratingText);
                productBeanList.add(productBean);
            }

            requestModel = new SaveReviewRatingRequestModel(orderId, recomRating, rating, collectioTimeRating,
                    overAllRating, ratingText, productBeanList);

            Call<NotifyMeResponseModel> call = apiInterface.saveRatingOverAll(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
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
                                successActionListner.onSuccessActionListner();
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

    private boolean isValid() {
        /*if (responseModel.getReview().getProductRating().getProduct_rattings() > binding.rbRateProduct.getRating()) {
            showSnackBar(binding.getRoot(), context.getString(R.string.product_rating_msg) + " " + responseModel.getProductRating().getProduct_rattings());
            return false;
        } else*/
        if (Float.parseFloat(responseModel.getReview().getStaff_rattings()) > binding.rbRateStaffQuality.getRating()) {
            showSnackBar(binding.getRoot(), context.getString(R.string.staff_rating_msg) + " " + responseModel.getReview().getStaff_rattings());
            return false;
        } /*else if (responseModel.getProductRating().getRecomend_ratting() > binding.rbRateRecommendation.getRating()) {
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
        }*/
        return true;
    }
}

