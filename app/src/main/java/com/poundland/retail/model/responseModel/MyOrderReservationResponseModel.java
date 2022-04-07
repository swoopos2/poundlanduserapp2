package com.poundland.retail.model.responseModel;

import java.io.Serializable;
import java.util.List;

public class MyOrderReservationResponseModel {


    /**
     * status : true
     * message : My Reservation Oredr List.
     * reservations : {"current_page":1,"data":[{"id":1360,"cust_id":142,"venue_id":"202004271734165","reservation_id":"20210203060542142","check_in":"2021-02-03 11:40:00","check_out":"2021-02-03 15:30:00","total_guest":2,"amount":"4.00","status":1,"date":"2021-02-03","qr_code":"uploaded/reservationQr/6274616123323420.png","stripe_capture_id":null,"active_capture_id":"21020306BV06DP22KD41CNM","payment_gateway_type":null,"refunded":"0.00","table_no":null,"floor_id":null,"is_pos_completed":0,"is_smoking":1,"message_for_venue":null,"created_at":"2021-02-03 06:05:42","updated_at":"2021-02-03 06:06:22","is_payment":1,"card_number":"401200******1112","sub_total":"4.00","transaction_amt":"0.00","order_cancel_by":0,"app_type":"LOCAL","venue":{"id":34,"kyc_status":"pending","user_id":83,"venue_id":"202004271734165","merchant_id":5,"venue_name":"Dixi Chicken Pizza","venue_email":"dixicp@mailop7.com","address_1":"Southport Road, Liverpool, Bootle, UK","address_2":null,"post_code":"L31QR","city_name":"Liverpool","country":"United Kingdom","phone_number":"1231231231","venue_images":"20200622115653-resize.jpg","start_days":1,"end_days":3,"collection_time":70,"preparation_time":50,"is_booking_cancel_allow":1},"customer_details":{"id":142,"email":"aryanm22897@gmail.com","contact_no":"918920207597","cust_name":"Aryan Mishra"},"orders_masters":[{"id":6542,"unique_code":"142#0128","merchant_id":5,"venue_id":"202004271734165","source_type":"POS","payment_mode":"cash","user_addr_id":null,"reservation_id":"20210203060542142","table_no":"22","card_number":null,"order_date":"2021-02-03 14:49:09","delivery_type":"Reservati D/OkHttp: on","order_status":"4","total_items":2,"amt_without_tax_discount":"24.99","total_discount":"0.00","total_tax":"0.00","net_amount":"29.99","delivery_charge":"0.00","claim_stamp_reward_amt":"0.00","loyelty_consumed":"0.00","loyelty_used_amount":"0.00","status":4,"is_gift":0,"expected_delivery_date":null,"is_asap":0,"qr_order":null,"delivery_date":"","canceled_date":"","trackingData":[],"trackingStatus":[],"order_details":[{"id":9736,"order_id":6542,"product_qty":1,"product_id":"1184","modifier_id":1574,"billing_address":null,"attributes":"Single","selling_price":"5.99","discount_applied":"0.00","discount_type":"","offer_id":null,"tax_applied":"0.00","tax_id":0,"net_amount":"5.99","return_days":0,"item_status":"1","status":4,"avl_quantity":932,"modifier_image":null,"product_details":{"id":1184,"product_name":"Chicken Fillet Burger","product_description":"<p><br data-mce-bogus=\"1\"><\/p>","category_id":"260","brand_id":null,"product_image":"uploaded/products/7175415928224480.jpeg"}},{"id":9737,"order_id":6542,"product_qty":1,"product_id":"2568","modifier_id":3319,"billing_address":null,"attributes":"M","selling_price":"18.00","discount_applied":"0.00","discount_type":"","offer_id":null,"tax_applied":"0.00","tax_id":0,"net_amount":"19.00","return_days":0,"item_status":"1","status":4,"avl_quantity":null,"modifier_image":null,"product_details":{"id":2568,"product_name":"Chicken Pizza with red onion","product_description":"<p>Pizza tasty pizza...!!<\/p>","category_id":"656","brand_id":null,"product_image":"uploaded/products/6100016123315130.png"}}]}]}],"first_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getMyReservationOrder?page=1","from":1,"last_page":9,"last_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getMyReservationOrder?page=9","next_page_url":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getMyReservationOrder?page=2","path":"https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getMyReservationOrder","per_page":10,"prev_page_url":null,"to":10,"total":82}
     */

    private boolean status;
    private String message;
    private ReservationsBean reservations;

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

    public ReservationsBean getReservations() {
        return reservations;
    }

    public void setReservations(ReservationsBean reservations) {
        this.reservations = reservations;
    }

    public static class ReservationsBean {
        /**
         * current_page : 1
         * data : [{"id":1360,"cust_id":142,"venue_id":"202004271734165","reservation_id":"20210203060542142","check_in":"2021-02-03 11:40:00","check_out":"2021-02-03 15:30:00","total_guest":2,"amount":"4.00","status":1,"date":"2021-02-03","qr_code":"uploaded/reservationQr/6274616123323420.png","stripe_capture_id":null,"active_capture_id":"21020306BV06DP22KD41CNM","payment_gateway_type":null,"refunded":"0.00","table_no":null,"floor_id":null,"is_pos_completed":0,"is_smoking":1,"message_for_venue":null,"created_at":"2021-02-03 06:05:42","updated_at":"2021-02-03 06:06:22","is_payment":1,"card_number":"401200******1112","sub_total":"4.00","transaction_amt":"0.00","order_cancel_by":0,"app_type":"LOCAL","venue":{"id":34,"kyc_status":"pending","user_id":83,"venue_id":"202004271734165","merchant_id":5,"venue_name":"Dixi Chicken Pizza","venue_email":"dixicp@mailop7.com","address_1":"Southport Road, Liverpool, Bootle, UK","address_2":null,"post_code":"L31QR","city_name":"Liverpool","country":"United Kingdom","phone_number":"1231231231","venue_images":"20200622115653-resize.jpg","start_days":1,"end_days":3,"collection_time":70,"preparation_time":50,"is_booking_cancel_allow":1},"customer_details":{"id":142,"email":"aryanm22897@gmail.com","contact_no":"918920207597","cust_name":"Aryan Mishra"},"orders_masters":[{"id":6542,"unique_code":"142#0128","merchant_id":5,"venue_id":"202004271734165","source_type":"POS","payment_mode":"cash","user_addr_id":null,"reservation_id":"20210203060542142","table_no":"22","card_number":null,"order_date":"2021-02-03 14:49:09","delivery_type":"Reservati D/OkHttp: on","order_status":"4","total_items":2,"amt_without_tax_discount":"24.99","total_discount":"0.00","total_tax":"0.00","net_amount":"29.99","delivery_charge":"0.00","claim_stamp_reward_amt":"0.00","loyelty_consumed":"0.00","loyelty_used_amount":"0.00","status":4,"is_gift":0,"expected_delivery_date":null,"is_asap":0,"qr_order":null,"delivery_date":"","canceled_date":"","trackingData":[],"trackingStatus":[],"order_details":[{"id":9736,"order_id":6542,"product_qty":1,"product_id":"1184","modifier_id":1574,"billing_address":null,"attributes":"Single","selling_price":"5.99","discount_applied":"0.00","discount_type":"","offer_id":null,"tax_applied":"0.00","tax_id":0,"net_amount":"5.99","return_days":0,"item_status":"1","status":4,"avl_quantity":932,"modifier_image":null,"product_details":{"id":1184,"product_name":"Chicken Fillet Burger","product_description":"<p><br data-mce-bogus=\"1\"><\/p>","category_id":"260","brand_id":null,"product_image":"uploaded/products/7175415928224480.jpeg"}},{"id":9737,"order_id":6542,"product_qty":1,"product_id":"2568","modifier_id":3319,"billing_address":null,"attributes":"M","selling_price":"18.00","discount_applied":"0.00","discount_type":"","offer_id":null,"tax_applied":"0.00","tax_id":0,"net_amount":"19.00","return_days":0,"item_status":"1","status":4,"avl_quantity":null,"modifier_image":null,"product_details":{"id":2568,"product_name":"Chicken Pizza with red onion","product_description":"<p>Pizza tasty pizza...!!<\/p>","category_id":"656","brand_id":null,"product_image":"uploaded/products/6100016123315130.png"}}]}]}]
         * first_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getMyReservationOrder?page=1
         * from : 1
         * last_page : 9
         * last_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getMyReservationOrder?page=9
         * next_page_url : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getMyReservationOrder?page=2
         * path : https://swoopelocaltesting.com/admin/public/api/swoopelocalapp/getMyReservationOrder
         * per_page : 10
         * prev_page_url : null
         * to : 10
         * total : 82
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
             * id : 1360
             * cust_id : 142
             * venue_id : 202004271734165
             * reservation_id : 20210203060542142
             * check_in : 2021-02-03 11:40:00
             * check_out : 2021-02-03 15:30:00
             * total_guest : 2
             * amount : 4.00
             * status : 1
             * date : 2021-02-03
             * qr_code : uploaded/reservationQr/6274616123323420.png
             * stripe_capture_id : null
             * active_capture_id : 21020306BV06DP22KD41CNM
             * payment_gateway_type : null
             * refunded : 0.00
             * table_no : null
             * floor_id : null
             * is_pos_completed : 0
             * is_smoking : 1
             * message_for_venue : null
             * created_at : 2021-02-03 06:05:42
             * updated_at : 2021-02-03 06:06:22
             * is_payment : 1
             * card_number : 401200******1112
             * sub_total : 4.00
             * transaction_amt : 0.00
             * order_cancel_by : 0
             * app_type : LOCAL
             * venue : {"id":34,"kyc_status":"pending","user_id":83,"venue_id":"202004271734165","merchant_id":5,"venue_name":"Dixi Chicken Pizza","venue_email":"dixicp@mailop7.com","address_1":"Southport Road, Liverpool, Bootle, UK","address_2":null,"post_code":"L31QR","city_name":"Liverpool","country":"United Kingdom","phone_number":"1231231231","venue_images":"20200622115653-resize.jpg","start_days":1,"end_days":3,"collection_time":70,"preparation_time":50,"is_booking_cancel_allow":1}
             * customer_details : {"id":142,"email":"aryanm22897@gmail.com","contact_no":"918920207597","cust_name":"Aryan Mishra"}
             * orders_masters : [{"id":6542,"unique_code":"142#0128","merchant_id":5,"venue_id":"202004271734165","source_type":"POS","payment_mode":"cash","user_addr_id":null,"reservation_id":"20210203060542142","table_no":"22","card_number":null,"order_date":"2021-02-03 14:49:09","delivery_type":"Reservati D/OkHttp: on","order_status":"4","total_items":2,"amt_without_tax_discount":"24.99","total_discount":"0.00","total_tax":"0.00","net_amount":"29.99","delivery_charge":"0.00","claim_stamp_reward_amt":"0.00","loyelty_consumed":"0.00","loyelty_used_amount":"0.00","status":4,"is_gift":0,"expected_delivery_date":null,"is_asap":0,"qr_order":null,"delivery_date":"","canceled_date":"","trackingData":[],"trackingStatus":[],"order_details":[{"id":9736,"order_id":6542,"product_qty":1,"product_id":"1184","modifier_id":1574,"billing_address":null,"attributes":"Single","selling_price":"5.99","discount_applied":"0.00","discount_type":"","offer_id":null,"tax_applied":"0.00","tax_id":0,"net_amount":"5.99","return_days":0,"item_status":"1","status":4,"avl_quantity":932,"modifier_image":null,"product_details":{"id":1184,"product_name":"Chicken Fillet Burger","product_description":"<p><br data-mce-bogus=\"1\"><\/p>","category_id":"260","brand_id":null,"product_image":"uploaded/products/7175415928224480.jpeg"}},{"id":9737,"order_id":6542,"product_qty":1,"product_id":"2568","modifier_id":3319,"billing_address":null,"attributes":"M","selling_price":"18.00","discount_applied":"0.00","discount_type":"","offer_id":null,"tax_applied":"0.00","tax_id":0,"net_amount":"19.00","return_days":0,"item_status":"1","status":4,"avl_quantity":null,"modifier_image":null,"product_details":{"id":2568,"product_name":"Chicken Pizza with red onion","product_description":"<p>Pizza tasty pizza...!!<\/p>","category_id":"656","brand_id":null,"product_image":"uploaded/products/6100016123315130.png"}}]}]
             */

            private boolean mOrderStatus;

            private int id;
            private int cust_id;
            private String venue_id;
            private String reservation_id;
            private String check_in;
            private String check_out;
            private int total_guest;
            private String amount;
            private int status;
            private String date;
            private String qr_code;
            private Object stripe_capture_id;
            private String active_capture_id;
            private Object payment_gateway_type;
            private String refunded;
            private Object table_no;
            private Object floor_id;
            private int is_pos_completed;
            private int is_smoking;
            private Object message_for_venue;
            private String created_at;
            private String updated_at;
            private int is_payment;
            private String card_number;
            private String sub_total;
            private String transaction_amt;
            private int order_cancel_by;
            private String app_type;
            private VenueBean venue;
            private CustomerDetailsBean customer_details;
            private List<OrdersMastersBean> orders_masters;
            private List<ReservationGuestBean> reservation_guest;

            public boolean ismOrderStatus() {
                return mOrderStatus;
            }

            public void setmOrderStatus(boolean mOrderStatus) {
                this.mOrderStatus = mOrderStatus;
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

            public String getVenue_id() {
                return venue_id;
            }

            public void setVenue_id(String venue_id) {
                this.venue_id = venue_id;
            }

            public String getReservation_id() {
                return reservation_id;
            }

            public void setReservation_id(String reservation_id) {
                this.reservation_id = reservation_id;
            }

            public String getCheck_in() {
                return check_in;
            }

            public void setCheck_in(String check_in) {
                this.check_in = check_in;
            }

            public String getCheck_out() {
                return check_out;
            }

            public void setCheck_out(String check_out) {
                this.check_out = check_out;
            }

            public int getTotal_guest() {
                return total_guest;
            }

            public void setTotal_guest(int total_guest) {
                this.total_guest = total_guest;
            }

            public String getAmount() {
                return amount;
            }

            public void setAmount(String amount) {
                this.amount = amount;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getQr_code() {
                return qr_code;
            }

            public void setQr_code(String qr_code) {
                this.qr_code = qr_code;
            }

            public Object getStripe_capture_id() {
                return stripe_capture_id;
            }

            public void setStripe_capture_id(Object stripe_capture_id) {
                this.stripe_capture_id = stripe_capture_id;
            }

            public String getActive_capture_id() {
                return active_capture_id;
            }

            public void setActive_capture_id(String active_capture_id) {
                this.active_capture_id = active_capture_id;
            }

            public Object getPayment_gateway_type() {
                return payment_gateway_type;
            }

            public void setPayment_gateway_type(Object payment_gateway_type) {
                this.payment_gateway_type = payment_gateway_type;
            }

            public String getRefunded() {
                return refunded;
            }

            public void setRefunded(String refunded) {
                this.refunded = refunded;
            }

            public Object getTable_no() {
                return table_no;
            }

            public void setTable_no(Object table_no) {
                this.table_no = table_no;
            }

            public Object getFloor_id() {
                return floor_id;
            }

            public void setFloor_id(Object floor_id) {
                this.floor_id = floor_id;
            }

            public int getIs_pos_completed() {
                return is_pos_completed;
            }

            public void setIs_pos_completed(int is_pos_completed) {
                this.is_pos_completed = is_pos_completed;
            }

            public int getIs_smoking() {
                return is_smoking;
            }

            public void setIs_smoking(int is_smoking) {
                this.is_smoking = is_smoking;
            }

            public Object getMessage_for_venue() {
                return message_for_venue;
            }

            public void setMessage_for_venue(Object message_for_venue) {
                this.message_for_venue = message_for_venue;
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

            public int getIs_payment() {
                return is_payment;
            }

            public void setIs_payment(int is_payment) {
                this.is_payment = is_payment;
            }

            public String getCard_number() {
                return card_number;
            }

            public void setCard_number(String card_number) {
                this.card_number = card_number;
            }

            public String getSub_total() {
                return sub_total;
            }

            public void setSub_total(String sub_total) {
                this.sub_total = sub_total;
            }

            public String getTransaction_amt() {
                return transaction_amt;
            }

            public void setTransaction_amt(String transaction_amt) {
                this.transaction_amt = transaction_amt;
            }

            public int getOrder_cancel_by() {
                return order_cancel_by;
            }

            public void setOrder_cancel_by(int order_cancel_by) {
                this.order_cancel_by = order_cancel_by;
            }

            public String getApp_type() {
                return app_type;
            }

            public void setApp_type(String app_type) {
                this.app_type = app_type;
            }

            public VenueBean getVenue() {
                return venue;
            }

            public void setVenue(VenueBean venue) {
                this.venue = venue;
            }

            public CustomerDetailsBean getCustomer_details() {
                return customer_details;
            }

            public void setCustomer_details(CustomerDetailsBean customer_details) {
                this.customer_details = customer_details;
            }

            public List<OrdersMastersBean> getOrders_masters() {
                return orders_masters;
            }

            public void setOrders_masters(List<OrdersMastersBean> orders_masters) {
                this.orders_masters = orders_masters;
            }

            public List<ReservationGuestBean> getReservation_guest() {
                return reservation_guest;
            }

            public void setReservation_guest(List<ReservationGuestBean> reservation_guest) {
                this.reservation_guest = reservation_guest;
            }

            public static class VenueBean {
                /**
                 * id : 34
                 * kyc_status : pending
                 * user_id : 83
                 * venue_id : 202004271734165
                 * merchant_id : 5
                 * venue_name : Dixi Chicken Pizza
                 * venue_email : dixicp@mailop7.com
                 * address_1 : Southport Road, Liverpool, Bootle, UK
                 * address_2 : null
                 * post_code : L31QR
                 * city_name : Liverpool
                 * country : United Kingdom
                 * phone_number : 1231231231
                 * venue_images : 20200622115653-resize.jpg
                 * start_days : 1
                 * end_days : 3
                 * collection_time : 70
                 * preparation_time : 50
                 * is_booking_cancel_allow : 1
                 */

                private int id;
                private String kyc_status;
                private int user_id;
                private String venue_id;
                private int merchant_id;
                private String venue_name;
                private String venue_email;
                private String address_1;
                private Object address_2;
                private String post_code;
                private String city_name;
                private String country;
                private String phone_number;
                private String venue_images;
                private int start_days;
                private int end_days;
                private int collection_time;
                private int preparation_time;
                private int is_booking_cancel_allow;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getKyc_status() {
                    return kyc_status;
                }

                public void setKyc_status(String kyc_status) {
                    this.kyc_status = kyc_status;
                }

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
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

                public String getVenue_name() {
                    return venue_name;
                }

                public void setVenue_name(String venue_name) {
                    this.venue_name = venue_name;
                }

                public String getVenue_email() {
                    return venue_email;
                }

                public void setVenue_email(String venue_email) {
                    this.venue_email = venue_email;
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

                public String getVenue_images() {
                    return venue_images;
                }

                public void setVenue_images(String venue_images) {
                    this.venue_images = venue_images;
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

                public int getIs_booking_cancel_allow() {
                    return is_booking_cancel_allow;
                }

                public void setIs_booking_cancel_allow(int is_booking_cancel_allow) {
                    this.is_booking_cancel_allow = is_booking_cancel_allow;
                }
            }

            public static class CustomerDetailsBean  {
                /**
                 * id : 142
                 * email : aryanm22897@gmail.com
                 * contact_no : 918920207597
                 * cust_name : Aryan Mishra
                 */

                private int id;
                private String email;
                private String contact_no;
                private String cust_name;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getEmail() {
                    return email;
                }

                public void setEmail(String email) {
                    this.email = email;
                }

                public String getContact_no() {
                    return contact_no;
                }

                public void setContact_no(String contact_no) {
                    this.contact_no = contact_no;
                }

                public String getCust_name() {
                    return cust_name;
                }

                public void setCust_name(String cust_name) {
                    this.cust_name = cust_name;
                }
            }

            public static class OrdersMastersBean {
                /**
                 * id : 6542
                 * unique_code : 142#0128
                 * merchant_id : 5
                 * venue_id : 202004271734165
                 * source_type : POS
                 * payment_mode : cash
                 * user_addr_id : null
                 * reservation_id : 20210203060542142
                 * table_no : 22
                 * card_number : null
                 * order_date : 2021-02-03 14:49:09
                 * delivery_type : Reservation
                 * order_status : 4
                 * total_items : 2
                 * amt_without_tax_discount : 24.99
                 * total_discount : 0.00
                 * total_tax : 0.00
                 * net_amount : 29.99
                 * delivery_charge : 0.00
                 * claim_stamp_reward_amt : 0.00
                 * loyelty_consumed : 0.00
                 * loyelty_used_amount : 0.00
                 * status : 4
                 * is_gift : 0
                 * expected_delivery_date : null
                 * is_asap : 0
                 * qr_order : null
                 * delivery_date :
                 * canceled_date :
                 * trackingData : []
                 * trackingStatus : []
                 * order_details : [{"id":9736,"order_id":6542,"product_qty":1,"product_id":"1184","modifier_id":1574,"billing_address":null,"attributes":"Single","selling_price":"5.99","discount_applied":"0.00","discount_type":"","offer_id":null,"tax_applied":"0.00","tax_id":0,"net_amount":"5.99","return_days":0,"item_status":"1","status":4,"avl_quantity":932,"modifier_image":null,"product_details":{"id":1184,"product_name":"Chicken Fillet Burger","product_description":"<p><br data-mce-bogus=\"1\"><\/p>","category_id":"260","brand_id":null,"product_image":"uploaded/products/7175415928224480.jpeg"}},{"id":9737,"order_id":6542,"product_qty":1,"product_id":"2568","modifier_id":3319,"billing_address":null,"attributes":"M","selling_price":"18.00","discount_applied":"0.00","discount_type":"","offer_id":null,"tax_applied":"0.00","tax_id":0,"net_amount":"19.00","return_days":0,"item_status":"1","status":4,"avl_quantity":null,"modifier_image":null,"product_details":{"id":2568,"product_name":"Chicken Pizza with red onion","product_description":"<p>Pizza tasty pizza...!!<\/p>","category_id":"656","brand_id":null,"product_image":"uploaded/products/6100016123315130.png"}}]
                 */

                private int id;
                private String unique_code;
                private int merchant_id;
                private String venue_id;
                private String source_type;
                private String payment_mode;
                private Object user_addr_id;
                private String reservation_id;
                private String table_no;
                private Object card_number;
                private String order_date;
                private String delivery_type;
                private String order_status;
                private int total_items;
                private String amt_without_tax_discount;
                private String total_discount;
                private String total_tax;
                private String net_amount;
                private String delivery_charge;
                private String claim_stamp_reward_amt;
                private String loyelty_consumed;
                private String loyelty_used_amount;
                private int status;
                private int is_gift;
                private Object expected_delivery_date;
                private int is_asap;
                private Object qr_order;
                private String delivery_date;
                private String canceled_date;
                private List<?> trackingData;
                private List<?> trackingStatus;
                private List<OrderDetailsBean> order_details;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getUnique_code() {
                    return unique_code;
                }

                public void setUnique_code(String unique_code) {
                    this.unique_code = unique_code;
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

                public String getSource_type() {
                    return source_type;
                }

                public void setSource_type(String source_type) {
                    this.source_type = source_type;
                }

                public String getPayment_mode() {
                    return payment_mode;
                }

                public void setPayment_mode(String payment_mode) {
                    this.payment_mode = payment_mode;
                }

                public Object getUser_addr_id() {
                    return user_addr_id;
                }

                public void setUser_addr_id(Object user_addr_id) {
                    this.user_addr_id = user_addr_id;
                }

                public String getReservation_id() {
                    return reservation_id;
                }

                public void setReservation_id(String reservation_id) {
                    this.reservation_id = reservation_id;
                }

                public String getTable_no() {
                    return table_no;
                }

                public void setTable_no(String table_no) {
                    this.table_no = table_no;
                }

                public Object getCard_number() {
                    return card_number;
                }

                public void setCard_number(Object card_number) {
                    this.card_number = card_number;
                }

                public String getOrder_date() {
                    return order_date;
                }

                public void setOrder_date(String order_date) {
                    this.order_date = order_date;
                }

                public String getDelivery_type() {
                    return delivery_type;
                }

                public void setDelivery_type(String delivery_type) {
                    this.delivery_type = delivery_type;
                }

                public String getOrder_status() {
                    return order_status;
                }

                public void setOrder_status(String order_status) {
                    this.order_status = order_status;
                }

                public int getTotal_items() {
                    return total_items;
                }

                public void setTotal_items(int total_items) {
                    this.total_items = total_items;
                }

                public String getAmt_without_tax_discount() {
                    return amt_without_tax_discount;
                }

                public void setAmt_without_tax_discount(String amt_without_tax_discount) {
                    this.amt_without_tax_discount = amt_without_tax_discount;
                }

                public String getTotal_discount() {
                    return total_discount;
                }

                public void setTotal_discount(String total_discount) {
                    this.total_discount = total_discount;
                }

                public String getTotal_tax() {
                    return total_tax;
                }

                public void setTotal_tax(String total_tax) {
                    this.total_tax = total_tax;
                }

                public String getNet_amount() {
                    return net_amount;
                }

                public void setNet_amount(String net_amount) {
                    this.net_amount = net_amount;
                }

                public String getDelivery_charge() {
                    return delivery_charge;
                }

                public void setDelivery_charge(String delivery_charge) {
                    this.delivery_charge = delivery_charge;
                }

                public String getClaim_stamp_reward_amt() {
                    return claim_stamp_reward_amt;
                }

                public void setClaim_stamp_reward_amt(String claim_stamp_reward_amt) {
                    this.claim_stamp_reward_amt = claim_stamp_reward_amt;
                }

                public String getLoyelty_consumed() {
                    return loyelty_consumed;
                }

                public void setLoyelty_consumed(String loyelty_consumed) {
                    this.loyelty_consumed = loyelty_consumed;
                }

                public String getLoyelty_used_amount() {
                    return loyelty_used_amount;
                }

                public void setLoyelty_used_amount(String loyelty_used_amount) {
                    this.loyelty_used_amount = loyelty_used_amount;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getIs_gift() {
                    return is_gift;
                }

                public void setIs_gift(int is_gift) {
                    this.is_gift = is_gift;
                }

                public Object getExpected_delivery_date() {
                    return expected_delivery_date;
                }

                public void setExpected_delivery_date(Object expected_delivery_date) {
                    this.expected_delivery_date = expected_delivery_date;
                }

                public int getIs_asap() {
                    return is_asap;
                }

                public void setIs_asap(int is_asap) {
                    this.is_asap = is_asap;
                }

                public Object getQr_order() {
                    return qr_order;
                }

                public void setQr_order(Object qr_order) {
                    this.qr_order = qr_order;
                }

                public String getDelivery_date() {
                    return delivery_date;
                }

                public void setDelivery_date(String delivery_date) {
                    this.delivery_date = delivery_date;
                }

                public String getCanceled_date() {
                    return canceled_date;
                }

                public void setCanceled_date(String canceled_date) {
                    this.canceled_date = canceled_date;
                }

                public List<?> getTrackingData() {
                    return trackingData;
                }

                public void setTrackingData(List<?> trackingData) {
                    this.trackingData = trackingData;
                }

                public List<?> getTrackingStatus() {
                    return trackingStatus;
                }

                public void setTrackingStatus(List<?> trackingStatus) {
                    this.trackingStatus = trackingStatus;
                }

                public List<OrderDetailsBean> getOrder_details() {
                    return order_details;
                }

                public void setOrder_details(List<OrderDetailsBean> order_details) {
                    this.order_details = order_details;
                }

                public static class OrderDetailsBean {
                    /**
                     * id : 9736
                     * order_id : 6542
                     * product_qty : 1
                     * product_id : 1184
                     * modifier_id : 1574
                     * billing_address : null
                     * attributes : Single
                     * selling_price : 5.99
                     * discount_applied : 0.00
                     * discount_type :
                     * offer_id : null
                     * tax_applied : 0.00
                     * tax_id : 0
                     * net_amount : 5.99
                     * return_days : 0
                     * item_status : 1  ////
                     * status : 4       ///
                     * status_name : ""       ///
                     * avl_quantity : 932
                     * modifier_image : null
                     * product_details : {"id":1184,"product_name":"Chicken Fillet Burger","product_description":"<p><br data-mce-bogus=\"1\"><\/p>","category_id":"260","brand_id":null,"product_image":"uploaded/products/7175415928224480.jpeg"}
                     */

                    private int id;
                    private int order_id;
                    private int product_qty;
                    private String product_id;
                    private int modifier_id;
                    private Object billing_address;
                    private String attributes;
                    private String selling_price;
                    private String discount_applied;
                    private String discount_type;
                    private int offer_id;
                    private String tax_applied;
                    private int tax_id;
                    private String net_amount;
                    private int return_days;
                    private String item_status;
                    private String status_name;
                    private int status;
                    private int avl_quantity;
                    private String modifier_image;
                    private ProductDetailsBean product_details;

                    public String getStatus_name() {
                        return status_name;
                    }

                    public void setStatus_name(String status_name) {
                        this.status_name = status_name;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getOrder_id() {
                        return order_id;
                    }

                    public void setOrder_id(int order_id) {
                        this.order_id = order_id;
                    }

                    public int getProduct_qty() {
                        return product_qty;
                    }

                    public void setProduct_qty(int product_qty) {
                        this.product_qty = product_qty;
                    }

                    public String getProduct_id() {
                        return product_id;
                    }

                    public void setProduct_id(String product_id) {
                        this.product_id = product_id;
                    }

                    public int getModifier_id() {
                        return modifier_id;
                    }

                    public void setModifier_id(int modifier_id) {
                        this.modifier_id = modifier_id;
                    }

                    public Object getBilling_address() {
                        return billing_address;
                    }

                    public void setBilling_address(Object billing_address) {
                        this.billing_address = billing_address;
                    }

                    public String getAttributes() {
                        return attributes;
                    }

                    public void setAttributes(String attributes) {
                        this.attributes = attributes;
                    }

                    public String getSelling_price() {
                        return selling_price;
                    }

                    public void setSelling_price(String selling_price) {
                        this.selling_price = selling_price;
                    }

                    public String getDiscount_applied() {
                        return discount_applied;
                    }

                    public void setDiscount_applied(String discount_applied) {
                        this.discount_applied = discount_applied;
                    }

                    public String getDiscount_type() {
                        return discount_type;
                    }

                    public void setDiscount_type(String discount_type) {
                        this.discount_type = discount_type;
                    }

                    public int getOffer_id() {
                        return offer_id;
                    }

                    public void setOffer_id(int offer_id) {
                        this.offer_id = offer_id;
                    }

                    public String getTax_applied() {
                        return tax_applied;
                    }

                    public void setTax_applied(String tax_applied) {
                        this.tax_applied = tax_applied;
                    }

                    public int getTax_id() {
                        return tax_id;
                    }

                    public void setTax_id(int tax_id) {
                        this.tax_id = tax_id;
                    }

                    public String getNet_amount() {
                        return net_amount;
                    }

                    public void setNet_amount(String net_amount) {
                        this.net_amount = net_amount;
                    }

                    public int getReturn_days() {
                        return return_days;
                    }

                    public void setReturn_days(int return_days) {
                        this.return_days = return_days;
                    }

                    public String getItem_status() {
                        return item_status;
                    }

                    public void setItem_status(String item_status) {
                        this.item_status = item_status;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public int getAvl_quantity() {
                        return avl_quantity;
                    }

                    public void setAvl_quantity(int avl_quantity) {
                        this.avl_quantity = avl_quantity;
                    }

                    public String getModifier_image() {
                        return modifier_image;
                    }

                    public void setModifier_image(String modifier_image) {
                        this.modifier_image = modifier_image;
                    }

                    public ProductDetailsBean getProduct_details() {
                        return product_details;
                    }

                    public void setProduct_details(ProductDetailsBean product_details) {
                        this.product_details = product_details;
                    }

                    public static class ProductDetailsBean  {
                        /**
                         * id : 1184
                         * product_name : Chicken Fillet Burger
                         * product_description : <p><br data-mce-bogus="1"></p>
                         * category_id : 260
                         * brand_id : null
                         * product_image : uploaded/products/7175415928224480.jpeg
                         */

                        private int id;
                        private String product_name;
                        private String product_description;
                        private String category_id;
                        private Object brand_id;
                        private String product_image;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getProduct_name() {
                            return product_name;
                        }

                        public void setProduct_name(String product_name) {
                            this.product_name = product_name;
                        }

                        public String getProduct_description() {
                            return product_description;
                        }

                        public void setProduct_description(String product_description) {
                            this.product_description = product_description;
                        }

                        public String getCategory_id() {
                            return category_id;
                        }

                        public void setCategory_id(String category_id) {
                            this.category_id = category_id;
                        }

                        public Object getBrand_id() {
                            return brand_id;
                        }

                        public void setBrand_id(Object brand_id) {
                            this.brand_id = brand_id;
                        }

                        public String getProduct_image() {
                            return product_image;
                        }

                        public void setProduct_image(String product_image) {
                            this.product_image = product_image;
                        }
                    }
                }
            }

            public static class ReservationGuestBean {
                /**
                 * id : 2301
                 * contact_no : 919142140515
                 * cust_id : 145
                 * reservation_id : 2592
                 * first_name : Shakti
                 * last_name : Kumar
                 * email : null
                 * same_house_hold : 0
                 * customer : {"id":145,"first_name":"Shakti","last_name":"Kumar","email":"shakti.kumar22dec92@gmail.com","contact_no":"919142140515","profile_image":"uploaded/profile/16145832920.jpg","social_type":"google"}
                 */

                private int id;
                private String contact_no;
                private int cust_id;
                private int reservation_id;
                private String first_name;
                private String last_name;
                private Object email;
                private int same_house_hold;
                private ReservationsBean.DataBean.ReservationGuestBean.CustomerBean customer;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getContact_no() {
                    return contact_no;
                }

                public void setContact_no(String contact_no) {
                    this.contact_no = contact_no;
                }

                public int getCust_id() {
                    return cust_id;
                }

                public void setCust_id(int cust_id) {
                    this.cust_id = cust_id;
                }

                public int getReservation_id() {
                    return reservation_id;
                }

                public void setReservation_id(int reservation_id) {
                    this.reservation_id = reservation_id;
                }

                public String getFirst_name() {
                    return first_name;
                }

                public void setFirst_name(String first_name) {
                    this.first_name = first_name;
                }

                public String getLast_name() {
                    return last_name;
                }

                public void setLast_name(String last_name) {
                    this.last_name = last_name;
                }

                public Object getEmail() {
                    return email;
                }

                public void setEmail(Object email) {
                    this.email = email;
                }

                public int getSame_house_hold() {
                    return same_house_hold;
                }

                public void setSame_house_hold(int same_house_hold) {
                    this.same_house_hold = same_house_hold;
                }

                public ReservationsBean.DataBean.ReservationGuestBean.CustomerBean getCustomer() {
                    return customer;
                }

                public void setCustomer(ReservationsBean.DataBean.ReservationGuestBean.CustomerBean customer) {
                    this.customer = customer;
                }

                public static class CustomerBean implements Serializable {
                    /**
                     * id : 145
                     * first_name : Shakti
                     * last_name : Kumar
                     * email : shakti.kumar22dec92@gmail.com
                     * contact_no : 919142140515
                     * profile_image : uploaded/profile/16145832920.jpg
                     * social_type : google
                     */

                    private int id;
                    private String first_name;
                    private String last_name;
                    private String email;
                    private String contact_no;
                    private String profile_image;
                    private String social_type;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getFirst_name() {
                        return first_name;
                    }

                    public void setFirst_name(String first_name) {
                        this.first_name = first_name;
                    }

                    public String getLast_name() {
                        return last_name;
                    }

                    public void setLast_name(String last_name) {
                        this.last_name = last_name;
                    }

                    public String getEmail() {
                        return email;
                    }

                    public void setEmail(String email) {
                        this.email = email;
                    }

                    public String getContact_no() {
                        return contact_no;
                    }

                    public void setContact_no(String contact_no) {
                        this.contact_no = contact_no;
                    }

                    public String getProfile_image() {
                        return profile_image;
                    }

                    public void setProfile_image(String profile_image) {
                        this.profile_image = profile_image;
                    }

                    public String getSocial_type() {
                        return social_type;
                    }

                    public void setSocial_type(String social_type) {
                        this.social_type = social_type;
                    }
                }
            }
        }
    }
}
