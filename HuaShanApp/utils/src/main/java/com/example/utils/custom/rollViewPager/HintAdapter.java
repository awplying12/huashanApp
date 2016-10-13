package com.example.utils.custom.rollViewPager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.R;
import com.example.utils.custom.RoundCornerImageView;


/**
 * Created by Administrator on 2016/10/13.
 */

public class HintAdapter extends RecyclerView.Adapter<HintAdapter.ViewHolder> {

    private int size;
    private int[] ishint;
    private Context context;

    public HintAdapter(int size, Context context) {
        this.size = size;
        this.context = context;

        ishint = new int[size];
        init();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_recyclerview_hint_item,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        switch (ishint[position]){
            case 0:
//                holder.imageView.setBackgroundColor(context.getResources().getColor(R.color.homeactivity_textcolor_selected));
                break;
            case 1:
//                holder.imageView.setBackgroundColor(context.getResources().getColor(R.color.homeactivity_textcolor_default));
                break;
            default:
                break;
        }

    }

    @Override
    public int getItemCount() {
        return size;
    }

    public void w(int position){
        init();
        ishint[position] = 1;
        notifyDataSetChanged();

    }

    private void init(){
        for(int i = 0 ; i < size ; i++){
            ishint[i] = 0;
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        RoundCornerImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (RoundCornerImageView) itemView.findViewById(R.id.hint);
            imageView.setAngie(360,360);
        }

    }
}
