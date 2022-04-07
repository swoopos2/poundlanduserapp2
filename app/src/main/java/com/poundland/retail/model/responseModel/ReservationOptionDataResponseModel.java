package com.poundland.retail.model.responseModel;

import java.util.List;

public class ReservationOptionDataResponseModel {


    /**
     * status : true
     * data : {"venue":{"venue_id":"2020102009594316","is_booking_allow":1,"booking_slot_gap":15,"cleaning_buffer":10,"restaurant_capacity":100,"booking_fee_type":1,"max_booking_duration":7,"max_guest":10,"is_refundable":0,"default_reservation_length":50},"reservation_slot":[{"id":32680,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"11:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32681,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"11:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32682,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"11:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32683,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"11:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32684,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"12:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32685,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"12:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32686,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"12:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32687,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"12:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32688,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"13:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32689,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"13:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32690,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"13:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32691,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"13:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32692,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"14:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32693,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"14:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32694,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"14:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32695,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"14:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32696,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"15:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32697,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"15:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32698,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"15:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32699,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"15:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32700,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"16:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32701,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"16:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32702,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"16:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32703,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"16:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32704,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"17:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32705,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"17:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32706,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"17:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32707,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"17:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32708,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"18:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32709,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"18:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32710,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"18:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32711,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"18:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"}]}
     * reservationcloseday : []
     */

    private String message;
    private boolean status;
    private DataBean data;
    private List<ReservationclosedayBean> reservationcloseday;

    public List<ReservationclosedayBean> getReservationcloseday() {
        return reservationcloseday;
    }

    public void setReservationcloseday(List<ReservationclosedayBean> reservationcloseday) {
        this.reservationcloseday = reservationcloseday;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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



    public static class DataBean {
        /**
         * venue : {"venue_id":"2020102009594316","is_booking_allow":1,"booking_slot_gap":15,"cleaning_buffer":10,"restaurant_capacity":100,"booking_fee_type":1,"max_booking_duration":7,"max_guest":10,"is_refundable":0,"default_reservation_length":50}
         * reservation_slot : [{"id":32680,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"11:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32681,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"11:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32682,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"11:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32683,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"11:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32684,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"12:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32685,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"12:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32686,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"12:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32687,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"12:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32688,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"13:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32689,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"13:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32690,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"13:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32691,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"13:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32692,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"14:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32693,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"14:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32694,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"14:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32695,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"14:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32696,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"15:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32697,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"15:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32698,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"15:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32699,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"15:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32700,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"16:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32701,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"16:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32702,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"16:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32703,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"16:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32704,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"17:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32705,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"17:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32706,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"17:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32707,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"17:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32708,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"18:00:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32709,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"18:15:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32710,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"18:30:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"},{"id":32711,"venue_id":"2020102009594316","merchant_id":16,"day":5,"time":"18:45:00","priceing_tier":1,"price":"2.00","created_at":"2020-10-20 10:13:38","updated_at":"2020-10-20 10:13:38"}]
         */

        private VenueBean venue;
        private List<ReservationSlotBean> reservation_slot;

        public VenueBean getVenue() {
            return venue;
        }

        public void setVenue(VenueBean venue) {
            this.venue = venue;
        }

        public List<ReservationSlotBean> getReservation_slot() {
            return reservation_slot;
        }

        public void setReservation_slot(List<ReservationSlotBean> reservation_slot) {
            this.reservation_slot = reservation_slot;
        }

        public static class VenueBean {
            /**
             * venue_id : 2020102009594316
             * is_booking_allow : 1
             * booking_slot_gap : 15
             * cleaning_buffer : 10
             * restaurant_capacity : 100
             * booking_fee_type : 1
             * max_booking_duration : 7
             * max_guest : 10
             * is_refundable : 0
             * default_reservation_length : 50
             */

            private String venue_id;
            private int is_booking_allow;
            private int booking_slot_gap;
            private int cleaning_buffer;
            private int restaurant_capacity;
            private int booking_fee_type;
            private int max_booking_duration;
            private int max_guest;
            private int is_refundable;
            private int default_reservation_length;

            public String getVenue_id() {
                return venue_id;
            }

            public void setVenue_id(String venue_id) {
                this.venue_id = venue_id;
            }

            public int getIs_booking_allow() {
                return is_booking_allow;
            }

            public void setIs_booking_allow(int is_booking_allow) {
                this.is_booking_allow = is_booking_allow;
            }

            public int getBooking_slot_gap() {
                return booking_slot_gap;
            }

            public void setBooking_slot_gap(int booking_slot_gap) {
                this.booking_slot_gap = booking_slot_gap;
            }

            public int getCleaning_buffer() {
                return cleaning_buffer;
            }

            public void setCleaning_buffer(int cleaning_buffer) {
                this.cleaning_buffer = cleaning_buffer;
            }

            public int getRestaurant_capacity() {
                return restaurant_capacity;
            }

            public void setRestaurant_capacity(int restaurant_capacity) {
                this.restaurant_capacity = restaurant_capacity;
            }

            public int getBooking_fee_type() {
                return booking_fee_type;
            }

            public void setBooking_fee_type(int booking_fee_type) {
                this.booking_fee_type = booking_fee_type;
            }

            public int getMax_booking_duration() {
                return max_booking_duration;
            }

            public void setMax_booking_duration(int max_booking_duration) {
                this.max_booking_duration = max_booking_duration;
            }

            public int getMax_guest() {
                return max_guest;
            }

            public void setMax_guest(int max_guest) {
                this.max_guest = max_guest;
            }

            public int getIs_refundable() {
                return is_refundable;
            }

            public void setIs_refundable(int is_refundable) {
                this.is_refundable = is_refundable;
            }

            public int getDefault_reservation_length() {
                return default_reservation_length;
            }

            public void setDefault_reservation_length(int default_reservation_length) {
                this.default_reservation_length = default_reservation_length;
            }
        }

        public static class ReservationSlotBean {
            /**
             * id : 32680
             * venue_id : 2020102009594316
             * merchant_id : 16
             * day : 5
             * time : 11:00:00
             * priceing_tier : 1
             * price : 2.00
             * created_at : 2020-10-20 10:13:38
             * updated_at : 2020-10-20 10:13:38
             */

            private int id;
            private String venue_id;
            private int merchant_id;
            private int day;
            private String time;
            private int priceing_tier;
            private String price;
            private String created_at;
            private String updated_at;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getVenue_id() {
                return venue_id;
            }

            public void setVenue_id(String venue_id) {
                this.venue_id = venue_id;
            }

            public int getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(int merchant_id) {
                this.merchant_id = merchant_id;
            }

            public int getDay() {
                return day;
            }

            public void setDay(int day) {
                this.day = day;
            }

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public int getPriceing_tier() {
                return priceing_tier;
            }

            public void setPriceing_tier(int priceing_tier) {
                this.priceing_tier = priceing_tier;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
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
        }
    }

    public static class ReservationclosedayBean {
        /**
         * day_id : 6
         */

        private int day_id;

        public int getDay_id() {
            return day_id;
        }

        public void setDay_id(int day_id) {
            this.day_id = day_id;
        }
    }
}
