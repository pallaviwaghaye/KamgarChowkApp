package com.webakruti.kamgarchowk.kamgarUI;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.KamgarSubcategoryAdapter;
import com.webakruti.kamgarchowk.adapter.SubcategoryAdapter;

public class KamgarSubcategoryActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private TextView textViewCategoryHeading;
    private RecyclerView recyclerView;
    private TextView textViewNoData;
    private Button buttonDoneSubcategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_subcategory);

        textViewCategoryHeading = (TextView)findViewById(R.id.textViewCategoryHeading);
        textViewNoData = (TextView)findViewById(R.id.textViewNoData);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(KamgarSubcategoryActivity.this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(new KamgarSubcategoryAdapter(getApplicationContext(), 5));
    }
}
