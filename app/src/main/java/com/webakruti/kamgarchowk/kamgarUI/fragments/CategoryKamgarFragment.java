package com.webakruti.kamgarchowk.kamgarUI.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.CategoryAdapter;
import com.webakruti.kamgarchowk.adapter.KamgarCategoryAdapter;
import com.webakruti.kamgarchowk.adapter.SubcategoryAdapter;
import com.webakruti.kamgarchowk.kamgarUI.KamgarCategoryActivity;
import com.webakruti.kamgarchowk.model.KamgarCategoryResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.GridSpacingItemDecoration;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;
import com.webakruti.kamgarchowk.utils.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryKamgarFragment extends Fragment {

    private View rootView;
    private RecyclerView recyclerView;
    private TextView textViewNoData;
    private KamgarCategoryAdapter kamgarCategoryAdapter;
    private ProgressDialog progressDialogForAPI;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_category_kamgar, container, false);
        // Inflate the layout for this fragment

        initViews();

        if (NetworkUtil.hasConnectivity(getActivity())) {
            callGetKamgarCategoryAPI();
        } else {
           // Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
            new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.no_internet_message)
                    .setPositiveButton("OK", null)
                    .show();
        }

        return rootView;
    }

    private void initViews() {
        textViewNoData = (TextView)rootView.findViewById(R.id.textViewNoData);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerView);



    }
    private void callGetKamgarCategoryAPI() {

        progressDialogForAPI = new ProgressDialog(getActivity());
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        SharedPreferenceManager.setApplicationContext(getActivity());
        String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();

        String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<KamgarCategoryResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).getkamgarCategory(headers);
        requestCallback.enqueue(new Callback<KamgarCategoryResponse>() {
            @Override
            public void onResponse(Call<KamgarCategoryResponse> call, Response<KamgarCategoryResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarCategoryResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getKamgarcategorylist() != null && details.getKamgarcategorylist().size() > 0) {
                        textViewNoData.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                        List<KamgarCategoryResponse.Kamgarcategorylist> list = details.getKamgarcategorylist();
                        //Toast.makeText(CategoryActivity.this, list.size(),Toast.LENGTH_LONG).show();
                        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL, false);
                        recyclerView.setLayoutManager(layoutManager1);
                        recyclerView.setAdapter(new KamgarCategoryAdapter(getActivity(), list));
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
            public void onFailure(Call<KamgarCategoryResponse> call, Throwable t) {

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
