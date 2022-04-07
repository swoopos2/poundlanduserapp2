package com.poundland.retail.model.responseModel;

import java.util.List;

public class NotificationResponseModel {

    /**
     * status : true
     * message : List of notifications.
     * notifications : {"current_page":1,"data":[{"id":"672c19ed-1093-4bd0-8788-b99c386ef550","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":804}],"created_at":"2020-01-29 07:31:49","title":"New Order","message":"Your order has been done successfully.Your package containing Goose Fat Potatoes and 1 others item(s).Thanks for shopping!","type":1,"order_id":804,"image":"uploaded/products/4194615764986130.jpeg"},{"id":"2a767890-3b27-4f95-9f2e-821c6d39f9fb","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":799}],"created_at":"2020-01-29 06:40:50","title":"New Order","message":"Your order has been done successfully.Your package containing FlowersThanks for shopping!","type":1,"order_id":799,"image":"uploaded/products/6313015790868850.png"},{"id":"06c761b7-4c1f-4abb-8116-bc76cd2f5265","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":798}],"created_at":"2020-01-29 06:39:13","title":"New Order","message":"Your order has been done successfully.Your package containing FlowersThanks for shopping!","type":1,"order_id":798,"image":"uploaded/products/6313015790868850.png"},{"id":"1fdc8dfd-4cf2-4c0a-900f-c3bd4020f1d2","data":[{"title":"Order Rejected","message":"Your Order 202001281320149 has been rejected.","type":1,"order_id":789}],"created_at":"2020-01-28 14:46:42","title":"Order Rejected","message":"Your Order 202001281320149 has been rejected.Your package containing Enfamil Follow Up Formula A+ S...Thanks for shopping!","type":1,"order_id":789,"image":"uploaded/products/3857715797865620.jpeg"},{"id":"10517131-e3b4-405a-8275-16f82ea8f191","data":[{"title":"Order Rejected","message":"Your Order 202001281436129 has been rejected.","type":1,"order_id":796}],"created_at":"2020-01-28 14:46:38","title":"Order Rejected","message":"Your Order 202001281436129 has been rejected.Your package containing Mamaearth Complete Baby Care K...Thanks for shopping!","type":1,"order_id":796,"image":"uploaded/products/4555215797866310.jpeg"},{"id":"e557a80d-8dda-49b9-8f8c-a6ef77d70e7e","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":796}],"created_at":"2020-01-28 14:36:17","title":"New Order","message":"Your order has been done successfully.Your package containing Mamaearth Complete Baby Care K...Thanks for shopping!","type":1,"order_id":796,"image":"uploaded/products/4555215797866310.jpeg"},{"id":"55a12774-b0db-4042-a0d8-cb7ea8bf0af4","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":794}],"created_at":"2020-01-28 14:21:29","title":"New Order","message":"Your order has been done successfully.Your package containing Family Pack Bananas 10 per pac...Thanks for shopping!","type":1,"order_id":794,"image":"uploaded/products/5374815764911100.jpeg"},{"id":"5af0e041-3153-4f02-a392-77adba6768db","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":790}],"created_at":"2020-01-28 13:25:38","title":"New Order","message":"Your order has been done successfully.Your package containing Goose Fat PotatoesThanks for shopping!","type":1,"order_id":790,"image":"uploaded/products/4194615764986130.jpeg"},{"id":"d8ab01e8-0a15-4d3f-8ab2-028c7fde2540","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":789}],"created_at":"2020-01-28 13:20:17","title":"New Order","message":"Your order has been done successfully.Your package containing Enfamil Follow Up Formula A+ S...Thanks for shopping!","type":1,"order_id":789,"image":"uploaded/products/3857715797865620.jpeg"}],"first_page_url":"http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/fetchUserNotifications?page=1","from":1,"last_page":1,"last_page_url":"http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/fetchUserNotifications?page=1","next_page_url":null,"path":"http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/fetchUserNotifications","per_page":10,"prev_page_url":null,"to":9,"total":9}
     */

    private boolean status;
    private String message;
    private NotificationsBean notifications;

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

    public NotificationsBean getNotifications() {
        return notifications;
    }

    public void setNotifications(NotificationsBean notifications) {
        this.notifications = notifications;
    }

    public static class NotificationsBean {
        /**
         * current_page : 1
         * data : [{"id":"672c19ed-1093-4bd0-8788-b99c386ef550","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":804}],"created_at":"2020-01-29 07:31:49","title":"New Order","message":"Your order has been done successfully.Your package containing Goose Fat Potatoes and 1 others item(s).Thanks for shopping!","type":1,"order_id":804,"image":"uploaded/products/4194615764986130.jpeg"},{"id":"2a767890-3b27-4f95-9f2e-821c6d39f9fb","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":799}],"created_at":"2020-01-29 06:40:50","title":"New Order","message":"Your order has been done successfully.Your package containing FlowersThanks for shopping!","type":1,"order_id":799,"image":"uploaded/products/6313015790868850.png"},{"id":"06c761b7-4c1f-4abb-8116-bc76cd2f5265","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":798}],"created_at":"2020-01-29 06:39:13","title":"New Order","message":"Your order has been done successfully.Your package containing FlowersThanks for shopping!","type":1,"order_id":798,"image":"uploaded/products/6313015790868850.png"},{"id":"1fdc8dfd-4cf2-4c0a-900f-c3bd4020f1d2","data":[{"title":"Order Rejected","message":"Your Order 202001281320149 has been rejected.","type":1,"order_id":789}],"created_at":"2020-01-28 14:46:42","title":"Order Rejected","message":"Your Order 202001281320149 has been rejected.Your package containing Enfamil Follow Up Formula A+ S...Thanks for shopping!","type":1,"order_id":789,"image":"uploaded/products/3857715797865620.jpeg"},{"id":"10517131-e3b4-405a-8275-16f82ea8f191","data":[{"title":"Order Rejected","message":"Your Order 202001281436129 has been rejected.","type":1,"order_id":796}],"created_at":"2020-01-28 14:46:38","title":"Order Rejected","message":"Your Order 202001281436129 has been rejected.Your package containing Mamaearth Complete Baby Care K...Thanks for shopping!","type":1,"order_id":796,"image":"uploaded/products/4555215797866310.jpeg"},{"id":"e557a80d-8dda-49b9-8f8c-a6ef77d70e7e","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":796}],"created_at":"2020-01-28 14:36:17","title":"New Order","message":"Your order has been done successfully.Your package containing Mamaearth Complete Baby Care K...Thanks for shopping!","type":1,"order_id":796,"image":"uploaded/products/4555215797866310.jpeg"},{"id":"55a12774-b0db-4042-a0d8-cb7ea8bf0af4","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":794}],"created_at":"2020-01-28 14:21:29","title":"New Order","message":"Your order has been done successfully.Your package containing Family Pack Bananas 10 per pac...Thanks for shopping!","type":1,"order_id":794,"image":"uploaded/products/5374815764911100.jpeg"},{"id":"5af0e041-3153-4f02-a392-77adba6768db","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":790}],"created_at":"2020-01-28 13:25:38","title":"New Order","message":"Your order has been done successfully.Your package containing Goose Fat PotatoesThanks for shopping!","type":1,"order_id":790,"image":"uploaded/products/4194615764986130.jpeg"},{"id":"d8ab01e8-0a15-4d3f-8ab2-028c7fde2540","data":[{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":789}],"created_at":"2020-01-28 13:20:17","title":"New Order","message":"Your order has been done successfully.Your package containing Enfamil Follow Up Formula A+ S...Thanks for shopping!","type":1,"order_id":789,"image":"uploaded/products/3857715797865620.jpeg"}]
         * first_page_url : http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/fetchUserNotifications?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/fetchUserNotifications?page=1
         * next_page_url : null
         * path : http://swooposretailuk.com/SWOOPOSRETAILBACKOFFICE/public/api/swoope/fetchUserNotifications
         * per_page : 10
         * prev_page_url : null
         * to : 9
         * total : 9
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
        private List<DataBeanX> data;

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

        public List<DataBeanX> getData() {
            return data;
        }

        public void setData(List<DataBeanX> data) {
            this.data = data;
        }

        public static class DataBeanX {
            /**
             * id : 672c19ed-1093-4bd0-8788-b99c386ef550
             * data : [{"title":"New Order","message":"Your order has been done successfully.","type":1,"order_id":804}]
             * created_at : 2020-01-29 07:31:49
             * title : New Order
             * message : Your order has been done successfully.Your package containing Goose Fat Potatoes and 1 others item(s).Thanks for shopping!
             * type : 1
             * order_id : 804
             * image : uploaded/products/4194615764986130.jpeg
             */

            private String id;
            private String created_at;
            private String title;
            private String message;
            private int type;
            private int order_id;
            private String image;
            private List<DataBean> data;


            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCreated_at() {
                return created_at;
            }

            public void setCreated_at(String created_at) {
                this.created_at = created_at;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getMessage() {
                return message;
            }

            public void setMessage(String message) {
                this.message = message;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public List<DataBean> getData() {
                return data;
            }

            public void setData(List<DataBean> data) {
                this.data = data;
            }

            public static class DataBean {
                /**
                 * title : New Order
                 * message : Your order has been done successfully.
                 * type : 1
                 * order_id : 804
                 */

                private String title;
                private String message;
                private int type;
                private int order_id;

                public String getTitle() {
                    return title;
                }

                public void setTitle(String title) {
                    this.title = title;
                }

                public String getMessage() {
                    return message;
                }

                public void setMessage(String message) {
                    this.message = message;
                }

                public int getType() {
                    return type;
                }

                public void setType(int type) {
                    this.type = type;
                }

                public int getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(int order_id) {
                    this.order_id = order_id;
                }
            }
        }
    }
}
