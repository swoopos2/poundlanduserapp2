package com.poundland.retail.model.requestModel;

import java.util.HashMap;

public class AllProductByCategoryRequestModel {
    /**
     * category_id :
     * search :
     * venue_id : 201911011148462
     * latitude : 52.57592
     * longitude : -2.138813
     * filter : {"Price":["0-50","50-100"],"Brand":["1","10","3","2"],"Discount":["0-10"]}
     */
    private int isVenue;
    private String category_id;
    private String search;
    private String venue_id;
    private String latitude;
    private String longitude;
    private HashMap filter;


    public AllProductByCategoryRequestModel(String category_id, String search, String venue_id, String latitude, String longitude, HashMap filter) {
        this.category_id = category_id;
        this.search = search;
        this.venue_id = venue_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.filter = filter;
    }

    //////// this is for HomeFilterActivity request
    public AllProductByCategoryRequestModel(int isVenue, String search, String venue_id, String latitude, String longitude, HashMap filter) {
        this.isVenue = isVenue;
        this.search = search;
        this.venue_id = venue_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.filter = filter;
    }
/*{
	"latitude":"52.57592",
	"longitude":"-2.138813",
	"isVenue":0,
	"search":"",
	"venue_id":"",
	"filter":{
		     "Category":["11","4"],
		     "Brand":["1","2"],
		     "Price":["0-50","50-120","200"],
		     "Rating":[]
	       }
}*/


    public int getIsVenue() {
        return isVenue;
    }

    public void setIsVenue(int isVenue) {
        this.isVenue = isVenue;
    }

    public HashMap getFilter() {
        return filter;
    }

    public void setFilter(HashMap filter) {
        this.filter = filter;
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
