package com.webakruti.kamgarchowk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class FreeListingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_free_listing);
    }

    @Override
    public void onBackPressed() {
        Intent new_intent = new Intent(FreeListingActivity.this, LandingActivity.class);
        this.startActivity(new_intent);
    }
}
