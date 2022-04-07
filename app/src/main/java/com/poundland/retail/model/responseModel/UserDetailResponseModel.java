package com.poundland.retail.model.responseModel;

public class UserDetailResponseModel {


    /**
     * status : true
     * message : Valid User.
     * user : {"id":5,"first_name":"Vinit","last_name":"Mishra","email":"mvinit44@gmail.com","password":"$2y$10$d1spfuZ3jeGLAXA/lT1jt.WjoD4ydiGGT/1Fu1Ek77lDwUKdw2vHq","dob":null,"contact_no":"","reff_code":"","gender":"","remember_token":"","api_token":"91c0665f2d87dbdbb0296b625f045eb6571e565abdd0936438ac04b1ad221091","login_time":"2019-12-11 07:14:11","social_type":"google","social_token":"ya29.Il-xB0lS75G52YRBrp9NE8CXgPABX2YJhk-lzVN0CEb0xaIyKJNuNkeDcPUarBZ4cXGo011kjMsxd02FIgNT8A6dZLox1GNwFIQVpXmUYfGmUYM_dtgKdbOo5T81Yb53nQ","device_type":"","custmer_type":"ecom","device_id":"","profile_image":"https://lh3.googleusercontent.com/a-/AAuE7mA2AaBPE-CPUP9s0_l8WXUCJJaY0HivOD1rQsRj","pos_cus_id":0,"status":1,"created_at":"2019-11-05 09:42:16","updated_at":"2019-12-11 07:14:11"}
     */

    private boolean status;
    private String message;
    private UserBean user;

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

    public UserBean getUser() {
        return user;
    }

    public void setUser(UserBean user) {
        this.user = user;
    }

    public static class UserBean {
        /**
         * id : 5
         * first_name : Vinit
         * last_name : Mishra
         * email : mvinit44@gmail.com
         * password : $2y$10$d1spfuZ3jeGLAXA/lT1jt.WjoD4ydiGGT/1Fu1Ek77lDwUKdw2vHq
         * dob : null
         * contact_no :
         * reff_code :
         * gender :
         * remember_token :
         * api_token : 91c0665f2d87dbdbb0296b625f045eb6571e565abdd0936438ac04b1ad221091
         * login_time : 2019-12-11 07:14:11
         * social_type : google
         * social_token : ya29.Il-xB0lS75G52YRBrp9NE8CXgPABX2YJhk-lzVN0CEb0xaIyKJNuNkeDcPUarBZ4cXGo011kjMsxd02FIgNT8A6dZLox1GNwFIQVpXmUYfGmUYM_dtgKdbOo5T81Yb53nQ
         * device_type :
         * custmer_type : ecom
         * device_id :
         * profile_image : https://lh3.googleusercontent.com/a-/AAuE7mA2AaBPE-CPUP9s0_l8WXUCJJaY0HivOD1rQsRj
         * pos_cus_id : 0
         * status : 1
         * created_at : 2019-11-05 09:42:16
         * updated_at : 2019-12-11 07:14:11
         */

        private int id;
        private String first_name;
        private String last_name;
        private String email;
        private String password;
        private String dob;
        private String contact_no;
        private String reff_code;
        private String gender;
        private String remember_token;
        private String api_token;
        private String login_time;
        private String social_type;
        private String social_token;
        private String device_type;
        private String custmer_type;
        private String device_id;
        private String profile_image;
        private double pos_cus_id;
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

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
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

        public String getReff_code() {
            return reff_code;
        }

        public void setReff_code(String reff_code) {
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

        public String getProfile_image() {
            return profile_image;
        }

        public void setProfile_image(String profile_image) {
            this.profile_image = profile_image;
        }

        public double getPos_cus_id() {
            return pos_cus_id;
        }

        public void setPos_cus_id(double pos_cus_id) {
            this.pos_cus_id = pos_cus_id;
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
