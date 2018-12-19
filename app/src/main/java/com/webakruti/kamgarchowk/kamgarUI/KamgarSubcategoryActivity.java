package com.webakruti.kamgarchowk.kamgarUI;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.KamgarSubcategoryAdapter;
import com.webakruti.kamgarchowk.adapter.SubcategoryAdapter;
import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.model.KamgarCategoryResponse;
import com.webakruti.kamgarchowk.model.KamgarSubcategoriesResponse;
import com.webakruti.kamgarchowk.model.SubcategoryListResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.SubcategoryActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarSubcategoryActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private TextView textViewCategoryHeading;
    private RecyclerView recyclerView;
    private TextView textViewNoData;
    private Button buttonDoneSubcategory;
    private ProgressDialog progressDialogForAPI;

    private KamgarCategoryResponse.Kamgarcategorylist kamgarCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_subcategory);

        kamgarCategory = (KamgarCategoryResponse.Kamgarcategorylist) getIntent().getSerializableExtra("KamgarCategory");

        textViewCategoryHeading = (TextView)findViewById(R.id.textViewCategoryHeading);
        textViewNoData = (TextView)findViewById(R.id.textViewNoData);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        textViewCategoryHeading.setText(kamgarCategory.getName());

        if (NetworkUtil.hasConnectivity(KamgarSubcategoryActivity.this)) {
            callGetKamgarSubcategoryAPI();
        } else {
            Toast.makeText(KamgarSubcategoryActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
        }

    }


    private void callGetKamgarSubcategoryAPI() {


        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(KamgarSubcategoryActivity.this);
        String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();

        Integer categoryid = kamgarCategory.getId();

        String headers = "Bearer " + token;
        Call<KamgarSubcategoriesResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).getKamgarSubcat(headers,categoryid);
        requestCallback.enqueue(new Callback<KamgarSubcategoriesResponse>() {
            @Override
            public void onResponse(Call<KamgarSubcategoriesResponse> call, Response<KamgarSubcategoriesResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarSubcategoriesResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSubcategory() != null && details.getSubcategory().size() > 0) {
                        textViewNoData.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        List<KamgarSubcategoriesResponse.Subcategory> list = details.getSubcategory();
                        //Toast.makeText(SubcategoryActivity.this, list.size(),Toast.LENGTH_LONG).show();
                        LinearLayoutManager layoutManager1 = new LinearLayoutManager(KamgarSubcategoryActivity.this,LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager1);
                        recyclerView.setAdapter(new KamgarSubcategoryAdapter(KamgarSubcategoryActivity.this, list, recyclerView,kamgarCategory.getId()));
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
            public void onFailure(Call<KamgarSubcategoriesResponse> call, Throwable t) {

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
