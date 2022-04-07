package com.poundland.retail.model.requestModel;

public class GetWishlistedRequestModel {


    /**
     * venue_id :
     * latitude : 52.57592
     * longitude : -2.138813
     */

    private String venue_id;
    private String latitude;
    private String longitude;

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
