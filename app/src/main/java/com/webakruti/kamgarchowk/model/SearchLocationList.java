package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 12/3/2018.
 */

public class SearchLocationList{

    @SerializedName("citylist")
    private List<Citylist> citylist = null;

    public List<Citylist> getCitylist() {
        return citylist;
    }

    public void setCitylist(List<Citylist> citylist) {
        this.citylist = citylist;
    }

    public static class Citylist implements Serializable{

        @SerializedName("id")
        private Integer id;
        @SerializedName("name")
        private String name;

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

        @Override
        public String toString() {
            return name;
        }

    }


}
