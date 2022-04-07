package com.poundland.retail.model.requestModel;

public class GetCartWithSummaryRequestModel {


    /**
     * venue_id :
     * latitude : 52.57592
     * longitude : -2.138813
     */

    private int customer_id;
    private String venue_id;
    private String latitude;
    private String longitude;

    public GetCartWithSummaryRequestModel(int customer_id) {
        this.customer_id = customer_id;
    }

    /* FOR HOSPITALITY PRODUCT*/
    public GetCartWithSummaryRequestModel(int customer_id, String venue_id, String latitude, String longitude) {
        this.customer_id = customer_id;
        this.venue_id = venue_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GetCartWithSummaryRequestModel(String venue_id, String latitude, String longitude) {
        this.venue_id = venue_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public GetCartWithSummaryRequestModel(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
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
