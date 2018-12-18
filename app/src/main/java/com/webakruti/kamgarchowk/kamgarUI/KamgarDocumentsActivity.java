package com.webakruti.kamgarchowk.kamgarUI;

import android.Manifest;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.webakruti.kamgarchowk.R;
import com.webakruti.kamgarchowk.model.KamgarSaveDocsResp;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;
import com.webakruti.kamgarchowk.retrofit.service.RestClient;
import com.webakruti.kamgarchowk.utils.NetworkUtil;
import com.webakruti.kamgarchowk.utils.SharedPreferenceManager;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class KamgarDocumentsActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageViewBack;
    private EditText editTextPanNumber;
    private EditText editTextChoosePancard;

    private RelativeLayout relativeLayoutPANUpload;

    private EditText editTextBankName;
    private EditText editTextAccountNo;
    private EditText editTextChooseBankPassbook;
    private RelativeLayout relativeLayoutPassbookUpload;

    private Button buttonDocumentSubmit;

    private static final int SELECT_FILE1 = 1;
    private static final int SELECT_FILE2 = 2;

    String volumeName = "external";

    String panImage;
    String passbookImage;

    private static final int REQUEST_IMAGE_TAKEN = 1;

    /*private File panImage;
    private File passbookImage;*/
    private String path;
    private String path1;
    Uri outPutfileUri;

    private ProgressDialog progressDialogForAPI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kamgar_documents);

        SharedPreferenceManager.setApplicationContext(KamgarDocumentsActivity.this);

        initViews();
    }

    private void initViews() {
        imageViewBack = (ImageView) findViewById(R.id.imageViewBack);

        imageViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        editTextPanNumber = (EditText) findViewById(R.id.editTextPanNumber);
        editTextChoosePancard = (EditText) findViewById(R.id.editTextChoosePancard);
        editTextBankName = (EditText) findViewById(R.id.editTextBankName);
        editTextAccountNo = (EditText) findViewById(R.id.editTextAccountNo);
        editTextChooseBankPassbook = (EditText) findViewById(R.id.editTextChooseBankPassbook);
        relativeLayoutPANUpload = (RelativeLayout) findViewById(R.id.relativeLayoutPANUpload);
        relativeLayoutPassbookUpload = (RelativeLayout) findViewById(R.id.relativeLayoutPassbookUpload);
        buttonDocumentSubmit = (Button) findViewById(R.id.buttonDocumentSubmit);

        relativeLayoutPANUpload.setOnClickListener(this);
        relativeLayoutPassbookUpload.setOnClickListener(this);
        buttonDocumentSubmit.setOnClickListener(this);

    }

    boolean flag = false;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.relativeLayoutPANUpload:
                flag = true;
                openGalleryActivity(SELECT_FILE1);
                //openGallery(SELECT_FILE1);
                //openDocument();
                break;
            case R.id.relativeLayoutPassbookUpload:
                flag = false;
                openGalleryActivity(SELECT_FILE2);
                //openGallery(SELECT_FILE2);
                break;
            case R.id.buttonDocumentSubmit:
                if (editTextPanNumber.getText().toString().length() == 10) {
                    if (editTextChoosePancard.getText().toString().length() > 0) {
                        if (editTextBankName.getText().toString().length() > 0) {
                            if (editTextAccountNo.getText().toString().length() == 11) {
                                if (editTextChooseBankPassbook.getText().toString().length() > 0) {
                                    if (NetworkUtil.hasConnectivity(KamgarDocumentsActivity.this)) {
                                        callUploadDocuments();
                                    /*Intent intent = new Intent(KamgarDocumentsActivity.this, HomeOrProfileActivity.class);
                                    startActivity(intent);*/
                                    } else {
                                        Toast.makeText(KamgarDocumentsActivity.this, R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(KamgarDocumentsActivity.this, "Please Upload Bank Passbook Image", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                Toast.makeText(KamgarDocumentsActivity.this, "Please Enter valid Bank Account Number.", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(KamgarDocumentsActivity.this, "Please Enter Bank Name", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(KamgarDocumentsActivity.this, "Please Upload PAN Card Image", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(KamgarDocumentsActivity.this, "Please Enter valid PAN Card Number", Toast.LENGTH_SHORT).show();
                }


                break;
        }
    }


    // Camera Permission
    @RequiresApi(api = Build.VERSION_CODES.M)
    public void openGalleryActivity(int req_code) {

        if (!checkPermission()) {

            if (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE)
                    && shouldShowRequestPermissionRationale(READ_EXTERNAL_STORAGE)) {
                new TedPermission(KamgarDocumentsActivity.this)
                        .setPermissionListener(permissionlistener)
                        .setRationaleConfirmText("ALLOW")
                        .setRationaleMessage("App Requires Permission")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
                        .check();
            } else {
                new TedPermission(KamgarDocumentsActivity.this)
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
        int result1 = ContextCompat.checkSelfPermission(KamgarDocumentsActivity.this, WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(KamgarDocumentsActivity.this, READ_EXTERNAL_STORAGE);

        return
                result1 == PackageManager.PERMISSION_GRANTED &&
                        result2 == PackageManager.PERMISSION_GRANTED;
    }

    PermissionListener permissionlistener = new PermissionListener() {
        @Override
        public void onPermissionGranted() {
            if (flag) {
                openGallery(SELECT_FILE1);
            } else {
                openGallery(SELECT_FILE2);
            }
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

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();

            if (requestCode == SELECT_FILE1) {
                panImage = getPath(selectedImageUri);
                path = getPath(selectedImageUri);
                editTextChoosePancard.setText(panImage);
            }
            if (requestCode == SELECT_FILE2) {
                passbookImage = getPath(selectedImageUri);
                path1 = getPath(selectedImageUri);
                editTextChooseBankPassbook.setText(passbookImage);
            }

           /* editTextChoosePancard.setText("Selected File paths : " + selectedPath1);
            editTextChooseBankPassbook.setText("Selected File paths : " + selectedPath2);*/
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = managedQuery(uri, projection, null, null, null);
        int column_index = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        return cursor.getString(column_index);
    }


    private void callUploadDocuments() {
        File panImage = null;
        File passbookImage = null;
        if (path != null && path1 != null) {
            panImage = new File(path);
            passbookImage = new File(path1);

            int compressionRatio = 2; //1 == originalImage, 2 = 50% compression, 4=25% compress
            try {
                Bitmap bitmap = BitmapFactory.decodeFile(panImage.getPath());
                bitmap.compress(Bitmap.CompressFormat.JPEG, 75, new FileOutputStream(panImage));

                Bitmap bitmap1 = BitmapFactory.decodeFile(passbookImage.getPath());
                bitmap1.compress(Bitmap.CompressFormat.JPEG, 75, new FileOutputStream(passbookImage));
            } catch (Throwable t) {
                Log.e("ERROR", "Error compressing file." + t.toString());
                t.printStackTrace();
            }
        } else {
            path = null;
            path1 = null;
        }


        progressDialogForAPI = new ProgressDialog(KamgarDocumentsActivity.this);
        progressDialogForAPI.setCancelable(false);
        progressDialogForAPI.setIndeterminate(true);
        progressDialogForAPI.setMessage("Please wait...");
        progressDialogForAPI.show();

        RequestBody PANno = RequestBody.create(MediaType.parse("multipart/form-data"), editTextPanNumber.getText().toString());
        RequestBody BankName = RequestBody.create(MediaType.parse("multipart/form-data"), editTextBankName.getText().toString());
        RequestBody BankAccntNo = RequestBody.create(MediaType.parse("multipart/form-data"), editTextAccountNo.getText().toString());

        RequestBody requestBaseFile;
        RequestBody requestBaseFile1;
        MultipartBody.Part bodyImage = null;
        MultipartBody.Part bodyImage1 = null;

        // pan_no, pan_copy_url, bank_name, bank_acc_no, bank_passbook_copy_url

        if (path != null) {
            // with image
            requestBaseFile = RequestBody.create(MediaType.parse("multipart/form-data"), panImage);
            bodyImage = MultipartBody.Part.createFormData("pan_copy_url", "image" +
                    System.currentTimeMillis(), requestBaseFile);
        } else {
            // without image
            requestBaseFile = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            bodyImage = MultipartBody.Part.createFormData("pan_copy_url", "image1" +
                    System.currentTimeMillis(), requestBaseFile);
        }
        if (path1 != null) {
            // with image
            requestBaseFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), passbookImage);
            bodyImage1 = MultipartBody.Part.createFormData("bank_passbook_copy_url", "image" +
                    System.currentTimeMillis(), requestBaseFile1);
        } else {
            // without image
            requestBaseFile1 = RequestBody.create(MediaType.parse("multipart/form-data"), "");
            bodyImage1 = MultipartBody.Part.createFormData("bank_passbook_copy_url", "image1" +
                    System.currentTimeMillis(), requestBaseFile1);
        }

        String header = "Bearer " + SharedPreferenceManager.getKamgarObject().getSuccess().getToken();

        Call<KamgarSaveDocsResp> documents = RestClient.getApiService(ApiConstants.BASE_URL).
                saveDocuments(header, PANno, bodyImage, BankName, BankAccntNo, bodyImage1);

        documents.enqueue(new Callback<KamgarSaveDocsResp>() {
            @Override
            public void onResponse(Call<KamgarSaveDocsResp> call, Response<KamgarSaveDocsResp> response) {
                if (response.isSuccessful() && response.body() != null && response.code() == 200) {
                    //
                    try {
                        KamgarSaveDocsResp saveDocsResponse = response.body();

                        if (saveDocsResponse.getSuccess() != null) {
//                            if (saveDocsResponse.getSuccess()) {

                            Log.e("Upload", "Upload Successful");
                            Toast.makeText(getApplicationContext(), saveDocsResponse.getSuccess().getMsg(), Toast.LENGTH_SHORT).show();
                            //}
                        } else {
                            Toast.makeText(KamgarDocumentsActivity.this, "Unable to reach server ", Toast.LENGTH_SHORT).show();
                        }
                    } catch (Exception e) {
                        Toast.makeText(KamgarDocumentsActivity.this, "Unable to reach server ", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(KamgarDocumentsActivity.this, "Unable to reach server ", Toast.LENGTH_SHORT).show();
                }


                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }

            }


            @Override
            public void onFailure(Call<KamgarSaveDocsResp> call, Throwable t) {

                Toast.makeText(KamgarDocumentsActivity.this, "Time out error. ", Toast.LENGTH_SHORT).show();
                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }
                if (t != null) {

                    if (t.getMessage() != null)
                        Log.e("error", t.getMessage());

                }
            }
        });


    }


}

// public void openDocument() {

// Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
//   intent.addCategory(Intent.CATEGORY_OPENABLE);

// intent.setType("**/*//*");

//  startActivityForResult(intent, READ_REQUEST_CODE);
// }