package com.ysj.sc.layout.fragment;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.ysj.sc.R;
import com.ysj.sc.databinding.FragmentAnalysisBinding;

import java.util.ArrayList;

public class AnalysisFragment extends Fragment {
    FragmentAnalysisBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_analysis, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentAnalysisBinding.bind(getView());
        setChart();
    }

    private void setChart() {

        binding.chartMonthly.getDescription().setEnabled(false);
        binding.chartMonthly.setBackgroundColor(ContextCompat.getColor(getContext(), R.color.ac_chart_card_background));
        binding.chartMonthly.setNoDataTextColor(ContextCompat.getColor(getContext(), R.color.ac_chart_text_color));
        binding.chartMonthly.setDrawGridBackground(false);
        binding.chartMonthly.setDrawBarShadow(false);
        binding.chartMonthly.setHighlightFullBarEnabled(false);
        binding.chartMonthly.setTouchEnabled(false);

        binding.chartMonthly.getLegend().setEnabled(false);
        binding.chartMonthly.getAxisRight().setEnabled(false);
        binding.chartMonthly.getDescription().setEnabled(false);
        binding.chartMonthly.getXAxis().setEnabled(false);

        YAxis leftAxis = binding.chartMonthly.getAxisLeft();
        leftAxis.setDrawGridLines(false);
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)
        leftAxis.setTextColor(ContextCompat.getColor(getContext(), R.color.ac_chart_text_color));

        CombinedData data = new CombinedData();

        data.setData(generateBarData());
        data.setData(generateLineData());
        binding.chartMonthly.setData(data);
        binding.chartMonthly.getXAxis().setAxisMinimum(0.5f);
        binding.chartMonthly.getXAxis().setAxisMaximum(12.5f);
        binding.chartMonthly.animateY(1000);
        binding.chartMonthly.invalidate();
    }



    private BarData generateBarData() {

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();

        for (int index = 1; index <= 12; index++) {
            if(index <= 4) {
                entries1.add(new BarEntry(index, index * 9));
                entries2.add(new BarEntry(index, index * 5));
            }else {
                entries1.add(new BarEntry(index, index * 6));
                entries2.add(new BarEntry(index, index * 3));
            }
        }
        float barWidth = 0.5f; // x2 dataset
        BarDataSet set1 = new BarDataSet(entries1, "Bar 1");
        set1.setColor(ContextCompat.getColor(getContext(), R.color.ac_chart_cash_color));
        set1.setBarBorderWidth(0.1f);
        set1.setBarBorderColor(ContextCompat.getColor(getContext(), R.color.ac_chart_card_background));

        BarDataSet set2 = new BarDataSet(entries2, "Bar 2");
        set2.setColor(ContextCompat.getColor(getContext(), R.color.ac_chart_credit_color));
        set2.setBarBorderColor(ContextCompat.getColor(getContext(), R.color.ac_chart_card_background));

        BarData d = new BarData(set1, set2);
        d.setDrawValues(false);
        d.setBarWidth(barWidth);

        return d;
    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<>();
        ArrayList<Entry> recom = new ArrayList<>();

        for (int index = 1; index <= 12; index++) {
            entries.add(new Entry(index, index * 5));
            if( index == 4) recom.add(new Entry(index, index * 5));
            if( index > 4) recom.add(new Entry(index, index * 3));
        }
        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        set.setColor(Color.rgb(240, 238, 70));
        set.setCircleColor(Color.rgb(240, 238, 70));
        set.setCircleRadius(1f);
        set.setFillColor(Color.rgb(240, 238, 70));
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(false);


        LineDataSet recomSet = new LineDataSet(recom, "Line DataSet");
        recomSet.setColor(Color.RED);
        recomSet.setCircleColor(Color.RED);
        recomSet.setCircleRadius(1f);
        recomSet.setFillColor(Color.RED);
        recomSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        recomSet.setDrawValues(false);


        d.addDataSet(set);
        d.addDataSet(recomSet);


        return d;
    }

}
