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
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;

public class KamgarSubcategoryAdapter extends RecyclerView.Adapter<KamgarSubcategoryAdapter.ViewHolder> {

    private Activity context;
    private int size;

    public KamgarSubcategoryAdapter(Activity context, int size) {
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public KamgarSubcategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kamgar_subcategory, viewGroup, false);
        KamgarSubcategoryAdapter.ViewHolder viewHolder = new KamgarSubcategoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull KamgarSubcategoryAdapter.ViewHolder viewHolder, final int position) {


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

       /* viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
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

        private CheckBox checkboxSubcategoryName;
        private EditText edittextHourlyPrice;
        private EditText edittextHalfdayPrice;
        private EditText edittextFulldayPrice;
        private EditText edittextWeeklyPrice;
        private EditText edittextMonthlyPrice;

        private CardView cardView;


        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.cardView);
            edittextHourlyPrice = (EditText)itemView.findViewById(R.id.edittextHourlyPrice);
            edittextHalfdayPrice = (EditText)itemView.findViewById(R.id.edittextHalfdayPrice);
            edittextFulldayPrice = (EditText)itemView.findViewById(R.id.edittextFulldayPrice);
            edittextWeeklyPrice = (EditText)itemView.findViewById(R.id.edittextWeeklyPrice);
            edittextMonthlyPrice = (EditText)itemView.findViewById(R.id.edittextMonthlyPrice);
            checkboxSubcategoryName = (CheckBox)itemView.findViewById(R.id.checkboxSubcategoryName);



        }
    }
}
