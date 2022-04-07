package com.poundland.retail.model.responseModel;

import java.util.List;

public class MessageDetailResponseModel {


    /**
     * status : true
     * message : List Of messages.
     * messages : {"current_page":1,"data":[{"id":245,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"368","venue_id":"2019120307071210","merchant_id":10,"user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":257,"created_at":"2019-12-11 13:49:04","updated_at":"2019-12-12 10:36:59","VenueId":"2019120307071210","venue_name":"Petalon Flowers London","phone_number":"44121212121","venue_email":"pfl@imailt.com","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"N1 5LQ","status":1,"latitude":"51.5341388","longitude":"-0.08055539999998018"},{"id":257,"From":"venue","To":"cust","message":"Order is Rejected","order_id":"368","venue_id":"2019120307071210","merchant_id":10,"user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":25,"staff_name":"JohnCanady","prevmsg":245,"nextmsg":271,"created_at":"2019-12-12 10:36:59","updated_at":"2019-12-12 11:53:32","VenueId":"2019120307071210","venue_name":"Petalon Flowers London","phone_number":"44121212121","venue_email":"pfl@imailt.com","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"N1 5LQ","status":1,"latitude":"51.5341388","longitude":"-0.08055539999998018"},{"id":271,"From":"cust","To":"venue","message":"Test","order_id":"368","venue_id":"2019120307071210","merchant_id":10,"user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":0,"staff_name":"","prevmsg":257,"nextmsg":272,"created_at":"2019-12-12 11:53:32","updated_at":"2019-12-12 12:27:36","VenueId":"2019120307071210","venue_name":"Petalon Flowers London","phone_number":"44121212121","venue_email":"pfl@imailt.com","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"N1 5LQ","status":1,"latitude":"51.5341388","longitude":"-0.08055539999998018"},{"id":272,"From":"cust","To":"venue","message":"Test never let me down","order_id":"368","venue_id":"2019120307071210","merchant_id":10,"user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":0,"staff_name":"","prevmsg":271,"nextmsg":273,"created_at":"2019-12-12 12:27:36","updated_at":"2019-12-12 12:28:06","VenueId":"2019120307071210","venue_name":"Petalon Flowers London","phone_number":"44121212121","venue_email":"pfl@imailt.com","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"N1 5LQ","status":1,"latitude":"51.5341388","longitude":"-0.08055539999998018"},{"id":273,"From":"cust","To":"venue","message":"i m fedded","order_id":"368","venue_id":"2019120307071210","merchant_id":10,"user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":0,"staff_name":"","prevmsg":272,"nextmsg":0,"created_at":"2019-12-12 12:28:06","updated_at":"2019-12-12 12:28:06","VenueId":"2019120307071210","venue_name":"Petalon Flowers London","phone_number":"44121212121","venue_email":"pfl@imailt.com","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"N1 5LQ","status":1,"latitude":"51.5341388","longitude":"-0.08055539999998018"}],"first_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/messageDetails?page=1","from":1,"last_page":1,"last_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/messageDetails?page=1","next_page_url":null,"path":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/messageDetails","per_page":20,"prev_page_url":null,"to":5,"total":5}
     */

    private boolean status;
    private String message;
    private MessagesBean messages;

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

    public MessagesBean getMessages() {
        return messages;
    }

    public void setMessages(MessagesBean messages) {
        this.messages = messages;
    }

    public static class MessagesBean {
        /**
         * current_page : 1
         * data : [{"id":245,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"368","venue_id":"2019120307071210","merchant_id":10,"user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":257,"created_at":"2019-12-11 13:49:04","updated_at":"2019-12-12 10:36:59","VenueId":"2019120307071210","venue_name":"Petalon Flowers London","phone_number":"44121212121","venue_email":"pfl@imailt.com","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"N1 5LQ","status":1,"latitude":"51.5341388","longitude":"-0.08055539999998018"},{"id":257,"From":"venue","To":"cust","message":"Order is Rejected","order_id":"368","venue_id":"2019120307071210","merchant_id":10,"user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":25,"staff_name":"JohnCanady","prevmsg":245,"nextmsg":271,"created_at":"2019-12-12 10:36:59","updated_at":"2019-12-12 11:53:32","VenueId":"2019120307071210","venue_name":"Petalon Flowers London","phone_number":"44121212121","venue_email":"pfl@imailt.com","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"N1 5LQ","status":1,"latitude":"51.5341388","longitude":"-0.08055539999998018"},{"id":271,"From":"cust","To":"venue","message":"Test","order_id":"368","venue_id":"2019120307071210","merchant_id":10,"user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":0,"staff_name":"","prevmsg":257,"nextmsg":272,"created_at":"2019-12-12 11:53:32","updated_at":"2019-12-12 12:27:36","VenueId":"2019120307071210","venue_name":"Petalon Flowers London","phone_number":"44121212121","venue_email":"pfl@imailt.com","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"N1 5LQ","status":1,"latitude":"51.5341388","longitude":"-0.08055539999998018"},{"id":272,"From":"cust","To":"venue","message":"Test never let me down","order_id":"368","venue_id":"2019120307071210","merchant_id":10,"user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":0,"staff_name":"","prevmsg":271,"nextmsg":273,"created_at":"2019-12-12 12:27:36","updated_at":"2019-12-12 12:28:06","VenueId":"2019120307071210","venue_name":"Petalon Flowers London","phone_number":"44121212121","venue_email":"pfl@imailt.com","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"N1 5LQ","status":1,"latitude":"51.5341388","longitude":"-0.08055539999998018"},{"id":273,"From":"cust","To":"venue","message":"i m fedded","order_id":"368","venue_id":"2019120307071210","merchant_id":10,"user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":0,"staff_name":"","prevmsg":272,"nextmsg":0,"created_at":"2019-12-12 12:28:06","updated_at":"2019-12-12 12:28:06","VenueId":"2019120307071210","venue_name":"Petalon Flowers London","phone_number":"44121212121","venue_email":"pfl@imailt.com","venue_images":"1575356832.Petalon-98.jpg","address_1":"263 Hoxton St, Whitmore Estate, London, UK","address_2":null,"city_name":"London","country":"United Kingdom","post_code":"N1 5LQ","status":1,"latitude":"51.5341388","longitude":"-0.08055539999998018"}]
         * first_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/messageDetails?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/messageDetails?page=1
         * next_page_url : null
         * path : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/messageDetails
         * per_page : 20
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
             * id : 245
             * From : venue
             * To : cust
             * message : Order has been placed successfully.
             * order_id : 368
             * venue_id : 2019120307071210
             * merchant_id : 10
             * user_id : 9
             * cust_type : ecom
             * is_read : 0
             * ecom_is_read : 1
             * staff_id : null
             * staff_name : null
             * prevmsg : 0
             * nextmsg : 257
             * created_at : 2019-12-11 13:49:04
             * updated_at : 2019-12-12 10:36:59
             * VenueId : 2019120307071210
             * venue_name : Petalon Flowers London
             * phone_number : 44121212121
             * venue_email : pfl@imailt.com
             * venue_images : 1575356832.Petalon-98.jpg
             * address_1 : 263 Hoxton St, Whitmore Estate, London, UK
             * address_2 : null
             * city_name : London
             * country : United Kingdom
             * post_code : N1 5LQ
             * status : 1
             * latitude : 51.5341388
             * longitude : -0.08055539999998018
             */

            private int id;
            private String From;
            private String To;
            private String message;
            private String order_id;
            private String venue_id;
            private int merchant_id;
            private int user_id;
            private String cust_type;
            private int is_read;
            private int ecom_is_read;
            private Object staff_id;
            private String staff_name;
            private int prevmsg;
            private int nextmsg;
            private String created_at;
            private String updated_at;
            private String VenueId;
            private String venue_name;
            private String phone_number;
            private String venue_email;
            private String venue_images;
            private String address_1;
            private Object address_2;
            private String city_name;
            private String country;
            private String post_code;
            private int status;
            private String latitude;
            private String longitude;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getFrom() {
                return From;
            }

            public void setFrom(String From) {
                this.From = From;
            }

            public String getTo() {
                return To;
            }

            public void setTo(String To) {
                this.To = To;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
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

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getCust_type() {
                return cust_type;
            }

            public void setCust_type(String cust_type) {
                this.cust_type = cust_type;
            }

            public int getIs_read() {
                return is_read;
            }

            public void setIs_read(int is_read) {
                this.is_read = is_read;
            }

            public int getEcom_is_read() {
                return ecom_is_read;
            }

            public void setEcom_is_read(int ecom_is_read) {
                this.ecom_is_read = ecom_is_read;
            }

            public Object getStaff_id() {
                return staff_id;
            }

            public void setStaff_id(Object staff_id) {
                this.staff_id = staff_id;
            }

            public String getStaff_name() {
                return staff_name;
            }

            public void setStaff_name(String staff_name) {
                this.staff_name = staff_name;
            }

            public int getPrevmsg() {
                return prevmsg;
            }

            public void setPrevmsg(int prevmsg) {
                this.prevmsg = prevmsg;
            }

            public int getNextmsg() {
                return nextmsg;
            }

            public void setNextmsg(int nextmsg) {
                this.nextmsg = nextmsg;
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

            public String getVenueId() {
                return VenueId;
            }

            public void setVenueId(String VenueId) {
                this.VenueId = VenueId;
            }

            public String getVenue_name() {
                return venue_name;
            }

            public void setVenue_name(String venue_name) {
                this.venue_name = venue_name;
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

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
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
        }
    }
}

