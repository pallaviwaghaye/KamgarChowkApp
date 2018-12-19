package com.webakruti.kamgarchowk.kamgarUI.fragments;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.KamgarCategoryActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarChangePasswordActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarDocumentsActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarEditProfileActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarLoginActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarMyOrdersActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarSubscriptionPlanActivity;
import com.webakruti.kamgarchowk.kamgarUI.KamgarSupportActivity;
import com.webakruti.kamgarchowk.model.KamgarGetProfile;
import com.webakruti.kamgarchowk.model.UserProfileResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.ChangePasswordActivity;
import com.webakruti.kamgarchowk.userUI.EditProfileUserActivity;
import com.webakruti.kamgarchowk.userUI.HomeActivity;
import com.webakruti.kamgarchowk.userUI.UserLoginActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.io.Serializable;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeOrProfileFragment extends Fragment {

    private View rootView;

    private FragmentManager fragManager;

    private ImageView imageViewKamgarImage;
    private TextView textViewKamgarFullname;
    private TextView textViewKamgarMobNo;
    private ImageView imageViewKamgarEdit;
    private TextView textViewkamgarEmail;
    private TextView textViewKamgarMobile;
    private TextView textViewKamgarAddress;
    private TextView textViewPincode;
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
        fragManager = getFragmentManager();
        // Inflate the layout for this fragment

        initViews();

        if (NetworkUtil.hasConnectivity(getActivity())) {
            callGetKamgarProfile();
        } else {
            Toast.makeText(getActivity(), "No Internet connection", Toast.LENGTH_SHORT).show();
        }

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
        textViewPincode = (TextView)rootView.findViewById(R.id.textViewPincode);

        linearLayoutGotoCategory = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoCategory);
        linearLayoutGotoMyorders = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoMyorders);
        linearLayoutGotoDocuments = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoDocuments);
        linearLayoutGotoSubscriptionPlan = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoSubscriptionPlan);
        linearLayoutGotoChangePassword = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoChangePassword);
        linearLayoutGotoSupport = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoSupport);
        linearLayoutGotoLogout = (LinearLayout) rootView.findViewById(R.id.linearLayoutGotoLogout);


       /* imageViewKamgarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),KamgarEditProfileActivity.class);
                startActivity(intent);
            }
        });
*/
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


    private void callGetKamgarProfile() {
        final ProgressDialog progressDialog = new ProgressDialog(getActivity());
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Please wait...");
        progressDialog.show();


        SharedPreferenceManager.setApplicationContext(getActivity());
        String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();
        //final Integer userid = SharedPreferenceManager.getKamgarObject().getSuccess().getAuthuser().getId();

        String headers = "Bearer " + token;
        Call<KamgarGetProfile> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).kamgarprofile(headers);
        requestCallback.enqueue(new Callback<KamgarGetProfile>() {
            @Override
            public void onResponse(Call<KamgarGetProfile> call, Response<KamgarGetProfile> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarGetProfile kamgarGetProfile = response.body();

                    if(kamgarGetProfile != null)
                    {
                        setUIData(kamgarGetProfile);

                    }

                } else {
                    // Response code is 401
                }

                if (progressDialog != null) {
                    progressDialog.cancel();
                }
            }

            @Override
            public void onFailure(Call<KamgarGetProfile> call, Throwable t) {

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

    private void setUIData(final KamgarGetProfile details)
    {

        KamgarGetProfile.Authkamgar list = details.getSuccess().getAuthkamgar();

        textViewKamgarFullname.setText(list.getFirstName()+" "+list.getLastName());
        textViewKamgarMobile.setText(list.getMobileNo());
        textViewKamgarMobNo.setText(list.getMobileNo());

        Picasso.with(getActivity())
                .load(list.getContImgUrl())
                .placeholder(R.drawable.kamgarborder)
                .resize(300, 300)
                .into(imageViewKamgarImage);

        if(list.getEmail()!= null) {
            textViewkamgarEmail.setText(list.getEmail());
        }else{
            textViewkamgarEmail.setText("N/A");
        }
        if (list.getAddress()!=null && list.getCity()!=null && list.getState()!=null && list.getCountry()!=null) {
            textViewKamgarAddress.setText(list.getAddress()+", "+list.getCity().getName()+", "+list.getState().getName()+", "+list.getCountry().getName());
            /*if(list.get(0).getPincode() > 0) {
                textViewPincode.setText(list.get(0).getPincode());
            }else{
                textViewPincode.setText("");
            }*/
        } else {
            textViewKamgarAddress.setText("N/A");
            //textViewPincode.setText("");
        }

     /*   if(list.get(0).getPincode() > 0) {
            textViewPincode.setText(list.get(0).getPincode());
        }else{
            textViewPincode.setText("");
        }*/

        imageViewKamgarEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(),KamgarEditProfileActivity.class);

                intent.putExtra("SpinnerData", (Serializable) details);
               /* intent.putExtra("cities", (Serializable) cities);
                intent.putExtra("states", (Serializable) states);
                intent.putExtra("countries", (Serializable) countries);
                intent.putExtra("gender", (Serializable) gender);*/

                startActivity(intent);
            }
        });

    }

}
