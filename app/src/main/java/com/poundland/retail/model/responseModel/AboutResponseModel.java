package com.poundland.retail.model.responseModel;

public class AboutResponseModel {


    /**
     * status : true
     * message : Term & Condition
     * term : <div>
     <h1 class="page-title">Terms and Conditions</h1>
     <p>These Terms and Conditions are just a sample and are not legally binding. Real Terms of Services do not (usually) contain vegetables...</p>

     </div>
     */

    private boolean status;
    private String message;
    private String term;

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

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
