package com.webakruti.kamgarchowk.kamgarUI;

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
import com.webakruti.kamgarchowk.adapter.CategoryAdapter;
import com.webakruti.kamgarchowk.adapter.KamgarCategoryAdapter;
import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.model.KamgarCategoryResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.CategoryActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarCategoryActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private RecyclerView recyclerView;
    private TextView textViewNoData;
    private KamgarCategoryAdapter kamgarCategoryAdapter;

    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_category);

        initViews();

        if (NetworkUtil.hasConnectivity(KamgarCategoryActivity.this)) {
            callGetKamgarCategoryAPI();
        } else {
            Toast.makeText(KamgarCategoryActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
        }
    }

    private void initViews() {
        textViewNoData = (TextView)findViewById(R.id.textViewNoData);
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);



    }

    private void callGetKamgarCategoryAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(KamgarCategoryActivity.this);
        String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();

        String headers = "Bearer " + token;
        Call<KamgarCategoryResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).getkamgarCategory(headers);
        requestCallback.enqueue(new Callback<KamgarCategoryResponse>() {
            @Override
            public void onResponse(Call<KamgarCategoryResponse> call, Response<KamgarCategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarCategoryResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getKamgarcategorylist() != null && details.getKamgarcategorylist().size() > 0) {
                        textViewNoData.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        List<KamgarCategoryResponse.Kamgarcategorylist> list = details.getKamgarcategorylist();
                        //Toast.makeText(CategoryActivity.this, list.size(),Toast.LENGTH_LONG).show();
                        LinearLayoutManager layoutManager1 = new LinearLayoutManager(KamgarCategoryActivity.this,LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager1);
                        recyclerView.setAdapter(new KamgarCategoryAdapter(KamgarCategoryActivity.this, list));
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
            public void onFailure(Call<KamgarCategoryResponse> call, Throwable t) {

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
