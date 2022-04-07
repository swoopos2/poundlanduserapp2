package com.poundland.retail.zzznewPoundland.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class UpdateLoyaltyPointsRequest implements Parcelable {
    @SerializedName("user_id")
    String userId;
    @SerializedName("loyalty_points")
    double loyaltyPoints;
    @SerializedName("order_id")
    String orderId;
    @SerializedName("order_amount")
    float orderAmount;

    public UpdateLoyaltyPointsRequest() {
    }

    protected UpdateLoyaltyPointsRequest(Parcel in) {
        userId = in.readString();
        loyaltyPoints = in.readDouble();
        orderId = in.readString();
        orderAmount = in.readFloat();
    }

    public static final Creator<UpdateLoyaltyPointsRequest> CREATOR = new Creator<UpdateLoyaltyPointsRequest>() {
        @Override
        public UpdateLoyaltyPointsRequest createFromParcel(Parcel in) {
            return new UpdateLoyaltyPointsRequest(in);
        }

        @Override
        public UpdateLoyaltyPointsRequest[] newArray(int size) {
            return new UpdateLoyaltyPointsRequest[size];
        }
    };

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(double loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public float getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(float orderAmount) {
        this.orderAmount = orderAmount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(userId);
        parcel.writeDouble(loyaltyPoints);
        parcel.writeString(orderId);
        parcel.writeFloat(orderAmount);
    }
}
