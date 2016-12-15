package com.karazam.huashanapp.my.myreturn.main.view.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.Bean.financialproject.ReturnRecords;
import com.karazam.huashanapp.main.Bean.financialproject.ReturnRecordsItem;

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

        holder.periods.setText((position+1)+"/"+list.size());

        if(position == (list.size()-1)){
            holder.periods.setTextColor(Color.parseColor("#ffffff"));
            holder.sum.setTextColor(Color.parseColor("#ffffff"));
            holder.date.setTextColor(Color.parseColor("#ffffff"));
            holder.state.setTextColor(Color.parseColor("#ffffff"));
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView periods;
        private TextView sum;
        private TextView date;
        private TextView state;
        public ViewHolder(View itemView) {
            super(itemView);

            periods = (TextView) itemView.findViewById(R.id.periods_tv);
            sum = (TextView) itemView.findViewById(R.id.sum_tv);
            date = (TextView) itemView.findViewById(R.id.date_tv);
            state = (TextView) itemView.findViewById(R.id.state_tv);

        }
    }
}
