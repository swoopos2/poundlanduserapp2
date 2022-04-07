package com.poundland.retail.model.requestModel;

import android.os.Parcel;
import android.os.Parcelable;

public class SignUpRequestModel implements Parcelable {


    /**
     * first_name : Shakti
     * last_name : Singh
     * email : shakti@gmail.com
     * password : 12345678
     * dob : 2010-02-10
     * contact_no : 7503695703
     * reff_code :
     * gender : male
     * social_type :
     * social_token :
     * device_type :
     * custmer_type : ecom
     * device_id :
     * profile_image :
     * pos_cus_id :
     * imei: "",
     * otp : "2656"
     */

      private String first_name;
    private String last_name;
    private String email;
    private String password;
    private String dob;
    private String contact_no;
    private String reff_code;
    private String gender;
    private String social_type;
    private String social_token;
    private String device_type;
    private String custmer_type;
    private String device_id;
    private String profile_image;
    private String pos_cus_id;
    private String otp;
    private String imei;

    public SignUpRequestModel(String first_name, String last_name, String email, String password, String dob,
                              String contact_no, String reff_code, String gender, String social_type, String social_token,
                              String device_type, String custmer_type, String device_id, String profile_image,
                              String pos_cus_id,String otp,String imei) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.password = password;
        this.dob = dob;
        this.contact_no = contact_no;
        this.reff_code = reff_code;
        this.gender = gender;
        this.social_type = social_type;
        this.social_token = social_token;
        this.device_type = device_type;
        this.custmer_type = custmer_type;
        this.device_id = device_id;
        this.profile_image = profile_image;
        this.pos_cus_id = pos_cus_id;
        this.otp = otp;
        this.imei = imei;
    }

    public SignUpRequestModel(String contact_no, String otp) {
        this.contact_no = contact_no;
        this.otp = otp;
    }

    protected SignUpRequestModel(Parcel in) {
        first_name = in.readString();
        last_name = in.readString();
        email = in.readString();
        password = in.readString();
        dob = in.readString();
        contact_no = in.readString();
        reff_code = in.readString();
        gender = in.readString();
        social_type = in.readString();
        social_token = in.readString();
        device_type = in.readString();
        custmer_type = in.readString();
        device_id = in.readString();
        profile_image = in.readString();
        pos_cus_id = in.readString();
        otp = in.readString();
        imei = in.readString();
    }

    public static final Creator<SignUpRequestModel> CREATOR = new Creator<SignUpRequestModel>() {
        @Override
        public SignUpRequestModel createFromParcel(Parcel in) {
            return new SignUpRequestModel(in);
        }

        @Override
        public SignUpRequestModel[] newArray(int size) {
            return new SignUpRequestModel[size];
        }
    };

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getOtp() {
        return otp;
    }

    public void setOtp(String otp) {
        this.otp = otp;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getReff_code() {
        return reff_code;
    }

    public void setReff_code(String reff_code) {
        this.reff_code = reff_code;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSocial_type() {
        return social_type;
    }

    public void setSocial_type(String social_type) {
        this.social_type = social_type;
    }

    public String getSocial_token() {
        return social_token;
    }

    public void setSocial_token(String social_token) {
        this.social_token = social_token;
    }

    public String getDevice_type() {
        return device_type;
    }

    public void setDevice_type(String device_type) {
        this.device_type = device_type;
    }

    public String getCustmer_type() {
        return custmer_type;
    }

    public void setCustmer_type(String custmer_type) {
        this.custmer_type = custmer_type;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public String getProfile_image() {
        return profile_image;
    }

    public void setProfile_image(String profile_image) {
        this.profile_image = profile_image;
    }

    public String getPos_cus_id() {
        return pos_cus_id;
    }

    public void setPos_cus_id(String pos_cus_id) {
        this.pos_cus_id = pos_cus_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(first_name);
        parcel.writeString(last_name);
        parcel.writeString(email);
        parcel.writeString(password);
        parcel.writeString(dob);
        parcel.writeString(contact_no);
        parcel.writeString(reff_code);
        parcel.writeString(gender);
        parcel.writeString(social_type);
        parcel.writeString(social_token);
        parcel.writeString(device_type);
        parcel.writeString(custmer_type);
        parcel.writeString(device_id);
        parcel.writeString(profile_image);
        parcel.writeString(pos_cus_id);
        parcel.writeString(otp);
        parcel.writeString(imei);
    }
}
