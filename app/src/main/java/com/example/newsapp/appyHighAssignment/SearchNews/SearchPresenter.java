/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 10:15 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 10:15 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.SearchNews;

import android.content.Context;

import com.example.newsapp.appyHighAssignment.API.APIUtility;
import com.example.newsapp.appyHighAssignment.API.UserService;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchPresenter {
    ISearchNews view;
    Context context;

    public SearchPresenter(ISearchNews view, Context context) {
        this.view = view;
        this.context = context;
    }


    public void searchNews(String searchText) {

        UserService mUtil = APIUtility.getUserService();
        Call<NewsArticleResponse> apiCall = mUtil.getNewsFeed(APIUtility.API_URL + "/everything?q=" + searchText + "&apiKey=" + APIUtility.API_KEY);
        apiCall.enqueue(new Callback<NewsArticleResponse>() {
            @Override
            public void onResponse(Call<NewsArticleResponse> call, Response<NewsArticleResponse> response) {
                // Log.e(TAG,"RESPONSE: "+response.message());
                if (response.body() != null) {
                    view.onSearchResult(response.body().getArticles());
                } else {
                    view.onError("Something went wrong");
                }

            }

            @Override
            public void onFailure(Call<NewsArticleResponse> call, Throwable t) {
                // Log.e(TAG,"Error: "+t.getMessage());
                view.onError("Something went wrong!");
            }
        });

    }

}
