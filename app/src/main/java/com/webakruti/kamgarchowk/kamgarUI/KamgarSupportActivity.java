package com.webakruti.kamgarchowk.kamgarUI;

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
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.SupportResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.HomeActivity;
import com.webakruti.kamgarchowk.userUI.SupportActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarSupportActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private TextView textViewSupportHeading;
    private EditText editTextSubject;
    private EditText editTextProblemDetails;
    private Button buttonKamgarSupportSubmit;
    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_support);

        SharedPreferenceManager.setApplicationContext(KamgarSupportActivity.this);

        initViews();
    }

    private void initViews() {
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        textViewSupportHeading = (TextView) findViewById(R.id.textViewSupportHeading);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextProblemDetails = (EditText) findViewById(R.id.editTextProblemDetails);
        buttonKamgarSupportSubmit = (Button) findViewById(R.id.buttonKamgarSupportSubmit);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonKamgarSupportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSubject.getText().toString().length() > 0) {
                    if (editTextProblemDetails.getText().toString().length() > 0) {

                        if (NetworkUtil.hasConnectivity(KamgarSupportActivity.this)) {
                            callSupportAPI();
                            //Toast.makeText(SupportActivity.this, "Request Sent To Support Team! We Will Contact You Soon!!!",Toast.LENGTH_LONG).show();

                        } else {
                           // Toast.makeText(KamgarSupportActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(KamgarSupportActivity.this)
                                    .setMessage(R.string.no_internet_message)
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } else {
                      //  Toast.makeText(KamgarSupportActivity.this, "Problem Detail Can't be Empty", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(KamgarSupportActivity.this)
                                .setMessage("Problem Detail Can't be Empty")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                   // Toast.makeText(KamgarSupportActivity.this, "Subject Can't be Empty", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(KamgarSupportActivity.this)
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

        String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();
        String FirstName = SharedPreferenceManager.getKamgarObject().getSuccess().getAuthkamgar().getFirstName();
        String LastName = SharedPreferenceManager.getKamgarObject().getSuccess().getAuthkamgar().getLastName();
        String ContactNumber = SharedPreferenceManager.getKamgarObject().getSuccess().getAuthkamgar().getMobileNo();

        String headers = "Bearer " + token;
        Call<SupportResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).sendSupportRequest(headers,FirstName,LastName,ContactNumber,editTextSubject.getText().toString(),editTextProblemDetails.getText().toString());
        requestCallback.enqueue(new Callback<SupportResponse>() {
            @Override
            public void onResponse(Call<SupportResponse> call, Response<SupportResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    SupportResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSuccess() != null) {

                        /*Toast.makeText(KamgarSupportActivity.this, details.getSuccess().getMsg(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(KamgarSupportActivity.this, HomeOrProfileActivity.class);
                        startActivity(intent);
                        finish();*/

                        new AlertDialog.Builder(KamgarSupportActivity.this)
                                .setMessage(details.getSuccess().getMsg())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent intent = new Intent(KamgarSupportActivity.this, HomeOrProfileActivity.class);
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
