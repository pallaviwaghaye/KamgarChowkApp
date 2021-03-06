package com.vishwaraj.kamgarchowk.userUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishwaraj.kamgarchowk.R;
import com.vishwaraj.kamgarchowk.adapter.CategoryAdapter;
import com.vishwaraj.kamgarchowk.model.CategoryList;
import com.vishwaraj.kamgarchowk.retrofit.ApiConstants;
import com.vishwaraj.kamgarchowk.retrofit.service.RestClient;
import com.vishwaraj.kamgarchowk.utils.GridSpacingItemDecoration;
import com.vishwaraj.kamgarchowk.utils.NetworkUtil;
import com.vishwaraj.kamgarchowk.utils.SharedPreferenceManager;
import com.vishwaraj.kamgarchowk.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    private ProgressDialog progressDialogForAPI;
    private TextView textViewNoData;
    private ImageView imageViewBack;
    private TextView textViewHeading;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initViews();
        if (NetworkUtil.hasConnectivity(CategoryActivity.this)) {
            callGetKamgarCategoryAPI();
        } else {
           // Toast.makeText(CategoryActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(CategoryActivity.this)
                    .setMessage(R.string.no_internet_message)
                    .setPositiveButton("OK", null)
                    .show();
        }
    }

    private void callGetKamgarCategoryAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(CategoryActivity.this);
        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();

        String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
            Call<CategoryList> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).getcategorylist(headers);
            requestCallback.enqueue(new Callback<CategoryList>() {
            @Override
            public void onResponse(Call<CategoryList> call, Response<CategoryList> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    CategoryList details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getCategorylist() != null && details.getCategorylist().size() > 0) {
                        textViewNoData.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        List<CategoryList.Categorylist> list = details.getCategorylist();
                        //Toast.makeText(CategoryActivity.this, list.size(),Toast.LENGTH_LONG).show();
                        categoryAdapter = new CategoryAdapter(CategoryActivity.this, list);
                        recyclerView.setAdapter(categoryAdapter);
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
            public void onFailure(Call<CategoryList> call, Throwable t) {

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

    private void initViews() {

        textViewNoData = (TextView)findViewById(R.id.textViewNoData);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        textViewHeading = (TextView)findViewById(R.id.textViewHeading);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(CategoryActivity.this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        int spacing = (int) Utils.DpToPixel(CategoryActivity.this, 11); // 40px

        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(2, spacing, true);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setNestedScrollingEnabled(false);


        //recyclerView.setAdapter(new CategoryAdapter(getApplicationContext(), 11));


       /* List<Category> listOfCategories = new ArrayList<Category>();
        listOfCategories.add(new Category("Waiting Hall", getResources().getDrawable(R.drawable.icons_01)));
        listOfCategories.add(new Category("Urinal", getResources().getDrawable(R.drawable.icons_02)));
        listOfCategories.add(new Category("Lavatories", getResources().getDrawable(R.drawable.icons_03)));
        listOfCategories.add(new Category("Divyanhjan Toilet", getResources().getDrawable(R.drawable.icons_10)));
        listOfCategories.add(new Category("Foot Over Bridge", getResources().getDrawable(R.drawable.icons_09)));
        listOfCategories.add(new Category("Water Cooler", getResources().getDrawable(R.drawable.icons_08)));
        listOfCategories.add(new Category("Parking", getResources().getDrawable(R.drawable.icons_07)));
        listOfCategories.add(new Category("Dustbin", getResources().getDrawable(R.drawable.icons_06)));
        listOfCategories.add(new Category("Catering", getResources().getDrawable(R.drawable.icons_05)));
        listOfCategories.add(new Category("Waiting Room", getResources().getDrawable(R.drawable.icons_01)));
        listOfCategories.add(new Category("Any Other Places", getResources().getDrawable(R.drawable.icons_04)));*/


    }




}
