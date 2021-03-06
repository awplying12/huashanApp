package com.karazam.huashanapp.my.message.messagedetails.view.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.DataUtil;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/21.
 */

public class MessagedetailsAdapter extends RecyclerView.Adapter<MessagedetailsAdapter.ViewHolder> {

    private Context context;
    private ArrayList<DetailsBean> list = new ArrayList<>();

    public MessagedetailsAdapter(Context context, ArrayList<DetailsBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_message_details_item,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(BaseActivity.ScreeW, ViewGroup.LayoutParams.WRAP_CONTENT));
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setItem(holder,position);

        String noticeTime = list.get(position).getNoticeTime();
        Long date = Long.parseLong(StringUtil.interrupt(noticeTime,0,"0"));
        if(date == null || date == 0){
            holder.data_tv.setText("");
        } else {
            String time = DataUtil.getDate(new Date(date),"yyyy-MM-dd HH:mm");
            holder.data_tv.setText(StringUtil.interrupt(time,0,""));
        }

        String title = StringUtil.interrupt(list.get(position).getTitle(),0,"未知");
        holder.title_tv.setText(title);

        String content = StringUtil.interrupt(list.get(position).getContent(),0,"未知");
        holder.content_tv.setText("  "+content);

//        holder.content_tv.setText("关于华善金融全新改版的通知\n" +
//                "\n" +
//                "尊敬的华善金融用户：\n" +
//                "\n" +
//                "您好！\n" +
//                "\n" +
//                " 为了华善金融用户提供更便捷、更安全的投资服务。华善金融对官网进行了全面");
    }


    private void setItem(ViewHolder holder, int position) {

        int pad = (int) (BaseActivity.ScreeH*0.03);
        RecyclerView.LayoutParams rl = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
//        if(position == 0){
//
//            rl.setMargins(0,pad,0,0);
//        }else if(position == list.size()-1){
//            rl.setMargins(0,0,0,pad);
//        }

        if(position == list.size()-1){
            rl.setMargins(0,0,0,pad);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView data_tv;
        private TextView content_tv;
        private TextView title_tv;
        public ViewHolder(View itemView) {
            super(itemView);

            data_tv = (TextView) itemView.findViewById(R.id.data_tv);
            title_tv = (TextView) itemView.findViewById(R.id.title_tv);
            content_tv = (TextView) itemView.findViewById(R.id.content_tv);

        }
    }

    public ArrayList<DetailsBean> getList() {
        return list;
    }

    public void setList(ArrayList<DetailsBean> list) {
        this.list = list;
    }
}
