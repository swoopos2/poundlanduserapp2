package com.poundland.retail.model.requestModel;

public class AllVenuesRequestModel {


    /**
     * category_id :
     * search :
     * latitude : 52.57592
     * longitude : -2.138813
     */

/*{   extra key for Hospitality venue
    "venue_type":2, // 1 for retail 2 for eat
    "customer_id":0 // if loged in send customer id if not then 0
}*/

    private int venue_type;
    private int customer_id;

    private String category_id;
    private String search;
    private String latitude;
    private String longitude;
    private String venue_id;

    public AllVenuesRequestModel(int venue_type, int customer_id, String latitude, String longitude) {
        this.venue_type = venue_type;
        this.customer_id = customer_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public AllVenuesRequestModel(String venue_id,String latitude, String longitude) {
        this.venue_id = venue_id;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public AllVenuesRequestModel(String category_id, String search, String latitude, String longitude) {
        this.category_id = category_id;
        this.search = search;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getVenue_type() {
        return venue_type;
    }

    public void setVenue_type(int venue_type) {
        this.venue_type = venue_type;
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
    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
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
