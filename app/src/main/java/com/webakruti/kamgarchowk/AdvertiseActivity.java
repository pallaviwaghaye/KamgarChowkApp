package com.webakruti.kamgarchowk;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class AdvertiseActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private TextView textViewAdvertiseHeading;

    private EditText editTextFirstname;
    private EditText editTextLastname;
    private EditText editTextFarmCompanyname;
    private EditText editTextMobileNo;
    private EditText editTextEmailId;

    private Spinner spinnerCountry;
    private Spinner spinnerState;
    private Spinner spinnerCity;


    private Button buttonContinue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advertise);

        initViews();
    }

    private void initViews()
    {
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        textViewAdvertiseHeading = (TextView)findViewById(R.id.textViewAdvertiseHeading);
        editTextFirstname = (EditText)findViewById(R.id.editTextFirstname);
        editTextLastname = (EditText)findViewById(R.id.editTextLastname);
        editTextFarmCompanyname = (EditText)findViewById(R.id.editTextFarmCompanyname);
        editTextMobileNo = (EditText)findViewById(R.id.editTextMobileNo);
        editTextEmailId = (EditText)findViewById(R.id.editTextEmailId);

        spinnerCountry = (Spinner)findViewById(R.id.spinnerCountry);
        spinnerState = (Spinner)findViewById(R.id.spinnerState);
        spinnerCity = (Spinner)findViewById(R.id.spinnerCity);

        buttonContinue = (Button) findViewById(R.id.buttonContinue);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdvertiseActivity.this,LandingActivity.class);
                startActivity(intent);
                finish();
            }
        });

        buttonContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdvertiseActivity.this,CustomPopupActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
