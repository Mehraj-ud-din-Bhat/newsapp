package com.example.newsapp.appyHighAssignment.MainScreen.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.newsapp.appyHighAssignment.NewsByCategory.NewsByCategory;
import com.example.newsapp.appyHighAssignment.R;
import com.google.gson.Gson;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class NewsCategoryAdapter extends RecyclerView.Adapter<NewsCategoryAdapter.ViewHolder> {

    ArrayList<Category> categories;
    private LayoutInflater mInflater;
    private Context context;
    Gson gson ;

    public NewsCategoryAdapter( Context context) {
        mInflater=(LayoutInflater)   context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        categories=new ArrayList<>();
        categories.add(new Category("Coronavirus",R.drawable.corona));
        categories.add(new Category("General",R.drawable.generalnews));
        categories.add(new Category("Business",R.drawable.businessicon));
        categories.add(new Category("Science",R.drawable.sciencenews));
        categories.add(new Category("Technology",R.drawable.technewsicon));
        categories.add(new Category("Entertainment",R.drawable.enicon));


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
         holder.itemNameView.setText(this.categories.get(position).getName());
         holder.itemImageView.setImageResource(this.categories.get(position).getImageId());


        holder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                context.startActivity(new Intent(context, NewsByCategory.class).putExtra("catName",categories.get(position).getName()));
            }
        });


    }

    // total number of rows
    @Override
    public int getItemCount() {


        return categories.size();


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