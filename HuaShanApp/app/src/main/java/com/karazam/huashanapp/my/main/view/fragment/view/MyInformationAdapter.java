package com.karazam.huashanapp.my.main.view.fragment.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/19.
 */

public class MyInformationAdapter extends RecyclerView.Adapter<MyInformationAdapter.ViewHolder> {

    private ArrayList<MyInformationModel> models = new ArrayList<>();
    private Context context;
    private RecyclerView rView;

    public MyInformationAdapter(ArrayList<MyInformationModel> models, Context context,RecyclerView rView) {
        this.models = models;
        this.context = context;
        this.rView = rView;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_my_information_item,parent,false);
        int w = rView.getWidth()/3;
        int h = rView.getHeight()/(models.size()/3);
        view.setLayoutParams(new RelativeLayout.LayoutParams(w,h));
        ViewHolder holder = new ViewHolder(view,mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        int id = models.get(position).getIcon_id();
        if(id == -1){
            holder.icon_img.setVisibility(View.GONE);
        }else if(id != 0){
            holder.icon_img.setBackgroundResource(id);
        }






        String name = models.get(position).getName();
        if(name.equals("-1")){
            holder.name.setVisibility(View.GONE);
            return;
        }
        holder.name.setText(StringUtil.interrupt(name,0,""));


    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView icon_img;
        TextView name;

        onItemClickListener listener;

        public ViewHolder(View itemView,onItemClickListener onItemClickListener) {
            super(itemView);
            this.listener = onItemClickListener;
            itemView.setOnClickListener(this);
            this.icon_img = (ImageView) itemView.findViewById(R.id.icon_img);
            this.name = (TextView) itemView.findViewById(R.id.name);
        }

        @Override
        public void onClick(View view) {
            listener.onItemClick(view,getPosition());
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
