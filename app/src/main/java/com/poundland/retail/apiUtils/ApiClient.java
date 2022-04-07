package com.poundland.retail.apiUtils;



import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.poundland.retail.apiUtils.ApiRequestUrl.BASE_URL;

public class ApiClient {


    private static int REQUEST_TIMEOUT = 120;
    private static Retrofit retrofit = null;
    private static long SIZE_OF_CACHE = 10 * 1024 * 1024; // 10 MiB

    private static HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
            .setLevel(HttpLoggingInterceptor.Level.BODY);


    private static OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .connectTimeout(20, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)
            .build();

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(okHttpClient)
                    .build();
        }
        // okHttpClient.dispatcher().cancelAll();
        return retrofit;

    }


   /* private static void initOkHttp() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder OkHttpClient = new OkHttpClient().newBuilder()
                .connectTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .readTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(REQUEST_TIMEOUT, TimeUnit.SECONDS);
//        httpClient.addInterceptor(new ServiceInterceptor(BaseApp.getInstance().sharedPref().getString(SharedPref.AUTH_TOKEN)));
        OkHttpClient.addInterceptor(interceptor);
        OkHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
                original = original
                        .newBuilder()
                        .addHeader("Authorization", BaseApp.getInstance().sharedPref().getString(SharedPref.AUTH_TOKEN)==null?"":BaseApp.getInstance().sharedPref().getString(SharedPref.AUTH_TOKEN))
                        .addHeader("Accept", "application/json")
                        .addHeader("Content-Type", "application/json")
                        .build();
                return chain.proceed(original);
            }
        });
        okHttpClient = OkHttpClient.build();
    }*/
    public static void cancelAll() {
        try {
            okHttpClient.dispatcher().cancelAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
   // @Header("token") String authorization,
}