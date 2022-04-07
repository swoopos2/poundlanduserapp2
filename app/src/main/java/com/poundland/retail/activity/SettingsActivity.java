package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;

import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.HttpMethod;
import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivitySettingsBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.ChangePasswordRequestModel;
import com.poundland.retail.model.responseModel.AboutResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.POSITION;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TITLE;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class SettingsActivity extends BaseActivity implements View.OnClickListener {

    private ActivitySettingsBinding binding;
    private boolean showAbout;
    private PrefManager prefManager;
    private GoogleSignInClient mGoogleSignInClient;
    private SettingsActivity instance = null;
    private String DELETE_ACCOUNT = "2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_settings);
        instance = this;
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        setListner();
    }

    private void setListner() {
        prefManager = PrefManager.getInstance(this);

        binding.llAboutExpand.setVisibility(View.GONE);
        showAbout = true;


        binding.rlProfile.setOnClickListener(this);
        binding.rlChangePassword.setOnClickListener(this);
        binding.rlResetPin.setOnClickListener(this);
        binding.rlAbout.setOnClickListener(this);
        binding.llAboutExpand.setOnClickListener(this);
        binding.tvAboutSwoope.setOnClickListener(this);
        binding.tvAppVersion.setOnClickListener(this);
        binding.tvTermCondition.setOnClickListener(this);
        binding.tvPrivacyPolicy.setOnClickListener(this);
        binding.rlFaq.setOnClickListener(this);
        binding.rlLogout.setOnClickListener(this);
        binding.rlDeleteAccount.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.ivNotification.setOnClickListener(this);


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_profile:

                Intent allShow = new Intent(this, MyProfileActivity.class);
                startActivity(allShow);

                break;
            case R.id.rl_change_password:
                Intent changePass = new Intent(this, ChangePasswordActivity.class);
                startActivity(changePass);
                break;
            case R.id.iv_back:
                finish();
                break;
            case R.id.rl_reset_pin:
               // Intent resetPin = new Intent(this, ResetPinActivity.class);
                Intent resetPin = new Intent(this, VerifyPinActivity.class);
                resetPin.putExtra(FROM_WHERE, getString(R.string.setting));
                resetPin.putExtra(POSITION, -1);
                startActivity(resetPin);

                break;
            case R.id.rl_about:

                if (showAbout) {
                    binding.llAboutExpand.setVisibility(View.VISIBLE);
                    showAbout = false;
                } else {
                    binding.llAboutExpand.setVisibility(View.GONE);
                    showAbout = true;
                }

                break;

            case R.id.tv_about_swoope:
                Intent intent = new Intent(this, TermConditionActivity.class);
                intent.putExtra(TITLE, getString(R.string.about));
                startActivity(intent);
                break;
            case R.id.tv_app_version:
                DialogUtils.appVersionDialog(this);

                break;
            case R.id.tv_term_condition:
                Intent term = new Intent(this, TermConditionActivity.class);
                term.putExtra(TITLE, getString(R.string.tc));
                startActivity(term);
                break;
            case R.id.tv_privacy_policy:
                Intent policy = new Intent(this, TermConditionActivity.class);
                policy.putExtra(TITLE, getString(R.string.privacy_policy));
                startActivity(policy);
                break;


            case R.id.rl_faq:
                Intent faq = new Intent(this, FaqActivity.class);
                startActivity(faq);
                break;
            case R.id.rl_logout:
                DialogUtils.dialogLogOut(instance, getString(R.string.logout_message),getString(R.string.logout), new OnDialogClickListener() {
                    @Override
                    public void onPositiveClick() {

                        HelperClass.logOut(instance);
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });

                break;
            case R.id.rl_delete_account:
                DialogUtils.dialogLogOut(this, getString(R.string.delete_account_message),getString(R.string.delete_account), new OnDialogClickListener() {
                    @Override
                    public void onPositiveClick() {
                        deleteAccount();
                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });
                break;

            case R.id.iv_cart:
                if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                    Intent intentH = new Intent(this, MyCartHospitalityActivity.class);
                    startActivity(intentH);
                } else {
                    Intent intentR = new Intent(this, MyCartActivity.class);
                    startActivity(intentR);
                }
                break;
            case R.id.iv_notification:
                Intent noti = new Intent(this, NotificationActivity.class);
                startActivity(noti);

                break;
        }
    }

    private void signOut() {
       /* mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        // ...
                        Toast.makeText(SettingsActivity.this, "sign out", Toast.LENGTH_SHORT).show();
                        HelperClass.logoutUser(SettingsActivity.this, "");
                    }
                });*/


        HelperClass.signOutGmail(SettingsActivity.this);
    }

    private void revokeAccess() {  // disconnect app
        mGoogleSignInClient.revokeAccess()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(SettingsActivity.this, "Deactivated", Toast.LENGTH_SHORT).show();
                        HelperClass.logoutUser(SettingsActivity.this, "");
                    }
                });
    }


    public void signOutFacebook() {

        if (AccessToken.getCurrentAccessToken() == null) {
            return; // already logged out
        }

        new GraphRequest(AccessToken.getCurrentAccessToken(), "/me/permissions/", null, HttpMethod.DELETE, new GraphRequest
                .Callback() {
            @Override
            public void onCompleted(GraphResponse graphResponse) {

                LoginManager.getInstance().logOut();
                HelperClass.logoutUser(SettingsActivity.this, "");

            }
        }).executeAsync();
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

                        }else  showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
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
                                if (totalCartResponseModel.getTotal_carts() > 0) {
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
                                }else binding.tvCartCount.setVisibility(View.INVISIBLE);
                                binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
                            } else {
                                showSnackBar(binding.getRoot(), totalCartResponseModel.getMessage());
                            }
                        } else {
                          //  showSnackBar(binding.getRoot(), getString(R.string.total_cart_item_not_found));
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