package com.webakruti.kamgarchowk.retrofit.service;


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

   /* // --------------------User APIS-------------------------
    // Registration API
    // http://nirmalrail.webakruti.in/api/registration?name=pallavi&mobile=9561665846&password=9561665846
    @POST(ApiConstants.REG_API)
    Call<RegistrationResponse> registration(@Query("name") String name,
                                            @Query("mobile") String mobileNo,
                                            @Query("password") String password);

    // Login API
    //http://nirmalrail.webakruti.in/api/login?mobile=9561665846&password=9561665846&otp=123456
    @POST(ApiConstants.LOGIN_API)
    Call<UserResponse> login(@Query("mobile") String mobileNo,
                             @Query("password") String password,
                             @Query("otp") String otp);


    // OTP API
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
