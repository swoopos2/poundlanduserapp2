package com.poundland.retail.model.responseModel;

public class DeleteMessageResponseModel {


    /**
     * status : 200
     * message : Messages deleted successfully
     */


    private boolean status;
    private String message;

    public boolean getStatus() {
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
