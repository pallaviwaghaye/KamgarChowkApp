package com.vishwaraj.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/5/2018.
 */

public class SupportResponse {


        @SerializedName("success")

        private Success success;

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



        @SerializedName("first_name")

        private List<String> firstName = null;
        @SerializedName("last_name")

        private List<String> lastName = null;
        @SerializedName("contact_no")

        private List<String> contactNo = null;
        @SerializedName("subject")

        private List<String> subject = null;
        @SerializedName("problem_details")

        private List<String> problemDetails = null;

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

        public List<String> getContactNo() {
            return contactNo;
        }

        public void setContactNo(List<String> contactNo) {
            this.contactNo = contactNo;
        }

        public List<String> getSubject() {
            return subject;
        }

        public void setSubject(List<String> subject) {
            this.subject = subject;
        }

        public List<String> getProblemDetails() {
            return problemDetails;
        }

        public void setProblemDetails(List<String> problemDetails) {
            this.problemDetails = problemDetails;
        }



}
