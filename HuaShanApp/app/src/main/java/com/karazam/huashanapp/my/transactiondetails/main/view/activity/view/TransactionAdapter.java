package com.karazam.huashanapp.my.transactiondetails.main.view.activity.view;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.stickylistview_library.StickyListHeadersAdapter;
import com.example.utils.base.BaseBaseAdapter;
import com.example.utils.utils.DataUtil;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;



/**
 * Created by Administrator on 2016/12/9.
 */

public class TransactionAdapter extends BaseBaseAdapter<TransactionItem> implements StickyListHeadersAdapter {

    private Activity activity;
    public TransactionAdapter(Context context, ArrayList<TransactionItem> list,Activity activity) {
        super(context, list);
        this.activity = activity;
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

        String month = DataUtil.getDate(new Date(getList().get(position).getCreateDate()),"yyyy年M月");
        headerViewHolder.month.setText(StringUtil.interrupt(month,0,""));
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {

        Time time = new Time("GMT+8");
        time.set(getList().get(position).getCreateDate());

        return time.month;
    }

    @Override
    public View getViewBase(final int position, View convertView, ViewGroup parent) {

        ViewHolder holder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.layout_transaction_item,null);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }

            String week = getList().get(position).getWeekDay();
            holder.data1.setText(StringUtil.interrupt(week,0,"未知"));

            SimpleDateFormat sdf = new SimpleDateFormat("MM-dd");
            Date date = new Date(getList().get(position).getCreateDate());
            String data2 = StringUtil.interrupt(sdf.format(date),0,"未知");
            holder.data2.setText(data2);

            String amount = StringUtil.reservedDecimal(StringUtil.interrupt(getList().get(position).getAmount(),0,"0"),2);
            holder.amount.setText(amount);

            String momo = StringUtil.interrupt(getList().get(position).getMemo(),0,"未知");
            holder.momo.setText(momo);

            TransactionItem item = getList().get(position);
            String orderId = item.getOrderId();
            String type = item.getType();

            if (type.equals("investment")){ //投资
                holder. goto_icon.setVisibility(View.VISIBLE);
            } else if(type.equals("withdrawal")){ //提现
                holder. goto_icon.setVisibility(View.VISIBLE);
            } else if(type.equals("recharge")){ //充值
                holder. goto_icon.setVisibility(View.VISIBLE);
            } else if(type.equals("repayment")){ //回款
                holder. goto_icon.setVisibility(View.VISIBLE);
            } else {
                holder. goto_icon.setVisibility(View.VISIBLE);
            }


        return convertView;
    }

    public class ViewHolder {

        private View itemView;
        private TextView data1,data2
                        ,amount,momo;

        private ImageView goto_icon;

        public ViewHolder(View itemView) {
            this.itemView = itemView;

            data1 = (TextView) itemView.findViewById(R.id.data_tv_1);
            data2 = (TextView) itemView.findViewById(R.id.data_tv_2);

            amount = (TextView) itemView.findViewById(R.id.amount);
            momo = (TextView) itemView.findViewById(R.id.momo);

            goto_icon = (ImageView) itemView.findViewById(R.id.goto_icon);


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
