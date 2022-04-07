package com.poundland.retail.model.responseModel;

import java.util.List;

public class HomeFilterResponseModel {

    /**
     * status : true
     * message : Product List
     * products : {"current_page":1,"data":[{"id":18,"merchant_id":2,"product_name":"Women Black & Maroon Kalamkari Hand Block Print Ethnic Maxi Skirt with Gathers","product_description":"<p><span style=\"box-sizing: inherit; display: inline-block; margin-top: 0px; color: rgb(40, 44, 63); font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif; font-size: 16px;\">Special Technique/Craft:<\/span><br style=\"box-sizing: inherit; color: rgb(40, 44, 63); font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif; font-size: 16px;\"><span style=\"color: rgb(40, 44, 63); font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif; font-size: 16px;\">Traditionally Kalamkari is a style of hand painting done on &nbsp;fabric with a pen, using natural dyes. The word Kalamkari is derived from a Persian word where 'kalam' means pen and 'kari' refers to craftsmanship.Now-a-days Kalamkari is a type of hand-painted or block-printed textile, wherein motifs are essentially printed with hand-carved traditional blocks with painted by hands using a pen. Motifs drawn in Kalamkari spans from flowers, peacock, paisleys to divine characters of Hindu epics like Mahabharata and Ramayana.&nbsp;<\/span><br><\/p>","category_id":"4","brand_id":2,"images":"uploaded/products/2731915825284200.png","buy_price":"0.50","selling_price":"1.00","modifier_id":18,"avl_quantity":66,"modifier_images":null,"venue_id":"202002110747362","venue_name":"AmazonGo","address_1":"Wolverhampton Bus Station, Victoria Street, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV1 1LD","venue_images":"1581407256.amazon (1).jpg,,,,,","phone_number":"44456498498648","venue_email":"goamazon@nwesmail.com","home_delivery_mov":"0.30","delivery":1,"collection":1,"delivery_distance":0,"latitude":"52.5855814","longitude":"-2.1221402","distance":"3,725.19","stars":"4.5"},{"id":37,"merchant_id":2,"product_name":"Women Black Solid Top","product_description":"<div class=\"meta-info\" style=\"box-sizing: inherit; color: rgb(40, 44, 63); font-size: medium; margin: 0px; padding: 0px; position: relative; font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif;\"><div class=\"meta-desc\" style=\"box-sizing: inherit; margin: 5px 0px; display: inline-block; width: 461.734px; vertical-align: top;\">100% Original Products<\/div><\/div><div class=\"meta-info\" style=\"box-sizing: inherit; color: rgb(40, 44, 63); font-size: medium; margin: 0px; padding: 0px; position: relative; font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif;\"><div class=\"meta-desc\" style=\"box-sizing: inherit; margin: 5px 0px; display: inline-block; width: 461.734px; vertical-align: top;\">Free Delivery on order above Rs.&nbsp;1199<\/div><\/div><div class=\"meta-info\" style=\"box-sizing: inherit; color: rgb(40, 44, 63); font-size: medium; margin: 0px; padding: 0px; position: relative; font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif;\"><div class=\"meta-desc\" style=\"box-sizing: inherit; margin: 5px 0px; display: inline-block; width: 461.734px; vertical-align: top;\">Cash on delivery might be available<\/div><\/div><div class=\"meta-info\" style=\"box-sizing: inherit; color: rgb(40, 44, 63); font-size: medium; margin: 0px; padding: 0px; position: relative; font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif;\"><div class=\"meta-desc\" style=\"box-sizing: inherit; margin: 5px 0px; display: inline-block; width: 461.734px; vertical-align: top;\">Easy 30 days returns and exchanges<\/div><\/div><div class=\"meta-info\" style=\"b D: ox-sizing: inherit; color: rgb(40, 44, 63); font-size: medium; margin: 0px; padding: 0px; position: relative; font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif;\"><div class=\"meta-desc\" style=\"box-sizing: inherit; margin: 5px 0px; display: inline-block; width: 461.734px; vertical-align: top;\">Try &amp; Buy might be available<\/div><\/div>","category_id":"4","brand_id":2,"images":"uploaded/products/6335115814244390.jpeg","buy_price":"0.35","selling_price":"1.00","modifier_id":37,"avl_quantity":77,"modifier_images":null,"venue_id":"202002110747362","venue_name":"AmazonGo","address_1":"Wolverhampton Bus Station, Victoria Street, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV1 1LD","venue_images":"1581407256.amazon (1).jpg,,,,,","phone_number":"44456498498648","venue_email":"goamazon@nwesmail.com","home_delivery_mov":"0.30","delivery":1,"collection":1,"delivery_distance":0,"latitude":"52.5855814","longitude":"-2.1221402","distance":"3,725.19","stars":"0"}],"first_page_url":"https://swooperetail.com/admin/public/api/swoope/getProductsByFilter?page=1","from":1,"last_page":1,"last_page_url":"https://swooperetail.com/admin/public/api/swoope/getProductsByFilter?page=1","next_page_url":null,"path":"https://swooperetail.com/admin/public/api/swoope/getProductsByFilter","per_page":10,"prev_page_url":null,"to":2,"total":2}
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
         * data : [{"id":18,"merchant_id":2,"product_name":"Women Black & Maroon Kalamkari Hand Block Print Ethnic Maxi Skirt with Gathers","product_description":"<p><span style=\"box-sizing: inherit; display: inline-block; margin-top: 0px; color: rgb(40, 44, 63); font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif; font-size: 16px;\">Special Technique/Craft:<\/span><br style=\"box-sizing: inherit; color: rgb(40, 44, 63); font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif; font-size: 16px;\"><span style=\"color: rgb(40, 44, 63); font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif; font-size: 16px;\">Traditionally Kalamkari is a style of hand painting done on &nbsp;fabric with a pen, using natural dyes. The word Kalamkari is derived from a Persian word where 'kalam' means pen and 'kari' refers to craftsmanship.Now-a-days Kalamkari is a type of hand-painted or block-printed textile, wherein motifs are essentially printed with hand-carved traditional blocks with painted by hands using a pen. Motifs drawn in Kalamkari spans from flowers, peacock, paisleys to divine characters of Hindu epics like Mahabharata and Ramayana.&nbsp;<\/span><br><\/p>","category_id":"4","brand_id":2,"images":"uploaded/products/2731915825284200.png","buy_price":"0.50","selling_price":"1.00","modifier_id":18,"avl_quantity":66,"modifier_images":null,"venue_id":"202002110747362","venue_name":"AmazonGo","address_1":"Wolverhampton Bus Station, Victoria Street, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV1 1LD","venue_images":"1581407256.amazon (1).jpg,,,,,","phone_number":"44456498498648","venue_email":"goamazon@nwesmail.com","home_delivery_mov":"0.30","delivery":1,"collection":1,"delivery_distance":0,"latitude":"52.5855814","longitude":"-2.1221402","distance":"3,725.19","stars":"4.5"},{"id":37,"merchant_id":2,"product_name":"Women Black Solid Top","product_description":"<div class=\"meta-info\" style=\"box-sizing: inherit; color: rgb(40, 44, 63); font-size: medium; margin: 0px; padding: 0px; position: relative; font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif;\"><div class=\"meta-desc\" style=\"box-sizing: inherit; margin: 5px 0px; display: inline-block; width: 461.734px; vertical-align: top;\">100% Original Products<\/div><\/div><div class=\"meta-info\" style=\"box-sizing: inherit; color: rgb(40, 44, 63); font-size: medium; margin: 0px; padding: 0px; position: relative; font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif;\"><div class=\"meta-desc\" style=\"box-sizing: inherit; margin: 5px 0px; display: inline-block; width: 461.734px; vertical-align: top;\">Free Delivery on order above Rs.&nbsp;1199<\/div><\/div><div class=\"meta-info\" style=\"box-sizing: inherit; color: rgb(40, 44, 63); font-size: medium; margin: 0px; padding: 0px; position: relative; font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif;\"><div class=\"meta-desc\" style=\"box-sizing: inherit; margin: 5px 0px; display: inline-block; width: 461.734px; vertical-align: top;\">Cash on delivery might be available<\/div><\/div><div class=\"meta-info\" style=\"box-sizing: inherit; color: rgb(40, 44, 63); font-size: medium; margin: 0px; padding: 0px; position: relative; font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif;\"><div class=\"meta-desc\" style=\"box-sizing: inherit; margin: 5px 0px; display: inline-block; width: 461.734px; vertical-align: top;\">Easy 30 days returns and exchanges<\/div><\/div><div class=\"meta-info\" style=\"b D: ox-sizing: inherit; color: rgb(40, 44, 63); font-size: medium; margin: 0px; padding: 0px; position: relative; font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif;\"><div class=\"meta-desc\" style=\"box-sizing: inherit; margin: 5px 0px; display: inline-block; width: 461.734px; vertical-align: top;\">Try &amp; Buy might be available<\/div><\/div>","category_id":"4","brand_id":2,"images":"uploaded/products/6335115814244390.jpeg","buy_price":"0.35","selling_price":"1.00","modifier_id":37,"avl_quantity":77,"modifier_images":null,"venue_id":"202002110747362","venue_name":"AmazonGo","address_1":"Wolverhampton Bus Station, Victoria Street, Wolverhampton, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV1 1LD","venue_images":"1581407256.amazon (1).jpg,,,,,","phone_number":"44456498498648","venue_email":"goamazon@nwesmail.com","home_delivery_mov":"0.30","delivery":1,"collection":1,"delivery_distance":0,"latitude":"52.5855814","longitude":"-2.1221402","distance":"3,725.19","stars":"0"}]
         * first_page_url : https://swooperetail.com/admin/public/api/swoope/getProductsByFilter?page=1
         * from : 1
         * last_page : 1
         * last_page_url : https://swooperetail.com/admin/public/api/swoope/getProductsByFilter?page=1
         * next_page_url : null
         * path : https://swooperetail.com/admin/public/api/swoope/getProductsByFilter
         * per_page : 10
         * prev_page_url : null
         * to : 2
         * total : 2
         */

        private int current_page;
        private String first_page_url;
        private int from;
        private int last_page;
        private String last_page_url;
        private Object next_page_url;
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

        public Object getNext_page_url() {
            return next_page_url;
        }

        public void setNext_page_url(Object next_page_url) {
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
             * id : 18
             * merchant_id : 2
             * product_name : Women Black & Maroon Kalamkari Hand Block Print Ethnic Maxi Skirt with Gathers
             * product_description : <p><span style="box-sizing: inherit; display: inline-block; margin-top: 0px; color: rgb(40, 44, 63); font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif; font-size: 16px;">Special Technique/Craft:</span><br style="box-sizing: inherit; color: rgb(40, 44, 63); font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif; font-size: 16px;"><span style="color: rgb(40, 44, 63); font-family: Whitney, -apple-system, BlinkMacSystemFont, &quot;Segoe UI&quot;, Roboto, Helvetica, Arial, sans-serif; font-size: 16px;">Traditionally Kalamkari is a style of hand painting done on &nbsp;fabric with a pen, using natural dyes. The word Kalamkari is derived from a Persian word where 'kalam' means pen and 'kari' refers to craftsmanship.Now-a-days Kalamkari is a type of hand-painted or block-printed textile, wherein motifs are essentially printed with hand-carved traditional blocks with painted by hands using a pen. Motifs drawn in Kalamkari spans from flowers, peacock, paisleys to divine characters of Hindu epics like Mahabharata and Ramayana.&nbsp;</span><br></p>
             * category_id : 4
             * brand_id : 2
             * images : uploaded/products/2731915825284200.png
             * buy_price : 0.50
             * selling_price : 1.00
             * modifier_id : 18
             * avl_quantity : 66
             * modifier_images : null
             * venue_id : 202002110747362
             * venue_name : AmazonGo
             * address_1 : Wolverhampton Bus Station, Victoria Street, Wolverhampton, UK
             * address_2 : null
             * city_name : Wolverhampton
             * country : United Kingdom
             * post_code : WV1 1LD
             * venue_images : 1581407256.amazon (1).jpg,,,,,
             * phone_number : 44456498498648
             * venue_email : goamazon@nwesmail.com
             * home_delivery_mov : 0.30
             * delivery : 1
             * collection : 1
             * delivery_distance : 0
             * latitude : 52.5855814
             * longitude : -2.1221402
             * distance : 3,725.19
             * stars : 4.5
             */

            private int id;
            private int merchant_id;
            private String product_name;
            private String product_description;
            private String category_id;
            private int brand_id;
            private String images;
            private String buy_price;
            private String selling_price;
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
            private List<String> venue_images;
            private String phone_number;
            private String venue_email;
            private String home_delivery_mov;
            private int home_delivery;
            private int click_collect;
            private int delivery;
            private int collection;
            private int delivery_distance;
            private String latitude;
            private String longitude;
            private String distance;
            private String stars;
            private String opening_time;
            private String closing_time;
            private String isClose;
            private int total_offers;
            private boolean isWishlisted;
            private String offer_title;
            private int mod_count;
            private int sameDayShipping;
            private int quantity_per_case;
            private String bulk_selling_price;
            private int isCart;
            private CartBean cart;

            public int getIsCart() {
                return isCart;
            }

            public void setIsCart(int isCart) {
                this.isCart = isCart;
            }

            public CartBean getCart() {
                return cart;
            }

            public void setCart(CartBean cart) {
                this.cart = cart;
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

            public String getOffer_title() {
                return offer_title;
            }

            public void setOffer_title(String offer_title) {
                this.offer_title = offer_title;
            }

            public boolean isWishlisted() {
                return isWishlisted;
            }

            public void setWishlisted(boolean wishlisted) {
                isWishlisted = wishlisted;
            }

            public void setAddress_2(String address_2) {
                this.address_2 = address_2;
            }

            public void setVenue_images(List<String> venue_images) {
                this.venue_images = venue_images;
            }

            public int getHome_delivery() {
                return home_delivery;
            }

            public void setHome_delivery(int home_delivery) {
                this.home_delivery = home_delivery;
            }

            public int getClick_collect() {
                return click_collect;
            }

            public void setClick_collect(int click_collect) {
                this.click_collect = click_collect;
            }

            public String getOpening_time() {
                return opening_time;
            }

            public void setOpening_time(String opening_time) {
                this.opening_time = opening_time;
            }

            public String getClosing_time() {
                return closing_time;
            }

            public void setClosing_time(String closing_time) {
                this.closing_time = closing_time;
            }

            public String getIsClose() {
                return isClose;
            }

            public void setIsClose(String isClose) {
                this.isClose = isClose;
            }

            public int getTotal_offers() {
                return total_offers;
            }

            public void setTotal_offers(int total_offers) {
                this.total_offers = total_offers;
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

            public String getPhone_number() {
                return phone_number;
            }

            public void setPhone_number(String phone_number) {
                this.phone_number = phone_number;
            }

            public String getVenue_email() {
                return venue_email;
            }

            public void setVenue_email(String venue_email) {
                this.venue_email = venue_email;
            }

            public String getHome_delivery_mov() {
                return home_delivery_mov;
            }

            public void setHome_delivery_mov(String home_delivery_mov) {
                this.home_delivery_mov = home_delivery_mov;
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

            public int getDelivery_distance() {
                return delivery_distance;
            }

            public void setDelivery_distance(int delivery_distance) {
                this.delivery_distance = delivery_distance;
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

            public String getModifier_images() {
                return modifier_images;
            }

            public void setModifier_images(String modifier_images) {
                this.modifier_images = modifier_images;
            }

            public String getAddress_2() {
                return address_2;
            }

            public List<String> getVenue_images() {
                return venue_images;
            }

            public static class CartBean  {
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


