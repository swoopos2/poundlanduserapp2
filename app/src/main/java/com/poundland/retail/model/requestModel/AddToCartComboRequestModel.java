package com.poundland.retail.model.requestModel;

import java.util.List;

public class AddToCartComboRequestModel {


    private List<CartsBean> carts;

    public AddToCartComboRequestModel(List<CartsBean> carts) {
        this.carts = carts;
    }

    public List<CartsBean> getCarts() {
        return carts;
    }

    public void setCarts(List<CartsBean> carts) {
        this.carts = carts;
    }

    public static class CartsBean {
        /**
         * venue_id : 2019071613181810
         * merchant_id : 10
         * customer_id : 6
         * product_id : 4
         * modifier_id : 6
         * offer_id :
         * quantities : 1
         * cost_per_product : 1100.75
         * coupon_code :
         * ip_address : 127.0.0.1
         * combo_group : 4,6
         */

        private String venue_id;
        private String merchant_id;
        private String customer_id;
        private String product_id;
        private String modifier_id;
        private String offer_id;
        private int quantities;
        private String cost_per_product;
        private String coupon_code;
        private String ip_address;
        private String combo_group;
        private String base_price ;

        private int special_offer_id;
        private String addon_ids;
        private List<AddOnsBean> add_ons;
        private List<Integer> addon_qty_json;
        private int is_hospitality;

        public CartsBean(String venue_id, String merchant_id, String customer_id, String product_id, String modifier_id,
                         String offer_id, int quantities, String cost_per_product, String coupon_code, String ip_address, String combo_group,String base_price) {
            this.venue_id = venue_id;
            this.merchant_id = merchant_id;
            this.customer_id = customer_id;
            this.product_id = product_id;
            this.modifier_id = modifier_id;
            this.offer_id = offer_id;
            this.quantities = quantities;
            this.cost_per_product = cost_per_product;
            this.coupon_code = coupon_code;
            this.ip_address = ip_address;
            this.combo_group = combo_group;
            this.base_price = base_price;
        }



        public CartsBean(String venue_id, String merchant_id, String customer_id, String product_id, String modifier_id, int quantities,
                     String offer_id, int special_offer_id, String cost_per_product, String base_price, String addon_ids,
                     List<AddToCartComboRequestModel.CartsBean.AddOnsBean> add_ons, List<Integer> addon_qty_json, int is_hospitality,
                         String coupon_code, String ip_address) {
            this.venue_id = venue_id;
            this.merchant_id = merchant_id;
            this.customer_id = customer_id;
            this.product_id = product_id;
            this.modifier_id = modifier_id;
            this.quantities = quantities;
            this.offer_id = offer_id;
            this.special_offer_id = special_offer_id;
            this.cost_per_product = cost_per_product;
            this.base_price = base_price;
            this.addon_ids = addon_ids;
            this.add_ons = add_ons;
            this.addon_qty_json = addon_qty_json;
            this.is_hospitality = is_hospitality;
            this.coupon_code = coupon_code;
            this.ip_address = ip_address;
        }



        public int getSpecial_offer_id() {
            return special_offer_id;
        }

        public void setSpecial_offer_id(int special_offer_id) {
            this.special_offer_id = special_offer_id;
        }

        public String getAddon_ids() {
            return addon_ids;
        }

        public void setAddon_ids(String addon_ids) {
            this.addon_ids = addon_ids;
        }

        public List<AddOnsBean> getAdd_ons() {
            return add_ons;
        }

        public void setAdd_ons(List<AddOnsBean> add_ons) {
            this.add_ons = add_ons;
        }

        public List<Integer> getAddon_qty_json() {
            return addon_qty_json;
        }

        public void setAddon_qty_json(List<Integer> addon_qty_json) {
            this.addon_qty_json = addon_qty_json;
        }

        public int getIs_hospitality() {
            return is_hospitality;
        }

        public void setIs_hospitality(int is_hospitality) {
            this.is_hospitality = is_hospitality;
        }

        public String getBase_price() {
            return base_price;
        }

        public void setBase_price(String base_price) {
            this.base_price = base_price;
        }
        public String getCombo_group() {
            return combo_group;
        }

        public void setCombo_group(String combo_group) {
            this.combo_group = combo_group;
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

        public String getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(String customer_id) {
            this.customer_id = customer_id;
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

        public int getQuantities() {
            return quantities;
        }

        public void setQuantities(int quantities) {
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

        public static class AddOnsBean {
            /**
             * id : 142
             * qty : 1
             * price : 0
             * addon_id : 22
             * name : Chilli Base
             * groupId :
             * tot_price : 0
             */

            private String id;
            private String qty;
            private String price;
            private String addon_id;
            private String name;
            private String groupId;
            private String tot_price;

            public AddOnsBean(String id, String qty, String price, String addon_id, String name, String groupId, String tot_price) {
                this.id = id;
                this.qty = qty;
                this.price = price;
                this.addon_id = addon_id;
                this.name = name;
                this.groupId = groupId;
                this.tot_price = tot_price;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getQty() {
                return qty;
            }

            public void setQty(String qty) {
                this.qty = qty;
            }

            public String getPrice() {
                return price;
            }

            public void setPrice(String price) {
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

            public String getGroupId() {
                return groupId;
            }

            public void setGroupId(String groupId) {
                this.groupId = groupId;
            }

            public String getTot_price() {
                return tot_price;
            }

            public void setTot_price(String tot_price) {
                this.tot_price = tot_price;
            }
        }
    }
}
