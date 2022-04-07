package com.poundland.retail.model.responseModel;

import java.util.List;

public class WishlistedProductResponseModel {


    /**
     * status : true
     * message : List of liked Venues.
     * venues : {"current_page":1,"data":[{"id":8,"guest":null,"customer_id":4,"venue_id":"2020032409274312","ip_address":"162.158.154.203","created_at":"2020-03-26 09:03:18","updated_at":"2020-03-26 09:03:18","venue":{"id":19,"merchant_id":12,"venue_id":"2020032409274312","venue_name":"NKdpizza","venue_images":"20200324093151.jaguar_thumb.ngsversion.1481754612130.adapt.1900.1.jpeg","address_1":"Corstorphine, Edinburgh, UK","address_2":"7th floor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH127XD","phone_number":"08076138481","venue_email":"abc@mailinator.com","venue_website":null,"home_delivery_mov":"1.00","start_days":1,"end_days":2,"collection_time":90,"preparation_time":15,"free_delivery":"1.00","delivery_charge":"1.00","delivery":1,"delivery_distance":0,"collection":1,"latitude":"55.9415474","longitude":"-3.2865553","distance":"4,254.15","opening_time":"2020-04-01 09:00","closing_time":"2020-04-01 11:00","stars":"0","isClose":"1","total_offers":0,"isWishlisted":true}},{"id":17,"guest":null,"customer_id":4,"venue_id":"2020032506412414","ip_address":"162.158.50.220","created_at":"2020-03-30 08:24:17","updated_at":"2020-03-30 08:24:17","venue":{"id":22,"merchant_id":14,"venue_id":"2020032506412414","venue_name":"stefen venue","venue_images":"1585118484.dominos.jpeg","address_1":"244-247 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"EH127XD","country":"United Kingdom","post_code":"EH127XD","phone_number":"9632588956","venue_email":"stefenvenue@mailinator.com","venue_website":null,"home_delivery_mov":"10.00","start_days":1,"end_days":2,"collection_time":90,"preparation_time":15,"free_delivery":"15.00","delivery_charge":"2.00","delivery":1,"delivery_distance":0,"collection":1,"latitude":"55.9428422","longitude":"-3.2831273","distance":"4,254.01","opening_time":"2020-04-01 09:00","closing_time":"2020-04-01 23:00","stars":"0","isClose":"0","total_offers":0,"isWishlisted":true}},{"id":23,"guest":null,"customer_id":4,"venue_id":"202002121635135","ip_address":"162.158.198.176","created_at":"2020-03-30 09:59:56","updated_at":"2020-03-30 09:59:56","venue":{"id":5,"merchant_id":5,"venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"1581525313.lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","phone_number":"03443325602","venue_email":"lillywhites@swoopos.com","venue_website":null,"home_delivery_mov":"1.00","start_days":3,"end_days":5,"collection_time":60,"preparation_time":65,"free_delivery":"1.00","delivery_charge":"0.00","delivery":1,"delivery_distance":0,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","distance":"4,179.46","opening_time":"2020-04-01 04:00","closing_time":"2020-04-01 23:00","stars":"3.8","isClose":"0","total_offers":0,"isWishlisted":true}}],"first_page_url":"https://swooposretailuk.com/admintesting/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getLikedVenues?page=1","from":1,"last_page":1,"last_page_url":"https://swooposretailuk.com/admintesting/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getLikedVenues?page=1","next_page_url":null,"path":"https://swooposretailuk.com/admintesting/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getLikedVenues","per_page":10,"prev_page_url":null,"to":3,"total":3}
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
         * data : [{"id":8,"guest":null,"customer_id":4,"venue_id":"2020032409274312","ip_address":"162.158.154.203","created_at":"2020-03-26 09:03:18","updated_at":"2020-03-26 09:03:18","venue":{"id":19,"merchant_id":12,"venue_id":"2020032409274312","venue_name":"NKdpizza","venue_images":"20200324093151.jaguar_thumb.ngsversion.1481754612130.adapt.1900.1.jpeg","address_1":"Corstorphine, Edinburgh, UK","address_2":"7th floor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH127XD","phone_number":"08076138481","venue_email":"abc@mailinator.com","venue_website":null,"home_delivery_mov":"1.00","start_days":1,"end_days":2,"collection_time":90,"preparation_time":15,"free_delivery":"1.00","delivery_charge":"1.00","delivery":1,"delivery_distance":0,"collection":1,"latitude":"55.9415474","longitude":"-3.2865553","distance":"4,254.15","opening_time":"2020-04-01 09:00","closing_time":"2020-04-01 11:00","stars":"0","isClose":"1","total_offers":0,"isWishlisted":true}},{"id":17,"guest":null,"customer_id":4,"venue_id":"2020032506412414","ip_address":"162.158.50.220","created_at":"2020-03-30 08:24:17","updated_at":"2020-03-30 08:24:17","venue":{"id":22,"merchant_id":14,"venue_id":"2020032506412414","venue_name":"stefen venue","venue_images":"1585118484.dominos.jpeg","address_1":"244-247 St John's Rd, Corstorphine, Edinburgh EH12 7XD, United Kingdom","address_2":"7th flor","city_name":"EH127XD","country":"United Kingdom","post_code":"EH127XD","phone_number":"9632588956","venue_email":"stefenvenue@mailinator.com","venue_website":null,"home_delivery_mov":"10.00","start_days":1,"end_days":2,"collection_time":90,"preparation_time":15,"free_delivery":"15.00","delivery_charge":"2.00","delivery":1,"delivery_distance":0,"collection":1,"latitude":"55.9428422","longitude":"-3.2831273","distance":"4,254.01","opening_time":"2020-04-01 09:00","closing_time":"2020-04-01 23:00","stars":"0","isClose":"0","total_offers":0,"isWishlisted":true}},{"id":23,"guest":null,"customer_id":4,"venue_id":"202002121635135","ip_address":"162.158.198.176","created_at":"2020-03-30 09:59:56","updated_at":"2020-03-30 09:59:56","venue":{"id":5,"merchant_id":5,"venue_id":"202002121635135","venue_name":"Lillywhites","venue_images":"1581525313.lillywhites.jpg","address_1":"24-36 Regent St, St. James's, London, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"SW1Y 4QF","phone_number":"03443325602","venue_email":"lillywhites@swoopos.com","venue_website":null,"home_delivery_mov":"1.00","start_days":3,"end_days":5,"collection_time":60,"preparation_time":65,"free_delivery":"1.00","delivery_charge":"0.00","delivery":1,"delivery_distance":0,"collection":1,"latitude":"51.5095249","longitude":"-0.134223","distance":"4,179.46","opening_time":"2020-04-01 04:00","closing_time":"2020-04-01 23:00","stars":"3.8","isClose":"0","total_offers":0,"isWishlisted":true}}]
         * first_page_url : https://swooposretailuk.com/admintesting/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getLikedVenues?page=1
         * from : 1
         * last_page : 1
         * last_page_url : https://swooposretailuk.com/admintesting/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getLikedVenues?page=1
         * next_page_url : null
         * path : https://swooposretailuk.com/admintesting/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getLikedVenues
         * per_page : 10
         * prev_page_url : null
         * to : 3
         * total : 3
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
             * id : 8
             * guest : null
             * customer_id : 4
             * venue_id : 2020032409274312
             * ip_address : 162.158.154.203
             * created_at : 2020-03-26 09:03:18
             * updated_at : 2020-03-26 09:03:18
             * venue : {"id":19,"merchant_id":12,"venue_id":"2020032409274312","venue_name":"NKdpizza","venue_images":"20200324093151.jaguar_thumb.ngsversion.1481754612130.adapt.1900.1.jpeg","address_1":"Corstorphine, Edinburgh, UK","address_2":"7th floor","city_name":"Edinburgh","country":"United Kingdom","post_code":"EH127XD","phone_number":"08076138481","venue_email":"abc@mailinator.com","venue_website":null,"home_delivery_mov":"1.00","start_days":1,"end_days":2,"collection_time":90,"preparation_time":15,"free_delivery":"1.00","delivery_charge":"1.00","delivery":1,"delivery_distance":0,"collection":1,"latitude":"55.9415474","longitude":"-3.2865553","distance":"4,254.15","opening_time":"2020-04-01 09:00","closing_time":"2020-04-01 11:00","stars":"0","isClose":"1","total_offers":0,"isWishlisted":true}
             */

            private int id;
            private Object guest;
            private int customer_id;
            private String venue_id;
            private String ip_address;
            private String created_at;
            private String updated_at;
            private VenueBean venue;

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

            public String getVenue_id() {
                return venue_id;
            }

            public void setVenue_id(String venue_id) {
                this.venue_id = venue_id;
            }

            public String getIp_address() {
                return ip_address;
            }

            public void setIp_address(String ip_address) {
                this.ip_address = ip_address;
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

            public VenueBean getVenue() {
                return venue;
            }

            public void setVenue(VenueBean venue) {
                this.venue = venue;
            }

            public static class VenueBean {
                /**
                 * id : 19
                 * merchant_id : 12
                 * venue_id : 2020032409274312
                 * venue_name : NKdpizza
                 * venue_images : 20200324093151.jaguar_thumb.ngsversion.1481754612130.adapt.1900.1.jpeg
                 * address_1 : Corstorphine, Edinburgh, UK
                 * address_2 : 7th floor
                 * city_name : Edinburgh
                 * country : United Kingdom
                 * post_code : EH127XD
                 * phone_number : 08076138481
                 * venue_email : abc@mailinator.com
                 * venue_website : null
                 * home_delivery_mov : 1.00
                 * start_days : 1
                 * end_days : 2
                 * collection_time : 90
                 * preparation_time : 15
                 * free_delivery : 1.00
                 * delivery_charge : 1.00
                 * delivery : 1
                 * delivery_distance : 0
                 * collection : 1
                 * latitude : 55.9415474
                 * longitude : -3.2865553
                 * distance : 4,254.15
                 * opening_time : 2020-04-01 09:00
                 * closing_time : 2020-04-01 11:00
                 * stars : 0
                 * isClose : 1
                 * total_offers : 0
                 * isWishlisted : true
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
                private String venue_website;
                private String home_delivery_mov;
                private int start_days;
                private int end_days;
                private int collection_time;
                private int preparation_time;
                private String free_delivery;
                private String delivery_charge;
                private int delivery;
                private int delivery_distance;
                private int collection;
                private int venue_type;
                private String latitude;
                private String longitude;
                private String distance;
                private String opening_time;
                private String closing_time;
                private String stars;
                private String isClose;
                private int total_offers;
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

                public String getVenue_website() {
                    return venue_website;
                }

                public void setVenue_website(String venue_website) {
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

                public int getDelivery_distance() {
                    return delivery_distance;
                }

                public void setDelivery_distance(int delivery_distance) {
                    this.delivery_distance = delivery_distance;
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

                public String getStars() {
                    return stars;
                }

                public void setStars(String stars) {
                    this.stars = stars;
                }

                public String getIsClose() {
                    return isClose;
                }

                public void setIsClose(String isClose) {
                    this.isClose = isClose;
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
        }
    }
}
