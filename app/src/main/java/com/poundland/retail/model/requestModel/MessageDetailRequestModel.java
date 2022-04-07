package com.poundland.retail.model.requestModel;

public class MessageDetailRequestModel {


    /**
     * venueid : 2019120307071210
     * orderid : 368
     */
    private String order_detail_id;
    private String venueid;
    private String orderid;

    public MessageDetailRequestModel(String order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public MessageDetailRequestModel(String venueid, String orderid) {
        this.venueid = venueid;
        this.orderid = orderid;
    }

    public String getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(String order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public String getVenueid() {
        return venueid;
    }

    public void setVenueid(String venueid) {
        this.venueid = venueid;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }
}
