package com.webakruti.kamgarchowk.userUI.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.HomeAvailAllServicesAdapter;
import com.webakruti.kamgarchowk.adapter.HomeCategoryGridAdapter;
import com.webakruti.kamgarchowk.adapter.HomePopularKamgarAdapter;
import com.webakruti.kamgarchowk.model.HomeGridCategory;
import com.webakruti.kamgarchowk.userUI.CategoryActivity;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;
import com.webakruti.kamgarchowk.userUI.SubcategoryActivity;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private View rootView;
    private Spinner spinnerLocation;
    private EditText editTextSearch;
    private ImageView imageViewSearch;
    private ImageView imageViewHomeImage;
    private GridView gridview;
    private RecyclerView recyclerViewPopular;
    private RecyclerView recyclerViewAvailableAllServices;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();

        return rootView;
    }

    private void initViews() {

        editTextSearch = (EditText)rootView.findViewById(R.id.editTextSearch);

        //click on keyboard search icon
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //performSearch();
                    Intent i = new Intent(getActivity(),KamgarListActivity.class);
                    startActivity(i);

                    return true;
                }
                return false;
            }
        });

        imageViewHomeImage = (ImageView)rootView.findViewById(R.id.imageViewHomeImage);

        imageViewSearch = (ImageView)rootView.findViewById(R.id.imageViewSearch);
        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getActivity(), KamgarListActivity.class);
                getContext().startActivity(intent);
            }
        });

        spinnerLocation = (Spinner) rootView.findViewById(R.id.spinnerLocation);
        //spinnerLocation.setOnClickListener(this);

        //list of cities for search
        String[] list = getResources().getStringArray(R.array.serachlocation);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,list);
        spinnerLocation.setAdapter(adapter);



        gridview = (GridView)rootView.findViewById(R.id.gridview);

        //list of featured kamgar categories with more option
        List<HomeGridCategory> listOfCategories = new ArrayList<HomeGridCategory>();
        listOfCategories.add(new HomeGridCategory("Plumbers",getResources().getDrawable(R.drawable.plumber_icon)));
        listOfCategories.add(new HomeGridCategory("Painter",getResources().getDrawable(R.drawable.painter_icon)));

        listOfCategories.add(new HomeGridCategory("Carpenter",getResources().getDrawable(R.drawable.carpenter_icon)));

        listOfCategories.add(new HomeGridCategory("Welder",getResources().getDrawable(R.drawable.welder_icon)));

        listOfCategories.add(new HomeGridCategory("Plasterer",getResources().getDrawable(R.drawable.plasterer_icon)));
        listOfCategories.add(new HomeGridCategory("Masonry",getResources().getDrawable(R.drawable.masonry_icon)));
        listOfCategories.add(new HomeGridCategory("Electricians",getResources().getDrawable(R.drawable.electrician)));
        listOfCategories.add(new HomeGridCategory("More",getResources().getDrawable(R.drawable.more2)));



        //pass this in adapter
        gridview.setAdapter(new HomeCategoryGridAdapter(getActivity(), listOfCategories));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // showDlg();

                HomeGridCategory category = (HomeGridCategory)  adapterView.getItemAtPosition(i);
                // navigate to Shop Books Activity
                Intent intent = new Intent(getActivity(), SubcategoryActivity.class);

                intent.putExtra("CategoryName", category.getCategoryName());
                startActivity(intent);

                if(i == adapterView.getLastVisiblePosition())
                {
                    Intent intent1 = new Intent(getActivity(), CategoryActivity.class);

                    //intent.putExtra("CategoryName", category.getCategoryName());
                    startActivity(intent1);
                }


            }
        });


        //list of popular images
        recyclerViewPopular = (RecyclerView)rootView.findViewById(R.id.recyclerViewPopular);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopular.setLayoutManager(layoutManager);
        recyclerViewPopular.setAdapter(new HomePopularKamgarAdapter(getContext(), 4));

        //list of kamgar available for all services
        recyclerViewAvailableAllServices = (RecyclerView)rootView.findViewById(R.id.recyclerViewAvailableAllServices);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
        recyclerViewAvailableAllServices.setLayoutManager(layoutManager1);
        recyclerViewAvailableAllServices.setAdapter(new HomeAvailAllServicesAdapter(getContext(), 15));


    }

}
