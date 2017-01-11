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

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.Bean.financialproject.FinancialInformation;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.RepayingBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/5.
 */

public class HoldingAdapter extends RecyclerView.Adapter<HoldingAdapter.ViewHolder> {

    private Context context;
    private ArrayList<RepayingBean> list = new ArrayList<>();

    private RecyclerView rl;

    private FrameLayout fl;
    private ImageView open;

    public HoldingAdapter(Context context, ArrayList<RepayingBean> list, RecyclerView rl) {
        this.context = context;
        this.list = list;
        this.rl = rl;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_holding_item,null);
        view.setLayoutParams(new RelativeLayout.LayoutParams(rl.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {


        setItem(holder,position);

//        if(list.get(position).isState()){
//            holder.transfer_btn.setText("可转让");
//            holder.transfer_btn.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
//        }else {
//            holder.transfer_btn.setText("撤销转让");
//            holder.transfer_btn.setBackgroundResource(R.drawable.btn_bg_img_ff5722_5dp);
//        }



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

        RepayingBean bean = list.get(position);

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

        String dueTime = StringUtil.interrupt(bean.getDueTime(),0,"未知");
        holder.due_time.setText(dueTime);

        String unpaidAmount = StringUtil.reservedDecimal(StringUtil.interrupt(bean.getUnpaidAmount(),0,"0"),2);
        holder.unpaid_amount.setText(unpaidAmount);

        String paidAmount = StringUtil.reservedDecimal(StringUtil.interrupt(bean.getPaidAmount(),0,"0"),2);
        holder.paid_amount.setText(paidAmount);

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private FrameLayout fl1;
        private FrameLayout fl2;
        private ImageView open;

        private TextView check_btn;
        private TextView download_btn;
        private TextView transfer_btn;


        private TextView name;
        private TextView amount;
        private TextView time;
        private TextView term;
        private TextView interest_rate;
        private TextView repayment_method;
        private TextView due_time;
        private TextView unpaid_amount;
        private TextView paid_amount;


        public ViewHolder(View itemView) {
            super(itemView);

            fl1 = (FrameLayout) itemView.findViewById(R.id.fl_1);
            fl2 = (FrameLayout) itemView.findViewById(R.id.fl_2);
            open = (ImageView) itemView.findViewById(R.id.open);
            check_btn = (TextView) itemView.findViewById(R.id.check_btn);
            check_btn.setOnClickListener(this);
            download_btn = (TextView) itemView.findViewById(R.id.download_btn);
            download_btn.setOnClickListener(this);
            transfer_btn = (TextView) itemView.findViewById(R.id.transfer_btn);
            transfer_btn.setOnClickListener(this);

            name = (TextView) itemView.findViewById(R.id.name);
            amount = (TextView) itemView.findViewById(R.id.amount);
            time = (TextView) itemView.findViewById(R.id.time);
            term = (TextView) itemView.findViewById(R.id.term);
            interest_rate = (TextView) itemView.findViewById(R.id.interest_rate);
            repayment_method = (TextView) itemView.findViewById(R.id.repayment_method);
            due_time = (TextView) itemView.findViewById(R.id.due_time);
            unpaid_amount = (TextView) itemView.findViewById(R.id.unpaid_amount);
            paid_amount = (TextView) itemView.findViewById(R.id.paid_amount);


        }

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.check_btn:

                    mOnItemClickListener.onCheck(getPosition());
                    break;
                case R.id.download_btn:

                    mOnItemClickListener.onDownload(getPosition());
                    break;
                case R.id.transfer_btn:

                    mOnItemClickListener.onTransfer(getPosition());
                    break;
                default:
                    break;
            }
        }
    }

    public ArrayList<RepayingBean> getList() {
        return list;
    }

    public void setList(ArrayList<RepayingBean> list) {
        this.list = list;
    }

    public interface OnItemClickListener{

        void onCheck(int position);

        void onDownload(int position);

        void onTransfer(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

}

