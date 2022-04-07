package com.poundland.retail.model.requestModel;

public class PinRequestModel {

    /**
     * pin : 1234
     * type : 1    // 1 for create , 2 for verify
     */

    private String pin;
    private String type;

    public PinRequestModel(String pin, String type) {
        this.pin = pin;
        this.type = type;
    }

    public String getPin() {
        return pin;
    }

    public void setPin(String pin) {
        this.pin = pin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
