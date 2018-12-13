package com.webakruti.kamgarchowk.kamgarUI;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.userUI.SupportActivity;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

public class KamgarSupportActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private TextView textViewSupportHeading;
    private EditText editTextSubject;
    private EditText editTextProblemDetails;
    private Button buttonKamgarSupportSubmit;
    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_support);

        SharedPreferenceManager.setApplicationContext(KamgarSupportActivity.this);

        initViews();
    }

    private void initViews() {
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        textViewSupportHeading = (TextView) findViewById(R.id.textViewSupportHeading);
        editTextSubject = (EditText) findViewById(R.id.editTextSubject);
        editTextProblemDetails = (EditText) findViewById(R.id.editTextProblemDetails);
        buttonKamgarSupportSubmit = (Button) findViewById(R.id.buttonKamgarSupportSubmit);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
