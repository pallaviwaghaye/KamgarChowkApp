package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 12/5/2018.
 */

public class SubcategoryListResponse implements Serializable{


        @SerializedName("error")
        private String error;

        public String getError() {
            return error;
        }

        public void setError(String error) {
            this.error = error;
        }


        @SerializedName("subcategory")
        private List<Subcategory> subcategory = null;

        public List<Subcategory> getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(List<Subcategory> subcategory) {
            this.subcategory = subcategory;
        }


    public static class Subcategory implements Serializable{

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("category_id")
        private Integer categoryId;

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

        public Integer getCategoryId() {
            return categoryId;
        }

        public void setCategoryId(Integer categoryId) {
            this.categoryId = categoryId;
        }

        @Override
        public String toString() {
            return name;
        }
    }

}
