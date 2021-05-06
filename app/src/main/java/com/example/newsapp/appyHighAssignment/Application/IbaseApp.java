/*
 * Created BY MEHRAJ UD DIN BHAT on 5/6/21 10:33 AM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/6/21 10:31 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.Application;


//----------------------------------------------------------------------------------------------
//ALL THE APPLICATION VIEW INTERFACES WILL EXTEND THIS INTERFACE: TO IMPLEMENT THE Common METHODS
//---------------------------------------------------------------------------------------------

public interface IbaseApp {
    void showLoader();
    void hideLoader();
    void showLoader(String msg);
    void showDialog(String msg);
    void hideDialog();
    void onError(String msg);
    void onSuccess();
    void onSuccess(String msg);
}
