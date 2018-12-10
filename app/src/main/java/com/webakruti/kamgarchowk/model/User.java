package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 12/10/2018.
 */

public class User {

    @SerializedName("id")
    private Integer id;
    @SerializedName("first_name")
    private String firstName;
    @SerializedName("middle_name")
    private Object middleName;
    @SerializedName("last_name")
    private String lastName;
    @SerializedName("mobile_no")
    private String mobileNo;
    @SerializedName("email")
    private String email;
    @SerializedName("dob")
    private Object dob;
    @SerializedName("gender_id")
    private Object genderId;
    @SerializedName("address")
    private Object address;
    @SerializedName("country_id")
    private Object countryId;
    @SerializedName("state_id")
    private Object stateId;
    @SerializedName("city_id")
    private Object cityId;
    @SerializedName("pincode")
    private Object pincode;

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

    public Object getMiddleName() {
        return middleName;
    }

    public void setMiddleName(Object middleName) {
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

    public Object getDob() {
        return dob;
    }

    public void setDob(Object dob) {
        this.dob = dob;
    }

    public Object getGenderId() {
        return genderId;
    }

    public void setGenderId(Object genderId) {
        this.genderId = genderId;
    }

    public Object getAddress() {
        return address;
    }

    public void setAddress(Object address) {
        this.address = address;
    }

    public Object getCountryId() {
        return countryId;
    }

    public void setCountryId(Object countryId) {
        this.countryId = countryId;
    }

    public Object getStateId() {
        return stateId;
    }

    public void setStateId(Object stateId) {
        this.stateId = stateId;
    }

    public Object getCityId() {
        return cityId;
    }

    public void setCityId(Object cityId) {
        this.cityId = cityId;
    }

    public Object getPincode() {
        return pincode;
    }

    public void setPincode(Object pincode) {
        this.pincode = pincode;
    }
}
