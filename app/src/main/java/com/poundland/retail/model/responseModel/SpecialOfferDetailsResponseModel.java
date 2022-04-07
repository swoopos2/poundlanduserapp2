package com.poundland.retail.model.responseModel;

public class SpecialOfferDetailsResponseModel {

    /**
     * status : true
     * productOffers : {"is_on_sale":0,"avl_quantity":2,"buy_price":"11.00","start_date":"2020-02-10","end_date":"2020-02-26","offer_time_start":"00:00:00","offer_time_end":"23:55:00","venue_id":"201911011148462","merchant_id":2,"venue_name":"LillyWhites","modifier_id":136,"product_id":70,"product_images":"special_offer/20191101114846220200210104137.png","product_name":"Women Sea Green & Blue Striped Kurta with Palazzos","modifier_name":"Gerua,L","new_price":"10.00","selling_price":"22.00","special_offer_id":1,"offer_title":"Lilly Wihtes Offer R","offer_type":"Special Offer","description":"Lilly Wihtes Offer Returning customer","stars":"0","review_count":0,"current_loyalty_point":0,"loyalty_point_for_offer":0,"loyalty_point":57.1}
     */

    private boolean status;
    private ProductOffersBean productOffers;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public ProductOffersBean getProductOffers() {
        return productOffers;
    }

    public void setProductOffers(ProductOffersBean productOffers) {
        this.productOffers = productOffers;
    }

    public static class ProductOffersBean {
        /**
         * is_on_sale : 0
         * avl_quantity : 2
         * buy_price : 11.00
         * start_date : 2020-02-10
         * end_date : 2020-02-26
         * offer_time_start : 00:00:00
         * offer_time_end : 23:55:00
         * venue_id : 201911011148462
         * merchant_id : 2
         * venue_name : LillyWhites
         * modifier_id : 136
         * product_id : 70
         * product_images : special_offer/20191101114846220200210104137.png
         * product_name : Women Sea Green & Blue Striped Kurta with Palazzos
         * modifier_name : Gerua,L
         * new_price : 10.00
         * selling_price : 22.00
         * special_offer_id : 1
         * offer_title : Lilly Wihtes Offer R
         * offer_type : Special Offer
         * description : Lilly Wihtes Offer Returning customer
         * stars : 0
         * review_count : 0
         * current_loyalty_point : 0
         * loyalty_point_for_offer : 0
         * loyalty_point : 57.1
         */

        private int is_on_sale;
        private int avl_quantity;
        private String buy_price;
        private String start_date;
        private String end_date;
        private String offer_time_start;
        private String offer_time_end;
        private String venue_id;
        private int merchant_id;
        private String venue_name;
        private int modifier_id;
        private int product_id;
        private String product_images;
        private String product_name;
        private String modifier_name;
        private String new_price;
        private String selling_price;
        private int special_offer_id;
        private String offer_title;
        private String offer_type;
        private String description;
        private String stars;
        private int review_count;
        private double current_loyalty_point;
        private double loyalty_point_for_offer;
        private double loyalty_point;

        public int getIs_on_sale() {
            return is_on_sale;
        }

        public void setIs_on_sale(int is_on_sale) {
            this.is_on_sale = is_on_sale;
        }

        public int getAvl_quantity() {
            return avl_quantity;
        }

        public void setAvl_quantity(int avl_quantity) {
            this.avl_quantity = avl_quantity;
        }

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }

        public String getStart_date() {
            return start_date;
        }

        public void setStart_date(String start_date) {
            this.start_date = start_date;
        }

        public String getEnd_date() {
            return end_date;
        }

        public void setEnd_date(String end_date) {
            this.end_date = end_date;
        }

        public String getOffer_time_start() {
            return offer_time_start;
        }

        public void setOffer_time_start(String offer_time_start) {
            this.offer_time_start = offer_time_start;
        }

        public String getOffer_time_end() {
            return offer_time_end;
        }

        public void setOffer_time_end(String offer_time_end) {
            this.offer_time_end = offer_time_end;
        }

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public int getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(int modifier_id) {
            this.modifier_id = modifier_id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getProduct_images() {
            return product_images;
        }

        public void setProduct_images(String product_images) {
            this.product_images = product_images;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getModifier_name() {
            return modifier_name;
        }

        public void setModifier_name(String modifier_name) {
            this.modifier_name = modifier_name;
        }

        public String getNew_price() {
            return new_price;
        }

        public void setNew_price(String new_price) {
            this.new_price = new_price;
        }

        public String getSelling_price() {
            return selling_price;
        }

        public void setSelling_price(String selling_price) {
            this.selling_price = selling_price;
        }

        public int getSpecial_offer_id() {
            return special_offer_id;
        }

        public void setSpecial_offer_id(int special_offer_id) {
            this.special_offer_id = special_offer_id;
        }

        public String getOffer_title() {
            return offer_title;
        }

        public void setOffer_title(String offer_title) {
            this.offer_title = offer_title;
        }

        public String getOffer_type() {
            return offer_type;
        }

        public void setOffer_type(String offer_type) {
            this.offer_type = offer_type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getReview_count() {
            return review_count;
        }

        public void setReview_count(int review_count) {
            this.review_count = review_count;
        }

        public double getCurrent_loyalty_point() {
            return current_loyalty_point;
        }

        public void setCurrent_loyalty_point(double current_loyalty_point) {
            this.current_loyalty_point = current_loyalty_point;
        }

        public double getLoyalty_point_for_offer() {
            return loyalty_point_for_offer;
        }

        public void setLoyalty_point_for_offer(double loyalty_point_for_offer) {
            this.loyalty_point_for_offer = loyalty_point_for_offer;
        }

        public double getLoyalty_point() {
            return loyalty_point;
        }

        public void setLoyalty_point(double loyalty_point) {
            this.loyalty_point = loyalty_point;
        }
    }
}
