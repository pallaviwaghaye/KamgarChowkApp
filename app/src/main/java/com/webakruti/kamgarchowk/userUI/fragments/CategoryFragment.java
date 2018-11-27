package com.webakruti.kamgarchowk.userUI.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.CategoryKamgarAdapter;
import com.webakruti.kamgarchowk.adapter.HomePopularKamgarAdapter;
import com.webakruti.kamgarchowk.utils.GridSpacingItemDecoration;
import com.webakruti.kamgarchowk.utils.Utils;

public class CategoryFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private CategoryKamgarAdapter categoryKamgarAdapter;
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

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 2, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        int spacing = (int) Utils.DpToPixel(getActivity(), 11); // 40px

        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(2, spacing, true);
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setNestedScrollingEnabled(false);


            recyclerView.setAdapter(new CategoryKamgarAdapter(getContext(), 11));


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



}
