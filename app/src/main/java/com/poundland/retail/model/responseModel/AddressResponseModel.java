package com.poundland.retail.model.responseModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class AddressResponseModel {


    /**
     * status : true
     * message : List of customer Address.
     * addressess : [{"id":23,"user_id":20,"name":"Rahul Kr","mobile":null,"pincode":"b139pg","flat":null,"area":"varanasi","landmark":"varanasi","city":"varanasi","state":null,"country":"varanasi","latitude":"varanasi","longitude":"varanasi","type":"work","update_token":null,"created_at":"2019-11-28 13:24:56","updated_at":"2019-11-28 13:24:56","distance":""},{"id":24,"user_id":20,"name":"vinni","mobile":null,"pincode":"b13t5g","flat":null,"area":"Mau","landmark":"MAu","city":"mau","state":null,"country":"mau","latitude":"ma45890u","longitude":"varanasi","type":"work","update_token":null,"created_at":"2019-11-28 13:26:07","updated_at":"2019-11-28 13:26:07","distance":""},{"id":25,"user_id":20,"name":"vinni Mishra","mobile":null,"pincode":"b13t5g","flat":null,"area":"pilibhit","landmark":"pilibhit","city":"pilibhit","state":null,"country":"pilibhit","latitude":"pilibhit","longitude":"pilibhit","type":"HOME","update_token":null,"created_at":"2019-11-28 13:27:00","updated_at":"2019-11-28 13:27:00","distance":""}]
     */
    private boolean status;
    private String message;
    private List<AddressessBean> addressess;

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

    public List<AddressessBean> getAddressess() {
        return addressess;
    }

    public void setAddressess(List<AddressessBean> addressess) {
        this.addressess = addressess;
    }

    public static class AddressessBean implements Parcelable {
        /**
         * id : 23
         * user_id : 20
         * name : Rahul Kr
         * mobile : null
         * pincode : b139pg
         * flat : null
         * area : varanasi
         * landmark : varanasi
         * city : varanasi
         * state : null
         * country : varanasi
         * latitude : varanasi
         * longitude : varanasi
         * type : work
         * update_token : null
         * created_at : 2019-11-28 13:24:56
         * updated_at : 2019-11-28 13:24:56
         * distance : 
         */

        private int id;
        private int user_id;
        private String name;
        private String mobile;
        private String pincode;
        private String flat;
        private String area;
        private String landmark;
        private String city;
        private String state;
        private String country;
        private String latitude;
        private String longitude;
        private String type;
        private String update_token;
        private String created_at;
        private String updated_at;
        private String distance;

        protected AddressessBean(Parcel in) {
            id = in.readInt();
            user_id = in.readInt();
            name = in.readString();
            mobile = in.readString();
            pincode = in.readString();
            flat = in.readString();
            area = in.readString();
            landmark = in.readString();
            city = in.readString();
            state = in.readString();
            country = in.readString();
            latitude = in.readString();
            longitude = in.readString();
            type = in.readString();
            update_token = in.readString();
            created_at = in.readString();
            updated_at = in.readString();
            distance = in.readString();
        }

        public static final Creator<AddressessBean> CREATOR = new Creator<AddressessBean>() {
            @Override
            public AddressessBean createFromParcel(Parcel in) {
                return new AddressessBean(in);
            }

            @Override
            public AddressessBean[] newArray(int size) {
                return new AddressessBean[size];
            }
        };

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getMobile() {
            return mobile;
        }

        public void setMobile(String mobile) {
            this.mobile = mobile;
        }

        public String getPincode() {
            return pincode;
        }

        public void setPincode(String pincode) {
            this.pincode = pincode;
        }

        public String getFlat() {
            return flat;
        }

        public void setFlat(String flat) {
            this.flat = flat;
        }

        public String getArea() {
            return area;
        }

        public void setArea(String area) {
            this.area = area;
        }

        public String getLandmark() {
            return landmark;
        }

        public void setLandmark(String landmark) {
            this.landmark = landmark;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdate_token() {
            return update_token;
        }

        public void setUpdate_token(String update_token) {
            this.update_token = update_token;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(id);
            dest.writeInt(user_id);
            dest.writeString(name);
            dest.writeString(mobile);
            dest.writeString(pincode);
            dest.writeString(flat);
            dest.writeString(area);
            dest.writeString(landmark);
            dest.writeString(city);
            dest.writeString(state);
            dest.writeString(country);
            dest.writeString(latitude);
            dest.writeString(longitude);
            dest.writeString(type);
            dest.writeString(update_token);
            dest.writeString(created_at);
            dest.writeString(updated_at);
            dest.writeString(distance);
        }
    }
}
