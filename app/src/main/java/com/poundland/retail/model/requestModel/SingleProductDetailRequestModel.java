package com.poundland.retail.model.requestModel;

public class SingleProductDetailRequestModel {


    /**  getProductDetails
     * product_id : 4
     * modifier_id :
     * offer_id : 36
     * latitude : 52.57592
     * longitude : -2.138813
     */

   /* {   fetchProductDetails
        "customer_id":"100",
            "product_id":"255",
            "modifier_id":"",
            "offer_id":"",
            "special_offer_id":"",
            "limit":""
    }*/

    private String customer_id;
    private String limit;
    /////////////////////////////////////////////////////

    private String special_offer_id;
    private String product_id;
    private String modifier_id;
    private String offer_id;
    private String latitude;
    private String longitude;
    private String barcode_id;


    public SingleProductDetailRequestModel(String customer_id, String product_id,String modifier_id,
                                           String offer_id, String special_offer_id,String limit) {
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.modifier_id = modifier_id;
        this.offer_id = offer_id;
        this.special_offer_id = special_offer_id;
        this.limit = limit;
    }




    public SingleProductDetailRequestModel(String product_id, String modifier_id, String offer_id,
                                           String latitude, String longitude,String barcode_id,String nothing) {
        this.product_id = product_id;
        this.modifier_id = modifier_id;
        this.offer_id = offer_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.barcode_id = barcode_id;
    }

    public SingleProductDetailRequestModel(String special_offer_id,String product_id, String offer_id, String latitude, String longitude) {
        this.special_offer_id = special_offer_id;
        this.product_id = product_id;
        this.offer_id = offer_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getSpecial_offer_id() {
        return special_offer_id;
    }

    public void setSpecial_offer_id(String special_offer_id) {
        this.special_offer_id = special_offer_id;
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
}
