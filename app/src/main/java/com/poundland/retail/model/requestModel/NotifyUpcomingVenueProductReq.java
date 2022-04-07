package com.poundland.retail.model.requestModel;

public class NotifyUpcomingVenueProductReq {
    /*"email" => "abhi@yopmail.com"
            "product_id" => "7"
            "venue_id" => "4"
            "customer_id" => 0
            "u_lat" => "26.8467088"
            "u_lng" => "80.9461592"
            "u_city" => "Lucknow"
            "ip_address" => "::1"*/

    private String email;
    private String product_id;
    private String venue_id;
    private long customer_id;
    private String u_lat;
    private String u_lng;
    private String u_city;
    private String ip_address;

    public NotifyUpcomingVenueProductReq(String email, String product_id, String venue_id, long customer_id, String u_lat, String u_lng, String u_city, String ip_address) {
        this.email = email;
        this.product_id = product_id;
        this.venue_id = venue_id;
        this.customer_id = customer_id;
        this.u_lat = u_lat;
        this.u_lng = u_lng;
        this.u_city = u_city;
        this.ip_address = ip_address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public long getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(long customer_id) {
        this.customer_id = customer_id;
    }

    public String getU_lat() {
        return u_lat;
    }

    public void setU_lat(String u_lat) {
        this.u_lat = u_lat;
    }

    public String getU_lng() {
        return u_lng;
    }

    public void setU_lng(String u_lng) {
        this.u_lng = u_lng;
    }

    public String getU_city() {
        return u_city;
    }

    public void setU_city(String u_city) {
        this.u_city = u_city;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
}
