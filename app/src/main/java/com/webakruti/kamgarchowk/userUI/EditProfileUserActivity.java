package com.webakruti.kamgarchowk.userUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;

public class EditProfileUserActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private TextView textViewEditHeading;

    private EditText editTextFname;
    private EditText editTextMname;
    private EditText editTextLname;
    private EditText editTextDOB;
    private EditText editTextEmail;
    private EditText editTextMobile;
    private EditText editTextAddress;


    private Spinner spinnerGender;
    private Spinner spinnerCountry;
    private Spinner spinnerState;
    private Spinner spinnerCity;
    private Spinner spinnerPincode;

    private Button buttonSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);

        initViews();
    }

    private void initViews()
    {
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        textViewEditHeading = (TextView)findViewById(R.id.textViewEditHeading);
        editTextFname = (EditText)findViewById(R.id.editTextFname);
        editTextMname = (EditText)findViewById(R.id.editTextMname);
        editTextLname = (EditText)findViewById(R.id.editTextLname);
        editTextDOB = (EditText)findViewById(R.id.editTextDOB);
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextMobile = (EditText)findViewById(R.id.editTextMobile);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);

        spinnerGender = (Spinner)findViewById(R.id.spinnerGender);
        spinnerCountry = (Spinner)findViewById(R.id.spinnerCountry);
        spinnerState = (Spinner)findViewById(R.id.spinnerState);
        spinnerCity = (Spinner)findViewById(R.id.spinnerCity);
        spinnerPincode = (Spinner)findViewById(R.id.spinnerPincode);

        buttonSave = (Button) findViewById(R.id.buttonSave);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
