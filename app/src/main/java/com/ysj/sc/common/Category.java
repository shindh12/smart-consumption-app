package com.ysj.sc.common;

import com.ysj.sc.R;

public enum Category {
    CC(R.drawable.ic_category_credit, "신용카드", "CC"),
    DC(R.drawable.ic_category_credit, "체크카드", "DC"),
    CS(R.drawable.ic_category_cash, "현금영수증", "CS"),
    TR(R.drawable.ic_category_market, "교통", "TR"),
    CU(R.drawable.ic_category_market, "문화", "CU"),
    TM(R.drawable.ic_category_market, "전통시장", "TM");

    private int icon;
    private String name;
    private String code;

    Category(int icon, String name, String code) {
        this.icon = icon;
        this.name = name;
        this.code = code;
    }

    public int getIcon() {
        return icon;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
};