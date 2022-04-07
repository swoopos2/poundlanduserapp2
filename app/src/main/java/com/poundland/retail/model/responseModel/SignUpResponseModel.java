package com.poundland.retail.model.responseModel;

public class SignUpResponseModel {

    /**
     * status : true
     * data : Ragistration successfull !
     * success_data : {"token_type":"Swoopos","expires_in":1579855115,"access_token":"eyJpdiI6ImlTOWNSa0dvRStwbWlHcXpGSERFMkE9PSIsInZhbHVlIjoiZEZXcVVFZmJIRzM1TkwzMTZsbDV0UWxXTElXeHZTalkyalJSa3diTlZNMWNsWlY5SWY1eGpvR0tTUG5vRVZLMWtDbDFLWjEwdGhETFVLSVpLa0haREJyQmlhNXRQUXloMFE0VWJ2cXg0N2VucmdEclZSZCt3aklMakNLUUJwS0ciLCJtYWMiOiJmNzYwZmYyZWIzYTkwNDE4NzdhOTFjNTM1MTA4M2Q4ZjMwYmNjNTZlYzI3ZDkwN2FhYjc4MTgzN2RlZGVkMTFhIn0=","id":65,"first_name":"Shaktii","last_name":"Singh","dob":"2010-02-10","email":"sz@gmail.com","contact_no":"7503695562","gender":"male","image":""}
     */
    private boolean status;
    private String data;
    private String message;
    private SuccessDataBean success_data;

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
         * expires_in : 1579855115
         * access_token : eyJpdiI6ImlTOWNSa0dvRStwbWlHcXpGSERFMkE9PSIsInZhbHVlIjoiZEZXcVVFZmJIRzM1TkwzMTZsbDV0UWxXTElXeHZTalkyalJSa3diTlZNMWNsWlY5SWY1eGpvR0tTUG5vRVZLMWtDbDFLWjEwdGhETFVLSVpLa0haREJyQmlhNXRQUXloMFE0VWJ2cXg0N2VucmdEclZSZCt3aklMakNLUUJwS0ciLCJtYWMiOiJmNzYwZmYyZWIzYTkwNDE4NzdhOTFjNTM1MTA4M2Q4ZjMwYmNjNTZlYzI3ZDkwN2FhYjc4MTgzN2RlZGVkMTFhIn0=
         * id : 65
         * first_name : Shaktii
         * last_name : Singh
         * dob : 2010-02-10
         * email : sz@gmail.com
         * contact_no : 7503695562
         * gender : male
         * image :
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
        private String cart;

        public String getCart() {
            return cart;
        }

        public void setCart(String cart) {
            this.cart = cart;
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
