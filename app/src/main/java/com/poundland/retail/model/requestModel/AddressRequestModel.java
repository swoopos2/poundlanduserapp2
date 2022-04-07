package com.poundland.retail.model.requestModel;

public class AddressRequestModel {

    private String product_id;
    private String venue_id;
    private String merchant_id;
    private String modifier_id;
    private String email;
    private String latitude;
    private String longitude;
    private String status;

    public AddressRequestModel(String status) {
        this.status = status;
    }

    public AddressRequestModel(String latitude, String longitude) {                    ///  get address
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public AddressRequestModel(String product_id, String latitude, String longitude) {    /// For getMatchWislistProduct
        this.product_id = product_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public AddressRequestModel(String email,String product_id, String venue_id, String merchant_id, String modifier_id) {  /// for notify me
        this.product_id = product_id;
        this.venue_id = venue_id;
        this.merchant_id = merchant_id;
        this.modifier_id = modifier_id;
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getMerchant_id() {
        return merchant_id;
    }

    public void setMerchant_id(String merchant_id) {
        this.merchant_id = merchant_id;
    }

    public String getModifier_id() {
        return modifier_id;
    }

    public void setModifier_id(String modifier_id) {
        this.modifier_id = modifier_id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
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
