package com.poundland.retail.model.responseModel;

public class LikeDislikeProductResponseModel {


    /**
     * status : true
     * message : Product Liked Successfully.
     * like : {"merchant_id":"10","venue_id":"2019071613181310","customer_id":8,"product_id":"1","modifier_id":"1","offer_id":"1","ip_address":"127.0.0.1:8000","updated_at":"2019-08-28 08:46:17","created_at":"2019-08-28 08:46:17","id":57}
     */

    private boolean status;
    private String message;
    private LikeBean like;

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

    public LikeBean getLike() {
        return like;
    }

    public void setLike(LikeBean like) {
        this.like = like;
    }

    public static class LikeBean {
        /**
         * merchant_id : 10
         * venue_id : 2019071613181310
         * customer_id : 8
         * product_id : 1
         * modifier_id : 1
         * offer_id : 1
         * ip_address : 127.0.0.1:8000
         * updated_at : 2019-08-28 08:46:17
         * created_at : 2019-08-28 08:46:17
         * id : 57
         */

        private String merchant_id;
        private String venue_id;
        private int customer_id;
        private String product_id;
        private String modifier_id;
        private String offer_id;
        private String ip_address;
        private String updated_at;
        private String created_at;
        private int id;

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
        }

        public String getProduct_id() {
            return product_id;
        }

        public void setProduct_id(String product_id) {
            this.product_id = product_id;
        }

        public String getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(String modifier_id) {
            this.modifier_id = modifier_id;
        }

        public String getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(String offer_id) {
            this.offer_id = offer_id;
        }

        public String getIp_address() {
            return ip_address;
        }

        public void setIp_address(String ip_address) {
            this.ip_address = ip_address;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
