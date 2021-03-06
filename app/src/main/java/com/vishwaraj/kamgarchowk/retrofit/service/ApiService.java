package com.vishwaraj.kamgarchowk.retrofit.service;


import com.vishwaraj.kamgarchowk.model.CategoryList;
import com.vishwaraj.kamgarchowk.model.ChangePasswordResponse;
import com.vishwaraj.kamgarchowk.model.HireKamgarResponse;
import com.vishwaraj.kamgarchowk.model.HomeResponse;
import com.vishwaraj.kamgarchowk.model.KamgarCategoryResponse;
import com.vishwaraj.kamgarchowk.model.KamgarChangePwdResp;
import com.vishwaraj.kamgarchowk.model.KamgarChangeStatus;
import com.vishwaraj.kamgarchowk.model.KamgarGetProfile;
import com.vishwaraj.kamgarchowk.model.KamgarSaveDocsResp;
import com.vishwaraj.kamgarchowk.model.KamgarForgotPwdOtp;
import com.vishwaraj.kamgarchowk.model.KamgarForgotPwdResp;
import com.vishwaraj.kamgarchowk.model.KamgarLoginResponse;
import com.vishwaraj.kamgarchowk.model.KamgarRegOtp;
import com.vishwaraj.kamgarchowk.model.KamgarRegistrationResp;
import com.vishwaraj.kamgarchowk.model.KamgarResponse;
import com.vishwaraj.kamgarchowk.model.KamgarSubcategoriesResponse;
import com.vishwaraj.kamgarchowk.model.KamgarUpdateResp;
import com.vishwaraj.kamgarchowk.model.KamgarUpdateStatus;
import com.vishwaraj.kamgarchowk.model.MyEnquiryResponse;
import com.vishwaraj.kamgarchowk.model.MyOrdersResponse;
import com.vishwaraj.kamgarchowk.model.RateResponse;
import com.vishwaraj.kamgarchowk.model.SearchAutofill;
import com.vishwaraj.kamgarchowk.model.SearchLocationList;
import com.vishwaraj.kamgarchowk.model.SearchResponse;
import com.vishwaraj.kamgarchowk.model.SubcategoryListResponse;
import com.vishwaraj.kamgarchowk.model.SubscripnPlanResp;
import com.vishwaraj.kamgarchowk.model.SupportResponse;
import com.vishwaraj.kamgarchowk.model.UpdateProfileResponse;
import com.vishwaraj.kamgarchowk.model.UserForgotPassword;
import com.vishwaraj.kamgarchowk.model.UserForgtPassSndOtpResponse;
import com.vishwaraj.kamgarchowk.model.UserLoginResponse;
import com.vishwaraj.kamgarchowk.model.UserProfileResponse;
import com.vishwaraj.kamgarchowk.model.UserRegistrationResponse;
import com.vishwaraj.kamgarchowk.retrofit.ApiConstants;

import org.json.JSONArray;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface ApiService {

    // --------------------User APIS-------------------------

    @POST(ApiConstants.REG_API)
    Call<UserRegistrationResponse> qq(@Query("first_name") String firstName,
                                      @Body JSONArray lastName,
                                      @Query("mobile_no") String mobileNo,
                                      @Query("email") String email);


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
    Call<SearchLocationList> search_location(@Header("Authorization") String header);

    @POST(ApiConstants.search_autofill_API)
    Call<SearchAutofill> autofillsearch(@Header("Authorization") String header);

    @POST(ApiConstants.search_API)
    Call<SearchResponse> search(@Query("city_id") String cityId,
                                @Query("name") String queryName);

    @POST(ApiConstants.category_API)
    Call<CategoryList> getcategorylist(@Header("Authorization") String header);

    @POST(ApiConstants.subcategory_API)
    Call<SubcategoryListResponse> getsubcategorylist(@Header("Authorization") String header,
                                                     @Query("category_id") Integer categoryId);

    @POST(ApiConstants.kamgar_API)
    Call<KamgarResponse> getkamgarlist(@Header("Authorization") String header,
                                       @Query("subcat_id") Integer subcategoryid,
                                       @Query("city_id") Integer cityid);

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

    @POST(ApiConstants.changepwd_API)
    Call<ChangePasswordResponse> changepwd(@Header("Authorization") String header,
                                           @Query("id") Integer id,
                                           @Query("old_password") String oldpassword,
                                           @Query("password") String password,
                                           @Query("cpassword") String cpassword);

    @POST(ApiConstants.userprofile_API)
    Call<UserProfileResponse> userprofile(@Header("Authorization") String header,
                                          @Query("id") Integer id);

    /*@POST(ApiConstants.updateuserprofile_API)
    Call<UpdateProfileResponse> updateprofile(@Header("Authorization") String header,
                                              @Query("id") Integer id,
                                              @Query("first_name") String FName,
                                              @Query("middle_name") String middleName,
                                              @Query("last_name") String LName,
                                              @Query("dob") String datebirth,
                                              @Query("gender_id") Integer gender,
                                              @Query("mobile_no") String mobNo,
                                              @Query("email") String emailid,
                                              @Query("address") String address,
                                              @Query("country_id") Integer country,
                                              @Query("state_id") Integer state,
                                              @Query("city_id") Integer city,
                                              @Query("pincode") String pincode);*/

    @Multipart
    //header, id, bodyImage, FName, middleName, LName, datebirth, gender, mobNo, emailid, address, country, state, city, pincode
    @POST(ApiConstants.updateuserprofile_API)
    Call<UpdateProfileResponse> updateprofile(@Header("Authorization") String header,
                                              @Part("id") RequestBody id,
                                              @Part MultipartBody.Part userImage,
                                              @Part("first_name") RequestBody FName,
                                              @Part("middle_name") RequestBody middleName,
                                              @Part("last_name") RequestBody LName,
                                              @Part("dob") RequestBody datebirth,
                                              @Part("gender_id") RequestBody gender,
                                              @Part("mobile_no") RequestBody mobNo,
                                              @Part("email") RequestBody emailid,
                                              @Part("address") RequestBody address,
                                              @Part("country_id") RequestBody country,
                                              @Part("state_id") RequestBody state,
                                              @Part("city_id") RequestBody city,
                                              @Part("pincode") RequestBody pincode);

    @POST(ApiConstants.myenquiry_API)
    Call<MyEnquiryResponse> myenquiry(@Header("Authorization") String header,
                                      @Query("id") Integer id);

    @POST(ApiConstants.HireKamgar_API)
    Call<HireKamgarResponse> hirekamgar(@Header("Authorization") String header,
                                        @Query("kamgar_id") Integer kamgarid,
                                        @Query("user_id") Integer userid,
                                        @Query("subcategory_id") Integer subcategoryid);


    @POST(ApiConstants.Give_Rating_API)
    Call<RateResponse> giveRating(@Header("Authorization") String header,
                                  @Query("enquiryId") String enquiryId,
                                  @Query("workRatingId") String workRatingId);


    // --------------------Kamgar APIS-------------------------


    @POST(ApiConstants.RegSendOTP_API)
    Call<KamgarRegOtp> kamgarRegOTP(@Query("first_name") String firstname,
                                    @Query("last_name") String lastname,
                                    @Query("mobile_no") String mobileno);

    @POST(ApiConstants.Registration_API)
    Call<KamgarRegistrationResp> kamgarRegistration(@Query("first_name") String firstname,
                                                    @Query("last_name") String lastname,
                                                    @Query("mobile_no") String mobileno,
                                                    @Query("otp") String otp);


    @POST(ApiConstants.Kamgar_Login_API)
    Call<KamgarLoginResponse> kamgarLogin(@Query("mobile_no") String mobileno,
                                          @Query("password") String password);

    @POST(ApiConstants.Kamgar_forgotOTP_API)
    Call<KamgarForgotPwdOtp> kamgarforgotOTP(@Query("mobile_no") String mobileno);


    @POST(ApiConstants.Kamgar_forgot_API)
    Call<KamgarForgotPwdResp> kamgarForgot(@Query("mobile_no") String mobileno,
                                           @Query("otp") String otp);


    @POST(ApiConstants.Kamgar_ChangePwd_API)
    Call<KamgarChangePwdResp> kamgarChangepwd(@Header("Authorization") String header,
                                              @Query("id") Integer kamgarid,
                                              @Query("old_password") String oldpassword,
                                              @Query("password") String newpassword,
                                              @Query("cpassword") String confirmpassword);

    @POST(ApiConstants.Kamgar_GetProfile_API)
    Call<KamgarGetProfile> kamgarprofile(@Header("Authorization") String header);

    /*@POST(ApiConstants.Kamgar_updateProfile_API)
    Call<KamgarUpdateResp> updatekamgarprofile(@Header("Authorization") String header,
                                               @Query("id") Integer id,
                                               @Query("first_name") String FName,
                                               @Query("middle_name") String middleName,
                                               @Query("last_name") String LName,
                                               @Query("dob") String datebirth,
                                               @Query("gender_id") Integer gender,
                                               @Query("mobile_no") String mobNo,
                                               @Query("email") String emailid,
                                               @Query("address") String address,
                                               @Query("country_id") Integer country,
                                               @Query("state_id") Integer state,
                                               @Query("city_id") Integer city,
                                               @Query("pincode") String pincode);*/

    @Multipart
    @POST(ApiConstants.Kamgar_updateProfile_API)
    Call<KamgarUpdateResp> updatekamgarprofile(@Header("Authorization") String header,
                                               @Part MultipartBody.Part kamgarImage,
                                               @Part("first_name") RequestBody FName,
                                               @Part("middle_name") RequestBody middleName,
                                               @Part("last_name") RequestBody LName,
                                               @Part("dob") RequestBody datebirth,
                                               @Part("gender_id") RequestBody gender,
                                               @Part("mobile_no") RequestBody mobNo,
                                               @Part("email") RequestBody emailid,
                                               @Part("address") RequestBody address,
                                               @Part("country_id") RequestBody country,
                                               @Part("state_id") RequestBody state,
                                               @Part("city_id") RequestBody city,
                                               @Part("pincode") RequestBody pincode);


    @POST(ApiConstants.Kamgar_Category_API)
    Call<KamgarCategoryResponse> getkamgarCategory(@Header("Authorization") String header);

    @POST(ApiConstants.Kamgar_GetSubcat_API)
    Call<KamgarSubcategoriesResponse> getKamgarSubcat(@Header("Authorization") String header,
                                                      @Query("category_id") Integer categoryid);


    @POST(ApiConstants.Kamgar_support_API)
    Call<SupportResponse> KamgarSupportRequest(@Header("Authorization") String header,
                                               @Query("first_name") String firstName,
                                               @Query("last_name") String lastName,
                                               @Query("contact_no") String mobileNo,
                                               @Query("subject") String subject,
                                               @Query("problem_details") String problemDetails);


    // pan_no, pan_copy_url, bank_name, bank_acc_no, bank_passbook_copy_url
    @Multipart
    @POST(ApiConstants.Save_Documents)
    Call<KamgarSaveDocsResp> saveDocuments(
            @Header("Authorization") String header,
            @Part("pan_no") RequestBody PANno,
            @Part MultipartBody.Part panImage,
            @Part("bank_name") RequestBody BankName,
            @Part("bank_acc_no") RequestBody BankAccntNo,
            @Part MultipartBody.Part passbookImage);


    @GET(ApiConstants.SubscriptionPlans)
    Call<SubscripnPlanResp> subscriptionPlan(@Header("Authorization") String header);

    @POST(ApiConstants.KamgarOrders)
    Call<MyOrdersResponse> kamgaroders(@Header("Authorization") String header);

    @POST(ApiConstants.ChangeWorkStatus)
    Call<KamgarChangeStatus> kamgarChangeStatus(@Header("Authorization") String header,
                                                @Query("order_id") String orderid,
                                                @Query("work_status_id") String workstatusid);


    @POST(ApiConstants.Submit_kamgar_Status_Update)
    Call<KamgarUpdateStatus> updateKamgarStatus(@Header("Authorization") String header,
                                                @Body RequestBody body);

}
