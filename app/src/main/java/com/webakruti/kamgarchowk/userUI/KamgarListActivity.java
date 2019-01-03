package com.webakruti.kamgarchowk.userUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.HomeAvailAllServicesAdapter;
import com.webakruti.kamgarchowk.adapter.KamgarListAdapter;
import com.webakruti.kamgarchowk.adapter.UserMyEnquiryAdapter;
import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.model.KamgarResponse;
import com.webakruti.kamgarchowk.model.MyEnquiryResponse;
import com.webakruti.kamgarchowk.model.SubcategoryListResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarListActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private TextView textViewHeading;
    private LinearLayout linearLayoutSubcategorySearch;
    private LinearLayout linearLayoutSort;
    private LinearLayout linearLayoutFilter;
    private LinearLayout linearLayoutShowListView;
    private RecyclerView recyclerView;
    private TextView textViewNoData;

    private SubcategoryListResponse.Subcategory kamgarSubcategory;
    private ProgressDialog progressDialogForAPI;
   // private Integer subcategoryid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_list);

        SharedPreferenceManager.setApplicationContext(KamgarListActivity.this);

        kamgarSubcategory = (SubcategoryListResponse.Subcategory) getIntent().getSerializableExtra("KamgarSubCategory");
        //subcategoryid = (Integer) getIntent().getSerializableExtra("KamgarSubCategory");

        if (NetworkUtil.hasConnectivity(KamgarListActivity.this)) {
            callKamgarAPI();
        } else {
           // Toast.makeText(KamgarListActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();

            new AlertDialog.Builder(KamgarListActivity.this)
                    .setMessage(R.string.no_internet_message)
                    .setPositiveButton("OK", null)
                    .show();
        }

        initViews();

    }

    private void initViews() {

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textViewHeading = (TextView)findViewById(R.id.textViewHeading);
        textViewNoData = (TextView)findViewById(R.id.textViewNoData);
        linearLayoutSubcategorySearch = (LinearLayout)findViewById(R.id.linearLayoutSubcategorySearch);
        linearLayoutSort = (LinearLayout)findViewById(R.id.linearLayoutSort);
        linearLayoutFilter = (LinearLayout)findViewById(R.id.linearLayoutFilter);
        linearLayoutShowListView = (LinearLayout)findViewById(R.id.linearLayoutShowListView);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        if(kamgarSubcategory != null)
        {
            textViewHeading.setText(kamgarSubcategory.getName());
        }

    }


    private void callKamgarAPI() {

        progressDialogForAPI = new ProgressDialog(KamgarListActivity.this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

       // SharedPreferenceManager.setApplicationContext(KamgarListActivity.this);
        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();
        //final Integer userid = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getId();
        Integer subcategoryid = kamgarSubcategory.getId();
        Integer cityid = SharedPreferenceManager.getUserLocationObjectFromSharedPreference().getId();

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<KamgarResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).getkamgarlist(headers,subcategoryid,cityid);
        requestCallback.enqueue(new Callback<KamgarResponse>() {
            @Override
            public void onResponse(Call<KamgarResponse> call, Response<KamgarResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getKamgar() != null && details.getKamgar().size() > 0) {

                        //handleStationPlatformData(details);
                        textViewNoData.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        final List<KamgarResponse.Kamgar> kamgars = details.getKamgar();
                        LinearLayoutManager layoutManager1 = new LinearLayoutManager(KamgarListActivity.this,LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager1);
                        recyclerView.setAdapter(new KamgarListAdapter(KamgarListActivity.this, kamgars));
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
            public void onFailure(Call<KamgarResponse> call, Throwable t) {

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
