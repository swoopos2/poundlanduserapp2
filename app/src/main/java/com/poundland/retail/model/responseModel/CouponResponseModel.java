package com.poundland.retail.model.responseModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class CouponResponseModel implements Parcelable{


    /**
     * status : true
     * couponData : [{"coupon_code":"96804C","coupon_title":"10 % DISC FOR ALL","offer_type":1,"offer_amount":"10.00","max_discount":"10.00","minimum_spend":"10.00","start_date":"2021-05-04","end_date":"2021-07-31","offer_time_start":"00:00:00","offer_time_end":"23:00:00","coupon_usage_limit":1000,"total_used_coupons":0}]
     */
// {"status":true,"message":"Applied Successfully","coupon_amount":2.5}   // response of applyCoupon Api

    private boolean status;
    private List<CouponDataBean> couponData;
    private String message;
    private double coupon_amount;

    protected CouponResponseModel(Parcel in) {
        status = in.readByte() != 0;
        couponData = in.createTypedArrayList(CouponDataBean.CREATOR);
        message = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeByte((byte) (status ? 1 : 0));
        dest.writeTypedList(couponData);
        dest.writeString(message);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<CouponResponseModel> CREATOR = new Creator<CouponResponseModel>() {
        @Override
        public CouponResponseModel createFromParcel(Parcel in) {
            return new CouponResponseModel(in);
        }

        @Override
        public CouponResponseModel[] newArray(int size) {
            return new CouponResponseModel[size];
        }
    };

    public double getCoupon_amount() {
        return coupon_amount;
    }

    public void setCoupon_amount(double coupon_amount) {
        this.coupon_amount = coupon_amount;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<CouponDataBean> getCouponData() {
        return couponData;
    }

    public void setCouponData(List<CouponDataBean> couponData) {
        this.couponData = couponData;
    }

    public static class CouponDataBean implements Parcelable {
        /**
         * coupon_code : 96804C
         * coupon_title : 10 % DISC FOR ALL
         * offer_type : 1
         * offer_amount : 10.00
         * max_discount : 10.00
         * minimum_spend : 10.00
         * start_date : 2021-05-04
         * end_date : 2021-07-31
         * offer_time_start : 00:00:00
         * offer_time_end : 23:00:00
         * coupon_usage_limit : 1000
         * total_used_coupons : 0
         */

        private String coupon_code;
        private String coupon_title;
        private int offer_type;
        private String offer_amount;
        private String max_discount;
        private String minimum_spend;
        private String start_date;
        private String end_date;
        private String offer_time_start;
        private String offer_time_end;
        private int coupon_usage_limit;
        private int total_used_coupons;

        protected CouponDataBean(Parcel in) {
            coupon_code = in.readString();
            coupon_title = in.readString();
            offer_type = in.readInt();
            offer_amount = in.readString();
            max_discount = in.readString();
            minimum_spend = in.readString();
            start_date = in.readString();
            end_date = in.readString();
            offer_time_start = in.readString();
            offer_time_end = in.readString();
            coupon_usage_limit = in.readInt();
            total_used_coupons = in.readInt();
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(coupon_code);
            dest.writeString(coupon_title);
            dest.writeInt(offer_type);
            dest.writeString(offer_amount);
            dest.writeString(max_discount);
            dest.writeString(minimum_spend);
            dest.writeString(start_date);
            dest.writeString(end_date);
            dest.writeString(offer_time_start);
            dest.writeString(offer_time_end);
            dest.writeInt(coupon_usage_limit);
            dest.writeInt(total_used_coupons);
        }

        @Override
        public int describeContents() {
            return 0;
        }

        public static final Creator<CouponDataBean> CREATOR = new Creator<CouponDataBean>() {
            @Override
            public CouponDataBean createFromParcel(Parcel in) {
                return new CouponDataBean(in);
            }

            @Override
            public CouponDataBean[] newArray(int size) {
                return new CouponDataBean[size];
            }
        };

        public String getCoupon_code() {
            return coupon_code;
        }

        public void setCoupon_code(String coupon_code) {
            this.coupon_code = coupon_code;
        }

        public String getCoupon_title() {
            return coupon_title;
        }

        public void setCoupon_title(String coupon_title) {
            this.coupon_title = coupon_title;
        }

        public int getOffer_type() {
            return offer_type;
        }

        public void setOffer_type(int offer_type) {
            this.offer_type = offer_type;
        }

        public String getOffer_amount() {
            return offer_amount;
        }

        public void setOffer_amount(String offer_amount) {
            this.offer_amount = offer_amount;
        }

        public String getMax_discount() {
            return max_discount;
        }

        public void setMax_discount(String max_discount) {
            this.max_discount = max_discount;
        }

        public String getMinimum_spend() {
            return minimum_spend;
        }

        public void setMinimum_spend(String minimum_spend) {
            this.minimum_spend = minimum_spend;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getOffer_time_start() {
            return offer_time_start;
        }

        public void setOffer_time_start(String offer_time_start) {
            this.offer_time_start = offer_time_start;
        }

        public String getOffer_time_end() {
            return offer_time_end;
        }

        public void setOffer_time_end(String offer_time_end) {
            this.offer_time_end = offer_time_end;
        }

        public int getCoupon_usage_limit() {
            return coupon_usage_limit;
        }

        public void setCoupon_usage_limit(int coupon_usage_limit) {
            this.coupon_usage_limit = coupon_usage_limit;
        }

        public int getTotal_used_coupons() {
            return total_used_coupons;
        }

        public void setTotal_used_coupons(int total_used_coupons) {
            this.total_used_coupons = total_used_coupons;
        }
    }
}
