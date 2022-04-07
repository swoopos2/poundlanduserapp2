package com.poundland.retail.model.requestModel;

public class ShippingMethodRequestModel {


    /**
     * shipping_distance : 10
     * shipping_venueid : 2020040808100815
     * shipping_weight : 0.5
     * shipping_price : 10
     */

    private String shipping_distance;
    private String shipping_venueid;
    private String shipping_weight;
    private String shipping_price;

    public ShippingMethodRequestModel(String shipping_distance, String shipping_venueid, String shipping_weight, String shipping_price) {
        this.shipping_distance = shipping_distance;
        this.shipping_venueid = shipping_venueid;
        this.shipping_weight = shipping_weight;
        this.shipping_price = shipping_price;
    }

    public String getShipping_distance() {
        return shipping_distance;
    }

    public void setShipping_distance(String shipping_distance) {
        this.shipping_distance = shipping_distance;
    }

    public String getShipping_venueid() {
        return shipping_venueid;
    }

    public void setShipping_venueid(String shipping_venueid) {
        this.shipping_venueid = shipping_venueid;
    }

    public String getShipping_weight() {
        return shipping_weight;
    }

    public void setShipping_weight(String shipping_weight) {
        this.shipping_weight = shipping_weight;
    }

    public String getShipping_price() {
        return shipping_price;
    }

    public void setShipping_price(String shipping_price) {
        this.shipping_price = shipping_price;
    }
}
