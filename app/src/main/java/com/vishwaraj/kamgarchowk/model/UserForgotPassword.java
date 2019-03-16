package com.vishwaraj.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/1/2018.
 */

public class UserForgotPassword {

        @SerializedName("success")

        private String success;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }


        @SerializedName("error")

        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }


        @SerializedName("mobile_no")

        private List<String> mobileNo = null;
        @SerializedName("otp_code")

        private List<String> otpCode = null;

        public List<String> getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(List<String> mobileNo) {
            this.mobileNo = mobileNo;
        }

        public List<String> getOtpCode() {
            return otpCode;
        }

        public void setOtpCode(List<String> otpCode) {
            this.otpCode = otpCode;
        }




}
