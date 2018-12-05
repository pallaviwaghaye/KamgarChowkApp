package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/5/2018.
 */

public class HomeResponse {

        @SerializedName("featuredlist")
        private List<Featuredlist> featuredlist = null;
        @SerializedName("popularlist")
        private List<Popularlist> popularlist = null;
        @SerializedName("workavllist")
        private List<Workavllist> workavllist = null;

        public List<Featuredlist> getFeaturedlist() {
            return featuredlist;
        }

        public void setFeaturedlist(List<Featuredlist> featuredlist) {
            this.featuredlist = featuredlist;
        }

        public List<Popularlist> getPopularlist() {
            return popularlist;
        }

        public void setPopularlist(List<Popularlist> popularlist) {
            this.popularlist = popularlist;
        }

        public List<Workavllist> getWorkavllist() {
            return workavllist;
        }

        public void setWorkavllist(List<Workavllist> workavllist) {
            this.workavllist = workavllist;
        }



    public class Featuredlist {

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("categoryicon")
        private String categoryicon;

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

    }


    public class Popularlist {

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
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

        public String getCategoryimage() {
            return categoryimage;
        }

        public void setCategoryimage(String categoryimage) {
            this.categoryimage = categoryimage;
        }

    }

    public class Workavllist {

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;
        @SerializedName("categorybanner")
        private String categorybanner;

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

        public String getCategorybanner() {
            return categorybanner;
        }

        public void setCategorybanner(String categorybanner) {
            this.categorybanner = categorybanner;
        }

    }
}
