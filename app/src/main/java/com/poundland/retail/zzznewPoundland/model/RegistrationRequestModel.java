package com.poundland.retail.zzznewPoundland.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RegistrationRequestModel {
    @SerializedName("first_name")
    @Expose
    String firstName;
    @SerializedName("last_name")
    @Expose
    String lastName;
    @SerializedName("email")
    @Expose
    String email;
    @SerializedName("contact")
    @Expose
    String contact;
    @SerializedName("gender")
    @Expose
    String gender;
    @SerializedName("dob")
    @Expose
    String dob;
    @SerializedName("password")
    @Expose
    String password;
    @SerializedName("fire_base_id")
    @Expose
    String fireBaseId;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFireBaseId() {
        return fireBaseId;
    }

    public void setFireBaseId(String fireBaseId) {
        this.fireBaseId = fireBaseId;
    }
}
