package com.webakruti.kamgarchowk.userUI.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.userUI.HomeActivity;

public class SupportFragment extends Fragment {

    private View rootView;
    private EditText editTextSubject;
    private EditText editTextProblemDetails;
    private Button buttonUserSupportSubmit;
    //private ProgressDialog progressDialogForAPI;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_support, container, false);
        // Inflate the layout for this fragment

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
                Intent intent = new Intent(getContext(), HomeActivity.class);
                startActivity(intent);
            }
        });


    }

}
