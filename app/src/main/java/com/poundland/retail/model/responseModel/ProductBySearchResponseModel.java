package com.poundland.retail.model.responseModel;

import java.util.List;

public class ProductBySearchResponseModel {


    /**
     * status : true
     * message : List Of product Details By Category
     * category : [{"id":15,"cat_name":"Fashion","parent_cat_id":0,"breadcrumb":"0","color":"e66465","menu_level":1},{"id":16,"cat_name":"Techy","parent_cat_id":0,"breadcrumb":"0","color":"e66465","menu_level":1},{"id":17,"cat_name":"Western Fashion","parent_cat_id":15,"breadcrumb":"15","color":"e66465","menu_level":2}]
     * venues : [{"venue_id":"2019081207002912","merchant_id":"12","venue_name":"Norwich Art Center","venue_images":"1565593229.eastnor-castle.jpg","address_1":"Wake Green Road, Birmingham, England, UK","address_2":null,"city_name":"Birmingham","country":"United Kingdom","post_code":"B139PG","home_delivery_mov":"11.00","delivery":1,"collection":0,"opening_time":null,"closing_time":null,"distance":"0.46","stars":"0","total_offers":0}]
     * products : [{"id":7,"merchant_id":12,"product_name":"Shirt","product_description":null,"category_id":"16,15,17","brand_id":null,"images":null,"buy_price":"100.00","selling_price":"1000.00","modifier_id":11,"avl_quantity":10,"modifier_images":null,"venue_id":"2019081207002912","venue_name":"Norwich Art Center","venue_images":"20190829105557.XScentric Logo.png","address_1":"Wake Green Road, Birmingham, England, UK","address_2":null,"city_name":"Birmingham","country":"United Kingdom","post_code":"B139PG","delivery":1,"collection":0,"latitude":"52.44009920000001","longitude":"-1.865403600000036","distance":"0.46","stars":"0"}]
     * isfilterData : 0
     * products_offer : []
     * countProduct : 1
     */

    private boolean status;
    private String message;
    private int isfilterData;
    private int countProduct;
    private int totalVenue;
    private List<CategoryBean> category;
    private List<VenuesBean> venues;
    private List<ProductsBean> products;
    private List<ProductOfferBean> products_offer;
    private List<BannersBean> banners;

    public int getTotalVenue() {
        return totalVenue;
    }

    public void setTotalVenue(int totalVenue) {
        this.totalVenue = totalVenue;
    }

    public List<BannersBean> getBanners() {
        return banners;
    }

    public void setBanners(List<BannersBean> banners) {
        this.banners = banners;
    }

    public List<ProductOfferBean> getProducts_offer() {
        return products_offer;
    }

    public void setProducts_offer(List<ProductOfferBean> products_offer) {
        this.products_offer = products_offer;
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

    public int getIsfilterData() {
        return isfilterData;
    }

    public void setIsfilterData(int isfilterData) {
        this.isfilterData = isfilterData;
    }

    public int getCountProduct() {
        return countProduct;
    }

    public void setCountProduct(int countProduct) {
        this.countProduct = countProduct;
    }

    public List<CategoryBean> getCategory() {
        return category;
    }

    public void setCategory(List<CategoryBean> category) {
        this.category = category;
    }

    public List<VenuesBean> getVenues() {
        return venues;
    }

    public void setVenues(List<VenuesBean> venues) {
        this.venues = venues;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }



    public static class CategoryBean {
        /**
         * id : 15
         * cat_name : Fashion
         * parent_cat_id : 0
         * breadcrumb : 0
         * color : e66465
         * menu_level : 1
         */

        private int id;
        private String cat_name;
        private int parent_cat_id;
        private String breadcrumb;
        private String color;
        private String image;
        private int menu_level;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

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

        public String getBreadcrumb() {
            return breadcrumb;
        }

        public void setBreadcrumb(String breadcrumb) {
            this.breadcrumb = breadcrumb;
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
    }

    public static class VenuesBean {
        /**
         * venue_id : 2019081207002912
         * merchant_id : 12
         * venue_name : Norwich Art Center
         * venue_images : 1565593229.eastnor-castle.jpg
         * address_1 : Wake Green Road, Birmingham, England, UK
         * address_2 : null
         * city_name : Birmingham
         * country : United Kingdom
         * post_code : B139PG
         * home_delivery_mov : 11.00
         * delivery : 1
         * collection : 0
         * opening_time : null
         * closing_time : null
         * distance : 0.46
         * stars : 0
         * total_offers : 0
         */

        private String venue_id;
        private String merchant_id;
        private String venue_name;
        private String venue_images;
        private String address_1;
        private String address_2;
        private String city_name;
        private String country;
        private String post_code;
        private String home_delivery_mov;
        private int delivery;
        private int collection;
        private String opening_time;
        private String isClose;
        private String closing_time;
        private String distance;
        private String stars;
        private int total_offers;
        private boolean isWishlisted;

        public boolean isWishlisted() {
            return isWishlisted;
        }

        public void setWishlisted(boolean wishlisted) {
            isWishlisted = wishlisted;
        }

        public String getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(String venue_id) {
            this.venue_id = venue_id;
        }

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
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

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
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

        public String getIsClose() {
            return isClose;
        }

        public void setIsClose(String isClose) {
            this.isClose = isClose;
        }
    }

    public static class ProductsBean {
        /**
         * id : 7
         * merchant_id : 12
         * product_name : Shirt
         * product_description : null
         * category_id : 16,15,17
         * brand_id : null
         * images : null
         * buy_price : 100.00
         * selling_price : 1000.00
         * modifier_id : 11
         * avl_quantity : 10
         * modifier_images : null
         * venue_id : 2019081207002912
         * venue_name : Norwich Art Center
         * venue_images : 20190829105557.XScentric Logo.png
         * address_1 : Wake Green Road, Birmingham, England, UK
         * address_2 : null
         * city_name : Birmingham
         * country : United Kingdom
         * post_code : B139PG
         * delivery : 1
         * collection : 0
         * latitude : 52.44009920000001
         * longitude : -1.865403600000036
         * distance : 0.46
         * stars : 0
         */

        private int id;
        private int merchant_id;
        private String product_name;
        private String product_description;
        private String category_id;
        private String brand_id;
        private String images;
        private String buy_price;
        private String selling_price;
        private int modifier_id;
        private int avl_quantity;
        private String modifier_images;
        private String venue_id;
        private String venue_name;
        private String venue_images;
        private String address_1;
        private String address_2;
        private String city_name;
        private String country;
        private String post_code;
        private int delivery;
        private int collection;
        private String latitude;
        private String longitude;
        private String distance;
        private String stars;
        private int sameDayShipping;
        private int quantity_per_case;
        private String bulk_selling_price;

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

        public int getSameDayShipping() {
            return sameDayShipping;
        }

        public void setSameDayShipping(int sameDayShipping) {
            this.sameDayShipping = sameDayShipping;
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

        public String getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(String brand_id) {
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

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }
    }

    public static class ProductOfferBean {
        /**
         * offer_id : 1
         * offer_type : discper
         * disc_per : 10.00
         * disc_amt : 0.00
         * offer_title : 10% Off
         * start_date : 2019-10-31
         * end_date : 2019-11-15
         * offer_time_start : 00:05:00
         * offer_time_end : 23:55:00
         * type : 1
         * discount_type : 1
         * discount_amount : 10.00
         * product_id : 1
         * merchant_id : 1
         * venue_id : 201910311251401
         * product_name : Moto G
         * product_description :  3GB RAM, 32GB internal memory expandable purchase</span></li></ul>
         * product_images : uploaded/products/3295015725270480.png
         * modifier_id : 1
         * buy_price : 80.00
         * selling_price : 100.00
         * avl_quantity : 99
         * modifier_images : null
         * venue_name : Nando wolverhampton
         * distance : 0.02
         * stars : 0
         */
        private int offer_id;
        private String offer_type;
        private String disc_per;
        private String disc_amt;
        private String offer_title;
        private String start_date;
        private String end_date;
        private String offer_time_start;
        private String offer_time_end;
        private int type;
        private int discount_type;
        private String discount_amount;
        private int product_id;
        private int merchant_id;
        private String venue_id;
        private String product_name;
        private String product_description;
        private String product_images;
        private int modifier_id;
        private String buy_price;
        private String selling_price;
        private String new_price;
        private int avl_quantity;
        private String modifier_images;
        private String venue_name;
        private String distance;
        private String stars;
        private boolean isWishlisted;

        private int sameDayShipping;
        private int quantity_per_case;
        private String bulk_selling_price;

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

        public int getSameDayShipping() {
            return sameDayShipping;
        }

        public void setSameDayShipping(int sameDayShipping) {
            this.sameDayShipping = sameDayShipping;
        }



        public boolean isWishlisted() {
            return isWishlisted;
        }

        public void setWishlisted(boolean wishlisted) {
            isWishlisted = wishlisted;
        }

        public String getNew_price() {
            return new_price;
        }

        public void setNew_price(String new_price) {
            this.new_price = new_price;
        }

        public int getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(int offer_id) {
            this.offer_id = offer_id;
        }

        public String getOffer_type() {
            return offer_type;
        }

        public void setOffer_type(String offer_type) {
            this.offer_type = offer_type;
        }

        public String getDisc_per() {
            return disc_per;
        }

        public void setDisc_per(String disc_per) {
            this.disc_per = disc_per;
        }

        public String getDisc_amt() {
            return disc_amt;
        }

        public void setDisc_amt(String disc_amt) {
            this.disc_amt = disc_amt;
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

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
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

        public String getProduct_images() {
            return product_images;
        }

        public void setProduct_images(String product_images) {
            this.product_images = product_images;
        }

        public int getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(int modifier_id) {
            this.modifier_id = modifier_id;
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

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getStars() {
            return stars;
        }

        public void setStars(String stars) {
            this.stars = stars;
        }
    }
    public static class BannersBean {
        /**
         * id : 5
         * merchant_id : 2
         * venue_id : 201911011148462
         * app_banner : uploaded/banner/appbanner/8207815802873770.png
         * banner_placement_main : 0
         * category : 2
         * start_time : 00:00:00
         * end_time : 23:55:00
         * appCatUrl :
         * appProductUrl : 158
         * gender_for_show : Both
         * catName : null
         */

        private int id;
        private int merchant_id;
        private String venue_id;
        private String venue_name;
        private String app_banner;
        private int banner_placement_main;
        private int category;
        private int app_banner_for;
        private String start_time;
        private String end_time;
        private String appCatUrl;
        private String appProductUrl;
        private String gender_for_show;
        private String catName;
        private String product_categories_id;

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
        }

        public int getApp_banner_for() {
            return app_banner_for;
        }

        public void setApp_banner_for(int app_banner_for) {
            this.app_banner_for = app_banner_for;
        }

        public String getProduct_categories_id() {
            return product_categories_id;
        }

        public void setProduct_categories_id(String product_categories_id) {
            this.product_categories_id = product_categories_id;
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

        public String getApp_banner() {
            return app_banner;
        }

        public void setApp_banner(String app_banner) {
            this.app_banner = app_banner;
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

        public String getAppCatUrl() {
            return appCatUrl;
        }

        public void setAppCatUrl(String appCatUrl) {
            this.appCatUrl = appCatUrl;
        }

        public String getAppProductUrl() {
            return appProductUrl;
        }

        public void setAppProductUrl(String appProductUrl) {
            this.appProductUrl = appProductUrl;
        }

        public String getGender_for_show() {
            return gender_for_show;
        }

        public void setGender_for_show(String gender_for_show) {
            this.gender_for_show = gender_for_show;
        }

        public String getCatName() {
            return catName;
        }

        public void setCatName(String catName) {
            this.catName = catName;
        }
    }
}
