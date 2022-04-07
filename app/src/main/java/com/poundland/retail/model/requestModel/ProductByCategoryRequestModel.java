package com.poundland.retail.model.requestModel;

public class ProductByCategoryRequestModel {


    /**
     * search_cat :
     * latitude : 52.57592
     * longitude : -2.138813
     */

    private String search_cat;
    private String latitude;
    private String longitude;

    public ProductByCategoryRequestModel(String search_cat, String latitude, String longitude) {
        this.search_cat = search_cat;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getSearch_cat() {
        return search_cat;
    }

    public void setSearch_cat(String search_cat) {
        this.search_cat = search_cat;
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
