package com.webakruti.kamgarchowk.kamgarUI;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.SubscriptionPlanAdapter;

public class KamgarSubscriptionPlanActivity extends AppCompatActivity {
    private ImageView imageViewBack;
    private RecyclerView recyclerView;
    private TextView textViewNoData;
    private SubscriptionPlanAdapter subscriptionPlanAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_subscription_plan);

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

        textViewNoData = (TextView)findViewById(R.id.textViewNoData);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(KamgarSubscriptionPlanActivity.this,LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(new SubscriptionPlanAdapter(getApplicationContext(), 3));


    }
}
