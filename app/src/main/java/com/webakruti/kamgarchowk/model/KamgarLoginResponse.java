package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 12/14/2018.
 */

public class KamgarLoginResponse {


    @SerializedName("error")
    private String error;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    @SerializedName("success")
    private Success success;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }


    public class Authkamgar {

        @SerializedName("id")
        private Integer id;
        @SerializedName("cont_img_url")
        private String contImgUrl;
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
        @SerializedName("city")
        private String city;
        @SerializedName("state")
        private String state;
        @SerializedName("country")
        private String country;
        @SerializedName("pincode")
        private Integer pincode;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getContImgUrl() {
            return contImgUrl;
        }

        public void setContImgUrl(String contImgUrl) {
            this.contImgUrl = contImgUrl;
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

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCountry() {
            return country;
        }

        public void setCountry(String country) {
            this.country = country;
        }

        public Integer getPincode() {
            return pincode;
        }

        public void setPincode(Integer pincode) {
            this.pincode = pincode;
        }

    }

    public class Success {

            @SerializedName("authkamgar")
            private Authkamgar authkamgar;
            @SerializedName("status")
            private Boolean status;
            @SerializedName("token")
            private String token;

            public Authkamgar getAuthkamgar() {
                return authkamgar;
            }

            public void setAuthkamgar(Authkamgar authkamgar) {
                this.authkamgar = authkamgar;
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
