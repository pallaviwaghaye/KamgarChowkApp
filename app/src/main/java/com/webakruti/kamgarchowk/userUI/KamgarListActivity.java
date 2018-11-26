package com.webakruti.kamgarchowk.userUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
    private RecyclerView recycler_view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_list);



    }

    private void initViews() {

        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        textViewHeading = (TextView)findViewById(R.id.textViewHeading);
        linearLayoutSubcategorySearch = (LinearLayout)findViewById(R.id.linearLayoutSubcategorySearch);
        linearLayoutSort = (LinearLayout)findViewById(R.id.linearLayoutSort);
        linearLayoutFilter = (LinearLayout)findViewById(R.id.linearLayoutFilter);
        linearLayoutShowListView = (LinearLayout)findViewById(R.id.linearLayoutShowListView);
        recycler_view = (RecyclerView)findViewById(R.id.recycler_view);

        LinearLayoutManager layoutManager = new LinearLayoutManager(KamgarListActivity.this,LinearLayoutManager.VERTICAL, false);
        recycler_view.setLayoutManager(layoutManager);
        recycler_view.setAdapter(new KamgarListAdapter(KamgarListActivity.this, 10));
    }
}
