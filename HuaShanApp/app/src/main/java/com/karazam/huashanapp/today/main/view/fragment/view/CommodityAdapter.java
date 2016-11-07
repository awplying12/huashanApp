package com.karazam.huashanapp.today.main.view.fragment.view;

import android.graphics.Color;
import android.support.percent.PercentFrameLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.manage.view.view.TitleBarAdapter;

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

        ViewHolder holder = new ViewHolder(view,mOnItemClickListener);
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
        holder.name.setText(StringUtil.interrupt(title,0,""));
        holder.name.setTextColor(Color.parseColor("#ffffff"));


        String content = items.get(position).getContent();
        holder.content.setText(StringUtil.interrupt(content,0,""));
        holder.content.setTextColor(Color.parseColor("#ffffff"));

        String color = items.get(position).getColor();
        holder.pl.setBackgroundColor(Color.parseColor(StringUtil.interrupt(color,0,"#ffffff")));
//        holder.pl.setBackgroundColor(Color.parseColor("#FFFFFF"));

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private PercentFrameLayout pl;
        private TextView name;
        private TextView content;
        private onItemClickListener listener;
        public ViewHolder(View itemView,onItemClickListener listener) {
            super(itemView);
            this.listener = listener;
            itemView.setOnClickListener(this);
            pl = (PercentFrameLayout) itemView.findViewById(R.id.item_pl);
            name = (TextView) itemView.findViewById(R.id.tv_item_name);
            content = (TextView) itemView.findViewById(R.id.tv_item_content);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view,getPosition());
        }
    }

    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    private  onItemClickListener mOnItemClickListener;

    public  void setmOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }

}
