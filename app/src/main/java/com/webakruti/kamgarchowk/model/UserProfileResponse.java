package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 12/7/2018.
 */

public class UserProfileResponse implements Serializable{

    @SerializedName("success")
    private Success success;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }


    public class Authuser implements Serializable{

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

    public static class City implements Serializable{

        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private Integer id;
        @SerializedName("state_id")
        private Integer stateId;
        @SerializedName("country_id")
        private Integer countryId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getStateId() {
            return stateId;
        }

        public void setStateId(Integer stateId) {
            this.stateId = stateId;
        }

        public Integer getCountryId() {
            return countryId;
        }

        public void setCountryId(Integer countryId) {
            this.countryId = countryId;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public static class Country implements Serializable{

        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private Integer id;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return name;
        }

    }


    public static class Gender implements Serializable{

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("value")
        private String value;
        @SerializedName("gm_id")
        private Integer gmId;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public Integer getGmId() {
            return gmId;
        }

        public void setGmId(Integer gmId) {
            this.gmId = gmId;
        }

        @Override
        public String toString() {
            return value;
        }

    }

    public static class State implements Serializable{

        @SerializedName("name")
        private String name;
        @SerializedName("id")
        private Integer id;
        @SerializedName("country_id")
        private Integer countryId;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getCountryId() {
            return countryId;
        }

        public void setCountryId(Integer countryId) {
            this.countryId = countryId;
        }

        @Override
        public String toString() {
            return name;
        }

    }

    public class Success implements Serializable{

        @SerializedName("authuser")
        private List<Authuser> authuser = null;
        @SerializedName("countries")
        private List<Country> countries = null;
        @SerializedName("states")
        private List<State> states = null;
        @SerializedName("cities")
        private List<City> cities = null;
        @SerializedName("gender")
        private List<Gender> gender = null;

        public List<Authuser> getAuthuser() {
            return authuser;
        }

        public void setAuthuser(List<Authuser> authuser) {
            this.authuser = authuser;
        }

        public List<Country> getCountries() {
            return countries;
        }

        public void setCountries(List<Country> countries) {
            this.countries = countries;
        }

        public List<State> getStates() {
            return states;
        }

        public void setStates(List<State> states) {
            this.states = states;
        }

        public List<City> getCities() {
            return cities;
        }

        public void setCities(List<City> cities) {
            this.cities = cities;
        }

        public List<Gender> getGender() {
            return gender;
        }

        public void setGender(List<Gender> gender) {
            this.gender = gender;
        }

    }
}
