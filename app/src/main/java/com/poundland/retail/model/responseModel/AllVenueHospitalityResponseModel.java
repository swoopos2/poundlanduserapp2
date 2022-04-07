package com.poundland.retail.model.responseModel;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AllVenueHospitalityResponseModel {


    /**
     * status : true
     * message : List Of Venue
     * category : ["ae12","champagne","drink","eat","eat-in","food","healthy","pizza","rum","scotch","spicy","tasty","vodka","whisky","wine"]
     * dietary : {"veg":"Veg","non-veg":"Non Veg"}
     * venues : {"current_page":1,"data":[{"id":41,"order_status":1,"venue_type":2,"latitude":null,"longitude":null,"timing_slot_gap":"15","max_order_slot":"2.00","venue_category":"healthy,Tasty,spicy,Pizza","product_type":"both","merchant_id":16,"venue_id":"2020052110402516","venue_name":"Your Resturant","collection_time":90,"is_booking_allow":0,"venue_images":"20200522093601-images (8).jpeg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH12 7XD","home_delivery_mov":"20.00","delivery":1,"collection":1,"delivery_distance":0,"distance":null,"is_like":0,"rate":"4.250000000","timing":{"total_offers":0,"opening_time":"2020-12-28 06:00","closing_time":"2020-12-28 22:00","delivery_opening_time":"08:00","delivery_closing_time":"20:00","delivery_closing_datetime":"2020-12-28 20:00","collection_opening_time":"08:00","collection_closing_time":"19:00","is_venue_open":1,"isClose":"0"}},{"id":92,"order_status":1,"venue_type":2,"latitude":null,"longitude":null,"timing_slot_gap":"0","max_order_slot":"0","venue_category":"Eat,Food,Drink","product_type":"both","merchant_id":36,"venue_id":"2020112705294136","venue_name":"Lasan Restaurant","collection_time":8,"is_booking_allow":0,"venue_images":"2508316064549120.jpeg","address_1":"Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":null,"post_code":"WV30DS","home_delivery_mov":"0.00","delivery":1,"collection":0,"delivery_distance":0,"distance":null,"is_like":0,"rate":null,"timing":{"total_offers":0,"opening_time":"2020-12-28 08:00","closing_time":"2020-12-28 20:00","delivery_opening_time":"08:00","delivery_closing_time":"20:00","delivery_closing_datetime":"2020-12-28 20:00","collection_opening_time":"08:00","collection_closing_time":"20:00","is_venue_open":1,"isClose":"0"}},{"id":31,"order_status":1,"venue_type":2,"latitude":"55.9428422","longitude":"-3.2831273","timing_slot_gap":"15","max_order_slot":"10.00","venue_category":"healthy, spicy","product_type":"non-veg","merchant_id":16,"venue_id":"2020042308172516","venue_name":"Raman Restaurant","collection_time":70,"is_booking_allow":1,"venue_images":"20200520154700-what-to-eat-in-morocco.jpg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"th floor 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","city_name":"castophine","country":"United Kingdom","post_code":"eh127xd","home_delivery_mov":"20.00","delivery":1,"collection":1,"delivery_distance":0,"distance":3997.86,"is_like":0,"rate":"3.250000000","timing":{"total_offers":0,"opening_time":"2020-12-28 08:00","closing_time":"2020-12-28 22:00","delivery_opening_time":"09:00","delivery_closing_time":"22:00","delivery_closing_datetime":"2020-12-28 22:00","collection_opening_time":"09:00","collection_closing_time":"22:00","is_venue_open":1,"isClose":"0"}},{"id":96,"order_status":1,"venue_type":2,"latitude":"52.4815938","longitude":"-1.9000993","timing_slot_gap":"0","max_order_slot":"0","venue_category":"Food,Drink","product_type":"both","merchant_id":40,"venue_id":"2020112708020440","venue_name":"Gaucho Birmingham","collection_time":0,"is_booking_allow":0,"venue_images":"4738716064640960.jpeg","address_1":"55 Colmore Row, Colmore Row, Birmingham, UK","address_2":null,"city_name":"Birmingham","country":"United Kingdom","post_code":"B3 2AA","home_delivery_mov":"0.00","delivery":1,"collection":0,"delivery_distance":0,"distance":4084.94,"is_like":0,"rate":null,"timing":{"total_offers":0,"opening_time":"2020-12-28 11:00","closing_time":"2020-12-28 21:00","delivery_opening_time":"11:00","delivery_closing_time":"21:00","delivery_closing_datetime":"2020-12-28 21:00","collection_opening_time":"11:00","collection_closing_time":"21:00","is_venue_open":1,"isClose":"0"}},{"id":94,"order_status":1,"venue_type":2,"latitude":"52.45864599999999","longitude":"-1.9498564","timing_slot_gap":"0","max_order_slot":"0","venue_category":"Eat-In,Food,Drink","product_type":"both","merchant_id":38,"venue_id":"2020112707261538","venue_name":"Harborne Kitchen","collection_time":0,"is_booking_allow":0,"venue_images":"3275516064618900.jpeg","address_1":"175-179 High St, Harborne, Birmingham, UK","address_2":null,"city_name":"Birmingham","country":"United Kingdom","post_code":"B17 9QE","home_delivery_mov":"0.00","delivery":1,"collection":1,"delivery_distance":0,"distance":4087.74,"is_like":0,"rate":null,"timing":{"total_offers":0,"opening_time":"2020-12-28 10:00","closing_time":"2020-12-28 22:00","delivery_opening_time":"10:00","delivery_closing_time":"22:00","delivery_closing_datetime":"2020-12-28 22:00","collection_opening_time":"10:00","collection_closing_time":"22:00","is_venue_open":1,"isClose":"0"}},{"id":33,"order_status":1,"venue_type":2,"latitude":"52.4572148","longitude":"-2.1474445","timing_slot_gap":"15","max_order_slot":"10.00","venue_category":"Wine,Whisky,Rum,Scotch,Vodka,champagne","product_type":"veg","merchant_id":15,"venue_id":"2020042710540215","venue_name":"Majestic Stourbridge","collection_time":45,"is_booking_allow":1,"venue_images":"20200918122353-20200520154611-majestic.jpg","address_1":"Stourbridge Town Hall, Crown Lane, Stourbridge, UK","address_2":null,"city_name":"Stourbridge","country":"United Kingdom","post_code":"DY8 1YE","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":10000,"distance":4095.18,"is_like":0,"rate":"4.562500000","timing":{"total_offers":0,"opening_time":"2020-12-28 05:05","closing_time":"2020-12-29 00:00","delivery_opening_time":"05:05","delivery_closing_time":"22:40","delivery_closing_datetime":"2020-12-28 22:40","collection_opening_time":"05:05","collection_closing_time":"20:00","is_venue_open":1,"isClose":"0"}},{"id":95,"order_status":1,"venue_type":2,"latitude":"-33.7544","longitude":"151.2854","timing_slot_gap":"0","max_order_slot":"0","venue_category":"ae12","product_type":"both","merchant_id":39,"venue_id":"2020112707435839","venue_name":"Eat Best","collection_time":0,"is_booking_allow":0,"venue_images":"8240816064628750.png","address_1":"Dee Why NSW, Australia","address_2":null,"city_name":"Dee Why","country":"Australia","post_code":"2099","home_delivery_mov":"0.00","delivery":1,"collection":0,"delivery_distance":0,"distance":5606.61,"is_like":0,"rate":null,"timing":{"total_offers":0,"opening_time":"2020-12-28 00:10","closing_time":"2020-12-28 23:50","delivery_opening_time":"00:10","delivery_closing_time":"23:50","delivery_closing_datetime":"2020-12-28 23:50","collection_opening_time":"00:10","collection_closing_time":"23:50","is_venue_open":1,"isClose":"0"}}],"first_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/venue_list?page=1","from":1,"last_page":1,"last_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/venue_list?page=1","next_page_url":null,"path":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/venue_list","per_page":16,"prev_page_url":null,"to":7,"total":7}
     */

    private boolean status;
    private String message;
    private int usePaginate;
    private DietaryBean dietary;
    private VenuesBean venues;
    private List<String> category;

    public int getUsePaginate() {
        return usePaginate;
    }

    public void setUsePaginate(int usePaginate) {
        this.usePaginate = usePaginate;
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

    public DietaryBean getDietary() {
        return dietary;
    }

    public void setDietary(DietaryBean dietary) {
        this.dietary = dietary;
    }

    public VenuesBean getVenues() {
        return venues;
    }

    public void setVenues(VenuesBean venues) {
        this.venues = venues;
    }

    public List<String> getCategory() {
        return category;
    }

    public void setCategory(List<String> category) {
        this.category = category;
    }

    public static class DietaryBean {
        /**
         * veg : Veg
         * non-veg : Non Veg
         */

        private String veg;
        @SerializedName("non-veg")
        private String nonveg;

        public String getVeg() {
            return veg;
        }

        public void setVeg(String veg) {
            this.veg = veg;
        }

        public String getNonveg() {
            return nonveg;
        }

        public void setNonveg(String nonveg) {
            this.nonveg = nonveg;
        }
    }

    public static class VenuesBean {
        /**
         * current_page : 1
         * data : [{"id":41,"order_status":1,"venue_type":2,"latitude":null,"longitude":null,"timing_slot_gap":"15","max_order_slot":"2.00","venue_category":"healthy,Tasty,spicy,Pizza","product_type":"both","merchant_id":16,"venue_id":"2020052110402516","venue_name":"Your Resturant","collection_time":90,"is_booking_allow":0,"venue_images":"20200522093601-images (8).jpeg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH12 7XD","home_delivery_mov":"20.00","delivery":1,"collection":1,"delivery_distance":0,"distance":null,"is_like":0,"rate":"4.250000000","timing":{"total_offers":0,"opening_time":"2020-12-28 06:00","closing_time":"2020-12-28 22:00","delivery_opening_time":"08:00","delivery_closing_time":"20:00","delivery_closing_datetime":"2020-12-28 20:00","collection_opening_time":"08:00","collection_closing_time":"19:00","is_venue_open":1,"isClose":"0"}},{"id":92,"order_status":1,"venue_type":2,"latitude":null,"longitude":null,"timing_slot_gap":"0","max_order_slot":"0","venue_category":"Eat,Food,Drink","product_type":"both","merchant_id":36,"venue_id":"2020112705294136","venue_name":"Lasan Restaurant","collection_time":8,"is_booking_allow":0,"venue_images":"2508316064549120.jpeg","address_1":"Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":null,"post_code":"WV30DS","home_delivery_mov":"0.00","delivery":1,"collection":0,"delivery_distance":0,"distance":null,"is_like":0,"rate":null,"timing":{"total_offers":0,"opening_time":"2020-12-28 08:00","closing_time":"2020-12-28 20:00","delivery_opening_time":"08:00","delivery_closing_time":"20:00","delivery_closing_datetime":"2020-12-28 20:00","collection_opening_time":"08:00","collection_closing_time":"20:00","is_venue_open":1,"isClose":"0"}},{"id":31,"order_status":1,"venue_type":2,"latitude":"55.9428422","longitude":"-3.2831273","timing_slot_gap":"15","max_order_slot":"10.00","venue_category":"healthy, spicy","product_type":"non-veg","merchant_id":16,"venue_id":"2020042308172516","venue_name":"Raman Restaurant","collection_time":70,"is_booking_allow":1,"venue_images":"20200520154700-what-to-eat-in-morocco.jpg","address_1":"107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"th floor 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","city_name":"castophine","country":"United Kingdom","post_code":"eh127xd","home_delivery_mov":"20.00","delivery":1,"collection":1,"delivery_distance":0,"distance":3997.86,"is_like":0,"rate":"3.250000000","timing":{"total_offers":0,"opening_time":"2020-12-28 08:00","closing_time":"2020-12-28 22:00","delivery_opening_time":"09:00","delivery_closing_time":"22:00","delivery_closing_datetime":"2020-12-28 22:00","collection_opening_time":"09:00","collection_closing_time":"22:00","is_venue_open":1,"isClose":"0"}},{"id":96,"order_status":1,"venue_type":2,"latitude":"52.4815938","longitude":"-1.9000993","timing_slot_gap":"0","max_order_slot":"0","venue_category":"Food,Drink","product_type":"both","merchant_id":40,"venue_id":"2020112708020440","venue_name":"Gaucho Birmingham","collection_time":0,"is_booking_allow":0,"venue_images":"4738716064640960.jpeg","address_1":"55 Colmore Row, Colmore Row, Birmingham, UK","address_2":null,"city_name":"Birmingham","country":"United Kingdom","post_code":"B3 2AA","home_delivery_mov":"0.00","delivery":1,"collection":0,"delivery_distance":0,"distance":4084.94,"is_like":0,"rate":null,"timing":{"total_offers":0,"opening_time":"2020-12-28 11:00","closing_time":"2020-12-28 21:00","delivery_opening_time":"11:00","delivery_closing_time":"21:00","delivery_closing_datetime":"2020-12-28 21:00","collection_opening_time":"11:00","collection_closing_time":"21:00","is_venue_open":1,"isClose":"0"}},{"id":94,"order_status":1,"venue_type":2,"latitude":"52.45864599999999","longitude":"-1.9498564","timing_slot_gap":"0","max_order_slot":"0","venue_category":"Eat-In,Food,Drink","product_type":"both","merchant_id":38,"venue_id":"2020112707261538","venue_name":"Harborne Kitchen","collection_time":0,"is_booking_allow":0,"venue_images":"3275516064618900.jpeg","address_1":"175-179 High St, Harborne, Birmingham, UK","address_2":null,"city_name":"Birmingham","country":"United Kingdom","post_code":"B17 9QE","home_delivery_mov":"0.00","delivery":1,"collection":1,"delivery_distance":0,"distance":4087.74,"is_like":0,"rate":null,"timing":{"total_offers":0,"opening_time":"2020-12-28 10:00","closing_time":"2020-12-28 22:00","delivery_opening_time":"10:00","delivery_closing_time":"22:00","delivery_closing_datetime":"2020-12-28 22:00","collection_opening_time":"10:00","collection_closing_time":"22:00","is_venue_open":1,"isClose":"0"}},{"id":33,"order_status":1,"venue_type":2,"latitude":"52.4572148","longitude":"-2.1474445","timing_slot_gap":"15","max_order_slot":"10.00","venue_category":"Wine,Whisky,Rum,Scotch,Vodka,champagne","product_type":"veg","merchant_id":15,"venue_id":"2020042710540215","venue_name":"Majestic Stourbridge","collection_time":45,"is_booking_allow":1,"venue_images":"20200918122353-20200520154611-majestic.jpg","address_1":"Stourbridge Town Hall, Crown Lane, Stourbridge, UK","address_2":null,"city_name":"Stourbridge","country":"United Kingdom","post_code":"DY8 1YE","home_delivery_mov":"10.00","delivery":1,"collection":1,"delivery_distance":10000,"distance":4095.18,"is_like":0,"rate":"4.562500000","timing":{"total_offers":0,"opening_time":"2020-12-28 05:05","closing_time":"2020-12-29 00:00","delivery_opening_time":"05:05","delivery_closing_time":"22:40","delivery_closing_datetime":"2020-12-28 22:40","collection_opening_time":"05:05","collection_closing_time":"20:00","is_venue_open":1,"isClose":"0"}},{"id":95,"order_status":1,"venue_type":2,"latitude":"-33.7544","longitude":"151.2854","timing_slot_gap":"0","max_order_slot":"0","venue_category":"ae12","product_type":"both","merchant_id":39,"venue_id":"2020112707435839","venue_name":"Eat Best","collection_time":0,"is_booking_allow":0,"venue_images":"8240816064628750.png","address_1":"Dee Why NSW, Australia","address_2":null,"city_name":"Dee Why","country":"Australia","post_code":"2099","home_delivery_mov":"0.00","delivery":1,"collection":0,"delivery_distance":0,"distance":5606.61,"is_like":0,"rate":null,"timing":{"total_offers":0,"opening_time":"2020-12-28 00:10","closing_time":"2020-12-28 23:50","delivery_opening_time":"00:10","delivery_closing_time":"23:50","delivery_closing_datetime":"2020-12-28 23:50","collection_opening_time":"00:10","collection_closing_time":"23:50","is_venue_open":1,"isClose":"0"}}]
         * first_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/venue_list?page=1
         * from : 1
         * last_page : 1
         * last_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/venue_list?page=1
         * next_page_url : null
         * path : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/venue_list
         * per_page : 16
         * prev_page_url : null
         * to : 7
         * total : 7
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
             * order_status : 1
             * venue_type : 2
             * latitude : null
             * longitude : null
             * timing_slot_gap : 15
             * max_order_slot : 2.00
             * venue_category : healthy,Tasty,spicy,Pizza
             * product_type : both
             * merchant_id : 16
             * venue_id : 2020052110402516
             * venue_name : Your Resturant
             * collection_time : 90
             * is_booking_allow : 0
             * venue_images : 20200522093601-images (8).jpeg
             * address_1 : 107-108 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom
             * address_2 : 7th flor
             * city_name : Edinburgh
             * country : United Kingdom
             * post_code : EH12 7XD
             * home_delivery_mov : 20.00
             * delivery : 1
             * collection : 1
             * delivery_distance : 0
             * distance : null
             * is_like : 0
             * rate : 4.250000000
             * timing : {"total_offers":0,"opening_time":"2020-12-28 06:00","closing_time":"2020-12-28 22:00","delivery_opening_time":"08:00","delivery_closing_time":"20:00","delivery_closing_datetime":"2020-12-28 20:00","collection_opening_time":"08:00","collection_closing_time":"19:00","is_venue_open":1,"isClose":"0"}
             */

            private int id;
            private int order_status;
            private int venue_type;
            private Object latitude;
            private Object longitude;
            private String timing_slot_gap;
            private String max_order_slot;
            private String venue_category;
            private String product_type;
            private int merchant_id;
            private String venue_id;
            private String venue_name;
            private int collection_time;
            private int is_booking_allow;
            private String venue_images;
            private String address_1;
            private String address_2;
            private String city_name;
            private String country;
            private String post_code;
            private String home_delivery_mov;
            private int delivery;
            private int collection;
            private int delivery_distance;
            private String distance;
            private int is_like;
            private String rate;
            private TimingBean timing;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getOrder_status() {
                return order_status;
            }

            public void setOrder_status(int order_status) {
                this.order_status = order_status;
            }

            public int getVenue_type() {
                return venue_type;
            }

            public void setVenue_type(int venue_type) {
                this.venue_type = venue_type;
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

            public String getTiming_slot_gap() {
                return timing_slot_gap;
            }

            public void setTiming_slot_gap(String timing_slot_gap) {
                this.timing_slot_gap = timing_slot_gap;
            }

            public String getMax_order_slot() {
                return max_order_slot;
            }

            public void setMax_order_slot(String max_order_slot) {
                this.max_order_slot = max_order_slot;
            }

            public String getVenue_category() {
                return venue_category;
            }

            public void setVenue_category(String venue_category) {
                this.venue_category = venue_category;
            }

            public String getProduct_type() {
                return product_type;
            }

            public void setProduct_type(String product_type) {
                this.product_type = product_type;
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

            public int getCollection_time() {
                return collection_time;
            }

            public void setCollection_time(int collection_time) {
                this.collection_time = collection_time;
            }

            public int getIs_booking_allow() {
                return is_booking_allow;
            }

            public void setIs_booking_allow(int is_booking_allow) {
                this.is_booking_allow = is_booking_allow;
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

            public int getIs_like() {
                return is_like;
            }

            public void setIs_like(int is_like) {
                this.is_like = is_like;
            }

            public String getRate() {
                return rate;
            }

            public void setRate(String rate) {
                this.rate = rate;
            }

            public TimingBean getTiming() {
                return timing;
            }

            public void setTiming(TimingBean timing) {
                this.timing = timing;
            }

            public static class TimingBean {
                /**
                 * total_offers : 0
                 * opening_time : 2020-12-28 06:00
                 * closing_time : 2020-12-28 22:00
                 * delivery_opening_time : 08:00
                 * delivery_closing_time : 20:00
                 * delivery_closing_datetime : 2020-12-28 20:00
                 * collection_opening_time : 08:00
                 * collection_closing_time : 19:00
                 * is_venue_open : 1
                 * isClose : 0
                 */

                private int total_offers;
                private String opening_time;
                private String closing_time;
                private String delivery_opening_time;
                private String delivery_closing_time;
                private String delivery_closing_datetime;
                private String collection_opening_time;
                private String collection_closing_time;
                private int is_venue_open;
                private String isClose;

                public int getTotal_offers() {
                    return total_offers;
                }

                public void setTotal_offers(int total_offers) {
                    this.total_offers = total_offers;
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

                public String getDelivery_opening_time() {
                    return delivery_opening_time;
                }

                public void setDelivery_opening_time(String delivery_opening_time) {
                    this.delivery_opening_time = delivery_opening_time;
                }

                public String getDelivery_closing_time() {
                    return delivery_closing_time;
                }

                public void setDelivery_closing_time(String delivery_closing_time) {
                    this.delivery_closing_time = delivery_closing_time;
                }

                public String getDelivery_closing_datetime() {
                    return delivery_closing_datetime;
                }

                public void setDelivery_closing_datetime(String delivery_closing_datetime) {
                    this.delivery_closing_datetime = delivery_closing_datetime;
                }

                public String getCollection_opening_time() {
                    return collection_opening_time;
                }

                public void setCollection_opening_time(String collection_opening_time) {
                    this.collection_opening_time = collection_opening_time;
                }

                public String getCollection_closing_time() {
                    return collection_closing_time;
                }

                public void setCollection_closing_time(String collection_closing_time) {
                    this.collection_closing_time = collection_closing_time;
                }

                public int getIs_venue_open() {
                    return is_venue_open;
                }

                public void setIs_venue_open(int is_venue_open) {
                    this.is_venue_open = is_venue_open;
                }

                public String getIsClose() {
                    return isClose;
                }

                public void setIsClose(String isClose) {
                    this.isClose = isClose;
                }
            }
        }
    }
}
