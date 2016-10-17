package com.handmark.pulltorefresh.library.slidelistview;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;
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
 * Created by 010 on 2015/7/17.
 */
public abstract class SlideListAdapter<DataType,BaseViewHolder> extends BaseAdapter {

    private Context context;
    private ArrayList<DataType> list = new ArrayList<>();
    private OnSlideClickListener slideClickListener;


    public SlideListAdapter(Context context, ArrayList<DataType> list, OnSlideClickListener slideClickListener) {
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
        final ViewHolder viewHolder;
        final BaseViewHolder baseViewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.slide_list_view_item, null);
            baseViewHolder = onCreateViewHolder();
            viewHolder = (ViewHolder) baseViewHolder;

            viewHolder.content_layout = (LinearLayout) convertView.findViewById(R.id.content_layout);

            viewHolder.content_layout.addView(viewHolder.view);

            viewHolder.thirdLayout = (LinearLayout) convertView.findViewById(R.id.slide_third_layout);

//            viewHolder.thirdLayout.setLayoutParams(new LinearLayout.LayoutParams(300,setContentViewHigh()));

            viewHolder.headerView = (ImageView) convertView.findViewById(R.id.header_img);
            viewHolder.headerView.setLayoutParams(new LinearLayout.LayoutParams(300,setHeaderViewHigh()));

            viewHolder.bottomView = (ImageView) convertView.findViewById(R.id.bottom_img);
            viewHolder.bottomView.setLayoutParams(new LinearLayout.LayoutParams(300,setBottomViewHigh()));

            convertView.setTag(baseViewHolder);
        } else {
            baseViewHolder = (BaseViewHolder) convertView.getTag();
            viewHolder = (ViewHolder) baseViewHolder;
        }



            final View item = convertView;
            final int pos = position;
            onBindViewHolder(baseViewHolder,position);


        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
              final   int hi =viewHolder.content_layout.getHeight();
                Activity activity = (Activity) getContext();
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        viewHolder.thirdLayout.setLayoutParams(new LinearLayout.LayoutParams(300,hi-setHeaderViewHigh()-setBottomViewHigh()));
                    }
                });

            }
        };
        timer.schedule(task,100);


            viewHolder.thirdLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    slideClickListener.onDeleteClick(pos, item);
                }
            });


        return convertView;

    }



    public abstract int setHeaderViewHigh();

    public abstract int setBottomViewHigh();

    public abstract BaseViewHolder onCreateViewHolder();

    public abstract void onBindViewHolder(BaseViewHolder holder,int position);


    public class ViewHolder {

        private LinearLayout content_layout;

        private LinearLayout thirdLayout;

        private ImageView headerView;

        private ImageView bottomView;

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
