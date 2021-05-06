package com.example.newsapp.appyHighAssignment.MainScreen.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.appyHighAssignment.R;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class NewsCategoryAdapter extends RecyclerView.Adapter<NewsCategoryAdapter.ViewHolder> {
    String  newsCategory[]={"Headlines","General","Business","Science","Technology","Entertainment"};
    private LayoutInflater mInflater;
    private Context context;
    Gson gson ;

    public NewsCategoryAdapter( Context context) {
        mInflater=(LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        gson  = new Gson();
    }

    // inflates the row layout from xml when needed
    @Override
    @NonNull
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.category_card, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the view and textview in each row
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {
         holder.itemNameView.setText(this.newsCategory[position]);


//
//        holder.root.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //String myJson = gson.toJson(products.get(position));
//               // context.startActivity(new Intent(context, ProductDescription.class).putExtra("Product",myJson));
//            }
//        });


    }

    // total number of rows
    @Override
    public int getItemCount() {


        return newsCategory.length;

        //return  20;
    }

    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView itemImageView;
        TextView itemNameView;

        View root;

        ViewHolder(View itemView) {
            super(itemView);
            root=itemView.findViewById(R.id.catROOT);
            itemImageView = itemView.findViewById(R.id.cat_image_id);
            itemNameView = itemView.findViewById(R.id.cat_name_ids);


        }

    }









}