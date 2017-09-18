package com.example.damian.projektgrupowy.firebase;

import android.content.Intent;

import com.example.damian.projektgrupowy.MainActivity;
import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.inverce.mod.core.Log;

/**
 * Created by Damian on 17.09.2017.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "FCM Service";
    boolean showFromNotification = false;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());
        showFromNotification = true;
        Intent i = new Intent(getBaseContext(), MainActivity.class);
        i.putExtra("isFromNotification",showFromNotification);
        startActivity(i);
    }
}
