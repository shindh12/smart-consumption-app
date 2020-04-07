package com.ysj.sc.layout.fragment;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.LineHeightSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.github.mikephil.charting.utils.MPPointF;
import com.ysj.sc.R;
import com.ysj.sc.adapter.ConsumptionListAdapter;
import com.ysj.sc.mvvm.db.entity.Statistics;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CategoryFragment extends Fragment implements OnChartValueSelectedListener {
    private static final String TAG = CategoryFragment.class.getSimpleName();

    @BindView(R.id.consumption_chart)
    PieChart pieChart;
    @BindView(R.id.category_amount_list)
    ListView categoryListView;
    @BindView(R.id.consumption_list)
    ListView listView;

    private List<HashMap<String, String>> categoryAmountList;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);
        setData();
        categoryAmountList = new ArrayList<>();
        setChart();
        setChartData();
        setCategoryList();
        return view;
    }


    private void setData() {
        Statistics data1 = new Statistics("202001", "CC", 3000000L, 1500000L);
        Statistics data2 = new Statistics("202001", "DC", 3000000L, 700000L);
        Statistics data3 = new Statistics("202001", "CS", 3000000L, 300000L);
        Statistics data4 = new Statistics("202001", "TR", 1000000L, 300000L);
        Statistics data5 = new Statistics("202001", "CU", 1000000L, 200000L);
        Statistics data6 = new Statistics("202001", "TM", 1000000L, 100000L);
        ArrayList<Statistics> list = new ArrayList<>();
        list.add(data1);
        list.add(data2);
        list.add(data3);
        list.add(data4);
        list.add(data5);
        list.add(data6);

        ConsumptionListAdapter adapter = new ConsumptionListAdapter(getContext(), R.layout.item_consumption_list, list);

        listView.setAdapter(adapter);

    }

    private void setCategoryList() {
        SimpleAdapter adapter = new SimpleAdapter(
                getContext(),
                categoryAmountList,
                R.layout.item_category_list,
                new String[]{"category", "amount"},
                new int[]{R.id.category_name_view, R.id.category_amount_view});

        categoryListView.setAdapter(adapter);

    }

    private void setChart() {
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5, 10, 5, 5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setCenterText(generateCenterSpannableText());

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);

        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(110);

        pieChart.setHoleRadius(80f);
        pieChart.setTransparentCircleRadius(82f);

        pieChart.setDrawCenterText(true);

        pieChart.setRotationAngle(0);
        // enable rotation of the chart by touch
        pieChart.setRotationEnabled(true);
        pieChart.setHighlightPerTapEnabled(true);
        pieChart.animateY(1400, Easing.EaseInOutQuad);
        // chart.spin(2000, 0, 360);


        pieChart.setOnChartValueSelectedListener(this);
        pieChart.getLegend().setEnabled(false);

//        pieChart.highlightValue(1.0f, 0, false);
    }

    private CharSequence generateCenterSpannableText() {
        SpannableString s = new SpannableString("최대지출 카테고리\n신용카드\n1,500,000원");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            s.setSpan(new LineHeightSpan.Standard(50), 10, s.length() - 11, 0);
        }
        s.setSpan(new RelativeSizeSpan(1.5f), 10, s.length() - 11, 0);
        s.setSpan(new StyleSpan(Typeface.BOLD), 9, s.length() - 11, 0);
        s.setSpan(new ForegroundColorSpan(Color.GRAY), 0, 9, 0);
        s.setSpan(new RelativeSizeSpan(1.1f), s.length() - 11, s.length(), 0);
        s.setSpan(new ForegroundColorSpan(Color.DKGRAY), s.length() - 11, s.length(), 0);
        return s;
    }

    private void setChartData() {
        ArrayList<PieEntry> yEntrys = new ArrayList<>();
        ArrayList<String> xEntrys = new ArrayList<>();

        long total = 3000000;
        long[] yData = {1500000, 700000, 300000, 300000, 200000, 100000};
        String[] xData = {"신용카드", "체크카드", "현금", "교통비", "의료비", "전통시장"};

        for (int i = 0; i < 6; i++) {
            HashMap<String, String> map = new HashMap<>();
            map.put("category", xData[i]);
            map.put("amount", NumberFormat.getNumberInstance(Locale.KOREA).format(yData[i]) + "원");
            categoryAmountList.add(map);
            yEntrys.add(new PieEntry(100.0f * yData[i] / total, i));
            xEntrys.add(xData[i]);
        }

        PieDataSet dataSet = new PieDataSet(yEntrys, "Results");

        dataSet.setDrawIcons(false);
        dataSet.setSliceSpace(1f);
        dataSet.setIconsOffset(new MPPointF(0, 40));
        dataSet.setSelectionShift(15f);
        dataSet.setDrawValues(false);
        // add a lot of colors

        ArrayList<Integer> colors = new ArrayList<>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.JOYFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.COLORFUL_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.LIBERTY_COLORS)
            colors.add(c);

        for (int c : ColorTemplate.PASTEL_COLORS)
            colors.add(c);

        colors.add(ColorTemplate.getHoloBlue());

        dataSet.setColors(colors);
        PieData data = new PieData(dataSet);
        pieChart.setData(data);
        // undo all highlights
        pieChart.setDrawEntryLabels(false);
        pieChart.setDrawRoundedSlices(true);
        pieChart.invalidate();
    }


    @Override
    public void onValueSelected(Entry e, Highlight h) {
        Log.i("VAL SELECTED",
                "Value: " + e.getY() + ", index: " + h.getX()
                        + ", DataSet index: " + h.getDataSetIndex());
    }

    @Override
    public void onNothingSelected() {

    }


}
