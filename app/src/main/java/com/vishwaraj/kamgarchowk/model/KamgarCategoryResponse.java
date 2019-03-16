package com.vishwaraj.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by DELL on 12/14/2018.
 */

public class KamgarCategoryResponse implements Serializable{


        @SerializedName("kamgarcategorylist")
        private List<Kamgarcategorylist> kamgarcategorylist = null;

        public List<Kamgarcategorylist> getKamgarcategorylist() {
            return kamgarcategorylist;
        }

        public void setKamgarcategorylist(List<Kamgarcategorylist> kamgarcategorylist) {
            this.kamgarcategorylist = kamgarcategorylist;
        }


    public class Kamgarcategorylist implements Serializable{

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
}
