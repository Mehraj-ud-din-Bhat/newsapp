/*
 * Created BY MEHRAJ UD DIN BHAT on 5/6/21 10:33 AM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/6/21 10:31 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.API;

import com.example.newsapp.appyHighAssignment.Modals.NewsArticle;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticleResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

import retrofit2.http.Url;
//------------------------------------------------------
//Contains All API Methods
//-------------------------------------------------------


public interface UserService {
   //---------------------
   //GET THE NEWS  FEED
   //------------------------
   @GET
   Call<NewsArticleResponse> getNewsFeed(@Url String url);
}

