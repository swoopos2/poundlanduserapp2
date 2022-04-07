package com.poundland.retail.interfaces;

import java.text.DecimalFormat;

public interface Constants {

    String YYYY_MM_DD = "yyyy-MM-dd";
    String DD_MMM_YYYY_dash_in_middle = "dd-MMM-yyyy";
    String DD_MMMM_YYYY_space_in_midlle = "dd MMMM yyyy";
    String DD_MMMM_YYYY_space_in_midlle_HHmm = "dd MMMM yyyy HH:mm";
    String DD_MMM_YYYY_space_in_midlle = "dd MMM yyyy";
    String DD_MMM_YYYY_space_in_midlle_HHmm = "dd MMM yyyy HH:mm";
    String EEE_dd_MMM = "EEE dd MMM";
    String EEE_dd_MMM_YYYY = "EEE dd MMM yyyy";
    String EEE_dd_MMM_YYYY_HH_mm = "EEE dd MM yyyy HH:mm";
    String YYYY_MM_dd_HH_mm_ss = "yyyy-MM-dd HH:mm:ss";
    String EEEE = "EEEE";
    String YYYY_MM_dd_HH_mm = "yyyy-MM-dd HH:mm";
    String HHmm = "HH:mm";
    String HHmmss = "HH:mm:ss";

    String EURO = "\u20ac";

    String POUND = "\u00a3";

    String PAYMENT_GATEWAY_STRIPE = "stripe";
    String PAYMENT_GATEWAY_ACTIVE = "active";
    String PAYMENT_GATEWAY_PAYDOO = "paydoo";
    String PAYMENT_GATEWAY_ACTIVE_SAVED_CARD = "activePay";

    String ACTIVE_PAYMENT_TYPE = "active_pay_type";
    String CARD_NUMBER = "cardNumber";
    String CARD_EXPIRY_MONTH = "cardExpiryMonth";
    String CARD_EXPIRY_YEAR = "cardExpiryYear";
    String CARD_CVV = "cardCVV";

    String CARD_NUMBER_TO_SHOW = "show_card_number";
    String CARD_BRAND_NAME = "show_card_brand";

///// POUNDLAND
    String USER_ID = "user_id";
    String USER_NAME="user_name";
    String USER_EMAIL="user_email";


    String LOGIN_TYPE = "login_type";
    String AUTH_TOKEN = "access_token";
    String TOKEN_TYPE = "token_type";
    String EXPIRE_IN = "expires_in";
    String LOGIN_ID = "id";
    String FIRST_NAME = "first_name";
    String LAST_NAME = "last_name";
    String DOB = "dob";
    String EMAIL = "email";
    String CONTACT_NO = "contact_no";
    String REFERRAL_CODE = "referral_code";
    String REFERRED_CODE = "referred_code";
    String SPLASH_PREF = "spalsh_pref";
    String SAVE_SPLASH = "save_splash";
    String LATITUDE = "lat";
    String LONGITUDE = "longi";
    String CITY = "longi";

    String POST_ID = "post_id";
    String FCM_ID = "fId";

    String CART_ENTRY_TYPE = "cart_entry_type";
    int CART_HOSPITALITY = 2;
    int CART_RETAIL = 1;
    int CART_RESERVATION = 99;  //// cart  for reservation
    String CUSTOMER_ORDER = "customer_order";
    String STRIPE_CUST_ID = "stripe_cust_id";
    String PROFILE_IMAGE = "image";
    String GENDER = "gender";
    String SIGN_UP_REQU_MODEL = "user_details";
    String EDIT_ADDRESS_REQU_MODEL = "edit_address_req_model";
    String FROM_EDIT_ADDRESS = "from_edit_address";
    String FROM_ADD_ADDRESS = "from_add_address";
    String FROM_MY_CART = "from_my_cart";
    String DEVICE_TYPE = "ANDROID";
    String CUSTOMER_TYPE = "app";
    String QR_CODE_CUST = "CUST";
    String CUST = "cust";
    String VENUE = "venue";
    String UPCOMING_VENUE = "upcoming_venue";
    String MALE = "male";
    String FEMALE = "female";

    String NONE = "none";
    String UPDATE = "update";
    String PLUS = "plus";
    String MINUS = "minus";
    String DELETE = "delete";
    String GMAIL = "google";
    String FB = "fb";
    String FUSED_LOCATION = "f_loc";
    String VENUE_ID_IN_CART = "inCartVenue";
    String TOTAL_CART_COUNT = "total_cart_count";
    String CARD = "Card";
    String isLoyaltyApplied = "isLoyaltyApplied";
    String POSTAL_CODE = "postal_code";
    String IS_SAVE_CARD = "is_save_card";

    ////////////////////////////////////  Intent ids    /////////
    String DATE_INTENT = "dt_intent";
    String TIME_INTENT = "tm_intent";
    String PERSON_INTENT = "prsn_intent";
    String VENUE_INTENT = "vnue_intent";
    String QR_URL = "qr_url";
    String CATEGORY_NAME = "cat_name";
    String CATEGORY_ID = "cat_id";
    String SLUG = "slug";
    String SEARCH_KEY = "s_key";
    String STORE_ID = "s_id";
    String PRODUCT_ID = "p_id";
    String BARCODE_ID = "bar_id";
    String OFFER_ID = "offer_id";
    String SPECIAL_OFFER_ID = "special_offer_id";
    String TOP_OFFER_TITLE = "top_offer";
    String TITLE = "title";
    String FROM_WHERE = "from_where";
    String FROM_RESERVATION = "from_reservation";
    String FROM_RESERVATION_CART = "from_reservation_cart";
    String RESERVATION_ID = "reservation_id";
    String NO_OF_GUEST = "no_of_guest";
    String SLOT_ID = "slot_id";
    String AMOUNT_SEND = "amt_send";
    String IS_SMOKING = "is_smoking";
    String IS_UPCOMING_VENUE = "is_upcoming_venue";
    String HOME_FILTER = "home_filter";
    String FROM_SCAN_QR = "from_scan_qr";
    String FROM_HOSPITALITY_PRODUCT_LIST = "from_hosp_pro_list";
    String FROM_REGISTER = "from_register";
    String FROM_PROFILE = "from_profile";
    String FROM_PRODUCT = "from_product";
    String FROM_CATEGORY_TAP = "category_tap";
    String FROM_WISHLIST = "from_wishlist";
    String FROM_SEARCH_TAP = "search_tap";
    String FROM_LOYALTY = "from_loyalty";
    String FROM_APPLY_FILTER_TAP = "apply_filter_tap";
    String MY_FILE = "file";
    String ORDER_ID = "order_id";
    String ORDER_DETAIL_ID = "order_detail_id";
    String ORDER_DATE = "order_date";
    String COLLECT_TIME = "collect_time";
    String ESTIMATED_DELIVERY = "est_deliver_days";
    String CANCEL_DATE = "cancel_date";
    String CANCEL_STATUS = "cancel_status";
    String DELIVERY_TYPE = "delivery_type";
    String VENUE_TYPE = "venue_type";
    String IS_VENUE_SCANNED = "is_venue_scanned";
    String TRACK_DATA = "track_data";
    String VENUE_ID = "venue_id";
    String VENUE_ADDRESS = "venue_address";
    String TRACK_STATUS = "track";
    String ADDRESS_GET = "address";

    String ADDRESS_ONE = "address_one";
    String ADDRESS_TWO = "address_two";
    String ADDRESS_THREE = "address_three";
    String PRODUCT_NAME = "pro_name_";
    String PRODUCT_TYPE_SELECTED = "pro_type_";
    String VENUE_NAME = "ven_name";
    String DISTANCE = "distance_";
    String DISCOUNT_PRICE = "disc_price";
    String IS_PRODUCT_IN_CART = "isProductInCart";


    String QUANTITY = "qty";
    String IMAGE_LINK = "img_link";
    String HOME = "Home";
    String HOME_RETAIL = "HomeRetail";
    String HOME_HOSPITALITY = "HomeHospitality";
    String WORK = "Work";
    String OTHERS = "Others";
    String POSITION = "POSI";
    String WHOLE_FILTER_API_DATA = "fil_data";
    String FILTER_DATA_SELECTED = "fil_data_selected";

    String CANCEL_PRODUCT_MESSAGE = "product cancel";

////////////////////////////////////////////////////////////////////

    String SIMPLE_NOTIFY = "99";
    String DISC_OFFER_NOTIFY = "3";
    String SPECIAL_OFFER_NOTIFY = "2";
    String RATTING_NOTIFY = "1";


    String PUSH_STATUS = "pushStatus";
    String USER_TYPE = "userType";
    String IS_GUEST = "guest";
    String OTP = "otp";
    String YES = "yes";
    String NO = "no";
    String IS_REGISTER = "isRegister";
    String IS_LOGIN = "isLogin";

    ///////////////////////// DRAWER LISTNER CODE  //////////////////////////////////
    int SHOW_ALL_CATEGORY = 101;
    int HOME_CATEGORY = 102;
    int SELECT_CATEGORY = 103;
    int DRAWER = 104;
    int TOP_OFFER = 105;
    int TOP_VENUE = 106;
    int STORE = 107;
    int PRODUCT = 108;
    int ADDRESS = 109;
    int LOYALTY = 110;
    int STAMP = 111;
    int VOUCHER = 112;
    int EDIT_ITEM = 113;
    int DELETE_ITEM = 114;
    int MESSAGE = 115;
    int TRACK_PACKAGE = 116;
    int SHOW_REVIEW = 117;
    int REFER_TO_FRIEND = 118;
    int REPLACE_REFUND = 119;
    int WISHLIST = 120;
    int CANCEL_ORDER = 121;
    int FAVOURITE = 122;
    int SHOP_NOW = 123;
    int REVIEW_TO_PRODUCT = 124;
    int REVIEW_TO_OVERALL = 125;
    int SPECIAL_OFFERS = 126;
    int BANNER_CLICK = 127;
    int NOTIFY_UPCOMING_VENUE = 128;
    int SHIPPING_METHOD = 129;
    int SHIPPING_METHOD_UNCHEKED = 130;
    int ADD_TO_CART = 131;
    int HOSPITALITY_VENUE = 132;
    int SHOW_ALL_CATEGORY_HOSPITALITY = 133;
    int HOSPITALITY_PRODUCT = 134;
    int SIMILAR_PRODUCT = 135;
    int TOP_CAT_OF_MONTH = 136;
    int UPSELLING_PRODUCT = 137;
    int SELECTED_TIP = 138;
    int TOP_CATEGORY_PRODUCTS = 139;
    int CATEGORY_HOSPITALITY = 140;
    int CATEGORY_RETAIL = 141;
    int SELF_CHECKIN = 142;
    int ORDER_FOOD = 143;
    int NEW_BOOKING = 144;
    int AUTO_COMPLETE_CATEGORY = 145;
    int LOCAL_SHOP_CATEGORY = 146;
    int AUTO_COMPLETE_PRODUCT = 147;
    int AUTO_COMPLETE_VENUE = 148;
    int UNIVERSAL_CODE = 99999;

    ///////////////////////// ACTIVITY CALLBACK CODE  //////////////////////////////////
    int OPEN_CHANGE_ADDRESS_ACTIVITY = 201;
    int OPEN_LOYALTY_STAMP_VOUCHER_ACTIVITY = 202;
    int OPEN_PAYMENT_DETAIL_ACTIVITY = 203;
    int OPEN_PAYMENT_DETAIL_ADD_ACTIVITY = 204;
    int OPEN_PIN_VERIFY_ACTIVITY = 205;
    int OPEN_PIN_CREATE_ACTIVITY = 206;
    int OPEN_LOOKUP_ACTIVITY = 207;
    int OPEN_ADD_ADDRESS_ACTIVITY = 208;
    int OPEN_ACTIVE_PAYMENT_DETAIL_ADD_ACTIVITY = 209;
    int OPEN_ACTIVE_PAYMENT_LIST_ACTIVITY = 210;
    int OPEN_STRIPE_CARD_ACTIVITY_FOR_INFO = 211;
    int OPEN_ACTIVE_CARD_ACTIVITY_FOR_INFO = 212;
    int TAKE_NEW_PERSON_ACTIVITY_CALLBACK = 213;
    int OPEN_ACTIVITY_COUPON_CALLBACK_CODE = 214;
    int ADD_NEW_CARD_CHECKOUT_CALLBACK_CODE = 215;

    ///////////////////////// START ACTIVITY FOR RESULT CODE //////////////////////////////////

    String ADDRESS_ID = "address_id_";
    String ADDRESS_ACTIVITY_CALLBACK = "address_call";
    String LOYA_STAMP_VOUC_ACTIVITY_CALLBACK = "loyal_call";
    String PAYMENT_DETAIL_ACTIVITY_CALLBACK = "payment_call";
    String PIN_VERIFY_ACTIVITY_CALLBACK = "pin_verify";
    String PIN_CREATE_ACTIVITY_CALLBACK = "pin_create";
    String LOOKUP_ACTIVITY_CALLBACK_DATA = "lookup_data";
    String ADD_ADDRESS_ACTIVITY_CALLBACK = "add_address_call";
    String PAYMENT_DETAIL_ADD_INSTANT_CALLBACK = "add_instant_pay_call";
    String OPEN_ACTIVITY_COUPON_CALLBACK_DATA = "coupon_callback_data";


    ///////////////////////////////////////////////////////////////////////////////////////////////
    int ACTION_NO = -1;
    int Unprocessable_Entity = 422;

    /////////////////////////////////// CART KEYWORDS /////////////////////////////////////////

    String SHOW_RECENT_ACTIVITY = "showRecentActivity";
    DecimalFormat DECIMAL_FORMAT = new DecimalFormat("#0.00");
}
