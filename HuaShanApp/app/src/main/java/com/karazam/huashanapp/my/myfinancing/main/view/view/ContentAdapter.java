package com.karazam.huashanapp.my.myfinancing.main.view.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.financialproject.FinancialInformation;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/12/5.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FinancialInformation> list = new ArrayList<>();

    private FrameLayout fl;

    public ContentAdapter(Context context, ArrayList<FinancialInformation> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_finance_item,null);
        Log.i("msg","1");
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        Log.i("msg","2");
        if(holder.fl1 != null){
            holder.fl1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(holder.fl2.getVisibility() != View.VISIBLE ){
                        holder.fl2.setVisibility(View.VISIBLE);
                        if(fl != null && fl != holder.fl2){
                            fl.setVisibility(View.GONE);
                        }
                        fl = holder.fl2;


                    }else {
                        holder.fl2.setVisibility(View.GONE);
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private FrameLayout fl1;
        private FrameLayout fl2;
        private TextView text2;

        public ViewHolder(View itemView) {
            super(itemView);

            fl1 = (FrameLayout) itemView.findViewById(R.id.fl_1);
            fl2 = (FrameLayout) itemView.findViewById(R.id.fl_2);
            text2 = (TextView) itemView.findViewById(R.id.text2);
        }
    }
}

