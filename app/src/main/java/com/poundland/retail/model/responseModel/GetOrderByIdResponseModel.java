package com.poundland.retail.model.responseModel;

public class GetOrderByIdResponseModel {


    /**
     * status : true
     * message : Product reviews and ratings
     * productRating : {"id":71,"staff_review":null,"staff_rattings":5,"recomend_review":null,"recomend_ratting":5,"delivery_raview":null,"delivery_ratting":5,"overall_ratting":5,"overall_review":"nice product and services !!!","venueId":"2020040808100815","order_id":5242,"status":1,"created_at":"2020-11-06 12:48:24","updated_at":"2020-11-19 06:44:16","product_rattings":5,"product_review":"nice product and services !!!"}
     */

    private boolean status;
    private String message;
    private ProductRatingBean productRating;

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

    public ProductRatingBean getProductRating() {
        return productRating;
    }

    public void setProductRating(ProductRatingBean productRating) {
        this.productRating = productRating;
    }

    public static class ProductRatingBean {
        /**
         * id : 71
         * staff_review : null
         * staff_rattings : 5
         * recomend_review : null
         * recomend_ratting : 5
         * delivery_raview : null
         * delivery_ratting : 5
         * overall_ratting : 5
         * overall_review : nice product and services !!!
         * venueId : 2020040808100815
         * order_id : 5242
         * status : 1
         * created_at : 2020-11-06 12:48:24
         * updated_at : 2020-11-19 06:44:16
         * product_rattings : 5
         * product_review : nice product and services !!!
         */

        private int id;
        private Object staff_review;
        private float staff_rattings;
        private Object recomend_review;
        private float recomend_ratting;
        private Object delivery_raview;
        private float delivery_ratting;
        private float overall_ratting;
        private String overall_review;
        private String venueId;
        private int order_id;
        private int status;
        private String created_at;
        private String updated_at;
        private float product_rattings;
        private String product_review;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getStaff_review() {
            return staff_review;
        }

        public void setStaff_review(Object staff_review) {
            this.staff_review = staff_review;
        }

        public float getStaff_rattings() {
            return staff_rattings;
        }

        public void setStaff_rattings(float staff_rattings) {
            this.staff_rattings = staff_rattings;
        }

        public Object getRecomend_review() {
            return recomend_review;
        }

        public void setRecomend_review(Object recomend_review) {
            this.recomend_review = recomend_review;
        }

        public float getRecomend_ratting() {
            return recomend_ratting;
        }

        public void setRecomend_ratting(float recomend_ratting) {
            this.recomend_ratting = recomend_ratting;
        }

        public Object getDelivery_raview() {
            return delivery_raview;
        }

        public void setDelivery_raview(Object delivery_raview) {
            this.delivery_raview = delivery_raview;
        }

        public float getDelivery_ratting() {
            return delivery_ratting;
        }

        public void setDelivery_ratting(float delivery_ratting) {
            this.delivery_ratting = delivery_ratting;
        }

        public float getOverall_ratting() {
            return overall_ratting;
        }

        public void setOverall_ratting(float overall_ratting) {
            this.overall_ratting = overall_ratting;
        }

        public String getOverall_review() {
            return overall_review;
        }

        public void setOverall_review(String overall_review) {
            this.overall_review = overall_review;
        }

        public String getVenueId() {
            return venueId;
        }

        public void setVenueId(String venueId) {
            this.venueId = venueId;
        }

        public int getOrder_id() {
            return order_id;
        }

        public void setOrder_id(int order_id) {
            this.order_id = order_id;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public float getProduct_rattings() {
            return product_rattings;
        }

        public void setProduct_rattings(float product_rattings) {
            this.product_rattings = product_rattings;
        }

        public String getProduct_review() {
            return product_review;
        }

        public void setProduct_review(String product_review) {
            this.product_review = product_review;
        }
    }
}
