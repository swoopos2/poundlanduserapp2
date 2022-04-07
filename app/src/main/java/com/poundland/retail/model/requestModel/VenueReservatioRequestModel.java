package com.poundland.retail.model.requestModel;

public class VenueReservatioRequestModel {

    /**
     * total_guest : 1
     * venue_id : 202004271734165
     * booking_date : 2020-12-29
     * timing_slot : 2660
     * default_reservation_length : 50
     * restaurant_capacity : 100
     * customer_id : 84
     * max_guest : 15
     * customer_email : swetagupta0022@gmail.com
     * customer_name : Sweta Gupta
     * app_type : LOCAL
     * contact_no : 919616666919
     */

    private int total_guest;
    private String venue_id;
    private String booking_date;
    private String timing_slot;
    private String default_reservation_length;
    private String restaurant_capacity;
    private int customer_id;
    private String max_guest;
    private String customer_email;
    private String customer_name;
    private String app_type;
    private String contact_no;

    public VenueReservatioRequestModel(int total_guest, String venue_id, String booking_date, String timing_slot, String default_reservation_length, String restaurant_capacity, int customer_id, String max_guest, String customer_email, String customer_name, String app_type, String contact_no) {
        this.total_guest = total_guest;
        this.venue_id = venue_id;
        this.booking_date = booking_date;
        this.timing_slot = timing_slot;
        this.default_reservation_length = default_reservation_length;
        this.restaurant_capacity = restaurant_capacity;
        this.customer_id = customer_id;
        this.max_guest = max_guest;
        this.customer_email = customer_email;
        this.customer_name = customer_name;
        this.app_type = app_type;
        this.contact_no = contact_no;
    }

    public int getTotal_guest() {
        return total_guest;
    }

    public void setTotal_guest(int total_guest) {
        this.total_guest = total_guest;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public String getTiming_slot() {
        return timing_slot;
    }

    public void setTiming_slot(String timing_slot) {
        this.timing_slot = timing_slot;
    }

    public String getDefault_reservation_length() {
        return default_reservation_length;
    }

    public void setDefault_reservation_length(String default_reservation_length) {
        this.default_reservation_length = default_reservation_length;
    }

    public String getRestaurant_capacity() {
        return restaurant_capacity;
    }

    public void setRestaurant_capacity(String restaurant_capacity) {
        this.restaurant_capacity = restaurant_capacity;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getMax_guest() {
        return max_guest;
    }

    public void setMax_guest(String max_guest) {
        this.max_guest = max_guest;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getContact_no() {
        return contact_no;
    }

    public void setContact_no(String contact_no) {
        this.contact_no = contact_no;
    }
}