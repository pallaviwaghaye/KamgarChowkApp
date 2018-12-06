package com.webakruti.kamgarchowk.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.util.List;

public class HomeAvailAllServicesAdapter extends RecyclerView.Adapter<HomeAvailAllServicesAdapter.ViewHolder> {

    private Context context;
    List<HomeResponse.Workavllist> list;

    public HomeAvailAllServicesAdapter(Context context, List<HomeResponse.Workavllist> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public HomeAvailAllServicesAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_home_avail_all_services_adapter, viewGroup, false);
        HomeAvailAllServicesAdapter.ViewHolder viewHolder = new HomeAvailAllServicesAdapter.ViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final HomeAvailAllServicesAdapter.ViewHolder viewHolder, final int position) {


        final HomeResponse.Workavllist workavllist = list.get(position);

        //final Student.Studentbatch studentbatch = list.get(position);

        viewHolder.textViewAllServices.setText(workavllist.getName());

        Picasso.with(context)
                .load(workavllist.getCategoryimage())
                .placeholder(R.drawable.image_not_found)
                .resize(500, 300)
                .into(viewHolder.imageViewAllServicesImage);

        /*viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoOutForLunchDinnerActivity.class);
                context.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewAllServices;
        private ImageView imageViewAllServicesImage;
        private ImageView imageViewAllServicesArrow;
        private CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
            textViewAllServices = (TextView)itemView.findViewById(R.id.textViewAllServices);
            imageViewAllServicesArrow = (ImageView)itemView.findViewById(R.id.imageViewAllServicesArrow);
            imageViewAllServicesImage = (ImageView) itemView.findViewById(R.id.imageViewAllServicesImage);



        }
    }
}
