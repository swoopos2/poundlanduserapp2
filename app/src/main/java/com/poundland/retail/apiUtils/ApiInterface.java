package com.poundland.retail.apiUtils;


import com.poundland.retail.model.requestModel.AddMobileCartQuantityRequestModel;
import com.poundland.retail.model.requestModel.AddToCartComboRequestModel;
import com.poundland.retail.model.requestModel.AddToCartHospitalityRequestModel;
import com.poundland.retail.model.requestModel.AddToCartRequestModel;
import com.poundland.retail.model.requestModel.AddressRequestModel;
import com.poundland.retail.model.requestModel.AllProductByCategoryRequestModel;
import com.poundland.retail.model.requestModel.AllVenuesHospitalityRequestModel;
import com.poundland.retail.model.requestModel.AllVenuesRequestModel;
import com.poundland.retail.model.requestModel.CancelOrderRequestModel;
import com.poundland.retail.model.requestModel.CancelreservationRequestModel;
import com.poundland.retail.model.requestModel.ChangePasswordRequestModel;
import com.poundland.retail.model.requestModel.CheckOutRequestModel;
import com.poundland.retail.model.requestModel.CheckoutCartHospitalityRequestModel;
import com.poundland.retail.model.requestModel.ClaimStoreRequestModel;
import com.poundland.retail.model.requestModel.CouponRequestModel;
import com.poundland.retail.model.requestModel.DeleteSavedCardRequestModel;
import com.poundland.retail.model.requestModel.FetchPOSorderRequestModel;
import com.poundland.retail.model.requestModel.GetBrandResponseModel;
import com.poundland.retail.model.requestModel.GetCartWithSummaryRequestModel;
import com.poundland.retail.model.requestModel.GetRattingRequestModel;
import com.poundland.retail.model.requestModel.HeaderSearchRequestModel;
import com.poundland.retail.model.requestModel.LandingPageRequestModel;
import com.poundland.retail.model.requestModel.LikeDislikeProductRequestModel;
import com.poundland.retail.model.requestModel.LoginRequestModel;
import com.poundland.retail.model.requestModel.MessageDetailRequestModel;
import com.poundland.retail.model.requestModel.NotificationDeleteRequestModel;
import com.poundland.retail.model.requestModel.NotifyUpcomingVenueProductReq;
import com.poundland.retail.model.requestModel.PinRequestModel;
import com.poundland.retail.model.requestModel.ProductByCategoryRequestModel;
import com.poundland.retail.model.requestModel.ProductBySearchRequestModel;
import com.poundland.retail.model.requestModel.ProductDetailByModifierRequestModel;
import com.poundland.retail.model.requestModel.QRCodePayRequestModel;
import com.poundland.retail.model.requestModel.ReservationGuestReqModel;
import com.poundland.retail.model.requestModel.ReservationPaymentRequestModel;
import com.poundland.retail.model.requestModel.ReservationSlotAvailRequestModel;
import com.poundland.retail.model.requestModel.ReservationVenuesRequestModel;
import com.poundland.retail.model.requestModel.ReturnRequestModel;
import com.poundland.retail.model.requestModel.SaveLocationRequestModel;
import com.poundland.retail.model.requestModel.SaveReviewRatingRequestModel;
import com.poundland.retail.model.requestModel.SendMessageRequestModel;
import com.poundland.retail.model.requestModel.ShippingMethodRequestModel;
import com.poundland.retail.model.requestModel.SignUpRequestModel;
import com.poundland.retail.model.requestModel.SingleProductDetailRequestModel;
import com.poundland.retail.model.requestModel.SingleVenueDetailRequestModel;
import com.poundland.retail.model.requestModel.StripeAddCardRequestModel;
import com.poundland.retail.model.requestModel.UpcomingVenueDetailReqModel;
import com.poundland.retail.model.requestModel.VenueFilterDataRequestModel;
import com.poundland.retail.model.requestModel.VenueReservatioRequestModel;
import com.poundland.retail.model.responseModel.AboutResponseModel;
import com.poundland.retail.model.responseModel.ActivePayResponseModel;
import com.poundland.retail.model.responseModel.AddGuestResponseModel;
import com.poundland.retail.model.responseModel.AddToCartResponseModel;
import com.poundland.retail.model.responseModel.AddressResponseModel;
import com.poundland.retail.model.responseModel.AllCategoryResponseModel;
import com.poundland.retail.model.responseModel.AllProductByCategoryResponseModel;
import com.poundland.retail.model.responseModel.AllVenueHospitalityResponseModel;
import com.poundland.retail.model.responseModel.AllVenueResponseModel;
import com.poundland.retail.model.responseModel.CheckOutResponseModel;
import com.poundland.retail.model.responseModel.CheckoutCartHospitalityResponseModel;
import com.poundland.retail.model.responseModel.ClaimStoreresponseModel;
import com.poundland.retail.model.responseModel.CommonResponseModel;
import com.poundland.retail.model.responseModel.CouponResponseModel;
import com.poundland.retail.model.responseModel.DeleteMessageResponseModel;
import com.poundland.retail.model.responseModel.FaqResponseModel;
import com.poundland.retail.model.responseModel.FetchPOSorderResponseModel;
import com.poundland.retail.model.responseModel.GetAllMessageEcomResponseModel;
import com.poundland.retail.model.responseModel.GetCartSummaryHospResponseModel;
import com.poundland.retail.model.responseModel.GetCartWithSummaryResponseModel;
import com.poundland.retail.model.responseModel.GetCustomerLoyaltyResponseModel;
import com.poundland.retail.model.responseModel.GetCustomerVouchersResponseModel;
import com.poundland.retail.model.responseModel.GetMatchWishlistProductResponseModel;
import com.poundland.retail.model.responseModel.GetOrderByIdResponseModel;
import com.poundland.retail.model.responseModel.GetRattingResponseModel;
import com.poundland.retail.model.responseModel.GetStripeCardDBModel;
import com.poundland.retail.model.responseModel.GetTotalCartResponseModel;
import com.poundland.retail.model.responseModel.GetUserLocationResponseModel;
import com.poundland.retail.model.responseModel.GetWishListResponseModel;
import com.poundland.retail.model.responseModel.HeaderSearchResponseModel;
import com.poundland.retail.model.responseModel.HomeFilterResponseModel;
import com.poundland.retail.model.responseModel.HospitalityVenueDetailResponseModel;
import com.poundland.retail.model.responseModel.HospitalityVenueProductResponseModel;
import com.poundland.retail.model.responseModel.LandingPageBottomResponseModel;
import com.poundland.retail.model.responseModel.LandingPageResponseModel;
import com.poundland.retail.model.responseModel.LandingPageTopResponseModel;
import com.poundland.retail.model.responseModel.LikeDislikeProductResponseModel;
import com.poundland.retail.model.responseModel.LoginResponseModel;
import com.poundland.retail.model.responseModel.MessageDetailResponseModel;
import com.poundland.retail.model.responseModel.ModifierListByProductIdModel;
import com.poundland.retail.model.responseModel.MyOrderReservationResponseModel;
import com.poundland.retail.model.responseModel.MyOrderResponseModel;
import com.poundland.retail.model.responseModel.NearByDealsResponseModel;
import com.poundland.retail.model.responseModel.NotificationResponseModel;
import com.poundland.retail.model.responseModel.NotifyMeResponseModel;
import com.poundland.retail.model.responseModel.NotifyUpcomingVenueProductResp;
import com.poundland.retail.model.responseModel.PinResponseModel;
import com.poundland.retail.model.responseModel.ProductByCategoryResponseModel;
import com.poundland.retail.model.responseModel.ProductBySearchResponseModel;
import com.poundland.retail.model.responseModel.ProductDetailByModifierResponseModel;
import com.poundland.retail.model.responseModel.QRCodePayResponseModel;
import com.poundland.retail.model.responseModel.ReservationCheckoutResponseModel;
import com.poundland.retail.model.responseModel.ReservationOptionDataResponseModel;
import com.poundland.retail.model.responseModel.ReservationPaymentResponseModel;
import com.poundland.retail.model.responseModel.ReservationSlotAvailResponseModel;
import com.poundland.retail.model.responseModel.ReservationVenuesResponseModel;
import com.poundland.retail.model.responseModel.ReviewResponseModel;
import com.poundland.retail.model.responseModel.SaveLocationResponseModel;
import com.poundland.retail.model.responseModel.SaveUserCardDetailsResponseModel;
import com.poundland.retail.model.responseModel.SendMessageResponseModel;
import com.poundland.retail.model.responseModel.SendOtpResponseModel;
import com.poundland.retail.model.responseModel.ShippingMethodResponseModel;
import com.poundland.retail.model.responseModel.SignUpResponseModel;
import com.poundland.retail.model.responseModel.SingleProductDetailResponseModel;
import com.poundland.retail.model.responseModel.SingleVenueDetailResponseModel;
import com.poundland.retail.model.responseModel.SocialMediaResponseModel;
import com.poundland.retail.model.responseModel.SpecialOfferDetailsResponseModel;
import com.poundland.retail.model.responseModel.SpecialOffersResponseModel;
import com.poundland.retail.model.responseModel.StripeAddCardResponseModel;
import com.poundland.retail.model.responseModel.StripeGetCardDetailsResponseModel;
import com.poundland.retail.model.responseModel.UpcomingVenueDetailResponseModel;
import com.poundland.retail.model.responseModel.UpcomingVenueResponseModel;
import com.poundland.retail.model.responseModel.UserDetailResponseModel;
import com.poundland.retail.model.responseModel.VenueDetailResponseModel;
import com.poundland.retail.model.responseModel.VenueFilterDataResponseModel;
import com.poundland.retail.model.responseModel.VenueReservationResponseModel;
import com.poundland.retail.model.responseModel.VenueThemeResponse;
import com.poundland.retail.model.responseModel.WishlistedProductResponseModel;
import com.poundland.retail.zzznewPoundland.model.FetchCustomerRequest;
import com.poundland.retail.zzznewPoundland.model.RegistrationRequestModel;
import com.poundland.retail.zzznewPoundland.model.RegistrationResponseModel;
import com.poundland.retail.zzznewPoundland.model.UpdateLoyaltyPointsRequest;
import com.poundland.retail.zzznewPoundland.model.UpdateLoyaltyPointsResponse;
import com.poundland.retail.zzznewPoundland.model.UserLoginResponseModel;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface ApiInterface {

    @POST(ApiRequestUrl.LOG_IN_SOCIAL_URL)
    Call<SocialMediaResponseModel> saveCustomerFromSocial(@Body SignUpRequestModel body);

    @POST(ApiRequestUrl.LOG_IN_URL)
    Call<LoginResponseModel> userLoginApi(@Body LoginRequestModel body);


    @POST(ApiRequestUrl.CHECK_USER_EXIST)
    Call<LoginResponseModel> checkUserExist(@Body LoginRequestModel body);

    @POST(ApiRequestUrl.SIGN_UP_URL)
    Call<SignUpResponseModel> userSignUpApi(@Body SignUpRequestModel body);

    @POST(ApiRequestUrl.OTP_URL)
    Call<SendOtpResponseModel> OtpApi(@Body SignUpRequestModel body);

    @POST(ApiRequestUrl.VERIFY_MOBILE_NO)
    Call<SendOtpResponseModel> verifyMobileNo(@Body SignUpRequestModel body);

    @POST(ApiRequestUrl.SEND_PIN_TO_EMAIL)
    Call<PinResponseModel> sendCustPinToEmail(@Header("token") String authorization, @Header("username") String username);

    @POST(ApiRequestUrl.GENERATE_PIN)
    Call<PinResponseModel> generateCustPin(@Header("token") String authorization, @Header("username") String username, @Body PinRequestModel body);

    @POST(ApiRequestUrl.UPDATE_PIN)
    Call<PinResponseModel> updatePin(@Header("token") String authorization, @Header("username") String username, @Body ChangePasswordRequestModel body);

    @POST
    Call<PinResponseModel> sendOTP(@Url String url);

    @POST(ApiRequestUrl.FORGOT_PASSWORD)
    Call<CommonResponseModel> forgotPassword(@Body LoginRequestModel body);

    @POST(ApiRequestUrl.CHANGE_PASSWORD)
    Call<AboutResponseModel> changePassword(@Header("token") String authorization, @Header("username") String username, @Body ChangePasswordRequestModel body);

    @POST(ApiRequestUrl.DELETE_ACCOUNT)
    Call<AboutResponseModel> deleteAccount(@Header("token") String authorization, @Header("username") String username, @Body ChangePasswordRequestModel body);

    @POST(ApiRequestUrl.GET_PRODUCT_BY_SEARCH)
        /// api in home page
    Call<ProductBySearchResponseModel> getProductBySearch(@Header("token") String authorization, @Header("username") String username,
                                                          @Body ProductBySearchRequestModel body);

    @POST(ApiRequestUrl.FETCH_LANDING_PAGE_DATAS)
    Call<LandingPageResponseModel> fetchLandingPageDatas(@Header("token") String authorization, @Header("username") String username,
                                                         @Body LandingPageRequestModel body);

    @POST(ApiRequestUrl.FETCH_LANDING_PAGE_TOP_DATAS_V2)
    Call<LandingPageTopResponseModel> fetchLandingPageDatasV2Top(@Header("token") String authorization, @Header("username") String username,
                                                                 @Body LandingPageRequestModel body);

    @POST(ApiRequestUrl.FETCH_LANDING_PAGE_BOTTOM_DATAS_V2)
    Call<LandingPageBottomResponseModel> fetchLandingPageDatasV2Bottom(@Header("token") String authorization, @Header("username") String username,
                                                                       @Body LandingPageRequestModel body);


    @POST(ApiRequestUrl.GET_PRODUCT_BY_SEARCH)
        // api in category details
    Call<ProductByCategoryResponseModel> getProductBySearch_inCategoryDetail(@Header("token") String authorization, @Header("username") String username, @Body ProductBySearchRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.FETCH_HEADER_SEARCH_DETAILS)
        // api in category details
    Call<HeaderSearchResponseModel> fetchHeaderSearchDetails(@Header("token") String authorization, @Header("username") String username, @Body HeaderSearchRequestModel body);

    @POST(ApiRequestUrl.GET_PRODUCT_BY_CATEGORY_ID)
    Call<ProductByCategoryResponseModel> getProductByCategoryId(@Header("token") String authorization, @Header("username") String username, @Body ProductByCategoryRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_ALL_CATEGORY)
    Call<AllCategoryResponseModel> getAllCatWithSearch(@Header("token") String authorization, @Header("username") String username, @Body ProductBySearchRequestModel body);


    @POST(ApiRequestUrl.GET_ALL_PRODUCT_OFFERS)
    Call<NearByDealsResponseModel> getAllProductOffers(@Header("token") String authorization, @Header("username") String username, @Body ProductBySearchRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_SPECIAL_OFFERS)
    Call<SpecialOffersResponseModel> getSPecialOffers(@Header("token") String authorization, @Header("username") String username, @Body ProductBySearchRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_ALL_VENUES)
    Call<AllVenueResponseModel> getAllVenues(@Header("token") String authorization, @Header("username") String username, @Body AllVenuesRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_ALL_VENUES_HOSPITALITY)
    Call<AllVenueHospitalityResponseModel> venue_list_Hospitality(@Header("token") String authorization, @Header("username") String username, @Body AllVenuesHospitalityRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_RESERVATION_VENUE)
    Call<ReservationVenuesResponseModel> getReservationVenue(@Header("token") String authorization, @Header("username") String username, @Body ReservationVenuesRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_VENUE_DETAIL)
    Call<VenueDetailResponseModel> getVenueDetail(@Header("token") String authorization, @Header("username") String username, @Body SingleVenueDetailRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.FETCH_RETAIL_VENUE_PRODUCTS)
    Call<SingleVenueDetailResponseModel> getVenueDetailProduct(@Header("token") String authorization, @Header("username") String username, @Body SingleVenueDetailRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_VENUE_THEME)
    Call<VenueThemeResponse> getVenueTheme(@Body SingleVenueDetailRequestModel body);


    @POST(ApiRequestUrl.GET_HOSPITALITY_VENUE_DETAIL)
    Call<HospitalityVenueDetailResponseModel> hospitalityVenueDetail(@Header("token") String authorization, @Header("username") String username, @Body SingleVenueDetailRequestModel body, @Query("page") String page);


    @POST(ApiRequestUrl.FETCH_HOSPITALITY_VENUE_PRODUCTS)
    Call<HospitalityVenueProductResponseModel> hospitalityVenueProducts(@Header("token") String authorization, @Header("username") String username, @Body SingleVenueDetailRequestModel body, @Query("page") String page);


    @POST(ApiRequestUrl.GET_UPCOMING_VENUE_DETAILS)
    Call<UpcomingVenueDetailResponseModel> getUpcomingVenueDetail(@Header("token") String authorization, @Header("username") String username, @Body UpcomingVenueDetailReqModel body);

    @POST(ApiRequestUrl.GET_PRODUCT_DETAIL)
    Call<SingleProductDetailResponseModel> getProductDetail(@Header("token") String authorization, @Header("username") String username, @Body SingleProductDetailRequestModel body);

    @POST(ApiRequestUrl.FETCH_PRODUCT_DETAILS)
    Call<SingleProductDetailResponseModel> fetchProductDetails(@Header("token") String authorization, @Header("username") String username, @Body SingleProductDetailRequestModel body);

    @POST(ApiRequestUrl.GET_SPECIAL_OFFERS_DETAILS)
    Call<SpecialOfferDetailsResponseModel> getSpecialOfferDetails(@Header("token") String authorization, @Header("username") String username, @Body SingleProductDetailRequestModel body);

    @POST(ApiRequestUrl.GET_ALL_PRODUCT_BY_CATEGORY)
    Call<AllProductByCategoryResponseModel> getAllProductByCategory(@Header("token") String authorization, @Header("username") String username,
                                                                    @Body AllProductByCategoryRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_PRODUCT_FILTER_DATA)
        // On HomeFilter Activity
    Call<HomeFilterResponseModel> getProductsByFilter(@Header("token") String authorization, @Header("username") String username,
                                                      @Body AllProductByCategoryRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.PRODUCT_DETAIL_BY_MODIFIER)
    Call<ProductDetailByModifierResponseModel> getProductDetailByModifier(@Header("token") String authorization, @Header("username") String username, @Body ProductDetailByModifierRequestModel body);

    @POST(ApiRequestUrl.GET_VENUE_FILTER_DATA)
    Call<VenueFilterDataResponseModel> getVenueFilterData(@Header("token") String authorization, @Header("username") String username, @Body VenueFilterDataRequestModel body);

    @POST(ApiRequestUrl.GET_BRAND)
    Call<GetBrandResponseModel> getBrand(@Header("token") String authorization, @Header("username") String username, @Body ProductDetailByModifierRequestModel body);

    @POST(ApiRequestUrl.GET_HOME_FILTER_DATA)
    Call<VenueFilterDataResponseModel> getHomeFilterData(@Header("token") String authorization, @Header("username") String username, @Body VenueFilterDataRequestModel body);

    @POST(ApiRequestUrl.ADD_TO_CART_HOSPITALITY)
    Call<AddToCartResponseModel> hospitality_addCart(@Header("token") String authorization, @Header("username") String username, @Body AddToCartHospitalityRequestModel body);

    @POST(ApiRequestUrl.ADD_TO_CART)
    Call<AddToCartResponseModel> addToCart(@Header("token") String authorization, @Header("username") String username, @Body AddToCartRequestModel body);

    @POST(ApiRequestUrl.ADD_ECOM_CART_CASE)
    Call<AddToCartResponseModel> addToCartBulk(@Header("token") String authorization, @Header("username") String username, @Body AddToCartRequestModel body);

    @POST(ApiRequestUrl.ADD_TO_CART_COMBO)
    Call<AddToCartResponseModel> addMultipleCarts(@Header("token") String authorization, @Header("username") String username, @Body AddToCartComboRequestModel body);

    @GET(ApiRequestUrl.GET_TOTAL_CART)
    Call<GetTotalCartResponseModel> getTotalCart(@Header("token") String authorization, @Header("username") String username);

    @POST(ApiRequestUrl.RESERVATION_CHECKOUT)
    Call<ReservationCheckoutResponseModel> reservation_checkout(@Header("token") String authorization, @Header("username") String username, @Body GetCartWithSummaryRequestModel body);

    @POST(ApiRequestUrl.GET_CART_WITH_SUMMARY_HOSPITALITY)
    Call<GetCartSummaryHospResponseModel> hospitality_GetCartWithSummary(@Header("token") String authorization, @Header("username") String username, @Body GetCartWithSummaryRequestModel body);

    @POST(ApiRequestUrl.UPDATE_CART_QUANTITY)
    Call<GetCartSummaryHospResponseModel> updateCartQuantity(@Header("token") String authorization, @Header("username") String username, @Body AddMobileCartQuantityRequestModel body);


    @POST(ApiRequestUrl.UPDATE_CART_QUANTITIES)
    Call<GetCartSummaryHospResponseModel> updateCartQuantities(@Header("token") String authorization, @Header("username") String username, @Body AddMobileCartQuantityRequestModel body);

    @POST(ApiRequestUrl.DELETE_CART_HOSPITALITY)
    Call<GetCartSummaryHospResponseModel> deleteCart(@Header("token") String authorization, @Header("username") String username, @Body AddMobileCartQuantityRequestModel body);

    @POST(ApiRequestUrl.GET_CART_WITH_SUMMARY)
    Call<GetCartWithSummaryResponseModel> getCartWithSummary(@Header("token") String authorization, @Header("username") String username, @Body GetCartWithSummaryRequestModel body);

    @POST(ApiRequestUrl.ADD_MOBILE_CART_QUANTITY)
    Call<GetCartWithSummaryResponseModel> addMobileCartQuantity(@Header("token") String authorization, @Header("username") String username, @Body AddMobileCartQuantityRequestModel body);

    @POST(ApiRequestUrl.DELETE_MOBILE_CART_QUANTITY)
    Call<GetCartWithSummaryResponseModel> deleteMobileCartQuantity(@Header("token") String authorization, @Header("username") String username, @Body AddMobileCartQuantityRequestModel body);

    @POST(ApiRequestUrl.GET_USER_ADDRESS)
    Call<AddressResponseModel> getUserAddress(@Header("token") String authorization, @Header("username") String username, @Body AddressRequestModel body);

    @POST(ApiRequestUrl.GET_USER_DETAIL)
    Call<UserDetailResponseModel> getUserDetail(@Header("token") String authorization, @Header("username") String username);

    @POST(ApiRequestUrl.LIKE_DISLIKE_PRODUCT)
    Call<LikeDislikeProductResponseModel> likeDislikeProduct(@Header("token") String authorization, @Header("username") String username, @Body LikeDislikeProductRequestModel body);

    @POST(ApiRequestUrl.LIKE_DISLIKE_VENUE)
    Call<LikeDislikeProductResponseModel> likeDislikeVenue(@Header("token") String authorization, @Header("username") String username, @Body LikeDislikeProductRequestModel body);

    @POST(ApiRequestUrl.GET_WISHLIST_PRODUCT)
    Call<GetWishListResponseModel> getWishlists(@Header("token") String authorization, @Header("username") String username, @Body GetCartWithSummaryRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_WISHLIST_VENUES)
    Call<WishlistedProductResponseModel> getWishlistVenues(@Header("token") String authorization, @Header("username") String username, @Body GetCartWithSummaryRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_MATCH_WISHLIST_PRODUCTS)
    Call<GetMatchWishlistProductResponseModel> getMatchWishlistProduct(@Header("token") String authorization, @Header("username") String username, @Body AddressRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_ALL_MESSAGE_ECOM)
        /// provide data for all venue's message for message Activity
    Call<GetAllMessageEcomResponseModel> getAllMessageEcom(@Header("token") String authorization, @Header("username") String username, @Query("page") String type);

    @POST(ApiRequestUrl.SEND_MESSAGE)
    Call<SendMessageResponseModel> sendMessage(@Header("token") String authorization, @Header("username") String username, @Body SendMessageRequestModel body);

    @POST(ApiRequestUrl.MESSAGE_DETAILS)
        /// provide data for single venue's message for chat Activity
    Call<MessageDetailResponseModel> messageDetails(@Header("token") String authorization, @Header("username") String username, @Body MessageDetailRequestModel body, @Query("page") String type);

    @POST(ApiRequestUrl.MY_ORDER)
    Call<MyOrderResponseModel> getMyOrder(@Header("token") String authorization, @Header("username") String username, @Body AddressRequestModel body, @Query("page") String type);

    @POST(ApiRequestUrl.MY_ORDER_RESERVATION)
    Call<MyOrderReservationResponseModel> getMyReservationOrder(@Header("token") String authorization, @Header("username") String username, @Query("page") String type);

    @POST(ApiRequestUrl.FETCH_PRODUCT_RATTING)
    Call<GetOrderByIdResponseModel> fetchProductRatting(@Header("token") String authorization, @Header("username") String username, @Body MessageDetailRequestModel body);


    @POST(ApiRequestUrl.GET_ORDER_DETAILS_BY_ID)
    Call<GetRattingResponseModel> getOrderDetailsById(@Header("token") String authorization, @Header("username") String username, @Body GetRattingRequestModel body);

    @POST(ApiRequestUrl.RESERVATION_OPTION_DATA)
    Call<ReservationOptionDataResponseModel> reservation_option_data(@Header("token") String authorization, @Header("username") String username, @Body CancelOrderRequestModel body);

    @POST(ApiRequestUrl.VENUE_RESERVATION)
    Call<VenueReservationResponseModel> venue_reservation(@Header("token") String authorization, @Header("username") String username, @Body VenueReservatioRequestModel body);

    @POST(ApiRequestUrl.RESERVATION_SLOT_AVAILABLE)
    Call<ReservationSlotAvailResponseModel> reservation_slotIsAvailable(@Header("token") String authorization, @Header("username") String username, @Body ReservationSlotAvailRequestModel body);

    @POST(ApiRequestUrl.VENUE_RESERVATION_ADD_GUEST)
    Call<AddGuestResponseModel> venue_reservation_addguest(@Header("token") String authorization, @Header("username") String username, @Body ReservationGuestReqModel body);

    @POST(ApiRequestUrl.CANCEL_ORDER)
    Call<MyOrderResponseModel> cancelOrder(@Header("token") String authorization, @Header("username") String username, @Body CancelOrderRequestModel body);

    @POST(ApiRequestUrl.CANCEL_BOOKING)
    Call<MyOrderResponseModel> cancelBooking(@Header("token") String authorization, @Header("username") String username, @Body CancelreservationRequestModel body);

    @POST(ApiRequestUrl.SELF_CHECKIN)
    Call<MyOrderResponseModel> selfCheckIn(@Header("token") String authorization, @Header("username") String username, @Body CancelreservationRequestModel body);

    @POST(ApiRequestUrl.RETURN_ORDER)
    Call<MyOrderResponseModel> returnOrder(@Header("token") String authorization, @Header("username") String username, @Body ReturnRequestModel body);

    @POST(ApiRequestUrl.NOTIFY_ME)
    Call<NotifyMeResponseModel> notifyMe(@Header("token") String authorization, @Header("username") String username, @Body AddressRequestModel body);

    @POST(ApiRequestUrl.NOTIFY_ME_UPCOMING_VENUE_PRODUCT)
    Call<NotifyUpcomingVenueProductResp> notifyUpcomingVenueProduct(@Header("token") String authorization, @Header("username") String username, @Body NotifyUpcomingVenueProductReq body);

    @POST(ApiRequestUrl.SAVE_PRODUCT_RATING)
    Call<NotifyMeResponseModel> saveReviewRating(@Header("token") String authorization, @Header("username") String username, @Body SaveReviewRatingRequestModel body);

    @POST(ApiRequestUrl.SAVE_REVIEW_RATING_OVERALL)
    Call<NotifyMeResponseModel> saveRatingOverAll(@Header("token") String authorization, @Header("username") String username, @Body SaveReviewRatingRequestModel body);

    @POST(ApiRequestUrl.GET_TODAY_COUPON)
    Call<CouponResponseModel> getTodayCoupon(@Header("token") String authorization, @Header("username") String username, @Body CouponRequestModel body);


    @POST(ApiRequestUrl.APPLY_COUPON)
    Call<CouponResponseModel> applyCoupon(@Header("token") String authorization, @Header("username") String username, @Body CouponRequestModel body);

    @POST(ApiRequestUrl.GET_CUSTOMER_LOYALTY)
    Call<GetCustomerLoyaltyResponseModel> getLoyalty(@Header("token") String authorization, @Header("username") String username,
                                                     @Body AllVenuesRequestModel body, @Query("page") String page);

    @POST(ApiRequestUrl.GET_REVIEW)
    Call<ReviewResponseModel> getReview(@Header("token") String authorization, @Header("username") String username, @Body CancelOrderRequestModel body);

    @POST(ApiRequestUrl.SAVE_USER_LOCATION)
    Call<SaveLocationResponseModel> saveUserLocation(@Header("token") String authorization, @Header("username") String username, @Body SaveLocationRequestModel body);

    @POST(ApiRequestUrl.UPDATE_USER_LOCATION)
    Call<SaveLocationResponseModel> updateUserLocation(@Header("token") String authorization, @Header("username") String username, @Body SaveLocationRequestModel body);

    @POST(ApiRequestUrl.DELETE_USER_LOCATION)
    Call<SaveLocationResponseModel> deleteLocation(@Header("token") String authorization, @Header("username") String username, @Body SaveLocationRequestModel body);


    @GET(ApiRequestUrl.GET_FAQ)
    Call<FaqResponseModel> getFaq();

    @GET(ApiRequestUrl.GET_ABOUT)
    Call<AboutResponseModel> getAboutUs(@Path("id") String username);

    @Multipart
    @POST(ApiRequestUrl.UPDATE_USER_PROFILE)
    Call<LoginResponseModel> updateProfile(@Header("token") String authorization, @Header("username") String username,
                                           @Part MultipartBody.Part image,
                                           @Part("firstname") RequestBody firstname,
                                           @Part("lastname") RequestBody lastname,
                                           @Part("email") RequestBody email,
                                           @Part("contact_no") RequestBody contact_no,
                                           @Part("otp") RequestBody otp
    );

    @FormUrlEncoded
    @POST(ApiRequestUrl.ADD_STRIPE_CARD)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<StripeAddCardResponseModel> addStripeCard(@Header("Authorization") String authorization, @Path("customerId") String username, @Field("source") String source);

    @GET(ApiRequestUrl.GET_STRIPE_CARD)
    @Headers("Content-Type: application/x-www-form-urlencoded")
    Call<StripeGetCardDetailsResponseModel> getStripeCard(@Header("Authorization") String authorization, @Path("customerId") String username);

    @POST(ApiRequestUrl.GET_STRIPE_CARD_SWOOPE)
    Call<GetStripeCardDBModel> getStripeCardSwoope(@Header("token") String authorization, @Header("username") String username, @Body StripeAddCardRequestModel requestModel);

    @POST(ApiRequestUrl.GET_ACTIVE_CARD)
    Call<ActivePayResponseModel> getActiveCard(@Header("token") String authorization, @Header("username") String username);

    @POST(ApiRequestUrl.SAVE_USER_CARD_DETAILS)
    Call<SaveUserCardDetailsResponseModel> saveUserCardDetails(@Header("token") String authorization, @Header("username") String username, @Body StripeAddCardRequestModel body);

    @POST(ApiRequestUrl.DELETE_CARD_DETAILS)
    Call<SaveUserCardDetailsResponseModel> deleteUserCardDetails(@Header("token") String authorization, @Header("username") String username, @Body DeleteSavedCardRequestModel body);

    @POST(ApiRequestUrl.SAVE_ECOMM_ORDER)
    Call<CheckOutResponseModel> saveEcommOrder(@Header("token") String authorization, @Header("username") String username, @Body CheckOutRequestModel body);

    @POST(ApiRequestUrl.SAVE_ECOMM_ORDER)
    Call<CheckoutCartHospitalityResponseModel> saveEcommOrderHospitality(@Header("token") String authorization, @Header("username") String username, @Body CheckoutCartHospitalityRequestModel body);

    @POST(ApiRequestUrl.RESERVATION_PAYMENT)
    Call<ReservationPaymentResponseModel> reservation_payment(@Header("token") String authorization, @Header("username") String username, @Body ReservationPaymentRequestModel body);

    @POST(ApiRequestUrl.FETCH_USER_NOTIFICATION)
    Call<NotificationResponseModel> fetchNotification(@Header("token") String authorization, @Header("username") String username, @Query("page") String page);

    @POST(ApiRequestUrl.GET_MODIFIER_BY_PRODUCT_ID)
    Call<ModifierListByProductIdModel> getModifiersByProductId(@Header("token") String authorization, @Header("username") String username, @Body ProductDetailByModifierRequestModel requestBody);

    @POST(ApiRequestUrl.DELETE_USER_NOTIFICATION)
    Call<NotificationResponseModel> deleteNotification(@Header("token") String authorization, @Header("username") String username, @Body NotificationDeleteRequestModel body);

    @POST(ApiRequestUrl.DELETE_USER_MESSAGE)
    Call<DeleteMessageResponseModel> deleteMessage(@Header("token") String authorization, @Header("username") String username, @Body NotificationDeleteRequestModel body);

    @POST(ApiRequestUrl.GET_USER_LOCATION)
    Call<GetUserLocationResponseModel> get_user_location(@Header("token") String authorization, @Header("username") String username, @Body NotificationDeleteRequestModel body);

    @POST(ApiRequestUrl.GET_CUSTOMER_VOUCHER)
    Call<GetCustomerVouchersResponseModel> getCustomerVoucher(@Header("token") String authorization, @Header("username") String username);

    @GET
    Call<SingleProductDetailResponseModel> getDeepLinkData(@Url String url, @Query("product_id") String product_id, @Query("offer_id") String offerId);


    @POST(ApiRequestUrl.GET_UPCOMING_VENUES)
    Call<UpcomingVenueResponseModel> getUpcomingVenues(@Header("token") String authorization, @Header("username") String username, @Query("page") String page, @Query("latitude") String latitude, @Query("longitude") String longitude);

    @GET
    Call<SingleVenueDetailResponseModel> getVenueDetailGetType(@Url String url);
// https://swooposretailuk.com/admin/public/api/swoope/getVenueDetailsScan/202002121635135/0/0

    @POST(ApiRequestUrl.CLAIM_STORE)
    Call<ClaimStoreresponseModel> claimStore(@Header("token") String authorization, @Header("username") String username, @Body ClaimStoreRequestModel body);

    @POST(ApiRequestUrl.GET_SHIPPING_METHOD)
    Call<ShippingMethodResponseModel> getShippingMethods(@Header("token") String authorization, @Header("username") String username, @Body ShippingMethodRequestModel body);

    @POST(ApiRequestUrl.FETCH_ORDER_BY_POS_ORDER_ID)
    Call<FetchPOSorderResponseModel> fetchOrdersByPosOrderId(@Header("token") String authorization, @Header("username") String username, @Body FetchPOSorderRequestModel body);


    @POST(ApiRequestUrl.QR_CODE_PAYMENT)
    Call<QRCodePayResponseModel> qrCodePayment(@Header("token") String authorization, @Header("username") String username, @Body QRCodePayRequestModel body);


    @POST("api/login")
    Call<UserLoginResponseModel> userLoginApi2(@Body LoginRequestModel body);


    @POST("api/save-customer")
    Call<RegistrationResponseModel> userRegistration(@Body List<RegistrationRequestModel> body);


    @POST("api/update-loyalty-points")
    Call<UpdateLoyaltyPointsResponse> updateLoyaltyPoints(@Body UpdateLoyaltyPointsRequest body);


    @POST("api/user-loyalty-earns")
    Call<UpdateLoyaltyPointsResponse> userLoyaltyearns(@Body UpdateLoyaltyPointsRequest body);


    @POST("api/fetch-customer-details")
    Call<UserLoginResponseModel> fetchCustomerDetails(@Body FetchCustomerRequest body);

}
