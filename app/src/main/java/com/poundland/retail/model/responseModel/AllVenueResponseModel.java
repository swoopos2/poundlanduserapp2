package com.poundland.retail.model.responseModel;

import java.util.List;

public class AllVenueResponseModel {


    /**
     * status : true
     * message : Venues List
     * venues : {"current_page":1,"data":[{"venue_id":"201910311251401","merchant_id":"1","venue_name":"Nando wolverhampton","venue_images":["20191111135857.Nando\u2019s-Job-Application.jpg"],"address_1":"Oaklands Road, Wolverhampton WV3 0DS, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","phone_number":"123456765","venue_email":"richy@vmailcloud.com","home_delivery_mov":"100.00","home_delivery":1,"click_collect":1,"opening_time":"05:00","closing_time":"20:00","latitude":"52.5756465","longitude":"-2.1385980000000018","delivery_distance":10,"distance":"0.02","stars":"0","total_offers":0},{"venue_id":"201911011148462","merchant_id":"2","venue_name":"Lilly Whites","venue_images":["1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg"],"address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","phone_number":"5468496484","venue_email":"whiteslilly@dmailpro.net","home_delivery_mov":"200.00","home_delivery":1,"click_collect":1,"opening_time":"06:00","closing_time":"20:30","latitude":"52.5756465","longitude":"-2.1385980000000018","delivery_distance":0,"distance":"0.02","stars":"4.1","total_offers":3},{"venue_id":"201911071043174","merchant_id":"4","venue_name":"Ankush Venue","venue_images":["1573123397.photo-1533481405265-e9ce0c044abb.jpeg"],"address_1":"99 City Road Conference Centre, City Road, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"EC1Y 1AX","phone_number":"9140832767","venue_email":"ankush.omnisttechhub@gmail.com","home_delivery_mov":"0.00","home_delivery":1,"click_collect":1,"opening_time":"06:50","closing_time":"21:00","latitude":"51.5255719","longitude":"-0.08634430000006432","delivery_distance":115,"distance":"114.26","stars":"0","total_offers":0},{"venue_id":"201911071149453","merchant_id":"3","venue_name":"S & D Dudley Road","venue_images":["1573127385.unnamed.jpg"],"address_1":"35 Barcroft Road, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV2 3AT","phone_number":"4412121212","venue_email":"sdvenueone@3dmail.top","home_delivery_mov":"50.00","home_delivery":1,"click_collect":1,"opening_time":"09:00","closing_time":"21:00","latitude":"52.5721601","longitude":"-2.1260597000000416","delivery_distance":0,"distance":"0.60","stars":"0","total_offers":0}],"first_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllVenues?page=1","from":1,"last_page":1,"last_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllVenues?page=1","next_page_url":null,"path":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllVenues","per_page":10,"prev_page_url":null,"to":4,"total":4}
     */

    private boolean status;
    private String message;
    private VenuesBean venues;

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

    public static class VenuesBean {
        /**
         * current_page : 1
         * data : [{"venue_id":"201910311251401","merchant_id":"1","venue_name":"Nando wolverhampton","venue_images":["20191111135857.Nando\u2019s-Job-Application.jpg"],"address_1":"Oaklands Road, Wolverhampton WV3 0DS, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","phone_number":"123456765","venue_email":"richy@vmailcloud.com","home_delivery_mov":"100.00","home_delivery":1,"click_collect":1,"opening_time":"05:00","closing_time":"20:00","latitude":"52.5756465","longitude":"-2.1385980000000018","delivery_distance":10,"distance":"0.02","stars":"0","total_offers":0},{"venue_id":"201911011148462","merchant_id":"2","venue_name":"Lilly Whites","venue_images":["1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg"],"address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","phone_number":"5468496484","venue_email":"whiteslilly@dmailpro.net","home_delivery_mov":"200.00","home_delivery":1,"click_collect":1,"opening_time":"06:00","closing_time":"20:30","latitude":"52.5756465","longitude":"-2.1385980000000018","delivery_distance":0,"distance":"0.02","stars":"4.1","total_offers":3},{"venue_id":"201911071043174","merchant_id":"4","venue_name":"Ankush Venue","venue_images":["1573123397.photo-1533481405265-e9ce0c044abb.jpeg"],"address_1":"99 City Road Conference Centre, City Road, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"EC1Y 1AX","phone_number":"9140832767","venue_email":"ankush.omnisttechhub@gmail.com","home_delivery_mov":"0.00","home_delivery":1,"click_collect":1,"opening_time":"06:50","closing_time":"21:00","latitude":"51.5255719","longitude":"-0.08634430000006432","delivery_distance":115,"distance":"114.26","stars":"0","total_offers":0},{"venue_id":"201911071149453","merchant_id":"3","venue_name":"S & D Dudley Road","venue_images":["1573127385.unnamed.jpg"],"address_1":"35 Barcroft Road, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV2 3AT","phone_number":"4412121212","venue_email":"sdvenueone@3dmail.top","home_delivery_mov":"50.00","home_delivery":1,"click_collect":1,"opening_time":"09:00","closing_time":"21:00","latitude":"52.5721601","longitude":"-2.1260597000000416","delivery_distance":0,"distance":"0.60","stars":"0","total_offers":0}]
         * first_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllVenues?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllVenues?page=1
         * next_page_url : null
         * path : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllVenues
         * per_page : 10
         * prev_page_url : null
         * to : 4
         * total : 4
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private String next_page_url;
        private String path;
        private int per_page;
        private String prev_page_url;
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

        public String getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(String prev_page_url) {
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
             * venue_id : 201910311251401
             * merchant_id : 1
             * venue_name : Nando wolverhampton
             * venue_images : ["20191111135857.Nando\u2019s-Job-Application.jpg"]
             * address_1 : Oaklands Road, Wolverhampton WV3 0DS, UK
             * address_2 : null
             * city_name : Wolverhampton
             * country : United Kingdom
             * post_code : WV3 0DS
             * phone_number : 123456765
             * venue_email : richy@vmailcloud.com
             * home_delivery_mov : 100.00
             * home_delivery : 1
             * click_collect : 1
             * opening_time : 05:00
             * closing_time : 20:00
             * latitude : 52.5756465
             * longitude : -2.1385980000000018
             * delivery_distance : 10
             * distance : 0.02
             * stars : 0
             * total_offers : 0
             */

            private String venue_id;
            private String merchant_id;
            private String venue_name;
            private String address_1;
            private String address_2;
            private String city_name;
            private String country;
            private String post_code;
            private String phone_number;
            private String venue_email;
            private String home_delivery_mov;
            private int home_delivery;
            private int click_collect;
            private String opening_time;
            private String closing_time;
            private String isClose;
            private String latitude;
            private String longitude;
            private int delivery_distance;
            private String distance;
            private String stars;
            private int total_offers;
            private int venue_type;
            private List<String> venue_images;
            private boolean isWishlisted;

            public int getVenue_type() {
                return venue_type;
            }

            public void setVenue_type(int venue_type) {
                this.venue_type = venue_type;
            }

            public boolean isWishlisted() {
                return isWishlisted;
            }

            public void setWishlisted(boolean wishlisted) {
                isWishlisted = wishlisted;
            }
            public String getIsClose() {
                return isClose;
            }

            public void setIsClose(String isClose) {
                this.isClose = isClose;
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

            public String getHome_delivery_mov() {
                return home_delivery_mov;
            }

            public void setHome_delivery_mov(String home_delivery_mov) {
                this.home_delivery_mov = home_delivery_mov;
            }

            public int getHome_delivery() {
                return home_delivery;
            }

            public void setHome_delivery(int home_delivery) {
                this.home_delivery = home_delivery;
            }

            public int getClick_collect() {
                return click_collect;
            }

            public void setClick_collect(int click_collect) {
                this.click_collect = click_collect;
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

            public List<String> getVenue_images() {
                return venue_images;
            }

            public void setVenue_images(List<String> venue_images) {
                this.venue_images = venue_images;
            }
        }
    }
}
