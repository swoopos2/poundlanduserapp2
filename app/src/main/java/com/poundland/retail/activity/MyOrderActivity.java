package com.poundland.retail.activity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
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
import com.poundland.retail.adapter.CancellationReasonAdapter;
import com.poundland.retail.adapter.MyOrderCancelledAdapter;
import com.poundland.retail.adapter.MyOrderCompletedAdapter;
import com.poundland.retail.adapter.MyOrderNewAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.appUtils.WrapContentLinearLayoutManager;
import com.poundland.retail.databinding.ActivityMyOrderBinding;
import com.poundland.retail.databinding.DialogReasonForCancellationBinding;
import com.poundland.retail.dialog.DialogAllReviews;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.dialog.DialogWriteReviews;
import com.poundland.retail.interfaces.CancelListner;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.MyOrderListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.SuccessActionListner;
import com.poundland.retail.model.requestModel.AddressRequestModel;
import com.poundland.retail.model.requestModel.CancelOrderRequestModel;
import com.poundland.retail.model.requestModel.ReturnRequestModel;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.model.responseModel.MyOrderResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.ADDRESS_GET;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CANCEL_DATE;
import static com.poundland.retail.interfaces.Constants.CANCEL_PRODUCT_MESSAGE;
import static com.poundland.retail.interfaces.Constants.CANCEL_STATUS;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.COLLECT_TIME;
import static com.poundland.retail.interfaces.Constants.CONTACT_NO;
import static com.poundland.retail.interfaces.Constants.DELIVERY_TYPE;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.ESTIMATED_DELIVERY;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.IMAGE_LINK;
import static com.poundland.retail.interfaces.Constants.MESSAGE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.ORDER_DATE;
import static com.poundland.retail.interfaces.Constants.ORDER_DETAIL_ID;
import static com.poundland.retail.interfaces.Constants.ORDER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.QUANTITY;
import static com.poundland.retail.interfaces.Constants.REPLACE_REFUND;
import static com.poundland.retail.interfaces.Constants.REVIEW_TO_OVERALL;
import static com.poundland.retail.interfaces.Constants.REVIEW_TO_PRODUCT;
import static com.poundland.retail.interfaces.Constants.SHOW_REVIEW;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.TRACK_DATA;
import static com.poundland.retail.interfaces.Constants.TRACK_PACKAGE;
import static com.poundland.retail.interfaces.Constants.TRACK_STATUS;
import static com.poundland.retail.interfaces.Constants.VENUE;
import static com.poundland.retail.interfaces.Constants.VENUE_ADDRESS;
import static com.poundland.retail.interfaces.Constants.VENUE_ID;
import static com.poundland.retail.interfaces.Constants.VENUE_TYPE;

public class MyOrderActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, CancelListner, MyOrderListner, SuccessActionListner {
    private ActivityMyOrderBinding binding;
    private PrefManager prefManager;
    private String authToken;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String userId;
    private List<MyOrderResponseModel.OrdersBean.DataBean> myOrderList;
    private List<MyOrderResponseModel.ReturnReasonsBean> returnReasonsList;
    private MyOrderActivity instance = null;
    private String NEW_ORDER = "0";
    private String CANCELED_ORDER = "1";
    private String COMPLETED_ORDER = "4";
    private String ORDER_TYPE = "0";
    private MyOrderNewAdapter myOrderNewAdapter;
    private MyOrderCompletedAdapter myOrderCompletedAdapter;
    private MyOrderCancelledAdapter myOrderCancelledAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order);
        init();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getTotalCartCount();
    }

    private void init() {
        instance = this;
        isLoading = true;
        pageNumber = 1;
        prefManager = PrefManager.getInstance(this);
        myOrderList = new ArrayList<>();
        returnReasonsList = new ArrayList<>();
        authToken = prefManager.getPreference(AUTH_TOKEN, "");
        binding.title.setText(getString(R.string.my_orders));
        setTabs();
        setListeners();
        myOrder(false, NEW_ORDER);
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.ivAppLogo.setOnClickListener(this);
//        binding.ivNotification.setOnClickListener(this);
        //   binding.swipeRefresh.setOnRefreshListener(this);
    }

    private void setNewOrderAdapter() {
        myOrderNewAdapter = new MyOrderNewAdapter(this, myOrderList, this);
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //new LinearLayoutManager(this);
        binding.rvLsv.setLayoutManager(layoutManager);
        binding.rvLsv.setAdapter(myOrderNewAdapter);

        binding.rvLsv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            ORDER_TYPE = NEW_ORDER;
                            myOrder(true, NEW_ORDER);
                        }
                    }
                }
            }
        });


    }

    private void setCompletedOrderAdapter() {
        myOrderCompletedAdapter = new MyOrderCompletedAdapter(this, myOrderList, returnReasonsList, this);
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        binding.rvLsv.setLayoutManager(layoutManager);
        binding.rvLsv.setAdapter(myOrderCompletedAdapter);

        //////////////////////////////////////////////////////////////////////////////////////////////
        binding.rvLsv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            ORDER_TYPE = COMPLETED_ORDER;
                            myOrder(true, COMPLETED_ORDER);
                        }
                    }
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////


    }

    private void setCancelledOrderAdapter() {
        myOrderCancelledAdapter = new MyOrderCancelledAdapter(this, myOrderList);
        final LinearLayoutManager layoutManager = new WrapContentLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false); //new LinearLayoutManager(this);
        binding.rvLsv.setLayoutManager(layoutManager);
        binding.rvLsv.setAdapter(myOrderCancelledAdapter);

        //////////////////////////////////////////////////////////////////////////////////////////////
        binding.rvLsv.addOnScrollListener(new RecyclerView.OnScrollListener() {
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
                            ORDER_TYPE = CANCELED_ORDER;
                            myOrder(true, CANCELED_ORDER);
                        }
                    }
                }
            }
        });
        //////////////////////////////////////////////////////////////////////////////////////////////

    }

    @Override
    public void OnSelectOption(int type, String trackStatus, String qty, String imageLink,
                               MyOrderResponseModel.OrdersBean.DataBean.CustomerAddressBean customer_address,
                               String venue, String offer, String product, String orderId, String order_date,
                               String Venue_address_1, String venue_images, String cancel_date,
                               Float rating, String review, String productName, String productDetailId,
                               String venue_name, String venue_contact, String cancelStatus, String deliveryType, String collectTime,
                               List<MyOrderResponseModel.OrdersBean.DataBean.TrackingDataBean> trackingData, int is_hospitality,
                               String estDeliveryDays, String expected_shipping_date) {
        switch (type) {
            case REVIEW_TO_PRODUCT:

                // trackStatus  is adapter position  here
                new DialogWriteReviews(this, orderId, product, rating, review, productName, imageLink,
                        true, productDetailId, Integer.parseInt(trackStatus)).show();
                break;
            case REVIEW_TO_OVERALL:   // trackStatus  is adapter position  here

                new DialogAllReviews(this, String.valueOf(myOrderList.get(Integer.parseInt(trackStatus)).getId()), new SuccessActionListner() {
                    @Override
                    public void onSuccessActionListner() {
                        myOrderList.get(Integer.parseInt(trackStatus)).setIs_rated(1);
                    }
                }).show();
                break;
            case TRACK_PACKAGE:
                Intent track = new Intent(instance, TrackPackageActivity.class);
                track.putExtra(TRACK_STATUS, trackStatus);
                track.putExtra(QUANTITY, qty);
                track.putExtra(IMAGE_LINK, imageLink);
                track.putExtra(ADDRESS_GET, customer_address);
                track.putExtra(VENUE_ID, venue);
                track.putExtra(VENUE_ADDRESS, Venue_address_1);
                track.putExtra(OFFER_ID, offer);
                track.putExtra(PRODUCT_ID, product);
                track.putExtra(ORDER_DATE, order_date);
                track.putExtra(COLLECT_TIME, collectTime);
                track.putExtra(ESTIMATED_DELIVERY, expected_shipping_date);  // estDeliveryDays was being used earlier
                track.putExtra(CANCEL_DATE, cancel_date);  ///its  deliver date too if complete is seleced
                track.putExtra(ORDER_ID, orderId);
                track.putExtra(ORDER_DETAIL_ID, productDetailId);  // this is orderDetailId
                track.putExtra(CANCEL_STATUS, cancelStatus);    ///  refund status in case of refund
                track.putExtra(DELIVERY_TYPE, deliveryType);
                track.putExtra(VENUE_TYPE, is_hospitality);
                track.putParcelableArrayListExtra(TRACK_DATA, (ArrayList<? extends Parcelable>) trackingData);
                track.putExtra(FROM_WHERE, ORDER_TYPE);
                startActivity(track);
                break;
            case MESSAGE:
                Intent chat = new Intent(instance, ChatDetailActivity.class);
                chat.putExtra(VENUE_ID, venue);
                chat.putExtra(VENUE, venue_name);
                chat.putExtra(ORDER_ID, orderId);
                chat.putExtra(CONTACT_NO, venue_contact);
                chat.putExtra(IMAGE_LINK, ApiRequestUrl.BASE_URL_VENUE + venue_images);
                startActivity(chat);
                break;

            case SHOW_REVIEW:
                Intent intent = new Intent(instance, ReviewListActivity.class);
                intent.putExtra(PRODUCT_ID, product);
                startActivity(intent);

                break;
            case REPLACE_REFUND:
                /*DialogUtils.dialogReasonForCancellation(instance, returnReasonsList, new DrawerListner() {
                    @Override
                    public void onDrawerSelect(int position, int clickId) {
                        List<Integer> integerList = new ArrayList<>();
                        HashMap<String, String> hashMap = new HashMap();
                        integerList.add(Integer.valueOf(product));
                        hashMap.put(String.valueOf(integerList.get(0)), qty);
                        returnOrder(new ReturnRequestModel(orderId, order_date, order_date, integerList, hashMap));
                    }
                });*/
                List<Integer> integerList = new ArrayList<>();
                HashMap<String, String> hashMap = new HashMap();
                integerList.add(Integer.valueOf(product));
                hashMap.put(String.valueOf(integerList.get(0)), qty);
                returnDialog(new ReturnRequestModel(orderId, order_date, order_date, integerList, hashMap));
                break;
        }
    }

    public void returnDialog(ReturnRequestModel returnRequestModel) {
        Dialog dialog;
        DialogReasonForCancellationBinding dialogBinding;
        View dialogView = LayoutInflater.from((Activity) this).inflate(R.layout.dialog_reason_for_cancellation, null);
        dialogBinding = DataBindingUtil.bind(dialogView);
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(dialogBinding.getRoot());
        dialog.setCancelable(false);
        //////////////////////////////////////////////////////////////////////////////
        final int[] reasonId = new int[1];
        RecyclerView recyclerView = dialog.findViewById(R.id.rv_reason_list);
        dialog.findViewById(R.id.iv_close).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.findViewById(R.id.tv_submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                returnOrder(returnRequestModel);
            }
        });

        //  this.selectionListner = (MyOrderActivity) mContext;
        CancellationReasonAdapter loyaltyStampVoucherAdapter = new CancellationReasonAdapter(instance, returnReasonsList, new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {
                reasonId[0] = position;
            }
        }, 0);
        LinearLayoutManager layoutManager = new LinearLayoutManager(instance);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(loyaltyStampVoucherAdapter);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.iv_app_logo:
                finishAffinity();
                Intent stamp = new Intent(instance, MainActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.finishAffinity));
                startActivity(stamp);
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

    private void myOrder(final boolean isMore, final String type) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();

            final AddressRequestModel requestModel = new AddressRequestModel(type);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<MyOrderResponseModel> call = apiInterface.getMyOrder(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel, String.valueOf(pageNumber++));
            binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<MyOrderResponseModel>() {
                @Override
                public void onResponse(Call<MyOrderResponseModel> call, Response<MyOrderResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            MyOrderResponseModel responseModel = response.body();
                            if (responseModel != null) {
                                if (responseModel.isStatus()) {
                                    isLoading = false;
                                    totalPage = responseModel.getOrders().getLast_page();

                                    if (responseModel.getReturn_reasons() != null && responseModel.getReturn_reasons().size() > 0) {
                                        returnReasonsList.clear();
                                        returnReasonsList.addAll(responseModel.getReturn_reasons());
                                    } else {
                                        returnReasonsList.clear();
                                    }


                                    if (responseModel.getOrders().getData() != null && responseModel.getOrders().getData().size() > 0) {
                                        binding.tvNoDataFound.setVisibility(View.GONE);
                                        myOrderList.addAll(responseModel.getOrders().getData());
                                        if (isMore) {
                                            if (type.equals(NEW_ORDER)) {
                                                if (myOrderNewAdapter != null)
                                                    myOrderNewAdapter.notifyDataSetChanged();
                                            } else if (type.equals(CANCELED_ORDER)) {
                                                if (myOrderCancelledAdapter != null)
                                                    myOrderCancelledAdapter.notifyDataSetChanged();
                                            } else {
                                                if (myOrderCompletedAdapter != null)
                                                    myOrderCompletedAdapter.notifyDataSetChanged();
                                            }

                                        } else {
                                            if (type.equals(NEW_ORDER)) {
                                                setNewOrderAdapter();
                                            } else if (type.equals(CANCELED_ORDER)) {
                                                setCancelledOrderAdapter();
                                            } else
                                                setCompletedOrderAdapter();
                                        }
                                    } else {

                                        if (!isMore) {
                                            myOrderList.clear();
                                            binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                            if (type.equals(NEW_ORDER)) {
                                                setNewOrderAdapter();
                                            } else if (type.equals(CANCELED_ORDER)) {
                                                setCancelledOrderAdapter();
                                            } else
                                                setCompletedOrderAdapter();
                                        }

                                    }
                                } else {
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());
                                }
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
                        HelperClass.log("EXCC", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<MyOrderResponseModel> call, Throwable t) {
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
    public void onRefresh() {

    }

    public void setTab(int tabPosition) {
        if (binding.tabLayout != null) {
            binding.tabLayout.getTabAt(tabPosition).select();
        } else {
            // selectedTab = tabPosition;
        }
    }

    private void setTabs() {
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.new_order)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.completed)));
        binding.tabLayout.addTab(binding.tabLayout.newTab().setText(getString(R.string.cancelled)));

        binding.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //  selectedTab = tab.getPosition();
                switch (binding.tabLayout.getSelectedTabPosition()) {
                    case 0:
                        myOrderList.clear();
                        ORDER_TYPE = NEW_ORDER;
                        pageNumber = 1;
                        myOrder(false, NEW_ORDER);
                        break;
                    case 1:
                        myOrderList.clear();
                        ORDER_TYPE = COMPLETED_ORDER;
                        pageNumber = 1;
                        myOrder(false, COMPLETED_ORDER);
                        break;
                    case 2:
                        myOrderList.clear();
                        ORDER_TYPE = CANCELED_ORDER;
                        pageNumber = 1;
                        myOrder(false, CANCELED_ORDER);
                        break;

                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                // lastSelectedPosition = -1;

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

    }

    @Override
    public void onCancelOrder(int order_id, int order_details_id) {
        DialogUtils.showAlertDialog(instance, getString(R.string.cancel_message), new OnDialogClickListener() {
            @Override
            public void onPositiveClick() {
                List<Integer> integers = new ArrayList<>();
                integers.add(order_id);
                CancelOrderRequestModel requestModel = new CancelOrderRequestModel(order_details_id, CANCEL_PRODUCT_MESSAGE, integers);
                //  cancelOrder(requestModel);
            }

            @Override
            public void onNegativeClick() {

            }
        });
    }

    private void cancelOrder(CancelOrderRequestModel requestModel) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<MyOrderResponseModel> call = apiInterface.cancelOrder(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<MyOrderResponseModel>() {
                @Override
                public void onResponse(Call<MyOrderResponseModel> call, Response<MyOrderResponseModel> response) {
                    try {
                        //      binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            MyOrderResponseModel responseModel = response.body();
                            if (responseModel != null) {
                                if (responseModel.isStatus()) {
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());
                                    pageNumber = 1;
                                    myOrder(false, NEW_ORDER);
                                } else {
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());
                                    pageNumber = 1;
                                    myOrder(false, NEW_ORDER);
                                }
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
                        HelperClass.log("", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<MyOrderResponseModel> call, Throwable t) {
                    //   binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            //  binding.swipeRefresh.setRefreshing(false);
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
                                if (totalCartResponseModel.getTotal_carts() > 0) {
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
                                } else binding.tvCartCount.setVisibility(View.INVISIBLE);

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

    private void returnOrder(ReturnRequestModel requestModel) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<MyOrderResponseModel> call = apiInterface.returnOrder(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<MyOrderResponseModel>() {
                @Override
                public void onResponse(Call<MyOrderResponseModel> call, Response<MyOrderResponseModel> response) {
                    try {
                        //      binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            MyOrderResponseModel responseModel = response.body();
                            if (responseModel != null) {
                                if (responseModel.isStatus()) {
                                    ORDER_TYPE = COMPLETED_ORDER;
                                    pageNumber = 1;
                                    myOrder(false, COMPLETED_ORDER);
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());

                                } else {
                                    showSnackBar(binding.getRoot(), responseModel.getMessage());

                                }
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
                        HelperClass.log("", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<MyOrderResponseModel> call, Throwable t) {
                    //   binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            //  binding.swipeRefresh.setRefreshing(false);
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
                        //  startActivity(myOrder);
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSuccessActionListner() {
        getTotalCartCount();
    }
}
