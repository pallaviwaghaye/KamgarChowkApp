package com.vishwaraj.kamgarchowk.userUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;

import com.vishwaraj.kamgarchowk.R;
import com.vishwaraj.kamgarchowk.model.SearchLocationList;
import com.vishwaraj.kamgarchowk.retrofit.ApiConstants;
import com.vishwaraj.kamgarchowk.retrofit.service.RestClient;
import com.vishwaraj.kamgarchowk.utils.NetworkUtil;
import com.vishwaraj.kamgarchowk.utils.SharedPreferenceManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CitySelectActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private Spinner spinnerMidPageCity;
    private Button buttonProceedToHome;

    String defaultLocation = "Select city";
    SearchLocationList.Citylist selectedLocation;
    private ProgressDialog progressDialogForAPI;

    Integer cityid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_select);
        SharedPreferenceManager.setApplicationContext(CitySelectActivity.this);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        spinnerMidPageCity = (Spinner)findViewById(R.id.spinnerMidPageCity);
        buttonProceedToHome = (Button)findViewById(R.id.buttonProceedToHome);

        selectedLocation = new SearchLocationList.Citylist();
        selectedLocation.setName(defaultLocation);

        if (NetworkUtil.hasConnectivity(CitySelectActivity.this)) {
            callGetLocationAPI();
        } else {
          //  Toast.makeText(CitySelectActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(CitySelectActivity.this)
                    .setMessage(R.string.no_internet_message)
                    .setPositiveButton("OK", null)
                    .show();
        }


        buttonProceedToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    if (selectedLocation.getName().equalsIgnoreCase(defaultLocation) ) {
                        //Toast.makeText(CitySelectActivity.this, "Please select city", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(CitySelectActivity.this)
                                .setMessage("Please select city")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                    else {
                        SharedPreferenceManager.storeUserLocationResponseInSharedPreference(selectedLocation);
                        Intent intent = new Intent(CitySelectActivity.this, HomeActivity.class);
                        //intent.putExtra("cityId",(Serializable) cityid);
                        startActivity(intent);
                        finish();


                    }

            }
        });


    }


    private void callGetLocationAPI() {
        final ProgressDialog progressDialog = new ProgressDialog(CitySelectActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        SharedPreferenceManager.setApplicationContext(CitySelectActivity.this);
        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();

        String headers = "Bearer " + token;
        Call<SearchLocationList> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).search_location(headers);
        requestCallback.enqueue(new Callback<SearchLocationList>() {
            @Override
            public void onResponse(Call<SearchLocationList> call, Response<SearchLocationList> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    SearchLocationList Location = response.body();

                    if (Location != null) {

                        List<SearchLocationList.Citylist> locationLists = Location.getCitylist();
                        /*ArrayAdapter<SearchLocationList> adapterLocation = new ArrayAdapter<SearchLocationList>(getActivity(), android.R.layout.simple_spinner_dropdown_item, locationLists);
                        spinnerLocation.setAdapter(adapterLocation);*/
                        setSearchLocation(locationLists);
                        //setPlatFormSpinnerData(0, -1); // should be 0





                    }

                } else {
                    // Response code is 401
                }

                if (progressDialog != null) {
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<SearchLocationList> call, Throwable t) {

                if (t != null) {

                    if (progressDialog != null) {
                        progressDialog.cancel();
                    }
                    if (t.getMessage() != null)
                        Log.e("error", t.getMessage());
                }

            }
        });

    }

    private void setSearchLocation(List<SearchLocationList.Citylist> locationList) {

        List<SearchLocationList.Citylist> finalList = new ArrayList<>();
        selectedLocation = new SearchLocationList.Citylist();
        selectedLocation.setId(-1);
        selectedLocation.setName(defaultLocation);

        finalList.add(selectedLocation);
        finalList.addAll(locationList);

        ArrayAdapter<SearchLocationList.Citylist> adapterLocation = new ArrayAdapter<SearchLocationList.Citylist>(CitySelectActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerMidPageCity.setAdapter(adapterLocation);


        spinnerMidPageCity.setSelection(0, true);
        View v = spinnerMidPageCity.getSelectedView();
        //setTextCustom(v);


        spinnerMidPageCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedLocation = (SearchLocationList.Citylist) adapterView.getItemAtPosition(position);
                //cityid = selectedLocation.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}
