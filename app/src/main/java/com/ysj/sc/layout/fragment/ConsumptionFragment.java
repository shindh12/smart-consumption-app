package com.ysj.sc.layout.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.ysj.sc.R;
import com.ysj.sc.adapter.ConsumptionRecyclerAdapter;
import com.ysj.sc.databinding.FragmentConsumptionBinding;
import com.ysj.sc.layout.custom.AmountYaxisValueFormatter;
import com.ysj.sc.listener.RecyclerViewClickListener;
import com.ysj.sc.mvvm.db.entity.Statistics;
import com.ysj.sc.mvvm.vm.ConsumptionViewModel;

import java.util.ArrayList;

public class ConsumptionFragment extends Fragment implements RecyclerViewClickListener {
    private static final String TAG = ConsumptionFragment.class.getSimpleName();

    FragmentConsumptionBinding binding;
    ConsumptionViewModel viewModel;

    private ArrayList<Statistics> categoryData = new ArrayList<>();


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentConsumptionBinding.bind(getView());
        viewModel = new ViewModelProvider(this).get(ConsumptionViewModel.class);
        setChart();
        setData();
        setCategoryData();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_consumption, container, false);
    }

    private void setChart() {

        binding.chartTotal.setDrawBarShadow(false);
        binding.chartTotal.setDrawValueAboveBar(true);

        binding.chartTotal.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the binding.ccTotalBarChart, no values will be
        // drawn
        binding.chartTotal.setMaxVisibleValueCount(60);

        // scaling can now only be done on x- and y-axis separately
        binding.chartTotal.setPinchZoom(false);

        binding.chartTotal.setDrawGridBackground(false);
        // binding.chartTotal.setDrawYLabels(false);

        XAxis xAxis = binding.chartTotal.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setDrawGridLines(false);
        xAxis.setGranularity(1f); // only intervals of 1 day
        xAxis.setLabelCount(6);

        YAxis leftAxis = binding.chartTotal.getAxisLeft();
        leftAxis.setLabelCount(3, false);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setValueFormatter(new AmountYaxisValueFormatter());
        leftAxis.setAxisMinimum(0f); // this replaces setStartAtZero(true)

        binding.chartTotal.getAxisRight().setEnabled(false);
        binding.chartTotal.getLegend().setEnabled(false);
    }

    private void setData() {

        ArrayList<BarEntry> entries = new ArrayList<>();

        for (int i = 1; i <= 12; i++) {
            entries.add(new BarEntry(i, i * 1000));
        }

        BarDataSet set;

        if (binding.chartTotal.getData() != null &&
                binding.chartTotal.getData().getDataSetCount() > 0) {
            set = (BarDataSet) binding.chartTotal.getData().getDataSetByIndex(0);
            set.setValues(entries);
            binding.chartTotal.getData().notifyDataChanged();
            binding.chartTotal.notifyDataSetChanged();
        } else {
            set = new BarDataSet(entries, "Sinus Function");
            set.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        }

        BarData data = new BarData(set);
        data.setValueTextSize(10f);
        data.setDrawValues(false);
        data.setBarWidth(0.5f);

        binding.chartTotal.setData(data);
    }

    private void setCategoryData() {
        Log.d("####", "!!setCategoryData()!!");
//        ConsumptionListAdapter adapter = new ConsumptionListAdapter(getContext(), R.layout.item_consumption_list, categoryData);
        ConsumptionRecyclerAdapter adapter = new ConsumptionRecyclerAdapter(this, categoryData);
        binding.listCategory.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.listCategory.setAdapter(adapter);
    }


    @Override
    public void onItemClickListener(View v, int position) {
        Statistics data = categoryData.get(position);
        FragmentManager fm = getActivity().getSupportFragmentManager();
        fm.beginTransaction()
                .setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left, R.anim.enter_from_left, R.anim.exit_to_right)
                .replace(R.id.nav_host_fragment, DetailFragment.newInstance(data.getCategory()))
                .addToBackStack(null)
                .commit();
    }
}
