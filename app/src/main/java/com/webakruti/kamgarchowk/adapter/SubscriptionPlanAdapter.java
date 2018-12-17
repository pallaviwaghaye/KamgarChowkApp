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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.SubscripnPlanResp;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;

import java.util.List;

public class SubscriptionPlanAdapter extends RecyclerView.Adapter<SubscriptionPlanAdapter.ViewHolder> {

    private Activity context;
    List<SubscripnPlanResp.Subcriptionplan> list;
    public SubscriptionPlanAdapter(Activity context, List<SubscripnPlanResp.Subcriptionplan> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public SubscriptionPlanAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_subscription_plans, viewGroup, false);
        SubscriptionPlanAdapter.ViewHolder viewHolder = new SubscriptionPlanAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SubscriptionPlanAdapter.ViewHolder viewHolder, final int position) {


        final SubscripnPlanResp.Subcriptionplan plans = list.get(position);
        //viewHolder.textViewCategory.setText("Category " + position);
        viewHolder.textViewSubPlanType.setText(plans.getName());
        viewHolder.textViewSubPlanPrice.setText(plans.getAmount());
        viewHolder.textView1stText.setText(plans.getDuration());


        if (plans.getName().equalsIgnoreCase("Free")) {
            viewHolder.textViewSubPlanType.setBackgroundColor(context.getResources().getColor(R.color.green));
            viewHolder.textViewSubPlanType.setText(plans.getName());
        } else if (plans.getName().equalsIgnoreCase("Silver")) {
            viewHolder.textViewSubPlanType.setBackgroundColor(context.getResources().getColor(R.color.purple));
            viewHolder.textViewSubPlanType.setText(plans.getName());
        } else {
            viewHolder.textViewSubPlanType.setBackgroundColor(context.getResources().getColor(R.color.yellow));
            viewHolder.textViewSubPlanType.setText(plans.getName());
        }

       /* Picasso.with(context)
                .load(R.drawable.navab_thali)
                .into(viewHolder.imageViewVegImage);*/

        /*viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KamgarListActivity.class);
                context.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView textViewSubPlanType;
        private TextView textViewSubPlanPrice;
        private TextView textView1stText;
        private TextView textView2ndText;
        private TextView textView3rdText;

        private Button buttonSubscribeNow;

        private CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
            textViewSubPlanType = (TextView)itemView.findViewById(R.id.textViewSubPlanType);
            textViewSubPlanPrice = (TextView)itemView.findViewById(R.id.textViewSubPlanPrice);
            textView1stText = (TextView)itemView.findViewById(R.id.textView1stText);
            textView2ndText = (TextView)itemView.findViewById(R.id.textView2ndText);
            textView3rdText = (TextView)itemView.findViewById(R.id.textView3rdText);

            buttonSubscribeNow = (Button)itemView.findViewById(R.id.buttonSubscribeNow);



        }
    }
}
