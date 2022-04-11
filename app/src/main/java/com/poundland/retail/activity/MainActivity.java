package com.poundland.retail.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.gson.Gson;
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
import com.poundland.retail.activityHospitality.MyCartReservationActivity;
import com.poundland.retail.adapter.DrawerItemAdapter;
import com.poundland.retail.adapter.VenueFilterListAdapter;
import com.poundland.retail.adapter.VenueFilterListItemAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityMainBinding;
import com.poundland.retail.databinding.DialogShowVenueFilterBinding;
import com.poundland.retail.databinding.NavHeaderMainBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.fragment.HomeFragment;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.DataModel;
import com.poundland.retail.model.QR_Model;
import com.poundland.retail.model.requestModel.GetBrandResponseModel;
import com.poundland.retail.model.requestModel.ProductDetailByModifierRequestModel;
import com.poundland.retail.model.requestModel.VenueFilterDataRequestModel;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.model.responseModel.VenueFilterDataResponseModel;
import com.poundland.retail.notificationService.NotificationModel;
import com.poundland.retail.zzznewPoundland.activity.FavouriteActivity;
import com.poundland.retail.zzznewPoundland.activity.InviteActivity;
import com.poundland.retail.zzznewPoundland.activity.LoyaltyPointsActivity;
import com.poundland.retail.zzznewPoundland.activity.LoyaltyStampVoucherActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.getAddress;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.setFragment;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CART_RESERVATION;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FILTER_DATA_SELECTED;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.FUSED_LOCATION;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.QR_CODE_CUST;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.USER_NAME;
import static com.poundland.retail.interfaces.Constants.WHOLE_FILTER_API_DATA;

public class MainActivity extends BaseActivity implements DrawerListner, View.OnClickListener {
    private static final String TAG = MainActivity.class.getName();
    private VenueFilterListAdapter filterListAdapter;
    private List<String> listSelectedCategory;
    private List<VenueFilterDataResponseModel.FilterBean> filterListData;
    private String[] mNavigationDrawerItemTitles;
    private CharSequence mDrawerTitle;
    private CharSequence mTitle;
    private ActionBarDrawerToggle mDrawerToggle;
    private ActivityMainBinding binding;
    private NavHeaderMainBinding navHeaderMainBinding;
    private PrefManager prefManager;
    boolean doubleBackToExitPressedOnce = false;
    private MainActivity instance = null;
    private FusedLocationProviderClient fusedLocationClient;
    private String address = "";
    private final int PERMISSION_ALL = 1;
    private Dialog dialog;
    private String fromWhere;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        instance = this;
        prefManager = PrefManager.getInstance(this);
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        if (TextUtils.isEmpty(fromWhere))
            getPermisssionForLocation();

        mTitle = mDrawerTitle = getTitle();
        mNavigationDrawerItemTitles = getResources().getStringArray(R.array.navigation_drawer_items_array);
        listSelectedCategory = new ArrayList<>();
        filterListData = new ArrayList<>();
        setupToolbar();
        setupDrawerToggle();
        setClickListner();
        HomeFragment addInventoryFragment = new HomeFragment(this, prefManager);
        setFragment(addInventoryFragment, false, this, R.id.main_container);
        HelperClass.printHashKey(this);

        binding.includeToolbar.ivAppLogo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void getPermisssionForLocation() {
        Dexter.withContext(this)
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse response) {
                        getLocation();
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
                        Toast.makeText(getApplicationContext(), "Seems you denied the permission!", Toast.LENGTH_SHORT).show();
                    }
                })
                .onSameThread()
                .check();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_main;
    }

    private void setClickListner() {

        binding.includeToolbar.ivNotification.setOnClickListener(this);
        binding.includeToolbar.ivCart.setOnClickListener(this);
        binding.includeToolbar.ivFilter.setOnClickListener(this);
        binding.includeToolbar.ivPayNGo.setOnClickListener(this);
        // binding.includeToolbar.ivFilter.setVisibility(View.GONE);
        View view = binding.navView.getHeaderView(0);
        navHeaderMainBinding = DataBindingUtil.bind(view);
        //navHeaderMainBinding.tvUserName.setText(prefManager.getPreference(FIRST_NAME, "") + " " + prefManager.getPreference(LAST_NAME, ""));
        navHeaderMainBinding.tvUserName.setText(prefManager.getPreference(USER_NAME, "") );


        Glide.with(this).load("")
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_app_icon)
                .into(navHeaderMainBinding.ivProfile);

        navHeaderMainBinding.ivCloseDrawer.setOnClickListener(this);
        navHeaderMainBinding.ivEditProfile.setOnClickListener(this);
        navHeaderMainBinding.tvUserLocation.setText(prefManager.getPreference(FUSED_LOCATION));
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == (R.id.iv_notification)) {
            Intent intent = new Intent(this, NotificationActivity.class);
            startActivity(intent);
        } else if (view.getId() == (R.id.iv_pay_n_go)) {

            Dexter.withContext(this)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse response) {
                            Intent intent = new Intent(instance, DecoderActivity.class);
                            startActivity(intent);
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
                            Toast.makeText(getApplicationContext(), "Seems you denied the permission", Toast.LENGTH_SHORT).show();
                        }
                    })
                    .onSameThread()
                    .check();

        } else if (view.getId() == (R.id.iv_close_drawer)) {

            binding.drawerLayout.closeDrawers();

        } else if (view.getId() == (R.id.iv_edit_profile)) {

            Intent allShow = new Intent(this, MyProfileActivity.class);
            startActivity(allShow);

        } else if (view.getId() == (R.id.iv_cart)) {

            if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                Intent intent = new Intent(this, MyCartHospitalityActivity.class);
                intent.putExtra(FROM_WHERE, getString(R.string.home));
                startActivity(intent);
            } else if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_RESERVATION) {
                Intent intent = new Intent(this, MyCartReservationActivity.class);
                startActivity(intent);
            } else {
                Intent intent = new Intent(this, MyCartActivity.class);
                startActivity(intent);
            }

        } else if (view.getId() == (R.id.iv_filter)) {
            getHomeFilterData();
        }
    }

    void setupDrawerToggle() {
        DataModel[] drawerItem = new DataModel[14];
        drawerItem[0] = new DataModel(R.drawable.ic_qr_scan, getString(R.string.pay_go));
        drawerItem[1] = new DataModel(R.drawable.ic_loyalty_stamp_voucher, getString(R.string.loy_stamp_voucher));  //  | Stamps | Vouchers
        drawerItem[2] = new DataModel(R.drawable.ic_my_order, getString(R.string.my_orders));
       // drawerItem[3] = new DataModel(R.drawable.ic_booking, getString(R.string.my_bookings));
        drawerItem[3] = new DataModel(R.drawable.ic_loyalty, getString(R.string.loyalty));
        drawerItem[4] = new DataModel(R.drawable.ic_message, getString(R.string.invite));
        drawerItem[5] = new DataModel(R.drawable.ic_wishlist, getString(R.string.wishlist));
        drawerItem[6] = new DataModel(R.drawable.ic_payment_details, getString(R.string.payment_details));
        drawerItem[7] = new DataModel(R.drawable.ic_address, getString(R.string.address));
        drawerItem[8] = new DataModel(R.drawable.ic_voucher_nav, getString(R.string.voucher));
        drawerItem[9] = new DataModel(R.drawable.ic_settings_drawer, getString(R.string.settings));
        drawerItem[10] = new DataModel(R.drawable.selected_indicator, getString(R.string.i_want_to));
        drawerItem[11] = new DataModel(R.drawable.ic_home, getString(R.string.my_loyalty_points));
        // drawerItem[9] = new DataModel(R.drawable.ic_share, getString(R.string.refer_a_friend));
        drawerItem[12] = new DataModel(R.drawable.ic_store_blue, getString(R.string.favorite_ven_pro));
        drawerItem[13] = new DataModel(R.drawable.ic_nearby_deals, getString(R.string.nearby_deals));



        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(true);

        DrawerItemAdapter recentScanAdapter = new DrawerItemAdapter(this, drawerItem, this);
        binding.leftDrawer.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.leftDrawer.setAdapter(recentScanAdapter);
        binding.drawerLayout.setDrawerListener(mDrawerToggle);
        mDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.includeToolbar.toolbar, R.string.drawer_open, R.string.drawer_close) {
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                // Do whatever you want here
            }

            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                // Do whatever you want here
                navHeaderMainBinding.tvUserLocation.setText(prefManager.getPreference(FUSED_LOCATION));
            }
        };
        binding.drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle = new ActionBarDrawerToggle(this, binding.drawerLayout, binding.includeToolbar.toolbar, R.string.drawer_open, R.string.drawer_close);
        mDrawerToggle.setDrawerIndicatorEnabled(false);
        mDrawerToggle.setHomeAsUpIndicator(R.drawable.ic_navigation_drawer_icon); // set custom drawer icon
        mDrawerToggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.drawerLayout.openDrawer(GravityCompat.START);
            }
        });
        mDrawerToggle.syncState();


    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        selectItem(position);
    }

    private void selectItem(int position) {

        Fragment fragment = null;

        switch (position) {
            case 0:
                Dexter.withContext(this)
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(instance, DecoderActivity.class);
                                startActivity(intent);
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
                                Toast.makeText(getApplicationContext(), "Seems you denied the permission", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .onSameThread()
                        .check();
                break;


            case 1:
                // Intent in = new Intent(this, LoyaltyStampVoucherActivity.class);
                Intent in = new Intent(this, LoyaltyPointsActivity.class);
                in.putExtra(FROM_WHERE, getString(R.string.home));
                startActivity(in);
                break;
            case 2:
                Intent myOrder = new Intent(this, MyOrderActivity.class);
                startActivity(myOrder);
                break;
            case 3:
                binding.drawerLayout.closeDrawers();
                QR_Model qr_model = new QR_Model(String.valueOf(prefManager.getPreference(LOGIN_ID, 0)), QR_CODE_CUST);
                String qrData = new Gson().toJson(qr_model);
                DialogUtils.scanLoyaltyDialog(this, qrData, getString(R.string.qr_code_message));

                break;

            case 4:
                // Intent message = new Intent(this, MessageActivity.class);
                Intent message = new Intent(this, InviteActivity.class);
                startActivity(message);
                break;
            case 5:
                Intent wish = new Intent(this, WishListActivity.class);
                startActivity(wish);
                break;
            case 6:
                Intent pay = new Intent(this, CardActivity.class);
                startActivity(pay);
                break;
            case 7:
                Intent address = new Intent(this, AddressActivity.class);
                address.putExtra(FROM_WHERE, getString(R.string.home));
                startActivity(address);
                break;
            case 8:
                Intent vouc = new Intent(this, VouchersListActivity.class);
                startActivity(vouc);
                break;
            case 9:
                Intent setting = new Intent(this, SettingsActivity.class);
                startActivity(setting);

                break;
            case 11:
                binding.drawerLayout.closeDrawers();   /// MY LOYALTY
                Intent intent = new Intent(this, LoyaltyStampVoucherActivity.class);
                startActivity(intent);
                break;
            case 12:
                Intent upcomingVenue = new Intent(this, FavouriteActivity.class);
                upcomingVenue.putExtra(TOP_OFFER_TITLE, getString(R.string.upcoming_venue));
                startActivity(upcomingVenue);
                break;
            case 13:
                Intent nearByDeals = new Intent(this, NearByDealsActivity.class);
                nearByDeals.putExtra(TOP_OFFER_TITLE, getString(R.string.nearby_deals));
                startActivity(nearByDeals);
                break;
            default:
                break;
        }
        if (fragment != null) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.main_container, fragment).commit();
            setTitle(mNavigationDrawerItemTitles[position]);
            binding.drawerLayout.closeDrawer(binding.leftDrawer);

        } else {
            Log.e("MainActivity", "Error in creating fragment");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        getSupportActionBar().setTitle(mTitle);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mDrawerToggle.syncState();
    }

    void setupToolbar() {
        setSupportActionBar(binding.includeToolbar.toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            /*super.onBackPressed();
            return;*/
            System.exit(0);
        }
        binding.drawerLayout.closeDrawer(GravityCompat.START);
        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit!", Toast.LENGTH_SHORT).show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBackToExitPressedOnce = false;
            }
        }, 2000);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        navHeaderMainBinding.tvUserName.setText(prefManager.getPreference(USER_NAME, "") );

        Glide.with(this).load("")
                .apply(RequestOptions.circleCropTransform())
                .placeholder(R.drawable.ic_app_icon)
                .into(navHeaderMainBinding.ivProfile);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTotalCartCount();
        if (TextUtils.isEmpty(prefManager.getPreference(FUSED_LOCATION)))
            getLocation();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_ALL:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    Toast.makeText(instance, "Permission Denied!", Toast.LENGTH_SHORT).show();
                    Log.e("Permission ", "Permission Denied!");
                }
        }
    }

    private void getLocation() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            setAddress(location.getLatitude(), location.getLongitude());
                            prefManager.savePreference(LATITUDE, String.valueOf(location.getLatitude()));
                            prefManager.savePreference(LONGITUDE, String.valueOf(location.getLongitude()));
                        } else
                            Log.e(" Place Err", "location is err  MainActivity");
                    }
                });
    }

    private void setAddress(double latitude, double longitude) {
        String[] addresses = getAddress(this, latitude, longitude);
        if (addresses.length > 0) {
            address = addresses[0];
            if (!TextUtils.isEmpty(address) && address.contains("null"))
                address = address.replace("null", "");
//            postal_code = addresses[1];
            prefManager.savePreference(FUSED_LOCATION, address);
            Log.e("Address", "postal_code" + addresses[1] + "address" + addresses[0]);
            navHeaderMainBinding.tvUserLocation.setText(address);
            if (HomeFragment.getHomeFragment() != null) {
                HomeFragment.getHomeFragment().updateLocation(address);

            }
        }
    }

    public void getTotalCartCount() {
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
                                    binding.includeToolbar.tvCartCount.setVisibility(View.VISIBLE);
                                } else binding.includeToolbar.tvCartCount.setVisibility(View.GONE);
                                binding.includeToolbar.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
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
                    showSnackBar(binding.getRoot(), t.getLocalizedMessage());
//                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void getHomeFilterData() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            VenueFilterDataRequestModel requestModel = new VenueFilterDataRequestModel("");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<VenueFilterDataResponseModel> call = apiInterface.getHomeFilterData(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<VenueFilterDataResponseModel>() {
                @Override
                public void onResponse(Call<VenueFilterDataResponseModel> call, Response<VenueFilterDataResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            VenueFilterDataResponseModel responseModel = response.body();

                            if (responseModel.isStatus()) {

                                if (responseModel.getFilter() != null && responseModel.getFilter().size() > 0) {
                                    filterListData.clear();

                                    filterListData.addAll(responseModel.getFilter());
                                    homeFilterDialog(filterListData);

                                } else {
                                    filterListData.clear();
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
                public void onFailure(Call<VenueFilterDataResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    public void homeFilterDialog(List<VenueFilterDataResponseModel.FilterBean> filterListData) {
        DialogShowVenueFilterBinding dialogBinding;
        View dialogView = LayoutInflater.from((Activity) this).inflate(R.layout.dialog_show_venue_filter, null);
        dialogBinding = DataBindingUtil.bind(dialogView);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.setCancelable(true);

        HashMap<String, List<String>> hashMap = new HashMap();
        RecyclerView rv_filterList = dialog.findViewById(R.id.rv_filterList);
        filterListAdapter = new VenueFilterListAdapter(this, filterListData, new ModifierSelectionListner() {
            @Override
            public void onModifierSelect(int pos_1st, int pos_2nd) {

                if (filterListData.get(pos_1st).getFilter_type().contains(getResources().getString(R.string.category))) {
                    if (!listSelectedCategory.contains(filterListData.get(pos_1st).getFilter_list().get(pos_2nd).getFilter_value())) {
                        listSelectedCategory.add(filterListData.get(pos_1st).getFilter_list().get(pos_2nd).getFilter_value());
                    }
                    if (listSelectedCategory.size() > 0)
                        getBrands(listSelectedCategory, dialog);
                    Log.e("listSelectedCategory A", "" + listSelectedCategory);
                }
            }

            @Override
            public void onModifierDeselect(int pos_1st, int pos_2nd) {
                if (filterListData.get(pos_1st).getFilter_type().contains(getResources().getString(R.string.category))) {
                    if (listSelectedCategory.contains(filterListData.get(pos_1st).getFilter_list().get(pos_2nd).getFilter_value())) {
                        listSelectedCategory.remove(filterListData.get(pos_1st).getFilter_list().get(pos_2nd).getFilter_value());
                    }

                    if (listSelectedCategory.size() > 0) {
                        getBrands(listSelectedCategory, dialog);
                    } else {
                        List<String> emptylist = new ArrayList<>();
                        emptylist.add("");
                        getBrands(emptylist, dialog);
                    }
                    Log.e("listSelectedCategory R", "" + listSelectedCategory);
                }
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(instance);
        rv_filterList.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        // rv_filterList.setLayoutManager(layoutManager);
        rv_filterList.setAdapter(filterListAdapter);
        filterListAdapter.getFilter().filter("");
        dialog.findViewById(R.id.tv_apply_button).setOnClickListener(v -> {

            HashMap<String, VenueFilterListItemAdapter> hashMapList = filterListAdapter.getAdapterList();

            for (Map.Entry<String, VenueFilterListItemAdapter> item : hashMapList.entrySet()) {
                VenueFilterListItemAdapter ss = item.getValue();
                List<String> data = new ArrayList<>();
                for (Map.Entry<Integer, String> _item : ss.getSelected().entrySet()) {
                    data.add(_item.getValue());
                }
                hashMap.put(item.getKey(), data);
            }
            Log.e("filterList ", "" + hashMap.toString());
            Intent in = new Intent(this, HomeFilterActivity.class);
            in.putParcelableArrayListExtra(WHOLE_FILTER_API_DATA, (ArrayList<? extends Parcelable>) filterListData);
            in.putExtra(FILTER_DATA_SELECTED, hashMap);
            startActivity(in);
            dialog.dismiss();
        });
        dialog.findViewById(R.id.tv_clear_all).setOnClickListener(v -> {

            if (filterListAdapter != null && filterListData != null) {
                filterListAdapter.clearAllCheck();
            }
        });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(v -> dialog.dismiss());
        ////////////////////////////////////////////////////////////////////////////////////////////////
        dialog.show();
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER;
        dialog.getWindow().setAttributes(lp);
        dialog.getWindow().setBackgroundDrawableResource(R.color.seme_transparent);
        // dialog.getWindow().setBackgroundDrawable(new ColorDrawable(ContextCompat.getColor(getContext(), R.color.seme_transparent)));
    }


    private void getBrands(List<String> listSelectedCategory, Dialog filterDialog) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            ProductDetailByModifierRequestModel requestModel = new ProductDetailByModifierRequestModel(listSelectedCategory);

            Call<GetBrandResponseModel> call = apiInterface.getBrand(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<GetBrandResponseModel>() {
                @Override
                public void onResponse(Call<GetBrandResponseModel> call, Response<GetBrandResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            GetBrandResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {

                                if (responseModel.getFilter() != null && responseModel.getFilter().getFilter_list().size() > 0) {

                                    List<VenueFilterDataResponseModel.FilterBean.FilterListBean> filterListBrand = new ArrayList<>();
                                    List<VenueFilterDataResponseModel.FilterBean.FilterListBean> filterListModifier = new ArrayList<>();
                                    /*///// ****************************  for brand start  *****************************//////*/
                                    for (int i = 0; i < responseModel.getFilter().getFilter_list().size(); i++) {
                                        filterListBrand.add(new VenueFilterDataResponseModel.FilterBean.FilterListBean(responseModel.getFilter().getFilter_list().get(i).getFilter_value(), responseModel.getFilter().getFilter_list().get(i).getDisplay_name()));
                                    }
                                    VenueFilterDataResponseModel.FilterBean filterBean = new VenueFilterDataResponseModel.FilterBean(
                                            responseModel.getFilter().getFilter_type(), responseModel.getFilter().getIsMultiple(), filterListBrand);

                                    filterListAdapter.addAll(1, filterBean);
                                    /*///// ****************************  for brand end  ^    for Modifier start *****************************//////*/
                                    for (int j = 0; j < responseModel.getModifiers().getFilter_list().size(); j++) {
                                        filterListModifier.add(new VenueFilterDataResponseModel.FilterBean.FilterListBean(
                                                responseModel.getModifiers().getFilter_list().get(j).getFilter_value(), responseModel.getModifiers().getFilter_list().get(j).getDisplay_name()));
                                    }

                                    VenueFilterDataResponseModel.FilterBean filterBeanModi = new VenueFilterDataResponseModel.FilterBean(
                                            responseModel.getModifiers().getFilter_type(), responseModel.getModifiers().getIsMultiple(), filterListModifier);

                                    filterListAdapter.addAll(2, filterBeanModi);
                                    filterListAdapter.getFilter().filter("");
                                    /*///// ****************************  for Modifier end  *****************************//////*/
                                } else {
                                    filterListAdapter.remove(1);
                                    filterListAdapter.remove(2);
                                    filterListAdapter.getFilter().filter("");
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
                public void onFailure(Call<GetBrandResponseModel> call, Throwable t) {
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
    protected void onNotificationReceived(Intent intent) {
        try {
            Log.e(TAG, "Notification : " + "Found in onNotificationReceived");
            Bundle extras = intent.getExtras();
            if (extras != null) {
                String timestamp = extras.getString("timestamp");
                String title = extras.getString("title");
                String message = extras.getString("message");
                String image = extras.getString("image");
                String data = extras.getString("data");

                if (!TextUtils.isEmpty(data)) {
                    NotificationModel model = new Gson().fromJson(data, NotificationModel.class);
                    DialogUtils.offerNotificationDialog(this, title, message, image, new Gson().fromJson(data, NotificationModel.class), (position, clickId) -> {

                    });
                } else {
                    DialogUtils.simpleNotificationDialog(this, title, message, image, null, (position, clickId) -> {

                    });
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
