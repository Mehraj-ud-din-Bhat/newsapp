/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 12:03 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 12:03 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.Application;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.newsapp.appyHighAssignment.Loaction.Country;
import com.google.gson.Gson;

public class ApplicationPreferences {
       Context context;

    public ApplicationPreferences(Context context) {
        this.context = context;
    }

    public   void storeLocation(Country country)
    {
        SharedPreferences sharedPreferences = context.getSharedPreferences("mPref", 0);
        SharedPreferences.Editor sharedPreferencesEditor = sharedPreferences.edit();
        final Gson gson = new Gson();
        String serializedObject = gson.toJson(country);
        sharedPreferencesEditor.putString("location", serializedObject);
        sharedPreferencesEditor.apply();

    }

 public  Country getStoredLocation()
 {
     Country country;
     SharedPreferences sharedPreferences = context.getSharedPreferences("mPref", 0);
     if (sharedPreferences.contains("location")) {
         final Gson gson = new Gson();
      return  gson.fromJson(sharedPreferences.getString("location", ""), Country.class);
     }

     return  null;
 }







}
