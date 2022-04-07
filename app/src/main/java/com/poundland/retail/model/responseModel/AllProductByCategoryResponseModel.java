package com.poundland.retail.model.responseModel;

import java.util.List;

public class AllProductByCategoryResponseModel {


    /**
     * status : true
     * message : Product List
     * products : {"current_page":1,"data":[{"id":7,"merchant_id":2,"product_name":"Women Olive Green Solid Lightweight Puffer Jacket","product_description":"<p><span style=\"color: rgb(40, 44, 63); font-family: Whitney; font-size: 16px;\">Olive Green solid jacket, has a mock collar, 2 pockets, zip closure, long sleeves, straight hem, and polyester lining<\/span><br><\/p>","category_id":"2","brand_id":3,"images":"uploaded/products/3074915726105480.jpeg","buy_price":"100.00","selling_price":"110.00","modifier_id":7,"avl_quantity":3,"modifier_images":null,"venue_id":"201911011148462","venue_name":"Lilly Whites","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02","stars":"4.0"},{"id":26,"merchant_id":2,"product_name":"Marlin Women's Cotton Kurti With Palazzo Pant Set ","product_description":"Care Instructions: Hand Wash;Machine Wash\nFabric :- Kurta 100% Cotton, Plazzo 1000% Cotton , Colour:- Light C Green\nSize :- Kurta (Bust Size) M- 38\" L- 40\" XL- 42\" XXL-44\" Length :- Up to 46\" Sleeves :- 3/4 sleeves\nSize :- Plazzo WAIST REGULAR FIT For 28\" To 46\" This Plazzo Pant Having Elasticated Track with Drawstring - plazzo Length- Up to 39\"\nCARE INSTRUCTION :- Gentle Hand Wash Or Machine Wash Separately In Cold Water, And Dry In Shade Only.\n","category_id":"2","brand_id":11,"images":"uploaded/products/9050315730351550.jpeg","buy_price":"50.00","selling_price":"70.00","modifier_id":29,"avl_quantity":10,"modifier_images":null,"venue_id":"201911011148462","venue_name":"Lilly Whites","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02","stars":"0"},{"id":27,"merchant_id":2,"product_name":"ROSSO BRUNELLO Men's Tan Lace Ups Formal Leather Shoes","product_description":"Sole: rubber\nClosure: Lace-Up\nLACE UPS\nKeep away from moisture and extreme heat\nGenuine Leather Guaranteed\nProduct Dimensions : 30 X 18 X 12 cm ; 750 g\n","category_id":"2","brand_id":11,"images":"uploaded/products/5853415730351820.jpeg","buy_price":"80.00","selling_price":"100.00","modifier_id":30,"avl_quantity":10,"modifier_images":null,"venue_id":"201911011148462","venue_name":"Lilly Whites","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02","stars":"0"},{"id":28,"merchant_id":2,"product_name":"Lauwodun Mens Quick Dry Water Shoes Barefoot","product_description":"Sole: rubber\nClosure: Slip On\nShoe Width: Medium\nPEFECT MATERIAL: Upper with breathable ultra-lightweight fabric for fast draining, flexible and comfortable for leisure or any other sports activities\nGUM RUBBER SOLE: Anti-slip gum rubber outsoles are soft, bring more breathable feeling when you wearing.\nConvenience: Easy on and off, lightweight and compressible for easy packing.\nPROTECTION: Rubber sole and smooth neck prevent feet from blisters and chafing, thickening Insole prevent getting hurt by rock and sharp objects activities.","category_id":"2","brand_id":11,"images":"uploaded/products/1715415730351990.jpeg","buy_price":"88.00","selling_price":"99.00","modifier_id":31,"avl_quantity":10,"modifier_images":null,"venue_id":"201911011148462","venue_name":"Lilly Whites","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02","stars":"0"},{"id":41,"merchant_id":2,"product_name":"Flamboyant Pink Top","product_description":"<p><span style=\"color: rgb(17, 17, 17); font-family: &quot;josefin sans&quot;, sans-serif; font-size: 16px;\">Flamboyant Pink Top<\/span><br><\/p>","category_id":"2","brand_id":8,"images":"uploaded/products/5180815735443960.jpeg","buy_price":"25.00","selling_price":"40.00","modifier_id":52,"avl_quantity":30,"modifier_images":null,"venue_id":"201911011148462","venue_name":"Lilly Whites","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02","stars":"0"}],"first_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllProductsByCat?page=1","from":1,"last_page":1,"last_page_url":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllProductsByCat?page=1","next_page_url":null,"path":"http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllProductsByCat","per_page":10,"prev_page_url":null,"to":5,"total":5}
     */

    private boolean status;
    private String message;
    private ProductsBean products;
    private int isProductInCart;

    public int getIsProductInCart() {
        return isProductInCart;
    }

    public void setIsProductInCart(int isProductInCart) {
        this.isProductInCart = isProductInCart;
    }

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

    public static class ProductsBean {
        /**
         * current_page : 1
         * data : [{"id":7,"merchant_id":2,"product_name":"Women Olive Green Solid Lightweight Puffer Jacket","product_description":"<p><span style=\"color: rgb(40, 44, 63); font-family: Whitney; font-size: 16px;\">Olive Green solid jacket, has a mock collar, 2 pockets, zip closure, long sleeves, straight hem, and polyester lining<\/span><br><\/p>","category_id":"2","brand_id":3,"images":"uploaded/products/3074915726105480.jpeg","buy_price":"100.00","selling_price":"110.00","modifier_id":7,"avl_quantity":3,"modifier_images":null,"venue_id":"201911011148462","venue_name":"Lilly Whites","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02","stars":"4.0"},{"id":26,"merchant_id":2,"product_name":"Marlin Women's Cotton Kurti With Palazzo Pant Set ","product_description":"Care Instructions: Hand Wash;Machine Wash\nFabric :- Kurta 100% Cotton, Plazzo 1000% Cotton , Colour:- Light C Green\nSize :- Kurta (Bust Size) M- 38\" L- 40\" XL- 42\" XXL-44\" Length :- Up to 46\" Sleeves :- 3/4 sleeves\nSize :- Plazzo WAIST REGULAR FIT For 28\" To 46\" This Plazzo Pant Having Elasticated Track with Drawstring - plazzo Length- Up to 39\"\nCARE INSTRUCTION :- Gentle Hand Wash Or Machine Wash Separately In Cold Water, And Dry In Shade Only.\n","category_id":"2","brand_id":11,"images":"uploaded/products/9050315730351550.jpeg","buy_price":"50.00","selling_price":"70.00","modifier_id":29,"avl_quantity":10,"modifier_images":null,"venue_id":"201911011148462","venue_name":"Lilly Whites","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02","stars":"0"},{"id":27,"merchant_id":2,"product_name":"ROSSO BRUNELLO Men's Tan Lace Ups Formal Leather Shoes","product_description":"Sole: rubber\nClosure: Lace-Up\nLACE UPS\nKeep away from moisture and extreme heat\nGenuine Leather Guaranteed\nProduct Dimensions : 30 X 18 X 12 cm ; 750 g\n","category_id":"2","brand_id":11,"images":"uploaded/products/5853415730351820.jpeg","buy_price":"80.00","selling_price":"100.00","modifier_id":30,"avl_quantity":10,"modifier_images":null,"venue_id":"201911011148462","venue_name":"Lilly Whites","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02","stars":"0"},{"id":28,"merchant_id":2,"product_name":"Lauwodun Mens Quick Dry Water Shoes Barefoot","product_description":"Sole: rubber\nClosure: Slip On\nShoe Width: Medium\nPEFECT MATERIAL: Upper with breathable ultra-lightweight fabric for fast draining, flexible and comfortable for leisure or any other sports activities\nGUM RUBBER SOLE: Anti-slip gum rubber outsoles are soft, bring more breathable feeling when you wearing.\nConvenience: Easy on and off, lightweight and compressible for easy packing.\nPROTECTION: Rubber sole and smooth neck prevent feet from blisters and chafing, thickening Insole prevent getting hurt by rock and sharp objects activities.","category_id":"2","brand_id":11,"images":"uploaded/products/1715415730351990.jpeg","buy_price":"88.00","selling_price":"99.00","modifier_id":31,"avl_quantity":10,"modifier_images":null,"venue_id":"201911011148462","venue_name":"Lilly Whites","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02","stars":"0"},{"id":41,"merchant_id":2,"product_name":"Flamboyant Pink Top","product_description":"<p><span style=\"color: rgb(17, 17, 17); font-family: &quot;josefin sans&quot;, sans-serif; font-size: 16px;\">Flamboyant Pink Top<\/span><br><\/p>","category_id":"2","brand_id":8,"images":"uploaded/products/5180815735443960.jpeg","buy_price":"25.00","selling_price":"40.00","modifier_id":52,"avl_quantity":30,"modifier_images":null,"venue_id":"201911011148462","venue_name":"Lilly Whites","address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","delivery":1,"collection":1,"latitude":"52.5756465","longitude":"-2.1385980000000018","distance":"0.02","stars":"0"}]
         * first_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllProductsByCat?page=1
         * from : 1
         * last_page : 1
         * last_page_url : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllProductsByCat?page=1
         * next_page_url : null
         * path : http://34.245.78.97/SWOOPOSRETAILBACKOFFICE/public/api/swoope/getAllProductsByCat
         * per_page : 10
         * prev_page_url : null
         * to : 5
         * total : 5
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private String next_page_url;
        private String path;
        private int per_page;
        private String prev_page_url;
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

        public String getPrev_page_url() {
            return prev_page_url;
        }

        public void setPrev_page_url(String prev_page_url) {
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
             * id : 7
             * merchant_id : 2
             * product_name : Women Olive Green Solid Lightweight Puffer Jacket
             * product_description : <p><span style="color: rgb(40, 44, 63); font-family: Whitney; font-size: 16px;">Olive Green solid jacket, has a mock collar, 2 pockets, zip closure, long sleeves, straight hem, and polyester lining</span><br></p>
             * category_id : 2
             * brand_id : 3
             * images : uploaded/products/3074915726105480.jpeg
             * buy_price : 100.00
             * selling_price : 110.00
             * modifier_id : 7
             * avl_quantity : 3
             * modifier_images : null
             * venue_id : 201911011148462
             * venue_name : Lilly Whites
             * address_1 : Oaklands Road, Wolverhampton WV3 0DS, England, UK
             * address_2 : null
             * city_name : Wolverhampton
             * country : United Kingdom
             * post_code : WV3 0DS
             * delivery : 1
             * collection : 1
             * latitude : 52.5756465
             * longitude : -2.1385980000000018
             * distance : 0.02
             * stars : 4.0
             */

            private int id;
            private int offer_id;
            private int merchant_id;
            private String product_name;
            private String offer_title;
            private String product_description;
            private String category_id;
            private int brand_id;
            private String images;
            private String buy_price;
            private String selling_price;
            private String new_price;
            private int modifier_id;
            private int avl_quantity;
            private String modifier_images;
            private String venue_id;
            private String venue_name;
            private String address_1;
            private String address_2;
            private String city_name;
            private String country;
            private String post_code;
            private int delivery;
            private int collection;
            private String latitude;
            private String longitude;
            private String distance;
            private String stars;
            private boolean isWishlisted;
            private int isCart;
            private int mod_count;
            private int sameDayShipping;
            private int quantity_per_case;
            private String bulk_selling_price;
            private CartBean cart;

            public CartBean getCart() {
                return cart;
            }

            public void setCart(CartBean cart) {
                this.cart = cart;
            }

            public int getIsCart() {
                return isCart;
            }

            public void setIsCart(int isCart) {
                this.isCart = isCart;
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

            public int getSameDayShipping() {
                return sameDayShipping;
            }

            public void setSameDayShipping(int sameDayShipping) {
                this.sameDayShipping = sameDayShipping;
            }

            public int getMod_count() {
                return mod_count;
            }

            public void setMod_count(int mod_count) {
                this.mod_count = mod_count;
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

            public String getNew_price() {
                return new_price;
            }

            public void setNew_price(String new_price) {
                this.new_price = new_price;
            }

            public boolean isWishlisted() {
                return isWishlisted;
            }

            public void setWishlisted(boolean wishlisted) {
                isWishlisted = wishlisted;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getMerchant_id() {
                return merchant_id;
            }

            public void setMerchant_id(int merchant_id) {
                this.merchant_id = merchant_id;
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

            public int getBrand_id() {
                return brand_id;
            }

            public void setBrand_id(int brand_id) {
                this.brand_id = brand_id;
            }

            public String getImages() {
                return images;
            }

            public void setImages(String images) {
                this.images = images;
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

            public int getModifier_id() {
                return modifier_id;
            }

            public void setModifier_id(int modifier_id) {
                this.modifier_id = modifier_id;
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

            public String getAddress_1() {
                return address_1;
            }

            public void setAddress_1(String address_1) {
                this.address_1 = address_1;
            }

            public String getAddress_2() {
                return address_2;
            }

            public void setAddress_2(String address_2) {
                this.address_2 = address_2;
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

            public String getPost_code() {
                return post_code;
            }

            public void setPost_code(String post_code) {
                this.post_code = post_code;
            }

            public int getDelivery() {
                return delivery;
            }

            public void setDelivery(int delivery) {
                this.delivery = delivery;
            }

            public int getCollection() {
                return collection;
            }

            public void setCollection(int collection) {
                this.collection = collection;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public String getDistance() {
                return distance;
            }

            public void setDistance(String distance) {
                this.distance = distance;
            }

            public String getStars() {
                return stars;
            }

            public void setStars(String stars) {
                this.stars = stars;
            }

            public static class CartBean {
                /**
                 * quantities : 1
                 * bulk_quantities : 0
                 * total_carts : 1
                 * isCart : 1
                 */

                private int quantities;
                private int bulk_quantities;
                private int total_carts;
                private int isCart;

                public int getQuantities() {
                    return quantities;
                }

                public void setQuantities(int quantities) {
                    this.quantities = quantities;
                }

                public int getBulk_quantities() {
                    return bulk_quantities;
                }

                public void setBulk_quantities(int bulk_quantities) {
                    this.bulk_quantities = bulk_quantities;
                }

                public int getTotal_carts() {
                    return total_carts;
                }

                public void setTotal_carts(int total_carts) {
                    this.total_carts = total_carts;
                }

                public int getIsCart() {
                    return isCart;
                }

                public void setIsCart(int isCart) {
                    this.isCart = isCart;
                }
            }
        }
    }
}
