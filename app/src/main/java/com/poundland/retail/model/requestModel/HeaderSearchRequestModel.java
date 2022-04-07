package com.poundland.retail.model.requestModel;

public class HeaderSearchRequestModel {


    /**
     * product_type : 0
     * search : Cadbury Dairy Milk Buttons 5
     * limit :
     */
    private String product_type;
    private String search;
    private String limit;
    private String latitude;
    private String longitude;

    public HeaderSearchRequestModel(String product_type, String search, String limit,String latitude,String longitude) {
        this.product_type = product_type;
        this.search = search;
        this.limit = limit;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getProduct_type() {
        return product_type;
    }

    public void setProduct_type(String product_type) {
        this.product_type = product_type;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }
}
