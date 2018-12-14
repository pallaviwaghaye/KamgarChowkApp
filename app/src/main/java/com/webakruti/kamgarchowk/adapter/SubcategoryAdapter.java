package com.webakruti.kamgarchowk.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.model.SubcategoryListResponse;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;
import com.webakruti.kamgarchowk.userUI.SubcategoryActivity;

import java.io.Serializable;
import java.util.List;

public class SubcategoryAdapter extends RecyclerView.Adapter<SubcategoryAdapter.ViewHolder> {

    private Activity context;
    private int size;
    List<SubcategoryListResponse.Subcategory> list;

    public SubcategoryAdapter(Activity context, List<SubcategoryListResponse.Subcategory> list) {
        this.context = context;
        this.size = size;
        this.list = list;

    }

    @NonNull
    @Override
    public SubcategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_subcategory, viewGroup, false);
        SubcategoryAdapter.ViewHolder viewHolder = new SubcategoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SubcategoryAdapter.ViewHolder viewHolder, final int position) {


        final SubcategoryListResponse.Subcategory subcategoryListResponse = list.get(position);
        //viewHolder.textViewCategory.setText("Category " + position);
        viewHolder.textViewSubcategoryName.setText(subcategoryListResponse.getName());


        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KamgarListActivity.class);
                intent.putExtra("KamgarSubCategory", (Serializable) subcategoryListResponse);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewSubcategoryArrow;
        private TextView textViewSubcategoryName;
        private LinearLayout linearLayoutSubcategoryArrow;


        private CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
            textViewSubcategoryName = (TextView)itemView.findViewById(R.id.textViewSubcategoryName);
            linearLayoutSubcategoryArrow = (LinearLayout)itemView.findViewById(R.id.linearLayoutSubcategoryArrow);
            imageViewSubcategoryArrow = (ImageView)itemView.findViewById(R.id.imageViewSubcategoryArrow);



        }
    }
}
