package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;

import com.google.gson.Gson;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityReferFriendsBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.notificationService.NotificationModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class ReferFriendsActivity extends BaseActivity implements View.OnClickListener {

    private ActivityReferFriendsBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private String type;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_refer_friends);
        getIntentData();
        init();
       // setData();
        setListeners();
    }

    private void getIntentData() {
      //  type = getIntent().getStringExtra(CONTENT_TYPE);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
    private void init() {
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        Log.e("AUTH Token", "" + authToken);

    }


    /*private void setData() {
        switch (type) {
            case "HELP":
                binding.tvTitle.setText(getResources().getString(R.string.help));
                binding.tvHeading.setText(getResources().getString(R.string.how_can_we_help));
                getStaticContent("help");
                break;
            case "ABOUT":
                binding.tvTitle.setText(getResources().getString(R.string.about_us));
                binding.tvHeading.setVisibility(View.GONE);
                getStaticContent("about");
                break;
            case "TERM":
                binding.tvTitle.setText(getResources().getString(R.string.term_condition));
                binding.tvHeading.setText(getResources().getString(R.string.our_t_c));
                getStaticContent("term");
                break;
            case "POLICY":
                binding.tvTitle.setText(getResources().getString(R.string.privacy_policy));
                binding.tvHeading.setText(getResources().getString(R.string.privacy_policy));
                getStaticContent("policy");
                break;
        }
    }*/

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
//        binding.ivCart.setOnClickListener(this);
        binding.tvShare.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
                case R.id.tv_share:
                    HelperClass.shareApp(this,binding.tvReferMessage.getText().toString()
                            ,"\n\nHere is referral code: "+binding.tvRefferCode.getText().toString());
                break;
                /*case R.id.iv_cart:
                    Intent intent = new Intent(this, MyCartActivity.class);
                    startActivity(intent);

                    break;*/
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        getTotalCartCount();
    }

    private void getTotalCartCount() {
        if (isInternetOn(this)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<GetTotalCartResponseModel> call = apiInterface.getTotalCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""));
            call.enqueue(new Callback<GetTotalCartResponseModel>() {
                @Override
                public void onResponse(Call<GetTotalCartResponseModel> call, Response<GetTotalCartResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            GetTotalCartResponseModel totalCartResponseModel = response.body();
                            if (totalCartResponseModel.isStatus()) {
                                if (totalCartResponseModel.getTotal_carts() == 0)
                                    binding.tvCartCount.setVisibility(View.INVISIBLE);
                                binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
                            } else {
                                showSnackBar(binding.getRoot(), totalCartResponseModel.getMessage());
                            }
                        } else {
                         //   showSnackBar(binding.getRoot(), getString(R.string.total_cart_item_not_found));
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetTotalCartResponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

   /* private void getStaticContent(final String contentType) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<StaticContentBean> call = apiInterface.getStaticContent(authToken, contentType);
            call.enqueue(new Callback<StaticContentBean>() {
                @Override
                public void onResponse(@NonNull Call<StaticContentBean> call, @NonNull Response<StaticContentBean> response) {
                    try {

                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            StaticContentBean staticResponseBean = response.body();
                            if (staticResponseBean != null) {
                                if (staticResponseBean.getStatus() == 200) {
                                    binding.tvContent.setText(Html.fromHtml(staticResponseBean.getData().getContent()));

                                } else if (staticResponseBean.getStatus() == 401) {
                                    DialogUtils.showAlertDialogWithSingleButton(TermConditionActivity.this, staticResponseBean.getUserMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            Helper.logoutUser(TermConditionActivity.this);
                                        }
                                    });
                                } else {
                                    showSnackBar(binding.getRoot(), staticResponseBean.getUserMessage());
                                }
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<StaticContentBean> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }*/

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
