package com.poundland.retail.model.responseModel;

import java.util.List;

public class GetRattingResponseModel {


    /**
     * status : true
     * message : Order Rattings & Reviews Details
     * review : {"id":8240,"ord_no":"145#0146","ord_date":"2021-04-26 06:50:28","venue_id":"2020042710540215","ord_amt":"43.00","product":2,"venue_name":"Majestic Stourbridge","recomend_ratting":"2.0","staff_rattings":"2.0","delivery_ratting":"2.0","delivery_raview":"","overall_ratting":"3","overall_review":"","products":[{"id":14698,"product_id":"870","name":"Dead Man's Finger Coconut Spiced Rum 70cl","modifier_id":1018,"product_qty":1,"case_qty":0,"product_images":"uploaded/products/6030015887459610.png","modifier_name":"S","modifier_images":"","review":"","ratings":"4.0"},{"id":14699,"product_id":"872","name":"Brewdog Lone Wolf Gin","modifier_id":1025,"product_qty":1,"case_qty":0,"product_images":"uploaded/products/7496915887458980.png","modifier_name":"S","modifier_images":"","review":"","ratings":"3.0"}]}
     */

    private boolean status;
    private String message;
    private ReviewBean review;

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

    public ReviewBean getReview() {
        return review;
    }

    public void setReview(ReviewBean review) {
        this.review = review;
    }

    public static class ReviewBean {
        /**
         * id : 8240
         * ord_no : 145#0146
         * ord_date : 2021-04-26 06:50:28
         * venue_id : 2020042710540215
         * ord_amt : 43.00
         * product : 2
         * venue_name : Majestic Stourbridge
         * recomend_ratting : 2.0
         * staff_rattings : 2.0
         * delivery_ratting : 2.0
         * delivery_raview :
         * overall_ratting : 3
         * overall_review :
         * products : [{"id":14698,"product_id":"870","name":"Dead Man's Finger Coconut Spiced Rum 70cl","modifier_id":1018,"product_qty":1,"case_qty":0,"product_images":"uploaded/products/6030015887459610.png","modifier_name":"S","modifier_images":"","review":"","ratings":"4.0"},{"id":14699,"product_id":"872","name":"Brewdog Lone Wolf Gin","modifier_id":1025,"product_qty":1,"case_qty":0,"product_images":"uploaded/products/7496915887458980.png","modifier_name":"S","modifier_images":"","review":"","ratings":"3.0"}]
         */

        private int id;
        private String ord_no;
        private String ord_date;
        private String venue_id;
        private String ord_amt;
        private int product;
        private String venue_name;
        private String recomend_ratting;
        private String staff_rattings;
        private String delivery_ratting;
        private String delivery_raview;
        private String overall_ratting;
        private String overall_review;
        private List<ProductsBean> products;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrd_no() {
            return ord_no;
        }

        public void setOrd_no(String ord_no) {
            this.ord_no = ord_no;
        }

        public String getOrd_date() {
            return ord_date;
        }

        public void setOrd_date(String ord_date) {
            this.ord_date = ord_date;
        }

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public String getOrd_amt() {
            return ord_amt;
        }

        public void setOrd_amt(String ord_amt) {
            this.ord_amt = ord_amt;
        }

        public int getProduct() {
            return product;
        }

        public void setProduct(int product) {
            this.product = product;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getRecomend_ratting() {
            return recomend_ratting;
        }

        public void setRecomend_ratting(String recomend_ratting) {
            this.recomend_ratting = recomend_ratting;
        }

        public String getStaff_rattings() {
            return staff_rattings;
        }

        public void setStaff_rattings(String staff_rattings) {
            this.staff_rattings = staff_rattings;
        }

        public String getDelivery_ratting() {
            return delivery_ratting;
        }

        public void setDelivery_ratting(String delivery_ratting) {
            this.delivery_ratting = delivery_ratting;
        }

        public String getDelivery_raview() {
            return delivery_raview;
        }

        public void setDelivery_raview(String delivery_raview) {
            this.delivery_raview = delivery_raview;
        }

        public String getOverall_ratting() {
            return overall_ratting;
        }

        public void setOverall_ratting(String overall_ratting) {
            this.overall_ratting = overall_ratting;
        }

        public String getOverall_review() {
            return overall_review;
        }

        public void setOverall_review(String overall_review) {
            this.overall_review = overall_review;
        }

        public List<ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean  {
            /**
             * id : 14698
             * product_id : 870
             * name : Dead Man's Finger Coconut Spiced Rum 70cl
             * modifier_id : 1018
             * product_qty : 1
             * case_qty : 0
             * product_images : uploaded/products/6030015887459610.png
             * modifier_name : S
             * modifier_images :
             * review :
             * ratings : 4.0
             */

            private int id;
            private String product_id;
            private String name;
            private int modifier_id;
            private int product_qty;
            private int case_qty;
            private String product_images;
            private String modifier_name;
            private String modifier_images;
            private String review;
            private String ratings;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getModifier_id() {
                return modifier_id;
            }

            public void setModifier_id(int modifier_id) {
                this.modifier_id = modifier_id;
            }

            public int getProduct_qty() {
                return product_qty;
            }

            public void setProduct_qty(int product_qty) {
                this.product_qty = product_qty;
            }

            public int getCase_qty() {
                return case_qty;
            }

            public void setCase_qty(int case_qty) {
                this.case_qty = case_qty;
            }

            public String getProduct_images() {
                return product_images;
            }

            public void setProduct_images(String product_images) {
                this.product_images = product_images;
            }

            public String getModifier_name() {
                return modifier_name;
            }

            public void setModifier_name(String modifier_name) {
                this.modifier_name = modifier_name;
            }

            public String getModifier_images() {
                return modifier_images;
            }

            public void setModifier_images(String modifier_images) {
                this.modifier_images = modifier_images;
            }

            public String getReview() {
                return review;
            }

            public void setReview(String review) {
                this.review = review;
            }

            public String getRatings() {
                return ratings;
            }

            public void setRatings(String ratings) {
                this.ratings = ratings;
            }
        }
    }
}
