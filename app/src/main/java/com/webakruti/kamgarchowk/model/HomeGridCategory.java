package com.webakruti.kamgarchowk.model;

import android.graphics.drawable.Drawable;

public class HomeGridCategory {
    String categoryName;
    Drawable categoryImage;

    public HomeGridCategory(String categoryName, Drawable categoryImage) {
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Drawable getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(Drawable categoryImage) {
        this.categoryImage = categoryImage;
    }
}
