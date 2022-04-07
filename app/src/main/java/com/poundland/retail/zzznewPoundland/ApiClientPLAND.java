package com.poundland.retail.zzznewPoundland;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.poundland.retail.apiUtils.ApiRequestUrl;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClientPLAND {


    private static int REQUEST_TIMEOUT = 30;
    private static Retrofit retrofit = null;
    private static long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);

    static Gson gson = new GsonBuilder()
            .enableComplexMapKeySerialization()
            .serializeNulls()
            .setLenient()
            .setDateFormat("yyyy-MM-dd HH:mm:ss")
            .create();


    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(new Interceptor() {
                @NotNull
                @Override
                public Response intercept(@NotNull Chain chain) throws IOException {
                    Request.Builder ongoing = chain.request().newBuilder();
                    return chain.proceed(ongoing.build());
                }
            })
            .build();


    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(ApiRequestUrl.BASE_URL_POUNDLAND_USER)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .client(okHttpClient)
                    .build();
        }
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