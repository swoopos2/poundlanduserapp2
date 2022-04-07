package com.poundland.retail.model.requestModel;

import java.util.List;

public class AllVenuesHospitalityRequestModel {


    /**
     * venue_type : 2
     * customer_id : 111
     * sort :
     * offer :
     * home :
     * collect :
     * search : dixi chicken
     * category : ["chicken","pizza"]
     * product_type : ["non-veg","both"]
     * ratting : [4]
     * latitude : 52.57592
     * longitude : -2.138813
     */

    private String venue_type;
    private String customer_id;
    private String sort;
    private String offer;
    private String home;
    private String collect;
    private String search;
    private String latitude;
    private String longitude;
    private String slug;
    private List<String> category;
    private List<String> product_type;
    private List<Integer> ratting;

    public AllVenuesHospitalityRequestModel(String venue_type, String customer_id, String sort, String offer, String home,
                                            String collect, String search, String latitude, String longitude,
                                            List<String> category, List<String> product_type, List<Integer> ratting,String slug) {
        this.venue_type = venue_type;
        this.customer_id = customer_id;
        this.sort = sort;
        this.offer = offer;
        this.home = home;
        this.collect = collect;
        this.search = search;
        this.latitude = latitude;
        this.longitude = longitude;
        this.category = category;
        this.product_type = product_type;
        this.ratting = ratting;
        this.slug = slug;
    }

    public String getVenue_type() {
        return venue_type;
    }

    public void setVenue_type(String venue_type) {
        this.venue_type = venue_type;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOffer() {
        return offer;
    }

    public void setOffer(String offer) {
        this.offer = offer;
    }

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    public String getCollect() {
        return collect;
    }

    public void setCollect(String collect) {
        this.collect = collect;
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

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public List<String> getProduct_type() {
        return product_type;
    }

    public void setProduct_type(List<String> product_type) {
        this.product_type = product_type;
    }

    public List<Integer> getRatting() {
        return ratting;
    }

    public void setRatting(List<Integer> ratting) {
        this.ratting = ratting;
    }
}
