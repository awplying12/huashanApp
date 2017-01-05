package com.karazam.huashanapp.my.bankcard.bindcard.view.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BankBean;

import java.util.ArrayList;

import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2017/1/5.
 */

public class BankAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    private int mHeaderCount=1;//头部View个数
    private int mBottomCount=1;//底部View个数

    private Context context;
    private ArrayList<BankBean> list = new ArrayList<>();

    private View mFooteView;

    public BankAdapter(Context context, ArrayList<BankBean> list) {
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
            View view = new View(parent.getContext());
            view.setLayoutParams(new RecyclerView.LayoutParams(BaseActivity.ScreeW, (int) (BaseActivity.ScreeH*0.03)));
            return new HeaderViewHolder(view);
        } else if (viewType == mHeaderCount) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_bank_item,null);
            view.setLayoutParams(new RecyclerView.LayoutParams(BaseActivity.ScreeW, (int) (BaseActivity.ScreeH*0.08)));
            ViewHolder holder = new ViewHolder(view);

            return holder;
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            View view = new View(parent.getContext());
            view.setLayoutParams(new RecyclerView.LayoutParams(BaseActivity.ScreeW, (int) (BaseActivity.ScreeH*0.03)));
            return new BottomViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof HeaderViewHolder){
            //头部View
        }else if(holder instanceof BottomViewHolder){
            //底部View
        } else {
            final int pos = position-1;
            ViewHolder holder1 = (ViewHolder) holder;

            String name = list.get(pos).getName();
            holder1.back_name.setText(StringUtil.interrupt(name,0,""));

            String logoUrl = StringUtil.interrupt(list.get(pos).getLogo(),0,"");

            RxImageLoader.getLoaderObservable(holder1.back_logo,logoUrl).subscribe(new Subscriber<Data>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onNext(Data data) {

                }
            });


        }

    }

    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView back_logo;
        private TextView back_name;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            back_logo = (ImageView) itemView.findViewById(R.id.back_logo);
            back_name = (TextView) itemView.findViewById(R.id.back_name);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener == null){
                return;
            }
            mOnItemClickListener.onItem(view,getPosition()-1);
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

        public BottomViewHolder(View itemView) {
            super(itemView);

        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener == null){
                return;
            }
            mOnItemClickListener.onBottomView(view);
        }
    }

    public ArrayList<BankBean> getList() {
        return list;
    }

    public void setList(ArrayList<BankBean> list) {
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
