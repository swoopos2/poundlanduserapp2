package com.poundland.retail.model.responseModel;

public class CheckoutCartHospitalityResponseModel {

   /**
    * status : true
    * message : Order Created Successfully.
    * order : {"unique_code":"145#0039","same_day_id":null,"merchant_id":"5","venue_id":"202004271734165","reservation_id":0,"table_no":0,"till_id":"0","staff_id":0,"cust_id":145,"card_number":"************1112","expected_delivery_date":"2021-01-25 10:20","payment_mode":"Card","source_type":"app","order_date":"2021-01-25 06:55:17","delivery_type":"Home Delivery","user_addr_id":null,"qr_order":"uploaded/orderQr/6629016115577180.png","total_items":2,"is_hospitality":1,"amt_without_tax_discount":19.98,"transaction_charge":"0.00","service_charge":"10","tip_amt":"1.9980000000000002","total_discount":0,"total_tax":2.17,"is_asap":0,"net_amount":24.148,"delivery_charge":"0.00","shipping_id":0,"expected_shipping_date":"2021-04-05 06:55:18","shipping_name":"Normal","stamp_reward_consumed_id":0,"claim_stamp_reward_id":0,"claim_stamp_reward_amt":0,"loyalty_point_value":0,"loyalty_point":0,"loyelty_used_amount":0,"pos_order_id":0,"loyelty_consumed":0,"reorder_status":0,"order_status":null,"status":0,"cust_message":null,"is_gift":0,"coupon_code":null,"coupon_amt":0,"app_type":"LOCAL","is_in_house":0,"updated_at":"2021-01-25 06:55:18","created_at":"2021-01-25 06:55:18","id":6416,"active_capture_id":"21012506DR55KQ18ZQ65NYL","payment_gatway":"active","stripe_capture_id":null}
    * venueData : {"id":34,"fire_base_id":"dxANAVEIQTmdQkFdJ1Wdje:APA91bGsH4wzcF5_wSSC9QjiCbLFna2QLFV3uifb1x1RRliyoJUORAzZlbgwJ83P-Yyni2bBnKddZdwoRmrZRgM32y0zjB1a90eFOEOuq9Y8U69HRAvRNBL-54pokLd-MCvvhQYkR1nA","venue_id":"202004271734165","app_type":"POS","venue_name":"Dixi Chicken Pizza","phone_number":"1231231231","venue_email":"dixicp@mailop7.com","address_1":"Southport Road, Liverpool, Bootle, UK","venue_images":"20200622115653-resize.jpg,,,,,","end_days":70,"collection_time":70,"active_merchant_id":"120050","active_signature":"FJnZrv1gWCdTWdp","stripe_account_id":"acct_1FQuVcENl1JAqpZk"}
    */

   private boolean status;
   private String message;
   private OrderBean order;
   private VenueDataBean venueData;

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

   public OrderBean getOrder() {
      return order;
   }

   public void setOrder(OrderBean order) {
      this.order = order;
   }

   public VenueDataBean getVenueData() {
      return venueData;
   }

   public void setVenueData(VenueDataBean venueData) {
      this.venueData = venueData;
   }

   public static class OrderBean {
      /**
       * unique_code : 145#0039
       * same_day_id : null
       * merchant_id : 5
       * venue_id : 202004271734165
       * reservation_id : 0
       * table_no : 0
       * till_id : 0
       * staff_id : 0
       * cust_id : 145
       * card_number : ************1112
       * expected_delivery_date : 2021-01-25 10:20
       * payment_mode : Card
       * source_type : app
       * order_date : 2021-01-25 06:55:17
       * delivery_type : Home Delivery
       * user_addr_id : null
       * qr_order : uploaded/orderQr/6629016115577180.png
       * total_items : 2
       * is_hospitality : 1
       * amt_without_tax_discount : 19.98
       * transaction_charge : 0.00
       * service_charge : 10
       * tip_amt : 1.9980000000000002
       * total_discount : 0
       * total_tax : 2.17
       * is_asap : 0
       * net_amount : 24.148
       * delivery_charge : 0.00
       * shipping_id : 0
       * expected_shipping_date : 2021-04-05 06:55:18
       * shipping_name : Normal
       * stamp_reward_consumed_id : 0
       * claim_stamp_reward_id : 0
       * claim_stamp_reward_amt : 0
       * loyalty_point_value : 0
       * loyalty_point : 0
       * loyelty_used_amount : 0
       * pos_order_id : 0
       * loyelty_consumed : 0
       * reorder_status : 0
       * order_status : null
       * status : 0
       * cust_message : null
       * is_gift : 0
       * coupon_code : null
       * coupon_amt : 0
       * app_type : LOCAL
       * is_in_house : 0
       * updated_at : 2021-01-25 06:55:18
       * created_at : 2021-01-25 06:55:18
       * id : 6416
       * active_capture_id : 21012506DR55KQ18ZQ65NYL
       * payment_gatway : active
       * stripe_capture_id : null
       */

      private String unique_code;
      private Object same_day_id;
      private String merchant_id;
      private String venue_id;
      private double reservation_id;
      private int table_no;
      private String till_id;
      private int staff_id;
      private int cust_id;
      private String card_number;
      private String expected_delivery_date;
      private String payment_mode;
      private String source_type;
      private String order_date;
      private String delivery_type;
      private Object user_addr_id;
      private String qr_order;
      private int total_items;
      private int is_hospitality;
      private double amt_without_tax_discount;
      private String transaction_charge;
      private String service_charge;
      private String tip_amt;
      private double total_discount;
      private double total_tax;
      private int is_asap;
      private double net_amount;
      private String delivery_charge;
      private int shipping_id;
      private String expected_shipping_date;
      private String shipping_name;
      private int stamp_reward_consumed_id;
      private int claim_stamp_reward_id;
      private double claim_stamp_reward_amt;
      private double loyalty_point_value;
      private double loyalty_point;
      private double loyelty_used_amount;
      private int pos_order_id;
      private int loyelty_consumed;
      private int reorder_status;
      private Object order_status;
      private int status;
      private Object cust_message;
      private int is_gift;
      private Object coupon_code;
      private double coupon_amt;
      private String app_type;
      private int is_in_house;
      private String updated_at;
      private String created_at;
      private int id;
      private String active_capture_id;
      private String payment_gatway;
      private Object stripe_capture_id;

      public String getUnique_code() {
         return unique_code;
      }

      public void setUnique_code(String unique_code) {
         this.unique_code = unique_code;
      }

      public Object getSame_day_id() {
         return same_day_id;
      }

      public void setSame_day_id(Object same_day_id) {
         this.same_day_id = same_day_id;
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

      public double getReservation_id() {
         return reservation_id;
      }

      public void setReservation_id(double reservation_id) {
         this.reservation_id = reservation_id;
      }

      public int getTable_no() {
         return table_no;
      }

      public void setTable_no(int table_no) {
         this.table_no = table_no;
      }

      public String getTill_id() {
         return till_id;
      }

      public void setTill_id(String till_id) {
         this.till_id = till_id;
      }

      public int getStaff_id() {
         return staff_id;
      }

      public void setStaff_id(int staff_id) {
         this.staff_id = staff_id;
      }

      public int getCust_id() {
         return cust_id;
      }

      public void setCust_id(int cust_id) {
         this.cust_id = cust_id;
      }

      public String getCard_number() {
         return card_number;
      }

      public void setCard_number(String card_number) {
         this.card_number = card_number;
      }

      public String getExpected_delivery_date() {
         return expected_delivery_date;
      }

      public void setExpected_delivery_date(String expected_delivery_date) {
         this.expected_delivery_date = expected_delivery_date;
      }

      public String getPayment_mode() {
         return payment_mode;
      }

      public void setPayment_mode(String payment_mode) {
         this.payment_mode = payment_mode;
      }

      public String getSource_type() {
         return source_type;
      }

      public void setSource_type(String source_type) {
         this.source_type = source_type;
      }

      public String getOrder_date() {
         return order_date;
      }

      public void setOrder_date(String order_date) {
         this.order_date = order_date;
      }

      public String getDelivery_type() {
         return delivery_type;
      }

      public void setDelivery_type(String delivery_type) {
         this.delivery_type = delivery_type;
      }

      public Object getUser_addr_id() {
         return user_addr_id;
      }

      public void setUser_addr_id(Object user_addr_id) {
         this.user_addr_id = user_addr_id;
      }

      public String getQr_order() {
         return qr_order;
      }

      public void setQr_order(String qr_order) {
         this.qr_order = qr_order;
      }

      public int getTotal_items() {
         return total_items;
      }

      public void setTotal_items(int total_items) {
         this.total_items = total_items;
      }

      public int getIs_hospitality() {
         return is_hospitality;
      }

      public void setIs_hospitality(int is_hospitality) {
         this.is_hospitality = is_hospitality;
      }

      public double getAmt_without_tax_discount() {
         return amt_without_tax_discount;
      }

      public void setAmt_without_tax_discount(double amt_without_tax_discount) {
         this.amt_without_tax_discount = amt_without_tax_discount;
      }

      public String getTransaction_charge() {
         return transaction_charge;
      }

      public void setTransaction_charge(String transaction_charge) {
         this.transaction_charge = transaction_charge;
      }

      public String getService_charge() {
         return service_charge;
      }

      public void setService_charge(String service_charge) {
         this.service_charge = service_charge;
      }

      public String getTip_amt() {
         return tip_amt;
      }

      public void setTip_amt(String tip_amt) {
         this.tip_amt = tip_amt;
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

      public int getIs_asap() {
         return is_asap;
      }

      public void setIs_asap(int is_asap) {
         this.is_asap = is_asap;
      }

      public double getNet_amount() {
         return net_amount;
      }

      public void setNet_amount(double net_amount) {
         this.net_amount = net_amount;
      }

      public String getDelivery_charge() {
         return delivery_charge;
      }

      public void setDelivery_charge(String delivery_charge) {
         this.delivery_charge = delivery_charge;
      }

      public int getShipping_id() {
         return shipping_id;
      }

      public void setShipping_id(int shipping_id) {
         this.shipping_id = shipping_id;
      }

      public String getExpected_shipping_date() {
         return expected_shipping_date;
      }

      public void setExpected_shipping_date(String expected_shipping_date) {
         this.expected_shipping_date = expected_shipping_date;
      }

      public String getShipping_name() {
         return shipping_name;
      }

      public void setShipping_name(String shipping_name) {
         this.shipping_name = shipping_name;
      }

      public int getStamp_reward_consumed_id() {
         return stamp_reward_consumed_id;
      }

      public void setStamp_reward_consumed_id(int stamp_reward_consumed_id) {
         this.stamp_reward_consumed_id = stamp_reward_consumed_id;
      }

      public int getClaim_stamp_reward_id() {
         return claim_stamp_reward_id;
      }

      public void setClaim_stamp_reward_id(int claim_stamp_reward_id) {
         this.claim_stamp_reward_id = claim_stamp_reward_id;
      }

      public double getClaim_stamp_reward_amt() {
         return claim_stamp_reward_amt;
      }

      public void setClaim_stamp_reward_amt(double claim_stamp_reward_amt) {
         this.claim_stamp_reward_amt = claim_stamp_reward_amt;
      }

      public double getLoyalty_point_value() {
         return loyalty_point_value;
      }

      public void setLoyalty_point_value(double loyalty_point_value) {
         this.loyalty_point_value = loyalty_point_value;
      }

      public double getLoyalty_point() {
         return loyalty_point;
      }

      public void setLoyalty_point(double loyalty_point) {
         this.loyalty_point = loyalty_point;
      }

      public double getLoyelty_used_amount() {
         return loyelty_used_amount;
      }

      public void setLoyelty_used_amount(double loyelty_used_amount) {
         this.loyelty_used_amount = loyelty_used_amount;
      }

      public int getPos_order_id() {
         return pos_order_id;
      }

      public void setPos_order_id(int pos_order_id) {
         this.pos_order_id = pos_order_id;
      }

      public int getLoyelty_consumed() {
         return loyelty_consumed;
      }

      public void setLoyelty_consumed(int loyelty_consumed) {
         this.loyelty_consumed = loyelty_consumed;
      }

      public int getReorder_status() {
         return reorder_status;
      }

      public void setReorder_status(int reorder_status) {
         this.reorder_status = reorder_status;
      }

      public Object getOrder_status() {
         return order_status;
      }

      public void setOrder_status(Object order_status) {
         this.order_status = order_status;
      }

      public int getStatus() {
         return status;
      }

      public void setStatus(int status) {
         this.status = status;
      }

      public Object getCust_message() {
         return cust_message;
      }

      public void setCust_message(Object cust_message) {
         this.cust_message = cust_message;
      }

      public int getIs_gift() {
         return is_gift;
      }

      public void setIs_gift(int is_gift) {
         this.is_gift = is_gift;
      }

      public Object getCoupon_code() {
         return coupon_code;
      }

      public void setCoupon_code(Object coupon_code) {
         this.coupon_code = coupon_code;
      }

      public double getCoupon_amt() {
         return coupon_amt;
      }

      public void setCoupon_amt(double coupon_amt) {
         this.coupon_amt = coupon_amt;
      }

      public String getApp_type() {
         return app_type;
      }

      public void setApp_type(String app_type) {
         this.app_type = app_type;
      }

      public int getIs_in_house() {
         return is_in_house;
      }

      public void setIs_in_house(int is_in_house) {
         this.is_in_house = is_in_house;
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

      public String getActive_capture_id() {
         return active_capture_id;
      }

      public void setActive_capture_id(String active_capture_id) {
         this.active_capture_id = active_capture_id;
      }

      public String getPayment_gatway() {
         return payment_gatway;
      }

      public void setPayment_gatway(String payment_gatway) {
         this.payment_gatway = payment_gatway;
      }

      public Object getStripe_capture_id() {
         return stripe_capture_id;
      }

      public void setStripe_capture_id(Object stripe_capture_id) {
         this.stripe_capture_id = stripe_capture_id;
      }
   }

   public static class VenueDataBean {
      /**
       * id : 34
       * fire_base_id : dxANAVEIQTmdQkFdJ1Wdje:APA91bGsH4wzcF5_wSSC9QjiCbLFna2QLFV3uifb1x1RRliyoJUORAzZlbgwJ83P-Yyni2bBnKddZdwoRmrZRgM32y0zjB1a90eFOEOuq9Y8U69HRAvRNBL-54pokLd-MCvvhQYkR1nA
       * venue_id : 202004271734165
       * app_type : POS
       * venue_name : Dixi Chicken Pizza
       * phone_number : 1231231231
       * venue_email : dixicp@mailop7.com
       * address_1 : Southport Road, Liverpool, Bootle, UK
       * venue_images : 20200622115653-resize.jpg,,,,,
       * end_days : 70
       * collection_time : 70
       * active_merchant_id : 120050
       * active_signature : FJnZrv1gWCdTWdp
       * stripe_account_id : acct_1FQuVcENl1JAqpZk
       */

      private int id;
      private String fire_base_id;
      private String venue_id;
      private String app_type;
      private String venue_name;
      private String phone_number;
      private String venue_email;
      private String address_1;
      private String venue_images;
      private int end_days;
      private int collection_time;
      private String active_merchant_id;
      private String active_signature;
      private String stripe_account_id;

      public int getId() {
         return id;
      }

      public void setId(int id) {
         this.id = id;
      }

      public String getFire_base_id() {
         return fire_base_id;
      }

      public void setFire_base_id(String fire_base_id) {
         this.fire_base_id = fire_base_id;
      }

      public String getVenue_id() {
         return venue_id;
      }

      public void setVenue_id(String venue_id) {
         this.venue_id = venue_id;
      }

      public String getApp_type() {
         return app_type;
      }

      public void setApp_type(String app_type) {
         this.app_type = app_type;
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

      public String getAddress_1() {
         return address_1;
      }

      public void setAddress_1(String address_1) {
         this.address_1 = address_1;
      }

      public String getVenue_images() {
         return venue_images;
      }

      public void setVenue_images(String venue_images) {
         this.venue_images = venue_images;
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

      public String getActive_merchant_id() {
         return active_merchant_id;
      }

      public void setActive_merchant_id(String active_merchant_id) {
         this.active_merchant_id = active_merchant_id;
      }

      public String getActive_signature() {
         return active_signature;
      }

      public void setActive_signature(String active_signature) {
         this.active_signature = active_signature;
      }

      public String getStripe_account_id() {
         return stripe_account_id;
      }

      public void setStripe_account_id(String stripe_account_id) {
         this.stripe_account_id = stripe_account_id;
      }
   }
}