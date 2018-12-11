package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 12/7/2018.
 */

public class UserProfileResponse implements Serializable {

    @SerializedName("success")
    @Expose
    private Success success;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }


    public class Success implements Serializable {

        @SerializedName("authuser")
        @Expose
        private List<Authuser> authuser = null;
        @SerializedName("data")
        @Expose
        private List<Country> country = null;
        @SerializedName("gender")
        @Expose
        private List<Gender> gender = null;

        public List<Authuser> getAuthuser() {
            return authuser;
        }

        public void setAuthuser(List<Authuser> authuser) {
            this.authuser = authuser;
        }

        public List<Country> getCountry() {
            return country;
        }

        public void setCountry(List<Country> data) {
            this.country = data;
        }

        public List<Gender> getGender() {
            return gender;
        }

        public void setGender(List<Gender> gender) {
            this.gender = gender;
        }

    }

    public static class Authuser implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("first_name")
        @Expose
        private String firstName;
        @SerializedName("middle_name")
        @Expose
        private String middleName;
        @SerializedName("last_name")
        @Expose
        private String lastName;
        @SerializedName("mobile_no")
        @Expose
        private String mobileNo;
        @SerializedName("email")
        @Expose
        private String email;
        @SerializedName("dob")
        @Expose
        private String dob;
        @SerializedName("address")
        @Expose
        private String address;
        @SerializedName("country_id")
        @Expose
        private Integer countryId;
        @SerializedName("state_id")
        @Expose
        private Integer stateId;
        @SerializedName("city_id")
        @Expose
        private Integer cityId;
        @SerializedName("gender_id")
        @Expose
        private Integer genderId;
        @SerializedName("pincode")
        @Expose
        private long pincode;
        @SerializedName("country")
        @Expose
        private Country country;
        @SerializedName("state")
        @Expose
        private State state;
        @SerializedName("city")
        @Expose
        private City city;
        @SerializedName("gender")
        @Expose
        private Gender gender;

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

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public Integer getCountryId() {
            return countryId;
        }

        public void setCountryId(Integer countryId) {
            this.countryId = countryId;
        }

        public Integer getStateId() {
            return stateId;
        }

        public void setStateId(Integer stateId) {
            this.stateId = stateId;
        }

        public Integer getCityId() {
            return cityId;
        }

        public void setCityId(Integer cityId) {
            this.cityId = cityId;
        }

        public Integer getGenderId() {
            return genderId;
        }

        public void setGenderId(Integer genderId) {
            this.genderId = genderId;
        }

        public long getPincode() {
            return pincode;
        }

        public void setPincode(long pincode) {
            this.pincode = pincode;
        }

        public Country getCountry() {
            return country;
        }

        public void setCountry(Country country) {
            this.country = country;
        }

        public State getState() {
            return state;
        }

        public void setState(State state) {
            this.state = state;
        }

        public City getCity() {
            return city;
        }

        public void setCity(City city) {
            this.city = city;
        }

        public Gender getGender() {
            return gender;
        }

        public void setGender(Gender gender) {
            this.gender = gender;
        }

    }

    public static class City implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("state_id")
        @Expose
        private Integer stateId;

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

        public Integer getStateId() {
            return stateId;
        }

        public void setStateId(Integer stateId) {
            this.stateId = stateId;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof City)) return false;
            City other = (City) o;
            return (this.id == other.id);
        }
    }

    public static class Country implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("states")
        @Expose
        private List<State> states = null;

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

        public List<State> getStates() {
            return states;
        }

        public void setStates(List<State> states) {
            this.states = states;
        }

        @Override
        public String toString() {
            return this.name;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Country)) return false;
            Country other = (Country) o;
            return (this.id == other.id);
        }

    }

    public static class Gender implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("value")
        @Expose
        private String value;
        @SerializedName("gm_id")
        @Expose
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
            return this.value;
        }

        @Override
        public boolean equals(Object o) {
            if (!(o instanceof Gender)) return false;
            Gender other = (Gender) o;
            return (this.id == other.id);
        }
    }

    public static class State implements Serializable {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("country_id")
        @Expose
        private Integer countryId;
        @SerializedName("cities")
        @Expose
        private List<City> cities = null;

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

        public Integer getCountryId() {
            return countryId;
        }

        public void setCountryId(Integer countryId) {
            this.countryId = countryId;
        }

        public List<City> getCities() {
            return cities;
        }

        public void setCities(List<City> cities) {
            this.cities = cities;
        }

        @Override
        public String toString() {
            return this.name;
        }


        @Override
        public boolean equals(Object o) {
            if (!(o instanceof State)) return false;
            State other = (State) o;
            return (this.id == other.id);
        }
    }


}
