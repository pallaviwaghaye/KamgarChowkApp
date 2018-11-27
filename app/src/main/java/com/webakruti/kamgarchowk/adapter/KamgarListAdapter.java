package com.webakruti.kamgarchowk.adapter;

import android.app.Dialog;
import android.content.Context;
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

import com.webakruti.kamgarchowk.R;

public class KamgarListAdapter extends RecyclerView.Adapter<KamgarListAdapter.ViewHolder> {

    private Context context;
    private int size;

    public KamgarListAdapter(Context context, int size) {
        this.context = context;
        this.size = size;

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


        viewHolder.textViewEnquiry.setText("Enquiry");
        viewHolder.textViewEnquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.myDialog = new Dialog(context);
            }
        });


        /*//final Student.Studentbatch studentbatch = list.get(position);

        viewHolder.textViewBatchCourseName.setText(size[position]);
        viewHolder.textViewBatchTime.setText(studentbatch.getBatch().getStartTime());
        viewHolder.textViewCourseTeacher.setText(studentbatch.getWhoAssinged());

        viewHolder.textViewBatchCourseDuration.setText(studentbatch.getBatch().getCourse().getDuration());
        viewHolder.textViewBatchStartDate.setText(studentbatch.getBatch().getStartDate());
        viewHolder.textViewBatchEndDate.setText(studentbatch.getBatch().getEndDate());*/

        /*Picasso.with(context)
                .load(R.drawable.navab_thali)
                .into(viewHolder.imageViewVegImage);

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoOutForLunchDinnerActivity.class);
                context.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return size;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        Dialog myDialog;

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

        public void ShowPopup(View v) {
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
