package com.webakruti.kamgarchowk.userUI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private ProgressDialog progressDialogForAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_kamgar);

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
        textViewHourlyPrice.setText(kamgar.getHourly()+"");
        textViewHalfdayPrice.setText(kamgar.getHalfday()+"");
        textViewFulldayPrice.setText(kamgar.getFullday()+"");
        textViewWeeklyPrice.setText(kamgar.getWeekly()+"");
        textViewMonthlyPrice.setText(kamgar.getMonthly()+"");


        buttonHire = (Button)findViewById(R.id.buttonHire);
        buttonHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callHireKamgarAPI();
                Intent intent = new Intent(HireKamgarActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void callHireKamgarAPI()
    {
        progressDialogForAPI = new ProgressDialog(HireKamgarActivity.this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(HireKamgarActivity.this);

        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();
        Integer userid = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getId();

        Integer kamgarid = kamgar.getKamgarId();
        Integer subcategoryid =kamgar.getSubcategoryId();

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<HireKamgarResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).hirekamgar(headers,userid,kamgarid,subcategoryid);
        requestCallback.enqueue(new Callback<HireKamgarResponse>() {
            @Override
            public void onResponse(Call<HireKamgarResponse> call, Response<HireKamgarResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    HireKamgarResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getStatus() != null) {

                        Toast.makeText(HireKamgarActivity.this, details.getMsg(),Toast.LENGTH_LONG).show();

                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<HireKamgarResponse> call, Throwable t) {

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


}
