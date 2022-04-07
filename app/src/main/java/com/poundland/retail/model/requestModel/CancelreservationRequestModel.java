package com.poundland.retail.model.requestModel;

public class CancelreservationRequestModel {

    private int reservationid;

    public CancelreservationRequestModel(int reservationid) {
        this.reservationid = reservationid;
    }

    public int getReservationid() {
        return reservationid;
    }

    public void setReservationid(int reservationid) {
        this.reservationid = reservationid;
    }
}