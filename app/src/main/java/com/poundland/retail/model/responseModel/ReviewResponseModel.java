package com.poundland.retail.model.responseModel;

import java.util.List;

public class ReviewResponseModel {

    /**
     * status : true
     * message : List Of Product reviews and ratings
     * productRating : {"count":1,"rating_value":"5.0","reviews":{"current_page":1,"data":[{"cust_id":5,"cust_name":"Vinit Mishra","cust_image":"https://lh3.googleusercontent.com/a-/AAuE7mA2AaBPE-CPUP9s0_l8WXUCJJaY0HivOD1rQsRj","rattings":5,"review":null}],"first_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getProductRatings?page=1","from":1,"last_page":1,"last_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getProductRatings?page=1","next_page_url":null,"path":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getProductRatings","per_page":10,"prev_page_url":null,"to":1,"total":1}}
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
         * count : 1
         * rating_value : 5.0
         * reviews : {"current_page":1,"data":[{"cust_id":5,"cust_name":"Vinit Mishra","cust_image":"https://lh3.googleusercontent.com/a-/AAuE7mA2AaBPE-CPUP9s0_l8WXUCJJaY0HivOD1rQsRj","rattings":5,"review":null}],"first_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getProductRatings?page=1","from":1,"last_page":1,"last_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getProductRatings?page=1","next_page_url":null,"path":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getProductRatings","per_page":10,"prev_page_url":null,"to":1,"total":1}
         */

        private int count;
        private String rating_value;
        private ReviewsBean reviews;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public String getRating_value() {
            return rating_value;
        }

        public void setRating_value(String rating_value) {
            this.rating_value = rating_value;
        }

        public ReviewsBean getReviews() {
            return reviews;
        }

        public void setReviews(ReviewsBean reviews) {
            this.reviews = reviews;
        }

        public static class ReviewsBean {
            /**
             * current_page : 1
             * data : [{"cust_id":5,"cust_name":"Vinit Mishra","cust_image":"https://lh3.googleusercontent.com/a-/AAuE7mA2AaBPE-CPUP9s0_l8WXUCJJaY0HivOD1rQsRj","rattings":5,"review":null}]
             * first_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getProductRatings?page=1
             * from : 1
             * last_page : 1
             * last_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getProductRatings?page=1
             * next_page_url : null
             * path : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getProductRatings
             * per_page : 10
             * prev_page_url : null
             * to : 1
             * total : 1
             */

            private int current_page;
            private String first_page_url;
            private int from;
            private int last_page;
            private String last_page_url;
            private Object next_page_url;
            private String path;
            private int per_page;
            private Object prev_page_url;
            private int to;
            private int total;
            private List<DataBean> data;

            public int getCurrent_page() {
                return current_page;
            }

            public void setCurrent_page(int current_page) {
                this.current_page = current_page;
            }

            public String getFirst_page_url() {
                return first_page_url;
            }

            public void setFirst_page_url(String first_page_url) {
                this.first_page_url = first_page_url;
            }

            public int getFrom() {
                return from;
            }

            public void setFrom(int from) {
                this.from = from;
            }

            public int getLast_page() {
                return last_page;
            }

            public void setLast_page(int last_page) {
                this.last_page = last_page;
            }

            public String getLast_page_url() {
                return last_page_url;
            }

            public void setLast_page_url(String last_page_url) {
                this.last_page_url = last_page_url;
            }

            public Object getNext_page_url() {
                return next_page_url;
            }

            public void setNext_page_url(Object next_page_url) {
                this.next_page_url = next_page_url;
            }

            public String getPath() {
                return path;
            }

            public void setPath(String path) {
                this.path = path;
            }

            public int getPer_page() {
                return per_page;
            }

            public void setPer_page(int per_page) {
                this.per_page = per_page;
            }

            public Object getPrev_page_url() {
                return prev_page_url;
            }

            public void setPrev_page_url(Object prev_page_url) {
                this.prev_page_url = prev_page_url;
            }

            public int getTo() {
                return to;
            }

            public void setTo(int to) {
                this.to = to;
            }

            public int getTotal() {
                return total;
            }

            public void setTotal(int total) {
                this.total = total;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * cust_id : 5
                 * cust_name : Vinit Mishra
                 * cust_image : https://lh3.googleusercontent.com/a-/AAuE7mA2AaBPE-CPUP9s0_l8WXUCJJaY0HivOD1rQsRj
                 * rattings : 5
                 * review : null
                 */

                private int cust_id;
                private String cust_name;
                private String cust_image;
                private int rattings;
                private String review;

                public int getCust_id() {
                    return cust_id;
                }

                public void setCust_id(int cust_id) {
                    this.cust_id = cust_id;
                }

                public String getCust_name() {
                    return cust_name;
                }

                public void setCust_name(String cust_name) {
                    this.cust_name = cust_name;
                }

                public String getCust_image() {
                    return cust_image;
                }

                public void setCust_image(String cust_image) {
                    this.cust_image = cust_image;
                }

                public int getRattings() {
                    return rattings;
                }

                public void setRattings(int rattings) {
                    this.rattings = rattings;
                }

                public String getReview() {
                    return review;
                }

                public void setReview(String review) {
                    this.review = review;
                }
            }
        }
    }
}
