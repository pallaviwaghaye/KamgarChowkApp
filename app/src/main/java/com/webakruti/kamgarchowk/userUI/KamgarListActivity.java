package com.webakruti.kamgarchowk.userUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.HomeAvailAllServicesAdapter;
import com.webakruti.kamgarchowk.adapter.KamgarListAdapter;

public class KamgarListActivity extends AppCompatActivity {

    private ImageView imageViewBack;
    private TextView textViewHeading;
    private LinearLayout linearLayoutSubcategorySearch;
    private LinearLayout linearLayoutSort;
    private LinearLayout linearLayoutFilter;
    private LinearLayout linearLayoutShowListView;
    private RecyclerView recyclerView;
    private TextView textViewNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_list);

        initViews();

    }

    private void initViews() {

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        textViewHeading = (TextView)findViewById(R.id.textViewHeading);
        textViewNoData = (TextView)findViewById(R.id.textViewNoData);
        linearLayoutSubcategorySearch = (LinearLayout)findViewById(R.id.linearLayoutSubcategorySearch);
        linearLayoutSort = (LinearLayout)findViewById(R.id.linearLayoutSort);
        linearLayoutFilter = (LinearLayout)findViewById(R.id.linearLayoutFilter);
        linearLayoutShowListView = (LinearLayout)findViewById(R.id.linearLayoutShowListView);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(KamgarListActivity.this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(new KamgarListAdapter(getApplicationContext(), 15));
    }
}
