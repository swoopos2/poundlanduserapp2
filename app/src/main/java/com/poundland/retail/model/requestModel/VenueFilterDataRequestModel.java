package com.poundland.retail.model.requestModel;

public class VenueFilterDataRequestModel {


    /**
     * venue_id : 201910311251401
     * category_id : 4
     */

    private String venue_id;
    private String category_id;

    public VenueFilterDataRequestModel(String venue_id, String category_id) {
        this.venue_id = venue_id;
        this.category_id = category_id;
    }

    public VenueFilterDataRequestModel(String category_id) {
        this.category_id = category_id;
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
}
