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

import com.vishwaraj.kamgarchowk.R;
import com.vishwaraj.kamgarchowk.model.UserForgotPassword;
import com.vishwaraj.kamgarchowk.model.UserForgtPassSndOtpResponse;
import com.vishwaraj.kamgarchowk.retrofit.ApiConstants;
import com.vishwaraj.kamgarchowk.retrofit.service.RestClient;
import com.vishwaraj.kamgarchowk.utils.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPasswordUserActivity extends AppCompatActivity implements View.OnClickListener  {
    private EditText editTextUserForgotMobno;
    private Button buttonUserForgotSendOtp;
    private EditText editTextUserForgotOtp;
    private Button buttonSubmitUserForgot;
    private ImageView imageViewBack;

    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_user);

        editTextUserForgotMobno = (EditText)findViewById(R.id.editTextUserForgotMobno);
        editTextUserForgotOtp = (EditText)findViewById(R.id.editTextUserForgotOtp);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(ForgotPasswordUserActivity.this, UserLoginActivity.class);
                startActivity(new_intent);
                finish();
            }
        });

        buttonUserForgotSendOtp = (Button)findViewById(R.id.buttonUserForgotSendOtp);
        buttonUserForgotSendOtp.setOnClickListener(this);
        buttonSubmitUserForgot = (Button)findViewById(R.id.buttonSubmitUserForgot);
        buttonSubmitUserForgot.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonUserForgotSendOtp:
                if (editTextUserForgotMobno.getText().toString().length() > 0) {
                    if (editTextUserForgotMobno.getText().toString().length() == 10) {
                        //if(editTextUserForgotOtp.getText().toString().length() >= 6) {

                            if (NetworkUtil.hasConnectivity(ForgotPasswordUserActivity.this)) {
                                callOTPAPI();

                                /*Intent intent = new Intent(ForgotPasswordUserActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();*/
                            } else {
                              //  Toast.makeText(ForgotPasswordUserActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                                        .setMessage(R.string.no_internet_message)
                                        .setPositiveButton("OK", null)
                                        .show();
                            }
                       /* }else{
                            Toast.makeText(ForgotPasswordUserActivity.this, "Password must be greater than 6", Toast.LENGTH_SHORT).show();
                        }*/
                    } else {
                     //   Toast.makeText(ForgotPasswordUserActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                                .setMessage("Mobile number must be valid")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                   // Toast.makeText(ForgotPasswordUserActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                            .setMessage("Mobile number Can't be empty")
                            .setPositiveButton("OK", null)
                            .show();
                }

                break;

            case R.id.buttonSubmitUserForgot:
                if (editTextUserForgotMobno.getText().toString().length() > 0) {
                    if (editTextUserForgotMobno.getText().toString().length() == 10) {
                        if(editTextUserForgotOtp.getText().toString().length() >= 6) {

                        if (NetworkUtil.hasConnectivity(ForgotPasswordUserActivity.this)) {
                            callForgotAPI();

                                /*Intent intent = new Intent(ForgotPasswordUserActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();*/
                        } else {
                           // Toast.makeText(ForgotPasswordUserActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                                    .setMessage(R.string.no_internet_message)
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                        }else{
                            //Toast.makeText(ForgotPasswordUserActivity.this, "OTP must be greater than 6", Toast.LENGTH_SHORT).show();

                            new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                                    .setMessage("OTP must be greater than 6.")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } else {
                      //  Toast.makeText(ForgotPasswordUserActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                                .setMessage("Mobile number must be valid")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                   // Toast.makeText(ForgotPasswordUserActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                            .setMessage("Mobile number Can't be empty")
                            .setPositiveButton("OK", null)
                            .show();
                }
                break;
        }
    }


    private void callForgotAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();


        Call<UserForgotPassword> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).forgot(editTextUserForgotMobno.getText().toString(), editTextUserForgotOtp.getText().toString());
        requestCallback.enqueue(new Callback<UserForgotPassword>() {
            @Override
            public void onResponse(Call<UserForgotPassword> call, Response<UserForgotPassword> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    UserForgotPassword result = response.body();
                    if (result.getError() == null && result.getSuccess() != null) {
                        if (result.getSuccess() != null) {

                            /*Toast.makeText(ForgotPasswordUserActivity.this, "New Password sent to your Mobile Number", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPasswordUserActivity.this, UserLoginActivity.class);
                            startActivity(intent);
                            finish();*/

                            new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                                    .setMessage("New Password sent to your Mobile Number")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            Intent intent = new Intent(ForgotPasswordUserActivity.this, UserLoginActivity.class);
                                            startActivity(intent);
                                            finish();

                                        }
                                    })
                                    .show();

                        }
                    } else {
                        // Response code is 401
                       // Toast.makeText(ForgotPasswordUserActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                                .setMessage(result.getError())
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }else{
                    //Toast.makeText(ForgotPasswordUserActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                            .setMessage("Server Error")
                            .setPositiveButton("OK", null)
                            .show();
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<UserForgotPassword> call, Throwable t) {

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


    private void callOTPAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();


        Call<UserForgtPassSndOtpResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).forgototp(editTextUserForgotMobno.getText().toString());
        requestCallback.enqueue(new Callback<UserForgtPassSndOtpResponse>() {
            @Override
            public void onResponse(Call<UserForgtPassSndOtpResponse> call, Response<UserForgtPassSndOtpResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    UserForgtPassSndOtpResponse result = response.body();
                    if (result.getErrors() == null && result.getMsgstatus() != null) {
                        if (result.getMsgstatus()) {

                            //Toast.makeText(ForgotPasswordUserActivity.this,"OTP sent to your mobile number", Toast.LENGTH_LONG).show();
                            new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                                    .setMessage("OTP sent to your mobile number")
                                    .setPositiveButton("OK", null)
                                    .show();
                            //progressDialogForAPI.cancel();
                        }
                    } else {
                        // Response code is 401
                        //Toast.makeText(ForgotPasswordUserActivity.this, result.getErrors().getMobileNo().get(0).toString(), Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(ForgotPasswordUserActivity.this)
                                .setMessage(result.getErrors().getMobileNo().get(0).toString())
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<UserForgtPassSndOtpResponse> call, Throwable t) {

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

    /*@Override
    public void onBackPressed() {
        Intent new_intent = new Intent(ForgotPasswordUserActivity.this, UserLoginActivity.class);
        this.startActivity(new_intent);

    }*/
}
