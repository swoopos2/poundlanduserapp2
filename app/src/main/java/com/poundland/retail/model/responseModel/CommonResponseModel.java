package com.poundland.retail.model.responseModel;

public class CommonResponseModel {


    /**
     * status : true
     * message : We have e-mailed your password reset link!
     */


    private String message;
    private boolean status;
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
