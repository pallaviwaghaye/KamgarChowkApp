package com.vishwaraj.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/14/2018.
 */

public class KamgarForgotPwdResp {


        @SerializedName("success")
        private String success;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }


        @SerializedName("mobile_no")
        private List<String> mobileNo = null;
        @SerializedName("otp")
        private List<String> otp = null;

        public List<String> getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(List<String> mobileNo) {
            this.mobileNo = mobileNo;
        }

        public List<String> getOtp() {
            return otp;
        }

        public void setOtp(List<String> otp) {
            this.otp = otp;
        }


        @SerializedName("error")
        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }



}
