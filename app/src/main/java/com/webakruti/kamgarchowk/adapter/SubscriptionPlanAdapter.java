package com.webakruti.kamgarchowk.adapter;

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
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;

public class SubscriptionPlanAdapter extends RecyclerView.Adapter<SubscriptionPlanAdapter.ViewHolder> {

    private Context context;
    private int size;

    public SubscriptionPlanAdapter(Context context, int size) {
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public SubscriptionPlanAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_subscription_plans, viewGroup, false);
        SubscriptionPlanAdapter.ViewHolder viewHolder = new SubscriptionPlanAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SubscriptionPlanAdapter.ViewHolder viewHolder, final int position) {


        /*//final Student.Studentbatch studentbatch = list.get(position);

        viewHolder.textViewBatchCourseName.setText(size[position]);
        viewHolder.textViewBatchTime.setText(studentbatch.getBatch().getStartTime());
        viewHolder.textViewCourseTeacher.setText(studentbatch.getWhoAssinged());

        viewHolder.textViewBatchCourseDuration.setText(studentbatch.getBatch().getCourse().getDuration());
        viewHolder.textViewBatchStartDate.setText(studentbatch.getBatch().getStartDate());
        viewHolder.textViewBatchEndDate.setText(studentbatch.getBatch().getEndDate());*/

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
        return size;
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
