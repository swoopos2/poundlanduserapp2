package com.poundland.retail.model.responseModel;

import java.util.List;

public class UpcomingVenueDetailResponseModel {


    /**
     * status : true
     * message : Venue Details page Information!
     * venueDetails : {"id":1,"name":"FOREVER 21","venue_type":1,"venue_category":null,"address_1":"London Luton Airport (LTN), Airport Way, Luton, UK","address_2":"Airport","post_code":"LU2 9LY","city_name":null,"country":"United Kingdom","latitude":"51.87626460000001","longitude":"-0.3717471000000001","opening_time":"10:10","closing_time":"14:30","status":1,"image":"uploaded/demo_venue/15982468950.jpg","image_name":"shutterstock_149255888-1-1-696x464-1-1-696x464.jpg","created_at":"2020-08-07 15:33:06","updated_at":"2020-09-01 06:31:31","distance":"0.00"}
     * productList : {"current_page":1,"data":[{"id":1,"name":"Lenovo","venue_id":1,"price":"400.00","stars":5,"image":"uploaded/demo_product/15968109420.jpg","status":1,"created_at":"2020-08-07 15:34:29","updated_at":"2020-08-07 15:53:53"},{"id":2,"name":"Hp  Laptop","venue_id":1,"price":"22.00","stars":3,"image":"uploaded/demo_product/15968121220.jpg","status":1,"created_at":"2020-08-07 15:55:22","updated_at":"2020-08-07 15:55:22"},{"id":3,"name":"HP EliteBook 840","venue_id":1,"price":"750.00","stars":4,"image":"uploaded/demo_product/15968121700.jpeg","status":1,"created_at":"2020-08-07 15:56:10","updated_at":"2020-08-07 15:57:39"},{"id":4,"name":"Philips AC1215/20 Air purifier","venue_id":1,"price":"100.00","stars":4,"image":"uploaded/demo_product/15968121880.jpg","status":1,"created_at":"2020-08-07 15:56:28","updated_at":"2020-08-07 15:56:28"}],"first_page_url":"https://swooposretailuk.com/admin/public/api/swoope/upcomingvenueDetails?page=1","from":1,"last_page":1,"last_page_url":"https://swooposretailuk.com/admin/public/api/swoope/upcomingvenueDetails?page=1","next_page_url":null,"path":"https://swooposretailuk.com/admin/public/api/swoope/upcomingvenueDetails","per_page":12,"prev_page_url":null,"to":4,"total":4}
     */

    private boolean status;
    private String message;
    private VenueDetailsBean venueDetails;
    private ProductListBean productList;

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

    public VenueDetailsBean getVenueDetails() {
        return venueDetails;
    }

    public void setVenueDetails(VenueDetailsBean venueDetails) {
        this.venueDetails = venueDetails;
    }

    public ProductListBean getProductList() {
        return productList;
    }

    public void setProductList(ProductListBean productList) {
        this.productList = productList;
    }

    public static class VenueDetailsBean {
        /**
         * id : 1
         * name : FOREVER 21
         * venue_type : 1
         * venue_category : null
         * address_1 : London Luton Airport (LTN), Airport Way, Luton, UK
         * address_2 : Airport
         * post_code : LU2 9LY
         * city_name : null
         * country : United Kingdom
         * latitude : 51.87626460000001
         * longitude : -0.3717471000000001
         * opening_time : 10:10
         * closing_time : 14:30
         * status : 1
         * image : uploaded/demo_venue/15982468950.jpg
         * image_name : shutterstock_149255888-1-1-696x464-1-1-696x464.jpg
         * created_at : 2020-08-07 15:33:06
         * updated_at : 2020-09-01 06:31:31
         * distance : 0.00
         */

        private int id;
        private String name;
        private int venue_type;
        private Object venue_category;
        private String address_1;
        private String address_2;
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
        private String isClose;;

        public String getIsClose() {
            return isClose;
        }

        public void setIsClose(String isClose) {
            this.isClose = isClose;
        }
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

        public String getAddress_2() {
            return address_2;
        }

        public void setAddress_2(String address_2) {
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
    }

    public static class ProductListBean {
        /**
         * current_page : 1
         * data : [{"id":1,"name":"Lenovo","venue_id":1,"price":"400.00","stars":5,"image":"uploaded/demo_product/15968109420.jpg","status":1,"created_at":"2020-08-07 15:34:29","updated_at":"2020-08-07 15:53:53"},{"id":2,"name":"Hp  Laptop","venue_id":1,"price":"22.00","stars":3,"image":"uploaded/demo_product/15968121220.jpg","status":1,"created_at":"2020-08-07 15:55:22","updated_at":"2020-08-07 15:55:22"},{"id":3,"name":"HP EliteBook 840","venue_id":1,"price":"750.00","stars":4,"image":"uploaded/demo_product/15968121700.jpeg","status":1,"created_at":"2020-08-07 15:56:10","updated_at":"2020-08-07 15:57:39"},{"id":4,"name":"Philips AC1215/20 Air purifier","venue_id":1,"price":"100.00","stars":4,"image":"uploaded/demo_product/15968121880.jpg","status":1,"created_at":"2020-08-07 15:56:28","updated_at":"2020-08-07 15:56:28"}]
         * first_page_url : https://swooposretailuk.com/admin/public/api/swoope/upcomingvenueDetails?page=1
         * from : 1
         * last_page : 1
         * last_page_url : https://swooposretailuk.com/admin/public/api/swoope/upcomingvenueDetails?page=1
         * next_page_url : null
         * path : https://swooposretailuk.com/admin/public/api/swoope/upcomingvenueDetails
         * per_page : 12
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

        public Object getNext_page_url() {
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
             * id : 1
             * name : Lenovo
             * venue_id : 1
             * price : 400.00
             * stars : 5
             * image : uploaded/demo_product/15968109420.jpg
             * status : 1
             * created_at : 2020-08-07 15:34:29
             * updated_at : 2020-08-07 15:53:53
             */

            private int id;
            private String name;
            private int venue_id;
            private String price;
            private float stars;
            private String image;
            private int status;
            private String created_at;
            private String updated_at;
            private String distance;


            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

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

            public int getVenue_id() {
                return venue_id;
            }

            public void setVenue_id(int venue_id) {
                this.venue_id = venue_id;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
                this.price = price;
            }

            public float getStars() {
                return stars;
            }

            public void setStars(float stars) {
                this.stars = stars;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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
        }
    }
}
