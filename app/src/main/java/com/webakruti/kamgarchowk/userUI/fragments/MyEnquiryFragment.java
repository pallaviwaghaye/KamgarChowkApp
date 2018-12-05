package com.webakruti.kamgarchowk.userUI.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.KamgarListAdapter;
import com.webakruti.kamgarchowk.adapter.UserMyEnquiryAdapter;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;

public class MyEnquiryFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_enquiry, container, false);
        initViews();

        return rootView;
    }

    private void initViews()
    {
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager1);
        recyclerView.setAdapter(new UserMyEnquiryAdapter(getContext(), 15));
    }


}
