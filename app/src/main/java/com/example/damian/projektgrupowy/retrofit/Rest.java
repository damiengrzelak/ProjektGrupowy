package com.example.damian.projektgrupowy.retrofit;

import com.google.gson.Gson;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Damian on 12.09.2017.
 */

public class Rest {
    public static final String SERVER = "https://api.myjson.com/";
    private static Gson gson;
    private static OkHttpClient okHttpClient;
    private static AplicationInterface appService;

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static Gson getGson() {
        return gson;
    }

    public static AplicationInterface getAppService() {
        return appService;
    }
    public Rest(){}

    public static void init(){
        HttpLoggingInterceptor loggingInterceptor=new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        okHttpClient=new OkHttpClient.Builder()
                .addInterceptor(new RequestInterceptor())
                .addInterceptor(loggingInterceptor)
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30,TimeUnit.SECONDS)
                .build();

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl(SERVER)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
        appService =retrofit.create(AplicationInterface.class);

        gson=new Gson();
    }
}
