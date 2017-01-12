package com.karazam.huashanapp.my.myfinancing.main.view.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.Bean.financialproject.FinancialInformation;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.InvestingBean;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/12/5.
 */

public class BidingAdapter extends RecyclerView.Adapter<BidingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<InvestingBean> list = new ArrayList<>();

    private RecyclerView rl;

    private FrameLayout fl;
    private ImageView open;

    public BidingAdapter(Context context, ArrayList<InvestingBean> list, RecyclerView rl) {
        this.context = context;
        this.list = list;
        this.rl = rl;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_biding_item,null);
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
    private void setItem(final ViewHolder holder, int position) {

        InvestingBean bean = list.get(position);

        String name = StringUtil.interrupt(bean.getTitle(),20,"未知");
        holder.name.setText(name);

        String amount = StringUtil.reservedDecimal(StringUtil.interrupt(bean.getAmount(),0,"0"),2);
        holder.amount.setText(StringUtil.interrupt(amount,12,"0.00"));

        String time = StringUtil.interrupt(bean.getBuyTime(),16,"未知");
        holder.time.setText(time);

        String term = StringUtil.interrupt(bean.getPeriod(),0,"未知");
        holder.term.setText(term);

        String interest_rate = StringUtil.interrupt(bean.getInterestRate(),0,"0.0%");
        holder.interest_rate.setText(interest_rate);

        String repayment_method = StringUtil.interrupt(bean.getRepaymentMethod(),18,"未知");
        holder.repayment_method.setText(repayment_method);

        String progress = StringUtil.interrupt(bean.getProgress(),0,"0");
        float pro = Float.parseFloat(progress)*100;

        holder.progress.setText(StringUtil.reservedDecimal(pro+"",0)+"%");

        holder.rp_progress.setProgress(pro);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private FrameLayout fl1;
        private FrameLayout fl2;
        private ImageView open;


        private TextView name;
        private TextView amount;
        private TextView time;
        private TextView term;
        private TextView interest_rate;
        private TextView repayment_method;
        private TextView progress;

        private RoundCornerProgressBar rp_progress;


        public ViewHolder(View itemView) {
            super(itemView);

            fl1 = (FrameLayout) itemView.findViewById(R.id.fl_1);
            fl2 = (FrameLayout) itemView.findViewById(R.id.fl_2);
            open = (ImageView) itemView.findViewById(R.id.open);

            name = (TextView) itemView.findViewById(R.id.name);
            amount = (TextView) itemView.findViewById(R.id.amount);
            time = (TextView) itemView.findViewById(R.id.time);
            term = (TextView) itemView.findViewById(R.id.term);
            interest_rate = (TextView) itemView.findViewById(R.id.interest_rate);
            repayment_method = (TextView) itemView.findViewById(R.id.repayment_method);
            progress = (TextView) itemView.findViewById(R.id.progress);

            rp_progress = (RoundCornerProgressBar) itemView.findViewById(R.id.rp_progress);


        }
    }
}

