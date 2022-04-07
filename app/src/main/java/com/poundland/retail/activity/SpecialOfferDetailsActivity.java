package com.poundland.retail.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.adapter.SliderAdapterHome;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivitySpecialOfferDetailsBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.AddToCartRequestModel;
import com.poundland.retail.model.requestModel.AddressRequestModel;
import com.poundland.retail.model.requestModel.SingleProductDetailRequestModel;
import com.poundland.retail.model.responseModel.AddToCartResponseModel;
import com.poundland.retail.model.responseModel.NotifyMeResponseModel;
import com.poundland.retail.model.responseModel.SpecialOfferDetailsResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.log;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.printDifference;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CART_RETAIL;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;

public class SpecialOfferDetailsActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {
    private ActivitySpecialOfferDetailsBinding binding;
    private PrefManager prefManager;
    private ArrayList<String> imageList;
    private String venueId;
    private String productId;
    private String specialOfferId;
    private String offerId;
    private SpecialOfferDetailsActivity instance = null;
    private String format = "HH:mm:ss";
    private SimpleDateFormat sdf = new SimpleDateFormat(format);
    private String currentTime;
    String quantity = "1";
    String couponCode;
    private SpecialOfferDetailsResponseModel responseModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_special_offer_details);
        init();
    }

    private void init() {
        instance = this;
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());

        specialOfferId = getIntent().getStringExtra(SPECIAL_OFFER_ID);
        productId = getIntent().getStringExtra(PRODUCT_ID);
        offerId = getIntent().getStringExtra(OFFER_ID);
        imageList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        setListeners();
        getSpecialOfferDetails();
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.llBuy.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;


            case R.id.ll_buy:
                if (binding.tvBuyNow.getText().toString().trim().equals(getString(R.string.notify_me))){
                    notifyMe();
                }else {

                    if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(venueId) || prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                        prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                        addToCart(false);
                    } else {
                        DialogUtils.showAlertDialog(instance, getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                                prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                                addToCart(false);
                            }

                            @Override
                            public void onNegativeClick() {

                            }
                        });
                    }

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

        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    private void setData(SpecialOfferDetailsResponseModel data) {
        venueId = data.getProductOffers().getVenue_id();
        binding.tvStoreName.setText(data.getProductOffers().getVenue_name());
        binding.tvProductName.setText(data.getProductOffers().getProduct_name());
        if (data.getProductOffers().getIs_on_sale() == 0) {
            binding.tvDealStatus.setText(getString(R.string.until_deal_ends));
            binding.llBuy.setAlpha(1f);
            binding.llBuy.setOnClickListener(this);
        } else {
            binding.tvDealStatus.setText(getString(R.string.until_deal_start));
            binding.llBuy.setAlpha(0.5f);
            binding.llBuy.setOnClickListener(null);
        }
        if (data.getProductOffers().getDescription()!=null){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                binding.tvDescription.setText(Html.fromHtml(data.getProductOffers().getDescription(), Html.FROM_HTML_MODE_COMPACT));
            } else
                binding.tvDescription.setText(Html.fromHtml(data.getProductOffers().getDescription()));
        }

        if (data.getProductOffers().getAvl_quantity() > 0) {
            /*if (!offerId.equals(""))
                binding.tvPrice.setPaintFlags(binding.tvPrice.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);*/
            binding.tvPrice.setText(getString(R.string.pound) + data.getProductOffers().getSelling_price());
        } else {
            binding.tvPrice.setVisibility(View.INVISIBLE);
            binding.tvPriceLevel.setText(getString(R.string.out_of_stock));
            binding.tvBuyNow.setText(getString(R.string.notify_me));
            binding.tvBuyNow.setAlpha(1f);
            binding.llBuy.setOnClickListener(this);
        }
        binding.tvDiscount.setText(getString(R.string.pound) + String.format("%.2f", (Double.parseDouble(data.getProductOffers().getSelling_price()) - Double.parseDouble(data.getProductOffers().getNew_price()))));
        if (data.getProductOffers().getLoyalty_point_for_offer() > 0.0) {
            binding.tvEarnPoints.setText(getString(R.string.earn) + " " + data.getProductOffers().getLoyalty_point_for_offer() + " Points");
        } else binding.tvEarnPoints.setVisibility(View.INVISIBLE);
        if (data.getProductOffers() != null) {
            if (data.getProductOffers().getOffer_title() != null) {
                binding.tvOfferPrice.setText(getString(R.string.pound) + data.getProductOffers().getNew_price());
            }

            binding.rbRating.setRating(Float.parseFloat(data.getProductOffers().getStars()));
            if (Float.parseFloat(data.getProductOffers().getStars()) < 0.5) {
                binding.tvRatingCount.setText(getString(R.string.no_rating));
            } else
                binding.tvRatingCount.setText("(" + data.getProductOffers().getStars() + " Ratings)");
            try {
                // binding.llDealExpire.setVisibility(View.GONE);
                String endTime = data.getProductOffers().getOffer_time_end();
                Date dateObj1 = sdf.parse(currentTime);
                Date dateObj2 = sdf.parse(endTime);
                new CountDownTimer(dateObj2.getTime() - dateObj1.getTime(), 1000) {
                    public void onTick(long millisUntilFinished) {
                        long millis = millisUntilFinished;
                        String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                        binding.tvExpiry.setText(hms);
                    }

                    public void onFinish() {
                    }
                }.start();
            } catch (Exception exc) {
                exc.printStackTrace();
            }
            if (data.getProductOffers().getProduct_images() != null) {
                imageList.clear();
                imageList.add(data.getProductOffers().getProduct_images());
                setViewPager();
            }
        }
        /////////////////////////////////////////////////////// /// 2020-02-11
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-dd-mm", Locale.getDefault());
            Date endDate = sdf.parse("2020-02-29"/*data.getProductOffers().getEnd_date()*/);

            String date = new SimpleDateFormat("yyyy-dd-mm", Locale.getDefault()).format(new Date());
            Date strDate = sdf.parse("2020-02-27"/*date*/);

            try {
                log("diff", "" + printDifference(strDate, endDate));
            } catch (Exception e) {
                e.printStackTrace();
            }
            ///////////////////////////////////


        } catch (ParseException e) {
            e.printStackTrace();
            showSnackBar(binding.viewPager, "exce");
        }

        ///////////////////////////////////////////////////////
    }

    private void setViewPager() {
        binding.viewPager.setAdapter(new SliderAdapterHome(this, imageList, true, -1, false, new DrawerListner() {
            @Override
            public void onDrawerSelect(int position, int clickId) {

            }
        }));
        binding.viewPager.setPadding(0, 0, 0, 0);
        binding.viewPager.setClipToPadding(false);
        binding.viewPager.setCurrentItem(0, true);
        binding.viewPager.setPageMargin(10);
        binding.indicator.setupWithViewPager(binding.viewPager, true);

    }

    private void getSpecialOfferDetails() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");

            SingleProductDetailRequestModel requestModel = new SingleProductDetailRequestModel(specialOfferId, productId, offerId, latitude, longitude);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<SpecialOfferDetailsResponseModel> call = apiInterface.getSpecialOfferDetails(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<SpecialOfferDetailsResponseModel>() {
                @Override
                public void onResponse(Call<SpecialOfferDetailsResponseModel> call, Response<SpecialOfferDetailsResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            responseModel = response.body();
                            if (responseModel.isStatus()) {

                                setData(responseModel);

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
                    }
                }

                @Override
                public void onFailure(Call<SpecialOfferDetailsResponseModel> call, Throwable t) {
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
    public void onRefresh() {
        init();
        binding.swipeRefresh.setRefreshing(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        getTotalCartCount();
    }

    private void addToCart(boolean isCombo) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            AddToCartRequestModel requestModel = null;
           /* if (isCombo) {
                requestModel = new AddToCartRequestModel
                        (String.valueOf(comboMerchantId), responseModel.getProductOffers().getVenue_id(), String.valueOf(comboProductId), String.valueOf(comboModifierId),
                                "", String.valueOf(quantity), String.valueOf(comboCostPerProduct), couponCode, "", specialOfferId);
            } else {*/
            requestModel = new AddToCartRequestModel
                    (String.valueOf(responseModel.getProductOffers().getMerchant_id()),
                            responseModel.getProductOffers().getVenue_id(),
                            String.valueOf(responseModel.getProductOffers().getProduct_id()),
                            String.valueOf(responseModel.getProductOffers().getModifier_id()),
                            offerId, quantity, responseModel.getProductOffers().getNew_price(),
                            couponCode, "", String.valueOf(responseModel.getProductOffers().getSpecial_offer_id()),prefManager.getPreference(LATITUDE, ""),prefManager.getPreference(LONGITUDE, ""));
            // }

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.addToCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);

            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {

                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            prefManager.savePreference(CART_ENTRY_TYPE,CART_RETAIL);
                            AddToCartResponseModel cartResponseModel = response.body();
                            if (cartResponseModel.isStatus()) {
                                if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_HOSPITALITY) {
                                    Intent intentH = new Intent(instance, MyCartHospitalityActivity.class);
                                    startActivity(intentH);
                                } else {
                                    Intent intentR = new Intent(instance, MyCartActivity.class);
                                    startActivity(intentR);
                                }
                                getTotalCartCount();
                            } else {
                                showSnackBar(binding.getRoot(), cartResponseModel.getMessage());
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
                public void onFailure(Call<AddToCartResponseModel> call, Throwable t) {

                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {

            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void notifyMe() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            final AddressRequestModel requestModel = new AddressRequestModel(prefManager.getPreference(EMAIL, ""),
                    productId, venueId, ""+responseModel.getProductOffers().getMerchant_id(),
                    String.valueOf(responseModel.getProductOffers().getModifier_id()));

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<NotifyMeResponseModel> call = apiInterface.notifyMe(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<NotifyMeResponseModel>() {
                @Override
                public void onResponse(Call<NotifyMeResponseModel> call, Response<NotifyMeResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            NotifyMeResponseModel responseModel = response.body();

                            if (responseModel.isStatus()) {
                                showSnackBar(binding.getRoot(), responseModel.getMessage());
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
                        HelperClass.log("", e.getMessage());
                    }
                }

                @Override
                public void onFailure(Call<NotifyMeResponseModel> call, Throwable t) {
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
                    DialogUtils.offerNotificationDialog(this, title, message, image, new Gson().fromJson(data, NotificationModel.class), (position, clickId) -> {
                        init();
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