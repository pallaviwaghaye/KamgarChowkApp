package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/17/2018.
 */

public class MyOrdersResponse {


        @SerializedName("success")
        private Success success;

        public Success getSuccess() {
            return success;
        }

        public void setSuccess(Success success) {
            this.success = success;
        }


    public class Success {

        @SerializedName("kamgaractenquiries")
        private List<Kamgaractenquiry> kamgaractenquiries = null;
        @SerializedName("workstatusselect")
        private List<Workstatusselect> workstatusselect = null;

        public List<Kamgaractenquiry> getKamgaractenquiries() {
            return kamgaractenquiries;
        }

        public void setKamgaractenquiries(List<Kamgaractenquiry> kamgaractenquiries) {
            this.kamgaractenquiries = kamgaractenquiries;
        }

        public List<Workstatusselect> getWorkstatusselect() {
            return workstatusselect;
        }

        public void setWorkstatusselect(List<Workstatusselect> workstatusselect) {
            this.workstatusselect = workstatusselect;
        }

    }

    public class Kamgaractenquiry {

        @SerializedName("id")
        private Integer id;
        @SerializedName("user_first_name")
        private String userFirstName;
        @SerializedName("user_last_name")
        private String userLastName;
        @SerializedName("user_mobile_no")
        private String userMobileNo;
        @SerializedName("user_address")
        private String userAddress;
        @SerializedName("user_pincode")
        private Integer userPincode;
        @SerializedName("enquiry_date")
        private String enquiryDate;
        @SerializedName("workstatus")
        private String workstatus;
        @SerializedName("cityname")
        private String cityname;
        @SerializedName("pincode")
        private Integer pincode;
        @SerializedName("work_status")
        private Integer workStatus;
        @SerializedName("rating")
        private Integer rating;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getUserFirstName() {
            return userFirstName;
        }

        public void setUserFirstName(String userFirstName) {
            this.userFirstName = userFirstName;
        }

        public String getUserLastName() {
            return userLastName;
        }

        public void setUserLastName(String userLastName) {
            this.userLastName = userLastName;
        }

        public String getUserMobileNo() {
            return userMobileNo;
        }

        public void setUserMobileNo(String userMobileNo) {
            this.userMobileNo = userMobileNo;
        }

        public String getUserAddress() {
            return userAddress;
        }

        public void setUserAddress(String userAddress) {
            this.userAddress = userAddress;
        }

        public Integer getUserPincode() {
            return userPincode;
        }

        public void setUserPincode(Integer userPincode) {
            this.userPincode = userPincode;
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

    }


    public class Workstatusselect {

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
            return this.value;
        }

        @Override
        public boolean equals(Object obj) {
            if(!(obj instanceof Workstatusselect)) return false;
            Workstatusselect other = (Workstatusselect) obj;
            return (this.id == other.id);
        }

    }
}
