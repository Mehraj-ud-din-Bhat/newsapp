/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 10:38 AM
 * /
 */

package com.example.newsapp.appyHighAssignment.WebView;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;

import com.example.newsapp.appyHighAssignment.R;

public class WebView extends AppCompatActivity {
    android.webkit.WebView webView;
    View loader_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);
        String url=getIntent().getStringExtra("newsUrl");
        webView=findViewById(R.id.webview);
        loader_layout=findViewById(R.id.loader_layout);
        loadWebView(url);

    }

    void  loadWebView(String url)
    {
        webView.setVisibility(View.INVISIBLE);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebChromeClient(new WebChromeClient() {
                    public void onProgressChanged(android.webkit.WebView view, int progress) {
                        if (progress == 100) {
                            //do your task
                            webView.setVisibility(View.VISIBLE);
                            loader_layout.setVisibility(View.GONE);
                        }
                    }
                });

        webView.loadUrl(url);
    }


}