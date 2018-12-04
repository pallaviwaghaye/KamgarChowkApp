package com.webakruti.kamgarchowk.userUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.HomeAvailAllServicesAdapter;
import com.webakruti.kamgarchowk.adapter.KamgarListAdapter;
import com.webakruti.kamgarchowk.adapter.SubcategoryAdapter;
import com.webakruti.kamgarchowk.model.CategoryList;

public class SubcategoryActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private TextView textViewSubcategoryHeading;
    private RecyclerView recyclerView;
    private TextView textViewNoData;

    private CategoryList kamgarCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subcategory);

        kamgarCategory = (CategoryList) getIntent().getSerializableExtra("KamgarCategory");

        textViewSubcategoryHeading = (TextView)findViewById(R.id.textViewSubcategoryHeading);
        textViewNoData = (TextView)findViewById(R.id.textViewNoData);

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(SubcategoryActivity.this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(new SubcategoryAdapter(getApplicationContext(), 15));
    }
}
