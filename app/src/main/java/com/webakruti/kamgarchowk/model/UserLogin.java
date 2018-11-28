package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 11/28/2018.
 */

public class UserLogin {


        @SerializedName("success")

        private Success success;

        public Success getSuccess() {
            return success;
        }

        public void setSuccess(Success success) {
            this.success = success;
        }


    public class Success {

        @SerializedName("status")
        @Expose
        private Boolean status;
        @SerializedName("token")
        @Expose
        private String token;

        public Boolean getStatus() {
            return status;
        }

        public void setStatus(Boolean status) {
            this.status = status;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

    }
}
