package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by DELL on 12/1/2018.
 */

public class CategoryList{

        @SerializedName("id")

        private Integer id;
        @SerializedName("name")

        private String name;
        @SerializedName("categoryicon")

        private String categoryicon;
        @SerializedName("categoryimage")

        private String categoryimage;

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

        public String getCategoryicon() {
            return categoryicon;
        }

        public void setCategoryicon(String categoryicon) {
            this.categoryicon = categoryicon;
        }

        public String getCategoryimage() {
            return categoryimage;
        }

        public void setCategoryimage(String categoryimage) {
            this.categoryimage = categoryimage;
        }


}
