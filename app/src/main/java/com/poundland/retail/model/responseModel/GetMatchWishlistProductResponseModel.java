package com.poundland.retail.model.responseModel;

import java.util.List;

public class GetMatchWishlistProductResponseModel {


    /**
     * status : true
     * message : List of match wishlist products.
     * match_products : {"current_page":1,"data":[{"id":41,"product_name":"Flam Black Top","images":"uploaded/products/3361815759651890.png","modifier_id":52,"selling_price":"0.30","buy_price":"0.10","avl_quantity":23,"modifier_images":"","venue_id":"201911011148462","venue_name":"LillyWhites","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg","country":"United Kingdom","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","post_code":"WV3 0DS","latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02"}],"first_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getMatchWishlistProducts?page=1","from":1,"last_page":1,"last_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getMatchWishlistProducts?page=1","next_page_url":null,"path":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getMatchWishlistProducts","per_page":10,"prev_page_url":null,"to":1,"total":1}
     */

    private boolean status;
    private String message;
    private MatchProductsBean match_products;

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

    public MatchProductsBean getMatch_products() {
        return match_products;
    }

    public void setMatch_products(MatchProductsBean match_products) {
        this.match_products = match_products;
    }

    public static class MatchProductsBean {
        /**
         * current_page : 1
         * data : [{"id":41,"product_name":"Flam Black Top","images":"uploaded/products/3361815759651890.png","modifier_id":52,"selling_price":"0.30","buy_price":"0.10","avl_quantity":23,"modifier_images":"","venue_id":"201911011148462","venue_name":"LillyWhites","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg","country":"United Kingdom","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","post_code":"WV3 0DS","latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02"}]
         * first_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getMatchWishlistProducts?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getMatchWishlistProducts?page=1
         * next_page_url : null
         * path : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getMatchWishlistProducts
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
             * id : 41
             * product_name : Flam Black Top
             * images : uploaded/products/3361815759651890.png
             * modifier_id : 52
             * selling_price : 0.30
             * buy_price : 0.10
             * avl_quantity : 23
             * modifier_images :
             * venue_id : 201911011148462
             * venue_name : LillyWhites
             * venue_images : 1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg
             * country : United Kingdom
             * address_1 : Oaklands Road, Wolverhampton WV3 0DS, England, UK
             * address_2 : null
             * city_name : Wolverhampton
             * post_code : WV3 0DS
             * latitude : 52.5756465
             * longitude : -2.1385980000000018
             * distance : 0.02
             */
            private int id;
            private String product_name;
            private String images;
            private int modifier_id;
            private String selling_price;
            private String buy_price;
            private int avl_quantity;
            private int product_type;
            private String modifier_images;
            private String venue_id;
            private String venue_name;
            private String venue_images;
            private String country;
            private String address_1;
            private Object address_2;
            private String city_name;
            private String post_code;
            private String latitude;
            private String longitude;
            private String distance;

            public int getProduct_type() {
                return product_type;
            }

            public void setProduct_type(int product_type) {
                this.product_type = product_type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getProduct_name() {
                return product_name;
            }

            public void setProduct_name(String product_name) {
                this.product_name = product_name;
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

            public String getSelling_price() {
                return selling_price;
            }

            public void setSelling_price(String selling_price) {
                this.selling_price = selling_price;
            }

            public String getBuy_price() {
                return buy_price;
            }

            public void setBuy_price(String buy_price) {
                this.buy_price = buy_price;
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

            public String getCountry() {
                return country;
            }

            public void setCountry(String country) {
                this.country = country;
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

            public String getPost_code() {
                return post_code;
            }

            public void setPost_code(String post_code) {
                this.post_code = post_code;
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
        }
    }
}
