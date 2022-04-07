package com.poundland.retail.notificationService;

public class NotificationDiscount_OfferModel {

    /**
     * image : app-assets/images/logo-1.png
     * special_offer_id : 12
     * data : {"end_date":"2020-04-30","offer_time_end":"23:55:00","gender":"both","combo_disc_per":null,"merchant_venue_id":"202002121635135","created_at":"2020-04-11 14:35:47","merchant_id":"5","offer_title":"2% off","updated_at":"2020-04-11 14:35:47","terms_conditions":"TNC","product_id":"{\"3\":[\"789\"]}","cat_id":"{\"3\":[\"789\"]}","offer_time_start":"00:00:00","id":12,"age_brackets":"0-17,18-21,22-25,26-29,30-33,34-37,38-41,42-45,46-50,50-100","offer_image":null,"offer_type":"discper","disc_amt":0,"days_available":"Mon,Tue,Wed,Thu,Fri,Sat,Sun","start_date":"2020-04-11","combo_disc_amt":null,"disc_per":"2","beacon_id":"","combo_product_id":"combo_product_id","status":"Enable"}
     * login_device_type : ANDROID
     * title : 2% off
     * message : 2% off
     * type : 3
     * venue_id : 202002121635135
     * token : daEwlvMvHDA:APA91bHgf01xdjeByEa4_ZibZT38wBjrMt1Ihn-Xe6kYc6aNPKkPZRB5_Lzs2MV-FamLNdmagvONO6w8cMRbMKqJy7Zfm6EKBaEiNprlw4_HAltKvshvNAGQ3rz91G7U7EwAtcCXVYBH
     */
    private DataBean data;
    private String image;
    private int special_offer_id;
    private String login_device_type;
    private String title;
    private String message;
    private int type;
    private String venue_id;
    private String token;


    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public int getSpecial_offer_id() {
        return special_offer_id;
    }

    public void setSpecial_offer_id(int special_offer_id) {
        this.special_offer_id = special_offer_id;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getLogin_device_type() {
        return login_device_type;
    }

    public void setLogin_device_type(String login_device_type) {
        this.login_device_type = login_device_type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public static class DataBean {
        /**
         * end_date : 2020-04-30
         * offer_time_end : 23:55:00
         * gender : both
         * combo_disc_per : null
         * merchant_venue_id : 202002121635135
         * created_at : 2020-04-11 14:35:47
         * merchant_id : 5
         * offer_title : 2% off
         * updated_at : 2020-04-11 14:35:47
         * terms_conditions : TNC
         * product_id : {"3":["789"]}
         * cat_id : {"3":["789"]}
         * offer_time_start : 00:00:00
         * id : 12
         * age_brackets : 0-17,18-21,22-25,26-29,30-33,34-37,38-41,42-45,46-50,50-100
         * offer_image : null
         * offer_type : discper
         * disc_amt : 0
         * days_available : Mon,Tue,Wed,Thu,Fri,Sat,Sun
         * start_date : 2020-04-11
         * combo_disc_amt : null
         * disc_per : 2
         * beacon_id :
         * combo_product_id : combo_product_id
         * status : Enable
         */

        private String end_date;
        private String offer_time_end;
        private String gender;
        private double combo_disc_per;
        private String merchant_venue_id;
        private String created_at;
        private String merchant_id;
        private String offer_title;
        private String updated_at;
        private String terms_conditions;
        private String product_id;
        private String cat_id;
        private String offer_time_start;
        private int id;
        private String age_brackets;
        private Object offer_image;
        private String offer_type;
        private int disc_amt;
        private String days_available;
        private String start_date;
        private double combo_disc_amt;
        private double disc_per;
        private String beacon_id;
        private String combo_product_id;
        private String status;

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getOffer_time_end() {
            return offer_time_end;
        }

        public void setOffer_time_end(String offer_time_end) {
            this.offer_time_end = offer_time_end;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public double getCombo_disc_per() {
            return combo_disc_per;
        }

        public void setCombo_disc_per(double combo_disc_per) {
            this.combo_disc_per = combo_disc_per;
        }

        public String getMerchant_venue_id() {
            return merchant_venue_id;
        }

        public void setMerchant_venue_id(String merchant_venue_id) {
            this.merchant_venue_id = merchant_venue_id;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getOffer_title() {
            return offer_title;
        }

        public void setOffer_title(String offer_title) {
            this.offer_title = offer_title;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getTerms_conditions() {
            return terms_conditions;
        }

        public void setTerms_conditions(String terms_conditions) {
            this.terms_conditions = terms_conditions;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getCat_id() {
            return cat_id;
        }

        public void setCat_id(String cat_id) {
            this.cat_id = cat_id;
        }

        public String getOffer_time_start() {
            return offer_time_start;
        }

        public void setOffer_time_start(String offer_time_start) {
            this.offer_time_start = offer_time_start;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getAge_brackets() {
            return age_brackets;
        }

        public void setAge_brackets(String age_brackets) {
            this.age_brackets = age_brackets;
        }

        public Object getOffer_image() {
            return offer_image;
        }

        public void setOffer_image(Object offer_image) {
            this.offer_image = offer_image;
        }

        public String getOffer_type() {
            return offer_type;
        }

        public void setOffer_type(String offer_type) {
            this.offer_type = offer_type;
        }

        public int getDisc_amt() {
            return disc_amt;
        }

        public void setDisc_amt(int disc_amt) {
            this.disc_amt = disc_amt;
        }

        public String getDays_available() {
            return days_available;
        }

        public void setDays_available(String days_available) {
            this.days_available = days_available;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public double getCombo_disc_amt() {
            return combo_disc_amt;
        }

        public void setCombo_disc_amt(double combo_disc_amt) {
            this.combo_disc_amt = combo_disc_amt;
        }

        public double getDisc_per() {
            return disc_per;
        }

        public void setDisc_per(double disc_per) {
            this.disc_per = disc_per;
        }

        public String getBeacon_id() {
            return beacon_id;
        }

        public void setBeacon_id(String beacon_id) {
            this.beacon_id = beacon_id;
        }

        public String getCombo_product_id() {
            return combo_product_id;
        }

        public void setCombo_product_id(String combo_product_id) {
            this.combo_product_id = combo_product_id;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
