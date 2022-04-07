
package com.poundland.retail.activityHospitality;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.ActiveCardActivity;
import com.poundland.retail.activity.AddAddressActivity;
import com.poundland.retail.activity.AddNewCardOnCheckoutActivity;
import com.poundland.retail.activity.AddressActivity;
import com.poundland.retail.activity.BaseActivity;
import com.poundland.retail.activity.CardActivity;
import com.poundland.retail.activity.CouponActivity;
import com.poundland.retail.activity.CreatePinActivity;
import com.poundland.retail.activity.MainActivity;
import com.poundland.retail.activity.MyOrderActivity;
import com.poundland.retail.activity.NearByDealsActivity;
import com.poundland.retail.activity.NotificationActivity;
import com.poundland.retail.activity.SpecialOfferDetailsActivity;
import com.poundland.retail.activity.VenueDetailActivity;
import com.poundland.retail.activity.VerifyPinActivity;
import com.poundland.retail.activityHospitality.adapter.MyCartHospitalityAdapter;
import com.poundland.retail.adapter.TipAdapter;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.ActivityMyCartHospitalityBinding;
import com.poundland.retail.dialog.DialogUtils;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.DrawerListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.UpdateCartListner;
import com.poundland.retail.model.TipModel;
import com.poundland.retail.model.requestModel.AddMobileCartQuantityRequestModel;
import com.poundland.retail.model.requestModel.CheckoutCartHospitalityRequestModel;
import com.poundland.retail.model.requestModel.CouponRequestModel;
import com.poundland.retail.model.requestModel.GetCartWithSummaryRequestModel;
import com.poundland.retail.model.responseModel.CheckoutCartHospitalityResponseModel;
import com.poundland.retail.model.responseModel.CouponResponseModel;
import com.poundland.retail.model.responseModel.GetCartSummaryHospResponseModel;
import com.poundland.retail.notificationService.NotificationModel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.isInternetOn;
import static com.poundland.retail.appUtils.HelperClass.log;
import static com.poundland.retail.appUtils.HelperClass.logOut;
import static com.poundland.retail.appUtils.HelperClass.showSnackBar;
import static com.poundland.retail.appUtils.HelperClass.toBase64;
import static com.poundland.retail.appUtils.HelperClass.toast;
import static com.poundland.retail.interfaces.Constants.ACTIVE_PAYMENT_TYPE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ID;
import static com.poundland.retail.interfaces.Constants.ADDRESS_ONE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_THREE;
import static com.poundland.retail.interfaces.Constants.ADDRESS_TWO;
import static com.poundland.retail.interfaces.Constants.ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE;
import static com.poundland.retail.interfaces.Constants.AUTH_TOKEN;
import static com.poundland.retail.interfaces.Constants.CARD;
import static com.poundland.retail.interfaces.Constants.CARD_CVV;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_MONTH;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_YEAR;
import static com.poundland.retail.interfaces.Constants.CARD_NUMBER;
import static com.poundland.retail.interfaces.Constants.CATEGORY_ID;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_ORDER;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_TYPE;
import static com.poundland.retail.interfaces.Constants.DELETE;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_MY_CART;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.HHmm;
import static com.poundland.retail.interfaces.Constants.HOME_HOSPITALITY;
import static com.poundland.retail.interfaces.Constants.IS_SAVE_CARD;
import static com.poundland.retail.interfaces.Constants.LATITUDE;
import static com.poundland.retail.interfaces.Constants.LOGIN_ID;
import static com.poundland.retail.interfaces.Constants.LONGITUDE;
import static com.poundland.retail.interfaces.Constants.LOYA_STAMP_VOUC_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.MINUS;
import static com.poundland.retail.interfaces.Constants.NONE;
import static com.poundland.retail.interfaces.Constants.OFFER_ID;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVITY_COUPON_CALLBACK_CODE;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVITY_COUPON_CALLBACK_DATA;
import static com.poundland.retail.interfaces.Constants.OPEN_ADD_ADDRESS_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_CHANGE_ADDRESS_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_LOYALTY_STAMP_VOUCHER_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PAYMENT_DETAIL_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_CREATE_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_VERIFY_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.PAYMENT_DETAIL_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.PAYMENT_GATEWAY_ACTIVE_SAVED_CARD;
import static com.poundland.retail.interfaces.Constants.PAYMENT_GATEWAY_STRIPE;
import static com.poundland.retail.interfaces.Constants.PIN_CREATE_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.PLUS;
import static com.poundland.retail.interfaces.Constants.POSITION;
import static com.poundland.retail.interfaces.Constants.POSTAL_CODE;
import static com.poundland.retail.interfaces.Constants.PRODUCT_ID;
import static com.poundland.retail.interfaces.Constants.RESERVATION_ID;
import static com.poundland.retail.interfaces.Constants.SPECIAL_OFFER_ID;
import static com.poundland.retail.interfaces.Constants.STORE;
import static com.poundland.retail.interfaces.Constants.STORE_ID;
import static com.poundland.retail.interfaces.Constants.TOP_OFFER_TITLE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;
import static com.poundland.retail.interfaces.Constants.YYYY_MM_DD;


public class MyCartHospitalityActivity extends BaseActivity implements View.OnClickListener, UpdateCartListner, SwipeRefreshLayout.OnRefreshListener, RadioGroup.OnCheckedChangeListener, DrawerListner {
    private static final String TAG = MyCartHospitalityActivity.class.getName();
    private final int SERVICE_CHARGE_SELECTED = 301;
    private final int SERVICE_CHARGE_DESELECTED = 302;
    private final int ONLOAD = 303;
    private final int RB_TABLE_CHECKED = 304;
    private final int FREE_HOME_DELIVERY = 305;
    private final int PAID_HOME_DELIVERY = 306;
    private final int RB_CLICK_COLLECT = 307;
    private final int RB_TABLE_SELECTED = 308;
    private ActivityMyCartHospitalityBinding binding;
    private PrefManager prefManager;
    private String venue_id = "";
    private MyCartHospitalityActivity instance = null;
    private String deliveryMethod = "";
    private double totalLoyaltyValue;
    private String PAYMENT_MODE = CARD;
    private int REORDER_STATUS = 0;
    private String addressId;
    private String cardId = "";
    private String payment_gatway = PAYMENT_GATEWAY_STRIPE;
    private String cardNumber = "";
    private String cardExpiryMonth = "";
    private String cardExpiryYear = "";
    private String cardCVV = "";
    private String IS_GIFT = "0";
    private GetCartSummaryHospResponseModel responseModel;
    private GetCartSummaryHospResponseModel.CustAddressBean customerAddress;
    private List<GetCartSummaryHospResponseModel.CartsBean> myCartList;
    private List<GetCartSummaryHospResponseModel.DefaultCardsBean> myCardList;
    private String totalAmountPayable;
    private String DEFAULT_DELIVERY_CHARGE = "0.00";
    private String deliveryCharge;
    private String LOYALTY = "loyalty";
    private String app_type = "LOCAL";
    private int IS_HOSPITALITY = 1;
    private String address_one = "";
    private String address_two = "";
    private String address_three = "";
    private String postal_code = "";
    private boolean isSaveCard;
    private String shippingId = "";
    private KProgressHUD dialogGlobal = null;
    private List<TipModel> tipList;
    private List<String> deliveryTimeGap;
    private List<String> collectionTimeGap;
    private double tipAmount;
    private ListPopupWindow popupWindow;
    private boolean isPopup;
    private String deliveryGap = "ASAP";
    private int selectedtTip;
    private String currentTime;
    private String couponCode;
    private double serviceCharge;
    private MyCartHospitalityAdapter myCartAdapter;
    private double couponPrice = 0.0;
    private double enteredLoyaltyPoint = 0.0;
    private boolean isAddNewCard;
    // private boolean isOutOfStock;
    // private boolean isItemInStock;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_cart_hospitality);
        dialogGlobal = DialogUtils.getCustomDialog(this);
        init();
        setListeners();
        binding.etTableNo.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    if (responseModel.getCarts_summary().getIsOrderEnable() == 0 || responseModel.getVenue_timing().getIs_venue_open() == 0) {
                        allOrderClosed();
                        if (responseModel.getVenue_timing().getIs_venue_open() == 1)
                            showOutOfStockDialog(getString(R.string.hourly_order_limit_message));
                        return false;
                    } else if (responseModel.getVenue_timing().getIsClose() != null && responseModel.getVenue_timing().getIsClose().equals("1")) {
                        allOrderClosed();
                        showVenueClosedDialog(getString(R.string.venue_is_closed));
                    } else onCheckoutBtnClick();
                    return true;
                }
                return false;
            }
        });
    }

    private void init() {
        instance = this;
        tipAmount = 0;
        serviceCharge = 0;
        selectedtTip = 0;
        defautDeliveryCharge();

        binding.tvSelectedValue.setVisibility(View.GONE);
        binding.ivCancelLoyalty.setVisibility(View.GONE);
        binding.tvTipAmount.setVisibility(View.GONE);
        binding.tvTipPercent.setVisibility(View.GONE);
        binding.tvTipAmountShow.setVisibility(View.GONE);
        binding.tvTipLabel.setVisibility(View.GONE);

        binding.tvCouponAppliedLbl.setVisibility(View.GONE);
        binding.tvCouponAppliedAmount.setVisibility(View.GONE);
        binding.ivCancelCouponApplied.setVisibility(View.GONE);

        binding.tvCheckout.setVisibility(View.INVISIBLE);
        binding.tvProductDetails.setVisibility(View.GONE);
        // binding.rlTip.setVisibility(View.GONE);
        binding.rlMyCartAddress.setVisibility(View.GONE);
        binding.tvServiceChargeAmt.setVisibility(View.GONE);
        binding.tvServiceChargeLbl.setVisibility(View.GONE);

        prefManager = PrefManager.getInstance(this);
        currentTime = new SimpleDateFormat(YYYY_MM_DD, Locale.getDefault()).format(new Date());
        deliveryTimeGap = new ArrayList<>();
        collectionTimeGap = new ArrayList<>();
        myCartList = new ArrayList<>();
        myCardList = new ArrayList<>();
        tipList = new ArrayList<>();
        TipModel tipModel = new TipModel(5, false);
        TipModel tipModel1 = new TipModel(10, false);
        TipModel tipModel2 = new TipModel(15, false);
        TipModel tipModel3 = new TipModel(20, false);
        tipList.add(tipModel);
        tipList.add(tipModel1);
        tipList.add(tipModel2);
        tipList.add(tipModel3);
        binding.tbMyCart.tvTitle.setText(getString(R.string.cart));
        binding.tbMyCart.ivCart.setVisibility(View.GONE);

        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) binding.tbMyCart.ivNotification.getLayoutParams();
        params.addRule(RelativeLayout.ALIGN_PARENT_END);
        params.addRule(RelativeLayout.LEFT_OF, R.id.iv_notification);

        binding.tbMyCart.ivNotification.setLayoutParams(params); //function for adjust view if you want some view gone visible
        binding.tbMyCart.ivNotification.setVisibility(View.GONE);

        getCartHospitality(NONE, "", "");
        setTipAdapter();

        binding.cbIsServiceCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (((CheckBox) view).isChecked()) {
                    binding.tvServiceChargeAmt.setVisibility(View.VISIBLE);
                    binding.tvServiceChargeLbl.setVisibility(View.VISIBLE);
                    calculateTotalAmount(SERVICE_CHARGE_SELECTED);
                } else {
                    binding.tvServiceChargeAmt.setVisibility(View.GONE);
                    binding.tvServiceChargeLbl.setVisibility(View.GONE);
                    calculateTotalAmount(SERVICE_CHARGE_DESELECTED);
                }
                showTotalAmountLable(totalAmountPayable);

            }
        });
    }

    private void setListeners() {
        binding.tbMyCart.tvTitle.setOnClickListener(this);
        binding.btnSelectCoupon.setOnClickListener(this);
        binding.btnApplyCoupon.setOnClickListener(this);
        //  binding.rlAddNewCard.setOnClickListener(this);
        binding.tbMyCart.ivBack.setOnClickListener(this);
        binding.tbMyCart.ivNotification.setOnClickListener(this);
        binding.tvChangeAddress.setOnClickListener(this);
        binding.ivCancelCouponApplied.setOnClickListener(this);
        binding.tvInputLoyalty.setOnClickListener(this);
        binding.tvCheckout.setOnClickListener(this);
        binding.rlCards.setOnClickListener(this);
        binding.tvStartShopping.setOnClickListener(this);
        binding.ivCancelLoyalty.setOnClickListener(this);
        binding.tvRedeemNow.setOnClickListener(this);
        binding.tvAsap.setOnClickListener(this);
        binding.tvAsapCollectio.setOnClickListener(this);
        binding.radioGroup.setOnCheckedChangeListener(this);
        binding.tvAddMore.setOnClickListener(this);
    }

    private void setAdapter() {
        myCartAdapter = new MyCartHospitalityAdapter(this, myCartList, this, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.rvMyCart.setLayoutManager(layoutManager);
        binding.rvMyCart.setAdapter(myCartAdapter);
    }

    private void setTipAdapter() {
        //  TipAdapterNew tipAdapter = new TipAdapterNew(this, tipList, this);
        TipAdapter tipAdapter = new TipAdapter(this, tipList, this);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        binding.rvTipList.setLayoutManager(layoutManager);
        binding.rvTipList.setAdapter(tipAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_back:
                finish();
                break;

            case R.id.tv_add_more:

                if (myCartList.size() > 0)
                    openVenue(0);
                break;


            case R.id.btn_apply_coupon:
                if (binding.etCouponCode.getText().toString().length() > 3) {
                    applyCoupon(myCartList.get(0).getReservation_id(), String.valueOf(myCartList.get(0).getVenue_id()), binding.etCouponCode.getText().toString(),
                            String.valueOf(prefManager.getPreference(LOGIN_ID, 0)), "1", deliveryCharge);
                }

                break;


            case R.id.iv_cancel_coupon_applied:
                cancelCoupon();
                break;

            case R.id.btn_select_coupon:

                if (myCartList != null && myCartList.size() > 0) {
                    Intent coupon = new Intent(this, CouponActivity.class);
                    coupon.putExtra(FROM_WHERE, getString(R.string.cart));
                    coupon.putExtra(VENUE_ID, String.valueOf(myCartList.get(0).getVenue_id()));
                    coupon.putExtra(RESERVATION_ID, myCartList.get(0).getReservation_id());
                    startActivityForResult(coupon, OPEN_ACTIVITY_COUPON_CALLBACK_CODE);
                }


                break;


            case R.id.tv_asap:
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    setSpinnerFordeliveryGap();
                    isPopup = true;
                }

                break;
            case R.id.tv_asap_collectio:
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    setSpinnerForCollectionGap();
                    isPopup = true;
                }

                break;


            case R.id.rl_cards:
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    setSpinnerForCards();
                    isPopup = true;
                }

                break;
           /* case R.id.rl_add_new_card:
                addNewCard();
                break;*/

            case R.id.tv_change_address:
                if (binding.tvChangeAddress.getText().toString().equals(getString(R.string.change_address))) {
                    Intent intent = new Intent(this, AddressActivity.class);
                    intent.putExtra(FROM_WHERE, getString(R.string.cart));
                    startActivityForResult(intent, OPEN_CHANGE_ADDRESS_ACTIVITY);
                } else if (binding.tvChangeAddress.getText().toString().equals(getString(R.string.add_address))) {
                    Intent intent = new Intent(this, AddAddressActivity.class);
                    intent.putExtra(FROM_WHERE, FROM_MY_CART);
                    startActivityForResult(intent, OPEN_ADD_ADDRESS_ACTIVITY);
                } else {
                    if (myCartList != null && myCartList.get(0).getLatitude() != null && myCartList.get(0).getLongitude() != null) {
                        Uri gmmIntentUri = Uri.parse("geo:" + myCartList.get(0).getLatitude() + "," + myCartList.get(0).getLongitude());
                        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                        mapIntent.setPackage("com.google.android.apps.maps");
                        if (mapIntent.resolveActivity(getPackageManager()) != null) {
                            startActivity(mapIntent);
                        }
                    } else
                        showSnackBar(binding.tvChangeAddress, "Direction is unavailable currently");
                }
                break;

            case R.id.tv_input_loyalty:
            case R.id.tv_redeem_now: ///Merging tv_input_loyalty & tv_redeem_now click BCZ both have same functionality.

                // if (isItemInStock) {
                showLoyaltyDialog();
                // } else showOutOfStockDialog(getString(R.string.cart_item_out_of_stock));


                break;

            case R.id.iv_notification:
                Intent noti = new Intent(this, NotificationActivity.class);
                startActivity(noti);
                break;

            case R.id.iv_cancel_loyalty:
                closeLoyalty();

                break;
            case R.id.tv_checkout:
                onCheckoutBtnClick();
                break;
            case R.id.tv_start_shopping:
                if (dialogGlobal != null)
                    dialogGlobal.show();
                //finish();
                finishAffinity();
                Intent stamp = new Intent(instance, MainActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.finishAffinity));
                startActivity(stamp);
                break;
        }

    }

    private void onCheckoutBtnClick() {
        if (validateDeliveryMethod()) {

            if (Double.parseDouble(totalAmountPayable) > 0) {
                if (isValid()) {
                    Intent stamp = new Intent(instance, VerifyPinActivity.class);
                    stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                    stamp.putExtra(POSITION, -1);
                    startActivityForResult(stamp, OPEN_PIN_VERIFY_ACTIVITY);


                }
            } else {
                payment_gatway = "zero";
                PAYMENT_MODE = LOYALTY;
                checkOut();
            }
        }
    }


    private void showCarts_summary(GetCartSummaryHospResponseModel.CartsSummaryBean carts_summary) {
        if (carts_summary.getCollection() == 0) {
            clickNcollectClosed();
        }
        if (carts_summary.getDelivery() == 0) {
            binding.rbStandard.setChecked(false);
            binding.rbStandard.setClickable(false);
            binding.rbStandard.setEnabled(false);
            binding.rbStandard.setAlpha(.5f);
            binding.tvStandardDeliveryTime.setAlpha(.5f);
        } else {
            if (binding.rbClickNCollect.isChecked()) {
                if (Double.parseDouble(carts_summary.getHome_delivery_mov()) <= Double.parseDouble(carts_summary.getCollection_grand_total())) {
                    binding.rbStandard.setClickable(true);
                    binding.rbStandard.setEnabled(true);
                }
            } else {
                if (Double.parseDouble(carts_summary.getHome_delivery_mov()) <= Double.parseDouble(carts_summary.getGrand_total())) {
                    binding.rbStandard.setClickable(true);
                    binding.rbStandard.setEnabled(true);
                }
            }

        }

        binding.tvStandardDeliveryTime.setText(getString(R.string.est_delivery) + " " + carts_summary.getDelivery_end_days() + " Mins)");
        binding.tvCollectionTime.setText(getString(R.string.est_collection_time) + " " + carts_summary.getCollection_time() == null ? "" : getString(R.string.est_collection_time) + " " + carts_summary.getCollection_time() + " Mins)");

        binding.tvCartTotalAmount.setText(getString(R.string.pound) + carts_summary.getCart_total());
        if (!TextUtils.isEmpty(carts_summary.getDiscount()) && Double.parseDouble(carts_summary.getDiscount()) > 0) {
            binding.tvDiscountAmount.setVisibility(View.VISIBLE);
            binding.tvDiscount.setVisibility(View.VISIBLE);
            binding.tvDiscountAmount.setText(String.format("%s%s", getString(R.string.pound), carts_summary.getDiscount()));
        } else {
            binding.tvDiscountAmount.setVisibility(View.GONE);
            binding.tvDiscount.setVisibility(View.GONE);
        }

        setTax();
        setDeliveryCharge();

        setTransactionCharge();
        binding.tvSubTotalAmount.setText(getString(R.string.pound) + carts_summary.getSub_total());


        calculateTotalAmount(ONLOAD);

        if (binding.rbTable.isChecked()) {
            if (selectedtTip > 0) {
                calculateTotalAmount(RB_TABLE_CHECKED);
            }
        }

        if (binding.cbIsServiceCharge.isChecked()) {
            calculateTotalAmount(SERVICE_CHARGE_SELECTED);
        }

        showTotalAmountLable(totalAmountPayable);
        if (responseModel.getCarts_summary().getIsOrderEnable() == 0 || responseModel.getVenue_timing().getIs_venue_open() == 0) {
            allOrderClosed();
            if (responseModel.getVenue_timing().getIs_venue_open() == 1)
                showOutOfStockDialog(getString(R.string.hourly_order_limit_message));
        }

    }

    private void setTransactionCharge() {
        if (!TextUtils.isEmpty(responseModel.getCarts_summary().getTransaction_charge_amt()) && Double.parseDouble(responseModel.getCarts_summary().getTransaction_charge_amt()) > 0 /*&& isItemInStock*/) {
            binding.tvTvTransactionAmt.setVisibility(View.VISIBLE);
            binding.tvTransactionAmtLabel.setVisibility(View.VISIBLE);
            binding.tvTvTransactionAmt.setText(getString(R.string.pound) + responseModel.getCarts_summary().getTransaction_charge_amt());
        } else {
            binding.tvTvTransactionAmt.setVisibility(View.GONE);
            binding.tvTransactionAmtLabel.setVisibility(View.GONE);
        }

    }

    private void allOrderClosed() {
        binding.tvCheckout.setText(R.string.order_closed);
        binding.tvCheckout.setBackground(ContextCompat.getDrawable(instance, R.drawable.light_red_filled_rounded_rect));
        binding.tvCheckout.setAlpha(.5f);
        binding.tvCheckout.setClickable(false);
        binding.tvCheckout.setEnabled(false);
    }

    private void setDeliveryCharge() {
        if (!TextUtils.isEmpty(deliveryCharge) && Double.parseDouble(deliveryCharge) > 0 /*&& isItemInStock*/) {
            binding.tvHandlingChargeAmount.setVisibility(View.VISIBLE);
            binding.tvHandlingCharge.setVisibility(View.VISIBLE);
            binding.tvHandlingChargeAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(deliveryCharge)));
        } else {
            binding.tvHandlingChargeAmount.setVisibility(View.GONE);
            binding.tvHandlingCharge.setVisibility(View.GONE);
        }
    }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        switch (checkedId) {
            case R.id.rb_standard:
                setTipAdapter();
                serviceChargeTipVisibilityGone(getString(R.string.home_delivery));
                if (responseModel != null) {
                    double movAmt = Double.parseDouble(responseModel.getCarts_summary().getHome_delivery_mov()) - Double.parseDouble(responseModel.getCarts_summary().getGrand_total());
                    double freeAmt = Double.parseDouble(responseModel.getCarts_summary().getFree_delivery()) - Double.parseDouble(responseModel.getCarts_summary().getGrand_total());

                    if (Double.parseDouble(responseModel.getCarts_summary().getHome_delivery_mov()) > Double.parseDouble(responseModel.getCarts_summary().getGrand_total())) {
                        binding.rbStandard.setChecked(false);
                        DialogUtils.showMinValueDialog(instance, "Spent " + getString(R.string.pound) + String.format("%.2f", movAmt) + " " + getString(R.string.more_for_home_deliver), new OnDialogClickListener() {
                            @Override
                            public void onPositiveClick() {

                            }

                            @Override
                            public void onNegativeClick() {

                            }
                        });
                    } else {
                        binding.rbStandard.setTextColor(getResources().getColor(R.color.app_header_color));
                        binding.rbClickNCollect.setTextColor(getResources().getColor(R.color.color_black));
                        binding.rbTable.setTextColor(getResources().getColor(R.color.color_black));

                        binding.rbStandard.setChecked(true);
                        binding.rbStandard.setClickable(true);
                        binding.rbStandard.setEnabled(true);
                        binding.rlMyCartAddress.setVisibility(View.VISIBLE);
                        deliveryMethod = getString(R.string.home_delivery);
                        if (myCartList != null && myCartList.size() > 0) {
                            showCustAddress(customerAddress);

                            if (Double.parseDouble(responseModel.getCarts_summary().getGrand_total())
                                    < Double.parseDouble(responseModel.getCarts_summary().getFree_delivery())) {

                                calculateTotalAmount(PAID_HOME_DELIVERY);

                                DialogUtils.showMinValueDialog(instance, "Spent " + getString(R.string.pound) + String.format("%.2f", freeAmt) + " " + getString(R.string.more_for_free_deliver), new OnDialogClickListener() {
                                    @Override
                                    public void onPositiveClick() {

                                    }

                                    @Override
                                    public void onNegativeClick() {

                                    }
                                });

                            } else {

                                calculateTotalAmount(FREE_HOME_DELIVERY);
                            }
                            setTax();
                            showTotalAmountLable(totalAmountPayable);
                            binding.tvTipAmount.setText(getString(R.string.pound) + String.format("%.2f", tipAmount));
                            binding.tvTipAmountShow.setText(getString(R.string.pound) + String.format("%.2f", tipAmount));

                            setDeliveryCharge();

                        }
                    }

                }

                break;

            case R.id.rb_click_n_collect:
                setTipAdapter();

                binding.rlMyCartAddress.setVisibility(View.VISIBLE);
                deliveryMethod = getString(R.string.click_n_collect);
                serviceChargeTipVisibilityGone(deliveryMethod);
                if (myCartList != null && myCartList.size() > 0) {
                    showVenueAddress();
                    calculateTotalAmount(RB_CLICK_COLLECT);
                    showTotalAmountLable(totalAmountPayable);
                    binding.rbClickNCollect.setTextColor(getResources().getColor(R.color.app_header_color));
                    binding.rbStandard.setTextColor(getResources().getColor(R.color.color_black));
                    binding.rbTable.setTextColor(getResources().getColor(R.color.color_black));
                    setDeliveryCharge();
                }
                break;
            case R.id.rb_table:
                if (responseModel.getCarts_summary().getIs_service_charge() == 1 && Double.parseDouble(responseModel.getCarts_summary().getService_charge()) > 0) {
                    binding.tvServiceChargeAmt.setVisibility(View.VISIBLE);
                    binding.tvServiceChargeLbl.setVisibility(View.VISIBLE);
                    binding.cbIsServiceCharge.setChecked(true);
                    binding.cbIsServiceCharge.setVisibility(View.GONE);
                } else if (responseModel.getCarts_summary().getIs_service_charge() == 0 && Double.parseDouble(responseModel.getCarts_summary().getService_charge()) > 0) {
                    binding.cbIsServiceCharge.setVisibility(View.VISIBLE);
                } else {
                    binding.cbIsServiceCharge.setVisibility(View.GONE);
                    binding.tvServiceChargeAmt.setVisibility(View.GONE);
                    binding.tvServiceChargeLbl.setVisibility(View.GONE);
                }

                binding.tvAsapCollectio.setVisibility(View.GONE);
                binding.tvAsap.setVisibility(View.GONE);
                binding.rlTip.setVisibility(View.VISIBLE);

                binding.rlMyCartAddress.setVisibility(View.GONE);
                deliveryMethod = getString(R.string.reservation);

                if (myCartList != null && myCartList.size() > 0) {
                    showVenueAddress();
                    defautDeliveryCharge();

                    binding.rbClickNCollect.setTextColor(getResources().getColor(R.color.color_black));
                    binding.rbStandard.setTextColor(getResources().getColor(R.color.color_black));
                    binding.rbTable.setTextColor(getResources().getColor(R.color.app_header_color));

                    calculateTotalAmount(RB_TABLE_SELECTED);
                    setTax();
                    showTotalAmountLable(totalAmountPayable);
                    binding.tvServiceChargeAmt.setText(getString(R.string.pound) + String.format("%.2f", serviceCharge));

                    setDeliveryCharge();
                }
                break;
        }
    }

    @Override
    public void onDrawerSelect(int position, int selectedTip) {
        if (selectedTip == STORE) {  /// ENTERED IN VENUE SELECTED PART
            openVenue(position);
        } else {   /// ENTERED IN TIP SELECTED PART
            if (selectedTip == 0) {
                binding.tvTipAmount.setVisibility(View.GONE);
                binding.tvTipPercent.setVisibility(View.GONE);
                binding.tvTipAmountShow.setVisibility(View.GONE);
                binding.tvTipLabel.setVisibility(View.GONE);
                selectedtTip = selectedTip;
                totalAmountPayable = String.valueOf(Double.parseDouble(totalAmountPayable) - tipAmount);
                tipAmount = 0.0;

            } else {
                binding.tvTipAmount.setVisibility(View.VISIBLE);
                binding.tvTipPercent.setVisibility(View.VISIBLE);
                binding.tvTipAmountShow.setVisibility(View.VISIBLE);
                binding.tvTipLabel.setVisibility(View.VISIBLE);
                selectedtTip = selectedTip;
                totalAmountPayable = String.valueOf(Double.parseDouble(totalAmountPayable) - tipAmount);
                tipAmount = Double.parseDouble(responseModel.getCarts_summary().getSub_total()) * selectedTip / 100;
                totalAmountPayable = String.valueOf(Double.parseDouble(totalAmountPayable) + tipAmount);
            }
            binding.tvTipPercent.setText(tipList.get(position).percent + getString(R.string.percent));
            binding.tvTipAmount.setText(getString(R.string.pound) + String.format("%.2f", tipAmount));
            binding.tvTipAmountShow.setText(getString(R.string.pound) + String.format("%.2f", tipAmount));

            showTotalAmountLable(totalAmountPayable);

        }

    }

    private void openVenue(int pos) {
        Intent topStore = new Intent(instance, VenueDetailActivity.class);
        topStore.putExtra(STORE_ID, myCartList.get(pos).getVenue_id());
        topStore.putExtra(CATEGORY_ID, "");
        topStore.putExtra(FROM_WHERE, HOME_HOSPITALITY);
        startActivity(topStore);
    }

    private void calculateTotalAmount(int type) {
        double grandTotal;

       /* if (!isItemInStock) {
            return;
        }*/

        if (binding.rbClickNCollect.isChecked()) {
            grandTotal = Double.parseDouble(responseModel.getCarts_summary().getCollection_grand_total());
        } else {
            grandTotal = Double.parseDouble(responseModel.getCarts_summary().getGrand_total());
        }


        switch (type) {
            case SERVICE_CHARGE_SELECTED:
                serviceCharge = (Double.parseDouble(responseModel.getCarts_summary().getSub_total()) + Double.parseDouble(responseModel.getCarts_summary().getExcluded_tax())) * Double.parseDouble(responseModel.getCarts_summary().getService_charge()) / 100;

                totalAmountPayable = String.valueOf(grandTotal - enteredLoyaltyPoint + serviceCharge + tipAmount - couponPrice);
                binding.tvServiceChargeAmt.setText(getString(R.string.pound) + String.format("%.2f", serviceCharge));

                break;
            case SERVICE_CHARGE_DESELECTED:
                totalAmountPayable = String.valueOf(grandTotal - enteredLoyaltyPoint + tipAmount - couponPrice);
                break;
            case ONLOAD:
                totalAmountPayable = String.valueOf(grandTotal);
                break;
            case RB_TABLE_CHECKED:
                tipAmount = Double.parseDouble(responseModel.getCarts_summary().getSub_total()) * selectedtTip / 100;
                totalAmountPayable = String.valueOf(grandTotal + tipAmount - couponPrice);

                binding.tvTipPercent.setText(selectedtTip + getString(R.string.percent));
                binding.tvTipAmount.setText(getString(R.string.pound) + String.format("%.2f", tipAmount));
                binding.tvTipAmountShow.setText(getString(R.string.pound) + String.format("%.2f", tipAmount));
                break;
            case RB_TABLE_SELECTED:

                if (responseModel.getCarts_summary().getIs_service_charge() == 1 && Double.parseDouble(responseModel.getCarts_summary().getService_charge()) > 0) {
                    serviceCharge = (Double.parseDouble(responseModel.getCarts_summary().getSub_total()) + Double.parseDouble(responseModel.getCarts_summary().getExcluded_tax())) * Double.parseDouble(responseModel.getCarts_summary().getService_charge()) / 100;
                    totalAmountPayable = String.valueOf(grandTotal
                            - enteredLoyaltyPoint + serviceCharge - couponPrice);
                } else {
                    totalAmountPayable = String.valueOf(grandTotal - enteredLoyaltyPoint - couponPrice);
                }

                break;
            case RB_CLICK_COLLECT:
                totalAmountPayable = String.valueOf(grandTotal - enteredLoyaltyPoint - couponPrice);
                tipAmount = 0;
                selectedtTip = 0;
                defautDeliveryCharge();

                setTax();

                break;
            case PAID_HOME_DELIVERY:
                totalAmountPayable = String.valueOf(grandTotal + Double.parseDouble(responseModel.getCarts_summary().getDelivery_charges())
                        - enteredLoyaltyPoint - couponPrice);
                tipAmount = 0;
                selectedtTip = 0;
                deliveryCharge = responseModel.getCarts_summary().getDelivery_charges();

                break;
            case FREE_HOME_DELIVERY:
                totalAmountPayable = String.valueOf(grandTotal
                        - enteredLoyaltyPoint - couponPrice);
                tipAmount = 0;
                selectedtTip = 0;
                defautDeliveryCharge();
                break;
        }
    }

    private void showTotalAmountLable(String totalAmountPayable) {
        binding.tvCheckout.setText(getString(R.string.checkout) + "      " + getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
        binding.tvTotalAmount.setText(getString(R.string.pound) + String.format("%.2f", Double.parseDouble(totalAmountPayable)));
    }

    private void getCartHospitality(String type, String cartId, String quantity) {

        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            final String latitude = prefManager.getPreference(LATITUDE, "");
            final String longitude = prefManager.getPreference(LONGITUDE, "");

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<GetCartSummaryHospResponseModel> call = null;
            if (type.equals(NONE)) {
                final GetCartWithSummaryRequestModel requestModel = new GetCartWithSummaryRequestModel(prefManager.getPreference(LOGIN_ID, 0), venue_id, latitude, longitude);
                call = apiInterface.hospitality_GetCartWithSummary(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            } else if (type.equals(PLUS)) {
                final AddMobileCartQuantityRequestModel requestModel = new AddMobileCartQuantityRequestModel(PLUS, "" + prefManager.getPreference(LOGIN_ID, 0), quantity, cartId, latitude, longitude);
                call = apiInterface.updateCartQuantity(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            } else if (type.equals(MINUS)) {
                final AddMobileCartQuantityRequestModel requestModel = new AddMobileCartQuantityRequestModel(MINUS, "" + prefManager.getPreference(LOGIN_ID, 0), quantity, cartId, latitude, longitude);
                call = apiInterface.updateCartQuantity(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            } else if (type.equals(DELETE)) {
                final AddMobileCartQuantityRequestModel requestModel = new AddMobileCartQuantityRequestModel(cartId, latitude, longitude);
                call = apiInterface.deleteCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            }
            binding.llNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<GetCartSummaryHospResponseModel>() {
                @Override
                public void onResponse(Call<GetCartSummaryHospResponseModel> call, Response<GetCartSummaryHospResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            if (response.body().isStatus()) {
                                responseModel = response.body();

                                if (responseModel.getVenue_timing().getIsClose() != null && responseModel.getVenue_timing().getIsClose().equals("1")) {
                                    // clickNcollectClosed();
                                    allOrderClosed();
                                }

                                if (responseModel.getCarts_summary().getIsTableOrderAllow() == 0) {
                                    tableClosed();
                                }

                                /*if (responseModel.getCarts() != null)
                                    for (int i = 0; i < responseModel.getCarts().size(); i++) {
                                        if (responseModel.getCarts().get(i).getAvl_quantity() == 0) {
                                          //  isOutOfStock = true;
                                            showOutOfStockDialog(getString(R.string.cart_item_out_of_stock));
                                        } else {
                                            isItemInStock = true;
                                        }
                                    }*/


                                if (!type.equals(NONE)) {
                                    if (binding.rbClickNCollect.isChecked() || binding.rbStandard.isChecked() || binding.rbTable.isChecked()) {
                                        if (binding.rbStandard.isChecked()) {
                                            if (Double.parseDouble(responseModel.getCarts_summary().getGrand_total()) < Double.parseDouble(responseModel.getCarts_summary().getFree_delivery())) {
                                                deliveryCharge = responseModel.getCarts_summary().getDelivery_charges();
                                            } else {
                                                defautDeliveryCharge();
                                            }
                                        } else if (binding.rbClickNCollect.isChecked()) {
                                            defautDeliveryCharge();
                                        } else if (binding.rbTable.isChecked()) {
                                            defautDeliveryCharge();
                                        }
                                    }
                                } else {

                                    if (responseModel.getCarts_summary().getIsTableOrderAllow() == 0) {
                                        binding.rbTable.setChecked(false);
                                    } else binding.rbTable.setChecked(true);
                                }

                                if (responseModel.getCarts() != null && responseModel.getCarts().size() > 0) {

                                    binding.rlAddMoreItem.setVisibility(View.VISIBLE);
                                    binding.llNoDataFound.setVisibility(View.GONE);
                                    binding.llInfo.setVisibility(View.VISIBLE);
                                    binding.tvCheckout.setVisibility(View.VISIBLE);
                                    binding.tvProductDetails.setVisibility(View.VISIBLE);
                                    myCartList.clear();
                                    myCartList.addAll(responseModel.getCarts());
                                    setAdapter();
                                    totalLoyaltyValue = Double.parseDouble(myCartList.get(0).getTotal_loyalty_amount());
                                    binding.tvInputLoyalty.setText("You have " + totalLoyaltyValue + " loyalty points");
                                    getTodayCoupon();
                                } else {
                                    prefManager.savePreference(VENUE_ID_IN_CART, "");
                                    binding.llNoDataFound.setVisibility(View.VISIBLE);
                                    binding.llInfo.setVisibility(View.GONE);
                                    binding.tvCheckout.setVisibility(View.GONE);
                                    binding.tvProductDetails.setVisibility(View.GONE);
                                    binding.rlAddMoreItem.setVisibility(View.GONE);
                                    myCartList.clear();
                                    setAdapter();
                                }

                                if (responseModel.getDefaultCards() != null && responseModel.getDefaultCards().size() > 0) {
                                    myCardList.addAll(responseModel.getDefaultCards());
                                    setCardDetail(myCardList.get(0));
                                } else {
                                    binding.rlCards.setVisibility(View.GONE);
                                    //   binding.rlAddNewCard.setVisibility(View.GONE);
                                }
                                /*---------------------- Cart details , delivery n collection time ------------------------------------------*/
                                collectionTimeGap.addAll(getDateGapDelivery(responseModel.getVenue_timing().getCollection_opening_time(), responseModel.getVenue_timing().getCollection_closing_time(), Integer.parseInt(responseModel.getVenue_timing().getCollection_timing_slot_gap())));
                                deliveryTimeGap.addAll(getDateGapDelivery(responseModel.getVenue_timing().getDelivery_opening_time(), responseModel.getVenue_timing().getDelivery_closing_time(), Integer.parseInt(responseModel.getVenue_timing().getTiming_slot_gap())));

                                if (responseModel.getCarts_summary() != null) {
                                    showCarts_summary(responseModel.getCarts_summary());
                                }

                                customerAddress = responseModel.getCust_address();
                                showCustAddress(customerAddress);


                            } else {
                                showSnackBar(binding.getRoot(), response.body().getMessage());
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

                    } catch (
                            Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetCartSummaryHospResponseModel> call, Throwable t) {
                    // binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void defautDeliveryCharge() {
        deliveryCharge = DEFAULT_DELIVERY_CHARGE;
    }

    private void clickNcollectClosed() {
        binding.rbClickNCollect.setChecked(false);
        binding.rbClickNCollect.setClickable(false);
        binding.rbClickNCollect.setEnabled(false);
        binding.rbClickNCollect.setAlpha(.5f);
        binding.tvCollectionTime.setAlpha(.5f);
    }

    private void tableClosed() {
        binding.rbTable.setChecked(false);
        binding.rbTable.setClickable(false);
        binding.rbTable.setEnabled(false);
        binding.rbTable.setAlpha(.5f);
        binding.etTableNo.setVisibility(View.GONE);
    }

    private void setCardDetail(GetCartSummaryHospResponseModel.DefaultCardsBean defaultCardsBean) {
        binding.tvCards.setText(defaultCardsBean.getCard_number());
        if (defaultCardsBean.getGateway().equals("active")) {
            payment_gatway = PAYMENT_GATEWAY_ACTIVE_SAVED_CARD;
            cardId = HelperClass.toBase64(defaultCardsBean.getStripe_card_id());
        } else {
            payment_gatway = defaultCardsBean.getGateway();
            cardId = defaultCardsBean.getStripe_card_id();
        }

        if (defaultCardsBean.getCard_type() == null) {
            Glide.with(instance).load(R.drawable.ic_app_icon).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);
            return;
        }
        if (defaultCardsBean.getCard_type().equalsIgnoreCase(getString(R.string.visa))) {


            Glide.with(instance).load(R.drawable.ic_visa).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);

        } else if (defaultCardsBean.getCard_type().equalsIgnoreCase(getString(R.string.Visa_Credit))) {
            Glide.with(instance).load(R.drawable.ic_visa).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);

        } else if (defaultCardsBean.getCard_type().equalsIgnoreCase(getString(R.string.mastercard))) {
            Glide.with(instance).load(R.drawable.cio_ic_mastercard).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);
        } else if (defaultCardsBean.getCard_type().equalsIgnoreCase(getString(R.string.maestro_card))) {
            Glide.with(instance).load(R.drawable.ic_maestro).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);
        }
    }

    public void checkOut() {
        CheckoutCartHospitalityRequestModel requestModel = null;
        List<CheckoutCartHospitalityRequestModel.ProductsBean> productList = new ArrayList<>();
        CheckoutCartHospitalityRequestModel.ProductsBean productsBean;
        for (int i = 0; i < myCartList.size(); i++) {
            double tax_applied;
            String included_tax;
            if (deliveryMethod.equals(getString(R.string.click_n_collect)) && myCartList.get(i).getTakeaway_with_tax() == 0) {
                tax_applied = 0;
                included_tax = "0";
            } else {
                if (myCartList.get(i).getTax_type() == 1) {
                    tax_applied = Double.parseDouble("" + myCartList.get(i).getTax_applied());
                    included_tax = "0";
                } else {
                    tax_applied = 0;
                    included_tax = myCartList.get(i).getTax_applied();
                }
            }
            productsBean = new CheckoutCartHospitalityRequestModel.ProductsBean(myCartList.get(i).getQuantities(), myCartList.get(i).getProduct_id(),
                    String.valueOf(myCartList.get(i).getModifier_id()), binding.cbDeliveryAddress.getText().toString(),
                    binding.cbDeliveryAddress.getText().toString(), myCartList.get(i).getMerchant_id(),
                    String.valueOf(myCartList.get(i).getVenue_id()),
                    myCartList.get(i).getModifier_name(), Double.parseDouble(myCartList.get(i).getSelling_price()),   ///selling price
                    Double.parseDouble(myCartList.get(i).getBuy_price()),
                    Double.parseDouble(myCartList.get(i).getDiscount_applied()),
                    Long.parseLong("" + myCartList.get(i).getOffer_id()),
                    tax_applied, TextUtils.isEmpty(myCartList.get(i).getTax_id()) ? 0 : Integer.parseInt(myCartList.get(i).getTax_id()),/*taxId*/
                    included_tax,
                    myCartList.get(i).getNet_amount(), "", deliveryMethod, myCartList.get(i).getReturn_day(), 0,
                    myCartList.get(i).getCaseqty(), "",
                    responseModel.getCarts().get(i).getAdd_on() != null ? new Gson().toJson(responseModel.getCarts().get(i).getAdd_on()) : "",
                    String.valueOf(myCartList.get(i).getTax_type()), myCartList.get(i).getAllergens().isEmpty() ? null : String.join(", ", myCartList.get(i).getAllergens()),
                    myCartList.get(i).getmItem_message());

            productList.add(productsBean);
        }
        double total_tax;
        if (deliveryMethod.equals(getString(R.string.click_n_collect))) {
            total_tax = Double.parseDouble(responseModel.getCarts_summary().getCollection_tax());
        } else {
            total_tax = Double.parseDouble(responseModel.getCarts_summary().getTax());
        }
        requestModel = new CheckoutCartHospitalityRequestModel(myCartList.get(0).getReservation_id(), payment_gatway, toBase64(cardNumber), toBase64(cardExpiryMonth),
                toBase64(cardExpiryYear), toBase64(cardCVV), String.valueOf(myCartList.get(0).getMerchant_id()),
                String.valueOf(myCartList.get(0).getVenue_id()), CUSTOMER_TYPE, PAYMENT_MODE, cardId, "", addressId,
                myCartList.size(), Double.parseDouble("" + responseModel.getCarts_summary().getCart_total()),
                Double.parseDouble(responseModel.getCarts_summary().getDiscount()), total_tax,
                String.valueOf(Double.parseDouble(totalAmountPayable) + enteredLoyaltyPoint), deliveryCharge,
                enteredLoyaltyPoint, IS_GIFT, REORDER_STATUS, deliveryMethod, "0", "",
                responseModel.getCarts_summary().getTransaction_charge_amt(), responseModel.getCarts_summary().getTransaction_charge(), postal_code,
                address_one, address_two, address_three, isSaveCard, shippingId,
                IS_HOSPITALITY, app_type, "" + serviceCharge, String.valueOf(tipAmount),
                binding.rbTable.isChecked() ? binding.etTableNo.getText().toString() : "", deliveryGap == null ? currentTime + " ASAP" : currentTime + " " + deliveryGap,
                productList, binding.etMessage.getText().toString().trim(), couponCode, String.valueOf(couponPrice));
        final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
        if (dialog != null)
            dialog.show();
        if (isInternetOn(this)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CheckoutCartHospitalityResponseModel> call = apiInterface.saveEcommOrderHospitality(
                    prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<CheckoutCartHospitalityResponseModel>() {
                @Override
                public void onResponse(Call<CheckoutCartHospitalityResponseModel> call, Response<CheckoutCartHospitalityResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            cardId = "";
                            CheckoutCartHospitalityResponseModel checkOutResponseModel = response.body();

                            if (checkOutResponseModel.isStatus()) {

                                DialogUtils.orderSuccesDialog(MyCartHospitalityActivity.this, checkOutResponseModel.getMessage(), true,
                                        String.valueOf(checkOutResponseModel.getOrder().getNet_amount()),
                                        prefManager.getPreference(EMAIL, ""), checkOutResponseModel.getOrder().getUnique_code(),
                                        myCartList.get(0).getVenue_images(), couponPrice, new OnDialogClickListener() {
                                            @Override
                                            public void onPositiveClick() {
                                                prefManager.savePreference(VENUE_ID_IN_CART, "");
                                                finish();
                                                Intent intent = new Intent(instance, MyOrderActivity.class);
                                                startActivity(intent);
                                            }

                                            @Override
                                            public void onNegativeClick() {

                                            }
                                        }, checkOutResponseModel.getOrder().getLoyelty_consumed(), enteredLoyaltyPoint);

                            } else {

                                DialogUtils.orderSuccesDialog(MyCartHospitalityActivity.this, checkOutResponseModel.getMessage(), false,
                                        totalAmountPayable, prefManager.getPreference(EMAIL, ""), "", "", couponPrice, new OnDialogClickListener() {
                                            @Override
                                            public void onPositiveClick() {

                                            }

                                            @Override
                                            public void onNegativeClick() {

                                            }
                                        }, checkOutResponseModel.getOrder() == null ? 0 : checkOutResponseModel.getOrder().getLoyelty_consumed(), enteredLoyaltyPoint);
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
                public void onFailure(Call<CheckoutCartHospitalityResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void updateCheckedUi() {
        closeLoyalty();
        cancelCoupon();
    }

    private void AddCoupon(CouponResponseModel.CouponDataBean model) {
        if (Double.parseDouble(model.getMinimum_spend()) > Double.parseDouble(responseModel.getCarts_summary().getCart_total())) {
            Toast.makeText(instance, "Cart amount should be " + getString(R.string.pound) + model.getMinimum_spend() + " to use this coupon", Toast.LENGTH_SHORT).show();
            return;
        }
        DialogUtils.dialogCouponApply(instance, model.getCoupon_code(), new OnDialogClickListener() {
            @Override
            public void onPositiveClick() {
                binding.tvCouponAppliedLbl.setVisibility(View.VISIBLE);
                binding.tvCouponAppliedAmount.setVisibility(View.VISIBLE);
                binding.ivCancelCouponApplied.setVisibility(View.VISIBLE);

                if (model.getOffer_type() == 1) {  // percent
                   /* getString(R.string.pound) + model.getMinimum_spend() + ". Max discount " +
                            getString(R.string.pound) + model.getMax_discount() + ".");*/
                    couponPrice = (Double.parseDouble(responseModel.getCarts_summary().getCart_total()) * Double.parseDouble(model.getOffer_amount())) / 100;
                    binding.tvCouponAppliedAmount.setText(getString(R.string.pound) + String.format("%.2f", couponPrice));
                    binding.tvCouponAppliedLbl.setText(getString(R.string.coupon_applied) + " (" + model.getCoupon_code() + ")");
                } else {  // pound
                   /* getString(R.string.pound) + model.getMinimum_spend() + ". Max discount " +
                            getString(R.string.pound) + model.getMax_discount() + ".");*/
                    couponPrice = Double.parseDouble(model.getOffer_amount());
                    binding.tvCouponAppliedAmount.setText(getString(R.string.pound) + String.format("%.2f", couponPrice));
                    binding.tvCouponAppliedLbl.setText(getString(R.string.coupon_applied) + " (" + model.getCoupon_code() + ")");
                }
                if (Double.parseDouble(model.getMax_discount()) >= couponPrice) {
                    calculatePriceAfterCoupon(couponPrice);
                } else calculatePriceAfterCoupon(Double.parseDouble(model.getMax_discount()));

                couponCode = model.getCoupon_code();
            }

            @Override
            public void onNegativeClick() {

            }
        });
    }

    private void AddManualyCoupon(double coupon_amount, String coupon_code) {
        DialogUtils.dialogCouponApply(instance, coupon_code, new OnDialogClickListener() {
            @Override
            public void onPositiveClick() {
                binding.tvCouponAppliedLbl.setVisibility(View.VISIBLE);
                binding.tvCouponAppliedAmount.setVisibility(View.VISIBLE);
                binding.ivCancelCouponApplied.setVisibility(View.VISIBLE);

                couponPrice = coupon_amount;
                binding.tvCouponAppliedAmount.setText(getString(R.string.pound) + String.format("%.2f", couponPrice));
                binding.tvCouponAppliedLbl.setText(getString(R.string.coupon_applied) + " (" + coupon_code + ")");
                calculatePriceAfterCoupon(couponPrice);
                couponCode = coupon_code;
            }

            @Override
            public void onNegativeClick() {

            }
        });
    }

    private void calculatePriceAfterCoupon(double couponPrice) {
        totalAmountPayable = String.valueOf(Double.parseDouble(totalAmountPayable) - couponPrice);
        showTotalAmountLable(totalAmountPayable);
    }

    private void cancelCoupon() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.tvCouponAppliedLbl.setVisibility(View.GONE);
                binding.tvCouponAppliedAmount.setVisibility(View.GONE);
                binding.ivCancelCouponApplied.setVisibility(View.GONE);

                totalAmountPayable = String.valueOf(Double.parseDouble(totalAmountPayable) + couponPrice);
                couponPrice = 0.0;
                couponCode = "";

                showTotalAmountLable(totalAmountPayable);
            }
        });

    }

    private void applyCoupon(String reservation_id, String venueId, String coupon_code, String customer_id, String is_hospitality, String deliveryCharge) {
        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            CouponRequestModel couponRequestModel = new CouponRequestModel(reservation_id, venueId, coupon_code, customer_id, is_hospitality, deliveryCharge);

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CouponResponseModel> call = apiInterface.applyCoupon(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""),
                    couponRequestModel);
            call.enqueue(new Callback<CouponResponseModel>() {
                @Override
                public void onResponse(Call<CouponResponseModel> call, Response<CouponResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {
                            CouponResponseModel loyaltyResponseModel = response.body();
                            if (loyaltyResponseModel != null) {
                                if (loyaltyResponseModel.isStatus()) {
                                    // isLoading = false;
                                    cancelCoupon();
                                    binding.etCouponCode.setText("");
                                    AddManualyCoupon(loyaltyResponseModel.getCoupon_amount(), coupon_code);

                                } else {
                                    showSnackBar(binding.getRoot(), loyaltyResponseModel.getMessage());
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
                    }
                }

                @Override
                public void onFailure(Call<CouponResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }

    public void showLoyaltyDialog() {

        final Dialog dialog = new Dialog(instance, android.R.style.Theme_Translucent_NoTitleBar);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_enter_loyalty);


        TextView tvMessage = dialog.findViewById(R.id.tv_total_loyalty);
        final EditText etLoyalty = dialog.findViewById(R.id.et_loyalty);
        etLoyalty.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
        // imm.showSoftInput(etLoyalty, InputMethodManager.SHOW_FORCED);

        tvMessage.setText(getString(R.string.total_loyalty_amount) + String.format("%.2f", totalLoyaltyValue));

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
                double grandTotal;
                if (binding.rbClickNCollect.isChecked()) {
                    grandTotal = Double.parseDouble(responseModel.getCarts_summary().getCollection_grand_total());
                } else {
                    grandTotal = Double.parseDouble(responseModel.getCarts_summary().getGrand_total());
                }
                if (myCartList.size() > 0 && !TextUtils.isEmpty(etLoyalty.getText().toString()))
                    if (totalLoyaltyValue > 0 && totalLoyaltyValue >= Double.parseDouble(etLoyalty.getText().toString())) {

                        if (Double.parseDouble(etLoyalty.getText().toString()) > 0.09 && Double.parseDouble(etLoyalty.getText().toString()) <= Double.parseDouble(String.format("%.2f", (grandTotal)))) {
                            enteredLoyaltyPoint = Double.parseDouble(etLoyalty.getText().toString());
                            binding.tvSelectedValue.setVisibility(View.VISIBLE);
                            binding.ivCancelLoyalty.setVisibility(View.VISIBLE);
                            binding.tvSelectedValue.setText(getString(R.string.loyalty_value) + String.format("%.2f", enteredLoyaltyPoint));
                            binding.tvAppliedLoyaltyVal.setVisibility(View.VISIBLE);
                            binding.tvAppliedLoyalty.setVisibility(View.VISIBLE);
                            binding.tvAppliedLoyaltyVal.setText(String.format("%.2f", enteredLoyaltyPoint));
                            //////////////////////////////////////////////////////////////////////
                            if (deliveryMethod.equals(getString(R.string.home_delivery))) {
                                if (grandTotal < Double.parseDouble(responseModel.getCarts_summary().getFree_delivery())) {

                                    if (shippingId.equals("")) {
                                        deliveryCharge = responseModel.getCarts_summary().getDelivery_charges();
                                    }

                                    totalAmountPayable = String.valueOf(Double.parseDouble(String.format("%.2f", grandTotal
                                            + Double.parseDouble(deliveryCharge)
                                            - enteredLoyaltyPoint + tipAmount - couponPrice)));


                                } else {

                                    if (shippingId.equals("")) {
                                        defautDeliveryCharge();
                                        totalAmountPayable = String.valueOf(Double.parseDouble(String.format("%.2f", grandTotal
                                                - enteredLoyaltyPoint + tipAmount - couponPrice)));
                                    } else {
                                        totalAmountPayable = String.valueOf(Double.parseDouble(String.format("%.2f", grandTotal
                                                + Double.parseDouble(deliveryCharge)
                                                - enteredLoyaltyPoint + tipAmount - couponPrice)));
                                    }

                                }

                                showTotalAmountLable(totalAmountPayable);

                                setDeliveryCharge();

                            } else if (deliveryMethod.equals(getString(R.string.click_n_collect))) {
                                defautDeliveryCharge();
                                totalAmountPayable = String.valueOf(Double.parseDouble(String.format("%.2f", grandTotal
                                        - enteredLoyaltyPoint + tipAmount - couponPrice)));
                                showTotalAmountLable(totalAmountPayable);
                                setDeliveryCharge();

                            } else {
                                totalAmountPayable = String.valueOf(Double.parseDouble(String.format("%.2f", grandTotal
                                        - enteredLoyaltyPoint + tipAmount + serviceCharge - couponPrice)));

                                showTotalAmountLable(totalAmountPayable);
                            }
                            //////////////////////////////////////////////////////////////////////////////

                            dialog.dismiss();
                        } else toast(instance, getString(R.string.loyalty_validation));

                    } else toast(instance, getString(R.string.enter_valid_loyalty));
            }
        });

        dialog.findViewById(R.id.btnCancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

    private void closeLoyalty() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                binding.tvSelectedValue.setVisibility(View.GONE);
                binding.ivCancelLoyalty.setVisibility(View.GONE);
                binding.tvAppliedLoyaltyVal.setVisibility(View.GONE);
                binding.tvAppliedLoyaltyVal.setText("");
                binding.tvAppliedLoyalty.setVisibility(View.GONE);
                totalAmountPayable = String.valueOf(Double.parseDouble(totalAmountPayable) + enteredLoyaltyPoint);
                enteredLoyaltyPoint = 0.0;

                showTotalAmountLable(totalAmountPayable);
            }
        });

    }

    private void setTax() {

        if (binding.rbClickNCollect.isChecked()) {
            if (!TextUtils.isEmpty(responseModel.getCarts_summary().getCollection_exTax()) && Double.parseDouble(responseModel.getCarts_summary().getCollection_exTax()) > 0) {
                binding.tvTaxAmount.setVisibility(View.VISIBLE);
                binding.tvTax.setVisibility(View.VISIBLE);
                binding.tvTaxAmount.setText(getString(R.string.pound) + responseModel.getCarts_summary().getCollection_exTax());
            } else {
                binding.tvTaxAmount.setVisibility(View.GONE);
                binding.tvTax.setVisibility(View.GONE);
            }
        } else {
            if (!TextUtils.isEmpty(responseModel.getCarts_summary().getExcluded_tax()) && Double.parseDouble(responseModel.getCarts_summary().getExcluded_tax()) > 0) {
                binding.tvTaxAmount.setVisibility(View.VISIBLE);
                binding.tvTax.setVisibility(View.VISIBLE);
                binding.tvTaxAmount.setText(getString(R.string.pound) + responseModel.getCarts_summary().getExcluded_tax());
            } else {
                binding.tvTaxAmount.setVisibility(View.GONE);
                binding.tvTax.setVisibility(View.GONE);
            }
        }
    }

    private void serviceChargeTipVisibilityGone(String type) {

        if (type.equalsIgnoreCase(getString(R.string.home_delivery))) {
            binding.tvAsap.setVisibility(View.VISIBLE);
            binding.tvAsapCollectio.setVisibility(View.GONE);
        } else {
            binding.tvAsapCollectio.setVisibility(View.VISIBLE);
            binding.tvAsap.setVisibility(View.GONE);
        }
        binding.cbIsServiceCharge.setChecked(false);
        binding.tvServiceChargeAmt.setVisibility(View.GONE);
        binding.tvServiceChargeLbl.setVisibility(View.GONE);
        binding.cbIsServiceCharge.setVisibility(View.GONE);
        binding.tvTipAmount.setVisibility(View.GONE);
        binding.tvTipPercent.setVisibility(View.GONE);
        binding.tvTipLabel.setVisibility(View.GONE);
        binding.tvTipAmountShow.setVisibility(View.GONE);
        binding.rlTip.setVisibility(View.GONE);


    }

    private void showCustAddress(GetCartSummaryHospResponseModel.CustAddressBean cust_address) {
        if (cust_address != null) {
            addressId = String.valueOf(cust_address.getId());
            String address = "" + cust_address.getFlat() + cust_address.getArea() + "," + cust_address.getCity() + " ," + cust_address.getLandmark() + "," + cust_address.getState() + " " + cust_address.getPincode(); ///  + cust_address.getCountry()
            address = address.replace("null", "");
            address = address.replace(",,", ",");
            binding.tvDeliverAddress.setText(getString(R.string.deliver_address));
            binding.tvChangeAddress.setText(getString(R.string.change_address));
            binding.cbDeliveryAddress.setText(address);
            postal_code = cust_address.getPincode();

        } else {
            binding.tvDeliverAddress.setText(getString(R.string.deliver_address));
            binding.tvChangeAddress.setText(getString(R.string.add_address));
            binding.cbDeliveryAddress.setText("");
        }
    }

    private void showVenueAddress() {
        String address = getString(R.string.not_given);
        if (myCartList != null) {
            address = "" + myCartList.get(0).getAddress_1();
        }
        binding.tvChangeAddress.setText(getString(R.string.get_direction));
        binding.tvDeliverAddress.setText(getString(R.string.venue_address));
        binding.cbDeliveryAddress.setText(address);
    }

    private boolean isValid() {
        try {
            if (customerAddress == null && deliveryMethod.equals(getString(R.string.home_delivery))) {
                Intent intent = new Intent(this, AddressActivity.class);
                intent.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(intent, OPEN_CHANGE_ADDRESS_ACTIVITY);
                return false;
            } else if (cardId.equals("")) {
                isCardPresent();
                return false;
            }

        } catch (Exception exc) {
            log("Validation exc", "" + exc.getMessage());
        }
        return true;
    }

    private void isCardPresent() {
        if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_STRIPE)) {
            if (prefManager.getPreference(CUSTOMER_ORDER, -1) == 0) {
                Intent setPin = new Intent(this, CreatePinActivity.class);
                setPin.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(setPin, OPEN_PIN_CREATE_ACTIVITY);
            } else {
                Intent stamp = new Intent(this, CardActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(stamp, OPEN_PAYMENT_DETAIL_ACTIVITY);
            }
        } else if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
            if (prefManager.getPreference(CUSTOMER_ORDER, -1) == 0) {   // check whether pin is already set or not
                Intent setPin = new Intent(instance, CreatePinActivity.class);
                setPin.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(setPin, OPEN_PIN_CREATE_ACTIVITY);
            } else {
                Intent stamp = new Intent(this, ActiveCardActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                startActivityForResult(stamp, OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY);
            }
        } else if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_PAYDOO)) {

            showSnackBar(binding.getRoot(), "PAYDOO PAYMENT GATEWAY IS NOT SUPPORTED BY APP YET!");

        } else {
            showSnackBar(binding.getRoot(), myCartList.get(0).getPayment_gatway() + " :Unsupported Payment Gateway Detected!");
        }
    }

    private boolean validateDeliveryMethod() {
        if (deliveryMethod.equals("")) {
            showSnackBar(binding.getRoot(), getString(R.string.select_delivery_type));
            return false;
        }/* else if (isOutOfStock) {
            showOutOfStockDialog(getString(R.string.cart_item_out_of_stock));
            // showSnackBar(binding.getRoot(), getString(R.string.cart_item_out_of_stock));
            return false;
        } */ else if (deliveryMethod.equals(getString(R.string.home_delivery))) {
            if (Double.parseDouble(responseModel.getCarts_summary().getGrand_total()) < Double.parseDouble(responseModel.getCarts_summary().getHome_delivery_mov())) {
                showSnackBar(binding.getRoot(), getString(R.string.cart_value_minimum));
                return false;
            }
        } else if (deliveryMethod.equals(getString(R.string.reservation))) {
            if (binding.etTableNo.getText().toString().isEmpty()) {
                showSnackBar(binding.getRoot(), getString(R.string.enter_table_number));
                binding.etTableNo.requestFocus();
                HelperClass.openKeyboard(instance, binding.etTableNo);
                return false;
            }
        }
        return true;
    }

    private void showOutOfStockDialog(String message) {

        DialogUtils.showAlertDialogWithSingleButton(instance, message, new OnDialogClickListener() {
            @Override
            public void onPositiveClick() {

            }

            @Override
            public void onNegativeClick() {

            }
        });
    }

    private void showVenueClosedDialog(String message) {

        DialogUtils.showAlertDialogWithSingleButton(instance, message, new OnDialogClickListener() {
            @Override
            public void onPositiveClick() {

            }

            @Override
            public void onNegativeClick() {

            }
        });
    }

    public void setSpinnerFordeliveryGap() {
        popupWindow = new ListPopupWindow(instance);
        ArrayAdapter<String> adapter = new ArrayAdapter(instance, android.R.layout.simple_spinner_dropdown_item, deliveryTimeGap);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.tvAsap);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (deliveryTimeGap.size() > 1 && position == 0) {
                    binding.tvAsap.setText(deliveryTimeGap.get(position));
                    deliveryGap = deliveryTimeGap.get(1);
                } else {
                    binding.tvAsap.setText(deliveryTimeGap.get(position));
                    deliveryGap = deliveryTimeGap.get(position);
                }


                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }

    public void setSpinnerForCollectionGap() {
        popupWindow = new ListPopupWindow(instance);
        ArrayAdapter<String> adapter = new ArrayAdapter(instance, android.R.layout.simple_spinner_dropdown_item, collectionTimeGap);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.tvAsapCollectio);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                if (collectionTimeGap.size() > 1 && position == 0) {
                    binding.tvAsap.setText(collectionTimeGap.get(position));
                    deliveryGap = collectionTimeGap.get(1);
                } else {
                    binding.tvAsapCollectio.setText(collectionTimeGap.get(position));
                    deliveryGap = collectionTimeGap.get(position);
                }

                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }


    public void setSpinnerForCards() {

        List<String> cards = new ArrayList<>();
        for (int i = 0; i < myCardList.size(); i++) {
            cards.add(myCardList.get(i).getCard_number());
        }
        cards.add("ADD NEW CARD");
        popupWindow = new ListPopupWindow(instance);
        ArrayAdapter<String> adapter = new ArrayAdapter(instance, android.R.layout.simple_spinner_dropdown_item, cards);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.rlCards);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (position == cards.size() - 1) {
                    cardId = "";
                    addNewCard();
                } else {
                    setCardDetail(myCardList.get(position));
                }

                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }


    private void addNewCard() {

        if (prefManager.getPreference(CUSTOMER_ORDER, -1) == 0) {   // check whether pin is already set or not
            Intent setPin = new Intent(instance, CreatePinActivity.class);
            setPin.putExtra(FROM_WHERE, getString(R.string.cart));
            startActivityForResult(setPin, OPEN_PIN_CREATE_ACTIVITY);
        } else {
            if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_STRIPE)) {
                Intent stamp = new Intent(this, AddNewCardOnCheckoutActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.stripe));
                startActivityForResult(stamp, ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE);
            } else if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
                Intent stamp = new Intent(this, AddNewCardOnCheckoutActivity.class);
                stamp.putExtra(FROM_WHERE, getString(R.string.active));
                startActivityForResult(stamp, ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE);

            } else if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_PAYDOO)) {
                showSnackBar(binding.getRoot(), "PAYDOO PAYMENT GATEWAY IS NOT SUPPORTED BY APP YET!");
            }
        }


    }


    @Override
    public void onRefresh() {
    }

    @Override
    public void onIncrease(int position, String count) {
        updateCheckedUi();
       /* isOutOfStock = false;
        isItemInStock = false;*/
        getCartHospitality(PLUS, String.valueOf(myCartList.get(position).getId()), count);
        // updateCartHospitality(PLUS, String.valueOf(myCartList.get(position).getId()), count);
    }

    @Override
    public void onDecrease(int position, String count) {
        updateCheckedUi();
      /*  isItemInStock = false;
        isOutOfStock = false;*/
        getCartHospitality(MINUS, String.valueOf(myCartList.get(position).getId()), count);
        // updateCartHospitality(MINUS, String.valueOf(myCartList.get(position).getId()), count);
    }

    @Override
    public void onDelete(int position) {
        updateCheckedUi();
      /*  isOutOfStock = false;
        isItemInStock = false;*/
        getCartHospitality(DELETE, String.valueOf(myCartList.get(position).getId()), "");
        //  deletetCartHospitality(String.valueOf(myCartList.get(position).getId()));
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == OPEN_CHANGE_ADDRESS_ACTIVITY && resultCode == RESULT_OK && data != null) {

            customerAddress = new GetCartSummaryHospResponseModel.CustAddressBean();
            addressId = String.valueOf(data.getIntExtra(ADDRESS_ID, 0));
            customerAddress.setId(data.getIntExtra(ADDRESS_ID, 0));
            customerAddress.setCity(data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));

            binding.cbDeliveryAddress.setText(data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));

        } else if (requestCode == OPEN_LOYALTY_STAMP_VOUCHER_ACTIVITY && resultCode == RESULT_OK && data != null) {

            String coupon = data.getStringExtra(LOYA_STAMP_VOUC_ACTIVITY_CALLBACK);
            binding.tvSelectedValue.setVisibility(View.VISIBLE);
            binding.ivCancelLoyalty.setVisibility(View.VISIBLE);
            binding.tvSelectedValue.setText(getString(R.string.loyalty_value) + coupon);
            binding.tvAppliedLoyaltyVal.setVisibility(View.VISIBLE);
            binding.tvAppliedLoyalty.setVisibility(View.VISIBLE);
            binding.tvAppliedLoyaltyVal.setText(coupon);

        } else if (requestCode == OPEN_PAYMENT_DETAIL_ACTIVITY && resultCode == RESULT_OK && data != null) {
            cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
            if (isValid())
                checkOut();
        } else if (requestCode == OPEN_PIN_CREATE_ACTIVITY && resultCode == RESULT_OK && data != null) {
            boolean isOK = data.getBooleanExtra(PIN_CREATE_ACTIVITY_CALLBACK, false);
            if (isOK) {
                if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
                    Intent stamp = new Intent(this, ActiveCardActivity.class);
                    stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                    startActivityForResult(stamp, OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY);
                } else {
                    Intent stamp = new Intent(this, CardActivity.class);
                    stamp.putExtra(FROM_WHERE, getString(R.string.cart));
                    startActivityForResult(stamp, OPEN_PAYMENT_DETAIL_ACTIVITY);
                }

            }
        } else if (requestCode == OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY && resultCode == RESULT_OK && data != null) {
            cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
            payment_gatway = data.getStringExtra(ACTIVE_PAYMENT_TYPE);
            cardNumber = data.getStringExtra(CARD_NUMBER);
            cardExpiryMonth = String.valueOf(data.getIntExtra(CARD_EXPIRY_MONTH, 22));
            cardExpiryYear = String.valueOf(data.getIntExtra(CARD_EXPIRY_YEAR, 11));
            cardCVV = data.getStringExtra(CARD_CVV);

            address_one = data.getStringExtra(ADDRESS_ONE);
            address_two = data.getStringExtra(ADDRESS_TWO);
            address_three = data.getStringExtra(ADDRESS_THREE);
            if (!data.getStringExtra(POSTAL_CODE).isEmpty()) {
                postal_code = data.getStringExtra(POSTAL_CODE);
            }
            isSaveCard = data.getBooleanExtra(IS_SAVE_CARD, false);
            checkOut();

        } else if (requestCode == OPEN_ADD_ADDRESS_ACTIVITY && resultCode == RESULT_OK && data != null) {
            try {
                binding.cbDeliveryAddress.setText(data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));
                addressId = String.valueOf(data.getIntExtra(ADDRESS_ID, 0));
                customerAddress = new GetCartSummaryHospResponseModel.CustAddressBean();
                customerAddress.setId(data.getIntExtra(ADDRESS_ID, 0));
                customerAddress.setCity(data.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));
            } catch (Exception e) {
                e.printStackTrace();
                log("bbb", e.getMessage());
            }
        } else if (requestCode == OPEN_ACTIVITY_COUPON_CALLBACK_CODE && resultCode == RESULT_OK && data != null) {

            CouponResponseModel.CouponDataBean model = data.getExtras().getParcelable(OPEN_ACTIVITY_COUPON_CALLBACK_DATA);
            AddCoupon(model);
        } else if (requestCode == OPEN_PIN_VERIFY_ACTIVITY && resultCode == RESULT_OK && data != null) {
            checkOut();
        } else if (requestCode == ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE && resultCode == RESULT_OK && data != null) {
            isSaveCard = data.getBooleanExtra(IS_SAVE_CARD, false);
            if (myCartList.get(0).getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
                // cardId = HelperClass.toBase64(defaultCardsBean.getStripe_card_id());
                cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
                payment_gatway = data.getStringExtra(ACTIVE_PAYMENT_TYPE);
                cardNumber = data.getStringExtra(CARD_NUMBER);
                cardExpiryMonth = String.valueOf(data.getIntExtra(CARD_EXPIRY_MONTH, 22));
                cardExpiryYear = String.valueOf(data.getIntExtra(CARD_EXPIRY_YEAR, 11));
                cardCVV = data.getStringExtra(CARD_CVV);

                address_one = data.getStringExtra(ADDRESS_ONE);
                address_two = data.getStringExtra(ADDRESS_TWO);
                address_three = data.getStringExtra(ADDRESS_THREE);
                if (!data.getStringExtra(POSTAL_CODE).isEmpty()) {
                    postal_code = data.getStringExtra(POSTAL_CODE);
                }
            } else {
                cardId = data.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
            }

            binding.tvCards.setText(/*data.getStringExtra(CARD_BRAND_NAME) +*/ cardNumber);

        } else if (requestCode == OPEN_ACTIVITY_COUPON_CALLBACK_CODE && resultCode == RESULT_CANCELED) {

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (dialogGlobal != null)
            dialogGlobal.dismiss();
        DialogUtils.dismissAll();
        DialogUtils.dismissCountDownTimer();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected int getLayoutResourceId() {
        return R.layout.activity_my_cart_hospitality;
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
                } /*else {
                    DialogUtils.simpleNotificationDialog(this, title, message, image, null, (position, clickId) -> {
                        Intent myOrder = new Intent(this, MyOrderActivity.class);
                        startActivity(myOrder);
                    });
                }*/

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<String> getDateGapDelivery(String delivery_opening_time, String delivery_closing_time, int timing_slot_gap) {
        //  String date1 = "24/01/2021"; String time1 = "02:00";  //  String date2 = "24/01/2021";  String time2 = "3:00";
        List<String> deliGap = new ArrayList<>();
        deliGap.add("ASAP");
        if (!TextUtils.isEmpty(delivery_opening_time) && !TextUtils.isEmpty(delivery_closing_time)) {
            long gap = timing_slot_gap * 1000 * 60;
            SimpleDateFormat sdf = new SimpleDateFormat(HHmm);
            Date dateObj1 = null;
            try {
                dateObj1 = sdf.parse(/*date1 + " " +*/ delivery_opening_time);
                Date dateObj2 = sdf.parse(/*date2 + " " +*/ delivery_closing_time);
                long dif = dateObj1.getTime();
                while (dif < dateObj2.getTime()) {
                    Date slot = new Date(dif);
                    //  System.out.println("Hour Slot --->" + slot + " ddd " + sdf.format(slot));
                    dif += gap;  /// time gap in milisec   900000  (15 mmin)
                    deliGap.add(sdf.format(slot));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return deliGap;
    }

    private void deletetCartHospitality(String cartId) {

        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            final AddMobileCartQuantityRequestModel requestModel = new AddMobileCartQuantityRequestModel(cartId, "");
            Call<GetCartSummaryHospResponseModel> call = apiInterface.deleteCart(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);

            binding.llNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<GetCartSummaryHospResponseModel>() {
                @Override
                public void onResponse(Call<GetCartSummaryHospResponseModel> call, Response<GetCartSummaryHospResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            if (response.body().isStatus()) {
                                responseModel = response.body();
                                init();
                            } else {
                                showSnackBar(binding.getRoot(), response.body().getMessage());
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

                    } catch (
                            Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetCartSummaryHospResponseModel> call, Throwable t) {
                    // binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }
    }

    private void updateCartHospitality(String type, String cartId, String quantity) {

        if (isInternetOn(this)) {
            final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null)
                dialog.show();

            final String latitude = prefManager.getPreference(LATITUDE, "");
            final String longitude = prefManager.getPreference(LONGITUDE, "");

            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

            Call<GetCartSummaryHospResponseModel> call = null;
            if (type.equals(PLUS)) {
                final AddMobileCartQuantityRequestModel requestModel = new AddMobileCartQuantityRequestModel(PLUS, "" + prefManager.getPreference(LOGIN_ID, 0), quantity, cartId, latitude, longitude);
                call = apiInterface.updateCartQuantity(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            } else if (type.equals(MINUS)) {
                final AddMobileCartQuantityRequestModel requestModel = new AddMobileCartQuantityRequestModel(MINUS, "" + prefManager.getPreference(LOGIN_ID, 0), quantity, cartId, latitude, longitude);
                call = apiInterface.updateCartQuantity(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            }
            binding.llNoDataFound.setVisibility(View.GONE);
            call.enqueue(new Callback<GetCartSummaryHospResponseModel>() {
                @Override
                public void onResponse(Call<GetCartSummaryHospResponseModel> call, Response<GetCartSummaryHospResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();

                        if (response.isSuccessful()) {

                            if (response.body().isStatus()) {
                                responseModel = response.body();
                                init();
                            } else {
                                showSnackBar(binding.getRoot(), response.body().getMessage());
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

                    } catch (
                            Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(Call<GetCartSummaryHospResponseModel> call, Throwable t) {
                    // binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));

        }

    }


    private void getTodayCoupon() {
        if (isInternetOn(this)) {
            /*final KProgressHUD dialog = DialogUtils.getCustomDialog(this);
            if (dialog != null && !isMore)
                dialog.show();*/
            CouponRequestModel couponRequestModel = new CouponRequestModel(myCartList.get(0).getReservation_id(), String.valueOf(myCartList.get(0).getVenue_id()));


            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CouponResponseModel> call = apiInterface.getTodayCoupon(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""),
                    couponRequestModel);
            call.enqueue(new Callback<CouponResponseModel>() {
                @Override
                public void onResponse(Call<CouponResponseModel> call, Response<CouponResponseModel> response) {
                    try {
                        /*binding.swipeRefresh.setRefreshing(false);
                        if (dialog != null)
                            dialog.dismiss();*/

                        if (response.isSuccessful()) {
                            CouponResponseModel loyaltyResponseModel = response.body();
                            if (loyaltyResponseModel != null) {
                                if (loyaltyResponseModel.isStatus()) {
                                    //   isLoading = false;
                                    if (loyaltyResponseModel.getCouponData() != null && loyaltyResponseModel.getCouponData().size() > 0) {
                                        binding.btnSelectCoupon.setVisibility(View.VISIBLE);

                                    } else {
                                        binding.btnSelectCoupon.setVisibility(View.GONE);
                                    }
                                } else {
                                    showSnackBar(binding.getRoot(), loyaltyResponseModel.getMessage());
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
                    }
                }

                @Override
                public void onFailure(Call<CouponResponseModel> call, Throwable t) {
                    /*binding.swipeRefresh.setRefreshing(false);
                    if (dialog != null)
                        dialog.dismiss();*/
                    showSnackBar(binding.getRoot(), getString(R.string.msg_please_try_later));
                }
            });
        } else {
            // binding.swipeRefresh.setRefreshing(false);
            showSnackBar(binding.getRoot(), getString(R.string.no_internet_available_msg));
        }
    }
}




