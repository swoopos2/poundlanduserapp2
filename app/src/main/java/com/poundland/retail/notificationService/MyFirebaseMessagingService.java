package com.poundland.retail.notificationService;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;

import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.google.gson.Gson;
import com.poundland.retail.activity.MainActivity;
import com.poundland.retail.appUtils.PrefManager;

import java.util.Map;

import static com.poundland.retail.appUtils.HelperClass.log;

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    NotificationModel notificationModel;
    NotificationDiscount_OfferModel discount_offerModel;
    private NotificationUtils notificationUtils;
    private PrefManager prefManager;
    private static final String TAG = MyFirebaseMessagingService.class.getSimpleName();


    @Override
    public void onCreate() {
        super.onCreate();
        prefManager = PrefManager.getInstance(this);
    }


    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ
        Log.d(TAG, "Notification Message From: " + remoteMessage.getFrom());
        if (remoteMessage == null)
            return;
        android.os.Debug.waitForDebugger();

        if (remoteMessage.getNotification() != null) {
            Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        }

        if (remoteMessage.getData().size() > 0) {// data payload.
            try {
                handleDataMessage(remoteMessage.getData());
            } catch (Exception e) {
                Log.e(TAG, "Notification Exception: " + e.getMessage());
            }
        } else if (remoteMessage.getNotification() != null) {      // notification payload.
            Log.e(TAG, "Notification Simple Body: " + remoteMessage.getNotification().getBody());
            handleNotification(remoteMessage.getNotification().getBody());
        }
    }


    @Override
    public void onDeletedMessages() {
        super.onDeletedMessages();
        log("TAG_NOTIFICATION", "onDeletedMessages");
    }


    private void handleNotification(String message) {
        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            Intent pushNotification = new Intent(Config.PUSH_NOTIFICATION);
            pushNotification.putExtra("message", message);
            pushNotification.setAction(Config.NEW_NOTIFICATION);

            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
           /* NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();*/
        } else {
            // If the app is in background, firebase itself handles the notification
        }
    }


    private void handleDataMessage(Map<String, String> data) {
        Log.e(TAG, "Notification data payload: " + data.toString());

        String dataJson = data.get("data");
        String type = data.get("type");
        String title = data.get("title");
        String message = data.get("message");
        String timestamp = data.get("timestamp");
        String imageUrl = null;
        NotificationModel notificationModel = new Gson().fromJson(dataJson, NotificationModel.class);
       // if (notificationModel != null && !TextUtils.isEmpty(notificationModel.getImage()))
           // imageUrl = notificationModel.getImage();
        Intent pushNotification = null;
        pushNotification = new Intent(Config.PUSH_NOTIFICATION);

        pushNotification.putExtra("title", title);
        pushNotification.putExtra("message", message);
        pushNotification.putExtra("image", imageUrl);
        pushNotification.putExtra("data", dataJson);
        pushNotification.putExtra("timestamp", timestamp);
        pushNotification.putExtra("type", type);



        if (!NotificationUtils.isAppIsInBackground(getApplicationContext())) {
            // app is in foreground, broadcast the push message
            pushNotification.setAction(Config.NEW_NOTIFICATION);

            LocalBroadcastManager.getInstance(this).sendBroadcast(pushNotification);

            // play notification sound
           /* NotificationUtils notificationUtils = new NotificationUtils(getApplicationContext());
            notificationUtils.playNotificationSound();*/
        }

        createAndShowNotification(title, message, imageUrl, timestamp, true);

    }


    private void createAndShowNotification(String title, String message, String imageUrl, String timestamp, boolean passActivity) {
        Intent resultIntent = null;
        if (passActivity) {
            resultIntent = new Intent(getApplicationContext(), MainActivity.class);
        } else {
            resultIntent = new Intent();
        }
        resultIntent.putExtra("title", title);
        resultIntent.putExtra("message", message);

        if (imageUrl == null || TextUtils.isEmpty(imageUrl)) {
            showNotificationMessage(getApplicationContext(), title, message, timestamp, resultIntent);
        } else {
            showNotificationMessageWithBigImage(getApplicationContext(), title, message, timestamp, resultIntent, imageUrl);
        }
    }

    /**   * Showing notification with text only   */
    private void showNotificationMessage(Context context, String title, String message, String timeStamp, Intent intent) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent,null);
        Log.e(TAG, "Notification data : No image" );
    }

    /**  * Showing notification with text and image */
    private void showNotificationMessageWithBigImage(Context context, String title, String message, String timeStamp, Intent intent, String imageUrl) {
        notificationUtils = new NotificationUtils(context);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        notificationUtils.showNotificationMessage(title, message, timeStamp, intent, imageUrl);
        Log.e(TAG, "Notification data : with image" );
    }


    @Override
    public void onNewToken(String token) {
        Log.d(TAG, "Refreshed token: " + token);
        FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);
    }
}
