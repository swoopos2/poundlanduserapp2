package com.poundland.retail.model.responseModel;

public class AddToCartResponseModel {


    /**
     * status : true
     * message : Cart added Successfully.
     * cart : {"merchant_id":"10","venue_id":"2019071613143410","product_id":"3","modifier_id":"4","offer_id":"1","quantities":"2","cost_per_product":"10","coupon_code":null,"ip_address":null,"customer_id":5,"updated_at":"2019-11-26 08:11:14","created_at":"2019-11-26 08:11:14","id":140}
     * total_carts : 1
     */

    private boolean status;
    private String message;
    private CartBean cart;
    private int total_carts;

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

    public CartBean getCart() {
        return cart;
    }

    public void setCart(CartBean cart) {
        this.cart = cart;
    }

    public int getTotal_carts() {
        return total_carts;
    }

    public void setTotal_carts(int total_carts) {
        this.total_carts = total_carts;
    }

    public static class CartBean {
        /**
         * merchant_id : 10
         * venue_id : 2019071613143410
         * product_id : 3
         * modifier_id : 4
         * offer_id : 1
         * quantities : 2
         * cost_per_product : 10
         * coupon_code : null
         * ip_address : null
         * customer_id : 5
         * updated_at : 2019-11-26 08:11:14
         * created_at : 2019-11-26 08:11:14
         * id : 140
         */

        private String merchant_id;
        private String venue_id;
        private String product_id;
        private String modifier_id;
        private String offer_id;
        private String quantities;
        private String cost_per_product;
        private String coupon_code;
        private String ip_address;
        private int customer_id;
        private String updated_at;
        private String created_at;
        private int id;

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

        public String getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(String offer_id) {
            this.offer_id = offer_id;
        }

        public String getQuantities() {
            return quantities;
        }

        public void setQuantities(String quantities) {
            this.quantities = quantities;
        }

        public String getCost_per_product() {
            return cost_per_product;
        }

        public void setCost_per_product(String cost_per_product) {
            this.cost_per_product = cost_per_product;
        }

        public String getCoupon_code() {
            return coupon_code;
        }

        public void setCoupon_code(String coupon_code) {
            this.coupon_code = coupon_code;
        }

        public String getIp_address() {
            return ip_address;
        }

        public void setIp_address(String ip_address) {
            this.ip_address = ip_address;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }
    }
}
