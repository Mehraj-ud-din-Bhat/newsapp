/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 1:05 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.MainScreen;

import android.util.Log;

import com.example.newsapp.appyHighAssignment.API.APIUtility;
import com.example.newsapp.appyHighAssignment.API.UserService;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainScreenPresenter {
    ImainScreenView view;
    String TAG = "MAIN SCREEN PRESENTER";

    public MainScreenPresenter(ImainScreenView view) {
        this.view = view;
    }

    void getTopHeadlines(String countryCode) {
        Log.e(TAG, "CODE: " + countryCode);
        UserService mUtil = APIUtility.getUserService();
        //  https://newsapi.org/v2/top-headlines?country=us&apiKey=28a87b70255143128bea8d58d836fd07

        Call<NewsArticleResponse> apiCall = mUtil.getNewsFeed(APIUtility.API_URL + "/top-headlines?country=" + countryCode + "&apiKey=" + APIUtility.API_KEY);
        // Call<NewsArticleResponse> apiCall= mUtil.getNewsFeed("https://newsapi.org/v2/top-headlines?country=us&apiKey=28a87b70255143128bea8d58d836fd07");

        apiCall.enqueue(new Callback<NewsArticleResponse>() {
            @Override
            public void onResponse(Call<NewsArticleResponse> call, Response<NewsArticleResponse> response) {
                Log.e(TAG, "RESPONSE: " + response.message());
                if (response.body() != null) {
                    view.onTopHeadlinesRecieved(response.body().getArticles());
                } else {
                    view.onError("Something went wrong");
                }

            }

            @Override
            public void onFailure(Call<NewsArticleResponse> call, Throwable t) {
                Log.e(TAG, "Error: " + t.getMessage());
                view.onError("Something went wrong!");
            }
        });


    }


}
