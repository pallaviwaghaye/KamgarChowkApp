package com.webakruti.kamgarchowk.retrofit.service;


import com.webakruti.kamgarchowk.model.CategoryList;
import com.webakruti.kamgarchowk.model.HomeResponse;
import com.webakruti.kamgarchowk.model.SearchAutofill;
import com.webakruti.kamgarchowk.model.SearchLocationList;
import com.webakruti.kamgarchowk.model.SearchResponse;
import com.webakruti.kamgarchowk.model.SubcategoryListResponse;
import com.webakruti.kamgarchowk.model.SupportResponse;
import com.webakruti.kamgarchowk.model.UserForgotPassword;
import com.webakruti.kamgarchowk.model.UserForgtPassSndOtpResponse;
import com.webakruti.kamgarchowk.model.UserLoginResponse;
import com.webakruti.kamgarchowk.model.UserRegistrationResponse;
import com.webakruti.kamgarchowk.retrofit.ApiConstants;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiService {

    // --------------------User APIS-------------------------
    // Registration API
    //192.168.29.107/kamgar-chowk/api/user-registration?first_name=Pallavi&last_name=Waghaye&mobile_no=7975972248
    @POST(ApiConstants.REG_API)
    Call<UserRegistrationResponse> registration(@Query("first_name") String firstName,
                                                @Query("last_name") String lastName,
                                                @Query("mobile_no") String mobileNo,
                                                @Query("email") String email);

    // Login API
    //192.168.29.107/kamgar-chowk/api/user-login?mobile_no=7975972248&password=rx00k31a
    @POST(ApiConstants.LOGIN_API)
    Call<UserLoginResponse> login(@Query("mobile_no") String mobileNo,
                                  @Query("password") String password);

    @POST(ApiConstants.FORGOT_OTP_API)
    Call<UserForgtPassSndOtpResponse> forgototp(@Query("mobile_no") String mobileNo);

    @POST(ApiConstants.FORGOT_API)
    Call<UserForgotPassword> forgot(@Query("mobile_no") String mobileNo,
                                    @Query("otp_code") String otp);

    @GET(ApiConstants.search_location_API)
    Call<List<SearchLocationList>> search_location(@Header("Authorization") String header);

    @POST(ApiConstants.search_autofill_API)
    Call<SearchAutofill> search_autofill(@Header("Authorization") String header);

    @POST(ApiConstants.search_API)
    Call<SearchResponse> search(@Query("city_id") String cityId,
                                         @Query("name") String queryName);

    @POST(ApiConstants.category_API)
    Call<List<CategoryList>> getcategorylist(@Header("Authorization") String header);

    @POST(ApiConstants.subcategory_API)
    Call<List<SubcategoryListResponse>> getsubcategorylist(@Header("Authorization") String header);

    //http://beta.kamgarchowk.com/api/support?contact_no=7975972248&first_name=pallavi&last_name=waghaye&subject=abcdasnsj&problem_details=testing sbdhsdhsd
    @POST(ApiConstants.support_API)
    Call<SupportResponse> sendSupportRequest(@Header("Authorization") String header,
                                             @Query("first_name") String firstName,
                                             @Query("last_name") String lastName,
                                             @Query("contact_no") String mobileNo,
                                             @Query("subject") String subject,
                                             @Query("problem_details") String problemDetails);

    @GET(ApiConstants.home_API)
    Call<HomeResponse> homeList(@Header("Authorization") String header);



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
