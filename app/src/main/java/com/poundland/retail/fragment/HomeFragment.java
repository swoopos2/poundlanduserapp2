package com.poundland.retail.fragment;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.Places;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.api.net.PlacesClient;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
import com.google.gson.Gson;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.DexterError;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.PermissionRequestErrorListener;
import com.karumi.dexter.listener.single.PermissionListener;
import com.poundland.retail.R;
import com.poundland.retail.activity.AllCategoryListActivity;
import com.poundland.retail.activity.AllVenueListActivity;
import com.poundland.retail.activity.CategoryDetailsActivity;
import com.poundland.retail.activity.DecoderActivity;
import com.poundland.retail.activity.LookUpActivity;
import com.poundland.retail.activity.MainActivity;
import com.poundland.retail.activity.NearByDealsActivity;
import com.poundland.retail.activity.ProductDetailActivity;
import com.poundland.retail.activity.VenueDetailActivity;
import com.poundland.retail.activityHospitality.AllVenueReservationActivity;
import com.poundland.retail.activityHospitality.AllVenuesHospitalityActivity;
import com.poundland.retail.activityHospitality.adapter.AutoCompleteAdapter;
import com.poundland.retail.activityHospitality.adapter.AutoCompleteVenueOnlyAdapter;
import com.poundland.retail.adapter.HomeCategoryAdapter;
import com.poundland.retail.adapter.HomeCategoryHospitalityAdapter;
import com.poundland.retail.adapter.HomeCategoryOfMonthAdapter;
import com.poundland.retail.adapter.HomeRestoCafeBarsAdapter;
import com.poundland.retail.adapter.HomeTopOffersAdapter;
import com.poundland.retail.adapter.HomeTopVenueAdapter;
import com.poundland.retail.adapter.SliderAdapterHome;
import com.poundland.retail.adapter.TopCatProductAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PermissionsUtil;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.FragmentHomeBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.QR_Model;
import com.poundland.retail.model.requestModel.HeaderSearchRequestModel;
import com.poundland.retail.model.requestModel.LandingPageRequestModel;
import com.poundland.retail.model.responseModel.HeaderSearchResponseModel;
import com.poundland.retail.model.responseModel.LandingPageBottomResponseModel;
import com.poundland.retail.model.responseModel.LandingPageResponseModel;
import com.poundland.retail.model.responseModel.LandingPageTopResponseModel;
import com.poundland.retail.model.responseModel.ProductBySearchResponseModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.TimerTask;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.app.Activity.RESULT_CANCELED;
import static android.app.Activity.RESULT_OK;
import static com.facebook.FacebookSdk.getApplicationContext;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.AUTO_COMPLETE_CATEGORY;
import static com.poundland.retail.interfaces.Constants.AUTO_COMPLETE_PRODUCT;
import static com.poundland.retail.interfaces.Constants.AUTO_COMPLETE_VENUE;
import static com.poundland.retail.interfaces.Constants.BANNER_CLICK;
import static com.poundland.retail.interfaces.Constants.BARCODE_ID;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.CATEGORY_NAME;
import static com.poundland.retail.interfaces.Constants.DATE_INTENT;
import static com.poundland.retail.interfaces.Constants.DD_MMM_YYYY_dash_in_middle;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FIRST_NAME;
import static com.poundland.retail.interfaces.Constants.FROM_CATEGORY_TAP;
import static com.poundland.retail.interfaces.Constants.FROM_SEARCH_TAP;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.FUSED_LOCATION;
import static com.poundland.retail.interfaces.Constants.HOME_CATEGORY;
import static com.poundland.retail.interfaces.Constants.HOME_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.HOME_RETAIL;
import static com.poundland.retail.interfaces.Constants.HOSPITALITY_VENUE;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LOCAL_SHOP_CATEGORY;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.LOOKUP_ACTIVITY_CALLBACK_DATA;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.OPEN_LOOKUP_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.PERSON_INTENT;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_TYPE_SELECTED;
import static com.poundland.retail.interfaces.Constants.QR_CODE_CUST;
import static com.poundland.retail.interfaces.Constants.SHOW_ALL_CATEGORY;
import static com.poundland.retail.interfaces.Constants.SLUG;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TIME_INTENT;
import static com.poundland.retail.interfaces.Constants.TOP_CATEGORY_PRODUCTS;
import static com.poundland.retail.interfaces.Constants.TOP_CAT_OF_MONTH;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.TOP_VENUE;
import static com.poundland.retail.interfaces.Constants.VENUE_INTENT;

public class HomeFragment extends Fragment implements DrawerListner, View.OnClickListener, SuccessActionListner, ModifierSelectionListner {
    static HomeFragment homeFragment;
    private final static int PLACE_PICKER_REQUEST = 999;
    private int PRODUCT_TYPE = 0;
    private FragmentHomeBinding binding;
    private Context context;
    private PrefManager prefManager;
    private PlacesClient placesClient;

    private List<ProductBySearchResponseModel.ProductOfferBean> topOfferList;
    private List<LandingPageBottomResponseModel.CategoriesBean> categoryList;
    private List<LandingPageBottomResponseModel.TopcategoriesMonthBean> categoryOfMonthList;
    private List<LandingPageBottomResponseModel.TopCatProductsBean> topCatProductsList;
    private List<Integer> noOfGuests;
    private List<LandingPageTopResponseModel.LocalshopBean> localShopCategoryList;
    private List<LandingPageTopResponseModel.SwoopeEatVanuesBean> topHospitalityVenueList;
    private List<LandingPageTopResponseModel.RetailVanuesBean> topVenueList;
    private List<LandingPageTopResponseModel.BannersBean> bannerImageList;
    private List<LandingPageTopResponseModel.TimesBean> timeSlot;
    private String search = "";
    private double lat;
    private double longi;
    private String location;
    private List<String> images;
    private Handler handler = new Handler();
    private DatePickerDialog datePickerDialog;
    private Calendar customDate;
    private String startDate, endDate;
    private SimpleDateFormat sendDateFormat;
    private ListPopupWindow popupWindow;
    private boolean isPopup;
    private Integer nofGuest = 2;
    private List<HeaderSearchResponseModel.SuggestionBean> listSuggestion;
    private List<HeaderSearchResponseModel.CategoriesNameBean> listCategoriesName;

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int year, int monthOfYear, int dayOfMonth) {
            customDate.set(year, monthOfYear, dayOfMonth);
            startDate = sendDateFormat.format(customDate.getTime());
            endDate = sendDateFormat.format(customDate.getTime());
            binding.tvDob.setText(startDate.substring(0, startDate.length() - 5));
            // age = HelperClass.calculateAge(customDate.getTimeInMillis());
            Log.e("from time:", startDate + " to time" + endDate);
        }
    };
    private Call<HeaderSearchResponseModel> callSearch;
    // private List<String> suggetionList;
    // private AutoCompleteAdapter autoCompletAdapter;

    public HomeFragment(Context context, PrefManager prefManager) {
        this.context = context;
        this.prefManager = prefManager;
    }

    public static HomeFragment getHomeFragment() {
        return homeFragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        bannerImageList = new ArrayList<>();
        images = new ArrayList<>();
        images.clear();

        images.add("");
        images.add("");
        images.add("");

        setViewPager();
        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.homeFragment = this;
    }

    public void updateLocation(String address) {
        binding.tvCurrentLocation.setText(address);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setViewPager();
        initLists();
    }

    @Override
    public void onResume() {
        super.onResume();

        init();


        binding.etSearchNowHeader.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    binding.ivClose.setVisibility(View.VISIBLE);
                    binding.ivSearch.setVisibility(View.GONE);
                } else {
                    binding.ivClose.setVisibility(View.GONE);
                    binding.ivSearch.setVisibility(View.VISIBLE);
                }

                if (editable.length() > 2) {
                    /*final Handler handler = new Handler();
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                             fetchHeaderSearch(editable.toString());
                        }
                    }, 200);*/

                    fetchHeaderSearch(editable.toString());
                    Log.e("GGGGG", editable.toString());

                }

            }
        });

    }


    private void setListner() {
        binding.rlLoyalty.setOnClickListener(this);
        binding.rlNearbyDeals.setOnClickListener(this);
        binding.btnShowDropDown.setOnClickListener(this);
        binding.tvGo.setOnClickListener(this);
        binding.tvShowAllTopVenues.setOnClickListener(this);
        binding.tvSwoopeIt.setOnClickListener(this);
        binding.llGuest.setOnClickListener(this);
        binding.llSelectTime.setOnClickListener(this);
        binding.rlSelectDate.setOnClickListener(this);
        binding.llCurrentLocation.setOnClickListener(this);
        binding.rlPayNGo.setOnClickListener(this);
        binding.tvShowAllCategory.setOnClickListener(this);
        binding.tvShowAllCategoryHosp.setOnClickListener(this);
        binding.btnShowAllTopSellingCategories.setOnClickListener(this);
        binding.btnShowAllTopRestoBar.setOnClickListener(this);
        binding.ivClose.setOnClickListener(this);

    }


    private void init() {
        customDate = Calendar.getInstance();
        sendDateFormat = new SimpleDateFormat(DD_MMM_YYYY_dash_in_middle, Locale.getDefault());
        prefManager = PrefManager.getInstance(context);
        location = prefManager.getPreference(FUSED_LOCATION, "");
        startDate = HelperClass.getCurrentDate(DD_MMM_YYYY_dash_in_middle);
        binding.tvDob.setText(startDate.substring(0, startDate.length() - 5));

        Log.e("from time:", startDate + " to time" + endDate);
        if (!location.equals("")) {
            //location = location.replace("null", "");
            //  binding.tvTopOffersNearYouLocation.setText(location);
            binding.tvCurrentLocation.setText(location);
            binding.tvCurrentLocationLabel.setText(context.getString(R.string.current_location_show));
        }

        setListner();
        clearSearchField();

        if (!Places.isInitialized())
            Places.initialize(context, getString(R.string.api_key_place));
        // Create a new Places client instance
        placesClient = Places.createClient(context);
        binding.tvUserName.setText("Hi " + prefManager.getPreference(FIRST_NAME, "") /*+ " " + prefManager.getPreference(LAST_NAME, "") */ + ",");
        //  fetchLandingPageDatas();
        fetchLandingPageDatasTop();
        fetchLandingPageDatasBot();
        binding.etSearchNowHeader.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    search = binding.etSearchNowHeader.getText().toString();
                    if (search.length() > 0) {
                        Intent inn = new Intent(context, CategoryDetailsActivity.class);
                        inn.putExtra(FROM_WHERE, FROM_SEARCH_TAP);
                        inn.putExtra(CATEGORY_NAME, search);
                        inn.putExtra(PRODUCT_TYPE_SELECTED, PRODUCT_TYPE);
                        startActivity(inn);
                    }
                    handled = true;
                }
                return handled;
            }

        });
    }

    private void initLists() {
        //  suggetionList = new ArrayList<>();
        topVenueList = new ArrayList<>();
        topHospitalityVenueList = new ArrayList<>();
        topOfferList = new ArrayList<>();
        categoryList = new ArrayList<>();
        localShopCategoryList = new ArrayList<>();
        noOfGuests = new ArrayList<>();
        timeSlot = new ArrayList<>();
        categoryOfMonthList = new ArrayList<>();
        topCatProductsList = new ArrayList<>();
        listSuggestion = new ArrayList<>();
        listCategoriesName = new ArrayList<>();
    }

    private void setCategoryAdapter() {
        final GridLayoutManager layoutManager = new GridLayoutManager(context, 4);
        HomeCategoryAdapter homeCategoryAdapter = new HomeCategoryAdapter(context, categoryList, this, true);
        binding.rvCategoryList.setLayoutManager(layoutManager);
        binding.rvCategoryList.setAdapter(homeCategoryAdapter);
    }

    private void setCategoryHospitalityAdapter() {
        final GridLayoutManager layoutManager = new GridLayoutManager(context, 3);
        HomeCategoryHospitalityAdapter homeCategoryAdapter = new HomeCategoryHospitalityAdapter(context, localShopCategoryList, this, true);
        binding.rvCategoryListHosp.setLayoutManager(layoutManager);
        binding.rvCategoryListHosp.setAdapter(homeCategoryAdapter);
    }

    private void setTopVenueAdapter() {
        HomeTopVenueAdapter homeCategoryAdapter = new HomeTopVenueAdapter(context, topVenueList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        binding.rvTopVenues.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        // binding.rvTopVenues.setLayoutManager(layoutManager);
        binding.rvTopVenues.setAdapter(homeCategoryAdapter);
    }

    private void setTopRestoCafeAdapter() {
        HomeRestoCafeBarsAdapter homeCategoryAdapter = new HomeRestoCafeBarsAdapter(context, topHospitalityVenueList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(context);
        binding.rvRestoCafeBar.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        // binding.rvTopVenues.setLayoutManager(layoutManager);
        binding.rvRestoCafeBar.setAdapter(homeCategoryAdapter);
    }

    private void setTopSellingCategoryAdapter() {
        HomeCategoryOfMonthAdapter homeCategoryAdapter = new HomeCategoryOfMonthAdapter(context, categoryOfMonthList, this, true);
        binding.rvTopSellingCategories.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        binding.rvTopSellingCategories.setAdapter(homeCategoryAdapter);
    }

    private void setTopOffersAdapter() {
        HomeTopOffersAdapter homeCategoryAdapter = new HomeTopOffersAdapter(context, topOfferList, this);
        //    binding.rvTopOffers.setLayoutManager(new LinearLayoutManager(context));
        //   binding.rvTopOffers.setAdapter(homeCategoryAdapter);
    }

    private void setTopCatProductsAdapter() {
        TopCatProductAdapter homeCategoryAdapter = new TopCatProductAdapter(context, topCatProductsList, this, this, this);
        binding.rvTopCatProducts.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        binding.rvTopCatProducts.setAdapter(homeCategoryAdapter);
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        switch (clickType) {
            case HOME_CATEGORY:
                if (categoryList.size() > 0) {
                    Intent in = new Intent(context, CategoryDetailsActivity.class);
                    in.putExtra(FROM_WHERE, FROM_CATEGORY_TAP);
                    in.putExtra(CATEGORY_NAME, categoryList.get(position).getCat_name());
                    in.putExtra(CATEGORY_ID, String.valueOf(categoryList.get(position).getId()));
                    in.putExtra(PRODUCT_TYPE_SELECTED, PRODUCT_TYPE);
                    startActivity(in);
                }
                break;

            case TOP_CAT_OF_MONTH:
                if (categoryOfMonthList.size() > 0) {
                    Intent in = new Intent(context, CategoryDetailsActivity.class);
                    in.putExtra(FROM_WHERE, FROM_CATEGORY_TAP);
                    in.putExtra(CATEGORY_NAME, categoryOfMonthList.get(position).getCat_name());
                    in.putExtra(CATEGORY_ID, String.valueOf(categoryOfMonthList.get(position).getId()));
                    in.putExtra(PRODUCT_TYPE_SELECTED, PRODUCT_TYPE);
                    startActivity(in);
                }
                break;
            case TOP_CATEGORY_PRODUCTS:
                if (topCatProductsList.size() > 0) {
                    Intent in = new Intent(context, CategoryDetailsActivity.class);
                    in.putExtra(FROM_WHERE, FROM_CATEGORY_TAP);
                    in.putExtra(CATEGORY_NAME, topCatProductsList.get(position).getCat_name());
                    in.putExtra(CATEGORY_ID, String.valueOf(topCatProductsList.get(position).getCat_id()));
                    in.putExtra(PRODUCT_TYPE_SELECTED, PRODUCT_TYPE);
                    startActivity(in);
                }
                break;

            case SHOW_ALL_CATEGORY:
                Intent allShow = new Intent(context, AllCategoryListActivity.class);
                startActivity(allShow);
                break;

            case TOP_OFFER:
                if (topOfferList.size() > 0) {
                    Intent topProduct = new Intent(context, ProductDetailActivity.class);
                    topProduct.putExtra(STORE_ID, topOfferList.get(position).getVenue_id());
                    topProduct.putExtra(PRODUCT_ID, String.valueOf(topOfferList.get(position).getProduct_id()));
                    topProduct.putExtra(OFFER_ID, String.valueOf(topOfferList.get(position).getOffer_id()));
                    topProduct.putExtra(BARCODE_ID, "");
                    startActivity(topProduct);
                }
                break;

            case TOP_VENUE:
                if (topVenueList.size() > 0) {
                    Intent topStore = new Intent(context, VenueDetailActivity.class);
                    topStore.putExtra(STORE_ID, topVenueList.get(position).getVenue_id());
                    topStore.putExtra(CATEGORY_ID, "");
                    topStore.putExtra(FROM_WHERE, HOME_RETAIL);
                    startActivity(topStore);
                }
                break;
            case HOSPITALITY_VENUE:
                if (topHospitalityVenueList.size() > 0) {
                    Intent topStore = new Intent(context, VenueDetailActivity.class);
                    topStore.putExtra(STORE_ID, topHospitalityVenueList.get(position).getVenue_id());
                    topStore.putExtra(CATEGORY_ID, "");
                    topStore.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                    startActivity(topStore);
                }
                break;
            case LOCAL_SHOP_CATEGORY:
                if (localShopCategoryList.size() > 0) {
                    Intent show_all_venue_hos = new Intent(context, AllVenuesHospitalityActivity.class);
                    show_all_venue_hos.putExtra(CATEGORY_ID, localShopCategoryList.get(position).getShop_type());
                    show_all_venue_hos.putExtra(CATEGORY_NAME, localShopCategoryList.get(position).getName());
                    show_all_venue_hos.putExtra(SLUG, localShopCategoryList.get(position).getSlug());
                    startActivity(show_all_venue_hos);
                }
                break;
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_loyalty:
                QR_Model qr_model = new QR_Model(String.valueOf(prefManager.getPreference(LOGIN_ID, 0)), QR_CODE_CUST);
                String qrData = new Gson().toJson(qr_model);
                DialogUtils.scanLoyaltyDialog(context, qrData, getString(R.string.qr_code_message));
                break;

            case R.id.tv_show_all_category:
                Intent allShow = new Intent(context, AllCategoryListActivity.class);
                startActivity(allShow);
                break;

            case R.id.tv_show_all_category_hosp:
                Intent showAll = new Intent(context, AllCategoryListActivity.class);
                startActivity(showAll);
                break;

            case R.id.tv_go:
                search = binding.etSearchNowHeader.getText().toString();
                if (search.length() > 0) {
                    Intent inn = new Intent(context, CategoryDetailsActivity.class);
                    inn.putExtra(FROM_WHERE, FROM_SEARCH_TAP);
                    inn.putExtra(CATEGORY_NAME, search);
                    inn.putExtra(PRODUCT_TYPE_SELECTED, PRODUCT_TYPE);
                    startActivity(inn);
                }
                break;


            case R.id.btn_show_all_top_selling_categories:
                Intent allShowCat = new Intent(context, AllCategoryListActivity.class);
                startActivity(allShowCat);
                break;

            case R.id.tv_swoope_it:
                Intent favo = new Intent(context, AllVenueReservationActivity.class);
                favo.putExtra(DATE_INTENT, binding.tvDob.getText().toString().isEmpty() ? "" : startDate);
                favo.putExtra(TIME_INTENT, binding.tvSelectTime.getText().toString().isEmpty() ? "" : binding.tvSelectTime.getText().toString());
                favo.putExtra(PERSON_INTENT, nofGuest);
                favo.putExtra(VENUE_INTENT, binding.etRestaurantCousin.getText().toString().isEmpty() ? "" : binding.etRestaurantCousin.getText().toString());
                startActivity(favo);
                break;
            case R.id.rl_select_date:
                showDatePickerDialog(customDate);
                break;
            case R.id.ll_select_time:

                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    setSpinnerForTime();
                    isPopup = true;
                }

                break;
            case R.id.ll_guest:
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    setSpinnerForGuest();
                    isPopup = true;

                }
                break;
            case R.id.btn_show_drop_down:
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    setSpinnerForShopType();
                    isPopup = true;
                }
                break;
            case R.id.rl_pay_n_go:
                Dexter.withContext(context)
                        .withPermission(Manifest.permission.CAMERA)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                Intent intent = new Intent(context, DecoderActivity.class);
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
            case R.id.rl_nearby_deals:
                Intent topStore = new Intent(context, NearByDealsActivity.class);
                topStore.putExtra(TOP_OFFER_TITLE, getString(R.string.nearby_deals));
                startActivity(topStore);
                break;
            /*case R.id.tv_show_all_top_offers:
                Intent show_all_top_offers = new Intent(context, NearByDealsActivity.class);
                show_all_top_offers.putExtra(TOP_OFFER_TITLE, getString(R.string.top_offers));
                startActivity(show_all_top_offers);
                break;*/

            case R.id.tv_show_all_top_venues:
                Intent show_all_venue = new Intent(context, AllVenueListActivity.class);
                startActivity(show_all_venue);
                break;
            case R.id.iv_close:
                binding.etSearchNowHeader.setText("");
                break;

            case R.id.btn_show_all_top_resto_bar:
                Intent show_all_venue_hos = new Intent(context, AllVenuesHospitalityActivity.class);
                show_all_venue_hos.putExtra(CATEGORY_ID, "");
                show_all_venue_hos.putExtra(CATEGORY_NAME, "");
                show_all_venue_hos.putExtra(SLUG, "");
                startActivity(show_all_venue_hos);
                break;

            case R.id.ll_current_location:
                /*List<Place.Field> fields = Arrays.asList(Place.Field.ID, Place.Field.NAME, Place.Field.LAT_LNG);
                Intent intent = new Autocomplete.IntentBuilder(AutocompleteActivityMode.FULLSCREEN, fields)
                        .setTypeFilter(TypeFilter.ADDRESS)
                        .setCountry("UK")
                        .build(context);
                startActivityForResult(intent, PLACE_PICKER_REQUEST);*/

                Dexter.withContext(context)
                        .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                        .withListener(new PermissionListener() {
                            @Override
                            public void onPermissionGranted(PermissionGrantedResponse response) {
                                // permission is granted, open the camera
                                Intent stamp = new Intent(context, LookUpActivity.class);
                                stamp.putExtra(FROM_WHERE, getString(R.string.home_frag));
                                startActivityForResult(stamp, OPEN_LOOKUP_ACTIVITY);
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
                break;

        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 0) {

            if (resultCode == RESULT_OK) {
                String contents = data.getStringExtra("SCAN_RESULT");
            }
            if (resultCode == RESULT_CANCELED) {
                //handle cancel
            }
        }


        if (requestCode == PLACE_PICKER_REQUEST) {
            if (resultCode == RESULT_OK) {
                Place place = Autocomplete.getPlaceFromIntent(data);
                LatLng latLng = place.getLatLng();
                if (latLng != null) {
                    lat = latLng.latitude;
                    longi = latLng.longitude;
                    prefManager.savePreference(LATITUDE, String.valueOf(lat));
                    prefManager.savePreference(LONGITUDE, String.valueOf(longi));
                }
                binding.tvCurrentLocation.setText(getString(R.string.current_location_show) + "\n" + place.getName());
                Log.e("Place: ", "" + place.getName() + ", " + lat + " / " + longi);
            } else if (resultCode == AutocompleteActivity.RESULT_ERROR) {
                Status status = Autocomplete.getStatusFromIntent(data);
                Log.e("Place Err", status.getStatusMessage());
            } else if (resultCode == RESULT_CANCELED) {
                Log.e("Place Err", "RESULT_CANCELED");
            }
        } else if (requestCode == OPEN_LOOKUP_ACTIVITY && resultCode == RESULT_OK && data != null) {
            lat = data.getDoubleExtra(LATITUDE, lat);
            longi = data.getDoubleExtra(LONGITUDE, longi);
            String lookUpdata = data.getStringExtra(LOOKUP_ACTIVITY_CALLBACK_DATA);

            LatLng latLng = new LatLng(lat, longi);
            if (latLng != null) {
                lat = latLng.latitude;
                longi = latLng.longitude;
                prefManager.savePreference(LATITUDE, String.valueOf(lat));
                prefManager.savePreference(LONGITUDE, String.valueOf(longi));
                prefManager.savePreference(FUSED_LOCATION, lookUpdata);
            }
            binding.tvCurrentLocation.setText(lookUpdata);
            binding.tvCurrentLocationLabel.setText(context.getString(R.string.current_location_show));
        }
    }

    private void fetchHeaderSearch(String search) {
        if (isInternetOn(context)) {
            /*all 0 Retail 1  eat 2*/
            HeaderSearchRequestModel requestModel = new HeaderSearchRequestModel(String.valueOf(PRODUCT_TYPE), search, "50", prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""));
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            /*Call<HeaderSearchResponseModel> */
            callSearch = apiInterface.fetchHeaderSearchDetails(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            callSearch.enqueue(new Callback<HeaderSearchResponseModel>() {
                @Override
                public void onResponse(Call<HeaderSearchResponseModel> call, Response<HeaderSearchResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            HeaderSearchResponseModel homeResponseModel = response.body();
                            if (homeResponseModel.isStatus()) {
                                // if (homeResponseModel.getProducts() != null && homeResponseModel.getProducts().size() > 0) {
                                initSearchAdapter(homeResponseModel.getSuggestion(), homeResponseModel.getCategoriesName(), homeResponseModel.getVenuesSearchData());
                                /*} else {
                                }*/
                            }
                        } else {
                            final int httpCode = response.code();
                            DialogUtils.showAlertDialogWithSingleButton(context, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(getActivity());
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
                public void onFailure(Call<HeaderSearchResponseModel> call, Throwable t) {
                    if (call.isCanceled()) {
                        Log.e("CANCELED", "request was cancelled");
                    } else
                        showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }



    public void setDefaultTime() {
        for (int i = 0; i < timeSlot.size(); i++) {
            if (HelperClass.getComparedDate(HelperClass.formatDD_MMM_YYYY_to_YYYY_MM_DD(startDate))) {
                if (HelperClass.getCheckTimings(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()), HelperClass.formatHrMinSec_TO_hrMin(timeSlot.get(i).getTime()))) {
                    binding.tvSelectTime.setText(HelperClass.formatHrMinSec_TO_hrMin(timeSlot.get(i).getTime()));
                    break;
                }
            }
        }
    }

    public void setSpinnerForTime() { //  gives time

        popupWindow = new ListPopupWindow(context);
        List<String> timeSlots = new ArrayList<>();
        for (int i = 0; i < timeSlot.size(); i++) {
            if (HelperClass.getComparedDate(HelperClass.formatDD_MMM_YYYY_to_YYYY_MM_DD(startDate))) {
                if (HelperClass.getCheckTimings(new SimpleDateFormat("HH:mm").format(Calendar.getInstance().getTime()), HelperClass.formatHrMinSec_TO_hrMin(timeSlot.get(i).getTime()))) {
                    timeSlots.add(HelperClass.formatHrMinSec_TO_hrMin(timeSlot.get(i).getTime()));
                }
            } else {
                timeSlots.add(HelperClass.formatHrMinSec_TO_hrMin(timeSlot.get(i).getTime()));
            }

        }
        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, timeSlots);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.tvSelectTime);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                binding.tvSelectTime.setText(timeSlots.get(position));
                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }

    public void setSpinnerForGuest() {
        popupWindow = new ListPopupWindow(context);

        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, noOfGuests);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.tvNoOfGuest);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.tvNoOfGuest.setText(noOfGuests.get(position) + " People");
                nofGuest = noOfGuests.get(position);
                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }

    public void setSpinnerForShopType() {
        popupWindow = new ListPopupWindow(context);
        List<String> shopType = new ArrayList<>();
        shopType.add("ALL");
        shopType.add("SHOP");
        shopType.add("EAT");
        ArrayAdapter<String> adapter = new ArrayAdapter(context, R.layout.layout_single_text_view, shopType);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.btnShowDropDown);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.tvSearchNowHeaderLeft.setText(shopType.get(position));
                if (shopType.get(position).equals("EAT")) {
                    PRODUCT_TYPE = 2;
                } else if (shopType.get(position).equals("SHOP")) {
                    PRODUCT_TYPE = 1;
                } else PRODUCT_TYPE = 0;
                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }


    private void setViewPager() {
        binding.viewPager.setAdapter(new SliderAdapterHome(context, images, false, -1, true, new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {
                if (clickId == BANNER_CLICK) {

                }
            }
        }));
        binding.viewPager.setPadding(0, 0, 0, 0);
        binding.viewPager.setClipToPadding(false);
        binding.viewPager.setCurrentItem(0, true);
        binding.viewPager.setPageMargin(0);
        binding.indicator.setupWithViewPager(binding.viewPager, true);
        //  Timer timer = new Timer();
        //  timer.scheduleAtFixedRate(new SliderTimer(), 5000, 5000);

        handler.postDelayed(slider, 8000);
    }

    Runnable slider = new Runnable() {
        @Override
        public void run() {
            handler.removeCallbacks(slider);
            if (binding.viewPager.getCurrentItem() < images.size() - 1) {
                binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
            } else {
                binding.viewPager.setCurrentItem(0);
            }
            handler.postDelayed(slider, 5000);
        }
    };


    private void showDatePickerDialog(Calendar mCal) {
        //////////////////////////////////////////////////   if we hide, it will set today date
       /* mCal.set(Calendar.YEAR, 1982);
        mCal.set(Calendar.MONTH, 2);
        mCal.set(Calendar.DAY_OF_MONTH, 22);*/
        /////////////////////////////////////////////////////

        datePickerDialog = new DatePickerDialog(context, dateSetListener,
                mCal.get(Calendar.YEAR), mCal.get(Calendar.MONTH), mCal.get(Calendar.DAY_OF_MONTH));
        //           datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());  /// for the date prev to today date
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());  /// for the date next to today date
        datePickerDialog.show();
    }

    @Override
    public void onSuccessActionListner() {

        ((MainActivity) getContext()).getTotalCartCount();
    }

    @Override
    public void onModifierSelect(int parentPosition, int childPosition) {
        if (topCatProductsList.size() > 0) {
            Intent topProduct = new Intent(context, ProductDetailActivity.class);
            topProduct.putExtra(STORE_ID, topCatProductsList.get(parentPosition).getProducts().get(childPosition).getVenue_id());
            topProduct.putExtra(PRODUCT_ID, String.valueOf(topCatProductsList.get(parentPosition).getProducts().get(childPosition).getId()));
            topProduct.putExtra(OFFER_ID, "");
            topProduct.putExtra(BARCODE_ID, "");
            startActivity(topProduct);
        }
    }

    @Override
    public void onModifierDeselect(int pos_1st, int pos_2nd) {
    }

    private class SliderTimer extends TimerTask {

        @Override
        public void run() {
            if (getActivity() == null)
                return;
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (binding.viewPager.getCurrentItem() < bannerImageList.size() - 1) {
                        binding.viewPager.setCurrentItem(binding.viewPager.getCurrentItem() + 1);
                    } else {
                        binding.viewPager.setCurrentItem(0);
                    }
                }
            });
        }
    }

    public void clearSearchField() {
        binding.etSearchNowHeader.setText("");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacks(slider);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionsUtil.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }


    private void fetchLandingPageDatasTop() {
        if (isInternetOn(context)) {
            LandingPageRequestModel requestModel = new LandingPageRequestModel(String.valueOf(prefManager.getPreference(LOGIN_ID, 0)),
                    prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""), "");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<LandingPageTopResponseModel> call = apiInterface.fetchLandingPageDatasV2Top(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<LandingPageTopResponseModel>() {
                @Override
                public void onResponse(Call<LandingPageTopResponseModel> call, Response<LandingPageTopResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            LandingPageTopResponseModel topResponseModel = response.body();
                            if (topResponseModel.isStatus()) {

                                /*ALL LOCAL CATS*/

                                if (topResponseModel.getLocalshop() != null && topResponseModel.getLocalshop().size() > 0) {
                                    localShopCategoryList.clear();
                                    localShopCategoryList.addAll(topResponseModel.getLocalshop());
                                    setCategoryHospitalityAdapter();
                                } else {
                                    localShopCategoryList.clear();
                                    setCategoryHospitalityAdapter();
                                }


                                /*GUESTS*/
                                if (!TextUtils.isEmpty(topResponseModel.getMax_person())) {
                                    noOfGuests.clear();
                                    for (int i = 1; i <= Integer.parseInt(topResponseModel.getMax_person()); i++) {
                                        noOfGuests.add(i);
                                    }
                                } else {
                                    noOfGuests.clear();
                                }


                                /* TIME */
                                if (topResponseModel.getTimes() != null && topResponseModel.getTimes().size() > 0) {
                                    timeSlot.clear();
                                    timeSlot.addAll(topResponseModel.getTimes());
                                    setDefaultTime();
                                } else {
                                    timeSlot.clear();
                                }

                                /*retail venue*/
                                if (topResponseModel.getRetailVanues() != null && topResponseModel.getRetailVanues().size() > 0) {
                                    binding.layoutShop.setVisibility(View.VISIBLE);
                                    topVenueList.clear();
                                    topVenueList.addAll(topResponseModel.getRetailVanues());
                                    setTopVenueAdapter();
                                } else {
                                    binding.layoutShop.setVisibility(View.GONE);
                                    topVenueList.clear();
                                    binding.tvShowAllTopVenues.setVisibility(View.GONE);
                                    setTopVenueAdapter();
                                }
                                /*Eat Venue*/
                                if (topResponseModel.getSwoopeEatVanues() != null && topResponseModel.getSwoopeEatVanues().size() > 0) {
                                    binding.layoutTopCafeBar.setVisibility(View.GONE);
                                    topHospitalityVenueList.clear();
                                    topHospitalityVenueList.addAll(topResponseModel.getSwoopeEatVanues());
                                    setTopRestoCafeAdapter();
                                } else {
                                    binding.layoutTopCafeBar.setVisibility(View.GONE);
                                    topHospitalityVenueList.clear();
                                    binding.tvShowAllTopVenues.setVisibility(View.GONE);
                                    setTopRestoCafeAdapter();
                                }


                            } else {
                                showSnackBar(binding.getRoot(), getString(R.string.something_went_wrong));
                            }
                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(context, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(getActivity());
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
                public void onFailure(Call<LandingPageTopResponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), t.getLocalizedMessage());
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void fetchLandingPageDatasBot() {
        if (isInternetOn(context)) {
            LandingPageRequestModel requestModel = new LandingPageRequestModel(String.valueOf(prefManager.getPreference(LOGIN_ID, 0)),
                    prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""), "");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<LandingPageBottomResponseModel> call = apiInterface.fetchLandingPageDatasV2Bottom(prefManager.getPreference(AUTH_TOKEN, ""),
                    //  Call<LandingPageResponseModel> call = apiInterface.fetchLandingPageDatasV2(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<LandingPageBottomResponseModel>() {
                @Override
                public void onResponse(Call<LandingPageBottomResponseModel> call, Response<LandingPageBottomResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            LandingPageBottomResponseModel bottomResponseModel = response.body();
                            if (bottomResponseModel.isStatus()) {

                                /*ALL CATS */
                                if (bottomResponseModel.getCategories() != null && bottomResponseModel.getCategories().size() > 0) {
                                    categoryList.clear();
                                    categoryList.addAll(bottomResponseModel.getCategories());
                                    setCategoryAdapter();
                                } else {
                                    categoryList.clear();
                                    setCategoryAdapter();
                                }


                                /*TOP CAT OF MONTH*/
                                if (bottomResponseModel.getTopcategoriesMonth() != null && bottomResponseModel.getTopcategoriesMonth().size() > 0) {
                                    categoryOfMonthList.clear();
                                    categoryOfMonthList.addAll(bottomResponseModel.getTopcategoriesMonth());
                                    setTopSellingCategoryAdapter();
                                } else {
                                    categoryOfMonthList.clear();
                                    setTopSellingCategoryAdapter();
                                }

                                /*TOP PRO CATS*/
                                if (bottomResponseModel.getTopCatProducts() != null && bottomResponseModel.getTopCatProducts().size() > 0) {
                                    topCatProductsList.clear();
                                    topCatProductsList.addAll(bottomResponseModel.getTopCatProducts());
                                    setTopCatProductsAdapter();
                                } else {
                                    topCatProductsList.clear();
                                    setTopCatProductsAdapter();
                                }


                            } else {
                                showSnackBar(binding.getRoot(), getString(R.string.something_went_wrong));
                            }
                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(context, httpCode == 401 ? getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(getActivity());
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
                public void onFailure(Call<LandingPageBottomResponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), t.getLocalizedMessage());
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void initSearchAdapter(List<HeaderSearchResponseModel.SuggestionBean> listSuggestion, List<HeaderSearchResponseModel.CategoriesNameBean> listCategoriesName, List<HeaderSearchResponseModel.VenuesSearchDataBean> venuesSearchData) {
        if (listSuggestion!=null && listSuggestion.size()>0) {
            AutoCompleteAdapter autoCompletAdapter = new AutoCompleteAdapter(context, listSuggestion, listCategoriesName, venuesSearchData, new DrawerListner() {
                @Override
                public void onDrawerSelect(int position, int clickId) {

                    if (clickId == AUTO_COMPLETE_PRODUCT) {
                        if (PRODUCT_TYPE == 2) {
                            Intent topStore = new Intent(context, VenueDetailActivity.class);
                            //  topStore.putExtra(STORE_ID, autoCompletAdapter.getObject(position).getVenue_id());
                            topStore.putExtra(STORE_ID, listSuggestion.get(position).getVenue_id());
                            topStore.putExtra(CATEGORY_ID, "");
                            topStore.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                            startActivity(topStore);
                        } else if (PRODUCT_TYPE == 1 || PRODUCT_TYPE == 0) {
                            //  if (autoCompletAdapter.getObject(position).getProduct_type() == 1) {
                            if (listSuggestion.get(position).getProduct_type() == 1) {
                                //1 retail 2 hosptlty
                                Intent topProduct = new Intent(context, ProductDetailActivity.class);
                                topProduct.putExtra(STORE_ID, listSuggestion.get(position).getVenue_id());
                                topProduct.putExtra(PRODUCT_ID, String.valueOf(listSuggestion.get(position).getProduct_id()));
                                topProduct.putExtra(OFFER_ID, "");
                                topProduct.putExtra(BARCODE_ID, "");
                                startActivity(topProduct);
                            } else {
                                Intent topStore = new Intent(context, VenueDetailActivity.class);
                                topStore.putExtra(STORE_ID, listSuggestion.get(position).getVenue_id());
                                topStore.putExtra(CATEGORY_ID, "");
                                topStore.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                                startActivity(topStore);
                            }

                        }
                    } else if (clickId == AUTO_COMPLETE_CATEGORY) {
                        Intent in = new Intent(context, CategoryDetailsActivity.class);
                        in.putExtra(FROM_WHERE, FROM_CATEGORY_TAP);
                        in.putExtra(CATEGORY_NAME, listCategoriesName.get(position).getCat_name());
                        in.putExtra(CATEGORY_ID, String.valueOf(listCategoriesName.get(position).getId()));
                        in.putExtra(PRODUCT_TYPE_SELECTED, PRODUCT_TYPE);
                        startActivity(in);
                    } else if (clickId == AUTO_COMPLETE_VENUE) {  //
                        if (venuesSearchData.get(position).getVenue_type() == 1) {
                            Intent venue = new Intent(context, VenueDetailActivity.class);
                            venue.putExtra(STORE_ID, venuesSearchData.get(position).getVenue_id());
                            venue.putExtra(CATEGORY_ID, "");
                            venue.putExtra(FROM_WHERE, HOME_RETAIL);
                            startActivity(venue);
                        } else if (venuesSearchData.get(position).getVenue_type() == 2) {
                            Intent venue = new Intent(context, VenueDetailActivity.class);
                            venue.putExtra(STORE_ID, venuesSearchData.get(position).getVenue_id());
                            venue.putExtra(CATEGORY_ID, "");
                            venue.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                            startActivity(venue);
                        }
                    }
                }
            });
            binding.etSearchNowHeader.setThreshold(2);
            binding.etSearchNowHeader.setAdapter(autoCompletAdapter);
        } else {
            AutoCompleteVenueOnlyAdapter autoCompletAdapter = new AutoCompleteVenueOnlyAdapter(context, listCategoriesName, venuesSearchData, new DrawerListner() {
                @Override
                public void onDrawerSelect(int position, int clickId) {

                     if (clickId == AUTO_COMPLETE_CATEGORY) {
                        Intent in = new Intent(context, CategoryDetailsActivity.class);
                        in.putExtra(FROM_WHERE, FROM_CATEGORY_TAP);
                        in.putExtra(CATEGORY_NAME, listCategoriesName.get(position).getCat_name());
                        in.putExtra(CATEGORY_ID, String.valueOf(listCategoriesName.get(position).getId()));
                         in.putExtra(PRODUCT_TYPE_SELECTED, PRODUCT_TYPE);
                        startActivity(in);
                    } else if (clickId == AUTO_COMPLETE_VENUE) {
                        if (venuesSearchData.get(position).getVenue_type() == 1) {
                            Intent venue = new Intent(context, VenueDetailActivity.class);
                            venue.putExtra(STORE_ID, venuesSearchData.get(position).getVenue_id());
                            venue.putExtra(CATEGORY_ID, "");
                            venue.putExtra(FROM_WHERE, HOME_RETAIL);
                            startActivity(venue);
                        } else if (venuesSearchData.get(position).getVenue_type() == 2) {
                            Intent venue = new Intent(context, VenueDetailActivity.class);
                            venue.putExtra(STORE_ID, venuesSearchData.get(position).getVenue_id());
                            venue.putExtra(CATEGORY_ID, "");
                            venue.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                            startActivity(venue);
                        }
                    }
                }
            });
            binding.etSearchNowHeader.setThreshold(2);
            binding.etSearchNowHeader.setAdapter(autoCompletAdapter);
        }
    }


}
