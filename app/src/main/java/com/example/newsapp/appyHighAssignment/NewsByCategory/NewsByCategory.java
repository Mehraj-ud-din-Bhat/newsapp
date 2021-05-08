/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 3:59 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.NewsByCategory;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.appyHighAssignment.Adapters.NewsArticleAdapter;
import com.example.newsapp.appyHighAssignment.Application.ApplicationPreferences;
import com.example.newsapp.appyHighAssignment.Location.Country;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticle;
import com.example.newsapp.appyHighAssignment.R;

import java.util.ArrayList;
import java.util.List;

public class NewsByCategory extends AppCompatActivity implements InewsByCategory {

    TextView categoryName;
    ImageView backButton;
    View loading_layout;
    RecyclerView recyclerViewNewsArticles;
    NewsByCategoryPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_by_category);
        presenter = new NewsByCategoryPresenter(this, this);
        initViews();
        String category = getIntent().getStringExtra("catName");
        getNewsByCategory(category);
        categoryName.setText(category);
    }


    private void initViews() {
        categoryName = findViewById(R.id.categoryName);
        loading_layout = findViewById(R.id.loader);
        backButton = findViewById(R.id.backIcon);
        recyclerViewNewsArticles = findViewById(R.id.newsArticles);
        recyclerViewNewsArticles.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }


    @Override
    public void getNewsByCategory(String category) {
        showLoader();
        ApplicationPreferences preferences = new ApplicationPreferences(this);
        Country country = preferences.getStoredLocation();
        presenter.newsByCategory(country.code.toLowerCase(), category);
    }

    @Override
    public void onNewsRecieved(List<NewsArticle> list) {
        hideLoader();
        List<Object> newsList = new ArrayList<>(list);
        NewsArticleAdapter adapter = new NewsArticleAdapter(this, newsList);
        recyclerViewNewsArticles.setAdapter(adapter);
    }

    @Override
    public void showLoader() {
        loading_layout.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoader() {
        loading_layout.setVisibility(View.GONE);
    }


    @Override
    public void onError(String msg) {
        hideLoader();
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onSuccess() {

    }


}