package com.handmark.pulltorefresh.library.slidelistview;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.handmark.pulltorefresh.library.R;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/5/12.
 */
public abstract class SlideCardListAdapter<DataType,BaseViewHolder> extends BaseAdapter {

    private Context context;
    private ArrayList<DataType> list;
    private OnSlideClickListener slideClickListener;



    public SlideCardListAdapter(Context context, ArrayList<DataType> list, OnSlideClickListener slideClickListener) {
        this.context = context;
        this.list = list;
        this.slideClickListener = slideClickListener;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final BaseViewHolder  baseViewHolder;
        final ViewHolder viewHolder;
//        BaseViewHolder baseViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.slide_card_list_view_item, null);
             baseViewHolder = onCreateViewHolder();
            viewHolder = (ViewHolder) baseViewHolder;

            viewHolder.content_layout = (LinearLayout) convertView.findViewById(R.id.content_layout);

            viewHolder.content_layout.addView(viewHolder.view);


            viewHolder.thirdLayout = (LinearLayout) convertView.findViewById(R.id.slide_third_layout);
            viewHolder.thirdLayout.setLayoutParams(new LinearLayout.LayoutParams(300,setContentViewHigh()));
            viewHolder.headerView = (ImageView) convertView.findViewById(R.id.header_img);
            viewHolder.headerView.setLayoutParams(new LinearLayout.LayoutParams(300,setHeaderViewHigh()));
            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
            viewHolder = (ViewHolder) baseViewHolder;
        }


        final View item = convertView;
        final int pos = position;
        onBindViewHolder(baseViewHolder,position);

        viewHolder.thirdLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                slideClickListener.onDeleteClick(pos, item);
            }
        });


        return convertView;

    }

    public abstract int setHeaderViewHigh();

    public abstract int setContentViewHigh();

    public abstract BaseViewHolder onCreateViewHolder();

    public abstract void onBindViewHolder(BaseViewHolder holder,int position);


    public class ViewHolder {

        private LinearLayout content_layout;

        private LinearLayout thirdLayout;

        private ImageView headerView;

        private View view;

        public ViewHolder(View itemView){
            this.view = itemView;
        }
    }

    public interface OnSlideClickListener {

        void onDeleteClick(int position, View item);

    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public ArrayList<DataType> getList() {
        return list;
    }

    public void setList(ArrayList<DataType> list) {
        this.list = list;
    }
}
