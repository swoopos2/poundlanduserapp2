package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.adapter.VouchersListAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityVouchersListBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.responseModel.GetCustomerVouchersResponseModel;
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
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SIGN_UP_REQU_MODEL;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class VouchersListActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {

    private ActivityVouchersListBinding binding;
    private PrefManager prefManager;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String search = "";
    private VouchersListActivity instance = null;
    private List<GetCustomerVouchersResponseModel.VoucherBean.DataBean> voucherList = null;
    private VouchersListAdapter vouchersListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vouchers_list);
        init();
        setListeners();
    }

    private void init() {
        instance = this;
        isLoading = true;
        pageNumber = 1;
        voucherList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        binding.title.setText(getString(R.string.voucher));

        getCustomerVoucher(false);

    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
       /* binding.ivSearchNowHeader.setOnClickListener(this);
        binding.ivFilter.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);*/
    }

    private void setCustomerVoucherAdapter() {
        vouchersListAdapter = new VouchersListAdapter(this, voucherList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvVouchers.setLayoutManager(layoutManager);
        binding.rvVouchers.setAdapter(vouchersListAdapter);
        binding.rvVouchers.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            getCustomerVoucher(true);
                        }
                    }
                }
            }
        });
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {  /// clickType ==> TOP_OFFER
        Intent product = new Intent(this, VoucherDetailActivity.class);
        product.putExtra(SIGN_UP_REQU_MODEL, voucherList.get(position));
        startActivity(product);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

          /*  case R.id.iv_search_now_header:
                isLoading = true;
                pageNumber = 1;
                allProductList.clear();
                search = binding.etSearchNowHeader.getText().toString();
                getAllProductByCategory(false);
                break;

            case R.id.iv_filter:
                if (filterListData != null)
                    homeFilterDialog(filterListData);
                   // new DialogShowVenueFilter(this, filterListData, this).show();
                break;
            case R.id.iv_cart:
                Intent intent = new Intent(this, MyCartActivity.class);
                startActivity(intent);
                break;*/
        }
    }

    @Override
    public void onRefresh() {
        voucherList.clear();
        isLoading = true;
        pageNumber = 1;
        getCustomerVoucher(false);

    }

    private void getCustomerVoucher(boolean isMore) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();


            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<GetCustomerVouchersResponseModel> call = apiInterface.getCustomerVoucher(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""));
            binding.llNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<GetCustomerVouchersResponseModel>() {

                @Override
                public void onResponse(Call<GetCustomerVouchersResponseModel> call, Response<GetCustomerVouchersResponseModel> response) {
                    try {
                        binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            GetCustomerVouchersResponseModel responseModel = response.body();
                          if (responseModel.isStatus()) {
                                isLoading = false;
                                totalPage = responseModel.getVoucher().getLast_page();

                              if (responseModel.getVoucher().getData() != null && responseModel.getVoucher().getData().size() > 0) {
                                    binding.llNoDataFound.setVisibility(View.GONE);
                                    voucherList.addAll(responseModel.getVoucher().getData());
                                    if (isMore) {
                                        if (vouchersListAdapter != null)
                                            vouchersListAdapter.notifyDataSetChanged();
                                    } else {
                                        setCustomerVoucherAdapter();
                                    }
                                } else {
                                    if (!isMore) {
                                        voucherList.clear();
                                        binding.llNoDataFound.setVisibility(View.VISIBLE);
                                        setCustomerVoucherAdapter();
                                    }
                                }

                            } else {
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
                            }
                          ////////////////////////////////////////////////////
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
                public void onFailure(Call<GetCustomerVouchersResponseModel> call, Throwable t) {
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
        //getTotalCartCount();
    }

   /* private void getTotalCartCount() {
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
    }*/

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

                if (data.equals("DISC_OFFER_NOTIFY")){
                    DialogUtils.offerNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent topStore = new Intent(this, NearByDealsActivity.class);
                        topStore.putExtra(TOP_OFFER_TITLE, getString(R.string.nearby_deals));
                        startActivity(topStore);
                    });
                } else if (!TextUtils.isEmpty(data)){
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
