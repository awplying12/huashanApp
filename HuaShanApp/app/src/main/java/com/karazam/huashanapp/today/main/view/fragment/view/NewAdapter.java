package com.karazam.huashanapp.today.main.view.fragment.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.karazam.huashanapp.R;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/7.
 */

public class NewAdapter extends RecyclerView.Adapter<NewAdapter.ViewHolder> {

    private Context context;
    private ArrayList<NewItem> items = new ArrayList<>();

    public NewAdapter(Context context, ArrayList<NewItem> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_new_item,parent,false);
        ViewHolder holder = new ViewHolder(view,mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private onItemClickListener listener;
        public ViewHolder(View itemView,onItemClickListener listener) {
            super(itemView);
            this.listener = listener;
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
