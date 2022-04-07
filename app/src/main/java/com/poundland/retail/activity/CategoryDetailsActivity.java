package com.poundland.retail.activity;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
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
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.libraries.places.api.model.Place;
import com.google.android.libraries.places.widget.Autocomplete;
import com.google.android.libraries.places.widget.AutocompleteActivity;
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
import com.poundland.retail.adapter.CategoryWiseProductAdapter;
import com.poundland.retail.adapter.CategoryWiseStoreAdapter;
import com.poundland.retail.adapter.CategoryWiseTopOffersAdapter;
import com.poundland.retail.adapter.ProductStoreCategoryFilterAdapter;
import com.poundland.retail.adapter.VenueFilterListItemAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityCategoryDetailsBinding;
import com.poundland.retail.databinding.DialogShowVenueFilterBinding;
import com.poundland.retail.dialog.DialogShowCategory;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.FilterListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.GetBrandResponseModel;
import com.poundland.retail.model.requestModel.ProductByCategoryRequestModel;
import com.poundland.retail.model.requestModel.ProductBySearchRequestModel;
import com.poundland.retail.model.requestModel.ProductDetailByModifierRequestModel;
import com.poundland.retail.model.requestModel.VenueFilterDataRequestModel;
import com.poundland.retail.model.responseModel.ProductByCategoryResponseModel;
import com.poundland.retail.model.responseModel.VenueFilterDataResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.BARCODE_ID;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.CATEGORY_NAME;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_CATEGORY_TAP;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.FUSED_LOCATION;
import static com.poundland.retail.interfaces.Constants.HOME_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.HOME_RETAIL;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.LOOKUP_ACTIVITY_CALLBACK_DATA;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.OPEN_LOOKUP_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.PRODUCT;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_TYPE_SELECTED;
import static com.poundland.retail.interfaces.Constants.RESERVATION_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class CategoryDetailsActivity extends BaseActivity implements View.OnClickListener, DrawerListner, FilterListner, SuccessActionListner {
    private ActivityCategoryDetailsBinding binding;
    private final static int PLACE_PICKER_REQUEST = 900;
    private PrefManager prefManager;
    private List<ProductByCategoryResponseModel.VenuesBean> storeList;
    private List<ProductByCategoryResponseModel.ProductsOffersBean> topOfferList;
    private List<ProductByCategoryResponseModel.ProductsBean.DataBean> productList;
    private List<ProductByCategoryResponseModel.CategoryBean> categoryList;
    private List<VenueFilterDataResponseModel.FilterBean> filterListData;
    private String search = "";
    private ListPopupWindow popupWindow;
    private boolean isPopup;
    private boolean productSelected = true;
    private String catId = "";
    private CategoryDetailsActivity instance = null;
    private String fromWhere = "";
    private int pageNumber = 1;
    private double lat;
    private double longi;
    private String location;
    private boolean isLoading = true;
    private int totalPage;
    private Dialog dialog;
    private ProductStoreCategoryFilterAdapter filterListAdapter;
    private List<String> listSelectedCategory;
    private HashMap<String, List<String>> filterData;
    private String venueId = "";
    private CategoryWiseProductAdapter categoryWiseProductAdapter;
    private int totalProduct;
    private boolean clearAll;
    private String lastApiCall;
    private CategoryWiseStoreAdapter categoryWiseStoreAdapter;
    private String productType = "0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_category_details);
        getIntentData();

        binding.nestedScroll.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                View view = (View) binding.nestedScroll.getChildAt(binding.nestedScroll.getChildCount() - 1);
                int diff = (view.getBottom() - (binding.nestedScroll.getHeight() + binding.nestedScroll.getScrollY()));
                if (diff == 0) {
                    if (!isLoading && pageNumber <= totalPage) {
                        if (productSelected) {
                            isLoading = true;
                            if (fromWhere.equalsIgnoreCase(FROM_CATEGORY_TAP) && lastApiCall.equals("BY_ID")) {
                                producthByCategoryId(true, true);
                            } else producthBySearch(true, true);
                        }
                    }
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

    }

    private void getIntentData() {
        pageNumber = 1;
        instance = this;
        filterData = new HashMap<>();
        storeList = new ArrayList<>();
        topOfferList = new ArrayList<>();
        productList = new ArrayList<>();
        categoryList = new ArrayList<>();
        filterListData = new ArrayList<>();
        listSelectedCategory = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);

        binding.ivSearchNowHeader.setOnClickListener(this);
        binding.rlSelectCategory.setOnClickListener(this);
        binding.rlProduct.setOnClickListener(this);
        binding.rlStore.setOnClickListener(this);
        binding.ivBack.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.ivFilter.setOnClickListener(this);
        binding.llCurrentLocation.setOnClickListener(this);
        binding.ivClose.setOnClickListener(this);

        binding.ivProductFound.setColorFilter(ContextCompat.getColor(instance, R.color.color_white), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.ivStoreFound.setColorFilter(ContextCompat.getColor(instance, R.color.app_header_color), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.rlProduct.setBackground(ContextCompat.getDrawable(this, R.drawable.app_color_left_cut_rounded_rect));
        binding.rlStore.setBackground(ContextCompat.getDrawable(this, R.drawable.grey_right_cut_rounded_rect));
        binding.tvProductFound.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        binding.tvProductQty.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        binding.ivDropDown.setVisibility(View.VISIBLE);
        binding.ivDropDown2.setVisibility(View.INVISIBLE);

        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        if (fromWhere.equals(FROM_CATEGORY_TAP)) {
            search = getIntent().getStringExtra(CATEGORY_NAME);
            catId = getIntent().getStringExtra(CATEGORY_ID);
            binding.tvCategoryName.setText(search);
            search = "";
            producthByCategoryId(true, false);
        } else {
            search = getIntent().getStringExtra(CATEGORY_NAME);
            productType = String.valueOf(getIntent().getIntExtra(PRODUCT_TYPE_SELECTED, 0));

            producthBySearch(true, false);

            if (search != null && search.length() > 0) {
                binding.ivClose.setVisibility(View.VISIBLE);
            } else {
                binding.ivClose.setVisibility(View.GONE);
            }


            binding.etSearchNowHeader.setText(search);
            binding.rlSelectCategory.setVisibility(View.GONE);
            binding.rlTopOfferNearYou.setVisibility(View.GONE);
            binding.rvTopOffers.setVisibility(View.GONE);
        }

        binding.etSearchNowHeader.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    pageNumber = 1;
                    search = binding.etSearchNowHeader.getText().toString().trim();
                    if (productSelected) {
                        producthBySearch(true, false);
                    } else {
                        producthBySearch(false, false);
                    }
                    handled = true;
                }
                return handled;
            }
        });

        location = prefManager.getPreference(FUSED_LOCATION, "");
        if (!location.equals("")) {
            location = location.replace("null", "");
            binding.tvTopOffersNearYouLocation.setText(location);
            binding.tvCurrentLocation.setText(location);
            // binding.tvMatchFoundLoc.setText(location);
        } else {
            binding.tvTopOffersNearYouLocation.setText(getString(R.string.current_location));
        }

        getHomeFilterData(catId);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_search_now_header:
                if (binding.etSearchNowHeader.getText().toString().length() > 0) {
                    search = binding.etSearchNowHeader.getText().toString().trim();
                    pageNumber = 1;
                    if (productSelected) {
                        producthBySearch(true, false);

                    } else {
                        producthBySearch(false, false);
                    }
                }

                break;
            case R.id.iv_back:
                finish();
                break;


            case R.id.iv_close:
                if (binding.etSearchNowHeader.getText().toString().length() > 0) {
                    binding.etSearchNowHeader.setText("");
                    search = "";
                    pageNumber = 1;
                    if (productSelected) {
                        producthBySearch(true, false);
                    } else {
                        producthBySearch(false, false);
                    }
                }
                break;

            case R.id.ll_current_location:

                getCurrentLoc();

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
                homeFilterDialog(filterListData);
                break;

            case R.id.rl_select_category:
                new DialogShowCategory(this, categoryList, search, new DrawerListner() {

                    @Override
                    public void onDrawerSelect(int position, int clickId) {
                        binding.tvCategoryName.setText(categoryList.get(position).getCat_name().toString());
                        // search = categoryList.get(position).getCat_name();
                        catId = String.valueOf(categoryList.get(position).getId());

                        // categoryList.clear();
                        pageNumber = 1;
                        storeList.clear();
                        if (categoryWiseStoreAdapter != null)
                            categoryWiseStoreAdapter.notifyDataSetChanged();
                        topOfferList.clear();
                        productList.clear();
                        if (categoryWiseProductAdapter != null)
                            categoryWiseProductAdapter.notifyDataSetChanged();
                        if (productSelected) {
                            producthByCategoryId(true, false);
                        } else
                            producthByCategoryId(false, false);

                    }
                }).show();
                break;

            case R.id.rl_product:
                selectProductTab();

                break;

            case R.id.rl_store:
                selectStoreTab();
                break;
        }
    }

    private void getCurrentLoc() {
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
    }

    private void selectProductTab() {
        productSelected = true;

        setCategoryWiseProductAdapter();
        binding.tvProductQty.setText(String.valueOf(totalProduct));
        if (totalProduct > 1) {
            binding.tvMatchFound.setText("Found " + totalProduct + " Products in " + search + getString(R.string.dummy_search_message));
            binding.tvProductFound.setText(getString(R.string.products_found));
        } else {
            binding.tvMatchFound.setText("Found " + totalProduct + " Product in " + search + getString(R.string.dummy_search_message));
            binding.tvProductFound.setText(getString(R.string.product_found));
        }
        binding.ivProductFound.setColorFilter(ContextCompat.getColor(instance, R.color.color_white), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.ivStoreFound.setColorFilter(ContextCompat.getColor(instance, R.color.app_header_color), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.rlProduct.setBackground(ContextCompat.getDrawable(this, R.drawable.app_color_left_cut_rounded_rect));
        binding.rlStore.setBackground(ContextCompat.getDrawable(this, R.drawable.grey_right_cut_rounded_rect));
        binding.tvProductFound.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        binding.tvProductQty.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        binding.tvStoreQty.setTextColor(ContextCompat.getColor(this, R.color.hint_grey));
        binding.tvStoreFound.setTextColor(ContextCompat.getColor(this, R.color.hint_grey));
        binding.ivDropDown.setVisibility(View.VISIBLE);
        binding.ivDropDown2.setVisibility(View.INVISIBLE);
    }

    private void selectStoreTab() {
        productSelected = false;
        binding.ivProductFound.setColorFilter(ContextCompat.getColor(instance, R.color.app_header_color), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.ivStoreFound.setColorFilter(ContextCompat.getColor(instance, R.color.color_white), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.rlStore.setBackground(ContextCompat.getDrawable(this, R.drawable.app_color_right_cut_rounded_rect));
        binding.rlProduct.setBackground(ContextCompat.getDrawable(this, R.drawable.grey_left_cut_rounded_rect));
        binding.tvStoreQty.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        binding.tvStoreFound.setTextColor(ContextCompat.getColor(this, R.color.color_white));
        binding.tvProductFound.setTextColor(ContextCompat.getColor(this, R.color.hint_grey));
        binding.tvProductQty.setTextColor(ContextCompat.getColor(this, R.color.hint_grey));
        binding.ivDropDown2.setVisibility(View.VISIBLE);
        binding.ivDropDown.setVisibility(View.INVISIBLE);
        setCategoryWiseStoreAdapter();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
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
                binding.tvCurrentLocation.setText(place.getName());
                binding.tvTopOffersNearYouLocation.setText("" + place.getName());
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
            binding.tvTopOffersNearYouLocation.setText(lookUpdata);
        }
    }

    private void setTopOffersAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        CategoryWiseTopOffersAdapter homeCategoryAdapter = new CategoryWiseTopOffersAdapter(this, topOfferList, this);
        binding.rvTopOffers.setLayoutManager(layoutManager);
        binding.rvTopOffers.setAdapter(homeCategoryAdapter);
    }

    private void setCategoryWiseProductAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        categoryWiseProductAdapter = new CategoryWiseProductAdapter(this, productList, topOfferList, this
                , this);
        binding.rvStoreProduct.setLayoutManager(layoutManager);
        binding.rvStoreProduct.setAdapter(categoryWiseProductAdapter);
    }

    private void setCategoryWiseStoreAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        categoryWiseStoreAdapter = new CategoryWiseStoreAdapter(this, storeList, this);
        binding.rvStoreProduct.setLayoutManager(layoutManager);
        binding.rvStoreProduct.setAdapter(categoryWiseStoreAdapter);


        if (storeList.size() > 1) {
            binding.tvMatchFound.setText("Found " + storeList.size() + " Stores in " + search + getString(R.string.dummy_search_message));
            binding.tvStoreFound.setText(getString(R.string.stores_found));
        } else {
            binding.tvMatchFound.setText("Found " + storeList.size() + " Store in " + search + getString(R.string.dummy_search_message));
            binding.tvStoreFound.setText(getString(R.string.store_found));
        }
    }

    @Override
    public void onDrawerSelect(int position, int clickId) {
        switch (clickId) {

            case STORE:
                Intent store = new Intent(this, VenueDetailActivity.class);
                store.putExtra(STORE_ID, storeList.get(position).getVenue_id());

                if (storeList.get(position).getVenue_type() == 1) {
                    store.putExtra(FROM_WHERE, HOME_RETAIL);
                } else if (storeList.get(position).getVenue_type() == 2) {
                    store.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                }
                store.putExtra(RESERVATION_ID, "");
                store.putExtra(CATEGORY_ID, "");
                startActivity(store);
                break;

            case PRODUCT:
                if (productList.get(position).getVenue_type() == 2) {
                    Intent store1 = new Intent(this, VenueDetailActivity.class);
                    store1.putExtra(STORE_ID, productList.get(position).getVenue_id());
                    store1.putExtra(FROM_WHERE, HOME_HOSPITALITY);
                    store1.putExtra(RESERVATION_ID, "");
                    store1.putExtra(CATEGORY_ID, "");
                    startActivity(store1);
                } else {
                    Intent product = new Intent(this, ProductDetailActivity.class);
                    product.putExtra(STORE_ID, productList.get(position).getVenue_id());
                    product.putExtra(PRODUCT_ID, String.valueOf(productList.get(position).getId()));
                    product.putExtra(OFFER_ID, "");
                    product.putExtra(BARCODE_ID, "");
                    startActivity(product);
                }

                break;

            case TOP_OFFER:
                Intent offer = new Intent(this, ProductDetailActivity.class);
                offer.putExtra(STORE_ID, topOfferList.get(position).getVenue_id());
                offer.putExtra(PRODUCT_ID, String.valueOf(topOfferList.get(position).getProduct_id()));
                offer.putExtra(OFFER_ID, String.valueOf(topOfferList.get(position).getOffer_id()));
                offer.putExtra(BARCODE_ID, "");
                startActivity(offer);
                break;
        }
    }

    private void producthByCategoryId(final boolean isProduct, boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();
            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");

            ProductByCategoryRequestModel byCategoryRequestModel = new ProductByCategoryRequestModel
                    (catId, latitude, longitude);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<ProductByCategoryResponseModel> call = apiInterface.getProductByCategoryId(
                    prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), byCategoryRequestModel, String.valueOf(pageNumber++));
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<ProductByCategoryResponseModel>() {
                @Override
                public void onResponse(Call<ProductByCategoryResponseModel> call, Response<ProductByCategoryResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();
                        lastApiCall = "BY_ID";
                        isLoading = false;
                        if (response.isSuccessful()) {
                            ProductByCategoryResponseModel homeResponseModel = response.body();
                            if (homeResponseModel.isStatus()) {
                                if (isProduct) {
                                    if (homeResponseModel.getProducts().getData() != null) {
                                        totalProduct = homeResponseModel.getProducts().getTotal();
                                        binding.tvProductQty.setText(String.valueOf(totalProduct));

                                        if (totalProduct > 1) {
                                            binding.tvMatchFound.setText("Found " + totalProduct + " Products in " + search + getString(R.string.dummy_search_message));
                                            binding.tvProductFound.setText(getString(R.string.products_found));
                                        } else {
                                            binding.tvMatchFound.setText("Found " + totalProduct + " Product in " + search + getString(R.string.dummy_search_message));
                                            binding.tvProductFound.setText(getString(R.string.product_found));
                                        }
                                    }
                                }

                                if (homeResponseModel.getCategory() != null && homeResponseModel.getCategory().size() > 0) {
                                    categoryList.clear();
                                    categoryList.addAll(homeResponseModel.getCategory());
                                } else {
                                    categoryList.clear();
                                }

                                if (homeResponseModel.getProducts_offers() != null && homeResponseModel.getProducts_offers().size() > 0) {
                                    binding.rlTopOfferNearYou.setVisibility(View.GONE);
                                    topOfferList.clear();
                                    topOfferList.addAll(homeResponseModel.getProducts_offers());
                                    setTopOffersAdapter();
                                } else {
                                    binding.rlTopOfferNearYou.setVisibility(View.GONE);
                                    topOfferList.clear();
                                    setTopOffersAdapter();
                                }

                                if (homeResponseModel.getVenues() != null && homeResponseModel.getVenues().size() > 0) {
                                    storeList.clear();
                                    if (categoryWiseStoreAdapter != null)
                                        categoryWiseStoreAdapter.notifyDataSetChanged();
                                    storeList.addAll(homeResponseModel.getVenues());
                                    binding.tvStoreQty.setText(String.valueOf(storeList.size()));
                                    binding.tvStoreFound.setText(getString(R.string.stores_found));
                                    if (!isProduct)
                                        setCategoryWiseStoreAdapter();
                                } else {
                                    storeList.clear();
                                    if (categoryWiseStoreAdapter != null)
                                        categoryWiseStoreAdapter.notifyDataSetChanged();
                                    binding.tvStoreQty.setText(String.valueOf(storeList.size()));
                                    binding.tvStoreFound.setText(getString(R.string.store_found));
                                    if (!isProduct)
                                        setCategoryWiseStoreAdapter();
                                }

                                if (homeResponseModel.getProducts().getData() != null &&
                                        homeResponseModel.getProducts().getData().size() > 0) {
                                    totalPage = homeResponseModel.getProducts().getLast_page();
                                    productList.addAll(homeResponseModel.getProducts().getData());
                                    if (isProduct) {
                                        if (isMore) {
                                            if (categoryWiseProductAdapter != null)
                                                categoryWiseProductAdapter.notifyDataSetChanged();
                                        } else {
                                            productList.clear();
                                            if (categoryWiseProductAdapter != null)
                                                categoryWiseProductAdapter.notifyDataSetChanged();
                                            productList.addAll(homeResponseModel.getProducts().getData());
                                            setCategoryWiseProductAdapter();
                                        }
                                    }

                                } else {

                                    if (!isMore) {
                                        productList.clear();
                                        if (categoryWiseProductAdapter != null)
                                            categoryWiseProductAdapter.notifyDataSetChanged();
                                        if (isProduct)
                                            setCategoryWiseProductAdapter();
                                        binding.tvProductQty.setText(String.valueOf("0"));
                                        binding.tvMatchFound.setText("Found " + "0" + " Product in " + search + getString(R.string.dummy_search_message));
                                        binding.tvProductFound.setText(getString(R.string.product_found));
                                        selectStoreTab();
                                    }

                                }


                            } else {
                                showSnackBar(binding.getRoot(), homeResponseModel.getMessage());
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
                public void onFailure(Call<ProductByCategoryResponseModel> call, Throwable t) {
//                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
//            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void producthBySearch(final boolean isProduct, boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            ProductBySearchRequestModel requestModel = new ProductBySearchRequestModel(search,
                    prefManager.getPreference(LATITUDE, ""),
                    prefManager.getPreference(LONGITUDE, ""), venueId, filterData, productType);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<ProductByCategoryResponseModel> call = apiInterface.getProductBySearch_inCategoryDetail(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel, String.valueOf(pageNumber++));
            call.enqueue(new Callback<ProductByCategoryResponseModel>() {
                @Override
                public void onResponse(Call<ProductByCategoryResponseModel> call, Response<ProductByCategoryResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        isLoading = false;
                        lastApiCall = "BY_SEARCH";
                        if (response.isSuccessful()) {
                            ProductByCategoryResponseModel homeResponseModel = response.body();
                            if (homeResponseModel.isStatus()) {

                                if (isProduct) {

                                    if (homeResponseModel.getProducts().getData() != null) {
                                        totalProduct = homeResponseModel.getProducts().getTotal();
                                        binding.tvProductQty.setText(String.valueOf(totalProduct));

                                        if (totalProduct > 1) {
                                            binding.tvMatchFound.setText("Found " + totalProduct + " Products in " + search + getString(R.string.dummy_search_message));
                                            binding.tvProductFound.setText(getString(R.string.products_found));
                                        } else {
                                            binding.tvMatchFound.setText("Found " + totalProduct + " Product in " + search + getString(R.string.dummy_search_message));
                                            binding.tvProductFound.setText(getString(R.string.product_found));
                                        }

                                    }
                                }

                                if (homeResponseModel.getCategory() != null && homeResponseModel.getCategory().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);
                                    categoryList.clear();
                                    categoryList.addAll(homeResponseModel.getCategory());
                                }

                                if (homeResponseModel.getProducts_offers() != null && homeResponseModel.getProducts_offers().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);
                                    topOfferList.clear();
                                    topOfferList.addAll(homeResponseModel.getProducts_offers());
                                    setTopOffersAdapter();
                                } else {
                                    // binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    topOfferList.clear();
                                    setTopOffersAdapter();
                                }


                                if (homeResponseModel.getVenues() != null && homeResponseModel.getVenues().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);
                                    storeList.clear();
                                    if (categoryWiseStoreAdapter != null)
                                        categoryWiseStoreAdapter.notifyDataSetChanged();
                                    storeList.addAll(homeResponseModel.getVenues());
                                    binding.tvStoreQty.setText(String.valueOf(storeList.size()));
                                    binding.tvStoreFound.setText(getString(R.string.stores_found));
                                   /* if (!isProduct)
                                        setCategoryWiseStoreAdapter();*/
                                } else {
                                    storeList.clear();
                                    if (categoryWiseStoreAdapter != null)
                                        categoryWiseStoreAdapter.notifyDataSetChanged();
                                    binding.tvStoreQty.setText(String.valueOf(storeList.size()));
                                    binding.tvStoreFound.setText(getString(R.string.store_found));
                                  /*  if (!isProduct)
                                        setCategoryWiseStoreAdapter();*/
                                }

                                if (homeResponseModel.getProducts().getData() != null &&
                                        homeResponseModel.getProducts().getData().size() > 0) {
                                    totalPage = homeResponseModel.getProducts().getLast_page();
                                    productList.addAll(homeResponseModel.getProducts().getData());
                                    if (isProduct) {
                                        if (isMore) {
                                            if (categoryWiseProductAdapter != null)
                                                categoryWiseProductAdapter.notifyDataSetChanged();
                                        } else {
                                            productList.clear();
                                            if (categoryWiseProductAdapter != null)
                                                categoryWiseProductAdapter.notifyDataSetChanged();
                                            productList.addAll(homeResponseModel.getProducts().getData());
                                            //  setCategoryWiseProductAdapter();
                                        }
                                    }

                                } else {
                                    if (!isMore) {
                                        productList.clear();
                                        if (categoryWiseProductAdapter != null)
                                            categoryWiseProductAdapter.notifyDataSetChanged();
                                        // binding.tvProductQty.setText(String.valueOf(productList.size()));
                                       /* if (isProduct)
                                            setCategoryWiseProductAdapter();*/
                                        binding.tvProductQty.setText("0");
                                        binding.tvMatchFound.setText("Found " + "0" + " Product in " + search + getString(R.string.dummy_search_message));
                                        binding.tvProductFound.setText(getString(R.string.product_found));
                                    }

                                }

                                if (isProduct) {
                                    if (productList != null && productList.size() > 0) {
                                        setCategoryWiseProductAdapter();
                                    } else selectStoreTab();

                                } else {
                                    setCategoryWiseStoreAdapter();
                                }


                            } else {
                                showSnackBar(binding.getRoot(), homeResponseModel.getMessage());
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
                public void onFailure(Call<ProductByCategoryResponseModel> call, Throwable t) {
//                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
//            binding.swipeRefresh.setRefreshing(false);
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

        filterListAdapter = new ProductStoreCategoryFilterAdapter(this, filterListData, new ModifierSelectionListner() {  // from where
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

        rv_filterList.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        rv_filterList.setAdapter(filterListAdapter);
        filterListAdapter.getFilter().filter("");
        dialog.findViewById(R.id.tv_clear_all).setOnClickListener(v -> {
            if (filterListAdapter != null && filterListData != null) {
                filterListAdapter.clearAllCheck();
            }
            clearAll = true;
        });
        dialog.findViewById(R.id.tv_cancel).setOnClickListener(v -> {
            clearAll = false;
            dialog.dismiss();
        });
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
            isLoading = true;
            pageNumber = 1;
            filterData = hashMap;

            if (clearAll && fromWhere.equalsIgnoreCase(FROM_CATEGORY_TAP)) {
                if (productSelected) {
                    producthByCategoryId(true, false);
                } else
                    producthByCategoryId(false, false);
                clearAll = false;
            } else {
                if (productSelected) {
                    producthBySearch(true, false);
                } else {
                    producthBySearch(false, false);
                }
            }
            dialog.dismiss();
        });
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

    private void getHomeFilterData(String catId) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();


            VenueFilterDataRequestModel requestModel = new VenueFilterDataRequestModel(catId);

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

                                } else filterListData.clear();

                            } else showSnackBar(binding.getRoot(), responseModel.getMessage());

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

    @Override
    public void onSuccessActionListner() {
        getTotalCartCount();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTotalCartCount();
    }

    @Override
    public void onApplyFilter(HashMap<String, List<String>> data) {

    }
}

  /*private void fetchHeaderSearch(final boolean isProduct) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            HeaderSearchRequestModel requestModel = new HeaderSearchRequestModel("1", search, "15");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<HeaderSearchResponseModel> call = apiInterface.fetchHeaderSearchDetails(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<HeaderSearchResponseModel>() {
                @Override
                public void onResponse(Call<HeaderSearchResponseModel> call, Response<HeaderSearchResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            HeaderSearchResponseModel homeResponseModel = response.body();
                            if (homeResponseModel.isStatus()) {

                                if (homeResponseModel.getCategory() != null && homeResponseModel.getCategory().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);
                                    categoryList.clear();
                                    categoryList.addAll(homeResponseModel.getCategory());
                                }

                                if (homeResponseModel.getProducts_offers() != null && homeResponseModel.getProducts_offers().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);
                                    topOfferList.clear();
                                    topOfferList.addAll(homeResponseModel.getProducts_offers());
                                    setTopOffersAdapter();
                                } else {
                                    // binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    topOfferList.clear();
                                    setTopOffersAdapter();
                                }


                                if (homeResponseModel.getVenues() != null && homeResponseModel.getVenues().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);
                                    storeList.clear();
                                    storeList.addAll(homeResponseModel.getVenues());
                                    binding.tvStoreQty.setText(String.valueOf(storeList.size()));

                                } else {
                                    storeList.clear();
                                    binding.tvStoreQty.setText(String.valueOf(storeList.size()));
                                    setStoreAdapter();
                                }

                                if (homeResponseModel.getProducts() != null && homeResponseModel.getProducts().size() > 0) {
                                    productList.clear();
                                    productList.addAll(homeResponseModel.getProducts());
                                    binding.tvProductQty.setText(String.valueOf(productList.size()));

                                } else {
                                    productList.clear();
                                    binding.tvProductQty.setText(String.valueOf(productList.size()));
                                    setProductAdapter();
                                }


                                if (isProduct) {
                                    setProductAdapter();
                                } else {
                                    setStoreAdapter();
                                }


                            } else {
                                showSnackBar(binding.getRoot(), homeResponseModel.getMessage());
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
                public void onFailure(Call<HeaderSearchResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }*/

  /*private void getVenueFilterData() {
           if (isInternetOn(instance)) {
               final KProgressHUD dialog = DialogUtils.getCustomDialog(instance);
               if (dialog != null)
                   dialog.show();

               VenueFilterDataRequestModel requestModel = new VenueFilterDataRequestModel(venue_id, category_id);
               ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

               Call<VenueFilterDataResponseModel> call = apiInterface.getVenueFilterData(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
               //binding.tvNoDataFound.setVisibility(View.GONE);
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
       }*/

  /*public void setCategorySpinner() {
        try {
            popupWindow = new ListPopupWindow(this);
            final ArrayList<String> stringArrayList = new ArrayList<>();
            for (int i = 0; i < categoryList.size() - 1; i++) {
                stringArrayList.add(categoryList.get(i).getCat_name());
            }
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, stringArrayList);
            popupWindow.setAdapter(adapter);
            popupWindow.setAnchorView(binding.tvCategoryName);
            popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    binding.tvCategoryName.setText(stringArrayList.get(position));
                    binding.tvCategoryName.setText(categoryList.get(position).getCat_name().toString());
                    search = categoryList.get(position).getCat_name();

                    categoryList.clear();
                    storeList.clear();
                    topOfferList.clear();
                    productList.clear();

                    //getHomeDetails();
                    popupWindow.dismiss();
                    isPopup = false;
                }
            });
            popupWindow.show();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }*/