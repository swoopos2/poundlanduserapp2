package com.poundland.retail.model.responseModel;

public class SocialMediaResponseModel {


    /**
     * status : true
     * data : Login successfull !
     * success_data : {"token_type":"Swoopos","expires_in":1583569440,"access_token":"eyJpdiI6ImNNYVZaR25KM05IRXlLXC95S2RaMUZBPT0iLCJ2YWx1ZSI6ImJrVXRnMDY4K2pzdG4xKzVpXC9UT1NiVzhiVDVWUnVvdHVPOWJ5ZWdTWnNYUkJTa0tmSGhZN2tmOHRaNHFzeUwwbFwvZCt2a3FDU21uOHFKU2s2dUNraHhvQnZlS0ZDNWhyQlV4Z2ZHelRScWtuT1Q3alEyV0FwVlNJQWpaUkxpUjEiLCJtYWMiOiI2YWExNzE3YzAwMmYxZjM5NGYxYzJhY2YxMjg1ZTJiNDUxY2YyZTFiYzIxNmNhNjEzZjg2NmYwMWM1OTkyOGY1In0=","id":37,"first_name":"Rahul","last_name":"Kr","dob":null,"email":"rahulkr14@gmail.com","contact_no":"","gender":"","image":"https://lh6.googleusercontent.com/-MvUsxM976oc/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3rdHaKkbW_EQ-BiuBoEIzt2AEH6rWQ/photo.jpg"}
     */

    private boolean status;
    private String data;
    private SuccessDataBean success_data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public SuccessDataBean getSuccess_data() {
        return success_data;
    }

    public void setSuccess_data(SuccessDataBean success_data) {
        this.success_data = success_data;
    }

    public static class SuccessDataBean {
        /**
         * token_type : Swoopos
         * expires_in : 1583569440
         * access_token : eyJpdiI6ImNNYVZaR25KM05IRXlLXC95S2RaMUZBPT0iLCJ2YWx1ZSI6ImJrVXRnMDY4K2pzdG4xKzVpXC9UT1NiVzhiVDVWUnVvdHVPOWJ5ZWdTWnNYUkJTa0tmSGhZN2tmOHRaNHFzeUwwbFwvZCt2a3FDU21uOHFKU2s2dUNraHhvQnZlS0ZDNWhyQlV4Z2ZHelRScWtuT1Q3alEyV0FwVlNJQWpaUkxpUjEiLCJtYWMiOiI2YWExNzE3YzAwMmYxZjM5NGYxYzJhY2YxMjg1ZTJiNDUxY2YyZTFiYzIxNmNhNjEzZjg2NmYwMWM1OTkyOGY1In0=
         * id : 37
         * first_name : Rahul
         * last_name : Kr
         * dob : null
         * email : rahulkr14@gmail.com
         * contact_no :
         * gender :
         * image : https://lh6.googleusercontent.com/-MvUsxM976oc/AAAAAAAAAAI/AAAAAAAAAAA/ACHi3rdHaKkbW_EQ-BiuBoEIzt2AEH6rWQ/photo.jpg
         */

        private String token_type;
        private int expires_in;
        private String access_token;
        private int id;
        private String first_name;
        private String last_name;
        private String dob;
        private String email;
        private String contact_no;
        private String gender;
        private String image;
        private String referral_code;
        private String referred_code;
        private String stripe_cust_id;
        private int customerOrder;
        private int cart;

        public int getCart() {
            return cart;
        }

        public void setCart(int cart) {
            this.cart = cart;
        }

        public int getCustomerOrder() {
            return customerOrder;
        }

        public void setCustomerOrder(int customerOrder) {
            this.customerOrder = customerOrder;
        }

        public String getStripe_cust_id() {
            return stripe_cust_id;
        }

        public void setStripe_cust_id(String stripe_cust_id) {
            this.stripe_cust_id = stripe_cust_id;
        }

        public String getReferral_code() {
            return referral_code;
        }

        public void setReferral_code(String referral_code) {
            this.referral_code = referral_code;
        }

        public String getReferred_code() {
            return referred_code;
        }

        public void setReferred_code(String referred_code) {
            this.referred_code = referred_code;
        }

        public String getToken_type() {
            return token_type;
        }

        public void setToken_type(String token_type) {
            this.token_type = token_type;
        }

        public int getExpires_in() {
            return expires_in;
        }

        public void setExpires_in(int expires_in) {
            this.expires_in = expires_in;
        }

        public String getAccess_token() {
            return access_token;
        }

        public void setAccess_token(String access_token) {
            this.access_token = access_token;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

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

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContact_no() {
            return contact_no;
        }

        public void setContact_no(String contact_no) {
            this.contact_no = contact_no;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }
}
