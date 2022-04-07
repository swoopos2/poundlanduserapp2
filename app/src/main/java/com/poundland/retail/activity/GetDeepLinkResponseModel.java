package com.poundland.retail.activity;

import java.util.List;

public class GetDeepLinkResponseModel {

    /**
     * status : true
     * message : Product Details
     * venues : {"id":1,"merchant_id":2,"venue_id":"202002110747362","venue_name":"AmazonGo","venue_images":["1581407256.amazon (1).jpg"],"address_1":"Wolverhampton Bus Station, Victoria Street, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV1 1LD","phone_number":"44456498498648","venue_email":"goamazon@nwesmail.com","venue_website":null,"home_delivery_mov":"0.30","start_days":1,"end_days":2,"collection_time":40,"preparation_time":45,"free_delivery":"0.40","delivery_charge":"0.20","delivery":1,"collection":1,"opening_time":"2020-02-12 04:00","closing_time":"2020-02-12 22:00","isClose":"0"}
     * products : {"id":19,"merchant_id":2,"venue_id":"202002110747362","product_name":"Women Beige & Green Embroidered Suede Finish Mini Straight Skirt","product_description":"<p><span style=\"color: rgb(40, 44, 63); font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif; font-size: 16px;\">Beige and green printed woven suede finish mini straight skirt, has a concealed zip and button closure, straight hem, an attached lining<\/span><br><\/p>","measurement_unit_id":2,"category_id":["3"],"related_product_ids":[],"brand_id":2,"tax_id":"0","supplier_id":2,"product_tags":"Skirt","images":["uploaded/products/3635615814234810.jpeg","uploaded/products/1107915814234811.jpeg","uploaded/products/2908415814234812.jpeg"],"modifier_list":[{"type":"name","type_list":[]}],"modifier_id":19,"modifier_name":null,"buy_price":"0.50","selling_price":"0.90","avl_quantity":97,"modifier_images":[],"isWishlisted":false,"isCart":false,"new_price":"0.90"}
     * productOfers : {}
     * loyalitypoints : {"loyalty_points_value":0.5,"loyalty_points":0.5,"total_loyalty_points_value":0.25}
     * comboOffer : []
     * productRating : {"count":0,"rating_value":"0","reviews":[]}
     * /// added
     */

    private boolean status;
    private String message;
    private VenuesBean venues;
    private ProductsBean products;
    private ProductOfersBean productOfers;
    private LoyalitypointsBean loyalitypoints;
    private ProductRatingBean productRating;
    private List<?> comboOffer;

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

    public VenuesBean getVenues() {
        return venues;
    }

    public void setVenues(VenuesBean venues) {
        this.venues = venues;
    }

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }

    public ProductOfersBean getProductOfers() {
        return productOfers;
    }

    public void setProductOfers(ProductOfersBean productOfers) {
        this.productOfers = productOfers;
    }

    public LoyalitypointsBean getLoyalitypoints() {
        return loyalitypoints;
    }

    public void setLoyalitypoints(LoyalitypointsBean loyalitypoints) {
        this.loyalitypoints = loyalitypoints;
    }

    public ProductRatingBean getProductRating() {
        return productRating;
    }

    public void setProductRating(ProductRatingBean productRating) {
        this.productRating = productRating;
    }

    public List<?> getComboOffer() {
        return comboOffer;
    }

    public void setComboOffer(List<?> comboOffer) {
        this.comboOffer = comboOffer;
    }

    public static class VenuesBean {
        /**
         * id : 1
         * merchant_id : 2
         * venue_id : 202002110747362
         * venue_name : AmazonGo
         * venue_images : ["1581407256.amazon (1).jpg"]
         * address_1 : Wolverhampton Bus Station, Victoria Street, Wolverhampton, UK
         * address_2 : null
         * city_name : Wolverhampton
         * country : United Kingdom
         * post_code : WV1 1LD
         * phone_number : 44456498498648
         * venue_email : goamazon@nwesmail.com
         * venue_website : null
         * home_delivery_mov : 0.30
         * start_days : 1
         * end_days : 2
         * collection_time : 40
         * preparation_time : 45
         * free_delivery : 0.40
         * delivery_charge : 0.20
         * delivery : 1
         * collection : 1
         * opening_time : 2020-02-12 04:00
         * closing_time : 2020-02-12 22:00
         * isClose : 0
         */

        private int id;
        private int merchant_id;
        private String venue_id;
        private String venue_name;
        private String address_1;
        private Object address_2;
        private String city_name;
        private String country;
        private String post_code;
        private String phone_number;
        private String venue_email;
        private Object venue_website;
        private String home_delivery_mov;
        private int start_days;
        private int end_days;
        private float collection_time;
        private float preparation_time;
        private String free_delivery;
        private String delivery_charge;
        private int delivery;
        private int collection;
        private String opening_time;
        private String closing_time;
        private String isClose;
        private List<String> venue_images;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
        }

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public Object getAddress_2() {
            return address_2;
        }

        public void setAddress_2(Object address_2) {
            this.address_2 = address_2;
        }

        public String getCity_name() {
            return city_name;
        }

        public void setCity_name(String city_name) {
            this.city_name = city_name;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getPost_code() {
            return post_code;
        }

        public void setPost_code(String post_code) {
            this.post_code = post_code;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getVenue_email() {
            return venue_email;
        }

        public void setVenue_email(String venue_email) {
            this.venue_email = venue_email;
        }

        public Object getVenue_website() {
            return venue_website;
        }

        public void setVenue_website(Object venue_website) {
            this.venue_website = venue_website;
        }

        public String getHome_delivery_mov() {
            return home_delivery_mov;
        }

        public void setHome_delivery_mov(String home_delivery_mov) {
            this.home_delivery_mov = home_delivery_mov;
        }

        public int getStart_days() {
            return start_days;
        }

        public void setStart_days(int start_days) {
            this.start_days = start_days;
        }

        public int getEnd_days() {
            return end_days;
        }

        public void setEnd_days(int end_days) {
            this.end_days = end_days;
        }

        public float getCollection_time() {
            return collection_time;
        }

        public void setCollection_time(float collection_time) {
            this.collection_time = collection_time;
        }

        public float getPreparation_time() {
            return preparation_time;
        }

        public void setPreparation_time(float preparation_time) {
            this.preparation_time = preparation_time;
        }

        public String getFree_delivery() {
            return free_delivery;
        }

        public void setFree_delivery(String free_delivery) {
            this.free_delivery = free_delivery;
        }

        public String getDelivery_charge() {
            return delivery_charge;
        }

        public void setDelivery_charge(String delivery_charge) {
            this.delivery_charge = delivery_charge;
        }

        public int getDelivery() {
            return delivery;
        }

        public void setDelivery(int delivery) {
            this.delivery = delivery;
        }

        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public String getOpening_time() {
            return opening_time;
        }

        public void setOpening_time(String opening_time) {
            this.opening_time = opening_time;
        }

        public String getClosing_time() {
            return closing_time;
        }

        public void setClosing_time(String closing_time) {
            this.closing_time = closing_time;
        }

        public String getIsClose() {
            return isClose;
        }

        public void setIsClose(String isClose) {
            this.isClose = isClose;
        }

        public List<String> getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(List<String> venue_images) {
            this.venue_images = venue_images;
        }
    }

    public static class ProductsBean {
        public static class ModifierListBean {
        }
    }

    public static class ProductOfersBean {
    }

    public static class LoyalitypointsBean {
        /**
         * loyalty_points_value : 0.5
         * loyalty_points : 0.5
         * total_loyalty_points_value : 0.25
         */

        private double loyalty_points_value;
        private double loyalty_points;
        private double total_loyalty_points_value;

        public double getLoyalty_points_value() {
            return loyalty_points_value;
        }

        public void setLoyalty_points_value(double loyalty_points_value) {
            this.loyalty_points_value = loyalty_points_value;
        }

        public double getLoyalty_points() {
            return loyalty_points;
        }

        public void setLoyalty_points(double loyalty_points) {
            this.loyalty_points = loyalty_points;
        }

        public double getTotal_loyalty_points_value() {
            return total_loyalty_points_value;
        }

        public void setTotal_loyalty_points_value(double total_loyalty_points_value) {
            this.total_loyalty_points_value = total_loyalty_points_value;
        }
    }

    public static class ProductRatingBean {
        /**
         * count : 0
         * rating_value : 0
         * reviews : []
         */

        private int count;
        private String rating_value;
        private List<?> reviews;

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

        public List<?> getReviews() {
            return reviews;
        }

        public void setReviews(List<?> reviews) {
            this.reviews = reviews;
        }
    }
}
