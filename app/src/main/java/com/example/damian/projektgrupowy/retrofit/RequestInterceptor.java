package com.example.damian.projektgrupowy.retrofit;

import android.annotation.SuppressLint;
import android.util.Log;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Damian on 12.09.2017.
 */

public class RequestInterceptor implements okhttp3.Interceptor {

    public static final String APP_ID="com.example.damian.projektgrupowy";
    public static final String HOST = "api.myjson.com";
    public static final String MD5_PASSWORD = toMD5("pass");
    public static String generated = "";


    private static String date() {
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("UTC"));
        @SuppressLint("SimpleDateFormat")
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(cal.getTime());
    }
    @Override
    public Response intercept(Chain chain) throws IOException {
        okhttp3.HttpUrl url=chain.request().url();
        if(url.host().equals(HOST)){
            Request newRequest=chain.request().newBuilder()
                    .addHeader("app-id", APP_ID)
                    .addHeader("token",toMD5(MD5_PASSWORD + "+" + date()))
                    .build();
            generated = toMD5(MD5_PASSWORD + "+" + date());
            Log.d("Token: ", generated);

            return chain.proceed(newRequest);
        }
        else {
            Log.d("Token: ", generated);
            return chain.proceed(chain.request());
        }
    }
    public static String toMD5(String toEncode) {
        try {
            byte[] hash = MessageDigest.getInstance("MD5").digest(toEncode.getBytes("UTF-8"));

            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        } catch (NoSuchAlgorithmException e) {
        } catch (UnsupportedEncodingException e) {
        }
        return "";
    }
    public static String getAppId() {
        return APP_ID;
    }

    public static String getHOST() {
        return HOST;
    }

    public static String getMd5Password() {
        return MD5_PASSWORD;
    }

    public static String getGenerated() {
        return generated;
    }

    public static void setGenerated(String generated) {
        // RequestInterceptor.generated = generated;
    }
}