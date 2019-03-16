package com.vishwaraj.kamgarchowk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.vishwaraj.kamgarchowk.kamgarUI.KamgarLoginActivity;
import com.vishwaraj.kamgarchowk.userUI.UserLoginActivity;

public class LandingActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout linearLayoutKamgar;
    private LinearLayout linearLayoutUser;

    private LinearLayout linearLayoutAdvertise;
    private LinearLayout linearLayoutFreeListing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        linearLayoutUser = (LinearLayout)findViewById(R.id.linearLayoutUser);
        linearLayoutKamgar = (LinearLayout)findViewById(R.id.linearLayoutKamgar);

        linearLayoutUser.setOnClickListener(this);

        linearLayoutKamgar.setOnClickListener(this);

        linearLayoutAdvertise = (LinearLayout)findViewById(R.id.linearLayoutAdvertise);
        linearLayoutFreeListing = (LinearLayout)findViewById(R.id.linearLayoutFreeListing);

        linearLayoutAdvertise.setOnClickListener(this);

        linearLayoutFreeListing.setOnClickListener(this);
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

            case R.id.linearLayoutAdvertise:
                /*Intent intent2 = new Intent(LandingActivity.this, AdvertiseActivity.class);
                startActivity(intent2);
                finish();*/
                break;
            case R.id.linearLayoutFreeListing:
                Intent intent3 = new Intent(LandingActivity.this, FreeListingActivity.class);
                startActivity(intent3);
                finish();
                break;

        }
    }
}
