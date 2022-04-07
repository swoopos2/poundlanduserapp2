package com.poundland.retail.notificationService;

import android.content.Context;

public class NotificationManager {

    private Context mCtx;
    private static NotificationManager mInstance;

    private NotificationManager(Context context) {
        mCtx = context;
    }

    public static synchronized NotificationManager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new NotificationManager(context);
        }
        return mInstance;
    }

    /*public void displayNotification(String title, String body) {

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(mCtx, Constants.CHANNEL_ID)
                        .setSmallIcon(R.drawable.app_icon)
                        .setContentTitle(title)
                        .setContentText(body);


        *//*
         *  Clicking on the notification will take us to this intent
         *  Right now we are using the MainActivity as this is the only activity we have in our application
         *  But for your project you can customize it as you want
         * *//*

        Intent resultIntent = new Intent(mCtx, MainActivity.class);

        *//*
         *  Now we will create a pending intent
         *  The method getActivity is taking 4 parameters
         *  All paramters are describing themselves
         *  0 is the request code (the second parameter)
         *  We can detect this code in the activity that will open by this we can get
         *  Which notification opened the activity
         * *//*
        PendingIntent pendingIntent = PendingIntent.getActivity(mCtx, 0, resultIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        *//*
         *  Setting the pending intent to notification builder
         * *//*

        mBuilder.setContentIntent(pendingIntent);

        NotificationManager mNotifyMgr =
                (NotificationManager) mCtx.getSystemService(NOTIFICATION_SERVICE);

        *//*
         * The first parameter is the notification id
         * better don't give a literal here (right now we are giving a int literal)
         * because using this id we can modify it later
         * *//*
        if (mNotifyMgr != null) {
            mNotifyMgr.notify(1, mBuilder.build());
        }
    }*/


}
