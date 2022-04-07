package com.poundland.retail.model.responseModel;

import java.util.List;

public class SingleProductDetailResponseModel {

    /**
     * status : true
     * message : Product Details
     * venues : {"id":2,"merchant_id":"2","venue_id":"201911011148462","venue_name":"Lilly Whites","venue_images":["1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg"],"address_1":"Oaklands Road, Wolverhampton WV3 0DS, England, UK","address_2":null,"city_name":"Wolverhampton","country":"United Kingdom","post_code":"WV3 0DS","phone_number":"5468496484","venue_email":"whiteslilly@dmailpro.net","venue_website":"https://www.lillywhites.com/","home_delivery_mov":"0.00","start_days":0,"end_days":0,"collection_time":30,"preparation_time":20,"free_delivery":"0.00","delivery_charge":"0.00","delivery":1,"collection":1,"opening_time":"07:30","closing_time":"20:30"}
     * products : {"id":34,"merchant_id":2,"venue_id":"201911011148462","product_name":"Forest Essentials Silkening Shower Wash Soundarya, 200ml","product_description":"The Soundarya silkening shower wash is a careful amalgamation of all-natural ingredients for supple, toned and satin smooth skin. It has at its base our Advanced Soundarya Age Defying Serum which is a powerful combination of 24 Karat Gold Bhasma, effective herb infusions, unprocessed oils, pure cow\u2019s ghee and nourishing roots. This shower wash is light in in texture and is made with the most precious ingredients. The cooling Bamboo Extract makes for the perfect skin energizer and fresh Aloe Vera juice retains moisture. Ashwagandha and Turmeric are known for their antioxidant and rejuvenating properties.","measurement_unit_id":2,"category_id":["7"],"related_product_ids":[],"brand_id":11,"tax_id":"0","supplier_id":2,"product_tags":"Skin","images":["uploaded/products/2086915730403740.jpeg"],"modifier_list":[],"modifier_id":45,"modifier_name":null,"buy_price":"10.00","selling_price":"20.00","avl_quantity":10,"modifier_images":[],"new_price":"20.00"}
     * productOfers : {"id":5,"offer_type":"combo","disc_per":"0.00","disc_amt":"0.00","offer_title":"SpecialCombo","terms_conditions":"TNC","offer_time_start":"00:15:00","offer_time_end":"23:55:00"}
     * loyalitypoints : {"loyalty_points_value":0.25,"loyalty_points":0,"total_loyalty_points_value":0}
     * comboOffer : [[{"id":35,"modifier_id":46,"merchant_id":2,"venue_id":"201911011148462","product_name":"Forest Essentials Silken Dusting Powder, Indian Rose Absolute, 100g","modifier_images":null,"images":"uploaded/products/9039315730403870.jpeg","discount_id":5,"selling_price":"20.00","discount_type":2,"type":0,"discount_amount":"5.00","rating":{"count":1,"rating_value":"5.0"}},{"id":36,"modifier_id":47,"merchant_id":2,"venue_id":"201911011148462","product_name":"Forest Essentials Forest Essentials Bhringraj Hair Vitalizer, 100ml Hair Vitalizer, 100ml","modifier_images":null,"images":"uploaded/products/3365715730404010.jpeg","discount_id":5,"selling_price":"25.00","discount_type":2,"type":0,"discount_amount":"5.00","rating":{"count":0,"rating_value":"0"}}]]
     * productRating : {"count":0,"rating_value":"0","reviews":[]}
     */

    private boolean status;
    private String message;
    private VenuesBean venues;
    private ProductsBean products;
    private ProductOfersBean productOfers;
    private LoyalitypointsBean loyalitypoints;
    private ProductRatingBean productRating;
    private List<List<ComboOfferBean>> comboOffer;
    private ShippingDataBean shippingData;
    private List<SimilarProductsBean> similarProducts;
    private CustAddressBean cust_address;
    private List<DefaultCardsBean> defaultCards;

    public List<DefaultCardsBean> getDefaultCards() {
        return defaultCards;
    }

    public void setDefaultCards(List<DefaultCardsBean> defaultCards) {
        this.defaultCards = defaultCards;
    }

    public List<SimilarProductsBean> getSimilarProducts() {
        return similarProducts;
    }


    public CustAddressBean getCust_address() {
        return cust_address;
    }

    public void setCust_address(CustAddressBean cust_address) {
        this.cust_address = cust_address;
    }

    public void setSimilarProducts(List<SimilarProductsBean> similarProducts) {
        this.similarProducts = similarProducts;
    }

    public ShippingDataBean getShippingData() {
        return shippingData;
    }

    public void setShippingData(ShippingDataBean shippingData) {
        this.shippingData = shippingData;
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

    public VenuesBean getVenues() {
        return venues;
    }

    public void setVenues(VenuesBean venues) {
        this.venues = venues;
    }

    public ProductsBean getProducts() {
        return products;
    }

    public void setProducts(ProductsBean products) {
        this.products = products;
    }

    public ProductOfersBean getProductOfers() {
        return productOfers;
    }

    public void setProductOfers(ProductOfersBean productOfers) {
        this.productOfers = productOfers;
    }

    public LoyalitypointsBean getLoyalitypoints() {
        return loyalitypoints;
    }

    public void setLoyalitypoints(LoyalitypointsBean loyalitypoints) {
        this.loyalitypoints = loyalitypoints;
    }

    public ProductRatingBean getProductRating() {
        return productRating;
    }

    public void setProductRating(ProductRatingBean productRating) {
        this.productRating = productRating;
    }

    public List<List<ComboOfferBean>> getComboOffer() {
        return comboOffer;
    }

    public void setComboOffer(List<List<ComboOfferBean>> comboOffer) {
        this.comboOffer = comboOffer;
    }

    public static class VenuesBean {
        /**
         * id : 2
         * merchant_id : 2
         * venue_id : 201911011148462
         * venue_name : Lilly Whites
         * venue_images : ["1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg"]
         * address_1 : Oaklands Road, Wolverhampton WV3 0DS, England, UK
         * address_2 : null
         * city_name : Wolverhampton
         * country : United Kingdom
         * post_code : WV3 0DS
         * phone_number : 5468496484
         * venue_email : whiteslilly@dmailpro.net
         * venue_website : https://www.lillywhites.com/
         * home_delivery_mov : 0.00
         * start_days : 0
         * end_days : 0
         * collection_time : 30
         * preparation_time : 20
         * free_delivery : 0.00
         * delivery_charge : 0.00
         * delivery : 1
         * collection : 1
         * opening_time : 07:30
         * closing_time : 20:30
         */

        private int id;
        private String merchant_id;
        private String venue_id;
        private String venue_name;
        private String address_1;
        private Object address_2;
        private String city_name;
        private String country;
        private String post_code;
        private String phone_number;
        private String venue_email;
        private String venue_website;
        private String latitude;
        private String longitude;
        private String home_delivery_mov;
        private int start_days;
        private int end_days;
        private int collection_time;
        private int preparation_time;
        private String free_delivery;
        private String payment_gatway;
        private String delivery_charge;
        private int delivery;
        private int collection;
        private String opening_time;
        private String closing_time;
        private List<String> venue_images;

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

        public Object getAddress_2() {
            return address_2;
        }

        public void setAddress_2(Object address_2) {
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

        public String getVenue_website() {
            return venue_website;
        }

        public void setVenue_website(String venue_website) {
            this.venue_website = venue_website;
        }

        public String getHome_delivery_mov() {
            return home_delivery_mov;
        }

        public void setHome_delivery_mov(String home_delivery_mov) {
            this.home_delivery_mov = home_delivery_mov;
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

        public String getFree_delivery() {
            return free_delivery;
        }

        public void setFree_delivery(String free_delivery) {
            this.free_delivery = free_delivery;
        }

        public String getDelivery_charge() {
            return delivery_charge;
        }

        public void setDelivery_charge(String delivery_charge) {
            this.delivery_charge = delivery_charge;
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

        public List<String> getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(List<String> venue_images) {
            this.venue_images = venue_images;
        }
    }

    public static class ProductsBean {
        /**
         * id : 34
         * merchant_id : 2
         * venue_id : 201911011148462
         * product_name : Forest Essentials Silkening Shower Wash Soundarya, 200ml
         * product_description : The Soundarya silkening shower wash is a careful amalgamation of all-natural ingredients for supple, toned and satin smooth skin. It has at its base our Advanced Soundarya Age Defying Serum which is a powerful combination of 24 Karat Gold Bhasma, effective herb infusions, unprocessed oils, pure cow’s ghee and nourishing roots. This shower wash is light in in texture and is made with the most precious ingredients. The cooling Bamboo Extract makes for the perfect skin energizer and fresh Aloe Vera juice retains moisture. Ashwagandha and Turmeric are known for their antioxidant and rejuvenating properties.
         * measurement_unit_id : 2
         * category_id : ["7"]
         * related_product_ids : []
         * brand_id : 11
         * tax_id : 0
         * supplier_id : 2
         * product_tags : Skin
         * images : ["uploaded/products/2086915730403740.jpeg"]
         * modifier_list : []
         * modifier_id : 45
         * modifier_name : null
         * buy_price : 10.00
         * selling_price : 20.00
         * avl_quantity : 10
         * modifier_images : []
         * new_price : 20.00
         */

        private int id;
        private int merchant_id;
        private String venue_id;
        private String product_name;
        private String product_description;
        private int measurement_unit_id;
        private int brand_id;
        private String tax_id;
        private int supplier_id;
        private String product_tags;
        private int modifier_id;
        private Object modifier_name;
        private String buy_price;
        private String selling_price;
        private int avl_quantity;
        private int quantity_per_case;
        private String weight;
        private int sameDayShipping;
        private String new_price;
        private String bulk_selling_price;
        private int bulk_selling_qty;
        private List<String> category_id;
        private List<?> related_product_ids;
        private List<String> images;
        private List<ModifierListBean> modifier_list;
        private List<String> modifier_images;
        private boolean isWishlisted;
        private boolean isCart;

        public String getBulk_selling_price() {
            return bulk_selling_price;
        }

        public void setBulk_selling_price(String bulk_selling_price) {
            this.bulk_selling_price = bulk_selling_price;
        }

        public int getBulk_selling_qty() {
            return bulk_selling_qty;
        }

        public void setBulk_selling_qty(int bulk_selling_qty) {
            this.bulk_selling_qty = bulk_selling_qty;
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

        public String getProduct_description() {
            return product_description;
        }

        public void setProduct_description(String product_description) {
            this.product_description = product_description;
        }

        public int getMeasurement_unit_id() {
            return measurement_unit_id;
        }

        public void setMeasurement_unit_id(int measurement_unit_id) {
            this.measurement_unit_id = measurement_unit_id;
        }

        public int getBrand_id() {
            return brand_id;
        }

        public void setBrand_id(int brand_id) {
            this.brand_id = brand_id;
        }

        public String getTax_id() {
            return tax_id;
        }

        public void setTax_id(String tax_id) {
            this.tax_id = tax_id;
        }

        public int getSupplier_id() {
            return supplier_id;
        }

        public void setSupplier_id(int supplier_id) {
            this.supplier_id = supplier_id;
        }

        public String getProduct_tags() {
            return product_tags;
        }

        public void setProduct_tags(String product_tags) {
            this.product_tags = product_tags;
        }

        public int getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(int modifier_id) {
            this.modifier_id = modifier_id;
        }

        public Object getModifier_name() {
            return modifier_name;
        }

        public void setModifier_name(Object modifier_name) {
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

        public List<String> getCategory_id() {
            return category_id;
        }

        public void setCategory_id(List<String> category_id) {
            this.category_id = category_id;
        }

        public List<?> getRelated_product_ids() {
            return related_product_ids;
        }

        public void setRelated_product_ids(List<?> related_product_ids) {
            this.related_product_ids = related_product_ids;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }

        public List<ModifierListBean> getModifier_list() {
            return modifier_list;
        }

        public void setModifier_list(List<ModifierListBean> modifier_list) {
            this.modifier_list = modifier_list;
        }

        public List<String> getModifier_images() {
            return modifier_images;
        }

        public void setModifier_images(List<String> modifier_images) {
            this.modifier_images = modifier_images;
        }

        public boolean isWishlisted() {
            return isWishlisted;
        }

        public void setWishlisted(boolean wishlisted) {
            isWishlisted = wishlisted;
        }

        public boolean isCart() {
            return isCart;
        }

        public void setCart(boolean cart) {
            isCart = cart;
        }

        public static class ModifierListBean {
            /**
             * type : color
             * type_list : [{"name":"Light Blue","code":"#8ecde6"}]
             */

            private String type;
            private List<TypeListBean> type_list;


            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public List<TypeListBean> getType_list() {
                return type_list;
            }

            public void setType_list(List<TypeListBean> type_list) {
                this.type_list = type_list;
            }

            public static class TypeListBean {
                /**
                 * name : Light Blue
                 * code : #8ecde6
                 */

                private String name;
                private String code;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getCode() {
                    return code;
                }

                public void setCode(String code) {
                    this.code = code;
                }
            }
        }
    }

    public static class ProductOfersBean {
        /**
         * id : 5
         * offer_type : combo
         * disc_per : 0.00
         * disc_amt : 0.00
         * offer_title : SpecialCombo
         * terms_conditions : TNC
         * offer_time_start : 00:15:00
         * offer_time_end : 23:55:00
         */
         /*
         "id": 2,
        "offer_type": "discper",
        "disc_per": "10.00",
        "disc_amt": "0.00",
        "offer_title": "10%Off",
        "terms_conditions": "TNC",
        "offer_time_start": "00:05:00",
        "offer_time_end": "23:55:00"*/
        private int id;
        private String offer_type;
        private String disc_per;
        private String disc_amt;
        private String offer_title;
        private String terms_conditions;
        private String offer_time_start;
        private String offer_time_end;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOffer_type() {
            return offer_type;
        }

        public void setOffer_type(String offer_type) {
            this.offer_type = offer_type;
        }

        public String getDisc_per() {
            return disc_per;
        }

        public void setDisc_per(String disc_per) {
            this.disc_per = disc_per;
        }

        public String getDisc_amt() {
            return disc_amt;
        }

        public void setDisc_amt(String disc_amt) {
            this.disc_amt = disc_amt;
        }

        public String getOffer_title() {
            return offer_title;
        }

        public void setOffer_title(String offer_title) {
            this.offer_title = offer_title;
        }

        public String getTerms_conditions() {
            return terms_conditions;
        }

        public void setTerms_conditions(String terms_conditions) {
            this.terms_conditions = terms_conditions;
        }

        public String getOffer_time_start() {
            return offer_time_start;
        }

        public void setOffer_time_start(String offer_time_start) {
            this.offer_time_start = offer_time_start;
        }

        public String getOffer_time_end() {
            return offer_time_end;
        }

        public void setOffer_time_end(String offer_time_end) {
            this.offer_time_end = offer_time_end;
        }
    }

    public static class LoyalitypointsBean {
        /**
         * loyalty_points_value : 0.25
         * loyalty_points : 0
         * total_loyalty_points_value : 0
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

    public static class ProductRatingBean {
        /**
         * count : 0
         * rating_value : 0
         * reviews : []
         */

        private int count;
        private String rating_value;
        private List<ReviewsBean> reviews;

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

        public List<ReviewsBean> getReviews() {
            return reviews;
        }

        public void setReviews(List<ReviewsBean> reviews) {
            this.reviews = reviews;
        }

        public static class ReviewsBean {
            /**
             * cust_id : 0
             * cust_name :
             * cust_image :
             * rattings : 4
             * review : null
             */

            private int cust_id;
            private String cust_name;
            private String cust_image;
            private float rattings;
            private String review;

            public int getCust_id() {
                return cust_id;
            }

            public void setCust_id(int cust_id) {
                this.cust_id = cust_id;
            }

            public String getCust_name() {
                return cust_name;
            }

            public void setCust_name(String cust_name) {
                this.cust_name = cust_name;
            }

            public String getCust_image() {
                return cust_image;
            }

            public void setCust_image(String cust_image) {
                this.cust_image = cust_image;
            }

            public float getRattings() {
                return rattings;
            }

            public void setRattings(float rattings) {
                this.rattings = rattings;
            }

            public String getReview() {
                return review;
            }

            public void setReview(String review) {
                this.review = review;
            }
        }
    }

    public static class ComboOfferBean {
        /**
         * id : 35
         * modifier_id : 46
         * merchant_id : 2
         * venue_id : 201911011148462
         * product_name : Forest Essentials Silken Dusting Powder, Indian Rose Absolute, 100g
         * modifier_images : null
         * images : uploaded/products/9039315730403870.jpeg
         * discount_id : 5
         * selling_price : 20.00
         * discount_type : 2
         * type : 0
         * discount_amount : 5.00
         * rating : {"count":1,"rating_value":"5.0"}
         */

        private int id;
        private int modifier_id;
        private int merchant_id;
        private String venue_id;
        private String product_name;
        private Object modifier_images;
        private String images;
        private int discount_id;
        private String selling_price;
        private int discount_type;
        private int type;
        private String discount_amount;
        private RatingBean rating;
        private String new_price;
        private String offer_time_start;
        private String offer_time_end;

        public String getOffer_time_start() {
            return offer_time_start;
        }

        public void setOffer_time_start(String offer_time_start) {
            this.offer_time_start = offer_time_start;
        }

        public String getOffer_time_end() {
            return offer_time_end;
        }

        public void setOffer_time_end(String offer_time_end) {
            this.offer_time_end = offer_time_end;
        }

        public String getNew_price() {
            return new_price;
        }

        public void setNew_price(String new_price) {
            this.new_price = new_price;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getModifier_id() {
            return modifier_id;
        }

        public void setModifier_id(int modifier_id) {
            this.modifier_id = modifier_id;
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

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public Object getModifier_images() {
            return modifier_images;
        }

        public void setModifier_images(Object modifier_images) {
            this.modifier_images = modifier_images;
        }

        public String getImages() {
            return images;
        }

        public void setImages(String images) {
            this.images = images;
        }

        public int getDiscount_id() {
            return discount_id;
        }

        public void setDiscount_id(int discount_id) {
            this.discount_id = discount_id;
        }

        public String getSelling_price() {
            return selling_price;
        }

        public void setSelling_price(String selling_price) {
            this.selling_price = selling_price;
        }

        public int getDiscount_type() {
            return discount_type;
        }

        public void setDiscount_type(int discount_type) {
            this.discount_type = discount_type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getDiscount_amount() {
            return discount_amount;
        }

        public void setDiscount_amount(String discount_amount) {
            this.discount_amount = discount_amount;
        }

        public RatingBean getRating() {
            return rating;
        }

        public void setRating(RatingBean rating) {
            this.rating = rating;
        }

        public static class RatingBean {
            /**
             * count : 1
             * rating_value : 5.0
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

    public static class CustAddressBean {
        /**
         * id : 5
         * user_id : 5
         * name : vinit mishra
         * mobile : 8920207597
         * pincode : WV3 0DS
         * flat : 17
         * area : 17 Oaklands Road
         * landmark : null
         * city : Wolverhampton
         * state : null
         * country : England
         * latitude : 52.57592
         * longitude : -2.138813
         * type : other
         * update_token : null
         */

        private int id;
        private int user_id;
        private String name;
        private String mobile;
        private String pincode;
        private String flat;
        private String area;
        private String landmark;
        private String city;
        private String state;
        private String country;
        private String latitude;
        private String longitude;
        private String type;
        private String update_token;
        private String created_at;
        private String updated_at;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
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

        public String getFlat() {
            return flat;
        }

        public void setFlat(String flat) {
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
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

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getUpdate_token() {
            return update_token;
        }

        public void setUpdate_token(String update_token) {
            this.update_token = update_token;
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
    public static class ShippingDataBean {
        /**
         * id : 13
         * from_day : 1
         * to_day : 1
         * time : 13:00
         * amount : 10.00
         * type : 2
         * name : Same Day Delivery
         */

        private int id;
        private int from_day;
        private int to_day;
        private String time;
        private String amount;
        private int type;
        private String name;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getFrom_day() {
            return from_day;
        }

        public void setFrom_day(int from_day) {
            this.from_day = from_day;
        }

        public int getTo_day() {
            return to_day;
        }

        public void setTo_day(int to_day) {
            this.to_day = to_day;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    public static class SimilarProductsBean {
        /**
         * id : 1979
         * merchant_id : 15
         * product_name : Women Solid Round Neck T-shirt
         * product_description : <div><h4 class="pdp-product-description-title">PRODUCT DETAILS&nbsp;</h4><p class="pdp-product-description-content">Black solid T-shirt, has a round neck, and long sleeves</p></div><div class="pdp-sizeFitDesc"><h4 class="pdp-sizeFitDescTitle pdp-product-description-title">Size &amp; Fit</h4><p class="pdp-sizeFitDescContent pdp-product-description-content">The model (height 5'8") is wearing a size S</p></div><div class="pdp-sizeFitDesc"><h4 class="pdp-sizeFitDescTitle pdp-product-description-title">Material &amp; Care</h4><p class="pdp-sizeFitDescContent pdp-product-description-content">Material: 100% cotton<br>Machine Wash</p></div>
         * category_id : 377
         * brand_id : 62
         * images : uploaded/products/6165116045761450.jpeg
         * buy_price : 1.00
         * selling_price : 15.00
         * quantity_per_case : 1
         * bulk_selling_price : 21.00
         * modifier_id : 2523
         * avl_quantity : 85
         * modifier_images : uploaded/modifierImages/9072716045574320.jpeg
         * mod_count : 4
         * venue_id : 2020102010172215
         * venue_name : Next
         * venue_images : 1603185442-500.jpg
         * address_1 : Wolverhampton, Railway Drive, West Midlands, Wolverhampton, UK
         * address_2 : null
         * city_name : West Midlands
         * country : United Kingdom
         * post_code : WV1 1LE
         * delivery : 1
         * collection : 1
         * latitude : 52.5875483
         * longitude : -2.120122100000001
         * delivery_distance : 0
         * stars : {"count":4,"rating_value":"4.5"}
         * isLiked : 1
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
        private int quantity_per_case;
        private String bulk_selling_price;
        private int modifier_id;
        private int avl_quantity;
        private String modifier_images;
        private int mod_count;
        private String venue_id;
        private String venue_name;
        private String venue_images;
        private String address_1;
        private Object address_2;
        private String city_name;
        private String country;
        private String post_code;
        private int delivery;
        private int collection;
        private String latitude;
        private String longitude;
        private int delivery_distance;
        private StarsBean stars;
        private int isLiked;

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

        public int getMod_count() {
            return mod_count;
        }

        public void setMod_count(int mod_count) {
            this.mod_count = mod_count;
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

        public String getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(String venue_images) {
            this.venue_images = venue_images;
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

        public int getDelivery_distance() {
            return delivery_distance;
        }

        public void setDelivery_distance(int delivery_distance) {
            this.delivery_distance = delivery_distance;
        }

        public StarsBean getStars() {
            return stars;
        }

        public void setStars(StarsBean stars) {
            this.stars = stars;
        }

        public int getIsLiked() {
            return isLiked;
        }

        public void setIsLiked(int isLiked) {
            this.isLiked = isLiked;
        }

        public static class StarsBean {
            /**
             * count : 4
             * rating_value : 4.5
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

    public static class DefaultCardsBean  {
        /**
         * id : 313
         * user_id : 145
         * stripe_cust_id : cus_IIYD9CNR9FKBIK
         * stripe_card_id : card_1IkqvVA4mS3BlT3I5J9KPsIK
         * card_name : Shakti
         * card_number : ************4242
         * exp_month : 02
         * exp_year : 2022
         * card_type : Visa
         * isDefault : 0
         * gateway : stripe
         */

        private int id;
        private int user_id;
        private String stripe_cust_id;
        private String stripe_card_id;
        private String card_name;
        private String card_number;
        private String exp_month;
        private String exp_year;
        private String card_type;
        private int isDefault;
        private String gateway;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getStripe_cust_id() {
            return stripe_cust_id;
        }

        public void setStripe_cust_id(String stripe_cust_id) {
            this.stripe_cust_id = stripe_cust_id;
        }

        public String getStripe_card_id() {
            return stripe_card_id;
        }

        public void setStripe_card_id(String stripe_card_id) {
            this.stripe_card_id = stripe_card_id;
        }

        public String getCard_name() {
            return card_name;
        }

        public void setCard_name(String card_name) {
            this.card_name = card_name;
        }

        public String getCard_number() {
            return card_number;
        }

        public void setCard_number(String card_number) {
            this.card_number = card_number;
        }

        public String getExp_month() {
            return exp_month;
        }

        public void setExp_month(String exp_month) {
            this.exp_month = exp_month;
        }

        public String getExp_year() {
            return exp_year;
        }

        public void setExp_year(String exp_year) {
            this.exp_year = exp_year;
        }

        public String getCard_type() {
            return card_type;
        }

        public void setCard_type(String card_type) {
            this.card_type = card_type;
        }

        public int getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(int isDefault) {
            this.isDefault = isDefault;
        }

        public String getGateway() {
            return gateway;
        }

        public void setGateway(String gateway) {
            this.gateway = gateway;
        }
    }
}