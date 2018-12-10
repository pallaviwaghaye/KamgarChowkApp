package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 11/28/2018.
 */

public class UserLoginResponse {

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



    public class Authuser {

        @SerializedName("id")
        private Integer id;
        @SerializedName("first_name")
        private String firstName;
        @SerializedName("middle_name")
        private String middleName;
        @SerializedName("last_name")
        private String lastName;
        @SerializedName("mobile_no")
        private String mobileNo;
        @SerializedName("email")
        private String email;
        @SerializedName("dob")
        private String dob;
        @SerializedName("gender_id")
        private String genderId;
        @SerializedName("address")
        private String address;
        @SerializedName("country_id")
        private String countryId;
        @SerializedName("state_id")
        private String stateId;
        @SerializedName("city_id")
        private String cityId;
        @SerializedName("pincode")
        private Integer pincode;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getMiddleName() {
            return middleName;
        }

        public void setMiddleName(String middleName) {
            this.middleName = middleName;
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

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getDob() {
            return dob;
        }

        public void setDob(String dob) {
            this.dob = dob;
        }

        public String getGenderId() {
            return genderId;
        }

        public void setGenderId(String genderId) {
            this.genderId = genderId;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCountryId() {
            return countryId;
        }

        public void setCountryId(String countryId) {
            this.countryId = countryId;
        }

        public String getStateId() {
            return stateId;
        }

        public void setStateId(String stateId) {
            this.stateId = stateId;
        }

        public String getCityId() {
            return cityId;
        }

        public void setCityId(String cityId) {
            this.cityId = cityId;
        }

        public Integer getPincode() {
            return pincode;
        }

        public void setPincode(Integer pincode) {
            this.pincode = pincode;
        }

    }


    public class Success {

        @SerializedName("authuser")
        private Authuser authuser;
        @SerializedName("status")
        private Boolean status;
        @SerializedName("token")
        private String token;

        public Authuser getAuthuser() {
            return authuser;
        }

        public void setAuthuser(Authuser authuser) {
            this.authuser = authuser;
        }

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
