package com.poundland.retail.zzznewPoundland.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.poundland.retail.zzznewPoundland.CommonResponse;

public class UpdateLoyaltyPointsResponse implements Parcelable {
    /**
     * status : true
     * message : Ccustomer details.
     * customer : {"id":2,"first_name":"Anand","last_name":"Kumar","email":"","contact":"918750110867","gender":"M","dob":"1982-03-10","pos_id":"2","email_verified_at":"2022-03-29T08:34:46.000000Z","loyalty_points":"85.00","user_type":1,"fire_base_id":"fVqAFcdoR9qOvrXbozc-HK:APA91bFawdOi56IDZPPaPgfg5ubyinlWDiap8uDgh2Nior2ctl4SFvxg33e7NCzPc-t3mIyd-AmQjI4i48IGtzzec80hLAkgUc7V8Fk-um65EwcXh0wAtfu3eAGXUJJyTAysLowNFpz-","status":1,"created_at":"2022-03-29 08:34:46","updated_at":"2022-04-05 09:25:23","name":"Anand Kumar"}
     */

    private boolean status;
    private String message;
    private double earn_loyalty_points;
    private CustomerBean customer;

    protected UpdateLoyaltyPointsResponse(Parcel in) {
        status = in.readByte() != 0;
        message = in.readString();
        earn_loyalty_points = in.readDouble();
        customer = in.readParcelable(CustomerBean.class.getClassLoader());
    }

    public static final Creator<UpdateLoyaltyPointsResponse> CREATOR = new Creator<UpdateLoyaltyPointsResponse>() {
        @Override
        public UpdateLoyaltyPointsResponse createFromParcel(Parcel in) {
            return new UpdateLoyaltyPointsResponse(in);
        }

        @Override
        public UpdateLoyaltyPointsResponse[] newArray(int size) {
            return new UpdateLoyaltyPointsResponse[size];
        }
    };

    public double getEarn_loyalty_points() {
        return earn_loyalty_points;
    }

    public void setEarn_loyalty_points(double earn_loyalty_points) {
        this.earn_loyalty_points = earn_loyalty_points;
    }

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

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeByte((byte) (status ? 1 : 0));
        parcel.writeString(message);
        parcel.writeDouble(earn_loyalty_points);
        parcel.writeParcelable(customer, i);
    }

    public static class CustomerBean implements Parcelable {
        /**
         * id : 2
         * first_name : Anand
         * last_name : Kumar
         * email :
         * contact : 918750110867
         * gender : M
         * dob : 1982-03-10
         * pos_id : 2
         * email_verified_at : 2022-03-29T08:34:46.000000Z
         * loyalty_points : 85.00
         * user_type : 1
         * fire_base_id : fVqAFcdoR9qOvrXbozc-HK:APA91bFawdOi56IDZPPaPgfg5ubyinlWDiap8uDgh2Nior2ctl4SFvxg33e7NCzPc-t3mIyd-AmQjI4i48IGtzzec80hLAkgUc7V8Fk-um65EwcXh0wAtfu3eAGXUJJyTAysLowNFpz-
         * status : 1
         * created_at : 2022-03-29 08:34:46
         * updated_at : 2022-04-05 09:25:23
         * name : Anand Kumar
         */

        private int id;
        private String first_name;
        private String last_name;
        private String email;
        private String contact;
        private String gender;
        private String dob;
        private String pos_id;
        private String email_verified_at;
        private String loyalty_points;
        private int user_type;
        private String fire_base_id;
        private int status;
        private String created_at;
        private String updated_at;
        private String name;

        protected CustomerBean(Parcel in) {
            id = in.readInt();
            first_name = in.readString();
            last_name = in.readString();
            email = in.readString();
            contact = in.readString();
            gender = in.readString();
            dob = in.readString();
            pos_id = in.readString();
            email_verified_at = in.readString();
            loyalty_points = in.readString();
            user_type = in.readInt();
            fire_base_id = in.readString();
            status = in.readInt();
            created_at = in.readString();
            updated_at = in.readString();
            name = in.readString();
        }

        public static final Creator<CustomerBean> CREATOR = new Creator<CustomerBean>() {
            @Override
            public CustomerBean createFromParcel(Parcel in) {
                return new CustomerBean(in);
            }

            @Override
            public CustomerBean[] newArray(int size) {
                return new CustomerBean[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public String getLoyalty_points() {
            return loyalty_points;
        }

        public void setLoyalty_points(String loyalty_points) {
            this.loyalty_points = loyalty_points;
        }

        public int getUser_type() {
            return user_type;
        }

        public void setUser_type(int user_type) {
            this.user_type = user_type;
        }

        public String getFire_base_id() {
            return fire_base_id;
        }

        public void setFire_base_id(String fire_base_id) {
            this.fire_base_id = fire_base_id;
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

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeInt(id);
            parcel.writeString(first_name);
            parcel.writeString(last_name);
            parcel.writeString(email);
            parcel.writeString(contact);
            parcel.writeString(gender);
            parcel.writeString(dob);
            parcel.writeString(pos_id);
            parcel.writeString(email_verified_at);
            parcel.writeString(loyalty_points);
            parcel.writeInt(user_type);
            parcel.writeString(fire_base_id);
            parcel.writeInt(status);
            parcel.writeString(created_at);
            parcel.writeString(updated_at);
            parcel.writeString(name);
        }
    }
}
