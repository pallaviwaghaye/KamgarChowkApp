package com.vishwaraj.kamgarchowk.userUI;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.vishwaraj.kamgarchowk.R;
import com.vishwaraj.kamgarchowk.model.SupportResponse;
import com.vishwaraj.kamgarchowk.retrofit.ApiConstants;
import com.vishwaraj.kamgarchowk.retrofit.service.RestClient;
import com.vishwaraj.kamgarchowk.utils.NetworkUtil;
import com.vishwaraj.kamgarchowk.utils.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SupportActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private TextView textViewSupportHeading;
    private EditText editTextSubject;
    private EditText editTextProblemDetails;
    private Button buttonUserSupportSubmit;
    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support);

        SharedPreferenceManager.setApplicationContext(SupportActivity.this);

        initViews();
    }

    private void initViews()
    {
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        textViewSupportHeading = (TextView)findViewById(R.id.textViewSupportHeading);
        editTextSubject = (EditText)findViewById(R.id.editTextSubject);
        editTextProblemDetails = (EditText)findViewById(R.id.editTextProblemDetails);
        buttonUserSupportSubmit = (Button) findViewById(R.id.buttonUserSupportSubmit);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonUserSupportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSubject.getText().toString().length() > 0) {
                    if (editTextProblemDetails.getText().toString().length() > 0) {

                        if (NetworkUtil.hasConnectivity(SupportActivity.this)) {
                            callSupportAPI();
                            //Toast.makeText(SupportActivity.this, "Request Sent To Support Team! We Will Contact You Soon!!!",Toast.LENGTH_LONG).show();

                        } else {
                         //   Toast.makeText(SupportActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(SupportActivity.this)
                                    .setMessage(R.string.no_internet_message)
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } else {
                      //  Toast.makeText(SupportActivity.this, "Problem Detail Can't be Empty", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(SupportActivity.this)
                                .setMessage("Problem Detail Can't be Empty")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                   // Toast.makeText(SupportActivity.this, "Subject Can't be Empty", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(SupportActivity.this)
                            .setMessage("Subject Can't be Empty")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }
        });
    }

    private void callSupportAPI() {


        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        //SharedPreferenceManager.setApplicationContext(SupportActivity.this);

        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();
        String FirstName = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getFirstName();
        String LastName = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getLastName();
        String ContactNumber = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getMobileNo();

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<SupportResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).sendSupportRequest(headers,FirstName,LastName,ContactNumber,editTextSubject.getText().toString(),editTextProblemDetails.getText().toString());
        requestCallback.enqueue(new Callback<SupportResponse>() {
            @Override
            public void onResponse(Call<SupportResponse> call, Response<SupportResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    SupportResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSuccess() != null) {

                        /*Toast.makeText(SupportActivity.this, details.getSuccess().getMsg(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SupportActivity.this, HomeActivity.class);
                        startActivity(intent);
                        finish();*/

                        new AlertDialog.Builder(SupportActivity.this)
                                .setMessage(details.getSuccess().getMsg())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent intent = new Intent(SupportActivity.this, HomeActivity.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                })
                                .show();
                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<SupportResponse> call, Throwable t) {

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
