package com.poundland.retail.activity;

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
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.adapter.AllProductByCategoryAdapter;
import com.poundland.retail.adapter.VenueFilterListAdapter;
import com.poundland.retail.adapter.VenueFilterListItemAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityAllProductByCategoryBinding;
import com.poundland.retail.databinding.DialogShowVenueFilterBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.FilterListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.AllProductByCategoryRequestModel;
import com.poundland.retail.model.requestModel.GetBrandResponseModel;
import com.poundland.retail.model.requestModel.ProductDetailByModifierRequestModel;
import com.poundland.retail.model.requestModel.VenueFilterDataRequestModel;
import com.poundland.retail.model.responseModel.AllProductByCategoryResponseModel;
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
import static com.poundland.retail.interfaces.Constants.DISTANCE;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FILTER_DATA_SELECTED;
import static com.poundland.retail.interfaces.Constants.FROM_APPLY_FILTER_TAP;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.IS_PRODUCT_IN_CART;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;
import static com.poundland.retail.interfaces.Constants.VENUE_NAME;
import static com.poundland.retail.interfaces.Constants.WHOLE_FILTER_API_DATA;

public class AllProductByCategoryListActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner, FilterListner, SuccessActionListner {

    private ActivityAllProductByCategoryBinding binding;
    private PrefManager prefManager;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String search = "";
    private List<AllProductByCategoryResponseModel.ProductsBean.DataBean> allProductList;
    private String category_id = "";
    private String venue_id = "";
    //private int isProductInCart;
    private AllProductByCategoryListActivity instance = null;
    private List<VenueFilterDataResponseModel.FilterBean> filterListData;
    private AllProductByCategoryAdapter allProductByCategoryAdapter;
    private HashMap<String, List<String>> filterData;
    private String fromWhere;
    private Dialog dialog;
    private VenueFilterListAdapter filterListAdapter;
    private List<String> listSelectedCategory;
    private String category_name;
    //private boolean isUpcomingVenue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_product_by_category);
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


    }

    @Override
    protected void onResume() {
        super.onResume();
        init();
        setListeners();
        getTotalCartCount();

    }

    private void init() {
        instance = this;
        isLoading = true;
        pageNumber = 1;
        filterData = new HashMap<>();
        allProductList = new ArrayList<>();
        filterListData = new ArrayList<>();
        listSelectedCategory = new ArrayList<>();
        binding.rlFram.setVisibility(View.GONE);
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        // isUpcomingVenue = getIntent().getBooleanExtra(IS_UPCOMING_VENUE,false);
        category_id = getIntent().getStringExtra(CATEGORY_ID);
        category_name = getIntent().getStringExtra(CATEGORY_NAME);
        venue_id = getIntent().getStringExtra(STORE_ID);
       // isProductInCart = getIntent().getIntExtra(IS_PRODUCT_IN_CART, 0);
       // Log.e("SKKKK","p "+isProductInCart);
        prefManager = PrefManager.getInstance(this);
        binding.title.setText(getString(R.string.product_list));

        binding.tvStoreName.setText(getIntent().getStringExtra(VENUE_NAME));
        binding.tvDistance.setText(getIntent().getStringExtra(DISTANCE));


        getAllProductByCategory(false);
        if (!fromWhere.equals(FROM_APPLY_FILTER_TAP)) {
            getproductFilterData();
        } else {
            filterListData = getIntent().getExtras().getParcelableArrayList(WHOLE_FILTER_API_DATA);
            filterData = (HashMap<String, List<String>>) getIntent().getSerializableExtra(FILTER_DATA_SELECTED);
        }

        binding.etSearchNowHeader.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    search = binding.etSearchNowHeader.getText().toString();
                    if (search.length() > 0) {
                        isLoading = true;
                        pageNumber = 1;
                        allProductList.clear();
                        if (allProductByCategoryAdapter != null)
                            allProductByCategoryAdapter.notifyDataSetChanged();
                        getAllProductByCategory(false);
                    }
                    handled = true;
                }
                return handled;
            }

        });


    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
        binding.ivSearchNowHeader.setOnClickListener(this);
        binding.ivFilter.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.ivClose.setOnClickListener(this);
    }

    private void setAllProductByCategoryAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        allProductByCategoryAdapter = new AllProductByCategoryAdapter(this, allProductList, this, this);
        binding.rvProductList.setLayoutManager(layoutManager);
        binding.rvProductList.setAdapter(allProductByCategoryAdapter);
        binding.rvProductList.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            getAllProductByCategory(true);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {  /// clickType ==> TOP_OFFER
        Intent product = new Intent(this, ProductDetailActivity.class);
        product.putExtra(STORE_ID, allProductList.get(position).getVenue_id());
        product.putExtra(PRODUCT_ID, String.valueOf(allProductList.get(position).getId()));
        product.putExtra(OFFER_ID, String.valueOf(allProductList.get(position).getOffer_id()));
        product.putExtra(BARCODE_ID, "");
        startActivity(product);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.iv_close:

                if (binding.etSearchNowHeader.getText().toString().length() > 0) {
                    allProductList.clear();
                    if (allProductByCategoryAdapter != null)
                        allProductByCategoryAdapter.notifyDataSetChanged();
                    isLoading = true;
                    pageNumber = 1;
                    filterData = new HashMap<>();
                    binding.etSearchNowHeader.setText("");
                    search = "";
                    getAllProductByCategory(false);
                    if (!fromWhere.equals(FROM_APPLY_FILTER_TAP)) {
                        getproductFilterData();
                    }
                } else {
                    Toast.makeText(instance, "empt fld", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.iv_search_now_header:
                isLoading = true;
                pageNumber = 1;
                allProductList.clear();
                if (allProductByCategoryAdapter != null)
                    allProductByCategoryAdapter.notifyDataSetChanged();
                search = binding.etSearchNowHeader.getText().toString();
                getAllProductByCategory(false);
                break;

            case R.id.iv_filter:
                if (filterListData != null)
                    homeFilterDialog(filterListData);
                // new DialogShowVenueFilter(this, filterListData, this).show();
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
        }
    }

    @Override
    public void onRefresh() {
        allProductList.clear();
        if (allProductByCategoryAdapter != null)
            allProductByCategoryAdapter.notifyDataSetChanged();
        isLoading = true;
        pageNumber = 1;
        filterData = new HashMap<>();
        binding.etSearchNowHeader.setText("");
        search = "";
        getAllProductByCategory(false);
        if (!fromWhere.equals(FROM_APPLY_FILTER_TAP)) {
            getproductFilterData();
        }

        getTotalCartCount();

    }

    private void getAllProductByCategory(boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            AllProductByCategoryRequestModel allProductByCategoryRequestModel =
                    new AllProductByCategoryRequestModel(category_id, search, venue_id, prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""), filterData);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AllProductByCategoryResponseModel> call = apiInterface.getAllProductByCategory(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), allProductByCategoryRequestModel, String.valueOf(pageNumber++));
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<AllProductByCategoryResponseModel>() {
                @Override
                public void onResponse(Call<AllProductByCategoryResponseModel> call, Response<AllProductByCategoryResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            AllProductByCategoryResponseModel allProductByCategoryResponseModel = response.body();
                            if (allProductByCategoryResponseModel.isStatus()) {
                                isLoading = false;
                                totalPage = allProductByCategoryResponseModel.getProducts().getLast_page();
                                binding.tvStoreFound.setText("Found " + allProductByCategoryResponseModel.getProducts().getTotal() + " Product in " + category_name + " Category");

                                if (allProductByCategoryResponseModel.getIsProductInCart() == 1 || getIntent().getIntExtra(IS_PRODUCT_IN_CART, 0) == 1) {
                                    binding.rlFram.setVisibility(View.VISIBLE);
                                }

                                if (allProductByCategoryResponseModel.getProducts().getData() != null &&
                                        allProductByCategoryResponseModel.getProducts().getData().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);
                                    allProductList.addAll(allProductByCategoryResponseModel.getProducts().getData());
                                    binding.tvStoreName.setText(allProductList.get(0).getVenue_name());
                                    binding.tvDistance.setText(allProductList.get(0).getDistance() != null ? allProductList.get(0).getDistance() + getString(R.string.miles) : "" + getString(R.string.miles));
                                    if (isMore) {
                                        if (allProductByCategoryAdapter != null)
                                            allProductByCategoryAdapter.notifyDataSetChanged();
                                    } else {
                                        setAllProductByCategoryAdapter();
                                    }
                                } else {
                                    if (!isMore) {
                                        allProductList.clear();
                                        if (allProductByCategoryAdapter != null)
                                            allProductByCategoryAdapter.notifyDataSetChanged();
                                        binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                        setAllProductByCategoryAdapter();
                                    }
                                }

                            } else {
                                showSnackBar(binding.getRoot(), allProductByCategoryResponseModel.getMessage());
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
                public void onFailure(Call<AllProductByCategoryResponseModel> call, Throwable t) {
                    binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void getTotalCartCount() {
        if (isInternetOn(this)) {
            binding.tvCartCount.setVisibility(View.INVISIBLE);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<GetTotalCartResponseModel> call = apiInterface.getTotalCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""));
            call.enqueue(new Callback<GetTotalCartResponseModel>() {
                @Override
                public void onResponse(Call<GetTotalCartResponseModel> call, Response<GetTotalCartResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            GetTotalCartResponseModel totalCartResponseModel = response.body();
                            if (totalCartResponseModel.isStatus()) {

                                ///////
                                if (totalCartResponseModel.getTotal_carts() != 0) {
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
                                    binding.tvProdCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
                                } else {
                                    binding.rlFram.setVisibility(View.GONE);
                                    binding.tvCartCount.setVisibility(View.GONE);
                                    prefManager.savePreference(VENUE_ID_IN_CART, "");
                                }
                                binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));

                                ///////
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

    private void getproductFilterData() {

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
                                filterListData = new ArrayList<>();
                                if (responseModel.getFilter() != null && responseModel.getFilter().size() > 0) {
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
    public void onApplyFilter(HashMap<String, List<String>> data) {
        isLoading = true;
        pageNumber = 1;
        allProductList.clear();
        if (allProductByCategoryAdapter != null)
            allProductByCategoryAdapter.notifyDataSetChanged();
        filterData = data;
        getAllProductByCategory(false);
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
            isLoading = true;
            pageNumber = 1;
            allProductList.clear();
            if (allProductByCategoryAdapter != null)
                allProductByCategoryAdapter.notifyDataSetChanged();
            filterData = hashMap;
            getAllProductByCategory(false);

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
}
