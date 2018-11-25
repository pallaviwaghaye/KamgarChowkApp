package com.webakruti.kamgarchowk.userUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.webakruti.kamgarchowk.LandingActivity;
import com.webakruti.kamgarchowk.userUI.HomeActivity;
import com.webakruti.kamgarchowk.R;

public class UserLoginActivity extends AppCompatActivity {
    private LinearLayout linearLayoutSignUpNow;
    private LinearLayout linearLayoutForgotPassword;
    private RelativeLayout relativeLayoutUserPassword;
    private EditText editTextUserMobileNumber;
    private EditText editTextUserPassword;
    private Button buttonUserLogin;

    private ImageView imageViewBack;

    private TextView textViewGoToRegistration;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_login);

        linearLayoutSignUpNow = (LinearLayout)findViewById(R.id.linearLayoutSignUpNow);
        linearLayoutForgotPassword = (LinearLayout)findViewById(R.id.linearLayoutForgotPassword);
        relativeLayoutUserPassword = (RelativeLayout)findViewById(R.id.relativeLayoutUserPassword);
        editTextUserMobileNumber = (EditText)findViewById(R.id.editTextUserMobileNumber);
        editTextUserPassword = (EditText)findViewById(R.id.editTextUserPassword);
        buttonUserLogin = (Button) findViewById(R.id.buttonUserLogin);

        textViewGoToRegistration = (TextView)findViewById(R.id.textViewGoToRegistration);
        textViewGoToRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, UserRegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, LandingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonUserLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserLoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
