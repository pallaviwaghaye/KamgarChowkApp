package com.webakruti.kamgarchowk.userUI;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.webakruti.kamgarchowk.adapter.CategoryAdapter;
import com.webakruti.kamgarchowk.adapter.HomeAvailAllServicesAdapter;
import com.webakruti.kamgarchowk.adapter.KamgarListAdapter;
import com.webakruti.kamgarchowk.adapter.SubcategoryAdapter;
import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.model.SubcategoryListResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SubcategoryActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private TextView textViewSubcategoryHeading;
    private RecyclerView recyclerView;
    private TextView textViewNoData;

    private CategoryList kamgarCategory;
    private SubcategoryAdapter subcategoryAdapter;
    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        //kamgarCategory = (CategoryList) getIntent().getStringExtra("KamgarCategory");

        textViewSubcategoryHeading = (TextView)findViewById(R.id.textViewSubcategoryHeading);
        textViewNoData = (TextView)findViewById(R.id.textViewNoData);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(SubcategoryActivity.this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        //recyclerView.setAdapter(new SubcategoryAdapter(getApplicationContext(), 15));

        callGetKamgarSubcategoryAPI();
    }

    private void callGetKamgarSubcategoryAPI() {


        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(SubcategoryActivity.this);
        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();

        String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<List<SubcategoryListResponse>> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).getsubcategorylist(headers);
        requestCallback.enqueue(new Callback<List<SubcategoryListResponse>>() {
            @Override
            public void onResponse(Call<List<SubcategoryListResponse>> call, Response<List<SubcategoryListResponse>> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    List<SubcategoryListResponse> details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details != null) {

                        List<SubcategoryListResponse> list = details;
                        //Toast.makeText(SubcategoryActivity.this, list.size(),Toast.LENGTH_LONG).show();
                        subcategoryAdapter = new SubcategoryAdapter(getApplicationContext(), list);
                        recyclerView.setAdapter(subcategoryAdapter);
                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<List<SubcategoryListResponse>> call, Throwable t) {

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
