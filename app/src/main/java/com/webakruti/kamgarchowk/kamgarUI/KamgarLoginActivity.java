package com.webakruti.kamgarchowk.kamgarUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webakruti.kamgarchowk.LandingActivity;
import com.webakruti.kamgarchowk.userUI.ForgotPasswordUserActivity;
import com.webakruti.kamgarchowk.userUI.HomeActivity;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.userUI.UserLoginActivity;
import com.webakruti.kamgarchowk.userUI.UserRegistrationActivity;

public class KamgarLoginActivity extends AppCompatActivity implements View.OnClickListener{
    private TextView textViewGotoKamgarRegistration;
    private Button buttonKamgarLogin;
    private ImageView imageViewBack;
    private LinearLayout linearLayoutKamgarForgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_login);

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

            case R.id.buttonKamgarLogin:
                Intent intent3 = new Intent(KamgarLoginActivity.this, HomeOrProfileActivity.class);
                startActivity(intent3);
                finish();

                break;
        }
    }

    @Override
    public void onBackPressed() {
        Intent new_intent = new Intent(KamgarLoginActivity.this, LandingActivity.class);
        this.startActivity(new_intent);
    }
}
