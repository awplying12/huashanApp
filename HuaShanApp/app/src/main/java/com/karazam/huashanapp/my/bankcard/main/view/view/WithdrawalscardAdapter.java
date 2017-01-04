package com.karazam.huashanapp.my.bankcard.main.view.view;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/12/27.
 */

public class WithdrawalscardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    private int mHeaderCount=1;//头部View个数
    private int mBottomCount=1;//底部View个数

    private Context context;
    private ArrayList<CardBean> list = new ArrayList<>();



    public WithdrawalscardAdapter(Context context, ArrayList<CardBean> list) {
        this.context = context;
        this.list = list;
    }

    //判断当前item是否是HeadView
    public boolean isHeaderView(int position) {
        return mHeaderCount != 0 && position < mHeaderCount;
    }
    //判断当前item是否是FooterView
    public boolean isBottomView(int position) {
        return mBottomCount != 0 && position >= (mHeaderCount + getContentItemCount());
    }



    public int getContentItemCount(){
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        int dataItemCount = getContentItemCount();
        if (mHeaderCount != 0 && position < mHeaderCount) {
//头部View
            return ITEM_TYPE_HEADER;
        } else if (mBottomCount != 0 && position >= (mHeaderCount + dataItemCount)) {
//底部View
            return ITEM_TYPE_BOTTOM;
        } else {
//内容View
            return ITEM_TYPE_CONTENT;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType ==ITEM_TYPE_HEADER) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_header,null);
            return new HeaderViewHolder(view);
        } else if (viewType == mHeaderCount) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_bankcard_item,null);
            view.setLayoutParams(new RecyclerView.LayoutParams(BaseActivity.ScreeW, (int) (BaseActivity.ScreeH*0.2)));
            ViewHolder holder = new ViewHolder(view);

            return holder;
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.rv_footer_1,null);
            RecyclerView.LayoutParams lm = new RecyclerView.LayoutParams(BaseActivity.ScreeW, (int) (BaseActivity.ScreeH*0.08));
            lm.setMargins(0,(int) (BaseActivity.ScreeH*0.05),0,(int) (BaseActivity.ScreeH*0.05));
            view.setLayoutParams(lm);

            return new BottomViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener == null){
                return;
            }
            mOnItemClickListener.onItem(view,getPosition());
        }
    }

    //头部 ViewHolder
    public static class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        public HeaderViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener == null){
                return;
            }
            mOnItemClickListener.isHeaderView(view);
        }
    }
    //底部 ViewHolder
    public static class BottomViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private PercentRelativeLayout btn_add;

        public BottomViewHolder(View itemView) {
            super(itemView);

            btn_add = (PercentRelativeLayout) itemView.findViewById(R.id.btn_add);
            btn_add.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener == null){
                return;
            }
            mOnItemClickListener.onBottomView(view);
        }
    }

    public ArrayList<CardBean> getList() {
        return list;
    }

    public void setList(ArrayList<CardBean> list) {
        this.list = list;
    }

    public interface OnItemClickListener{

        void isHeaderView(View view);

        void onItem(View view,int position);

        void onBottomView(View view);

    }

    private static OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}
