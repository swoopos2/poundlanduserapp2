package com.poundland.retail.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityLoginBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.LoginRequestModel;
import com.poundland.retail.model.requestModel.SignUpRequestModel;
import com.poundland.retail.model.responseModel.LoginResponseModel;
import com.poundland.retail.model.responseModel.PinResponseModel;
import com.poundland.retail.model.responseModel.SendOtpResponseModel;
import com.poundland.retail.model.responseModel.SocialMediaResponseModel;
import com.poundland.retail.zzznewPoundland.ApiClientPLAND;
import com.poundland.retail.zzznewPoundland.model.UserLoginResponseModel;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.apiUtils.ApiRequestUrl.BASE_URL;
import static com.poundland.retail.appUtils.HelperClass.getFcmToken;
import static com.poundland.retail.appUtils.HelperClass.hideKeyboard;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.isValidEmail;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.appUtils.HelperClass.toast;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_ORDER;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_TYPE;
import static com.poundland.retail.interfaces.Constants.DEVICE_TYPE;
import static com.poundland.retail.interfaces.Constants.DOB;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FB;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.GENDER;
import static com.poundland.retail.interfaces.Constants.GMAIL;
import static com.poundland.retail.interfaces.Constants.LAST_NAME;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.LOGIN_TYPE;
import static com.poundland.retail.interfaces.Constants.PROFILE_IMAGE;
import static com.poundland.retail.interfaces.Constants.REFERRAL_CODE;
import static com.poundland.retail.interfaces.Constants.REFERRED_CODE;
import static com.poundland.retail.interfaces.Constants.SIGN_UP_REQU_MODEL;
import static com.poundland.retail.interfaces.Constants.STRIPE_CUST_ID;
import static com.poundland.retail.interfaces.Constants.TOKEN_TYPE;
import static com.poundland.retail.interfaces.Constants.Unprocessable_Entity;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private ActivityLoginBinding binding;
    private PrefManager prefManager;
    private SharedPreferences loginPreferences;
    private SharedPreferences.Editor loginPrefsEditor;
    private Boolean saveLogin;
    private AccessToken accessToken;
    private CallbackManager callbackManager;
    private GoogleSignInClient mGoogleSignInClient;
    private static final int RC_SIGN_IN = 2212;
    private Uri personPhoto;
    private String refreshedToken;
    private boolean isResendClickable = true;
    private FirebaseAnalytics mFirebaseAnalytics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        LoginManager.getInstance().logOut();
        HelperClass.hashFromSHA1("1F:35:58:4E:83:E9:5A:6D:CB:3D:26:7F:CD:03:38:61:13:AA:33:2F");
        HelperClass.printHashKey(this);
        init();
        binding.ivLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void setListeners() {
        binding.tvRegister.setEnabled(true);
        binding.tvRegister.setOnClickListener(this);
        binding.tvLogin.setOnClickListener(this);
        binding.cvLoginNow.setOnClickListener(this);
        binding.tvForgotPassword.setOnClickListener(this);
        binding.ivFb.setOnClickListener(this);
        binding.ivEmail.setOnClickListener(this);
        binding.ivLogo.setOnClickListener(this);
    }

    private void init() {
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this);
        prefManager = PrefManager.getInstance(this);
        //refreshedToken = FirebaseInstanceId.getInstance().getToken();
        getFcmToken(this);


        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {

                if (task.isSuccessful()) {
                    refreshedToken = task.getResult();
                    Log.e("refreshedToken : >>> ", refreshedToken);
                }
            }
        });

        loginPreferences = getSharedPreferences("loginPrefs", MODE_PRIVATE);
        loginPrefsEditor = loginPreferences.edit();

        saveLogin = loginPreferences.getBoolean("saveLogin", false);
        if (saveLogin) {
            binding.etUserId.setText(loginPreferences.getString("username", ""));
            binding.etPassword.setText(loginPreferences.getString("password", ""));
            binding.cbRememberMe.setChecked(true);
        }
        callbackManager = CallbackManager.Factory.create();
        // binding.fbLoginButton.setPermissions(Arrays.asList(EMAIL,));
        binding.fbLoginButton.setPermissions(Arrays.asList("user_photos", "email", "user_birthday", "user_friends", "public_profile"));
        // LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
        binding.fbLoginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();
                getProfile(loginResult);  // App code
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(LoginActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(LoginActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });

//////////////////////////////////////  gmail //////////////////////////////////////////////////////////

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }

    private void getProfile(LoginResult loginResult) {

        GraphRequest graphRequest = GraphRequest.newMeRequest(loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
            //OnCompleted is invoked once the GraphRequest is successful
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                Bundle bFacebookData = getFacebookData(object);
            }
        });
        Bundle bundle = new Bundle();
        bundle.putString("fields", "id, name, first_name, last_name , email, gender, birthday,picture.width(200)");

        graphRequest.setParameters(bundle);
        graphRequest.executeAsync();

    }


    private Bundle getFacebookData(JSONObject object) {
        try {
            Bundle bundle = new Bundle();
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?width=200&height=150");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }

            bundle.putString("idFacebook", id);
            if (object.has("first_name"))
                bundle.putString("first_name", object.getString("first_name"));
            if (object.has("last_name"))
                bundle.putString("last_name", object.getString("last_name"));
            if (object.has("email"))
                bundle.putString("email", object.getString("email"));
            if (object.has("gender"))
                bundle.putString("gender", object.getString("gender"));
            if (object.has("birthday"))
                bundle.putString("birthday", object.getString("birthday"));
            if (object.has("location"))
                bundle.putString("location", object.getJSONObject("location").getString("name"));

            String fname = bundle.getString("first_name");
            String lName = bundle.getString("last_name");
            String email = TextUtils.isEmpty(bundle.getString("email")) ? "" : bundle.getString("email");
            if (email == null) {
                toast(this, "Hello " + fname + " we are unable to find your email!");
            }
            isUserExist(FB, fname, lName, email, id, "" + profile_pic, "", "");

            return bundle;
        } catch (JSONException e) {
            Log.e("Error", "Error parsing JSON");
        }
        return null;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cv_login_now:
                hideKeyboard(this);
                if (isValid()) {
                    if (binding.cbRememberMe.isChecked()) {
                        loginPrefsEditor.putBoolean("saveLogin", true);
                        loginPrefsEditor.putString("username", binding.etUserId.getText().toString());
                        loginPrefsEditor.putString("password", binding.etPassword.getText().toString());
                        loginPrefsEditor.commit();
                    } else {
                        loginPrefsEditor.clear();
                        loginPrefsEditor.commit();
                    }
                    userLoginDef();
                    userLogin();

                }
                break;
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                binding.tvRegister.setEnabled(false);
                break;
            case R.id.tv_forgot_password:
                startActivity(new Intent(LoginActivity.this, ForgetPasswordActivity.class));
                break;


            case R.id.iv_fb:

                // LoginManager.getInstance().setLoginBehavior(LoginBehavior.WEB_VIEW_ONLY);
                // LoginManager.getInstance().//(Arrays.asList("public_profile, email, user_birthday"));
                // List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "user_friends", "public_profile");
                // LoginManager.getInstance().logInWithReadPermissions(this, permissionNeeds);
                //  binding.fbLoginButton.performClick();
                break;

            case R.id.iv_email:
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
            case R.id.iv_logo:
                binding.etUserId.setText("918750110867");
                binding.etPassword.setText("qwerty");
                break;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setListeners();

    }

    private boolean isValid() {

        if (binding.etUserId.getText().toString().trim().isEmpty() && binding.etPassword.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etUserId.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.enter_email_id_and_password));
            return false;
        } else if (binding.etUserId.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etUserId.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.enter_email_id));
            return false;
        }/* else if (!isValidEmail(binding.etUserId.getText().toString())) {
            clearFocus();
            binding.etUserId.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_email));
            return false;
        } */ else if (binding.etPassword.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_password));
            return false;
        } /*else if (binding.etPassword.getText().toString().length() < 8) {
            clearFocus();
            binding.etPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.password_must_contain));
            return false;
        }*/
        return true;
    }

    private void clearFocus() {
        binding.etUserId.clearFocus();
        binding.etPassword.clearFocus();
    }

    private void userLoginDef() {
        if (isInternetOn(this)) {
            //final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            // if (dialog != null)
            //     dialog.show();
            LoginRequestModel loginRequestModel;
            if (true) {
                loginRequestModel = new LoginRequestModel("aryanm22897@gmail.com"/*binding.etUserId.getText().toString()*/,
                        "Arya@#567"/*binding.etPassword.getText().toString()*/, refreshedToken, DEVICE_TYPE);
            } else {
                loginRequestModel = new LoginRequestModel("noah89816@gmail.com"/*binding.etUserId.getText().toString()*/,
                        "Arya@#567"/*binding.etPassword.getText().toString()*/, refreshedToken, DEVICE_TYPE);
            }


            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<LoginResponseModel> call = apiInterface.userLoginApi(loginRequestModel);
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                    try {

                        //  if (dialog != null)
                        //       dialog.dismiss();

                        if (response.isSuccessful()) {
                            LoginResponseModel userResponseBean = response.body();
                            if (userResponseBean.isStatus()) {

                                saveDataInPrefernece(userResponseBean);
                                //  startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                //  finish();

                            } else {

                                DialogUtils.showAlertDialogWithSingleButton(LoginActivity.this, userResponseBean.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });

                            }
                        } else {
                            DialogUtils.showAlertDialogWithSingleButton(LoginActivity.this, response.code() == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

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
                public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                    //   if (dialog != null)
                    //       dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void userLogin() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            LoginRequestModel loginRequestModel = new LoginRequestModel(binding.etUserId.getText().toString(),
                    binding.etPassword.getText().toString(), refreshedToken);

            ApiInterface apiInterface = ApiClientPLAND.getClient().create(ApiInterface.class);
            Call<UserLoginResponseModel> call = apiInterface.userLoginApi2(loginRequestModel);
            call.enqueue(new Callback<UserLoginResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<UserLoginResponseModel> call, @NonNull Response<UserLoginResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful() && response.body().getStatus()) {
                            UserLoginResponseModel userResponseBean = response.body();

                            prefManager.savePreference(Constants.USER_ID, userResponseBean.getData().getId());
                            prefManager.savePreference(Constants.USER_NAME, userResponseBean.getData().getName());
                            prefManager.savePreference(Constants.USER_EMAIL, userResponseBean.getData().getEmail());

                            startActivity();
                        } else {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        if (dialog != null)
                            dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(@NonNull Call<UserLoginResponseModel> call, @NonNull Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void startActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void saveDataInPrefernece(LoginResponseModel loginResponseModel) {
        prefManager.savePreference(TOKEN_TYPE, loginResponseModel.getToken_type());
        // prefManager.savePreference(EXPIRE_IN, loginResponseModel.getExpires_in());
        prefManager.savePreference(AUTH_TOKEN, loginResponseModel.getAccess_token());
        prefManager.savePreference(LOGIN_ID, loginResponseModel.getId());
        prefManager.savePreference(FIRST_NAME, loginResponseModel.getFirst_name());
        prefManager.savePreference(LAST_NAME, loginResponseModel.getLast_name());
        prefManager.savePreference(DOB, loginResponseModel.getDob());
        prefManager.savePreference(EMAIL, loginResponseModel.getEmail());
        prefManager.savePreference(CONTACT_NO, loginResponseModel.getContact_no());
        prefManager.savePreference(REFERRAL_CODE, loginResponseModel.getReferral_code());
        prefManager.savePreference(REFERRED_CODE, loginResponseModel.getReferred_code());
        prefManager.savePreference(GENDER, loginResponseModel.getGender());
        prefManager.savePreference(PROFILE_IMAGE, loginResponseModel.getImage());
        prefManager.savePreference(STRIPE_CUST_ID, loginResponseModel.getStripe_cust_id());
        prefManager.savePreference(CUSTOMER_ORDER, loginResponseModel.getCustomerOrder());
        prefManager.savePreference(CART_ENTRY_TYPE, loginResponseModel.getCart());
    }

    private void saveSOCIALDataInPrefernece(SocialMediaResponseModel.SuccessDataBean SignUpRequestModel) {
        prefManager.savePreference(TOKEN_TYPE, SignUpRequestModel.getToken_type());
        // prefManager.savePreference(EXPIRE_IN, loginResponseModel.getExpires_in());
        prefManager.savePreference(AUTH_TOKEN, SignUpRequestModel.getAccess_token());
        prefManager.savePreference(LOGIN_ID, SignUpRequestModel.getId());
        prefManager.savePreference(FIRST_NAME, SignUpRequestModel.getFirst_name());
        prefManager.savePreference(LAST_NAME, SignUpRequestModel.getLast_name());
        prefManager.savePreference(DOB, SignUpRequestModel.getDob());
        prefManager.savePreference(EMAIL, SignUpRequestModel.getEmail());
        prefManager.savePreference(CONTACT_NO, SignUpRequestModel.getContact_no());
        prefManager.savePreference(REFERRAL_CODE, SignUpRequestModel.getReferral_code());
        prefManager.savePreference(REFERRED_CODE, SignUpRequestModel.getReferred_code());
        prefManager.savePreference(GENDER, SignUpRequestModel.getGender());
        prefManager.savePreference(PROFILE_IMAGE, SignUpRequestModel.getImage());
        prefManager.savePreference(STRIPE_CUST_ID, SignUpRequestModel.getStripe_cust_id());
        prefManager.savePreference(CUSTOMER_ORDER, SignUpRequestModel.getCustomerOrder());
        prefManager.savePreference(CART_ENTRY_TYPE, SignUpRequestModel.getCart());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case RC_SIGN_IN:
                    try {
                        // The Task returned from this call is always completed, no need to attach a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);

                        onLoggedIn(account);
                    } catch (ApiException e) {
                        // The ApiException status code indicates the detailed failure reason.
                        Log.e("signIn:failed code", "" + e.getStatusCode());
                    }
                    break;
            }
    }

    private void onLoggedIn(GoogleSignInAccount googleSignInAccount) {
        try {
            googleSignInAccount = GoogleSignIn.getLastSignedInAccount(this);
            if (googleSignInAccount != null) {
                String personName = googleSignInAccount.getDisplayName();
                String fName = googleSignInAccount.getGivenName();  //dhi
                String lName = googleSignInAccount.getFamilyName(); //kr
                String personEmail = googleSignInAccount.getEmail();
                String idToken = googleSignInAccount.getIdToken();
                String personId = googleSignInAccount.getId();
                if (googleSignInAccount.getPhotoUrl() != null) {
                    personPhoto = googleSignInAccount.getPhotoUrl();
                }

                isUserExist(GMAIL, fName, lName, personEmail, personId, "" + personPhoto, "", "");
            }
        } catch (Exception e) {
            Log.e("err", "" + e.getMessage());
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {
            //onLoggedIn(alreadyloggedAccount);
            signOutGmail();
        } else {
            Log.d("ISLogedIn", "Not logged in");
        }

    }

    /* private void revokeAccess() {  // disconnect app
         mGoogleSignInClient.revokeAccess()
                 .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         // ...
                     }
                 });
     }

     */

    private void signOutGmail() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        //   "sign out",
                    }
                });
    }

    private void userRegister(final String socialType, String fName, String lName, String personEmail, String idToken,
                              String personPhoto, String mobile, String otp) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            final SignUpRequestModel signUpRequestModel = new SignUpRequestModel(fName, lName, personEmail, null,
                    null, mobile, null, null, socialType, idToken, DEVICE_TYPE, CUSTOMER_TYPE, refreshedToken,
                    "" + personPhoto, "", otp, HelperClass.getUniqueID(this)); // otp field will be blank here.

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<SocialMediaResponseModel> call = apiInterface.saveCustomerFromSocial(signUpRequestModel);

            call.enqueue(new Callback<SocialMediaResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<SocialMediaResponseModel> call, @NonNull Response<SocialMediaResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        SocialMediaResponseModel userResponseBean = response.body();
                        if (response.isSuccessful()) {
                            if (userResponseBean.isStatus()) {
                                prefManager.savePreference(LOGIN_TYPE, socialType);
                                saveSOCIALDataInPrefernece(userResponseBean.getSuccess_data());
                                Intent i = new Intent(LoginActivity.this, MainActivity.class);
                                i.putExtra(SIGN_UP_REQU_MODEL, signUpRequestModel);
                                startActivity(i);
                                finish();
                            } else showSnackBar(binding.getRoot(), userResponseBean.getData());
                        } else if (response.code() == Unprocessable_Entity) {
                            JSONObject jObjError = new JSONObject(response.errorBody().string());
                            // jObjError.getJSONObject("error").getString("message").toString()   /// early code
                            DialogUtils.showAlertDialogWithSingleButton(LoginActivity.this, jObjError.getString("message").toString(), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

                                }

                                @Override
                                public void onNegativeClick() {

                                }
                            });
                        } else {
                            DialogUtils.showAlertDialogWithSingleButton(LoginActivity.this, getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

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
                public void onFailure(@NonNull Call<SocialMediaResponseModel> call, @NonNull Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {

            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void inputUserDetail(String socialType, String fname, String lName, String email, String id, String profile_pic) {

        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.layout_dialog_verify_user);
        TextView tv_get_otp = dialog.findViewById(R.id.tv_get_otp);
        EditText et_name = dialog.findViewById(R.id.et_name);
        EditText et_mobile = dialog.findViewById(R.id.et_mobile);
        EditText et_email = dialog.findViewById(R.id.et_email);
        EditText et_otp = dialog.findViewById(R.id.et_otp);
        et_name.setText(fname + " " + lName);
        et_email.setText(email);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialog.show();

        dialog.findViewById(R.id.btn_save).setOnClickListener(v -> {

            if (isValidEmail(et_email.getText().toString())) {
                if (!TextUtils.isEmpty(et_name.getText().toString())) {
                    if (!TextUtils.isEmpty(et_mobile.getText().toString()) && et_mobile.getText().length() > 9) {
                        if (!TextUtils.isEmpty(et_otp.getText().toString()) && et_otp.getText().length() == 4) {

                            verifyOtp(et_mobile.getText().toString().trim(), et_otp.getText().toString().trim()
                                    , dialog, socialType, fname, lName, et_email.getText().toString().trim(), id, profile_pic + "");

                        } else {
                            HelperClass.toast(this, getString(R.string.enter_valid_otp));
                        }

                    } else {
                        HelperClass.toast(this, getString(R.string.please_enter_a_valid_mobile_number));
                    }
                } else {
                    HelperClass.toast(this, getString(R.string.please_enter_first_name));
                }
            } else {
                HelperClass.toast(this, getString(R.string.please_enter_a_valid_email));
            }

        });

        dialog.findViewById(R.id.tv_get_otp).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isResendClickable)
                    if (!TextUtils.isEmpty(et_mobile.getText().toString()) && et_mobile.getText().toString().length() > 9) {
                        sendOTP(tv_get_otp, et_mobile.getText().toString());
                    } else {
                        HelperClass.toast(LoginActivity.this, getString(R.string.please_enter_a_valid_mobile_number));
                    }

            }
        });
        dialog.findViewById(R.id.iv_close_dialog).setOnClickListener(v -> dialog.dismiss());

    }

    private void verifyOtp(String contactNo, String otp, Dialog dialogx, String socialType, String fname, String lName, String email, String id, String profile_pic) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            SignUpRequestModel signUpRequestModel = new SignUpRequestModel(contactNo, otp);
            Call<SendOtpResponseModel> call = apiInterface.verifyMobileNo(signUpRequestModel);
            call.enqueue(new Callback<SendOtpResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<SendOtpResponseModel> call, @NonNull Response<SendOtpResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            SendOtpResponseModel userResponseBean = response.body();
                            if (userResponseBean.isStatus()) {
                                dialogx.dismiss();

                                if (socialType.equals(FB)) {
                                    userRegister(FB, fname, lName, email, id, "" + profile_pic, contactNo, otp);
                                } else
                                    userRegister(GMAIL, fname, lName, email, id, "" + profile_pic, contactNo, otp);

                                showSnackBar(binding.getRoot(), userResponseBean.getMessage());
                            } else {
                                // showSnackBar(binding.getRoot(), userResponseBean.getMessage());
                                toast(LoginActivity.this, userResponseBean.getMessage());
                            }
                        } else {
                            String msg = getString(R.string.msg_please_try_later);
                            if (response.code() == 500) {
                                msg = contactNo + " " + getString(R.string.wrong_number);
                            }
                            DialogUtils.showAlertDialogWithSingleButton(LoginActivity.this, msg, new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

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
                public void onFailure(@NonNull Call<SendOtpResponseModel> call, @NonNull Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void isUserExist(final String socialType, String fName, String lName, String personEmail, String idToken,
                             String personPhoto, String mobile, String otp) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            LoginRequestModel loginRequestModel = new LoginRequestModel(personEmail, "", idToken, socialType, "");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<LoginResponseModel> call = apiInterface.checkUserExist(loginRequestModel);
            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            LoginResponseModel userResponseBean = response.body();
                            if (userResponseBean.isStatus()) {
                                if (userResponseBean.getIsCustomer() == 1) {

                                    userRegister(socialType, fName, lName, userResponseBean.getCustomerData().getEmail(), idToken, "" + personPhoto,
                                            userResponseBean.getCustomerData().getContact_no(), "");
                                } else
                                    inputUserDetail(socialType, fName, lName, personEmail, idToken, "" + personPhoto);
                            } else {

                                DialogUtils.showAlertDialogWithSingleButton(LoginActivity.this, userResponseBean.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });

                            }
                        } else {
                            DialogUtils.showAlertDialogWithSingleButton(LoginActivity.this, response.code() == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

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
                public void onFailure(@NonNull Call<LoginResponseModel> call, @NonNull Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }

    }

    private void showCounter(TextView tv_get_otp) {
        CountDownTimer mCountDownTimer = new CountDownTimer(60 * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                isResendClickable = false;
                tv_get_otp.setText("" + getDurationString((int) millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                isResendClickable = true;
                tv_get_otp.setAlpha(1f);
                tv_get_otp.setText(getString(R.string.get_otp));
            }
        };
        mCountDownTimer.start();
    }

    public String getDurationString(int seconds) {
        //   int hours = seconds / 3600;
        int minutes = (seconds % 3600) / 60;
        seconds = seconds % 60;
        return twoDigitString(minutes) + " : " + twoDigitString(seconds);
    }

    private String twoDigitString(int number) {
        if (number == 0) {
            return "00";
        }
        if (number / 10 == 0) {
            return "0" + number;
        }
        return String.valueOf(number);
    }

    private void sendOTP(TextView tv_get_otp, String mobile) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<PinResponseModel> call = apiInterface.sendOTP(BASE_URL + ApiRequestUrl.SEND_OTP + mobile);
            call.enqueue(new Callback<PinResponseModel>() {
                @Override
                public void onResponse(Call<PinResponseModel> call, Response<PinResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            PinResponseModel pinResponseModel = response.body();
                            if (pinResponseModel.isStatus()) {
                                // showSnackBar(binding.getRoot(), pinResponseModel.getMessage());
                                toast(LoginActivity.this, pinResponseModel.getMessage());
                                showCounter(tv_get_otp);
                            } else {
                                toast(LoginActivity.this, pinResponseModel.getMessage());
                                // showSnackBar(binding.getRoot(), pinResponseModel.getMessage());
                            }
                        } else {
                            String msgs = getString(R.string.something_went_wrong);
                            final int httpCode = response.code();
                            if (httpCode == 500) {
                                msgs = mobile + " " + getString(R.string.wrong_number);
                            } else if (httpCode == 401) {
                                msgs = getString(R.string.season_expired);
                            }
                            DialogUtils.showAlertDialogWithSingleButton(LoginActivity.this, msgs, new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(LoginActivity.this);
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
                public void onFailure(Call<PinResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }
}


