package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/14/2018.
 */

public class KamgarForgotPwdOtp {


    @SerializedName("status")
    private Boolean status;
    @SerializedName("msgstatus")
    private Boolean msgstatus;
    @SerializedName("success")
    private String success;
    @SerializedName("data")
    private Data data;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Boolean getMsgstatus() {
        return msgstatus;
    }

    public void setMsgstatus(Boolean msgstatus) {
        this.msgstatus = msgstatus;
    }

    public String getSuccess() {
        return success;
    }

    public void setSuccess(String success) {
        this.success = success;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    @SerializedName("error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public class Data {

        @SerializedName("mobile_no")
        private String mobileNo;

        public String getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(String mobileNo) {
            this.mobileNo = mobileNo;
        }

    }


    public class Errors {

        @SerializedName("mobile_no")
        private List<String> mobileNo = null;

        public List<String> getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(List<String> mobileNo) {
            this.mobileNo = mobileNo;
        }

    }


        @SerializedName("errors")
        private Errors errors;

        public Errors getErrors() {
            return errors;
        }

        public void setErrors(Errors errors) {
            this.errors = errors;
        }




}
