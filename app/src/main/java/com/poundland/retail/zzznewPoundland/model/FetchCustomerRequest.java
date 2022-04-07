package com.poundland.retail.zzznewPoundland.model;

import com.google.gson.annotations.SerializedName;

public class FetchCustomerRequest {
    @SerializedName("user_id")
    String userId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
