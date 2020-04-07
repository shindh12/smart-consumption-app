package com.ysj.sc.adapter.entity;

public class DetailVO {
    private String date;
    private String store;
    private long price;

    public DetailVO() {
    }

    public DetailVO(String date, String store, long price) {
        this.date = date;
        this.store = store;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStore() {
        return store;
    }

    public void setStore(String store) {
        this.store = store;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
