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
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.webakruti.kamgarchowk.model.UserProfileResponse.*;

public class EditProfileUserActivity extends AppCompatActivity implements View.OnClickListener {
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


    private UserProfileResponse userData;

    private Button buttonSave;

    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_user);

        SharedPreferenceManager.setApplicationContext(EditProfileUserActivity.this);

        userData = (UserProfileResponse) getIntent().getSerializableExtra("SpinnerData");
        initViews();

        setData(userData.getSuccess().getAuthuser().get(0));
    }

    private void setData(UserProfileResponse.Authuser user) {
        editTextFname.setText(user.getFirstName());
        editTextLname.setText(user.getLastName());
        editTextAddress.setText(user.getAddress());
        editTextEmail.setText(user.getEmail());
        editTextMname.setText(user.getMiddleName());
        editTextPincode.setText(user.getPincode() + "");
        editTextMobile.setText(user.getMobileNo());
        editTextDOB.setText(user.getDob());

        initInitialSpinnerData(user);
    }

    private void initViews() {
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);
        textViewEditHeading = (TextView) findViewById(R.id.textViewEditHeading);
        editTextFname = (EditText) findViewById(R.id.editTextFname);
        editTextMname = (EditText) findViewById(R.id.editTextMname);
        editTextLname = (EditText) findViewById(R.id.editTextLname);
        editTextDOB = (EditText) findViewById(R.id.editTextDOB);
        editTextDOB.setOnClickListener(this);
        myCalendar = Calendar.getInstance();
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextMobile = (EditText) findViewById(R.id.editTextMobile);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);

        spinnerGender = (Spinner) findViewById(R.id.spinnerGender);
        spinnerCountry = (Spinner) findViewById(R.id.spinnerCountry);
        spinnerState = (Spinner) findViewById(R.id.spinnerState);
        spinnerCity = (Spinner) findViewById(R.id.spinnerCity);

        editTextPincode = (EditText) findViewById(R.id.editTextPincode);

        buttonSave = (Button) findViewById(R.id.buttonSave);
        buttonSave.setOnClickListener(this);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void initInitialSpinnerData(Authuser user) {
        setInitialSpinnerAdapter(user);
    }

    private void setInitialSpinnerAdapter(Authuser user) {

        final List<UserProfileResponse.Country> countries = userData.getSuccess().getCountry(); // Data -- Country

        setCountrySpinner(countries, user.getCountry(), user);
        setGenderSpinner(userData.getSuccess().getGender(), user.getGender());
    }


    private void setCountrySpinner(List<UserProfileResponse.Country> countryList, Country countryObj, Authuser user) {

        List<UserProfileResponse.Country> finalList = new ArrayList<>();

        selectedCountry = new UserProfileResponse.Country();
        selectedCountry.setName(defaultCountry);
        selectedCountry.setId(-1);

        finalList.add(selectedCountry);
        finalList.addAll(countryList);
        ArrayAdapter<UserProfileResponse.Country> adapterStation = new ArrayAdapter<UserProfileResponse.Country>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerCountry.setAdapter(adapterStation);
        final List<UserProfileResponse.State> stateList = new ArrayList<>();
        final List<UserProfileResponse.City> cityList = new ArrayList<>();
        // authuser's country, state, city is null
        selectedCity = new UserProfileResponse.City();
        selectedCity.setName(defaultCity);
        selectedCity.setId(-1);

        selectedState = new UserProfileResponse.State();
        selectedState.setName(defaultState);
        selectedState.setId(-1);
        // set normal spinner for State, City
        stateList.add(selectedState);
        cityList.add(selectedCity);
        if (countryObj == null && countryObj.getName() == null) {

            setInitialSpinnersForStateAndCity(stateList, cityList);
        } else {
            // if authuser country obj is not null, means others like state and city also won't be null
            // so, set country spinner
            int pos = finalList.indexOf(countryObj);
            spinnerCountry.setSelection(pos, false);
            selectedCountry.setName(countryObj.getName());
            selectedCountry.setId(countryObj.getId());

            // get states for selected country
            List<State> states = finalList.get(pos).getStates();
            // set adapter with these states
            setSelectedStateSpinner(states, user.getState(), user);


        }
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedCountry = (UserProfileResponse.Country) adapterView.getItemAtPosition(position);
                if (selectedCountry.getId() != -1) {
                    // set state spinner
                    setStateSpinner(selectedCountry.getStates());
                } else {
                    setInitialSpinnersForStateAndCity(stateList, cityList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerState.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedState = (UserProfileResponse.State) adapterView.getItemAtPosition(position);
                if (selectedState.getId() != -1) {
                    setCitySpinner(selectedState.getCities());
                } else {
                    setInitialSpinnersForCity(cityList);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerCity.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedCity = (UserProfileResponse.City) adapterView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setInitialSpinnersForStateAndCity(List<State> stateList, List<City> cityList) {
        ArrayAdapter<UserProfileResponse.State> adapterState = new ArrayAdapter<State>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, stateList);
        spinnerState.setAdapter(adapterState);

        ArrayAdapter<UserProfileResponse.City> adapterCity = new ArrayAdapter<City>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, cityList);
        spinnerCity.setAdapter(adapterCity);
    }


    private void setInitialSpinnersForCity(List<City> cityList) {

        ArrayAdapter<UserProfileResponse.City> adapterCity = new ArrayAdapter<City>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, cityList);
        spinnerCity.setAdapter(adapterCity);
    }


    private void setStateSpinner(List<UserProfileResponse.State> stateList) {

        List<UserProfileResponse.State> finalList = new ArrayList<>();

        selectedState = new UserProfileResponse.State();
        selectedState.setName(defaultState);
        selectedState.setId(-1);

        finalList.add(selectedState);
        finalList.addAll(stateList);
        ArrayAdapter<UserProfileResponse.State> adapterStation = new ArrayAdapter<UserProfileResponse.State>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerState.setAdapter(adapterStation);

    }


    private void setSelectedStateSpinner(List<UserProfileResponse.State> stateList, State state, Authuser user) {

        List<UserProfileResponse.State> finalList = new ArrayList<>();

        selectedState = new UserProfileResponse.State();
        selectedState.setName(defaultState);
        selectedState.setId(-1);

        finalList.add(selectedState);
        finalList.addAll(stateList);
        ArrayAdapter<UserProfileResponse.State> adapterStation = new ArrayAdapter<UserProfileResponse.State>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerState.setAdapter(adapterStation);

        int pos = finalList.indexOf(state);
        spinnerState.setSelection(pos, false);

        List<City> cityList = finalList.get(pos).getCities();
        setSelectedCitySpinner(cityList, user.getCity());

        selectedState.setName(state.getName());
        selectedState.setId(state.getId());
    }

    private void setCitySpinner(List<UserProfileResponse.City> cityList) {

        List<UserProfileResponse.City> finalList = new ArrayList<>();

        selectedCity = new UserProfileResponse.City();
        selectedCity.setName(defaultCity);
        selectedCity.setId(-1);

        finalList.add(selectedCity);
        finalList.addAll(cityList);
        ArrayAdapter<UserProfileResponse.City> adapterStation = new ArrayAdapter<UserProfileResponse.City>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerCity.setAdapter(adapterStation);


    }

    private void setSelectedCitySpinner(List<UserProfileResponse.City> cityList, City city) {

        List<UserProfileResponse.City> finalList = new ArrayList<>();

        selectedCity = new UserProfileResponse.City();
        selectedCity.setName(defaultCity);
        selectedCity.setId(-1);

        finalList.add(selectedCity);
        finalList.addAll(cityList);
        ArrayAdapter<UserProfileResponse.City> adapterStation = new ArrayAdapter<UserProfileResponse.City>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerCity.setAdapter(adapterStation);

        int pos = finalList.indexOf(city);
        spinnerCity.setSelection(pos, false);

        selectedCity.setName(city.getName());
        selectedCity.setId(city.getId());
    }

    private void setGenderSpinner(List<UserProfileResponse.Gender> genderList, Gender gender) {

        List<UserProfileResponse.Gender> finalList = new ArrayList<>();

        selectedGender = new UserProfileResponse.Gender();
        selectedGender.setValue(defaultGender);
        selectedGender.setId(-1);

        finalList.add(selectedGender);
        finalList.addAll(genderList);
        ArrayAdapter<UserProfileResponse.Gender> adapterStation = new ArrayAdapter<UserProfileResponse.Gender>(EditProfileUserActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerGender.setAdapter(adapterStation);


        if (gender != null && gender.getValue() != null) {
            int pos = finalList.indexOf(gender);
            spinnerGender.setSelection(pos, false);

            selectedGender.setName(gender.getValue());
            selectedGender.setId(gender.getId());
        }

        spinnerGender.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedGender = (UserProfileResponse.Gender) adapterView.getItemAtPosition(position);
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
                                            if (selectedGender.getId() != -1) {
                                                if (editTextAddress.getText().toString().length() > 0) {
                                                    if (selectedCountry.getId() != -1) {
                                                        if (selectedState.getId() != -1) {
                                                            if (selectedCity.getId() != -1) {

                                                                if (NetworkUtil.hasConnectivity(EditProfileUserActivity.this)) {
                                                                    CallUpdateUserAPI();
                                                                }
                                                            } else {
                                                                Toast.makeText(EditProfileUserActivity.this, "Select city", Toast.LENGTH_SHORT).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(EditProfileUserActivity.this, "Select state", Toast.LENGTH_SHORT).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(EditProfileUserActivity.this, "Select country", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(EditProfileUserActivity.this, "Address can't be empty", Toast.LENGTH_SHORT).show();
                                                }

                                            } else {
                                                Toast.makeText(EditProfileUserActivity.this, "Select gender", Toast.LENGTH_SHORT).show();
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
                            } else {
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
                DatePickerDialog dlg = new DatePickerDialog(EditProfileUserActivity.this, date, myCalendar
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
        Call<UpdateProfileResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).updateprofile(headers, userid, editTextFname.getText().toString(), editTextMname.getText().toString(), editTextLname.getText().toString(), editTextDOB.getText().toString(), selectedGender.getId(), editTextMobile.getText().toString(), editTextEmail.getText().toString(), editTextAddress.getText().toString(), selectedCountry.getId(), selectedState.getId(), selectedCity.getId(), editTextPincode.getText().toString());

        requestCallback.enqueue(new Callback<UpdateProfileResponse>() {
            @Override
            public void onResponse(Call<UpdateProfileResponse> call, Response<UpdateProfileResponse> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    UpdateProfileResponse details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSuccess() != null) {

                        Toast.makeText(EditProfileUserActivity.this, details.getSuccess().getMsg(), Toast.LENGTH_LONG).show();
                        //user.setId(user.getUserId());

                        UpdateProfileResponse.Authuser authuser = details.getSuccess().getAuthuser();

                        UserLoginResponse user = SharedPreferenceManager.getUserObjectFromSharedPreference();
                        user.getSuccess().getAuthuser().setFirstName(authuser.getFirstName());
                        user.getSuccess().getAuthuser().setEmail(authuser.getEmail());
                        user.getSuccess().getAuthuser().setLastName(authuser.getLastName());
                        user.getSuccess().getAuthuser().setMobileNo(authuser.getMobileNo());
                        SharedPreferenceManager.storeUserResponseObjectInSharedPreference(user);

                        Intent intent = new Intent(EditProfileUserActivity.this, HomeActivity.class);
                        intent.putExtra("fromUpdate", true);
                        startActivity(intent);
                        finish();
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
