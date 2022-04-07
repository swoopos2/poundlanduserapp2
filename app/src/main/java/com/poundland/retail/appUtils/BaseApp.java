package com.poundland.retail.appUtils;

import android.app.Application;

import com.poundland.retail.R;
import com.stripe.android.PaymentConfiguration;
import com.poundland.retail.apiUtils.ApiRequestUrl;

public class BaseApp extends Application {
    private static BaseApp mInstance;

    public static synchronized BaseApp getInstance() {
        if (mInstance == null) {
            mInstance = new BaseApp();
        }
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
//        if (Build.PRODUCT)
        /*Stetho.initializeWithDefaults(this);*/
       /* IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(connectivityRecevier, intentFilter);*/

       // Stripe.setAdvancedFraudSignalsEnabled(false);


       if (ApiRequestUrl.BASE_URL.equals("https://swoopelocaltesting.com/admin/public/")){
           PaymentConfiguration.init(
                   getApplicationContext(), getString(R.string.strip_publish_key_testing));
       }else {
           PaymentConfiguration.init(
                   getApplicationContext(), getString(R.string.strip_publish_key));
       }

    }
   }
