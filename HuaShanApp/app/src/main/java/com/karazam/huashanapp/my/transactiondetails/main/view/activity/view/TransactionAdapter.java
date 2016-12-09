package com.karazam.huashanapp.my.transactiondetails.main.view.activity.view;

import android.content.Context;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.stickylistview_library.StickyListHeadersAdapter;
import com.example.utils.base.BaseBaseAdapter;
import com.example.utils.utils.DataUtil;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;

/**
 * Created by Administrator on 2016/12/9.
 */

public class TransactionAdapter extends BaseBaseAdapter<TransactionBean> implements StickyListHeadersAdapter {


    public TransactionAdapter(Context context, ArrayList<TransactionBean> list) {
        super(context, list);
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {

        HeaderViewHolder headerViewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_transaction_header_item,null);
            headerViewHolder = new HeaderViewHolder(convertView);
            convertView.setTag(headerViewHolder);
        }else {
            headerViewHolder = (HeaderViewHolder) convertView.getTag();
        }

        String month = DataUtil.getDate(new Date(getList().get(position).getData()),"yyyy年M月");
        headerViewHolder.month.setText(StringUtil.interrupt(month,0,""));
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {

        Time time = new Time("GMT+8");
        time.set(getList().get(position).getData());

        return time.month;
    }

    @Override
    public View getViewBase(int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_transaction_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }


            holder.data1.setText(getList().get(position).getWeekDay());


        return convertView;
    }

    public class ViewHolder {

        private View itemView;
        private TextView txt;
        private TextView data1,data2;


        public ViewHolder(View itemView) {
            this.itemView = itemView;

            data1 = (TextView) itemView.findViewById(R.id.data_tv_1);
            data2 = (TextView) itemView.findViewById(R.id.data_tv_2);
        }
    }

    public class HeaderViewHolder {

        private View itemView;
        private TextView month;

        public HeaderViewHolder(View itemView) {
            this.itemView = itemView;

            month = (TextView) itemView.findViewById(R.id.month);
        }
    }
}
