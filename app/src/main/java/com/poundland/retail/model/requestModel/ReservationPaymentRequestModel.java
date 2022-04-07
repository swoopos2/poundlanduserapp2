package com.poundland.retail.model.requestModel;

public class ReservationPaymentRequestModel {


    /**
     * reservation_id : 145
     * customer_name : 145
     * customer_email : 145
     * customer_id : 145
     * is_card : 145
     * card_id : 145
     * cardExpiryMonth : 145
     * cardExpiryYear : 145
     * cvv : 145
     * card_number : 145
     * address : 145
     * post_code : 145
     * save_card : 145
     */

    private int reservation_id;
    private String customer_name;
    private String customer_email;
    private int customer_id;
    private int is_card;
    private String card_id;
    private String cardExpiryMonth;
    private String cardExpiryYear;
    private String cvv;
    private String card_number;
    private String address;
    private String post_code;
    private boolean save_card;

    public ReservationPaymentRequestModel(int reservation_id, String customer_name, String customer_email, int customer_id, int is_card, String card_id, String cardExpiryMonth, String cardExpiryYear, String cvv, String card_number, String address, String post_code, boolean save_card) {
        this.reservation_id = reservation_id;
        this.customer_name = customer_name;
        this.customer_email = customer_email;
        this.customer_id = customer_id;
        this.is_card = is_card;
        this.card_id = card_id;
        this.cardExpiryMonth = cardExpiryMonth;
        this.cardExpiryYear = cardExpiryYear;
        this.cvv = cvv;
        this.card_number = card_number;
        this.address = address;
        this.post_code = post_code;
        this.save_card = save_card;
    }

    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public int getIs_card() {
        return is_card;
    }

    public void setIs_card(int is_card) {
        this.is_card = is_card;
    }

    public String getCard_id() {
        return card_id;
    }

    public void setCard_id(String card_id) {
        this.card_id = card_id;
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

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getCard_number() {
        return card_number;
    }

    public void setCard_number(String card_number) {
        this.card_number = card_number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPost_code() {
        return post_code;
    }

    public void setPost_code(String post_code) {
        this.post_code = post_code;
    }

    public boolean getSave_card() {
        return save_card;
    }

    public void setSave_card(boolean save_card) {
        this.save_card = save_card;
    }
}
