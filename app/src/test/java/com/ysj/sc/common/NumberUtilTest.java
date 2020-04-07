package com.ysj.sc.common;

import com.ysj.sc.common.util.NumberUtil;

import org.junit.Test;

public class NumberUtilTest {
    @Test
    public void convertComma_guide() {
        String amountWithComma = "3,000,000";
        System.out.println(NumberUtil.removeComma(amountWithComma));
    }
}
