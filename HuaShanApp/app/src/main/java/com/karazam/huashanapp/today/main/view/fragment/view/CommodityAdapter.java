package com.karazam.huashanapp.today.main.view.fragment.view;

import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/4.
 */

public class CommodityAdapter extends RecyclerView.Adapter<CommodityAdapter.ViewHolder> {

    private ArrayList<CommodityItem> items = new ArrayList<>();
    private RecyclerView rView;

    public CommodityAdapter(ArrayList<CommodityItem> items,RecyclerView rView) {
        this.items = items;
        this.rView = rView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_commodity_item,parent,false);
        int w = (int) (rView.getWidth()*0.4);
        int h = rView.getHeight();
        RecyclerView.LayoutParams rl = new RecyclerView.LayoutParams(w,h);

        view.setLayoutParams( rl);

        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int end = items.size()-1;
        int w = rView.getWidth();
        RecyclerView.LayoutParams rl = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();

        if(position == 0){
            rl.setMargins((int) (w*0.05),0,(int)(w*0.01),10);

        }else if(position == end){
            rl.setMargins((int)(w*0.01),0,(int)(w*0.05),10);
        }else {
            rl.setMargins((int)(w*0.01),0,(int)(w*0.01),10);
        }


        String title = items.get(position).getTitle();
        holder.tv.setText(StringUtil.interrupt(title,0,""));
//        holder.tv.setText(StringUtil.linefeed(title,5));

        String color = items.get(position).getColor();
        holder.tv.setBackgroundColor(Color.parseColor(StringUtil.interrupt(color,0,"#ffffff")));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.tv_item);
        }
    }

}
