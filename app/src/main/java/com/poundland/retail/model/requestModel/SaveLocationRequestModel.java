package com.poundland.retail.model.requestModel;

public class SaveLocationRequestModel {

    /**
     * name : Rahul Kr
     * contact_no : 8605602556
     * post_code : b139pg
     * area : varanasi
     * flat : varanasi
     * landmark : varanasi
     * city : varanasi
     * country : varanasi
     * latitude : varanasi
     * longitude : varanasi
     * type : work
     */
    private String address_id;

    private String name;
    private String contact_no;
    private String post_code;
    private String area;
    private String flat;
    private String landmark;
    private String city;
    private String country;
    private String latitude;
    private String longitude;
    private String type;


    public SaveLocationRequestModel(String address_id, String name, String contact_no, String post_code, String area, String flat, String landmark, String city, String country, String latitude, String longitude, String type) {
        this.address_id = address_id;
        this.name = name;
        this.contact_no = contact_no;
        this.post_code = post_code;
        this.area = area;
        this.flat = flat;
        this.landmark = landmark;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }                               ////// update address api

    public SaveLocationRequestModel(String address_id) {     /// delete address api
        this.address_id = address_id;
    }


    public SaveLocationRequestModel(String name, String contact_no, String post_code, String area, String flat, String landmark, String city, String country, String latitude, String longitude, String type) {
        this.name = name;
        this.contact_no = contact_no;
        this.post_code = post_code;
        this.area = area;
        this.flat = flat;
        this.landmark = landmark;
        this.city = city;
        this.country = country;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }                                                   ///////////////////////// save address api
    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getFlat() {
        return flat;
    }

    public void setFlat(String flat) {
        this.flat = flat;
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
}
