package com.poundland.retail.model.responseModel;

import java.util.List;

public class UpcomingVenueResponseModel {

    /**
     * status : true
     * message : List of upcoming venues
     * upcomingVenues : {"current_page":1,"data":[{"id":12,"name":"Vegan Store","venue_type":1,"venue_category":null,"address_1":"Wolverhampton, UK","address_2":null,"post_code":"WV30DS","city_name":"Wolverhampton","country":"United Kingdom","latitude":null,"longitude":null,"opening_time":"07:00","closing_time":"20:00","status":1,"image":"uploaded/demo_venue/16063721380.jpeg","image_name":"hcmp61763_113013.jpeg","created_at":"2020-11-26 06:28:58","updated_at":"2020-11-26 06:28:58","distance":null,"isClose":"0"},{"id":10,"name":"HOUSE OF FRASER","venue_type":1,"venue_category":null,"address_1":"28 Durward Street, London, UK","address_2":null,"post_code":"WV30DS","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.5193473","longitude":"-0.06225530000000001","opening_time":"06:50","closing_time":"19:00","status":1,"image":"uploaded/demo_venue/15989454650.jpg","image_name":"34-346196_house-of-fraser-store-closures-to-cost-6.jpg","created_at":"2020-09-01 08:31:05","updated_at":"2020-09-01 08:31:22","distance":"4,054.17","isClose":"1"},{"id":9,"name":"ICELAND","venue_type":1,"venue_category":null,"address_1":"28 Harley Street, London, UK","address_2":null,"post_code":"W1G 9QY","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.51814359999999","longitude":"-0.1465646","opening_time":"06:00","closing_time":"05:50","status":1,"image":"uploaded/demo_venue/15989431530.jpg","image_name":"TQVQAAF5WVEYVDYDQHRW6MWHOI.jpg","created_at":"2020-09-01 07:52:33","updated_at":"2020-09-01 07:55:22","distance":"4,057.46","isClose":"1"},{"id":7,"name":"Jack Wills","venue_type":1,"venue_category":null,"address_1":"Oxford, Park End Street, Oxford, UK","address_2":null,"post_code":"OX1 1HS","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.7534694","longitude":"-1.2699454","opening_time":"04:20","closing_time":"08:40","status":1,"image":"uploaded/demo_venue/15982472200.jpg","image_name":"Jack-Wills-Westfield-1_660-1.jpg","created_at":"2020-08-24 06:33:40","updated_at":"2020-09-01 06:27:42","distance":"4,090.95","isClose":"1"},{"id":6,"name":"PENNEYS","venue_type":1,"venue_category":null,"address_1":"215 Banbury Road, Summertown, Oxford, UK","address_2":null,"post_code":"OX2 7HQ","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.7767268","longitude":"-1.2649905","opening_time":"05:30","closing_time":"09:10","status":1,"image":"uploaded/demo_venue/15982471680.png","image_name":"2_Screen-Shot-2019-07-25-at-110401.png","created_at":"2020-08-24 06:32:48","updated_at":"2020-08-31 13:02:43","distance":"4,089.80","isClose":"1"},{"id":5,"name":"MotherCare","venue_type":1,"venue_category":null,"address_1":"Three UK Office, Grenfell Road, Maidenhead, UK","address_2":null,"post_code":"SL6 1EH","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.5197222","longitude":"-0.7224999999999999","opening_time":"05:10","closing_time":"09:20","status":1,"image":"uploaded/demo_venue/15982471140.jpg","image_name":"000ae3d6-1600.jpg","created_at":"2020-08-24 06:31:54","updated_at":"2020-08-31 13:01:18","distance":"4,079.53","isClose":"1"},{"id":4,"name":"Ron Flower Sports","venue_type":1,"venue_category":"Sports","address_1":"22 Harley Street, London, UK","address_2":null,"post_code":"W1G 9AP","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.51796789999999","longitude":"-0.1462408","opening_time":"05:00","closing_time":"17:05","status":1,"image":"uploaded/demo_venue/15984313080.jpg","image_name":"1544014663_ron_flowers_logo.jpg","created_at":"2020-08-10 08:04:56","updated_at":"2020-08-31 12:55:23","distance":"4,057.45","isClose":"1"},{"id":3,"name":"99p Stores","venue_type":1,"venue_category":"Fashion,Clothing","address_1":"21 Harley Street, London, UK","address_2":null,"post_code":"W1G 9QD","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.5174828","longitude":"-0.1467476","opening_time":"08:00","closing_time":"21:30","status":1,"image":"uploaded/demo_venue/15982468330.jpeg","image_name":"99p-Stores-the-latest-victim-of-administration.jpeg","created_at":"2020-08-10 07:22:01","updated_at":"2020-08-31 12:54:31","distance":"4,057.49","isClose":"0"},{"id":2,"name":"One Stop","venue_type":1,"venue_category":null,"address_1":"One Stop Shopping, Walsall Road, Perry Barr, Birmingham, UK","address_2":null,"post_code":"B42 1AA","city_name":"Wolverhampton","country":"United Kingdom","latitude":"52.5181852","longitude":"-1.9030093","opening_time":"09:00","closing_time":"20:00","status":1,"image":"uploaded/demo_venue/15982469830.jpg","image_name":"0_AL_SOM_270219OneStop_005.jpg","created_at":"2020-08-07 15:53:53","updated_at":"2020-09-01 06:31:41","distance":"4,083.48","isClose":"0"},{"id":1,"name":"FOREVER 21","venue_type":1,"venue_category":null,"address_1":"London Luton Airport (LTN), Airport Way, Luton, UK","address_2":"Airport","post_code":"LU2 9LY","city_name":null,"country":"United Kingdom","latitude":"51.87626460000001","longitude":"-0.3717471000000001","opening_time":"10:10","closing_time":"14:30","status":1,"image":"uploaded/demo_venue/15982468950.jpg","image_name":"shutterstock_149255888-1-1-696x464-1-1-696x464.jpg","created_at":"2020-08-07 15:33:06","updated_at":"2020-10-09 09:49:38","distance":"4,051.78","isClose":"1"}],"first_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/upcomingvenues?page=1","from":1,"last_page":1,"last_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/upcomingvenues?page=1","next_page_url":null,"path":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/upcomingvenues","per_page":10,"prev_page_url":null,"to":10,"total":10}
     */

    private boolean status;
    private String message;
    private UpcomingVenuesBean upcomingVenues;

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

    public UpcomingVenuesBean getUpcomingVenues() {
        return upcomingVenues;
    }

    public void setUpcomingVenues(UpcomingVenuesBean upcomingVenues) {
        this.upcomingVenues = upcomingVenues;
    }

    public static class UpcomingVenuesBean {
        /**
         * current_page : 1
         * data : [{"id":12,"name":"Vegan Store","venue_type":1,"venue_category":null,"address_1":"Wolverhampton, UK","address_2":null,"post_code":"WV30DS","city_name":"Wolverhampton","country":"United Kingdom","latitude":null,"longitude":null,"opening_time":"07:00","closing_time":"20:00","status":1,"image":"uploaded/demo_venue/16063721380.jpeg","image_name":"hcmp61763_113013.jpeg","created_at":"2020-11-26 06:28:58","updated_at":"2020-11-26 06:28:58","distance":null,"isClose":"0"},{"id":10,"name":"HOUSE OF FRASER","venue_type":1,"venue_category":null,"address_1":"28 Durward Street, London, UK","address_2":null,"post_code":"WV30DS","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.5193473","longitude":"-0.06225530000000001","opening_time":"06:50","closing_time":"19:00","status":1,"image":"uploaded/demo_venue/15989454650.jpg","image_name":"34-346196_house-of-fraser-store-closures-to-cost-6.jpg","created_at":"2020-09-01 08:31:05","updated_at":"2020-09-01 08:31:22","distance":"4,054.17","isClose":"1"},{"id":9,"name":"ICELAND","venue_type":1,"venue_category":null,"address_1":"28 Harley Street, London, UK","address_2":null,"post_code":"W1G 9QY","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.51814359999999","longitude":"-0.1465646","opening_time":"06:00","closing_time":"05:50","status":1,"image":"uploaded/demo_venue/15989431530.jpg","image_name":"TQVQAAF5WVEYVDYDQHRW6MWHOI.jpg","created_at":"2020-09-01 07:52:33","updated_at":"2020-09-01 07:55:22","distance":"4,057.46","isClose":"1"},{"id":7,"name":"Jack Wills","venue_type":1,"venue_category":null,"address_1":"Oxford, Park End Street, Oxford, UK","address_2":null,"post_code":"OX1 1HS","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.7534694","longitude":"-1.2699454","opening_time":"04:20","closing_time":"08:40","status":1,"image":"uploaded/demo_venue/15982472200.jpg","image_name":"Jack-Wills-Westfield-1_660-1.jpg","created_at":"2020-08-24 06:33:40","updated_at":"2020-09-01 06:27:42","distance":"4,090.95","isClose":"1"},{"id":6,"name":"PENNEYS","venue_type":1,"venue_category":null,"address_1":"215 Banbury Road, Summertown, Oxford, UK","address_2":null,"post_code":"OX2 7HQ","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.7767268","longitude":"-1.2649905","opening_time":"05:30","closing_time":"09:10","status":1,"image":"uploaded/demo_venue/15982471680.png","image_name":"2_Screen-Shot-2019-07-25-at-110401.png","created_at":"2020-08-24 06:32:48","updated_at":"2020-08-31 13:02:43","distance":"4,089.80","isClose":"1"},{"id":5,"name":"MotherCare","venue_type":1,"venue_category":null,"address_1":"Three UK Office, Grenfell Road, Maidenhead, UK","address_2":null,"post_code":"SL6 1EH","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.5197222","longitude":"-0.7224999999999999","opening_time":"05:10","closing_time":"09:20","status":1,"image":"uploaded/demo_venue/15982471140.jpg","image_name":"000ae3d6-1600.jpg","created_at":"2020-08-24 06:31:54","updated_at":"2020-08-31 13:01:18","distance":"4,079.53","isClose":"1"},{"id":4,"name":"Ron Flower Sports","venue_type":1,"venue_category":"Sports","address_1":"22 Harley Street, London, UK","address_2":null,"post_code":"W1G 9AP","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.51796789999999","longitude":"-0.1462408","opening_time":"05:00","closing_time":"17:05","status":1,"image":"uploaded/demo_venue/15984313080.jpg","image_name":"1544014663_ron_flowers_logo.jpg","created_at":"2020-08-10 08:04:56","updated_at":"2020-08-31 12:55:23","distance":"4,057.45","isClose":"1"},{"id":3,"name":"99p Stores","venue_type":1,"venue_category":"Fashion,Clothing","address_1":"21 Harley Street, London, UK","address_2":null,"post_code":"W1G 9QD","city_name":"Wolverhampton","country":"United Kingdom","latitude":"51.5174828","longitude":"-0.1467476","opening_time":"08:00","closing_time":"21:30","status":1,"image":"uploaded/demo_venue/15982468330.jpeg","image_name":"99p-Stores-the-latest-victim-of-administration.jpeg","created_at":"2020-08-10 07:22:01","updated_at":"2020-08-31 12:54:31","distance":"4,057.49","isClose":"0"},{"id":2,"name":"One Stop","venue_type":1,"venue_category":null,"address_1":"One Stop Shopping, Walsall Road, Perry Barr, Birmingham, UK","address_2":null,"post_code":"B42 1AA","city_name":"Wolverhampton","country":"United Kingdom","latitude":"52.5181852","longitude":"-1.9030093","opening_time":"09:00","closing_time":"20:00","status":1,"image":"uploaded/demo_venue/15982469830.jpg","image_name":"0_AL_SOM_270219OneStop_005.jpg","created_at":"2020-08-07 15:53:53","updated_at":"2020-09-01 06:31:41","distance":"4,083.48","isClose":"0"},{"id":1,"name":"FOREVER 21","venue_type":1,"venue_category":null,"address_1":"London Luton Airport (LTN), Airport Way, Luton, UK","address_2":"Airport","post_code":"LU2 9LY","city_name":null,"country":"United Kingdom","latitude":"51.87626460000001","longitude":"-0.3717471000000001","opening_time":"10:10","closing_time":"14:30","status":1,"image":"uploaded/demo_venue/15982468950.jpg","image_name":"shutterstock_149255888-1-1-696x464-1-1-696x464.jpg","created_at":"2020-08-07 15:33:06","updated_at":"2020-10-09 09:49:38","distance":"4,051.78","isClose":"1"}]
         * first_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/upcomingvenues?page=1
         * from : 1
         * last_page : 1
         * last_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/upcomingvenues?page=1
         * next_page_url : null
         * path : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/upcomingvenues
         * per_page : 10
         * prev_page_url : null
         * to : 10
         * total : 10
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
             * isClose : 0
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
            private String latitude;
            private String longitude;
            private String opening_time;
            private String closing_time;
            private int status;
            private String image;
            private String image_name;
            private String created_at;
            private String updated_at;
            private String distance;
            private String isClose;

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

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
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
