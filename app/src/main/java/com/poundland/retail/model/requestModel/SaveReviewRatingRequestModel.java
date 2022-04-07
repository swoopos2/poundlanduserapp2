package com.poundland.retail.model.requestModel;

import java.util.List;

public class SaveReviewRatingRequestModel {
    /**
     * order_id : 308
     * order_details_id : 1152
     * rating : 4
     * review : nice Product
     ///////////////////////////// below for over all and above for single product////////////////////////////////////////
     * order_id : 308
     * recomend : 4
     * rating : 4
     * collection : 4
     * overAllRating : 4
     * overall_review : Nice Product
     * product : [{"order_detail_id":"516","rating":"4","review":"Nice Product"},{"order_detail_id":"517","rating":"5","review":"Nice Product"}]
     */
    private float recomend;
    private float collection;
    private float overAllRating;
    private String overall_review;
    private List<ProductBean> product;

    private String order_id;
    private int order_details_id;
    private float rating;
    private String review;

    public SaveReviewRatingRequestModel(String order_id, float recomend, float rating, float collection, float overAllRating,
                                        String overall_review, List<ProductBean> product) {
        this.order_id = order_id;
        this.recomend = recomend;
        this.rating = rating;
        this.collection = collection;
        this.overAllRating = overAllRating;
        this.overall_review = overall_review;
        this.product = product;
    }

    public SaveReviewRatingRequestModel(String order_id, int order_details_id, float rating, String review) {
        this.order_id = order_id;
        this.order_details_id = order_details_id;
        this.rating = rating;
        this.review = review;
    }

    public String getOrder_id() {
        return order_id;
    }

    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public int getOrder_details_id() {
        return order_details_id;
    }

    public void setOrder_details_id(int order_details_id) {
        this.order_details_id = order_details_id;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public static class ProductBean {
        /**
         * order_detail_id : 516
         * rating : 4
         * review : Nice Product
         */

        private String order_detail_id;
        private float rating;
        private String review;

        public String getOrder_detail_id() {
            return order_detail_id;
        }

        public void setOrder_detail_id(String order_detail_id) {
            this.order_detail_id = order_detail_id;
        }

        public float getRating() {
            return rating;
        }

        public void setRating(float rating) {
            this.rating = rating;
        }

        public String getReview() {
            return review;
        }

        public void setReview(String review) {
            this.review = review;
        }
    }

    public float getRecomend() {
        return recomend;
    }

    public void setRecomend(float recomend) {
        this.recomend = recomend;
    }

    public float getCollection() {
        return collection;
    }

    public void setCollection(float collection) {
        this.collection = collection;
    }

    public float getOverAllRating() {
        return overAllRating;
    }

    public void setOverAllRating(float overAllRating) {
        this.overAllRating = overAllRating;
    }

    public String getOverall_review() {
        return overall_review;
    }

    public void setOverall_review(String overall_review) {
        this.overall_review = overall_review;
    }

    public List<ProductBean> getProduct() {
        return product;
    }

    public void setProduct(List<ProductBean> product) {
        this.product = product;
    }
}
