package com.webakruti.kamgarchowk.userUI.fragments;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.annotations.SerializedName;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.CategoryAdapter;
import com.webakruti.kamgarchowk.adapter.HomeAvailAllServicesAdapter;
import com.webakruti.kamgarchowk.adapter.HomeCategoryGridAdapter;
import com.webakruti.kamgarchowk.adapter.HomeGridAdapter;
import com.webakruti.kamgarchowk.adapter.HomePopularKamgarAdapter;
import com.webakruti.kamgarchowk.adapter.SubcategoryAdapter;
import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.model.HomeGridCategory;
import com.webakruti.kamgarchowk.model.HomeResponse;
import com.webakruti.kamgarchowk.model.SearchAutofill;
import com.webakruti.kamgarchowk.model.SearchLocationList;
import com.webakruti.kamgarchowk.model.SubcategoryListResponse;
import com.webakruti.kamgarchowk.model.UserProfileResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.CategoryActivity;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;
import com.webakruti.kamgarchowk.userUI.SubcategoryActivity;
import com.webakruti.kamgarchowk.utils.GridSpacingItemDecoration;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;
import com.webakruti.kamgarchowk.utils.Utils;
import com.webakruti.kamgarchowk.utils.httpparsers.JSONParserGETRequest;
import com.webakruti.kamgarchowk.utils.httpparsers.JSONParserPOSTRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private View rootView;
    private Spinner spinnerLocation;
    private AutoCompleteTextView autoComTextViewSearch;
    private ImageView imageViewSearch;
    private ImageView imageViewHomeImage;
    private RecyclerView recyclerViewCategory;
    private GridView gridview;
    private RecyclerView recyclerViewPopular;
    private RecyclerView recyclerViewAvailableAllServices;

    String selectedLocations = "Select city";
    SearchLocationList.Citylist selectedLocation;
    private ProgressDialog progressDialogForAPI;

    private SearchLocationList.Citylist city;

    SearchLocationList.Citylist obj;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        SharedPreferenceManager.setApplicationContext(getActivity());

        initViews();

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

        selectedLocation = new SearchLocationList.Citylist();
        selectedLocation.setName(selectedLocations);

        return rootView;
    }



    private void callGetLocationAPI() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();

        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();

        String headers = "Bearer " + token;
        Call<SearchLocationList> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).search_location(headers);
        requestCallback.enqueue(new Callback<SearchLocationList>() {
            @Override
            public void onResponse(Call<SearchLocationList> call, Response<SearchLocationList> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    SearchLocationList searchLocationList = response.body();

                    if (searchLocationList != null) {

                        List<SearchLocationList.Citylist> locationLists = searchLocationList.getCitylist();
                        /*ArrayAdapter<SearchLocationList.Citylist> adapterLocation = new ArrayAdapter<SearchLocationList.Citylist>(getActivity(), android.R.layout.simple_spinner_dropdown_item, locationLists);
                        spinnerLocation.setAdapter(adapterLocation);

                        Integer cityid = SharedPreferenceManager.getUserLocationObjectFromSharedPreference().getCitylist().indexOf(locationLists);
                        spinnerLocation.setSelection(cityid+1);*/


                          setSearchLocation(locationLists);
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
            public void onFailure(Call<SearchLocationList> call, Throwable t) {

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

    public class BGTaskForTest extends AsyncTask<String, String, JSONObject> {
        Activity context;
        private ProgressDialog dialog;
        private String testAPI;
        private JSONObject jsonObject;
        private String token;
        private String searchQuery;


        /**
         * Constructor
         */
        public BGTaskForTest(Activity context,String token,String searchQuery) {
            this.context = context;
            this.token = token;
            this.searchQuery = searchQuery;

        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }


        /**
         * Calling API and Getting Response
         */
        @Override
        protected JSONObject doInBackground(String... args) {

            testAPI = "http://beta.kamgarchowk.com/api/autofill/" + searchQuery;

            try {
                // for making request, we have to pass URL and json object in string format
                JSONParserPOSTRequest parser = new JSONParserPOSTRequest();
                jsonObject = parser.makeRequest(testAPI,token);
            } catch (Exception ex) {
                Log.e("Exception Login Parse:", ex.toString());
            }
            return jsonObject;
        }

        /**
         * Handling response
         */
        @Override
        protected void onPostExecute(JSONObject jsonObject) {
            try {

                if(jsonObject != null)
                {
                    List<SubcategoryListResponse.Subcategory> list = new ArrayList<>();
                    JSONArray jsonArray = jsonObject.getJSONArray("subcategory");
                    if(jsonArray != null && jsonArray.length() > 0)
                    {
                        for(int i=0;i<jsonArray.length();i++)
                        {

                            JSONObject subcategoryJsonObject = jsonArray.getJSONObject(i);
                            SubcategoryListResponse.Subcategory autofillObj = new SubcategoryListResponse.Subcategory();
                            autofillObj.setId(subcategoryJsonObject.getInt("id"));
                            autofillObj.setCategoryId(subcategoryJsonObject.getInt("category_id"));
                            autofillObj.setName(subcategoryJsonObject.getString("name"));

                            list.add(autofillObj);
                        }

                        ArrayAdapter<SubcategoryListResponse.Subcategory> arrAdapter = new ArrayAdapter<SubcategoryListResponse.Subcategory>(getActivity(), android.R.layout.simple_dropdown_item_1line, list);
                        autoComTextViewSearch.setAdapter(arrAdapter);
                    }else{
                        //Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                    }
                }

            } catch (Exception e) {

                e.printStackTrace();
            }
        }

    }


    private void callBGSearchTask(String token,String searchQuery)
    {
        new BGTaskForTest(getActivity(),token,searchQuery).execute();
    }

    private void initViews() {

        autoComTextViewSearch = (AutoCompleteTextView) rootView.findViewById(R.id.autoComTextViewSearch);

        autoComTextViewSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {



            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString().length() > 0) {
                    if (NetworkUtil.hasConnectivity(getActivity())) {
                       /* progressDialogForAPI = new ProgressDialog(getActivity());
                        progressDialogForAPI.setCancelable(false);
                        progressDialogForAPI.setIndeterminate(true);
                        progressDialogForAPI.setMessage("Please wait...");
                        progressDialogForAPI.show();*/

                        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();

                        callBGSearchTask(token, s.toString());
                        /*String str = s.toString();
                        String API = "http://beta.kamgarchowk.com/api/autofill/" + s.toString();
                        String headers = "Bearer " + token;
                        Call<SearchAutofill> requestCallback = RestClient.getApiService(API).autofillsearch(headers);
                        requestCallback.enqueue(new Callback<SearchAutofill>() {
                            @Override
                            public void onResponse(Call<SearchAutofill> call, Response<SearchAutofill> response) {
                                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                                    SearchAutofill details = response.body();
                                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                                    if (details != null) {

                                        List<SearchAutofill.Subcategory> list = details.getSubcategory();
                                        ArrayAdapter<SearchAutofill.Subcategory> arrAdapter = new ArrayAdapter<SearchAutofill.Subcategory>(getActivity(), android.R.layout.simple_dropdown_item_1line, list);
                                        autoComTextViewSearch.setAdapter(arrAdapter);
                                    }

                                } else {
                                    // Response code is 401
                                }

                                if (progressDialogForAPI != null) {
                                    progressDialogForAPI.cancel();
                                }
                            }

                            @Override
                            public void onFailure(Call<SearchAutofill> call, Throwable t) {

                                if (t != null) {

                                    if (progressDialogForAPI != null) {
                                        progressDialogForAPI.cancel();
                                    }
                                    if (t.getMessage() != null)
                                        Log.e("error", t.getMessage());
                                }

                            }
                        });
*/


                    } else {
                        Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        autoComTextViewSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                SubcategoryListResponse.Subcategory subcategory = (SubcategoryListResponse.Subcategory) parent.getItemAtPosition(position);
               Intent intent = new Intent(getActivity(),KamgarListActivity.class);
               intent.putExtra("KamgarSubCategory",subcategory);
               startActivity(intent);


            }
        });

        //click on keyboard search icon
       /* autoComTextViewSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
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
        });*/

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
                        recyclerViewCategory.setAdapter(new HomeGridAdapter(getContext(), listOfCategories1));


                        /*final List<HomeResponse.Featuredlist> listOfCategories = details.getFeaturedlist();
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
*/

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


    private void setSearchLocation(List<SearchLocationList.Citylist> locationList) {

        if(locationList!=null && locationList.size() > 0) {
            ArrayAdapter<SearchLocationList.Citylist> adapterLocation = new ArrayAdapter<SearchLocationList.Citylist>(getActivity(), android.R.layout.simple_spinner_dropdown_item, locationList);
            spinnerLocation.setAdapter(adapterLocation);
        }

        obj = SharedPreferenceManager.getUserLocationObjectFromSharedPreference();
        int position = locationList.indexOf(obj);
        spinnerLocation.setSelection(position);

         spinnerLocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 obj = (SearchLocationList.Citylist)parent.getItemAtPosition(position);
                 SharedPreferenceManager.storeUserLocationResponseInSharedPreference(obj);
             }

             @Override
             public void onNothingSelected(AdapterView<?> parent) {

             }
         });


    }

    public void setTextCustom(View view) {
        TextView customTextView = ((TextView) view);
        if (customTextView != null) {
            //customTextView.setTextColor(getResources().getColor(R.color.white));
            customTextView.setTextColor(getResources().getColor(R.color.black));
        }
    }

}
