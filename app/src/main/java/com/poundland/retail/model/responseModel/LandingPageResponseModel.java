package com.poundland.retail.model.responseModel;

import java.util.List;

public class LandingPageResponseModel {
    private String message;
    private boolean status;
    private Banner1Bean banner1;
    private Banner2Bean banner2;
    private List<TopCatProductsBean> topCatProducts;
    private int total_liked;
    private int total_quantity;
    private int total_bulk_quantity;
    private int total_carts;
    private int total_retailVenues;
    private int total_swoopeEatVenues;
    private List<DiscountOffersBean> discountOffers;
    private List<BannersBean> banners;
    private List<CategoriesBean> categories;
    private List<TopcategoriesMonthBean> topcategoriesMonth;
    private List<?> carts;
    // private List<?> shippingData;
    private List<NewArrivalProductsBean> newArrivalProducts;
    private List<DemoVenuesBean> demoVenues;
    private List<SpecialOffersBean> special_offers;
    private List<RetailVanuesBean> retailVanues;
    private List<SwoopeEatVanuesBean> swoopeEatVanues;
    private List<Integer> guests;
    private List<TimesBean> times;
    private List<ShowTabsBean> showTabs;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<TopCatProductsBean> getTopCatProducts() {
        return topCatProducts;
    }

    public void setTopCatProducts(List<TopCatProductsBean> topCatProducts) {
        this.topCatProducts = topCatProducts;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Banner1Bean getBanner1() {
        return banner1;
    }

    public void setBanner1(Banner1Bean banner1) {
        this.banner1 = banner1;
    }

    public Banner2Bean getBanner2() {
        return banner2;
    }

    public void setBanner2(Banner2Bean banner2) {
        this.banner2 = banner2;
    }

    public int getTotal_liked() {
        return total_liked;
    }

    public void setTotal_liked(int total_liked) {
        this.total_liked = total_liked;
    }

    public int getTotal_quantity() {
        return total_quantity;
    }

    public void setTotal_quantity(int total_quantity) {
        this.total_quantity = total_quantity;
    }

    public int getTotal_bulk_quantity() {
        return total_bulk_quantity;
    }

    public void setTotal_bulk_quantity(int total_bulk_quantity) {
        this.total_bulk_quantity = total_bulk_quantity;
    }

    public int getTotal_carts() {
        return total_carts;
    }

    public void setTotal_carts(int total_carts) {
        this.total_carts = total_carts;
    }

    public int getTotal_retailVenues() {
        return total_retailVenues;
    }

    public void setTotal_retailVenues(int total_retailVenues) {
        this.total_retailVenues = total_retailVenues;
    }

    public int getTotal_swoopeEatVenues() {
        return total_swoopeEatVenues;
    }

    public void setTotal_swoopeEatVenues(int total_swoopeEatVenues) {
        this.total_swoopeEatVenues = total_swoopeEatVenues;
    }

    public List<DiscountOffersBean> getDiscountOffers() {
        return discountOffers;
    }

    public void setDiscountOffers(List<DiscountOffersBean> discountOffers) {
        this.discountOffers = discountOffers;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<CategoriesBean> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesBean> categories) {
        this.categories = categories;
    }

    public List<TopcategoriesMonthBean> getTopcategoriesMonth() {
        return topcategoriesMonth;
    }

    public void setTopcategoriesMonth(List<TopcategoriesMonthBean> topcategoriesMonth) {
        this.topcategoriesMonth = topcategoriesMonth;
    }

    public List<?> getCarts() {
        return carts;
    }

    public void setCarts(List<?> carts) {
        this.carts = carts;
    }


    public List<NewArrivalProductsBean> getNewArrivalProducts() {
        return newArrivalProducts;
    }

    public void setNewArrivalProducts(List<NewArrivalProductsBean> newArrivalProducts) {
        this.newArrivalProducts = newArrivalProducts;
    }

    public List<DemoVenuesBean> getDemoVenues() {
        return demoVenues;
    }

    public void setDemoVenues(List<DemoVenuesBean> demoVenues) {
        this.demoVenues = demoVenues;
    }

    public List<SpecialOffersBean> getSpecial_offers() {
        return special_offers;
    }

    public void setSpecial_offers(List<SpecialOffersBean> special_offers) {
        this.special_offers = special_offers;
    }

    public List<RetailVanuesBean> getRetailVanues() {
        return retailVanues;
    }

    public void setRetailVanues(List<RetailVanuesBean> retailVanues) {
        this.retailVanues = retailVanues;
    }

    public List<SwoopeEatVanuesBean> getSwoopeEatVanues() {
        return swoopeEatVanues;
    }

    public void setSwoopeEatVanues(List<SwoopeEatVanuesBean> swoopeEatVanues) {
        this.swoopeEatVanues = swoopeEatVanues;
    }

    public List<Integer> getGuests() {
        return guests;
    }

    public void setGuests(List<Integer> guests) {
        this.guests = guests;
    }

    public List<TimesBean> getTimes() {
        return times;
    }

    public void setTimes(List<TimesBean> times) {
        this.times = times;
    }

    public List<ShowTabsBean> getShowTabs() {
        return showTabs;
    }

    public void setShowTabs(List<ShowTabsBean> showTabs) {
        this.showTabs = showTabs;
    }

    public static class Banner1Bean {
        /**
         * id : 24
         * merchant_id : 5
         * venue_id : 202002121635135
         * banner_for : 1
         * banner_type : 1
         * user_ecom : 1
         * ecom_banner : uploaded/banner/ecombanner/6747616045798070.png
         * ecom_banner_ipad : uploaded/banner/ecombanner/5119116009343450.png
         * ecom_banner_mobile : uploaded/banner/ecombanner/7753916009343450.png
         * ecom_banner_internal : null
         * banner_placement_main : 0
         * category : 3
         * start_time : 00:00:00
         * end_time : 23:00:00
         * product_categories_id : 3
         * ecomProductUrl :
         * gender_for_show : Both
         * ecomCatUrl : 1
         * venue_name : Lillywhites
         */

        private int id;
        private int merchant_id;
        private String venue_id;
        private int banner_for;
        private int banner_type;
        private int user_ecom;
        private String ecom_banner;
        private String ecom_banner_ipad;
        private String ecom_banner_mobile;
        private Object ecom_banner_internal;
        private int banner_placement_main;
        private int category;
        private String start_time;
        private String end_time;
        private String product_categories_id;
        private String ecomProductUrl;
        private String gender_for_show;
        private int ecomCatUrl;
        private String venue_name;

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

        public int getBanner_for() {
            return banner_for;
        }

        public void setBanner_for(int banner_for) {
            this.banner_for = banner_for;
        }

        public int getBanner_type() {
            return banner_type;
        }

        public void setBanner_type(int banner_type) {
            this.banner_type = banner_type;
        }

        public int getUser_ecom() {
            return user_ecom;
        }

        public void setUser_ecom(int user_ecom) {
            this.user_ecom = user_ecom;
        }

        public String getEcom_banner() {
            return ecom_banner;
        }

        public void setEcom_banner(String ecom_banner) {
            this.ecom_banner = ecom_banner;
        }

        public String getEcom_banner_ipad() {
            return ecom_banner_ipad;
        }

        public void setEcom_banner_ipad(String ecom_banner_ipad) {
            this.ecom_banner_ipad = ecom_banner_ipad;
        }

        public String getEcom_banner_mobile() {
            return ecom_banner_mobile;
        }

        public void setEcom_banner_mobile(String ecom_banner_mobile) {
            this.ecom_banner_mobile = ecom_banner_mobile;
        }

        public Object getEcom_banner_internal() {
            return ecom_banner_internal;
        }

        public void setEcom_banner_internal(Object ecom_banner_internal) {
            this.ecom_banner_internal = ecom_banner_internal;
        }

        public int getBanner_placement_main() {
            return banner_placement_main;
        }

        public void setBanner_placement_main(int banner_placement_main) {
            this.banner_placement_main = banner_placement_main;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getProduct_categories_id() {
            return product_categories_id;
        }

        public void setProduct_categories_id(String product_categories_id) {
            this.product_categories_id = product_categories_id;
        }

        public String getEcomProductUrl() {
            return ecomProductUrl;
        }

        public void setEcomProductUrl(String ecomProductUrl) {
            this.ecomProductUrl = ecomProductUrl;
        }

        public String getGender_for_show() {
            return gender_for_show;
        }

        public void setGender_for_show(String gender_for_show) {
            this.gender_for_show = gender_for_show;
        }

        public int getEcomCatUrl() {
            return ecomCatUrl;
        }

        public void setEcomCatUrl(int ecomCatUrl) {
            this.ecomCatUrl = ecomCatUrl;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }
    }

    public static class Banner2Bean {
        /**
         * id : 25
         * merchant_id : 5
         * venue_id : 202002121635135
         * banner_for : 1
         * banner_type : 2
         * user_ecom : 1
         * ecom_banner : uploaded/banner/ecombanner/6329216044874410.png
         * ecom_banner_ipad : uploaded/banner/ecombanner/8508116009344520.png
         * ecom_banner_mobile : uploaded/banner/ecombanner/8436916009344520.png
         * ecom_banner_internal : null
         * banner_placement_main : 0
         * category : 3
         * start_time : 00:15:00
         * end_time : 23:00:00
         * product_categories_id : 3
         * ecomProductUrl :
         * gender_for_show : Both
         * ecomCatUrl : 1
         * venue_name : Lillywhites
         */

        private int id;
        private int merchant_id;
        private String venue_id;
        private int banner_for;
        private int banner_type;
        private int user_ecom;
        private String ecom_banner;
        private String ecom_banner_ipad;
        private String ecom_banner_mobile;
        private Object ecom_banner_internal;
        private int banner_placement_main;
        private int category;
        private String start_time;
        private String end_time;
        private String product_categories_id;
        private String ecomProductUrl;
        private String gender_for_show;
        private int ecomCatUrl;
        private String venue_name;

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

        public int getBanner_for() {
            return banner_for;
        }

        public void setBanner_for(int banner_for) {
            this.banner_for = banner_for;
        }

        public int getBanner_type() {
            return banner_type;
        }

        public void setBanner_type(int banner_type) {
            this.banner_type = banner_type;
        }

        public int getUser_ecom() {
            return user_ecom;
        }

        public void setUser_ecom(int user_ecom) {
            this.user_ecom = user_ecom;
        }

        public String getEcom_banner() {
            return ecom_banner;
        }

        public void setEcom_banner(String ecom_banner) {
            this.ecom_banner = ecom_banner;
        }

        public String getEcom_banner_ipad() {
            return ecom_banner_ipad;
        }

        public void setEcom_banner_ipad(String ecom_banner_ipad) {
            this.ecom_banner_ipad = ecom_banner_ipad;
        }

        public String getEcom_banner_mobile() {
            return ecom_banner_mobile;
        }

        public void setEcom_banner_mobile(String ecom_banner_mobile) {
            this.ecom_banner_mobile = ecom_banner_mobile;
        }

        public Object getEcom_banner_internal() {
            return ecom_banner_internal;
        }

        public void setEcom_banner_internal(Object ecom_banner_internal) {
            this.ecom_banner_internal = ecom_banner_internal;
        }

        public int getBanner_placement_main() {
            return banner_placement_main;
        }

        public void setBanner_placement_main(int banner_placement_main) {
            this.banner_placement_main = banner_placement_main;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getProduct_categories_id() {
            return product_categories_id;
        }

        public void setProduct_categories_id(String product_categories_id) {
            this.product_categories_id = product_categories_id;
        }

        public String getEcomProductUrl() {
            return ecomProductUrl;
        }

        public void setEcomProductUrl(String ecomProductUrl) {
            this.ecomProductUrl = ecomProductUrl;
        }

        public String getGender_for_show() {
            return gender_for_show;
        }

        public void setGender_for_show(String gender_for_show) {
            this.gender_for_show = gender_for_show;
        }

        public int getEcomCatUrl() {
            return ecomCatUrl;
        }

        public void setEcomCatUrl(int ecomCatUrl) {
            this.ecomCatUrl = ecomCatUrl;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }
    }

    public static class DiscountOffersBean {
        /**
         * id : 157
         * offer_title : 50% Off
         * start_date : 2020-12-08
         * end_date : 2020-12-31
         * offer_time_start : 00:00:00
         * offer_time_end : 23:00:00
         * product_id : 1981
         * type : 1
         * discount_type : 1
         * discount_amount : 50.00
         * merchant_id : 15
         * venue_id : 2020102010172215
         * product_name : Unisex Jet Black Shots BEAT Truly Wireless Earbuds
         * product_description : <p><br data-mce-bogus="1"></p>
         * brand_id : 90
         * tax_id : 27
         * images : uploaded/products/1597116046527300.jpeg
         * modifier_id : 2538
         * modifier_name : null
         * buy_price : 2.00
         * sell_price : 20.00
         * weight : 0
         * quantity_per_case : 1
         * bulk_selling_price : 35.00
         * avl_quantity : 49941
         * modifier_images : null
         * venue_name : Next
         * venue_images : 1603185442-500.jpg
         * address_1 : Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK
         * address_2 : null
         * city_name : West Midlands
         * country : United Kingdom
         * post_code : WV1 1LE
         * home_delivery_mov : 12.00
         * delivery : 1
         * collection : 1
         * latitude : 52.5875483
         * longitude : -2.120122100000001
         * distance : 1.12
         * sold_count : 33
         * mod_count : 1
         * new_price : 10.00
         * stars : {"count":3,"rating_value":"4.7"}
         * isLiked : 1
         * sameDayShipping : 1
         */

        private int id;
        private String offer_title;
        private String start_date;
        private String end_date;
        private String offer_time_start;
        private String offer_time_end;
        private int product_id;
        private int type;
        private int discount_type;
        private String discount_amount;
        private int merchant_id;
        private String venue_id;
        private String product_name;
        private String product_description;
        private int brand_id;
        private String tax_id;
        private String images;
        private int modifier_id;
        private Object modifier_name;
        private String buy_price;
        private String sell_price;
        private String weight;
        private int quantity_per_case;
        private String bulk_selling_price;
        private int avl_quantity;
        private Object modifier_images;
        private String venue_name;
        private String venue_images;
        private String address_1;
        private Object address_2;
        private String city_name;
        private String country;
        private String post_code;
        private String home_delivery_mov;
        private int delivery;
        private int collection;
        private String latitude;
        private String longitude;
        private String distance;
        private String sold_count;
        private int mod_count;
        private String new_price;
        private StarsBeanXXX stars;
        private int isLiked;
        private int sameDayShipping;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOffer_title() {
            return offer_title;
        }

        public void setOffer_title(String offer_title) {
            this.offer_title = offer_title;
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

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getDiscount_type() {
            return discount_type;
        }

        public void setDiscount_type(int discount_type) {
            this.discount_type = discount_type;
        }

        public String getDiscount_amount() {
            return discount_amount;
        }

        public void setDiscount_amount(String discount_amount) {
            this.discount_amount = discount_amount;
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

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_description() {
            return product_description;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public String getTax_id() {
            return tax_id;
        }

        public void setTax_id(String tax_id) {
            this.tax_id = tax_id;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(int modifier_id) {
            this.modifier_id = modifier_id;
        }

        public Object getModifier_name() {
            return modifier_name;
        }

        public void setModifier_name(Object modifier_name) {
            this.modifier_name = modifier_name;
        }

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }

        public String getSell_price() {
            return sell_price;
        }

        public void setSell_price(String sell_price) {
            this.sell_price = sell_price;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public int getQuantity_per_case() {
            return quantity_per_case;
        }

        public void setQuantity_per_case(int quantity_per_case) {
            this.quantity_per_case = quantity_per_case;
        }

        public String getBulk_selling_price() {
            return bulk_selling_price;
        }

        public void setBulk_selling_price(String bulk_selling_price) {
            this.bulk_selling_price = bulk_selling_price;
        }

        public int getAvl_quantity() {
            return avl_quantity;
        }

        public void setAvl_quantity(int avl_quantity) {
            this.avl_quantity = avl_quantity;
        }

        public Object getModifier_images() {
            return modifier_images;
        }

        public void setModifier_images(Object modifier_images) {
            this.modifier_images = modifier_images;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(String venue_images) {
            this.venue_images = venue_images;
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

        public String getHome_delivery_mov() {
            return home_delivery_mov;
        }

        public void setHome_delivery_mov(String home_delivery_mov) {
            this.home_delivery_mov = home_delivery_mov;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getSold_count() {
            return sold_count;
        }

        public void setSold_count(String sold_count) {
            this.sold_count = sold_count;
        }

        public int getMod_count() {
            return mod_count;
        }

        public void setMod_count(int mod_count) {
            this.mod_count = mod_count;
        }

        public String getNew_price() {
            return new_price;
        }

        public void setNew_price(String new_price) {
            this.new_price = new_price;
        }

        public StarsBeanXXX getStars() {
            return stars;
        }

        public void setStars(StarsBeanXXX stars) {
            this.stars = stars;
        }

        public int getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(int isLiked) {
            this.isLiked = isLiked;
        }

        public int getSameDayShipping() {
            return sameDayShipping;
        }

        public void setSameDayShipping(int sameDayShipping) {
            this.sameDayShipping = sameDayShipping;
        }

        public static class StarsBeanXXX {
            /**
             * count : 3
             * rating_value : 4.7
             */

            private int count;
            private String rating_value;

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
        }
    }

    public static class BannersBean {
        /**
         * id : 9
         * merchant_id : 5
         * venue_id : 202002121635135
         * banner_for : 1
         * banner_type : 0
         * user_ecom : 1
         * ecom_banner : uploaded/banner/ecombanner/6887116009282980.png
         * ecom_banner_ipad : uploaded/banner/ecombanner/6729515978336410.png
         * ecom_banner_mobile : uploaded/banner/ecombanner/7236315978336410.png
         * ecom_banner_internal : uploaded/banner/ecomInternalbanner/8658615978336410.png
         * banner_placement_main : 1
         * category : 0
         * start_time : 00:00:00  * end_time : 23:00:00
         * product_categories_id : 3
         * ecomProductUrl :
         * gender_for_show : Both
         * ecomCatUrl : 1
         * venue_name : Lillywhites
         */


        private int id;
        private int merchant_id;
        private String venue_id;
        private int banner_for;
        private int user_app;
        private int app_banner_for;
        private int banner_type;
        private int user_ecom;
        private String appProductUrl;
        private String app_banner;
        private String ecom_banner;
        private String ecom_banner_ipad;
        private String ecom_banner_mobile;
        private String ecom_banner_internal;
        private int banner_placement_main;
        private int category;
        private String start_time;
        private String end_time;
        private String product_categories_id;
        private String ecomProductUrl;
        private int appCatUrl;
        private String gender_for_show;
        private int ecomCatUrl;
        private String venue_name;

        public int getUser_app() {
            return user_app;
        }

        public void setUser_app(int user_app) {
            this.user_app = user_app;
        }

        public int getApp_banner_for() {
            return app_banner_for;
        }

        public void setApp_banner_for(int app_banner_for) {
            this.app_banner_for = app_banner_for;
        }

        public String getAppProductUrl() {
            return appProductUrl;
        }

        public void setAppProductUrl(String appProductUrl) {
            this.appProductUrl = appProductUrl;
        }

        public String getApp_banner() {
            return app_banner;
        }

        public void setApp_banner(String app_banner) {
            this.app_banner = app_banner;
        }

        public int getAppCatUrl() {
            return appCatUrl;
        }

        public void setAppCatUrl(int appCatUrl) {
            this.appCatUrl = appCatUrl;
        }

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

        public int getBanner_for() {
            return banner_for;
        }

        public void setBanner_for(int banner_for) {
            this.banner_for = banner_for;
        }

        public int getBanner_type() {
            return banner_type;
        }

        public void setBanner_type(int banner_type) {
            this.banner_type = banner_type;
        }

        public int getUser_ecom() {
            return user_ecom;
        }

        public void setUser_ecom(int user_ecom) {
            this.user_ecom = user_ecom;
        }

        public String getEcom_banner() {
            return ecom_banner;
        }

        public void setEcom_banner(String ecom_banner) {
            this.ecom_banner = ecom_banner;
        }

        public String getEcom_banner_ipad() {
            return ecom_banner_ipad;
        }

        public void setEcom_banner_ipad(String ecom_banner_ipad) {
            this.ecom_banner_ipad = ecom_banner_ipad;
        }

        public String getEcom_banner_mobile() {
            return ecom_banner_mobile;
        }

        public void setEcom_banner_mobile(String ecom_banner_mobile) {
            this.ecom_banner_mobile = ecom_banner_mobile;
        }

        public String getEcom_banner_internal() {
            return ecom_banner_internal;
        }

        public void setEcom_banner_internal(String ecom_banner_internal) {
            this.ecom_banner_internal = ecom_banner_internal;
        }

        public int getBanner_placement_main() {
            return banner_placement_main;
        }

        public void setBanner_placement_main(int banner_placement_main) {
            this.banner_placement_main = banner_placement_main;
        }

        public int getCategory() {
            return category;
        }

        public void setCategory(int category) {
            this.category = category;
        }

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getProduct_categories_id() {
            return product_categories_id;
        }

        public void setProduct_categories_id(String product_categories_id) {
            this.product_categories_id = product_categories_id;
        }

        public String getEcomProductUrl() {
            return ecomProductUrl;
        }

        public void setEcomProductUrl(String ecomProductUrl) {
            this.ecomProductUrl = ecomProductUrl;
        }

        public String getGender_for_show() {
            return gender_for_show;
        }

        public void setGender_for_show(String gender_for_show) {
            this.gender_for_show = gender_for_show;
        }

        public int getEcomCatUrl() {
            return ecomCatUrl;
        }

        public void setEcomCatUrl(int ecomCatUrl) {
            this.ecomCatUrl = ecomCatUrl;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }
    }

    public static class CategoriesBean {
        /**
         * id : 17
         * cat_name : Chocolates
         * parent_cat_id : 0
         * image : Chocolates29102020042128.png
         * color : e66465
         * menu_level : 1
         * sold_count : 2
         */

        private int id;
        private String cat_name;
        private int parent_cat_id;
        private String image;
        private String color;
        private int menu_level;
        private int sold_count;

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

        public int getParent_cat_id() {
            return parent_cat_id;
        }

        public void setParent_cat_id(int parent_cat_id) {
            this.parent_cat_id = parent_cat_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
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

        public int getSold_count() {
            return sold_count;
        }

        public void setSold_count(int sold_count) {
            this.sold_count = sold_count;
        }
    }

    public static class TopcategoriesMonthBean {
        /**
         * id : 18
         * cat_name : Cleaning
         * parent_cat_id : 0
         * image : Cleaning29102020042701.png
         * color : e66465
         * menu_level : 1
         * sold_count : 751
         */

        private int id;
        private String cat_name;
        private int parent_cat_id;
        private String image;
        private String color;
        private int menu_level;
        private int sold_count;

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

        public int getParent_cat_id() {
            return parent_cat_id;
        }

        public void setParent_cat_id(int parent_cat_id) {
            this.parent_cat_id = parent_cat_id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
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

        public int getSold_count() {
            return sold_count;
        }

        public void setSold_count(int sold_count) {
            this.sold_count = sold_count;
        }
    }

    public static class NewArrivalProductsBean {
        /**
         * id : 2308
         * merchant_id : 5
         * product_name : Stylecraft Extra Special DK Wool
         * product_description : 100% Acrylic
         * <p>
         * 100g Ball
         * <p>
         * 322 yards / 295m
         * <p>
         * Needle Size: 4mm
         * <p>
         * Tension: 22 Stitches * 30 rows to 10cm square
         * <p>
         * Washcare: Machine wash at 40 degrees c, cool tumble dry.
         * category_id : 583
         * brand_id : 0
         * images : uploaded/products/33334160855191919.jpeg
         * modifier_name : Carnation
         * buy_price : 0.00
         * selling_price : 1.95
         * quantity_per_case : 1
         * bulk_selling_price : 0.00
         * modifier_id : 2982
         * weight : 0
         * avl_quantity : 10
         * modifier_images : uploaded/products/34076160855191921.jpeg
         * mod_count : 4
         * venue_id : 202010281859135
         * venue_name : Vik Grocery
         * venue_images : 1603911554-download.jpeg
         * address_1 : Coalway Road
         * address_2 : null
         * city_name : Wolverhampton
         * country : United Kingdom
         * post_code : WV3 7NA
         * delivery : 1
         * collection : 1
         * latitude : 52.5691533
         * longitude : -2.1561618
         * delivery_distance : 12
         * v_distance : 0.87
         * offer_id : null
         * offer_title : null
         * type : null
         * discount_type : null
         * discount_amount : null
         * new_price : 1.95
         * sameDayShipping : 1
         * isLiked : 0
         */

        private int id;
        private int merchant_id;
        private String product_name;
        private String product_description;
        private String category_id;
        private int brand_id;
        private String images;
        private String modifier_name;
        private String buy_price;
        private String selling_price;
        private int quantity_per_case;
        private String bulk_selling_price;
        private int modifier_id;
        private String weight;
        private int avl_quantity;
        private String modifier_images;
        private int mod_count;
        private String venue_id;
        private String venue_name;
        private String venue_images;
        private String address_1;
        private Object address_2;
        private String city_name;
        private String country;
        private String post_code;
        private int delivery;
        private int collection;
        private String latitude;
        private String longitude;
        private int delivery_distance;
        private String v_distance;
        private Object offer_id;
        private Object offer_title;
        private Object type;
        private Object discount_type;
        private Object discount_amount;
        private String new_price;
        private int sameDayShipping;
        private int isLiked;

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

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_description() {
            return product_description;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
        }

        public String getCategory_id() {
            return category_id;
        }

        public void setCategory_id(String category_id) {
            this.category_id = category_id;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public String getModifier_name() {
            return modifier_name;
        }

        public void setModifier_name(String modifier_name) {
            this.modifier_name = modifier_name;
        }

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }

        public String getSelling_price() {
            return selling_price;
        }

        public void setSelling_price(String selling_price) {
            this.selling_price = selling_price;
        }

        public int getQuantity_per_case() {
            return quantity_per_case;
        }

        public void setQuantity_per_case(int quantity_per_case) {
            this.quantity_per_case = quantity_per_case;
        }

        public String getBulk_selling_price() {
            return bulk_selling_price;
        }

        public void setBulk_selling_price(String bulk_selling_price) {
            this.bulk_selling_price = bulk_selling_price;
        }

        public int getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(int modifier_id) {
            this.modifier_id = modifier_id;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }

        public int getAvl_quantity() {
            return avl_quantity;
        }

        public void setAvl_quantity(int avl_quantity) {
            this.avl_quantity = avl_quantity;
        }

        public String getModifier_images() {
            return modifier_images;
        }

        public void setModifier_images(String modifier_images) {
            this.modifier_images = modifier_images;
        }

        public int getMod_count() {
            return mod_count;
        }

        public void setMod_count(int mod_count) {
            this.mod_count = mod_count;
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

        public String getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(String venue_images) {
            this.venue_images = venue_images;
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

        public String getV_distance() {
            return v_distance;
        }

        public void setV_distance(String v_distance) {
            this.v_distance = v_distance;
        }

        public Object getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(Object offer_id) {
            this.offer_id = offer_id;
        }

        public Object getOffer_title() {
            return offer_title;
        }

        public void setOffer_title(Object offer_title) {
            this.offer_title = offer_title;
        }

        public Object getType() {
            return type;
        }

        public void setType(Object type) {
            this.type = type;
        }

        public Object getDiscount_type() {
            return discount_type;
        }

        public void setDiscount_type(Object discount_type) {
            this.discount_type = discount_type;
        }

        public Object getDiscount_amount() {
            return discount_amount;
        }

        public void setDiscount_amount(Object discount_amount) {
            this.discount_amount = discount_amount;
        }

        public String getNew_price() {
            return new_price;
        }

        public void setNew_price(String new_price) {
            this.new_price = new_price;
        }

        public int getSameDayShipping() {
            return sameDayShipping;
        }

        public void setSameDayShipping(int sameDayShipping) {
            this.sameDayShipping = sameDayShipping;
        }

        public int getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(int isLiked) {
            this.isLiked = isLiked;
        }
    }


    public static class TopCatProductsBean {
        /**
         * cat_name : Cleaning
         * cat_id : 18
         * products : [{"id":164,"merchant_id":5,"product_name":"Adidas Linear OTH Hoodie Ladies Grey","product_description":"<p>This Adidas Linear OTH Hoodie is crafted in an over the head style with long sleeves. It features a drawstring adjustable hood and two side pockets. This hoodie has a lightweight construction designed with a signature printed logo and is complete with Adidas branding. You do not want to miss out on this one.<\/p>","category_id":"3","brand_id":5,"images":"uploaded/products/9177515995428920.png","buy_price":"0.50","selling_price":"1.00","quantity_per_case":0,"bulk_selling_price":"0.00","modifier_id":163,"avl_quantity":9,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":4,"rating_value":"4.0"},"isLiked":1},{"id":165,"merchant_id":5,"product_name":"Adidas SID Hoodie Ladies","product_description":"<p>Keep things casual with the adidas SID Hoodie. Constructed from a soft cotton-blend for a comfortable wear, this hoodie is crafted with long sleeves leading to ribbed cuffs for a precise fit and a drawstring adjustable hood. Boasting a modern geometric design to the sleeves, the piece is finished with the adidas logo to the front body for a sporty look.<\/p>","category_id":"3","brand_id":5,"images":"uploaded/products/3576915995427700.png","buy_price":"12.50","selling_price":"25.00","quantity_per_case":0,"bulk_selling_price":"0.00","modifier_id":164,"avl_quantity":53,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":2,"rating_value":"5.0"},"isLiked":1},{"id":166,"merchant_id":5,"product_name":"Adidas Foil QT T Shirt Ladies","product_description":"<p>This adidas Foil QT T Shirt is crafted with short sleeves and a crew neck for a classic look. It features a ribbed trim for a comfortable feel and is a lightweight construction. This t shirt is designed with a signature logo and is complete with adidas branding.<\/p>","category_id":"3","brand_id":5,"images":"uploaded/products/7087315995428430.png","buy_price":"7.00","selling_price":"13.00","quantity_per_case":0,"bulk_selling_price":"0.00","modifier_id":165,"avl_quantity":30,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":4,"rating_value":"4.5"},"isLiked":1},{"id":167,"merchant_id":5,"product_name":"Adidas Essentials AOP T Shirt Ladies","product_description":"The Ladies adidas Essentials AOP T Shirt offers a fun and stylish look that is perfect for everyday wear, featuring an all over print along with a wide collar and short sleeves for comfort, completed with the classic adidas branding printed to the chest.","category_id":"3","brand_id":5,"images":"uploaded/products/7062015995426520.png","buy_price":"10.00","selling_price":"19.99","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":166,"avl_quantity":65,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":1,"rating_value":"5.0"},"isLiked":0},{"id":168,"merchant_id":5,"product_name":"Adidas Wind Breaker Jacket Ladies","product_description":"This adidas Wind Breaker Jacket is crafted with full zip fastening and long sleeves for a classic look. It features a hood and 2 pockets. This jacket is a lightweight construction designed with a signature logo and is complete with adidas branding.","category_id":"3","brand_id":5,"images":"uploaded/products/3997915995425980.png","buy_price":"16.50","selling_price":"33.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":167,"avl_quantity":74,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":1,"rating_value":"5.0"},"isLiked":0},{"id":169,"merchant_id":5,"product_name":"Adidas Helionic Jacket Women's","product_description":"This Helionic Jacket from Adidas is crafted with a central zip fastening, elasticated cuffs for comfort and a fixed hood for those rainy days. With four pockets in total, this jacket is sure to have space to keep all your essentials secure and its high neck design is there to keep you warm. The quilted design is finished with contrasting trims and the Adidas logo to the chest.","category_id":"3","brand_id":5,"images":"uploaded/products/4596115995424730.png","buy_price":"52.50","selling_price":"105.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":168,"avl_quantity":88,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":1,"rating_value":"5.0"},"isLiked":0},{"id":170,"merchant_id":5,"product_name":"Adidas Varilite Down Jacket Ladies","product_description":"Stay warm through those cold months in this Ladies Varilite Down Jacket from adidas. Created from a lightweight fabric this jacket features a warm down interior, a stand up collar, elasticated cuffs, an elasticated waist, side zip pockets, full zip closure and adidas branding on the chest.","category_id":"3","brand_id":5,"images":"uploaded/products/1907415995423360.png","buy_price":"40.00","selling_price":"80.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":169,"avl_quantity":96,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":1,"rating_value":"4.0"},"isLiked":0},{"id":171,"merchant_id":5,"product_name":"Adidas Linear Slim Fit Cotton Joggers Ladies","product_description":"<p>Go from the gym to city sidewalks in sporty style. A slim fit and tapered legs create a flattering sleek look. These pants are made of cotton and recycled polyester French terry for a soft feel. You do not want to miss out on these ones. You do not want to miss out on this one, Perfect for everyday casual attire. Breathable and lightweight this piece is really something special to wear.<\/p>","category_id":"3","brand_id":5,"images":"uploaded/products/4769816039023620.png","buy_price":"14.00","selling_price":"27.99","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":170,"avl_quantity":91,"modifier_images":"uploaded/products/3767916038098280.jpeg","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":1,"rating_value":"5.0"},"isLiked":0},{"id":172,"merchant_id":5,"product_name":"Linear Slim Fit Cotton Joggers Ladies","product_description":"Go from the gym to city sidewalks in sporty style. A slim fit and tapered legs create a flattering sleek look. These pants are made of cotton and recycled polyester French terry for a soft feel. You do not want to miss out on these ones. You do not want to miss out on this one, Perfect for everyday casual attire. Breathable and lightweight this piece is really something special to wear.","category_id":"3","brand_id":5,"images":"uploaded/products/7900215995425410.png","buy_price":"9.00","selling_price":"18.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":171,"avl_quantity":80,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":1,"rating_value":"5.0"},"isLiked":0},{"id":173,"merchant_id":5,"product_name":"Logo Sports Bra Ladies","product_description":"<p>Conquer your next workout with the Logo Sports Bra from adidas. Crafted with a racer-back and elasticated hem, this piece is perfect for training sessions. Design is finished with adidas branding for a slick and stylish finish.<\/p>","category_id":"3","brand_id":5,"images":"uploaded/products/3858516034529270.png","buy_price":"6.50","selling_price":"13.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":172,"avl_quantity":57,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":174,"merchant_id":5,"product_name":"3 Stripes Logo Over The Head Hoody Mens","product_description":"The adidas 3 Stripes Logo Over The Head Hoody is ideal for training or casual wear. The over the head design is constructed with a soft outer fabric and brushed fleece lining for comfort and warmth. It features long sleeves with ribbed cuffs, a ribbed rear hem, hood with drawstrings and kangaroo pocket, with three stripe branding running across the shoulders and arms with an adidas logo to the chest.","category_id":"3","brand_id":5,"images":"uploaded/products/8533315984493550.png","buy_price":"20.00","selling_price":"35.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":173,"avl_quantity":94,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":175,"merchant_id":5,"product_name":"3 Stripe Logo Jogging Pants Mens","product_description":"These adidas 3 Stripe Logo Jogging Pants are crafted with an elasticated waistband and drawstring fastening. They feature 2 pockets for small possessions and are a lightweight construction. These joggers are designed with a signature logo and are complete with adidas branding. You do not want to miss out on these stunning classic tracksuit bottoms.","category_id":"3","brand_id":5,"images":"uploaded/products/6949815984494120.png","buy_price":"17.50","selling_price":"35.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":174,"avl_quantity":96,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":176,"merchant_id":5,"product_name":"3 Stripe Sereno T Shirt Mens","product_description":"This adidas C amo Linear T Shirt is crafted with short sleeves and a crew neck. It has a ribbed trim and a lightweight construction. This t shirt is a block colour design with a printed logo and is complete with adidas branding.","category_id":"3","brand_id":5,"images":"uploaded/products/9554215984494730.png","buy_price":"7.00","selling_price":"14.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":175,"avl_quantity":84,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":177,"merchant_id":5,"product_name":"Camo Linear T Shirt Mens","product_description":"This adidas Camo Linear T Shirt is crafted with short sleeves and a crew neck. It has a ribbed trim and a lightweight construction. This t shirt is a block colour design with a printed logo and is complete with adidas branding.","category_id":"3","brand_id":5,"images":"uploaded/products/7724215984495370.png","buy_price":"7.50","selling_price":"15.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":176,"avl_quantity":47,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":178,"merchant_id":5,"product_name":"3 Stripe Rugby Training Top Mens","product_description":"Leave it all out on the pitch in this men's rugby jersey. Made with a skin-tight fit, it features a contrast collar, 3-Stripes on the shoulders and an adidas Badge of Sport woven into the chest.","category_id":"3","brand_id":5,"images":"uploaded/products/7115915984495960.png","buy_price":"9.00","selling_price":"18.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":177,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":1},{"id":179,"merchant_id":5,"product_name":"Varilite Down Hooded Jacket Mens","product_description":"","category_id":"3","brand_id":5,"images":"uploaded/products/2500115984496550.png","buy_price":"30.00","selling_price":"60.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":178,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":180,"merchant_id":5,"product_name":"Adidas Solar Ride Mens Running Shoes","product_description":"","category_id":"7","brand_id":5,"images":"uploaded/products/8831815984497770.png","buy_price":"29.50","selling_price":"59.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":179,"avl_quantity":97,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":1,"rating_value":"5.0"},"isLiked":0},{"id":181,"merchant_id":5,"product_name":"Solarboost Mens Running Shoes","product_description":"","category_id":"7","brand_id":5,"images":"uploaded/products/6296615984498350.png","buy_price":"35.00","selling_price":"70.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":180,"avl_quantity":95,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":182,"merchant_id":5,"product_name":"Sensebounce Mens Running Shoes","product_description":"","category_id":"7","brand_id":5,"images":"uploaded/products/2342715984502020.png","buy_price":"42.00","selling_price":"84.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":181,"avl_quantity":98,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":1,"rating_value":"5.0"},"isLiked":0},{"id":183,"merchant_id":5,"product_name":"Ultraboost 19 Ladies Running Shoes","product_description":"","category_id":"7","brand_id":5,"images":"uploaded/products/6139815984502620.png","buy_price":"79.50","selling_price":"159.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":182,"avl_quantity":99,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":184,"merchant_id":5,"product_name":"SolarBlaze Ladies Running Shoes","product_description":"","category_id":"7","brand_id":5,"images":"uploaded/products/1666015984503240.png","buy_price":"25.00","selling_price":"50.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":183,"avl_quantity":99,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":185,"merchant_id":5,"product_name":"Bounce Trainers Ladies","product_description":"","category_id":"7","brand_id":5,"images":"uploaded/products/3590215984503650.png","buy_price":"25.00","selling_price":"50.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":184,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":186,"merchant_id":5,"product_name":"Crazy Chaos Ladies Trainers","product_description":"","category_id":"7","brand_id":5,"images":"uploaded/products/9409415984504070.png","buy_price":"25.50","selling_price":"51.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":185,"avl_quantity":94,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":187,"merchant_id":5,"product_name":"Epic Lux Women's Running Tights","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/5632615984504790.png","buy_price":"17.50","selling_price":"35.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":186,"avl_quantity":99,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":188,"merchant_id":5,"product_name":"Windrunner Hoodied Jacket Ladies","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/8666715984505230.png","buy_price":"20.00","selling_price":"40.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":187,"avl_quantity":99,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":189,"merchant_id":5,"product_name":"Sportswear Icon Clash Women's Fleece Hoodie","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/3920015984505760.png","buy_price":"14.50","selling_price":"29.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":188,"avl_quantity":99,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":190,"merchant_id":5,"product_name":"Air Satin Jacket Ladies","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/9661315984506220.png","buy_price":"15.00","selling_price":"30.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":189,"avl_quantity":99,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":191,"merchant_id":5,"product_name":"Indy Women's Light-Support Logo Sports Bra","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/4633115984506660.png","buy_price":"9.00","selling_price":"18.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":190,"avl_quantity":87,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":192,"merchant_id":5,"product_name":"Luxe Mesh TopLd01","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/7048815984507190.png","buy_price":"5.00","selling_price":"10.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":191,"avl_quantity":98,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":193,"merchant_id":5,"product_name":"Women's Medium-Support Sports Bra","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/8193115984507790.png","buy_price":"6.50","selling_price":"13.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":192,"avl_quantity":99,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":194,"merchant_id":5,"product_name":"Swoosh Run Women's Running Tank","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/5477015984508230.png","buy_price":"14.50","selling_price":"29.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":193,"avl_quantity":98,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":195,"merchant_id":5,"product_name":"Fast Back Swimsuit Ladies","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/4176515984508810.png","buy_price":"9.00","selling_price":"18.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":194,"avl_quantity":90,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":196,"merchant_id":5,"product_name":"Swoosh Medium-Support Sports Bra Ladies","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/1137315984509430.png","buy_price":"6.00","selling_price":"12.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":195,"avl_quantity":82,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":197,"merchant_id":5,"product_name":"Shine Hoodie Ladies","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/1941315984510130.png","buy_price":"17.50","selling_price":"35.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":196,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":198,"merchant_id":5,"product_name":"Shine Leggings Ladies","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/9567815984510840.png","buy_price":"9.50","selling_price":"19.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":197,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":199,"merchant_id":5,"product_name":"Air Max 200 Men's Shoe","product_description":"","category_id":"7","brand_id":4,"images":"uploaded/products/4490615984511320.png","buy_price":"40.00","selling_price":"80.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":198,"avl_quantity":99,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":200,"merchant_id":5,"product_name":"Free X Metcon 2 Men's Training Shoe,Unisex Cream-Coloured HOVR Summit Running Shoes","product_description":"","category_id":"7","brand_id":4,"images":"uploaded/products/7836415984511930.png","buy_price":"39.50","selling_price":"79.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":199,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":201,"merchant_id":5,"product_name":"Zoom 2K Men's Shoe","product_description":"","category_id":"7","brand_id":4,"images":"uploaded/products/7465015984512360.png","buy_price":"44.50","selling_price":"89.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":200,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":202,"merchant_id":5,"product_name":"Saint-Germain Fleece Pullover Hoodie","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/6945315984512820.png","buy_price":"20.00","selling_price":"40.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":201,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":203,"merchant_id":5,"product_name":"Element Wild Run Men's Long-Sleeve Top","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/7506315984513270.png","buy_price":"30.00","selling_price":"60.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":202,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":204,"merchant_id":5,"product_name":"Sportswear Club Men's Pullover Hoodie","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/9500615984514100.png","buy_price":"17.50","selling_price":"35.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":203,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":205,"merchant_id":5,"product_name":"Tiger Woods Novelty Polo Shirt Mens","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/6320715984514700.png","buy_price":"22.00","selling_price":"44.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":204,"avl_quantity":99,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":206,"merchant_id":5,"product_name":"Air Men's Pullover Hoodie test","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/4172815984515200.png","buy_price":"11.00","selling_price":"22.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":205,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":207,"merchant_id":5,"product_name":"Revolution 5 Women's Running Shoe","product_description":"","category_id":"7","brand_id":4,"images":"uploaded/products/7624615985087930.png","buy_price":"16.00","selling_price":"32.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":206,"avl_quantity":97,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":208,"merchant_id":5,"product_name":"Revolution 5 Women's Running Shoe Grey Red","product_description":"","category_id":"7","brand_id":4,"images":"uploaded/products/7528215985088790.png","buy_price":"17.50","selling_price":"35.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":207,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":209,"merchant_id":5,"product_name":"Air Max Oketo Women's Shoe","product_description":"","category_id":"7","brand_id":4,"images":"uploaded/products/6422415985096810.png","buy_price":"29.50","selling_price":"59.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":208,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":210,"merchant_id":5,"product_name":"Dry Crew Sweatshirt Mens","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/4698615985100710.png","buy_price":"11.50","selling_price":"23.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":209,"avl_quantity":35,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":211,"merchant_id":5,"product_name":"Sportswear Club Fleece Pullover Hoodie","product_description":"","category_id":"3","brand_id":4,"images":"uploaded/products/3477715985101260.png","buy_price":"17.00","selling_price":"34.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":210,"avl_quantity":96,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":212,"merchant_id":5,"product_name":"Courtland Trainers","product_description":"","category_id":"2,7","brand_id":6,"images":"uploaded/products/2438115985101990.png","buy_price":"22.50","selling_price":"45.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":211,"avl_quantity":97,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":213,"merchant_id":5,"product_name":"Rival Mid Mid Top Trainers Mens","product_description":"","category_id":"7","brand_id":6,"images":"uploaded/products/3276615985102490.png","buy_price":"29.00","selling_price":"58.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":212,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":214,"merchant_id":5,"product_name":"Nova Crew Sweatshirt Mens","product_description":"","category_id":"3","brand_id":6,"images":"uploaded/products/2743115985102980.png","buy_price":"11.50","selling_price":"23.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":213,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0},{"id":215,"merchant_id":5,"product_name":"Nova Hoodie Mens","product_description":"","category_id":"3","brand_id":6,"images":"uploaded/products/5632115985103540.png","buy_price":"22.00","selling_price":"44.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":214,"avl_quantity":100,"modifier_images":"","venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"20200918135512-20200506075051-lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","delivery":1,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","delivery_distance":0,"stars":{"count":0,"rating_value":"0"},"isLiked":0}]
         */

        private String cat_name;
        private int cat_id;
        private List<TopCatProductsBean.ProductsBean> products;

        public String getCat_name() {
            return cat_name;
        }

        public void setCat_name(String cat_name) {
            this.cat_name = cat_name;
        }

        public int getCat_id() {
            return cat_id;
        }

        public void setCat_id(int cat_id) {
            this.cat_id = cat_id;
        }

        public List<TopCatProductsBean.ProductsBean> getProducts() {
            return products;
        }

        public void setProducts(List<TopCatProductsBean.ProductsBean> products) {
            this.products = products;
        }

        public static class ProductsBean {
            /**
             * id : 164
             * merchant_id : 5
             * product_name : Adidas Linear OTH Hoodie Ladies Grey
             * product_description : <p>This Adidas Linear OTH Hoodie is crafted in an over the head style with long sleeves. It features a drawstring adjustable hood and two side pockets. This hoodie has a lightweight construction designed with a signature printed logo and is complete with Adidas branding. You do not want to miss out on this one.</p>
             * category_id : 3
             * brand_id : 5
             * images : uploaded/products/9177515995428920.png
             * buy_price : 0.50
             * selling_price : 1.00
             * quantity_per_case : 0
             * bulk_selling_price : 0.00
             * modifier_id : 163
             * avl_quantity : 9
             * modifier_images :
             * venue_id : 202002121635135
             * venue_name : Lillywhites
             * venue_images : 20200918135512-20200506075051-lillywhites.jpg
             * address_1 : 24-36 Regent St, St. James's, London, UK
             * address_2 : null
             * city_name : Wolverhampton
             * country : United Kingdom
             * post_code : SW1Y 4QF
             * delivery : 1
             * collection : 1
             * latitude : 51.5095249
             * longitude : -0.134223
             * delivery_distance : 0
             * stars : {"count":4,"rating_value":"4.0"}
             * isLiked : 1
             */

            private int id;
            private int merchant_id;
            private String product_name;
            private String product_description;
            private String category_id;
            private int brand_id;
            private String images;
            private String buy_price;
            private String selling_price;
            private int quantity_per_case;
            private String bulk_selling_price;
            private int modifier_id;
            private int avl_quantity;
            private String modifier_images;
            private String venue_id;
            private String venue_name;
            private String venue_images;
            private String address_1;
            private Object address_2;
            private String city_name;
            private String country;
            private String post_code;
            private int delivery;
            private int collection;
            private String latitude;
            private String longitude;
            private int delivery_distance;
            private TopCatProductsBean.ProductsBean.StarsBeanX stars;
            private int isLiked;

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

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
            }

            public String getProduct_description() {
                return product_description;
            }

            public void setProduct_description(String product_description) {
                this.product_description = product_description;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
            }

            public String getBuy_price() {
                return buy_price;
            }

            public void setBuy_price(String buy_price) {
                this.buy_price = buy_price;
            }

            public String getSelling_price() {
                return selling_price;
            }

            public void setSelling_price(String selling_price) {
                this.selling_price = selling_price;
            }

            public int getQuantity_per_case() {
                return quantity_per_case;
            }

            public void setQuantity_per_case(int quantity_per_case) {
                this.quantity_per_case = quantity_per_case;
            }

            public String getBulk_selling_price() {
                return bulk_selling_price;
            }

            public void setBulk_selling_price(String bulk_selling_price) {
                this.bulk_selling_price = bulk_selling_price;
            }

            public int getModifier_id() {
                return modifier_id;
            }

            public void setModifier_id(int modifier_id) {
                this.modifier_id = modifier_id;
            }

            public int getAvl_quantity() {
                return avl_quantity;
            }

            public void setAvl_quantity(int avl_quantity) {
                this.avl_quantity = avl_quantity;
            }

            public String getModifier_images() {
                return modifier_images;
            }

            public void setModifier_images(String modifier_images) {
                this.modifier_images = modifier_images;
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

            public String getVenue_images() {
                return venue_images;
            }

            public void setVenue_images(String venue_images) {
                this.venue_images = venue_images;
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

            public TopCatProductsBean.ProductsBean.StarsBeanX getStars() {
                return stars;
            }

            public void setStars(TopCatProductsBean.ProductsBean.StarsBeanX stars) {
                this.stars = stars;
            }

            public int getIsLiked() {
                return isLiked;
            }

            public void setIsLiked(int isLiked) {
                this.isLiked = isLiked;
            }

            public static class StarsBeanX {
                /**
                 * count : 4
                 * rating_value : 4.0
                 */

                private int count;
                private String rating_value;

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
            }
        }
    }

    public static class DemoVenuesBean {
        /**
         * id : 12
         * name : Vegan Store
         * venue_type : 1
         * venue_category : null
         * address_1 : Wolverhampton, UK
         * address_2 : null
         * post_code : WV30DS
         * city_name : Wolverhampton
         * country : United Kingdom
         * latitude : null
         * longitude : null
         * opening_time : 07:00
         * closing_time : 20:00
         * status : 1
         * image : uploaded/demo_venue/16063721380.jpeg
         * image_name : hcmp61763_113013.jpeg
         * created_at : 2020-11-26 06:28:58
         * updated_at : 2020-11-26 06:28:58
         * distance : null
         */

        private int id;
        private String name;
        private int venue_type;
        private Object venue_category;
        private String address_1;
        private Object address_2;
        private String post_code;
        private String city_name;
        private String country;
        private Object latitude;
        private Object longitude;
        private String opening_time;
        private String closing_time;
        private int status;
        private String image;
        private String image_name;
        private String created_at;
        private String updated_at;
        private Object distance;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getVenue_type() {
            return venue_type;
        }

        public void setVenue_type(int venue_type) {
            this.venue_type = venue_type;
        }

        public Object getVenue_category() {
            return venue_category;
        }

        public void setVenue_category(Object venue_category) {
            this.venue_category = venue_category;
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

        public String getPost_code() {
            return post_code;
        }

        public void setPost_code(String post_code) {
            this.post_code = post_code;
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

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
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

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getImage_name() {
            return image_name;
        }

        public void setImage_name(String image_name) {
            this.image_name = image_name;
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

        public Object getDistance() {
            return distance;
        }

        public void setDistance(Object distance) {
            this.distance = distance;
        }
    }

    public static class SpecialOffersBean {
        /**
         * id : 28
         * offer_title : Special offer X-mas
         * merchant_id : 16
         * venue_id : 2020061707363216
         * offer_detail : null
         * product_id : 1980
         * category_id : 251
         * modifier_id : 2532
         * terms_condition : null
         * start_date : 2020-11-07
         * end_date : 2020-12-31
         * start_time : 00:30:00
         * end_time : 23:00:00
         * regular_price : 22.00
         * offer_price : 15.00
         * gender : both
         * total_qty : 5
         * quantity_offer : 5
         * image : special_offer/9507116046415070.png
         * venue_name : World of Cloth
         * venue_images : 20200918135831-20200617083735-New-folder-1024x576.jpg
         * delivery : 1
         * collection : 1
         * delivery_distance : 0
         * buy_price : 15.00
         * sold_count : 0
         * avl_quantity : 5
         * modifier_images : special_offer/9507116046415070.png
         * product_name : Special offer X-mas
         * new_price : 15.00
         * selling_price : 22.00
         * offer_id : null
         * special_offer_id : 28
         * product_description : null
         * stars : {"count":0,"rating_value":"0"}
         * isLiked : 0
         */

        private int id;
        private String offer_title;
        private int merchant_id;
        private String venue_id;
        private Object offer_detail;
        private int product_id;
        private int category_id;
        private int modifier_id;
        private Object terms_condition;
        private String start_date;
        private String end_date;
        private String start_time;
        private String end_time;
        private String regular_price;
        private String offer_price;
        private String gender;
        private int total_qty;
        private int quantity_offer;
        private String image;
        private String venue_name;
        private String venue_images;
        private int delivery;
        private int collection;
        private int delivery_distance;
        private String buy_price;
        private int sold_count;
        private int avl_quantity;
        private String modifier_images;
        private String product_name;
        private String new_price;
        private String selling_price;
        private Object offer_id;
        private int special_offer_id;
        private Object product_description;
        private StarsBeanXXXX stars;
        private int isLiked;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOffer_title() {
            return offer_title;
        }

        public void setOffer_title(String offer_title) {
            this.offer_title = offer_title;
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

        public Object getOffer_detail() {
            return offer_detail;
        }

        public void setOffer_detail(Object offer_detail) {
            this.offer_detail = offer_detail;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public int getCategory_id() {
            return category_id;
        }

        public void setCategory_id(int category_id) {
            this.category_id = category_id;
        }

        public int getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(int modifier_id) {
            this.modifier_id = modifier_id;
        }

        public Object getTerms_condition() {
            return terms_condition;
        }

        public void setTerms_condition(Object terms_condition) {
            this.terms_condition = terms_condition;
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

        public String getStart_time() {
            return start_time;
        }

        public void setStart_time(String start_time) {
            this.start_time = start_time;
        }

        public String getEnd_time() {
            return end_time;
        }

        public void setEnd_time(String end_time) {
            this.end_time = end_time;
        }

        public String getRegular_price() {
            return regular_price;
        }

        public void setRegular_price(String regular_price) {
            this.regular_price = regular_price;
        }

        public String getOffer_price() {
            return offer_price;
        }

        public void setOffer_price(String offer_price) {
            this.offer_price = offer_price;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public int getTotal_qty() {
            return total_qty;
        }

        public void setTotal_qty(int total_qty) {
            this.total_qty = total_qty;
        }

        public int getQuantity_offer() {
            return quantity_offer;
        }

        public void setQuantity_offer(int quantity_offer) {
            this.quantity_offer = quantity_offer;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(String venue_images) {
            this.venue_images = venue_images;
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

        public int getDelivery_distance() {
            return delivery_distance;
        }

        public void setDelivery_distance(int delivery_distance) {
            this.delivery_distance = delivery_distance;
        }

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }

        public int getSold_count() {
            return sold_count;
        }

        public void setSold_count(int sold_count) {
            this.sold_count = sold_count;
        }

        public int getAvl_quantity() {
            return avl_quantity;
        }

        public void setAvl_quantity(int avl_quantity) {
            this.avl_quantity = avl_quantity;
        }

        public String getModifier_images() {
            return modifier_images;
        }

        public void setModifier_images(String modifier_images) {
            this.modifier_images = modifier_images;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
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

        public Object getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(Object offer_id) {
            this.offer_id = offer_id;
        }

        public int getSpecial_offer_id() {
            return special_offer_id;
        }

        public void setSpecial_offer_id(int special_offer_id) {
            this.special_offer_id = special_offer_id;
        }

        public Object getProduct_description() {
            return product_description;
        }

        public void setProduct_description(Object product_description) {
            this.product_description = product_description;
        }

        public StarsBeanXXXX getStars() {
            return stars;
        }

        public void setStars(StarsBeanXXXX stars) {
            this.stars = stars;
        }

        public int getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(int isLiked) {
            this.isLiked = isLiked;
        }

        public static class StarsBeanXXXX {
            /**
             * count : 0
             * rating_value : 0
             */

            private int count;
            private String rating_value;

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
        }
    }

    public static class RetailVanuesBean {
        /**
         * id : 47
         * merchant_id : 16
         * venue_id : 2020061707363216
         * venue_name : World of Cloth
         * venue_images : 20200918135831-20200617083735-New-folder-1024x576.jpg
         * address_1 : 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom
         * address_2 : 7th flor
         * city_name : Edinburgh
         * country : United Kingdom
         * post_code : EH127XD
         * phone_number : 919717802669
         * venue_email : willusssmith2019@gmail.com
         * venue_website : null
         * home_delivery_mov : 10.00
         * start_days : 1
         * end_days : 90
         * collection_time : 60
         * preparation_time : null
         * free_delivery : 20.00
         * delivery_charge : 2.00
         * delivery : 1
         * collection : 1
         * latitude : 55.9428422
         * longitude : -3.2831273
         * delivery_rating : 0
         * distance : 236.83
         * product_count : 303
         * opening_time : 2020-12-22 08:00
         * closing_time : 2020-12-22 20:00
         * isClose : 0
         * stars : 4.3
         * total_offers : 6
         * isWishlisted : false
         */

        private int id;
        private int merchant_id;
        private String venue_id;
        private String venue_name;
        private String venue_images;
        private String address_1;
        private String address_2;
        private String city_name;
        private String country;
        private String post_code;
        private String phone_number;
        private String venue_email;
        private Object venue_website;
        private String home_delivery_mov;
        private int start_days;
        private int end_days;
        private int collection_time;
        private Object preparation_time;
        private String free_delivery;
        private String delivery_charge;
        private int delivery;
        private int collection;
        private String latitude;
        private String longitude;
        private int delivery_rating;
        private String distance;
        private int product_count;
        private String opening_time;
        private String closing_time;
        private String isClose;
        private String stars;
        private int total_offers;
        private boolean isWishlisted;

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

        public String getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(String venue_images) {
            this.venue_images = venue_images;
        }

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public String getAddress_2() {
            return address_2;
        }

        public void setAddress_2(String address_2) {
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

        public int getCollection_time() {
            return collection_time;
        }

        public void setCollection_time(int collection_time) {
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

        public int getDelivery_rating() {
            return delivery_rating;
        }

        public void setDelivery_rating(int delivery_rating) {
            this.delivery_rating = delivery_rating;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public int getProduct_count() {
            return product_count;
        }

        public void setProduct_count(int product_count) {
            this.product_count = product_count;
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

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getTotal_offers() {
            return total_offers;
        }

        public void setTotal_offers(int total_offers) {
            this.total_offers = total_offers;
        }

        public boolean isIsWishlisted() {
            return isWishlisted;
        }

        public void setIsWishlisted(boolean isWishlisted) {
            this.isWishlisted = isWishlisted;
        }
    }

    public static class SwoopeEatVanuesBean {
        /**
         * id : 27
         * merchant_id : 15
         * venue_id : 2020040808042515
         * venue_name : MAJESTIC WINE
         * venue_images : 20200506070720-Majestic-Wine.jpg
         * address_1 : Wolverhampton, UK
         * address_2 : null
         * city_name : Wolverhampton
         * country : United Kingdom
         * post_code : WV30DS
         * phone_number : 892567165465498
         * venue_email : amazongo@itiomail.com
         * venue_website : null
         * home_delivery_mov : 10.00
         * start_days : 30
         * end_days : 45
         * collection_time : 30
         * preparation_time : 45
         * free_delivery : 20.00
         * delivery_charge : 2.00
         * delivery : 1
         * collection : 1
         * is_booking_allow : 0
         * latitude : 52.586973
         * longitude : -2.12882
         * venue_category : ["Wine","Redwine","Scotch"]
         * delivery_rating : 5
         * distance : 0.87
         * opening_time : 2020-12-22 08:00
         * closing_time : 2020-12-22 22:00
         * isClose : 0
         * stars : 5.0
         * total_offers : 0
         * isWishlisted : false
         */

        private int id;
        private int merchant_id;
        private String venue_id;
        private String venue_name;
        private String venue_images;
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
        private int collection_time;
        private int preparation_time;
        private String free_delivery;
        private String delivery_charge;
        private int delivery;
        private int collection;
        private int is_booking_allow;
        private String latitude;
        private String longitude;
        private int delivery_rating;
        private String distance;
        private String opening_time;
        private String closing_time;
        private String isClose;
        private String stars;
        private int total_offers;
        private boolean isWishlisted;
        private List<String> venue_category;

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

        public String getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(String venue_images) {
            this.venue_images = venue_images;
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

        public int getCollection_time() {
            return collection_time;
        }

        public void setCollection_time(int collection_time) {
            this.collection_time = collection_time;
        }

        public int getPreparation_time() {
            return preparation_time;
        }

        public void setPreparation_time(int preparation_time) {
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

        public int getIs_booking_allow() {
            return is_booking_allow;
        }

        public void setIs_booking_allow(int is_booking_allow) {
            this.is_booking_allow = is_booking_allow;
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

        public int getDelivery_rating() {
            return delivery_rating;
        }

        public void setDelivery_rating(int delivery_rating) {
            this.delivery_rating = delivery_rating;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
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

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }

        public int getTotal_offers() {
            return total_offers;
        }

        public void setTotal_offers(int total_offers) {
            this.total_offers = total_offers;
        }

        public boolean isIsWishlisted() {
            return isWishlisted;
        }

        public void setIsWishlisted(boolean isWishlisted) {
            this.isWishlisted = isWishlisted;
        }

        public List<String> getVenue_category() {
            return venue_category;
        }

        public void setVenue_category(List<String> venue_category) {
            this.venue_category = venue_category;
        }
    }

    public static class TimesBean {
        /**
         * time : 10:00:00
         */

        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }

    public static class ShowTabsBean {
        /**
         * id : 1
         * tab_name : top_banner
         * sort : 1
         * status : 1
         */

        private int id;
        private String tab_name;
        private int sort;
        private int status;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTab_name() {
            return tab_name;
        }

        public void setTab_name(String tab_name) {
            this.tab_name = tab_name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }
    }
}
