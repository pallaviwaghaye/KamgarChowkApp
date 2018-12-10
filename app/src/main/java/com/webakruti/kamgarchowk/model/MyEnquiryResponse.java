package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/7/2018.
 */

public class MyEnquiryResponse {


        @SerializedName("success")

        private Success success;

        public Success getSuccess() {
            return success;
        }

        public void setSuccess(Success success) {
            this.success = success;
        }



    public class Success {

        @SerializedName("userenquiry")

        private List<Userenquiry> userenquiry = null;

        public List<Userenquiry> getUserenquiry() {
            return userenquiry;
        }

        public void setUserenquiry(List<Userenquiry> userenquiry) {
            this.userenquiry = userenquiry;
        }

    }


    public class Userenquiry {

        @SerializedName("id")

        private Integer id;
        @SerializedName("kamgar_first_name")

        private String kamgarFirstName;
        @SerializedName("kamgar_last_name")

        private String kamgarLastName;
        @SerializedName("kamgar_mobile_no")

        private String kamgarMobileNo;
        @SerializedName("kamgar_address")

        private String kamgarAddress;
        @SerializedName("kamgar_pincode")

        private Integer kamgarPincode;
        @SerializedName("enquiry_date")

        private String enquiryDate;
        @SerializedName("workstatus")

        private String workstatus;
        @SerializedName("rateremark")

        private String rateremark;
        @SerializedName("cityname")

        private String cityname;
        @SerializedName("pincode")

        private Integer pincode;
        @SerializedName("work_status")

        private Integer workStatus;
        @SerializedName("rating")

        private Integer rating;
        @SerializedName("subcategory")

        private String subcategory;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getKamgarFirstName() {
            return kamgarFirstName;
        }

        public void setKamgarFirstName(String kamgarFirstName) {
            this.kamgarFirstName = kamgarFirstName;
        }

        public String getKamgarLastName() {
            return kamgarLastName;
        }

        public void setKamgarLastName(String kamgarLastName) {
            this.kamgarLastName = kamgarLastName;
        }

        public String getKamgarMobileNo() {
            return kamgarMobileNo;
        }

        public void setKamgarMobileNo(String kamgarMobileNo) {
            this.kamgarMobileNo = kamgarMobileNo;
        }

        public String getKamgarAddress() {
            return kamgarAddress;
        }

        public void setKamgarAddress(String kamgarAddress) {
            this.kamgarAddress = kamgarAddress;
        }

        public Integer getKamgarPincode() {
            return kamgarPincode;
        }

        public void setKamgarPincode(Integer kamgarPincode) {
            this.kamgarPincode = kamgarPincode;
        }

        public String getEnquiryDate() {
            return enquiryDate;
        }

        public void setEnquiryDate(String enquiryDate) {
            this.enquiryDate = enquiryDate;
        }

        public String getWorkstatus() {
            return workstatus;
        }

        public void setWorkstatus(String workstatus) {
            this.workstatus = workstatus;
        }

        public String getRateremark() {
            return rateremark;
        }

        public void setRateremark(String rateremark) {
            this.rateremark = rateremark;
        }

        public String getCityname() {
            return cityname;
        }

        public void setCityname(String cityname) {
            this.cityname = cityname;
        }

        public Integer getPincode() {
            return pincode;
        }

        public void setPincode(Integer pincode) {
            this.pincode = pincode;
        }

        public Integer getWorkStatus() {
            return workStatus;
        }

        public void setWorkStatus(Integer workStatus) {
            this.workStatus = workStatus;
        }

        public Integer getRating() {
            return rating;
        }

        public void setRating(Integer rating) {
            this.rating = rating;
        }

        public String getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(String subcategory) {
            this.subcategory = subcategory;
        }

    }
}
