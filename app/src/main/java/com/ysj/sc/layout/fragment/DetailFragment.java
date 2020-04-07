package com.ysj.sc.layout.fragment;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.CombinedData;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.ysj.MainActivity;
import com.ysj.sc.R;
import com.ysj.sc.adapter.DetailRecyclerAdapter;
import com.ysj.sc.common.Category;
import com.ysj.sc.databinding.FragmentConsumptionDetailBinding;
import com.ysj.sc.mvvm.db.entity.Consumption;
import com.ysj.sc.mvvm.vm.DetailViewModel;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class DetailFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private static final String TAG = DetailFragment.class.getSimpleName();
    private static final String KEY = "category";
    private static final int PAGE_START = 1;
    private int itemCount = 0;
    private int currentPage = PAGE_START;
    private int totalPage = 10;

    FragmentConsumptionDetailBinding binding;

    private DetailViewModel viewModel;
    private Category category;

    public static DetailFragment newInstance(String category) {
        // TODO: 2020-03-31 나중에 safe args 방식으로 바꿔 
        DetailFragment f = new DetailFragment();
        Bundle args = new Bundle();
        args.putString(KEY, category);
        f.setArguments(args);
        return f; 
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if (args != null && args.containsKey(KEY)) {
            this.category = Category.valueOf(args.getString(KEY));
        }
        viewModel = new ViewModelProvider(this).get(DetailViewModel.class);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        return inflater.inflate(R.layout.fragment_consumption_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        binding = FragmentConsumptionDetailBinding.bind(getView());

        binding.tvTitle.setText(category.getName());
        binding.tvItemName.setText(category.getName());
        binding.tvItemAmount.setText(NumberFormat.getCurrencyInstance(Locale.KOREA).format(1000000));
        binding.ivItemName.setImageResource(category.getIcon());

        ((MainActivity) getActivity()).toggleVisibleBackButton(true);
        binding.layoutRefresh.setProgressBackgroundColorSchemeResource(R.color.app_background);
//        binding.layoutRefresh.setRefreshing(true);
//        binding.layoutRefresh.setOnRefreshListener(this);
        binding.listDetail.addItemDecoration(new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        setChart();
        setDetailList();
    }

    @Override
    public void onStop() {
        super.onStop();
        ((MainActivity) getActivity()).toggleVisibleBackButton(false);
    }

    private void setChart() {
        CombinedData data = new CombinedData();
        data.setData(generateBarData());
        data.setData(generateLineData());
        binding.chartDetail.getLegend().setEnabled(false);
        binding.chartDetail.getAxisLeft().setEnabled(false);
        binding.chartDetail.getAxisRight().setEnabled(false);
        binding.chartDetail.getXAxis().setEnabled(false);
        binding.chartDetail.getDescription().setEnabled(false);
        binding.chartDetail.setData(data);

        binding.chartDetail.invalidate();
    }

    private void setDetailList() {
        List<Consumption> data = viewModel.getDetailsByCategory(category.getCode());
        DetailRecyclerAdapter adapter = new DetailRecyclerAdapter(data);
        binding.listDetail.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.listDetail.setAdapter(adapter);
    }

    private LineData generateLineData() {

        LineData d = new LineData();

        ArrayList<Entry> entries = new ArrayList<>();

        for (int index = 1; index <= 12; index++) {
            if(index % 3 == 0) entries.add(new Entry(index, (index -1) * 2000 + 1000));
            else entries.add(new Entry(index, index * 2000));
        }

        LineDataSet set = new LineDataSet(entries, "Line DataSet");
        Drawable drawable = ContextCompat.getDrawable(getContext(), R.drawable.fade_accent);
        set.setFillDrawable(drawable);
        set.setColor(ContextCompat.getColor(getContext(), R.color.colorAccent));
        set.setDrawFilled(true);
        set.setLineWidth(1.5f);
        set.setDrawCircles(false);
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setDrawValues(false);
        d.addDataSet(set);

        return d;
    }

    private BarData generateBarData() {

        ArrayList<BarEntry> entries1 = new ArrayList<>();
        ArrayList<BarEntry> entries2 = new ArrayList<>();

        for (int index = 1; index <= 12; index++) {
            if (index % 3 ==0 ) entries1.add(new BarEntry(index, 1000));
            else entries1.add(new BarEntry(index, 2000));
        }

        BarDataSet set1 = new BarDataSet(entries1, "Bar 1");
        set1.setColor(ContextCompat.getColor(getContext(), R.color.colorPrimary));
        set1.setDrawValues(false);
        float barWidth = 0.45f; // x2 dataset
        // (0.45 + 0.02) * 2 + 0.06 = 1.00 -> interval per "group"

        BarData d = new BarData(set1);
        d.setBarWidth(barWidth);

        return d;
    }

    @Override
    public void onRefresh() {
        // 여기서 다음 paging 하는 로직 추가
    }
}
