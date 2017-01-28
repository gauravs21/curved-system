package com.example.kopite.curvedactionbar;


import android.os.Debug;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;
import java.util.Objects;

/**
 * Created by kopite on 13/1/17.
 */

public class MyFirebaseMessagingService extends FirebaseMessagingService{
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        super.onMessageReceived(remoteMessage);
        Log.d("DB","message received "+remoteMessage.toString());
        Map data=remoteMessage.getData();
        Log.d("DB",data.toString());
        JSONObject jsonObject=new JSONObject(data);
        try {
            String value=jsonObject.getString("singer");
            Log.d("DB",value+ " "+ jsonObject.toString());
        } catch (JSONException e) {
            e.printStackTrace();
            Log.d("DB",e.getMessage());
        }

    }
}
