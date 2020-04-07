package com.ysj.sc.mvvm.retrofit.entity;

public class ConsumptionOutVO {
    private String date;
    private String category;
    private long usedAmount;

    public ConsumptionOutVO(String date, String category, long usedAmount) {
        this.date = date;
        this.category = category;
        this.usedAmount = usedAmount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public long getUsedAmount() {
        return usedAmount;
    }

    public void setUsedAmount(long usedAmount) {
        this.usedAmount = usedAmount;
    }
}
