package com.ysj.sc.adapter.entity;

import java.util.ArrayList;

public class CumulativeVO {
    private String categoryName;
    private long categoryAmount;
    private ArrayList<Long> monthlyData;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getCategoryAmount() {
        return categoryAmount;
    }

    public void setCategoryAmount(long categoryAmount) {
        this.categoryAmount = categoryAmount;
    }

    public ArrayList<Long> getMonthlyData() {
        return monthlyData;
    }

    public void setMonthlyData(ArrayList<Long> monthlyData) {
        this.monthlyData = monthlyData;
    }
}
