package com.karazam.huashanapp.my.recharge.changecard;

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
import com.karazam.huashanapp.main.Bean.MyInformation.CardBean;

import java.util.ArrayList;

import rx.Subscriber;
import util.changhongit.com.cacheutils.Cache_RxBitmap.Data;
import util.changhongit.com.cacheutils.Cache_RxBitmap.RxImageLoader;

/**
 * Created by Administrator on 2017/1/9.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder>{

    private ArrayList<CardBean> list = new ArrayList<>();
    private Context context;

    public CardAdapter(ArrayList<CardBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_paymentmod_item,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(BaseActivity.ScreeW, ViewGroup.LayoutParams.WRAP_CONTENT));
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        String url = list.get(position).getBankLogo();
        RxImageLoader.getLoaderObservable(holder.pay_img,url).subscribe(new Subscriber<Data>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                holder.pay_img.setImageDrawable(context.getResources().getDrawable(R.drawable.bankdef_logo));
            }

            @Override
            public void onNext(Data data) {
                if(data.bitmap == null){
                    holder.pay_img.setImageDrawable(context.getResources().getDrawable(R.drawable.bankdef_logo));
                }
            }
        });

        String bankName = StringUtil.interrupt(list.get(position).getBankName(),6,"");
        String cardNo = StringUtil.interrupt(list.get(position).getCardNo(),0,"");
        if(!cardNo.equals("")){
            cardNo = "(尾号"+cardNo.substring(8,12)+")";
        }
        holder.pay_method.setText(bankName+cardNo);

//        String
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private ImageView pay_img;
        private TextView pay_method;
        private TextView pay_content;

        public ViewHolder(View itemView) {
            super(itemView);

            itemView.setOnClickListener(this);

            pay_img = (ImageView) itemView.findViewById(R.id.pay_img);
            pay_method = (TextView) itemView.findViewById(R.id.pay_method);
            pay_content = (TextView) itemView.findViewById(R.id.pay_content);
        }

        @Override
        public void onClick(View view) {
            if(mOnItemClickListener == null){
                return;
            }
            mOnItemClickListener.onItem(view,getPosition());
        }
    }

    public ArrayList<CardBean> getList() {
        return list;
    }

    public void setList(ArrayList<CardBean> list) {
        this.list = list;
    }

    public interface OnItemClickListener{

        void onItem(View view,int position);

    }

    private static OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}
