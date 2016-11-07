package com.karazam.huashanapp.manage.view.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.manage.model.databinding.Project;
import com.ogaclejapan.rx.binding.RxView;

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
    public void onBindViewHolder(ContentAdapter.ViewHolder holder, final int position) {

//        R
//        RxView.of(holder.buy_now).bind()

        holder.buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"立即购买  "+position,Toast.LENGTH_SHORT).show();
            }
        });

        int status = mData.get(position).getStatus();
        switch (status){
            case 0:
                holder.buy_now.setText("立即购买");
                holder.buy_now.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
                holder.buy_now.setClickable(true);
                break;
            case 1:
                holder.buy_now.setText("还款中");
                holder.buy_now.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                holder.buy_now.setClickable(false);
                break;
            default:
                break;
        }



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
        private TextView buy_now;

        public ViewHolder(View itemView,onItemClickListener onItemClickListener) {
            super(itemView);
            this.listener = onItemClickListener;
            itemView.setOnClickListener(this);
//            Annual_Income = (TextView) itemView.findViewById(R.id.tv_1_2);
//            Project_Duration = (TextView) itemView.findViewById(R.id.tv_2_2);
//            Project_Scale = (TextView) itemView.findViewById(R.id.tv_3_2);
            buy_now = (TextView) itemView.findViewById(R.id.buy_now_item);

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
