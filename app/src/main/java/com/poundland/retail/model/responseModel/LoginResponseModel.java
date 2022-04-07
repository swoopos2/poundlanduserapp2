package com.poundland.retail.model.responseModel;

public class LoginResponseModel {

    /**
     * token_type : Swoopos
     * expires_in : 1579422419
     * access_token : eyQ2MDczN2ViODE2MDYxYWU3NyJ9
     * message : Signing Successfully.
     * status : true
     * id : 8
     * first_name : Rahul
     * last_name : Kr
     * dob : 2010-02-10
     * email : rahulkr@gmail.com
     * contact_no : 7897646955
     * gender : M
     * image :
     * "is_otp": false,
     * "stripe_cust_id": "cus_GL6No0SmwHvCHy"
     */
    private String token_type;
    private double expires_in;
    private String access_token;
    private String message;
    private boolean status;
    private int id;
    private String first_name;
    private String last_name;
    private String dob;
    private String email;
    private String contact_no;
    private String referral_code;
    private String referred_code;
    private String gender;
    private String image;
    private boolean is_otp;
    private String stripe_cust_id;
    private int customerOrder;
    private int isCustomer;
    private int cart;
    private CustomerDataBean customerData;

    public int getCart() {
        return cart;
    }

    public void setCart(int cart) {
        this.cart = cart;
    }

    public CustomerDataBean getCustomerData() {
        return customerData;
    }

    public void setCustomerData(CustomerDataBean customerData) {
        this.customerData = customerData;
    }


    public int getIsCustomer() {
        return isCustomer;
    }

    public void setIsCustomer(int isCustomer) {
        this.isCustomer = isCustomer;
    }

    public int getCustomerOrder() {
        return customerOrder;
    }

    public void setCustomerOrder(int customerOrder) {
        this.customerOrder = customerOrder;
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

    public boolean isIs_otp() {
        return is_otp;
    }

    public void setIs_otp(boolean is_otp) {
        this.is_otp = is_otp;
    }

    public String getStripe_cust_id() {
        return stripe_cust_id;
    }

    public void setStripe_cust_id(String stripe_cust_id) {
        this.stripe_cust_id = stripe_cust_id;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public double getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(double expires_in) {
        this.expires_in = expires_in;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
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

    public static class CustomerDataBean {
        /**
         * id : 29
         * first_name : Omnist
         * last_name : Techhub
         * email : shakti.kumar22dec92@gmail.com
         * password : null
         * dob : 1982-03-22
         * contact_no : 917503695703
         * referral_code : 1A4KYX1VTG
         * reff_code : null
         * gender : none
         * remember_token :
         * api_token : bbb23e98fdef29eae745b3215cad9a2eceb032b2030a9131aa032654de7bc113
         * login_time : 2020-05-15 15:22:25
         * social_type : google
         * social_token : 103655317593207827315
         * facebook_id : 538392293664268
         * google_id : null
         * device_type : ANDROID
         * custmer_type : app
         * device_id : fkGq4OeASf-l7mxoe-g6bP:APA91bFTogK7LZqfFcXj3sEAV27ooCr1n0jc-rtzzUu4hlqy5Xfam6hrQY6VH8B79aF6hsQ6YUiNCwzm87PL8B5rMrXG8MDHUsNrdhifsA7Pjhy-D0NgLu54H17G8SNbopO8JpMgEWIN
         * login_device_type : ANDROID
         * imei_number : null
         * profile_image : https://lh3.googleusercontent.com/a-/AAuE7mDtUS_4KBqluA92g5zcCiBtWkWMAlmmC4bb27J73g
         * pos_cus_id : null
         * stripe_cust_id : cus_GlKGZQKntUYGRT
         * pin : null
         * status : 1
         * created_at : 2020-02-19 08:09:10
         * updated_at : 2020-05-15 15:22:25
         */

        private int id;
        private String first_name;
        private String last_name;
        private String email;
        private Object password;
        private String dob;
        private String contact_no;
        private String referral_code;
        private Object reff_code;
        private String gender;
        private String remember_token;
        private String api_token;
        private String login_time;
        private String social_type;
        private String social_token;
        private String facebook_id;
        private Object google_id;
        private String device_type;
        private String custmer_type;
        private String device_id;
        private String login_device_type;
        private Object imei_number;
        private String profile_image;
        private Object pos_cus_id;
        private String stripe_cust_id;
        private Object pin;
        private int status;
        private String created_at;
        private String updated_at;

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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public Object getPassword() {
            return password;
        }

        public void setPassword(Object password) {
            this.password = password;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getContact_no() {
            return contact_no;
        }

        public void setContact_no(String contact_no) {
            this.contact_no = contact_no;
        }

        public String getReferral_code() {
            return referral_code;
        }

        public void setReferral_code(String referral_code) {
            this.referral_code = referral_code;
        }

        public Object getReff_code() {
            return reff_code;
        }

        public void setReff_code(Object reff_code) {
            this.reff_code = reff_code;
        }

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getRemember_token() {
            return remember_token;
        }

        public void setRemember_token(String remember_token) {
            this.remember_token = remember_token;
        }

        public String getApi_token() {
            return api_token;
        }

        public void setApi_token(String api_token) {
            this.api_token = api_token;
        }

        public String getLogin_time() {
            return login_time;
        }

        public void setLogin_time(String login_time) {
            this.login_time = login_time;
        }

        public String getSocial_type() {
            return social_type;
        }

        public void setSocial_type(String social_type) {
            this.social_type = social_type;
        }

        public String getSocial_token() {
            return social_token;
        }

        public void setSocial_token(String social_token) {
            this.social_token = social_token;
        }

        public String getFacebook_id() {
            return facebook_id;
        }

        public void setFacebook_id(String facebook_id) {
            this.facebook_id = facebook_id;
        }

        public Object getGoogle_id() {
            return google_id;
        }

        public void setGoogle_id(Object google_id) {
            this.google_id = google_id;
        }

        public String getDevice_type() {
            return device_type;
        }

        public void setDevice_type(String device_type) {
            this.device_type = device_type;
        }

        public String getCustmer_type() {
            return custmer_type;
        }

        public void setCustmer_type(String custmer_type) {
            this.custmer_type = custmer_type;
        }

        public String getDevice_id() {
            return device_id;
        }

        public void setDevice_id(String device_id) {
            this.device_id = device_id;
        }

        public String getLogin_device_type() {
            return login_device_type;
        }

        public void setLogin_device_type(String login_device_type) {
            this.login_device_type = login_device_type;
        }

        public Object getImei_number() {
            return imei_number;
        }

        public void setImei_number(Object imei_number) {
            this.imei_number = imei_number;
        }

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public Object getPos_cus_id() {
            return pos_cus_id;
        }

        public void setPos_cus_id(Object pos_cus_id) {
            this.pos_cus_id = pos_cus_id;
        }

        public String getStripe_cust_id() {
            return stripe_cust_id;
        }

        public void setStripe_cust_id(String stripe_cust_id) {
            this.stripe_cust_id = stripe_cust_id;
        }

        public Object getPin() {
            return pin;
        }

        public void setPin(Object pin) {
            this.pin = pin;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
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
}
