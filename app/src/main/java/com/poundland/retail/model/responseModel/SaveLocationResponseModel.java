package com.poundland.retail.model.responseModel;

public class SaveLocationResponseModel {


    /**
     * status : true
     * message : Address Saved Successfully.
     * addrId : 40
     * address : varanasi, , varanasivaranasi, varanasi - b139pg
     */

    private boolean status;
    private String message;
    private int addrId;
    private String address;

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

    public int getAddrId() {
        return addrId;
    }

    public void setAddrId(int addrId) {
        this.addrId = addrId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
