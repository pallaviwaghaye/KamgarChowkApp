package com.webakruti.kamgarchowk.userUI;

import android.Manifest;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
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

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.squareup.picasso.Picasso;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.kamgarUI.KamgarEditProfileActivity;
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

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
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

    private ImageView imageViewUserImage;
    private Button buttonUploadImage;

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

    String kamgarImage;

    private static final int REQUEST_IMAGE_TAKEN = 1;

    private String path;
    Uri outPutfileUri;

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
        if(user.getPincode() == 0)
        {
            editTextPincode.setText("");
        }else {
            editTextPincode.setText(user.getPincode()+"");
        }
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

        imageViewUserImage = (ImageView)findViewById(R.id.imageViewUserImage);
        buttonUploadImage = (Button)findViewById(R.id.buttonUploadImage);
        buttonUploadImage.setOnClickListener(this);

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
        if (countryObj == null) {
            //&& countryObj.getName() == null

            setInitialSpinnersForStateAndCity(stateList, cityList);
        } else {
            // if authuser country obj is not null, means others like state and city also won't be null
            // so, set country spinner
            int pos = finalList.indexOf(countryObj);
            spinnerCountry.setSelection(pos, false);
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

    boolean flag = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
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
                                                    if (((Country) spinnerCountry.getSelectedItem()).getId() != -1) {
                                                        if (((State) spinnerState.getSelectedItem()).getId() != -1) {
                                                            if (((City) spinnerCity.getSelectedItem()).getId() != -1) {

                                                                    if (NetworkUtil.hasConnectivity(EditProfileUserActivity.this)) {
                                                                        CallUpdateUserAPI();
                                                                    }else{
                                                                        Toast.makeText(EditProfileUserActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
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
            case R.id.buttonUploadImage:
                flag = true;
                openGalleryActivity(REQUEST_IMAGE_TAKEN);
                //openGallery(SELECT_FILE1);
                //openDocument();
                break;

        }
    }

    // Camera Permission
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openGalleryActivity(int req_code) {

        if (!checkPermission()) {

            if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)
                    && shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
                new TedPermission(EditProfileUserActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setRationaleConfirmText("ALLOW")
                        .setRationaleMessage("App Requires Permission")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
                        .check();
            } else {
                new TedPermission(EditProfileUserActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setDeniedCloseButtonText("Cancel")
                        .setDeniedMessage("If you reject permission,you can not use this service \n Please turn on permissions from Settings")
                        .setGotoSettingButtonText("Settings")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
                        .check();
            }
        } else {
            openGallery(req_code);
        }
    }


    private boolean checkPermission() {
        int result1 = ContextCompat.checkSelfPermission(EditProfileUserActivity.this, WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(EditProfileUserActivity.this, READ_EXTERNAL_STORAGE);

        return
                result1 == PackageManager.PERMISSION_GRANTED &&
                        result2 == PackageManager.PERMISSION_GRANTED;
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            if (flag) {
                openGallery(REQUEST_IMAGE_TAKEN);
            }/* else {
                openGallery(REQUEST_IMAGE_TAKEN);
            }*/
        }

        @Override
        public void onPermissionDenied(ArrayList<String> deniedPermissions) {

        }

    };


    public void openGallery(int req_code) {
        //Intent i = new Intent(Intent.ACTION_GET_CONTENT, android.provider.MediaStore.Files.getContentUri(volumeName));
        // android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        //i.setType("*/*");
        //startActivityForResult(i, req_code);
        Intent i = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(i, req_code);
    }


 /*   protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_TAKEN && resultCode == RESULT_OK) {
            try {

                if (path != null) {
                    linearLayoutCamera.setVisibility(View.GONE);
                    imageViewKamgarImage.setVisibility(View.VISIBLE);
                    Bitmap bitmap = decodeSampledBitmapFromFile(path, Utils.DpToPixel(KamgarEditProfileActivity.this, 270), Utils.DpToPixel(KamgarEditProfileActivity.this, 150));

                    imageViewKamgarImage.setImageBitmap(bitmap);

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }*/


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();

            if (requestCode == REQUEST_IMAGE_TAKEN) {
                kamgarImage = getPath(selectedImageUri);
                path = getPath(selectedImageUri);

            }
           /* else
            {
                Picasso.with(KamgarEditProfileActivity.this)
                        .load(R.drawable.kamgar)
                        .into(imageViewKamgarImage);
            }*/

            File kamgarImage = null;
            if (path != null) {
                kamgarImage = new File(path);

                int compressionRatio = 2; //1 == originalImage, 2 = 50% compression, 4=25% compress
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(kamgarImage.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 75, new FileOutputStream(kamgarImage));

                    imageViewUserImage.setImageBitmap(bitmap);

                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
            } else {
                path = null;
                Picasso.with(EditProfileUserActivity.this)
                        .load(R.drawable.kamgar)
                        .into(imageViewUserImage);
            }


           /* editTextChoosePancard.setText("Selected File paths : " + selectedPath1);
            editTextChooseBankPassbook.setText("Selected File paths : " + selectedPath2);*/
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getContentResolver().query(uri, projection, null, null, null);
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
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
        Call<UpdateProfileResponse> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).updateprofile(headers, userid, editTextFname.getText().toString(), editTextMname.getText().toString(), editTextLname.getText().toString(), editTextDOB.getText().toString(), selectedGender.getId(), editTextMobile.getText().toString(), editTextEmail.getText().toString(), editTextAddress.getText().toString(), ((Country) spinnerCountry.getSelectedItem()).getId(), ((State) spinnerState.getSelectedItem()).getId(),((City) spinnerCity.getSelectedItem()).getId(), editTextPincode.getText().toString());

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
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    // Response code is 401
                    Toast.makeText(EditProfileUserActivity.this, "Server error!!Email or MobileNo already in use.", Toast.LENGTH_LONG).show();
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
