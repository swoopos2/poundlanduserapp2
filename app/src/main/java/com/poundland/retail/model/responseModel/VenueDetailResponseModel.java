package com.poundland.retail.model.responseModel;

import java.io.Serializable;
import java.util.List;

public class VenueDetailResponseModel {

    /**
     * venue_details : {"id":30,"merchant_id":15,"venue_id":"2020042216124015","venue_name":"Morphe Wolverhampton","venue_images":["20210607115134-Bullring_Morphe_External--696x464.jpg"],"address_1":"Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV30DS","phone_number":"01212707500","venue_email":"jordan@swoopos.net","venue_website":null,"home_delivery_mov":"5.00","start_days":3,"end_days":5,"collection_time":null,"preparation_time":null,"free_delivery":"20.00","delivery_charge":"3.00","delivery":1,"collection":0,"latitude":"52.586973","longitude":"-2.12882","delivery_distance":0,"venue_category":"electrical","is_booking_allow":0,"payment_gatway":"active","distance":"4,705.17","stars":{"count":1,"venue_rating_value":"2.3"},"sameDayShipping":0,"opening_time":"2021-07-13 04:00","closing_time":"2021-07-14 00:00","isClose":"0","isWishlisted":false,"Categories":[{"id":-1,"cat_name":"My Fav","parent_cat_id":null,"image":null,"color":"#35CEEA","menu_level":1,"total_products":9,"isFav":1},{"id":1064,"cat_name":"Air Fresheners & Home Fragrance","parent_cat_id":1044,"image":null,"color":"1d01cf","menu_level":2,"total_products":27},{"id":1043,"cat_name":"Baby Toiletries &Healthcare","parent_cat_id":0,"image":null,"color":"1d01cf","menu_level":1,"total_products":4},{"id":1058,"cat_name":"Bath,Shower & Soap","parent_cat_id":1042,"image":null,"color":"1d01cf","menu_level":2,"total_products":25},{"id":1062,"cat_name":"Bin Bags & Liners","parent_cat_id":1044,"image":null,"color":"1d01cf","menu_level":2,"total_products":10},{"id":1045,"cat_name":"Cereal, Cereal Bars & On The Go Breakfast","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":13},{"id":1052,"cat_name":"Cheese","parent_cat_id":1038,"image":null,"color":"1d01cf","menu_level":2,"total_products":18},{"id":1063,"cat_name":"Cleaning Supplies","parent_cat_id":1044,"image":null,"color":"1d01cf","menu_level":2,"total_products":42},{"id":1048,"cat_name":"Crisps Nuts & Snacks","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":110},{"id":1047,"cat_name":"Crisps, Nuts & Snacks","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":52},{"id":1055,"cat_name":"Dental care","parent_cat_id":1042,"image":null,"color":"1d01cf","menu_level":2,"total_products":21},{"id":1057,"cat_name":"Face & Body Skincare","parent_cat_id":1042,"image":null,"color":"1d01cf","menu_level":2,"total_products":45},{"id":1059,"cat_name":"Feminine Care","parent_cat_id":1042,"image":null,"color":"1d01cf","menu_level":2,"total_products":23},{"id":1065,"cat_name":"Food Bags, Foil & Cling Flim","parent_cat_id":1044,"image":null,"color":"1d01cf","menu_level":2,"total_products":12},{"id":1051,"cat_name":"Fresh Fruits","parent_cat_id":1039,"image":null,"color":"1d01cf","menu_level":2,"total_products":19},{"id":1050,"cat_name":"Fresh Vegetables","parent_cat_id":1039,"image":null,"color":"1d01cf","menu_level":2,"total_products":28},{"id":1054,"cat_name":"Frozen Vegetables Pizza","parent_cat_id":1041,"image":null,"color":"1d01cf","menu_level":2,"total_products":63},{"id":1040,"cat_name":"Garden & Outdoor Cooking","parent_cat_id":0,"image":null,"color":"1d01cf","menu_level":1,"total_products":8},{"id":1056,"cat_name":"Hair Care","parent_cat_id":1042,"image":null,"color":"1d01cf","menu_level":2,"total_products":49},{"id":1061,"cat_name":"Home Baking","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":137},{"id":1060,"cat_name":"Jams, Spreads & Desserts","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":43},{"id":1049,"cat_name":"Meat, Sausages & Bacon","parent_cat_id":1038,"image":null,"color":"1d01cf","menu_level":2,"total_products":20},{"id":1053,"cat_name":"Milk, Butter, Cream & Eggs","parent_cat_id":1038,"image":null,"color":"1d01cf","menu_level":2,"total_products":74},{"id":1046,"cat_name":"Tinned Food","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":251},{"id":85,"cat_name":"TV and Home Entertainment","parent_cat_id":0,"image":"TV and Home Entertainment22042020163559.png","color":"e66465","menu_level":1,"total_products":9}]}
     * venue_week_times : [{"day_name":"Monday","opening_time":"2021-07-13 09:00","closing_time":"2021-07-13 20:00"},{"day_name":"Tuesday","opening_time":"2021-07-13 04:00","closing_time":"2021-07-14 00:00"},{"day_name":"Wednesday","opening_time":"2021-07-13 04:00","closing_time":"2021-07-14 00:00"},{"day_name":"Thursday","opening_time":"2021-07-13 04:00","closing_time":"2021-07-14 00:00"},{"day_name":"Friday","opening_time":"2021-07-13 04:00","closing_time":"2021-07-14 00:00"},{"day_name":"Saturday","opening_time":"2021-07-13 04:00","closing_time":"2021-07-14 00:00"},{"day_name":"Sunday","opening_time":"2021-07-13 04:00","closing_time":"2021-07-14 00:00"}]
     * isProductInCart : 0
     * shippingData : null
     * status : true
     * message : Venue Details
     */

    private VenueDetailsBean venue_details;
    private int isProductInCart;
    private boolean status;
    private String message;
    private List<VenueWeekTimesBean> venue_week_times;

    private ShippingDataBean shippingData;


    public ShippingDataBean getShippingData() {
        return shippingData;
    }

    public void setShippingData(ShippingDataBean shippingData) {
        this.shippingData = shippingData;
    }





    public VenueDetailsBean getVenue_details() {
        return venue_details;
    }

    public void setVenue_details(VenueDetailsBean venue_details) {
        this.venue_details = venue_details;
    }

    public int getIsProductInCart() {
        return isProductInCart;
    }

    public void setIsProductInCart(int isProductInCart) {
        this.isProductInCart = isProductInCart;
    }


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

    public List<VenueWeekTimesBean> getVenue_week_times() {
        return venue_week_times;
    }

    public void setVenue_week_times(List<VenueWeekTimesBean> venue_week_times) {
        this.venue_week_times = venue_week_times;
    }

    public static class VenueDetailsBean implements Serializable {
        /**
         * id : 30
         * merchant_id : 15
         * venue_id : 2020042216124015
         * venue_name : Morphe Wolverhampton
         * venue_images : ["20210607115134-Bullring_Morphe_External--696x464.jpg"]
         * address_1 : Wolverhampton, UK
         * address_2 : null
         * city_name : Wolverhampton
         * country : United Kingdom
         * post_code : WV30DS
         * phone_number : 01212707500
         * venue_email : jordan@swoopos.net
         * venue_website : null
         * home_delivery_mov : 5.00
         * start_days : 3
         * end_days : 5
         * collection_time : null
         * preparation_time : null
         * free_delivery : 20.00
         * delivery_charge : 3.00
         * delivery : 1
         * collection : 0
         * latitude : 52.586973
         * longitude : -2.12882
         * delivery_distance : 0
         * venue_category : electrical
         * is_booking_allow : 0
         * payment_gatway : active
         * distance : 4,705.17
         * stars : {"count":1,"venue_rating_value":"2.3"}
         * sameDayShipping : 0
         * opening_time : 2021-07-13 04:00
         * closing_time : 2021-07-14 00:00
         * isClose : 0
         * isWishlisted : false
         * Categories : [{"id":-1,"cat_name":"My Fav","parent_cat_id":null,"image":null,"color":"#35CEEA","menu_level":1,"total_products":9,"isFav":1},{"id":1064,"cat_name":"Air Fresheners & Home Fragrance","parent_cat_id":1044,"image":null,"color":"1d01cf","menu_level":2,"total_products":27},{"id":1043,"cat_name":"Baby Toiletries &Healthcare","parent_cat_id":0,"image":null,"color":"1d01cf","menu_level":1,"total_products":4},{"id":1058,"cat_name":"Bath,Shower & Soap","parent_cat_id":1042,"image":null,"color":"1d01cf","menu_level":2,"total_products":25},{"id":1062,"cat_name":"Bin Bags & Liners","parent_cat_id":1044,"image":null,"color":"1d01cf","menu_level":2,"total_products":10},{"id":1045,"cat_name":"Cereal, Cereal Bars & On The Go Breakfast","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":13},{"id":1052,"cat_name":"Cheese","parent_cat_id":1038,"image":null,"color":"1d01cf","menu_level":2,"total_products":18},{"id":1063,"cat_name":"Cleaning Supplies","parent_cat_id":1044,"image":null,"color":"1d01cf","menu_level":2,"total_products":42},{"id":1048,"cat_name":"Crisps Nuts & Snacks","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":110},{"id":1047,"cat_name":"Crisps, Nuts & Snacks","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":52},{"id":1055,"cat_name":"Dental care","parent_cat_id":1042,"image":null,"color":"1d01cf","menu_level":2,"total_products":21},{"id":1057,"cat_name":"Face & Body Skincare","parent_cat_id":1042,"image":null,"color":"1d01cf","menu_level":2,"total_products":45},{"id":1059,"cat_name":"Feminine Care","parent_cat_id":1042,"image":null,"color":"1d01cf","menu_level":2,"total_products":23},{"id":1065,"cat_name":"Food Bags, Foil & Cling Flim","parent_cat_id":1044,"image":null,"color":"1d01cf","menu_level":2,"total_products":12},{"id":1051,"cat_name":"Fresh Fruits","parent_cat_id":1039,"image":null,"color":"1d01cf","menu_level":2,"total_products":19},{"id":1050,"cat_name":"Fresh Vegetables","parent_cat_id":1039,"image":null,"color":"1d01cf","menu_level":2,"total_products":28},{"id":1054,"cat_name":"Frozen Vegetables Pizza","parent_cat_id":1041,"image":null,"color":"1d01cf","menu_level":2,"total_products":63},{"id":1040,"cat_name":"Garden & Outdoor Cooking","parent_cat_id":0,"image":null,"color":"1d01cf","menu_level":1,"total_products":8},{"id":1056,"cat_name":"Hair Care","parent_cat_id":1042,"image":null,"color":"1d01cf","menu_level":2,"total_products":49},{"id":1061,"cat_name":"Home Baking","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":137},{"id":1060,"cat_name":"Jams, Spreads & Desserts","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":43},{"id":1049,"cat_name":"Meat, Sausages & Bacon","parent_cat_id":1038,"image":null,"color":"1d01cf","menu_level":2,"total_products":20},{"id":1053,"cat_name":"Milk, Butter, Cream & Eggs","parent_cat_id":1038,"image":null,"color":"1d01cf","menu_level":2,"total_products":74},{"id":1046,"cat_name":"Tinned Food","parent_cat_id":1037,"image":null,"color":"1d01cf","menu_level":2,"total_products":251},{"id":85,"cat_name":"TV and Home Entertainment","parent_cat_id":0,"image":"TV and Home Entertainment22042020163559.png","color":"e66465","menu_level":1,"total_products":9}]
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
        private Object collection_time;
        private Object preparation_time;
        private String free_delivery;
        private String delivery_charge;
        private int delivery;
        private int collection;
        private String latitude;
        private String longitude;
        private int delivery_distance;
        private String venue_category;
        private int is_booking_allow;
        private String payment_gatway;
        private String distance;
        private StarsBean stars;
        private int sameDayShipping;
        private String opening_time;
        private String closing_time;
        private String isClose;
        private boolean isWishlisted;
        private List<String> venue_images;
        private List<CategoriesBean> Categories;

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

        public Object getCollection_time() {
            return collection_time;
        }

        public void setCollection_time(Object collection_time) {
            this.collection_time = collection_time;
        }

        public Object getPreparation_time() {
            return preparation_time;
        }

        public void setPreparation_time(Object preparation_time) {
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

        public String getLatitude() {
            return latitude;
        }

        public void setLatitude(String latitude) {
            this.latitude = latitude;
        }

        public String getLongitude() {
            return longitude;
        }

        public void setLongitude(String longitude) {
            this.longitude = longitude;
        }

        public int getDelivery_distance() {
            return delivery_distance;
        }

        public void setDelivery_distance(int delivery_distance) {
            this.delivery_distance = delivery_distance;
        }

        public String getVenue_category() {
            return venue_category;
        }

        public void setVenue_category(String venue_category) {
            this.venue_category = venue_category;
        }

        public int getIs_booking_allow() {
            return is_booking_allow;
        }

        public void setIs_booking_allow(int is_booking_allow) {
            this.is_booking_allow = is_booking_allow;
        }

        public String getPayment_gatway() {
            return payment_gatway;
        }

        public void setPayment_gatway(String payment_gatway) {
            this.payment_gatway = payment_gatway;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public StarsBean getStars() {
            return stars;
        }

        public void setStars(StarsBean stars) {
            this.stars = stars;
        }

        public int getSameDayShipping() {
            return sameDayShipping;
        }

        public void setSameDayShipping(int sameDayShipping) {
            this.sameDayShipping = sameDayShipping;
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

        public boolean isIsWishlisted() {
            return isWishlisted;
        }

        public void setIsWishlisted(boolean isWishlisted) {
            this.isWishlisted = isWishlisted;
        }

        public List<String> getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(List<String> venue_images) {
            this.venue_images = venue_images;
        }

        public List<CategoriesBean> getCategories() {
            return Categories;
        }

        public void setCategories(List<CategoriesBean> Categories) {
            this.Categories = Categories;
        }

        public static class StarsBean implements Serializable {
            /**
             * count : 1
             * venue_rating_value : 2.3
             */

            private int count;
            private String venue_rating_value;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getVenue_rating_value() {
                return venue_rating_value;
            }

            public void setVenue_rating_value(String venue_rating_value) {
                this.venue_rating_value = venue_rating_value;
            }
        }

        public static class CategoriesBean implements Serializable {
            /**
             * id : -1
             * cat_name : My Fav
             * parent_cat_id : null
             * image : null
             * color : #35CEEA
             * menu_level : 1
             * total_products : 9
             * isFav : 1
             */

            private int id;
            private String cat_name;
            private Object parent_cat_id;
            private Object image;
            private String color;
            private int menu_level;
            private int total_products;
            private int isFav;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCat_name() {
                return cat_name;
            }

            public void setCat_name(String cat_name) {
                this.cat_name = cat_name;
            }

            public Object getParent_cat_id() {
                return parent_cat_id;
            }

            public void setParent_cat_id(Object parent_cat_id) {
                this.parent_cat_id = parent_cat_id;
            }

            public Object getImage() {
                return image;
            }

            public void setImage(Object image) {
                this.image = image;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }

            public int getMenu_level() {
                return menu_level;
            }

            public void setMenu_level(int menu_level) {
                this.menu_level = menu_level;
            }

            public int getTotal_products() {
                return total_products;
            }

            public void setTotal_products(int total_products) {
                this.total_products = total_products;
            }

            public int getIsFav() {
                return isFav;
            }

            public void setIsFav(int isFav) {
                this.isFav = isFav;
            }
        }
    }

    public static class VenueWeekTimesBean implements Serializable {
        /**
         * day_name : Monday
         * opening_time : 2021-07-13 09:00
         * closing_time : 2021-07-13 20:00
         */

        private String day_name;
        private String opening_time;
        private String closing_time;

        public String getDay_name() {
            return day_name;
        }

        public void setDay_name(String day_name) {
            this.day_name = day_name;
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
    }

    public static class ShippingDataBean {
        /**
         * id : 31
         * from_day : 1
         * to_day : 1
         * time : 14:00
         * amount : 3.00
         * type : 1
         * name : Same Day Delivery
         * hourly_delivery : 0
         */

        private int id;
        private int from_day;
        private int to_day;
        private String time;
        private String amount;
        private int type;
        private String name;
        private int hourly_delivery;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFrom_day() {
            return from_day;
        }

        public void setFrom_day(int from_day) {
            this.from_day = from_day;
        }

        public int getTo_day() {
            return to_day;
        }

        public void setTo_day(int to_day) {
            this.to_day = to_day;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getHourly_delivery() {
            return hourly_delivery;
        }

        public void setHourly_delivery(int hourly_delivery) {
            this.hourly_delivery = hourly_delivery;
        }
    }

}
