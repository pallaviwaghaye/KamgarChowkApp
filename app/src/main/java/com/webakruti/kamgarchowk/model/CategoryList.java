package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 12/1/2018.
 */

public class CategoryList implements Serializable{

    @SerializedName("categorylist")
    private List<Categorylist> categorylist = null;
   /* @SerializedName("subcategorylist")
    private List<Subcategorylist> subcategorylist = null;*/

    public List<Categorylist> getCategorylist() {
        return categorylist;
    }

    public void setCategorylist(List<Categorylist> categorylist) {
        this.categorylist = categorylist;
    }

   /* public List<Subcategorylist> getSubcategorylist() {
        return subcategorylist;
    }

    public void setSubcategorylist(List<Subcategorylist> subcategorylist) {
        this.subcategorylist = subcategorylist;
    }
*/

    public class Categorylist implements Serializable{

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


    /*public class Subcategorylist {

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

    }*/

}
