package com.webakruti.kamgarchowk.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 12/3/2018.
 */

public class SearchAutofill {

        @SerializedName("subcategory")

        private List<SubcategoryListResponse.Subcategory> subcategory = null;

        public List<SubcategoryListResponse.Subcategory> getSubcategory() {
            return subcategory;
        }

        public void setSubcategory(List<SubcategoryListResponse.Subcategory> subcategory) {
            this.subcategory = subcategory;
        }



}
