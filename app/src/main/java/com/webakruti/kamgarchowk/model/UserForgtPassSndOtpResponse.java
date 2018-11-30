package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 11/30/2018.
 */

public class UserForgtPassSndOtpResponse {


    @SerializedName("status")

    private Boolean status;
    @SerializedName("msgstatus")

    private Boolean msgstatus;
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

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }


    @SerializedName("errors")

    private Errors errors;

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
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

}
