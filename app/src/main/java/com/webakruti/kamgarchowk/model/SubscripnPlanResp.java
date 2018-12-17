package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/17/2018.
 */

public class SubscripnPlanResp {

        @SerializedName("success")
        private Success success;

        public Success getSuccess() {
            return success;
        }

        public void setSuccess(Success success) {
            this.success = success;
        }


    public class Subcriptionplan {

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("amount")
        private String amount;
        @SerializedName("duration")
        private String duration;
        @SerializedName("expiry_date")
        private String expiryDate;

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

        public String getAmount() {
            return amount;
        }

        public void setAmount(String amount) {
            this.amount = amount;
        }

        public String getDuration() {
            return duration;
        }

        public void setDuration(String duration) {
            this.duration = duration;
        }

        public String getExpiryDate() {
            return expiryDate;
        }

        public void setExpiryDate(String expiryDate) {
            this.expiryDate = expiryDate;
        }

    }


    public class Success {

        @SerializedName("subcriptionplans")

        private List<Subcriptionplan> subcriptionplans = null;

        public List<Subcriptionplan> getSubcriptionplans() {
            return subcriptionplans;
        }

        public void setSubcriptionplans(List<Subcriptionplan> subcriptionplans) {
            this.subcriptionplans = subcriptionplans;
        }

    }
}
