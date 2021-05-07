/*
 * Created BY MEHRAJ UD DIN BHAT on 5/7/21 4:07 PM
 *  mehrajb33@gmail.com
 * Copyright (c) 2021. All Rights Reserved
 * LastModified 5/7/21 3:59 PM
 * /
 */

package com.example.newsapp.appyHighAssignment.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.appyHighAssignment.Admob.NativeAdManager;
import com.example.newsapp.appyHighAssignment.Modals.NewsArticle;
import com.example.newsapp.appyHighAssignment.R;
import com.example.newsapp.appyHighAssignment.Util.Utility;
import com.example.newsapp.appyHighAssignment.WebView.WebView;
import com.google.android.gms.ads.formats.MediaView;
import com.google.android.gms.ads.formats.NativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAd;
import com.google.android.gms.ads.formats.UnifiedNativeAdView;
import com.google.firebase.remoteconfig.FirebaseRemoteConfig;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.List;


public class NewsArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Object> newsArticleList;
    private LayoutInflater mInflater;
    private Context context;

    private static final int NEWS_ITEM_VIEW_TYPE = 0;

    private static final int UNIFIED_NATIVE_AD_VIEW_TYPE = 1;

    public NewsArticleAdapter(Context context, List<Object> newsArticleList) {
        mInflater=(LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.newsArticleList=newsArticleList;
         setAds();
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       // View view = mInflater.inflate(R.layout.news_card, parent, false);

        switch (viewType) {
            case UNIFIED_NATIVE_AD_VIEW_TYPE:
                View unifiedNativeLayoutView = LayoutInflater.from(
                        parent.getContext()).inflate(R.layout.native_ad,
                        parent, false);
                return new  UnifiedNativeAdViewHolder(unifiedNativeLayoutView);
            case NEWS_ITEM_VIEW_TYPE:
                // Fall through.
            default:
                View menuItemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                        R.layout.news_card, parent, false);
                return new NewsArticleView(menuItemLayoutView);
        }

    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder holder, int position) {

        int viewType = getItemViewType(position);
        switch (viewType) {
            case UNIFIED_NATIVE_AD_VIEW_TYPE:
                UnifiedNativeAd nativeAd = (UnifiedNativeAd) newsArticleList.get(position);
                populateNativeAdView(nativeAd, ((UnifiedNativeAdViewHolder) holder).getAdView());
                break;
            case NEWS_ITEM_VIEW_TYPE:
                // fall through
            default:
                setNewsArticle(holder,position);

        }






    }



    void setNewsArticle(RecyclerView.ViewHolder holder, int position)
    {
        holder=(NewsArticleView) holder;
        NewsArticle article=(NewsArticle)newsArticleList.get(position);

        ((NewsArticleView) holder).newsAuthor.setVisibility(View.GONE);
        ((NewsArticleView) holder).newsTitle.setText(article.getTitle());
        if(article!=null )
        {
            ((NewsArticleView) holder).newsAuthor.setVisibility(View.VISIBLE);
            ((NewsArticleView) holder).newsAuthor.setText(article.getSource().getName());
        }
        if(article.getSource()!=null)
        {
            ((NewsArticleView) holder).newsAuthor.setText(article.getSource().getName()+" | "+article.getAuthor());
        }

        try {
            ((NewsArticleView) holder).newstime.setText(Utility.CustomDate.getTimeDetails(article.getPublishedAt().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }



        if(article.getUrlToImage()!=null)
        {
            Picasso.with(context).load(article.getUrlToImage()).into(((NewsArticleView) holder).newsImage);
        }else {
            // IF IMAGE IS NOT AVAILABLE
            ((NewsArticleView) holder).newsImage.setVisibility(View.GONE);
        }




        ((NewsArticleView) holder).root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String myJson = gson.toJson(products.get(position));
                context.startActivity(new Intent(context, WebView.class).putExtra("newsUrl",article.getUrl()));
            }
        });
    }

    private void populateNativeAdView(UnifiedNativeAd nativeAd,
                                      UnifiedNativeAdView adView) {
        // Some assets are guaranteed to be in every UnifiedNativeAd.
        ((TextView) adView.getHeadlineView()).setText(nativeAd.getHeadline());
        ((TextView) adView.getBodyView()).setText(nativeAd.getBody());
        ((Button) adView.getCallToActionView()).setText(nativeAd.getCallToAction());

        // These assets aren't guaranteed to be in every UnifiedNativeAd, so it's important to
        // check before trying to display them.
        NativeAd.Image icon = nativeAd.getIcon();

        if (icon == null) {
            adView.getIconView().setVisibility(View.INVISIBLE);
        } else {
            ((ImageView) adView.getIconView()).setImageDrawable(icon.getDrawable());
            adView.getIconView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getPrice() == null) {
            adView.getPriceView().setVisibility(View.INVISIBLE);
        } else {
            adView.getPriceView().setVisibility(View.VISIBLE);
            ((TextView) adView.getPriceView()).setText(nativeAd.getPrice());
        }

        if (nativeAd.getStore() == null) {
            adView.getStoreView().setVisibility(View.INVISIBLE);
        } else {
            adView.getStoreView().setVisibility(View.VISIBLE);
            ((TextView) adView.getStoreView()).setText(nativeAd.getStore());
        }

        if (nativeAd.getStarRating() == null) {
            adView.getStarRatingView().setVisibility(View.INVISIBLE);
        } else {
            ((RatingBar) adView.getStarRatingView())
                    .setRating(nativeAd.getStarRating().floatValue());
            adView.getStarRatingView().setVisibility(View.VISIBLE);
        }

        if (nativeAd.getAdvertiser() == null) {
            adView.getAdvertiserView().setVisibility(View.INVISIBLE);
        } else {
            ((TextView) adView.getAdvertiserView()).setText(nativeAd.getAdvertiser());
            adView.getAdvertiserView().setVisibility(View.VISIBLE);
        }

        // Assign native ad object to the native view.
        adView.setNativeAd(nativeAd);
    }



    @Override
    public int getItemCount() {


        return newsArticleList.size();


    }


    @Override
    public int getItemViewType(int position) {

        Object recyclerViewItem =newsArticleList.get(position);
        if (recyclerViewItem instanceof UnifiedNativeAd) {
            return UNIFIED_NATIVE_AD_VIEW_TYPE;
        }
        return NEWS_ITEM_VIEW_TYPE;
    }


    public void setAds() {
        FirebaseRemoteConfig mFirebaseRemoteConfig;
        mFirebaseRemoteConfig = FirebaseRemoteConfig.getInstance();
        String adsEnabled = mFirebaseRemoteConfig.getString("adsEnabled");
        Boolean value= Boolean.parseBoolean(adsEnabled);


        if(value)
        {
            Toast.makeText(context,"Ads ARE Enabled",Toast.LENGTH_LONG).show();
            new NativeAdManager(context,3,newsArticleList,this);
            return;
        }
        Toast.makeText(context,"Ads ARE not Enabled",Toast.LENGTH_LONG).show();

    }


//---------------------------------------
//VIEWS-AD VIEW AND NEWS ARTICLE VIEW
//----------------------------------------

    public class NewsArticleView extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTitle,newstime,newsAuthor;

        View root;

        NewsArticleView(View itemView) {
            super(itemView);
            root=itemView.findViewById(R.id.news_card_root);
            newsImage = itemView.findViewById(R.id.news_image);
            newsTitle = itemView.findViewById(R.id.news_title);
            newstime=itemView.findViewById(R.id.newstimecountry);
            newsAuthor=itemView.findViewById(R.id.newsauthor);


        }

    }


    public class UnifiedNativeAdViewHolder extends RecyclerView.ViewHolder {

        private UnifiedNativeAdView adView;

        public UnifiedNativeAdView getAdView() {
            return adView;
        }

        UnifiedNativeAdViewHolder(View view) {
            super(view);
            adView = (UnifiedNativeAdView) view.findViewById(R.id.ad_view);

            // The MediaView will display a video asset if one is present in the ad, and the
            // first image asset otherwise.
            adView.setMediaView((MediaView) adView.findViewById(R.id.ad_media));

            // Register the view used for each individual asset.
            adView.setHeadlineView(adView.findViewById(R.id.ad_headline));
            adView.setBodyView(adView.findViewById(R.id.ad_body));
            adView.setCallToActionView(adView.findViewById(R.id.ad_call_to_action));
            adView.setIconView(adView.findViewById(R.id.ad_icon));
            adView.setPriceView(adView.findViewById(R.id.ad_price));
            adView.setStarRatingView(adView.findViewById(R.id.ad_stars));
            adView.setStoreView(adView.findViewById(R.id.ad_store));
            adView.setAdvertiserView(adView.findViewById(R.id.ad_advertiser));
        }
    }









}