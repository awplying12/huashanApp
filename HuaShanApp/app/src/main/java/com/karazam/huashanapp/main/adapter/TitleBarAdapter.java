package com.karazam.huashanapp.main.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/17.
 */

public class TitleBarAdapter extends RecyclerView.Adapter<TitleBarAdapter.ViewHolder> {

    private ArrayList<String> list = new ArrayList<>();
    private Context context;
    private int selectPosition = 0;
    private int size = 15;

    public TitleBarAdapter(ArrayList<String> list, Context context,int size) {
        this.list = list;
        this.context = context;
        this.size = size;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_titlebar_item,parent,false);
        int size = list.size();
        if(size >=4){
            view.setLayoutParams(new LinearLayout.LayoutParams((int)(BaseActivity.ScreeW*0.25),LinearLayout.LayoutParams.MATCH_PARENT));
        }else {
            view.setLayoutParams(new LinearLayout.LayoutParams((int)(BaseActivity.ScreeW/size),LinearLayout.LayoutParams.MATCH_PARENT));
        }

        ViewHolder viewHodel = new ViewHolder(view,mOnItemClickListener);
        return viewHodel;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String str = list.get(position);
        holder.content.setText(StringUtil.interrupt(str,4,""));
        holder.content.setTextSize(TypedValue.COMPLEX_UNIT_DIP,size);

            if(position == selectPosition){
               holder.hint.setBackgroundColor(Color.parseColor("#0894EC"));
                holder.content.setTextColor(Color.parseColor("#0894EC"));
            }else {

                holder.hint.setBackgroundColor(context.getResources().getColor(R.color.manage_titlebar_bg_color));
                holder.content.setTextColor(Color.parseColor("#4f4f4f"));
            }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public onItemClickListener listener;

        TextView content;
        ImageView hint;

        public ViewHolder(View itemView,onItemClickListener onItemClickListener) {
            super(itemView);
            this.listener = onItemClickListener;
            content = (TextView) itemView.findViewById(R.id.content_tv);
            hint = (ImageView) itemView.findViewById(R.id.hint_img);
            itemView.setOnClickListener(this);

        }


        @Override
        public void onClick(View view) {
            notifyDataSetChanged();

            selectPosition = getPosition();

            if(listener == null){
                return;
            }
            listener.onItemClick(view,selectPosition);
        }
    }

    public void setCurrentItem(int posation){
        notifyDataSetChanged();

        selectPosition = posation;

    }

    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    private static onItemClickListener mOnItemClickListener;

    public static void setmOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }
}
