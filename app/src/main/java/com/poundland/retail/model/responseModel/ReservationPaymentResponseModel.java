package com.poundland.retail.model.responseModel;

public class ReservationPaymentResponseModel {


    /**
     * status : true
     * reservation_id : 1016
     * data : {"id":1016,"cust_id":145,"venue_id":"2020042710540215","reservation_id":"20210108084509145","check_in":"2021-01-12 08:35:00","check_out":"2021-01-12 10:25:00","total_guest":2,"amount":"8.00","status":1,"date":"2021-01-12","qr_code":"uploaded/reservationQr/2920616100955090.png","stripe_capture_id":null,"active_capture_id":"21010808LW47SR44CS68JFX","refunded":"0.00","table_no":null,"floor_id":null,"is_pos_completed":0,"is_smoking":1,"message_for_venue":"ty","created_at":"2021-01-08 08:45:09","updated_at":"2021-01-08 08:47:44","is_payment":1,"card_number":"401200******1112","sub_total":"8.00","transaction_amt":"0.00","order_cancel_by":0,"app_type":"LOCAL"}
     * message : Reservation has been booked Successfully.
     */

    private boolean status;
    private int reservation_id;
    private DataBean data;
    private String message;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static class DataBean {
        /**
         * id : 1016
         * cust_id : 145
         * venue_id : 2020042710540215
         * reservation_id : 20210108084509145
         * check_in : 2021-01-12 08:35:00
         * check_out : 2021-01-12 10:25:00
         * total_guest : 2
         * amount : 8.00
         * status : 1
         * date : 2021-01-12
         * qr_code : uploaded/reservationQr/2920616100955090.png
         * stripe_capture_id : null
         * active_capture_id : 21010808LW47SR44CS68JFX
         * refunded : 0.00
         * table_no : null
         * floor_id : null
         * is_pos_completed : 0
         * is_smoking : 1
         * message_for_venue : ty
         * created_at : 2021-01-08 08:45:09
         * updated_at : 2021-01-08 08:47:44
         * is_payment : 1
         * card_number : 401200******1112
         * sub_total : 8.00
         * transaction_amt : 0.00
         * order_cancel_by : 0
         * app_type : LOCAL
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
        private Object stripe_capture_id;
        private String active_capture_id;
        private String refunded;
        private Object table_no;
        private Object floor_id;
        private int is_pos_completed;
        private int is_smoking;
        private String message_for_venue;
        private String created_at;
        private String updated_at;
        private int is_payment;
        private String card_number;
        private String sub_total;
        private String transaction_amt;
        private int order_cancel_by;
        private String app_type;

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

        public String getActive_capture_id() {
            return active_capture_id;
        }

        public void setActive_capture_id(String active_capture_id) {
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

        public String getMessage_for_venue() {
            return message_for_venue;
        }

        public void setMessage_for_venue(String message_for_venue) {
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

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getSub_total() {
            return sub_total;
        }

        public void setSub_total(String sub_total) {
            this.sub_total = sub_total;
        }

        public String getTransaction_amt() {
            return transaction_amt;
        }

        public void setTransaction_amt(String transaction_amt) {
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
}
