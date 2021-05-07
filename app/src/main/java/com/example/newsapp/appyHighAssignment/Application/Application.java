/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 5:13 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 5:13 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.Application;

import com.example.newsapp.appyHighAssignment.Notification.NotificationHandler;
import com.google.firebase.FirebaseApp;
import com.onesignal.OneSignal;


public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);

        OneSignal.initWithContext(this);
        OneSignal.setNotificationOpenedHandler(new NotificationHandler(this));
        OneSignal.setAppId("798ead95-100d-4f25-a0f0-5d05674ff9ad");
    }
}
