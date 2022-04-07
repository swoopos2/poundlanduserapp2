package com.poundland.retail.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.poundland.retail.notificationService.Config;

public abstract class BaseActivity extends AppCompatActivity {
    private NotificationReceiver notificationReceiver;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResourceId());
        notificationReceiver = new NotificationReceiver();

        registerReceiver(notificationReceiver, new IntentFilter(Config.NEW_NOTIFICATION));
        registerReceiver(notificationReceiver, new IntentFilter(Config.PUSH_NOTIFICATION));
      //  registerReceiver(ConnectivityReceiver, new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION));

        LocalBroadcastManager.getInstance(this).registerReceiver(/*new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String message = intent.getStringExtra("foo");
                onNotificationReceived(intent);
            }
        },*/notificationReceiver, new IntentFilter(Config.PUSH_NOTIFICATION));
    }

    @Override
    protected void onStart() {
        super.onStart();

        registerReceiver(notificationReceiver, new IntentFilter(Config.NEW_NOTIFICATION));
        registerReceiver(notificationReceiver, new IntentFilter(Config.PUSH_NOTIFICATION));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(notificationReceiver);
        LocalBroadcastManager.getInstance(this).unregisterReceiver(notificationReceiver);
    }

    protected abstract int getLayoutResourceId();

    protected abstract void onNotificationReceived(Intent intent);

    public class NotificationReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            onNotificationReceived(intent);

           /* ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = conn.getActiveNetworkInfo();

            if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {

            } else if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_ETHERNET) {

            } else if (networkInfo != null && networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {

            }*/
        }
    }
}