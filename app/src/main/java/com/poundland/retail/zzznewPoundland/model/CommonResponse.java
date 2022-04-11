package com.poundland.retail.zzznewPoundland.model;

import com.google.gson.annotations.SerializedName;

public abstract class CommonResponse {
    @SerializedName("status")
    private Boolean status;

    @SerializedName("message")
    private String message;

    @SerializedName("current_page")
    int currentPage;
    @SerializedName("last_page")
    int lastPage;

    @SerializedName("error")
    String errorMessage;

    public CommonResponse(Boolean status, String message, int currentPage, int lastPage, String errorMessage) {
        this.status = status;
        this.message = message;
        this.currentPage = currentPage;
        this.lastPage = lastPage;
        this.errorMessage = errorMessage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
