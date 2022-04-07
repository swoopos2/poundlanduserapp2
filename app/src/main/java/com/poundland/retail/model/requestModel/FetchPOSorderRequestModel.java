package com.poundland.retail.model.requestModel;

public class FetchPOSorderRequestModel {

    /**   {"pos_order_id":"163222931087695132",
     "order_no":"POS@68.42@163222931087695132@0",
     "net_amount":"68.42"}  */

    private String pos_order_id;
    private String order_no;
    private String net_amount;

    public FetchPOSorderRequestModel(String pos_order_id, String order_no, String net_amount) {
        this.pos_order_id = pos_order_id;
        this.order_no = order_no;
        this.net_amount = net_amount;
    }

    public String getPos_order_id() {
        return pos_order_id;
    }

    public void setPos_order_id(String pos_order_id) {
        this.pos_order_id = pos_order_id;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getNet_amount() {
        return net_amount;
    }

    public void setNet_amount(String net_amount) {
        this.net_amount = net_amount;
    }
}