package com.poundland.retail.model.responseModel;

public class StripeAddCardResponseModel {

    /**
     * id : card_1FxukqA4mS3BlT3Ikn4knSAp
     * object : card                                           /////////address_city : nulladdress_country : nulladdress_line1 : nulladdress_line1_check : nulladdress_line2 : nulladdress_state : nulladdress_zip : nulladdress_zip_check : null
     * brand : Visa
     * country : US
     * customer : cus_GLBQiYn3gU9V0x
     * cvc_check : pass
     * dynamic_last4 : null
     * exp_month : 4
     * exp_year : 2024
     * fingerprint : lqOi614pRZzkV6sw
     * funding : credit
     * last4 : 4242
     * metadata : {}
     * name : null
     * tokenization_method : null
     */
    private String id;
    private String object;
    private Object address_zip;
    private Object address_zip_check;
    private String brand;
    private String card_type;
    private String country;
    private String customer;
    private String cvc_check;
    private Object dynamic_last4;
    private int exp_month;
    private int exp_year;
    private String fingerprint;
    private String funding;
    private String last4;
    private MetadataBean metadata;
    private Object name;
    private Object tokenization_method;

    /////////////////////////////////////////  Below key are for update above info to server /////////////////////////////////////////////////////////
    private String stripe_cust_id;    /// getting during login time
    private String card_name;
    private String isDefault;
    private String card_number;
    private String stripe_card_id;
    private String gateway;

    public StripeAddCardResponseModel(String stripe_cust_id, String card_name, String stripe_card_id, String card_number, String card_type,
                                      String isDefault, int exp_month, int exp_year, String gateway) {
        this.stripe_cust_id = stripe_cust_id;
        this.card_name = card_name;
        this.stripe_card_id = stripe_card_id;
        this.card_number = card_number;
        this.card_type = card_type;
        this.isDefault = isDefault;
        this.exp_month = exp_month;
        this.exp_year = exp_year;
        this.gateway = gateway;
    }

    public String getCard_type() {
        return card_type;
    }

    public void setCard_type(String card_type) {
        this.card_type = card_type;
    }

    public String getGateway() {
        return gateway;
    }

    public void setGateway(String gateway) {
        this.gateway = gateway;
    }

    public String getStripe_card_id() {
        return stripe_card_id;
    }

    public void setStripe_card_id(String stripe_card_id) {
        this.stripe_card_id = stripe_card_id;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }


    public Object getAddress_zip() {
        return address_zip;
    }

    public void setAddress_zip(Object address_zip) {
        this.address_zip = address_zip;
    }

    public Object getAddress_zip_check() {
        return address_zip_check;
    }

    public void setAddress_zip_check(Object address_zip_check) {
        this.address_zip_check = address_zip_check;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getCvc_check() {
        return cvc_check;
    }

    public void setCvc_check(String cvc_check) {
        this.cvc_check = cvc_check;
    }

    public Object getDynamic_last4() {
        return dynamic_last4;
    }

    public void setDynamic_last4(Object dynamic_last4) {
        this.dynamic_last4 = dynamic_last4;
    }

    public int getExp_month() {
        return exp_month;
    }

    public void setExp_month(int exp_month) {
        this.exp_month = exp_month;
    }

    public int getExp_year() {
        return exp_year;
    }

    public void setExp_year(int exp_year) {
        this.exp_year = exp_year;
    }

    public String getFingerprint() {
        return fingerprint;
    }

    public void setFingerprint(String fingerprint) {
        this.fingerprint = fingerprint;
    }

    public String getFunding() {
        return funding;
    }

    public void setFunding(String funding) {
        this.funding = funding;
    }

    public String getLast4() {
        return last4;
    }

    public void setLast4(String last4) {
        this.last4 = last4;
    }

    public MetadataBean getMetadata() {
        return metadata;
    }

    public void setMetadata(MetadataBean metadata) {
        this.metadata = metadata;
    }

    public Object getName() {
        return name;
    }

    public void setName(Object name) {
        this.name = name;
    }

    public Object getTokenization_method() {
        return tokenization_method;
    }

    public void setTokenization_method(Object tokenization_method) {
        this.tokenization_method = tokenization_method;
    }

    public static class MetadataBean {
    }
}