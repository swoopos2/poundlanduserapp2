package com.poundland.retail.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.adapter.SliderAdapterHome;
import com.poundland.retail.adapter.UpcomingVenueProductAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityUpcomingVenueDetailsBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.ClaimStoreRequestModel;
import com.poundland.retail.model.requestModel.NotifyUpcomingVenueProductReq;
import com.poundland.retail.model.requestModel.UpcomingVenueDetailReqModel;
import com.poundland.retail.model.responseModel.ClaimStoreresponseModel;
import com.poundland.retail.model.responseModel.NotifyUpcomingVenueProductResp;
import com.poundland.retail.model.responseModel.UpcomingVenueDetailResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.hideKeyboard;
import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.isValidEmail;
import static com.poundland.retail.appUtils.HelperClass.isValidName;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.appUtils.HelperClass.toast;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.IMAGE_LINK;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.ORDER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID;

public class UpcomingVenueDetailActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, DrawerListner {
    private ActivityUpcomingVenueDetailsBinding binding;
    private PrefManager prefManager;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    private String userId;
    private UpcomingVenueDetailResponseModel venueDetailResponseModel;
    private List<UpcomingVenueDetailResponseModel.ProductListBean.DataBean> productList;
    private String venueId = "";
    private String venue_name;
    private UpcomingVenueDetailActivity instance = null;
    private ArrayList<String> imageList;
    private Dialog dialog;
    private String fromWhere;
    private boolean isUpcomingStore = false;
    private String lat, longi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_upcoming_venue_details);
        init();
    }

    private void init() {
        instance = this;

        binding.ivFilter.setVisibility(View.INVISIBLE);
        binding.ivFavo.setVisibility(View.INVISIBLE);
        binding.llMain.setVisibility(View.GONE);
        binding.tvMinOrder.setVisibility(View.GONE);
        binding.rbRating.setRating(5f);

        venueId = getIntent().getStringExtra(STORE_ID);
        lat = getIntent().getStringExtra(LATITUDE);
        longi = getIntent().getStringExtra(LONGITUDE);
        fromWhere = getIntent().getStringExtra(FROM_WHERE);
        isLoading = true;
        pageNumber = 1;
        prefManager = PrefManager.getInstance(this);
        productList = new ArrayList<>();
        imageList = new ArrayList<>();
        // if (fromWhere.equals(UPCOMING_VENUE)) {
        isUpcomingStore = true;
        binding.tvCollectionDelivery.setVisibility(View.VISIBLE);
        binding.tvClaim.setVisibility(View.VISIBLE);
        //}
        getUpcomingVenueDetails();
        setListeners();
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        // binding.swipeRefresh.setOnRefreshListener(this);
        binding.llMesage.setOnClickListener(this);
        binding.llPhone.setOnClickListener(this);
        binding.llDirection.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.tvClaim.setOnClickListener(this);
    }

    private void setViewPager() {
        binding.viewPager.setAdapter(new SliderAdapterHome(this, imageList, false, -1, true, new DrawerListner() {
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

    private void setUpcomingVenueProductAdapter() {
        UpcomingVenueProductAdapter categoryListAdapter = new UpcomingVenueProductAdapter(this, productList,
                this, String.valueOf(venueDetailResponseModel.getVenueDetails().getName()),
                lat, longi, venueDetailResponseModel.getVenueDetails().getCity_name());
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvCategoryList.setLayoutManager(layoutManager);
        binding.rvCategoryList.setAdapter(categoryListAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_direction:

                Uri gmmIntentUri = Uri.parse("geo:" + lat + "," + longi);
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
                break;
            case R.id.ll_phone:
                Intent inCall = new Intent(Intent.ACTION_DIAL);
                inCall.setData(Uri.parse("tel:011-61291501"));
                startActivity(inCall);
                break;
            case R.id.ll_mesage:
                Intent intent = new Intent(this, ChatDetailActivity.class);
                intent.putExtra(ORDER_ID, "");
                intent.putExtra(VENUE_ID, venueId);
                // intent.putExtra(CONTACT_NO, venueDetailResponseModel.getVenue_details().getPhone_number());
                intent.putExtra(VENUE, venue_name);
                intent.putExtra(IMAGE_LINK, imageList.size() > 0 ? ApiRequestUrl.BASE_URL_VENUE + imageList.get(0) : "");
                startActivity(intent);
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
            case R.id.tv_claim:
                dialogClaimStore();
                break;
        }
    }

    private void getUpcomingVenueDetails() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            UpcomingVenueDetailReqModel requestModel = new UpcomingVenueDetailReqModel(venueId, venueId, prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""));
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<UpcomingVenueDetailResponseModel> call = apiInterface.getUpcomingVenueDetail(
                    prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<UpcomingVenueDetailResponseModel>() {
                @Override
                public void onResponse(Call<UpcomingVenueDetailResponseModel> call, Response<UpcomingVenueDetailResponseModel> response) {

                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            venueDetailResponseModel = response.body();
                            if (venueDetailResponseModel.isStatus()) {

                                if (venueDetailResponseModel.getProductList().getData() != null && venueDetailResponseModel.getProductList().getData().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);
                                    productList.addAll(venueDetailResponseModel.getProductList().getData());
                                    setUpcomingVenueProductAdapter();
                                } else {
                                    // binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    productList.clear();
                                    setUpcomingVenueProductAdapter();
                                }
                                setData(venueDetailResponseModel.getVenueDetails());

                            } else {
                                showSnackBar(binding.getRoot(), venueDetailResponseModel.getMessage());
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
                public void onFailure(Call<UpcomingVenueDetailResponseModel> call, Throwable t) {
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

    private void setData(UpcomingVenueDetailResponseModel.VenueDetailsBean data) {
        venue_name = data.getName();
        binding.tvVenueName.setText(venue_name);
        binding.tvAddress.setText(data.getAddress_1());
        //if (data.getVenue_details().isWishlisted())
        //  binding.ivFavo.setColorFilter(ContextCompat.getColor(instance, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.ivFavo.setVisibility(View.INVISIBLE);

        if (data.getIsClose().equals("0")) {
            binding.tvTimingStatus.setText(getString(R.string.open_caps));
            binding.tvTimingStatus.setBackgroundResource(R.drawable.green_filled_rect);
            binding.tvOpeningTiming.setText("Opening-" + data.getOpening_time());
            binding.tvClosingTiming.setText(" Closing-" + data.getClosing_time());
        } else {
            binding.tvTimingStatus.setText(getString(R.string.close_caps));
            binding.tvTimingStatus.setBackgroundResource(R.drawable.app_colored_filled_rect_100);
            binding.tvOpeningTiming.setText("Next Open-" + data.getOpening_time());
            binding.tvClosingTiming.setText(" Closing-" + data.getClosing_time());
        }

        binding.tvMinOrder.setText(getString(R.string.min_order) + getString(R.string.pound)+" 10");
        binding.tvDistanceInMiles.setText(data.getDistance() == null ? getString(R.string.miles) : data.getDistance() + getString(R.string.miles));
        //binding.rbRating.setRating(data.getVenue_details().getStars().getVenue_rating_value()));
//        binding.tvRatingCount.setText("(" + data.getVenue_details().getStars().getCount() + " Ratings)");
        binding.tvRatingCount.setVisibility(View.INVISIBLE);

        if (data.getImage() != null) {
            imageList.clear();
            imageList.addAll(Collections.singleton(data.getImage()));
            setViewPager();
        }
    }

    @Override
    public void onRefresh() {
       /* notificationBean.clear();
        pageNumber = 1;
        getNotificationList(false);*/
    }

    @Override
    public void onDrawerSelect(int position, int clickType) {


        dialogNotifyStoreProduct(productList.get(position).getId(),
                String.valueOf(productList.get(position).getVenue_id()));
        /*notifyMe( productList.get(position).getId(),
                String.valueOf(productList.get(position).getVenue_id()));*/


       /* Intent in = new Intent(this, AllProductByCategoryListActivity.class);
        in.putExtra(CATEGORY_ID, String.valueOf(productList.get(position).getId()));
        in.putExtra(STORE_ID, venueDetailResponseModel.getVenueDetails().getId());
        in.putExtra(FROM_WHERE, FROM_CATEGORY_TAP);
        in.putExtra(IS_UPCOMING_VENUE, isUpcomingStore);

        // startActivity(in);*/
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

    public void dialogClaimStore() {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = new Dialog(instance, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_claim_store);

        final EditText et_name = dialog.findViewById(R.id.et_name);
        final EditText et_email = dialog.findViewById(R.id.et_email);
        final EditText et_mobile = dialog.findViewById(R.id.et_contact);
        final EditText et_message = dialog.findViewById(R.id.et_message);
        et_name.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        // imm.showSoftInput(etLoyalty, InputMethodManager.SHOW_FORCED);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialog.show();

        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!TextUtils.isEmpty(et_name.getText().toString().trim()) && isValidName(et_name.getText().toString().trim())) {
                    if (isValidEmail(et_email.getText().toString().trim())) {
                        if (!TextUtils.isEmpty(et_mobile.getText().toString()) && et_mobile.getText().length() > 9) {
                            if (!TextUtils.isEmpty(et_message.getText().toString().trim())) {
                                hideKeyboard(instance);
                                claimStoreApi(et_name.getText().toString().trim(), et_email.getText().toString().trim(),
                                        et_mobile.getText().toString().trim(), et_message.getText().toString().trim());

                            } else {
                                toast(instance, "Please Enter Message");
                            }

                        } else {
                            toast(instance, getString(R.string.please_enter_a_valid_mobile_number));
                        }

                    } else {
                        toast(instance, getString(R.string.please_enter_a_valid_email));
                    }
                } else {
                    toast(instance, getString(R.string.please_enter_valid_name));
                }

            }
        });

        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(instance);
                dialog.dismiss();
            }
        });
    }

    private void claimStoreApi(String name, String email, String mob, String message) {
        if (isInternetOn(instance)) {
            ClaimStoreRequestModel requestModel = new ClaimStoreRequestModel("" + venueDetailResponseModel.getVenueDetails().getId(), name, mob, email, message, "::1");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<ClaimStoreresponseModel> call = apiInterface.claimStore(prefManager.getPreference(AUTH_TOKEN, ""),
                    prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<ClaimStoreresponseModel>() {
                @Override
                public void onResponse(Call<ClaimStoreresponseModel> call, Response<ClaimStoreresponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            ClaimStoreresponseModel responseModel = response.body();
                            if (responseModel.isStatus()) {


                                DialogUtils.showAlertDialogWithSingleButton(instance, responseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {
                                        dialog.dismiss();
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
                public void onFailure(Call<ClaimStoreresponseModel> call, Throwable t) {
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
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
                                if (totalCartResponseModel.getTotal_carts() != 0)
                                    binding.tvCartCount.setVisibility(View.VISIBLE);
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
        return R.layout.activity_upcoming_venue_details;
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

    private void notifyMe(int product_id, String venue_id) {
        if (isInternetOn(instance)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(instance);
            if (dialog != null)
                dialog.show();

            final NotifyUpcomingVenueProductReq requestModel = new NotifyUpcomingVenueProductReq(
                    prefManager.getPreference(EMAIL, ""), String.valueOf(product_id), venue_id,
                    Long.parseLong(String.valueOf(prefManager.getPreference(LOGIN_ID, 0))), lat, longi,
                    venueDetailResponseModel.getVenueDetails().getCity_name(), "::1");

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<NotifyUpcomingVenueProductResp> call = apiInterface.notifyUpcomingVenueProduct(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<NotifyUpcomingVenueProductResp>() {
                @Override
                public void onResponse(Call<NotifyUpcomingVenueProductResp> call, Response<NotifyUpcomingVenueProductResp> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            NotifyUpcomingVenueProductResp responseModel = response.body();

                            if (responseModel.isStatus()) {
                                // showSnackBar(holder.binding.getRoot(), responseModel.getMessage());
                                DialogUtils.showAlertDialogWithSingleButton(instance, responseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
                            } else
                                showSnackBar(binding.ivBack, responseModel.getMessage());

                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(instance, httpCode == 401 ? instance.getString(R.string.season_expired) : getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {

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
                public void onFailure(Call<NotifyUpcomingVenueProductResp> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.ivBack, getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.ivBack, getString(R.string.no_internet_available_msg));
        }
    }

    public void dialogNotifyStoreProduct(int product_id, String venue_id) {
        if (dialog != null) {
            dialog.dismiss();
        }
        dialog = new Dialog(instance, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_notify_store);

        final EditText et_email = dialog.findViewById(R.id.et_email);
        et_email.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        // imm.showSoftInput(etLoyalty, InputMethodManager.SHOW_FORCED);
        WindowManager.LayoutParams layoutParams = dialog.getWindow().getAttributes();
        layoutParams.dimAmount = 0.7f;
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
        layoutParams.width = ViewGroup.LayoutParams.MATCH_PARENT;
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialog.getWindow().setAttributes(layoutParams);
        dialog.getWindow().setWindowAnimations(R.style.AnimationCenterPopUp);
        dialog.show();

        dialog.findViewById(R.id.btnOkay).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (isValidEmail(et_email.getText().toString().trim())) {
                    dialog.dismiss();
                    hideKeyboard(instance);
                    notifyMe(product_id, venue_id);
                } else {
                    toast(instance, getString(R.string.please_enter_a_valid_email));
                }
            }
        });

        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hideKeyboard(instance);
                dialog.dismiss();
            }
        });
    }
}

