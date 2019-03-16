package com.vishwaraj.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 12/18/2018.
 */

public class KamgarChangeStatus {

        @SerializedName("success")
        private Success success;

        public Success getSuccess() {
            return success;
        }

        public void setSuccess(Success success) {
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



    public class Success {

        @SerializedName("msg")
        private String msg;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

    }


}
