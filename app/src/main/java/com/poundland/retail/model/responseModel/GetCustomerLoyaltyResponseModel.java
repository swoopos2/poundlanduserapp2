package com.poundland.retail.model.responseModel;

import java.util.List;

public class GetCustomerLoyaltyResponseModel {

    /**
     * status : true
     * message : List of custome loyality Point Venues.
     * loyalty : {"current_page":1,"data":[{"id":19,"cust_id":9,"merchant_id":3,"loyalty_point":190,"store_credit":1.2,"venue_name":"S & D Dudley Road","venue_id":"201911071149453","venue_images":"1573127385.unnamed.jpg","address_1":"35 Barcroft Road, Wolverhampton, UK","address_2":null,"post_code":"WV2 3AT","city_name":"Wolverhampton","country":"United Kingdom","phone_number":"4412121212","venue_website":null,"latitude":"52.5721601","longitude":"-2.1260597000000416","loyalty_point_value":"0.10","distance":"0.60","loyalty_worth":"19.00","total_buyable_products":3},{"id":38,"cust_id":9,"merchant_id":11,"loyalty_point":10,"store_credit":0,"venue_name":"Wolverhamton Morrions","venue_id":"2019121606413511","venue_images":"1576478495.Morrisons (1).jpg","address_1":"Bilston WV14 0DZ, UK","address_2":null,"post_code":"WV14 0DZ","city_name":"Wolverhampton","country":"United Kingdom","phone_number":"44121212121","venue_website":null,"latitude":"52.56342480000001","longitude":"-2.074979100000064","loyalty_point_value":"0.10","distance":"2.82","loyalty_worth":"1.00","total_buyable_products":64},{"id":27,"cust_id":9,"merchant_id":10,"loyalty_point":0.2,"store_credit":0,"venue_name":"Petalon Flowers London","venue_id":"2019120307071210","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"post_code":"N1 5LQ","city_name":"London","country":"United Kingdom","phone_number":"44121212121","venue_website":null,"latitude":"51.5341388","longitude":"-0.08055539999998018","loyalty_point_value":"0.30","distance":"113.25","loyalty_worth":"0.06","total_buyable_products":0},{"id":11,"cust_id":9,"merchant_id":2,"loyalty_point":0,"store_credit":39,"venue_name":"LillyWhites","venue_id":"201911011148462","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"post_code":"WV3 0DS","city_name":"Wolverhampton","country":"United Kingdom","phone_number":"5468496484","venue_website":"https://www.lillywhites.com/","latitude":"52.5756465","longitude":"-2.1385980000000018","loyalty_point_value":"0.25","distance":"0.02","loyalty_worth":"0.00","total_buyable_products":32},{"id":18,"cust_id":9,"merchant_id":4,"loyalty_point":0,"store_credit":0,"venue_name":"Ankush Venue","venue_id":"201911071043174","venue_images":"1573123397.photo-1533481405265-e9ce0c044abb.jpeg","address_1":"99 City Road Conference Centre, City Road, London, UK","address_2":null,"post_code":"EC1Y 1AX","city_name":"London","country":"United Kingdom","phone_number":"9140832767","venue_website":null,"latitude":"51.5255719","longitude":"-0.08634430000006432","loyalty_point_value":"0","distance":"113.44","loyalty_worth":"0.00","total_buyable_products":0}],"first_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerLoyalty?page=1","from":1,"last_page":1,"last_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerLoyalty?page=1","next_page_url":null,"path":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerLoyalty","per_page":10,"prev_page_url":null,"to":5,"total":5}
     */

    private boolean status;
    private String message;
    private LoyaltyBean loyalty;

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

    public LoyaltyBean getLoyalty() {
        return loyalty;
    }

    public void setLoyalty(LoyaltyBean loyalty) {
        this.loyalty = loyalty;
    }

    public static class LoyaltyBean {
        /**
         * current_page : 1
         * data : [{"id":19,"cust_id":9,"merchant_id":3,"loyalty_point":190,"store_credit":1.2,"venue_name":"S & D Dudley Road","venue_id":"201911071149453","venue_images":"1573127385.unnamed.jpg","address_1":"35 Barcroft Road, Wolverhampton, UK","address_2":null,"post_code":"WV2 3AT","city_name":"Wolverhampton","country":"United Kingdom","phone_number":"4412121212","venue_website":null,"latitude":"52.5721601","longitude":"-2.1260597000000416","loyalty_point_value":"0.10","distance":"0.60","loyalty_worth":"19.00","total_buyable_products":3},{"id":38,"cust_id":9,"merchant_id":11,"loyalty_point":10,"store_credit":0,"venue_name":"Wolverhamton Morrions","venue_id":"2019121606413511","venue_images":"1576478495.Morrisons (1).jpg","address_1":"Bilston WV14 0DZ, UK","address_2":null,"post_code":"WV14 0DZ","city_name":"Wolverhampton","country":"United Kingdom","phone_number":"44121212121","venue_website":null,"latitude":"52.56342480000001","longitude":"-2.074979100000064","loyalty_point_value":"0.10","distance":"2.82","loyalty_worth":"1.00","total_buyable_products":64},{"id":27,"cust_id":9,"merchant_id":10,"loyalty_point":0.2,"store_credit":0,"venue_name":"Petalon Flowers London","venue_id":"2019120307071210","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"post_code":"N1 5LQ","city_name":"London","country":"United Kingdom","phone_number":"44121212121","venue_website":null,"latitude":"51.5341388","longitude":"-0.08055539999998018","loyalty_point_value":"0.30","distance":"113.25","loyalty_worth":"0.06","total_buyable_products":0},{"id":11,"cust_id":9,"merchant_id":2,"loyalty_point":0,"store_credit":39,"venue_name":"LillyWhites","venue_id":"201911011148462","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"post_code":"WV3 0DS","city_name":"Wolverhampton","country":"United Kingdom","phone_number":"5468496484","venue_website":"https://www.lillywhites.com/","latitude":"52.5756465","longitude":"-2.1385980000000018","loyalty_point_value":"0.25","distance":"0.02","loyalty_worth":"0.00","total_buyable_products":32},{"id":18,"cust_id":9,"merchant_id":4,"loyalty_point":0,"store_credit":0,"venue_name":"Ankush Venue","venue_id":"201911071043174","venue_images":"1573123397.photo-1533481405265-e9ce0c044abb.jpeg","address_1":"99 City Road Conference Centre, City Road, London, UK","address_2":null,"post_code":"EC1Y 1AX","city_name":"London","country":"United Kingdom","phone_number":"9140832767","venue_website":null,"latitude":"51.5255719","longitude":"-0.08634430000006432","loyalty_point_value":"0","distance":"113.44","loyalty_worth":"0.00","total_buyable_products":0}]
         * first_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerLoyalty?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerLoyalty?page=1
         * next_page_url : null
         * path : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getCustomerLoyalty
         * per_page : 10
         * prev_page_url : null
         * to : 5
         * total : 5
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
             * id : 19
             * cust_id : 9
             * merchant_id : 3
             * loyalty_point : 190
             * store_credit : 1.2
             * venue_name : S & D Dudley Road
             * venue_id : 201911071149453
             * venue_images : 1573127385.unnamed.jpg
             * address_1 : 35 Barcroft Road, Wolverhampton, UK
             * address_2 : null
             * post_code : WV2 3AT
             * city_name : Wolverhampton
             * country : United Kingdom
             * phone_number : 4412121212
             * venue_website : null
             * latitude : 52.5721601
             * longitude : -2.1260597000000416
             * loyalty_point_value : 0.10
             * distance : 0.60
             * loyalty_worth : 19.00
             * total_buyable_products : 3
             */

            private int id;
            private int cust_id;
            private int merchant_id;
            private double loyalty_point;
            private double store_credit;
            private String venue_name;
            private String venue_id;
            private String venue_images;
            private String address_1;
            private Object address_2;
            private String post_code;
            private String city_name;
            private String country;
            private String phone_number;
            private Object venue_website;
            private String latitude;
            private String longitude;
            private String loyalty_point_value;
            private String distance;
            private String loyalty_worth;
            private int venue_type;
            private int total_buyable_products;

            public int getVenue_type() {
                return venue_type;
            }

            public void setVenue_type(int venue_type) {
                this.venue_type = venue_type;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCust_id() {
                return cust_id;
            }

            public void setCust_id(int cust_id) {
                this.cust_id = cust_id;
            }

            public int getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(int merchant_id) {
                this.merchant_id = merchant_id;
            }

            public double getLoyalty_point() {
                return loyalty_point;
            }

            public void setLoyalty_point(double loyalty_point) {
                this.loyalty_point = loyalty_point;
            }

            public double getStore_credit() {
                return store_credit;
            }

            public void setStore_credit(double store_credit) {
                this.store_credit = store_credit;
            }

            public String getVenue_name() {
                return venue_name;
            }

            public void setVenue_name(String venue_name) {
                this.venue_name = venue_name;
            }

            public String getVenue_id() {
                return venue_id;
            }

            public void setVenue_id(String venue_id) {
                this.venue_id = venue_id;
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

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public Object getVenue_website() {
                return venue_website;
            }

            public void setVenue_website(Object venue_website) {
                this.venue_website = venue_website;
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

            public String getLoyalty_point_value() {
                return loyalty_point_value;
            }

            public void setLoyalty_point_value(String loyalty_point_value) {
                this.loyalty_point_value = loyalty_point_value;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getLoyalty_worth() {
                return loyalty_worth;
            }

            public void setLoyalty_worth(String loyalty_worth) {
                this.loyalty_worth = loyalty_worth;
            }

            public int getTotal_buyable_products() {
                return total_buyable_products;
            }

            public void setTotal_buyable_products(int total_buyable_products) {
                this.total_buyable_products = total_buyable_products;
            }
        }
    }
}