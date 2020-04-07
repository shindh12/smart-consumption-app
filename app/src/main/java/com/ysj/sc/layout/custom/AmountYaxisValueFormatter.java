package com.ysj.sc.layout.custom;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class AmountYaxisValueFormatter extends ValueFormatter {
    // 백억 이 이상은 이딴거 필요없음
    private final String[] WON = {"원","십","백","천", "만", "십만", "백만", "천만", "억", "십억"};

    public AmountYaxisValueFormatter() {
    }

    @Override
    public String getAxisLabel(float value, AxisBase axis) {
        if (value > 0) {
            int post = (int) Math.log10(Math.round(value));
            int pre = 0;
            if (post != 0) pre = (int) (value / Math.pow(10, post));

            return pre + WON[post];
        } else {
            return 0 + WON[0];
        }
    }
}
