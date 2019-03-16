package com.vishwaraj.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/6/2018.
 */

public class ChangePasswordResponse {


        @SerializedName("success")
        private String success;

        public String getSuccess() {
            return success;
        }

        public void setSuccess(String success) {
            this.success = success;
        }

        @SerializedName("error")
        private Error error;

        public Error getError() {
            return error;
        }

        public void setError(Error error) {
            this.error = error;
        }


    @SerializedName("old_password")

    private List<String> oldPassword = null;

    public List<String> getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(List<String> oldPassword) {
        this.oldPassword = oldPassword;
    }


    public class Error {

        @SerializedName("password")
        private List<String> password = null;
        @SerializedName("cpassword")

        private List<String> cpassword = null;
        @SerializedName("old_password")

        private List<String> oldPassword = null;

        public List<String> getPassword() {
            return password;
        }

        public void setPassword(List<String> password) {
            this.password = password;
        }

        public List<String> getCpassword() {
            return cpassword;
        }

        public void setCpassword(List<String> cpassword) {
            this.cpassword = cpassword;
        }

        public List<String> getOldPassword() {
            return oldPassword;
        }

        public void setOldPassword(List<String> oldPassword) {
            this.oldPassword = oldPassword;
        }

    }


}
