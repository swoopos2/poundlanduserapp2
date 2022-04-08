package com.poundland.retail.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.signature.ObjectKey;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;
import com.poundland.retail.R;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.ImagePickerUtilNew;
import com.poundland.retail.appUtils.PermissionsUtil;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityMyProfileBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.responseModel.LoginResponseModel;
import com.poundland.retail.model.responseModel.UserDetailResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.io.File;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.isValidEmail;
import static com.poundland.retail.appUtils.HelperClass.isValidName;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.appUtils.ImagePickerUtilNew.pickFromCameraWithCrop;
import static com.poundland.retail.appUtils.ImagePickerUtilNew.pickFromGalleryWithCrop;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.DOB;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.FROM_PROFILE;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.GENDER;
import static com.poundland.retail.interfaces.Constants.LAST_NAME;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.MY_FILE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.PROFILE_IMAGE;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOKEN_TYPE;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.USER_NAME;

public class MyProfileActivity extends BaseActivity implements View.OnClickListener {
    private ActivityMyProfileBinding binding;
    public static File profileImages;
    private PrefManager prefManager;
    private String authToken;
    private MyProfileActivity instance = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_profile);
        init();
        setToolbar();
        setListeners();
        getProfile();

    }

    private void init() {
        instance = this;
        binding.ivEditProfile.setVisibility(View.VISIBLE);
        binding.ivChooseImage.setVisibility(View.GONE);
        binding.etDisplayName.setVisibility(View.GONE);
        binding.etEmail.setVisibility(View.GONE);
        binding.tvSave.setVisibility(View.GONE);
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.ivEditProfile.setOnClickListener(this);
        binding.ivChooseImage.setOnClickListener(this);
        binding.tvSave.setOnClickListener(this);
    }

    private void setToolbar() {
        // binding.ivBack.setImageDrawable(getResources().getDrawable(R.drawable.ic_back_black));
        // binding.title.setText(R.string.profile);
        // binding.ivEditProfile.setImageDrawable(getResources().getDrawable(R.drawable.ic_menu_edit));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_edit_profile:
                binding.ivChooseImage.setVisibility(View.VISIBLE);
                binding.etDisplayName.setVisibility(View.VISIBLE);
                binding.etDisplayLastName.setVisibility(View.VISIBLE);
                binding.etEmail.setVisibility(View.GONE);
                binding.etContactNo.setVisibility(View.VISIBLE);
                binding.tvSave.setVisibility(View.VISIBLE);

                binding.ivEditProfile.setVisibility(View.GONE);
                binding.tvDisplayName.setVisibility(View.GONE);
                binding.tvDisplayLastName.setVisibility(View.GONE);
                binding.tvEmail.setVisibility(View.VISIBLE);
                binding.tvEmail.setAlpha(.5f);
                binding.tvContactNo.setVisibility(View.GONE);

                binding.title.setText(getString(R.string.edit_profile));
                break;
            case R.id.tv_save:
                if (isValid()) {
                    updateProfile();
                }
                break;
            case R.id.iv_choose_image:
                Dexter.withContext(this).withPermissions(PermissionsUtil.CAMERA, PermissionsUtil.STORAGE).withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport multiplePermissionsReport) {

                        if (multiplePermissionsReport.areAllPermissionsGranted()) {

                            pickFromCameraWithCrop((Activity) instance, (imageFile, tag) -> {
                                profileImages = new File(imageFile + "");
                                //  binding.ivImage.setImageBitmap(BitmapFactory.decodeFile(imageFile.getAbsolutePath()));

                                Glide.with(instance).load(new File(profileImages.getPath()))
                                        .signature(new ObjectKey(String.valueOf(System.currentTimeMillis())))
                                        .apply(RequestOptions.circleCropTransform()).into(binding.ivImage);


                            }, "Picture"/*, true*/);

                        }

                        if (multiplePermissionsReport.isAnyPermissionPermanentlyDenied()) {
                            showSettingsDialog();
                        }
                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> list, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).withErrorListener(new PermissionRequestErrorListener() {
                    @Override
                    public void onError(DexterError dexterError) {
                        showSnackBar(binding.getRoot(), dexterError.toString());
                    }
                }).onSameThread().check();

                break;
        }
    }


    private void showSettingsDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(instance);
        builder.setTitle("Need Permissions");
        builder.setMessage("This app needs camera and storage permission to use this feature. You can grant them in app settings.");
        builder.setPositiveButton("GOTO SETTINGS", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                openSettings();
            }
        });
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.show();

    }

    // navigating user to app settings
    private void openSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        Uri uri = Uri.fromParts("package", getPackageName(), null);
        intent.setData(uri);
        startActivityForResult(intent, 101);
    }


    private boolean isValid() {
        if (binding.etDisplayName.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etDisplayName.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_first_name));
            return false;
        } else if (!isValidName(binding.etDisplayName.getText().toString().trim())) {
            clearFocus();
            binding.etDisplayName.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_first_name));
            return false;
        } else if (!binding.etDisplayLastName.getText().toString().trim().isEmpty() && !isValidName(binding.etDisplayLastName.getText().toString().trim())) {
            clearFocus();
            binding.etDisplayName.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_last_name));
            return false;
        } else if (binding.etEmail.getText().toString().trim().isEmpty()) {
            clearFocus();
            binding.etEmail.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_email));
            return false;
        } else if (!isValidEmail(binding.etEmail.getText().toString())) {
            clearFocus();
            binding.etEmail.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_email));
            return false;
        } else if (binding.etContactNo.getText().toString().length() < 9) {
            clearFocus();
            binding.etContactNo.requestFocus();
            showSnackBar(binding.getRoot(), getString(R.string.please_enter_a_valid_mobile_number));
            return false;
        } /*else if (profileImages == null) {
            showSnackBar(binding.getRoot(), getString(R.string.please_choose_image));
            return false;
        }*/
        return true;
    }

    private void clearFocus() {
        binding.etDisplayName.clearFocus();
        binding.etEmail.clearFocus();
        binding.etContactNo.clearFocus();
    }

    public void selectImage(final Context context, final ImagePickerUtilNew.ImagePickerListener imagePickerListener, final String tag) {
        final CharSequence[] items = {"Take a Picture", "Browse Gallery"};
        AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.MyAlertDialogStyle);
        builder.setTitle(null);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (items[item].equals("Take a Picture")) {
                    pickFromCameraWithCrop((Activity) context, imagePickerListener, tag/*, true*/);
                } else if (items[item].equals("Browse Gallery")) {
                    pickFromGalleryWithCrop((Activity) context, imagePickerListener, tag/*, true*/);
                }
            }
        });
        builder.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        ImagePickerUtilNew.onActivityResult(requestCode, resultCode, data);
    }

    private void getProfile() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<UserDetailResponseModel> call = apiInterface.getUserDetail(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""));
            call.enqueue(new Callback<UserDetailResponseModel>() {

                @Override
                public void onResponse(@NonNull Call<UserDetailResponseModel> call, @NonNull Response<UserDetailResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            UserDetailResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {
                                if (responseModel.getUser() != null) {
                                    prefManager.savePreference(PROFILE_IMAGE, responseModel.getUser().getProfile_image());
                                    if (responseModel.getUser().getProfile_image() != null && responseModel.getUser().getProfile_image().contains("uploaded/profile/")) {
// TODO :REMOVE DOUBLE QUOTE ITS FOR DEMO ONLY
                                        Glide.with(instance).load("ApiRequestUrl.BASE_URL + responseModel.getUser().getProfile_image()")
                                                .placeholder(R.drawable.app_logo_poundland)
                                                .apply(RequestOptions.circleCropTransform()).into(binding.ivImage);

                                    } else {

                                        Glide.with(instance).load("responseModel.getUser().getProfile_image()")
                                                .apply(RequestOptions.circleCropTransform()).into(binding.ivImage);


                                    }
                                    binding.etDisplayName.setText(responseModel.getUser().getFirst_name());
                                    binding.etDisplayLastName.setText(responseModel.getUser().getLast_name());
                                    binding.etEmail.setText(responseModel.getUser().getEmail());
                                    binding.etContactNo.setText(responseModel.getUser().getContact_no());

                                    binding.tvDisplayName.setText(responseModel.getUser().getFirst_name());
                                    binding.tvDisplayLastName.setText(responseModel.getUser().getLast_name());
                                    binding.tvEmail.setText(responseModel.getUser().getEmail());
                                    binding.tvContactNo.setText(responseModel.getUser().getContact_no());


                                    //TODO : FOR DEMO ONLY REMOVE IT FURTHER
                                    binding.tvDisplayName.setText(PrefManager.getInstance(instance).getPreference(USER_NAME));
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
                public void onFailure(@NonNull Call<UserDetailResponseModel> call, @NonNull Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
    }

    private void updateProfile() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            Call<LoginResponseModel> call;
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            MultipartBody.Part body = null;
            if (profileImages == null) {
                body = null;
            } else {
                RequestBody requestFile = RequestBody.create(MediaType.parse("image/*"), profileImages);
                body = MultipartBody.Part.createFormData("image", profileImages.getName(), requestFile);
            }

            RequestBody bodyFirstName = RequestBody.create(MediaType.parse("text/plain"), binding.etDisplayName.getText().toString());
            RequestBody bodyLasttName = RequestBody.create(MediaType.parse("text/plain"), binding.etDisplayLastName.getText().toString());
            RequestBody bodyEmail = RequestBody.create(MediaType.parse("text/plain"), binding.etEmail.getText().toString());
            RequestBody bodyPhoneNumber = RequestBody.create(MediaType.parse("text/plain"), binding.etContactNo.getText().toString());
            RequestBody otp = RequestBody.create(MediaType.parse("text/plain"), "");

            call = apiInterface.updateProfile(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""),
                    body, bodyFirstName, bodyLasttName, bodyEmail, bodyPhoneNumber, otp);

            call.enqueue(new Callback<LoginResponseModel>() {
                @Override
                public void onResponse(@NonNull Call<LoginResponseModel> call, @NonNull Response<LoginResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            final LoginResponseModel getProfileModel = response.body();
                            if (getProfileModel.isStatus()) {
                                binding.title.setText(getString(R.string.profile));
                                if (getProfileModel.isIs_otp()) {

                                    Intent i = new Intent(MyProfileActivity.this, OtpVerifyActivity.class);
                                    i.putExtra(FROM_WHERE, FROM_PROFILE);
                                    i.putExtra(FIRST_NAME, binding.etDisplayName.getText().toString());
                                    i.putExtra(LAST_NAME, binding.etDisplayLastName.getText().toString());
                                    i.putExtra(EMAIL, binding.etEmail.getText().toString());
                                    i.putExtra(CONTACT_NO, binding.etContactNo.getText().toString());
                                    i.putExtra(MY_FILE, profileImages);
                                    startActivity(i);
                                    finish();

                                } else {
                                    DialogUtils.showAlertDialogWithSingleButton(MyProfileActivity.this, getProfileModel.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            saveDataInPrefernece(getProfileModel);
                                            binding.ivChooseImage.setVisibility(View.GONE);
                                            binding.etDisplayName.setVisibility(View.GONE);
                                            binding.etDisplayLastName.setVisibility(View.GONE);
                                            binding.etEmail.setVisibility(View.GONE);
                                            binding.etContactNo.setVisibility(View.GONE);
                                            binding.tvSave.setVisibility(View.GONE);

                                            binding.ivEditProfile.setVisibility(View.VISIBLE);
                                            binding.tvDisplayName.setVisibility(View.VISIBLE);
                                            binding.tvDisplayLastName.setVisibility(View.VISIBLE);
                                            binding.tvEmail.setVisibility(View.VISIBLE);
                                            binding.tvEmail.setAlpha(1f);
                                            binding.tvContactNo.setVisibility(View.VISIBLE);

                                            binding.etDisplayName.setText(getProfileModel.getFirst_name());
                                            binding.etDisplayLastName.setText(getProfileModel.getLast_name());
                                            binding.etEmail.setText(getProfileModel.getEmail());
                                            binding.etContactNo.setText(getProfileModel.getContact_no());

                                            binding.tvDisplayName.setText(getProfileModel.getFirst_name());
                                            binding.tvDisplayLastName.setText(getProfileModel.getLast_name());
                                            binding.tvEmail.setText(getProfileModel.getEmail());
                                            binding.tvContactNo.setText(getProfileModel.getContact_no());

                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                                }

                            } else {
                                showSnackBar(binding.getRoot(), getProfileModel.getMessage());
                                //{"status":false,"message":"The given data was missing.","data":{"contact_no":["The contact no has already been taken."]}}

                            }
                        } else {
                            final int httpCode = response.code();

                            String msg = getString(R.string.msg_please_try_later);
                            if (response.code() == 500) {
                                msg = binding.etContactNo.getText().toString().trim() + " " + getString(R.string.wrong_number);
                            } else if (response.code() == 401) {
                                msg = getString(R.string.season_expired);
                            }
                            DialogUtils.showAlertDialogWithSingleButton(instance, msg, new OnDialogClickListener() {
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
        prefManager.savePreference(GENDER, loginResponseModel.getGender());
        prefManager.savePreference(PROFILE_IMAGE, loginResponseModel.getImage());
        prefManager.savePreference(CART_ENTRY_TYPE, loginResponseModel.getCart());
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

                if (data.equals("DISC_OFFER_NOTIFY")) {
                    DialogUtils.offerNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent topStore = new Intent(this, NearByDealsActivity.class);
                        topStore.putExtra(TOP_OFFER_TITLE, getString(R.string.nearby_deals));
                        startActivity(topStore);
                    });
                } else if (!TextUtils.isEmpty(data)) {
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