/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 1:07 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.Location;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.Looper;
import android.telephony.TelephonyManager;
import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.util.List;
import java.util.Locale;

public class Location {
    Ilocation view;
    FusedLocationProviderClient mFusedLocationClient;
    Context context;
    private LocationCallback mLocationCallback = new LocationCallback() {

        @Override
        public void onLocationResult(LocationResult locationResult) {
            android.location.Location mLastLocation = locationResult.getLastLocation();
            // Loaction is Available
            Country location = getCountryCode(mLastLocation.getLatitude(), mLastLocation.getLongitude());
            // Log.e("Loca","Loaction: "+location.name);
            view.onLoactionRecieved(location);
        }
    };


    public Location(Ilocation view, Context context) {
        this.view = view;
        this.context = context;
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
    }

    public void detectLoaction() {
        @SuppressLint("MissingPermission")
        Task<android.location.Location> locationTask = mFusedLocationClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<android.location.Location>() {
            @Override
            public void onComplete(@NonNull Task<android.location.Location> task) {
                android.location.Location mLastLocation = task.getResult();
                if (mLastLocation == null) {
                    requestNewLocationData();
                } else {
                    //Loaction is Available
                    Country location = getCountryCode(mLastLocation.getLatitude(), mLastLocation.getLongitude());
                    Log.e("Loca", "Loaction: " + location.code);
                    view.onLoactionRecieved(location);
                }
            }
        });
        return;


    }

    @SuppressLint("MissingPermission")
    private void requestNewLocationData() {

        // Initializing LocationRequest
        // object with appropriate methods
        LocationRequest mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(5);
        mLocationRequest.setFastestInterval(0);
        mLocationRequest.setNumUpdates(1);

        // setting LocationRequest
        // on FusedLocationClient
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(context);
        mFusedLocationClient.requestLocationUpdates(mLocationRequest, mLocationCallback, Looper.myLooper());
    }

    private Country getCountryCode(double LATITUDE, double LONGITUDE) {
        Country country = new Country();
        //  Log.e("LOC", "LAT: "+LATITUDE+"LONG: "+LONGITUDE);
        Geocoder geocoder = new Geocoder(context, Locale.getDefault());
        try {
            List<Address> addresses = geocoder.getFromLocation(LATITUDE, LONGITUDE, 1);
            country.code = addresses.get(0).getCountryCode().toLowerCase();
            country.name = addresses.get(0).getCountryName();


        } catch (Exception e) {
            e.printStackTrace();

        }
        if (country.name.isEmpty()) {
            country = getUserCountry(context);
        }

        return country;
    }


    public Country getUserCountry(Context context) {
        String code = "";
        try {

            final TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
            final String simCountry = tm.getSimCountryIso();
            if (simCountry != null && simCountry.length() == 2) {
                // SIM country code is available
                code = simCountry.toLowerCase(Locale.US);
            } else if (tm.getPhoneType() != TelephonyManager.PHONE_TYPE_CDMA) {
                // device is not 3G (would be unreliable)
                String networkCountry = tm.getNetworkCountryIso();
                if (networkCountry != null && networkCountry.length() == 2) {
                    // network country code is available
                    code = networkCountry.toLowerCase(Locale.US);
                }
            }
        } catch (Exception e) {
        }
        Country country = new Country();
        country.code = code;
        return country;
    }
}
