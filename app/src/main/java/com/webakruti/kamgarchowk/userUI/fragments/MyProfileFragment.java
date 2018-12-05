package com.webakruti.kamgarchowk.userUI.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.adapter.UserMyEnquiryAdapter;
import com.webakruti.kamgarchowk.userUI.ChangePasswordActivity;
import com.webakruti.kamgarchowk.userUI.EditProfileUserActivity;
import com.webakruti.kamgarchowk.userUI.ForgotPasswordUserActivity;
import com.webakruti.kamgarchowk.userUI.HomeActivity;
import com.webakruti.kamgarchowk.userUI.MyEnquiryActivity;
import com.webakruti.kamgarchowk.userUI.SupportActivity;
import com.webakruti.kamgarchowk.userUI.UserLoginActivity;
import com.webakruti.kamgarchowk.userUI.UserRegistrationActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

public class MyProfileFragment extends Fragment {
    //public class MyProfileFragment extends Fragment implements View.OnClickListener{
    private View rootView;

    private FragmentManager fragManager;

    private ImageView imageViewUserImage;
    private TextView textViewUserName;
    private TextView textViewUserMobileNo;
    private ImageView imageViewUserEdit;
    private TextView textViewEmail;
    private TextView textViewMobile;
    private TextView textViewAddress;
    private TextView textViewCity;
    private TextView textViewState;
    private TextView textViewCountry;
    private TextView textViewPincode;
    private LinearLayout linearLayoutGotoMyenquiry;
    private LinearLayout linearLayoutGotoChangePassword;
    private LinearLayout linearLayoutGotoSupport;
    private LinearLayout linearLayoutGotoLogout;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_my_profile, container, false);
        fragManager = getFragmentManager();
        //fragManager.beginTransaction().replace(R.id.home_container, new HomeFragment()).commit();
        initView();
        return rootView;
    }



    private void initView()
    {
        imageViewUserImage = (ImageView)rootView.findViewById(R.id.imageViewUserImage);
        imageViewUserEdit = (ImageView)rootView.findViewById(R.id.imageViewUserEdit);
        imageViewUserEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),EditProfileUserActivity.class);
                startActivity(intent);
            }
        });

        textViewUserName = (TextView)rootView.findViewById(R.id.textViewUserName);
        textViewUserMobileNo = (TextView)rootView.findViewById(R.id.textViewUserMobileNo);
        textViewEmail = (TextView)rootView.findViewById(R.id.textViewEmail);
        textViewMobile = (TextView)rootView.findViewById(R.id.textViewMobile);
        textViewAddress = (TextView)rootView.findViewById(R.id.textViewAddress);
        textViewCity = (TextView)rootView.findViewById(R.id.textViewCity);
        textViewState = (TextView)rootView.findViewById(R.id.textViewState);
        textViewCountry = (TextView)rootView.findViewById(R.id.textViewCountry);
        textViewPincode = (TextView)rootView.findViewById(R.id.textViewPincode);
        linearLayoutGotoMyenquiry = (LinearLayout)rootView.findViewById(R.id.linearLayoutGotoMyenquiry);
        linearLayoutGotoMyenquiry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),MyEnquiryActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutGotoChangePassword = (LinearLayout)rootView.findViewById(R.id.linearLayoutGotoChangePassword);
        linearLayoutGotoChangePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutGotoSupport = (LinearLayout)rootView.findViewById(R.id.linearLayoutGotoSupport);
        linearLayoutGotoSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),SupportActivity.class);
                startActivity(intent);
            }
        });

        linearLayoutGotoLogout = (LinearLayout)rootView.findViewById(R.id.linearLayoutGotoLogout);
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
                        Intent intent = new Intent(getContext(), UserLoginActivity.class);
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

    /*@Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.linearLayoutGotoMyenquiry:
                Intent intent = new Intent(getContext(), MyEnquiryActivity.class);
                startActivity(intent);

                break;

            case R.id.linearLayoutGotoChangePassword:
                Intent intent1 = new Intent(getContext(), ChangePasswordActivity.class);
                startActivity(intent1);

                break;

            case R.id.linearLayoutGotoSupport:
                Intent intent2 = new Intent(getContext(), SupportActivity.class);
                startActivity(intent2);
                break;

            case R.id.imageViewUserEdit:
                Intent intent3 = new Intent(getContext(), EditProfileUserActivity.class);
                startActivity(intent3);
                break;

            case R.id.linearLayoutGotoLogout:
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
                        Intent intent = new Intent(getContext(), UserLoginActivity.class);
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

                break;
        }
    }*/


}
