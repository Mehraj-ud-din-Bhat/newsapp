/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 5:13 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 5:13 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.Application;

import com.google.firebase.FirebaseApp;

public class Application extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
