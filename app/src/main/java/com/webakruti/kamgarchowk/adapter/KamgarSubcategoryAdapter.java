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
import com.webakruti.kamgarchowk.model.KamgarSubcategoriesResponse;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;

import java.util.List;

public class KamgarSubcategoryAdapter extends RecyclerView.Adapter<KamgarSubcategoryAdapter.ViewHolder> {

    private Activity context;
    private int size;
    List<KamgarSubcategoriesResponse.Subcategory> list;

        public KamgarSubcategoryAdapter(Activity context, List<KamgarSubcategoriesResponse.Subcategory> list) {
        this.context = context;
        this.list = list;

    }

    @NonNull
    @Override
    public KamgarSubcategoryAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_kamgar_subcategory, viewGroup, false);
        KamgarSubcategoryAdapter.ViewHolder viewHolder = new KamgarSubcategoryAdapter.ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final KamgarSubcategoryAdapter.ViewHolder viewHolder, final int position) {


        final KamgarSubcategoriesResponse.Subcategory kamgarSubcategory = list.get(position);
        //viewHolder.textViewCategory.setText("Category " + position);

        viewHolder.checkboxSubcategoryName.setText(kamgarSubcategory.getName());

       /* viewHolder.edittextHourlyPrice.setText(kamgarSubcategory.getHourly()+"");
        viewHolder.edittextHalfdayPrice.setText(kamgarSubcategory.getHalfday()+"");
        viewHolder.edittextFulldayPrice.setText(kamgarSubcategory.getFullday()+"");
        viewHolder.edittextWeeklyPrice.setText(kamgarSubcategory.getWeekly()+"");
        viewHolder.edittextMonthlyPrice.setText(kamgarSubcategory.getMonthly()+"");*/


        if(kamgarSubcategory.getHourly() == 0)
        {
           viewHolder.edittextHourlyPrice.setText("");
        } else{
            viewHolder.edittextHourlyPrice.setText(kamgarSubcategory.getHourly()+"");
        }

        if(kamgarSubcategory.getHalfday() == 0)
        {
            viewHolder.edittextHalfdayPrice.setText("");
        }else{
            viewHolder.edittextHalfdayPrice.setText(kamgarSubcategory.getHalfday()+"");
        }

        if(kamgarSubcategory.getFullday() == 0)
        {
            viewHolder.edittextFulldayPrice.setText("");
        }else{
            viewHolder.edittextFulldayPrice.setText(kamgarSubcategory.getFullday()+"");
        }

        if(kamgarSubcategory.getWeekly() == 0)
        {
            viewHolder.edittextWeeklyPrice.setText("");
        }else{
            viewHolder.edittextWeeklyPrice.setText(kamgarSubcategory.getWeekly()+"");
        }

        if(kamgarSubcategory.getMonthly() == 0)
        {
            viewHolder.edittextMonthlyPrice.setText("");
        }else{
            viewHolder.edittextMonthlyPrice.setText(kamgarSubcategory.getMonthly()+"");
        }


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
        return list.size();
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
