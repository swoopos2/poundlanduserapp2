package com.poundland.retail.zzznewPoundland.model;

public class RegistrationResponseModel extends CommonResponse {
    public RegistrationResponseModel(Boolean status, String message, int currentPage, int lastPage, String errorMessage) {
        super(status, message, currentPage, lastPage, errorMessage);
    }
}
