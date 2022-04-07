package com.poundland.retail.model.responseModel;

import java.util.List;

public class SingleVenueDetailResponseModel {

    private ProductsBean products;
    private boolean status;
    private String message;

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
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

    public static class ProductsBean {
        /**
         * current_page : 1
         * data : [{"id":1815,"merchant_id":15,"product_name":"Women Blue & White Self Design Lightweight Sweater","product_description":"<div><h4 class=\"pdp-product-description-title\">PRODUCT DETAILS&nbsp;<\/h4><p class=\"pdp-product-description-content\">Pink solid knitted A-line dress, has a round neck, short sleeves, and flounce hem<\/p><\/div><div class=\"pdp-sizeFitDesc\"><h4 class=\"pdp-sizeFitDescTitle pdp-product-description-title\">Size &amp; Fit<\/h4><p class=\"pdp-sizeFitDescContent pdp-product-description-content\">Regular Fit<br>The model (height 5'8\") is wearing a size S<\/p><\/div><div class=\"pdp-sizeFitDesc\"><h4 class=\"pdp-sizeFitDescTitle pdp-product-description-title\">Material &amp; Care<\/h4><p class=\"pdp-sizeFitDescContent pdp-product-description-content\">97% Polyester and 3% elastane<br>Machine-wash<\/p><\/div>","category_id":"377","brand_id":62,"images":"uploaded/products/9119916038868160.jpeg","buy_price":"1.00","selling_price":"10.00","quantity_per_case":1,"bulk_selling_price":"0.00","modifier_id":2349,"modifier_name":null,"avl_quantity":49970,"weight":"0","modifier_images":null,"mod_count":1,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_distance":0,"distance":"4,088.75","offer_id":null,"offer_title":null,"start_date":null,"end_date":null,"offer_time_start":null,"offer_time_end":null,"type":null,"discount_type":null,"discount_amount":null,"new_price":"10.00","productRatingCount":1,"productRating":"5.0","images2":null,"modifier_images2":null,"isLiked":0,"isCart":0,"sameDayShipping":1},{"id":1861,"merchant_id":15,"product_name":"Women Black Printed Fit and Flare Dress","product_description":"PRODUCT DETAILS \nMauve and pink printed woven A-line net dress, has shoulder straps, sleeveless, concealed zip closure, an attached lining, flared hem\n\nSize & Fit\nRegular Fit\nThe model (height 5'8\") is wearing a size S\n\nMaterial & Care\nPolyester\nMachine-wash","category_id":"377","brand_id":45,"images":"uploaded/products/5192016040356002.jpeg","buy_price":"2.00","selling_price":"25.00","quantity_per_case":1,"bulk_selling_price":"0.00","modifier_id":2400,"modifier_name":"black,L","avl_quantity":197,"weight":"0","modifier_images":"uploaded/products/5192016040356002.jpeg","mod_count":1,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_distance":0,"distance":"4,088.75","offer_id":null,"offer_title":null,"start_date":null,"end_date":null,"offer_time_start":null,"offer_time_end":null,"type":null,"discount_type":null,"discount_amount":null,"new_price":"25.00","productRatingCount":0,"productRating":"0","images2":"uploaded/products/2981916040356001.jpeg","modifier_images2":"uploaded/products/2981916040356001.jpeg","isLiked":0,"isCart":0,"sameDayShipping":1},{"id":1862,"merchant_id":15,"product_name":"Women Self Design Sheath Dress","product_description":"<p>Navy blue self-design knitted sheath dress, has off-shoulder styling, short sleeves, straight hem<\/p>","category_id":"377","brand_id":45,"images":"uploaded/products/9722216044997670.jpeg","buy_price":"2.00","selling_price":"25.00","quantity_per_case":1,"bulk_selling_price":"32.00","modifier_id":2402,"modifier_name":"red,m","avl_quantity":187,"weight":"0","modifier_images":"uploaded/products/5953216040368931.jpeg","mod_count":1,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_distance":0,"distance":"4,088.75","offer_id":null,"offer_title":null,"start_date":null,"end_date":null,"offer_time_start":null,"offer_time_end":null,"type":null,"discount_type":null,"discount_amount":null,"new_price":"25.00","productRatingCount":1,"productRating":"5.0","images2":"uploaded/products/9341116044997671.jpeg","modifier_images2":"uploaded/products/2114016040368930.jpeg","isLiked":1,"isCart":0,"sameDayShipping":1},{"id":1908,"merchant_id":15,"product_name":"White color shirt","product_description":"<p>tst test<\/p>","category_id":"380","brand_id":null,"images":"uploaded/products/7749416040760240.jpeg","buy_price":"0.00","selling_price":"0.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":2450,"modifier_name":null,"avl_quantity":102,"weight":"0","modifier_images":null,"mod_count":1,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_distance":0,"distance":"4,088.75","offer_id":null,"offer_title":null,"start_date":null,"end_date":null,"offer_time_start":null,"offer_time_end":null,"type":null,"discount_type":null,"discount_amount":null,"new_price":"0.00","productRatingCount":0,"productRating":"0","images2":null,"modifier_images2":null,"isLiked":0,"isCart":0,"sameDayShipping":1},{"id":1909,"merchant_id":15,"product_name":"Black Bases Colorful Thsirt","product_description":"<p>test test<\/p>","category_id":"391","brand_id":null,"images":"uploaded/products/6067416040762290.jpeg","buy_price":"20.00","selling_price":"25.00","quantity_per_case":10,"bulk_selling_price":"0.00","modifier_id":2451,"modifier_name":null,"avl_quantity":93,"weight":"0","modifier_images":null,"mod_count":1,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_distance":0,"distance":"4,088.75","offer_id":null,"offer_title":null,"start_date":null,"end_date":null,"offer_time_start":null,"offer_time_end":null,"type":null,"discount_type":null,"discount_amount":null,"new_price":"25.00","productRatingCount":1,"productRating":"3.5","images2":null,"modifier_images2":null,"isLiked":1,"isCart":0,"sameDayShipping":1},{"id":1979,"merchant_id":15,"product_name":"Women Solid Round Neck T-shirt","product_description":"<div><h4 class=\"pdp-product-description-title\">PRODUCT DETAILS&nbsp;<\/h4><p class=\"pdp-product-description-content\">Black solid T-shirt, has a round neck, and long sleeves<\/p><\/div><div class=\"pdp-sizeFitDesc\"><h4 class=\"pdp-sizeFitDescTitle pdp-product-description-title\">Size &amp; Fit<\/h4><p class=\"pdp-sizeFitDescContent pdp-product-description-content\">The model (height 5'8\") is wearing a size S<\/p><\/div><div class=\"pdp-sizeFitDesc\"><h4 class=\"pdp-sizeFitDescTitle pdp-product-description-title\">Material &amp; Care<\/h4><p class=\"pdp-sizeFitDescContent pdp-product-description-content\">Material: 100% cotton<br>Machine Wash<\/p><\/div>","category_id":"377","brand_id":62,"images":"uploaded/products/6165116045761450.jpeg","buy_price":"1.00","selling_price":"15.00","quantity_per_case":1,"bulk_selling_price":"21.00","modifier_id":2523,"modifier_name":"off white,L","avl_quantity":89,"weight":"0","modifier_images":"uploaded/modifierImages/9072716045574320.jpeg","mod_count":4,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_distance":0,"distance":"4,088.75","offer_id":null,"offer_title":null,"start_date":null,"end_date":null,"offer_time_start":null,"offer_time_end":null,"type":null,"discount_type":null,"discount_amount":null,"new_price":"15.00","productRatingCount":4,"productRating":"4.5","images2":null,"modifier_images2":null,"isLiked":1,"isCart":0,"sameDayShipping":1},{"id":1907,"merchant_id":15,"product_name":"Platinum Jeans","product_description":"<p>test product&nbsp;<\/p>","category_id":"374","brand_id":null,"images":"uploaded/products/8084016040757850.jpeg","buy_price":"10.00","selling_price":"15.00","quantity_per_case":10,"bulk_selling_price":"12.00","modifier_id":2449,"modifier_name":null,"avl_quantity":83,"weight":"0","modifier_images":null,"mod_count":1,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_distance":0,"distance":"4,088.75","offer_id":null,"offer_title":null,"start_date":null,"end_date":null,"offer_time_start":null,"offer_time_end":null,"type":null,"discount_type":null,"discount_amount":null,"new_price":"15.00","productRatingCount":0,"productRating":"0","images2":null,"modifier_images2":null,"isLiked":0,"isCart":0,"sameDayShipping":1},{"id":1606,"merchant_id":15,"product_name":"Men Green Slim Fit Printed Round Neck T-shirt","product_description":"<p><br data-mce-bogus=\"1\"><\/p>","category_id":"375","brand_id":72,"images":"uploaded/products/8155716032704940.png","buy_price":"2.00","selling_price":"15.00","quantity_per_case":5,"bulk_selling_price":"20.00","modifier_id":2137,"modifier_name":null,"avl_quantity":71,"weight":"0","modifier_images":null,"mod_count":1,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_distance":0,"distance":"4,088.75","offer_id":null,"offer_title":null,"start_date":null,"end_date":null,"offer_time_start":null,"offer_time_end":null,"type":null,"discount_type":null,"discount_amount":null,"new_price":"15.00","productRatingCount":1,"productRating":"5.0","images2":"uploaded/products/2679816032704941.png","modifier_images2":null,"isLiked":0,"isCart":0,"sameDayShipping":1},{"id":1995,"merchant_id":15,"product_name":"ultima cloth1798","product_description":"<p>Qualitybproduct testing data data<\/p> description","category_id":"420","brand_id":89,"images":"uploaded/products/7038416041250221.jpeg","buy_price":"1.00","selling_price":"15.00","quantity_per_case":1,"bulk_selling_price":"5.00","modifier_id":2552,"modifier_name":"green,xl","avl_quantity":55,"weight":"0","modifier_images":"uploaded/products/7038416041250221.jpeg","mod_count":1,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_distance":0,"distance":"4,088.75","offer_id":null,"offer_title":null,"start_date":null,"end_date":null,"offer_time_start":null,"offer_time_end":null,"type":null,"discount_type":null,"discount_amount":null,"new_price":"15.00","productRatingCount":0,"productRating":"0","images2":"uploaded/products/7038416041250221.jpeg","modifier_images2":"uploaded/products/7038416041250221.jpeg","isLiked":0,"isCart":0,"sameDayShipping":1},{"id":1994,"merchant_id":15,"product_name":"ultima cloth1787","product_description":"<p>Qualitybproduct testing data <\/p>","category_id":"374","brand_id":89,"images":"uploaded/products/4852216041250220.jpeg","buy_price":"1.00","selling_price":"15.00","quantity_per_case":1,"bulk_selling_price":"5.00","modifier_id":2551,"modifier_name":"green,xl","avl_quantity":50,"weight":"0","modifier_images":"uploaded/products/4852216041250220.jpeg","mod_count":1,"venue_id":"2020102010172215","venue_name":"Next","venue_images":"1603185442-500.jpg","address_1":"Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK","address_2":null,"city_name":"West Midlands","country":"United Kingdom","post_code":"WV1 1LE","delivery":1,"collection":1,"latitude":"52.5875483","longitude":"-2.120122100000001","delivery_distance":0,"distance":"4,088.75","offer_id":null,"offer_title":null,"start_date":null,"end_date":null,"offer_time_start":null,"offer_time_end":null,"type":null,"discount_type":null,"discount_amount":null,"new_price":"15.00","productRatingCount":0,"productRating":"0","images2":"uploaded/products/9513616041250224.png","modifier_images2":"uploaded/products/9513616041250224.png","isLiked":0,"isCart":0,"sameDayShipping":1}]
         * first_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getVenueDetails?page=1
         * from : 1
         * last_page : 2
         * last_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getVenueDetails?page=2
         * next_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getVenueDetails?page=2
         * path : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getVenueDetails
         * per_page : 10
         * prev_page_url : null
         * to : 10
         * total : 19
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private String next_page_url;
        private String path;
        private int per_page;
        private Object prev_page_url;
        private int to;
        private int total;
        private List<ProductsBean.DataBean> data;

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

        public String getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(String next_page_url) {
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

        public List<ProductsBean.DataBean> getData() {
            return data;
        }

        public void setData(List<ProductsBean.DataBean> data) {
            this.data = data;
        }

        public static class DataBean {
            /**
             * id : 1815
             * merchant_id : 15
             * product_name : Women Blue & White Self Design Lightweight Sweater
             * product_description : <div><h4 class="pdp-product-description-title">PRODUCT DETAILS&nbsp;</h4><p class="pdp-product-description-content">Pink solid knitted A-line dress, has a round neck, short sleeves, and flounce hem</p></div><div class="pdp-sizeFitDesc"><h4 class="pdp-sizeFitDescTitle pdp-product-description-title">Size &amp; Fit</h4><p class="pdp-sizeFitDescContent pdp-product-description-content">Regular Fit<br>The model (height 5'8") is wearing a size S</p></div><div class="pdp-sizeFitDesc"><h4 class="pdp-sizeFitDescTitle pdp-product-description-title">Material &amp; Care</h4><p class="pdp-sizeFitDescContent pdp-product-description-content">97% Polyester and 3% elastane<br>Machine-wash</p></div>
             * category_id : 377
             * brand_id : 62
             * images : uploaded/products/9119916038868160.jpeg
             * buy_price : 1.00
             * selling_price : 10.00
             * quantity_per_case : 1
             * bulk_selling_price : 0.00
             * modifier_id : 2349
             * modifier_name : null
             * avl_quantity : 49970
             * weight : 0
             * modifier_images : null
             * mod_count : 1
             * venue_id : 2020102010172215
             * venue_name : Next
             * venue_images : 1603185442-500.jpg
             * address_1 : Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK
             * address_2 : null
             * city_name : West Midlands
             * country : United Kingdom
             * post_code : WV1 1LE
             * delivery : 1
             * collection : 1
             * latitude : 52.5875483
             * longitude : -2.120122100000001
             * delivery_distance : 0
             * distance : 4,088.75
             * offer_id : null
             * offer_title : null
             * start_date : null
             * end_date : null
             * offer_time_start : null
             * offer_time_end : null
             * type : null
             * discount_type : null
             * discount_amount : null
             * new_price : 10.00
             * productRatingCount : 1
             * productRating : 5.0
             * images2 : null
             * modifier_images2 : null
             * isLiked : 0
             * isCart : 0
             * sameDayShipping : 1
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
            private Object modifier_name;
            private int avl_quantity;
            private String weight;
            private Object modifier_images;
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
            private String distance;
            private String stars;
            private int offer_id;
            private Object offer_title;
            private Object start_date;
            private Object end_date;
            private Object offer_time_start;
            private Object offer_time_end;
            private Object type;
            private Object discount_type;
            private Object discount_amount;
            private String new_price;
            private int productRatingCount;
            private String productRating;
            private Object images2;
            private Object modifier_images2;
            private int isLiked;
            private int isCart;
            private int sameDayShipping;
            private CartBean cart;

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                stars = stars;
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

            public Object getModifier_name() {
                return modifier_name;
            }

            public void setModifier_name(Object modifier_name) {
                this.modifier_name = modifier_name;
            }

            public int getAvl_quantity() {
                return avl_quantity;
            }

            public void setAvl_quantity(int avl_quantity) {
                this.avl_quantity = avl_quantity;
            }

            public String getWeight() {
                return weight;
            }

            public void setWeight(String weight) {
                this.weight = weight;
            }

            public Object getModifier_images() {
                return modifier_images;
            }

            public void setModifier_images(Object modifier_images) {
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

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public int getOffer_id() {
                return offer_id;
            }

            public void setOffer_id(int offer_id) {
                this.offer_id = offer_id;
            }

            public Object getOffer_title() {
                return offer_title;
            }

            public void setOffer_title(Object offer_title) {
                this.offer_title = offer_title;
            }

            public Object getStart_date() {
                return start_date;
            }

            public void setStart_date(Object start_date) {
                this.start_date = start_date;
            }

            public Object getEnd_date() {
                return end_date;
            }

            public void setEnd_date(Object end_date) {
                this.end_date = end_date;
            }

            public Object getOffer_time_start() {
                return offer_time_start;
            }

            public void setOffer_time_start(Object offer_time_start) {
                this.offer_time_start = offer_time_start;
            }

            public Object getOffer_time_end() {
                return offer_time_end;
            }

            public void setOffer_time_end(Object offer_time_end) {
                this.offer_time_end = offer_time_end;
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

            public int getProductRatingCount() {
                return productRatingCount;
            }

            public void setProductRatingCount(int productRatingCount) {
                this.productRatingCount = productRatingCount;
            }

            public String getProductRating() {
                return productRating;
            }

            public void setProductRating(String productRating) {
                this.productRating = productRating;
            }

            public Object getImages2() {
                return images2;
            }

            public void setImages2(Object images2) {
                this.images2 = images2;
            }

            public Object getModifier_images2() {
                return modifier_images2;
            }

            public void setModifier_images2(Object modifier_images2) {
                this.modifier_images2 = modifier_images2;
            }

            public int getIsLiked() {
                return isLiked;
            }

            public void setIsLiked(int isLiked) {
                this.isLiked = isLiked;
            }

            public int getIsCart() {
                return isCart;
            }

            public void setIsCart(int isCart) {
                this.isCart = isCart;
            }

            public int getSameDayShipping() {
                return sameDayShipping;
            }

            public void setSameDayShipping(int sameDayShipping) {
                this.sameDayShipping = sameDayShipping;
            }
            public CartBean getCart() {
                return cart;
            }

            public void setCart(CartBean cart) {
                this.cart = cart;
            }
            public static class CartBean {
                /**
                 * quantities : 1
                 * bulk_quantities : 0
                 * total_carts : 1
                 * isCart : 1
                 */

                private int quantities;
                private int bulk_quantities;
                private int total_carts;
                private int isCart;

                public int getQuantities() {
                    return quantities;
                }

                public void setQuantities(int quantities) {
                    this.quantities = quantities;
                }

                public int getBulk_quantities() {
                    return bulk_quantities;
                }

                public void setBulk_quantities(int bulk_quantities) {
                    this.bulk_quantities = bulk_quantities;
                }

                public int getTotal_carts() {
                    return total_carts;
                }

                public void setTotal_carts(int total_carts) {
                    this.total_carts = total_carts;
                }

                public int getIsCart() {
                    return isCart;
                }

                public void setIsCart(int isCart) {
                    this.isCart = isCart;
                }
            }
        }

    }
}
