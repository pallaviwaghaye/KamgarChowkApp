package com.webakruti.kamgarchowk.retrofit.service;


import com.webakruti.kamgarchowk.model.UserLogin;
import com.webakruti.kamgarchowk.model.UserRegistration;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    // --------------------User APIS-------------------------
    // Registration API
    //192.168.29.107/kamgar-chowk/api/user-registration?first_name=Pallavi&last_name=Waghaye&mobile_no=7975972248
    @POST(ApiConstants.REG_API)
    Call<UserRegistration> registration(@Query("first_name") String firstName,
                                        @Query("last_name") String lastName,
                                        @Query("mobile_no") String mobileNo);

    // Login API
    //192.168.29.107/kamgar-chowk/api/user-login?mobile_no=7975972248&password=rx00k31a
    @POST(ApiConstants.LOGIN_API)
    Call<UserLogin> login(@Query("mobile_no") String mobileNo,
                          @Query("password") String password);


    /*// OTP API
    //http://nirmalrail.webakruti.in/api/login?mobile=9561665846&password=9561665846&otp=123456
    @POST(ApiConstants.OTP_VERIFICATION)
    Call<OTPResponse> otpVerification(@Query("mobile") String mobileNo);


    // GET RAILWAY CATEGORY API
    @POST(ApiConstants.GET_RAILWAY_CATEGORY)
    Call<RailwayCategoryResponse> getServices(@Header("Authorization") String header);


    // --------------------Kamgar APIS-------------------------

    @POST(ApiConstants.ADMIN_LOGIN_API)
    Call<AdminLoginSuccess> adminLogin(@Query("email") String emailId,
                                       @Query("password") String password);


    @POST(ApiConstants.GET_ADMIN_REQUEST_STATUS)
    Call<MyRequestStatusResponse> getAdminRequestStatus(@Header("Authorization") String header,
                                                        @Query("status") String status);

    @POST(ApiConstants.ADMIN_GET_COMPLAINT_BY_ID)
    Call<AdmintGetComplaintResponse> getAdminComplaintById(@Header("Authorization") String header,
                                                           @Query("id") String id);


    @Multipart
    @POST(ApiConstants.ADMIN_UPDATE_COMPLAINT_UPLOAD_IMAGE)
    Call<SaveComplaintResponse> uploadAdminComplaintUpdaate(
            @Header("Authorization") String header,
            @Part("id") RequestBody id,
            @Part("status") RequestBody status,
            @Part("comment") RequestBody comment,
            @Part MultipartBody.Part baseImage);
*/
}
