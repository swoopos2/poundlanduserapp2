package com.poundland.retail.model.responseModel;

import java.util.List;

public class ActivePayResponseModel {

    /**
     * status : true
     * message : Card Details
     * data : [{"cardToken":"MjAwNjAyMTJHTjAzV00wOEdMMTJDV1E=","card_type":"VmlzYSBDcmVkaXQ=","card_number":"NDAxMjAwKioqKioqMTExMg=="}]
     */
    private boolean status;
    private String message;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cardToken : MjAwNjAyMTJHTjAzV00wOEdMMTJDV1E=
         * card_type : VmlzYSBDcmVkaXQ=
         * card_number : NDAxMjAwKioqKioqMTExMg==
         */

        private String cardToken;
        private String card_type;
        private String card_number;

        public String getCardToken() {
            return cardToken;
        }

        public void setCardToken(String cardToken) {
            this.cardToken = cardToken;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }
    }
}
