package com.webakruti.kamgarchowk;

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

import com.webakruti.kamgarchowk.kamgarUI.KamgarLoginActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarRegistrationActivity;
import com.webakruti.kamgarchowk.model.KamgarRegOtp;
import com.webakruti.kamgarchowk.model.KamgarRegistrationResp;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.NetworkUtil;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FreeListingActivity extends AppCompatActivity implements View.OnClickListener{
   // private TextView textViewGoTokamgarLogin;
    private EditText editTextKamgarFirstName;
    private EditText editTextKamgarLastName;
    private EditText editTextKamgarMobileNumber;
    private Button buttonKamgarSendOtp;
    private EditText editTextKamgarOtp;
    private Button buttonKamgarSignup;
    private ImageView imageViewBack;

    private ProgressDialog progressDialogForAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_listing);

        editTextKamgarFirstName = (EditText)findViewById(R.id.editTextKamgarFirstName);
        editTextKamgarLastName = (EditText)findViewById(R.id.editTextKamgarLastName);
        editTextKamgarMobileNumber = (EditText)findViewById(R.id.editTextKamgarMobileNumber);
        editTextKamgarOtp = (EditText)findViewById(R.id.editTextKamgarOtp);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent new_intent = new Intent(FreeListingActivity.this, LandingActivity.class);
                startActivity(new_intent);
                finish();
            }
        });

        buttonKamgarSendOtp = (Button)findViewById(R.id.buttonKamgarSendOtp);
        buttonKamgarSignup = (Button)findViewById(R.id.buttonKamgarSignup);
        //textViewGoTokamgarLogin = (TextView)findViewById(R.id.textViewGoTokamgarLogin);
        //textViewGoTokamgarLogin.setOnClickListener(this);
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

                                if (NetworkUtil.hasConnectivity(FreeListingActivity.this)) {
                                    callOTPAPI();

                                } else {
                                  //  Toast.makeText(FreeListingActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                    new AlertDialog.Builder(FreeListingActivity.this)
                                            .setMessage(R.string.no_internet_message)
                                            .setPositiveButton("OK", null)
                                            .show();
                                }

                            } else {
                           //     Toast.makeText(FreeListingActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                                new AlertDialog.Builder(FreeListingActivity.this)
                                        .setMessage("Mobile number must be valid")
                                        .setPositiveButton("OK", null)
                                        .show();
                            }
                        } else {
                         //   Toast.makeText(FreeListingActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(FreeListingActivity.this)
                                    .setMessage("Mobile number Can't be empty")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } else {
                     //   Toast.makeText(FreeListingActivity.this, "Last name Can't be empty", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(FreeListingActivity.this)
                                .setMessage("Last name Can't be empty")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                  //  Toast.makeText(FreeListingActivity.this, "First name Can't be empty", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(FreeListingActivity.this)
                            .setMessage("First name Can't be empty")
                            .setPositiveButton("OK", null)
                            .show();
                }

                break;

            case R.id.buttonKamgarSignup:
                if (editTextKamgarFirstName.getText().toString().length() > 0) {
                    if (editTextKamgarLastName.getText().toString().length() > 0) {
                        if (editTextKamgarMobileNumber.getText().toString().length() > 0) {
                            if (editTextKamgarMobileNumber.getText().toString().length() == 10) {
                                if(editTextKamgarOtp.getText().toString().length() == 6) {

                                    if (NetworkUtil.hasConnectivity(FreeListingActivity.this)) {
                                        callRegistartionAPI();
                                /*Intent intent = new Intent(ForgotPasswordUserActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();*/
                                    } else {
                                      //  Toast.makeText(FreeListingActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                        new AlertDialog.Builder(FreeListingActivity.this)
                                                .setMessage(R.string.no_internet_message)
                                                .setPositiveButton("OK", null)
                                                .show();
                                    }
                                }else{
                                   // Toast.makeText(FreeListingActivity.this, "OTP must be 6 digits.", Toast.LENGTH_SHORT).show();
                                    new AlertDialog.Builder(FreeListingActivity.this)
                                            .setMessage("OTP must be 6 digits.")
                                            .setPositiveButton("OK", null)
                                            .show();
                                }
                            } else {
                               // Toast.makeText(FreeListingActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                                new AlertDialog.Builder(FreeListingActivity.this)
                                        .setMessage("Mobile number must be valid")
                                        .setPositiveButton("OK", null)
                                        .show();
                            }
                        } else {
                          //  Toast.makeText(FreeListingActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(FreeListingActivity.this)
                                    .setMessage("Mobile number Can't be empty")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } else {
                      //  Toast.makeText(FreeListingActivity.this, "Last name Can't be empty", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(FreeListingActivity.this)
                                .setMessage("Last name Can't be empty")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                  //  Toast.makeText(FreeListingActivity.this, "First name Can't be empty", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(FreeListingActivity.this)
                            .setMessage("First name Can't be empty")
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


        Call<KamgarRegOtp> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).kamgarRegOTP(editTextKamgarFirstName.getText().toString(),editTextKamgarLastName.getText().toString(),editTextKamgarMobileNumber.getText().toString());
        requestCallback.enqueue(new Callback<KamgarRegOtp>() {
            @Override
            public void onResponse(Call<KamgarRegOtp> call, Response<KamgarRegOtp> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarRegOtp result = response.body();
                    if (result.getErrors() == null && result.getSuccess() != null && result.getMsgstatus() != null) {
                        if (result.getMsgstatus()) {

                            //Toast.makeText(FreeListingActivity.this,"OTP sent to your mobile number", Toast.LENGTH_LONG).show();
                            //progressDialogForAPI.cancel();
                            new AlertDialog.Builder(FreeListingActivity.this)
                                    .setMessage("OTP sent to your mobile number")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } else {
                        // Response code is 401
                       // Toast.makeText(FreeListingActivity.this, result.getErrors().getMobileNo().get(0).toString(), Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(FreeListingActivity.this)
                                .setMessage(result.getErrors().getMobileNo().get(0).toString())
                                .setPositiveButton("OK", null)
                                .show();
                    }
                }else{
                    Toast.makeText(FreeListingActivity.this,"Server error !!", Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(FreeListingActivity.this)
                            .setMessage("Server error !!")
                            .setPositiveButton("OK", null)
                            .show();
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

                            //Toast.makeText(FreeListingActivity.this, "Registered Successfully !!", Toast.LENGTH_SHORT).show();

                            /*Intent intent = new Intent(FreeListingActivity.this, KamgarLoginActivity.class);
                            //intent.putExtra("MOBILE_NO", editTextUserMobileNumber.getText().toString());
                            startActivity(intent);
                            finish();
                            Toast.makeText(FreeListingActivity.this, "Password sent to mobile number.", Toast.LENGTH_LONG).show();
*/

                            new AlertDialog.Builder(FreeListingActivity.this)
                                    .setMessage("Registered Successfully !! Password sent to mobile number.")
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            Intent intent = new Intent(FreeListingActivity.this, KamgarLoginActivity.class);
                                            //intent.putExtra("MOBILE_NO", editTextUserMobileNumber.getText().toString());
                                            startActivity(intent);
                                            finish();
                                        }
                                    })
                                    .show();
                        }
                    } else {
                       // Toast.makeText(FreeListingActivity.this, result.getErrors(), Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(FreeListingActivity.this)
                                .setMessage(R.string.no_internet_message)
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                    // Response code is 401
                  //  Toast.makeText(FreeListingActivity.this, "Unauthorized Kamgar!! Server Error.", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(FreeListingActivity.this)
                            .setMessage("Unauthorized Kamgar!! Server Error.")
                            .setPositiveButton("OK", null)
                            .show();
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
        Intent new_intent = new Intent(FreeListingActivity.this, LandingActivity.class);
        this.startActivity(new_intent);
    }*/
}
