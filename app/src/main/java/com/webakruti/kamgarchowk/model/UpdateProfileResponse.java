package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/7/2018.
 */

public class UpdateProfileResponse {


        @SerializedName("success")
        Success success;

        public Success getSuccess() {
            return success;
        }

        public void setSuccess(Success success) {
            this.success = success;
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

    @SerializedName("error")

    private Error error;

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }




    public class Error {

        @SerializedName("first_name")
        private List<String> firstName = null;
        @SerializedName("last_name")
        private List<String> lastName = null;
        @SerializedName("mobile_no")
        private List<String> mobileNo = null;
        @SerializedName("gender_id")
        private List<String> genderId = null;
        @SerializedName("country_id")
        private List<String> countryId = null;
        @SerializedName("state_id")
        private List<String> stateId = null;
        @SerializedName("city_id")
        private List<String> cityId = null;
        @SerializedName("email")
        private List<String> email = null;
        @SerializedName("dob")
        private List<String> dob = null;
        @SerializedName("address")
        private List<String> address = null;

        public List<String> getFirstName() {
            return firstName;
        }

        public void setFirstName(List<String> firstName) {
            this.firstName = firstName;
        }

        public List<String> getLastName() {
            return lastName;
        }

        public void setLastName(List<String> lastName) {
            this.lastName = lastName;
        }

        public List<String> getMobileNo() {
            return mobileNo;
        }

        public void setMobileNo(List<String> mobileNo) {
            this.mobileNo = mobileNo;
        }

        public List<String> getGenderId() {
            return genderId;
        }

        public void setGenderId(List<String> genderId) {
            this.genderId = genderId;
        }

        public List<String> getCountryId() {
            return countryId;
        }

        public void setCountryId(List<String> countryId) {
            this.countryId = countryId;
        }

        public List<String> getStateId() {
            return stateId;
        }

        public void setStateId(List<String> stateId) {
            this.stateId = stateId;
        }

        public List<String> getCityId() {
            return cityId;
        }

        public void setCityId(List<String> cityId) {
            this.cityId = cityId;
        }

        public List<String> getEmail() {
            return email;
        }

        public void setEmail(List<String> email) {
            this.email = email;
        }

        public List<String> getDob() {
            return dob;
        }

        public void setDob(List<String> dob) {
            this.dob = dob;
        }

        public List<String> getAddress() {
            return address;
        }

        public void setAddress(List<String> address) {
            this.address = address;
        }

    }




}
