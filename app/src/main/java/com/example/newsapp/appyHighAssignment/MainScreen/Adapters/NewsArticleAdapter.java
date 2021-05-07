package com.example.newsapp.appyHighAssignment.MainScreen.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.appyHighAssignment.Modals.NewsArticle;
import com.example.newsapp.appyHighAssignment.R;
import com.example.newsapp.appyHighAssignment.Util.Utility;
import com.example.newsapp.appyHighAssignment.WebView.WebView;
import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.util.List;


public class NewsArticleAdapter extends RecyclerView.Adapter<NewsArticleAdapter.ViewHolder> {
    List<NewsArticle> newsArticleList;
    private LayoutInflater mInflater;
    private Context context;
    Gson gson ;

    public NewsArticleAdapter(Context context, List<NewsArticle> newsArticleList) {
        mInflater=(LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        this.newsArticleList=newsArticleList;
        gson  = new Gson();
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.news_card, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
        holder.newsAuthor.setVisibility(View.GONE);
        holder.newsTitle.setText(newsArticleList.get(position).getTitle());
        if(newsArticleList.get(position).getAuthor()!=null )
        {
            holder.newsAuthor.setVisibility(View.VISIBLE);
            holder.newsAuthor.setText(newsArticleList.get(position).getSource().getName());
        }
        if(newsArticleList.get(position).getSource()!=null)
        {
            holder.newsAuthor.setText(newsArticleList.get(position).getSource().getName()+" | "+newsArticleList.get(position).getAuthor());
        }

        try {
            holder.newstime.setText(Utility.CustomDate.getTimeDetails(newsArticleList.get(position).getPublishedAt().toString()));
        } catch (ParseException e) {
            e.printStackTrace();
        }



        if(newsArticleList.get(position).getUrlToImage()!=null)
        {
            Picasso.with(context).load(newsArticleList.get(position).getUrlToImage()).into(holder.newsImage);
        }else {
            // IF IMAGE IS NOT AVAILABLE
            holder.newsImage.setVisibility(View.GONE);
        }




        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String myJson = gson.toJson(products.get(position));
               context.startActivity(new Intent(context, WebView.class).putExtra("newsUrl",newsArticleList.get(position).getUrl()));
            }
        });


    }


    @Override
    public int getItemCount() {


        return newsArticleList.size();


    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView newsImage;
        TextView newsTitle,newstime,newsAuthor;

        View root;

        ViewHolder(View itemView) {
            super(itemView);
            root=itemView.findViewById(R.id.news_card_root);
            newsImage = itemView.findViewById(R.id.news_image);
            newsTitle = itemView.findViewById(R.id.news_title);
            newstime=itemView.findViewById(R.id.newstimecountry);
            newsAuthor=itemView.findViewById(R.id.newsauthor);


        }

    }









}