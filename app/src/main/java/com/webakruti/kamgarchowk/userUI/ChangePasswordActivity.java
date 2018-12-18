package com.webakruti.kamgarchowk.userUI;

import android.app.ProgressDialog;
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
import com.webakruti.kamgarchowk.kamgarUI.KamgarChangePasswordActivity;
import com.webakruti.kamgarchowk.model.ChangePasswordResponse;
import com.webakruti.kamgarchowk.model.SupportResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private TextView textViewChangePwdHeading;
    private EditText editTextUserOldPwd;
    private EditText editTextUserNewPwd;
    private EditText editTextUserConfirmNewPwd;
    private Button buttonChangePwd;

    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initViews();
    }

    private void initViews()
    {
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        textViewChangePwdHeading = (TextView)findViewById(R.id.textViewChangePwdHeading);
        editTextUserOldPwd = (EditText)findViewById(R.id.editTextUserOldPwd);
        editTextUserNewPwd = (EditText)findViewById(R.id.editTextUserNewPwd);
        editTextUserConfirmNewPwd = (EditText)findViewById(R.id.editTextUserConfirmNewPwd);
        buttonChangePwd = (Button) findViewById(R.id.buttonChangePwd);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextUserOldPwd.getText().toString().length() > 0) {
                    if (editTextUserNewPwd.getText().toString().length() > 0) {
                        if (editTextUserConfirmNewPwd.getText().toString().length() > 0) {
                            if(editTextUserConfirmNewPwd.getText().toString().equalsIgnoreCase(editTextUserNewPwd.getText().toString())){

                                if (NetworkUtil.hasConnectivity(ChangePasswordActivity.this)) {
                                    callChangePwdAPI();
                                    //Toast.makeText(SupportActivity.this, "Request Sent To Support Team! We Will Contact You Soon!!!",Toast.LENGTH_LONG).show();
                                    /*Intent intent = new Intent(ChangePasswordActivity.this, HomeActivity.class);
                                    startActivity(intent);
                                    finish();*/
                                } else {
                                    Toast.makeText(ChangePasswordActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(ChangePasswordActivity.this, "Password and Confirm password should be same", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(ChangePasswordActivity.this, "Confirm password Can't be Empty", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ChangePasswordActivity.this, "Password Can't be Empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ChangePasswordActivity.this, "Old password Can't be Empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


    private void callChangePwdAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(ChangePasswordActivity.this);

        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();
        Integer userid = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getId();


        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<ChangePasswordResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).changepwd(headers,userid,editTextUserOldPwd.getText().toString(),editTextUserNewPwd.getText().toString(),editTextUserConfirmNewPwd.getText().toString());
        requestCallback.enqueue(new Callback<ChangePasswordResponse>() {
            @Override
            public void onResponse(Call<ChangePasswordResponse> call, Response<ChangePasswordResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    ChangePasswordResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSuccess() != null) {

                        Toast.makeText(ChangePasswordActivity.this, details.getSuccess(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangePasswordActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Toast.makeText(ChangePasswordActivity.this, "Enter correct Old Password",Toast.LENGTH_LONG).show();
                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<ChangePasswordResponse> call, Throwable t) {

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
