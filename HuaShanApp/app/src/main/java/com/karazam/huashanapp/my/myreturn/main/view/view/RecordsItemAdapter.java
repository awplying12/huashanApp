package com.karazam.huashanapp.my.myreturn.main.view.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.Bean.financialproject.ReturnRecords;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.ReturnRecordsItem;


import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/8.
 */

public class RecordsItemAdapter extends RecyclerView.Adapter<RecordsItemAdapter.ViewHolder> {

    private ArrayList<ReturnRecordsItem> list = new ArrayList<>();
    private Context context;

    public RecordsItemAdapter(ArrayList<ReturnRecordsItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recordsitem_item,null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

//        holder.periods.setText((position+1)+"/"+list.size());

        setItem(holder,position);

        if(position == (list.size()-1)){
            holder.periods.setTextColor(Color.parseColor("#ffffff"));
            holder.amount.setTextColor(Color.parseColor("#ffffff"));
            holder.date.setTextColor(Color.parseColor("#ffffff"));
            holder.state.setTextColor(Color.parseColor("#ffffff"));
        }

    }

    /**
     * 设置Item
     * @param holder
     * @param position
     */
    private void setItem(ViewHolder holder, int position) {

        ReturnRecordsItem item = list.get(position);

        String period = StringUtil.interrupt(item.getPeriod(),0,"未知");
        holder.periods.setText(period);

        String amount = StringUtil.reservedDecimal(StringUtil.interrupt(item.getAmount(),0,"0"),2);
        holder.amount.setText("￥"+amount);

        String date = StringUtil.interrupt(item.getBuyTime(),0,"");
        holder.date.setText(date);

        String state = StringUtil.interrupt(item.getState(),4,"未知");
        holder.state.setText(state);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView periods;
        private TextView amount;
        private TextView date;
        private TextView state;
        public ViewHolder(View itemView) {
            super(itemView);

            periods = (TextView) itemView.findViewById(R.id.periods_tv);
            amount = (TextView) itemView.findViewById(R.id.sum_tv);
            date = (TextView) itemView.findViewById(R.id.date_tv);
            state = (TextView) itemView.findViewById(R.id.state_tv);

        }
    }
}
