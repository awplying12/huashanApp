package com.karazam.huashanapp.manage.view.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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

public class TitleBarAdapter extends RecyclerView.Adapter<TitleBarAdapter.ViewHodel> {

    private ArrayList<String> list = new ArrayList<>();
    private Context context;
    private int selectPosition = 0;

    public TitleBarAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHodel onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_titlebar_item,parent,false);
        view.setLayoutParams(new LinearLayout.LayoutParams((int)(BaseActivity.ScreeW*0.25),LinearLayout.LayoutParams.MATCH_PARENT));

        ViewHodel viewHodel = new ViewHodel(view,mOnItemClickListener);
        return viewHodel;
    }

    @Override
    public void onBindViewHolder(ViewHodel holder, int position) {

        String str = list.get(position);
        holder.content.setText(StringUtil.interrupt(str,4,""));

            if(position == selectPosition){
               holder.hint.setBackgroundColor(Color.parseColor("#0894EC"));
            }else {

                holder.hint.setBackgroundColor(context.getResources().getColor(R.color.manage_titlebar_bg_color));
            }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHodel extends RecyclerView.ViewHolder implements View.OnClickListener{

        public onItemClickListener listener;

        TextView content;
        ImageView hint;

        public ViewHodel(View itemView,onItemClickListener onItemClickListener) {
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

    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    private static onItemClickListener mOnItemClickListener;

    public static void setmOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }
}
