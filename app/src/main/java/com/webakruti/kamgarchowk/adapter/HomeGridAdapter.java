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

import com.squareup.picasso.Picasso;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.HomeResponse;
import com.webakruti.kamgarchowk.userUI.CategoryActivity;
import com.webakruti.kamgarchowk.userUI.SubcategoryActivity;

import java.io.Serializable;
import java.util.List;

public class HomeGridAdapter extends RecyclerView.Adapter<HomeGridAdapter.ViewHolder> {

    private Context context;
    List<HomeResponse.Featuredlist> list;

    int listSize = 0;
    public HomeGridAdapter(Context context, List<HomeResponse.Featuredlist> list) {
        this.context = context;
        this.list = list;
        this.listSize = list.size()+1;
    }

    @NonNull
    @Override
    public HomeGridAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_grid, viewGroup, false);
        HomeGridAdapter.ViewHolder viewHolder = new HomeGridAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HomeGridAdapter.ViewHolder viewHolder, final int position) {


        if (position == listSize-1) {
            viewHolder.cardView2.setVisibility(View.VISIBLE);
            viewHolder.cardView.setVisibility(View.GONE);

        }else{
            final HomeResponse.Featuredlist featuredlist = list.get(position);

            viewHolder.cardView.setVisibility(View.VISIBLE);
            viewHolder.cardView2.setVisibility(View.GONE);

            viewHolder.textView.setText(featuredlist.getName());
            Picasso.with(context)
                    .load(featuredlist.getCategoryicon())
                    .placeholder(R.drawable.image_not_found)
                    .into(viewHolder.imageGrid);

            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, SubcategoryActivity.class);
                    intent.putExtra("FeaturedCategory", (Serializable) featuredlist);
                    context.startActivity(intent);
                }
            });

        }


        viewHolder.cardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(context, CategoryActivity.class);

                //intent.putExtra("CategoryName", category.getCategoryName());
                context.startActivity(intent1);
            }
        });
    }
    @Override
    public int getItemCount() {
        return this.listSize;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textView;
        private ImageView imageGrid;
        private CardView cardView;

        private CardView cardView2;
        private ImageView imageGrid2;
        private TextView textView2;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
            textView = (TextView)itemView.findViewById(R.id.textView);
            imageGrid = (ImageView) itemView.findViewById(R.id.imageGrid);

            cardView2 = (CardView)itemView.findViewById(R.id.cardView2);
            textView2 = (TextView)itemView.findViewById(R.id.textView2);
            imageGrid2 = (ImageView) itemView.findViewById(R.id.imageGrid2);


        }
    }
}