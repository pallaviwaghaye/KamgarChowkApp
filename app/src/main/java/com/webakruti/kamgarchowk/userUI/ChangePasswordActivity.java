package com.webakruti.kamgarchowk.userUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;

public class ChangePasswordActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private TextView textViewChangePwdHeading;
    private EditText editTextUserOldPwd;
    private EditText editTextUserNewPwd;
    private EditText editTextUserConfirmNewPwd;
    private Button buttonChangePwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        initViews();
    }

    private void initViews()
    {
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        textViewChangePwdHeading = (TextView)findViewById(R.id.textViewChangePwdHeading);
        editTextUserOldPwd = (EditText)findViewById(R.id.editTextUserOldPwd);
        editTextUserNewPwd = (EditText)findViewById(R.id.editTextUserNewPwd);
        editTextUserConfirmNewPwd = (EditText)findViewById(R.id.editTextUserConfirmNewPwd);
        buttonChangePwd = (Button) findViewById(R.id.buttonChangePwd);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

}
