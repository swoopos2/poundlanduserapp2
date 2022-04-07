package com.poundland.retail.model.requestModel;

public class ClaimStoreRequestModel {

    private String venueidAI;
    private String name;
    private String contactno;
    private String email;
    private String message;
    private String ip_address;

    private String latitude;
    private String longitude;



    public ClaimStoreRequestModel(String venueidAI, String name, String contactno, String email, String message, String ip_address) {
        this.venueidAI = venueidAI;
        this.name = name;
        this.contactno = contactno;
        this.email = email;
        this.message = message;
        this.ip_address = ip_address;
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

    public String getVenueidAI() {
        return venueidAI;
    }

    public void setVenueidAI(String venueidAI) {
        this.venueidAI = venueidAI;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getIp_address() {
        return ip_address;
    }

    public void setIp_address(String ip_address) {
        this.ip_address = ip_address;
    }
}
