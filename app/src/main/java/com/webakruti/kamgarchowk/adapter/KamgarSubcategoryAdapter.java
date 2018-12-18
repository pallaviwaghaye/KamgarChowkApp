package com.webakruti.kamgarchowk.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.KamgarPrice;
import com.webakruti.kamgarchowk.model.KamgarSubcategoriesResponse;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class KamgarSubcategoryAdapter extends RecyclerView.Adapter<KamgarSubcategoryAdapter.ViewHolder> {

    private Activity context;
    private int size;
    List<KamgarSubcategoriesResponse.Subcategory> list;
    Button buttonDoneSubcategory;
    List<Boolean> listOfChecks;
    RecyclerView recyclerView;
    public HashMap<Integer, KamgarSubcategoryAdapter.ViewHolder> holderHashMap = new HashMap<>();

    public KamgarSubcategoryAdapter(Activity context, List<KamgarSubcategoriesResponse.Subcategory> list, RecyclerView recyclerView) {
        this.context = context;
        this.list = list;
        buttonDoneSubcategory = context.findViewById(R.id.buttonDoneSubcategory);
        this.recyclerView = recyclerView;
        listOfChecks = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            listOfChecks.add(i, false);
        }
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

        viewHolder.edittextFulldayPrice.setEnabled(false);
        viewHolder.edittextHalfdayPrice.setEnabled(false);
        viewHolder.edittextHourlyPrice.setEnabled(false);
        viewHolder.edittextMonthlyPrice.setEnabled(false);
        viewHolder.edittextWeeklyPrice.setEnabled(false);
        viewHolder.checkboxSubcategoryName.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    listOfChecks.set(position, true);
                    viewHolder.edittextFulldayPrice.setEnabled(true);
                    viewHolder.edittextHalfdayPrice.setEnabled(true);
                    viewHolder.edittextHourlyPrice.setEnabled(true);
                    viewHolder.edittextMonthlyPrice.setEnabled(true);
                    viewHolder.edittextWeeklyPrice.setEnabled(true);

                } else {
                    listOfChecks.set(position, false);
                    viewHolder.edittextFulldayPrice.setEnabled(false);
                    viewHolder.edittextHalfdayPrice.setEnabled(false);
                    viewHolder.edittextHourlyPrice.setEnabled(false);
                    viewHolder.edittextMonthlyPrice.setEnabled(false);
                    viewHolder.edittextWeeklyPrice.setEnabled(false);
                }
            }
        });

        if (kamgarSubcategory.getHourly() == 0) {
            viewHolder.edittextHourlyPrice.setText("");
        } else {
            viewHolder.edittextHourlyPrice.setText(kamgarSubcategory.getHourly() + "");
        }

        if (kamgarSubcategory.getHalfday() == 0) {
            viewHolder.edittextHalfdayPrice.setText("");
        } else {
            viewHolder.edittextHalfdayPrice.setText(kamgarSubcategory.getHalfday() + "");
        }

        if (kamgarSubcategory.getFullday() == 0) {
            viewHolder.edittextFulldayPrice.setText("");
        } else {
            viewHolder.edittextFulldayPrice.setText(kamgarSubcategory.getFullday() + "");
        }

        if (kamgarSubcategory.getWeekly() == 0) {
            viewHolder.edittextWeeklyPrice.setText("");
        } else {
            viewHolder.edittextWeeklyPrice.setText(kamgarSubcategory.getWeekly() + "");
        }

        if (kamgarSubcategory.getMonthly() == 0) {
            viewHolder.edittextMonthlyPrice.setText("");
        } else {
            viewHolder.edittextMonthlyPrice.setText(kamgarSubcategory.getMonthly() + "");
        }


       /* viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, KamgarListActivity.class);
                context.startActivity(intent);

            }
        });*/


        buttonDoneSubcategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
// reyclerView.findViewHolderforAdaperPosition(position)

                List<KamgarPrice> listToSend = new ArrayList<>();
                for (int i = 0; i < listOfChecks.size(); i++) {
                    if (listOfChecks.get(i)) {
                        ViewHolder holder = (ViewHolder) recyclerView.findViewHolderForAdapterPosition(i);
                        if (holder == null) {
                            holder = holderHashMap.get(i);
                        }
                        KamgarPrice kamgarPrice = new KamgarPrice();
                        kamgarPrice.setHourly(holder.edittextHourlyPrice.getText().toString() + " SubCId : " + holder.checkboxSubcategoryName.getText());
                        kamgarPrice.setHalfDay(holder.edittextHalfdayPrice.getText().toString());
                        kamgarPrice.setFullDay(holder.edittextFulldayPrice.getText().toString());
                        kamgarPrice.setWeekly(holder.edittextWeeklyPrice.getText().toString());
                        kamgarPrice.setMonthly(holder.edittextMonthlyPrice.getText().toString());
                        listToSend.add(kamgarPrice);
                    }
                }


                Log.e("Data:", listToSend.toString());
            }
        });
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

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            edittextHourlyPrice = (EditText) itemView.findViewById(R.id.edittextHourlyPrice);
            edittextHalfdayPrice = (EditText) itemView.findViewById(R.id.edittextHalfdayPrice);
            edittextFulldayPrice = (EditText) itemView.findViewById(R.id.edittextFulldayPrice);
            edittextWeeklyPrice = (EditText) itemView.findViewById(R.id.edittextWeeklyPrice);
            edittextMonthlyPrice = (EditText) itemView.findViewById(R.id.edittextMonthlyPrice);
            checkboxSubcategoryName = (CheckBox) itemView.findViewById(R.id.checkboxSubcategoryName);

        }
    }


    @Override
    public void onViewDetachedFromWindow(KamgarSubcategoryAdapter.ViewHolder holder) {
        holderHashMap.put(holder.getAdapterPosition(), holder);
        super.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewAttachedToWindow(KamgarSubcategoryAdapter.ViewHolder holder) {
        holderHashMap.remove(holder.getAdapterPosition());
        super.onViewAttachedToWindow(holder);

    }
}
