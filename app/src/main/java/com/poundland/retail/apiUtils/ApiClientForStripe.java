package com.poundland.retail.apiUtils;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.poundland.retail.apiUtils.ApiRequestUrl.BASE_URL_STRIPE_ADD;

public class ApiClientForStripe {


    private static int REQUEST_TIMEOUT = 120;
    private static Retrofit retrofit = null;
    private static long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);
    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .build();

    public static Retrofit getClientForStripe() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL_STRIPE_ADD)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        // okHttpClient.dispatcher().cancelAll();
        return retrofit;

    }
    public static void cancelAll() {
        try {
            okHttpClient.dispatcher().cancelAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}