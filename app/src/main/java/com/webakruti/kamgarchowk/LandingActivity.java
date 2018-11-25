package com.webakruti.kamgarchowk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.webakruti.kamgarchowk.kamgarUI.KamgarLoginActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarRegistrationActivity;
import com.webakruti.kamgarchowk.userUI.UserLoginActivity;
import com.webakruti.kamgarchowk.userUI.UserRegistrationActivity;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearLayoutKamgar;
    private LinearLayout linearLayoutUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        linearLayoutUser = (LinearLayout)findViewById(R.id.linearLayoutUser);
        linearLayoutKamgar = (LinearLayout)findViewById(R.id.linearLayoutKamgar);

        linearLayoutUser.setOnClickListener(this);

        linearLayoutKamgar.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.linearLayoutUser:
                Intent intent = new Intent(LandingActivity.this, UserLoginActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.linearLayoutKamgar:
                Intent intent1 = new Intent(LandingActivity.this, KamgarLoginActivity.class);
                startActivity(intent1);
                finish();
                break;

        }
    }
}
