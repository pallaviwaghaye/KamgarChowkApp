package com.vishwaraj.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 12/9/2018.
 */

public class HireKamgarResponse {

        @SerializedName("status")
        private Boolean status;
        @SerializedName("msg")
        private String msg;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }


}
