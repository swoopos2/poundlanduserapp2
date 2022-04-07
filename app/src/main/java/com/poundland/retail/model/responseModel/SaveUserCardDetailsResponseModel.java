package com.poundland.retail.model.responseModel;

public class SaveUserCardDetailsResponseModel {
    /**
     * status : true
     * message : Customer Cards Details Saved Successfully.
     * cards : 335
     * card_details : {"user_id":165,"stripe_cust_id":"cus_INJYHt2yjksWLU","stripe_card_id":"card_1J3hrOA4mS3BlT3IMHurmj28","card_name":"Noah","card_number":"************4242","exp_month":"02","exp_year":"2042","address_line1":"Flat B","address_line2":"5 Oaklands Road","address_line3":"Wolverhampton","post_code":"wv30ds","card_type":"Visa","isDefault":"0","gateway":"stripe","updated_at":"2021-06-18 14:36:34","created_at":"2021-06-18 14:36:34","id":335}
     */

    private boolean status;
    private String message;
    private int cards;
    private CardDetailsBean card_details;

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

    public int getCards() {
        return cards;
    }

    public void setCards(int cards) {
        this.cards = cards;
    }

    public CardDetailsBean getCard_details() {
        return card_details;
    }

    public void setCard_details(CardDetailsBean card_details) {
        this.card_details = card_details;
    }

    public static class CardDetailsBean {
        /**
         * user_id : 165
         * stripe_cust_id : cus_INJYHt2yjksWLU
         * stripe_card_id : card_1J3hrOA4mS3BlT3IMHurmj28
         * card_name : Noah
         * card_number : ************4242
         * exp_month : 02
         * exp_year : 2042
         * address_line1 : Flat B
         * address_line2 : 5 Oaklands Road
         * address_line3 : Wolverhampton
         * post_code : wv30ds
         * card_type : Visa
         * isDefault : 0
         * gateway : stripe
         * updated_at : 2021-06-18 14:36:34
         * created_at : 2021-06-18 14:36:34
         * id : 335
         */

        private int user_id;
        private String stripe_cust_id;
        private String stripe_card_id;
        private String card_name;
        private String card_number;
        private String exp_month;
        private String exp_year;
        private String address_line1;
        private String address_line2;
        private String address_line3;
        private String post_code;
        private String card_type;
        private String isDefault;
        private String gateway;
        private String updated_at;
        private String created_at;
        private int id;

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getStripe_cust_id() {
            return stripe_cust_id;
        }

        public void setStripe_cust_id(String stripe_cust_id) {
            this.stripe_cust_id = stripe_cust_id;
        }

        public String getStripe_card_id() {
            return stripe_card_id;
        }

        public void setStripe_card_id(String stripe_card_id) {
            this.stripe_card_id = stripe_card_id;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
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

        public String getPost_code() {
            return post_code;
        }

        public void setPost_code(String post_code) {
            this.post_code = post_code;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        public String getGateway() {
            return gateway;
        }

        public void setGateway(String gateway) {
            this.gateway = gateway;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }

}
