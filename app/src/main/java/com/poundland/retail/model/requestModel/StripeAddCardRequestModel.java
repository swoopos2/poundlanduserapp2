package com.poundland.retail.model.requestModel;

public class StripeAddCardRequestModel {

    /////////////////////////////////////////  Below key are for update above info to server /////////////////////////////////////////////////////////
    private String stripe_cust_id;    /// getting during login time
    private String card_name;
    private String isDefault;
    private String card_number;
    private String stripe_card_id;
    private String gateway;
    private String card_type;
    private String exp_month;
    private String exp_year;

    private String address_line1;
    private String address_line2;
    private String address_line3;
    private String post_code;

    public StripeAddCardRequestModel(String stripe_cust_id, String card_name, String stripe_card_id, String card_number, String card_type,
                                     String isDefault, String exp_month, String exp_year, String gateway,
                                     String address_line1,String address_line2,String address_line3,String post_code) {
        this.stripe_cust_id = stripe_cust_id;
        this.card_name = card_name;
        this.stripe_card_id = stripe_card_id;
        this.card_number = card_number;
        this.card_type = card_type;
        this.isDefault = isDefault;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
        this.gateway = gateway;
        this.address_line1 = address_line1;
        this.address_line2 = address_line2;
        this.address_line3 = address_line3;
        this.post_code = post_code;
    }

    public StripeAddCardRequestModel(String gateway) {
        this.gateway = gateway;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public String getAddress_line1() {
        return address_line1;
    }

    public void setAddress_line1(String address_line1) {
        this.address_line1 = address_line1;
    }

    public String getAddress_line2() {
        return address_line2;
    }

    public void setAddress_line2(String address_line2) {
        this.address_line2 = address_line2;
    }

    public String getAddress_line3() {
        return address_line3;
    }

    public void setAddress_line3(String address_line3) {
        this.address_line3 = address_line3;
    }

    public String getStripe_cust_id() {
        return stripe_cust_id;
    }

    public void setStripe_cust_id(String stripe_cust_id) {
        this.stripe_cust_id = stripe_cust_id;
    }

    public String getCard_name() {
        return card_name;
    }

    public void setCard_name(String card_name) {
        this.card_name = card_name;
    }

    public String getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(String isDefault) {
        this.isDefault = isDefault;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getStripe_card_id() {
        return stripe_card_id;
    }

    public void setStripe_card_id(String stripe_card_id) {
        this.stripe_card_id = stripe_card_id;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getExp_month() {
        return exp_month;
    }

    public void setExp_month(String exp_month) {
        this.exp_month = exp_month;
    }

    public String getExp_year() {
        return exp_year;
    }

    public void setExp_year(String exp_year) {
        this.exp_year = exp_year;
    }
}