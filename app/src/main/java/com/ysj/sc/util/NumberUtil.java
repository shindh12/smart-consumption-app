package com.ysj.sc.util;

import android.icu.text.DecimalFormat;
import android.icu.text.NumberFormat;
import android.icu.util.CurrencyAmount;

import com.ysj.sc.common.Const;

import java.util.Locale;

public class NumberUtil {

    private static NumberFormat FORMATTER = NumberFormat.getCurrencyInstance(Locale.KOREA);

    private NumberUtil() throws InstantiationException {
        throw new InstantiationException();
    }

    public static long removeComma(String amountWithComma) {
        return Long.parseLong(amountWithComma.replace(Const.COMMA, ""));
    }

    public static String getCurrencyFormat(long amount) {
        return FORMATTER.format(amount);
    }
    public static double getRatio(long dividend, long divisor) {
        if (divisor == 0) return 0;
        else return (double) dividend / divisor;
    }

}
