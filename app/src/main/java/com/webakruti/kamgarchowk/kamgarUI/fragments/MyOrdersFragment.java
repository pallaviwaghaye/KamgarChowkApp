package com.webakruti.kamgarchowk.kamgarUI.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.KamgarMyOrdersAdapter;
import com.webakruti.kamgarchowk.adapter.UserMyEnquiryAdapter;


public class MyOrdersFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private ProgressDialog progressDialogForAPI;
    private TextView textViewNoData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_orders, container, false);

        initViews();
        return rootView;
    }

    private void initViews()
    {
        textViewNoData = (TextView)rootView.findViewById(R.id.textViewNoData);

        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(new KamgarMyOrdersAdapter(getContext(),10));

    }
}
