package com.ysj.sc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.github.mikephil.charting.charts.LineChart;
import com.ysj.sc.R;
import com.ysj.sc.common.Category;
import com.ysj.sc.adapter.entity.CumulativeVO;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CumulativeListAdapter extends ArrayAdapter<CumulativeVO> {
    private static final String TAG = CumulativeListAdapter.class.getSimpleName();
    private Context context;
    private ArrayList<CumulativeVO> data; // activity가 전달하는 항목 집합 데이터..
    int resId; // activity가 전달하는 항목 layout xml 정보


    public CumulativeListAdapter(Context context, int resId, ArrayList<CumulativeVO> data) {
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);

            ViewHolder holder = new ViewHolder(convertView);
            convertView.setTag(holder); // setTag (key, value) 로 여러건 key 값으로 식별 가능

        }

        ViewHolder holder = (ViewHolder)convertView.getTag();

        ImageView caItemIconView = holder.caItemIconView;
        TextView caItemNameView = holder.caItemNameView;
        TextView caItemAmountView = holder.caItemAmountView;
        LineChart caItemChartView = holder.caItemChartView;


        final CumulativeVO vo = data.get(position);

        Category category = Category.valueOf(vo.getCategoryName());
        caItemIconView.setImageResource(category.getIcon());
        caItemNameView.setText(category.getName());
        caItemAmountView.setText(NumberFormat.getNumberInstance(Locale.KOREA).format(vo.getCategoryAmount()));
        setChartData(vo.getMonthlyData(), caItemChartView);
        Log.d(TAG, vo.getCategoryName());
        return convertView;
    }

    private void setChartData(ArrayList<Long> monthlyData, LineChart lineChart) {

    }

    public static class ViewHolder {
        ImageView caItemIconView;
        TextView caItemNameView;
        TextView caItemAmountView;
        LineChart caItemChartView;

        public ViewHolder(View root) {
            caItemIconView = root.findViewById(R.id.cc_item_icon);
            caItemNameView = root.findViewById(R.id.cc_item_name);
            caItemAmountView = root.findViewById(R.id.cc_item_amount);
            caItemChartView = root.findViewById(R.id.cc_item_chart);
        }
    }
}
