package com.poundland.retail.model.responseModel;

public class VenueReservationResponseModel {

    /**
     * status : true
     * message :
     * is_payment : 0
     * reservation_id : 530
     * reservation_data : {"venue_name":"Dixi Chicken Pizza","address_1":"Southport Road, Liverpool, Bootle, UK","address_2":null,"post_code":"L31QR","city_name":"Liverpool","id":530,"cust_id":84,"venue_id":"202004271734165","reservation_id":"2020122907540284","check_in":"2020-12-29 12:15:00","check_out":"2020-12-29 13:05:00","total_guest":2,"amount":"0.00","status":1,"date":"2020-12-29","qr_code":"uploaded/reservationQr/3267516092284420.png","stripe_capture_id":null,"active_capture_id":null,"refunded":"0.00","table_no":null,"floor_id":null,"is_pos_completed":0,"is_smoking":0,"message_for_venue":null,"created_at":"2020-12-29 07:54:03","updated_at":"2020-12-29 07:54:03","is_payment":0,"card_number":null,"sub_total":null,"transaction_amt":null,"order_cancel_by":0,"app_type":"LOCAL"}
     * loginUserData : {"first_name":"Sweta","last_name":"Gupta","email":"swetagupta0022@gmail.com","contact_no":"919616666919"}
     */

    private boolean status;
    private String message;
    private int is_payment;
    private int reservation_id;
    private ReservationDataBean reservation_data;
    private LoginUserDataBean loginUserData;

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

    public int getIs_payment() {
        return is_payment;
    }

    public void setIs_payment(int is_payment) {
        this.is_payment = is_payment;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public ReservationDataBean getReservation_data() {
        return reservation_data;
    }

    public void setReservation_data(ReservationDataBean reservation_data) {
        this.reservation_data = reservation_data;
    }

    public LoginUserDataBean getLoginUserData() {
        return loginUserData;
    }

    public void setLoginUserData(LoginUserDataBean loginUserData) {
        this.loginUserData = loginUserData;
    }

    public static class ReservationDataBean {
        /**
         * venue_name : Dixi Chicken Pizza
         * address_1 : Southport Road, Liverpool, Bootle, UK
         * address_2 : null
         * post_code : L31QR
         * city_name : Liverpool
         * id : 530
         * cust_id : 84
         * venue_id : 202004271734165
         * reservation_id : 2020122907540284
         * check_in : 2020-12-29 12:15:00
         * check_out : 2020-12-29 13:05:00
         * total_guest : 2
         * amount : 0.00
         * status : 1
         * date : 2020-12-29
         * qr_code : uploaded/reservationQr/3267516092284420.png
         * stripe_capture_id : null
         * active_capture_id : null
         * refunded : 0.00
         * table_no : null
         * floor_id : null
         * is_pos_completed : 0
         * is_smoking : 0
         * message_for_venue : null
         * created_at : 2020-12-29 07:54:03
         * updated_at : 2020-12-29 07:54:03
         * is_payment : 0
         * card_number : null
         * sub_total : null
         * transaction_amt : null
         * order_cancel_by : 0
         * app_type : LOCAL
         */

        private String venue_name;
        private String address_1;
        private Object address_2;
        private String post_code;
        private String city_name;
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
        private Object stripe_capture_id;
        private Object active_capture_id;
        private String refunded;
        private Object table_no;
        private Object floor_id;
        private int is_pos_completed;
        private int is_smoking;
        private int is_smoking_allow;
        private Object message_for_venue;
        private String created_at;
        private String updated_at;
        private int is_payment;
        private Object card_number;
        private Object sub_total;
        private Object transaction_amt;
        private int order_cancel_by;
        private String app_type;

        public int getIs_smoking_allow() {
            return is_smoking_allow;
        }

        public void setIs_smoking_allow(int is_smoking_allow) {
            this.is_smoking_allow = is_smoking_allow;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public Object getAddress_2() {
            return address_2;
        }

        public void setAddress_2(Object address_2) {
            this.address_2 = address_2;
        }

        public String getPost_code() {
            return post_code;
        }

        public void setPost_code(String post_code) {
            this.post_code = post_code;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
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

        public Object getTable_no() {
            return table_no;
        }

        public void setTable_no(Object table_no) {
            this.table_no = table_no;
        }

        public Object getFloor_id() {
            return floor_id;
        }

        public void setFloor_id(Object floor_id) {
            this.floor_id = floor_id;
        }

        public int getIs_pos_completed() {
            return is_pos_completed;
        }

        public void setIs_pos_completed(int is_pos_completed) {
            this.is_pos_completed = is_pos_completed;
        }

        public int getIs_smoking() {
            return is_smoking;
        }

        public void setIs_smoking(int is_smoking) {
            this.is_smoking = is_smoking;
        }

        public Object getMessage_for_venue() {
            return message_for_venue;
        }

        public void setMessage_for_venue(Object message_for_venue) {
            this.message_for_venue = message_for_venue;
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

        public int getOrder_cancel_by() {
            return order_cancel_by;
        }

        public void setOrder_cancel_by(int order_cancel_by) {
            this.order_cancel_by = order_cancel_by;
        }

        public String getApp_type() {
            return app_type;
        }

        public void setApp_type(String app_type) {
            this.app_type = app_type;
        }
    }

    public static class LoginUserDataBean {
        /**
         * first_name : Sweta
         * last_name : Gupta
         * email : swetagupta0022@gmail.com
         * contact_no : 919616666919
         */

        private String first_name;
        private String last_name;
        private String email;
        private String contact_no;

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

        public String getContact_no() {
            return contact_no;
        }

        public void setContact_no(String contact_no) {
            this.contact_no = contact_no;
        }
    }
}