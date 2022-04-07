package com.poundland.retail.model.responseModel;

import java.util.List;

public class ModifierListByProductIdModel {


    /**
     * status : true
     * message : Product Modifiers Details
     * modifiers : [{"product_id":832,"product_name":"Women Navy Blue & Maroon Checked Indigo Hand Block Print Regular Top","merchant_id":15,"venue_id":"2020040808100815","product_image":"uploaded/products/2349816045566510.png","modifier_id":887,"modifier_name":"M,Pink","buy_price":"5.00","selling_price":"11.00","quantity_per_case":1,"bulk_selling_price":"0.00","avl_quantity":49976,"modifier_images":"uploaded/products/8700715990269570.png","offer_id":148,"offer_title":"Midweek Special","offer_type":"discper","discount_amount":"10.00","discount_type":1,"disc_detail_type":1,"isCart":0,"stars":{"count":2,"rating_value":"4.0"},"new_price":9.9},{"product_id":832,"product_name":"Women Navy Blue & Maroon Checked Indigo Hand Block Print Regular Top","merchant_id":15,"venue_id":"2020040808100815","product_image":"uploaded/products/2349816045566510.png","modifier_id":889,"modifier_name":"L,Pink","buy_price":"5.00","selling_price":"13.00","quantity_per_case":1,"bulk_selling_price":"0.00","avl_quantity":49993,"modifier_images":"uploaded/products/2276315990269940.png","offer_id":148,"offer_title":"Midweek Special","offer_type":"discper","discount_amount":"10.00","discount_type":1,"disc_detail_type":1,"isCart":0,"stars":{"count":2,"rating_value":"4.0"},"new_price":11.7},{"product_id":832,"product_name":"Women Navy Blue & Maroon Checked Indigo Hand Block Print Regular Top","merchant_id":15,"venue_id":"2020040808100815","product_image":"uploaded/products/2349816045566510.png","modifier_id":890,"modifier_name":"L,Blue","buy_price":"5.00","selling_price":"14.00","quantity_per_case":1,"bulk_selling_price":"0.00","avl_quantity":49996,"modifier_images":"uploaded/products/4645015990270120.png","offer_id":148,"offer_title":"Midweek Special","offer_type":"discper","discount_amount":"10.00","discount_type":1,"disc_detail_type":1,"isCart":0,"stars":{"count":2,"rating_value":"4.0"},"new_price":12.6}]
     */

    private boolean status;
    private String message;
    private List<ModifiersBean> modifiers;

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

    public List<ModifiersBean> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<ModifiersBean> modifiers) {
        this.modifiers = modifiers;
    }

    public static class ModifiersBean {
        // 2 EXTRA KEY ADDED BY FRONT END
        public int quantity = 1;
        public boolean isMChecked;

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public boolean isMChecked() {
            return isMChecked;
        }

        public void setMChecked(boolean MChecked) {
            isMChecked = MChecked;
        }

        /**
         * product_id : 832
         * product_name : Women Navy Blue & Maroon Checked Indigo Hand Block Print Regular Top
         * merchant_id : 15
         * venue_id : 2020040808100815
         * product_image : uploaded/products/2349816045566510.png
         * modifier_id : 887
         * modifier_name : M,Pink
         * buy_price : 5.00
         * selling_price : 11.00
         * quantity_per_case : 1
         * bulk_selling_price : 0.00
         * avl_quantity : 49976
         * modifier_images : uploaded/products/8700715990269570.png
         * offer_id : 148
         * offer_title : Midweek Special
         * offer_type : discper
         * discount_amount : 10.00
         * discount_type : 1
         * disc_detail_type : 1
         * isCart : 0
         * stars : {"count":2,"rating_value":"4.0"}
         * new_price : 9.9
         */

        private int product_id;
        private String product_name;
        private int merchant_id;
        private String venue_id;
        private String product_image;
        private int modifier_id;
        private String modifier_name;
        private String buy_price;
        private String selling_price;
        private int quantity_per_case;
        private String bulk_selling_price;
        private int avl_quantity;
        private String modifier_images;
        private int offer_id;
        private String offer_title;
        private String offer_type;
        private String discount_amount;
        private int discount_type;
        private int disc_detail_type;
        private int isCart;
        private StarsBean stars;
        private String new_price;

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
        }

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
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

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
        }

        public int getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(int modifier_id) {
            this.modifier_id = modifier_id;
        }

        public String getModifier_name() {
            return modifier_name;
        }

        public void setModifier_name(String modifier_name) {
            this.modifier_name = modifier_name;
        }

        public String getBuy_price() {
            return buy_price;
        }

        public void setBuy_price(String buy_price) {
            this.buy_price = buy_price;
        }

        public String getSelling_price() {
            return selling_price;
        }

        public void setSelling_price(String selling_price) {
            this.selling_price = selling_price;
        }

        public int getQuantity_per_case() {
            return quantity_per_case;
        }

        public void setQuantity_per_case(int quantity_per_case) {
            this.quantity_per_case = quantity_per_case;
        }

        public String getBulk_selling_price() {
            return bulk_selling_price;
        }

        public void setBulk_selling_price(String bulk_selling_price) {
            this.bulk_selling_price = bulk_selling_price;
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

        public int getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(int offer_id) {
            this.offer_id = offer_id;
        }

        public String getOffer_title() {
            return offer_title;
        }

        public void setOffer_title(String offer_title) {
            this.offer_title = offer_title;
        }

        public String getOffer_type() {
            return offer_type;
        }

        public void setOffer_type(String offer_type) {
            this.offer_type = offer_type;
        }

        public String getDiscount_amount() {
            return discount_amount;
        }

        public void setDiscount_amount(String discount_amount) {
            this.discount_amount = discount_amount;
        }

        public int getDiscount_type() {
            return discount_type;
        }

        public void setDiscount_type(int discount_type) {
            this.discount_type = discount_type;
        }

        public int getDisc_detail_type() {
            return disc_detail_type;
        }

        public void setDisc_detail_type(int disc_detail_type) {
            this.disc_detail_type = disc_detail_type;
        }

        public int getIsCart() {
            return isCart;
        }

        public void setIsCart(int isCart) {
            this.isCart = isCart;
        }

        public StarsBean getStars() {
            return stars;
        }

        public void setStars(StarsBean stars) {
            this.stars = stars;
        }

        public String getNew_price() {
            return new_price;
        }

        public void setNew_price(String new_price) {
            this.new_price = new_price;
        }

        public static class StarsBean {
            /**
             * count : 2
             * rating_value : 4.0
             */

            private int count;
            private String rating_value;

            public int getCount() {
                return count;
            }

            public void setCount(int count) {
                this.count = count;
            }

            public String getRating_value() {
                return rating_value;
            }

            public void setRating_value(String rating_value) {
                this.rating_value = rating_value;
            }
        }
    }
}
