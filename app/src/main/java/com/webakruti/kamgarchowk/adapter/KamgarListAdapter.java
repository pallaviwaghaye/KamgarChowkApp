package com.webakruti.kamgarchowk.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.KamgarResponse;
import com.webakruti.kamgarchowk.model.SubcategoryListResponse;
import com.webakruti.kamgarchowk.userUI.HireKamgarActivity;

import java.io.Serializable;
import java.util.List;

public class KamgarListAdapter extends RecyclerView.Adapter<KamgarListAdapter.ViewHolder> {

    private Activity context;
    List<KamgarResponse.Kamgar> list;
    int countRating = 0;
    public KamgarListAdapter(Activity context, List<KamgarResponse.Kamgar> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public KamgarListAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kamgar_list_adapter, viewGroup, false);
        KamgarListAdapter.ViewHolder viewHolder = new KamgarListAdapter.ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull final KamgarListAdapter.ViewHolder viewHolder, final int position) {

        final KamgarResponse.Kamgar kamgar = list.get(position);

        viewHolder.textViewKamgarName.setText(kamgar.getFirstName()+" "+kamgar.getLastName());
        viewHolder.textViewExperience.setText(kamgar.getExperience()+"");
        viewHolder.textViewAddress.setText(kamgar.getAddress());
        if(kamgar.getFullday() != 0) {
            viewHolder.textViewIncome.setText(kamgar.getFullday()+"");
        }else{
            viewHolder.textViewIncome.setText("N/A");
        }


        if (kamgar.getContImgUrl() == null) {
            if(kamgar.getGenderId() != 0) {
                if (kamgar.getGenderId() == 2) {
                    Picasso.with(context)
                            .load(R.drawable.defaultfemaleimg)
                            .into(viewHolder.imageViewKamgarImage);
                } else {
                    Picasso.with(context)
                            .load(R.drawable.defaultmaleimg)
                            .into(viewHolder.imageViewKamgarImage);
                }
            }
            else{
                Picasso.with(context)
                        .load(R.drawable.defaultmaleimg)
                        .into(viewHolder.imageViewKamgarImage);
            }
        } else {
            Picasso.with(context)
                    .load(kamgar.getContImgUrl())
                    .into(viewHolder.imageViewKamgarImage);
        }

        //viewHolder.textViewEnquiry.setText("Enquiry");
        viewHolder.textViewEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //viewHolder.myDialog = new Dialog(context);
                Intent intent = new Intent(context, HireKamgarActivity.class);
                intent.putExtra("kamgar",(Serializable) kamgar);
                context.startActivity(intent);
            }
        });
       /* Picasso.with(context)
                .load(R.drawable.defaultmaleimg)
                *//*.placeholder(R.drawable.defaultmaleimg)*//*
                .into(viewHolder.imageViewKamgarImage);
*/
       /* viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, GoOutForLunchDinnerActivity.class);
            context.startActivity(intent);

        }
    });*/


        switch (kamgar.getRating()) {

            case 0:
                    viewHolder.imageViewRating1.setEnabled(false);
                    viewHolder.imageViewRating2.setEnabled(false);
                    viewHolder.imageViewRating3.setEnabled(false);
                    viewHolder.imageViewRating4.setEnabled(false);
                    viewHolder.imageViewRating5.setEnabled(false);

                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                //viewHolder.buttonRateNow.setEnabled(false);

                break;

            case 1:

                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));

                viewHolder.imageViewRating1.setEnabled(false);
                viewHolder.imageViewRating2.setEnabled(false);
                viewHolder.imageViewRating3.setEnabled(false);
                viewHolder.imageViewRating4.setEnabled(false);
                viewHolder.imageViewRating5.setEnabled(false);



                break;

            case 2:
                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));

                viewHolder.imageViewRating1.setEnabled(false);
                viewHolder.imageViewRating2.setEnabled(false);
                viewHolder.imageViewRating3.setEnabled(false);
                viewHolder.imageViewRating4.setEnabled(false);
                viewHolder.imageViewRating5.setEnabled(false);


                break;

            case 3:

                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));

                viewHolder.imageViewRating1.setEnabled(false);
                viewHolder.imageViewRating2.setEnabled(false);
                viewHolder.imageViewRating3.setEnabled(false);
                viewHolder.imageViewRating4.setEnabled(false);
                viewHolder.imageViewRating5.setEnabled(false);


                break;

            case 4:

                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));

                viewHolder.imageViewRating1.setEnabled(false);
                viewHolder.imageViewRating2.setEnabled(false);
                viewHolder.imageViewRating3.setEnabled(false);
                viewHolder.imageViewRating4.setEnabled(false);
                viewHolder.imageViewRating5.setEnabled(false);


                break;

            case 5:

                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));

                viewHolder.imageViewRating1.setEnabled(false);
                viewHolder.imageViewRating2.setEnabled(false);
                viewHolder.imageViewRating3.setEnabled(false);
                viewHolder.imageViewRating4.setEnabled(false);
                viewHolder.imageViewRating5.setEnabled(false);


                break;

        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Dialog myDialog;

        private ImageView imageViewKamgarImage;
        private TextView textViewKamgarName;
        private TextView textViewAddress;
        private TextView textViewExperience;
        private TextView textViewIncome;
        private TextView textViewDays;
        private TextView textViewEnquiry;

        private ImageView imageViewRating1;
        private ImageView imageViewRating2;
        private ImageView imageViewRating3;
        private ImageView imageViewRating4;
        private ImageView imageViewRating5;

        private CardView cardView;

        /* public void ShowPopup(View v) {
             TextView txtclose;
             Button btnFollow;
             myDialog.setContentView(R.layout.custom_popup);
             txtclose =(TextView) myDialog.findViewById(R.id.txtclose);
             txtclose.setText("X");
             btnFollow = (Button) myDialog.findViewById(R.id.btnfollow);
             txtclose.setOnClickListener(new View.OnClickListener() {
                 @Override
                 public void onClick(View v) {
                     myDialog.dismiss();
                 }
             });
             myDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
             myDialog.show();
         }
 */
        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
            textViewKamgarName = (TextView)itemView.findViewById(R.id.textViewKamgarName);
            textViewAddress = (TextView)itemView.findViewById(R.id.textViewAddress);
            textViewExperience = (TextView)itemView.findViewById(R.id.textViewExperience);
            textViewIncome = (TextView)itemView.findViewById(R.id.textViewIncome);
            textViewDays = (TextView)itemView.findViewById(R.id.textViewDays);
            textViewEnquiry = (TextView)itemView.findViewById(R.id.textViewEnquiry);
            imageViewKamgarImage = (ImageView)itemView.findViewById(R.id.imageViewKamgarImage);
            imageViewRating1 = (ImageView) itemView.findViewById(R.id.imageViewRating1);
            imageViewRating2 = (ImageView) itemView.findViewById(R.id.imageViewRating2);
            imageViewRating3 = (ImageView) itemView.findViewById(R.id.imageViewRating3);
            imageViewRating4 = (ImageView) itemView.findViewById(R.id.imageViewRating4);
            imageViewRating5 = (ImageView) itemView.findViewById(R.id.imageViewRating5);



        }
    }
}
