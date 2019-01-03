package com.webakruti.kamgarchowk.kamgarUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.KamgarCategoryAdapter;
import com.webakruti.kamgarchowk.adapter.KamgarMyOrdersAdapter;
import com.webakruti.kamgarchowk.model.KamgarCategoryResponse;
import com.webakruti.kamgarchowk.model.MyOrdersResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarMyOrdersActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialogForAPI;
    private TextView textViewNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_my_orders);

        initViews();

        if (NetworkUtil.hasConnectivity(KamgarMyOrdersActivity.this)) {
            callKamgarOrdersAPI();
        } else {
           // Toast.makeText(KamgarMyOrdersActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(KamgarMyOrdersActivity.this)
                    .setMessage(R.string.no_internet_message)
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    private void initViews()
    {

        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textViewNoData = (TextView)findViewById(R.id.textViewNoData);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);


    }

    private void callKamgarOrdersAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(KamgarMyOrdersActivity.this);
        String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();

        String headers = "Bearer " + token;
        Call<MyOrdersResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).kamgaroders(headers);
        requestCallback.enqueue(new Callback<MyOrdersResponse>() {
            @Override
            public void onResponse(Call<MyOrdersResponse> call, Response<MyOrdersResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    MyOrdersResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSuccess().getKamgaractenquiries() != null && details.getSuccess().getKamgaractenquiries().size() > 0) {
                        textViewNoData.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        List<MyOrdersResponse.Kamgaractenquiry> list = details.getSuccess().getKamgaractenquiries();
                        List<MyOrdersResponse.Workstatusselect> list1 = details.getSuccess().getWorkstatusselect();

                        //Toast.makeText(CategoryActivity.this, list.size(),Toast.LENGTH_LONG).show();
                        LinearLayoutManager layoutManager1 = new LinearLayoutManager(KamgarMyOrdersActivity.this,LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager1);
                        recyclerView.setAdapter(new KamgarMyOrdersAdapter(KamgarMyOrdersActivity.this,list,list1));
                    }else{
                        textViewNoData.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<MyOrdersResponse> call, Throwable t) {

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
