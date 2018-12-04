package com.webakruti.kamgarchowk.userUI.fragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.webakruti.kamgarchowk.adapter.HomeAvailAllServicesAdapter;
import com.webakruti.kamgarchowk.adapter.HomeCategoryGridAdapter;
import com.webakruti.kamgarchowk.adapter.HomePopularKamgarAdapter;
import com.webakruti.kamgarchowk.model.HomeGridCategory;
import com.webakruti.kamgarchowk.model.SearchLocationList;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.CategoryActivity;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;
import com.webakruti.kamgarchowk.userUI.SubcategoryActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.util.ArrayList;
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
    private GridView gridview;
    private RecyclerView recyclerViewPopular;
    private RecyclerView recyclerViewAvailableAllServices;

    String selectedLocations = "Select";
    SearchLocationList selectedLocation;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initViews();

        if (NetworkUtil.hasConnectivity(getActivity())) {
            callGetLocationAPI();
        } else {
            Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
        }

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


        gridview = (GridView) rootView.findViewById(R.id.gridview);

        //list of featured kamgar categories with more option
        List<HomeGridCategory> listOfCategories = new ArrayList<HomeGridCategory>();
        listOfCategories.add(new HomeGridCategory("Plumbers", getResources().getDrawable(R.drawable.plumber_icon)));
        listOfCategories.add(new HomeGridCategory("Painter", getResources().getDrawable(R.drawable.painter_icon)));

        listOfCategories.add(new HomeGridCategory("Carpenter", getResources().getDrawable(R.drawable.carpenter_icon)));

        listOfCategories.add(new HomeGridCategory("Welder", getResources().getDrawable(R.drawable.welder_icon)));

        listOfCategories.add(new HomeGridCategory("Plasterer", getResources().getDrawable(R.drawable.plasterer_icon)));
        listOfCategories.add(new HomeGridCategory("Masonry", getResources().getDrawable(R.drawable.masonry_icon)));
        listOfCategories.add(new HomeGridCategory("Electricians", getResources().getDrawable(R.drawable.electrician)));
        listOfCategories.add(new HomeGridCategory("More", getResources().getDrawable(R.drawable.more2)));


        //pass this in adapter
        gridview.setAdapter(new HomeCategoryGridAdapter(getActivity(), listOfCategories));

        gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                // showDlg();

                HomeGridCategory category = (HomeGridCategory) adapterView.getItemAtPosition(i);
                // navigate to Shop Books Activity
                Intent intent = new Intent(getActivity(), SubcategoryActivity.class);

                intent.putExtra("CategoryName", category.getCategoryName());
                startActivity(intent);

                if (i == adapterView.getLastVisiblePosition()) {
                    Intent intent1 = new Intent(getActivity(), CategoryActivity.class);

                    //intent.putExtra("CategoryName", category.getCategoryName());
                    startActivity(intent1);
                }


            }
        });


        //list of popular images
        recyclerViewPopular = (RecyclerView) rootView.findViewById(R.id.recyclerViewPopular);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        recyclerViewPopular.setLayoutManager(layoutManager);
        recyclerViewPopular.setAdapter(new HomePopularKamgarAdapter(getContext(), 4));

        //list of kamgar available for all services
        recyclerViewAvailableAllServices = (RecyclerView) rootView.findViewById(R.id.recyclerViewAvailableAllServices);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerViewAvailableAllServices.setLayoutManager(layoutManager1);
        recyclerViewAvailableAllServices.setAdapter(new HomeAvailAllServicesAdapter(getContext(), 15));

    }


    private void setSearchLocation(List<SearchLocationList> locationList) {

        List<SearchLocationList> finalList = new ArrayList<>();

        SearchLocationList locationList1 = new SearchLocationList();
        locationList1.setId(1);
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
