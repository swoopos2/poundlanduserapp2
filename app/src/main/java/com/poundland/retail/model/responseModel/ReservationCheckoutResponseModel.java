package com.poundland.retail.model.responseModel;

import java.util.List;

public class ReservationCheckoutResponseModel {


    /**
     * status : true
     * data : {"id":1006,"cust_id":145,"venue_id":"2020102009594316","reservation_id":"20210106125805145","check_in":"2021-01-06 13:30:00","check_out":"2021-01-06 14:20:00","total_guest":2,"amount":"4.00","status":1,"date":"2021-01-06","qr_code":"uploaded/reservationQr/3918516099378850.png","stripe_capture_id":null,"active_capture_id":null,"refunded":"0.00","created_at":"2021-01-06 12:58:05","updated_at":"2021-01-06 12:58:23","is_payment":0,"card_number":null,"sub_total":null,"transaction_amt":null,"venue_name":"Food Street","payment_gatway":"stripe"}
     * theme : null
     */

    private boolean status;
    private DataBean data;
    private Object theme;
    private List<DefaultCardsBean> defaultCards;

    public List<DefaultCardsBean> getDefaultCards() {
        return defaultCards;
    }

    public void setDefaultCards(List<DefaultCardsBean> defaultCards) {
        this.defaultCards = defaultCards;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public Object getTheme() {
        return theme;
    }

    public void setTheme(Object theme) {
        this.theme = theme;
    }

    public static class DataBean {
        /**
         * id : 1006
         * cust_id : 145
         * venue_id : 2020102009594316
         * reservation_id : 20210106125805145
         * check_in : 2021-01-06 13:30:00
         * check_out : 2021-01-06 14:20:00
         * total_guest : 2
         * amount : 4.00
         * status : 1
         * date : 2021-01-06
         * qr_code : uploaded/reservationQr/3918516099378850.png
         * stripe_capture_id : null
         * active_capture_id : null
         * refunded : 0.00
         * created_at : 2021-01-06 12:58:05
         * updated_at : 2021-01-06 12:58:23
         * is_payment : 0
         * card_number : null
         * sub_total : null
         * transaction_amt : null
         * venue_name : Food Street
         * payment_gatway : stripe
         */

        private int id;
        private int cust_id;
        private String venue_id;
        private String reservation_id;
        private String check_in;
        private String check_out;
        private int total_guest;
        private String amount;
        private int status;
        private String date;
        private String qr_code;
        private String venue_images;
        private String address_1;
        private String address_2;
        private Object stripe_capture_id;
        private Object active_capture_id;
        private String refunded;
        private String created_at;
        private String updated_at;
        private int is_payment;
        private Object card_number;
        private Object sub_total;
        private Object transaction_amt;
        private String venue_name;
        private String payment_gatway;

        public String getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(String venue_images) {
            this.venue_images = venue_images;
        }

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public String getAddress_2() {
            return address_2;
        }

        public void setAddress_2(String address_2) {
            this.address_2 = address_2;
        }


        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCust_id() {
            return cust_id;
        }

        public void setCust_id(int cust_id) {
            this.cust_id = cust_id;
        }

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public String getReservation_id() {
            return reservation_id;
        }

        public void setReservation_id(String reservation_id) {
            this.reservation_id = reservation_id;
        }

        public String getCheck_in() {
            return check_in;
        }

        public void setCheck_in(String check_in) {
            this.check_in = check_in;
        }

        public String getCheck_out() {
            return check_out;
        }

        public void setCheck_out(String check_out) {
            this.check_out = check_out;
        }

        public int getTotal_guest() {
            return total_guest;
        }

        public void setTotal_guest(int total_guest) {
            this.total_guest = total_guest;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getQr_code() {
            return qr_code;
        }

        public void setQr_code(String qr_code) {
            this.qr_code = qr_code;
        }

        public Object getStripe_capture_id() {
            return stripe_capture_id;
        }

        public void setStripe_capture_id(Object stripe_capture_id) {
            this.stripe_capture_id = stripe_capture_id;
        }

        public Object getActive_capture_id() {
            return active_capture_id;
        }

        public void setActive_capture_id(Object active_capture_id) {
            this.active_capture_id = active_capture_id;
        }

        public String getRefunded() {
            return refunded;
        }

        public void setRefunded(String refunded) {
            this.refunded = refunded;
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

        public int getIs_payment() {
            return is_payment;
        }

        public void setIs_payment(int is_payment) {
            this.is_payment = is_payment;
        }

        public Object getCard_number() {
            return card_number;
        }

        public void setCard_number(Object card_number) {
            this.card_number = card_number;
        }

        public Object getSub_total() {
            return sub_total;
        }

        public void setSub_total(Object sub_total) {
            this.sub_total = sub_total;
        }

        public Object getTransaction_amt() {
            return transaction_amt;
        }

        public void setTransaction_amt(Object transaction_amt) {
            this.transaction_amt = transaction_amt;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getPayment_gatway() {
            return payment_gatway;
        }

        public void setPayment_gatway(String payment_gatway) {
            this.payment_gatway = payment_gatway;
        }
    }


    public static class DefaultCardsBean  {
        /**
         * id : 238
         * user_id : 145
         * stripe_cust_id : 20110312FW46RJ56MJ72BVC
         * stripe_card_id : 20110312FW46RJ56MJ72BVC
         * card_name : Shakti Singh
         * card_number : 401200******1112
         * exp_month : null
         * exp_year : null
         * card_type : Visa Credit
         * isDefault : 0
         * gateway : active
         */

        private int id;
        private int user_id;
        private String stripe_cust_id;
        private String stripe_card_id;
        private String card_name;
        private String card_number;
        private Object exp_month;
        private Object exp_year;
        private String card_type;
        private int isDefault;
        private String gateway;

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

        public Object getExp_month() {
            return exp_month;
        }

        public void setExp_month(Object exp_month) {
            this.exp_month = exp_month;
        }

        public Object getExp_year() {
            return exp_year;
        }

        public void setExp_year(Object exp_year) {
            this.exp_year = exp_year;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public String getGateway() {
            return gateway;
        }

        public void setGateway(String gateway) {
            this.gateway = gateway;
        }
    }

}
