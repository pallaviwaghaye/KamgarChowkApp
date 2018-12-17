package com.webakruti.kamgarchowk.kamgarUI;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.LandingActivity;
import com.webakruti.kamgarchowk.model.KamgarLoginResponse;
import com.webakruti.kamgarchowk.model.UserLoginResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.CitySelectActivity;
import com.webakruti.kamgarchowk.userUI.ForgotPasswordUserActivity;
import com.webakruti.kamgarchowk.userUI.HomeActivity;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.userUI.UserLoginActivity;
import com.webakruti.kamgarchowk.userUI.UserRegistrationActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textViewGotoKamgarRegistration;
    private Button buttonKamgarLogin;
    private ImageView imageViewBack;
    private LinearLayout linearLayoutKamgarForgotPassword;
    private EditText editTextKamgarMobNo;
    private EditText editTextKamgarPassword;

    private ProgressDialog progressDialogForAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_login);

        editTextKamgarMobNo = (EditText)findViewById(R.id.editTextKamgarMobNo);
        editTextKamgarPassword = (EditText)findViewById(R.id.editTextKamgarPassword);

        textViewGotoKamgarRegistration = (TextView)findViewById(R.id.textViewGotoKamgarRegistration);
        textViewGotoKamgarRegistration.setOnClickListener(this);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KamgarLoginActivity.this, LandingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonKamgarLogin = (Button)findViewById(R.id.buttonKamgarLogin);
        buttonKamgarLogin.setOnClickListener(this);

        linearLayoutKamgarForgotPassword = (LinearLayout)findViewById(R.id.linearLayoutKamgarForgotPassword);
        linearLayoutKamgarForgotPassword.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonKamgarLogin:
                if (editTextKamgarMobNo.getText().toString().length() > 0) {
                    if (editTextKamgarMobNo.getText().toString().length() == 10) {
                        if(editTextKamgarPassword.getText().toString().length() >= 6) {

                            if (NetworkUtil.hasConnectivity(KamgarLoginActivity.this)) {
                                callLoginAPI();

                                /*Intent intent = new Intent(UserLoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();*/
                            } else {
                                Toast.makeText(KamgarLoginActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(KamgarLoginActivity.this, "Password should be greater than 6", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(KamgarLoginActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(KamgarLoginActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.textViewGotoKamgarRegistration:
                Intent intent1 = new Intent(KamgarLoginActivity.this, KamgarRegistrationActivity.class);
                startActivity(intent1);
                finish();

                break;
            case R.id.linearLayoutKamgarForgotPassword:
                Intent intent2 = new Intent(KamgarLoginActivity.this, ForgotPassKamgarActivity.class);
                startActivity(intent2);
                finish();

                break;
        }
    }

    private void callLoginAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();


        Call<KamgarLoginResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).kamgarLogin(editTextKamgarMobNo.getText().toString(), editTextKamgarPassword.getText().toString());
        requestCallback.enqueue(new Callback<KamgarLoginResponse>() {
            @Override
            public void onResponse(Call<KamgarLoginResponse> call, Response<KamgarLoginResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarLoginResponse result = response.body();
                    if (result.getSuccess().getStatus()) {

                        // Save KamgarResponse to SharedPref
                        SharedPreferenceManager.storeKamgarObject(result);
                        Intent intent = new Intent(KamgarLoginActivity.this, HomeOrProfileActivity.class);
                        startActivity(intent);
                        finish();

                    }
                } else {
                    // Response code is 401
                    Toast.makeText(KamgarLoginActivity.this, "Unauthorized Kamgar!! MobileNo or Password is Not Correct.", Toast.LENGTH_SHORT).show();
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<KamgarLoginResponse> call, Throwable t) {

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

    @Override
    public void onBackPressed() {
        Intent new_intent = new Intent(KamgarLoginActivity.this, LandingActivity.class);
        this.startActivity(new_intent);
    }
}
