package com.karazam.huashanapp.manage.view.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.utils.base.BaseBaseAdapter;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.manage.model.databinding.Project;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/17.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private ArrayList<Project> mData;
    private final LayoutInflater mLayoutInflater;
    private Context mContext;

    public ContentAdapter(Context context, ArrayList<Project> data) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.layout_project_content_item, parent, false);
        ViewHolder holder = new ViewHolder(view,mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContentAdapter.ViewHolder holder, int position) {

        

    }



    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public onItemClickListener listener;
        TextView Annual_Income;
        TextView Project_Duration;
        TextView Project_Scale;

        public ViewHolder(View itemView,onItemClickListener onItemClickListener) {
            super(itemView);
            this.listener = onItemClickListener;
            itemView.setOnClickListener(this);
            Annual_Income = (TextView) itemView.findViewById(R.id.tv_1_2);
            Project_Duration = (TextView) itemView.findViewById(R.id.tv_2_2);
            Project_Scale = (TextView) itemView.findViewById(R.id.tv_3_2);

        }

        @Override
        public void onClick(View view) {
            if(listener == null){
                return;
            }
            listener.onItemClick(view,getPosition());
        }
    }

    //添加数据
    public void addItem(ArrayList<Project> newDatas) {
        //mTitles.add(position, data);
        //notifyItemInserted(position);
        newDatas.addAll(mData);
        mData.removeAll(mData);
        mData.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void addMoreItem(ArrayList<Project> newDatas) {
        mData.addAll(newDatas);
        notifyDataSetChanged();
    }

    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    private static onItemClickListener mOnItemClickListener;

    public static void setmOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }
}
