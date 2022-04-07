package com.poundland.retail.model.requestModel;

import java.util.List;

public class SingleVenueDetailRequestModel {

    /*{   RETAIL
        "venue_id":"202002121635135",
            "search":"",
            "category_id":"",
            "limit":"",
            "latitude":"52.57592",
            "longitude":"-2.138813"
    }*/

    /* {   HOSPITALITY
         "venue_id":"2020040808042515",
             "isShow":0,
             "cateId":0,
             "productSearch":"",
             "fav_product":""
     }*/
    private int isShow;
    private int cateId;
    private String productSearch;
    private String fav_product;

    private String venue_id;
    private String latitude;
    private String longitude;
    private String limit;
    private String search;
    private String category_id;
    private List<String> subcategory;

    public SingleVenueDetailRequestModel(String venue_id) {
        this.venue_id = venue_id;
    }

    public SingleVenueDetailRequestModel(int isShow, int cateId, String productSearch, String fav_product, String venue_id,List<String> subcategory,
                                         String latitude, String longitude,String limit) {
        this.isShow = isShow;
        this.cateId = cateId;
        this.productSearch = productSearch;
        this.fav_product = fav_product;
        this.venue_id = venue_id;
        this.subcategory = subcategory;
        this.latitude = latitude;
        this.longitude = longitude;
        this.limit = limit;
    }

    public SingleVenueDetailRequestModel(String venue_id, String latitude, String longitude, String category_id, String limit, String search) {
        this.venue_id = venue_id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category_id = category_id;
        this.limit = limit;
        this.search = search;
    }

    public List<String> getSubcategory() {
        return subcategory;
    }

    public void setSubcategory(List<String> subcategory) {
        this.subcategory = subcategory;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getCategory_id() {
        return category_id;
    }

    public void setCategory_id(String category_id) {
        this.category_id = category_id;
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

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }
}
