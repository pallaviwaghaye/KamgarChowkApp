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
import com.webakruti.kamgarchowk.userUI.HireKamgarActivity;

public class UserMyEnquiryAdapter extends RecyclerView.Adapter<UserMyEnquiryAdapter.ViewHolder> {

    private Context context;
    private int size;

    public UserMyEnquiryAdapter(Context context, int size) {
        this.context = context;
        this.size = size;

    }

    @NonNull
    @Override
    public UserMyEnquiryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_enquiry, viewGroup, false);
        UserMyEnquiryAdapter.ViewHolder viewHolder = new UserMyEnquiryAdapter.ViewHolder(view);
        return viewHolder;
    }



    @Override
    public void onBindViewHolder(@NonNull final UserMyEnquiryAdapter.ViewHolder viewHolder, final int position) {

       /* viewHolder.textViewKamgarShowHideDetais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {*/
                if (viewHolder.linearLayoutEnquiryShowHide.getVisibility() == View.GONE)
                {
                    viewHolder.linearLayoutEnquiryShowHide.setVisibility(View.VISIBLE);
                    viewHolder.textViewKamgarShowHideDetais.setText("Less Details");
                } else{
                    viewHolder.linearLayoutEnquiryShowHide.setVisibility(View.GONE);
                    viewHolder.textViewKamgarShowHideDetais.setText("More Details");
                }
           /* }
        });*/


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

        //Dialog myDialog;

        private TextView textViewKamgarName;
        private TextView textViewKamgarDesignation;
        private TextView textViewKamgarDate;
        private TextView textViewRatingType;
        private TextView textViewKamgarAddress;
        private TextView textViewKamgarContactNo;
        private TextView textViewKamgarStatus;
        private TextView textViewKamgarShowHideDetais;
        private LinearLayout linearLayoutEnquiryShowHide;

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
            textViewKamgarDesignation = (TextView)itemView.findViewById(R.id.textViewKamgarDesignation);
            textViewKamgarDate = (TextView)itemView.findViewById(R.id.textViewKamgarDate);
            textViewRatingType = (TextView)itemView.findViewById(R.id.textViewRatingType);
            textViewKamgarAddress = (TextView)itemView.findViewById(R.id.textViewKamgarAddress);
            textViewKamgarContactNo = (TextView)itemView.findViewById(R.id.textViewKamgarContactNo);
            textViewKamgarStatus = (TextView)itemView.findViewById(R.id.textViewKamgarStatus);
            textViewKamgarShowHideDetais = (TextView)itemView.findViewById(R.id.textViewKamgarShowHideDetais);
            linearLayoutEnquiryShowHide = (LinearLayout) itemView.findViewById(R.id.linearLayoutEnquiryShowHide);

            imageViewRating1 = (ImageView) itemView.findViewById(R.id.imageViewRating1);
            imageViewRating2 = (ImageView) itemView.findViewById(R.id.imageViewRating2);
            imageViewRating3 = (ImageView) itemView.findViewById(R.id.imageViewRating3);
            imageViewRating4 = (ImageView) itemView.findViewById(R.id.imageViewRating4);
            imageViewRating5 = (ImageView) itemView.findViewById(R.id.imageViewRating5);



        }
    }
}
