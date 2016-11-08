package com.karazam.huashanapp.today.main.view.fragment.view;

/**
 * Created by Administrator on 2016/11/8.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.manage.details.view.activity.InvestmentdetailsActivity;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;


public class ExperienceAdapter extends RecyclerView.Adapter<ExperienceAdapter.ViewHolder> {
    private ArrayList<Project> mData;
    private final LayoutInflater mLayoutInflater;
    private Context mContext;


    public ExperienceAdapter(Context context, ArrayList<Project> data) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }



    @Override
    public ExperienceAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.layout_experience_content_item, parent, false);
        ViewHolder holder = new ExperienceAdapter.ViewHolder(view,mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ExperienceAdapter.ViewHolder holder, final int position) {

        setTextView(holder,position);

        holder.buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext,"立即体验  "+position,Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(mContext, InvestmentdetailsActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                mContext.startActivity(intent);
            }
        });


        RxProperty<Project> status = RxProperty.create();
        status.set(mData.get(position));
        RxView.of(holder.buy_now).bind(status, new Rx.Action<TextView, Project>() {
            @Override
            public void call(TextView target, Project project) {
                String tx = project.getStatus_tx();
                target.setText(StringUtil.interrupt(tx,0,""));

                int status = mData.get(position).getStatus();

                switch (status){
                    case 0:
                        target.setBackgroundResource(R.drawable.btn_bg_img_0894ec_5dp);
                        target.setClickable(true);
                        break;
                    case 1:
                        target.setBackgroundResource(R.drawable.bg_fillet_adadad_5dp);
                        target.setClickable(false);
                        break;
                    default:
                        break;
                }
            }
        });



    }

    /**
     * 设置text界面
     * @param holder
     * @param position
     */
    private void setTextView(ExperienceAdapter.ViewHolder holder, final int position) {

        String annualIncome = "8.60";
        Spanned text1 = Html.fromHtml(StringUtil.interrupt(annualIncome,0,"")+"<font color='#505050'><small><small>%<small><small>");
        holder.Annual_Income.setText(text1);


        String projectScale = "3";
        Spanned text3 = Html.fromHtml(StringUtil.interrupt(projectScale,0,"")+"<small><small>天<small><small>");
        holder.Project_Scale.setText(text3);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public onItemClickListener listener;
        private TextView Annual_Income;
        private TextView Project_Scale;
        private TextView buy_now;

        public ViewHolder(View itemView,onItemClickListener onItemClickListener) {
            super(itemView);
            this.listener = onItemClickListener;
            itemView.setOnClickListener(this);
            Annual_Income = (TextView) itemView.findViewById(R.id.tv_1_2);

            Project_Scale = (TextView) itemView.findViewById(R.id.tv_3_2);
            buy_now = (TextView) itemView.findViewById(R.id.buy_now_item);


        }

        @Override
        public void onClick(View view) {
            if(listener == null){
                return;
            }
            listener.onItemClick(view,getPosition());
        }
    }

    //添加数据
    public void addItem(ArrayList<Project> newDatas) {
        //mTitles.add(position, data);
        //notifyItemInserted(position);
        newDatas.addAll(mData);
        mData.removeAll(mData);
        mData.addAll(newDatas);
        notifyDataSetChanged();
    }

    public void addMoreItem(ArrayList<Project> newDatas) {
        mData.addAll(newDatas);
        notifyDataSetChanged();
    }

    public interface onItemClickListener{
        void onItemClick(View view,int position);
    }

    private static onItemClickListener mOnItemClickListener;

    public static void setmOnItemClickListener(onItemClickListener onItemClickListener){
        mOnItemClickListener = onItemClickListener;
    }
}

