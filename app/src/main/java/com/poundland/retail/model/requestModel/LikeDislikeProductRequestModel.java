package com.poundland.retail.model.requestModel;

public class LikeDislikeProductRequestModel {


    /**
     * merchant_id : 10
     * venue_id : 2019071613181310
     * customer_id : 5
     * product_id : 1
     * modifier_id : 1
     * offer_id : 1
     * cost_per_product : 10
     * coupon_code :
     * ip_address : 127.0.0.1:8000
     */

    private String merchant_id;
    private String venue_id;
    private String customer_id;
    private String product_id;
    private String modifier_id;
    private String offer_id;
    private String cost_per_product;
    private String coupon_code;
    private String ip_address;

    public LikeDislikeProductRequestModel(String merchant_id, String venue_id, String customer_id, String product_id, String modifier_id, String offer_id, String cost_per_product, String coupon_code) {
        this.merchant_id          = merchant_id;
        this.venue_id             = venue_id;
        this.customer_id         = customer_id;
        this.product_id         = product_id;
        this.modifier_id         = modifier_id;
        this.offer_id            = offer_id;
        this.cost_per_product   = cost_per_product;
        this.coupon_code       = coupon_code;
    }

    /******************************Constructor for Like/Dislike Venue**********************************************************/
    public LikeDislikeProductRequestModel(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getModifier_id() {
        return modifier_id;
    }

    public void setModifier_id(String modifier_id) {
        this.modifier_id = modifier_id;
    }

    public String getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(String offer_id) {
        this.offer_id = offer_id;
    }

    public String getCost_per_product() {
        return cost_per_product;
    }

    public void setCost_per_product(String cost_per_product) {
        this.cost_per_product = cost_per_product;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
}
