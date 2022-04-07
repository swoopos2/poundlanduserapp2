package com.poundland.retail.activity;

import android.content.Intent;
import android.graphics.Paint;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activityHospitality.MyCartHospitalityActivity;
import com.poundland.retail.activityHospitality.MyCartReservationActivity;
import com.poundland.retail.adapter.ComboOfferAdapter;
import com.poundland.retail.adapter.ModifierAdapter;
import com.poundland.retail.adapter.ReviewAdapter;
import com.poundland.retail.adapter.SimilarProductAdapter;
import com.poundland.retail.adapter.SliderAdapterHome;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityProductDetailsBinding;
import com.poundland.retail.dialog.DialogSwipeToBuy;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.ComboOfferListner;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.ModifierSelectionListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.model.requestModel.AddToCartComboRequestModel;
import com.poundland.retail.model.requestModel.AddToCartRequestModel;
import com.poundland.retail.model.requestModel.AddressRequestModel;
import com.poundland.retail.model.requestModel.LikeDislikeProductRequestModel;
import com.poundland.retail.model.requestModel.ProductDetailByModifierRequestModel;
import com.poundland.retail.model.requestModel.SingleProductDetailRequestModel;
import com.poundland.retail.model.responseModel.AddToCartResponseModel;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.model.responseModel.LikeDislikeProductResponseModel;
import com.poundland.retail.model.responseModel.NotifyMeResponseModel;
import com.poundland.retail.model.responseModel.ProductDetailByModifierResponseModel;
import com.poundland.retail.model.responseModel.SingleProductDetailResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

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

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.log;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.interfaces.Constants.ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.BARCODE_ID;
import static com.poundland.retail.interfaces.Constants.CART_ENTRY_TYPE;
import static com.poundland.retail.interfaces.Constants.CART_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.CART_RESERVATION;
import static com.poundland.retail.interfaces.Constants.CART_RETAIL;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.HOME_RETAIL;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_ADD_ADDRESS_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_CHANGE_ADDRESS_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PAYMENT_DETAIL_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_CREATE_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_VERIFY_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.PIN_CREATE_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;

public class ProductDetailActivity extends BaseActivity implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener, ModifierSelectionListner, DrawerListner, ComboOfferListner {
    private static CountDownTimer mCountDownTimer = null;
    private String PATTERN_START_WITH_DATE_NO_YEAR = "dd MMM";
    private ActivityProductDetailsBinding binding;
    private PrefManager prefManager;
    private boolean isLoading;
    private int pageNumber;
    private int totalPage;
    //  private List<SingleVenueDetailResponseModel.VenueDetailsBean.CategoriesBean> categoryList;
    private ArrayList<String> imageList;
    private List<SingleProductDetailResponseModel.ProductRatingBean.ReviewsBean> reviewList;
    private List<SingleProductDetailResponseModel.SimilarProductsBean> similarProList;
    private List<List<SingleProductDetailResponseModel.ComboOfferBean>> comboOfferList;
    private ArrayList<SingleProductDetailResponseModel.ProductsBean.ModifierListBean> modifierList;
    private String modifierName = "";
    private String currentModifier = "";
    private String couponCode = "";
    private int modifierId, merchantId;
    private int quantity = 1;
    private String costPerProduct, venueId, productId, offerId = "";
    private ProductDetailActivity instance = null;
    private int secondAdapterPos = 0;
    private String format = "HH:mm:ss";
    private SimpleDateFormat sdf = new SimpleDateFormat(format);
    private SimpleDateFormat sdfSameDayShipping = new SimpleDateFormat(Constants.HHmm);
    private String currentTime;
    private SingleProductDetailResponseModel productDetailResponseModel;
    private HashMap<String, String> requestHashMap = new HashMap<>();
    private String barcodeId = "";
    private boolean setAlpha;
    private String formatSameDayShipping = "HH:mm";

    private List<String> deliveryTypelist;
    private String sameDayDeliveryTime = "";
    private String sameDayDeliveryText = "";

    ///// decleare Call back variable here ( public )
    private DialogSwipeToBuy dialogSwipeToBuy = null;

    ///////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_product_details);
    }


    @Override
    protected void onResume() {
        super.onResume();
        init();
        getTotalCartCount();
        binding.tvSameDayDelivery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }

        });
    }

    private void init() {
        instance = this;
        onNewIntent(getIntent());
        setAlpha = false;
        binding.tvAddToCart.setAlpha(1f);
        //  binding.tvAddToWishlist.setAlpha(1f);
        binding.tvBuyNow.setAlpha(1f);
        modifierId = 0;
        // binding.tvBuyAndGetFree.setVisibility(View.GONE);
        binding.rvBuyAndGetFree.setVisibility(View.GONE);
        binding.rlSameDayDelivery.setVisibility(View.GONE);
        binding.llBuyBulk.setVisibility(View.GONE);


        binding.tvAddToCart.setText(getString(R.string.add_to_cart));
        binding.tvAddToCart.setBackgroundResource(R.color.color_light_red);
        binding.tvAddToCart.setTextColor(getResources().getColor(R.color.color_white));
        currentTime = new SimpleDateFormat("HH:mm:ss", Locale.getDefault()).format(new Date());
        //  currentTimeSameDayShipping = new SimpleDateFormat("HH:mm", Locale.getDefault()).format(new Date());
        venueId = getIntent().getStringExtra(STORE_ID);
        productId = getIntent().getStringExtra(PRODUCT_ID);
        barcodeId = getIntent().getStringExtra(BARCODE_ID);
        offerId = getIntent().getStringExtra(OFFER_ID);


        isLoading = true;
        pageNumber = 1;
        deliveryTypelist = new ArrayList<>();
        //   categoryList = new ArrayList<>();
        imageList = new ArrayList<>();
        reviewList = new ArrayList<>();
        similarProList = new ArrayList<>();
        comboOfferList = new ArrayList<>();
        modifierList = new ArrayList<>();
        prefManager = PrefManager.getInstance(this);
        setListeners();
        getProductDetails();
        // fetchProductDetails();

    }

    private void setData(SingleProductDetailResponseModel data) {
        productId = String.valueOf(data.getProducts().getId());
        venueId = String.valueOf(data.getProducts().getVenue_id());

        if (data.getProducts().getBulk_selling_qty() > 0) {
            binding.llBuyBulk.setVisibility(View.VISIBLE);
            binding.tvBbSetPrice.setText("" + data.getProducts().getBulk_selling_qty() + " at " + getString(R.string.pound) + data.getProducts().getBulk_selling_price());
        } else {
            binding.llBuyBulk.setVisibility(View.GONE);
        }

        if (data.getVenues().getDelivery() == 1 && data.getVenues().getCollection() == 1) {
            binding.tvHomeDelivery.setVisibility(View.VISIBLE);
            binding.ivHomeDelivery.setVisibility(View.VISIBLE);
            binding.tvClickCollect.setVisibility(View.VISIBLE);
            binding.ivClickNCollect.setVisibility(View.VISIBLE);
        } else if (data.getVenues().getDelivery() == 0 && data.getVenues().getCollection() == 0) {
            binding.tvHomeDelivery.setAlpha(.2f);
            binding.ivHomeDelivery.setAlpha(.2f);
            binding.tvClickCollect.setAlpha(.2f);
            binding.ivClickNCollect.setAlpha(.2f);
            binding.tvSameDayDelivery.setAlpha(.2f);
            binding.tvBuyNow.setEnabled(false);
            binding.tvBuyNow.setClickable(false);
            binding.tvBuyNow.setAlpha(.2f);
        } else if (data.getVenues().getDelivery() == 0) {
            binding.tvHomeDelivery.setVisibility(View.GONE);
            binding.ivHomeDelivery.setVisibility(View.GONE);
        } else if (data.getVenues().getCollection() == 0) {
            binding.tvClickCollect.setVisibility(View.GONE);
            binding.ivClickNCollect.setVisibility(View.GONE);
        }
        binding.tvStoreName.setText(data.getVenues().getVenue_name());
        binding.tvProductName.setText(data.getProducts().getProduct_name());

        Glide.with(instance).load(ApiRequestUrl.BASE_URL_VENUE + data.getVenues().getVenue_images().get(0)).apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(binding.ivHomeImg);
        if (data.getProducts().getProduct_description() != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                //  binding.tvDescription.setText(Html.fromHtml(data.getProducts().getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
                binding.tvProDescription.setText(Html.fromHtml(data.getProducts().getProduct_description(), Html.FROM_HTML_MODE_COMPACT));
            } else {
                //  binding.tvDescription.setText(Html.fromHtml(data.getProducts().getProduct_description()));
                binding.tvProDescription.setText(Html.fromHtml(data.getProducts().getProduct_description()));
            }
        }

        if (data.getProducts().getAvl_quantity() > 0) {
            if (offerId != null || !offerId.equals("null") || !offerId.equals("") || !offerId.equals("0"))
                binding.tvPriceOld.setPaintFlags(binding.tvPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            binding.tvPriceOld.setText(getString(R.string.pound) + data.getProducts().getSelling_price());
        } else {
            binding.tvAddToCart.setText(getString(R.string.notify_me));
            binding.tvBuyNow.setVisibility(View.GONE);
            binding.ivItemOutOfStock.setVisibility(View.VISIBLE);
        }

        if (data.getProductOfers() != null && data.getProductOfers().getOffer_title() != null) {
            binding.tvPriceNew.setText(getString(R.string.pound) + data.getProducts().getNew_price());
        } else {
            binding.tvPriceNew.setText(getString(R.string.pound) + data.getProducts().getNew_price());
            binding.tvPriceOld.setVisibility(View.GONE);
        }


        if (!TextUtils.isEmpty(data.getProductRating().getRating_value()) && Float.parseFloat(data.getProductRating().getRating_value()) > 0) {
            binding.rbRating.setRating(Float.parseFloat(data.getProductRating().getRating_value()));
        } else binding.rbRating.setRating(5f);


        if (data.getProductRating().getCount() < 1) {
            // binding.tvRatingCount.setText(getString(R.string.no_rating));
        } else {
            // binding.tvRatingCount.setText("(" + data.getProductRating().getCount() + " Ratings)");
        }
        if (data.getProducts().isWishlisted()) {
            binding.ivFavo.setColorFilter(ContextCompat.getColor(this, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
        } else
            binding.ivFavo.setColorFilter(ContextCompat.getColor(this, R.color.hint_grey), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            if (offerId == null || offerId.equals("null") || offerId.equals("") || offerId.equals("0")) {
                binding.llDealExpire.setVisibility(View.GONE);
            } else {
                String endTime = data.getProductOfers().getOffer_time_end();
                Date dateObj1 = sdf.parse(currentTime);
                Date dateObj2 = sdf.parse(endTime);
                dismissCountDownTimer();
                setTimer(dateObj2, dateObj1);
            }
        } catch (Exception exc) {
            exc.printStackTrace();
            binding.llDealExpire.setVisibility(View.GONE);
        }

    }

    private void setDataModifier(ProductDetailByModifierResponseModel data) {
        modifierId = data.getProducts().getId();
        productId = String.valueOf(data.getProducts().getProduct_id());

        binding.tvAddToCart.setText(getString(R.string.add_to_cart));
        binding.tvAddToCart.setBackgroundResource(R.color.color_light_red);
        binding.tvAddToCart.setTextColor(getResources().getColor(R.color.color_white));
        if (data.getProducts().getAvl_quantity() > 0) {
            binding.tvPriceOld.setPaintFlags(binding.tvPriceOld.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            binding.tvPriceOld.setText(getString(R.string.pound) + data.getProducts().getSelling_price());
            binding.tvPriceNew.setText(getString(R.string.pound) + data.getProducts().getNew_price());

            if (data.getProducts().getOffer_title() != null && !data.getProducts().getOffer_title().equals("")) {
                binding.tvPriceNew.setText(getString(R.string.pound) + data.getProducts().getNew_price());
            } else
                binding.tvPriceNew.setText(getString(R.string.pound) + data.getProducts().getNew_price());

        } else {
            binding.tvAddToCart.setText(getString(R.string.notify_me));
            binding.tvBuyNow.setVisibility(View.GONE);
            binding.ivItemOutOfStock.setVisibility(View.VISIBLE);
        }

        if (data.getProducts().getModifier_images() != null && data.getProducts().getModifier_images().size() > 0) {
            imageList.clear();
            imageList.addAll(data.getProducts().getModifier_images());
            setViewPager();
        }
    }

    private void setListeners() {
        binding.ivBack.setOnClickListener(this);
        binding.tvViewStore.setOnClickListener(this);
        binding.tvAddToCart.setOnClickListener(this);
        binding.ivFavo.setOnClickListener(this);
        //   binding.tvAddToWishlist.setOnClickListener(this);
        binding.ivCart.setOnClickListener(this);
        binding.tvBuyNow.setOnClickListener(this);
        binding.tvAddToCartBb.setOnClickListener(this);
        binding.swipeRefresh.setOnRefreshListener(this);
    }

    private void setReviewAdaper() {
        ReviewAdapter reviewAdapter = new ReviewAdapter(this, reviewList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //  binding.rvTopReview.setLayoutManager(layoutManager);
        //  binding.rvTopReview.setAdapter(reviewAdapter);
    }

    private void similarProAdaper() {
        SimilarProductAdapter reviewAdapter = new SimilarProductAdapter(this, similarProList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rvSimilarPro.setLayoutManager(layoutManager);
        binding.rvSimilarPro.setAdapter(reviewAdapter);
    }

    private void setComboAdaper() {
        ComboOfferAdapter reviewAdapter = new ComboOfferAdapter(this, comboOfferList, currentTime, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvComboOffer.setLayoutManager(layoutManager);
        binding.rvComboOffer.setAdapter(reviewAdapter);
    }

    private void setModifierAdaper() {
        ModifierAdapter reviewAdapter = new ModifierAdapter(this, modifierList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvModifier.setLayoutManager(layoutManager);
        binding.rvModifier.setAdapter(reviewAdapter);
    }

    @Override
    public void onModifierSelect(int pos_1st, int pos_2nd) {
        try {
            requestHashMap.put(modifierList.get(pos_1st).getType(), modifierList.get(pos_1st).getType_list().get(pos_2nd).getName());
            List<String> modifierRequestList = new ArrayList<>();
            for (Map.Entry<String, String> entry : requestHashMap.entrySet()) {
                modifierRequestList.add(entry.getValue());
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                modifierName = String.join(",", modifierRequestList);
            }
            currentModifier = modifierList.get(pos_1st).getType_list().get(pos_2nd).getName();
            getProductDetailByModifier();
            log("modifierRequestList", "" + requestHashMap.toString() + "\n" + modifierName + " crnt==>" + currentModifier);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public void onModifierDeselect(int pos_1st, int pos_2nd) {
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_add_to_cart_bb:

                if (binding.tvAddToCartBb.getText().toString().equalsIgnoreCase(getString(R.string.add_to_cart)) || binding.tvAddToCartBb.getText().toString().equalsIgnoreCase(getString(R.string.go_to_bag))) {
                    if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(venueId) || prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                        prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                        addToCartBulk();
                    } else {
                        DialogUtils.showAlertDialog(instance, getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                                prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                                addToCartBulk();
                            }

                            @Override
                            public void onNegativeClick() {
                            }
                        });
                    }
                }

                break;
            case R.id.tv_add_to_cart:
                if (!setAlpha) {
                    if (binding.tvAddToCart.getText().toString().equalsIgnoreCase(getString(R.string.notify_me))) {
                        notifyMe();
                    } else if (binding.tvAddToCart.getText().toString().equalsIgnoreCase(getString(R.string.add_to_cart)) || binding.tvAddToCart.getText().toString().equalsIgnoreCase(getString(R.string.go_to_bag))) {
                        if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(venueId) || prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                            prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                            addToCart(false, false);
                        } else {
                            DialogUtils.showAlertDialog(instance, getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                                    addToCart(false, false);
                                }

                                @Override
                                public void onNegativeClick() {
                                }
                            });
                        }
                    } /*else if (binding.tvAddToCart.getText().toString().equalsIgnoreCase(getString(R.string.go_to_bag))) {   // go to checkout
                    Intent goToCart = new Intent(this, MyCartActivity.class);
                    startActivity(goToCart);
                }*/
                }
                break;

            case R.id.tv_view_store:
                Intent topStore = new Intent(this, VenueDetailActivity.class);
                topStore.putExtra(STORE_ID, venueId);
                topStore.putExtra(CATEGORY_ID, "");
                topStore.putExtra(FROM_WHERE, HOME_RETAIL);
                startActivity(topStore);
                break;

            case R.id.iv_favo:
                // if (!setAlpha) {
                likeDislike();
                // }
                break;

            case R.id.tv_buy_now:


                if (!setAlpha) {
                    if (prefManager.getPreference(VENUE_ID_IN_CART, "").equals(venueId) || prefManager.getPreference(VENUE_ID_IN_CART, "").equals("")) {
                        prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                        // addToCart(true, false);


                        dialogSwipeToBuy = new DialogSwipeToBuy(instance, instance, imageList,
                                requestHashMap, binding.tvPriceNew.getText().toString(), deliveryTypelist, productDetailResponseModel, sameDayDeliveryTime, sameDayDeliveryText, modifierId, productId, offerId)/*.show()*/;

                        dialogSwipeToBuy.show();

                    } else {
                        DialogUtils.showAlertDialog(instance, getString(R.string.remove_previous_venue_cart), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {
                                prefManager.savePreference(VENUE_ID_IN_CART, venueId);
                                //  addToCart(true, false);


                                dialogSwipeToBuy = new DialogSwipeToBuy(instance, instance, imageList,
                                        requestHashMap, binding.tvPriceNew.getText().toString(), deliveryTypelist, productDetailResponseModel, sameDayDeliveryTime, sameDayDeliveryText, modifierId, productId, offerId)/*.show()*/;

                                dialogSwipeToBuy.show();

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
                    Intent intent = new Intent(this, MyCartHospitalityActivity.class);
                    intent.putExtra(FROM_WHERE, getString(R.string.home));
                    startActivity(intent);
                } else if (prefManager.getPreference(CART_ENTRY_TYPE, 0) == CART_RESERVATION) {
                    Intent intent = new Intent(this, MyCartReservationActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(this, MyCartActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }


    @Override
    public void onRefresh() {
        init();
        binding.tvAddToCart.setText(getString(R.string.add_to_cart));
        binding.tvAddToCartBb.setText(getString(R.string.add_to_cart));
        binding.swipeRefresh.setRefreshing(false);
    }

    @Override
    public void onDrawerSelect(int position, int clickId) {
        productId = String.valueOf(similarProList.get(position).getId());
        modifierId = similarProList.get(position).getModifier_id();
        getProductDetails();
    }

    @Override
    public void onComboOfferListner(int pos) {
        List<AddToCartComboRequestModel.CartsBean> comboRequestModelList = new ArrayList<>();
        List<String> ids = new ArrayList<>();
        for (int i = 0; i < comboOfferList.get(pos).size(); i++) {
            ids.add(String.valueOf(comboOfferList.get(pos).get(i).getId()));
        }
        ids.add(productId);

        comboRequestModelList.add(new AddToCartComboRequestModel.CartsBean(venueId, String.valueOf(merchantId),
                String.valueOf(prefManager.getPreference(LOGIN_ID, 0)), productId,
                String.valueOf(modifierId), String.valueOf(offerId), quantity,
                costPerProduct, couponCode, "", TextUtils.join(",", ids), costPerProduct));

        for (int i = 0; i < comboOfferList.get(pos).size(); i++) {
            comboRequestModelList.add(new AddToCartComboRequestModel.CartsBean(venueId,
                    "" + comboOfferList.get(pos).get(i).getMerchant_id(),
                    String.valueOf(prefManager.getPreference(LOGIN_ID, 0)),
                    "" + comboOfferList.get(pos).get(i).getId(),
                    "" + comboOfferList.get(pos).get(i).getModifier_id(),
                    "" + comboOfferList.get(pos).get(i).getDiscount_id(),
                    quantity, comboOfferList.get(pos).get(i).getSelling_price(), "", "",
                    TextUtils.join(",", ids), comboOfferList.get(pos).get(i).getSelling_price()));
        }
        AddToCartComboRequestModel comboRequestModel = new AddToCartComboRequestModel(comboRequestModelList);
        addToCartCombo(comboRequestModel);
    }

    private void getProductDetailByModifier() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ProductDetailByModifierRequestModel requestModel = new ProductDetailByModifierRequestModel(venueId, productId,
                    modifierName, currentModifier, offerId);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<ProductDetailByModifierResponseModel> call = apiInterface.getProductDetailByModifier(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<ProductDetailByModifierResponseModel>() {
                @Override
                public void onResponse(Call<ProductDetailByModifierResponseModel> call, Response<ProductDetailByModifierResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            ProductDetailByModifierResponseModel modifierResponseModel = response.body();

                            if (modifierResponseModel.isStatus()) {
                                if (modifierResponseModel.getProducts() != null) {
                                    setAlpha = false;
                                    binding.tvAddToCart.setAlpha(1f);
                                    //  binding.tvAddToWishlist.setAlpha(1f);
                                    binding.tvBuyNow.setAlpha(1f);
                                    setDataModifier(modifierResponseModel);
                                } else {
                                    showSnackBar(binding.getRoot(), getString(R.string.no_modifier_data_found));
                                    modifierId = 0;
                                    setAlpha = true;
                                    binding.tvAddToCart.setAlpha(.5f);
                                    //   binding.tvAddToWishlist.setAlpha(.5f);
                                    binding.tvBuyNow.setAlpha(.5f);
                                }
                            } else {
                                showSnackBar(binding.getRoot(), modifierResponseModel.getMessage());
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
                public void onFailure(Call<ProductDetailByModifierResponseModel> call, Throwable t) {
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

    private void getProductDetails() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            String latitude = prefManager.getPreference(LATITUDE, "");
            String longitude = prefManager.getPreference(LONGITUDE, "");

            SingleProductDetailRequestModel requestModel = new SingleProductDetailRequestModel
                    (productId, String.valueOf(modifierId), offerId, latitude, longitude, barcodeId, "");
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<SingleProductDetailResponseModel> call = apiInterface.getProductDetail(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<SingleProductDetailResponseModel>() {
                @Override
                public void onResponse(Call<SingleProductDetailResponseModel> call, Response<SingleProductDetailResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            productDetailResponseModel = response.body();
                            if (productDetailResponseModel.isStatus()) {
                                setData(productDetailResponseModel);
                                setSameDayDelivery(productDetailResponseModel);

                                merchantId = productDetailResponseModel.getProducts().getMerchant_id();
                                modifierId = productDetailResponseModel.getProducts().getModifier_id();
                                costPerProduct = productDetailResponseModel.getProducts().getSelling_price();

                                if (productDetailResponseModel.getProducts().getImages() != null && productDetailResponseModel.getProducts().getImages().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);
                                    imageList.clear();
                                    // (myCartList.get(position).getModifier_images()) ?  myCartList.get(position).getProduct_image() :  myCartList.get(position).getModifier_images()
                                    if (productDetailResponseModel.getProducts().getModifier_images() != null && productDetailResponseModel.getProducts().getModifier_images().size() > 0) {
                                        imageList.addAll(productDetailResponseModel.getProducts().getModifier_images());
                                    } else {
                                        imageList.addAll(productDetailResponseModel.getProducts().getImages());
                                    }
                                    setViewPager();

                                } else {
                                    // binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    imageList.clear();
                                    setViewPager();
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (productDetailResponseModel.getProductRating().getReviews() != null &&
                                        productDetailResponseModel.getProductRating().getReviews().size() > 0) {
                                    reviewList.clear();
                                    reviewList.addAll(productDetailResponseModel.getProductRating().getReviews());
                                    setReviewAdaper();
                                    //   binding.tvTopReview.setVisibility(View.VISIBLE);
                                } else {
                                    reviewList.clear();
                                    setReviewAdaper();
                                    //  binding.tvTopReview.setVisibility(View.GONE);
                                }
                                ///////////////////////////////////////////////////  SIMILAR PRODUCT //////////////////////////////////////////////////////

                                if (productDetailResponseModel.getSimilarProducts() != null &&
                                        productDetailResponseModel.getSimilarProducts().size() > 0) {
                                    similarProList.clear();
                                    similarProList.addAll(productDetailResponseModel.getSimilarProducts());
                                    similarProAdaper();
                                    //   binding.tvTopReview.setVisibility(View.VISIBLE);
                                } else {
                                    similarProList.clear();
                                    similarProAdaper();
                                    //  binding.tvTopReview.setVisibility(View.GONE);
                                }
                                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (productDetailResponseModel.getComboOffer() != null && productDetailResponseModel.getComboOffer().size() > 0) {
                                    comboOfferList.clear();
                                    binding.tvComboOffer.setVisibility(View.VISIBLE);
                                    comboOfferList.addAll(productDetailResponseModel.getComboOffer());
                                    setComboAdaper();
                                } else {
                                    binding.tvComboOffer.setVisibility(View.GONE);
                                    comboOfferList.clear();
                                    setComboAdaper();
                                }
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (productDetailResponseModel.getProducts().getModifier_list() != null && productDetailResponseModel.getProducts().getModifier_list().size() > 0) {
                                    modifierList.clear();
                                    binding.rlModifier.setVisibility(View.VISIBLE);
                                    modifierList.addAll(productDetailResponseModel.getProducts().getModifier_list());
                                    for (int i = 0; i < modifierList.size(); i++) {
                                        if (modifierList.get(i).getType_list().size() > 0)
                                            requestHashMap.put(modifierList.get(i).getType(), modifierList.get(i).getType_list().get(0).getName()); // TODO : PUT CHECK FOT TYPE LIST NULL
                                        log("modifierRequestList", "" + requestHashMap.toString());
                                    }
                                    setModifierAdaper();
                                } else {
                                    binding.rlModifier.setVisibility(View.GONE);
                                    modifierList.clear();
                                    setModifierAdaper();
                                }

                            } else {
                                DialogUtils.showAlertDialogWithSingleButton(instance, productDetailResponseModel.getMessage().isEmpty() ? getString(R.string.season_expired) : productDetailResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {
                                        finish();
                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });

                                showSnackBar(binding.getRoot(), productDetailResponseModel.getMessage());
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
                public void onFailure(Call<SingleProductDetailResponseModel> call, Throwable t) {
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
        if (isInternetOn(this)) {
            LikeDislikeProductRequestModel requestModel = new LikeDislikeProductRequestModel(String.valueOf(merchantId), venueId,
                    String.valueOf(prefManager.getPreference(LOGIN_ID, "")), productId, String.valueOf(modifierId),
                    offerId, costPerProduct, couponCode);
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<LikeDislikeProductResponseModel> call = apiInterface.likeDislikeProduct(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<LikeDislikeProductResponseModel>() {
                @Override
                public void onResponse(Call<LikeDislikeProductResponseModel> call, Response<LikeDislikeProductResponseModel> response) {
                    try {
                        if (response.isSuccessful()) {
                            LikeDislikeProductResponseModel venueDetailResponseModel = response.body();
                            if (venueDetailResponseModel.isStatus()) {
                                /*if (binding.tvAddToWishlist.getText().toString().equals(getString(R.string.add_to_wishlist))) {
                                    binding.tvAddToWishlist.setText(getString(R.string.wishlisted));
                                    binding.tvAddToWishlist.setBackgroundResource(R.drawable.green_filled_rounded_rect);
                                    binding.tvAddToWishlist.setTextColor(getResources().getColor(R.color.color_white));
                                } else {
                                    binding.tvAddToWishlist.setText(getString(R.string.add_to_wishlist));
                                    binding.tvAddToWishlist.setBackgroundResource(R.drawable.grey_filled_rounded_rect);
                                    binding.tvAddToWishlist.setTextColor(getResources().getColor(R.color.app_header_color));
                                }*/

                                if (productDetailResponseModel.getProducts().isWishlisted()) {
                                    productDetailResponseModel.getProducts().setWishlisted(false);
                                    binding.ivFavo.setColorFilter(ContextCompat.getColor(instance, R.color.hint_grey), android.graphics.PorterDuff.Mode.SRC_IN);
                                } else {
                                    productDetailResponseModel.getProducts().setWishlisted(true);
                                    binding.ivFavo.setColorFilter(ContextCompat.getColor(instance, R.color.color_light_red), android.graphics.PorterDuff.Mode.SRC_IN);
                                }

                                DialogUtils.showAlertDialogWithSingleButton(instance, venueDetailResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });

                            } else {
                                DialogUtils.showAlertDialogWithSingleButton(instance, venueDetailResponseModel.getMessage(), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });
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

    private void notifyMe() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            final AddressRequestModel requestModel = new AddressRequestModel(prefManager.getPreference(EMAIL, ""),
                    productId, venueId, String.valueOf(merchantId), String.valueOf(modifierId));

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
                                } else binding.tvCartCount.setVisibility(View.GONE);
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
        /*Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 2000, 3000);*/
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        String action = intent.getAction();
        String data = intent.getDataString();
        if (Intent.ACTION_VIEW.equals(action) && data != null) {
            //String productId = data.substring(data.lastIndexOf("/") + 1);
            //String offerId = data.substring(data.lastIndexOf("/")-1 );
            String[] array = data.split("/");
            String offerId = array[array.length - 1];
            String productId = array[array.length - 2];
            /*Uri contentUri = RecipeContentProvider.CONTENT_URI.buildUpon()
                    .appendPath(recipeId).build();
            showRecipe(contentUri);*/
            getDeepLinkData(productId, offerId, data);
        }
    }

    private void getDeepLinkData(String productId, String offerId, String data) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<SingleProductDetailResponseModel> call = apiInterface.getDeepLinkData(data, productId, offerId);
            call.enqueue(new Callback<SingleProductDetailResponseModel>() {
                @Override
                public void onResponse(Call<SingleProductDetailResponseModel> call, Response<SingleProductDetailResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            SingleProductDetailResponseModel venueDetailResponseModel = response.body();
                            if (venueDetailResponseModel.isStatus()) {

                                setData(venueDetailResponseModel);
                                setSameDayDelivery(venueDetailResponseModel);
                                merchantId = venueDetailResponseModel.getProducts().getMerchant_id();
                                modifierId = venueDetailResponseModel.getProducts().getModifier_id();
                                costPerProduct = venueDetailResponseModel.getProducts().getSelling_price();
                                if (venueDetailResponseModel.getProducts().getImages() != null && venueDetailResponseModel.getProducts().getImages().size() > 0) {
                                    // binding.tvNoDataFound.setVisibility(View.GONE);
                                    imageList.clear();

                                    imageList.addAll(venueDetailResponseModel.getProducts().getImages());
                                    setViewPager();

                                } else {
                                    // binding.tvNoDataFound.setVisibility(View.VISIBLE);
                                    imageList.clear();
                                    setViewPager();
                                }
                                ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (venueDetailResponseModel.getProductRating().getReviews() != null &&
                                        venueDetailResponseModel.getProductRating().getReviews().size() > 0) {
                                    reviewList.clear();
                                    reviewList.addAll(venueDetailResponseModel.getProductRating().getReviews());
                                    setReviewAdaper();
                                    //  binding.tvTopReview.setVisibility(View.VISIBLE);
                                } else {
                                    reviewList.clear();
                                    setReviewAdaper();
                                    // binding.tvTopReview.setVisibility(View.GONE);
                                }
                                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (venueDetailResponseModel.getComboOffer() != null && venueDetailResponseModel.getComboOffer().size() > 0) {
                                    comboOfferList.clear();
                                    //  binding.tvComboOffer.setVisibility(View.VISIBLE);
                                    comboOfferList.addAll(venueDetailResponseModel.getComboOffer());
                                    setComboAdaper();
                                } else {
                                    //   binding.tvComboOffer.setVisibility(View.GONE);
                                    comboOfferList.clear();
                                    setComboAdaper();
                                }
                                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                                if (venueDetailResponseModel.getProducts().getModifier_list() != null && venueDetailResponseModel.getProducts().getModifier_list().size() > 0) {
                                    modifierList.clear();
                                    binding.rlModifier.setVisibility(View.VISIBLE);
                                    modifierList.addAll(venueDetailResponseModel.getProducts().getModifier_list());

                                    for (int i = 0; i < modifierList.size(); i++) {
                                        requestHashMap.put(modifierList.get(i).getType(), modifierList.get(i).getType_list().get(0).getName());
                                    }
                                    setModifierAdaper();
                                } else {
                                    binding.rlModifier.setVisibility(View.GONE);
                                    modifierList.clear();
                                    setModifierAdaper();
                                }


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
                public void onFailure(Call<SingleProductDetailResponseModel> call, Throwable t) {
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

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_product_details;
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

    private void setSameDayDelivery(SingleProductDetailResponseModel venueDetailResponseModel) {  // TODO : SHARE IT
        if (venueDetailResponseModel.getShippingData() != null) {
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
                        binding.tvSameDayDeliveryCost.setText(getString(R.string.next_day_delivery) + " +" + getString(R.string.pound) + venueDetailResponseModel.getShippingData().getAmount());
                    } else
                        binding.tvSameDayDeliveryCost.setText(getString(R.string.next_day_delivery) + " +" + venueDetailResponseModel.getShippingData().getAmount() + getString(R.string.percent));

                } else {
                    if (venueDetailResponseModel.getShippingData().getTo_day() > 1) {
                        calendar.add(Calendar.DAY_OF_YEAR, venueDetailResponseModel.getShippingData().getTo_day());
                        Date tomorrow = calendar.getTime();
                        String tomorrowAsString = dateFormat.format(tomorrow);

                        binding.tvDeliveryDay.setText(tomorrowAsString);
                    } else binding.tvDeliveryDay.setText(getString(R.string.today));

                    if (venueDetailResponseModel.getShippingData().getType() == 1) {
                        binding.tvSameDayDeliveryCost.setText(venueDetailResponseModel.getShippingData().getName() + " +" + getString(R.string.pound) + venueDetailResponseModel.getShippingData().getAmount());
                    } else
                        binding.tvSameDayDeliveryCost.setText(venueDetailResponseModel.getShippingData().getName() + " +" + venueDetailResponseModel.getShippingData().getAmount() + getString(R.string.percent));

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
            binding.tvSameDayDelivery.setVisibility(View.GONE);
        }

        if (venueDetailResponseModel.getVenues().getDelivery() == 0 && venueDetailResponseModel.getVenues().getCollection() == 0) {
            binding.rlSameDayDelivery.setAlpha(.2f);
        }

        if (venueDetailResponseModel.getVenues().getDelivery() == 1) {
            deliveryTypelist.add(getString(R.string.home_delivery));
        }
        if (venueDetailResponseModel.getVenues().getCollection() == 1) {
            deliveryTypelist.add(getString(R.string.click_n_collect));
        }
        if (venueDetailResponseModel.getShippingData() != null) {
            deliveryTypelist.add(binding.tvSameDayDeliveryCost.getText().toString());
            sameDayDeliveryText = binding.tvSameDayDeliveryCost.getText().toString();
            sameDayDeliveryTime = binding.tvDeliveryDay.getText().toString();
        }
    }

    private void addToCartBulk() {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            AddToCartRequestModel requestModel = null;
            requestModel = new AddToCartRequestModel
                    (String.valueOf(merchantId), venueId, productId, String.valueOf(modifierId), offerId, "0",
                            "" + productDetailResponseModel.getProducts().getBulk_selling_qty(), costPerProduct, couponCode, "", "1", productDetailResponseModel.getProducts().getBulk_selling_price(),
                            prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""));

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.addToCartBulk(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            AddToCartResponseModel cartResponseModel = response.body();
                            if (cartResponseModel.isStatus()) {
                                prefManager.savePreference(CART_ENTRY_TYPE, CART_RETAIL);
                                binding.tvAddToCartBb.setText(getString(R.string.go_to_bag));
                                binding.tvAddToCartBb.setTextColor(getResources().getColor(R.color.color_white));

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

    private void addToCartCombo(AddToCartComboRequestModel comboRequestModel) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.addMultipleCarts(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), comboRequestModel);
            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {
                        //  binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            prefManager.savePreference(CART_ENTRY_TYPE, CART_RETAIL);
                            binding.tvAddToCart.setText(getString(R.string.go_to_bag));
                            //binding.tvAddToCart.setBackgroundResource(R.drawable.saffron_filled_rounded_rect);
                            binding.tvAddToCart.setTextColor(getResources().getColor(R.color.color_white));
                            AddToCartResponseModel cartResponseModel = response.body();
                            if (cartResponseModel.isStatus()) {

                                getTotalCartCount();

                            } else
                                showSnackBar(binding.getRoot(), cartResponseModel.getMessage());
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

    private void addToCart(boolean openCart, boolean isCombo) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();
            AddToCartRequestModel requestModel = null;

            requestModel = new AddToCartRequestModel
                    (String.valueOf(merchantId), venueId, productId, String.valueOf(modifierId),
                            offerId, String.valueOf(quantity), costPerProduct, couponCode, "", "0", prefManager.getPreference(LATITUDE, ""), prefManager.getPreference(LONGITUDE, ""));

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<AddToCartResponseModel> call = apiInterface.addToCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            //binding.tvNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<AddToCartResponseModel>() {
                @Override
                public void onResponse(Call<AddToCartResponseModel> call, Response<AddToCartResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            AddToCartResponseModel cartResponseModel = response.body();
                            if (cartResponseModel.isStatus()) {
                                prefManager.savePreference(CART_ENTRY_TYPE, CART_RETAIL);
                                if (openCart) {
                                    Intent goToCart = new Intent(instance, MyCartActivity.class);
                                    startActivity(goToCart);
                                } else {
                                    if (!isCombo) {
                                        binding.tvAddToCart.setText(getString(R.string.go_to_bag));
                                        //   binding.tvAddToCart.setBackgroundResource(R.drawable.saffron_filled_rounded_rect);
                                        binding.tvAddToCart.setTextColor(getResources().getColor(R.color.color_white));
                                    }
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

    private void setTimer(Date dateObj2, Date dateObj1) {
        mCountDownTimer = new CountDownTimer(dateObj2.getTime() - dateObj1.getTime(), 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long millis = millisUntilFinished;
                String hms = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(millis), TimeUnit.MILLISECONDS.toMinutes(millis) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millis)), TimeUnit.MILLISECONDS.toSeconds(millis) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis)));
                binding.tvExpiry.setText(hms);
            }

            @Override
            public void onFinish() {

            }
        };
        mCountDownTimer.start();
    }

    public void dismissCountDownTimer() {
        if (mCountDownTimer != null)
            mCountDownTimer.cancel();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    /////////////////////////////////////////////////////////////////////////
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (dialogSwipeToBuy != null) {
            if (requestCode == OPEN_CHANGE_ADDRESS_ACTIVITY && resultCode == RESULT_OK && data != null) {
                Intent dataChangeAddress = data;
                dialogSwipeToBuy.getcallback_OPEN_CHANGE_ADDRESS_ACTIVITY(dataChangeAddress);

            } else if (requestCode == OPEN_PAYMENT_DETAIL_ACTIVITY && resultCode == RESULT_OK && data != null) {
                Intent dataOpenPaymentDetail = data;
                dialogSwipeToBuy.getcallback_OPEN_PAYMENT_DETAIL_ACTIVITY(dataOpenPaymentDetail);

            } else if (requestCode == OPEN_PIN_CREATE_ACTIVITY && resultCode == RESULT_OK && data != null) {
                if (data.getBooleanExtra(PIN_CREATE_ACTIVITY_CALLBACK, false)) {
                    Intent dataOpenPinCreat = data;
                    dialogSwipeToBuy.getcallback_OPEN_PIN_CREATE_ACTIVITY(dataOpenPinCreat);
                }

            } else if (requestCode == OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY && resultCode == RESULT_OK && data != null) {
                Intent dataActivePayList = data;
                dialogSwipeToBuy.getcallback_OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY(dataActivePayList);

            } else if (requestCode == OPEN_ADD_ADDRESS_ACTIVITY && resultCode == RESULT_OK && data != null) {
                Intent dataAddAddress = data;
                dialogSwipeToBuy.getcallback_OPEN_ADD_ADDRESS_ACTIVITY(dataAddAddress);

            } else if (requestCode == OPEN_PIN_VERIFY_ACTIVITY && resultCode == RESULT_OK && data != null) {
                dialogSwipeToBuy.checkOut();
            }
            //////////////////////////////////////////////
            else if (requestCode == ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE && resultCode == RESULT_OK && data != null) {

                Intent mData = data;
                dialogSwipeToBuy.getcallback_add_card_ACTIVITY(mData);


            }
            //////////////////////////////////////////////
        }
    }
    //////////////////////////////////////////////////////////////////////////////
}
