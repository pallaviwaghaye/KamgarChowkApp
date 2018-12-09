package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 12/8/2018.
 */

public class KamgarResponse implements Serializable{


        @SerializedName("error")
        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }


        @SerializedName("kamgar")
        private List<Kamgar> kamgar = null;

        public List<Kamgar> getKamgar() {
            return kamgar;
        }

        public void setKamgar(List<Kamgar> kamgar) {
            this.kamgar = kamgar;
        }


    public class Kamgar implements Serializable{

        @SerializedName("kamgar_id")

        private Integer kamgarId;
        @SerializedName("first_name")

        private String firstName;
        @SerializedName("last_name")

        private String lastName;
        @SerializedName("mobile_no")

        private String mobileNo;
        @SerializedName("email")

        private String email;
        @SerializedName("address")

        private String address;
        @SerializedName("city")

        private String city;
        @SerializedName("cont_img_url")

        private Object contImgUrl;
        @SerializedName("rating")

        private Object rating;
        @SerializedName("experience")

        private Integer experience;
        @SerializedName("count")

        private Integer count;
        @SerializedName("subcategory_id")

        private Integer subcategoryId;

        public Integer getKamgarId() {
            return kamgarId;
        }

        public void setKamgarId(Integer kamgarId) {
            this.kamgarId = kamgarId;
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

        public Object getContImgUrl() {
            return contImgUrl;
        }

        public void setContImgUrl(Object contImgUrl) {
            this.contImgUrl = contImgUrl;
        }

        public Object getRating() {
            return rating;
        }

        public void setRating(Object rating) {
            this.rating = rating;
        }

        public Integer getExperience() {
            return experience;
        }

        public void setExperience(Integer experience) {
            this.experience = experience;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public Integer getSubcategoryId() {
            return subcategoryId;
        }

        public void setSubcategoryId(Integer subcategoryId) {
            this.subcategoryId = subcategoryId;
        }

    }
}
