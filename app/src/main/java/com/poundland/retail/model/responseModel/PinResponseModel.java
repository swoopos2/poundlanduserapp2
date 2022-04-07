package com.poundland.retail.model.responseModel;

public class PinResponseModel {

    /**
     * status : true
     * message : Pin generated successfully.
     * pin : 1234
     */

    private boolean status;
    private String message;
    private int pin;

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

    public int getPin() {
        return pin;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }


}
