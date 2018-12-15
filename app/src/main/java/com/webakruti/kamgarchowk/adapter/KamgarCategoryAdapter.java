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

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.KamgarSubcategoryActivity;
import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.model.KamgarCategoryResponse;

import java.io.Serializable;
import java.util.List;

public class KamgarCategoryAdapter extends RecyclerView.Adapter<KamgarCategoryAdapter.ViewHolder> {

    private Activity context;
    private int size;
    List<KamgarCategoryResponse.Kamgarcategorylist> list;

    public KamgarCategoryAdapter(Activity context, List<KamgarCategoryResponse.Kamgarcategorylist> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public KamgarCategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kamgar_category, viewGroup, false);
        KamgarCategoryAdapter.ViewHolder viewHolder = new KamgarCategoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final KamgarCategoryAdapter.ViewHolder viewHolder, final int position) {


        final KamgarCategoryResponse.Kamgarcategorylist kamgarcategory = list.get(position);
        //viewHolder.textViewCategory.setText("Category " + position);
        viewHolder.textViewKamgarcategoryName.setText(kamgarcategory.getName());


       /* Picasso.with(context)
                .load(R.drawable.navab_thali)
                .into(viewHolder.imageViewVegImage);*/

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KamgarSubcategoryActivity.class);
                intent.putExtra("KamgarCategory", (Serializable) kamgarcategory);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageViewKamgarcategoryArrow;
        private TextView textViewKamgarcategoryName;
        private LinearLayout linearLayoutKamgarcategoryArrow;


        private CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
            textViewKamgarcategoryName = (TextView)itemView.findViewById(R.id.textViewKamgarcategoryName);
            linearLayoutKamgarcategoryArrow = (LinearLayout)itemView.findViewById(R.id.linearLayoutKamgarcategoryArrow);
            imageViewKamgarcategoryArrow = (ImageView)itemView.findViewById(R.id.imageViewKamgarcategoryArrow);


        }
    }
}
