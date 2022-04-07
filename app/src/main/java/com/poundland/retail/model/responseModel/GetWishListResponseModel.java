package com.poundland.retail.model.responseModel;

import java.util.List;

public class GetWishListResponseModel {

    /**
     * status : true
     * message : List of Wishlist.
     * likes : {"current_page":1,"data":[{"id":72,"guest":null,"customer_id":9,"product_id":41,"modifier_id":52,"offer_id":2,"ip_address":null,"venue_id":"201911011148462","merchant_id":2,"created_at":"2019-12-11 17:21:14","updated_at":"2019-12-11 17:21:14","product_name":"Flam Black Top","images":"uploaded/products/3361815759651890.png","brand_id":8,"modifier_name":"Flamboyant Pink Top","selling_price":"0.30","avl_quantity":23,"modifier_images":"","venue_name":"LillyWhites","latitude":"52.5756465","longitude":"-2.1385980000000018","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","delivery_distance":0,"distance":"0.02","offer_title":"10%Off","offer_type":"discper","discount_amount":"10.00","discount_type":1,"disc_detail_type":1,"new_price":"0.27","brand_name":"Wow","match_count":1,"min_price":"0.30","max_price":"0.30"}],"first_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getWishlists?page=1","from":1,"last_page":1,"last_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getWishlists?page=1","next_page_url":null,"path":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getWishlists","per_page":10,"prev_page_url":null,"to":1,"total":1}
     */

    private boolean status;
    private String message;
    private LikesBean likes;

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

    public LikesBean getLikes() {
        return likes;
    }

    public void setLikes(LikesBean likes) {
        this.likes = likes;
    }

    public static class LikesBean {
        /**
         * current_page : 1
         * data : [{"id":72,"guest":null,"customer_id":9,"product_id":41,"modifier_id":52,"offer_id":2,"ip_address":null,"venue_id":"201911011148462","merchant_id":2,"created_at":"2019-12-11 17:21:14","updated_at":"2019-12-11 17:21:14","product_name":"Flam Black Top","images":"uploaded/products/3361815759651890.png","brand_id":8,"modifier_name":"Flamboyant Pink Top","selling_price":"0.30","avl_quantity":23,"modifier_images":"","venue_name":"LillyWhites","latitude":"52.5756465","longitude":"-2.1385980000000018","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","delivery_distance":0,"distance":"0.02","offer_title":"10%Off","offer_type":"discper","discount_amount":"10.00","discount_type":1,"disc_detail_type":1,"new_price":"0.27","brand_name":"Wow","match_count":1,"min_price":"0.30","max_price":"0.30"}]
         * first_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getWishlists?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getWishlists?page=1
         * next_page_url : null
         * path : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getWishlists
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
             * id : 72
             * guest : null
             * customer_id : 9
             * product_id : 41
             * modifier_id : 52
             * offer_id : 2
             * ip_address : null
             * venue_id : 201911011148462
             * merchant_id : 2
             * created_at : 2019-12-11 17:21:14
             * updated_at : 2019-12-11 17:21:14
             * product_name : Flam Black Top
             * images : uploaded/products/3361815759651890.png
             * brand_id : 8
             * modifier_name : Flamboyant Pink Top
             * selling_price : 0.30
             * avl_quantity : 23
             * modifier_images :
             * venue_name : LillyWhites
             * latitude : 52.5756465
             * longitude : -2.1385980000000018
             * address_1 : Oaklands Road, Wolverhampton WV3 0DS, England, UK
             * delivery_distance : 0
             * distance : 0.02
             * offer_title : 10%Off
             * offer_type : discper
             * discount_amount : 10.00
             * discount_type : 1
             * disc_detail_type : 1
             * new_price : 0.27
             * brand_name : Wow
             * match_count : 1
             * min_price : 0.30
             * max_price : 0.30
             */
            private int id;
            private Object guest;
            private int customer_id;
            private int product_id;
            private int modifier_id;
            private int offer_id;
            private Object ip_address;
            private String venue_id;
            private int merchant_id;
            private int product_type;
            private String created_at;
            private String updated_at;
            private String product_name;
            private String images;
            private int brand_id;
            private String modifier_name;
            private String selling_price;
            private int avl_quantity;
            private String modifier_images;
            private String venue_name;
            private String latitude;
            private String longitude;
            private String address_1;
            private int delivery_distance;
            private String distance;
            private String offer_title;
            private String offer_type;
            private String discount_amount;
            private int discount_type;
            private int disc_detail_type;
            private String new_price;
            private String brand_name;
            private int match_count;
            private String min_price;
            private String max_price;

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

            public Object getGuest() {
                return guest;
            }

            public void setGuest(Object guest) {
                this.guest = guest;
            }

            public int getCustomer_id() {
                return customer_id;
            }

            public void setCustomer_id(int customer_id) {
                this.customer_id = customer_id;
            }

            public int getProduct_id() {
                return product_id;
            }

            public void setProduct_id(int product_id) {
                this.product_id = product_id;
            }

            public int getModifier_id() {
                return modifier_id;
            }

            public void setModifier_id(int modifier_id) {
                this.modifier_id = modifier_id;
            }

            public int getOffer_id() {
                return offer_id;
            }

            public void setOffer_id(int offer_id) {
                this.offer_id = offer_id;
            }

            public Object getIp_address() {
                return ip_address;
            }

            public void setIp_address(Object ip_address) {
                this.ip_address = ip_address;
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

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public String getModifier_name() {
                return modifier_name;
            }

            public void setModifier_name(String modifier_name) {
                this.modifier_name = modifier_name;
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

            public String getAddress_1() {
                return address_1;
            }

            public void setAddress_1(String address_1) {
                this.address_1 = address_1;
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

            public String getDiscount_amount() {
                return discount_amount;
            }

            public void setDiscount_amount(String discount_amount) {
                this.discount_amount = discount_amount;
            }

            public int getDiscount_type() {
                return discount_type;
            }

            public void setDiscount_type(int discount_type) {
                this.discount_type = discount_type;
            }

            public int getDisc_detail_type() {
                return disc_detail_type;
            }

            public void setDisc_detail_type(int disc_detail_type) {
                this.disc_detail_type = disc_detail_type;
            }

            public String getNew_price() {
                return new_price;
            }

            public void setNew_price(String new_price) {
                this.new_price = new_price;
            }

            public String getBrand_name() {
                return brand_name;
            }

            public void setBrand_name(String brand_name) {
                this.brand_name = brand_name;
            }

            public int getMatch_count() {
                return match_count;
            }

            public void setMatch_count(int match_count) {
                this.match_count = match_count;
            }

            public String getMin_price() {
                return min_price;
            }

            public void setMin_price(String min_price) {
                this.min_price = min_price;
            }

            public String getMax_price() {
                return max_price;
            }

            public void setMax_price(String max_price) {
                this.max_price = max_price;
            }
        }
    }
}
