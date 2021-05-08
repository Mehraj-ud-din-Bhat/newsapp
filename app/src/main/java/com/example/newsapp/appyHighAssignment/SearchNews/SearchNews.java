/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 1:30 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.SearchNews;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.appyHighAssignment.Adapters.NewsArticleAdapter;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticle;
import com.example.newsapp.appyHighAssignment.R;

import java.util.ArrayList;
import java.util.List;

public class SearchNews extends AppCompatActivity implements ISearchNews {
    EditText etSearch;
    Button searchButton;
    SearchPresenter presenter;
    RecyclerView rvNewsArticles;
    RelativeLayout loader;
    RelativeLayout toolBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);
        etSearch = findViewById(R.id.search_EditText);
        searchButton = findViewById(R.id.search_button);
        presenter = new SearchPresenter(this, this);
        rvNewsArticles = findViewById(R.id.rvSearchNewsArticles);
        loader = findViewById(R.id.loadingBar);
        loader.setVisibility(View.GONE);
        toolBar = findViewById(R.id.toolBarRootConatiner);
        rvNewsArticles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (etSearch.getText().toString().isEmpty()) {
                    etSearch.setError("required");
                    return;
                }

                showLoader();

                presenter.searchNews(etSearch.getText().toString());

            }
        });

    }

    @Override
    public void onSearchResult(List<NewsArticle> list) {
        hideLoader();
        if (list.size() <= 0) {
            onError("Nothing found..");
            return;
        }

        List<Object> newsList = new ArrayList<>(list);
        NewsArticleAdapter adapter = new NewsArticleAdapter(this, newsList);
        rvNewsArticles.setAdapter(adapter);

    }

    @Override
    public void showLoader() {
        rvNewsArticles.setVisibility(View.GONE);
        searchButton.setEnabled(false);
        loader.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        rvNewsArticles.setVisibility(View.VISIBLE);
        searchButton.setEnabled(true);
        loader.setVisibility(View.GONE);
    }

    @Override
    public void onError(String msg) {
        findViewById(R.id.nothingFoundView).setVisibility(View.VISIBLE);
        TextView tv = findViewById(R.id.nothingFoundText);
        tv.setText(msg);
    }

    @Override
    public void onSuccess() {

    }
}