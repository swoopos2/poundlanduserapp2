package com.poundland.retail.model;

import java.util.Date;

public class ReservationBookingDate {
    Date date;
    boolean isEnable;

    public ReservationBookingDate(Date date, boolean isEnable) {
        this.date = date;
        this.isEnable = isEnable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isEnable() {
        return isEnable;
    }

    public void setEnable(boolean enable) {
        isEnable = enable;
    }
}
