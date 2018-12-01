package com.webakruti.kamgarchowk.userUI;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.CategoryAdapter;
import com.webakruti.kamgarchowk.utils.GridSpacingItemDecoration;
import com.webakruti.kamgarchowk.utils.Utils;

public class CategoryActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;

    private ImageView imageViewBack;
    private TextView textViewHeading;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        initViews();
    }

    private void initViews() {

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CategoryActivity.this,HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        textViewHeading = (TextView)findViewById(R.id.textViewHeading);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(CategoryActivity.this, 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        int spacing = (int) Utils.DpToPixel(CategoryActivity.this, 11); // 40px

        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(2, spacing, true);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setNestedScrollingEnabled(false);


        recyclerView.setAdapter(new CategoryAdapter(getApplicationContext(), 11));


       /* List<Category> listOfCategories = new ArrayList<Category>();
        listOfCategories.add(new Category("Waiting Hall", getResources().getDrawable(R.drawable.icons_01)));
        listOfCategories.add(new Category("Urinal", getResources().getDrawable(R.drawable.icons_02)));
        listOfCategories.add(new Category("Lavatories", getResources().getDrawable(R.drawable.icons_03)));
        listOfCategories.add(new Category("Divyanhjan Toilet", getResources().getDrawable(R.drawable.icons_10)));
        listOfCategories.add(new Category("Foot Over Bridge", getResources().getDrawable(R.drawable.icons_09)));
        listOfCategories.add(new Category("Water Cooler", getResources().getDrawable(R.drawable.icons_08)));
        listOfCategories.add(new Category("Parking", getResources().getDrawable(R.drawable.icons_07)));
        listOfCategories.add(new Category("Dustbin", getResources().getDrawable(R.drawable.icons_06)));
        listOfCategories.add(new Category("Catering", getResources().getDrawable(R.drawable.icons_05)));
        listOfCategories.add(new Category("Waiting Room", getResources().getDrawable(R.drawable.icons_01)));
        listOfCategories.add(new Category("Any Other Places", getResources().getDrawable(R.drawable.icons_04)));*/


    }


@Override
    public void onBackPressed() {

        Intent new_intent = new Intent(CategoryActivity.this, HomeActivity.class);

        this.startActivity(new_intent);

    }

}
