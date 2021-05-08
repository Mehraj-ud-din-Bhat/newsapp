/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 9:19 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 9:19 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.Notification;


import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.newsapp.appyHighAssignment.WebView.WebView;
import com.onesignal.OSNotificationAction;
import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class NotificationHandler implements OneSignal.OSNotificationOpenedHandler {

    Context context;

    public NotificationHandler(Context context) {
        this.context = context;
    }


    @Override
    public void notificationOpened(OSNotificationOpenedResult result) {
        OSNotificationAction.ActionType actionType = result.getAction().getType();
        JSONObject data = result.getNotification().getAdditionalData();
        String customKey = "";

        if (data != null) {
            customKey = data.optString("url", null);
            if (customKey != null)
                Log.e("OneSignalExample", "customkey set with value: " + customKey);
        }

        if (actionType == OSNotificationAction.ActionType.ActionTaken)
            Log.i("OneSignalExample", "Button pressed with id: " + result.getAction().getActionId());
        if (!customKey.isEmpty()) {
            Intent intent = new Intent(context, WebView.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT | Intent.FLAG_ACTIVITY_NEW_TASK);
            intent.putExtra("newsUrl", customKey);
            context.startActivity(intent);
        }
    }
}