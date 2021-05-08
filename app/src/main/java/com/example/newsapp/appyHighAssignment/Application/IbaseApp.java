/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 11:34 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.Application;


//----------------------------------------------------------------------------------------------
//ALL THE APPLICATION VIEW INTERFACES WILL EXTEND THIS INTERFACE: TO IMPLEMENT THE Common METHODS
//---------------------------------------------------------------------------------------------

public interface IbaseApp {
    void showLoader();

    void hideLoader();

    void onError(String msg);

    void onSuccess();
}
