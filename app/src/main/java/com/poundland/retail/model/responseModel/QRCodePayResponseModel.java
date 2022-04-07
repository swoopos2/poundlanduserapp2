package com.poundland.retail.model.responseModel;

public class QRCodePayResponseModel {


 /**
  * status : true
  * message : Order payment done successfully.
  * order : {"id":11690,"unique_code":"1213216543446","same_day_id":null,"stripe_capture_id":null,"active_capture_id":"21121810YC57VG31DD30PCJ","action_transaction_id":null,"payment_gatway":"active","merchant_id":"15","source_type":"ecom","payment_mode":"","payment_details":null,"pos_order_id":"0","pos_order_unique_id":null,"user_addr_id":null,"venue_id":"2020051914514215","till_id":"0","staff_id":"0","cust_id":216,"customer_details":{"first_name":"sam","last_name":"Billing","delivery_address":"Wake Green Road, Birmingham B13 9PG, UK","postcode":"B13 9PG"},"customer_email":"sam@gmail.com","customer_phone":"5686858756","card_number":"************1112","order_id":null,"reservation_id":"4545","table_no":"25","table_assign_id":"01639807320","order_date":"2020-10-19 12:29:43","real_order_date":"2021-12-18 06:02:00","delivery_type":"Reservation","order_status":"Complete","special_dis_id":null,"special_dis_name":null,"special_dis_per":null,"special_dis_type":null,"special_dis_amount":"0.00","special_dis_available_for":null,"special_dis_given_staff_id":null,"total_items":"1","amt_without_tax_discount":"11.26","total_discount":"0.00","service_charge":"0.00","tip_amt":"0.00","packing_charge":"0.00","total_tax":"0.00","transaction_charge":"1.00","net_amount":"13.26","wastage_amount":"0.00","paid_amount":"13.26","refunded_amount":"0.00","refund_store_credit_amount":"0.00","payment_detail":null,"delivery_charge":"2.00","shipping_id":"0","shipping_name":"Normal","stamp_reward_consumed_id":"0","claim_stamp_reward_id":"0","claim_stamp_reward_amt":"0.00","buffer_id":"0","loyalty_point_value":"0.2","loyalty_point":"0","loyelty_used_amount":"0","loyelty_consumed":"5","coupon_code":null,"coupon_amt":"0","message":null,"qr_order":"uploaded/orderQr/5170516398073200.png","reorder_status":"0","return_status":null,"cancel_status":null,"is_rated":"0","status":4,"integration_status":null,"email_receipt":null,"voucher_email_id":null,"voucher_amt":"0.00","voucher_details_id":"0","z_read_id":null,"z_read_status":"0","cust_message":"Delivery Soon","is_gift":"0","tracking_id":null,"tracking_url":null,"tracking_title":null,"tracking_carrier_company":null,"is_asap":"1","expected_delivery_date":"2021-12-18 06:32:00","expected_shipping_date":null,"shipping_method":null,"lat":null,"lng":null,"is_hospitality":"1","driver_id":null,"driver_order_id":null,"driver_assign_date":null,"order_deliver_date":null,"driver_order_reason":null,"driver_undeliver_custom_msg":null,"is_in_house":"0","created_at":"2021-12-18 06:02:00","updated_at":"2021-12-18 10:57:31","app_type":"APP4","is_undelivered":"0","is_sync":"0","api_name":null,"split_type":"0"}
  * venueData : {"id":40,"fire_base_id":"dXQPFMs-SMGR5t8DzC4C4Q:APA91bEKt3ogN0M3CAWYJtKni7nCHqz1kmXef7sKp7pFkAbPXOOv1nQAE_lP3kzesZDDKidofPkusWN4qU5BL2ESLMdUWIbr5OsOHatIoj0EWJdaIMcXbN7v22hBet7JhSDJw1nY2PFa","venue_id":"2020051914514215","app_type":"POS","venue_name":"New Look","phone_number":"044254877984","venue_email":"newlook@gilfun.com","address_1":"UKIM Madinah Masjid, New Hampton Road East","venue_images":"20200918135628-1589896302-new-look-cornwallPNG.png,,,,,","end_days":"3","collection_time":"30","active_merchant_id":"120050","active_signature":"FJnZrv1gWCdTWdp","stripe_account_id":"acct_1GnM05Damq0XDWei","is_event":"0"}
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

 public static class OrderBean  {
  /**
   * id : 11690
   * unique_code : 1213216543446
   * same_day_id : null
   * stripe_capture_id : null
   * active_capture_id : 21121810YC57VG31DD30PCJ
   * action_transaction_id : null
   * payment_gatway : active
   * merchant_id : 15
   * source_type : ecom
   * payment_mode :
   * payment_details : null
   * pos_order_id : 0
   * pos_order_unique_id : null
   * user_addr_id : null
   * venue_id : 2020051914514215
   * till_id : 0
   * staff_id : 0
   * cust_id : 216
   * customer_details : {"first_name":"sam","last_name":"Billing","delivery_address":"Wake Green Road, Birmingham B13 9PG, UK","postcode":"B13 9PG"}
   * customer_email : sam@gmail.com
   * customer_phone : 5686858756
   * card_number : ************1112
   * order_id : null
   * reservation_id : 4545
   * table_no : 25
   * table_assign_id : 01639807320
   * order_date : 2020-10-19 12:29:43
   * real_order_date : 2021-12-18 06:02:00
   * delivery_type : Reservation
   * order_status : Complete
   * special_dis_id : null
   * special_dis_name : null
   * special_dis_per : null
   * special_dis_type : null
   * special_dis_amount : 0.00
   * special_dis_available_for : null
   * special_dis_given_staff_id : null
   * total_items : 1
   * amt_without_tax_discount : 11.26
   * total_discount : 0.00
   * service_charge : 0.00
   * tip_amt : 0.00
   * packing_charge : 0.00
   * total_tax : 0.00
   * transaction_charge : 1.00
   * net_amount : 13.26
   * wastage_amount : 0.00
   * paid_amount : 13.26
   * refunded_amount : 0.00
   * refund_store_credit_amount : 0.00
   * payment_detail : null
   * delivery_charge : 2.00
   * shipping_id : 0
   * shipping_name : Normal
   * stamp_reward_consumed_id : 0
   * claim_stamp_reward_id : 0
   * claim_stamp_reward_amt : 0.00
   * buffer_id : 0
   * loyalty_point_value : 0.2
   * loyalty_point : 0
   * loyelty_used_amount : 0
   * loyelty_consumed : 5
   * coupon_code : null
   * coupon_amt : 0
   * message : null
   * qr_order : uploaded/orderQr/5170516398073200.png
   * reorder_status : 0
   * return_status : null
   * cancel_status : null
   * is_rated : 0
   * status : 4
   * integration_status : null
   * email_receipt : null
   * voucher_email_id : null
   * voucher_amt : 0.00
   * voucher_details_id : 0
   * z_read_id : null
   * z_read_status : 0
   * cust_message : Delivery Soon
   * is_gift : 0
   * tracking_id : null
   * tracking_url : null
   * tracking_title : null
   * tracking_carrier_company : null
   * is_asap : 1
   * expected_delivery_date : 2021-12-18 06:32:00
   * expected_shipping_date : null
   * shipping_method : null
   * lat : null
   * lng : null
   * is_hospitality : 1
   * driver_id : null
   * driver_order_id : null
   * driver_assign_date : null
   * order_deliver_date : null
   * driver_order_reason : null
   * driver_undeliver_custom_msg : null
   * is_in_house : 0
   * created_at : 2021-12-18 06:02:00
   * updated_at : 2021-12-18 10:57:31
   * app_type : APP4
   * is_undelivered : 0
   * is_sync : 0
   * api_name : null
   * split_type : 0
   */

  private int id;
  private String unique_code;
  private Object same_day_id;
  private Object stripe_capture_id;
  private String active_capture_id;
  private Object action_transaction_id;
  private String payment_gatway;
  private String merchant_id;
  private String source_type;
  private String payment_mode;
  private Object payment_details;
  private String pos_order_id;
  private Object pos_order_unique_id;
  private Object user_addr_id;
  private String venue_id;
  private String till_id;
  private String staff_id;
  private int cust_id;
  private CustomerDetailsBean customer_details;
  private String customer_email;
  private String customer_phone;
  private String card_number;
  private Object order_id;
  private String reservation_id;
  private String table_no;
  private String table_assign_id;
  private String order_date;
  private String real_order_date;
  private String delivery_type;
  private String order_status;
  private Object special_dis_id;
  private Object special_dis_name;
  private Object special_dis_per;
  private Object special_dis_type;
  private String special_dis_amount;
  private Object special_dis_available_for;
  private Object special_dis_given_staff_id;
  private String total_items;
  private String amt_without_tax_discount;
  private String total_discount;
  private String service_charge;
  private String tip_amt;
  private String packing_charge;
  private String total_tax;
  private String transaction_charge;
  private String net_amount;
  private String wastage_amount;
  private String paid_amount;
  private String refunded_amount;
  private String refund_store_credit_amount;
  private Object payment_detail;
  private String delivery_charge;
  private String shipping_id;
  private String shipping_name;
  private String stamp_reward_consumed_id;
  private String claim_stamp_reward_id;
  private String claim_stamp_reward_amt;
  private String buffer_id;
  private String loyalty_point_value;
  private String loyalty_point;
  private String loyelty_used_amount;
  private String loyelty_consumed;
  private Object coupon_code;
  private String coupon_amt;
  private Object message;
  private String qr_order;
  private String reorder_status;
  private Object return_status;
  private Object cancel_status;
  private String is_rated;
  private int status;
  private Object integration_status;
  private Object email_receipt;
  private Object voucher_email_id;
  private String voucher_amt;
  private String voucher_details_id;
  private Object z_read_id;
  private String z_read_status;
  private String cust_message;
  private String is_gift;
  private Object tracking_id;
  private Object tracking_url;
  private Object tracking_title;
  private Object tracking_carrier_company;
  private String is_asap;
  private String expected_delivery_date;
  private Object expected_shipping_date;
  private Object shipping_method;
  private Object lat;
  private Object lng;
  private String is_hospitality;
  private Object driver_id;
  private Object driver_order_id;
  private Object driver_assign_date;
  private Object order_deliver_date;
  private Object driver_order_reason;
  private Object driver_undeliver_custom_msg;
  private String is_in_house;
  private String created_at;
  private String updated_at;
  private String app_type;
  private String is_undelivered;
  private String is_sync;
  private Object api_name;
  private String split_type;

  public int getId() {
   return id;
  }

  public void setId(int id) {
   this.id = id;
  }

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

  public Object getStripe_capture_id() {
   return stripe_capture_id;
  }

  public void setStripe_capture_id(Object stripe_capture_id) {
   this.stripe_capture_id = stripe_capture_id;
  }

  public String getActive_capture_id() {
   return active_capture_id;
  }

  public void setActive_capture_id(String active_capture_id) {
   this.active_capture_id = active_capture_id;
  }

  public Object getAction_transaction_id() {
   return action_transaction_id;
  }

  public void setAction_transaction_id(Object action_transaction_id) {
   this.action_transaction_id = action_transaction_id;
  }

  public String getPayment_gatway() {
   return payment_gatway;
  }

  public void setPayment_gatway(String payment_gatway) {
   this.payment_gatway = payment_gatway;
  }

  public String getMerchant_id() {
   return merchant_id;
  }

  public void setMerchant_id(String merchant_id) {
   this.merchant_id = merchant_id;
  }

  public String getSource_type() {
   return source_type;
  }

  public void setSource_type(String source_type) {
   this.source_type = source_type;
  }

  public String getPayment_mode() {
   return payment_mode;
  }

  public void setPayment_mode(String payment_mode) {
   this.payment_mode = payment_mode;
  }

  public Object getPayment_details() {
   return payment_details;
  }

  public void setPayment_details(Object payment_details) {
   this.payment_details = payment_details;
  }

  public String getPos_order_id() {
   return pos_order_id;
  }

  public void setPos_order_id(String pos_order_id) {
   this.pos_order_id = pos_order_id;
  }

  public Object getPos_order_unique_id() {
   return pos_order_unique_id;
  }

  public void setPos_order_unique_id(Object pos_order_unique_id) {
   this.pos_order_unique_id = pos_order_unique_id;
  }

  public Object getUser_addr_id() {
   return user_addr_id;
  }

  public void setUser_addr_id(Object user_addr_id) {
   this.user_addr_id = user_addr_id;
  }

  public String getVenue_id() {
   return venue_id;
  }

  public void setVenue_id(String venue_id) {
   this.venue_id = venue_id;
  }

  public String getTill_id() {
   return till_id;
  }

  public void setTill_id(String till_id) {
   this.till_id = till_id;
  }

  public String getStaff_id() {
   return staff_id;
  }

  public void setStaff_id(String staff_id) {
   this.staff_id = staff_id;
  }

  public int getCust_id() {
   return cust_id;
  }

  public void setCust_id(int cust_id) {
   this.cust_id = cust_id;
  }

  public CustomerDetailsBean getCustomer_details() {
   return customer_details;
  }

  public void setCustomer_details(CustomerDetailsBean customer_details) {
   this.customer_details = customer_details;
  }

  public String getCustomer_email() {
   return customer_email;
  }

  public void setCustomer_email(String customer_email) {
   this.customer_email = customer_email;
  }

  public String getCustomer_phone() {
   return customer_phone;
  }

  public void setCustomer_phone(String customer_phone) {
   this.customer_phone = customer_phone;
  }

  public String getCard_number() {
   return card_number;
  }

  public void setCard_number(String card_number) {
   this.card_number = card_number;
  }

  public Object getOrder_id() {
   return order_id;
  }

  public void setOrder_id(Object order_id) {
   this.order_id = order_id;
  }

  public String getReservation_id() {
   return reservation_id;
  }

  public void setReservation_id(String reservation_id) {
   this.reservation_id = reservation_id;
  }

  public String getTable_no() {
   return table_no;
  }

  public void setTable_no(String table_no) {
   this.table_no = table_no;
  }

  public String getTable_assign_id() {
   return table_assign_id;
  }

  public void setTable_assign_id(String table_assign_id) {
   this.table_assign_id = table_assign_id;
  }

  public String getOrder_date() {
   return order_date;
  }

  public void setOrder_date(String order_date) {
   this.order_date = order_date;
  }

  public String getReal_order_date() {
   return real_order_date;
  }

  public void setReal_order_date(String real_order_date) {
   this.real_order_date = real_order_date;
  }

  public String getDelivery_type() {
   return delivery_type;
  }

  public void setDelivery_type(String delivery_type) {
   this.delivery_type = delivery_type;
  }

  public String getOrder_status() {
   return order_status;
  }

  public void setOrder_status(String order_status) {
   this.order_status = order_status;
  }

  public Object getSpecial_dis_id() {
   return special_dis_id;
  }

  public void setSpecial_dis_id(Object special_dis_id) {
   this.special_dis_id = special_dis_id;
  }

  public Object getSpecial_dis_name() {
   return special_dis_name;
  }

  public void setSpecial_dis_name(Object special_dis_name) {
   this.special_dis_name = special_dis_name;
  }

  public Object getSpecial_dis_per() {
   return special_dis_per;
  }

  public void setSpecial_dis_per(Object special_dis_per) {
   this.special_dis_per = special_dis_per;
  }

  public Object getSpecial_dis_type() {
   return special_dis_type;
  }

  public void setSpecial_dis_type(Object special_dis_type) {
   this.special_dis_type = special_dis_type;
  }

  public String getSpecial_dis_amount() {
   return special_dis_amount;
  }

  public void setSpecial_dis_amount(String special_dis_amount) {
   this.special_dis_amount = special_dis_amount;
  }

  public Object getSpecial_dis_available_for() {
   return special_dis_available_for;
  }

  public void setSpecial_dis_available_for(Object special_dis_available_for) {
   this.special_dis_available_for = special_dis_available_for;
  }

  public Object getSpecial_dis_given_staff_id() {
   return special_dis_given_staff_id;
  }

  public void setSpecial_dis_given_staff_id(Object special_dis_given_staff_id) {
   this.special_dis_given_staff_id = special_dis_given_staff_id;
  }

  public String getTotal_items() {
   return total_items;
  }

  public void setTotal_items(String total_items) {
   this.total_items = total_items;
  }

  public String getAmt_without_tax_discount() {
   return amt_without_tax_discount;
  }

  public void setAmt_without_tax_discount(String amt_without_tax_discount) {
   this.amt_without_tax_discount = amt_without_tax_discount;
  }

  public String getTotal_discount() {
   return total_discount;
  }

  public void setTotal_discount(String total_discount) {
   this.total_discount = total_discount;
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

  public String getPacking_charge() {
   return packing_charge;
  }

  public void setPacking_charge(String packing_charge) {
   this.packing_charge = packing_charge;
  }

  public String getTotal_tax() {
   return total_tax;
  }

  public void setTotal_tax(String total_tax) {
   this.total_tax = total_tax;
  }

  public String getTransaction_charge() {
   return transaction_charge;
  }

  public void setTransaction_charge(String transaction_charge) {
   this.transaction_charge = transaction_charge;
  }

  public String getNet_amount() {
   return net_amount;
  }

  public void setNet_amount(String net_amount) {
   this.net_amount = net_amount;
  }

  public String getWastage_amount() {
   return wastage_amount;
  }

  public void setWastage_amount(String wastage_amount) {
   this.wastage_amount = wastage_amount;
  }

  public String getPaid_amount() {
   return paid_amount;
  }

  public void setPaid_amount(String paid_amount) {
   this.paid_amount = paid_amount;
  }

  public String getRefunded_amount() {
   return refunded_amount;
  }

  public void setRefunded_amount(String refunded_amount) {
   this.refunded_amount = refunded_amount;
  }

  public String getRefund_store_credit_amount() {
   return refund_store_credit_amount;
  }

  public void setRefund_store_credit_amount(String refund_store_credit_amount) {
   this.refund_store_credit_amount = refund_store_credit_amount;
  }

  public Object getPayment_detail() {
   return payment_detail;
  }

  public void setPayment_detail(Object payment_detail) {
   this.payment_detail = payment_detail;
  }

  public String getDelivery_charge() {
   return delivery_charge;
  }

  public void setDelivery_charge(String delivery_charge) {
   this.delivery_charge = delivery_charge;
  }

  public String getShipping_id() {
   return shipping_id;
  }

  public void setShipping_id(String shipping_id) {
   this.shipping_id = shipping_id;
  }

  public String getShipping_name() {
   return shipping_name;
  }

  public void setShipping_name(String shipping_name) {
   this.shipping_name = shipping_name;
  }

  public String getStamp_reward_consumed_id() {
   return stamp_reward_consumed_id;
  }

  public void setStamp_reward_consumed_id(String stamp_reward_consumed_id) {
   this.stamp_reward_consumed_id = stamp_reward_consumed_id;
  }

  public String getClaim_stamp_reward_id() {
   return claim_stamp_reward_id;
  }

  public void setClaim_stamp_reward_id(String claim_stamp_reward_id) {
   this.claim_stamp_reward_id = claim_stamp_reward_id;
  }

  public String getClaim_stamp_reward_amt() {
   return claim_stamp_reward_amt;
  }

  public void setClaim_stamp_reward_amt(String claim_stamp_reward_amt) {
   this.claim_stamp_reward_amt = claim_stamp_reward_amt;
  }

  public String getBuffer_id() {
   return buffer_id;
  }

  public void setBuffer_id(String buffer_id) {
   this.buffer_id = buffer_id;
  }

  public String getLoyalty_point_value() {
   return loyalty_point_value;
  }

  public void setLoyalty_point_value(String loyalty_point_value) {
   this.loyalty_point_value = loyalty_point_value;
  }

  public String getLoyalty_point() {
   return loyalty_point;
  }

  public void setLoyalty_point(String loyalty_point) {
   this.loyalty_point = loyalty_point;
  }

  public String getLoyelty_used_amount() {
   return loyelty_used_amount;
  }

  public void setLoyelty_used_amount(String loyelty_used_amount) {
   this.loyelty_used_amount = loyelty_used_amount;
  }

  public String getLoyelty_consumed() {
   return loyelty_consumed;
  }

  public void setLoyelty_consumed(String loyelty_consumed) {
   this.loyelty_consumed = loyelty_consumed;
  }

  public Object getCoupon_code() {
   return coupon_code;
  }

  public void setCoupon_code(Object coupon_code) {
   this.coupon_code = coupon_code;
  }

  public String getCoupon_amt() {
   return coupon_amt;
  }

  public void setCoupon_amt(String coupon_amt) {
   this.coupon_amt = coupon_amt;
  }

  public Object getMessage() {
   return message;
  }

  public void setMessage(Object message) {
   this.message = message;
  }

  public String getQr_order() {
   return qr_order;
  }

  public void setQr_order(String qr_order) {
   this.qr_order = qr_order;
  }

  public String getReorder_status() {
   return reorder_status;
  }

  public void setReorder_status(String reorder_status) {
   this.reorder_status = reorder_status;
  }

  public Object getReturn_status() {
   return return_status;
  }

  public void setReturn_status(Object return_status) {
   this.return_status = return_status;
  }

  public Object getCancel_status() {
   return cancel_status;
  }

  public void setCancel_status(Object cancel_status) {
   this.cancel_status = cancel_status;
  }

  public String getIs_rated() {
   return is_rated;
  }

  public void setIs_rated(String is_rated) {
   this.is_rated = is_rated;
  }

  public int getStatus() {
   return status;
  }

  public void setStatus(int status) {
   this.status = status;
  }

  public Object getIntegration_status() {
   return integration_status;
  }

  public void setIntegration_status(Object integration_status) {
   this.integration_status = integration_status;
  }

  public Object getEmail_receipt() {
   return email_receipt;
  }

  public void setEmail_receipt(Object email_receipt) {
   this.email_receipt = email_receipt;
  }

  public Object getVoucher_email_id() {
   return voucher_email_id;
  }

  public void setVoucher_email_id(Object voucher_email_id) {
   this.voucher_email_id = voucher_email_id;
  }

  public String getVoucher_amt() {
   return voucher_amt;
  }

  public void setVoucher_amt(String voucher_amt) {
   this.voucher_amt = voucher_amt;
  }

  public String getVoucher_details_id() {
   return voucher_details_id;
  }

  public void setVoucher_details_id(String voucher_details_id) {
   this.voucher_details_id = voucher_details_id;
  }

  public Object getZ_read_id() {
   return z_read_id;
  }

  public void setZ_read_id(Object z_read_id) {
   this.z_read_id = z_read_id;
  }

  public String getZ_read_status() {
   return z_read_status;
  }

  public void setZ_read_status(String z_read_status) {
   this.z_read_status = z_read_status;
  }

  public String getCust_message() {
   return cust_message;
  }

  public void setCust_message(String cust_message) {
   this.cust_message = cust_message;
  }

  public String getIs_gift() {
   return is_gift;
  }

  public void setIs_gift(String is_gift) {
   this.is_gift = is_gift;
  }

  public Object getTracking_id() {
   return tracking_id;
  }

  public void setTracking_id(Object tracking_id) {
   this.tracking_id = tracking_id;
  }

  public Object getTracking_url() {
   return tracking_url;
  }

  public void setTracking_url(Object tracking_url) {
   this.tracking_url = tracking_url;
  }

  public Object getTracking_title() {
   return tracking_title;
  }

  public void setTracking_title(Object tracking_title) {
   this.tracking_title = tracking_title;
  }

  public Object getTracking_carrier_company() {
   return tracking_carrier_company;
  }

  public void setTracking_carrier_company(Object tracking_carrier_company) {
   this.tracking_carrier_company = tracking_carrier_company;
  }

  public String getIs_asap() {
   return is_asap;
  }

  public void setIs_asap(String is_asap) {
   this.is_asap = is_asap;
  }

  public String getExpected_delivery_date() {
   return expected_delivery_date;
  }

  public void setExpected_delivery_date(String expected_delivery_date) {
   this.expected_delivery_date = expected_delivery_date;
  }

  public Object getExpected_shipping_date() {
   return expected_shipping_date;
  }

  public void setExpected_shipping_date(Object expected_shipping_date) {
   this.expected_shipping_date = expected_shipping_date;
  }

  public Object getShipping_method() {
   return shipping_method;
  }

  public void setShipping_method(Object shipping_method) {
   this.shipping_method = shipping_method;
  }

  public Object getLat() {
   return lat;
  }

  public void setLat(Object lat) {
   this.lat = lat;
  }

  public Object getLng() {
   return lng;
  }

  public void setLng(Object lng) {
   this.lng = lng;
  }

  public String getIs_hospitality() {
   return is_hospitality;
  }

  public void setIs_hospitality(String is_hospitality) {
   this.is_hospitality = is_hospitality;
  }

  public Object getDriver_id() {
   return driver_id;
  }

  public void setDriver_id(Object driver_id) {
   this.driver_id = driver_id;
  }

  public Object getDriver_order_id() {
   return driver_order_id;
  }

  public void setDriver_order_id(Object driver_order_id) {
   this.driver_order_id = driver_order_id;
  }

  public Object getDriver_assign_date() {
   return driver_assign_date;
  }

  public void setDriver_assign_date(Object driver_assign_date) {
   this.driver_assign_date = driver_assign_date;
  }

  public Object getOrder_deliver_date() {
   return order_deliver_date;
  }

  public void setOrder_deliver_date(Object order_deliver_date) {
   this.order_deliver_date = order_deliver_date;
  }

  public Object getDriver_order_reason() {
   return driver_order_reason;
  }

  public void setDriver_order_reason(Object driver_order_reason) {
   this.driver_order_reason = driver_order_reason;
  }

  public Object getDriver_undeliver_custom_msg() {
   return driver_undeliver_custom_msg;
  }

  public void setDriver_undeliver_custom_msg(Object driver_undeliver_custom_msg) {
   this.driver_undeliver_custom_msg = driver_undeliver_custom_msg;
  }

  public String getIs_in_house() {
   return is_in_house;
  }

  public void setIs_in_house(String is_in_house) {
   this.is_in_house = is_in_house;
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

  public String getApp_type() {
   return app_type;
  }

  public void setApp_type(String app_type) {
   this.app_type = app_type;
  }

  public String getIs_undelivered() {
   return is_undelivered;
  }

  public void setIs_undelivered(String is_undelivered) {
   this.is_undelivered = is_undelivered;
  }

  public String getIs_sync() {
   return is_sync;
  }

  public void setIs_sync(String is_sync) {
   this.is_sync = is_sync;
  }

  public Object getApi_name() {
   return api_name;
  }

  public void setApi_name(Object api_name) {
   this.api_name = api_name;
  }

  public String getSplit_type() {
   return split_type;
  }

  public void setSplit_type(String split_type) {
   this.split_type = split_type;
  }

  public static class CustomerDetailsBean {
   /**
    * first_name : sam
    * last_name : Billing
    * delivery_address : Wake Green Road, Birmingham B13 9PG, UK
    * postcode : B13 9PG
    */

   private String first_name;
   private String last_name;
   private String delivery_address;
   private String postcode;

   public String getFirst_name() {
    return first_name;
   }

   public void setFirst_name(String first_name) {
    this.first_name = first_name;
   }

   public String getLast_name() {
    return last_name;
   }

   public void setLast_name(String last_name) {
    this.last_name = last_name;
   }

   public String getDelivery_address() {
    return delivery_address;
   }

   public void setDelivery_address(String delivery_address) {
    this.delivery_address = delivery_address;
   }

   public String getPostcode() {
    return postcode;
   }

   public void setPostcode(String postcode) {
    this.postcode = postcode;
   }
  }
 }

 public static class VenueDataBean  {
  /**
   * id : 40
   * fire_base_id : dXQPFMs-SMGR5t8DzC4C4Q:APA91bEKt3ogN0M3CAWYJtKni7nCHqz1kmXef7sKp7pFkAbPXOOv1nQAE_lP3kzesZDDKidofPkusWN4qU5BL2ESLMdUWIbr5OsOHatIoj0EWJdaIMcXbN7v22hBet7JhSDJw1nY2PFa
   * venue_id : 2020051914514215
   * app_type : POS
   * venue_name : New Look
   * phone_number : 044254877984
   * venue_email : newlook@gilfun.com
   * address_1 : UKIM Madinah Masjid, New Hampton Road East
   * venue_images : 20200918135628-1589896302-new-look-cornwallPNG.png,,,,,
   * end_days : 3
   * collection_time : 30
   * active_merchant_id : 120050
   * active_signature : FJnZrv1gWCdTWdp
   * stripe_account_id : acct_1GnM05Damq0XDWei
   * is_event : 0
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
  private String end_days;
  private String collection_time;
  private String active_merchant_id;
  private String active_signature;
  private String stripe_account_id;
  private String is_event;

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

  public String getEnd_days() {
   return end_days;
  }

  public void setEnd_days(String end_days) {
   this.end_days = end_days;
  }

  public String getCollection_time() {
   return collection_time;
  }

  public void setCollection_time(String collection_time) {
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

  public String getIs_event() {
   return is_event;
  }

  public void setIs_event(String is_event) {
   this.is_event = is_event;
  }
 }
}