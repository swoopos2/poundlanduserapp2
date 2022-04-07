package com.poundland.retail.model.requestModel;

import java.util.List;

public class CheckOutRequestModel {
    /**
     * merchant_id : 2
     * venue_id : 201911011148462
     * source_type : app
     * payment_mode : Card                                  * pos_order_id :* till_id : 0* staff_id : 0   * order_status :
     * card_id : card_1FosRKA4mS3BlT3IWFAz6V18
     * order_date : 2019-09-4 14:14:26
     * user_addr_id : 30
     * total_items : 1
     * amt_without_tax_discount : 120
     * total_discount : 80
     * total_tax : 0
     * "transaction_charge_amt":10,
     * net_amount : 120.2
     * delivery_charge : 15
     * loyelty_used_amount : 20
     * is_gift : 1
     * reorder_status : 0
     * delivery_type : Click & Collect
     * products : [{"product_qty":1}]
     */

    private String payment_gatway;
    private String cardNumber;
    private String cardExpiryMonth;
    private String cardExpiryYear;
    private String cardCVV;
    private String merchant_id;
    private String venue_id;
    private String source_type;
    private String payment_mode;
    private String card_id;
    private String order_date;
    private String user_addr_id;
    private int total_items;
    private double amt_without_tax_discount;
    private double total_discount;
    private double total_tax;
    private String net_amount;
    private String delivery_charge;
    private double loyelty_used_amount;
    private String is_gift;
    private int reorder_status;
    private String delivery_type;
    private String till_id;
    private String staff_id;
    private String transaction_charge_amt;
    private String transaction_charge;
    private String active_post_code;
    private String active_address_1;
    private String active_address_2;
    private String active_city;
    private boolean issave;
    private String shipping_ids;
    private int is_hospitality;
    private String app_type;
    private String message;
    private String shipping_date;
    private String coupon_code;
    private String coupon_amt;

    private List<ProductsBean> products;

    public CheckOutRequestModel(String payment_gatway, String cardNumber, String cardExpiryMonth, String cardExpiryYear,
                                String cardCVV, String merchant_id, String venue_id, String source_type, String payment_mode,
                                String card_id, String user_addr_id,
                                int total_items, Double amt_without_tax_discount, double total_discount,
                                double total_tax, String net_amount, String delivery_charge, double loyelty_used_amount,
                                String is_gift, int reorder_status, String delivery_type, String till_id,
                                String transaction_charge_amt, List<ProductsBean> products,
                                String active_post_code, String active_address_1, String active_address_2, String active_city,
                                boolean issave,String staff_id,String transaction_charge,String shipping_ids,int is_hospitality,
                                String app_type,String message ,String shipping_date,String coupon_code,String coupon_amt) {


        this.payment_gatway = payment_gatway;
        this.cardNumber = cardNumber;
        this.cardExpiryMonth = cardExpiryMonth;
        this.cardExpiryYear = cardExpiryYear;
        this.cardCVV = cardCVV;

        this.active_post_code = active_post_code;
        this.active_address_1 = active_address_1;
        this.active_address_2 = active_address_2;
        this.active_city = active_city;

        this.merchant_id = merchant_id;
        this.venue_id = venue_id;
        this.source_type = source_type;
        this.payment_mode = payment_mode;
        this.card_id = card_id;
        this.user_addr_id = user_addr_id;
        this.total_items = total_items;
        this.amt_without_tax_discount = amt_without_tax_discount;
        this.total_discount = total_discount;
        this.total_tax = total_tax;
        this.net_amount = net_amount;
        this.delivery_charge = delivery_charge;
        this.loyelty_used_amount = loyelty_used_amount;
        this.is_gift = is_gift;
        this.reorder_status = reorder_status;
        this.delivery_type = delivery_type;
        this.till_id = till_id;
        this.transaction_charge_amt = transaction_charge_amt;
        this.transaction_charge = transaction_charge;
        this.products = products;
        this.staff_id = staff_id;
        this.shipping_ids = shipping_ids;
        this.issave = issave;
        this.is_hospitality = is_hospitality;
        this.app_type = app_type;
        this.message = message;
        this.shipping_date = shipping_date;
        this.coupon_code = coupon_code;
        this.coupon_amt = coupon_amt;
    }

    public String getCoupon_code() {
        return coupon_code;
    }

    public void setCoupon_code(String coupon_code) {
        this.coupon_code = coupon_code;
    }

    public String getCoupon_amt() {
        return coupon_amt;
    }

    public void setCoupon_amt(String coupon_amt) {
        this.coupon_amt = coupon_amt;
    }

    public String getShipping_date() {
        return shipping_date;
    }

    public void setShipping_date(String shipping_date) {
        this.shipping_date = shipping_date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public int getIs_hospitality() {
        return is_hospitality;
    }

    public void setIs_hospitality(int is_hospitality) {
        this.is_hospitality = is_hospitality;
    }

    public String getApp_type() {
        return app_type;
    }

    public void setApp_type(String app_type) {
        this.app_type = app_type;
    }

    public String getShipping_ids() {
        return shipping_ids;
    }

    public void setShipping_ids(String shipping_ids) {
        this.shipping_ids = shipping_ids;
    }

    public String getTransaction_charge() {
        return transaction_charge;
    }

    public void setTransaction_charge(String transaction_charge) {
        this.transaction_charge = transaction_charge;
    }

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public boolean isIssave() {
        return issave;
    }

    public void setIssave(boolean issave) {
        this.issave = issave;
    }

    public String getActive_address_2() {
        return active_address_2;
    }

    public void setActive_address_2(String active_address_2) {
        this.active_address_2 = active_address_2;
    }

    public String getPayment_gatway() {
        return payment_gatway;
    }

    public void setPayment_gatway(String payment_gatway) {
        this.payment_gatway = payment_gatway;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardExpiryMonth() {
        return cardExpiryMonth;
    }

    public void setCardExpiryMonth(String cardExpiryMonth) {
        this.cardExpiryMonth = cardExpiryMonth;
    }

    public String getCardExpiryYear() {
        return cardExpiryYear;
    }

    public void setCardExpiryYear(String cardExpiryYear) {
        this.cardExpiryYear = cardExpiryYear;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public String getActive_post_code() {
        return active_post_code;
    }

    public void setActive_post_code(String active_post_code) {
        this.active_post_code = active_post_code;
    }

    public String getActive_address_1() {
        return active_address_1;
    }

    public void setActive_address_1(String active_address_1) {
        this.active_address_1 = active_address_1;
    }

    public String getActive_city() {
        return active_city;
    }

    public void setActive_city(String active_city) {
        this.active_city = active_city;
    }

    public String getTransaction_charge_amt() {
        return transaction_charge_amt;
    }

    public void setTransaction_charge_amt(String transaction_charge_amt) {
        this.transaction_charge_amt = transaction_charge_amt;
    }

    public String getTill_id() {
        return till_id;
    }

    public void setTill_id(String till_id) {
        this.till_id = till_id;
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

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }


    public String getUser_addr_id() {
        return user_addr_id;
    }

    public void setUser_addr_id(String user_addr_id) {
        this.user_addr_id = user_addr_id;
    }

    public int getTotal_items() {
        return total_items;
    }

    public void setTotal_items(int total_items) {
        this.total_items = total_items;
    }

    public double getAmt_without_tax_discount() {
        return amt_without_tax_discount;
    }

    public void setAmt_without_tax_discount(double amt_without_tax_discount) {
        this.amt_without_tax_discount = amt_without_tax_discount;
    }

    public double getTotal_discount() {
        return total_discount;
    }

    public void setTotal_discount(double total_discount) {
        this.total_discount = total_discount;
    }

    public double getTotal_tax() {
        return total_tax;
    }

    public void setTotal_tax(double total_tax) {
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

    public double getLoyelty_used_amount() {
        return loyelty_used_amount;
    }

    public void setLoyelty_used_amount(double loyelty_used_amount) {
        this.loyelty_used_amount = loyelty_used_amount;
    }

    public String getIs_gift() {
        return is_gift;
    }

    public void setIs_gift(String is_gift) {
        this.is_gift = is_gift;
    }

    public int getReorder_status() {
        return reorder_status;
    }

    public void setReorder_status(int reorder_status) {
        this.reorder_status = reorder_status;
    }

    public String getDelivery_type() {
        return delivery_type;
    }

    public void setDelivery_type(String delivery_type) {
        this.delivery_type = delivery_type;
    }

    public List<ProductsBean> getProducts() {
        return products;
    }

    public void setProducts(List<ProductsBean> products) {
        this.products = products;
    }

    public static class ProductsBean {
        /**
         * product_qty : 1
         * product_id : 8
         * modifier_id : 8
         * delivery_type : Click & Collect
         * delivery_address : 27a Oaklands Road, Wolverhampton, England- WV3 0DS
         * billing_address : Wake Green Road, Birmingham B13 9PG, UK
         * merchant_id : 2
         * venue_id : 201911011148462
         * attributes :
         * cost : 100
         * buy_price : 85
         * discount_applied : 40
         * offer_id : 2
         * tax_applied : 0
         * tax_id : 0
         * net_amount : 60
         * item_status : Complete
         */

        private int special_offer_id;
        private int product_qty;
        private int product_id;
        private String modifier_id;
        private String delivery_type;
        private String delivery_address;
        private String billing_address;
        private int merchant_id;
        private String venue_id;
        private String attributes;
        private double cost;
        private double buy_price;
        private double discount_applied;
        private long offer_id;
        private double tax_applied;
        private int tax_id;
        private String net_amount;
        private String item_status;
        private int return_day;

        private int case_qty;
        private String bulk_sale_price;


        public ProductsBean(int product_qty, int product_id, String modifier_id, String delivery_address, String billing_address,
                            int merchant_id, String venue_id, String attributes, double cost, double buy_price,
                            double discount_applied, long offer_id, double tax_applied, int tax_id, String net_amount,
                            String item_status, String delivery_type, int return_day, int special_offer_id, int case_qty,String bulk_sale_price) {
            this.product_qty = product_qty;
            this.product_id = product_id;
            this.modifier_id = modifier_id;
            this.delivery_address = delivery_address;
            this.billing_address = billing_address;
            this.merchant_id = merchant_id;
            this.venue_id = venue_id;
            this.attributes = attributes;
            this.cost = cost;
            this.buy_price = buy_price;
            this.discount_applied = discount_applied;
            this.offer_id = offer_id;
            this.tax_applied = tax_applied;
            this.tax_id = tax_id;
            this.net_amount = net_amount;
            this.item_status = item_status;
            this.delivery_type = delivery_type;
            this.return_day = return_day;
            this.special_offer_id = special_offer_id;
            this.case_qty = case_qty;
            this.bulk_sale_price = bulk_sale_price;
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

        public int getSpecial_offer_id() {
            return special_offer_id;
        }

        public void setSpecial_offer_id(int special_offer_id) {
            this.special_offer_id = special_offer_id;
        }

        public int getReturn_day() {
            return return_day;
        }

        public void setReturn_day(int return_day) {
            this.return_day = return_day;
        }

        public int getProduct_qty() {
            return product_qty;
        }

        public void setProduct_qty(int product_qty) {
            this.product_qty = product_qty;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(String modifier_id) {
            this.modifier_id = modifier_id;
        }

        public String getDelivery_type() {
            return delivery_type;
        }

        public void setDelivery_type(String delivery_type) {
            this.delivery_type = delivery_type;
        }

        public String getDelivery_address() {
            return delivery_address;
        }

        public void setDelivery_address(String delivery_address) {
            this.delivery_address = delivery_address;
        }

        public String getBilling_address() {
            return billing_address;
        }

        public void setBilling_address(String billing_address) {
            this.billing_address = billing_address;
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

        public String getAttributes() {
            return attributes;
        }

        public void setAttributes(String attributes) {
            this.attributes = attributes;
        }

        public double getCost() {
            return cost;
        }

        public void setCost(double cost) {
            this.cost = cost;
        }

        public double getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(double buy_price) {
            this.buy_price = buy_price;
        }

        public double getDiscount_applied() {
            return discount_applied;
        }

        public void setDiscount_applied(double discount_applied) {
            this.discount_applied = discount_applied;
        }

        public long getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(long offer_id) {
            this.offer_id = offer_id;
        }

        public double getTax_applied() {
            return tax_applied;
        }

        public void setTax_applied(double tax_applied) {
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

        public String getItem_status() {
            return item_status;
        }

        public void setItem_status(String item_status) {
            this.item_status = item_status;
        }
    }
}
