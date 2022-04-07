package com.poundland.retail.notificationService;

public class NotificationModel {
    /*{  "end_date": "2020-04-23",
   "image": "special_offer\/2020242.jpg",
   "special_offer_id": 2,
   "distance": 1000000,
   "data": "Best offer of the day",
   "login_device_type": "ANDROID",
   "latitude": "52.586973",
   "end_time": "23:55:00",
   "type": 2,
   "title": "Saturday special",
   "message": "Best offer of the day",
   "token": "Q3rz91G7U7EwAtcCXVYBH",
   "start_time": "00:00:00",
   "venue_id": "2020040808100815",
   "longitude": "-2.12882",
   "start_date": "2020-04-11"
 }*/


    private double distance;
    private String data;
    private double latitude;
    private String title;
    private String message;
    private String venue_id;
    private double longitude;
    private String token;
    private String end_date;
    private String image;
    private String login_device_type;
    private String end_time;
    private int type;
    private String start_time;
    private String start_date;
    private String special_offer_id;

    public String getSpecial_offer_id() {
        return special_offer_id;
    }

    public void setSpecial_offer_id(String special_offer_id) {
        this.special_offer_id = special_offer_id;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLogin_device_type() {
        return login_device_type;
    }

    public void setLogin_device_type(String login_device_type) {
        this.login_device_type = login_device_type;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStart_time() {
        return start_time;
    }

    public void setStart_time(String start_time) {
        this.start_time = start_time;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(String venue_id) {
        this.venue_id = venue_id;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
