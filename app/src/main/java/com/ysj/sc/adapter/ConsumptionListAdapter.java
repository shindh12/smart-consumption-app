package com.ysj.sc.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ysj.sc.R;
import com.ysj.sc.common.Category;
import com.ysj.sc.mvvm.db.entity.Statistics;
import com.ysj.sc.layout.view.UsedBarView;
import com.ysj.sc.util.NumberUtil;

import java.util.ArrayList;
import java.util.List;

public class ConsumptionListAdapter extends ArrayAdapter<Statistics> {
    private static final String TAG = ConsumptionListAdapter.class.getSimpleName();
    private static final int EMPTY_VIEW = 10;

    private Context context;
    private List<Statistics> data; // activity가 전달하는 항목 집합 데이터..
    int resId; // activity가 전달하는 항목 layout xml 정보


    public ConsumptionListAdapter(Context context, int resId, List<Statistics> data) {
        super(context, resId);
        this.context = context;
        this.resId = resId;
        this.data = data;
    }

    // 항목 갯수를 판단하기 위해서 자동 호출
    @Override
    public int getCount() {
        return data.size() > 0 ? data.size() : 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (data.size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }


    public void setData(List<Statistics> data) {
        Log.d("####", "SET!! " + data.size());
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);

            ViewHolder holder = new ViewHolder(convertView);
            // 모든 view에는 개발자 임의 데이터(non-visible) 저장 가능
            // 그 view가 메모리에 지속만 된다면 언제든지 획득 가능
            convertView.setTag(holder); // setTag (key, value) 로 여러건 key 값으로 식별 가능

        }
        ViewHolder holder = (ViewHolder)convertView.getTag();
        Log.d("####", holder.cardNameView.toString());

        TextView categoryAmountView = holder.categoryAmountView;
        TextView maxAmountView = holder.maxAmountView;
        TextView cardNameView = holder.cardNameView;
        UsedBarView usedBarView = holder.usedBarView;

        final Statistics vo = data.get(position);
        long categoryAmount = vo.getCategoryAmount();
        long maxAmount = vo.getMaxAmount();
        usedBarView.setRatio(NumberUtil.getRatio(categoryAmount, maxAmount));
        categoryAmountView.setText(NumberUtil.getCurrencyFormat(vo.getCategoryAmount()));
        maxAmountView.setText(NumberUtil.getCurrencyFormat(vo.getMaxAmount()));
        cardNameView.setText(Category.valueOf(vo.getCategory()).getName());
        // TODO: 2020-03-15 나중에 눌렀을 때 세부사항으로 ㄱ

        return convertView;
    }

    public static class ViewHolder {
        TextView categoryAmountView;
        TextView maxAmountView;
        TextView cardNameView;
        UsedBarView usedBarView;

        public ViewHolder(View root) {
            categoryAmountView = root.findViewById(R.id.category_amount);
            maxAmountView = root.findViewById(R.id.max_amount);
            cardNameView = root.findViewById(R.id.card_name_text);
            usedBarView = root.findViewById(R.id.used_bar_view);
        }
    }
}
