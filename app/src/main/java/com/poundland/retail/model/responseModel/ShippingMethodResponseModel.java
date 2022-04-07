package com.poundland.retail.model.responseModel;

import java.util.List;

public class ShippingMethodResponseModel {


    /**
     * status : true
     * message : Data fetch Successfully
     * shipping_details : [{"id":20,"from_day":1,"is_calendar":0,"to_day":1,"time":"21:10","amount":"3.00","type":1,"name":"Same Day Delivery"}]
     */

    private boolean status;
    private String message;
    private List<ShippingDetailsBean> shipping_details;

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

    public List<ShippingDetailsBean> getShipping_details() {
        return shipping_details;
    }

    public void setShipping_details(List<ShippingDetailsBean> shipping_details) {
        this.shipping_details = shipping_details;
    }

    public static class ShippingDetailsBean {
        /**
         * id : 20
         * from_day : 1
         * is_calendar : 0
         * to_day : 1
         * time : 21:10
         * amount : 3.00
         * type : 1
         * name : Same Day Delivery
         */

        private int id;
        private int from_day;
        private int is_calendar;
        private int to_day;
        private int hourly_delivery;
        private String time;
        private String amount;
        private int type;
        private String name;
        private boolean isActive;

        public boolean isActive() {
            return isActive;
        }

        public void setActive(boolean active) {
            isActive = active;
        }

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

        public int getIs_calendar() {
            return is_calendar;
        }

        public void setIs_calendar(int is_calendar) {
            this.is_calendar = is_calendar;
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

        public int getHourly_delivery() {
            return hourly_delivery;
        }

        public void setHourly_delivery(int hourly_delivery) {
            this.hourly_delivery = hourly_delivery;
        }
    }
}
