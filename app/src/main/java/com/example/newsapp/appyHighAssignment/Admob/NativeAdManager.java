/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 3:59 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.Admob;

import android.content.Context;

import com.example.newsapp.appyHighAssignment.Adapters.NewsArticleAdapter;
import com.example.newsapp.appyHighAssignment.R;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdLoader;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.formats.UnifiedNativeAd;

import java.util.ArrayList;
import java.util.List;

//---------------------------------------
//THIS CLASS DEALS WITH ADMOB NATIVE ADS
//--------------------------------------
public class NativeAdManager {
    Context context;
    int NUMBER_OF_ADS;
    List<Object> newsArticles;
    List<UnifiedNativeAd> nativeAds;
    NewsArticleAdapter adapter;
    private AdLoader adLoader;

    public NativeAdManager(Context context, int NUMBER_OF_ADS, List<Object> newsArticles, NewsArticleAdapter articleAdapter) {
        this.context = context;
        this.NUMBER_OF_ADS = NUMBER_OF_ADS;
        this.newsArticles = newsArticles;
        nativeAds = new ArrayList<>();
        this.adapter = articleAdapter;
        loadNativeAds();
    }

    private void loadNativeAds() {


        AdLoader.Builder builder = new AdLoader.Builder(context, context.getString(R.string.native_test_ad));
        adLoader = builder.forUnifiedNativeAd(
                new UnifiedNativeAd.OnUnifiedNativeAdLoadedListener() {
                    @Override
                    public void onUnifiedNativeAdLoaded(UnifiedNativeAd unifiedNativeAd) {
                        // A native ad loaded successfully, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.
                        nativeAds.add(unifiedNativeAd);
                        if (!adLoader.isLoading()) {
                            insertNativeAdsInNewsArticles();
                        }
                    }
                }).withAdListener(
                new AdListener() {
                    @Override
                    public void onAdFailedToLoad(int errorCode) {
                        // A native ad failed to load, check if the ad loader has finished loading
                        // and if so, insert the ads into the list.

                        if (!adLoader.isLoading()) {
                            insertNativeAdsInNewsArticles();
                        }
                    }
                }).build();

        // Load the Native ads.
        adLoader.loadAds(new AdRequest.Builder().build(), NUMBER_OF_ADS);
    }


    void insertNativeAdsInNewsArticles() {
        if (nativeAds.size() <= 0) {
            return;
        }

        int offset = (newsArticles.size() / nativeAds.size()) + 1;
        int index = 0;
        for (UnifiedNativeAd ad : nativeAds) {
            newsArticles.add(index, ad);
            index = index + offset;
        }
        this.adapter.notifyDataSetChanged();
    }

}
