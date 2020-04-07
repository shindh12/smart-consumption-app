package com.ysj.sc.adapter.entity;

public class ConsumptionVO {
    private String categoryCd;
    private Long maxAmount;
    private Long categoryAmount;

    public ConsumptionVO() {
    }

    public ConsumptionVO(String categoryCd, Long maxAmount, Long categoryAmount) {
        this.categoryCd = categoryCd;
        this.maxAmount = maxAmount;
        this.categoryAmount = categoryAmount;
    }

    public String getCategoryCd() {
        return categoryCd;
    }

    public void setCategoryCd(String categoryCd) {
        this.categoryCd = categoryCd;
    }

    public Long getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(Long maxAmount) {
        this.maxAmount = maxAmount;
    }

    public Long getCategoryAmount() {
        return categoryAmount;
    }

    public void setCategoryAmount(Long categoryAmount) {
        this.categoryAmount = categoryAmount;
    }
}
