package com.poundland.retail.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginBehavior;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityRegisterBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.responseModel.SocialMediaResponseModel;
import com.poundland.retail.zzznewPoundland.ApiClientPLAND;
import com.poundland.retail.zzznewPoundland.dialog.MultiSelectionSpinnerDialog;
import com.poundland.retail.zzznewPoundland.model.RegistrationRequestModel;
import com.poundland.retail.zzznewPoundland.model.RegistrationResponseModel;
import com.poundland.retail.zzznewPoundland.widget.MultiSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.getFcmToken;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.isValidEmail;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_ORDER;
import static com.poundland.retail.interfaces.Constants.DOB;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FEMALE;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.GENDER;
import static com.poundland.retail.interfaces.Constants.LAST_NAME;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.MALE;
import static com.poundland.retail.interfaces.Constants.NONE;
import static com.poundland.retail.interfaces.Constants.PROFILE_IMAGE;
import static com.poundland.retail.interfaces.Constants.REFERRAL_CODE;
import static com.poundland.retail.interfaces.Constants.REFERRED_CODE;
import static com.poundland.retail.interfaces.Constants.STRIPE_CUST_ID;
import static com.poundland.retail.interfaces.Constants.TOKEN_TYPE;
import static com.poundland.retail.interfaces.Constants.YYYY_MM_DD;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener, MultiSelectionSpinnerDialog.OnMultiSpinnerSelectionListener {
    private ActivityRegisterBinding binding;
    private PrefManager prefManager;
    private PopupWindow popupWindow;
    private String currency;
    private boolean isPopup;
    private static final int RC_SIGN_IN = 2212;
    private GoogleSignInClient mGoogleSignInClient;
    private String gender = "";
    private DatePickerDialog datePickerDialog;
    private Calendar customDate;
    private int age;
    private SimpleDateFormat sendDateFormat;
    private String startDate, endDate;
    private boolean isResendClickable = true;
    private AccessToken accessToken;
    private CallbackManager callbackManager;
    private Uri personPhoto;

    private KProgressHUD dialog;
    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            customDate.set(year, monthOfYear, dayOfMonth);
            startDate = sendDateFormat.format(customDate.getTime());
            endDate = sendDateFormat.format(customDate.getTime());
            binding.tvDob.setText(startDate);
            age = HelperClass.calculateAge(customDate.getTimeInMillis());
            Log.e("from time:", startDate + " to time" + endDate);
        }
    };
    private PopupWindow mPopupWindow;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        dialog = new KProgressHUD(this);
        init();


        getFcmToken(this);
        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {

                if (task.isSuccessful()) {
                    token = task.getResult();
                }
            }

        });

        MultiSpinner multiSpinner = binding.getRoot().findViewById(R.id.et_fab_category);
        List<String> category = new ArrayList<>();
        category.add("Chocolate Blocks & Bags");
        category.add("Chocolate Bars & Multipacks");
        category.add("Boxed Chocolate");
        category.add("Hair Accessories");
        category.add("Hair Styling");
        category.add("Shampoo");
        category.add("Conditioner");
        category.add("Baking & Cooking");
        category.add("Food Wrap & Storage");
        category.add("Tableware");
        multiSpinner.setAdapterWithOutImage(this, category, this);
        multiSpinner.initMultiSpinner(this, multiSpinner);
    }

    private void init() {
        sendDateFormat = new SimpleDateFormat(YYYY_MM_DD, Locale.getDefault());
        customDate = Calendar.getInstance();
        prefManager = PrefManager.getInstance(this);
        binding.tvLogin.setOnClickListener(this);
        binding.tvDob.setOnClickListener(this);
        binding.cvRegisterNow.setOnClickListener(this);
        binding.ivEmail.setOnClickListener(this);
        binding.ivFb.setOnClickListener(this);
        binding.tvTc.setOnClickListener(this);
        binding.ivInfo.setOnClickListener(this);
        binding.radioGroup.setOnCheckedChangeListener(this);


        callbackManager = CallbackManager.Factory.create();
        LoginManager.getInstance().registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                accessToken = loginResult.getAccessToken();
                //   getProfile(loginResult);  // App code
            }

            @Override
            public void onCancel() {
                // App code
                Toast.makeText(RegisterActivity.this, "cancel", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
                Toast.makeText(RegisterActivity.this, "failure", Toast.LENGTH_SHORT).show();
            }
        });


        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void showDatePickerDialog(Calendar mCal) {
        //////////////////////////////////////////////////   if we hide, it will set today date
        mCal.set(Calendar.YEAR, 1982);
        mCal.set(Calendar.MONTH, 2);
        mCal.set(Calendar.DAY_OF_MONTH, 22);
        /////////////////////////////////////////////////////

        datePickerDialog = new DatePickerDialog(this, dateSetListener,
                mCal.get(Calendar.YEAR), mCal.get(Calendar.MONTH), mCal.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
//            datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());  /// for the date next to today date
        datePickerDialog.show();
    }


    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_male:
                gender = MALE;
                break;
            case R.id.rb_female:
                gender = FEMALE;
                break;
            case R.id.rb_not_given:
                gender = NONE;
                break;
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_login:
                finish();
                break;
            case R.id.tv_dob:
                showDatePickerDialog(customDate);
                break;
            case R.id.cv_register_now:

                if (isValid())
                    register();
                //  userRegisterManualy();

                break;
            case R.id.iv_email:
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
                break;
            case R.id.iv_fb:
                LoginManager.getInstance().setLoginBehavior(LoginBehavior.WEB_VIEW_ONLY);
                // LoginManager.getInstance().//(Arrays.asList("public_profile, email, user_birthday"));
                List<String> permissionNeeds = Arrays.asList("user_photos", "email", "user_birthday", "user_friends", "public_profile");
                LoginManager.getInstance().logInWithReadPermissions(this, permissionNeeds);
                break;
            case R.id.tv_tc:

                break;
            case R.id.iv_info:
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View customView = inflater.inflate(R.layout.layout_window_pop_up, null);
                mPopupWindow = new PopupWindow(customView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                mPopupWindow.setElevation(5.0f);
                mPopupWindow.setOutsideTouchable(true);
                mPopupWindow.showAtLocation(binding.rlMain, Gravity.CENTER, 0, 0);
                //  }
                break;
        }
    }

    private boolean isValid() {
        if (binding.etFirstName.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etFirstName.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_first_name));
            return false;
        } else if (!HelperClass.isValidName(binding.etFirstName.getText().toString())) {
            clearFocus();
            binding.etFirstName.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_first_name));
            return false;
        } else if (!binding.etLastName.getText().toString().isEmpty() && !HelperClass.isValidName(binding.etLastName.getText().toString())) {
            clearFocus();
            binding.etLastName.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_last_name));
            return false;
        } /*else if (binding.etEmail.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etEmail.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.enter_email_id));
            return false;
        } else if (!isValidEmail(binding.etEmail.getText().toString())) {
            clearFocus();
            binding.etEmail.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_email));
            return false;
        } */else if (binding.etMobile.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etMobile.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_mobile_number));
            return false;
        } else if (binding.etMobile.getText().toString().length() < 9) {
            clearFocus();
            binding.etMobile.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_mobile_number));
            return false;
        } else if (binding.etPassword.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etPassword.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_password));
            return false;
        }else if (binding.tvDob.getText().toString().equalsIgnoreCase(getString(R.string.dob))) {
            clearFocus();
            binding.tvDob.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_select_dob));
            return false;
        } /*else if (age < 16) {
            clearFocus();
            binding.tvDob.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.minimum_age_error));
            return false;
        } */else if (gender.equals("")) {
            clearFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_select_gender));
            return false;
        } else if (!binding.cbTc.isChecked()) {
            clearFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_select_tc));
            return false;
        }
        return true;
    }

    private void clearFocus() {
        binding.etFirstName.clearFocus();
        binding.etLastName.clearFocus();
        binding.etPassword.clearFocus();
        binding.etEmail.clearFocus();
        binding.etMobile.clearFocus();
        binding.tvDob.clearFocus();
    }


    private void register() {
        if (isInternetOn(this)) {
            if (dialog != null)
                dialog.show();
            RegistrationRequestModel requestModel = new RegistrationRequestModel();
            requestModel.setFirstName(binding.etFirstName.getText().toString());
            requestModel.setLastName(binding.etLastName.getText().toString());
            requestModel.setContact(binding.etMobile.getText().toString());
            requestModel.setPassword(binding.etPassword.getText().toString());
            requestModel.setDob(binding.tvDob.getText().toString());
            switch (binding.radioGroup.getCheckedRadioButtonId()) {
                case R.id.rb_male:
                    requestModel.setGender("M");
                    break;
                case R.id.rb_female:
                    requestModel.setGender("F");
                    break;
                case R.id.rb_not_given:
                    requestModel.setGender("N");
                    break;
            }

            requestModel.setFireBaseId(/*mViewModel.getPref().getPreference(Constants.FIREBASE_TOKEN)*/"");

            List<RegistrationRequestModel> request = new ArrayList<>();
            request.add(requestModel);

            ApiInterface apiInterface = ApiClientPLAND.getClient().create(ApiInterface.class);
            Call<RegistrationResponseModel> call = apiInterface.userRegistration(request);
            call.enqueue(new Callback<RegistrationResponseModel>() {
                @Override
                public void onResponse(Call<RegistrationResponseModel> call, Response<RegistrationResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful() && response.body().getStatus()) {
                            startActivity();
                        } else {
                            Toast.makeText(getApplicationContext(), "Oops Something went wrong", Toast.LENGTH_SHORT).show();
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        if (dialog != null)
                            dialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<RegistrationResponseModel> call, Throwable t) {
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
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        finish();
    }


    /* private void userRegisterManualy() {


         if (isInternetOn(this)) {
             final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
             if (dialog != null)
                 dialog.show();
             final SignUpRequestModel signUpRequestModel = new SignUpRequestModel(
                     binding.etFirstName.getText().toString(),
                     binding.etLastName.getText().toString(),
                     binding.etEmail.getText().toString(),
                     binding.etPassword.getText().toString(),
                     binding.tvDob.getText().toString(),
                     binding.etMobile.getText().toString(),
                     binding.etRefferalCode.getText().toString(),
                     gender,
                     "",
                     "",
                     DEVICE_TYPE,
                     CUSTOMER_TYPE,
                     token,
                     "", "", "", HelperClass.getUniqueID(this)); // otp field will be blank here.


             ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
             Call<SendOtpResponseModel> call = apiInterface.OtpApi(signUpRequestModel);

             call.enqueue(new Callback<SendOtpResponseModel>() {
                 @Override
                 public void onResponse(@NonNull Call<SendOtpResponseModel> call, @NonNull Response<SendOtpResponseModel> response) {
                     try {
                         if (dialog != null)
                             dialog.dismiss();
                         SendOtpResponseModel userResponseBean = response.body();
                         if (response.isSuccessful()) {
                             //SendOtpResponseModel userResponseBean = response.body();
                             if (userResponseBean.isStatus()) {
                                 Intent i = new Intent(RegisterActivity.this, OtpVerifyActivity.class);
                                 i.putExtra(FROM_WHERE, FROM_REGISTER);
                                 i.putExtra(SIGN_UP_REQU_MODEL, signUpRequestModel);
                                 startActivity(i);
                             } else showSnackBar(binding.getRoot(), userResponseBean.getMessage());
                         } else if (response.code() == Unprocessable_Entity) {
                             JSONObject jObjError = new JSONObject(response.errorBody().string());
                             // Toast.makeText(RegisterActivity.this, jObjError.getJSONObject("error").getString("message"), Toast.LENGTH_LONG).show();
                             DialogUtils.showAlertDialogWithSingleButton(RegisterActivity.this, jObjError.get("message").toString(), new OnDialogClickListener() {
                                 @Override
                                 public void onPositiveClick() {

                                 }

                                 @Override
                                 public void onNegativeClick() {

                                 }
                             });

                         } else {
                             DialogUtils.showAlertDialogWithSingleButton(RegisterActivity.this, response.code() == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
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

     /////////////////////////////////////////////////////////////////////////////////
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
             // String email = bundle.getString("email");
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

                                     userRegisterSocial(socialType, fName, lName, userResponseBean.getCustomerData().getEmail(), idToken, "" + personPhoto,
                                             userResponseBean.getCustomerData().getContact_no(), "");
                                 } else
                                     inputUserDetail(socialType, fName, lName, personEmail, idToken, "" + personPhoto);
                             } else {

                                 DialogUtils.showAlertDialogWithSingleButton(RegisterActivity.this, userResponseBean.getMessage(), new OnDialogClickListener() {
                                     @Override
                                     public void onPositiveClick() {

                                     }

                                     @Override
                                     public void onNegativeClick() {

                                     }
                                 });

                             }
                         } else {
                             DialogUtils.showAlertDialogWithSingleButton(RegisterActivity.this, response.code() == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
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
                         HelperClass.toast(RegisterActivity.this, getString(R.string.please_enter_a_valid_mobile_number));
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
                                     userRegisterSocial(FB, fname, lName, email, id, "" + profile_pic, contactNo, otp);
                                 } else
                                     userRegisterSocial(GMAIL, fname, lName, email, id, "" + profile_pic, contactNo, otp);

                                 showSnackBar(binding.getRoot(), userResponseBean.getMessage());
                             } else {
                                 // showSnackBar(binding.getRoot(), userResponseBean.getMessage());
                                 toast(RegisterActivity.this, userResponseBean.getMessage());
                             }
                         } else {
                             String msg = getString(R.string.msg_please_try_later);
                             if (response.code() == 500) {
                                 msg = contactNo + " " + getString(R.string.wrong_number);
                             }
                             DialogUtils.showAlertDialogWithSingleButton(RegisterActivity.this, msg, new OnDialogClickListener() {
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
                                 toast(RegisterActivity.this, pinResponseModel.getMessage());
                                 showCounter(tv_get_otp);
                             } else {
                                 toast(RegisterActivity.this, pinResponseModel.getMessage());
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
                             DialogUtils.showAlertDialogWithSingleButton(RegisterActivity.this, msgs, new OnDialogClickListener() {
                                 @Override
                                 public void onPositiveClick() {
                                     if (httpCode == 401) {
                                         logOut(RegisterActivity.this);
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

     private void userRegisterSocial(final String socialType, String fName, String lName, String personEmail, String idToken,
                                     String personPhoto, String mobile, String otp) {
         if (isInternetOn(this)) {
             final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
             if (dialog != null)
                 dialog.show();
             final SignUpRequestModel signUpRequestModel = new SignUpRequestModel(
                     fName,
                     lName,
                     personEmail,
                     null,
                     null,
                     mobile,
                     null,
                     null,
                     socialType,
                     idToken,
                     DEVICE_TYPE,
                     CUSTOMER_TYPE,
                     token,
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
                                 Intent i = new Intent(RegisterActivity.this, MainActivity.class);
                                 i.putExtra(SIGN_UP_REQU_MODEL, signUpRequestModel);
                                 startActivity(i);
                                 finish();
                             } else showSnackBar(binding.getRoot(), userResponseBean.getData());
                         } else if (response.code() == Unprocessable_Entity) {
                             JSONObject jObjError = new JSONObject(response.errorBody().string());
                             DialogUtils.showAlertDialogWithSingleButton(RegisterActivity.this, jObjError.getJSONObject("error").getString("message").toString(), new OnDialogClickListener() {
                                 @Override
                                 public void onPositiveClick() {

                                 }

                                 @Override
                                 public void onNegativeClick() {

                                 }
                             });

                         } else {
                             DialogUtils.showAlertDialogWithSingleButton(RegisterActivity.this, getString(R.string.something_went_wrong), new OnDialogClickListener() {
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
 */
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

   /* private void showCounter(TextView tv_get_otp) {
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
*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK)
            switch (requestCode) {
                case RC_SIGN_IN:
                    try {
                        // The Task returned from this call is always completed, no need to attach a listener.
                        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
                        GoogleSignInAccount account = task.getResult(ApiException.class);
                        onLoggedIn(account);
                    } catch (ApiException e) {
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
                //   isUserExist(GMAIL, fName, lName, personEmail, personId, "" + personPhoto, "", "");
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

    private void signOutGmail() {
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        //  Toast.makeText(LoginActivity.this, "sign out", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    public void OnMultiSpinnerItemSelected(List<String> chosenItems) {

    }
}


