package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.adapter.AddressAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityAddressBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.AddressRequestModel;
import com.poundland.retail.model.requestModel.SaveLocationRequestModel;
import com.poundland.retail.model.responseModel.AddressResponseModel;
import com.poundland.retail.model.responseModel.SaveLocationResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.ADDRESS;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ID;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.DELETE_ITEM;
import static com.poundland.retail.interfaces.Constants.EDIT_ADDRESS_REQU_MODEL;
import static com.poundland.retail.interfaces.Constants.EDIT_ITEM;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_ADD_ADDRESS;
import static com.poundland.retail.interfaces.Constants.FROM_EDIT_ADDRESS;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.OPEN_ADD_ADDRESS_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PAYMENT_DETAIL_ADD_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;

public class AddressActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {
    private ActivityAddressBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String userId;
    private List<AddressResponseModel.AddressessBean> addressessBeanList;
    private AddressActivity instance = null;
    private String fromWhere = "";
    private AddressAdapter addressAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_address);
        init();
        getAddressList(false);
        setListeners();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTotalCartCount();
    }

    private void init() {
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        instance = this;
        isLoading = true;
        pageNumber = 1;
        addressessBeanList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.title.setText(getString(R.string.address));
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.fabAddAddress.setOnClickListener(this);
        // binding.swipeRefresh.setOnRefreshListener(this);\
        binding.ivCart.setOnClickListener(this);
        binding.ivNotification.setOnClickListener(this);


    }

    private void setAdapter() {
        addressAdapter = new AddressAdapter(this, addressessBeanList, fromWhere, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvAddress.setLayoutManager(layoutManager);
        binding.rvAddress.setAdapter(addressAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.fab_add_address:
                Intent in = new Intent(instance, AddAddressActivity.class);
                in.putExtra(FROM_WHERE, FROM_ADD_ADDRESS);
                startActivityForResult(in, OPEN_ADD_ADDRESS_ACTIVITY);
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
        }
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {
        if (clickType == EDIT_ITEM) {
            Intent intent = new Intent(this, AddAddressActivity.class);
            intent.putExtra(EDIT_ADDRESS_REQU_MODEL, addressessBeanList.get(position));
            intent.putExtra(FROM_WHERE, FROM_EDIT_ADDRESS);
            startActivityForResult(intent, OPEN_ADD_ADDRESS_ACTIVITY);

        } else if (clickType == DELETE_ITEM) {

            DialogUtils.dialogLogOut(instance, getString(R.string.delete_address_message), getString(R.string.delete_address), new OnDialogClickListener() {
                @Override
                public void onPositiveClick() {
                    deleteAddress(String.valueOf(addressessBeanList.get(position).getId()), position);
                }

                @Override
                public void onNegativeClick() {

                }
            });

        } else if (clickType == ADDRESS) {
            String address = addressessBeanList.get(position).getArea()+", "+ addressessBeanList.get(position).getLandmark()+", " + addressessBeanList.get(position).getCity()
                    +" "+ addressessBeanList.get(position).getPincode() ;
            address = address.replace("null", "");
            if (fromWhere != null && fromWhere.equals(getString(R.string.cart))) {
                Intent intent = new Intent();
                intent.putExtra(ADDRESS_ACTIVITY_CALLBACK, address);
                intent.putExtra(ADDRESS_ID, addressessBeanList.get(position).getId());

                setResult(RESULT_OK, intent);
                finish();
            }

        }
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_ADD_ADDRESS_ACTIVITY && resultCode == RESULT_OK && data != null) {
            Intent intent = new Intent();
            intent.putExtra(ADDRESS_ACTIVITY_CALLBACK, data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));
            intent.putExtra(ADDRESS_ID, data.getIntExtra(ADDRESS_ID,0));
            setResult(RESULT_OK, intent);
            finish();

        } else if (requestCode == OPEN_PAYMENT_DETAIL_ADD_ACTIVITY && resultCode == RESULT_CANCELED) {

        }
    }

    private void getAddressList(boolean isClose) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isClose)
                dialog.show();

            AddressRequestModel productBySearchRequestModel = new AddressRequestModel("", "");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddressResponseModel> call = apiInterface.getUserAddress(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), productBySearchRequestModel);
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<AddressResponseModel>() {
                @Override
                public void onResponse(Call<AddressResponseModel> call, Response<AddressResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            AddressResponseModel homeResponseModel = response.body();
                            if (homeResponseModel.isStatus()) {

                                if (homeResponseModel.getAddressess() != null && homeResponseModel.getAddressess().size() > 0) {
                                    binding.tvNoDataFound.setVisibility(View.GONE);
                                    addressessBeanList.clear();
                                    addressessBeanList.addAll(homeResponseModel.getAddressess());
                                    setAdapter();
                                } else {
                                    //   binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    addressessBeanList.clear();
                                    setAdapter();

                                    Intent in = new Intent(instance, AddAddressActivity.class);
                                    in.putExtra(FROM_WHERE, FROM_ADD_ADDRESS);
                                    startActivityForResult(in, OPEN_ADD_ADDRESS_ACTIVITY);
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
                        Log.e("onException ", " - " + e);
                    }
                }

                @Override
                public void onFailure(Call<AddressResponseModel> call, Throwable t) {
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

    private void deleteAddress(String id, final int position) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ;

            SaveLocationRequestModel requestModel = new SaveLocationRequestModel(id);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<SaveLocationResponseModel> call = apiInterface.deleteLocation(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
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

                                    DialogUtils.showAlertDialogWithSingleButton(instance, responseModel.getMessage(), new OnDialogClickListener() {
                                        @Override
                                        public void onPositiveClick() {
                                            addressessBeanList.remove(position);
                                            addressAdapter.notifyDataSetChanged();

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
