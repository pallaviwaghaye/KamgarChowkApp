package com.vishwaraj.kamgarchowk.model;

public class KamgarPrice {

    String hourly;
    String halfDay;
    String fullDay;
    String weekly;
    String monthly;


    public KamgarPrice() {
    }

    public KamgarPrice(String hourly, String halfDay, String fullDay, String weekly, String monthly) {
        this.hourly = hourly;
        this.halfDay = halfDay;
        this.fullDay = fullDay;
        this.weekly = weekly;
        this.monthly = monthly;
    }

    public String getHourly() {
        return hourly;
    }

    public void setHourly(String hourly) {
        this.hourly = hourly;
    }

    public String getHalfDay() {
        return halfDay;
    }

    public void setHalfDay(String halfDay) {
        this.halfDay = halfDay;
    }

    public String getFullDay() {
        return fullDay;
    }

    public void setFullDay(String fullDay) {
        this.fullDay = fullDay;
    }

    public String getWeekly() {
        return weekly;
    }

    public void setWeekly(String weekly) {
        this.weekly = weekly;
    }

    public String getMonthly() {
        return monthly;
    }

    public void setMonthly(String monthly) {
        this.monthly = monthly;
    }
}
