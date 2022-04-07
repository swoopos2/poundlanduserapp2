package com.poundland.retail.model.responseModel;

import java.util.List;

public class ProductDetailByModifierResponseModel {
    /**
     * products : {"id":529,"product_id":18,"modifier_name":"M,blue","buy_price":"1.00","selling_price":"2.00","avl_quantity":100,"modifier_images":["uploaded/products/5649815825283760.jpeg"],"images":["uploaded/products/2731915825284200.png","uploaded/products/5693915825284201.png","uploaded/products/5763315825284202.png"],"new_price":"1.00"}
     * loyalitypoints : {"loyalty_points_value":0.5,"loyalty_points":0.5,"total_loyalty_points_value":0.25}
     */
    private boolean status;
    private String message;
    private ProductsBean products;
    private LoyalitypointsBean loyalitypoints;

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

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }

    public LoyalitypointsBean getLoyalitypoints() {
        return loyalitypoints;
    }

    public void setLoyalitypoints(LoyalitypointsBean loyalitypoints) {
        this.loyalitypoints = loyalitypoints;
    }

    public static class ProductsBean {
        /**
         * id : 529
         * product_id : 18
         * modifier_name : M,blue
         * buy_price : 1.00
         * selling_price : 2.00
         * avl_quantity : 100
         * modifier_images : ["uploaded/products/5649815825283760.jpeg"]
         * images : ["uploaded/products/2731915825284200.png","uploaded/products/5693915825284201.png","uploaded/products/5763315825284202.png"]
         * new_price : 1.00
         */
        private int id;
        private int product_id;
        private String modifier_name;
        private String buy_price;
        private String selling_price;
        private int avl_quantity;
        private String new_price;
        private String offer_title;
        private List<String> modifier_images;

        public String getOffer_title() {
            return offer_title;
        }

        public void setOffer_title(String offer_title) {
            this.offer_title = offer_title;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getProduct_id() {
            return product_id;
        }

        public void setProduct_id(int product_id) {
            this.product_id = product_id;
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

        public int getAvl_quantity() {
            return avl_quantity;
        }

        public void setAvl_quantity(int avl_quantity) {
            this.avl_quantity = avl_quantity;
        }

        public String getNew_price() {
            return new_price;
        }

        public void setNew_price(String new_price) {
            this.new_price = new_price;
        }

        public List<String> getModifier_images() {
            return modifier_images;
        }

        public void setModifier_images(List<String> modifier_images) {
            this.modifier_images = modifier_images;
        }
    }

    public static class LoyalitypointsBean {
        /**
         * loyalty_points_value : 0.25
         * loyalty_points : 20
         * total_loyalty_points_value : 5
         */

        private double loyalty_points_value;
        private double loyalty_points;
        private double total_loyalty_points_value;

        public double getLoyalty_points_value() {
            return loyalty_points_value;
        }

        public void setLoyalty_points_value(double loyalty_points_value) {
            this.loyalty_points_value = loyalty_points_value;
        }

        public double getLoyalty_points() {
            return loyalty_points;
        }

        public void setLoyalty_points(double loyalty_points) {
            this.loyalty_points = loyalty_points;
        }

        public double getTotal_loyalty_points_value() {
            return total_loyalty_points_value;
        }

        public void setTotal_loyalty_points_value(double total_loyalty_points_value) {
            this.total_loyalty_points_value = total_loyalty_points_value;
        }
    }
}
