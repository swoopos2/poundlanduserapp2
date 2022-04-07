package com.poundland.retail.model.requestModel;

public class AddMobileCartQuantityRequestModel {

    ///// for Hospitality     cart_id will common
    private String type;
    private String customer_id;
    private String currentVal;
    /////   For Retail
    private String cart_id;
    private String quantities;
    private String latitude;
    private String longitude;

    //// // for update quantity of product on product list of hosp venue detail  START
    private String product_id;
    private String modifier_id;
    private String offer_id;

    public AddMobileCartQuantityRequestModel(String quantities, String product_id, String modifier_id, String offer_id) {
        this.quantities = quantities;
        this.product_id = product_id;
        this.modifier_id = modifier_id;
        this.offer_id = offer_id;
    }//// // for update quantity of product on product list of hosp venue detail END


    public AddMobileCartQuantityRequestModel(String type, String customer_id, String currentVal, String cart_id, String latitude, String longitude) {
        this.type = type;
        this.customer_id = customer_id;
        this.currentVal = currentVal;
        this.cart_id = cart_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public AddMobileCartQuantityRequestModel(String cart_id, String latitude, String longitude) {
        this.cart_id = cart_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public AddMobileCartQuantityRequestModel(String cart_id, String quantities) {
        this.cart_id = cart_id;
        this.quantities = quantities;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getCart_id() {
        return cart_id;
    }

    public void setCart_id(String cart_id) {
        this.cart_id = cart_id;
    }

    public String getQuantities() {
        return quantities;
    }

    public void setQuantities(String quantities) {
        this.quantities = quantities;
    }
}
