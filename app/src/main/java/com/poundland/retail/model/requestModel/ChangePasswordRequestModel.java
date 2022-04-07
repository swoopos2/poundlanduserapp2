package com.poundland.retail.model.requestModel;

public class ChangePasswordRequestModel {

    /**
     * old_password : 123456789
     * new_password : 12345678
     */
    /*{             /////////////////////////////////  for delete account
        "isLogout":"1"
    }*/
    private String isLogout;
    private String old_password;
    private String new_password;

    private int new_pin;
    private String otp;


    public ChangePasswordRequestModel(String old_password, String new_password) {
        this.old_password = old_password;
        this.new_password = new_password;
    }

    public ChangePasswordRequestModel(int new_pin, String otp) {
        this.new_pin = new_pin;
        this.otp = otp;
    }

    public int getNew_pin() {
        return new_pin;
    }

    public void setNew_pin(int new_pin) {
        this.new_pin = new_pin;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public ChangePasswordRequestModel(String isLogout) {
        this.isLogout = isLogout;
    }

    public String getIsLogout() {
        return isLogout;
    }

    public void setIsLogout(String isLogout) {
        this.isLogout = isLogout;
    }

    public String getOld_password() {
        return old_password;
    }

    public void setOld_password(String old_password) {
        this.old_password = old_password;
    }

    public String getNew_password() {
        return new_password;
    }

    public void setNew_password(String new_password) {
        this.new_password = new_password;
    }
}
