/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 10:53 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 10:53 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.Location.ManualLoactonSelection;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.newsapp.appyHighAssignment.Location.Country;
import com.example.newsapp.appyHighAssignment.Location.Ilocation;
import com.example.newsapp.appyHighAssignment.R;
import com.hbb20.CountryCodePicker;
import com.neovisionaries.i18n.CountryCode;

import java.util.Arrays;
import java.util.List;

public class LocationSelectionDialog {
    Context context;
    Ilocation mview;
    TextView tx;
    List<CountryCode> cc;

    public LocationSelectionDialog(Context context, Ilocation view) {
        this.context = context;
        this.mview = view;
        cc = Arrays.asList(CountryCode.values());
    }


    public void showDialog() {
        Country country = new Country();
        LayoutInflater inflater = LayoutInflater.from(context);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(context);
        View view = inflater.inflate(R.layout.loaction_selection_dialog, null);
        CountryCodePicker cc = view.findViewById(R.id.ccp);
        dialogBuilder.setView(view);
        AlertDialog alertDialog = dialogBuilder.create();
        cc.setOnCountryChangeListener(new CountryCodePicker.OnCountryChangeListener() {
            @Override
            public void onCountrySelected() {
                country.name = cc.getSelectedCountryName();
                country.code = cc.getSelectedCountryNameCode();
                mview.onLoactionRecieved(country);
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }


}
