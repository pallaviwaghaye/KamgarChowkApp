package com.webakruti.kamgarchowk.kamgarUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;

public class KamgarDocumentsActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private EditText editTextPanNumber;
    private EditText editTextChoosePancard;

    private RelativeLayout relativeLayoutPANUpload;

    private EditText editTextBankName;
    private EditText editTextAccountNo;
    private EditText editTextChooseBankPassbook;
    private RelativeLayout relativeLayoutPassbookUpload;

    private Button buttonDocumentSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_documents);

        initViews();
    }

    private void initViews() {
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editTextPanNumber = (EditText) findViewById(R.id.editTextPanNumber);
        editTextChoosePancard = (EditText) findViewById(R.id.editTextChoosePancard);
        editTextBankName = (EditText) findViewById(R.id.editTextBankName);
        editTextAccountNo = (EditText) findViewById(R.id.editTextAccountNo);
        editTextChooseBankPassbook = (EditText) findViewById(R.id.editTextChooseBankPassbook);
        relativeLayoutPANUpload = (RelativeLayout) findViewById(R.id.relativeLayoutPANUpload);
        relativeLayoutPassbookUpload = (RelativeLayout) findViewById(R.id.relativeLayoutPassbookUpload);
        buttonDocumentSubmit = (Button)findViewById(R.id.buttonDocumentSubmit);

        buttonDocumentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KamgarDocumentsActivity.this, HomeOrProfileActivity.class);
                startActivity(intent);

            }
        });

    }
}
