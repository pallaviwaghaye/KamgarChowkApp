package com.webakruti.kamgarchowk.userUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.ChangePasswordResponse;
import com.webakruti.kamgarchowk.model.HireKamgarResponse;
import com.webakruti.kamgarchowk.model.KamgarResponse;
import com.webakruti.kamgarchowk.model.SubcategoryListResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.fragments.MyEnquiryFragment;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HireKamgarActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private TextView textViewHireKamgarHeading;

    private ImageView imageViewKamgarImage;
    private TextView textViewKamgarName;
    private TextView textViewKamgarProfession;
    private TextView textViewAddress;
    private TextView textViewExperience;

    private TextView textViewHourlyPrice;
    private TextView textViewHalfdayPrice;
    private TextView textViewFulldayPrice;
    private TextView textViewWeeklyPrice;
    private TextView textViewMonthlyPrice;

    private ImageView imageViewRating1;
    private ImageView imageViewRating2;
    private ImageView imageViewRating3;
    private ImageView imageViewRating4;
    private ImageView imageViewRating5;

    private Button buttonHire;

    private KamgarResponse.Kamgar kamgar;
    int countRating = 0;

    private ProgressDialog progressDialogForAPI;

    Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_kamgar);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowTitleEnabled(false);

        kamgar = (KamgarResponse.Kamgar) getIntent().getSerializableExtra("kamgar");

        initViews();
    }

    private void initViews()
    {
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textViewHireKamgarHeading = (TextView)findViewById(R.id.textViewHireKamgarHeading);

        imageViewKamgarImage = (ImageView)findViewById(R.id.imageViewKamgarImage);

        imageViewKamgarImage = (ImageView)findViewById(R.id.imageViewKamgarImage);
        textViewKamgarName = (TextView)findViewById(R.id.textViewKamgarName);
        textViewKamgarProfession = (TextView)findViewById(R.id.textViewKamgarProfession);
        textViewAddress = (TextView)findViewById(R.id.textViewAddress);
        textViewExperience = (TextView)findViewById(R.id.textViewExperience);

        textViewHourlyPrice = (TextView)findViewById(R.id.textViewHourlyPrice);
        textViewHalfdayPrice = (TextView)findViewById(R.id.textViewHalfdayPrice);
        textViewFulldayPrice = (TextView)findViewById(R.id.textViewFulldayPrice);
        textViewWeeklyPrice = (TextView)findViewById(R.id.textViewWeeklyPrice);
        textViewMonthlyPrice = (TextView)findViewById(R.id.textViewMonthlyPrice);

        imageViewRating1 = (ImageView)findViewById(R.id.imageViewRating1);
        imageViewRating2 = (ImageView)findViewById(R.id.imageViewRating2);
        imageViewRating3 = (ImageView)findViewById(R.id.imageViewRating3);
        imageViewRating4 = (ImageView)findViewById(R.id.imageViewRating4);
        imageViewRating5 = (ImageView)findViewById(R.id.imageViewRating5);


        textViewKamgarName.setText(kamgar.getFirstName()+" "+kamgar.getLastName());
        textViewKamgarProfession.setText(kamgar.getSubcategory());
        textViewAddress.setText(kamgar.getAddress()+", "+kamgar.getCity());
        textViewExperience.setText(kamgar.getExperience()+"");

        if(kamgar.getHourly() != 0) {
            textViewHourlyPrice.setText(kamgar.getHourly() + "");
        }else{
            textViewHourlyPrice.setText("N/A");
        }
        if(kamgar.getHalfday() != 0) {
            textViewHalfdayPrice.setText(kamgar.getHalfday() + "");
        }else{
            textViewHalfdayPrice.setText("N/A");
        }
        if(kamgar.getFullday() != 0) {
            textViewFulldayPrice.setText(kamgar.getFullday()+"");
        }else{
            textViewFulldayPrice.setText("N/A");
        }
        if(kamgar.getWeekly() != 0) {
            textViewWeeklyPrice.setText(kamgar.getWeekly()+"");
        }else{
            textViewWeeklyPrice.setText("N/A");
        }
        if(kamgar.getMonthly() != 0) {
            textViewMonthlyPrice.setText(kamgar.getMonthly()+"");
        }else{
            textViewMonthlyPrice.setText("N/A");
        }


        switch (kamgar.getRating()) {

            case 0:
                imageViewRating1.setEnabled(false);
                imageViewRating2.setEnabled(false);
                imageViewRating3.setEnabled(false);
                imageViewRating4.setEnabled(false);
                imageViewRating5.setEnabled(false);

                imageViewRating1.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                imageViewRating2.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                imageViewRating3.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                imageViewRating4.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                imageViewRating5.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                //viewHolder.buttonRateNow.setEnabled(false);

                break;

            case 1:

                imageViewRating1.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating2.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                imageViewRating3.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                imageViewRating4.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                imageViewRating5.setImageDrawable(getResources().getDrawable(R.drawable.greystar));

                imageViewRating1.setEnabled(false);
                imageViewRating2.setEnabled(false);
                imageViewRating3.setEnabled(false);
                imageViewRating4.setEnabled(false);
                imageViewRating5.setEnabled(false);



                break;

            case 2:
                imageViewRating1.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating2.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating3.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                imageViewRating4.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                imageViewRating5.setImageDrawable(getResources().getDrawable(R.drawable.greystar));

                imageViewRating1.setEnabled(false);
                imageViewRating2.setEnabled(false);
                imageViewRating3.setEnabled(false);
                imageViewRating4.setEnabled(false);
                imageViewRating5.setEnabled(false);


                break;

            case 3:

                imageViewRating1.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating2.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating3.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating4.setImageDrawable(getResources().getDrawable(R.drawable.greystar));
                imageViewRating5.setImageDrawable(getResources().getDrawable(R.drawable.greystar));

                imageViewRating1.setEnabled(false);
                imageViewRating2.setEnabled(false);
                imageViewRating3.setEnabled(false);
                imageViewRating4.setEnabled(false);
                imageViewRating5.setEnabled(false);


                break;

            case 4:

                imageViewRating1.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating2.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating3.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating4.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating5.setImageDrawable(getResources().getDrawable(R.drawable.greystar));

                imageViewRating1.setEnabled(false);
                imageViewRating2.setEnabled(false);
                imageViewRating3.setEnabled(false);
                imageViewRating4.setEnabled(false);
                imageViewRating5.setEnabled(false);


                break;

            case 5:

                imageViewRating1.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating2.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating3.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating4.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));
                imageViewRating5.setImageDrawable(getResources().getDrawable(R.drawable.greenstar));

                imageViewRating1.setEnabled(false);
                imageViewRating2.setEnabled(false);
                imageViewRating3.setEnabled(false);
                imageViewRating4.setEnabled(false);
                imageViewRating5.setEnabled(false);


                break;

        }


        buttonHire = (Button)findViewById(R.id.buttonHire);
        buttonHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (NetworkUtil.hasConnectivity(HireKamgarActivity.this)) {
                    //callHireKamgarAPI();
                    new AlertDialog.Builder(HireKamgarActivity.this)
                            .setMessage("Thank You for enquiry!!")
                            .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int whichButton) {

                                    Intent intent = new Intent(HireKamgarActivity.this, HomeActivity.class);
                                    intent.putExtra("fromHire", true);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    finish();
                                }
                            })
                            .show();

                }else{
                   // Toast.makeText(HireKamgarActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(HireKamgarActivity.this)
                            .setMessage(R.string.no_internet_message)
                            .setPositiveButton("OK", null)
                            .show();
                }

            }
        });

    }

    private void callHireKamgarAPI()
    {
        /*progressDialogForAPI = new ProgressDialog(HireKamgarActivity.this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();*/

        SharedPreferenceManager.setApplicationContext(HireKamgarActivity.this);

        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();
        Integer userid = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getId();

        Integer kamgarid = kamgar.getKamgarId();
        Integer subcategoryid =kamgar.getSubcategoryId();

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<HireKamgarResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).hirekamgar(headers,kamgarid,userid,subcategoryid);
        requestCallback.enqueue(new Callback<HireKamgarResponse>() {
            @Override
            public void onResponse(Call<HireKamgarResponse> call, Response<HireKamgarResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    HireKamgarResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getMsg() != null) {

                       /* Intent intent = new Intent(HireKamgarActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();*/

                        new AlertDialog.Builder(HireKamgarActivity.this)
                                .setMessage(details.getMsg())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent intent = new Intent(HireKamgarActivity.this, HomeActivity.class);
                                        intent.putExtra("fromHire", true);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .show();

                        //Toast.makeText(HireKamgarActivity.this, details.getMsg(),Toast.LENGTH_LONG).show();

                    }else{
                      //  Toast.makeText(HireKamgarActivity.this, "enquiry not successful",Toast.LENGTH_LONG).show();
                        new AlertDialog.Builder(HireKamgarActivity.this)
                                .setMessage("enquiry not successful")
                                .setPositiveButton("OK", null)
                                .show();
                    }

                } else {
                    // Response code is 401
                  //  Toast.makeText(HireKamgarActivity.this, "enquiry not successful",Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(HireKamgarActivity.this)
                            .setMessage("enquiry not successful")
                            .setPositiveButton("OK", null)
                            .show();
                }

                /*if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }*/
            }

            @Override
            public void onFailure(Call<HireKamgarResponse> call, Throwable t) {

                if (t != null) {

                   /* if (progressDialogForAPI != null) {
                        progressDialogForAPI.cancel();
                    }*/
                    if (t.getMessage() != null)
                        Log.e("error", t.getMessage());
                }

            }
        });

    }


}
