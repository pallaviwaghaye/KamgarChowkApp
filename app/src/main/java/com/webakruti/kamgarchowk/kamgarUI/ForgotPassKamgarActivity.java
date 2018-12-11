package com.webakruti.kamgarchowk.kamgarUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.userUI.ForgotPasswordUserActivity;
import com.webakruti.kamgarchowk.userUI.UserLoginActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;

public class ForgotPassKamgarActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText editTextKamgarForgotMobno;
    private Button buttonKamgarForgotSendOtp;
    private EditText editTextKamgarForgotOtp;
    private Button buttonSubmitKamgarForgot;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass_kamgar);

        editTextKamgarForgotMobno = (EditText)findViewById(R.id.editTextKamgarForgotMobno);
        editTextKamgarForgotOtp = (EditText)findViewById(R.id.editTextKamgarForgotOtp);

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
                        if(editTextKamgarForgotOtp.getText().toString().length() >= 6) {

                            if (NetworkUtil.hasConnectivity(ForgotPassKamgarActivity.this)) {
                                //callLoginAPI();

                                /*Intent intent = new Intent(ForgotPasswordUserActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();*/
                            } else {
                                Toast.makeText(ForgotPassKamgarActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ForgotPassKamgarActivity.this, "Password must be greater than 6", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ForgotPassKamgarActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgotPassKamgarActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.buttonSubmitKamgarForgot:
                Intent intent3 = new Intent(ForgotPassKamgarActivity.this, KamgarLoginActivity.class);
                startActivity(intent3);
                finish();

                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent new_intent = new Intent(ForgotPassKamgarActivity.this, KamgarLoginActivity.class);
        this.startActivity(new_intent);
    }
}
