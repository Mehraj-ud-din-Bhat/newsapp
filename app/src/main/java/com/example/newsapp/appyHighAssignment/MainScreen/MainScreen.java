/*
 * Created BY MEHRAJ UD DIN BHAT on 5/6/21 10:33 AM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/6/21 10:31 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.MainScreen;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.newsapp.appyHighAssignment.Application.ApplicationPreferences;
import com.example.newsapp.appyHighAssignment.Loaction.Country;
import com.example.newsapp.appyHighAssignment.Loaction.Ilocation;
import com.example.newsapp.appyHighAssignment.Loaction.Location;
import com.example.newsapp.appyHighAssignment.MainScreen.Adapters.NewsArticleAdapter;
import com.example.newsapp.appyHighAssignment.MainScreen.Adapters.NewsCategoryAdapter;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticle;
import com.example.newsapp.appyHighAssignment.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.material.tabs.TabLayout;
import com.neovisionaries.i18n.CountryCode;

import java.util.List;

//--------------------------------------------------------------------
// MEHRAJ UD DIN BHAT(MCA)
// AppyHigh NEWS APP ANDROID ASSIGNMENT based on MVP Design Pattern
//--------------------------------------------------------------------------

public class MainScreen extends AppCompatActivity implements ImainScreenView, Ilocation {

    RecyclerView recyclerViewnewsCategory,recyclerViewNewsArticles;
     MainScreenPresenter presenter;
     View loaderLayout;
     Location locationPresenter;
     TextView newsCategoryName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_screen);
        initViews();
        presenter=new MainScreenPresenter(this);
        locationPresenter=new Location(this,this);
        getLastLocation();


    }
//--------------------------------------------------------------------------
//METHOD USED TO CONNECT NEWS CATEGORY DASHBOARD AND SETUP RECYCLER VIEW
//----------------------------------------------------------------------------
    void initViews()
    {
        recyclerViewnewsCategory=findViewById(R.id.newsCategory);
        recyclerViewNewsArticles=findViewById(R.id.newsArticles);
        loaderLayout=findViewById(R.id.loader);
        newsCategoryName=findViewById(R.id.newsCategoryName);
        loaderLayout.setVisibility(View.VISIBLE);
        recyclerViewNewsArticles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        NewsCategoryAdapter adapter=new NewsCategoryAdapter(this);
        recyclerViewnewsCategory.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        recyclerViewnewsCategory.setAdapter(adapter);
    }


    @Override
    public void showLoader() {
        loaderLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        loaderLayout.setVisibility(View.GONE);
    }



    @Override
    public void onError(String msg) {
        hideLoader();
        Toast.makeText(this,msg,Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {

    }



    @Override
    public void getTopHeadlines(String countryCode) {

    }

    @Override
    public void onTopHeadlinesRecieved(List<NewsArticle> newsArticleList) {
        NewsArticleAdapter newsArticleAdapter=new NewsArticleAdapter(this,newsArticleList);
        recyclerViewNewsArticles.setAdapter(newsArticleAdapter);
        loaderLayout.setVisibility(View.GONE);
      // Toast.makeText(this,"Recieved Data: "+newsArticleList.size(),Toast.LENGTH_LONG).show();
    }


    @Override
    public void onLoactionRecieved(Country country) {

        if(country.name.isEmpty() && country.code.isEmpty())
        {
            //SET A DEFAULT COUNTRY BECAUSE DEVICE NOT WAS ABLE TO GET COUNTRY NAME
            //ASK USER TO SELECT LOCATION


        }

        if(country.name.isEmpty() && country.code.length()>0)
        {
            CountryCode cc = CountryCode.getByCodeIgnoreCase(country.code);
            country.name=cc.getName();
        }

        //STORE LOCATION FOR USE IN OTHE ACTIVITIES
        ApplicationPreferences preferences=new ApplicationPreferences(this);
        preferences.storeLocation(country);
        newsCategoryName.setText("Headlines -"+country.name);
        presenter.getTopHeadlines(country.code.toLowerCase());

    }

    void getLastLocation() {
        // check if permissions are given
        if (checkPermissions()) {

            // check if location is enabled
            if (isLocationEnabled()) {
                locationPresenter.detectLoaction();

            } else {
                Toast.makeText(this, "Please turn on" + " your location...", Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        } else {
            // if permissions aren't available,
            // request for permissions
            requestPermissions();
        }

    }



    //---------------------------------------------
    //PERMISSIONS AREA FOR LOACTION DETECTION
    //-----------------------------------------------
    int PERMISSION_ID = 44;
    // method to check for permissions
    private boolean checkPermissions() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;


    }

    // method to request for permissions
    private void requestPermissions() {
        ActivityCompat.requestPermissions(this, new String[]{
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION}, PERMISSION_ID);
    }

    // method to check
    // if location is enabled
    private boolean isLocationEnabled() {
        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
    }

    // If everything is alright then
    @Override
    public void
    onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_ID) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getLastLocation();
            }
        }
    }
}