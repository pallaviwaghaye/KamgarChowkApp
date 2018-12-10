package com.webakruti.kamgarchowk.userUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.KamgarResponse;
import com.webakruti.kamgarchowk.model.SubcategoryListResponse;

public class HireKamgarActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private TextView textViewHireKamgarHeading;

    private ImageView imageViewKamgarImage;
    private TextView textViewKamgarName;
    private TextView textViewKamgarProfession;
    private TextView textViewAddress;
    private TextView textViewExperience;

    private TextView textViewHourlyPrice;
    private TextView textViewHalfdayPrice;
    private TextView textViewFulldayPrice;
    private TextView textViewWeeklyPrice;
    private TextView textViewMonthlyPrice;

    private ImageView imageViewRating1;
    private ImageView imageViewRating2;
    private ImageView imageViewRating3;
    private ImageView imageViewRating4;
    private ImageView imageViewRating5;

    private Button buttonHire;

    private KamgarResponse.Kamgar kamgar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hire_kamgar);

        kamgar = (KamgarResponse.Kamgar) getIntent().getSerializableExtra("kamgar");

        initViews();
    }

    private void initViews()
    {
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textViewHireKamgarHeading = (TextView)findViewById(R.id.textViewHireKamgarHeading);

        imageViewKamgarImage = (ImageView)findViewById(R.id.imageViewKamgarImage);

        imageViewKamgarImage = (ImageView)findViewById(R.id.imageViewKamgarImage);
        textViewKamgarName = (TextView)findViewById(R.id.textViewKamgarName);
        textViewKamgarProfession = (TextView)findViewById(R.id.textViewKamgarProfession);
        textViewAddress = (TextView)findViewById(R.id.textViewAddress);
        textViewExperience = (TextView)findViewById(R.id.textViewExperience);

        textViewHourlyPrice = (TextView)findViewById(R.id.textViewHourlyPrice);
        textViewHalfdayPrice = (TextView)findViewById(R.id.textViewHalfdayPrice);
        textViewFulldayPrice = (TextView)findViewById(R.id.textViewFulldayPrice);
        textViewWeeklyPrice = (TextView)findViewById(R.id.textViewWeeklyPrice);
        textViewMonthlyPrice = (TextView)findViewById(R.id.textViewMonthlyPrice);

        imageViewRating1 = (ImageView)findViewById(R.id.imageViewRating1);
        imageViewRating2 = (ImageView)findViewById(R.id.imageViewRating2);
        imageViewRating3 = (ImageView)findViewById(R.id.imageViewRating3);
        imageViewRating4 = (ImageView)findViewById(R.id.imageViewRating4);
        imageViewRating5 = (ImageView)findViewById(R.id.imageViewRating5);




        buttonHire = (Button)findViewById(R.id.buttonHire);
        buttonHire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HireKamgarActivity.this,HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }


}
