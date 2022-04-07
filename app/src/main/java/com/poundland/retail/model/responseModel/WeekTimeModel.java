package com.poundland.retail.model.responseModel;

import java.util.List;

public class WeekTimeModel {


    private List<VenueWeekTimesBean> venue_week_times;


    public List<VenueWeekTimesBean> getVenue_week_times() {
        return venue_week_times;
    }

    public void setVenue_week_times(List<VenueWeekTimesBean> venue_week_times) {
        this.venue_week_times = venue_week_times;
    }


    public static class VenueWeekTimesBean {
        /**
         * day_name : Monday
         * opening_time : 2021-03-02 08:00
         * closing_time : 2021-03-02 22:00
         */

        private String day_name;
        private String opening_time;
        private String closing_time;

        public String getDay_name() {
            return day_name;
        }

        public void setDay_name(String day_name) {
            this.day_name = day_name;
        }

        public String getOpening_time() {
            return opening_time;
        }

        public void setOpening_time(String opening_time) {
            this.opening_time = opening_time;
        }

        public String getClosing_time() {
            return closing_time;
        }

        public void setClosing_time(String closing_time) {
            this.closing_time = closing_time;
        }
    }

}
