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
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.KamgarForgotPwdOtp;
import com.webakruti.kamgarchowk.model.KamgarForgotPwdResp;
import com.webakruti.kamgarchowk.model.UserForgotPassword;
import com.webakruti.kamgarchowk.model.UserForgtPassSndOtpResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.ForgotPasswordUserActivity;
import com.webakruti.kamgarchowk.userUI.UserLoginActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgotPassKamgarActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextKamgarForgotMobno;
    private Button buttonKamgarForgotSendOtp;
    private EditText editTextKamgarForgotOtp;
    private Button buttonSubmitKamgarForgot;
    private ImageView imageViewBack;
    private ProgressDialog progressDialogForAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_kamgar);

        editTextKamgarForgotMobno = (EditText)findViewById(R.id.editTextKamgarForgotMobno);
        editTextKamgarForgotOtp = (EditText)findViewById(R.id.editTextKamgarForgotOtp);
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(ForgotPassKamgarActivity.this, KamgarLoginActivity.class);
                startActivity(new_intent);
                finish();
            }
        });

        buttonKamgarForgotSendOtp = (Button)findViewById(R.id.buttonKamgarForgotSendOtp);
        buttonKamgarForgotSendOtp.setOnClickListener(this);
        buttonSubmitKamgarForgot = (Button)findViewById(R.id.buttonSubmitKamgarForgot);
        buttonSubmitKamgarForgot.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonKamgarForgotSendOtp:
                if (editTextKamgarForgotMobno.getText().toString().length() > 0) {
                    if (editTextKamgarForgotMobno.getText().toString().length() == 10) {
                        //if(editTextUserForgotOtp.getText().toString().length() >= 6) {

                        if (NetworkUtil.hasConnectivity(ForgotPassKamgarActivity.this)) {
                            callOTPAPI();

                                /*Intent intent = new Intent(ForgotPasswordUserActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();*/
                        } else {
                           // Toast.makeText(ForgotPassKamgarActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                                    .setMessage(R.string.no_internet_message)
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                       /* }else{
                            Toast.makeText(ForgotPasswordUserActivity.this, "Password must be greater than 6", Toast.LENGTH_SHORT).show();
                        }*/
                    } else {
                      //  Toast.makeText(ForgotPassKamgarActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                                .setMessage("Mobile number must be valid")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                   // Toast.makeText(ForgotPassKamgarActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                            .setMessage("Mobile number Can't be empty")
                            .setPositiveButton("OK", null)
                            .show();
                }

                break;

            case R.id.buttonSubmitKamgarForgot:
                if (editTextKamgarForgotMobno.getText().toString().length() > 0) {
                    if (editTextKamgarForgotMobno.getText().toString().length() == 10) {
                        if(editTextKamgarForgotOtp.getText().toString().length() >= 6) {

                            if (NetworkUtil.hasConnectivity(ForgotPassKamgarActivity.this)) {
                                callForgotAPI();

                                /*Intent intent = new Intent(ForgotPasswordUserActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();*/
                            } else {
                             //   Toast.makeText(ForgotPassKamgarActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                                        .setMessage(R.string.no_internet_message)
                                        .setPositiveButton("OK", null)
                                        .show();
                            }
                        }else{
                          //  Toast.makeText(ForgotPassKamgarActivity.this, "OTP must be greater than 6", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                                    .setMessage("OTP must be greater than 6")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } else {
                      //  Toast.makeText(ForgotPassKamgarActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                                .setMessage("Mobile number must be valid")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                   // Toast.makeText(ForgotPassKamgarActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                            .setMessage("Mobile number Can't be empty")
                            .setPositiveButton("OK", null)
                            .show();
                }
                break;
        }
    }

    private void callOTPAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();


        Call<KamgarForgotPwdOtp> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).kamgarforgotOTP(editTextKamgarForgotMobno.getText().toString());
        requestCallback.enqueue(new Callback<KamgarForgotPwdOtp>() {
            @Override
            public void onResponse(Call<KamgarForgotPwdOtp> call, Response<KamgarForgotPwdOtp> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarForgotPwdOtp result = response.body();
                    if (result.getErrors() == null && result.getSuccess() != null && result.getMsgstatus()!=null) {
                        if (result.getMsgstatus()) {

                            //Toast.makeText(ForgotPassKamgarActivity.this,"OTP sent to your mobile number!!", Toast.LENGTH_LONG).show();

                            new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                                    .setMessage("OTP sent to your mobile number")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } else {
                        // Response code is 401
                        //Toast.makeText(ForgotPassKamgarActivity.this, result.getErrors().getMobileNo().get(0).toString(), Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(ForgotPassKamgarActivity.this)
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
            public void onFailure(Call<KamgarForgotPwdOtp> call, Throwable t) {

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



    private void callForgotAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();


        Call<KamgarForgotPwdResp> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).kamgarForgot(editTextKamgarForgotMobno.getText().toString(), editTextKamgarForgotOtp.getText().toString());
        requestCallback.enqueue(new Callback<KamgarForgotPwdResp>() {
            @Override
            public void onResponse(Call<KamgarForgotPwdResp> call, Response<KamgarForgotPwdResp> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarForgotPwdResp result = response.body();
                    if (result.getError() == null && result.getSuccess() != null) {
                        if (result.getSuccess() != null) {

                            /*Toast.makeText(ForgotPassKamgarActivity.this, "New Password sent to your Mobile Number", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(ForgotPassKamgarActivity.this, KamgarLoginActivity.class);
                            startActivity(intent);
                            finish();*/

                            new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                                    .setMessage("New Password sent to your Mobile Number")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            Intent intent = new Intent(ForgotPassKamgarActivity.this, KamgarLoginActivity.class);
                                            startActivity(intent);
                                            finish();

                                        }
                                    })
                                    .show();

                        }
                    } else {
                        // Response code is 401
                       // Toast.makeText(ForgotPassKamgarActivity.this, result.getError(), Toast.LENGTH_SHORT).show();

                        new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                                .setMessage(result.getError())
                                .setPositiveButton("Ok", null)
                                .show();
                    }
                }else{
                   // Toast.makeText(ForgotPassKamgarActivity.this, "Server Error", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(ForgotPassKamgarActivity.this)
                            .setMessage("Server Error")
                            .setPositiveButton("OK", null)
                            .show();
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<KamgarForgotPwdResp> call, Throwable t) {

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
        Intent new_intent = new Intent(ForgotPassKamgarActivity.this, KamgarLoginActivity.class);
        this.startActivity(new_intent);
    }*/
}
