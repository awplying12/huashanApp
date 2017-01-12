package com.karazam.huashanapp.my.myreturn.main.view.view;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.utils.custom.FullyLinearLayoutManager;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.Bean.financialproject.FinancialInformation;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.ReturnRecords;


import java.util.ArrayList;


/**
 * Created by Administrator on 2016/12/5.
 */

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<ReturnRecords> list = new ArrayList<>();

    private RecyclerView rl;

    private FrameLayout fl;
    private ImageView open;

    public RecordsAdapter(Context context, ArrayList<ReturnRecords> list, RecyclerView rl) {
        this.context = context;
        this.list = list;
        this.rl = rl;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_records_item,null);
        view.setLayoutParams(new RelativeLayout.LayoutParams(rl.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        setItem(holder,position);



        if(holder.fl1 != null){
            holder.fl1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.fl2.getVisibility() != View.VISIBLE ){
                        holder.fl2.setVisibility(View.VISIBLE);
                        holder.open.setSelected(true);
                        if(fl != null && fl != holder.fl2 && open != null && open != holder.open){
                            fl.setVisibility(View.GONE);
                            open.setSelected(false);
                        }
                        fl = holder.fl2;
                        open = holder.open;


                    }else {
                        holder.fl2.setVisibility(View.GONE);
                        holder.open.setSelected(false);
                    }
                }
            });
        }


    }

    /**
     * 设置Item
     * @param holder
     * @param position
     */
    private void setItem(ViewHolder holder, int position) {

        ReturnRecords bean = list.get(position);

        String name = StringUtil.interrupt(bean.getTitle(),20,"未知");
        holder.name.setText(name);

        String amount = StringUtil.reservedDecimal(StringUtil.interrupt(bean.getAmount(),0,"0"),2);
        holder.amount.setText(StringUtil.interrupt(amount,12,"0.00"));


        RecordsItemAdapter itemAdapter = new RecordsItemAdapter(list.get(position).getRpList(),context);
        holder.records_rl.setAdapter(itemAdapter);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private FrameLayout fl1;
        private FrameLayout fl2;
        private ImageView open;
        private RecyclerView records_rl;


        private TextView name;
        private TextView amount;

        public ViewHolder(View itemView) {
            super(itemView);

            fl1 = (FrameLayout) itemView.findViewById(R.id.fl_1);
            fl2 = (FrameLayout) itemView.findViewById(R.id.fl_2);
            open = (ImageView) itemView.findViewById(R.id.open);

            name = (TextView) itemView.findViewById(R.id.name);
            amount = (TextView) itemView.findViewById(R.id.amount);

            records_rl = (RecyclerView) itemView.findViewById(R.id.records_rl);
            FullyLinearLayoutManager lm = new FullyLinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
            records_rl.setLayoutManager(lm);

        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){

                default:
                    break;
            }
        }
    }


    public interface OnItemClickListener{


    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}


