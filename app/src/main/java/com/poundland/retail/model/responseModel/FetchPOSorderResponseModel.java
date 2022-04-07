package com.poundland.retail.model.responseModel;

import java.util.List;

public class FetchPOSorderResponseModel {


    /**
     * status : true
     * message : Order details.
     * orders : {"id":13153,"unique_code":"202112130749070","same_day_id":null,"cust_id":null,"source_type":"POS","payment_mode":"pending","merchant_id":"15","venue_id":"2020100508573315","reservation_id":"0","table_no":"22","order_date":"2021-12-13 07:40:00","order_status":"Pending","delivery_type":"Reservation","total_items":"2","amt_without_tax_discount":"18.99","total_discount":"0.00","total_tax":"0.00","net_amount":"20.89","delivery_charge":"0.00","service_charge":"1.90","tip_amt":"0.00","transaction_charge":"0.00","cust_message":null,"expected_delivery_date":null,"is_asap":"0","venue":{"id":62,"kyc_status":"pending","user_id":"136","venue_id":"2020100508573315","venue_name":"Bella Cosa","venue_email":"bellacosa@gmail.com","address_1":"Oaklands Road, Wolverhampton WV3 0DS, UK","address_2":null,"post_code":"WV3 0DS","city_name":"london","country":"United Kingdom","phone_number":"1218589888","venue_images":"20201005090417-Bella-Cosa.jpg,,,,,","start_days":"30","end_days":"45","collection_time":"45","preparation_time":null,"venue_type":"2"},"customer_details":null,"order_details":[{"id":30884,"order_id":"13153","pos_order_item_id":"1_16393812550577290_itdCt","product_qty":"1","product_id":"11597","modifier_id":"12575","attributes":"S","buy_price":"0.00","cost":"5.00","discount_applied":"0.00","tax_applied":"0.00","included_tax":"0.00","tax_type":null,"net_amount":"5.00","status":"4","return_days":"0","item_message":null,"add_on":null,"product_details":{"id":11597,"venue_id":"2020100508573315","product_name":"test misc 2","inventory_tag_id":"1","is_veg":"0"}},{"id":30885,"order_id":"13153","pos_order_item_id":"1_16393812550597290_y7I9D","product_qty":"1","product_id":"11056","modifier_id":"11896","attributes":"12 pack 12oz bottles","buy_price":"0.00","cost":"13.99","discount_applied":"0.00","tax_applied":"0.00","included_tax":"0.00","tax_type":null,"net_amount":"13.99","status":"4","return_days":"0","item_message":null,"add_on":null,"product_details":{"id":11056,"venue_id":"2020100508573315","product_name":"bud light","inventory_tag_id":"5","is_veg":"1"}}]}
     */

    private boolean status;
    private String message;
    private OrdersBean orders;

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

    public static class OrdersBean{
        /**
         * id : 13153
         * unique_code : 202112130749070
         * same_day_id : null
         * cust_id : null
         * source_type : POS
         * payment_mode : pending
         * merchant_id : 15
         * venue_id : 2020100508573315
         * reservation_id : 0
         * table_no : 22
         * order_date : 2021-12-13 07:40:00
         * order_status : Pending
         * delivery_type : Reservation
         * total_items : 2
         * amt_without_tax_discount : 18.99
         * total_discount : 0.00
         * total_tax : 0.00
         * net_amount : 20.89
         * delivery_charge : 0.00
         * service_charge : 1.90
         * tip_amt : 0.00
         * transaction_charge : 0.00
         * cust_message : null
         * expected_delivery_date : null
         * is_asap : 0
         * venue : {"id":62,"kyc_status":"pending","user_id":"136","venue_id":"2020100508573315","venue_name":"Bella Cosa","venue_email":"bellacosa@gmail.com","address_1":"Oaklands Road, Wolverhampton WV3 0DS, UK","address_2":null,"post_code":"WV3 0DS","city_name":"london","country":"United Kingdom","phone_number":"1218589888","venue_images":"20201005090417-Bella-Cosa.jpg,,,,,","start_days":"30","end_days":"45","collection_time":"45","preparation_time":null,"venue_type":"2"}
         * customer_details : null
         * order_details : [{"id":30884,"order_id":"13153","pos_order_item_id":"1_16393812550577290_itdCt","product_qty":"1","product_id":"11597","modifier_id":"12575","attributes":"S","buy_price":"0.00","cost":"5.00","discount_applied":"0.00","tax_applied":"0.00","included_tax":"0.00","tax_type":null,"net_amount":"5.00","status":"4","return_days":"0","item_message":null,"add_on":null,"product_details":{"id":11597,"venue_id":"2020100508573315","product_name":"test misc 2","inventory_tag_id":"1","is_veg":"0"}},{"id":30885,"order_id":"13153","pos_order_item_id":"1_16393812550597290_y7I9D","product_qty":"1","product_id":"11056","modifier_id":"11896","attributes":"12 pack 12oz bottles","buy_price":"0.00","cost":"13.99","discount_applied":"0.00","tax_applied":"0.00","included_tax":"0.00","tax_type":null,"net_amount":"13.99","status":"4","return_days":"0","item_message":null,"add_on":null,"product_details":{"id":11056,"venue_id":"2020100508573315","product_name":"bud light","inventory_tag_id":"5","is_veg":"1"}}]
         */

        private int id;
        private String unique_code;
        private Object same_day_id;
        private Object cust_id;
        private String source_type;
        private String payment_mode;
        private String merchant_id;
        private String venue_id;
        private String reservation_id;
        private String table_no;
        private String order_date;
        private String order_status;
        private String delivery_type;
        private String total_items;
        private String amt_without_tax_discount;
        private String total_discount;
        private String total_tax;
        private String net_amount;
        private String delivery_charge;
        private String service_charge;
        private String tip_amt;
        private String transaction_charge;
        private Object cust_message;
        private Object expected_delivery_date;
        private String is_asap;
        private VenueBean venue;
        private Object customer_details;
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

        public Object getSame_day_id() {
            return same_day_id;
        }

        public void setSame_day_id(Object same_day_id) {
            this.same_day_id = same_day_id;
        }

        public Object getCust_id() {
            return cust_id;
        }

        public void setCust_id(Object cust_id) {
            this.cust_id = cust_id;
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

        public String getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(String merchant_id) {
            this.merchant_id = merchant_id;
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

        public String getTable_no() {
            return table_no;
        }

        public void setTable_no(String table_no) {
            this.table_no = table_no;
        }

        public String getOrder_date() {
            return order_date;
        }

        public void setOrder_date(String order_date) {
            this.order_date = order_date;
        }

        public String getOrder_status() {
            return order_status;
        }

        public void setOrder_status(String order_status) {
            this.order_status = order_status;
        }

        public String getDelivery_type() {
            return delivery_type;
        }

        public void setDelivery_type(String delivery_type) {
            this.delivery_type = delivery_type;
        }

        public String getTotal_items() {
            return total_items;
        }

        public void setTotal_items(String total_items) {
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

        public String getService_charge() {
            return service_charge;
        }

        public void setService_charge(String service_charge) {
            this.service_charge = service_charge;
        }

        public String getTip_amt() {
            return tip_amt;
        }

        public void setTip_amt(String tip_amt) {
            this.tip_amt = tip_amt;
        }

        public String getTransaction_charge() {
            return transaction_charge;
        }

        public void setTransaction_charge(String transaction_charge) {
            this.transaction_charge = transaction_charge;
        }

        public Object getCust_message() {
            return cust_message;
        }

        public void setCust_message(Object cust_message) {
            this.cust_message = cust_message;
        }

        public Object getExpected_delivery_date() {
            return expected_delivery_date;
        }

        public void setExpected_delivery_date(Object expected_delivery_date) {
            this.expected_delivery_date = expected_delivery_date;
        }

        public String getIs_asap() {
            return is_asap;
        }

        public void setIs_asap(String is_asap) {
            this.is_asap = is_asap;
        }

        public VenueBean getVenue() {
            return venue;
        }

        public void setVenue(VenueBean venue) {
            this.venue = venue;
        }

        public Object getCustomer_details() {
            return customer_details;
        }

        public void setCustomer_details(Object customer_details) {
            this.customer_details = customer_details;
        }

        public List<OrderDetailsBean> getOrder_details() {
            return order_details;
        }

        public void setOrder_details(List<OrderDetailsBean> order_details) {
            this.order_details = order_details;
        }

        public static class VenueBean {
            /**
             * id : 62
             * kyc_status : pending
             * user_id : 136
             * venue_id : 2020100508573315
             * venue_name : Bella Cosa
             * venue_email : bellacosa@gmail.com
             * address_1 : Oaklands Road, Wolverhampton WV3 0DS, UK
             * address_2 : null
             * post_code : WV3 0DS
             * city_name : london
             * country : United Kingdom
             * phone_number : 1218589888
             * venue_images : 20201005090417-Bella-Cosa.jpg,,,,,
             * start_days : 30
             * end_days : 45
             * collection_time : 45
             * preparation_time : null
             * venue_type : 2
             */

            private int id;
            private String kyc_status;
            private String user_id;
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
            private String start_days;
            private String end_days;
            private String collection_time;
            private Object preparation_time;
            private String venue_type;
            private String payment_gatway;


            public String getPayment_gatway() {
                return payment_gatway;
            }

            public void setPayment_gatway(String payment_gatway) {
                this.payment_gatway = payment_gatway;
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

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
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

            public String getStart_days() {
                return start_days;
            }

            public void setStart_days(String start_days) {
                this.start_days = start_days;
            }

            public String getEnd_days() {
                return end_days;
            }

            public void setEnd_days(String end_days) {
                this.end_days = end_days;
            }

            public String getCollection_time() {
                return collection_time;
            }

            public void setCollection_time(String collection_time) {
                this.collection_time = collection_time;
            }

            public Object getPreparation_time() {
                return preparation_time;
            }

            public void setPreparation_time(Object preparation_time) {
                this.preparation_time = preparation_time;
            }

            public String getVenue_type() {
                return venue_type;
            }

            public void setVenue_type(String venue_type) {
                this.venue_type = venue_type;
            }
        }

        public static class OrderDetailsBean {
            /**
             * id : 30884
             * order_id : 13153
             * pos_order_item_id : 1_16393812550577290_itdCt
             * product_qty : 1
             * product_id : 11597
             * modifier_id : 12575
             * attributes : S
             * buy_price : 0.00
             * cost : 5.00
             * discount_applied : 0.00
             * tax_applied : 0.00
             * included_tax : 0.00
             * tax_type : null
             * net_amount : 5.00
             * status : 4
             * return_days : 0
             * item_message : null
             * add_on : null
             * product_details : {"id":11597,"venue_id":"2020100508573315","product_name":"test misc 2","inventory_tag_id":"1","is_veg":"0"}
             */

            private int id;
            private String order_id;
            private String pos_order_item_id;
            private String product_qty;
            private String product_id;
            private String modifier_id;
            private String attributes;
            private String buy_price;
            private String cost;
            private String discount_applied;
            private String tax_applied;
            private String included_tax;
            private Object tax_type;
            private String net_amount;
            private String status;
            private String return_days;
            private Object item_message;
            private List<AddOnBean> add_on;
            private ProductDetailsBean product_details;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
            }

            public String getPos_order_item_id() {
                return pos_order_item_id;
            }

            public void setPos_order_item_id(String pos_order_item_id) {
                this.pos_order_item_id = pos_order_item_id;
            }

            public String getProduct_qty() {
                return product_qty;
            }

            public void setProduct_qty(String product_qty) {
                this.product_qty = product_qty;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }

            public String getModifier_id() {
                return modifier_id;
            }

            public void setModifier_id(String modifier_id) {
                this.modifier_id = modifier_id;
            }

            public String getAttributes() {
                return attributes;
            }

            public void setAttributes(String attributes) {
                this.attributes = attributes;
            }

            public String getBuy_price() {
                return buy_price;
            }

            public void setBuy_price(String buy_price) {
                this.buy_price = buy_price;
            }

            public String getCost() {
                return cost;
            }

            public void setCost(String cost) {
                this.cost = cost;
            }

            public String getDiscount_applied() {
                return discount_applied;
            }

            public void setDiscount_applied(String discount_applied) {
                this.discount_applied = discount_applied;
            }

            public String getTax_applied() {
                return tax_applied;
            }

            public void setTax_applied(String tax_applied) {
                this.tax_applied = tax_applied;
            }

            public String getIncluded_tax() {
                return included_tax;
            }

            public void setIncluded_tax(String included_tax) {
                this.included_tax = included_tax;
            }

            public Object getTax_type() {
                return tax_type;
            }

            public void setTax_type(Object tax_type) {
                this.tax_type = tax_type;
            }

            public String getNet_amount() {
                return net_amount;
            }

            public void setNet_amount(String net_amount) {
                this.net_amount = net_amount;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public String getReturn_days() {
                return return_days;
            }

            public void setReturn_days(String return_days) {
                this.return_days = return_days;
            }

            public Object getItem_message() {
                return item_message;
            }

            public void setItem_message(Object item_message) {
                this.item_message = item_message;
            }

            public List<AddOnBean> getAdd_on() {
                return add_on;
            }

            public void setAdd_on(List<AddOnBean> add_on) {
                this.add_on = add_on;
            }

            public ProductDetailsBean getProduct_details() {
                return product_details;
            }

            public void setProduct_details(ProductDetailsBean product_details) {
                this.product_details = product_details;
            }

            public static class ProductDetailsBean {
                /**
                 * id : 11597
                 * venue_id : 2020100508573315
                 * product_name : test misc 2
                 * inventory_tag_id : 1
                 * is_veg : 0
                 */

                private int id;
                private String venue_id;
                private String product_name;
                private String inventory_tag_id;
                private String is_veg;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public String getVenue_id() {
                    return venue_id;
                }

                public void setVenue_id(String venue_id) {
                    this.venue_id = venue_id;
                }

                public String getProduct_name() {
                    return product_name;
                }

                public void setProduct_name(String product_name) {
                    this.product_name = product_name;
                }

                public String getInventory_tag_id() {
                    return inventory_tag_id;
                }

                public void setInventory_tag_id(String inventory_tag_id) {
                    this.inventory_tag_id = inventory_tag_id;
                }

                public String getIs_veg() {
                    return is_veg;
                }

                public void setIs_veg(String is_veg) {
                    this.is_veg = is_veg;
                }
            }

            public static class AddOnBean  {
                /**
                 * id : 1_1639998169702130173_b7BLcOysXL
                 * qty : 1
                 * price : 1.5
                 * addon_id : 193
                 * name : Anchovies
                 */

                private String id;
                private int qty;
                private double price;
                private String addon_id;
                private String name;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public int getQty() {
                    return qty;
                }

                public void setQty(int qty) {
                    this.qty = qty;
                }

                public double getPrice() {
                    return price;
                }

                public void setPrice(double price) {
                    this.price = price;
                }

                public String getAddon_id() {
                    return addon_id;
                }

                public void setAddon_id(String addon_id) {
                    this.addon_id = addon_id;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }
    }
}