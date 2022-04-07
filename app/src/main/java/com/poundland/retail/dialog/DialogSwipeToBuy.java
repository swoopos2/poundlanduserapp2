package com.poundland.retail.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.kaopiz.kprogresshud.KProgressHUD;
import com.poundland.retail.R;
import com.poundland.retail.activity.ActiveCardActivity;
import com.poundland.retail.activity.AddNewCardOnCheckoutActivity;
import com.poundland.retail.activity.AddressActivity;
import com.poundland.retail.activity.CardActivity;
import com.poundland.retail.activity.CreatePinActivity;
import com.poundland.retail.activity.MyOrderActivity;
import com.poundland.retail.activity.ProductDetailActivity;
import com.poundland.retail.activity.VerifyPinActivity;
import com.poundland.retail.apiUtils.ApiClient;
import com.poundland.retail.apiUtils.ApiInterface;
import com.poundland.retail.apiUtils.ApiRequestUrl;
import com.poundland.retail.appUtils.HelperClass;
import com.poundland.retail.appUtils.PrefManager;
import com.poundland.retail.databinding.DialogSwipeForAddToCartBinding;
import com.poundland.retail.interfaces.Constants;
import com.poundland.retail.interfaces.OnDateSelectListner;
import com.poundland.retail.interfaces.OnDialogClickListener;
import com.poundland.retail.interfaces.OnSwipeTouchListener;
import com.poundland.retail.model.requestModel.CheckOutRequestModel;
import com.poundland.retail.model.responseModel.CheckOutResponseModel;
import com.poundland.retail.model.responseModel.SingleProductDetailResponseModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.poundland.retail.appUtils.HelperClass.fromBase64ToStr;
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
import static com.poundland.retail.interfaces.Constants.CARD_BRAND_NAME;
import static com.poundland.retail.interfaces.Constants.CARD_CVV;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_MONTH;
import static com.poundland.retail.interfaces.Constants.CARD_EXPIRY_YEAR;
import static com.poundland.retail.interfaces.Constants.CARD_NUMBER;
import static com.poundland.retail.interfaces.Constants.CARD_NUMBER_TO_SHOW;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_ORDER;
import static com.poundland.retail.interfaces.Constants.CUSTOMER_TYPE;
import static com.poundland.retail.interfaces.Constants.EMAIL;
import static com.poundland.retail.interfaces.Constants.FROM_WHERE;
import static com.poundland.retail.interfaces.Constants.IS_SAVE_CARD;
import static com.poundland.retail.interfaces.Constants.OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_CHANGE_ADDRESS_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PAYMENT_DETAIL_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_CREATE_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.OPEN_PIN_VERIFY_ACTIVITY;
import static com.poundland.retail.interfaces.Constants.PAYMENT_DETAIL_ACTIVITY_CALLBACK;
import static com.poundland.retail.interfaces.Constants.PAYMENT_GATEWAY_ACTIVE_SAVED_CARD;
import static com.poundland.retail.interfaces.Constants.PAYMENT_GATEWAY_STRIPE;
import static com.poundland.retail.interfaces.Constants.POSITION;
import static com.poundland.retail.interfaces.Constants.POSTAL_CODE;
import static com.poundland.retail.interfaces.Constants.VENUE_ID_IN_CART;


public class DialogSwipeToBuy extends Dialog implements View.OnClickListener, OnDateSelectListner {
    private static final String PAYMENT_GATEWAY = "";
    private Context context;
    private LayoutInflater inflater;
    private DialogSwipeForAddToCartBinding binding;
    private PrefManager prefManager;
    private ListPopupWindow popupWindow;
    private boolean isPopup;
    private List<String> imageList;
    private String price;
    private double priceForCalculation;
    private HashMap<String, String> requestHashMap;
    private String cardId = "";
    private List<String> deliveryTypeList;
    private Activity activity;
    private String deliveryMethod;
    private SingleProductDetailResponseModel productDetailResponseModel;
    private String addressId;
    private String postal_code;
    private String venueLatitude;
    private String venueLongitude;
    private double grandTotal;
    String sameDayDeliveryTime;
    String sameDayDeliveryText;
    private double deliveryCharge;
    private ProductDetailActivity productDetailActivity;

    String IS_GIFT = "0";
    int REORDER_STATUS = 0;
    int IS_HOSPITALITY = 0;
    String app_type = "LOCAL";
    ////////////////////////////////////////////////////////////////////////////
    public SingleProductDetailResponseModel.CustAddressBean customerAddress;
    public String payment_gatway = PAYMENT_GATEWAY_STRIPE;
    public String cardNumber = "";
    public String cardExpiryMonth = "";
    public String cardExpiryYear = "";
    public String cardCVV = "";
    public String address_one = "";
    public String address_two = "";
    public String address_three = "";
    public boolean isSaveCard;
    private int modifierId;
    private String productId;
    private String offerId;

    private String cardNumberToShow = "";
    private String cardBrand = "";
    private List<SingleProductDetailResponseModel.DefaultCardsBean> myCardList;

    /////////////////////////////////////////////////////////////////////////
    public DialogSwipeToBuy(Activity activity, Context context, ArrayList<String> imageList, HashMap<String, String> requestHashMap,
                            String price, List<String> deliveryTypelist, SingleProductDetailResponseModel productDetailResponseModel, String sameDayDeliveryTime, String sameDayDeliveryText, int modifierId, String productId, String offerId) {
        super(context);
        this.activity = activity;
        this.context = context;
        this.price = price;
        this.offerId = TextUtils.isEmpty(offerId) ? offerId : offerId.replace("null", "");
        this.imageList = new ArrayList<>(imageList);
        this.modifierId = modifierId;
        this.productId = productId;
        this.deliveryTypeList = new ArrayList<>(deliveryTypelist);
        this.sameDayDeliveryTime = sameDayDeliveryTime;
        this.sameDayDeliveryText = sameDayDeliveryText;
        this.productDetailResponseModel = productDetailResponseModel;
        this.requestHashMap = requestHashMap;
        inflater = LayoutInflater.from(context);
        prefManager = PrefManager.getInstance(context);
        myCardList = new ArrayList<>();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().getAttributes().windowAnimations = R.style.AnimationCenterPopUp;

        binding = DataBindingUtil.inflate(inflater, R.layout.dialog_swipe_for_add_to_cart, null, false);
        setContentView(binding.getRoot());

        this.setCancelable(true);
        if (this.getWindow() != null) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            ((AppCompatActivity) context).getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int displayWidth = displayMetrics.widthPixels;
            int displayHeight = displayMetrics.heightPixels;
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.copyFrom(this.getWindow().getAttributes());
            int dialogWindowWidth = (int) (displayWidth * 0.96f);
            int dialogWindowHeight = (int) (displayHeight * 0.4f);
            layoutParams.width = dialogWindowWidth;
//        layoutParams.height = dialogWindowHeight;
            layoutParams.gravity = Gravity.BOTTOM;
            layoutParams.flags &= WindowManager.LayoutParams.FLAG_DIM_BEHIND;
            this.getWindow().setAttributes(layoutParams);
            this.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            this.getWindow().setLayout(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        if (getContext() instanceof ProductDetailActivity) {
            productDetailActivity = (ProductDetailActivity) getContext();
        }

        if (productDetailResponseModel.getDefaultCards() != null && productDetailResponseModel.getDefaultCards().size() > 0) {

             myCardList.addAll(productDetailResponseModel.getDefaultCards());
             setCardDetail(myCardList.get(0));
        }


        setData();

        binding.rlSwipe.setOnTouchListener(new OnSwipeTouchListener(context) {
            public void onSwipeTop() {
                // Toast.makeText(context, "top", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeRight() {
                //  Toast.makeText(context, "right", Toast.LENGTH_SHORT).show();

                if (validateDeliveryMethod()) {
                    if (isValid()) {

                        Intent stamp = new Intent(context, VerifyPinActivity.class);
                        stamp.putExtra(FROM_WHERE, context.getString(R.string.cart));
                        stamp.putExtra(POSITION, -1);
                        activity.startActivityForResult(stamp, OPEN_PIN_VERIFY_ACTIVITY);
                        /* checkOut();*/
                    }
                }
            }

            public void onSwipeLeft() {
                //    Toast.makeText(context, "left", Toast.LENGTH_SHORT).show();
            }

            public void onSwipeBottom() {
                ///   Toast.makeText(context, "bottom", Toast.LENGTH_SHORT).show();
            }

        });
    }

    private void setData() {
        binding.rlClose.setOnClickListener(this);
        binding.rlCards.setOnClickListener(this);
        binding.tvDeliveryType.setOnClickListener(this);
        binding.tvBuyNow.setOnClickListener(this);

        if (!TextUtils.isEmpty(price.replace(context.getResources().getString(R.string.pound), "")))
            priceForCalculation = Double.parseDouble(price.replace(context.getResources().getString(R.string.pound), ""));

        if (deliveryTypeList != null && deliveryTypeList.size() > 0) {
            binding.tvDeliveryType.setText(deliveryTypeList.size() > 0 ? deliveryTypeList.get(0) : "");

            deliveryMethod = deliveryTypeList.get(0);

            if (deliveryMethod.contains("+" + context.getResources().getString(R.string.pound)) || deliveryMethod.equalsIgnoreCase(getContext().getResources().getString(R.string.home_delivery))) {
                showCustAddress(productDetailResponseModel.getCust_address());
            } else if (deliveryMethod.equalsIgnoreCase(getContext().getResources().getString(R.string.click_n_collect))) {
                showVenueAddress();
            }
        }


        binding.tvProductName.setText(productDetailResponseModel.getProducts().getProduct_name());
        binding.tvPrice.setText(price);

        if (requestHashMap.toString().length() > 2) {
            String modifier = requestHashMap.toString().replace("=", ":");
            modifier = modifier.replace("{", "").replace("}", "");
            binding.tvModifier.setText(modifier);
        }

        Glide.with(context).load(imageList.size() > 0 ? ApiRequestUrl.BASE_URL + imageList.get(0) : "").apply(new RequestOptions()
                .placeholder(R.drawable.ic_app_icon))
                .into(binding.ivProductImage);

    }

    public void setSpinnerForCards() {

        List<String> cards = new ArrayList<>();
        for (int i = 0; i < myCardList.size(); i++) {
            cards.add(myCardList.get(i).getCard_number());
        }

        popupWindow = new ListPopupWindow(context);
        ArrayAdapter<String> adapter = new ArrayAdapter(context, android.R.layout.simple_spinner_dropdown_item, cards);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.rlCards);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                setCardDetail(myCardList.get(position));

                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }

    private void setCardDetail(SingleProductDetailResponseModel.DefaultCardsBean defaultCardsBean) {
        binding.tvCards.setText(defaultCardsBean.getCard_type() + " " + defaultCardsBean.getCard_number());
        if (defaultCardsBean.getGateway().equals("active")) {
            payment_gatway = PAYMENT_GATEWAY_ACTIVE_SAVED_CARD;
            cardId = HelperClass.toBase64(defaultCardsBean.getStripe_card_id());
        } else {
            payment_gatway = defaultCardsBean.getGateway();
            cardId = defaultCardsBean.getStripe_card_id();
        }

        if (defaultCardsBean.getCard_type().equalsIgnoreCase(context.getString(R.string.visa))) {


            Glide.with(context).load(R.drawable.ic_visa).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);

        } else if (defaultCardsBean.getCard_type().equalsIgnoreCase(context.getString(R.string.Visa_Credit))) {
            Glide.with(context).load(R.drawable.ic_visa).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);

        } else if (defaultCardsBean.getCard_type().equalsIgnoreCase(context.getString(R.string.mastercard))) {
            Glide.with(context).load(R.drawable.cio_ic_mastercard).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);
        } else if (defaultCardsBean.getCard_type().equalsIgnoreCase(context.getString(R.string.maestro_card))) {
            Glide.with(context).load(R.drawable.ic_maestro).apply(new RequestOptions()
                    .placeholder(R.drawable.ic_app_icon))
                    .into(binding.cardsImg);
        }
    }

    @Override
    public void onDrawerSelect(int position, String clickId) {

    }

    public void setSpinnerFordelivery() {
        HashSet<String> hashSet = new HashSet<>();
        popupWindow = new ListPopupWindow(context);
        ArrayAdapter<String> adapter = new ArrayAdapter(context, R.layout.layout_single_text_view, deliveryTypeList);
        popupWindow.setAdapter(adapter);
        popupWindow.setAnchorView(binding.tvDeliveryType);
        popupWindow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                binding.tvDeliveryType.setText(deliveryTypeList.get(position));
                deliveryMethod = deliveryTypeList.get(position);
                if (deliveryMethod.contains("+" + context.getResources().getString(R.string.pound)) || deliveryMethod.equalsIgnoreCase(getContext().getResources().getString(R.string.home_delivery))) {
                    showCustAddress(productDetailResponseModel.getCust_address());
                } else if (deliveryMethod.equalsIgnoreCase(getContext().getResources().getString(R.string.click_n_collect))) {
                    showVenueAddress();
                }
                popupWindow.dismiss();
                isPopup = false;
            }
        });
        popupWindow.show();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_close:
                dismiss();
                break;

            case R.id.tv_delivery_type:
                if (isPopup) {
                    popupWindow.dismiss();
                    isPopup = false;
                } else {
                    setSpinnerFordelivery();
                    isPopup = true;
                }
                break;

            case R.id.rl_cards:

                if (binding.tvCards.getText().equals(context.getString(R.string.select_your_card_for_payment))) {
                    addNewCard();

                } else {
                    if (isPopup) {
                        popupWindow.dismiss();
                        isPopup = false;
                    } else {
                        setSpinnerForCards();
                        isPopup = true;
                    }
                }


                break;
        }
    }

    private void addNewCard() {

        if (prefManager.getPreference(CUSTOMER_ORDER, -1) == 0) {   // check whether pin is already set or not
            Intent setPin = new Intent(context, CreatePinActivity.class);
            setPin.putExtra(FROM_WHERE, context.getString(R.string.cart));
            activity.startActivityForResult(setPin, OPEN_PIN_CREATE_ACTIVITY);
        } else {
            if (productDetailResponseModel.getVenues().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_STRIPE)) {
                Intent stamp = new Intent(activity, AddNewCardOnCheckoutActivity.class);
                stamp.putExtra(FROM_WHERE, context.getString(R.string.stripe));
                activity.startActivityForResult(stamp, ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE);
            } else if (productDetailResponseModel.getVenues().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
                Intent stamp = new Intent(activity, AddNewCardOnCheckoutActivity.class);
                stamp.putExtra(FROM_WHERE, context.getString(R.string.active));
                activity.startActivityForResult(stamp, ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE);

            } else if (productDetailResponseModel.getVenues().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_PAYDOO)) {
                showSnackBar(binding.getRoot(), "PAYDOO PAYMENT GATEWAY IS NOT SUPPORTED BY APP YET!");
            }
        }


    }

    private boolean validateDeliveryMethod() {
        if (deliveryMethod.equals("")) {
            showSnackBar(binding.getRoot(), context.getString(R.string.select_delivery_type));
            return false;
        } else if (deliveryMethod.equals(context.getString(R.string.home_delivery))) {
            if (priceForCalculation < Double.parseDouble(productDetailResponseModel.getVenues().getHome_delivery_mov())) {
                showSnackBar(binding.getRoot(), context.getString(R.string.cart_value_minimum));
                return false;
            }
        }
        return true;
    }


    private boolean isValid() {
        try {
            if (productDetailResponseModel.getCust_address() == null && deliveryMethod.equals(context.getString(R.string.home_delivery))) {
                Intent intent = new Intent(getContext(), AddressActivity.class);
                intent.putExtra(FROM_WHERE, context.getString(R.string.cart));
                activity.startActivityForResult(intent, OPEN_CHANGE_ADDRESS_ACTIVITY);
                return false;
            } else if (cardId.equals("")) {
                addNewCard();
                return false;
            }

        } catch (
                Exception exc) {
            log("Validation exc", "" + exc.getMessage());
        }
        return true;
    }


    private void showCustAddress(SingleProductDetailResponseModel.CustAddressBean cust_address) {
        if (cust_address != null) {
            addressId = String.valueOf(cust_address.getId());
            String address = "" + cust_address.getFlat() + cust_address.getArea() + "," + cust_address.getCity() + " ," + cust_address.getLandmark() + "," + cust_address.getState() + " " + cust_address.getPincode(); ///  + cust_address.getCountry()
            address = address.replace("null", "");
            address = address.replace(",,", ",");
            binding.tvAddress.setText(address);
            postal_code = cust_address.getPincode();
            if (deliveryMethod.equalsIgnoreCase(getContext().getResources().getString(R.string.home_delivery))) {  // HOME DELIVERY
                binding.tvDeliveryTime.setText("Get Delivered in " + productDetailResponseModel.getVenues().getStart_days() + " to " + productDetailResponseModel.getVenues().getEnd_days() + " day");
                deliveryCharge = Double.parseDouble(productDetailResponseModel.getVenues().getDelivery_charge());
                Glide.with(context).load("").apply(new RequestOptions()
                        .placeholder(R.drawable.ic_home_delivery))
                        .into(binding.ivDeliveryImage);

                if (priceForCalculation < Double.parseDouble(productDetailResponseModel.getVenues().getFree_delivery())) {
                    binding.tvDeliveryCost.setVisibility(View.VISIBLE);
                    binding.tvDeliveryCost.setText(context.getString(R.string.delivery_charge) + " " + context.getString(R.string.pound) + String.format("%.2f", deliveryCharge));
                } else {
                    deliveryCharge = 0.00;
                    binding.tvDeliveryCost.setVisibility(View.GONE);
                }

            } else if (deliveryMethod.contains("+" + context.getResources().getString(R.string.pound))) {  /// SAME DAY DELIVERY
                binding.tvDeliveryTime.setText("Get Delivery : " + sameDayDeliveryTime);

                Glide.with(context).load("").apply(new RequestOptions()
                        .placeholder(R.drawable.ic_same_day_delivery))
                        .into(binding.ivDeliveryImage);

                if (productDetailResponseModel.getShippingData().getType() == 1) {
                    deliveryCharge = Double.parseDouble(productDetailResponseModel.getShippingData().getAmount());
                } else {

                    double priceProduct = priceForCalculation;
                    double percent = Double.parseDouble(productDetailResponseModel.getShippingData().getAmount());
                    double deliCharge = (priceProduct * percent) / 100;
                    deliveryCharge = deliCharge;
                }
                binding.tvDeliveryCost.setVisibility(View.VISIBLE);
                binding.tvDeliveryCost.setText(context.getString(R.string.delivery_charge) + " " + context.getString(R.string.pound) + String.format("%.2f", deliveryCharge));

            }

        } else {
            binding.tvAddress.setText("Add Address For Delivery");
        }
    }

    private void showVenueAddress() {
        binding.tvDeliveryCost.setVisibility(View.GONE);
        deliveryCharge = 0.00;
        String address = context.getString(R.string.not_given);
        if (productDetailResponseModel != null) {
            address = "" + productDetailResponseModel.getVenues().getAddress_1();

            venueLatitude = productDetailResponseModel.getVenues().getLatitude();
            venueLongitude = productDetailResponseModel.getVenues().getLongitude();

            binding.tvDeliveryTime.setText("Collect in " + productDetailResponseModel.getVenues().getCollection_time() + " Mins");

        }
        binding.tvAddress.setText(address);

        Glide.with(context).load("").apply(new RequestOptions()
                .placeholder(R.drawable.ic_click_n_collect))
                .into(binding.ivDeliveryImage);
    }


    public void getcallback() {
        toast(context, "got call back ");
    }

    public void getcallback_OPEN_CHANGE_ADDRESS_ACTIVITY(Intent dataChangeAddress) {
        customerAddress = new SingleProductDetailResponseModel.CustAddressBean();
        addressId = String.valueOf(dataChangeAddress.getIntExtra(ADDRESS_ID, 0));
        customerAddress.setId(dataChangeAddress.getIntExtra(ADDRESS_ID, 0));
        customerAddress.setCity(dataChangeAddress.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));

        binding.tvAddress.setText(dataChangeAddress.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));
    }

    public void getcallback_OPEN_PAYMENT_DETAIL_ACTIVITY(Intent dataOpenPaymentDetail) {

        cardId = dataOpenPaymentDetail.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);

        cardNumberToShow = dataOpenPaymentDetail.getStringExtra(CARD_NUMBER_TO_SHOW);
        cardBrand = dataOpenPaymentDetail.getStringExtra(CARD_BRAND_NAME);
        binding.tvCards.setText(cardBrand + " " + cardNumberToShow);  //////////////
        binding.tvBuyNow.setText("Proceed To Pay");  //////////////
        binding.tvBuyNow.setBackgroundColor(ContextCompat.getColor(context, R.color.light_green));  //////////////
        binding.ivItemImage.setBackgroundColor(ContextCompat.getColor(context, R.color.light_green_726));  //////////////
        /*if (isValid())
            checkOut();*/
    }

    public void getcallback_OPEN_PIN_CREATE_ACTIVITY(Intent dataOpenPinCreat) {

        if (productDetailResponseModel.getVenues().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
            Intent stamp = new Intent(getContext(), ActiveCardActivity.class);
            stamp.putExtra(FROM_WHERE, context.getString(R.string.cart));
            activity.startActivityForResult(stamp, OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY);
        } else {
            Intent stamp = new Intent(getContext(), CardActivity.class);
            stamp.putExtra(FROM_WHERE, context.getString(R.string.cart));
            activity.startActivityForResult(stamp, OPEN_PAYMENT_DETAIL_ACTIVITY);
        }

    }

    public void getcallback_OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY(Intent dataActivePayList) {
        cardId = dataActivePayList.getStringExtra(PAYMENT_DETAIL_ACTIVITY_CALLBACK);
        payment_gatway = dataActivePayList.getStringExtra(ACTIVE_PAYMENT_TYPE);
        cardNumber = dataActivePayList.getStringExtra(CARD_NUMBER);
        cardExpiryMonth = String.valueOf(dataActivePayList.getIntExtra(CARD_EXPIRY_MONTH, 22));
        cardExpiryYear = String.valueOf(dataActivePayList.getIntExtra(CARD_EXPIRY_YEAR, 11));
        cardCVV = dataActivePayList.getStringExtra(CARD_CVV);
        cardNumberToShow = dataActivePayList.getStringExtra(CARD_NUMBER_TO_SHOW);
        cardBrand = dataActivePayList.getStringExtra(CARD_BRAND_NAME);
        binding.tvCards.setText(fromBase64ToStr(cardBrand) + " " + fromBase64ToStr(cardNumberToShow));  //////////////
        binding.tvBuyNow.setText("proceed To Pay");
        binding.tvBuyNow.setBackgroundColor(ContextCompat.getColor(context, R.color.light_green));  //////////////
        binding.ivItemImage.setBackgroundColor(ContextCompat.getColor(context, R.color.light_green_726));  //////////////


        address_one = dataActivePayList.getStringExtra(ADDRESS_ONE);
        address_two = dataActivePayList.getStringExtra(ADDRESS_TWO);
        address_three = dataActivePayList.getStringExtra(ADDRESS_THREE);
        if (!dataActivePayList.getStringExtra(POSTAL_CODE).isEmpty()) {
            postal_code = dataActivePayList.getStringExtra(POSTAL_CODE);
        }
        isSaveCard = dataActivePayList.getBooleanExtra(IS_SAVE_CARD, false);
        //checkOut();

    }

    public void getcallback_OPEN_ADD_ADDRESS_ACTIVITY(Intent dataAddAddress) {
        binding.tvAddress.setText(dataAddAddress.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));
        addressId = String.valueOf(dataAddAddress.getIntExtra(ADDRESS_ID, 0));
        customerAddress = new SingleProductDetailResponseModel.CustAddressBean();
        customerAddress.setId(dataAddAddress.getIntExtra(ADDRESS_ID, 0));
        customerAddress.setCity(dataAddAddress.getStringExtra(ADDRESS_ACTIVITY_CALLBACK));

    }

    public void getcallback_add_card_ACTIVITY(Intent data) {
        isSaveCard = data.getBooleanExtra(IS_SAVE_CARD, false);
        if (productDetailResponseModel.getVenues().getPayment_gatway().equals(Constants.PAYMENT_GATEWAY_ACTIVE)) {
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
            cardNumber = data.getStringExtra(CARD_NUMBER);
        }

        binding.tvCards.setText(/*data.getStringExtra(CARD_BRAND_NAME) +*/ cardNumber);

    }

    public void checkOut() {
        CheckOutRequestModel requestModel = null;


        List<CheckOutRequestModel.ProductsBean> productList = new ArrayList<>();
        CheckOutRequestModel.ProductsBean productsBean;
        // for (int i = 0; i < myCartList.size(); i++) {

        productsBean = new CheckOutRequestModel.ProductsBean(1, Integer.parseInt(productId),
                String.valueOf(modifierId), binding.tvAddress.getText().toString(), binding.tvAddress.getText().toString(),
                Integer.parseInt(productDetailResponseModel.getVenues().getMerchant_id()), productDetailResponseModel.getVenues().getVenue_id(),
                ""/*modifierName*/, priceForCalculation,   ///selling price
                Double.parseDouble(productDetailResponseModel.getProducts().getBuy_price()), 0d, TextUtils.isEmpty(offerId) ? 0 : Long.parseLong(offerId), 0d,
                TextUtils.isEmpty(productDetailResponseModel.getProducts().getTax_id()) ? 0 : Integer.parseInt(productDetailResponseModel.getProducts().getTax_id()),
                String.valueOf(priceForCalculation), "", deliveryMethod.contains("+" + context.getResources().getString(R.string.pound)) ? context.getString(R.string.home_delivery) : deliveryMethod, 0, 0, 0, "");
        productList.add(productsBean);
        //   }

        requestModel = new CheckOutRequestModel(payment_gatway, toBase64(cardNumber), toBase64(cardExpiryMonth), toBase64(cardExpiryYear), toBase64(cardCVV), productDetailResponseModel.getVenues().getMerchant_id(),
                productDetailResponseModel.getVenues().getVenue_id(), CUSTOMER_TYPE, CARD, cardId, addressId, 1,
                priceForCalculation,
                0,
                0,
                String.valueOf(priceForCalculation + deliveryCharge), String.valueOf(deliveryCharge), 0, IS_GIFT, REORDER_STATUS, deliveryMethod, "0",
                "0", productList, postal_code,
                address_one, address_two, address_three, isSaveCard, "0", "", deliveryMethod.contains("+" + context.getResources().getString(R.string.pound)) ? "" + productDetailResponseModel.getShippingData().getId() : "",
                IS_HOSPITALITY, app_type, "", "", "", "");

        // Log.e("REQUESTTTTT", new Gson().toJson(requestModel));
        final KProgressHUD dialog = DialogUtils.getCustomDialog(activity);
        if (dialog != null)
            dialog.show();
        if (isInternetOn(activity)) {
            ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);
            Call<CheckOutResponseModel> call = apiInterface.saveEcommOrder(prefManager.getPreference(AUTH_TOKEN, ""), prefManager.getPreference(EMAIL, ""), requestModel);
            call.enqueue(new Callback<CheckOutResponseModel>() {
                @Override
                public void onResponse(Call<CheckOutResponseModel> call, Response<CheckOutResponseModel> response) {
                    try {
                        if (dialog != null)
                            dialog.dismiss();
                        if (response.isSuccessful()) {
                            // cardId = "";
                            CheckOutResponseModel checkOutResponseModel = response.body();
                            if (checkOutResponseModel.isStatus()) {

                                DialogUtils.orderSuccesDialog(activity, checkOutResponseModel.getMessage(), true,
                                        String.valueOf(checkOutResponseModel.getOrder().getNet_amount()),
                                        prefManager.getPreference(EMAIL, ""), checkOutResponseModel.getOrder().getUnique_code(),
                                        imageList.size() > 0 ? ApiRequestUrl.BASE_URL + imageList.get(0) : "", 0, new OnDialogClickListener() {
                                            @Override
                                            public void onPositiveClick() {
                                                prefManager.savePreference(VENUE_ID_IN_CART, "");
                                                dismiss();
                                                Intent intent = new Intent(getContext(), MyOrderActivity.class);
                                                activity.startActivity(intent);
                                            }

                                            @Override
                                            public void onNegativeClick() {

                                            }
                                        }, checkOutResponseModel.getOrder().getLoyelty_consumed(), 0);

                            } else {

                                DialogUtils.orderSuccesDialog(activity, TextUtils.isEmpty(checkOutResponseModel.getError()) ? checkOutResponseModel.getMessage() : checkOutResponseModel.getMessage() + "(" + checkOutResponseModel.getError() + ")", false,
                                        String.valueOf(priceForCalculation), prefManager.getPreference(EMAIL, ""), "", "", 0, new OnDialogClickListener() {
                                            @Override
                                            public void onPositiveClick() {

                                            }

                                            @Override
                                            public void onNegativeClick() {

                                            }
                                        }, checkOutResponseModel.getOrder().getLoyelty_consumed(), 0);
                            }
                        } else {
                            final int httpCode = response.code();

                            DialogUtils.showAlertDialogWithSingleButton(getContext(), httpCode == 401 ? getContext().getResources().getString(R.string.season_expired) : getContext().getResources().getString(R.string.something_went_wrong), new OnDialogClickListener() {
                                @Override
                                public void onPositiveClick() {
                                    if (httpCode == 401) {
                                        logOut(activity);
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
                public void onFailure(Call<CheckOutResponseModel> call, Throwable t) {
                    if (dialog != null)
                        dialog.dismiss();
                    showSnackBar(binding.getRoot(), getContext().getResources().getString(R.string.msg_please_try_later));
                }
            });
        } else {
            showSnackBar(binding.getRoot(), getContext().getResources().getString(R.string.no_internet_available_msg));

        }
    }
}

