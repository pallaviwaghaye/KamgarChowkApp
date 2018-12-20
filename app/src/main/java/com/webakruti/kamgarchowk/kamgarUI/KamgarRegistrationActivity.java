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
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.LandingActivity;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.KamgarRegOtp;
import com.webakruti.kamgarchowk.model.KamgarRegistrationResp;
import com.webakruti.kamgarchowk.model.UserForgtPassSndOtpResponse;
import com.webakruti.kamgarchowk.model.UserRegistrationResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.ForgotPasswordUserActivity;
import com.webakruti.kamgarchowk.userUI.UserLoginActivity;
import com.webakruti.kamgarchowk.userUI.UserRegistrationActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarRegistrationActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textViewGoTokamgarLogin;
    private EditText editTextKamgarFirstName;
    private EditText editTextKamgarLastName;
    private EditText editTextKamgarMobileNumber;
    private Button buttonKamgarSendOtp;
    private EditText editTextKamgarOtp;
    private Button buttonKamgarSignup;

    private ProgressDialog progressDialogForAPI;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_registration);

        editTextKamgarFirstName = (EditText)findViewById(R.id.editTextKamgarFirstName);
        editTextKamgarLastName = (EditText)findViewById(R.id.editTextKamgarLastName);
        editTextKamgarMobileNumber = (EditText)findViewById(R.id.editTextKamgarMobileNumber);
        editTextKamgarOtp = (EditText)findViewById(R.id.editTextKamgarOtp);

        buttonKamgarSendOtp = (Button)findViewById(R.id.buttonKamgarSendOtp);
        buttonKamgarSignup = (Button)findViewById(R.id.buttonKamgarSignup);
        textViewGoTokamgarLogin = (TextView)findViewById(R.id.textViewGoTokamgarLogin);
        textViewGoTokamgarLogin.setOnClickListener(this);
        buttonKamgarSendOtp.setOnClickListener(this);
        buttonKamgarSignup.setOnClickListener(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.buttonKamgarSendOtp:
                if (editTextKamgarFirstName.getText().toString().length() > 0) {
                    if (editTextKamgarLastName.getText().toString().length() > 0) {
                        if (editTextKamgarMobileNumber.getText().toString().length() > 0) {
                            if (editTextKamgarMobileNumber.getText().toString().length() == 10) {

                                if (NetworkUtil.hasConnectivity(KamgarRegistrationActivity.this)) {
                                    callOTPAPI();

                                } else {
                                    Toast.makeText(KamgarRegistrationActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(KamgarRegistrationActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(KamgarRegistrationActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(KamgarRegistrationActivity.this, "Last name Can't be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(KamgarRegistrationActivity.this, "First name Can't be empty", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.buttonKamgarSignup:
                if (editTextKamgarFirstName.getText().toString().length() > 0) {
                    if (editTextKamgarLastName.getText().toString().length() > 0) {
                        if (editTextKamgarMobileNumber.getText().toString().length() > 0) {
                            if (editTextKamgarMobileNumber.getText().toString().length() == 10) {
                                if(editTextKamgarOtp.getText().toString().length() == 6) {

                                    if (NetworkUtil.hasConnectivity(KamgarRegistrationActivity.this)) {
                                        callRegistartionAPI();

                                /*Intent intent = new Intent(ForgotPasswordUserActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();*/
                                    } else {
                                        Toast.makeText(KamgarRegistrationActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                    }
                                }else{
                                    Toast.makeText(KamgarRegistrationActivity.this, "OTP must be 6 digits.", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(KamgarRegistrationActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(KamgarRegistrationActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(KamgarRegistrationActivity.this, "Last name Can't be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(KamgarRegistrationActivity.this, "First name Can't be empty", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.textViewGoTokamgarLogin:
                Intent intent = new Intent(KamgarRegistrationActivity.this, KamgarLoginActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }


    private void callOTPAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();


        Call<KamgarRegOtp> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).kamgarRegOTP(editTextKamgarFirstName.getText().toString(),editTextKamgarLastName.getText().toString(),editTextKamgarMobileNumber.getText().toString());
        requestCallback.enqueue(new Callback<KamgarRegOtp>() {
            @Override
            public void onResponse(Call<KamgarRegOtp> call, Response<KamgarRegOtp> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarRegOtp result = response.body();
                    if (result.getErrors() == null && result.getSuccess() != null && result.getMsgstatus() != null) {
                        if (result.getMsgstatus()) {

                            Toast.makeText(KamgarRegistrationActivity.this,"OTP sent to your mobile number", Toast.LENGTH_LONG).show();
                            //progressDialogForAPI.cancel();
                        }
                    } else {
                        // Response code is 401
                        Toast.makeText(KamgarRegistrationActivity.this, result.getErrors().getMobileNo().get(0).toString(), Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Toast.makeText(KamgarRegistrationActivity.this,"Server error !!", Toast.LENGTH_LONG).show();
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<KamgarRegOtp> call, Throwable t) {

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

    private void callRegistartionAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();


        Call<KamgarRegistrationResp> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).kamgarRegistration(editTextKamgarFirstName.getText().toString(),editTextKamgarLastName.getText().toString(),editTextKamgarMobileNumber.getText().toString(),editTextKamgarOtp.getText().toString());
        requestCallback.enqueue(new Callback<KamgarRegistrationResp>() {
            @Override
            public void onResponse(Call<KamgarRegistrationResp> call, Response<KamgarRegistrationResp> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarRegistrationResp result = response.body();
                    if (result.getErrors() == null && result.getSuccess() != null) {
                        if (result.getSuccess() != null ) {

                            Toast.makeText(KamgarRegistrationActivity.this, "Registered Successfully !!", Toast.LENGTH_SHORT).show();

                            Intent intent = new Intent(KamgarRegistrationActivity.this, KamgarLoginActivity.class);
                            //intent.putExtra("MOBILE_NO", editTextUserMobileNumber.getText().toString());
                            startActivity(intent);
                            finish();

                            Toast.makeText(KamgarRegistrationActivity.this, "Password sent to mobile number.", Toast.LENGTH_LONG).show();


                        }
                    } else {
                        Toast.makeText(KamgarRegistrationActivity.this, result.getErrors(), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Response code is 401
                    Toast.makeText(KamgarRegistrationActivity.this, "Unauthorized Kamgar!! Server Error.", Toast.LENGTH_SHORT).show();
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<KamgarRegistrationResp> call, Throwable t) {

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

   /* @Override
    public void onBackPressed() {
        Intent new_intent = new Intent(KamgarRegistrationActivity.this, KamgarLoginActivity.class);
        this.startActivity(new_intent);
    }*/
   @Override
   public void onBackPressed() {

       super.onBackPressed();
   }
}
