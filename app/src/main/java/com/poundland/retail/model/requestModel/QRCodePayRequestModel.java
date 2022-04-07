package com.poundland.retail.model.requestModel;

public class QRCodePayRequestModel {

 /**
  * payment_gatway : active
  * order_no : POS@68.42@163222931087695132@0
  * order_id : 11690
  * cardNumber : NDAxMjAwMTAzNzE0MTExMg==
  * cardExpiryMonth : MTI=
  * cardExpiryYear : MjQ=
  * cardCVV : MDgz
  * card_id : 21032511QR36SD31FS26SKD
  * issave : 0
  * billing_address : Noida
  * billing_postcode : TE15 5ST
  * loyelty_used_amount : 0
  * coupon_code :
  * coupon_amt : 0
  * payment_mode : Card
  * net_amount : 13.26
  */

 private String payment_gatway;
 private String order_no;
 private String order_id;
 private String cardNumber;
 private String cardExpiryMonth;
 private String cardExpiryYear;
 private String cardCVV;
 private String card_id;
 private String issave;
 private String billing_address;
 private String billing_postcode;
 private String loyelty_used_amount;
 private String coupon_code;
 private String coupon_amt;
 private String payment_mode;
 private String net_amount;

 public QRCodePayRequestModel(String payment_gatway, String order_no, String order_id, String cardNumber, String cardExpiryMonth, String cardExpiryYear, String cardCVV, String card_id, String issave, String billing_address, String billing_postcode, String loyelty_used_amount, String coupon_code, String coupon_amt, String payment_mode, String net_amount) {
  this.payment_gatway = payment_gatway;
  this.order_no = order_no;
  this.order_id = order_id;
  this.cardNumber = cardNumber;
  this.cardExpiryMonth = cardExpiryMonth;
  this.cardExpiryYear = cardExpiryYear;
  this.cardCVV = cardCVV;
  this.card_id = card_id;
  this.issave = issave;
  this.billing_address = billing_address;
  this.billing_postcode = billing_postcode;
  this.loyelty_used_amount = loyelty_used_amount;
  this.coupon_code = coupon_code;
  this.coupon_amt = coupon_amt;
  this.payment_mode = payment_mode;
  this.net_amount = net_amount;
 }

 public String getPayment_gatway() {
  return payment_gatway;
 }

 public void setPayment_gatway(String payment_gatway) {
  this.payment_gatway = payment_gatway;
 }

 public String getOrder_no() {
  return order_no;
 }

 public void setOrder_no(String order_no) {
  this.order_no = order_no;
 }

 public String getOrder_id() {
  return order_id;
 }

 public void setOrder_id(String order_id) {
  this.order_id = order_id;
 }

 public String getCardNumber() {
  return cardNumber;
 }

 public void setCardNumber(String cardNumber) {
  this.cardNumber = cardNumber;
 }

 public String getCardExpiryMonth() {
  return cardExpiryMonth;
 }

 public void setCardExpiryMonth(String cardExpiryMonth) {
  this.cardExpiryMonth = cardExpiryMonth;
 }

 public String getCardExpiryYear() {
  return cardExpiryYear;
 }

 public void setCardExpiryYear(String cardExpiryYear) {
  this.cardExpiryYear = cardExpiryYear;
 }

 public String getCardCVV() {
  return cardCVV;
 }

 public void setCardCVV(String cardCVV) {
  this.cardCVV = cardCVV;
 }

 public String getCard_id() {
  return card_id;
 }

 public void setCard_id(String card_id) {
  this.card_id = card_id;
 }

 public String getIssave() {
  return issave;
 }

 public void setIssave(String issave) {
  this.issave = issave;
 }

 public String getBilling_address() {
  return billing_address;
 }

 public void setBilling_address(String billing_address) {
  this.billing_address = billing_address;
 }

 public String getBilling_postcode() {
  return billing_postcode;
 }

 public void setBilling_postcode(String billing_postcode) {
  this.billing_postcode = billing_postcode;
 }

 public String getLoyelty_used_amount() {
  return loyelty_used_amount;
 }

 public void setLoyelty_used_amount(String loyelty_used_amount) {
  this.loyelty_used_amount = loyelty_used_amount;
 }

 public String getCoupon_code() {
  return coupon_code;
 }

 public void setCoupon_code(String coupon_code) {
  this.coupon_code = coupon_code;
 }

 public String getCoupon_amt() {
  return coupon_amt;
 }

 public void setCoupon_amt(String coupon_amt) {
  this.coupon_amt = coupon_amt;
 }

 public String getPayment_mode() {
  return payment_mode;
 }

 public void setPayment_mode(String payment_mode) {
  this.payment_mode = payment_mode;
 }

 public String getNet_amount() {
  return net_amount;
 }

 public void setNet_amount(String net_amount) {
  this.net_amount = net_amount;
 }
}