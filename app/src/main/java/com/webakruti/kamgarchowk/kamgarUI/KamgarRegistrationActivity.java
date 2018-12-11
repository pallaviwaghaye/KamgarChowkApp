package com.webakruti.kamgarchowk.kamgarUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.kamgarchowk.LandingActivity;
import com.webakruti.kamgarchowk.R;

public class KamgarRegistrationActivity extends AppCompatActivity {
    private TextView textViewGoTokamgarLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_registration);

        textViewGoTokamgarLogin = (TextView)findViewById(R.id.textViewGoTokamgarLogin);
        textViewGoTokamgarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KamgarRegistrationActivity.this, KamgarLoginActivity.class);
                startActivity(intent);
                finish();
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent new_intent = new Intent(KamgarRegistrationActivity.this, KamgarLoginActivity.class);
        this.startActivity(new_intent);
    }
}
