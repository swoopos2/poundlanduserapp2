package com.poundland.retail.model.requestModel;

public class CouponRequestModel {

    // {"venue_id":"1122222","reservation_id":"987654323"}
    private String reservation_id;
    private String venue_id;

    // coupon_code, venue_id, customer_id,is_hospitality,shippingChargesVal,reservation_id
    private String coupon_code;
    private String customer_id;
    private String is_hospitality;
    private String shippingChargesVal;


    public CouponRequestModel(String reservation_id, String venue_id, String coupon_code, String customer_id, String is_hospitality, String shippingChargesVal) {
        this.reservation_id = reservation_id;
        this.venue_id = venue_id;
        this.coupon_code = coupon_code;
        this.customer_id = customer_id;
        this.is_hospitality = is_hospitality;
        this.shippingChargesVal = shippingChargesVal;
    }

    public CouponRequestModel(String reservation_id, String venue_id) {
        this.reservation_id = reservation_id;
        this.venue_id = venue_id;
    }


    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getIs_hospitality() {
        return is_hospitality;
    }

    public void setIs_hospitality(String is_hospitality) {
        this.is_hospitality = is_hospitality;
    }

    public String getShippingChargesVal() {
        return shippingChargesVal;
    }

    public void setShippingChargesVal(String shippingChargesVal) {
        this.shippingChargesVal = shippingChargesVal;
    }

    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }
}