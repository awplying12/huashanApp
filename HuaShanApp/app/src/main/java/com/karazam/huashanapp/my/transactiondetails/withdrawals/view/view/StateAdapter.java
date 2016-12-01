package com.karazam.huashanapp.my.transactiondetails.withdrawals.view.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/1.
 */

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    private ArrayList<StateitemBean> list = new ArrayList<>();
    private Context context;
    private RecyclerView recyclerView;

    public StateAdapter(ArrayList<StateitemBean> list, Context context, RecyclerView recyclerView) {
        this.list = list;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_state_item,null);
        FrameLayout.LayoutParams lm = new FrameLayout.LayoutParams(recyclerView.getWidth(),(recyclerView.getHeight()/4));
        view.setLayoutParams(lm);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        String state = StringUtil.interrupt(list.get(position).getState(),0,"未知");


        String time = StringUtil.interrupt(list.get(position).getTime(),0,"");
        holder.time.setText(time);

        if(time.equals("")){
            holder.part1.setSelected(false);
            holder.part2.setVisibility(View.GONE);
            holder.state.setText(Html.fromHtml("<font color='#adadad'>"+state+"</font>"));
        }else {
            holder.part1.setSelected(true);
            holder.part2.setVisibility(View.VISIBLE);
            holder.state.setText(state);
        }

        if(position == list.size()-1){
            holder.part2.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView part1;
        private ImageView part2;

        private TextView state;
        private TextView time;
        public ViewHolder(View itemView) {
            super(itemView);

            part1 = (ImageView) itemView.findViewById(R.id.img_part1);
            part2 = (ImageView) itemView.findViewById(R.id.img_part2);

            state = (TextView) itemView.findViewById(R.id.tv_state);
            time = (TextView) itemView.findViewById(R.id.tv_time);
        }
    }

    public ArrayList<StateitemBean> getList() {
        return list;
    }

    public void setList(ArrayList<StateitemBean> list) {
        this.list = list;
    }
}
