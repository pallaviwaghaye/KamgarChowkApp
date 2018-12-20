package com.webakruti.kamgarchowk.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.KamgarChangeStatus;
import com.webakruti.kamgarchowk.model.MyEnquiryResponse;
import com.webakruti.kamgarchowk.model.MyOrdersResponse;
import com.webakruti.kamgarchowk.model.RateResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarMyOrdersAdapter extends RecyclerView.Adapter<KamgarMyOrdersAdapter.ViewHolder> {

    private Activity context;
    private int size;
    List<MyOrdersResponse.Kamgaractenquiry> list;
    int countRating = 0;
    private ProgressDialog progressDialogForAPI;
    List<MyOrdersResponse.Workstatusselect> list1;
    String defaultStatus = "Select";
    boolean flagForItemClickNotCalled = false;

    public KamgarMyOrdersAdapter(Activity context, List<MyOrdersResponse.Kamgaractenquiry> list, List<MyOrdersResponse.Workstatusselect> list1) {
        this.context = context;
        this.list = list;
        this.list1 = list1;
        //this.size = size;
    }

    @NonNull
    @Override
    public KamgarMyOrdersAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_my_orders, viewGroup, false);
        KamgarMyOrdersAdapter.ViewHolder viewHolder = new KamgarMyOrdersAdapter.ViewHolder(view);

        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final KamgarMyOrdersAdapter.ViewHolder viewHolder, final int position) {
        final MyOrdersResponse.Kamgaractenquiry orders = list.get(position);


        viewHolder.textViewUserName.setText(orders.getUserFirstName() + " " + orders.getUserLastName());
        viewHolder.textViewKamgarDesignation.setText(orders.getSubcategory());
        viewHolder.textViewUserDate.setText(orders.getEnquiryDate());
        if (orders.getUserAddress() != null && orders.getCityname() != null) {
            viewHolder.textViewUserAddress.setText(orders.getUserAddress() + " " + orders.getCityname());
        } else {
            viewHolder.textViewUserAddress.setText("N/A");
        }
        viewHolder.textViewUserContactNo.setText(orders.getUserMobileNo());
        // viewHolder.textViewRatingType.setText(orders.getRateremark());


        initStatusData(viewHolder, position, orders);
       /* List<MyOrdersResponse.Workstatusselect> status = new ArrayList<>();
        ArrayAdapter<MyOrdersResponse.Workstatusselect> arrayAdapter = new ArrayAdapter<MyOrdersResponse.Workstatusselect>(context,android.R.layout.simple_spinner_dropdown_item,status);
        viewHolder.spinnerStatus.setAdapter(arrayAdapter);*/

                /* String[] platformList = getResources().getStringArray(R.array.platforms);
        ArrayAdapter<String> adapterPlatform = new ArrayAdapter<String>(RailwayCategoryFormActivity.this, android.R.layout.simple_spinner_dropdown_item, platformList);
        spinnerPlatform.setAdapter(adapterPlatform);

        spinnerPlatform.setSelection(0, true);
        View v1 = spinnerPlatform.getSelectedView();
        setTextCustom(v1);*/


       /* if (orders.getWorkstatus().equalsIgnoreCase("Completed")) {
            viewHolder.imageViewRating1.setEnabled(true);
            viewHolder.imageViewRating2.setEnabled(true);
            viewHolder.imageViewRating3.setEnabled(true);
            viewHolder.imageViewRating4.setEnabled(true);
            viewHolder.imageViewRating5.setEnabled(true);
            viewHolder.buttonRateNow.setEnabled(true);*/

        switch (orders.getRating()) {

            case 0:
                viewHolder.imageViewRating1.setEnabled(false);
                viewHolder.imageViewRating2.setEnabled(false);
                viewHolder.imageViewRating3.setEnabled(false);
                viewHolder.imageViewRating4.setEnabled(false);
                viewHolder.imageViewRating5.setEnabled(false);

                viewHolder.textViewRatingType.setText("No Rating");

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

                viewHolder.textViewRatingType.setText("Poor");
                /*viewHolder.buttonRateNow.setEnabled(false);*/

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

                viewHolder.textViewRatingType.setText("Satisfactory");


//                viewHolder.buttonRateNow.setEnabled(false);

                break;

            case 3:

                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                //viewHolder.buttonRateNow.setEnabled(false);

                viewHolder.textViewRatingType.setText("Good");

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
                //viewHolder.buttonRateNow.setEnabled(false);

                viewHolder.textViewRatingType.setText("Very good");

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
                //viewHolder.buttonRateNow.setEnabled(false);

                viewHolder.textViewRatingType.setText("Excellent");

                viewHolder.imageViewRating1.setEnabled(false);
                viewHolder.imageViewRating2.setEnabled(false);
                viewHolder.imageViewRating3.setEnabled(false);
                viewHolder.imageViewRating4.setEnabled(false);
                viewHolder.imageViewRating5.setEnabled(false);


                break;

        }


       /* } else {
            viewHolder.imageViewRating1.setEnabled(false);
            viewHolder.imageViewRating2.setEnabled(false);
            viewHolder.imageViewRating3.setEnabled(false);
            viewHolder.imageViewRating4.setEnabled(false);
            viewHolder.imageViewRating5.setEnabled(false);
            viewHolder.buttonRateNow.setEnabled(false);
        }


        viewHolder.imageViewRating1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countRating = 1;
                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));

            }
        });

        viewHolder.imageViewRating2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countRating = 2;
                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));

            }
        });

        viewHolder.imageViewRating3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countRating = 3;
                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));

            }
        });

        viewHolder.imageViewRating4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countRating = 4;
                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greystar));

            }
        });
        viewHolder.imageViewRating5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                countRating = 5;
                viewHolder.imageViewRating1.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating2.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating3.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating4.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));
                viewHolder.imageViewRating5.setImageDrawable(context.getResources().getDrawable(R.drawable.greenstar));

            }
        });


        viewHolder.buttonRateNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (countRating > 0 && countRating < 6) {
                    if (NetworkUtil.hasConnectivity(context)) {
                        callRatingAPI(myEnquiry.getId(), countRating, position);
                    } else {
                        Toast.makeText(context, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });*/


        viewHolder.textViewUserShowHideDetais.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHolder.linearLayoutEnquiryShowHide.getVisibility() == View.GONE) {
                    viewHolder.textViewUserShowHideDetais.setText("Less Details");
                    viewHolder.linearLayoutEnquiryShowHide.setVisibility(View.VISIBLE);

                } else {
                    viewHolder.textViewUserShowHideDetais.setText("More Details");
                    viewHolder.linearLayoutEnquiryShowHide.setVisibility(View.GONE);

                }
            }
        });

       /* viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, GoOutForLunchDinnerActivity.class);
                context.startActivity(intent);

            }
        });*/


       /*viewHolder.spinnerStatus.setOnItemClickListener(new AdapterView.OnItemClickListener() {
           @Override
           public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               callStatusChangeAPI(orders.getId(),status.getId(),position);
           }
       });*/

    }

    private void initStatusData(ViewHolder viewHolder, final int listItemPosition, final MyOrdersResponse.Kamgaractenquiry orders) {

        if (orders.getWorkstatus().equalsIgnoreCase("Completed") || orders.getWorkstatus().equalsIgnoreCase("Cancelled")) {
            viewHolder.spinnerStatus.setVisibility(View.GONE);
            viewHolder.textViewStatus.setVisibility(View.VISIBLE);
            viewHolder.textViewStatus.setText(orders.getWorkstatus());
        } else {
            viewHolder.spinnerStatus.setVisibility(View.VISIBLE);
            viewHolder.textViewStatus.setVisibility(View.GONE);


            List<MyOrdersResponse.Workstatusselect> finalListOfStatus = new ArrayList<>();
            MyOrdersResponse.Workstatusselect workstatusselect = new MyOrdersResponse.Workstatusselect();
            workstatusselect.setId(-1);
            workstatusselect.setValue(defaultStatus);
            finalListOfStatus.add(workstatusselect);
            finalListOfStatus.addAll(list1);

            ArrayAdapter<MyOrdersResponse.Workstatusselect> dataAdapter = new ArrayAdapter<MyOrdersResponse.Workstatusselect>(context, android.R.layout.simple_spinner_item, finalListOfStatus);
            dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            viewHolder.spinnerStatus.setAdapter(dataAdapter);

            int pos = -1;
            for (int i = 0; i < finalListOfStatus.size(); i++) {
                if (orders.getWorkstatus().equalsIgnoreCase(finalListOfStatus.get(i).getValue())) {
                    pos = i;
                    break;
                }
            }
            viewHolder.spinnerStatus.setSelected(false);
            viewHolder.spinnerStatus.setSelection(pos, true);  //must


            viewHolder.spinnerStatus.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    flagForItemClickNotCalled = true;
                    return false;
                }
            });

            viewHolder.spinnerStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    MyOrdersResponse.Workstatusselect selectedStatus = (MyOrdersResponse.Workstatusselect) parent.getItemAtPosition(position);
                    if (selectedStatus.getId() != -1 && flagForItemClickNotCalled) {
                        flagForItemClickNotCalled = false;
                        callStatusChangeAPI(orders.getId(), selectedStatus.getValue(), listItemPosition, selectedStatus.getId());
                    } else {
                        //   Toast.makeText(context, "Please change the status", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });
        }
    }

    private void callStatusChangeAPI(int orderid, final String workstatus, final int position, final int workstatusid) {

        progressDialogForAPI = new ProgressDialog(context);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        // SharedPreferenceManager.setApplicationContext(KamgarListActivity.this);
        String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();
        String headers = "Bearer " + token;
        Call<KamgarChangeStatus> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).kamgarChangeStatus(headers, orderid + "", workstatusid + "");
        requestCallback.enqueue(new Callback<KamgarChangeStatus>() {
            @Override
            public void onResponse(Call<KamgarChangeStatus> call, Response<KamgarChangeStatus> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarChangeStatus details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details != null) {

                        if (details.getSuccess() != null) {
                            // Toast.makeText(context, "Status changed successfully.", Toast.LENGTH_SHORT).show();
                            Toast.makeText(context, details.getSuccess().getMsg(), Toast.LENGTH_SHORT).show();
                            updateUI(position, workstatus, workstatusid);

                        } else {
                            Toast.makeText(context, "Sorry... Some Error Occured", Toast.LENGTH_SHORT).show();

                        }
                    } else {
                        Toast.makeText(context, "Sorry... Some Error Occured", Toast.LENGTH_SHORT).show();

                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<KamgarChangeStatus> call, Throwable t) {

                if (t != null) {

                    if (progressDialogForAPI != null) {
                        progressDialogForAPI.cancel();
                    }
                    if (t.getMessage() != null)
                        Log.e("error", t.getMessage());
                }

            }
        });


    }

    private void updateUI(int position, String status, int statusId) {
        list.get(position).setWorkstatus(status);
        list.get(position).setWorkStatus(statusId);
        this.notifyDataSetChanged();


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        //Dialog myDialog;

        private TextView textViewUserName;
        private TextView textViewKamgarDesignation;
        private TextView textViewUserDate;
        private TextView textViewRatingType;
        private TextView textViewUserAddress;
        private TextView textViewUserContactNo;
        private TextView textViewUserStatus;
        private TextView textViewUserShowHideDetais;
        private LinearLayout linearLayoutEnquiryShowHide;

        private Spinner spinnerStatus;

        private ImageView imageViewRating1;
        private ImageView imageViewRating2;
        private ImageView imageViewRating3;
        private ImageView imageViewRating4;
        private ImageView imageViewRating5;
        Button buttonRateNow;
        private CardView cardView;
        TextView textViewStatus;

        public ViewHolder(View itemView) {
            super(itemView);

            cardView = (CardView) itemView.findViewById(R.id.cardView);
            textViewUserName = (TextView) itemView.findViewById(R.id.textViewKamgarName);
            textViewKamgarDesignation = (TextView) itemView.findViewById(R.id.textViewKamgarDesignation);
            textViewUserDate = (TextView) itemView.findViewById(R.id.textViewKamgarDate);
            textViewRatingType = (TextView) itemView.findViewById(R.id.textViewRatingType);
            textViewUserAddress = (TextView) itemView.findViewById(R.id.textViewKamgarAddress);
            textViewUserContactNo = (TextView) itemView.findViewById(R.id.textViewKamgarContactNo);
            textViewUserStatus = (TextView) itemView.findViewById(R.id.textViewKamgarStatus);
            textViewUserShowHideDetais = (TextView) itemView.findViewById(R.id.textViewKamgarShowHideDetais);
            linearLayoutEnquiryShowHide = (LinearLayout) itemView.findViewById(R.id.linearLayoutEnquiryShowHide);
            buttonRateNow = (Button) itemView.findViewById(R.id.buttonRateNow);
            imageViewRating1 = (ImageView) itemView.findViewById(R.id.imageViewRating1);
            imageViewRating2 = (ImageView) itemView.findViewById(R.id.imageViewRating2);
            imageViewRating3 = (ImageView) itemView.findViewById(R.id.imageViewRating3);
            imageViewRating4 = (ImageView) itemView.findViewById(R.id.imageViewRating4);
            imageViewRating5 = (ImageView) itemView.findViewById(R.id.imageViewRating5);

            spinnerStatus = (Spinner) itemView.findViewById(R.id.spinnerStatus);
            textViewStatus = (TextView) itemView.findViewById(R.id.textViewStatus);

        }
    }
}
