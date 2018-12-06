package com.webakruti.kamgarchowk.userUI.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.CategoryAdapter;
import com.webakruti.kamgarchowk.adapter.HomeAvailAllServicesAdapter;
import com.webakruti.kamgarchowk.adapter.HomeCategoryGridAdapter;
import com.webakruti.kamgarchowk.adapter.HomeGridAdapter;
import com.webakruti.kamgarchowk.adapter.HomePopularKamgarAdapter;
import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.model.HomeGridCategory;
import com.webakruti.kamgarchowk.model.HomeResponse;
import com.webakruti.kamgarchowk.model.SearchLocationList;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.CategoryActivity;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;
import com.webakruti.kamgarchowk.userUI.SubcategoryActivity;
import com.webakruti.kamgarchowk.utils.GridSpacingItemDecoration;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;
import com.webakruti.kamgarchowk.utils.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private View rootView;
    private Spinner spinnerLocation;
    private EditText editTextSearch;
    private ImageView imageViewSearch;
    private ImageView imageViewHomeImage;
    private RecyclerView recyclerViewCategory;
    private GridView gridview;
    private RecyclerView recyclerViewPopular;
    private RecyclerView recyclerViewAvailableAllServices;

    String selectedLocations = "Select";
    SearchLocationList selectedLocation;
    private ProgressDialog progressDialogForAPI;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();
        SharedPreferenceManager.setApplicationContext(getActivity());


        if (NetworkUtil.hasConnectivity(getActivity())) {
            callGetLocationAPI();
            callGetHomeAPI();
        } else {
            Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
        }

       /* if (NetworkUtil.hasConnectivity(getActivity())) {
            callGetHomeAPI();
        } else {
            Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
        }*/

        selectedLocation = new SearchLocationList();
        selectedLocation.setName(selectedLocations);

        return rootView;
    }

    private void callGetLocationAPI() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        SharedPreferenceManager.setApplicationContext(getActivity());
        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();

        String headers = "Bearer " + token;
        Call<List<SearchLocationList>> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).search_location(headers);
        requestCallback.enqueue(new Callback<List<SearchLocationList>>() {
            @Override
            public void onResponse(Call<List<SearchLocationList>> call, Response<List<SearchLocationList>> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    List<SearchLocationList> searchLocationList = response.body();

                    if (searchLocationList != null) {

                        /*ArrayAdapter<SearchLocationList> adapterLocation = new ArrayAdapter<SearchLocationList>(getActivity(), android.R.layout.simple_spinner_dropdown_item, searchLocationList);
                        spinnerLocation.setAdapter(adapterLocation);*/
                            setSearchLocation(searchLocationList);
                            //setPlatFormSpinnerData(0, -1); // should be 0

                    }

                } else {
                    // Response code is 401
                }

                if (progressDialog != null) {
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<List<SearchLocationList>> call, Throwable t) {

                if (t != null) {

                    if (progressDialog != null) {
                        progressDialog.cancel();
                    }
                    if (t.getMessage() != null)
                        Log.e("error", t.getMessage());
                }

            }
        });

    }

    private void initViews() {

        editTextSearch = (EditText) rootView.findViewById(R.id.editTextSearch);

        //click on keyboard search icon
        editTextSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    //performSearch();
                    Intent i = new Intent(getActivity(), KamgarListActivity.class);
                    startActivity(i);

                    return true;
                }
                return false;
            }
        });

        imageViewHomeImage = (ImageView) rootView.findViewById(R.id.imageViewHomeImage);

        imageViewSearch = (ImageView) rootView.findViewById(R.id.imageViewSearch);
        imageViewSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), KamgarListActivity.class);
                getContext().startActivity(intent);
            }
        });

        spinnerLocation = (Spinner) rootView.findViewById(R.id.spinnerLocation);
        //spinnerLocation.setOnClickListener(this);

        //list of cities for search
        /*String[] list = getResources().getStringArray(R.array.serachlocation);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,list);
        spinnerLocation.setAdapter(adapter);*/
        recyclerViewCategory = (RecyclerView) rootView.findViewById(R.id.recyclerViewCategory);
        gridview = (GridView) rootView.findViewById(R.id.gridview);
        recyclerViewPopular = (RecyclerView) rootView.findViewById(R.id.recyclerViewPopular);
        recyclerViewAvailableAllServices = (RecyclerView) rootView.findViewById(R.id.recyclerViewAvailableAllServices);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 4, LinearLayoutManager.VERTICAL, false);
        recyclerViewCategory.setLayoutManager(gridLayoutManager);
        int spacing = (int) Utils.DpToPixel(getActivity(), 11); // 40px

        GridSpacingItemDecoration itemDecoration = new GridSpacingItemDecoration(4, spacing, true);
        recyclerViewCategory.addItemDecoration(itemDecoration);
        recyclerViewCategory.setNestedScrollingEnabled(false);

    }


    private void callGetHomeAPI() {

        progressDialogForAPI = new ProgressDialog(getActivity());
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(getActivity());
        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<HomeResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).homeList(headers);
        requestCallback.enqueue(new Callback<HomeResponse>() {
            @Override
            public void onResponse(Call<HomeResponse> call, Response<HomeResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    HomeResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details != null) {

                        //handleStationPlatformData(details);

                        final List<HomeResponse.Popularlist> popularlists = details.getPopularlist();
                        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
                        recyclerViewPopular.setLayoutManager(layoutManager);
                        recyclerViewPopular.setAdapter(new HomePopularKamgarAdapter(getContext(), popularlists));

                        final List<HomeResponse.Workavllist> workavllists = details.getWorkavllist();
                        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        recyclerViewAvailableAllServices.setLayoutManager(layoutManager1);
                        recyclerViewAvailableAllServices.setAdapter(new HomeAvailAllServicesAdapter(getContext(), workavllists));

                        final List<HomeResponse.Featuredlist> listOfCategories1 = details.getFeaturedlist();
                       /* LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
                        recyclerViewCategory.setLayoutManager(layoutManager2);*/
                        recyclerViewCategory.setAdapter(new HomeGridAdapter(getContext(), listOfCategories1));


                        final List<HomeResponse.Featuredlist> listOfCategories = details.getFeaturedlist();
                        final ArrayAdapter<HomeResponse.Featuredlist> HomeCategoryGridAdapter = new ArrayAdapter<HomeResponse.Featuredlist>
                                (getContext(),android.R.layout.simple_list_item_1, listOfCategories);
                        gridview.setAdapter(new HomeCategoryGridAdapter(getActivity(), listOfCategories));
                        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                // showDlg();

                                HomeResponse.Featuredlist featuredlist = (HomeResponse.Featuredlist) adapterView.getItemAtPosition(i);
                                Intent intent = new Intent(getActivity(), SubcategoryActivity.class);

                                intent.putExtra("CategoryName", featuredlist.getName());
                                startActivity(intent);

                                if (i == adapterView.getLastVisiblePosition()) {
                                    Intent intent1 = new Intent(getActivity(), CategoryActivity.class);

                                    //intent.putExtra("CategoryName", category.getCategoryName());
                                    startActivity(intent1);
                                }


                            }
                        });


                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<HomeResponse> call, Throwable t) {

                if (t != null) {

                    if (progressDialogForAPI != null) {
                        progressDialogForAPI.cancel();
                    }
                    if (t.getMessage() != null)
                        Log.e("error", t.getMessage());
                }

            }
        });


    }



    /*    // Populate a List from Array elements
        final List<HomeResponse.Featuredlist> listOfCategories = new ArrayList<HomeResponse.Featuredlist>();


       *//* listOfCategories.add(listOfCategories.size(),"More",getResources().getDrawable(R.drawable.moreicon));


        HomeResponse.Featuredlist addedItemText = listOfCategories.get(listOfCategories.size()-1);*//*

        // Create a new ArrayAdapter
        final ArrayAdapter<HomeResponse.Featuredlist> HomeCategoryGridAdapter = new ArrayAdapter<HomeResponse.Featuredlist>
                (getContext(),android.R.layout.simple_list_item_1, listOfCategories);
        //HomeCategoryGridAdapter.notifyDataSetChanged();
        // Data bind GridView with ArrayAdapter (String Array elements)
        //gridview.setAdapter(gridViewArrayAdapter);

        //list of featured kamgar categories with more option
       // List<HomeResponse.Featuredlist> listOfCategories = new ArrayList<HomeResponse.Featuredlist>();
        //listOfCategories.add(new HomeResponse().getFeaturedlist("plumbers",));
        *//*listOfCategories.add(new HomeResponse.Featuredlist(1,"Plumbers", getResources().getDrawable(R.drawable.plumber_icon)));
        listOfCategories.add(new HomeResponse.Featuredlist("Painter", getResources().getDrawable(R.drawable.painter_icon)));

        listOfCategories.add(new HomeResponse.Featuredlist("Carpenter", getResources().getDrawable(R.drawable.carpenter_icon)));

        listOfCategories.add(new HomeResponse.Featuredlist("Welder", getResources().getDrawable(R.drawable.welder_icon)));

        listOfCategories.add(new HomeResponse.Featuredlist("Plasterer", getResources().getDrawable(R.drawable.plasterer_icon)));
        listOfCategories.add(new HomeResponse.Featuredlist("Masonry", getResources().getDrawable(R.drawable.masonry_icon)));
        listOfCategories.add(new HomeResponse.Featuredlist("Electricians", getResources().getDrawable(R.drawable.electrician)));*//*
        //listOfCategories.add(new HomeResponse.Featuredlist("More", getResources().getDrawable(R.drawable.moreicon)));


        //pass this in adapter
        gridview.setAdapter(new HomeCategoryGridAdapter(getActivity(), listOfCategories));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // showDlg();

                HomeResponse.Featuredlist featuredlist = (HomeResponse.Featuredlist) adapterView.getItemAtPosition(i);
                Intent intent = new Intent(getActivity(), SubcategoryActivity.class);

                intent.putExtra("CategoryName", featuredlist.getName());
                startActivity(intent);

                if (i+1 == adapterView.getLastVisiblePosition()) {
                    Intent intent1 = new Intent(getActivity(), CategoryActivity.class);

                    //intent.putExtra("CategoryName", category.getCategoryName());
                    startActivity(intent1);
                }


            }
        });


        //list of popular images

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopular.setLayoutManager(layoutManager);
        //recyclerViewPopular.setAdapter(new HomePopularKamgarAdapter(getContext(), 4));

        //list of kamgar available for all services

        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewAvailableAllServices.setLayoutManager(layoutManager1);
        recyclerViewAvailableAllServices.setAdapter(new HomeAvailAllServicesAdapter(getContext(), 15));
*/


    private void setSearchLocation(List<SearchLocationList> locationList) {

        List<SearchLocationList> finalList = new ArrayList<>();

        SearchLocationList locationList1 = new SearchLocationList();
        locationList1.setId(-1);
        locationList1.setName(selectedLocations);
        //locationList.get(0).getName();

        finalList.add(locationList1);
        finalList.addAll(locationList);

        /*SendRequestFormResponse.Station station = new SendRequestFormResponse.Station();
        station.setId(-1);
        station.setName(selectedStations);

        finalList.add(station);
        finalList.addAll(stationList);*/


        ArrayAdapter<SearchLocationList> adapterLocation = new ArrayAdapter<SearchLocationList>(getActivity(), android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerLocation.setAdapter(adapterLocation);

        Toast.makeText(getActivity(), finalList.get(1).getName().toString(), Toast.LENGTH_LONG).show();

       /* spinnerLocation.setSelection(0, true);
        View v = spinnerLocation.getSelectedView();
        setTextCustom(v);*/


        /*spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedLocation = (SearchLocationList) adapterView.getItemAtPosition(position);
                //setPlatFormSpinnerData(position, selectedLocation.getId());
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });*/
    }

    public void setTextCustom(View view) {
        TextView customTextView = ((TextView) view);
        if (customTextView != null) {
            //customTextView.setTextColor(getResources().getColor(R.color.white));
            customTextView.setTextColor(getResources().getColor(R.color.black));
        }
    }

}
