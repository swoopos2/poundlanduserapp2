package com.poundland.retail.model.requestModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {

    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("password")
    @Expose
    String password;

    String device_id;
    String device_type;
    String contact_no;
    String social_id;
    String type;


    @SerializedName("fire_base_id")
    @Expose
    String fireBaseId;


    public LoginRequestModel(String email,String type) {
        this.email = email;
        this.type = type;
    }
    public LoginRequestModel(String email, String password,String device_id,String device_type) {
        this.email = email;
        this.password = password;
        this.device_id = device_id;
        this.device_type = device_type;
    }

    public LoginRequestModel(String email, String contact_no, String social_id,String type,String xx) {
        this.email = email;
        this.contact_no = contact_no;
        this.social_id = social_id;
        this.type = type;
    }

    public LoginRequestModel(String email, String password, String fireBaseId) {
        this.email = email;
        this.password = password;
        this.fireBaseId = fireBaseId;
    }



    public String getSocial_id() {
        return social_id;
    }

    public void setSocial_id(String social_id) {
        this.social_id = social_id;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getFireBaseId() {
        return fireBaseId;
    }

    public void setFireBaseId(String fireBaseId) {
        this.fireBaseId = fireBaseId;
    }
}
