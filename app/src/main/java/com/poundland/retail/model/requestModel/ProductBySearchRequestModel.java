package com.poundland.retail.model.requestModel;

import java.util.HashMap;

public class ProductBySearchRequestModel {


    /*{
    "search":"",
    "latitude":"52.57592",
	"longitude":"-2.138813",
	"venue_id":"",
	"filter":{
		     "Category":["3"],
		     "Brand":[],
		     "Price":["0-50","50-120","200"],
		     "Rating":[]
	       }
}*/

    private String search;
    private String latitude;
    private String longitude;
    private String venue_id;
    private String venue_type;
    private HashMap filter;

    public ProductBySearchRequestModel(String search, String latitude, String longitude,String venue_id,HashMap filter,String venue_type) {
        this.search = search;
        this.latitude = latitude;
        this.longitude = longitude;

        this.venue_id = venue_id;
        this.filter = filter;
        this.venue_type = venue_type;
    }
    public ProductBySearchRequestModel(String search, String latitude, String longitude) {
        this.search = search;
        this.latitude = latitude;
        this.longitude = longitude;
    }
    public ProductBySearchRequestModel( String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public HashMap getFilter() {
        return filter;
    }

    public void setFilter(HashMap filter) {
        this.filter = filter;
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
