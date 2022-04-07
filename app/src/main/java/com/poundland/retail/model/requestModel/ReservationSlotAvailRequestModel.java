package com.poundland.retail.model.requestModel;

public class ReservationSlotAvailRequestModel {


    public ReservationSlotAvailRequestModel(String total_guest, String booking_date, int slot_id, int res_day, String venue_id) {
        this.total_guest = total_guest;
        this.booking_date = booking_date;
        this.slot_id = slot_id;
        this.res_day = res_day;
        this.venue_id = venue_id;
    }

    /**
     * total_guest : 3
     * booking_date : 2021-01-03
     * slot_id : 3161
     * res_day : 4
     * venue_id : 2020042308172516
     */

    private String total_guest;
    private String booking_date;
    private int slot_id;
    private int res_day;
    private String venue_id;

    public String getTotal_guest() {
        return total_guest;
    }

    public void setTotal_guest(String total_guest) {
        this.total_guest = total_guest;
    }

    public String getBooking_date() {
        return booking_date;
    }

    public void setBooking_date(String booking_date) {
        this.booking_date = booking_date;
    }

    public int getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(int slot_id) {
        this.slot_id = slot_id;
    }

    public int getRes_day() {
        return res_day;
    }

    public void setRes_day(int res_day) {
        this.res_day = res_day;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }
}