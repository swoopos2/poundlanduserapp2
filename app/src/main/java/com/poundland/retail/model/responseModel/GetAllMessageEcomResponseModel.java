package com.poundland.retail.model.responseModel;

import java.util.List;

public class GetAllMessageEcomResponseModel {


    /**
     * status : true
     * messages : {"current_page":1,"data":[{"id":245,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"368","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 13:49:04","updated_at":"2019-12-11 13:49:04","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":244,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"367","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 13:42:36","updated_at":"2019-12-11 13:42:36","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":243,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"366","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 13:39:02","updated_at":"2019-12-11 13:39:02","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":242,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"364","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 13:11:16","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":241,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"363","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 13:07:59","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":240,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"362","venue_id":"201911011148462","merchant_id":"2","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 12:54:02","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"201911011148462","venue_name":"LillyWhites","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg,20191121064555.lillywhites.jpg,20191121064555.lillywhites-sports-shopdepartment-store-londons-west-end-london-engtand-DA53PJ.jpg,,,","phone_number":"5468496484"},{"id":239,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"361","venue_id":"201911011148462","merchant_id":"2","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 12:47:27","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"201911011148462","venue_name":"LillyWhites","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg,20191121064555.lillywhites.jpg,20191121064555.lillywhites-sports-shopdepartment-store-londons-west-end-london-engtand-DA53PJ.jpg,,,","phone_number":"5468496484"},{"id":229,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"351","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-10 12:02:38","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":226,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"331","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-10 11:46:00","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":214,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"304","venue_id":"201911011148462","merchant_id":"2","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-09 08:52:42","updated_at":"2019-12-10 03:10:29","count":1,"venueid":"201911011148462","venue_name":"LillyWhites","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg,20191121064555.lillywhites.jpg,20191121064555.lillywhites-sports-shopdepartment-store-londons-west-end-london-engtand-DA53PJ.jpg,,,","phone_number":"5468496484"}],"first_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getallMessageEcomm?page=1","from":1,"last_page":4,"last_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getallMessageEcomm?page=4","next_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getallMessageEcomm?page=2","path":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getallMessageEcomm","per_page":10,"prev_page_url":null,"to":10,"total":33}
     */

    private boolean status;
    private MessagesBean messages;
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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
         * data : [{"id":245,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"368","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 13:49:04","updated_at":"2019-12-11 13:49:04","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":244,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"367","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 13:42:36","updated_at":"2019-12-11 13:42:36","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":243,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"366","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":0,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 13:39:02","updated_at":"2019-12-11 13:39:02","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":242,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"364","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 13:11:16","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":241,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"363","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 13:07:59","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":240,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"362","venue_id":"201911011148462","merchant_id":"2","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 12:54:02","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"201911011148462","venue_name":"LillyWhites","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg,20191121064555.lillywhites.jpg,20191121064555.lillywhites-sports-shopdepartment-store-londons-west-end-london-engtand-DA53PJ.jpg,,,","phone_number":"5468496484"},{"id":239,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"361","venue_id":"201911011148462","merchant_id":"2","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-11 12:47:27","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"201911011148462","venue_name":"LillyWhites","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg,20191121064555.lillywhites.jpg,20191121064555.lillywhites-sports-shopdepartment-store-londons-west-end-london-engtand-DA53PJ.jpg,,,","phone_number":"5468496484"},{"id":229,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"351","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-10 12:02:38","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":226,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"331","venue_id":"2019120307071210","merchant_id":"10","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-10 11:46:00","updated_at":"2019-12-11 13:38:51","count":1,"venueid":"2019120307071210","venue_name":"Petalon Flowers London","venue_images":"1575356832.Petalon-98.jpg","phone_number":"44121212121"},{"id":214,"From":"venue","To":"cust","message":"Order has been placed successfully.","order_id":"304","venue_id":"201911011148462","merchant_id":"2","user_id":9,"cust_type":"ecom","is_read":0,"ecom_is_read":1,"staff_id":null,"staff_name":null,"prevmsg":0,"nextmsg":0,"created_at":"2019-12-09 08:52:42","updated_at":"2019-12-10 03:10:29","count":1,"venueid":"201911011148462","venue_name":"LillyWhites","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg,20191121064555.lillywhites.jpg,20191121064555.lillywhites-sports-shopdepartment-store-londons-west-end-london-engtand-DA53PJ.jpg,,,","phone_number":"5468496484"}]
         * first_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getallMessageEcomm?page=1
         * from : 1
         * last_page : 4
         * last_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getallMessageEcomm?page=4
         * next_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getallMessageEcomm?page=2
         * path : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getallMessageEcomm
         * per_page : 10
         * prev_page_url : null
         * to : 10
         * total : 33
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
             * ecom_is_read : 0
             * staff_id : null
             * staff_name : null
             * prevmsg : 0
             * nextmsg : 0
             * created_at : 2019-12-11 13:49:04
             * updated_at : 2019-12-11 13:49:04
             * count : 1
             * venueid : 2019120307071210
             * venue_name : Petalon Flowers London
             * venue_images : 1575356832.Petalon-98.jpg
             * phone_number : 44121212121
             */

            private int id;
            private String From;
            private String To;
            private String message;
            private String order_id;
            private String venue_id;
            private String merchant_id;
            private int user_id;
            private String cust_type;
            private int is_read;
            private int ecom_is_read;
            private Object staff_id;
            private Object staff_name;
            private int prevmsg;
            private int nextmsg;
            private String created_at;
            private String updated_at;
            private int count;
            private int unread_msg;
            private String venueid;
            private String unique_code;
            private String net_amount;
            private String venue_name;
            private String venue_images;
            private String phone_number;

            public String getUnique_code() {
                return unique_code;
            }

            public void setUnique_code(String unique_code) {
                this.unique_code = unique_code;
            }

            public String getNet_amount() {
                return net_amount;
            }

            public void setNet_amount(String net_amount) {
                this.net_amount = net_amount;
            }

            public int getUnread_msg() {
                return unread_msg;
            }

            public void setUnread_msg(int unread_msg) {
                this.unread_msg = unread_msg;
            }

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

            public String getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(String merchant_id) {
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

            public Object getStaff_name() {
                return staff_name;
            }

            public void setStaff_name(Object staff_name) {
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

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getVenueid() {
                return venueid;
            }

            public void setVenueid(String venueid) {
                this.venueid = venueid;
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

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }
        }
    }
}
