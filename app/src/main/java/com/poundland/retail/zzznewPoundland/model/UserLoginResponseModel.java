package com.poundland.retail.zzznewPoundland.model;

import com.google.gson.annotations.SerializedName;
import com.poundland.retail.zzznewPoundland.CommonResponse;

public class UserLoginResponseModel extends CommonResponse {

    @SerializedName("user")
    private UserDataBean data;
    @SerializedName("customer")
    private UserDataBean customer;

    public UserLoginResponseModel(Boolean status, String message, int currentPage, int lastPage, String errorMessage) {
        super(status, message, currentPage, lastPage, errorMessage);
    }


    public UserDataBean getData() {
        return data;
    }

    public void setData(UserDataBean data) {
        this.data = data;
    }

    public UserDataBean getCustomer() {
        return customer;
    }

    public void setCustomer(UserDataBean customer) {
        this.customer = customer;
    }

    public static class UserDataBean {
        private String id;
        private String name;
        private String email;
        private String contact;
        private String pos_id;
        private String email_verified_at;
        private float loyalty_points;
        private int user_type;
        private int status;
        private String created_at;
        private String updated_at;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public String getPos_id() {
            return pos_id;
        }

        public void setPos_id(String pos_id) {
            this.pos_id = pos_id;
        }

        public String getEmail_verified_at() {
            return email_verified_at;
        }

        public void setEmail_verified_at(String email_verified_at) {
            this.email_verified_at = email_verified_at;
        }

        public float getLoyalty_points() {
            return loyalty_points;
        }

        public void setLoyalty_points(float loyalty_points) {
            this.loyalty_points = loyalty_points;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }

}
