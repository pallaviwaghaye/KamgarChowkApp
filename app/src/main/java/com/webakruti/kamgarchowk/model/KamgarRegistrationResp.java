package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 12/14/2018.
 */

public class KamgarRegistrationResp {


    @SerializedName("success")
    private String success;
    @SerializedName("otpverify")
    private Boolean otpverify;
    @SerializedName("status")
    private Boolean status;
    @SerializedName("data")
    private Data data;
    @SerializedName("errors")
    private String errors;


    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Boolean getOtpverify() {
        return otpverify;
    }

    public void setOtpverify(Boolean otpverify) {
        this.otpverify = otpverify;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getErrors() {
        return errors;
    }

    public void setErrors(String errors) {
        this.errors = errors;
    }



    public class Data {

        @SerializedName("first_name")
        private String firstName;
        @SerializedName("last_name")
        private String lastName;
        @SerializedName("mobile_no")
        private String mobileNo;
        @SerializedName("otp")
        private String otp;

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

        public String getOtp() {
            return otp;
        }

        public void setOtp(String otp) {
            this.otp = otp;
        }

    }


}
