package com.poundland.retail.model.requestModel;

import java.util.List;

public class AddToCartHospitalityRequestModel {
    /**
     * merchant_id : 5
     * venue_id : 202004271734165
     * customer_id : 107
     * product_id : 871
     * modifier_id : 1566
     * quantities : 1
     * offer_id : null
     * special_offer_id : 0
     * cost_per_product : 3.59
     * base_price : 2.99
     * add_ons : [{"id":"142","qty":"1","price":0,"addon_id":"22","name":"Chilli Base","groupId":"","tot_price":0}]
     * addon_ids : 142,143
     * addon_qty_json : [142,143]
     */

    private int merchant_id;
    private String venue_id;
    private String reservation_id;
    private int customer_id;
    private int product_id;
    private int modifier_id;
    private int quantities;
    private int offer_id;
    private int special_offer_id;
    private int is_hospitality;
    private String cost_per_product;
    private String base_price;
    private String allergens;
    private String addon_ids;
    private List<AddOnsBean> add_ons;
    private List<Integer> addon_qty_json;

    public AddToCartHospitalityRequestModel(String reservation_id, int merchant_id, String venue_id, int customer_id, int product_id, int modifier_id, int quantities, int offer_id, int special_offer_id, String cost_per_product, String base_price, String addon_ids, List<AddOnsBean> add_ons, List<Integer> addon_qty_json, String allergens,
                                            int is_hospitality) {
        this.reservation_id = reservation_id;
        this.merchant_id = merchant_id;
        this.venue_id = venue_id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.modifier_id = modifier_id;
        this.quantities = quantities;
        this.offer_id = offer_id;
        this.special_offer_id = special_offer_id;
        this.cost_per_product = cost_per_product;
        this.base_price = base_price;
        this.allergens = allergens;
        this.addon_ids = addon_ids;
        this.add_ons = add_ons;
        this.addon_qty_json = addon_qty_json;
        this.is_hospitality = is_hospitality;
    }

    public String getAllergens() {
        return allergens;
    }

    public void setAllergens(String allergens) {
        this.allergens = allergens;
    }

    public String getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(String reservation_id) {
        this.reservation_id = reservation_id;
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

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getModifier_id() {
        return modifier_id;
    }

    public void setModifier_id(int modifier_id) {
        this.modifier_id = modifier_id;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setQuantities(int quantities) {
        this.quantities = quantities;
    }

    public int getOffer_id() {
        return offer_id;
    }

    public void setOffer_id(int offer_id) {
        this.offer_id = offer_id;
    }

    public int getSpecial_offer_id() {
        return special_offer_id;
    }

    public void setSpecial_offer_id(int special_offer_id) {
        this.special_offer_id = special_offer_id;
    }

    public String getCost_per_product() {
        return cost_per_product;
    }

    public void setCost_per_product(String cost_per_product) {
        this.cost_per_product = cost_per_product;
    }

    public String getBase_price() {
        return base_price;
    }

    public void setBase_price(String base_price) {
        this.base_price = base_price;
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
