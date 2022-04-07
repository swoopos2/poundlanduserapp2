package com.poundland.retail.model.requestModel;

public class SendMessageRequestModel {


    /**
     * custtype : app
     * from : cust
     * message : Test
     * orderid : 308
     * to : venue
     * venueid : 201911011148462
     */

    private String custtype;
    private String from;
    private String message;
    private String orderid;
    private String to;
    private String venueid;

    public SendMessageRequestModel(String custtype, String from, String message, String orderid, String to, String venueid) {
        this.custtype = custtype;
        this.from = from;
        this.message = message;
        this.orderid = orderid;
        this.to = to;
        this.venueid = venueid;
    }

    public String getCusttype() {
        return custtype;
    }

    public void setCusttype(String custtype) {
        this.custtype = custtype;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getOrderid() {
        return orderid;
    }

    public void setOrderid(String orderid) {
        this.orderid = orderid;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getVenueid() {
        return venueid;
    }

    public void setVenueid(String venueid) {
        this.venueid = venueid;
    }
}
