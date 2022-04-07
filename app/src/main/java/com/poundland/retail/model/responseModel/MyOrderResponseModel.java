package com.poundland.retail.model.responseModel;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class MyOrderResponseModel {

    private boolean status;
    private String message;
    private OrdersBean orders;
    private List<ReturnReasonsBean> return_reasons;

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

    public OrdersBean getOrders() {
        return orders;
    }

    public void setOrders(OrdersBean orders) {
        this.orders = orders;
    }

    public List<ReturnReasonsBean> getReturn_reasons() {
        return return_reasons;
    }

    public void setReturn_reasons(List<ReturnReasonsBean> return_reasons) {
        this.return_reasons = return_reasons;
    }

    public static class OrdersBean {

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
             * id : 371
             * unique_code : 201912121252349
             * source_type : app
             * payment_mode : Card
             * user_addr_id : null
             * venue_id : 201911011148462
             * cust_id : 9
             * card_number : ******4242
             * order_date : 2019-12-12 12:52:34
             * delivery_type : Delivery   ,   Home Delivery  ,  Click & Collect
             * order_status : null
             * total_items : 2
             * amt_without_tax_discount : 210.00
             * total_discount : 0.00
             * total_tax : 0.00
             * net_amount : 210.00
             * delivery_charge : 0.00
             * claim_stamp_reward_amt : 0.00
             * loyelty_consumed : 20.00
             * loyelty_used_amount : 0.00
             * status : 0                 // if 0 then show cancell button  1 >> reject 2 >> shipped  3 >> out for delivered  4 deliverd
             * is_gift : 0
             * canceled_date:
             * delivery_date :"2021-02-10 14:49:35"
             * venue : {"id":2,"kyc_status":"pending","user_id":8,"venue_id":"201911011148462","venue_name":"LillyWhites","venue_email":"whiteslilly@dmailpro.net","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"post_code":"WV3 0DS","city_name":"Wolverhampton","country":"United Kingdom","phone_number":"5468496484","venue_images":"1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg","start_days":1,"end_days":2}
             * customer_address : {"id":29,"name":"Vikalp Saxena","mobile":"9818236375","pincode":"N1 5RA","flat":null,"area":"Be The Brand Experience Ltd","landmark":"Unit 5","city":"Hackney","state":null,"country":"England","type":null}
             * order_details : [{"id":651,"order_id":371,"product_qty":1,"product_id":"7","modifier_id":7,"billing_address":"Be The Brand Experience Ltd Hackney Unit 5  N1 5RA, England","attributes":null,"selling_price":"110.00","discount_applied":"0.00","discount_type":null,"offer_id":"-1","tax_applied":"0.00","tax_id":1,"net_amount":"110.00","return_days":0,"item_status":null,"status":0,"acknowledgement":[],"product_details":{"id":7,"brand_id":3,"product_name":"Women Olive Green Solid Lightweight Puffer Jacket","images":"uploaded/products/3074915726105480.jpeg","avl_quantity":97,"modifier_images":"","product_brand":{"id":3,"brand_name":"Levis","images":null}}},{"id":652,"order_id":371,"product_qty":1,"product_id":"27","modifier_id":30,"billing_address":"Be The Brand Experience Ltd Hackney Unit 5  N1 5RA, England","attributes":null,"selling_price":"100.00","discount_applied":"0.00","discount_type":null,"offer_id":"-1","tax_applied":"0.00","tax_id":0,"net_amount":"100.00","return_days":0,"item_status":null,"status":0,"acknowledgement":[],"product_details":{"id":27,"brand_id":11,"product_name":"ROSSO BRUNELLO Men's Tan Lace Ups Formal Leather Shoes","images":"uploaded/products/5853415730351820.jpeg","avl_quantity":98,"modifier_images":"","product_brand":{"id":11,"brand_name":"Pacifia","images":null}}}]
             */


            private int id;
            private String unique_code;
            private String source_type;
            private String payment_mode;
            private int user_addr_id;
            private String venue_id;
            private int cust_id;
            private int is_rated;
            private String card_number;
            private String order_date;
            private String delivery_type;
            private String order_status;
            private String table_no;
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
            private String expected_delivery_date;
            private String expected_shipping_date;
            private int is_asap;
            private int is_hospitality;
            private Object qr_order;
            private String delivery_date;
            private String canceled_date;
            private VenueBean venue;
            private CustomerAddressBean customer_address;
            private List<TrackingDataBean> trackingData;
            private List<Integer> trackingStatus;
            private List<OrderDetailsBean> order_details;
            private int merchant_id;

            public int getIs_rated() {
                return is_rated;
            }

            public void setIs_rated(int is_rated) {
                this.is_rated = is_rated;
            }

            public String getTable_no() {
                return table_no;
            }

            public void setTable_no(String table_no) {
                this.table_no = table_no;
            }

            public String getExpected_shipping_date() {
                return expected_shipping_date;
            }

            public void setExpected_shipping_date(String expected_shipping_date) {
                this.expected_shipping_date = expected_shipping_date;
            }

            public int getIs_hospitality() {
                return is_hospitality;
            }

            public void setIs_hospitality(int is_hospitality) {
                this.is_hospitality = is_hospitality;
            }

            public String getExpected_delivery_date() {
                return expected_delivery_date;
            }

            public void setExpected_delivery_date(String expected_delivery_date) {
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

            public int getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(int merchant_id) {
                this.merchant_id = merchant_id;
            }

            public String getCanceled_date() {
                return canceled_date;
            }

            public void setCanceled_date(String canceled_date) {
                this.canceled_date = canceled_date;
            }

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

            public int getUser_addr_id() {
                return user_addr_id;
            }

            public void setUser_addr_id(int user_addr_id) {
                this.user_addr_id = user_addr_id;
            }

            public String getVenue_id() {
                return venue_id;
            }

            public void setVenue_id(String venue_id) {
                this.venue_id = venue_id;
            }

            public int getCust_id() {
                return cust_id;
            }

            public void setCust_id(int cust_id) {
                this.cust_id = cust_id;
            }

            public String getCard_number() {
                return card_number;
            }

            public void setCard_number(String card_number) {
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

            public String getDelivery_date() {
                return delivery_date;
            }

            public void setDelivery_date(String delivery_date) {
                this.delivery_date = delivery_date;
            }

            public VenueBean getVenue() {
                return venue;
            }

            public void setVenue(VenueBean venue) {
                this.venue = venue;
            }

            public CustomerAddressBean getCustomer_address() {
                return customer_address;
            }

            public void setCustomer_address(CustomerAddressBean customer_address) {
                this.customer_address = customer_address;
            }

            public List<TrackingDataBean> getTrackingData() {
                return trackingData;
            }

            public void setTrackingData(List<TrackingDataBean> trackingData) {
                this.trackingData = trackingData;
            }

            public List<Integer> getTrackingStatus() {
                return trackingStatus;
            }

            public void setTrackingStatus(List<Integer> trackingStatus) {
                this.trackingStatus = trackingStatus;
            }

            public List<OrderDetailsBean> getOrder_details() {
                return order_details;
            }

            public void setOrder_details(List<OrderDetailsBean> order_details) {
                this.order_details = order_details;
            }

            public static class VenueBean {
                /**
                 * id : 2
                 * kyc_status : pending
                 * user_id : 8
                 * venue_id : 201911011148462
                 * venue_name : LillyWhites
                 * venue_email : whiteslilly@dmailpro.net
                 * address_1 : Oaklands Road, Wolverhampton WV3 0DS, England, UK
                 * address_2 : null
                 * post_code : WV3 0DS
                 * city_name : Wolverhampton
                 * country : United Kingdom
                 * phone_number : 5468496484
                 * venue_images : 1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg
                 * start_days : 1
                 * end_days : 2
                 */
                private int id;
                private String kyc_status;
                private int user_id;
                private String venue_id;
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
                private int venue_type;
                private int collection_time;
                private int preparation_time;

                public int getVenue_type() {
                    return venue_type;
                }

                public void setVenue_type(int venue_type) {
                    this.venue_type = venue_type;
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
            }

            public static class CustomerAddressBean implements Parcelable {
                /**
                 * id : 29
                 * name : Vikalp Saxena
                 * mobile : 9818236375
                 * pincode : N1 5RA
                 * flat : null
                 * area : Be The Brand Experience Ltd
                 * landmark : Unit 5
                 * city : Hackney
                 * state : null
                 * country : England
                 * type : null
                 */

                private int id;
                private String name;
                private String mobile;
                private String pincode;
                private Object flat;
                private String area;
                private String landmark;
                private String city;
                private Object state;
                private String country;
                private Object type;

                protected CustomerAddressBean(Parcel in) {
                    id = in.readInt();
                    name = in.readString();
                    mobile = in.readString();
                    pincode = in.readString();
                    area = in.readString();
                    landmark = in.readString();
                    city = in.readString();
                    country = in.readString();
                }

                public static final Creator<CustomerAddressBean> CREATOR = new Creator<CustomerAddressBean>() {
                    @Override
                    public CustomerAddressBean createFromParcel(Parcel in) {
                        return new CustomerAddressBean(in);
                    }

                    @Override
                    public CustomerAddressBean[] newArray(int size) {
                        return new CustomerAddressBean[size];
                    }
                };

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

                public String getMobile() {
                    return mobile;
                }

                public void setMobile(String mobile) {
                    this.mobile = mobile;
                }

                public String getPincode() {
                    return pincode;
                }

                public void setPincode(String pincode) {
                    this.pincode = pincode;
                }

                public Object getFlat() {
                    return flat;
                }

                public void setFlat(Object flat) {
                    this.flat = flat;
                }

                public String getArea() {
                    return area;
                }

                public void setArea(String area) {
                    this.area = area;
                }

                public String getLandmark() {
                    return landmark;
                }

                public void setLandmark(String landmark) {
                    this.landmark = landmark;
                }

                public String getCity() {
                    return city;
                }

                public void setCity(String city) {
                    this.city = city;
                }

                public Object getState() {
                    return state;
                }

                public void setState(Object state) {
                    this.state = state;
                }

                public String getCountry() {
                    return country;
                }

                public void setCountry(String country) {
                    this.country = country;
                }

                public Object getType() {
                    return type;
                }

                public void setType(Object type) {
                    this.type = type;
                }

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(id);
                    dest.writeString(name);
                    dest.writeString(mobile);
                    dest.writeString(pincode);
                    dest.writeString(area);
                    dest.writeString(landmark);
                    dest.writeString(city);
                    dest.writeString(country);
                }
            }

            public static class TrackingDataBean implements Parcelable{
                /**
                 * id : 702
                 * customer_id : null
                 * order_id : 822
                 * order_detail_id : 0
                 * return_qty : 0
                 * discount : 0
                 * tax : 0
                 * venue_id : 2020011511394213
                 * total_amount : 0.00
                 * loyalty_value : 0.00
                 * loyalty_amount : 0.00
                 * loyalty_point : null
                 * cause_message : null
                 * comment : Your Order 202001301410189 has been Approved.
                 * venue_comment : null
                 * status : 2
                 * venue_action : 0
                 * created_at : 2020-01-30 14:10:37
                 * updated_at : 2020-01-30 14:10:37
                 */

                private int id;
                private String customer_id;
                private String order_id;
                private String order_detail_id;
                private int return_qty;
                private double discount;
                private double tax;
                private String venue_id;
                private String total_amount;
                private String loyalty_value;
                private String loyalty_amount;
                private Object loyalty_point;
                private Object cause_message;
                private String comment;
                private Object venue_comment;
                private int status;
                private int venue_action;
                private String created_at;
                private String updated_at;

                protected TrackingDataBean(Parcel in) {
                    id = in.readInt();
                    customer_id = in.readString();
                    order_id = in.readString();
                    order_detail_id = in.readString();
                    return_qty = in.readInt();
                    discount = in.readDouble();
                    tax = in.readDouble();
                    venue_id = in.readString();
                    total_amount = in.readString();
                    loyalty_value = in.readString();
                    loyalty_amount = in.readString();
                    comment = in.readString();
                    status = in.readInt();
                    venue_action = in.readInt();
                    created_at = in.readString();
                    updated_at = in.readString();
                }

                public static final Creator<TrackingDataBean> CREATOR = new Creator<TrackingDataBean>() {
                    @Override
                    public TrackingDataBean createFromParcel(Parcel in) {
                        return new TrackingDataBean(in);
                    }

                    @Override
                    public TrackingDataBean[] newArray(int size) {
                        return new TrackingDataBean[size];
                    }
                };

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getCustomer_id() {
                    return customer_id;
                }

                public void setCustomer_id(String customer_id) {
                    this.customer_id = customer_id;
                }

                public String getOrder_id() {
                    return order_id;
                }

                public void setOrder_id(String order_id) {
                    this.order_id = order_id;
                }

                public String getOrder_detail_id() {
                    return order_detail_id;
                }

                public void setOrder_detail_id(String order_detail_id) {
                    this.order_detail_id = order_detail_id;
                }

                public int getReturn_qty() {
                    return return_qty;
                }

                public void setReturn_qty(int return_qty) {
                    this.return_qty = return_qty;
                }

                public double getDiscount() {
                    return discount;
                }

                public void setDiscount(double discount) {
                    this.discount = discount;
                }

                public double getTax() {
                    return tax;
                }

                public void setTax(double tax) {
                    this.tax = tax;
                }

                public String getVenue_id() {
                    return venue_id;
                }

                public void setVenue_id(String venue_id) {
                    this.venue_id = venue_id;
                }

                public String getTotal_amount() {
                    return total_amount;
                }

                public void setTotal_amount(String total_amount) {
                    this.total_amount = total_amount;
                }

                public String getLoyalty_value() {
                    return loyalty_value;
                }

                public void setLoyalty_value(String loyalty_value) {
                    this.loyalty_value = loyalty_value;
                }

                public String getLoyalty_amount() {
                    return loyalty_amount;
                }

                public void setLoyalty_amount(String loyalty_amount) {
                    this.loyalty_amount = loyalty_amount;
                }

                public Object getLoyalty_point() {
                    return loyalty_point;
                }

                public void setLoyalty_point(Object loyalty_point) {
                    this.loyalty_point = loyalty_point;
                }

                public Object getCause_message() {
                    return cause_message;
                }

                public void setCause_message(Object cause_message) {
                    this.cause_message = cause_message;
                }

                public String getComment() {
                    return comment;
                }

                public void setComment(String comment) {
                    this.comment = comment;
                }

                public Object getVenue_comment() {
                    return venue_comment;
                }

                public void setVenue_comment(Object venue_comment) {
                    this.venue_comment = venue_comment;
                }

                public int getStatus() {
                    return status;
                }

                public void setStatus(int status) {
                    this.status = status;
                }

                public int getVenue_action() {
                    return venue_action;
                }

                public void setVenue_action(int venue_action) {
                    this.venue_action = venue_action;
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

                @Override
                public int describeContents() {
                    return 0;
                }

                @Override
                public void writeToParcel(Parcel dest, int flags) {
                    dest.writeInt(id);
                    dest.writeString(customer_id);
                    dest.writeString(order_id);
                    dest.writeString(order_detail_id);
                    dest.writeInt(return_qty);
                    dest.writeDouble(discount);
                    dest.writeDouble(tax);
                    dest.writeString(venue_id);
                    dest.writeString(total_amount);
                    dest.writeString(loyalty_value);
                    dest.writeString(loyalty_amount);
                    dest.writeString(comment);
                    dest.writeInt(status);
                    dest.writeInt(venue_action);
                    dest.writeString(created_at);
                    dest.writeString(updated_at);
                }
            }

            public static class OrderDetailsBean {
                /**
                 * id : 651
                 * order_id : 371
                 * product_qty : 1
                 * product_id : 7
                 * modifier_id : 7
                 * billing_address : Be The Brand Experience Ltd Hackney Unit 5  N1 5RA, England
                 * attributes : null
                 * selling_price : 110.00
                 * discount_applied : 0.00
                 * discount_type : null
                 * offer_id : -1
                 * tax_applied : 0.00
                 * tax_id : 1
                 * net_amount : 110.00
                 * return_days : 0
                 * item_status : null
                 * status : 0
                 * acknowledgement : []
                 * product_details : {"id":7,"brand_id":3,"product_name":"Women Olive Green Solid Lightweight Puffer Jacket","images":"uploaded/products/3074915726105480.jpeg","avl_quantity":97,"modifier_images":"","product_brand":{"id":3,"brand_name":"Levis","images":null}}
                 */

                private int id;
                private int order_id;
                private int product_qty;
                private String product_id;
                private int modifier_id;
                private String billing_address;
                private String attributes;
                private String selling_price;
                private String discount_applied;
                private Object discount_type;
                private String offer_id;
                private String tax_applied;
                private String review;
                private float rattings;
                private int avl_quantity;
                private String modifier_images;
                private int tax_id;
                private String net_amount;
                private int return_days;
                private int remaining_return_days;
                private String item_status;
                private int status;
                private int case_qty;
                private String bulk_sale_price;
                private ProductDetailsBean product_details;
                private String order_track_status;
                private List<AcknowledgementBean> acknowledgement;
                private List<String> allergens;

                public List<String> getAllergens() {
                    return allergens;
                }

                public void setAllergens(List<String> allergens) {
                    this.allergens = allergens;
                }

                public int getCase_qty() {
                    return case_qty;
                }

                public void setCase_qty(int case_qty) {
                    this.case_qty = case_qty;
                }

                public String getBulk_sale_price() {
                    return bulk_sale_price;
                }

                public void setBulk_sale_price(String bulk_sale_price) {
                    this.bulk_sale_price = bulk_sale_price;
                }

                public String getOrder_track_status() {
                    return order_track_status;
                }

                public void setOrder_track_status(String order_track_status) {
                    this.order_track_status = order_track_status;
                }

                public int getAvl_quantity() {
                    return avl_quantity;
                }

                public void setAvl_quantity(int avl_quantity) {
                    this.avl_quantity = avl_quantity;
                }

                public String getModifier_images() {
                    return modifier_images;
                }

                public void setModifier_images(String modifier_images) {
                    this.modifier_images = modifier_images;
                }

                public String getReview() {
                    return review;
                }

                public void setReview(String review) {
                    this.review = review;
                }

                public float getRattings() {
                    return rattings;
                }

                public void setRattings(float rattings) {
                    this.rattings = rattings;
                }

                public int getRemaining_return_days() {
                    return remaining_return_days;
                }

                public void setRemaining_return_days(int remaining_return_days) {
                    this.remaining_return_days = remaining_return_days;
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

                public String getBilling_address() {
                    return billing_address;
                }

                public void setBilling_address(String billing_address) {
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

                public Object getDiscount_type() {
                    return discount_type;
                }

                public void setDiscount_type(Object discount_type) {
                    this.discount_type = discount_type;
                }

                public String getOffer_id() {
                    return offer_id;
                }

                public void setOffer_id(String offer_id) {
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

                public ProductDetailsBean getProduct_details() {
                    return product_details;
                }

                public void setProduct_details(ProductDetailsBean product_details) {
                    this.product_details = product_details;
                }

                public List<AcknowledgementBean> getAcknowledgement() {
                    return acknowledgement;
                }

                public void setAcknowledgement(List<AcknowledgementBean> acknowledgement) {
                    this.acknowledgement = acknowledgement;
                }

                public static class AcknowledgementBean {
                    /**
                     * "id": 56,
                     * "customer_id": null,
                     * "order_id": "85",
                     * "order_detail_id": "130",
                     * "return_qty": 1,
                     * "discount": 0,
                     * "tax": 0,
                     * "venue_id": "202002121635135",
                     * "total_amount": "0.00",
                     * "loyalty_value": "0.00",
                     * "loyalty_amount": "0.00",
                     * "loyalty_point": null,
                     * "cause_message": null,
                     * "comment": "product cancel",
                     * "venue_comment": null,
                     * "status": 1,
                     * "venue_action": 2,
                     * "created_at": "2020-04-03 08:48:59",
                     * "updated_at": "2020-04-03 08:52:25"}
                     */

                    private int id;
                    private String customer_id;
                    private String order_id;
                    private String order_detail_id;
                    private String venue_id;
                    private String total_amount;
                    private String loyalty_value;
                    private String loyalty_amount;
                    private String loyalty_point;
                    private String cause_message;
                    private String comment;
                    private String venue_comment;
                    private String type;
                    private int return_qty;
                    private double discount;
                    private double tax;
                    private int status;
                    private int venue_action;
                    private String created_at;
                    private String updated_at;

                    public String getCustomer_id() {
                        return customer_id;
                    }

                    public void setCustomer_id(String customer_id) {
                        this.customer_id = customer_id;
                    }

                    public String getOrder_id() {
                        return order_id;
                    }

                    public void setOrder_id(String order_id) {
                        this.order_id = order_id;
                    }

                    public String getOrder_detail_id() {
                        return order_detail_id;
                    }

                    public void setOrder_detail_id(String order_detail_id) {
                        this.order_detail_id = order_detail_id;
                    }

                    public String getVenue_id() {
                        return venue_id;
                    }

                    public void setVenue_id(String venue_id) {
                        this.venue_id = venue_id;
                    }

                    public String getTotal_amount() {
                        return total_amount;
                    }

                    public void setTotal_amount(String total_amount) {
                        this.total_amount = total_amount;
                    }

                    public String getLoyalty_value() {
                        return loyalty_value;
                    }

                    public void setLoyalty_value(String loyalty_value) {
                        this.loyalty_value = loyalty_value;
                    }

                    public String getLoyalty_amount() {
                        return loyalty_amount;
                    }

                    public void setLoyalty_amount(String loyalty_amount) {
                        this.loyalty_amount = loyalty_amount;
                    }

                    public String getLoyalty_point() {
                        return loyalty_point;
                    }

                    public void setLoyalty_point(String loyalty_point) {
                        this.loyalty_point = loyalty_point;
                    }

                    public String getCause_message() {
                        return cause_message;
                    }

                    public void setCause_message(String cause_message) {
                        this.cause_message = cause_message;
                    }

                    public String getComment() {
                        return comment;
                    }

                    public void setComment(String comment) {
                        this.comment = comment;
                    }

                    public String getVenue_comment() {
                        return venue_comment;
                    }

                    public void setVenue_comment(String venue_comment) {
                        this.venue_comment = venue_comment;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public String getType() {
                        return type;
                    }

                    public void setType(String type) {
                        this.type = type;
                    }

                    public int getReturn_qty() {
                        return return_qty;
                    }

                    public void setReturn_qty(int return_qty) {
                        this.return_qty = return_qty;
                    }

                    public double getDiscount() {
                        return discount;
                    }

                    public void setDiscount(double discount) {
                        this.discount = discount;
                    }

                    public double getTax() {
                        return tax;
                    }

                    public void setTax(double tax) {
                        this.tax = tax;
                    }

                    public int getStatus() {
                        return status;
                    }

                    public void setStatus(int status) {
                        this.status = status;
                    }

                    public int getVenue_action() {
                        return venue_action;
                    }

                    public void setVenue_action(int venue_action) {
                        this.venue_action = venue_action;
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

                public static class ProductDetailsBean {
                    /**
                     * id : 7
                     * brand_id : 3
                     * product_name : Women Olive Green Solid Lightweight Puffer Jacket
                     * images : uploaded/products/3074915726105480.jpeg
                     * avl_quantity : 97
                     * modifier_images :
                     * product_brand : {"id":3,"brand_name":"Levis","images":null}
                     */

                    private int id;
                    private String product_name;
                    private String product_description;
                    private String category_id;
                    private int brand_id;
                    private String category_name;
                    private String images;
                    //  private String modifier_images;
                    private ProductBrandBean product_brand;


                    public String getCategory_id() {
                        return category_id;
                    }

                    public void setCategory_id(String category_id) {
                        this.category_id = category_id;
                    }

                    public String getCategory_name() {
                        return category_name;
                    }

                    public void setCategory_name(String category_name) {
                        this.category_name = category_name;
                    }

                    public String getProduct_description() {
                        return product_description;
                    }

                    public void setProduct_description(String product_description) {
                        this.product_description = product_description;
                    }

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public int getBrand_id() {
                        return brand_id;
                    }

                    public void setBrand_id(int brand_id) {
                        this.brand_id = brand_id;
                    }

                    public String getProduct_name() {
                        return product_name;
                    }

                    public void setProduct_name(String product_name) {
                        this.product_name = product_name;
                    }

                    public String getImages() {
                        return images;
                    }

                    public void setImages(String images) {
                        this.images = images;
                    }

                   /* public String getModifier_images() {
                        return modifier_images;
                    }

                    public void setModifier_images(String modifier_images) {
                        this.modifier_images = modifier_images;
                    }*/

                    public ProductBrandBean getProduct_brand() {
                        return product_brand;
                    }

                    public void setProduct_brand(ProductBrandBean product_brand) {
                        this.product_brand = product_brand;
                    }

                    public static class ProductBrandBean {
                        /**
                         * id : 3
                         * brand_name : Levis
                         * images : null
                         */

                        private int id;
                        private String brand_name;
                        private Object images;

                        public int getId() {
                            return id;
                        }

                        public void setId(int id) {
                            this.id = id;
                        }

                        public String getBrand_name() {
                            return brand_name;
                        }

                        public void setBrand_name(String brand_name) {
                            this.brand_name = brand_name;
                        }

                        public Object getImages() {
                            return images;
                        }

                        public void setImages(Object images) {
                            this.images = images;
                        }
                    }
                }
            }
        }
    }

    public static class ReturnReasonsBean {
        /**
         * id : 1
         * reason : Order created by mistake
         */

        private int id;
        private String reason;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }
    }
}