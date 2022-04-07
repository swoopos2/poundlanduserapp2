package com.poundland.retail.apiUtils;

/**
 * Created by shakti on 10-11-2019....
 */

public class ApiRequestUrl {
 public static final String BASE_URL_POUNDLAND_USER = "https://swoopelocaltesting.com/poundlandbackoffice/public/";
    // public static final String BASE_URL = "http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/";  //FOR DEVELOPMENT TEAM
   public static final String BASE_URL = "https://swoopelocaltesting.com/admin/public/";// FOR TESTING TEAM NEW URL
  //  public static final String BASE_URL = "https://swooperetail.com/admin/public/";      //FOR LIVE


    public static final String BASE_URL_IMAGE_CATEGORY = BASE_URL + "uploaded/category_img/";
    public static final String BASE_URL_VENUE = BASE_URL + "uploaded/venues/";

    /*https://api.stripe.com/v1/customers/{customerId}/sources*/
    public static final String BASE_URL_STRIPE_ADD = "https://api.stripe.com/v1/";
    static final String ADD_STRIPE_CARD = "customers/{customerId}/sources";
    static final String GET_STRIPE_CARD = "customers/{customerId}/sources?object=card";

    static final String GET_STRIPE_CARD_SWOOPE = "api/swoopelocalapp/getUserCardDetails";
    static final String GET_ACTIVE_CARD = "api/swoopelocalapp/getActiveCard";
    static final String SAVE_USER_CARD_DETAILS = "api/swoopelocalapp/saveUserCardDetails";
    static final String DELETE_CARD_DETAILS = "api/swoopelocalapp/deleteUserCard";
    static final String SAVE_ECOMM_ORDER = "api/swoopelocalapp/customerOrderSave";              ///   CheckOut Api earlier was saveEcommOrder
    static final String RESERVATION_PAYMENT = "api/swoopelocalapp/reservation_payment";              ///   CheckOut Api reservation

    static final String LOG_IN_SOCIAL_URL = "api/swoopelocalapp/saveCustomerFromSocial";
    static final String LOG_IN_URL = "api/swoopelocalapp/signin";
    static final String GET_BRAND = "api/swoopelocalapp/getBrands";
    static final String SIGN_UP_URL = "api/swoopelocalapp/register";
    static final String OTP_URL = "api/swoopelocalapp/SendOtpValidation";        /// for manual registration time
    public static final String SEND_OTP = "api/swoopelocalapp/sendOTP/";         /// for social time
    static final String FORGOT_PASSWORD = "api/swoopelocalapp/create_token";
    static final String CHANGE_PASSWORD = "api/swoopelocalapp/changePassword";
    static final String DELETE_ACCOUNT = "api/swoopelocalapp/userLogOut";
    static final String GENERATE_PIN = "api/swoopelocalapp/generateCustPin";
    static final String SEND_PIN_TO_EMAIL = "api/swoopelocalapp/sendCustPinOnEmail";
    static final String UPDATE_PIN = "api/swoopelocalapp/updateCustPin";
    static final String VERIFY_MOBILE_NO = "api/swoopelocalapp/verifyMobileNumber";   // /// this is for social time
    static final String CHECK_USER_EXIST = "api/swoopelocalapp/checkSocialUserExists"; //checkUserExists  // /// this is for social time


    static final String FETCH_USER_NOTIFICATION = "api/swoopelocalapp/fetchUserNotifications";
    static final String GET_MODIFIER_BY_PRODUCT_ID = "api/swoopelocalapp/getModifiersByProductId";
    static final String DELETE_USER_NOTIFICATION = "api/swoopelocalapp/deleteNotifications";
    static final String DELETE_USER_MESSAGE = "api/swoopelocalapp/deleteMessage";   // delMessage
    static final String GET_USER_LOCATION = "api/swoopelocalapp/get_user_location";
    static final String GET_USER_DETAIL = "api/swoopelocalapp/getUserDetails";
    static final String UPDATE_USER_PROFILE = "api/swoopelocalapp/updateUserProfile";
    static final String GET_USER_ADDRESS = "api/swoopelocalapp/getUserAddress";
    static final String GET_FAQ = "api/swoopelocalapp/getFaq";
    static final String GET_ABOUT = "api/swoopelocalapp/getTermAndConditions/{id}";

    static final String FETCH_LANDING_PAGE_TOP_DATAS_V2 =    "api/swoopelocalapp/fetchLandingPageTopDataV2"   ; //
    static final String FETCH_LANDING_PAGE_BOTTOM_DATAS_V2 = "api/swoopelocalapp/fetchLandingPageBottomDataV2"; //

    static final String FETCH_LANDING_PAGE_DATAS = "api/swoopelocalapp/fetchLandingPageDatas";
    static final String GET_PRODUCT_BY_SEARCH = "api/swoopelocalapp/fetchProductBySearchV2";
    static final String GET_PRODUCT_BY_CATEGORY_ID = "api/swoopelocalapp/fetchProductsByCategoryIdV2";
    static final String FETCH_HEADER_SEARCH_DETAILS = "api/swoopelocalapp/fetchHeaderSearchDetails";

    static final String GET_ALL_CATEGORY = "api/swoopelocalapp/getAllCatWithSearch";
    static final String GET_ALL_PRODUCT_OFFERS = "api/swoopelocalapp/getAllProductOffers";
    static final String GET_SPECIAL_OFFERS = "api/swoopelocalapp/getSpecialOffer";
    static final String GET_SPECIAL_OFFERS_DETAILS = "api/swoopelocalapp/getSpecialOfferDetails";
    static final String GET_ALL_PRODUCT_BY_CATEGORY = "api/swoopelocalapp/getAllProductsByCat";

    static final String GET_VENUE_FILTER_DATA = "api/swoopelocalapp/getVenueFilterData";   // shows filter data in dialog on product list screen
    static final String GET_HOME_FILTER_DATA = "api/swoopelocalapp/getHomeFilterData";     // shows filter data on MainActivity/HomeFragment screen
    static final String GET_PRODUCT_FILTER_DATA = "api/swoopelocalapp/getProductsByFilter";// navigated by MainActivity Filter click show filter data on HOME FILTER ACTIVITY,
    static final String GET_ALL_VENUES = "api/swoopelocalapp/getAllVenues";
    static final String GET_UPCOMING_VENUES = "api/swoopelocalapp/upcomingvenues";
    static final String GET_UPCOMING_VENUE_DETAILS = "api/swoopelocalapp/upcomingvenueDetails";
    static final String GET_VENUE_DETAIL = "api/swoopelocalapp/getVenueDetails";
    static final String FETCH_RETAIL_VENUE_PRODUCTS = "api/swoopelocalapp/fetchRetailVenueProducts";
    static final String GET_VENUE_THEME = "api/swoopelocalapp/getVenueTheme";

    static final String GET_PRODUCT_DETAIL = "api/swoopelocalapp/getProductDetails";
    static final String FETCH_PRODUCT_DETAILS = "api/swoopelocalapp/fetchProductDetails";
    static final String PRODUCT_DETAIL_BY_MODIFIER = "api/swoopelocalapp/productDetailsByModifier";

    static final String GET_ALL_VENUES_HOSPITALITY = "api/swoopelocalapp/venue_list";     ///  Hospitality venue list
    static final String GET_RESERVATION_VENUE = "api/swoopelocalapp/getReservationVenue";     /// venue for restaurent book list
    static final String GET_HOSPITALITY_VENUE_DETAIL = "api/swoopelocalapp/hospitalityVenueDetail";
    static final String FETCH_HOSPITALITY_VENUE_PRODUCTS = "api/swoopelocalapp/fetchHopitalityVenueProducts";
    static final String RESERVATION_OPTION_DATA = "api/swoopelocalapp/reservation_option_data";
    static final String VENUE_RESERVATION = "api/swoopelocalapp/venue_reservation";
    static final String RESERVATION_SLOT_AVAILABLE = "api/swoopelocalapp/reservation_slotIsAvailable";
    static final String VENUE_RESERVATION_ADD_GUEST = "api/swoopelocalapp/venue_reservation_addguest";

    static final String GET_ORDER_DETAILS_BY_ID = "api/swoopelocalapp/getOrderDetailsById";
    static final String FETCH_PRODUCT_RATTING = "api/swoopelocalapp/fetchProductRatting";
    static final String MY_ORDER = "api/swoopelocalapp/getMyOrders";
    static final String MY_ORDER_RESERVATION = "api/swoopelocalapp/getMyReservationOrder";
    static final String CANCEL_ORDER = "api/swoopelocalapp/cancelOrder";
    static final String CANCEL_BOOKING = "api/swoopelocalapp/booking_cancel";
    static final String SELF_CHECKIN = "api/swoopelocalapp/selfCheckIn";
    static final String RETURN_ORDER = "api/swoopelocalapp/returnOrder";
    static final String NOTIFY_ME = "api/swoopelocalapp/outofstock_notify_me";
    static final String NOTIFY_ME_UPCOMING_VENUE_PRODUCT = "api/swoopelocalapp/upcomimg_notify_me";
    static final String SAVE_PRODUCT_RATING = "api/swoopelocalapp/saveProductRatings";
    static final String SAVE_REVIEW_RATING_OVERALL = "api/swoopelocalapp/saveReviewRatings";
    static final String GET_TODAY_COUPON = "api/swoopelocalapp/getTodayCoupon";
    static final String APPLY_COUPON = "api/swoopelocalapp/applyCoupon";

    static final String GET_CUSTOMER_LOYALTY = "api/swoopelocalapp/getCustomerLoyalty";
    static final String GET_REVIEW = "api/swoopelocalapp/getProductRatings";

    static final String SAVE_USER_LOCATION = "api/swoopelocalapp/save_user_location";
    static final String UPDATE_USER_LOCATION = "api/swoopelocalapp/update_user_location";
    static final String DELETE_USER_LOCATION = "api/swoopelocalapp/deleteUserAddress";

    static final String GET_TOTAL_CART = "api/swoopelocalapp/getTotalCarts";
    static final String ADD_TO_CART_HOSPITALITY = "api/swoopelocalapp/hospitality_addCart";// Add hospitality product to cart
    static final String ADD_TO_CART = "api/swoopelocalapp/AddEcomCart";// addCarts  earlier endpoint
    static final String ADD_ECOM_CART_CASE = "api/swoopelocalapp/AddEcomCartCase";
    static final String ADD_TO_CART_COMBO = "api/swoopelocalapp/addMultipleCarts";
    static final String GET_CART_WITH_SUMMARY = "api/swoopelocalapp/GetCartWithSummary";

    static final String GET_CART_WITH_SUMMARY_HOSPITALITY = "api/swoopelocalapp/hospitality_GetCartWithSummary";
    static final String RESERVATION_CHECKOUT = "api/swoopelocalapp/reservation_checkout";
    static final String ADD_MOBILE_CART_QUANTITY = "api/swoopelocalapp/addMobileCartQuantity";
    static final String UPDATE_CART_QUANTITY = "api/swoopelocalapp/update_cart_quantity";
    static final String UPDATE_CART_QUANTITIES = "api/swoopelocalapp/updateCartQuantities"; // for update quantity of product on product list of hosp venue detail
    static final String DELETE_CART_HOSPITALITY = "api/swoopelocalapp/delete_cart";
    static final String DELETE_MOBILE_CART_QUANTITY = "api/swoopelocalapp/deleteMobileCart";

    static final String LIKE_DISLIKE_VENUE = "api/swoopelocalapp/likeDislikeVenue";
    static final String LIKE_DISLIKE_PRODUCT = "api/swoopelocalapp/LikeDislikeProduct";
    static final String GET_WISHLIST_PRODUCT = "api/swoopelocalapp/getWishlists";    ////get Wishlisted product
    static final String GET_WISHLIST_VENUES = "api/swoopelocalapp/getLikedVenues";   ////get Wishlisted venues
    static final String GET_MATCH_WISHLIST_PRODUCTS = "api/swoopelocalapp/getMatchWishlistProducts";
    static final String GET_SHIPPING_METHOD = "api/swoopelocalapp/GetShippingMethod";

    static final String GET_ALL_MESSAGE_ECOM = "api/swoopelocalapp/getallMessageEcomm";
    static final String SEND_MESSAGE = "api/swoopelocalapp/sendMessage";
    static final String MESSAGE_DETAILS = "api/swoopelocalapp/messageDetails"; ////  messageDetails?page=1  /// all message from same venue and order
    static final String GET_CUSTOMER_VOUCHER = "api/swoopelocalapp/getCustomerVoucher";
    static final String CLAIM_STORE = "api/swoopelocalapp/upcomimg_claim_store";
    static final String FETCH_ORDER_BY_POS_ORDER_ID = "api/swoopelocalapp/fetchOrdersByPosOrderId";
    static final String QR_CODE_PAYMENT = "api/swoopelocalapp/qrCodePayment";
}
