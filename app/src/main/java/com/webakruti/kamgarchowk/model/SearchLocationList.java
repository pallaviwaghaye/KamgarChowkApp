package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 12/3/2018.
 */

public class SearchLocationList {

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

}
