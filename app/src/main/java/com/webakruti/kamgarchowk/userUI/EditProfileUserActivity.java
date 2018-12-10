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



        /*city = (UserProfileResponse.City) getIntent().getSerializableExtra("cities");
        state = (UserProfileResponse.State) getIntent().getSerializableExtra("states");
        country = (UserProfileResponse.Country) getIntent().getSerializableExtra("countries");
        gender = (UserProfileResponse.Gender) getIntent().getSerializableExtra("gender");
*/
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

        user = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser();
        if (user != null) {
            editTextFname.setText(user.getFirstName());
            editTextLname.setText(user.getLastName());
           /* editTextDOB.setText(user.getSuccess().getAuthuser().getDob().toString());
            editTextAddress.setText(user.getSuccess().getAuthuser().getAddress().toString());*/
            editTextEmail.setText(user.getEmail());
            editTextMobile.setText(user.getMobileNo());
        }

        final List<UserProfileResponse.City> cities = SpinnerData.getSuccess().getCities();
        final List<UserProfileResponse.State> states = SpinnerData.getSuccess().getStates();
        final List<UserProfileResponse.Country> countries = SpinnerData.getSuccess().getCountries();
        final List<UserProfileResponse.Gender> gender = SpinnerData.getSuccess().getGender();

        ArrayAdapter<UserProfileResponse.City> adapterCity = new ArrayAdapter<UserProfileResponse.City>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, cities);
        spinnerCity.setAdapter(adapterCity);

        ArrayAdapter<UserProfileResponse.State> adapterState = new ArrayAdapter<UserProfileResponse.State>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, states);
        spinnerState.setAdapter(adapterState);

        ArrayAdapter<UserProfileResponse.Country> adapterCountry = new ArrayAdapter<UserProfileResponse.Country>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, countries);
        spinnerCountry.setAdapter(adapterCountry);

        ArrayAdapter<UserProfileResponse.Gender> adapterGender = new ArrayAdapter<UserProfileResponse.Gender>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, gender);
        spinnerGender.setAdapter(adapterGender);

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedCity = (UserProfileResponse.City) adapterView.getItemAtPosition(position);
                //cityid = selectedLocation.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedState = (UserProfileResponse.State) adapterView.getItemAtPosition(position);
                //cityid = selectedLocation.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedCountry = (UserProfileResponse.Country) adapterView.getItemAtPosition(position);
                //cityid = selectedLocation.getId();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedGender = (UserProfileResponse.Gender) adapterView.getItemAtPosition(position);
                //cityid = selectedLocation.getId();
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
                                             /*   if (spinnerGender.getText().toString().length() == 10) {
                                                    if (spinnerCountry.getText().toString().length() == 10) {
                                                        if (spinnerState.getText().toString().length() == 10) {
                                                            if (spinnerCity.getText().toString().length() == 10) {*/
                                                    CallUpdateUserAPI();
                                                    UpdateProfileResponse user = new UpdateProfileResponse();

                                                 //   SharedPreferenceManager.storeUserResponseObjectInSharedPreference(user);

                                                    Intent i = new Intent(EditProfileUserActivity.this,HomeActivity.class);
                                                    startActivity(i);
                                                    finish();


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

        SharedPreferenceManager.setApplicationContext(EditProfileUserActivity.this);

        String token = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getToken();
        Integer userid = SharedPreferenceManager.getUserObjectFromSharedPreference().getSuccess().getAuthuser().getId();

        //String API = "http://beta.kamgarchowk.com/api/";
        String headers = "Bearer " + token;
        Call<UpdateProfileResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).updateprofile(headers,userid,editTextFname.getText().toString(),editTextMname.getText().toString(),editTextLname.getText().toString(),editTextMobile.getText().toString(),editTextEmail.getText().toString(),editTextDOB.getText().toString(),editTextAddress.getText().toString(),selectedCity.toString(),selectedState.toString(),selectedCountry.toString(),selectedGender.toString(),editTextPincode.getText().toString());

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
