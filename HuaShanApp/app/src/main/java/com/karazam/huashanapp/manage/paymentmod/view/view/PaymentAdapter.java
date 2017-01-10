package com.karazam.huashanapp.manage.paymentmod.view.view;

import android.content.Context;
import android.support.percent.PercentRelativeLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.ArrayList;

import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2016/12/27.
 */

public class PaymentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    public static final int ITEM_TYPE_HEADER = 0;
    public static final int ITEM_TYPE_CONTENT = 1;
    public static final int ITEM_TYPE_BOTTOM = 2;

    private int mHeaderCount=1;//头部View个数
    private int mBottomCount=1;//底部View个数

    private Context context;
    private ArrayList<CardBean> list = new ArrayList<>();

    private View mFooteView;

    public PaymentAdapter(Context context, ArrayList<CardBean> list) {
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
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_paymentmod_item,null);
            RecyclerView.LayoutParams lm = new RecyclerView.LayoutParams(BaseActivity.ScreeW, ViewGroup.LayoutParams.WRAP_CONTENT);
            lm.setMargins(0,(int) (BaseActivity.ScreeH*0.015),0,0);
            view.setLayoutParams(lm);

            return new HeaderViewHolder(view);
        } else if (viewType == mHeaderCount) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_paymentmod_item,null);
            view.setLayoutParams(new RecyclerView.LayoutParams(BaseActivity.ScreeW, ViewGroup.LayoutParams.WRAP_CONTENT));
            ViewHolder holder = new ViewHolder(view);

            return holder;
        } else if (viewType == ITEM_TYPE_BOTTOM) {
            View view = new View(parent.getContext());
            view.setLayoutParams(new RecyclerView.LayoutParams(BaseActivity.ScreeW, (int) (BaseActivity.ScreeH*0.015)));

            return new BottomViewHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {

        if(holder instanceof HeaderViewHolder){
            //头部View
            HeaderViewHolder header = (HeaderViewHolder) holder;

            header.pay_img.setImageDrawable(context.getResources().getDrawable(R.drawable.zhye_icon));

            header.pay_method.setText("账户余额");



            RxView.of(header.pay_content).bind(HuaShanApplication.myAssetsBeanRX, new Rx.Action<TextView, MyAssetsBean>() {
                @Override
                public void call(TextView target, MyAssetsBean myAssetsBean) {

                    String userbalance = StringUtil.interrupt(myAssetsBean.getAvailable(),0,"0.00");
                    target.setText("可用余额 "+userbalance);
                }
            });

            header.bt_recharge.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(context,"充值",Toast.LENGTH_SHORT).show();
                }
            });



        }else if(holder instanceof BottomViewHolder){
            //底部View
        } else {
           final int pos = position-1;
            final ViewHolder holder1 = (ViewHolder) holder;

            CardBean cardBean = getList().get(pos);

            String bankName = StringUtil.interrupt(cardBean.getBankName(),6,"");
            String cardNo = StringUtil.interrupt(cardBean.getCardNo(),0,"");
            if(!cardNo.equals("")){
                cardNo = "(尾号"+cardNo.substring(8,12)+")";
            }
            holder1.pay_method.setText(bankName+cardNo);

            String payMemo = StringUtil.interrupt(cardBean.getQuickPayMemo(),0,"未知");
            holder1.pay_content.setText(payMemo);


            String url = cardBean.getBankLogo();
            RxImageLoader.getLoaderObservable(holder1.pay_img,url).subscribe(new Subscriber<Data>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    holder1.pay_img.setImageDrawable(context.getResources().getDrawable(R.drawable.bankdef_logo));
                }

                @Override
                public void onNext(Data data) {
                    if(data.bitmap == null){
                        holder1.pay_img.setImageDrawable(context.getResources().getDrawable(R.drawable.bankdef_logo));
                    }
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mHeaderCount + getContentItemCount() + mBottomCount;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{

        private ImageView pay_img;
        private TextView pay_method;
        private TextView pay_content;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            itemView.setOnLongClickListener(this);

            pay_img = (ImageView) itemView.findViewById(R.id.pay_img);
            pay_method = (TextView) itemView.findViewById(R.id.pay_method);
            pay_content = (TextView) itemView.findViewById(R.id.pay_content);

        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener == null){
                return;
            }
            mOnItemClickListener.onItem(view,getPosition()-1);
        }

        @Override
        public boolean onLongClick(View view) {
            if(mOnItemClickListener == null){
                return false;
            }
            mOnItemClickListener.onItemLong(view,getPosition()-1);
            return false;
        }
    }

    //头部 ViewHolder
    public static class HeaderViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView pay_img;
        private TextView pay_method;
        private TextView pay_content;
        private TextView bt_recharge;

        public HeaderViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);
            pay_img = (ImageView) itemView.findViewById(R.id.pay_img);
            pay_method = (TextView) itemView.findViewById(R.id.pay_method);
            pay_content = (TextView) itemView.findViewById(R.id.pay_content);
            bt_recharge = (TextView) itemView.findViewById(R.id.bt_recharge);
            bt_recharge.setVisibility(View.VISIBLE);
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

    public ArrayList<CardBean> getList() {
        return list;
    }

    public void setList(ArrayList<CardBean> list) {
        this.list = list;
    }

    public interface OnItemClickListener{

        void isHeaderView(View view);

        void onItem(View view, int position);

        void onItemLong(View view, int position);

        void onBottomView(View view);

    }

    private static OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}
