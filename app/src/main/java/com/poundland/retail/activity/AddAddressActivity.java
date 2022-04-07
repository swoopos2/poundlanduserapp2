package com.poundland.retail.activity;

import android.Manifest;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityAddAddressBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.NotificationDeleteRequestModel;
import com.poundland.retail.model.requestModel.SaveLocationRequestModel;
import com.poundland.retail.model.responseModel.AddressResponseModel;
import com.poundland.retail.model.responseModel.GetUserLocationResponseModel;
import com.poundland.retail.model.responseModel.SaveLocationResponseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.getAddress;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ID;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.EDIT_ADDRESS_REQU_MODEL;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.FROM_EDIT_ADDRESS;
import static com.poundland.retail.interfaces.Constants.FROM_MY_CART;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.HOME;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.LOOKUP_ACTIVITY_CALLBACK_DATA;
import static com.poundland.retail.interfaces.Constants.OPEN_LOOKUP_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OTHERS;
import static com.poundland.retail.interfaces.Constants.POSTAL_CODE;
import static com.poundland.retail.interfaces.Constants.WORK;

public class AddAddressActivity extends FragmentActivity implements GoogleMap.OnMarkerClickListener, OnMapReadyCallback, View.OnClickListener {
    private final static int PLACE_PICKER_REQUEST = 999;
    private ActivityAddAddressBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private PlacesClient placesClient;
    private GoogleMap mMap;
    private double lat = 52.57592;
    private double longi = -2.138813;
    float zoomLevel = 16.0f; //This goes up to 21
    private AddAddressActivity instance = null;
    private String city = "";
    private String postal_code;
    private String addressType = OTHERS;
    private LatLng latLng;
    private AddressResponseModel.AddressessBean addressesModel;
    private String id, name, mob, area, flat, landmark, country;
    private String fromWhere = "";
    private FusedLocationProviderClient fusedLocationClient;
    private ListPopupWindow popupWindow;
    private boolean isPopup;
    private List<GetUserLocationResponseModel.DataBean> lookUpList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_address);
        init();
        setListeners();
        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

    }

    private void init() {
        instance = this;
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);
        lookUpList = new ArrayList<>();
        fromWhere = getIntent().getStringExtra(FROM_WHERE);

        if (fromWhere.equals(FROM_EDIT_ADDRESS)) {
            addressesModel = getIntent().getExtras().getParcelable(EDIT_ADDRESS_REQU_MODEL);
            binding.title.setText(getString(R.string.edit_address));
        } else {
            binding.title.setText(getString(R.string.add_address));
        }

        if (addressesModel != null) {
            id = String.valueOf(addressesModel.getId());
            name = TextUtils.isEmpty(addressesModel.getName()) ? "" : addressesModel.getName();
            mob = TextUtils.isEmpty(addressesModel.getMobile()) ? "" : addressesModel.getMobile();
            postal_code = TextUtils.isEmpty(addressesModel.getPincode()) ? "" : addressesModel.getPincode();
            area = TextUtils.isEmpty(addressesModel.getArea()) ? "" : addressesModel.getArea();
            flat = TextUtils.isEmpty(addressesModel.getFlat()) ? "" : addressesModel.getFlat();
            landmark = TextUtils.isEmpty(addressesModel.getLandmark()) ? "" : addressesModel.getLandmark();
            city = TextUtils.isEmpty(addressesModel.getCity()) ? "" : addressesModel.getCity();
            country = TextUtils.isEmpty(addressesModel.getCountry()) ? "" : addressesModel.getCountry();
            lat = Double.parseDouble(addressesModel.getLatitude());
            longi = Double.parseDouble(addressesModel.getLongitude());
            addressType = TextUtils.isEmpty(addressesModel.getType() )? OTHERS : addressesModel.getType();
            latLng = new LatLng(lat, longi);

            binding.etSearchNowHeader.setText(area);
            binding.etAddressLineOne.setText(area);
            binding.etAddressLineTwo.setText(landmark);
            binding.etAddressLineThree.setText(city);
            binding.etPostCode.setText(postal_code);

            if (addressType.equals(HOME)) {
                binding.rlHome.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rect_border_app_color_drawable));
                binding.rlWork.setBackground(null);
                binding.rlOther.setBackground(null);
            } else if (addressType.equals(WORK)) {
                binding.rlWork.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rect_border_app_color_drawable));
                binding.rlHome.setBackground(null);
                binding.rlOther.setBackground(null);
            } else {
                binding.rlOther.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rect_border_app_color_drawable));
                binding.rlHome.setBackground(null);
                binding.rlWork.setBackground(null);
            }


        }else {
            binding.rlHome.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rect_border_app_color_drawable));
            binding.rlWork.setBackground(null);
            binding.rlOther.setBackground(null);
            addressType = HOME;
        }

        if (!Places.isInitialized())
            Places.initialize(this, getString(R.string.api_key_place));
        // Create a new Places client instance
        placesClient = Places.createClient(this);
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");

        fusedLocationClient.getLastLocation().addOnSuccessListener(this, new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if (location != null) {
                    if (location != null) {
                        //  setAddress(location.getLatitude(), location.getLongitude());
                        latLng = new LatLng(location.getLatitude(), location.getLongitude());
                        mMap.addMarker(new MarkerOptions().position(latLng)
                                .title("Marker in!").anchor(0.5f, 0.5f));
                        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
                    } else showSnackBar(binding.etSearchNowHeader, "Add Place");
                }
            }
        });
    }

    private void setAddress(double latitude, double longitude) {
        String[] addresses = getAddress(instance, latitude, longitude);
        if (addresses.length > 0) {
            binding.etSearchNowHeader.setText(addresses[0]);
            area = addresses[0];
            postal_code = addresses[1];
            Log.e("Address", postal_code + area);
        }
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);

        binding.rlHome.setOnClickListener(this);
        binding.rlWork.setOnClickListener(this);
        binding.rlOther.setOnClickListener(this);

        binding.etSearchNowHeader.setOnClickListener(this);
        binding.rlSave.setOnClickListener(this);
        binding.rlCancel.setOnClickListener(this);
        binding.tvLookUp.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
//        binding.ivNotification.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                Intent intent = new Intent();
                setResult(RESULT_CANCELED, intent);
                finish();
                break;

            case R.id.et_search_now_header:
                Dexter.withContext(this)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {

                                Intent loc = new Intent(instance, LookUpActivity.class);
                                loc.putExtra(FROM_WHERE, getString(R.string.category_detail));
                                startActivityForResult(loc, OPEN_LOOKUP_ACTIVITY);

                            }

                            @Override
                            public void onPermissionDenied(PermissionDeniedResponse response) {
                                // check for permanent denial of permission
                                if (response.isPermanentlyDenied()) {
                                    // navigate user to app settings
                                }
                            }

                            @Override
                            public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                                token.continuePermissionRequest();
                            }
                        })
                        .withErrorListener(new PermissionRequestErrorListener() {
                            @Override
                            public void onError(DexterError dexterError) {
                                Toast.makeText(getApplicationContext(), "Error occurred! ", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onSameThread()
                        .check();
                break;

            case R.id.rl_home:
                binding.rlHome.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rect_border_app_color_drawable));
                binding.rlWork.setBackground(null);
                binding.rlOther.setBackground(null);
                addressType = HOME;
                break;

            case R.id.rl_work:
                binding.rlHome.setBackground(null);
                binding.rlWork.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rect_border_app_color_drawable));
                binding.rlOther.setBackground(null);
                addressType = WORK;
                break;

            case R.id.rl_other:
                binding.rlHome.setBackground(null);
                binding.rlWork.setBackground(null);
                binding.rlOther.setBackground(ContextCompat.getDrawable(this, R.drawable.rounded_rect_border_app_color_drawable));
                addressType = OTHERS;
                break;

            case R.id.rl_save:
                if (latLng != null) {
                    city = binding.etAddressLineThree.getText().toString().trim().equals("") ? city : binding.etAddressLineThree.getText().toString().trim();
                    if (fromWhere.equals(FROM_EDIT_ADDRESS)) {
                        updateAddress();
                    } else {
                        if (!TextUtils.isEmpty(binding.etAddressLineOne.getText().toString())){
                            saveAddress();
                        }else showSnackBar(binding.etAddressLineOne, getString(R.string.search_post_code));

                    }
                } else showSnackBar(binding.tvHome, getString(R.string.current_location));
                break;
            case R.id.rl_cancel:
                finish();
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

            case R.id.tv_look_up:
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    if (!TextUtils.isEmpty(binding.etPostCode.getText().toString().trim())) {
                        getUserLocation(binding.etPostCode.getText().toString().trim());
                    } else {
                        showSnackBar(binding.etAddressLineOne, getString(R.string.enter_post_code));
                    }
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent();
        setResult(RESULT_CANCELED, intent);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PLACE_PICKER_REQUEST) {
            /*if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                latLng = place.getLatLng();
                if (latLng != null) {
                    lat = latLng.latitude;
                    longi = latLng.longitude;

                    mMap.addMarker(new MarkerOptions().position(latLng)
                            .title("Marker in U.K.").anchor(0.5f, 0.5f));
                    mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
                    area = place.getAddress();
//                    postCode=place.getPlusCode();
//                    postal_code= "-"+postCode.getGlobalCode()+""+postCode.getCompoundCode();
                }
                binding.etSearchNowHeader.setText("" + place.getAddress());
                Log.e("Place: ", "" + "\n" + place.getAddressComponents() + postal_code);
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.e("Place Err", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                Log.e("Place Err", "RESULT_CANCELED");
            }*/
        } else if (requestCode == OPEN_LOOKUP_ACTIVITY && resultCode == RESULT_OK && data != null) {
            lat = data.getDoubleExtra(LATITUDE, lat);
            longi = data.getDoubleExtra(LONGITUDE, longi);
            String lookUpdata = data.getStringExtra(LOOKUP_ACTIVITY_CALLBACK_DATA);
            postal_code = data.getStringExtra(POSTAL_CODE);
            area = lookUpdata;
            city = binding.etAddressLineThree.getText().toString().trim().equals("") ? city : binding.etAddressLineThree.getText().toString().trim();
            binding.etSearchNowHeader.setText(area);
            binding.etAddressLineOne.setText(area);

            latLng = new LatLng(lat, longi);
            if (latLng != null) {
                lat = latLng.latitude;
                longi = latLng.longitude;
               /* mMap.addMarker(new MarkerOptions().position(latLng)
                        .title("Marker in U.K.").anchor(0.5f, 0.5f));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));*/
            }
        }
    }

    private void saveAddress() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            SaveLocationRequestModel requestModel = new SaveLocationRequestModel(prefManager.getPreference(FIRST_NAME, ""),
                    prefManager.getPreference(CONTACT_NO, ""),
                    postal_code, area, "",
                    binding.etAddressLineTwo.getText().toString().trim(),
                    city, "", String.valueOf(lat), String.valueOf(longi), addressType);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<SaveLocationResponseModel> call = apiInterface.saveUserLocation(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<SaveLocationResponseModel>() {
                @Override
                public void onResponse(Call<SaveLocationResponseModel> call, Response<SaveLocationResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            SaveLocationResponseModel responseModel = response.body();

                            if (responseModel != null) {
                                if (responseModel.isStatus()) {
                                    DialogUtils.dialogGeneratePinSuccess(instance, responseModel.getMessage(), getString(R.string.sucess), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {

                                            if (fromWhere != null && fromWhere.equals(FROM_MY_CART)) {

                                                Intent intent = new Intent();
                                                intent.putExtra(ADDRESS_ACTIVITY_CALLBACK, responseModel.getAddress());
                                                intent.putExtra(ADDRESS_ID, responseModel.getAddrId());
                                                setResult(RESULT_OK, intent);
                                                finish();

                                            } else {
                                                Intent intent = new Intent();
                                                intent.putExtra(ADDRESS_ACTIVITY_CALLBACK, responseModel.getAddress());
                                                intent.putExtra(ADDRESS_ID, responseModel.getAddrId());
                                                setResult(RESULT_OK, intent);
                                                finish();
                                            }
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                                } else {
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());
                                }


                            } else {
                                showSnackBar(binding.getRoot(), getString(R.string.something_went_wrong));
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
                public void onFailure(Call<SaveLocationResponseModel> call, Throwable t) {
                    // binding.swipeRefresh.setRefreshing(false);
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
            // binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void updateAddress() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            SaveLocationRequestModel requestModel = new SaveLocationRequestModel(id, name, mob,
                    postal_code, area, flat, binding.etAddressLineTwo.getText().toString().trim(), city, country, String.valueOf(lat), String.valueOf(longi), addressType);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<SaveLocationResponseModel> call = apiInterface.updateUserLocation(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<SaveLocationResponseModel>() {
                @Override
                public void onResponse(Call<SaveLocationResponseModel> call, Response<SaveLocationResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            SaveLocationResponseModel responseModel = response.body();

                            if (responseModel != null) {
                                if (responseModel.isStatus()) {
                                    DialogUtils.dialogGeneratePinSuccess(instance, responseModel.getMessage(), getString(R.string.sucess), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            Intent intent = new Intent();
                                            setResult(RESULT_OK, intent);
                                            finish();
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });

                                } else {
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());
                                }


                            } else {
                                showSnackBar(binding.getRoot(), getString(R.string.something_went_wrong));
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
                public void onFailure(Call<SaveLocationResponseModel> call, Throwable t) {
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
                                if (totalCartResponseModel.getTotal_carts() > 0)
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
                                binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
                            } else {
                                showSnackBar(binding.getRoot(), totalCartResponseModel.getMessage());
                            }
                        } else {
                            // showSnackBar(binding.getRoot(), getString(R.string.total_cart_item_not_found));
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
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            GetUserLocationResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {
                                if (responseModel.getData() != null && responseModel.getData().size() > 0) {
                                    lookUpList.clear();
                                    lookUpList.addAll(responseModel.getData());
                                    setAddressSpinner();
                                    isPopup = true;
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
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    public void setAddressSpinner() {
        try {
            popupWindow = new ListPopupWindow(this);
            final ArrayList<String> stringArrayList = new ArrayList<>();
            for (int i = 0; i < lookUpList.size() - 1; i++) {
                stringArrayList.add(lookUpList.get(i).getLine_1());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringArrayList);
            popupWindow.setAdapter(adapter);
            popupWindow.setAnchorView(binding.tvLookUpData);
            popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    postal_code = binding.etPostCode.getText().toString().trim();
                    area = lookUpList.get(position).getLine_1();
                    binding.etAddressLineOne.setText(lookUpList.get(position).getLine_1());
                    binding.etAddressLineTwo.setText(lookUpList.get(position).getLine_2());
                    binding.etAddressLineThree.setText(lookUpList.get(position).getDistrict());
                    latLng = new LatLng(lookUpList.get(position).getLatitude(), lookUpList.get(position).getLongitude());

                    popupWindow.dismiss();
                    isPopup = false;
                }
            });
            popupWindow.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        Integer clickCount = (Integer) marker.getTag();
        if (clickCount != null) {
            clickCount = clickCount + 1;
            marker.setTag(clickCount);
            HelperClass.toast(this, marker.getTitle() + " has been clicked " + clickCount + " times.");
        }
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        LatLng latLng = new LatLng(lat, longi);
        googleMap.addMarker(new MarkerOptions().position(latLng)
                .title("Marker").anchor(0.5f, 0.5f));
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, zoomLevel));
        mMap.setOnMarkerClickListener(this);
    }
}
