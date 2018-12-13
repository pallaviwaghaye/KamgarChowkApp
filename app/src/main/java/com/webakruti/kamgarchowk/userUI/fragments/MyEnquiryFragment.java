package com.webakruti.kamgarchowk.userUI.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.HomeAvailAllServicesAdapter;
import com.webakruti.kamgarchowk.adapter.HomeGridAdapter;
import com.webakruti.kamgarchowk.adapter.HomePopularKamgarAdapter;
import com.webakruti.kamgarchowk.adapter.KamgarListAdapter;
import com.webakruti.kamgarchowk.adapter.UserMyEnquiryAdapter;
import com.webakruti.kamgarchowk.model.HomeResponse;
import com.webakruti.kamgarchowk.model.MyEnquiryResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.KamgarListActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyEnquiryFragment extends Fragment {
    private View rootView;
    private RecyclerView recyclerView;
    private TextView textViewNoData;
    private ProgressDialog progressDialogForAPI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_enquiry, container, false);
        initViews();
        SharedPreferenceManager.setApplicationContext(getActivity());
        if (NetworkUtil.hasConnectivity(getActivity())) {
            callMyenquiryAPI();
        } else {
            Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
        }

        return rootView;
    }

    private void initViews()
    {
        recyclerView = (RecyclerView)rootView.findViewById(R.id.recyclerView);
        textViewNoData = (TextView)rootView.findViewById(R.id.textViewNoData);

    }

    private void callMyenquiryAPI() {

        progressDialogForAPI = new ProgressDialog(getActivity());
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(getActivity());
        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();
        final Integer userid = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getId();

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<MyEnquiryResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).myenquiry(headers,userid);
        requestCallback.enqueue(new Callback<MyEnquiryResponse>() {
            @Override
            public void onResponse(Call<MyEnquiryResponse> call, Response<MyEnquiryResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    MyEnquiryResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSuccess().getUserenquiry() != null && details.getSuccess().getUserenquiry().size() > 0) {

                        //handleStationPlatformData(details);
                        textViewNoData.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);

                        final List<MyEnquiryResponse.Userenquiry> myEnquiry = details.getSuccess().getUserenquiry();
                        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager1);
                        recyclerView.setAdapter(new UserMyEnquiryAdapter(getActivity(), myEnquiry));
                    }else{
                        textViewNoData.setVisibility(View.VISIBLE);
                        recyclerView.setVisibility(View.GONE);
                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<MyEnquiryResponse> call, Throwable t) {

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


}
