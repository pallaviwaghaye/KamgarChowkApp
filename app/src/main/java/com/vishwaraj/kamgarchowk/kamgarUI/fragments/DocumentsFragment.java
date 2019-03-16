package com.vishwaraj.kamgarchowk.kamgarUI.fragments;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;
import com.vishwaraj.kamgarchowk.R;
import com.vishwaraj.kamgarchowk.kamgarUI.HomeOrProfileActivity;
import com.vishwaraj.kamgarchowk.model.KamgarSaveDocsResp;
import com.vishwaraj.kamgarchowk.retrofit.ApiConstants;
import com.vishwaraj.kamgarchowk.retrofit.service.RestClient;
import com.vishwaraj.kamgarchowk.utils.NetworkUtil;
import com.vishwaraj.kamgarchowk.utils.SharedPreferenceManager;

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
import static android.app.Activity.RESULT_OK;


public class DocumentsFragment extends Fragment implements View.OnClickListener{

    private View rootView;
    private EditText editTextPanNumber;
    private EditText editTextChoosePancard;

    private RelativeLayout relativeLayoutPANUpload;

    private EditText editTextBankName;
    private EditText editTextAccountNo;
    private EditText editTextChooseBankPassbook;
    private RelativeLayout relativeLayoutPassbookUpload;

    private Button buttonDocumentSubmit;
    private ProgressDialog progressDialogForAPI;

    private static final int SELECT_FILE1 = 1;
    private static final int SELECT_FILE2 = 2;

    String volumeName = "external";

    String panImage;
    String passbookImage;

    private static final int REQUEST_IMAGE_TAKEN = 1;

    private String path;
    private String path1;
    Uri outPutfileUri;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_documents, container, false);
        // Inflate the layout for this fragment

        SharedPreferenceManager.setApplicationContext(getActivity());

        initViews();

        return rootView;
    }

    private void initViews() {

        editTextPanNumber = (EditText) rootView.findViewById(R.id.editTextPanNumber);
        editTextChoosePancard = (EditText) rootView.findViewById(R.id.editTextChoosePancard);
        editTextBankName = (EditText) rootView.findViewById(R.id.editTextBankName);
        editTextAccountNo = (EditText) rootView.findViewById(R.id.editTextAccountNo);
        editTextChooseBankPassbook = (EditText) rootView.findViewById(R.id.editTextChooseBankPassbook);
        relativeLayoutPANUpload = (RelativeLayout) rootView.findViewById(R.id.relativeLayoutPANUpload);
        relativeLayoutPassbookUpload = (RelativeLayout) rootView.findViewById(R.id.relativeLayoutPassbookUpload);
        buttonDocumentSubmit = (Button)rootView.findViewById(R.id.buttonDocumentSubmit);

        relativeLayoutPANUpload.setOnClickListener(this);
        relativeLayoutPassbookUpload.setOnClickListener(this);
        buttonDocumentSubmit.setOnClickListener(this);
        /*buttonDocumentSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), HomeOrProfileActivity.class);
                startActivity(intent);

            }
        });*/


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
                            if (editTextAccountNo.getText().toString().length() > 0) {
                                if (editTextChooseBankPassbook.getText().toString().length() > 0) {
                                    if (NetworkUtil.hasConnectivity(getActivity())) {
                                        callUploadDocuments();
                                    /*Intent intent = new Intent(KamgarDocumentsActivity.this, HomeOrProfileActivity.class);
                                    startActivity(intent);*/
                                    } else {
                                      //  Toast.makeText(getActivity(), R.string.no_internet_message, Toast.LENGTH_SHORT).show();
                                        new AlertDialog.Builder(getActivity())
                                                .setMessage(R.string.no_internet_message)
                                                .setPositiveButton("OK", null)
                                                .show();
                                    }
                                } else {
                                  //  Toast.makeText(getActivity(), "Please Upload Bank Passbook Image", Toast.LENGTH_SHORT).show();
                                    new AlertDialog.Builder(getActivity())
                                            .setMessage("Please Upload Bank Passbook Image")
                                            .setPositiveButton("OK", null)
                                            .show();
                                }
                            } else {
                               // Toast.makeText(getActivity(), "Please Enter valid Bank Account Number.", Toast.LENGTH_SHORT).show();
                                new AlertDialog.Builder(getActivity())
                                        .setMessage("Please Enter valid Bank Account Number.")
                                        .setPositiveButton("OK", null)
                                        .show();
                            }
                        } else {
                        //    Toast.makeText(getActivity(), "Please Enter Bank Name", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(getActivity())
                                    .setMessage("Please Enter Bank Name")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }

                    } else {
                     //   Toast.makeText(getActivity(), "Please Upload PAN Card Image", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(getActivity())
                                .setMessage("Please Upload PAN Card Image")
                                .setPositiveButton("OK", null)
                                .show();
                    }
                } else {
                 //   Toast.makeText(getActivity(), "Please Enter valid PAN Card Number", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(getActivity())
                            .setMessage("Please Enter valid PAN Card Number")
                            .setPositiveButton("OK", null)
                            .show();
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
                new TedPermission(getActivity())
                        .setPermissionListener(permissionlistener)
                        .setRationaleConfirmText("ALLOW")
                        .setRationaleMessage("App Requires Permission")
                        .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, READ_EXTERNAL_STORAGE)
                        .check();
            } else {
                new TedPermission(getActivity())
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
        int result1 = ContextCompat.checkSelfPermission(getActivity(), WRITE_EXTERNAL_STORAGE);
        int result2 = ContextCompat.checkSelfPermission(getActivity(), READ_EXTERNAL_STORAGE);

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
                //editTextChoosePancard.setText(panImage);
                String filename = path.substring(path.lastIndexOf("/")+1);
                Log.e("filename : ",filename);
                editTextChoosePancard.setText(filename);
            }
            if (requestCode == SELECT_FILE2) {
                passbookImage = getPath(selectedImageUri);
                path1 = getPath(selectedImageUri);
                //editTextChooseBankPassbook.setText(passbookImage);
                String filename1 = path1.substring(path1.lastIndexOf("/")+1);
                Log.e("filename : ",filename1);
                editTextChooseBankPassbook.setText(filename1);
            }

           /* editTextChoosePancard.setText("Selected File paths : " + selectedPath1);
            editTextChooseBankPassbook.setText("Selected File paths : " + selectedPath2);*/
        }
    }

    public String getPath(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = getActivity().getContentResolver().query(uri, projection, null, null, null);
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


        progressDialogForAPI = new ProgressDialog(getActivity());
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
                            /*Toast.makeText(getActivity(), saveDocsResponse.getSuccess().getMsg(), Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getActivity(),HomeOrProfileActivity.class);
                            startActivity(intent);*/
                            //finish();
                            //}

                            new AlertDialog.Builder(getActivity())
                                    .setMessage(saveDocsResponse.getSuccess().getMsg())
                                    .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                                        public void onClick(DialogInterface dialog, int whichButton) {
                                            Intent intent = new Intent(getActivity(),HomeOrProfileActivity.class);
                                            startActivity(intent);
                                        }
                                    })
                                    .show();
                        } else {
                           // Toast.makeText(getActivity(), "Unable to reach server ", Toast.LENGTH_SHORT).show();
                            new AlertDialog.Builder(getActivity())
                                    .setMessage("Unable to reach server ")
                                    .setPositiveButton("OK", null)
                                    .show();
                        }
                    } catch (Exception e) {
                       // Toast.makeText(getActivity(), "Unable to reach server ", Toast.LENGTH_SHORT).show();
                        new AlertDialog.Builder(getActivity())
                                .setMessage("Unable to reach server ")
                                .setPositiveButton("OK", null)
                                .show();
                    }

                } else {
                  //  Toast.makeText(getActivity(), "Unable to reach server ", Toast.LENGTH_SHORT).show();
                    new AlertDialog.Builder(getActivity())
                            .setMessage("Unable to reach server ")
                            .setPositiveButton("OK", null)
                            .show();
                }


                if (progressDialogForAPI != null) {
                    progressDialogForAPI.cancel();
                }

            }


            @Override
            public void onFailure(Call<KamgarSaveDocsResp> call, Throwable t) {

               // Toast.makeText(getActivity(), "Time out error. ", Toast.LENGTH_SHORT).show();
                new AlertDialog.Builder(getActivity())
                        .setMessage("Time out error. ")
                        .setPositiveButton("OK", null)
                        .show();
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
