package com.poundland.retail.model.responseModel;

import java.io.Serializable;
import java.util.List;

public class GetCartWithSummaryResponseModel {

    private boolean status;
    private String message;
    private CartsSummaryBean carts_summary;
    private VenueTimingBean venue_timing;
    private CustAddressBean cust_address;
    private List<CartsBean> carts;
    private int total_carts;
    private Object venueTheme;
    private TransChargeBean trans_charge;
    private ShippingDataBean shippingData;
    private List<VenueWeekTimesBean> venue_week_times;
    private List<DefaultCardsBean> defaultCards;

    public List<DefaultCardsBean> getDefaultCards() {
        return defaultCards;
    }

    public void setDefaultCards(List<DefaultCardsBean> defaultCards) {
        this.defaultCards = defaultCards;
    }

    public int getTotal_carts() {
        return total_carts;
    }

    public void setTotal_carts(int total_carts) {
        this.total_carts = total_carts;
    }

    public Object getVenueTheme() {
        return venueTheme;
    }

    public void setVenueTheme(Object venueTheme) {
        this.venueTheme = venueTheme;
    }

    public TransChargeBean getTrans_charge() {
        return trans_charge;
    }

    public void setTrans_charge(TransChargeBean trans_charge) {
        this.trans_charge = trans_charge;
    }

    public ShippingDataBean getShippingData() {
        return shippingData;
    }

    public void setShippingData(ShippingDataBean shippingData) {
        this.shippingData = shippingData;
    }

    public List<VenueWeekTimesBean> getVenue_week_times() {
        return venue_week_times;
    }

    public void setVenue_week_times(List<VenueWeekTimesBean> venue_week_times) {
        this.venue_week_times = venue_week_times;
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

    public CartsSummaryBean getCarts_summary() {
        return carts_summary;
    }

    public void setCarts_summary(CartsSummaryBean carts_summary) {
        this.carts_summary = carts_summary;
    }

    public VenueTimingBean getVenue_timing() {
        return venue_timing;
    }

    public void setVenue_timing(VenueTimingBean venue_timing) {
        this.venue_timing = venue_timing;
    }

    public CustAddressBean getCust_address() {
        return cust_address;
    }

    public void setCust_address(CustAddressBean cust_address) {
        this.cust_address = cust_address;
    }

    public List<CartsBean> getCarts() {
        return carts;
    }

    public void setCarts(List<CartsBean> carts) {
        this.carts = carts;
    }

    public static class CartsSummaryBean {
        /**
         * cart_total : 70.00
         * discount : 0.00
         * sub_total : 70.00
         * tax : 0.00
         * delivery_charges : 0.00
         * free_delivery : 0.00
         * grand_total : 70.00
         "transaction_charge": "2.00",
         "transaction_charge_amt": "2.00",
         */

        private String collection;
        private String delivery;
        private String collection_time;
        private String preparation_time;
        private String home_delivery_mov;
        private String total_items;
        private String cart_total;
        private String discount;
        private String sub_total;
        private String tax;
        private String delivery_charges;
        private String transaction_charge;
        private String transaction_charge_amt;
        private String free_delivery;
        private String grand_total;
        private int delivery_start_days;
        private int delivery_end_days;
        private int total_quantity;
        private int days_for_click_collect;
        private String included_tax;
        private String excluded_tax;
        private String max_weight;

        public int getDays_for_click_collect() {
            return days_for_click_collect;
        }

        public void setDays_for_click_collect(int days_for_click_collect) {
            this.days_for_click_collect = days_for_click_collect;
        }

        public String getMax_weight() {
            return max_weight;
        }

        public void setMax_weight(String max_weight) {
            this.max_weight = max_weight;
        }

        public int getTotal_quantity() {
            return total_quantity;
        }

        public void setTotal_quantity(int total_quantity) {
            this.total_quantity = total_quantity;
        }

        public String getIncluded_tax() {
            return included_tax;
        }

        public void setIncluded_tax(String included_tax) {
            this.included_tax = included_tax;
        }

        public String getExcluded_tax() {
            return excluded_tax;
        }

        public void setExcluded_tax(String excluded_tax) {
            this.excluded_tax = excluded_tax;
        }

        public String getTransaction_charge() {
            return transaction_charge;
        }

        public void setTransaction_charge(String transaction_charge) {
            this.transaction_charge = transaction_charge;
        }

        public String getTransaction_charge_amt() {
            return transaction_charge_amt;
        }

        public void setTransaction_charge_amt(String transaction_charge_amt) {
            this.transaction_charge_amt = transaction_charge_amt;
        }

        public int getDelivery_start_days() {
            return delivery_start_days;
        }

        public void setDelivery_start_days(int delivery_start_days) {
            this.delivery_start_days = delivery_start_days;
        }

        public int getDelivery_end_days() {
            return delivery_end_days;
        }

        public void setDelivery_end_days(int delivery_end_days) {
            this.delivery_end_days = delivery_end_days;
        }

        public String getCollection() {
            return collection;
        }

        public void setCollection(String collection) {
            this.collection = collection;
        }

        public String getDelivery() {
            return delivery;
        }

        public void setDelivery(String delivery) {
            this.delivery = delivery;
        }

        public String getCollection_time() {
            return collection_time;
        }

        public void setCollection_time(String collection_time) {
            this.collection_time = collection_time;
        }

        public String getPreparation_time() {
            return preparation_time;
        }

        public void setPreparation_time(String preparation_time) {
            this.preparation_time = preparation_time;
        }

        public String getHome_delivery_mov() {
            return home_delivery_mov;
        }

        public void setHome_delivery_mov(String home_delivery_mov) {
            this.home_delivery_mov = home_delivery_mov;
        }

        public String getTotal_items() {
            return total_items;
        }

        public void setTotal_items(String total_items) {
            this.total_items = total_items;
        }

        public String getCart_total() {
            return cart_total;
        }

        public void setCart_total(String cart_total) {
            this.cart_total = cart_total;
        }

        public String getDiscount() {
            return discount;
        }

        public void setDiscount(String discount) {
            this.discount = discount;
        }

        public String getSub_total() {
            return sub_total;
        }

        public void setSub_total(String sub_total) {
            this.sub_total = sub_total;
        }

        public String getTax() {
            return tax;
        }

        public void setTax(String tax) {
            this.tax = tax;
        }

        public String getDelivery_charges() {
            return delivery_charges;
        }

        public void setDelivery_charges(String delivery_charges) {
            this.delivery_charges = delivery_charges;
        }

        public String getFree_delivery() {
            return free_delivery;
        }

        public void setFree_delivery(String free_delivery) {
            this.free_delivery = free_delivery;
        }

        public String getGrand_total() {
            return grand_total;
        }

        public void setGrand_total(String grand_total) {
            this.grand_total = grand_total;
        }
    }

    public static class VenueTimingBean {
        /**
         * opening_time : 06:00
         * closing_time : 23:30
         * isClose : "0"
         */

        private String isClose;
        private String opening_time;
        private String closing_time;

        public String getIsClose() {
            return isClose;
        }

        public void setIsClose(String isClose) {
            this.isClose = isClose;
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
    }


    public static class TransChargeBean {
        /**
         * pence_1 : 1
         * pence_2 : 1
         * percent : 10
         */

        private int pence_1;
        private int pence_2;
        private int percent;

        public int getPence_1() {
            return pence_1;
        }

        public void setPence_1(int pence_1) {
            this.pence_1 = pence_1;
        }

        public int getPence_2() {
            return pence_2;
        }

        public void setPence_2(int pence_2) {
            this.pence_2 = pence_2;
        }

        public int getPercent() {
            return percent;
        }

        public void setPercent(int percent) {
            this.percent = percent;
        }
    }


    public static class ShippingDataBean {
        /**
         * id : 32
         * from_day : 1
         * to_day : 1
         * time : 21:00
         * amount : 4.00
         * type : 1
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


    public static class VenueWeekTimesBean implements Serializable {
        /**
         * day_name : Monday
         * day_id : 1
         * opening_time : 2021-04-05 07:00
         * closing_time : 2021-04-05 21:00
         */

        private String day_name;
        private int day_id;
        private int is_venue_open;
        private String opening_time;
        private String closing_time;

        public int getIs_venue_open() {
            return is_venue_open;
        }

        public void setIs_venue_open(int is_venue_open) {
            this.is_venue_open = is_venue_open;
        }

        public String getDay_name() {
            return day_name;
        }

        public void setDay_name(String day_name) {
            this.day_name = day_name;
        }

        public int getDay_id() {
            return day_id;
        }

        public void setDay_id(int day_id) {
            this.day_id = day_id;
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
    public static class CartsBean {

        /**
         * id : 142
         * guest : null
         * customer_id : 20
         * type : 1
         * product_id : 26
         * modifier_id : 29
         * offer_id : null
         * quantities : 1
         * cost_per_product : 70.00
         * coupon_code : null
         * combo_no : null
         * combo_group : null
         * ip_address : null
         * venue_id : 201911011148462
         * merchant_id : 2
         * created_at : 2019-11-26 09:13:37
         * updated_at : 2019-11-26 09:13:37
         * product_name : Marlin Women's Cotton Kurti With Palazzo Pant Set
         * product_image : uploaded/products/9050315730351550.jpeg
         * brand_id : 11
         * tax_id : 0
         * return_day : 1
         * modifier_name : null
         * buy_price : 50.00
         * selling_price : 70.00
        * avl_quantity : 9
         * modifier_images :
         * venue_name : Lilly Whites
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
         * venue_images : 1572608926.a-lillywhites-store-in-piccadilly-circus-london-england-uk-C5RJXF.jpg
         * latitude : 52.5756465
         * longitude : -2.1385980000000018
         * address_1 : Oaklands Road, Wolverhampton WV3 0DS, England, UK
         * delivery_distance : 0
         * distance : 0.02
         * loyalty_point : 0.00
         * loyalty_point_value : 0.25
         * offer_title :
         * discount_amount : null
         * discount_type : null
         * disc_detail_type : null
         * new_price : 70.00
         * brand_name : Pacifia
         * total_loyalty_amount : 0
         * weight :0
         *
         */
       // private int add_on;
        private int is_hospitality;
        private int special_offer_id;
        private String special_offer_image;
        private String payment_gatway;
        private String base_price;
        private String cart_price;
        private String discount_applied;
        private String net_amount;
        private String tax_applied;
        private int id;
        private Object guest;
        private int customer_id;
        private int type;
        private int product_id;
        private int modifier_id;
        private int offer_id;
        private int quantities;
        private String cost_per_product;
        private String coupon_code;
        private String combo_no;
        private String combo_group;
        private String ip_address;
        private long venue_id;
        private int merchant_id;
        private String created_at;
        private String updated_at;
        private String product_name;
        private String product_image;
        private int brand_id;
        private String tax_id;
        private int return_day;
        private String modifier_name;
        private String buy_price;
        private String selling_price;
        private int avl_quantity;
        private String modifier_images;
        private String venue_name;
        private String phone_number;
        private String venue_email;
        private String venue_website;
        private String home_delivery_mov;
        private int start_days;
        private int end_days;
        private int collection_time;
        private int preparation_time;
        private String free_delivery;
        private String delivery_charge;
        private int delivery;
        private int collection;
        private String venue_images;
        private String latitude;
        private String longitude;
        private String address_1;
        private int delivery_distance;
        private int caseqty;
        private String bulk_selling_price;
        private String distance;
        private String loyalty_point;
        private String loyalty_point_value;
        private double total_loyalty_amount;
        private String offer_title;
        private double discount_amount;
        private double discount_type;
        private double disc_detail_type;
        private String new_price;
        private String brand_name;
        private String tax_name;
        private String tax_amount;
        private String weight;

        public String getBulk_selling_price() {
            return bulk_selling_price;
        }

        public void setBulk_selling_price(String bulk_selling_price) {
            this.bulk_selling_price = bulk_selling_price;
        }

        public int getCaseqty() {
            return caseqty;
        }

        public void setCaseqty(int caseqty) {
            this.caseqty = caseqty;
        }

        public String getWeight() {
            return weight;
        }

        public void setWeight(String weight) {
            this.weight = weight;
        }



        public int getIs_hospitality() {
            return is_hospitality;
        }

        public void setIs_hospitality(int is_hospitality) {
            this.is_hospitality = is_hospitality;
        }

        public int getSpecial_offer_id() {
            return special_offer_id;
        }

        public void setSpecial_offer_id(int special_offer_id) {
            this.special_offer_id = special_offer_id;
        }

        public String getSpecial_offer_image() {
            return special_offer_image;
        }

        public void setSpecial_offer_image(String special_offer_image) {
            this.special_offer_image = special_offer_image;
        }

        public String getPayment_gatway() {
            return payment_gatway;
        }

        public void setPayment_gatway(String payment_gatway) {
            this.payment_gatway = payment_gatway;
        }

        public String getBase_price() {
            return base_price;
        }

        public void setBase_price(String base_price) {
            this.base_price = base_price;
        }

        public String getCart_price() {
            return cart_price;
        }

        public void setCart_price(String cart_price) {
            this.cart_price = cart_price;
        }

        public String getDiscount_applied() {
            return discount_applied;
        }

        public void setDiscount_applied(String discount_applied) {
            this.discount_applied = discount_applied;
        }

        public String getNet_amount() {
            return net_amount;
        }

        public void setNet_amount(String net_amount) {
            this.net_amount = net_amount;
        }

        public String getTax_applied() {
            return tax_applied;
        }

        public void setTax_applied(String tax_applied) {
            this.tax_applied = tax_applied;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public Object getGuest() {
            return guest;
        }

        public void setGuest(Object guest) {
            this.guest = guest;
        }

        public int getCustomer_id() {
            return customer_id;
        }

        public void setCustomer_id(int customer_id) {
            this.customer_id = customer_id;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
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

        public int getOffer_id() {
            return offer_id;
        }

        public void setOffer_id(int offer_id) {
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

        public String getCombo_no() {
            return combo_no;
        }

        public void setCombo_no(String combo_no) {
            this.combo_no = combo_no;
        }

        public String getCombo_group() {
            return combo_group;
        }

        public void setCombo_group(String combo_group) {
            this.combo_group = combo_group;
        }

        public String getIp_address() {
            return ip_address;
        }

        public void setIp_address(String ip_address) {
            this.ip_address = ip_address;
        }

        public long getVenue_id() {
            return venue_id;
        }

        public void setVenue_id(long venue_id) {
            this.venue_id = venue_id;
        }

        public int getMerchant_id() {
            return merchant_id;
        }

        public void setMerchant_id(int merchant_id) {
            this.merchant_id = merchant_id;
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

        public String getProduct_name() {
            return product_name;
        }

        public void setProduct_name(String product_name) {
            this.product_name = product_name;
        }

        public String getProduct_image() {
            return product_image;
        }

        public void setProduct_image(String product_image) {
            this.product_image = product_image;
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

        public int getReturn_day() {
            return return_day;
        }

        public void setReturn_day(int return_day) {
            this.return_day = return_day;
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

        public String getModifier_images() {
            return modifier_images;
        }

        public void setModifier_images(String modifier_images) {
            this.modifier_images = modifier_images;
        }

        public String getVenue_name() {
            return venue_name;
        }

        public void setVenue_name(String venue_name) {
            this.venue_name = venue_name;
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

        public String getVenue_images() {
            return venue_images;
        }

        public void setVenue_images(String venue_images) {
            this.venue_images = venue_images;
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

        public String getAddress_1() {
            return address_1;
        }

        public void setAddress_1(String address_1) {
            this.address_1 = address_1;
        }

        public int getDelivery_distance() {
            return delivery_distance;
        }

        public void setDelivery_distance(int delivery_distance) {
            this.delivery_distance = delivery_distance;
        }

        public String getDistance() {
            return distance;
        }

        public void setDistance(String distance) {
            this.distance = distance;
        }

        public String getLoyalty_point() {
            return loyalty_point;
        }

        public void setLoyalty_point(String loyalty_point) {
            this.loyalty_point = loyalty_point;
        }

        public String getLoyalty_point_value() {
            return loyalty_point_value;
        }

        public void setLoyalty_point_value(String loyalty_point_value) {
            this.loyalty_point_value = loyalty_point_value;
        }

        public String getOffer_title() {
            return offer_title;
        }

        public void setOffer_title(String offer_title) {
            this.offer_title = offer_title;
        }

        public double getDiscount_amount() {
            return discount_amount;
        }

        public void setDiscount_amount(double discount_amount) {
            this.discount_amount = discount_amount;
        }

        public double getDiscount_type() {
            return discount_type;
        }

        public void setDiscount_type(double discount_type) {
            this.discount_type = discount_type;
        }

        public double getDisc_detail_type() {
            return disc_detail_type;
        }

        public void setDisc_detail_type(double disc_detail_type) {
            this.disc_detail_type = disc_detail_type;
        }

        public String getNew_price() {
            return new_price;
        }

        public void setNew_price(String new_price) {
            this.new_price = new_price;
        }

        public String getBrand_name() {
            return brand_name;
        }

        public void setBrand_name(String brand_name) {
            this.brand_name = brand_name;
        }

        public String getTax_name() {
            return tax_name;
        }

        public void setTax_name(String tax_name) {
            this.tax_name = tax_name;
        }

        public String getTax_amount() {
            return tax_amount;
        }

        public void setTax_amount(String tax_amount) {
            this.tax_amount = tax_amount;
        }

        public double getTotal_loyalty_amount() {
            return total_loyalty_amount;
        }

        public void setTotal_loyalty_amount(double total_loyalty_amount) {
            this.total_loyalty_amount = total_loyalty_amount;
        }
    }

    public static class DefaultCardsBean {
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
