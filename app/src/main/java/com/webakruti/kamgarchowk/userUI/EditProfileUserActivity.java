package com.webakruti.kamgarchowk.userUI;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.model.ChangePasswordResponse;
import com.webakruti.kamgarchowk.model.HomeResponse;
import com.webakruti.kamgarchowk.model.SearchLocationList;
import com.webakruti.kamgarchowk.model.UpdateProfileResponse;
import com.webakruti.kamgarchowk.model.User;
import com.webakruti.kamgarchowk.model.UserLoginResponse;
import com.webakruti.kamgarchowk.model.UserProfileResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.webakruti.kamgarchowk.model.UserProfileResponse.*;

public class EditProfileUserActivity extends AppCompatActivity implements View.OnClickListener{
    private ImageView imageViewBack;
    private TextView textViewEditHeading;
    Calendar myCalendar;

    private EditText editTextFname;
    private EditText editTextMname;
    private EditText editTextLname;
    private EditText editTextDOB;
    private EditText editTextEmail;
    private EditText editTextMobile;
    private EditText editTextAddress;


    private Spinner spinnerGender;
    private Spinner spinnerCountry;
    private Spinner spinnerState;
    private Spinner spinnerCity;
    private EditText editTextPincode;

    String defaultCity = "Select city";
    String defaultState = "Select state";
    String defaultCountry = "Select country";
    String defaultGender = "Select gender";

    UserProfileResponse.City selectedCity;
    UserProfileResponse.State selectedState;
    UserProfileResponse.Country selectedCountry;
    UserProfileResponse.Gender selectedGender;

    Integer spinnerGenderId;
    Integer spinnerCityId;
    Integer spinnerStateId;
    Integer spinnerCountryId;

    private UserProfileResponse SpinnerData;

    private Button buttonSave;

    private UserLoginResponse.Authuser user;
    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);

        SharedPreferenceManager.setApplicationContext(EditProfileUserActivity.this);

        SpinnerData = (UserProfileResponse)getIntent().getSerializableExtra("SpinnerData");

        initViews();
    }


    private void initViews()
    {
        imageViewBack = (ImageView)findViewById(R.id.imageViewBack);
        textViewEditHeading = (TextView)findViewById(R.id.textViewEditHeading);
        editTextFname = (EditText)findViewById(R.id.editTextFname);
        editTextMname = (EditText)findViewById(R.id.editTextMname);
        editTextLname = (EditText)findViewById(R.id.editTextLname);
        editTextDOB = (EditText)findViewById(R.id.editTextDOB);
        editTextDOB.setOnClickListener(this);
        myCalendar = Calendar.getInstance();
        editTextEmail = (EditText)findViewById(R.id.editTextEmail);
        editTextMobile = (EditText)findViewById(R.id.editTextMobile);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);

        spinnerGender = (Spinner)findViewById(R.id.spinnerGender);
        spinnerCountry = (Spinner)findViewById(R.id.spinnerCountry);
        spinnerState = (Spinner)findViewById(R.id.spinnerState);
        spinnerCity = (Spinner)findViewById(R.id.spinnerCity);

        selectedCity = new UserProfileResponse.City();
        selectedCity.setName(defaultCity);

        selectedState = new UserProfileResponse.State();
        selectedState.setName(defaultState);

        selectedCountry = new UserProfileResponse.Country();
        selectedCountry.setName(defaultCountry);

        selectedGender = new UserProfileResponse.Gender();
        selectedGender.setName(defaultGender);

        editTextPincode = (EditText)findViewById(R.id.editTextPincode);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

       /* user = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser();
        if (user != null) {
            editTextFname.setText(user.getFirstName());
            editTextLname.setText(user.getLastName());
           *//* editTextDOB.setText(user.getSuccess().getAuthuser().getDob().toString());
            editTextAddress.setText(user.getSuccess().getAuthuser().getAddress().toString());*//*
            editTextEmail.setText(user.getEmail());
            editTextMobile.setText(user.getMobileNo());
        }*/

        final List<UserProfileResponse.City> cities = SpinnerData.getSuccess().getCities();
        final List<UserProfileResponse.State> states = SpinnerData.getSuccess().getStates();
        final List<UserProfileResponse.Country> countries = SpinnerData.getSuccess().getCountries();
        final List<UserProfileResponse.Gender> gender = SpinnerData.getSuccess().getGender();

        setCitySpinner(cities);
        setStateSpinner(states);
        setCountrySpinner(countries);
        setGenderSpinner(gender);
    }


    private void setCitySpinner(List<UserProfileResponse.City> cityList) {

        List<UserProfileResponse.City> finalList = new ArrayList<>();

        UserProfileResponse.City city = new UserProfileResponse.City();
        city.setId(-1);
        city.setName(defaultCity);

        finalList.add(city);
        finalList.addAll(cityList);
        ArrayAdapter<UserProfileResponse.City> adapterStation = new ArrayAdapter<UserProfileResponse.City>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerCity.setAdapter(adapterStation);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedCity = (UserProfileResponse.City) adapterView.getItemAtPosition(position);
                spinnerCityId = selectedCity.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setStateSpinner(List<UserProfileResponse.State> stateList) {

        List<UserProfileResponse.State> finalList = new ArrayList<>();

        UserProfileResponse.State state1 = new UserProfileResponse.State();
        state1.setId(-1);
        state1.setName(defaultState);

        finalList.add(state1);
        finalList.addAll(stateList);
        ArrayAdapter<UserProfileResponse.State> adapterStation = new ArrayAdapter<UserProfileResponse.State>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerState.setAdapter(adapterStation);

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedState = (UserProfileResponse.State) adapterView.getItemAtPosition(position);
                spinnerStateId =selectedState.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setCountrySpinner(List<UserProfileResponse.Country> countryList) {

        List<UserProfileResponse.Country> finalList = new ArrayList<>();

        UserProfileResponse.Country country = new UserProfileResponse.Country();
        country.setId(-1);
        country.setName(defaultCountry);

        finalList.add(country);
        finalList.addAll(countryList);
        ArrayAdapter<UserProfileResponse.Country> adapterStation = new ArrayAdapter<UserProfileResponse.Country>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerCountry.setAdapter(adapterStation);

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedCountry = (UserProfileResponse.Country) adapterView.getItemAtPosition(position);
                spinnerCountryId = selectedCountry.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setGenderSpinner(List<UserProfileResponse.Gender> genderList) {

        List<UserProfileResponse.Gender> finalList = new ArrayList<>();

        UserProfileResponse.Gender gender = new UserProfileResponse.Gender();
        gender.setId(-1);
        gender.setValue(defaultGender);

        finalList.add(gender);
        finalList.addAll(genderList);
        ArrayAdapter<UserProfileResponse.Gender> adapterStation = new ArrayAdapter<UserProfileResponse.Gender>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerGender.setAdapter(adapterStation);

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedGender = (UserProfileResponse.Gender) adapterView.getItemAtPosition(position);
                spinnerGenderId = selectedGender.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.buttonSave:
                if (editTextFname.getText().toString().length() > 0) {
                    if (editTextLname.getText().toString().length() > 0) {
                        if (editTextDOB.getText().toString().length() > 0) {
                            if (editTextEmail.getText().toString().length() > 0) {
                                if (isValidEmailAddress(editTextEmail.getText().toString().trim())) {
                                        if (editTextMobile.getText().toString().length() > 0) {
                                            if (editTextMobile.getText().toString().length() == 10) {
                                                if (editTextAddress.getText().toString().length() > 0) {
                                                if (selectedCity.toString().length() > 0) {
                                                    if (selectedState.toString().length() > 0) {
                                                        if (selectedCountry.toString().length() > 0) {
                                                            if (selectedGender.toString().length() > 0) {
                                                                CallUpdateUserAPI();
                                                                //UpdateProfileResponse user = new UpdateProfileResponse();

                                                             //   SharedPreferenceManager.storeUserResponseObjectInSharedPreference(user);

                                                                Intent i = new Intent(EditProfileUserActivity.this,HomeActivity.class);
                                                                startActivity(i);
                                                                finish();
                                                            }else{
                                                                Toast.makeText(EditProfileUserActivity.this, "Select gender", Toast.LENGTH_SHORT).show();
                                                            }
                                                        }else{
                                                            Toast.makeText(EditProfileUserActivity.this, "Select country", Toast.LENGTH_SHORT).show();
                                                        }
                                                    }else{
                                                        Toast.makeText(EditProfileUserActivity.this, "Select state", Toast.LENGTH_SHORT).show();
                                                    }
                                                }else{
                                                    Toast.makeText(EditProfileUserActivity.this, "Select city", Toast.LENGTH_SHORT).show();
                                                }

                                                }else{
                                                    Toast.makeText(EditProfileUserActivity.this, "Address Can't be empty", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(EditProfileUserActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(EditProfileUserActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                                        }
                                } else {
                                    Toast.makeText(EditProfileUserActivity.this, "Email Id must be valid", Toast.LENGTH_SHORT).show();
                                }
                            }else{
                                Toast.makeText(EditProfileUserActivity.this, "Email Id Can't be empty", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(EditProfileUserActivity.this, "Date of birth Can't be empty", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(EditProfileUserActivity.this, "Last Name Can't be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(EditProfileUserActivity.this, "First Name Can't be empty", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.editTextDOB:
                DatePickerDialog dlg = new DatePickerDialog(getApplicationContext(), date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH));
                dlg.getDatePicker().setMaxDate(System.currentTimeMillis());
                dlg.show();
                break;

        }
    }

    private void CallUpdateUserAPI() {

        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();


        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();
        Integer userid = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getId();

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<UpdateProfileResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).updateprofile(headers,userid,editTextFname.getText().toString(),editTextMname.getText().toString(),editTextLname.getText().toString(),editTextDOB.getText().toString(),spinnerGenderId,editTextEmail.getText().toString(),editTextMobile.getText().toString(),editTextAddress.getText().toString(),spinnerCountryId,spinnerStateId,spinnerCityId,editTextPincode.getText().toString());

        requestCallback.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    UpdateProfileResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSuccess() != null) {

                        Toast.makeText(EditProfileUserActivity.this, details.getSuccess().getMsg(),Toast.LENGTH_LONG).show();
                        //user.setId(user.getUserId());
                        user.setFirstName(details.getSuccess().getAuthuser().getFirstName());
                        user.setLastName(details.getSuccess().getAuthuser().getLastName());
                        user.setEmail(details.getSuccess().getAuthuser().getEmail());
                        user.setMobileNo(details.getSuccess().getAuthuser().getMobileNo());
                        user.setDob(details.getSuccess().getAuthuser().getDob());
                        user.setAddress(details.getSuccess().getAuthuser().getAddress());
                        user.setPincode(details.getSuccess().getAuthuser().getPincode());
                        user.setCityId(details.getSuccess().getAuthuser().getCity());
                        user.setStateId(details.getSuccess().getAuthuser().getState());
                        user.setCountryId(details.getSuccess().getAuthuser().getCountry());
                        user.setGenderId(details.getSuccess().getAuthuser().getGender());


                        /*Intent intent = new Intent(EditProfileUserActivity.this,HomeActivity.class);
                        startActivity(intent);
                        finish();*/
                    }

                } else {
                    // Response code is 401
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<UpdateProfileResponse> call, Throwable t) {

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


    DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            updateLabel();
        }
    };

    private void updateLabel() {
        String myFormat = "dd/MM/yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat);
        editTextDOB.setText(sdf.format(myCalendar.getTime()));
    }
    public boolean isValidEmailAddress(String email) {
                String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
                java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
                java.util.regex.Matcher m = p.matcher(email);
                return m.matches();
    }


}
