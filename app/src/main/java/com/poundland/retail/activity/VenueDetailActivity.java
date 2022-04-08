package com.poundland.retail.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.activityHospitality.adapter.VenueTimingAdapter;
import com.poundland.retail.adapter.SingleStoreCategoryHospitalityListAdapter;
import com.poundland.retail.adapter.SingleStoreCategoryListAdapter;
import com.poundland.retail.adapter.SliderAdapterHome;
import com.poundland.retail.adapter.VenueFilterListAdapter;
import com.poundland.retail.adapter.VenueFilterListItemAdapter;
import com.poundland.retail.adapter.VenueProductsHospitalityAdapter;
import com.poundland.retail.adapter.VenueProductsRetailAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.SpeedyLinearLayoutManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityStoreDetailsBinding;
import com.poundland.retail.databinding.DialogShowVenueFilterBinding;
import com.poundland.retail.dialog.DialogReservatioTimeDateEntry;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.OnSeasonExpireListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.GetBrandResponseModel;
import com.poundland.retail.model.requestModel.LikeDislikeProductRequestModel;
import com.poundland.retail.model.requestModel.ProductDetailByModifierRequestModel;
import com.poundland.retail.model.requestModel.SingleVenueDetailRequestModel;
import com.poundland.retail.model.requestModel.VenueFilterDataRequestModel;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.model.responseModel.HospitalityVenueDetailResponseModel;
import com.poundland.retail.model.responseModel.HospitalityVenueProductResponseModel;
import com.poundland.retail.model.responseModel.LikeDislikeProductResponseModel;
import com.poundland.retail.model.responseModel.SingleVenueDetailResponseModel;
import com.poundland.retail.model.responseModel.VenueDetailResponseModel;
import com.poundland.retail.model.responseModel.VenueFilterDataResponseModel;
import com.poundland.retail.model.responseModel.VenueThemeResponse;
import com.poundland.retail.model.responseModel.WeekTimeModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.getDaysName;
import static com.poundland.retail.appUtils.HelperClass.getDaysName_input_YYYY_MM_dd_HH_mm;
import static com.poundland.retail.appUtils.HelperClass.getTime;
import static com.poundland.retail.appUtils.HelperClass.hideKeyboard;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.BARCODE_ID;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CATEGORY_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.CATEGORY_RETAIL;
import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.DD_MMM_YYYY_dash_in_middle;
import static com.poundland.retail.interfaces.Constants.DISTANCE;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FILTER_DATA_SELECTED;
import static com.poundland.retail.interfaces.Constants.FROM_APPLY_FILTER_TAP;
import static com.poundland.retail.interfaces.Constants.FROM_RESERVATION;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.HOME;
import static com.poundland.retail.interfaces.Constants.HOME_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.HOME_RETAIL;
import static com.poundland.retail.interfaces.Constants.HOSPITALITY_PRODUCT;
import static com.poundland.retail.interfaces.Constants.IMAGE_LINK;
import static com.poundland.retail.interfaces.Constants.IS_PRODUCT_IN_CART;
import static com.poundland.retail.interfaces.Constants.IS_VENUE_SCANNED;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.ORDER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.RESERVATION_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.UPCOMING_VENUE;
import static com.poundland.retail.interfaces.Constants.VENUE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;
import static com.poundland.retail.interfaces.Constants.VENUE_NAME;
import static com.poundland.retail.interfaces.Constants.WHOLE_FILTER_API_DATA;

public class VenueDetailActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner, SuccessActionListner, OnSeasonExpireListener {
    private static final String LIMIT = "20";
    private List<String> params;
    private ActivityStoreDetailsBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String userId;
    private List<String> listSelectedCategory;
    private List<VenueFilterDataResponseModel.FilterBean> filterListData;
    private List<SingleVenueDetailResponseModel.ProductsBean.DataBean> productList;
    private String venueId = "";
    private String category_id = "";
    private String venue_name;
    private VenueDetailActivity instance = null;
    private ArrayList<String> imageList;
    private Dialog dialog;
    private KProgressHUD loaderDialog;
    private String fromWhere;
    private boolean isVenueScanned;
    private Uri data;
    private boolean isUpcomingStore = false;
    private final boolean isShow = false;
    private String search = "";
    private final int isShowHos = 0;
    private String favProduct;
    private VenueDetailResponseModel retailVenueResponseModel;
    private SingleVenueDetailResponseModel retailVenueProductResponseModel;
    private HospitalityVenueProductResponseModel hospitalityVenueProductResponseModel;
    private HospitalityVenueDetailResponseModel hospitalityVenueResponseModel;
    private List<VenueDetailResponseModel.VenueDetailsBean.CategoriesBean> categoryList;
    private List<HospitalityVenueDetailResponseModel.CategoryBean> categoryListHospitality;
    private List<HospitalityVenueProductResponseModel.ProductsBean.DataBean> productListHospitality;
    private final DateFormat formate_HHmm = new SimpleDateFormat("HH:mm");
    private VenueThemeResponse.Data themeColor;
    private List<String> subcategory;
    private String reservationId;
    private ListPopupWindow popupWindow;
    private boolean isPopup;
    private String PATTERN_START_WITH_DATE_NO_YEAR = "dd MMM";
    private SimpleDateFormat sdfSameDayShipping = new SimpleDateFormat(Constants.HHmm);
    private static CountDownTimer mCountDownTimer = null;
    /////////// for move fab button on screen
    private final short touchMoveFactor = 10;
    private final short touchTimeFactor = 300;
    private final PointF actionDownPoint = new PointF(0f, 0f);
    private final long touchDownTime = 0L;
    private int xDelta;
    private int yDelta;
    private int scroolPosition = -1;
    private boolean disableBack;
    ///////////////

    private VenueProductsRetailAdapter productsAdapter;
    private VenueProductsHospitalityAdapter productsHospitalityAdapter;

    private VenueFilterListAdapter filterListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_store_details);
        prefManager = PrefManager.getInstance(this);

        loaderDialog = DialogUtils.getCustomDialog(this);

        if (Intent.ACTION_VIEW.equals(getIntent().getAction())) {
            data = getIntent().getData();
            if (data == null) {
                finish();
            }

            String scheme = data.getScheme();
            String host = data.getHost();
            int port = data.getPort();

            if (host.equals("retail") && scheme.equals("swooperetail")) {
                params = data.getPathSegments();
                venueId = data.getQueryParameter("venue_id");
            } else if ((host.equals("swooperetail.com") && scheme.equals("https"))
                    || (host.equals("swooposretailuk.com") && scheme.equals("https"))
                    || (host.equals("swoopelocaltesting.com") && scheme.equals("https"))) {

                params = data.getPathSegments();
                venueId = params.get(params.size() - 3);
            }
            if (venueId == null) {
                finish();
            }
        } else {
            venueId = getIntent().getStringExtra(STORE_ID);
        }
        if (prefManager.getPreference(venueId) != null) {
            themeColor = new Gson().fromJson(prefManager.getPreference(venueId).toString(), VenueThemeResponse.Data.class);
        }

        binding.nestedScroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) binding.nestedScroll.getChildAt(binding.nestedScroll.getChildCount() - 1);
                int diff = (view.getBottom() - (binding.nestedScroll.getHeight() + binding.nestedScroll.getScrollY()));
                if (diff == 0) {
                    // your pagination code
                    if (!isLoading && pageNumber <= totalPage) {
                        isLoading = true;
                        if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
                            getHospitalityVenueProducts(true);
                        } else if (fromWhere.equals(HOME_RETAIL)) {
                            getRetailsProduct(true);
                        }

                    }
                }
            }
        });


        binding.etSearchNowHeader.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    search = binding.etSearchNowHeader.getText().toString();
                    hideKeyboard(instance);
                    if (search.length() > 0) {
                        isLoading = true;
                        pageNumber = 1;
                        ///////////////////////
                        if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
                            getHospitalityVenueProducts(false);
                        } else if (fromWhere.equals(HOME_RETAIL)) {
                            //  getVenueDetails(false);
                            getRetailsProduct(false);
                        }

                        /////////////////////////////
                    }
                    handled = true;
                }
                return handled;
            }

        });

        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                    Intent intentH = new Intent(instance, MyCartHospitalityActivity.class);
                    startActivity(intentH);
                } else {
                    Intent intentR = new Intent(instance, MyCartActivity.class);
                    startActivity(intentR);
                }
            }
        });

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
                } else {
                    binding.ivClose.setVisibility(View.GONE);
                }

            }
        });

        /* binding.rlFram.setOnTouchListener(onTouchListener());*/
    }

    @Override
    protected void onResume() {
        super.onResume();
        changeTheme();
        init();
        getTotalCartCount();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        // Toast.makeText(instance, "vvnbvvnv", Toast.LENGTH_SHORT).show();
    }

    public void changeTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = this.getWindow();
            if (themeColor != null) {

                int statusBarColor = Color.parseColor(themeColor.getHeaderColor() != null ? themeColor.getHeaderColor() : "#54a6bc");
                if (statusBarColor == Color.BLACK && window.getNavigationBarColor() == Color.BLACK) {
                    window.clearFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                } else {
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                }
                window.setStatusBarColor(statusBarColor);


//                changing toolbar color
                binding.rlTop.setBackgroundColor(statusBarColor/*Color.parseColor(themeColor.getHeaderColor())*/);

                if (themeColor.getHeaderFontColor() != null) {
                    int color = Color.parseColor(themeColor.getHeaderFontColor());
                    // int colorHeader = themeColor.getHeaderColor() ;
                    binding.ivBack.setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY);
                    binding.title.setTextColor(color);
                    binding.ivFilter.setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY);
                    binding.ivWatch.setColorFilter(color, android.graphics.PorterDuff.Mode.MULTIPLY);
                    binding.ivCall.setColorFilter(statusBarColor, android.graphics.PorterDuff.Mode.SRC_IN);
                    binding.tvVenueName.setTextColor(color);
                    binding.tvVenueFoods.setTextColor(color);
                    binding.tvRatingCountCard.setTextColor(color);
                    binding.tvDistanceInMiles.setTextColor(color);
                    binding.tvOpeningTiming.setTextColor(color);
                    binding.tvClosingTiming.setTextColor(color);
                    HelperClass.setTextViewTintDrawableColor(binding.tvDistanceInMiles, color);
                    binding.ivCart.setColorFilter(color, android.graphics.PorterDuff.Mode.SRC_IN);

                }

                binding.tvCallNow.setTextColor(statusBarColor);
                binding.tvDelivery.setTextColor(statusBarColor);
                binding.tvClickCollect.setTextColor(statusBarColor);
                binding.tvSameDayDelivery.setTextColor(statusBarColor);
                binding.tvTable.setTextColor(statusBarColor);

                if (themeColor.getHeaderColor() != null) {
                    String op = themeColor.getHeaderColor().replace("#", "#E6");
                    binding.layoutVenueDetails.setBackgroundColor(Color.parseColor(op));
                }


            }
        }
    }

    private void init() {
        instance = this;
        isLoading = true;
        pageNumber = 1;
        //    binding.mainFram.setVisibility(View.GONE);
        binding.rlFram.setVisibility(View.GONE);
        if (getIntent() != null && getIntent().getStringExtra(FROM_WHERE) != null) {
            fromWhere = getIntent().getStringExtra(FROM_WHERE);
            isVenueScanned = getIntent().getBooleanExtra(IS_VENUE_SCANNED, false);
            if (isVenueScanned) {
                disableBack = true;
            }
            if (fromWhere.equals(UPCOMING_VENUE)) {
                isUpcomingStore = true;
            } else if (fromWhere.equals(HOME)) {
                category_id = getIntent().getStringExtra(CATEGORY_ID);
            } else if (fromWhere.equals(HOME_HOSPITALITY)) {
                category_id = getIntent().getStringExtra(CATEGORY_ID);
            } else if (fromWhere.equals(FROM_RESERVATION)) {
                reservationId = getIntent().getStringExtra(RESERVATION_ID);  /////// RESERVATION_ID
                if (!reservationId.isEmpty()) {
                    disableBack = true;
                }
                category_id = getIntent().getStringExtra(CATEGORY_ID);
            } else if (fromWhere.equals(HOME_RETAIL)) {
                category_id = getIntent().getStringExtra(CATEGORY_ID);
            }/* else if (fromWhere.equals(FROM_SCAN_QR)) {

            }*/
        }

        listSelectedCategory = new ArrayList<>();
        filterListData = new ArrayList<>();
        categoryList = new ArrayList<>();
        categoryListHospitality = new ArrayList<>();
        productListHospitality = new ArrayList<>();
        productList = new ArrayList<>();
        imageList = new ArrayList<>();

        getVenueTheme();

        setListeners();


    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.llCallNow.setOnClickListener(this);
        binding.tvDistanceInMiles.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.ivFilter.setOnClickListener(this);
        binding.tvBookTable.setOnClickListener(this);
        binding.ivFavo.setOnClickListener(this);
        binding.ivSearchNowHeader.setOnClickListener(this);
        binding.rlTimings.setOnClickListener(this);
        binding.ivClose.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
    }

    private void setViewPager() {
        binding.viewPager.setAdapter(new SliderAdapterHome(this, imageList, false, STORE, true, new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {

            }
        }));
        binding.viewPager.setPadding(0, 0, 0, 0);
        binding.viewPager.setClipToPadding(false);
        binding.viewPager.setCurrentItem(0, true);
        binding.viewPager.setPageMargin(0);
        binding.indicator.setupWithViewPager(binding.viewPager, true);
    }

    private void setCategoryHospitalityAdapter() {
        if (scroolPosition > 0)
            return;
        SingleStoreCategoryHospitalityListAdapter hospitalityListAdapter = new SingleStoreCategoryHospitalityListAdapter(this, categoryListHospitality, 0, this, scroolPosition);
        // binding.rvCategoryList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvCategoryList.setLayoutManager(new SpeedyLinearLayoutManager(instance, SpeedyLinearLayoutManager.HORIZONTAL, false));
        binding.rvCategoryList.setNestedScrollingEnabled(false);
        binding.rvCategoryList.setAdapter(hospitalityListAdapter);
        /*if (scroolPosition != -1 && categoryListHospitality.size() >= scroolPosition) {
           // binding.rvCategoryList.smoothScrollToPosition(scroolPosition);
            binding.rvCategoryList.scrollToPosition(scroolPosition);
        }*/


    }

    private void setCategoryRetailAdapter(String venueType) {
        if (scroolPosition > 0)
            return;
        SingleStoreCategoryListAdapter categoryListAdapter = new SingleStoreCategoryListAdapter(this, categoryList, retailVenueResponseModel.getVenue_details().getSameDayShipping(), this, scroolPosition);
        binding.rvCategoryList.setLayoutManager(new SpeedyLinearLayoutManager(instance, SpeedyLinearLayoutManager.HORIZONTAL, false));
        // binding.rvCategoryList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        binding.rvCategoryList.setNestedScrollingEnabled(false);
        binding.rvCategoryList.setAdapter(categoryListAdapter);

       /* if (scroolPosition != -1 && categoryList.size() >= scroolPosition) {
          //  binding.rvCategoryList.smoothScrollToPosition(scroolPosition);
            binding.rvCategoryList.scrollToPosition(scroolPosition);
        }*/
    }

    private void setDealsAdapter() {
        // DealsOnVenueAdapter adapter = new DealsOnVenueAdapter(this, bestOffersList, this);
        /*final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvDealsList.setLayoutManager(layoutManager);
        binding.rvDealsList.setNestedScrollingEnabled(false);
        binding.rvDealsList.setAdapter(adapter);*/
    }

    private void setAllRetailProductAdapter() {
        productsAdapter = new VenueProductsRetailAdapter(this, themeColor, productList, this, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvProductList.setLayoutManager(layoutManager);
        binding.rvProductList.setNestedScrollingEnabled(false);
        binding.rvProductList.setHasFixedSize(true);
        binding.rvProductList.setItemViewCacheSize(20);
        binding.rvProductList.setDrawingCacheEnabled(true);
        binding.rvProductList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        binding.rvProductList.setAdapter(productsAdapter);
    }

    private void setAllHospitalityProductAdapter() {
        productsHospitalityAdapter = new VenueProductsHospitalityAdapter(this, themeColor, productListHospitality,
                this, this, venue_name, reservationId);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvProductList.setLayoutManager(layoutManager);
        binding.rvProductList.setNestedScrollingEnabled(false);
        binding.rvProductList.setHasFixedSize(true);
        binding.rvProductList.setItemViewCacheSize(20);
        binding.rvProductList.setDrawingCacheEnabled(true);
        binding.rvProductList.setDrawingCacheQuality(View.DRAWING_CACHE_QUALITY_HIGH);
        binding.rvProductList.setAdapter(productsHospitalityAdapter);
        /*binding.rvProductList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) //check for scroll down
                {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && pageNumber <= totalPage) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            isLoading = true;
                            //   getHospitalityVenueProducts(true);  /// not being use from here
                        }
                    }
                }
            }
        });*/
    }

    public void setVenueTimeTable() {
        popupWindow = new ListPopupWindow(instance);
        // WeekTimeModel model = new WeekTimeModel();
        List<WeekTimeModel.VenueWeekTimesBean> timeList = new ArrayList<>();
        if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
            for (int i = 0; i < hospitalityVenueResponseModel.getVenue_week_times().size(); i++) {
                WeekTimeModel.VenueWeekTimesBean timeModel = new WeekTimeModel.VenueWeekTimesBean();
                timeModel.setDay_name(hospitalityVenueResponseModel.getVenue_week_times().get(i).getDay_name());
                timeModel.setOpening_time(hospitalityVenueResponseModel.getVenue_week_times().get(i).getOpening_time());
                timeModel.setClosing_time(hospitalityVenueResponseModel.getVenue_week_times().get(i).getClosing_time());
                timeList.add(timeModel);
            }
        } else if (fromWhere.equals(HOME_RETAIL)) {
            for (int i = 0; i < retailVenueResponseModel.getVenue_week_times().size(); i++) {
                WeekTimeModel.VenueWeekTimesBean timeModel = new WeekTimeModel.VenueWeekTimesBean();
                timeModel.setDay_name(retailVenueResponseModel.getVenue_week_times().get(i).getDay_name());
                timeModel.setOpening_time(retailVenueResponseModel.getVenue_week_times().get(i).getOpening_time());
                timeModel.setClosing_time(retailVenueResponseModel.getVenue_week_times().get(i).getClosing_time());
                timeList.add(timeModel);
            }
        }


        VenueTimingAdapter adapter = new VenueTimingAdapter(instance, 0, timeList, themeColor);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.rlTimings);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_timings:
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    setVenueTimeTable();
                    isPopup = true;
                }
                break;
            case R.id.iv_back:

                if (disableBack) {
                    DialogUtils.showAlertDialog(instance, "Do You Want to exit ?"/*getString(R.string.logout_message)*/, new OnDialogClickListener() {
                        @Override
                        public void onPositiveClick() {
                            finishAffinity();
                            //  finish();

                          /*  Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);*/

                        }

                        @Override
                        public void onNegativeClick() {

                        }
                    });
                } else {
                    finish();
                }
                break;

            case R.id.iv_close:

                if (binding.etSearchNowHeader.getText().toString().length() > 0) {
                    scroolPosition = -1;
                    subcategory = new ArrayList<>();
                    hideKeyboard(instance);
                    pageNumber = 1;
                    isLoading = true;
                    binding.etSearchNowHeader.setText("");
                    search = "";

                    if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
                        getHospitalityVenueProducts(false);
                    } else if (fromWhere.equals(HOME_RETAIL)) {
                        // getVenueDetails(false);
                        getRetailsProduct(false);
                    }
                }

                break;
            case R.id.iv_search_now_header:
                hideKeyboard(instance);
                search = binding.etSearchNowHeader.getText().toString();
                if (search.length() > 0) {
                    isLoading = true;
                    pageNumber = 1;
                    if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
                        getHospitalityVenueProducts(false);
                    } else if (fromWhere.equals(HOME_RETAIL)) {
                        //   getVenueDetails(false);
                        getRetailsProduct(false);
                    }
                }
                break;

            case R.id.tv_distance_in_miles:

                if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
                    Uri gmmIntent = Uri.parse("geo:" + hospitalityVenueResponseModel.getVenues().getLatitude() + "," + hospitalityVenueResponseModel.getVenues().getLongitude());
                    Intent mapIntent_ = new Intent(Intent.ACTION_VIEW, gmmIntent);
                    mapIntent_.setPackage("com.google.android.apps.maps");
                    if (mapIntent_.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent_);
                    }
                } else if (fromWhere.equals(HOME_RETAIL)) {
                    Uri gmmIntent = Uri.parse("geo:" + retailVenueResponseModel.getVenue_details().getLatitude() + "," + retailVenueResponseModel.getVenue_details().getLongitude());
                    Intent mapIntent_ = new Intent(Intent.ACTION_VIEW, gmmIntent);
                    mapIntent_.setPackage("com.google.android.apps.maps");
                    if (mapIntent_.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent_);
                    }
                }


                break;

            case R.id.tv_address_card:

                if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
                    Uri gmmIntent = Uri.parse("geo:" + hospitalityVenueResponseModel.getVenues().getLatitude() + "," + hospitalityVenueResponseModel.getVenues().getLongitude());
                    Intent mapIntent_ = new Intent(Intent.ACTION_VIEW, gmmIntent);
                    mapIntent_.setPackage("com.google.android.apps.maps");
                    if (mapIntent_.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent_);
                    }
                } else if (fromWhere.equals(HOME_RETAIL)) {
                    Uri gmmIntent = Uri.parse("geo:" + retailVenueResponseModel.getVenue_details().getLatitude() + "," + retailVenueResponseModel.getVenue_details().getLongitude());
                    Intent mapIntent_ = new Intent(Intent.ACTION_VIEW, gmmIntent);
                    mapIntent_.setPackage("com.google.android.apps.maps");
                    if (mapIntent_.resolveActivity(getPackageManager()) != null) {
                        startActivity(mapIntent_);
                    }
                }


                break;

            case R.id.tv_book_table:
                /*new DialogReservatioGuestEntry(instance,
                        formate_HHmm.format(Calendar.getInstance().getTime()), venueId, HelperClass.getCurrentDate(DD_MMM_YYYY_dash_in_middle),
                        1, 0, this).show();*/

                String address = "";
                String venueName = "";
                if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
                    address = hospitalityVenueResponseModel.getVenues().getAddress_1();
                    venueName = hospitalityVenueResponseModel.getVenues().getVenue_name();

                } else if (fromWhere.equals(HOME_RETAIL)) {
                    address = retailVenueResponseModel.getVenue_details().getAddress_1();
                    venueName = retailVenueResponseModel.getVenue_details().getVenue_name();
                }

                DialogReservatioTimeDateEntry bottomSheetDialog = DialogReservatioTimeDateEntry.newInstance(this, instance,
                        formate_HHmm.format(Calendar.getInstance().getTime()),
                        venueId, HelperClass.getCurrentDate(DD_MMM_YYYY_dash_in_middle),
                        2, 0, imageList.size() > 0 ? ApiRequestUrl.BASE_URL_VENUE + imageList.get(0) : "",
                        address, venueName, new OnSeasonExpireListener() {
                            @Override
                            public void onSeasonExpire() {

                            }
                        });
                bottomSheetDialog.show(getSupportFragmentManager(), "Bottom Sheet Dialog Fragment");

                break;
            case R.id.ll_call_now:
                if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
                    Intent inCall_me = new Intent(Intent.ACTION_DIAL);
                    inCall_me.setData(Uri.parse("tel:" + hospitalityVenueResponseModel.getVenues().getPhone_number()));
                    startActivity(inCall_me);
                } else if (fromWhere.equals(HOME_RETAIL)) {
                    Intent inCall_me = new Intent(Intent.ACTION_DIAL);
                    inCall_me.setData(Uri.parse("tel:" + retailVenueResponseModel.getVenue_details().getPhone_number()));
                    startActivity(inCall_me);
                }

                break;
            case R.id.ll_mesage:
                Intent intent = new Intent(this, ChatDetailActivity.class);
                intent.putExtra(ORDER_ID, "");
                intent.putExtra(VENUE_ID, venueId);
                intent.putExtra(CONTACT_NO, retailVenueResponseModel.getVenue_details().getPhone_number());
                intent.putExtra(VENUE, venue_name);
                intent.putExtra(IMAGE_LINK, imageList.size() > 0 ? ApiRequestUrl.BASE_URL_VENUE + imageList.get(0) : "");
                startActivity(intent);
                break;
            case R.id.iv_message_card:

                if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
                    Intent intent_message = new Intent(this, ChatDetailActivity.class);
                    intent_message.putExtra(ORDER_ID, "");
                    intent_message.putExtra(VENUE_ID, venueId);
                    // intent_message.putExtra(CONTACT_NO, venueDetaiHospitalitylResponseModel.getVenue_details().getPhone_number());
                    intent_message.putExtra(VENUE, venue_name);
                    intent_message.putExtra(IMAGE_LINK, imageList.size() > 0 ? ApiRequestUrl.BASE_URL_VENUE + imageList.get(0) : " ");
                    //  startActivity(intent_message);
                } else if (fromWhere.equals(HOME_RETAIL)) {
                    Intent intent_message = new Intent(this, ChatDetailActivity.class);
                    intent_message.putExtra(ORDER_ID, "");
                    intent_message.putExtra(VENUE_ID, venueId);
                    intent_message.putExtra(CONTACT_NO, retailVenueResponseModel.getVenue_details().getPhone_number());
                    intent_message.putExtra(VENUE, venue_name);
                    intent_message.putExtra(IMAGE_LINK, imageList.size() > 0 ? ApiRequestUrl.BASE_URL_VENUE + imageList.get(0) : " ");
                    startActivity(intent_message);
                }


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
            case R.id.iv_filter:
                getVenueFilterData();
                break;
            case R.id.iv_favo:
                likeDislike();
                break;
        }
    }

    private void getVenueTheme() {
        if (isInternetOn(this)) {

            if (loaderDialog != null)
                loaderDialog.show();

            SingleVenueDetailRequestModel requestModel = new SingleVenueDetailRequestModel(venueId);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<VenueThemeResponse> call = apiInterface.getVenueTheme(requestModel);
            call.enqueue(new Callback<VenueThemeResponse>() {
                @Override
                public void onResponse(Call<VenueThemeResponse> call, Response<VenueThemeResponse> response) {

                    try {
                        //  binding.swipeRefresh.setRefreshing(false);

                        if (response.isSuccessful()) {
                            if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
                                getHospitalityVenueDetails(false);
                                getHospitalityVenueProducts(false);
                            } else if (fromWhere.equals(HOME_RETAIL)) {
                                getVenueDetails(false);
                                getRetailsProduct(false);
                            }
                            if (response.body().getStatus()) {
                                prefManager.savePreference(venueId, new Gson().toJson(response.body().getData()));
                                themeColor = response.body().getData();
                                changeTheme();
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
                public void onFailure(Call<VenueThemeResponse> call, Throwable t) {
//                    binding.swipeRefresh.setRefreshing(false);
                    if (loaderDialog != null)
                        loaderDialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
//            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void getVenueDetails(boolean isMore) {
        if (isInternetOn(this)) {

            if (loaderDialog != null && !loaderDialog.isShowing())
                loaderDialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");

            SingleVenueDetailRequestModel requestModel = new SingleVenueDetailRequestModel(venueId, latitude, longitude, category_id, "", search);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<VenueDetailResponseModel> call = apiInterface.getVenueDetail(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel, "1");
            call.enqueue(new Callback<VenueDetailResponseModel>() {
                @Override
                public void onResponse(Call<VenueDetailResponseModel> call, Response<VenueDetailResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (loaderDialog != null)
                            loaderDialog.dismiss();

                        if (response.isSuccessful()) {
                            retailVenueResponseModel = response.body();

                            if (retailVenueResponseModel.isStatus()) {
                                isLoading = false;

                                if (retailVenueResponseModel.getIsProductInCart() == 1) {
                                    binding.rlFram.setVisibility(View.VISIBLE);
                                }

                                setData(retailVenueResponseModel);
                                setSameDayDelivery(retailVenueResponseModel);

                                if (retailVenueResponseModel.getVenue_details().getCategories() != null && retailVenueResponseModel.getVenue_details().getCategories().size() > 0) {
                                    categoryList.clear();
                                    categoryList.addAll(retailVenueResponseModel.getVenue_details().getCategories());
                                    setCategoryRetailAdapter(HOME_RETAIL);
                                } else {
                                    categoryList.clear();
                                    setCategoryRetailAdapter(HOME_RETAIL);
                                }

                            } else {
                                showSnackBar(binding.getRoot(), retailVenueResponseModel.getMessage());
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
                public void onFailure(Call<VenueDetailResponseModel> call, Throwable t) {
                    binding.swipeRefresh.setRefreshing(false);
                    if (loaderDialog != null)
                        loaderDialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void getHospitalityVenueDetails(boolean isMore) {
        if (isInternetOn(this)) {
            if (loaderDialog != null && !loaderDialog.isShowing())
                loaderDialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");

            SingleVenueDetailRequestModel requestModel = new SingleVenueDetailRequestModel
                    (isShowHos, TextUtils.isEmpty(category_id) ? 0 : Integer.parseInt(category_id), search, favProduct, venueId, subcategory, latitude, longitude, LIMIT);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<HospitalityVenueDetailResponseModel> call = apiInterface.hospitalityVenueDetail(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel, "1");
            call.enqueue(new Callback<HospitalityVenueDetailResponseModel>() {
                @Override
                public void onResponse(Call<HospitalityVenueDetailResponseModel> call, Response<HospitalityVenueDetailResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (loaderDialog != null)
                            loaderDialog.dismiss();

                        if (response.isSuccessful()) {
                            hospitalityVenueResponseModel = response.body();
                            if (hospitalityVenueResponseModel.isStatus()) {

                                isLoading = false;
                                setDataHospitality(hospitalityVenueResponseModel);


                                if (hospitalityVenueResponseModel.getCategory() != null && hospitalityVenueResponseModel.getCategory().size() > 0) {
                                    categoryListHospitality.clear();
                                    categoryListHospitality.addAll(hospitalityVenueResponseModel.getCategory());
                                    setCategoryHospitalityAdapter();
                                } else {
                                    categoryListHospitality.clear();
                                    setCategoryHospitalityAdapter();
                                }

                            } else {
                                showSnackBar(binding.getRoot(), hospitalityVenueResponseModel.getMessage());

                                /*if (hospitalityVenueResponseModel.getMessage().equalsIgnoreCase(getString(R.string.venue_not_found))) {
                                    DialogUtils.showAlertDialogWithSingleButton(instance, hospitalityVenueResponseModel.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {

                                            finish();
                                        }

                                        @Override
                                        public void onNegativeClick() {

                                        }
                                    });
                                }
*/

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
                public void onFailure(Call<HospitalityVenueDetailResponseModel> call, Throwable t) {
                    binding.swipeRefresh.setRefreshing(false);
                    if (loaderDialog != null)
                        loaderDialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void setData(VenueDetailResponseModel data) {

        if (data.getVenue_details().getPhone_number().isEmpty()) {
            binding.llCallNow.setAlpha(.7f);
        }
        venue_name = data.getVenue_details().getVenue_name();
        binding.tvVenueName.setText(data.getVenue_details().getVenue_name());
        binding.tvVenueFoods.setText(data.getVenue_details().getVenue_category());

        if (data.getVenue_details().getCollection() == 0 && data.getVenue_details().getDelivery() == 0) {
            binding.llSameDayDelivery.setVisibility(View.VISIBLE);
            binding.llSameDayDelivery.setAlpha(.7f);
        } else {
            if (data.getVenue_details().getSameDayShipping() == 1) {
                binding.llSameDayDelivery.setVisibility(View.VISIBLE);
            } else if (data.getVenue_details().getSameDayShipping() == 2) {
                binding.tvSameDayDelivery.setText(getString(R.string.next_day_delivery));
                binding.llSameDayDelivery.setVisibility(View.VISIBLE);
            } else {
                binding.llSameDayDelivery.setVisibility(View.VISIBLE);
                binding.llSameDayDelivery.setAlpha(.7f);
            }
        }


        if (data.getVenue_details().getDelivery() == 1) {
            binding.llDelivery.setVisibility(View.VISIBLE);
        } else {
            binding.llDelivery.setVisibility(View.VISIBLE);
            binding.llDelivery.setAlpha(.7f);
        }

        if (data.getVenue_details().getCollection() == 1) {
            binding.llCollection.setVisibility(View.VISIBLE);
        } else {
            binding.llCollection.setVisibility(View.VISIBLE);
            binding.llCollection.setAlpha(.7f);
        }

        if (data.getVenue_details().isIsWishlisted())
            binding.ivFavo.setColorFilter(ContextCompat.getColor(instance, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);


        if (data.getVenue_details().getIsClose().equals("0")) {
            binding.tvTimingStatus.setText(getString(R.string.open_caps));
            binding.tvTimingStatus.setBackgroundResource(R.drawable.green_filled_rect);
            binding.tvOpeningTiming.setText(getTime(data.getVenue_details().getOpening_time()));
            binding.tvClosingTiming.setText(" - " + getTime(data.getVenue_details().getClosing_time()));
        } else {
            binding.tvTimingStatus.setText(getString(R.string.close_caps));
            binding.tvTimingStatus.setBackgroundResource(R.drawable.app_colored_filled_rect_100);
            binding.tvOpeningTiming.setText("Next Open-" + getDaysName_input_YYYY_MM_dd_HH_mm(data.getVenue_details().getOpening_time()) + " " + getTime(data.getVenue_details().getOpening_time()));
            // binding.tvClosingTiming.setText(" - " + getTime(data.getVenue_details().getClosing_time()));
        }
        //  binding.tvClosingTiming.setText("Closing-" + getTime(data.getVenue_details().getClosing_time()));
        binding.tvDistanceInMiles.setText(data.getVenue_details().getDistance() + " " + getString(R.string.miles));

        if (!TextUtils.isEmpty(data.getVenue_details().getStars().getVenue_rating_value()) && Float.parseFloat(data.getVenue_details().getStars().getVenue_rating_value()) > 0) {
            binding.rbRating.setRating(Float.parseFloat(data.getVenue_details().getStars().getVenue_rating_value()));
        } else binding.rbRating.setRating(5f);

        //  binding.tvRatingCountCard.setText("(" + data.getVenue_details().getStars().getCount() + " Ratings)");

        if (data.getVenue_details().getVenue_images() != null && data.getVenue_details().getVenue_images().size() > 0) {
            imageList.clear();
            imageList.addAll(data.getVenue_details().getVenue_images());
            setViewPager();
        }
    }

    private void setDataHospitality(HospitalityVenueDetailResponseModel data) {

        if (hospitalityVenueResponseModel.getVenues().getIsProductInCart() == 1) {
            binding.rlFram.setVisibility(View.VISIBLE);
        }


        if (data.getVenues().getPhone_number().isEmpty()) {
            binding.llCallNow.setAlpha(.7f);
        }

        venue_name = data.getVenues().getVenue_name();
        binding.tvVenueName.setText(venue_name);
        binding.tvVenueFoods.setText(data.getVenues().getVenue_category());


        if (data.getVenues().getTiming().getIs_venue_open() == 1) {
            if (data.getVenues().getIs_booking_allow() == 1) {
                binding.rlBookTable.setVisibility(View.VISIBLE);
                binding.llTable.setVisibility(View.VISIBLE);
            } else {
                binding.rlBookTable.setVisibility(View.GONE);
                binding.llTable.setVisibility(View.GONE);
            }

            if (data.getVenues().getDelivery() == 1) {
                binding.llDelivery.setVisibility(View.VISIBLE);
            } else {
                binding.llDelivery.setVisibility(View.VISIBLE);
                binding.llDelivery.setAlpha(.7f);
            }

            if (data.getVenues().getCollection() == 1) {
                binding.llCollection.setVisibility(View.VISIBLE);
            } else {
                binding.llCollection.setVisibility(View.VISIBLE);
                binding.llCollection.setAlpha(.7f);
            }

        } else {
            binding.rlBookTable.setVisibility(View.GONE);
            binding.llTable.setVisibility(View.GONE);
            binding.llDelivery.setVisibility(View.VISIBLE);
            binding.llDelivery.setAlpha(.7f);
            binding.llCollection.setVisibility(View.VISIBLE);
            binding.llCollection.setAlpha(.7f);
        }


        if (data.getVenues().getIs_like() == 1) {
            binding.ivFavo.setColorFilter(ContextCompat.getColor(instance, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
        } else
            binding.ivFavo.setColorFilter(ContextCompat.getColor(instance, R.color.hint_grey), android.graphics.PorterDuff.Mode.SRC_IN);

        if (data.getVenues().getTiming().getIsClose().equals("0")) {
            binding.tvTimingStatus.setText(getString(R.string.open_caps));
            binding.tvTimingStatus.setBackgroundResource(R.drawable.green_filled_rect);
            binding.tvOpeningTiming.setText(/*"Opening-" +*/ getTime(data.getVenues().getTiming().getOpening_time()));
            binding.tvClosingTiming.setText(" - " + getTime(data.getVenues().getTiming().getClosing_time()));
        } else {
            binding.tvTimingStatus.setText(getString(R.string.close_caps));
            binding.tvTimingStatus.setBackgroundResource(R.drawable.app_colored_filled_rect_100);
            binding.tvOpeningTiming.setText("Next Open-" + getDaysName(data.getVenues().getTiming().getOpening_time()) + " " + getTime(data.getVenues().getTiming().getOpening_time()));
        }

        binding.tvDistanceInMiles.setText(data.getVenues().getDistance() != null ? data.getVenues().getDistance() + " " + getString(R.string.miles) : " " + getString(R.string.miles));
        if (!TextUtils.isEmpty(data.getVenues().getRate()) && Float.parseFloat(data.getVenues().getRate()) > 0) {
            binding.rbRating.setRating(Float.parseFloat(data.getVenues().getRate()));
        } else binding.rbRating.setRating(5f);
        // binding.tvRatingCountCard.setText("(" + data.getVenues().getTotal_rating_count() + " Ratings)");


        if (data.getVenues().getVenue_images() != null) {
            imageList.clear();
            imageList.addAll(data.getVenues().getVenue_images());
            setViewPager();
        }
    }

    @Override
    public void onRefresh() {
        subcategory = new ArrayList<>();
        hideKeyboard(instance);
        scroolPosition = -1;
        pageNumber = 1;
        isLoading = true;
        binding.etSearchNowHeader.setText("");
        search = "";
        category_id = "";

        if (fromWhere.equals(HOME_HOSPITALITY) || fromWhere.equals(FROM_RESERVATION)) {
            getHospitalityVenueDetails(false);
            getHospitalityVenueProducts(false);
        } else if (fromWhere.equals(HOME_RETAIL)) {
            getVenueDetails(false);
            getRetailsProduct(false);
        }


    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        if (clickType == TOP_OFFER) {
           /* Intent topProduct = new Intent(this, ProductDetailActivity.class);
            topProduct.putExtra(STORE_ID, bestOffersList.get(position).getVenue_id());
            topProduct.putExtra(PRODUCT_ID, String.valueOf(bestOffersList.get(position).getProduct_id()));
            topProduct.putExtra(OFFER_ID, String.valueOf(bestOffersList.get(position).getId()));
            topProduct.putExtra(BARCODE_ID, "");
            startActivity(topProduct);*/
        } else if (clickType == PRODUCT) {
            scroolPosition = -1;
            Intent product = new Intent(this, ProductDetailActivity.class);
            product.putExtra(STORE_ID, productList.get(position).getVenue_id());
            product.putExtra(PRODUCT_ID, String.valueOf(productList.get(position).getId()));
            product.putExtra(OFFER_ID, String.valueOf(productList.get(position).getOffer_id()));
            product.putExtra(BARCODE_ID, "");
            startActivity(product);
        } else if (clickType == CATEGORY_HOSPITALITY) { // when we tap on hospitality category
            binding.etSearchNowHeader.setText("");
            search = "";
            scroolPosition = position;
            pageNumber = 1;
            subcategory = new ArrayList<>();
            subcategory.add(String.valueOf(categoryListHospitality.get(position).getId()));
            if (categoryListHospitality.get(position).getSub_category() != null) {
                for (int i = 0; i < categoryListHospitality.get(position).getSub_category().size(); i++) {
                    subcategory.add(String.valueOf(categoryListHospitality.get(position).getSub_category().get(i).getId()));
                }
            }
            productListHospitality.clear();
            getHospitalityVenueProducts(false);
        } else if (clickType == CATEGORY_RETAIL) {  // when we tap on retail category
            binding.etSearchNowHeader.setText("");
            search = "";
            scroolPosition = position;

            if (categoryList != null && categoryList.size() > 0) {
                /*Intent in = new Intent(this, AllProductByCategoryListActivity.class);
                in.putExtra(CATEGORY_ID, String.valueOf(categoryList.get(position).getId()));
                in.putExtra(CATEGORY_NAME, String.valueOf(categoryList.get(position).getCat_name()));
                in.putExtra(STORE_ID, retailVenueResponseModel.getVenue_details().getVenue_id());
                in.putExtra(VENUE_NAME, retailVenueResponseModel.getVenue_details().getVenue_name());
                in.putExtra(DISTANCE, retailVenueResponseModel.getVenue_details().getDistance());
                in.putExtra(IS_PRODUCT_IN_CART, retailVenueResponseModel.getIsProductInCart());
                in.putExtra(FROM_WHERE, FROM_CATEGORY_TAP);
                startActivity(in);*/
                pageNumber = 1;
                category_id = String.valueOf(categoryList.get(position).getId());
                //   getVenueDetails(false);
                getRetailsProduct(false);
            }

        } else if (clickType == HOSPITALITY_PRODUCT) {
           /* Intent product = new Intent(this, SingleProductDetailActivity.class);
            product.putExtra(STORE_ID, productListHospitality.get(position).getVenue_id());
            product.putExtra(PRODUCT_ID, String.valueOf(productListHospitality.get(position).getId()));
            product.putExtra(OFFER_ID, String.valueOf(productListHospitality.get(position).getOffer_id()));
            product.putExtra(BARCODE_ID, "");
            startActivity(product);*/
        }

    }

    @Override
    public void onBackPressed() {
        if (disableBack) {
            DialogUtils.showAlertDialog(instance, "Do You Want to exit ?"/*getString(R.string.logout_message)*/, new OnDialogClickListener() {
                @Override
                public void onPositiveClick() {

                    finishAffinity();
                    //  finish();

                          /*  Intent intent = new Intent(Intent.ACTION_MAIN);
                            intent.addCategory(Intent.CATEGORY_HOME);
                            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(intent);*/

                }

                @Override
                public void onNegativeClick() {

                }
            });
        } else {
            super.onBackPressed();
            finish();
        }

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

                                if (totalCartResponseModel.getTotal_carts() != 0) {
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
                                    binding.tvProdCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
                                } else {
                                    binding.rlFram.setVisibility(View.GONE);
                                    binding.tvCartCount.setVisibility(View.GONE);
                                    prefManager.savePreference(VENUE_ID_IN_CART, "");
                                }
                                binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));

                            } else {
                                showSnackBar(binding.getRoot(), totalCartResponseModel.getMessage());
                            }
                        } else {
                            //showSnackBar(binding.getRoot(), getString(R.string.total_cart_item_not_found));
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

    private void getVenueFilterData() {
        if (isInternetOn(instance)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(instance);
            if (dialog != null)
                dialog.show();

            VenueFilterDataRequestModel requestModel = new VenueFilterDataRequestModel(venueId, "");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<VenueFilterDataResponseModel> call = apiInterface.getVenueFilterData(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
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
                                        logOut((Activity) instance);
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
                    // binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            // binding.swipeRefresh.setRefreshing(false);
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
                    listSelectedCategory.remove(filterListData.get(pos_1st).getFilter_list().get(pos_2nd).getFilter_value());

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


        //  rv_filterList.setLayoutManager(layoutManager);
        rv_filterList.setAdapter(filterListAdapter);
        filterListAdapter.getFilter().filter("");
        dialog.findViewById(R.id.tv_clear_all).setOnClickListener(v -> {

            if (filterListAdapter != null && filterListData != null) {
                filterListAdapter.clearAllCheck();
            }
        });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(v -> dialog.dismiss());
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
///////////////////////////////////

            Intent in = new Intent(this, AllProductByCategoryListActivity.class);
            in.putExtra(CATEGORY_ID, "");
            in.putExtra(STORE_ID, venueId);
            in.putParcelableArrayListExtra(WHOLE_FILTER_API_DATA, (ArrayList<? extends Parcelable>) filterListData);
            in.putExtra(FILTER_DATA_SELECTED, hashMap);
            in.putExtra(VENUE_NAME, retailVenueResponseModel.getVenue_details().getVenue_name());
            in.putExtra(DISTANCE, retailVenueResponseModel.getVenue_details().getDistance());
            in.putExtra(IS_PRODUCT_IN_CART, retailVenueResponseModel.getIsProductInCart());
            in.putExtra(FROM_WHERE, FROM_APPLY_FILTER_TAP);
            startActivity(in);

            dialog.dismiss();
            /////////////////////////////////////////
        });
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

            Call<GetBrandResponseModel> call = apiInterface.getBrand
                    (prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
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
                                    List<VenueFilterDataResponseModel.FilterBean.FilterListBean> filterList = new ArrayList<>();
                                    List<VenueFilterDataResponseModel.FilterBean.FilterListBean> filterListModifier = new ArrayList<>();

                                    for (int i = 0; i < responseModel.getFilter().getFilter_list().size(); i++) {
                                        filterList.add(new VenueFilterDataResponseModel.FilterBean.FilterListBean(responseModel.getFilter().getFilter_list().get(i).getFilter_value(), responseModel.getFilter().getFilter_list().get(i).getDisplay_name()));

                                    }

                                    VenueFilterDataResponseModel.FilterBean filterBean = new VenueFilterDataResponseModel.FilterBean(
                                            responseModel.getFilter().getFilter_type(),
                                            responseModel.getFilter().getIsMultiple(),
                                            filterList);

                                    filterListAdapter.addAll(1, filterBean);
                                    /*///// ****************************  for Modifier start  *****************************//////*/
                                    for (int j = 0; j < responseModel.getModifiers().getFilter_list().size(); j++) {
                                        filterListModifier.add(new VenueFilterDataResponseModel.FilterBean.FilterListBean(responseModel.getModifiers().getFilter_list().get(j).getFilter_value(), responseModel.getModifiers().getFilter_list().get(j).getDisplay_name()));
                                    }

                                    VenueFilterDataResponseModel.FilterBean filterBeanModi = new VenueFilterDataResponseModel.FilterBean(
                                            responseModel.getModifiers().getFilter_type(),
                                            responseModel.getModifiers().getIsMultiple(), filterListModifier);

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

    private void likeDislike() {
        if (isInternetOn(instance)) {
            LikeDislikeProductRequestModel requestModel = new LikeDislikeProductRequestModel(venueId);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<LikeDislikeProductResponseModel> call = apiInterface.likeDislikeVenue(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<LikeDislikeProductResponseModel>() {
                @Override
                public void onResponse(Call<LikeDislikeProductResponseModel> call, Response<LikeDislikeProductResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            LikeDislikeProductResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {
                                if (retailVenueResponseModel.getVenue_details().isIsWishlisted()) {
                                    binding.ivFavo.setColorFilter(ContextCompat.getColor(instance, R.color.hint_grey), android.graphics.PorterDuff.Mode.SRC_IN);
                                    retailVenueResponseModel.getVenue_details().setIsWishlisted(false);
                                } else {
                                    binding.ivFavo.setColorFilter(ContextCompat.getColor(instance, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
                                    retailVenueResponseModel.getVenue_details().setIsWishlisted(true);
                                }

                                DialogUtils.showAlertDialogWithSingleButton(instance, responseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });


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
                public void onFailure(Call<LikeDislikeProductResponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    @Override
    public void onSuccessActionListner() {
        binding.rlFram.setVisibility(View.VISIBLE);
        getTotalCartCount();
    }


    private void setSameDayDelivery(VenueDetailResponseModel venueDetailResponseModel) {  // TODO : SHARE IT
        if (venueDetailResponseModel.getVenue_details().getSameDayShipping() == 1 && venueDetailResponseModel.getShippingData() != null) {
            binding.rlSameDayDelivery.setVisibility(View.VISIBLE);
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat(PATTERN_START_WITH_DATE_NO_YEAR, Locale.getDefault());
                Calendar calendar = Calendar.getInstance();
                Date today = calendar.getTime();  /// Today date

                String endTime = venueDetailResponseModel.getShippingData().getTime();
                Date dateObj1 = sdfSameDayShipping.parse(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
                Date dateObj2 = sdfSameDayShipping.parse(endTime);
                long diff = dateObj2.getTime() - dateObj1.getTime();

                if (diff <= 0) {  /// Time laps
                    String endTimeToday = "23:59";
                    Date dateObjCurrnt = sdfSameDayShipping.parse(new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date()));
                    Date dateObjEnd = sdfSameDayShipping.parse(endTimeToday);
                    diff = dateObjEnd.getTime() - dateObjCurrnt.getTime();

                    if (venueDetailResponseModel.getShippingData().getTo_day() > 1) {
                        calendar.add(Calendar.DAY_OF_YEAR, venueDetailResponseModel.getShippingData().getTo_day() + 1);
                        Date tomorrow = calendar.getTime();
                        String tomorrowAsString = dateFormat.format(tomorrow);
                        binding.tvDeliveryDay.setText(tomorrowAsString);

                    } else {
                        binding.tvDeliveryDay.setText(getString(R.string.tomorrow));
                    }
                    if (venueDetailResponseModel.getShippingData().getType() == 1) {
                        binding.tvSameDayDeliveryCost.setText(getString(R.string.next_day_delivery) /*+ " +" + getString(R.string.pound) + venueDetailResponseModel.getShippingData().getAmount()*/);
                    } else
                        binding.tvSameDayDeliveryCost.setText(getString(R.string.next_day_delivery) /*+ " +" + venueDetailResponseModel.getShippingData().getAmount() + getString(R.string.percent)*/);

                } else {
                    if (venueDetailResponseModel.getShippingData().getTo_day() > 1) {
                        calendar.add(Calendar.DAY_OF_YEAR, venueDetailResponseModel.getShippingData().getTo_day());
                        Date tomorrow = calendar.getTime();
                        String tomorrowAsString = dateFormat.format(tomorrow);

                        binding.tvDeliveryDay.setText(tomorrowAsString);
                    } else binding.tvDeliveryDay.setText(getString(R.string.today));

                    if (venueDetailResponseModel.getShippingData().getType() == 1) {
                        binding.tvSameDayDeliveryCost.setText(venueDetailResponseModel.getShippingData().getName() /*+ " +" + getString(R.string.pound) + venueDetailResponseModel.getShippingData().getAmount()*/);
                    } else
                        binding.tvSameDayDeliveryCost.setText(venueDetailResponseModel.getShippingData().getName()/* + " +" + venueDetailResponseModel.getShippingData().getAmount() + getString(R.string.percent)*/);

                }

                dismissCountDownTimer();
                mCountDownTimer = new CountDownTimer(diff, 1000) {
                    public void onTick(long millisUntilFinished) {
                        long millis = millisUntilFinished;
                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                        binding.tvExpiryShippingDay.setText(hms);
                    }

                    public void onFinish() {
                        binding.tvExpiryShippingDay.setText("Time up!");
                    }
                };
                mCountDownTimer.start();
            } catch (Exception exc) {
                exc.printStackTrace();
                Log.e("TMM", "" + exc);
            }

        } else {
            binding.rlSameDayDelivery.setVisibility(View.GONE);
        }
    }


    public void dismissCountDownTimer() {
        if (mCountDownTimer != null)
            mCountDownTimer.cancel();
    }

    @Override
    public void onSeasonExpire() {
        logOut(instance);
    }

    /////////////////// To Move fab Botton  ////////////////////////
    /*
    private View.OnTouchListener onTouchListener() {
        return new View.OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View view, MotionEvent event) {

                final int x = (int) event.getRawX();
                final int y = (int) event.getRawY();

                switch (event.getAction() & MotionEvent.ACTION_MASK) {

                    case MotionEvent.ACTION_DOWN:
                        FrameLayout.LayoutParams lParams = (FrameLayout.LayoutParams)
                                view.getLayoutParams();

                        xDelta = x - lParams.leftMargin;
                        yDelta = y - lParams.topMargin;


                        Log.d("TAG_touch", "onTouch: _____________ACTION_DOWN");
                        actionDownPoint.x = event.getX();
                        actionDownPoint.y = event.getY();
                        touchDownTime = System.currentTimeMillis();
                        break;


                    case MotionEvent.ACTION_UP:
                        Log.d("TAG_Touch", "onTouch: _____________ACTION_UP");
                        //check if the user's finger is still close to the point he/she clicked
                        boolean isTouchLength = (Math.abs(event.getX() - actionDownPoint.x)
                                + Math.abs(event.getY() - actionDownPoint.y)) < touchMoveFactor;
                        //check if it's not been more than @touchTimeFactor=200 ms
                        boolean isClickTime = System.currentTimeMillis() - touchDownTime < touchTimeFactor;
                        if (isTouchLength && isClickTime) {
                            performClick();
                        }
                        //ACTION_UP logic goes below this line
                        break;

                    case MotionEvent.ACTION_MOVE:
                        //move method should not work if the click was too short i.e click time +100 ms
                        if (System.currentTimeMillis() - touchDownTime < touchTimeFactor + 100)
                            return true;
                        Log.d("TAG_touch", "onTouch: _____________ACTION_MOVE");

// move method logic goes below this line


                        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) view
                                .getLayoutParams();
                        layoutParams.leftMargin = x - xDelta;
                        layoutParams.topMargin = y - yDelta;
                        layoutParams.rightMargin = 0;
                        layoutParams.bottomMargin = 0;
                        view.setLayoutParams(layoutParams);
                        break;
                }
                binding.mainFram.invalidate();
                return true;
            }
        };
    }

    private void performClick() {

        if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
            Intent intentH = new Intent(instance, MyCartHospitalityActivity.class);
            startActivity(intentH);
        } else {
            Intent intentR = new Intent(instance, MyCartActivity.class);
            startActivity(intentR);
        }

       */

    /* binding.tvProdCount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }*/
    //////////////////////////////////////

    private void getRetailsProduct(boolean isMore) {
        if (isInternetOn(this)) {

            if (loaderDialog != null && !loaderDialog.isShowing())
                loaderDialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");

            SingleVenueDetailRequestModel requestModel = new SingleVenueDetailRequestModel(venueId, latitude, longitude, category_id, LIMIT, search);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<SingleVenueDetailResponseModel> call = apiInterface.getVenueDetailProduct(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel, String.valueOf(pageNumber++));
            call.enqueue(new Callback<SingleVenueDetailResponseModel>() {
                @Override
                public void onResponse(Call<SingleVenueDetailResponseModel> call, Response<SingleVenueDetailResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (loaderDialog != null)
                            loaderDialog.dismiss();

                        if (response.isSuccessful()) {
                            retailVenueProductResponseModel = response.body();
                            if (retailVenueProductResponseModel.isStatus()) {
                                isLoading = false;
                                totalPage = retailVenueProductResponseModel.getProducts().getLast_page();
                                if (retailVenueProductResponseModel.getProducts().getData() != null
                                        && retailVenueProductResponseModel.getProducts().getData().size() > 0) {

                                    if (isMore) {
                                        productList.addAll(retailVenueProductResponseModel.getProducts().getData());
                                        if (productsAdapter != null)
                                            productsAdapter.notifyDataSetChanged();
                                    } else {
                                        productList.clear();
                                        productList.addAll(retailVenueProductResponseModel.getProducts().getData());
                                        setAllRetailProductAdapter();
                                    }
                                }
                                else {
                                    if (!isMore) {
                                        productList.clear();
                                        setAllRetailProductAdapter();
                                    }
                                }

                            } else {
                                showSnackBar(binding.getRoot(), retailVenueProductResponseModel.getMessage());
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
                public void onFailure(Call<SingleVenueDetailResponseModel> call, Throwable t) {
                    binding.swipeRefresh.setRefreshing(false);
                    if (loaderDialog != null)
                        loaderDialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    private void getHospitalityVenueProducts(boolean isMore) {
        if (isInternetOn(this)) {
            if (loaderDialog != null && !loaderDialog.isShowing())
                loaderDialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");

            SingleVenueDetailRequestModel requestModel = new SingleVenueDetailRequestModel
                    (isShowHos, TextUtils.isEmpty(category_id) ? 0 : Integer.parseInt(category_id), search, favProduct, venueId,
                            subcategory, latitude, longitude, LIMIT);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<HospitalityVenueProductResponseModel> call = apiInterface.hospitalityVenueProducts(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel, String.valueOf(pageNumber++));
            call.enqueue(new Callback<HospitalityVenueProductResponseModel>() {
                @Override
                public void onResponse(Call<HospitalityVenueProductResponseModel> call, Response<HospitalityVenueProductResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (loaderDialog != null)
                            loaderDialog.dismiss();

                        if (response.isSuccessful()) {
                            hospitalityVenueProductResponseModel = response.body();
                            if (hospitalityVenueProductResponseModel.isStatus()) {

                                isLoading = false;
                                totalPage = hospitalityVenueProductResponseModel.getProducts().getLast_page();


                                if (hospitalityVenueProductResponseModel.getProducts().getData() != null &&
                                        hospitalityVenueProductResponseModel.getProducts().getData().size() > 0) {

                                    if (isMore) {
                                        productListHospitality.addAll(hospitalityVenueProductResponseModel.getProducts().getData());
                                        if (productsHospitalityAdapter != null)
                                            productsHospitalityAdapter.notifyDataSetChanged();
                                    } else {
                                        productListHospitality.clear();
                                        productListHospitality.addAll(hospitalityVenueProductResponseModel.getProducts().getData());
                                        setAllHospitalityProductAdapter();
                                    }
                                } else {
                                    if (!isMore) {

                                        productListHospitality.clear();
                                        setAllHospitalityProductAdapter();
                                    }
                                }

                            } else {
                                showSnackBar(binding.getRoot(), retailVenueResponseModel.getMessage());
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
                public void onFailure(Call<HospitalityVenueProductResponseModel> call, Throwable t) {
                    binding.swipeRefresh.setRefreshing(false);
                    if (loaderDialog != null)
                        loaderDialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }


}
