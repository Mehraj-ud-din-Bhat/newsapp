/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 12:14 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.NewsByCategory;

import android.content.Context;
import android.util.Log;

import com.example.newsapp.appyHighAssignment.API.APIUtility;
import com.example.newsapp.appyHighAssignment.API.UserService;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsByCategoryPresenter {
    final String TAG = "NEWS CATEGORY";
    InewsByCategory view;
    Context context;

    public NewsByCategoryPresenter(InewsByCategory view, Context context) {
        this.view = view;
        this.context = context;
    }

    public void newsByCategory(String countryCode, String category) {

        //Log.e(TAG, "Code: " + countryCode);

        UserService mUtil = APIUtility.getUserService();
        String url = APIUtility.API_URL + "top-headlines?country=" + countryCode + "&category=" + category + "&apiKey=" + APIUtility.API_KEY;

        // Incase categroy is CoronaVirus change url
        if (category.equalsIgnoreCase("coronavirus"))
            url = APIUtility.API_URL + "top-headlines?q=coronavirus" + "&apiKey=" + APIUtility.API_KEY;
        Call<NewsArticleResponse> apiCall = mUtil.getNewsFeed(url);
        apiCall.enqueue(new Callback<NewsArticleResponse>() {
            @Override
            public void onResponse(Call<NewsArticleResponse> call, Response<NewsArticleResponse> response) {
                Log.e(TAG, "RESPONSE: " + response.message());
                if (response.body() != null) {
                    view.onNewsRecieved(response.body().getArticles());
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
