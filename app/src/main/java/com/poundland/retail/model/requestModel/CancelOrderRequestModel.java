package com.poundland.retail.model.requestModel;

import java.util.List;

public class CancelOrderRequestModel {

    /*{
        "venue_id":"2020042308172516",
        "res_day":0
    }*/
    private String venue_id;
    private int res_day;

    /**
     * order_id : 117
     * order_details_ids : [176]
     * message : product cancel
     */

    private int order_id;
    private String product_id;
    private String message;
    private List<Integer> order_details_ids;

    public CancelOrderRequestModel(int order_id, String message, List<Integer> order_details_ids) {
        this.order_id = order_id;
        this.message = message;
        this.order_details_ids = order_details_ids;
    }

    public CancelOrderRequestModel(String venue_id, int res_day) {
        this.venue_id = venue_id;
        this.res_day = res_day;
    }

    public CancelOrderRequestModel(String product_id) {
        this.product_id = product_id;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public int getRes_day() {
        return res_day;
    }

    public void setRes_day(int res_day) {
        this.res_day = res_day;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Integer> getOrder_details_ids() {
        return order_details_ids;
    }

    public void setOrder_details_ids(List<Integer> order_details_ids) {
        this.order_details_ids = order_details_ids;
    }
}
