package com.poundland.retail.model.requestModel;

import java.util.List;

public class AddGuestRequestModel {

    /**
     * reservation_id : 532
     * customer_email : swetagupta0022@gmail.com
     * customer_name : Sweta Gupta
     * slot_id : 3114
     * is_smoking : 1
     * message_for_venue : test from app
     * guest : [{"guestfname":"A","guestlname":"AL","guestmobile":"91123456789","same_house_hold":1},{"guestfname":"A","guestlname":"AL","guestmobile":"91123456789","same_house_hold":1}]
     */

    private int reservation_id;
    private String customer_email;
    private String customer_name;
    private String slot_id;
    private int is_smoking;
    private String message_for_venue;
    private List<GuestBean> guest;


    public int getReservation_id() {
        return reservation_id;
    }

    public void setReservation_id(int reservation_id) {
        this.reservation_id = reservation_id;
    }

    public String getCustomer_email() {
        return customer_email;
    }

    public void setCustomer_email(String customer_email) {
        this.customer_email = customer_email;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getSlot_id() {
        return slot_id;
    }

    public void setSlot_id(String slot_id) {
        this.slot_id = slot_id;
    }

    public int getIs_smoking() {
        return is_smoking;
    }

    public void setIs_smoking(int is_smoking) {
        this.is_smoking = is_smoking;
    }

    public String getMessage_for_venue() {
        return message_for_venue;
    }

    public void setMessage_for_venue(String message_for_venue) {
        this.message_for_venue = message_for_venue;
    }

    public List<GuestBean> getGuest() {
        return guest;
    }

    public void setGuest(List<GuestBean> guest) {
        this.guest = guest;
    }

    public static class GuestBean {
        /**
         * guestfname : A
         * guestlname : AL
         * guestmobile : 91123456789
         * same_house_hold : 1
         */

        private String guestfname;
        private String guestlname;
        private String guestmobile;
        private int same_house_hold;

        public String getGuestfname() {
            return guestfname;
        }

        public void setGuestfname(String guestfname) {
            this.guestfname = guestfname;
        }

        public String getGuestlname() {
            return guestlname;
        }

        public void setGuestlname(String guestlname) {
            this.guestlname = guestlname;
        }

        public String getGuestmobile() {
            return guestmobile;
        }

        public void setGuestmobile(String guestmobile) {
            this.guestmobile = guestmobile;
        }

        public int getSame_house_hold() {
            return same_house_hold;
        }

        public void setSame_house_hold(int same_house_hold) {
            this.same_house_hold = same_house_hold;
        }
    }
}