package com.poundland.retail.model.requestModel;

public class AddToCartRequestModel {

    /**
     * merchant_id : 10
     * venue_id : 2019071613143410
     * product_id : 3
     * modifier_id : 4
     * offer_id : 1
     * quantities : 2
     * cost_per_product : 10
     * coupon_code :
     * ip_address :
     */

    private String merchant_id;
    private String venue_id;
    private String product_id;
    private String modifier_id;
    private String offer_id;
    private String special_offer_id;
    private String quantities;
    private String cost_per_product;
    private String coupon_code;
    private String ip_address;
    private String latitude;
    private String longitude;

    //////////////
    private String caseqty;
    private String bulk_selling_price;

    public AddToCartRequestModel(String merchant_id, String venue_id, String product_id, String modifier_id, String offer_id, String special_offer_id,
                                 String quantities, String cost_per_product, String coupon_code, String ip_address, String caseqty,
                                 String bulk_selling_price, String latitude, String longitude) {
        this.merchant_id = merchant_id;
        this.venue_id = venue_id;
        this.product_id = product_id;
        this.modifier_id = modifier_id;
        this.offer_id = offer_id;
        this.special_offer_id = special_offer_id;
        this.quantities = quantities;
        this.cost_per_product = cost_per_product;
        this.coupon_code = coupon_code;
        this.ip_address = ip_address;
        this.caseqty = caseqty;
        this.bulk_selling_price = bulk_selling_price;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public AddToCartRequestModel(String merchant_id, String venue_id, String product_id, String modifier_id, String offer_id,
                                 String quantities, String cost_per_product, String coupon_code, String ip_address,
                                 String special_offer_id,String latitude,String longitude) {
        this.merchant_id = merchant_id;
        this.venue_id = venue_id;
        this.product_id = product_id;
        this.modifier_id = modifier_id;
        this.offer_id = offer_id;
        this.quantities = quantities;
        this.cost_per_product = cost_per_product;
        this.coupon_code = coupon_code;
        this.ip_address = ip_address;
        this.special_offer_id = special_offer_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getSpecial_offer_id() {
        return special_offer_id;
    }

    public void setSpecial_offer_id(String special_offer_id) {
        this.special_offer_id = special_offer_id;
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

    public String getQuantities() {
        return quantities;
    }

    public void setQuantities(String quantities) {
        this.quantities = quantities;
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
