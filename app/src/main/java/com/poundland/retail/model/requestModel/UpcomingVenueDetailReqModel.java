package com.poundland.retail.model.requestModel;

public class UpcomingVenueDetailReqModel {

    private String venue_id;
    private String venue_name;
    private String lat;
    private String log;
    //"venue_id" => "ICELAND"   "lat" => "26.8467088"   "log" => "80.9461592"

    public UpcomingVenueDetailReqModel(String venue_name,String venue_id, String lat, String log) {
        this.venue_id = venue_id;
        this.lat = lat;
        this.log = log;
        this.venue_name = venue_name;
    }

    public String getVenue_name() {
        return venue_name;
    }

    public void setVenue_name(String venue_name) {
        this.venue_name = venue_name;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLog() {
        return log;
    }

    public void setLog(String log) {
        this.log = log;
    }
}
