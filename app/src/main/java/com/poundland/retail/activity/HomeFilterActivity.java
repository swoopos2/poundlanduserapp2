package com.poundland.retail.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.android.material.tabs.TabLayout;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.adapter.HomeProductFilterAdapter;
import com.poundland.retail.adapter.HomeVenueFilterAdapter;
import com.poundland.retail.adapter.VenueFilterListAdapter;
import com.poundland.retail.adapter.VenueFilterListItemAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityHomeFilterBinding;
import com.poundland.retail.databinding.DialogShowVenueFilterBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.AllProductByCategoryRequestModel;
import com.poundland.retail.model.requestModel.GetBrandResponseModel;
import com.poundland.retail.model.requestModel.ProductDetailByModifierRequestModel;
import com.poundland.retail.model.responseModel.HomeFilterResponseModel;
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
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FILTER_DATA_SELECTED;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.FUSED_LOCATION;
import static com.poundland.retail.interfaces.Constants.HOME_FILTER;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;
import static com.poundland.retail.interfaces.Constants.WHOLE_FILTER_API_DATA;

public class HomeFilterActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner, SuccessActionListner {

    private ActivityHomeFilterBinding binding;
    private PrefManager prefManager;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String search = "";
    private HomeFilterActivity instance = null;
    private HomeProductFilterAdapter homeProductFilterAdapter;
    private HomeVenueFilterAdapter homeVenueFilterAdapter;
    private String CLICK_TYPE;
    private String venue_id = "";
    private int isVenue = 0;
    private HashMap<String, List<String>> filterData;
    private List<HomeFilterResponseModel.ProductsBean.DataBean> listFilter;
    private List<VenueFilterDataResponseModel.FilterBean> filterListData = new ArrayList<>();
    private VenueFilterListAdapter filterListAdapter;
    private List<String> listSelectedCategory;
    private Dialog dialog;
    private int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home_filter);
        init();
        setListeners();
        getProductFilterData(false);

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

    private void init() {
        instance = this;
        prefManager = PrefManager.getInstance(this);
        isLoading = true;
        pageNumber = 1;
        binding.rlFram.setVisibility(View.GONE);
        CLICK_TYPE = getString(R.string.products);
        binding.title.setText(getString(R.string.products));
        binding.tvCount.setText( total>1 ? "Found "+total+ " Products": "Found "+total+ " Product" );
        binding.tvTopOffersNearYouLocation.setText(prefManager.getPreference(FUSED_LOCATION, ""));
        listFilter = new ArrayList<>();
        setTabs();
        filterData = new HashMap<>();
        listSelectedCategory = new ArrayList<>();
        filterListData = getIntent().getExtras().getParcelableArrayList(WHOLE_FILTER_API_DATA);
        filterData = (HashMap<String, List<String>>) getIntent().getSerializableExtra(FILTER_DATA_SELECTED);


    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
        binding.ivFilter.setOnClickListener(this);

    }

    private void setTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.products)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.venues)));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //  selectedTab = tab.getPosition();
                switch (binding.tabLayout.getSelectedTabPosition()) {
                    case 0:
                        isVenue = 0;
                        CLICK_TYPE = getString(R.string.products);
                        binding.title.setText(getString(R.string.products));
                        listFilter.clear();
                        if (homeProductFilterAdapter != null)
                            homeProductFilterAdapter.notifyDataSetChanged();
                        pageNumber = 1;
                        getProductFilterData(false);
                        break;
                    case 1:
                        CLICK_TYPE = getString(R.string.venues);
                        isVenue = 1;
                        binding.title.setText(getString(R.string.venues));
                        pageNumber = 1;
                        listFilter.clear();


                        if (homeVenueFilterAdapter != null)
                            homeVenueFilterAdapter.notifyDataSetChanged();

                        getProductFilterData(false);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    private void setProductfilterAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        homeProductFilterAdapter = new HomeProductFilterAdapter(this, listFilter, this, this);
        binding.rvTopOffers.setLayoutManager(layoutManager);
        binding.rvTopOffers.setAdapter(homeProductFilterAdapter);
        binding.rvTopOffers.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                    if (!isLoading && pageNumber <= totalPage) {
                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            isLoading = true;
                            getProductFilterData(true);
                        }
                    }
                }
            }
        });
    }

    private void setVenuefilterAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        homeVenueFilterAdapter = new HomeVenueFilterAdapter(this, listFilter, this);
        binding.rvTopOffers.setLayoutManager(layoutManager);
        binding.rvTopOffers.setAdapter(homeVenueFilterAdapter);
        binding.rvTopOffers.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            getProductFilterData(true);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {  /// clickType ==> TOP_OFFER
        if (clickType == STORE) {
            Intent topStore = new Intent(this, VenueDetailActivity.class);
            topStore.putExtra(STORE_ID, listFilter.get(position).getVenue_id());
            topStore.putExtra(FROM_WHERE, HOME_FILTER);
            startActivity(topStore);
        } else {
            Intent topProduct = new Intent(this, ProductDetailActivity.class);
            topProduct.putExtra(STORE_ID, listFilter.get(position).getVenue_id());
            topProduct.putExtra(PRODUCT_ID, String.valueOf(listFilter.get(position).getId()));
            topProduct.putExtra(OFFER_ID, "");
            topProduct.putExtra(BARCODE_ID, "");
            startActivity(topProduct);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_filter:
                homeFilterDialog(filterListData);
                break;
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

        rv_filterList.setLayoutManager(new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
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
                filterData = hashMap;
            }
            Log.e("filterList ", "" + hashMap.toString());
            isLoading = true;
            pageNumber = 1;
            listFilter.clear();

            if (isVenue == 0) {
                if (homeProductFilterAdapter != null)
                    homeProductFilterAdapter.notifyDataSetChanged();
            } else {
                if (homeVenueFilterAdapter != null)
                    homeVenueFilterAdapter.notifyDataSetChanged();
            }

            getProductFilterData(false);
            dialog.dismiss();

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

    private void getProductFilterData(boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            AllProductByCategoryRequestModel allProductByCategoryRequestModel = new AllProductByCategoryRequestModel(isVenue, search, venue_id, prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""), filterData);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<HomeFilterResponseModel> call = apiInterface.getProductsByFilter(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), allProductByCategoryRequestModel, String.valueOf(pageNumber++));
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<HomeFilterResponseModel>() {
                @Override
                public void onResponse(Call<HomeFilterResponseModel> call, Response<HomeFilterResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            HomeFilterResponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {
                                isLoading = false;
                                totalPage = responseModel.getProducts().getLast_page();
                                total = responseModel.getProducts().getTotal();
                                if (isVenue == 1) {
                                    binding.tvCount.setText( total>1 ? "Found "+total+ " Venues": "Found "+total+ " Venue" );
                                } else {
                                    binding.tvCount.setText( total>1 ? "Found "+total+ " Products": "Found "+total+ " Product" );
                                }
                                if (responseModel.getIsProductInCart() == 1) {
                                    binding.rlFram.setVisibility(View.VISIBLE);
                                }

                                if (responseModel.getProducts().getData() != null && responseModel.getProducts().getData().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);
                                    listFilter.addAll(responseModel.getProducts().getData());
                                    if (isMore) {
                                        if (isVenue == 0) {
                                            if (homeProductFilterAdapter != null)
                                                homeProductFilterAdapter.notifyDataSetChanged();
                                        } else {
                                            if (homeVenueFilterAdapter != null)
                                                homeVenueFilterAdapter.notifyDataSetChanged();
                                        }


                                    } else {
                                        if (isVenue == 0) {
                                            setProductfilterAdapter();
                                        } else
                                            setVenuefilterAdapter();
                                    }
                                } else {
                                    if (!isMore) {
                                        listFilter.clear();
                                        binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                        if (isVenue == 0) {
                                            setProductfilterAdapter();
                                        } else
                                            setVenuefilterAdapter();
                                    }
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
                public void onFailure(Call<HomeFilterResponseModel> call, Throwable t) {
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

    @Override
    public void onRefresh() {
        isLoading = true;
        pageNumber = 1;
        listFilter.clear();

        if (isVenue == 0) {
            if (homeProductFilterAdapter != null)
                homeProductFilterAdapter.notifyDataSetChanged();
        } else {
            if (homeVenueFilterAdapter != null)
                homeVenueFilterAdapter.notifyDataSetChanged();
        }

        getProductFilterData(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getTotalCartCount();
    }

    @Override
    public void onSuccessActionListner() {
        binding.rlFram.setVisibility(View.VISIBLE);
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

                                if (totalCartResponseModel.getTotal_carts() != 0) {
                                  //  binding.tvCartCount.setVisibility(View.VISIBLE);
                                    binding.tvProdCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));
                                } else {
                                    binding.rlFram.setVisibility(View.GONE);
                                 //   binding.tvCartCount.setVisibility(View.GONE);
                                    prefManager.savePreference(VENUE_ID_IN_CART, "");
                                }
                             //   binding.tvCartCount.setText(String.valueOf(totalCartResponseModel.getTotal_carts()));

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

     /*private void homeFilterDialog(List<VenueFilterDataResponseModel.FilterBean> filterListData) {
        final Dialog dialog = new Dialog(this, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_show_venue_filter);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopIn);
        dialog.show();
        HashMap<String, List<String>> hashMap = new HashMap();
        RecyclerView rv_filterList = dialog.findViewById(R.id.rv_filterList);
        filterListAdapter = new VenueFilterListAdapter(this, filterListData, new ModifierSelectionListner() {
            @Override
            public void onModifierSelect(int pos_1st, int pos_2nd) {
                if (filterListData.get(pos_1st).getFilter_type().contains(getResources().getString(R.string.category))) {
                    if (!listSelectedCategory.contains(filterListData.get(pos_1st).getFilter_list().get(pos_2nd).getFilter_value())) {
                        listSelectedCategory.add(filterListData.get(pos_1st).getFilter_list().get(pos_2nd).getFilter_value());
                    }
                    if (listSelectedCategory.size()>0)
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
                    if (listSelectedCategory.size()>0)
                    getBrands(listSelectedCategory, dialog);
                    Log.e("listSelectedCategory R", "" + listSelectedCategory);
                }
            }
        });

        final LinearLayoutManager layoutManager = new LinearLayoutManager(instance);
        rv_filterList.setLayoutManager(layoutManager);
        rv_filterList.setAdapter(filterListAdapter);

        dialog.findViewById(R.id.tv_clear_all).setOnClickListener(v -> {

            if (filterListAdapter != null && filterListData != null)
               // filterListAdapter.addData(filterListData);
                filterListAdapter.clearAllCheck();
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
                filterData=hashMap;
            }
            isLoading = true;
            pageNumber = 1;
            listFilter.clear();
            getProductFilterData(false);
            dialog.dismiss();
        });
    }*/
}
