package com.webakruti.kamgarchowk.kamgarUI;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.UserProfileResponse;
import com.webakruti.kamgarchowk.userUI.EditProfileUserActivity;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class KamgarEditProfileActivity extends AppCompatActivity implements View.OnClickListener{
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

    /*UserProfileResponse.City selectedCity;
    UserProfileResponse.State selectedState;
    UserProfileResponse.Country selectedCountry;
    UserProfileResponse.Gender selectedGender;


    private UserProfileResponse userData;*/

    private Button buttonSave;

    private ProgressDialog progressDialogForAPI;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_edit_profile);


        SharedPreferenceManager.setApplicationContext(KamgarEditProfileActivity.this);

       // userData = (UserProfileResponse) getIntent().getSerializableExtra("SpinnerData");
        initViews();

       // setData(userData.getSuccess().getAuthuser().get(0));
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
//                                            if (selectedGender.getId() != -1) {
                                                if (editTextAddress.getText().toString().length() > 0) {
                                                    if (((UserProfileResponse.Country) spinnerCountry.getSelectedItem()).getId() != -1) {
                                                        if (((UserProfileResponse.State) spinnerState.getSelectedItem()).getId() != -1) {
                                                            if (((UserProfileResponse.City) spinnerCity.getSelectedItem()).getId() != -1) {

                                                                if (NetworkUtil.hasConnectivity(KamgarEditProfileActivity.this)) {
                                                                    //CallUpdateUserAPI();
                                                                }else{
                                                                    Toast.makeText(KamgarEditProfileActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
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

                                            /*} else {
                                                Toast.makeText(KamgarEditProfileActivity.this, "Select gender", Toast.LENGTH_SHORT).show();
                                            }*/
                                        } else {
                                            Toast.makeText(KamgarEditProfileActivity.this, "Mobile number must be valid", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(KamgarEditProfileActivity.this, "Mobile number Can't be empty", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(KamgarEditProfileActivity.this, "Email Id must be valid", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(KamgarEditProfileActivity.this, "Email Id Can't be empty", Toast.LENGTH_SHORT).show();
                            }
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

        }
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
