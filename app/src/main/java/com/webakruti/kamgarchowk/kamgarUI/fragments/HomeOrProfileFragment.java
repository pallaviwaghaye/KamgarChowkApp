package com.webakruti.kamgarchowk.kamgarUI.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.KamgarCategoryActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarChangePasswordActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarDocumentsActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarEditProfileActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarLoginActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarMyOrdersActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarSubscriptionPlanActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarSupportActivity;
import com.webakruti.kamgarchowk.userUI.ChangePasswordActivity;
import com.webakruti.kamgarchowk.userUI.HomeActivity;
import com.webakruti.kamgarchowk.userUI.UserLoginActivity;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

public class HomeOrProfileFragment extends Fragment {

    private View rootView;
    private ImageView imageViewKamgarImage;
    private TextView textViewKamgarFullname;
    private TextView textViewKamgarMobNo;
    private ImageView imageViewKamgarEdit;
    private TextView textViewkamgarEmail;
    private TextView textViewKamgarMobile;
    private TextView textViewKamgarAddress;
    private LinearLayout linearLayoutGotoCategory;
    private LinearLayout linearLayoutGotoMyorders;
    private LinearLayout linearLayoutGotoDocuments;
    private LinearLayout linearLayoutGotoSubscriptionPlan;
    private LinearLayout linearLayoutGotoChangePassword;
    private LinearLayout linearLayoutGotoSupport;
    private LinearLayout linearLayoutGotoLogout;

    //private ProgressDialog progressDialogForAPI;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_home_profile, container, false);
        // Inflate the layout for this fragment

        initViews();

        return rootView;
    }

    private void initViews() {

        imageViewKamgarImage = (ImageView)rootView.findViewById(R.id.imageViewKamgarImage);
        imageViewKamgarEdit = (ImageView)rootView.findViewById(R.id.imageViewKamgarEdit);

        textViewKamgarFullname = (TextView)rootView.findViewById(R.id.textViewKamgarFullname);
        textViewKamgarMobNo = (TextView)rootView.findViewById(R.id.textViewKamgarMobNo);
        textViewkamgarEmail = (TextView)rootView.findViewById(R.id.textViewkamgarEmail);
        textViewKamgarMobile = (TextView)rootView.findViewById(R.id.textViewKamgarMobile);
        textViewKamgarAddress = (TextView)rootView.findViewById(R.id.textViewKamgarAddress);

        linearLayoutGotoCategory = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoCategory);
        linearLayoutGotoMyorders = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoMyorders);
        linearLayoutGotoDocuments = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoDocuments);
        linearLayoutGotoSubscriptionPlan = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoSubscriptionPlan);
        linearLayoutGotoChangePassword = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoChangePassword);
        linearLayoutGotoSupport = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoSupport);
        linearLayoutGotoLogout = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoLogout);


        imageViewKamgarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),KamgarEditProfileActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutGotoCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(),KamgarCategoryActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutGotoMyorders.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),KamgarMyOrdersActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutGotoDocuments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),KamgarDocumentsActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutGotoSubscriptionPlan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),KamgarSubscriptionPlanActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutGotoChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),KamgarChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutGotoSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),KamgarSupportActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutGotoLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                // Setting Dialog Title
                alertDialog.setTitle("Logout");
                // Setting Dialog Message
                alertDialog.setMessage("Are you sure to logout?");
                // Setting Icon to Dialog
                // Setting Positive "Yes" Button
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferenceManager.clearPreferences();
                        Intent intent = new Intent(getContext(), KamgarLoginActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                // Showing Alert Message
                alertDialog.show();
            }
        });
    }

}
