package com.example.damian.projektgrupowy;

import android.app.Application;
import android.content.Context;

/**
 * Created by Damian on 08.04.2017.
 */

public class App extends Application {

    private static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getAppContext() {
        return mContext;
    }
}