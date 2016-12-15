package com.karazam.huashanapp.manage.main.view.view;

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
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.manage.details.view.activity.InvestmentdetailsActivity;
import com.karazam.huashanapp.manage.main.model.databinding.Project;
import com.karazam.huashanapp.user.login.view.activity.LoginActivity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/10/17.
 */

public class ContentAdapter extends RecyclerView.Adapter<ContentAdapter.ViewHolder> {
    private ArrayList<Project> mData;
    private final LayoutInflater mLayoutInflater;
    private Context mContext;


    public ContentAdapter(Context context, ArrayList<Project> data) {
        this.mContext = context;
        this.mData = data;
        this.mLayoutInflater = LayoutInflater.from(mContext);
    }



    @Override
    public ContentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mLayoutInflater.inflate(R.layout.layout_project_content_item, parent, false);
        ViewHolder holder = new ViewHolder(view,mOnItemClickListener);
        return holder;
    }

    @Override
    public void onBindViewHolder(ContentAdapter.ViewHolder holder, final int position) {

        setTextView(holder,position);

        holder.buy_now.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(mContext,"立即购买  "+position,Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(mContext, InvestmentdetailsActivity.class);
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                mContext.startActivity(intent);

                if(HuaShanApplication.loginStatus == 1){
                    Intent intent = new Intent(mContext, InvestmentdetailsActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    mContext.startActivity(intent);
                }else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    mContext.startActivity(intent);
                }


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
    private void setTextView(ContentAdapter.ViewHolder holder, final int position) {

        String annualIncome = "8.60";
        Spanned text1 = Html.fromHtml(StringUtil.interrupt(annualIncome,0,"")+"<font color='#505050'><small><small>%<small><small>");
        holder.Annual_Income.setText(text1);

        String projectDuration = "20";
        Spanned text2 = Html.fromHtml(StringUtil.interrupt(projectDuration,0,"")+"<small><small>万元<small><small>");
        holder.Project_Duration.setText(text2);

        String projectScale = "6";
        Spanned text3 = Html.fromHtml(StringUtil.interrupt(projectScale,0,"")+"<small><small>月<small><small>");
        holder.Project_Scale.setText(text3);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public onItemClickListener listener;
        private TextView Annual_Income;
        private TextView Project_Duration;
        private TextView Project_Scale;
        private TextView buy_now;

        public ViewHolder(View itemView,onItemClickListener onItemClickListener) {
            super(itemView);
            this.listener = onItemClickListener;
            itemView.setOnClickListener(this);
            Annual_Income = (TextView) itemView.findViewById(R.id.tv_1_2);
            Project_Duration = (TextView) itemView.findViewById(R.id.tv_2_2);
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
