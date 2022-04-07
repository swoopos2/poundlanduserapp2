package com.poundland.retail.model.requestModel;

public class NotificationDeleteRequestModel {
    private String notification_id;
    private String post_code;

    /******************   For delete message   **************************************/
    private String venueid;
    private String orderid;
    private int message_id;
    /******************   For delete message constructor **************************************/
    public NotificationDeleteRequestModel(String venueid, String orderid, int message_id) {
        this.venueid = venueid;
        this.orderid = orderid;
        this.message_id = message_id;
    }

    public NotificationDeleteRequestModel(String notification_id) {
        this.notification_id = notification_id;
    }

    public NotificationDeleteRequestModel(String post_code, String notification_id) {
        this.notification_id = notification_id;
        this.post_code = post_code;
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

    public int getMessage_id() {
        return message_id;
    }

    public void setMessage_id(int message_id) {
        this.message_id = message_id;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getNotification_id() {
        return notification_id;
    }

    public void setNotification_id(String notification_id) {
        this.notification_id = notification_id;
    }
}
