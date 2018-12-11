package com.webakruti.kamgarchowk.userUI.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.CategoryAdapter;
import com.webakruti.kamgarchowk.utils.GridSpacingItemDecoration;
import com.webakruti.kamgarchowk.utils.Utils;

public class CategoryFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private CategoryAdapter categoryAdapter;
    //private ProgressDialog progressDialogForAPI;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_category, container, false);
        // Inflate the layout for this fragment

        initViews();

        return rootView;
    }

    private void initViews() {

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);

    }



}
