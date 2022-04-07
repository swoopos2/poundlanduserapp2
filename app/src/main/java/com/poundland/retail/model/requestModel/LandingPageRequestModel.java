package com.poundland.retail.model.requestModel;

public class LandingPageRequestModel {


    public LandingPageRequestModel(String customer_id, String latitude, String longitude, String limit) {
        this.customer_id = customer_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.limit = limit;
    }

    /**
     * customer_id :
     * latitude : 52.57592
     * longitude : -2.138813
     * limit :
     */

    private String customer_id;
    private String latitude;
    private String longitude;
    private String limit;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
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

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
