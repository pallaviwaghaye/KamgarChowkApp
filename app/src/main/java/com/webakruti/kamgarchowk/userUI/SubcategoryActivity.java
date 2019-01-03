package com.webakruti.kamgarchowk.userUI;

import android.app.AlertDialog;
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
import com.webakruti.kamgarchowk.model.HomeResponse;
import com.webakruti.kamgarchowk.model.SubcategoryListResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
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

    private CategoryList.Categorylist kamgarCategory;
    private HomeResponse.Featuredlist featuredCategory;
    private HomeResponse.Popularlist popularCategory;
    private HomeResponse.Workavllist workavlCategory;
    private SubcategoryAdapter subcategoryAdapter;
    private ProgressDialog progressDialogForAPI;

    Integer catrgoryid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        kamgarCategory = (CategoryList.Categorylist) getIntent().getSerializableExtra("KamgarCategory");
        featuredCategory = (HomeResponse.Featuredlist) getIntent().getSerializableExtra("FeaturedCategory");
        popularCategory = (HomeResponse.Popularlist) getIntent().getSerializableExtra("PopularCategory");
        workavlCategory = (HomeResponse.Workavllist) getIntent().getSerializableExtra("WorkAvlCategory");

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

        if(kamgarCategory != null)
        {
            textViewSubcategoryHeading.setText(kamgarCategory.getName());
        }
        else if(featuredCategory != null)
        {
            textViewSubcategoryHeading.setText(featuredCategory.getName());
        }
        else if(popularCategory != null)
        {
            textViewSubcategoryHeading.setText(popularCategory.getName());
        }
        else{
            textViewSubcategoryHeading.setText(workavlCategory.getName());
        }

        if (NetworkUtil.hasConnectivity(SubcategoryActivity.this)) {
            callGetKamgarSubcategoryAPI();
        } else {
          //Toast.makeText(SubcategoryActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(SubcategoryActivity.this)
                    .setMessage(R.string.no_internet_message)
                    .setPositiveButton("OK", null)
                    .show();
        }



    }

    private void callGetKamgarSubcategoryAPI() {


        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(SubcategoryActivity.this);
        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();
        if(kamgarCategory != null)
        {
            catrgoryid = kamgarCategory.getId();
        }
        else if(featuredCategory != null)
        {
            catrgoryid = featuredCategory.getId();
        }
        else if(popularCategory != null)
        {
            catrgoryid = popularCategory.getId();
        }
        else{
            catrgoryid = workavlCategory.getId();
        }

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<SubcategoryListResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).getsubcategorylist(headers,catrgoryid);
        requestCallback.enqueue(new Callback<SubcategoryListResponse>() {
            @Override
            public void onResponse(Call<SubcategoryListResponse> call, Response<SubcategoryListResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    SubcategoryListResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSubcategory() != null && details.getSubcategory().size() > 0) {
                        textViewNoData.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        List<SubcategoryListResponse.Subcategory> list = details.getSubcategory();
                        //Toast.makeText(SubcategoryActivity.this, list.size(),Toast.LENGTH_LONG).show();
                        subcategoryAdapter = new SubcategoryAdapter(SubcategoryActivity.this, list);
                        recyclerView.setAdapter(subcategoryAdapter);
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
            public void onFailure(Call<SubcategoryListResponse> call, Throwable t) {

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
