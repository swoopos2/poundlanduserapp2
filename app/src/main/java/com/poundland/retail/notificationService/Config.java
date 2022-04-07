package com.poundland.retail.notificationService;

public class Config {
    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";
    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;


    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";




    /*
     * NOTIFICATION DATA
     * */
    public static final  String NEW_NOTIFICATION = "newNotification";
   public static final  String NEW_ORDER = "newOrder";
   public static final  String FIREBASE_TOKEN = "firebaseToken";
   public static final  String IS_FIREBASE_TOKEN_UPDATE = "firebaseTokenUpdated";

}
