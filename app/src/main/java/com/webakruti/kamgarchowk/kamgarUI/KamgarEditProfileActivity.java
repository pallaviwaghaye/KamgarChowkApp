package com.webakruti.kamgarchowk.kamgarUI;

import android.Manifest;
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
import com.webakruti.kamgarchowk.model.KamgarGetProfile;
import com.webakruti.kamgarchowk.model.KamgarLoginResponse;
import com.webakruti.kamgarchowk.model.KamgarUpdateResp;
import com.webakruti.kamgarchowk.model.UserProfileResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.userUI.EditProfileUserActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;
import com.webakruti.kamgarchowk.utils.Utils;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class KamgarEditProfileActivity extends AppCompatActivity implements View.OnClickListener {
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
    private ImageView imageViewKamgarImage;
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

    KamgarGetProfile.City selectedCity;
    KamgarGetProfile.State selectedState;
    KamgarGetProfile.Country selectedCountry;
    KamgarGetProfile.Gender selectedGender;


    private KamgarGetProfile kamgarData;

    private Button buttonSave;

    String kamgarImage = null;

    private static final int REQUEST_IMAGE_TAKEN = 1;
    Uri outPutfileUri;

    private ProgressDialog progressDialogForAPI;
    private File kamgarImageFile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_edit_profile);


        SharedPreferenceManager.setApplicationContext(KamgarEditProfileActivity.this);

        kamgarData = (KamgarGetProfile) getIntent().getSerializableExtra("SpinnerData");
        initViews();

        setData(kamgarData.getSuccess().getAuthkamgar());
    }

    private void setData(KamgarGetProfile.Authkamgar kamgar) {
        editTextFname.setText(kamgar.getFirstName());
        editTextLname.setText(kamgar.getLastName());
        editTextAddress.setText(kamgar.getAddress());
        editTextEmail.setText(kamgar.getEmail());
        editTextMname.setText(kamgar.getMiddleName());

        if (kamgar.getContImgUrl() == null) {
            Picasso.with(KamgarEditProfileActivity.this)
                    .load(R.drawable.kamgar)
                    .into(imageViewKamgarImage);
        } else {
            Picasso.with(KamgarEditProfileActivity.this)
                    .load(kamgar.getContImgUrl())
                    .into(imageViewKamgarImage);
        }
        if (kamgar.getPincode() == 0) {
            editTextPincode.setText("");
        } else {
            editTextPincode.setText(kamgar.getPincode() + "");
        }
        editTextMobile.setText(kamgar.getMobileNo());
        editTextDOB.setText(kamgar.getDob());

        initInitialSpinnerData(kamgar);
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

        imageViewKamgarImage = (ImageView) findViewById(R.id.imageViewKamgarImage);
        buttonUploadImage = (Button) findViewById(R.id.buttonUploadImage);
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

    private void initInitialSpinnerData(KamgarGetProfile.Authkamgar kamgar) {
        setInitialSpinnerAdapter(kamgar);
    }

    private void setInitialSpinnerAdapter(KamgarGetProfile.Authkamgar kamgar) {

        final List<KamgarGetProfile.Country> countries = kamgarData.getSuccess().getCountry(); // Data -- Country

        setCountrySpinner(countries, kamgar.getCountry(), kamgar);
        setGenderSpinner(kamgarData.getSuccess().getGender(), kamgar.getGender());
    }

    private void setCountrySpinner(List<KamgarGetProfile.Country> countryList, KamgarGetProfile.Country countryObj, KamgarGetProfile.Authkamgar kamgar) {

        List<KamgarGetProfile.Country> finalList = new ArrayList<>();

        selectedCountry = new KamgarGetProfile.Country();
        selectedCountry.setName(defaultCountry);
        selectedCountry.setId(-1);

        finalList.add(selectedCountry);
        finalList.addAll(countryList);
        ArrayAdapter<KamgarGetProfile.Country> adapterStation = new ArrayAdapter<KamgarGetProfile.Country>(KamgarEditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerCountry.setAdapter(adapterStation);
        final List<KamgarGetProfile.State> stateList = new ArrayList<>();
        final List<KamgarGetProfile.City> cityList = new ArrayList<>();
        // authuser's country, state, city is null
        selectedCity = new KamgarGetProfile.City();
        selectedCity.setName(defaultCity);
        selectedCity.setId(-1);

        selectedState = new KamgarGetProfile.State();
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
            List<KamgarGetProfile.State> states = finalList.get(pos).getStates();
            // set adapter with these states
            setSelectedStateSpinner(states, kamgar.getState(), kamgar);


        }
        spinnerCountry.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                selectedCountry = (KamgarGetProfile.Country) adapterView.getItemAtPosition(position);
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
                selectedState = (KamgarGetProfile.State) adapterView.getItemAtPosition(position);
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
                selectedCity = (KamgarGetProfile.City) adapterView.getItemAtPosition(position);

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setInitialSpinnersForStateAndCity(List<KamgarGetProfile.State> stateList, List<KamgarGetProfile.City> cityList) {
        ArrayAdapter<KamgarGetProfile.State> adapterState = new ArrayAdapter<KamgarGetProfile.State>(KamgarEditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, stateList);
        spinnerState.setAdapter(adapterState);

        ArrayAdapter<KamgarGetProfile.City> adapterCity = new ArrayAdapter<KamgarGetProfile.City>(KamgarEditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, cityList);
        spinnerCity.setAdapter(adapterCity);
    }

    private void setInitialSpinnersForCity(List<KamgarGetProfile.City> cityList) {

        ArrayAdapter<KamgarGetProfile.City> adapterCity = new ArrayAdapter<KamgarGetProfile.City>(KamgarEditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, cityList);
        spinnerCity.setAdapter(adapterCity);
    }


    private void setStateSpinner(List<KamgarGetProfile.State> stateList) {

        List<KamgarGetProfile.State> finalList = new ArrayList<>();

        selectedState = new KamgarGetProfile.State();
        selectedState.setName(defaultState);
        selectedState.setId(-1);

        finalList.add(selectedState);
        finalList.addAll(stateList);
        ArrayAdapter<KamgarGetProfile.State> adapterStation = new ArrayAdapter<KamgarGetProfile.State>(KamgarEditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerState.setAdapter(adapterStation);

    }


    private void setSelectedStateSpinner(List<KamgarGetProfile.State> stateList, KamgarGetProfile.State state, KamgarGetProfile.Authkamgar kamgar) {

        List<KamgarGetProfile.State> finalList = new ArrayList<>();

        selectedState = new KamgarGetProfile.State();
        selectedState.setName(defaultState);
        selectedState.setId(-1);

        finalList.add(selectedState);
        finalList.addAll(stateList);
        ArrayAdapter<KamgarGetProfile.State> adapterStation = new ArrayAdapter<KamgarGetProfile.State>(KamgarEditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerState.setAdapter(adapterStation);

        int pos = finalList.indexOf(state);
        spinnerState.setSelection(pos, false);

        List<KamgarGetProfile.City> cityList = finalList.get(pos).getCities();
        setSelectedCitySpinner(cityList, kamgar.getCity());

    }

    private void setCitySpinner(List<KamgarGetProfile.City> cityList) {

        List<KamgarGetProfile.City> finalList = new ArrayList<>();

        selectedCity = new KamgarGetProfile.City();
        selectedCity.setName(defaultCity);
        selectedCity.setId(-1);

        finalList.add(selectedCity);
        finalList.addAll(cityList);
        ArrayAdapter<KamgarGetProfile.City> adapterStation = new ArrayAdapter<KamgarGetProfile.City>(KamgarEditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerCity.setAdapter(adapterStation);


    }

    private void setSelectedCitySpinner(List<KamgarGetProfile.City> cityList, KamgarGetProfile.City city) {

        List<KamgarGetProfile.City> finalList = new ArrayList<>();

        selectedCity = new KamgarGetProfile.City();
        selectedCity.setName(defaultCity);
        selectedCity.setId(-1);

        finalList.add(selectedCity);
        finalList.addAll(cityList);
        ArrayAdapter<KamgarGetProfile.City> adapterStation = new ArrayAdapter<KamgarGetProfile.City>(KamgarEditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
        spinnerCity.setAdapter(adapterStation);

        int pos = finalList.indexOf(city);
        spinnerCity.setSelection(pos, false);

    }

    private void setGenderSpinner(List<KamgarGetProfile.Gender> genderList, KamgarGetProfile.Gender gender) {

        List<KamgarGetProfile.Gender> finalList = new ArrayList<>();

        selectedGender = new KamgarGetProfile.Gender();
        selectedGender.setValue(defaultGender);
        selectedGender.setId(-1);

        finalList.add(selectedGender);
        finalList.addAll(genderList);
        ArrayAdapter<KamgarGetProfile.Gender> adapterStation = new ArrayAdapter<KamgarGetProfile.Gender>(KamgarEditProfileActivity.this, android.R.layout.simple_spinner_dropdown_item, finalList);
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
                selectedGender = (KamgarGetProfile.Gender) adapterView.getItemAtPosition(position);
                if (selectedGender.getValue().equalsIgnoreCase(defaultGender)) {
                    selectedGender.setId(-1);
                    selectedGender.setName("");
                    selectedGender.setValue(defaultGender);
                }
                Log.e("", "");
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
                            /*if (editTextEmail.getText().toString().length() > 0) {
                                if (isValidEmailAddress(editTextEmail.getText().toString().trim())) {*/
                            if (editTextMobile.getText().toString().length() > 0) {
                                if (editTextMobile.getText().toString().length() == 10) {
                                    if (selectedGender.getId() != -1) {
                                        if (editTextAddress.getText().toString().length() > 0) {
                                            if (((KamgarGetProfile.Country) spinnerCountry.getSelectedItem()).getId() != -1) {
                                                if (((KamgarGetProfile.State) spinnerState.getSelectedItem()).getId() != -1) {
                                                    if (((KamgarGetProfile.City) spinnerCity.getSelectedItem()).getId() != -1) {
                                                        if (editTextPincode.getText().toString().length() == 0 ||
                                                                editTextEmail.getText().toString().length() == 0) {
                                                            // either is not entered

                                                            if (editTextPincode.getText().toString().length() > 0) {
                                                                if (editTextPincode.getText().toString().length() < 6) {
                                                                    Toast.makeText(KamgarEditProfileActivity.this, "Enter valid pincode", Toast.LENGTH_SHORT).show();
                                                                } else if (editTextPincode.getText().toString().length() == 6) {
                                                                    if (NetworkUtil.hasConnectivity(KamgarEditProfileActivity.this)) {
                                                                        CallUpdatekamgarAPI();
                                                                    } else {
                                                                        Toast.makeText(KamgarEditProfileActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                                                    }
                                                                }
                                                            } else {
                                                                if (NetworkUtil.hasConnectivity(KamgarEditProfileActivity.this)) {
                                                                    CallUpdatekamgarAPI();
                                                                } else {
                                                                    Toast.makeText(KamgarEditProfileActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                                                }
                                                            }

                                                        } else {
                                                            if (isValidEmailAddress(editTextEmail.getText().toString().trim()) || editTextPincode.getText().toString().length() == 6) {
                                                                // valid email and pincode
                                                                if (NetworkUtil.hasConnectivity(KamgarEditProfileActivity.this)) {
                                                                    CallUpdatekamgarAPI();
                                                                } else {
                                                                    Toast.makeText(KamgarEditProfileActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                                                }
                                                            } else {
                                                                if (!isValidEmailAddress(editTextEmail.getText().toString().trim())) {
                                                                    Toast.makeText(KamgarEditProfileActivity.this, "Email Id must be valid", Toast.LENGTH_SHORT).show();
                                                                } else if (editTextPincode.getText().toString().length() < 6) {
                                                                    Toast.makeText(KamgarEditProfileActivity.this, "Enter valid pincode", Toast.LENGTH_SHORT).show();
                                                                }


                                                            }
                                                        }


                                                    } else {
                                                        Toast.makeText(KamgarEditProfileActivity.this, "Select city", Toast.LENGTH_SHORT).show();
                                                    }
                                                } else {
                                                    Toast.makeText(KamgarEditProfileActivity.this, "Select state", Toast.LENGTH_SHORT).show();
                                                }
                                            } else {
                                                Toast.makeText(KamgarEditProfileActivity.this, "Select country", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(KamgarEditProfileActivity.this, "Address can't be empty", Toast.LENGTH_SHORT).show();
                                        }

                                    } else {
                                        Toast.makeText(KamgarEditProfileActivity.this, "Select gender", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(KamgarEditProfileActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(KamgarEditProfileActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                            }
                            /*    } else {
                                    Toast.makeText(KamgarEditProfileActivity.this, "Email Id must be valid", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(KamgarEditProfileActivity.this, "Email Id Can't be empty", Toast.LENGTH_SHORT).show();
                            }*/
                        } else {
                            Toast.makeText(KamgarEditProfileActivity.this, "Date of birth Can't be empty", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(KamgarEditProfileActivity.this, "Last Name Can't be empty", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(KamgarEditProfileActivity.this, "First Name Can't be empty", Toast.LENGTH_SHORT).show();
                }


                break;

            case R.id.editTextDOB:
                DatePickerDialog dlg = new DatePickerDialog(KamgarEditProfileActivity.this, date, myCalendar
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
                new TedPermission(KamgarEditProfileActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setRationaleConfirmText("ALLOW")
                        .setRationaleMessage("App Requires Permission")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
                        .check();
            } else {
                new TedPermission(KamgarEditProfileActivity.this)
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
        int result1 = ContextCompat.checkSelfPermission(KamgarEditProfileActivity.this, WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(KamgarEditProfileActivity.this, READ_EXTERNAL_STORAGE);

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

                /*Bitmap bitmap = decodeSampledBitmapFromFile(path, Utils.DpToPixel(KamgarEditProfileActivity.this, 270), Utils.DpToPixel(KamgarEditProfileActivity.this, 150));

                imageViewKamgarImage.setImageBitmap(bitmap);*/
                /*Picasso.with(KamgarEditProfileActivity.this)
                    .load(kamgarImage)
                    .into(imageViewKamgarImage);

                imageViewKamgarImage.setImageBitmap(bitmap);*/

            }/*else
            {
                Picasso.with(KamgarEditProfileActivity.this)
                        .load(R.drawable.kamgar)
                        .into(imageViewKamgarImage);
            }*/

            kamgarImageFile = null;
            if (kamgarImage != null) {
                kamgarImageFile = new File(kamgarImage);

                int compressionRatio = 2; //1 == originalImage, 2 = 50% compression, 4=25% compress
                try {
                    Bitmap bitmap = BitmapFactory.decodeFile(kamgarImageFile.getPath());
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 75, new FileOutputStream(kamgarImageFile));

                    imageViewKamgarImage.setImageBitmap(bitmap);

                } catch (Throwable t) {
                    Log.e("ERROR", "Error compressing file." + t.toString());
                    t.printStackTrace();
                }
            } else {
                kamgarImage = null;
                Picasso.with(KamgarEditProfileActivity.this)
                        .load(R.drawable.kamgar)
                        .into(imageViewKamgarImage);
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


    private void CallUpdatekamgarAPI() {


        progressDialogForAPI = new ProgressDialog(this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        //Integer userid = SharedPreferenceManager.getKamgarObject().getSuccess().getAuthkamgar().getId();

        //RequestBody id = RequestBody.create(MediaType.parse("multipart/form-data"), userid+"");
        RequestBody FName = RequestBody.create(MediaType.parse("multipart/form-data"), editTextFname.getText().toString());
        RequestBody middleName = RequestBody.create(MediaType.parse("multipart/form-data"), editTextMname.getText().toString());
        RequestBody LName = RequestBody.create(MediaType.parse("multipart/form-data"), editTextLname.getText().toString());
        RequestBody datebirth = RequestBody.create(MediaType.parse("multipart/form-data"), editTextDOB.getText().toString());
        RequestBody gender = RequestBody.create(MediaType.parse("multipart/form-data"), selectedGender.getId() + "");
        RequestBody mobNo = RequestBody.create(MediaType.parse("multipart/form-data"), editTextMobile.getText().toString());
        RequestBody emailid = RequestBody.create(MediaType.parse("multipart/form-data"), editTextEmail.getText().toString());
        RequestBody address = RequestBody.create(MediaType.parse("multipart/form-data"), editTextAddress.getText().toString());
        RequestBody country = RequestBody.create(MediaType.parse("multipart/form-data"), ((KamgarGetProfile.Country) spinnerCountry.getSelectedItem()).getId() + "");
        RequestBody state = RequestBody.create(MediaType.parse("multipart/form-data"), ((KamgarGetProfile.State) spinnerState.getSelectedItem()).getId() + "");
        RequestBody city = RequestBody.create(MediaType.parse("multipart/form-data"), ((KamgarGetProfile.City) spinnerCity.getSelectedItem()).getId() + "");
        RequestBody pincode = RequestBody.create(MediaType.parse("multipart/form-data"), editTextPincode.getText().toString());


        RequestBody requestBaseFile;
        MultipartBody.Part bodyImage = null;

        // pan_no, pan_copy_url, bank_name, bank_acc_no, bank_passbook_copy_url

        if (kamgarImage != null) {
            // with image
            requestBaseFile = RequestBody.create(MediaType.parse("multipart/form-data"), kamgarImageFile);
            bodyImage = MultipartBody.Part.createFormData("cont_img_url", "image" +
                    System.currentTimeMillis(), requestBaseFile);
        } else {
            // without image
            requestBaseFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            bodyImage = MultipartBody.Part.createFormData("cont_img_url", "", requestBaseFile);
        }


        //String token = SharedPreferenceManager.getKamgarObject().getSuccess().getToken();


        //String API = "http://beta.kamgarchowk.com/api/";
        String header = "Bearer " + SharedPreferenceManager.getKamgarObject().getSuccess().getToken();
        Call<KamgarUpdateResp> requestCallback = RestClient.getApiService(ApiConstants.BASE_URL).updatekamgarprofile(header, bodyImage, FName, middleName, LName, datebirth, gender, mobNo, emailid, address, country, state, city, pincode);
        //.updatekamgarprofile(header, userid, editTextFname.getText().toString(), editTextMname.getText().toString(), editTextLname.getText().toString(), editTextDOB.getText().toString(), selectedGender.getId(), editTextMobile.getText().toString(), editTextEmail.getText().toString(), editTextAddress.getText().toString(), ((KamgarGetProfile.Country) spinnerCountry.getSelectedItem()).getId(), ((KamgarGetProfile.State) spinnerState.getSelectedItem()).getId(),((KamgarGetProfile.City) spinnerCity.getSelectedItem()).getId(), editTextPincode.getText().toString());

        requestCallback.enqueue(new Callback<KamgarUpdateResp>() {
            @Override
            public void onResponse(Call<KamgarUpdateResp> call, Response<KamgarUpdateResp> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {

                    KamgarUpdateResp details = response.body();
                    //  Toast.makeText(getActivity(),"Data : " + details ,Toast.LENGTH_LONG).show();
                    if (details.getSuccess() != null) {

                        Toast.makeText(KamgarEditProfileActivity.this, details.getSuccess().getMsg(), Toast.LENGTH_LONG).show();
                        //user.setId(user.getUserId());

                        KamgarUpdateResp.Authkamgar authkamgar = details.getSuccess().getAuthkamgar();

                        KamgarLoginResponse kamgar = SharedPreferenceManager.getKamgarObject();
                        kamgar.getSuccess().getAuthkamgar().setFirstName(authkamgar.getFirstName());
                        kamgar.getSuccess().getAuthkamgar().setEmail(authkamgar.getEmail());
                        kamgar.getSuccess().getAuthkamgar().setLastName(authkamgar.getLastName());
                        kamgar.getSuccess().getAuthkamgar().setMobileNo(authkamgar.getMobileNo());
                        kamgar.getSuccess().getAuthkamgar().setContImgUrl(authkamgar.getContImgUrl());
                        SharedPreferenceManager.storeKamgarObject(kamgar);

                        Intent intent = new Intent(KamgarEditProfileActivity.this, HomeOrProfileActivity.class);
                        intent.putExtra("fromUpdate", true);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        finish();
                    }

                } else {
                    // Response code is 401
                    Toast.makeText(KamgarEditProfileActivity.this, "Email Id must be valid", Toast.LENGTH_LONG).show();
                    Toast.makeText(KamgarEditProfileActivity.this, "Unable to reach server", Toast.LENGTH_SHORT).show();
                }

                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
            }

            @Override
            public void onFailure(Call<KamgarUpdateResp> call, Throwable t) {

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
