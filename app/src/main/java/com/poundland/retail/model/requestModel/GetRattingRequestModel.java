package com.poundland.retail.model.requestModel;

public class GetRattingRequestModel {

    private String order_id;

    public GetRattingRequestModel(String order_id) {
        this.order_id = order_id;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }
}
