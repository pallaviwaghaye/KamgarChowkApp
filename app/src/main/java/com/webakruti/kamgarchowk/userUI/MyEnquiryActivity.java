package com.webakruti.kamgarchowk.userUI;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.UserMyEnquiryAdapter;
import com.webakruti.kamgarchowk.model.MyEnquiryResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyEnquiryActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private TextView textViewMyEnquiryHeading;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_enquiry);

        initViews();

        SharedPreferenceManager.setApplicationContext(MyEnquiryActivity.this);
        if (NetworkUtil.hasConnectivity(MyEnquiryActivity.this)) {
            callMyenquiryAPI();
        } else {
            Toast.makeText(MyEnquiryActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews()
    {
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        textViewMyEnquiryHeading = (TextView)findViewById(R.id.textViewMyEnquiryHeading);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

    }


    private void callMyenquiryAPI() {

        progressDialogForAPI = new ProgressDialog(MyEnquiryActivity.this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(MyEnquiryActivity.this);
        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();
        final Integer userid = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getId();

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<MyEnquiryResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).myenquiry(headers,userid);
        requestCallback.enqueue(new Callback<MyEnquiryResponse>() {
            @Override
            public void onResponse(Call<MyEnquiryResponse> call, Response<MyEnquiryResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    MyEnquiryResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details != null) {

                        //handleStationPlatformData(details);

                        final List<MyEnquiryResponse.Userenquiry> myEnquiry = details.getSuccess().getUserenquiry();
                        LinearLayoutManager layoutManager1 = new LinearLayoutManager(MyEnquiryActivity.this,LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager1);
                        recyclerView.setAdapter(new UserMyEnquiryAdapter(getApplicationContext(), myEnquiry));
                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<MyEnquiryResponse> call, Throwable t) {

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
