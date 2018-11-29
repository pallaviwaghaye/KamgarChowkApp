package com.webakruti.kamgarchowk.userUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.ForgotPassKamgarActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;

public class ForgotPasswordUserActivity extends AppCompatActivity implements View.OnClickListener  {
    private EditText editTextUserForgotMobno;
    private Button buttonUserForgotSendOtp;
    private EditText editTextUserForgotOtp;
    private Button buttonSubmitUserForgot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password_user);

        editTextUserForgotMobno = (EditText)findViewById(R.id.editTextUserForgotMobno);
        editTextUserForgotOtp = (EditText)findViewById(R.id.editTextUserForgotOtp);

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
                        if(editTextUserForgotOtp.getText().toString().length() >= 6) {

                            if (NetworkUtil.hasConnectivity(ForgotPasswordUserActivity.this)) {
                                //callLoginAPI();

                                /*Intent intent = new Intent(ForgotPasswordUserActivity.this, HomeActivity.class);
                                startActivity(intent);
                                finish();*/
                            } else {
                                Toast.makeText(ForgotPasswordUserActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                            }
                        }else{
                            Toast.makeText(ForgotPasswordUserActivity.this, "Password must be greater than 6", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(ForgotPasswordUserActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(ForgotPasswordUserActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                }

                break;

            case R.id.buttonSubmitUserForgot:
                Intent intent3 = new Intent(ForgotPasswordUserActivity.this, UserLoginActivity.class);
                startActivity(intent3);
                finish();

                break;
        }
    }
}
