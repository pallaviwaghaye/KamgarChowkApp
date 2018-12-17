package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/17/2018.
 */

public class KamgarUpdateResp {

    @SerializedName("success")
    private Success success;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
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



    public class Success {

        @SerializedName("msg")
        private String msg;
        @SerializedName("authkamgar")
        private Authkamgar authkamgar;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

        public Authkamgar getAuthkamgar() {
            return authkamgar;
        }

        public void setAuthkamgar(Authkamgar authkamgar) {
            this.authkamgar = authkamgar;
        }

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

    public class Authkamgar {

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
        @SerializedName("gender")
        private String gender;
        @SerializedName("address")
        private String address;
        @SerializedName("country")
        private String country;
        @SerializedName("state")
        private String state;
        @SerializedName("city")
        private String city;
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

        public String getGender() {
            return gender;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public Integer getPincode() {
            return pincode;
        }

        public void setPincode(Integer pincode) {
            this.pincode = pincode;
        }

    }


}
