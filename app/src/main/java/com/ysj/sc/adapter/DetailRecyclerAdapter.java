package com.ysj.sc.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ysj.sc.R;
import com.ysj.sc.mvvm.db.entity.Consumption;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

public class DetailRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final String TAG = DetailRecyclerAdapter.class.getSimpleName();
    private static final int EMPTY_VIEW = 10;

    private List<Consumption> data;

    public DetailRecyclerAdapter(List<Consumption> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;

        if (viewType == EMPTY_VIEW) {
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empty, parent, false);
            return new DetailRecyclerAdapter.EmptyViewHolder(v);
        }

        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_list, parent, false);
        return new DetailRecyclerAdapter.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof ViewHolder) {
            Consumption vo = data.get(position);
            ((ViewHolder)holder).date.setText(vo.getDate());
            ((ViewHolder)holder).store.setText(vo.getStore());
            ((ViewHolder)holder).price.setText(NumberFormat.getCurrencyInstance(Locale.KOREA).format(vo.getUsedAmount()));
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

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView date;
        TextView store;
        TextView price;

        public ViewHolder(@NonNull View root) {
            super(root);
            date = (TextView) root.findViewById(R.id.detail_list_date);
            store = (TextView) root.findViewById(R.id.detail_list_store);
            price = (TextView) root.findViewById(R.id.detail_list_price);
        }
    }


    public class EmptyViewHolder extends RecyclerView.ViewHolder {
        public EmptyViewHolder(View itemView) {
            super(itemView);
        }
    }
}
