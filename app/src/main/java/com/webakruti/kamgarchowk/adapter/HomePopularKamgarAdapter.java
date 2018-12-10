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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.HomeResponse;
import com.webakruti.kamgarchowk.userUI.SubcategoryActivity;

import java.io.Serializable;
import java.util.List;

public class HomePopularKamgarAdapter extends RecyclerView.Adapter<HomePopularKamgarAdapter.ViewHolder> {

    private Context context;
    private int size;
    List<HomeResponse.Popularlist> list;

    public HomePopularKamgarAdapter(Context context, List<HomeResponse.Popularlist> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_popular_kamgar_adapter, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder viewHolder, final int position) {


        final HomeResponse.Popularlist popularlist = list.get(position);

        //final Student.Studentbatch studentbatch = list.get(position);

        viewHolder.textViewPoprWorkrName.setText(popularlist.getName());

        Picasso.with(context)
                .load(popularlist.getCategoryimage())
                .placeholder(R.drawable.image_not_found)
                .into(viewHolder.imageViewPoprWorkrImage);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SubcategoryActivity.class);
                intent.putExtra("PopularCategory", (Serializable) popularlist);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewPoprWorkrName;
        private LinearLayout linearLayoutPoprWorkrArrow;
        private ImageView imageViewPoprWorkrImage;
        private CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
            textViewPoprWorkrName = (TextView)itemView.findViewById(R.id.textViewPoprWorkrName);
            linearLayoutPoprWorkrArrow = (LinearLayout) itemView.findViewById(R.id.linearLayoutPoprWorkrArrow);
            imageViewPoprWorkrImage = (ImageView)itemView.findViewById(R.id.imageViewPoprWorkrImage);

        }
    }
}
