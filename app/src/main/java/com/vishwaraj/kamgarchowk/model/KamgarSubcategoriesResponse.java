package com.vishwaraj.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/15/2018.
 */

public class KamgarSubcategoriesResponse {

        @SerializedName("subcategory")
        private List<Subcategory> subcategory = null;

        public List<Subcategory> getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(List<Subcategory> subcategory) {
            this.subcategory = subcategory;
        }


    public class Subcategory {

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("is_active")
        private Integer isActive;
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

        public Integer getIsActive() {
            return isActive;
        }

        public void setIsActive(Integer isActive) {
            this.isActive = isActive;
        }

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

    }



       /* @SerializedName("subcategory")
        private List<Object> subcategory = null;

        public List<Object> getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(List<Object> subcategory) {
            this.subcategory = subcategory;
        }*/

}
