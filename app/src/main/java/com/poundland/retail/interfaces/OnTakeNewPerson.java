package com.poundland.retail.interfaces;

import com.poundland.retail.model.requestModel.ReservationGuestReqModel;

import java.io.Serializable;

public interface OnTakeNewPerson extends Serializable {
    void onTakeNewPersonCallBack(boolean clickType, ReservationGuestReqModel reservationGuest);
}
