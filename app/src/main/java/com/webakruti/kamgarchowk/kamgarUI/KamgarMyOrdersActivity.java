package com.webakruti.kamgarchowk.kamgarUI;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.KamgarMyOrdersAdapter;

public class KamgarMyOrdersActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private RecyclerView recyclerView;
   // private ProgressDialog progressDialogForAPI;
    private TextView textViewNoData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_my_orders);

        initViews();
    }

    private void initViews()
    {

        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        textViewNoData = (TextView)findViewById(R.id.textViewNoData);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(KamgarMyOrdersActivity.this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(new KamgarMyOrdersAdapter(getApplicationContext(),10));

    }
}
