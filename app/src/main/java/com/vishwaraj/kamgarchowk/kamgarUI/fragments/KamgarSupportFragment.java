package com.vishwaraj.kamgarchowk.kamgarUI.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.vishwaraj.kamgarchowk.R;
import com.vishwaraj.kamgarchowk.kamgarUI.HomeOrProfileActivity;
import com.vishwaraj.kamgarchowk.model.SupportResponse;
import com.vishwaraj.kamgarchowk.retrofit.ApiConstants;
import com.vishwaraj.kamgarchowk.retrofit.service.RestClient;
import com.vishwaraj.kamgarchowk.utils.NetworkUtil;
import com.vishwaraj.kamgarchowk.utils.SharedPreferenceManager;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KamgarSupportFragment extends Fragment {

    private View rootView;
    private EditText editTextSubject;
    private EditText editTextProblemDetails;
    private Button buttonUserSupportSubmit;
    private ProgressDialog progressDialogForAPI;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_support, container, false);
        // Inflate the layout for this fragment

        SharedPreferenceManager.setApplicationContext(getActivity());

        initViews();

        return rootView;
    }

    private void initViews() {

        editTextSubject = (EditText) rootView.findViewById(R.id.editTextSubject);
        editTextProblemDetails = (EditText) rootView.findViewById(R.id.editTextProblemDetails);
        buttonUserSupportSubmit = (Button)rootView.findViewById(R.id.buttonUserSupportSubmit);

        buttonUserSupportSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextSubject.getText().toString().length() > 0) {
                    if (editTextProblemDetails.getText().toString().length() > 0) {

                        if (NetworkUtil.hasConnectivity(getActivity())) {
                            callSupportAPI();
                            //Toast.makeText(SupportActivity.this, "Request Sent To Support Team! We Will Contact You Soon!!!",Toast.LENGTH_LONG).show();

                        } else {
                           // Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(getActivity())
                                    .setMessage(R.string.no_internet_message)
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } else {
                      //  Toast.makeText(getActivity(), "Problem Detail Can't be Empty", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(getActivity())
                                .setMessage("Problem Detail Can't be Empty")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                   // Toast.makeText(getActivity(), "Subject Can't be Empty", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(getActivity())
                            .setMessage("Subject Can't be Empty")
                            .setPositiveButton("OK", null)
                            .show();
                }
            }
        });


    }

    private void callSupportAPI() {


        progressDialogForAPI = new ProgressDialog(getActivity());
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();



        String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();
        String FirstName = SharedPreferenceManager.getKamgarObject().getSuccess().getAuthkamgar().getFirstName();
        String LastName = SharedPreferenceManager.getKamgarObject().getSuccess().getAuthkamgar().getLastName();
        String ContactNumber = SharedPreferenceManager.getKamgarObject().getSuccess().getAuthkamgar().getMobileNo();

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<SupportResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).sendSupportRequest(headers,FirstName,LastName,ContactNumber,editTextSubject.getText().toString(),editTextProblemDetails.getText().toString());
        requestCallback.enqueue(new Callback<SupportResponse>() {
            @Override
            public void onResponse(Call<SupportResponse> call, Response<SupportResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    SupportResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSuccess() != null) {

                        /*Toast.makeText(getActivity(), details.getSuccess().getMsg(),Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getActivity(), HomeOrProfileActivity.class);
                        startActivity(intent);*/

                        new AlertDialog.Builder(getActivity())
                                .setMessage(details.getSuccess().getMsg())
                                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int whichButton) {
                                        Intent intent = new Intent(getActivity(), HomeOrProfileActivity.class);
                                        startActivity(intent);
                                    }
                                })
                                .show();
                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<SupportResponse> call, Throwable t) {

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
