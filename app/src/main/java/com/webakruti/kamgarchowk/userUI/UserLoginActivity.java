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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.LandingActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarLoginActivity;
import com.webakruti.kamgarchowk.model.UserLoginResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserLoginActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearLayoutSignUpNow;
    private LinearLayout linearLayoutForgotPassword;
    private RelativeLayout relativeLayoutUserPassword;
    private EditText editTextUserMobileNumber;
    private EditText editTextUserPassword;
    private Button buttonUserLogin;

    private ImageView imageViewBack;
    private TextView textViewGoToRegistration;
    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        SharedPreferenceManager.setApplicationContext(UserLoginActivity.this);

        linearLayoutSignUpNow = (LinearLayout)findViewById(R.id.linearLayoutSignUpNow);
        linearLayoutForgotPassword = (LinearLayout)findViewById(R.id.linearLayoutForgotPassword);
        relativeLayoutUserPassword = (RelativeLayout)findViewById(R.id.relativeLayoutUserPassword);
        editTextUserMobileNumber = (EditText)findViewById(R.id.editTextUserMobileNumber);
        editTextUserPassword = (EditText)findViewById(R.id.editTextUserPassword);
        buttonUserLogin = (Button) findViewById(R.id.buttonUserLogin);

        linearLayoutForgotPassword.setOnClickListener(this);

        textViewGoToRegistration = (TextView)findViewById(R.id.textViewGoToRegistration);
        textViewGoToRegistration.setOnClickListener(this);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserLoginActivity.this, LandingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonUserLogin.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonUserLogin:
                if (editTextUserMobileNumber.getText().toString().length() > 0) {
                    if (editTextUserMobileNumber.getText().toString().length() == 10) {
                        if(editTextUserPassword.getText().toString().length() >= 6) {

                            if (NetworkUtil.hasConnectivity(UserLoginActivity.this)) {
                                callLoginAPI();

                                /*Intent intent = new Intent(UserLoginActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();*/
                            } else {
                                Toast.makeText(UserLoginActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(UserLoginActivity.this, "Password should be greater than 6", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UserLoginActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserLoginActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.textViewGoToRegistration:
                Intent intent2 = new Intent(UserLoginActivity.this, UserRegistrationActivity.class);
                startActivity(intent2);
                finish();

                break;
            case R.id.linearLayoutForgotPassword:
                Intent intent3 = new Intent(UserLoginActivity.this, ForgotPasswordUserActivity.class);
                startActivity(intent3);
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


        Call<UserLoginResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).login(editTextUserMobileNumber.getText().toString(), editTextUserPassword.getText().toString());
        requestCallback.enqueue(new Callback<UserLoginResponse>() {
            @Override
            public void onResponse(Call<UserLoginResponse> call, Response<UserLoginResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    UserLoginResponse result = response.body();
                    if (result.getSuccess().getStatus()) {

                        // Save UserResponse to SharedPref
                        SharedPreferenceManager.storeUserResponseObjectInSharedPreference(result);
                        Intent intent = new Intent(UserLoginActivity.this, CitySelectActivity.class);
                        startActivity(intent);
                        finish();

                    }
                } else {
                    // Response code is 401
                    Toast.makeText(UserLoginActivity.this, "Error!! MobileNo or Password is Not Correct.", Toast.LENGTH_SHORT).show();
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<UserLoginResponse> call, Throwable t) {

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

        Intent new_intent = new Intent(UserLoginActivity.this, LandingActivity.class);

        this.startActivity(new_intent);

    }


}
