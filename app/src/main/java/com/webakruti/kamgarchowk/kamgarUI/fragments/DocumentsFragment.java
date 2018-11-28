package com.webakruti.kamgarchowk.kamgarUI.fragments;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.HomeOrProfileActivity;


public class DocumentsFragment extends Fragment {

    private View rootView;
    private EditText editTextPanNumber;
    private EditText editTextChoosePancard;

    private RelativeLayout relativeLayoutPANUpload;

    private EditText editTextBankName;
    private EditText editTextAccountNo;
    private EditText editTextChooseBankPassbook;
    private RelativeLayout relativeLayoutPassbookUpload;

    private Button buttonDocumentSubmit;
    //private ProgressDialog progressDialogForAPI;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_documents, container, false);
        // Inflate the layout for this fragment

        initViews();

        return rootView;
    }

    private void initViews() {

        editTextPanNumber = (EditText) rootView.findViewById(R.id.editTextPanNumber);
        editTextChoosePancard = (EditText) rootView.findViewById(R.id.editTextChoosePancard);
        editTextBankName = (EditText) rootView.findViewById(R.id.editTextBankName);
        editTextAccountNo = (EditText) rootView.findViewById(R.id.editTextAccountNo);
        editTextChooseBankPassbook = (EditText) rootView.findViewById(R.id.editTextChooseBankPassbook);
        relativeLayoutPANUpload = (RelativeLayout) rootView.findViewById(R.id.relativeLayoutPANUpload);
        relativeLayoutPassbookUpload = (RelativeLayout) rootView.findViewById(R.id.relativeLayoutPassbookUpload);
        buttonDocumentSubmit = (Button)rootView.findViewById(R.id.buttonDocumentSubmit);

        buttonDocumentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeOrProfileActivity.class);
                startActivity(intent);

            }
        });


    }



}
