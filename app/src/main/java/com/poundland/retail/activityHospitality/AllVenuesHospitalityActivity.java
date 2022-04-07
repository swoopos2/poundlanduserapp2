package com.poundland.retail.activityHospitality;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.BaseActivity;
import com.poundland.retail.activity.MyCartActivity;
import com.poundland.retail.activity.MyOrderActivity;
import com.poundland.retail.activity.NearByDealsActivity;
import com.poundland.retail.activity.NotificationActivity;
import com.poundland.retail.activity.VenueDetailActivity;
import com.poundland.retail.activity.SpecialOfferDetailsActivity;
import com.poundland.retail.activityHospitality.adapter.AllVenueHospitalityAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityAllVenueBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.AllVenuesHospitalityRequestModel;
import com.poundland.retail.model.responseModel.AllVenueHospitalityResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.FUSED_LOCATION;
import static com.poundland.retail.interfaces.Constants.HOME_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.HOME_RETAIL;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SLUG;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class AllVenuesHospitalityActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {

    private ActivityAllVenueBinding binding;
    private PrefManager prefManager;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String search = "";
    private AllVenuesHospitalityActivity instance = null;
    private List<AllVenueHospitalityResponseModel.VenuesBean.DataBean> allVenueList;
    private AllVenueHospitalityAdapter venueHospitalityAdapter;
    private String cat_id;
    private List<String> cat_list;
    private AllVenueHospitalityResponseModel allProductByCategoryResponseModel;
    private String slug;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_all_venue);
        binding.ivFilter.setVisibility(View.GONE);
        cat_id = getIntent().getStringExtra(CATEGORY_ID);
        slug = getIntent().getStringExtra(SLUG);
        cat_list = new ArrayList<>();
     //   cat_list = Collections.singletonList(getIntent().getStringExtra(CATEGORY_NAME));
     //   if (cat_list.size()>0 && cat_list.get(0).contains("Restaurant,")){
      //      cat_list =  Arrays.asList("Restaurant","Cafes & Bars");
      //  }
        if (TextUtils.isEmpty(slug)){

        }
        if (TextUtils.isEmpty(cat_id)) {
            cat_id = "2";
        }
        init();
        setListeners();
        getAllvenueHospitality(false);
        binding.etSearchNowHeader.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {

                    search = binding.etSearchNowHeader.getText().toString();
                    if (search.length() > 0) {
                        allVenueList.clear();
                        if (venueHospitalityAdapter != null)
                            venueHospitalityAdapter.notifyDataSetChanged();
                        isLoading = true;
                        pageNumber = 1;
                        getAllvenueHospitality(false);
                    }
                    handled = true;
                }
                return handled;
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
                }else{
                    binding.ivClose.setVisibility(View.GONE);
                }
            }
        });


    }

    private void init() {
        instance = this;
        isLoading = true;
        pageNumber = 1;
        allVenueList = new ArrayList<>();
        // category_id = getIntent().getStringExtra(CATEGORY_ID);
        prefManager = PrefManager.getInstance(this);
        binding.title.setText(getString(R.string.venue_list));
        binding.tvTopOffersNearYouLocation.setText(prefManager.getPreference(FUSED_LOCATION, ""));
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.ivNotification.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
        binding.ivSearchNowHeader.setOnClickListener(this);
        binding.ivClose.setOnClickListener(this);
    }


    private void setAllVenueAdapter() {
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(instance, LinearLayoutManager.VERTICAL, false);
        venueHospitalityAdapter = new AllVenueHospitalityAdapter(this, allVenueList, this);
        binding.rvVenueList.setLayoutManager(layoutManager);
        binding.rvVenueList.setAdapter(venueHospitalityAdapter);
        binding.rvVenueList.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                //check for scroll down
                if (allProductByCategoryResponseModel.getUsePaginate()==1) {
                    if (dy > 0) {
                        int visibleItemCount = layoutManager.getChildCount();
                        int totalItemCount = layoutManager.getItemCount();
                        int pastVisiblesItems = layoutManager.findFirstVisibleItemPosition();

                        if (!isLoading && pageNumber <= totalPage) {
                            if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                                isLoading = true;
                                getAllvenueHospitality(true);
                            }
                        }
                    }
                }
            }
        });

    }


    @Override
    public void onDrawerSelect(int position, int clickType) {  /// clickType ==> TOP_OFFER
        Intent topStore = new Intent(this, VenueDetailActivity.class);
        topStore.putExtra(STORE_ID, allVenueList.get(position).getVenue_id());
        if ( allVenueList.get(position).getVenue_type()==2){
            topStore.putExtra(FROM_WHERE, HOME_HOSPITALITY);
        }else {
            topStore.putExtra(FROM_WHERE, HOME_RETAIL);
        }

        topStore.putExtra(CATEGORY_ID, "");
        startActivity(topStore);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.iv_search_now_header:
                allVenueList.clear();
                if (venueHospitalityAdapter != null)
                    venueHospitalityAdapter.notifyDataSetChanged();

                isLoading = true;
                pageNumber = 1;
                search = binding.etSearchNowHeader.getText().toString();
                getAllvenueHospitality(false);
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
            case R.id.iv_notification:
                Intent noti = new Intent(this, NotificationActivity.class);
                startActivity(noti);

                break;

            case R.id.iv_close:
                    binding.etSearchNowHeader.setText("");
                break;

        }
    }


    @Override
    public void onRefresh() {
        allVenueList.clear();
        if (venueHospitalityAdapter != null)
            venueHospitalityAdapter.notifyDataSetChanged();

        isLoading = true;
        pageNumber = 1;
        binding.etSearchNowHeader.setText("");
        search = "";
        getAllvenueHospitality(false);
    }

    private void getAllvenueHospitality(boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            AllVenuesHospitalityRequestModel allVenuesRequestModel = new AllVenuesHospitalityRequestModel(cat_id,
                    String.valueOf(prefManager.getPreference(LOGIN_ID, 0)), "", "", "", "", search,
                    prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""),
                    cat_list, null, null,slug);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AllVenueHospitalityResponseModel> call = apiInterface.venue_list_Hospitality(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), allVenuesRequestModel, String.valueOf(pageNumber++));
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<AllVenueHospitalityResponseModel>() {
                @Override
                public void onResponse(Call<AllVenueHospitalityResponseModel> call, Response<AllVenueHospitalityResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                             allProductByCategoryResponseModel = response.body();
                            if (allProductByCategoryResponseModel.isStatus()) {
                                isLoading = false;
                                totalPage = allProductByCategoryResponseModel.getVenues().getLast_page();
                                if (allProductByCategoryResponseModel.getUsePaginate()==1){
                                    binding.tvStoreFound.setText("Found " + allProductByCategoryResponseModel.getVenues().getTotal() + " Stores Near You");
                                }else binding.tvStoreFound.setText("Found " + allProductByCategoryResponseModel.getVenues().getTo() + " Stores Near You");

                                if (allProductByCategoryResponseModel.getVenues().getData() != null &&
                                        allProductByCategoryResponseModel.getVenues().getData().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);
                                    // allVenueList.clear();
                                    allVenueList.addAll(allProductByCategoryResponseModel.getVenues().getData());


                                    if (isMore) {
                                        if (venueHospitalityAdapter != null)
                                            venueHospitalityAdapter.notifyDataSetChanged();
                                    } else {
                                        setAllVenueAdapter();
                                    }

                                } else {
                                    if (!isMore) {
                                        binding.tvNoDataFound.setVisibility(View.VISIBLE);
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
                public void onFailure(Call<AllVenueHospitalityResponseModel> call, Throwable t) {
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
                                if (totalCartResponseModel.getTotal_carts() > 0) {
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
                                } else
                                    binding.tvCartCount.setVisibility(View.INVISIBLE);
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
