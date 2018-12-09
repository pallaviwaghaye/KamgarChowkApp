package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/9/2018.
 */

public class HireGetKamgar {

    @SerializedName("success")

    private Success success;

    public Success getSuccess() {
        return success;
    }

    public void setSuccess(Success success) {
        this.success = success;
    }



    public class Success {

        @SerializedName("enquirydata")

        private List<Enquirydatum> enquirydata = null;

        public List<Enquirydatum> getEnquirydata() {
            return enquirydata;
        }

        public void setEnquirydata(List<Enquirydatum> enquirydata) {
            this.enquirydata = enquirydata;
        }

    }

    public class Enquirydatum {

        @SerializedName("hourly")
        private Integer hourly;
        @SerializedName("halfday")
        private Integer halfday;
        @SerializedName("fullday")
        private Integer fullday;
        @SerializedName("weekly")
        private Integer weekly;
        @SerializedName("monthly")
        private Integer monthly;
        @SerializedName("first_name")
        private String firstName;
        @SerializedName("last_name")
        private String lastName;
        @SerializedName("address")
        private String address;
        @SerializedName("cityname")
        private String cityname;
        @SerializedName("subcategory")
        private String subcategory;

        public Integer getHourly() {
            return hourly;
        }

        public void setHourly(Integer hourly) {
            this.hourly = hourly;
        }

        public Integer getHalfday() {
            return halfday;
        }

        public void setHalfday(Integer halfday) {
            this.halfday = halfday;
        }

        public Integer getFullday() {
            return fullday;
        }

        public void setFullday(Integer fullday) {
            this.fullday = fullday;
        }

        public Integer getWeekly() {
            return weekly;
        }

        public void setWeekly(Integer weekly) {
            this.weekly = weekly;
        }

        public Integer getMonthly() {
            return monthly;
        }

        public void setMonthly(Integer monthly) {
            this.monthly = monthly;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public String getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(String subcategory) {
            this.subcategory = subcategory;
        }

    }


}
