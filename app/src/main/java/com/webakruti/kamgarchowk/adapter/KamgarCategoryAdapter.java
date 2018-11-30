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

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.KamgarSubcategoryActivity;

public class KamgarCategoryAdapter extends RecyclerView.Adapter<KamgarCategoryAdapter.ViewHolder> {

    private Context context;
    private int size;

    public KamgarCategoryAdapter(Context context, int size) {
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public KamgarCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kamgar_category, viewGroup, false);
        KamgarCategoryAdapter.ViewHolder viewHolder = new KamgarCategoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KamgarCategoryAdapter.ViewHolder viewHolder, final int position) {


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

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KamgarSubcategoryActivity.class);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return size;
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
