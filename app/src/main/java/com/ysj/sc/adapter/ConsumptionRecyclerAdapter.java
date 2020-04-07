package com.ysj.sc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ysj.sc.R;
import com.ysj.sc.common.Category;
import com.ysj.sc.layout.view.UsedBarView;
import com.ysj.sc.listener.RecyclerViewClickListener;
import com.ysj.sc.mvvm.db.entity.Consumption;
import com.ysj.sc.mvvm.db.entity.Statistics;
import com.ysj.sc.util.NumberUtil;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class ConsumptionRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = ConsumptionRecyclerAdapter.class.getSimpleName();
    private static final int EMPTY_VIEW = 10;
    private static RecyclerViewClickListener listner;

    private List<Statistics> data;

    public ConsumptionRecyclerAdapter(RecyclerViewClickListener listner, List<Statistics> data) {
        this.listner = listner;
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;

        if (viewType == EMPTY_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty, parent, false);
            return new ConsumptionRecyclerAdapter.EmptyViewHolder(v);
        }

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_list, parent, false);
        return new ConsumptionRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            TextView categoryAmountView = ((ViewHolder)holder).categoryAmountView;
            TextView maxAmountView = ((ViewHolder)holder).maxAmountView;
            TextView cardNameView = ((ViewHolder)holder).cardNameView;
            UsedBarView usedBarView = ((ViewHolder)holder).usedBarView;

            final Statistics vo = data.get(position);
            long categoryAmount = vo.getCategoryAmount();
            long maxAmount = vo.getMaxAmount();
            usedBarView.setRatio(NumberUtil.getRatio(categoryAmount, maxAmount));
            categoryAmountView.setText(NumberUtil.getCurrencyFormat(vo.getCategoryAmount()));
            maxAmountView.setText(NumberUtil.getCurrencyFormat(vo.getMaxAmount()));
            cardNameView.setText(Category.valueOf(vo.getCategory()).getName());
        }
    }

    @Override
    public int getItemCount() {
        return data.size() > 0 ? data.size() : 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (data.size() == 0) {
            return EMPTY_VIEW;
        }
        return super.getItemViewType(position);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView categoryAmountView;
        TextView maxAmountView;
        TextView cardNameView;
        UsedBarView usedBarView;

        public ViewHolder(@NonNull View root) {
            super(root);
            categoryAmountView = (TextView) root.findViewById(R.id.category_amount);
            maxAmountView = (TextView)root.findViewById(R.id.max_amount);
            cardNameView = (TextView)root.findViewById(R.id.card_name_text);
            usedBarView = (UsedBarView) root.findViewById(R.id.used_bar_view);
        }

        @Override
        public void onClick(View view) {
            listner.onItemClickListener(view, this.getAdapterPosition());
        }
    }


    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
