package com.webakruti.kamgarchowk.kamgarUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.kamgarchowk.LandingActivity;
import com.webakruti.kamgarchowk.userUI.HomeActivity;
import com.webakruti.kamgarchowk.R;

public class KamgarLoginActivity extends AppCompatActivity {
    private TextView textViewGotoKamgarRegistration;
    private Button buttonKamgarLogin;
    private ImageView imageViewBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_login);

        textViewGotoKamgarRegistration = (TextView)findViewById(R.id.textViewGotoKamgarRegistration);
        textViewGotoKamgarRegistration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KamgarLoginActivity.this, KamgarRegistrationActivity.class);
                startActivity(intent);
                finish();
            }
        });

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
        buttonKamgarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KamgarLoginActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
