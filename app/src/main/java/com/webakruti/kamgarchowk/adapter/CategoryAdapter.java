package com.webakruti.kamgarchowk.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.KamgarSubcategoryActivity;
import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.userUI.SubcategoryActivity;

import java.io.Serializable;
import java.util.List;


public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private Context context;
    List<CategoryList> list;
    int size;

    public CategoryAdapter(Context context, List<CategoryList> list) {
        this.context = context;
        this.size = size;
        this.list = list;

    }

    @NonNull
    @Override
    public CategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_category, viewGroup, false);
        CategoryAdapter.ViewHolder viewHolder = new CategoryAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final CategoryAdapter.ViewHolder viewHolder, final int position) {

        final CategoryList category = list.get(position);
        //viewHolder.textViewCategory.setText("Category " + position);
        viewHolder.textViewCategoryName.setText(category.getName());

        Toast.makeText(context,"CategoryName = "+ category.getName(),Toast.LENGTH_LONG).show();
        Toast.makeText(context,"CategoryName : "+ category.getName(),Toast.LENGTH_LONG).show();

        Picasso.with(context)
                .load(category.getCategoryicon())
                .placeholder(R.drawable.carpenter_icon)
                .into(viewHolder.imageViewCategory);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, SubcategoryActivity.class);
                //intent.putExtra("KamgarCategory", (Serializable) category);
                context.startActivity(intent);
            }
        });

       /* viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubcategoryActivity.class);
                context.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewCategoryName;
        private ImageView imageViewCategory;
        private CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
            textViewCategoryName = (TextView)itemView.findViewById(R.id.textViewCategoryName);
            imageViewCategory = (ImageView)itemView.findViewById(R.id.imageViewCategory);



        }
    }
}


