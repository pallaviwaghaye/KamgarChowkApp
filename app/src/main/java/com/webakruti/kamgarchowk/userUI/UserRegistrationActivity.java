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
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.KamgarRegistrationActivity;
import com.webakruti.kamgarchowk.model.UserRegistrationResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textViewGoToLogin;
    private EditText editTextUserFirstName;
    private EditText editTextUserLastName;
    private EditText editTextUserMobileNumber;
    private EditText editTextUserEmail;
    private Button buttonSignUp;

    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_registration);


        editTextUserFirstName = (EditText)findViewById(R.id.editTextUserFirstName);
        editTextUserLastName = (EditText)findViewById(R.id.editTextUserLastName);
        editTextUserEmail = (EditText)findViewById(R.id.editTextUserEmail);
        editTextUserMobileNumber = (EditText)findViewById(R.id.editTextUserMobileNumber);

        buttonSignUp = (Button) findViewById(R.id.buttonSignUp);
        buttonSignUp.setOnClickListener(this);

        textViewGoToLogin = (TextView)findViewById(R.id.textViewGoToLogin);
        textViewGoToLogin.setOnClickListener(this);


    }



    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonSignUp:
                if (editTextUserFirstName.getText().toString().length() > 0) {
                    if (editTextUserLastName.getText().toString().length() > 0) {
                        if (editTextUserMobileNumber.getText().toString().length() > 0) {
                            if(editTextUserMobileNumber.getText().toString().length() == 10) {
                                if (isValidEmailAddress(editTextUserEmail.getText().toString().trim())) {

                                    if (NetworkUtil.hasConnectivity(UserRegistrationActivity.this)) {
                                        //callRegistartionAPI();
                                        Intent intent = new Intent(UserRegistrationActivity.this, UserLoginActivity.class);
                                        //intent.putExtra("MOBILE_NO", editTextUserMobileNumber.getText().toString());
                                        startActivity(intent);
                                        finish();
                                    } else {
                                        Toast.makeText(UserRegistrationActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(UserRegistrationActivity.this, "Email Id must be valid", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                    Toast.makeText(UserRegistrationActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                                Toast.makeText(UserRegistrationActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(UserRegistrationActivity.this, "Last Name Can't be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(UserRegistrationActivity.this, "First Name Can't be empty", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.textViewGoToLogin:
                Intent intent = new Intent(UserRegistrationActivity.this, UserLoginActivity.class);
                startActivity(intent);
                finish();

                break;
        }
    }



    private void callRegistartionAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();


        Call<UserRegistrationResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).registration(editTextUserFirstName.getText().toString(), editTextUserLastName.getText().toString(), editTextUserMobileNumber.getText().toString(), editTextUserEmail.getText().toString());
        requestCallback.enqueue(new Callback<UserRegistrationResponse>() {
            @Override
            public void onResponse(Call<UserRegistrationResponse> call, Response<UserRegistrationResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    UserRegistrationResponse result = response.body();
                    if (result.getError() == null && result.getSuccess() != null) {
                        if (result.getSuccess().getStatus()) {

                            Intent intent = new Intent(UserRegistrationActivity.this, UserLoginActivity.class);
                            //intent.putExtra("MOBILE_NO", editTextUserMobileNumber.getText().toString());
                            startActivity(intent);
                            finish();
                        }
                    } else {
                        Toast.makeText(UserRegistrationActivity.this, result.getError(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Response code is 401
                    Toast.makeText(UserRegistrationActivity.this, "Unauthorized User", Toast.LENGTH_SHORT).show();
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<UserRegistrationResponse> call, Throwable t) {

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



    public boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    @Override
    public void onBackPressed() {

        Intent new_intent = new Intent(UserRegistrationActivity.this, UserLoginActivity.class);

        this.startActivity(new_intent);

    }


}
