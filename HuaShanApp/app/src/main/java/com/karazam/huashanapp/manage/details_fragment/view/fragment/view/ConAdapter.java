package com.karazam.huashanapp.manage.details_fragment.view.fragment.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.RecordsItem;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/11/11.
 */

public class ConAdapter extends RecyclerView.Adapter<ConAdapter.ViewHolder> {

    public static final int TYPE_HEADER = 0;
    public static final int TYPE_NORMAL = 1;

    ArrayList<RecordsItem> list = new ArrayList<>();
    private Context context;

    private View mHeaderView;

    public ConAdapter(ArrayList<RecordsItem> list, Context context) {
        this.list = list;
        this.context = context;
    }

    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        notifyItemInserted(0);
    }

    @Override
    public int getItemViewType(int position) {
        if(mHeaderView == null) return TYPE_NORMAL;
        if(position == 0) return TYPE_HEADER;
        return TYPE_NORMAL;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if(mHeaderView != null && viewType == TYPE_HEADER){
            mHeaderView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            return new ViewHolder(mHeaderView);
        }
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_det_con_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if(getItemViewType(position) == TYPE_HEADER)
            return;

        final int pos = getRealPosition(holder);

        String name = list.get(pos).getInvestor();
        holder.name.setText(StringUtil.interrupt(name,0,""));

        String time = list.get(pos).getBuyTime();
        holder.time.setText(StringUtil.interrupt(time,0,""));

        String mon = list.get(pos).getAmount();
        Log.i("mon",mon);
        holder.mon.setText(StringUtil.interrupt(mon,0,""));

        String model = list.get(pos).getOperationMethodStr();
        holder.model.setText(StringUtil.interrupt(model,0,"")+"购买");


    }

    public int getRealPosition(RecyclerView.ViewHolder holder) {
        int position = holder.getLayoutPosition();
        return mHeaderView == null ? position : position - 1;
    }

    @Override
    public int getItemCount() {
        return mHeaderView == null ? list.size() : list.size() + 1;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView name;
        private TextView time;
        private TextView mon;
        private TextView model;

        public ViewHolder(View itemView) {

            super(itemView);
            if(itemView == mHeaderView)
                return;

            name = (TextView) itemView.findViewById(R.id.det_user);
            time = (TextView) itemView.findViewById(R.id.det_time);
            mon = (TextView) itemView.findViewById(R.id.det_mon);
            model = (TextView) itemView.findViewById(R.id.det_mod);

        }
    }

    public ArrayList<RecordsItem> getList() {
        return list;
    }

    public void setList(ArrayList<RecordsItem> list) {
        this.list = list;
    }
}
