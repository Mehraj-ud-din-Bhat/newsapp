/*
 * Created BY MEHRAJ UD DIN BHAT on 5/6/21 10:33 AM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/6/21 10:31 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.API;


public class APIUtility {
    //BASE URL OF NEWS API
   public static final String API_URL = "https://newsapi.org/v2/";
   public  static final String API_KEY="28a87b70255143128bea8d58d836fd07";
    private APIUtility(){
    }
        //-----------------------------------
       // Returns Retrofit Client
      // -------------------------------------
        public static UserService getUserService(){
        return RetrofitClinet.getclient(API_URL).create(UserService.class);
    }
}
