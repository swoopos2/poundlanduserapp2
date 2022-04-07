package com.poundland.retail.model.responseModel;

public class NotifyUpcomingVenueProductResp {

    /**
     * status : true
     * message : You will be notified when product is available
     */

    private boolean status;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
