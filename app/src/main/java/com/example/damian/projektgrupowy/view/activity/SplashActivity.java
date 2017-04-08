package com.example.damian.projektgrupowy.view.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.damian.projektgrupowy.core.BaseActivity;

/**
 * Created by Damian on 08.04.2017.
 */

public class SplashActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startActivity(intent(PhoneActivity.class));
        finish();
    }

    private Intent intent(Class<? extends Activity> clazz) {
        Intent intent = new Intent(this, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        return intent;
    }
}
