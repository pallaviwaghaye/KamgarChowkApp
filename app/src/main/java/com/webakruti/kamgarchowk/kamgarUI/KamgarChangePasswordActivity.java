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
import com.webakruti.kamgarchowk.model.ChangePasswordResponse;
import com.webakruti.kamgarchowk.model.KamgarChangePwdResp;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.ChangePasswordActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarChangePasswordActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private EditText editTextKamgarOldPwd;
    private EditText editTextKamgarNewPwd;
    private EditText editTextKamgarConfirmNewPwd;
    private Button buttonKamgarChangePwd;
    private TextView textViewChangePwdHeading;

    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_change_password);

        SharedPreferenceManager.setApplicationContext(KamgarChangePasswordActivity.this);

        initViews();
    }

    private void initViews()
    {
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        textViewChangePwdHeading = (TextView)findViewById(R.id.textViewChangePwdHeading);
        editTextKamgarOldPwd = (EditText)findViewById(R.id.editTextKamgarOldPwd);
        editTextKamgarNewPwd = (EditText)findViewById(R.id.editTextKamgarNewPwd);
        editTextKamgarConfirmNewPwd = (EditText)findViewById(R.id.editTextKamgarConfirmNewPwd);
        buttonKamgarChangePwd = (Button) findViewById(R.id.buttonKamgarChangePwd);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonKamgarChangePwd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextKamgarOldPwd.getText().toString().length() >= 6) {
                    if (editTextKamgarNewPwd.getText().toString().length() >= 6) {
                        if (editTextKamgarConfirmNewPwd.getText().toString().length() >= 6) {
                            if(editTextKamgarConfirmNewPwd.getText().toString().equalsIgnoreCase(editTextKamgarNewPwd.getText().toString())){

                                if (NetworkUtil.hasConnectivity(KamgarChangePasswordActivity.this)) {
                                    callChangePwdAPI();
                                    //Toast.makeText(SupportActivity.this, "Request Sent To Support Team! We Will Contact You Soon!!!",Toast.LENGTH_LONG).show();
                                    /*Intent intent = new Intent(KamgarChangePasswordActivity.this, HomeOrProfileActivity.class);
                                    startActivity(intent);
                                    finish();*/
                                } else {
                                   // Toast.makeText(KamgarChangePasswordActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                    new AlertDialog.Builder(KamgarChangePasswordActivity.this)
                                            .setMessage(R.string.no_internet_message)
                                            .setPositiveButton("OK", null)
                                            .show();
                                }
                            } else {
                              //  Toast.makeText(KamgarChangePasswordActivity.this, "Password and Confirm password should be same", Toast.LENGTH_SHORT).show();
                                new AlertDialog.Builder(KamgarChangePasswordActivity.this)
                                        .setMessage("New password and Confirm password should be same")
                                        .setPositiveButton("OK", null)
                                        .show();
                            }
                        } else {
                           // Toast.makeText(KamgarChangePasswordActivity.this, "Confirm password must be greater than 6", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(KamgarChangePasswordActivity.this)
                                    .setMessage("Confirm password must be greater than 6")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } else {
                      //  Toast.makeText(KamgarChangePasswordActivity.this, "New Password must be greater than 6", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(KamgarChangePasswordActivity.this)
                                .setMessage("New Password must be greater than 6")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                   // Toast.makeText(KamgarChangePasswordActivity.this, "Old password must be greater than 6", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(KamgarChangePasswordActivity.this)
                            .setMessage("Old password must be greater than 6")
                            .setPositiveButton("OK", null)
                            .show();
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


        String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();
        Integer kamgarid = SharedPreferenceManager.getKamgarObject().getSuccess().getAuthkamgar().getId();


        String headers = "Bearer " + token;
        Call<KamgarChangePwdResp> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).kamgarChangepwd(headers,kamgarid,editTextKamgarOldPwd.getText().toString(),editTextKamgarNewPwd.getText().toString(),editTextKamgarConfirmNewPwd.getText().toString());
        requestCallback.enqueue(new Callback<KamgarChangePwdResp>() {
            @Override
            public void onResponse(Call<KamgarChangePwdResp> call, Response<KamgarChangePwdResp> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarChangePwdResp details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSuccess() != null) {

                        /*Toast.makeText(KamgarChangePasswordActivity.this, details.getSuccess(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(KamgarChangePasswordActivity.this,HomeOrProfileActivity.class);
                        startActivity(intent);
                        finish();*/

                        new AlertDialog.Builder(KamgarChangePasswordActivity.this)
                                .setMessage(details.getSuccess())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent intent = new Intent(KamgarChangePasswordActivity.this,HomeOrProfileActivity.class);
                                        startActivity(intent);
                                        finish();

                                    }
                                })
                                .show();

                    }else{
                       // Toast.makeText(KamgarChangePasswordActivity.this, "Enter correct Old Password",Toast.LENGTH_LONG).show();
                        new AlertDialog.Builder(KamgarChangePasswordActivity.this)
                                .setMessage("Enter correct Old Password")
                                .setPositiveButton("OK", null)
                                .show();
                    }

                } else {
                    // Response code is 401
                 //   Toast.makeText(KamgarChangePasswordActivity.this, "Server error,Password not changed.",Toast.LENGTH_LONG).show();
                    new AlertDialog.Builder(KamgarChangePasswordActivity.this)
                            .setMessage("Server error,Password not changed.")
                            .setPositiveButton("OK", null)
                            .show();
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<KamgarChangePwdResp> call, Throwable t) {

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
