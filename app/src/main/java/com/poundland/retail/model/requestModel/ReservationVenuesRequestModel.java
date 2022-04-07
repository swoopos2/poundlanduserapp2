package com.poundland.retail.model.requestModel;

public class ReservationVenuesRequestModel {

/*{
    "latitude":"52.57592",
	"longitude":"-2.138813",
    "customer_id":"",
    "searchItem":"",
    "searchGuest":"1",
    "searchTime":"10:00",
    "searchDate":"28-Dec-2020",
    "venue_type":2
}*/

    private String latitude;
    private String longitude;
    private String customer_id;
    private String searchItem;
    private String searchGuest;
    private String searchTime;
    private String searchDate;
    private int venue_type;

    public ReservationVenuesRequestModel(String latitude, String longitude, String customer_id, String searchItem, String searchGuest, String searchTime, String searchDate, int venue_type) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.customer_id = customer_id;
        this.searchItem = searchItem;
        this.searchGuest = searchGuest;
        this.searchTime = searchTime;
        this.searchDate = searchDate;
        this.venue_type = venue_type;
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

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getSearchItem() {
        return searchItem;
    }

    public void setSearchItem(String searchItem) {
        this.searchItem = searchItem;
    }

    public String getSearchGuest() {
        return searchGuest;
    }

    public void setSearchGuest(String searchGuest) {
        this.searchGuest = searchGuest;
    }

    public String getSearchTime() {
        return searchTime;
    }

    public void setSearchTime(String searchTime) {
        this.searchTime = searchTime;
    }

    public String getSearchDate() {
        return searchDate;
    }

    public void setSearchDate(String searchDate) {
        this.searchDate = searchDate;
    }

    public int getVenue_type() {
        return venue_type;
    }

    public void setVenue_type(int venue_type) {
        this.venue_type = venue_type;
    }
}
