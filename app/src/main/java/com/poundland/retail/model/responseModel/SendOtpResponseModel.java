package com.poundland.retail.model.responseModel;

import java.util.List;

public class SendOtpResponseModel {


    /**
     * status : true
     * flag : 1
     * message : Otp Sent Successfully !
     */
    /*{
    "status": false,
    "message": "The given data was invalid.",
    "data": {
        "email": [
            "The email has already been taken."
        ]
    }
}*/

    private DataBean data;

    private boolean status;
    private String flag;
    private String message;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }



    public static class DataBean {
        private List<String> email;

        public List<String> getEmail() {
            return email;
        }

        public void setEmail(List<String> email) {
            this.email = email;
        }
    }


}
