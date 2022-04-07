package com.poundland.retail.model.requestModel;

import com.google.gson.annotations.Expose;

import java.util.HashMap;
import java.util.List;

public class ReturnRequestModel {

    /**
     * order_id : 140
     * order_details_ids : [210,209]
     * cause_message : sgsg
     * message : syue
     * return_qty : {"210":"2","209":"1"}
     */
    @Expose
    private String order_id;
    @Expose
    private String cause_message;
    @Expose
    private String message;
    @Expose
    private List<Integer> order_details_ids;
    @Expose
    private HashMap<String, String> return_qty;

    public ReturnRequestModel(String order_id, String cause_message, String message,
                              List<Integer> order_details_ids, HashMap<String, String> return_qty) {
        this.order_id = order_id;
        this.cause_message = cause_message;
        this.message = message;
        this.order_details_ids = order_details_ids;
        this.return_qty = return_qty;
    }

    public HashMap<String, String> getReturn_qty() {
        return return_qty;
    }

    public void setReturn_qty(HashMap<String, String> return_qty) {
        this.return_qty = return_qty;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public String getCause_message() {
        return cause_message;
    }

    public void setCause_message(String cause_message) {
        this.cause_message = cause_message;
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
