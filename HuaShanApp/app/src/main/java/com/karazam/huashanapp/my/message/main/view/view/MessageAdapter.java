package com.karazam.huashanapp.my.message.main.view.view;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.utils.base.BaseActivity;
import com.example.utils.utils.DataUtil;
import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.Bean.MessageBean;
import com.karazam.huashanapp.my.message.main.model.databinding.MessagelistItem;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context context;
    private ArrayList<MessageBean> list = new ArrayList<>();

    public MessageAdapter(Context context, ArrayList<MessageBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_message_item,null);
        view.setLayoutParams(new RecyclerView.LayoutParams(BaseActivity.ScreeW, ViewGroup.LayoutParams.WRAP_CONTENT));
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        setItem(holder,position);

        holder.icon.setImageBitmap(BitmapFactory.decodeResource(context.getResources(),list.get(position).getIconId()));

        String title = list.get(position).getTitle();
        holder.title.setText(StringUtil.interrupt(title,0,"未知"));

        MessagelistItem item = list.get(position).getItem();
        if(item == null){
            return;
        }
        String content = item.getContent();
        holder.content.setText(StringUtil.interrupt(content,20,"无"));

        String createDate = item.getNoticeTime();
        Long date = Long.parseLong(StringUtil.interrupt(createDate,0,"0"));
        if(date == null || date == 0){
            holder.time.setText("");
        } else {
            String time = DataUtil.getDate(new Date(date),"yyyy-MM-dd HH:mm");
            holder.time.setText(StringUtil.interrupt(time,0,""));
        }

    }

    private void setItem(ViewHolder holder, int position) {

        int pad = (int) (BaseActivity.ScreeH*0.03);
        RecyclerView.LayoutParams rl = (RecyclerView.LayoutParams) holder.itemView.getLayoutParams();
        if(position == 0){

            rl.setMargins(0,pad,0,0);
        }else if(position == list.size()-1){
            rl.setMargins(0,0,0,pad);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private ImageView icon;
        private TextView title;
        private TextView content;
        private TextView time;
        public ViewHolder(View itemView) {
            super(itemView);

            icon = (ImageView) itemView.findViewById(R.id.icon_img);
            title = (TextView) itemView.findViewById(R.id.title_tv);
            content = (TextView) itemView.findViewById(R.id.content_tv);
            time = (TextView) itemView.findViewById(R.id.time_tv);
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

    public ArrayList<MessageBean> getList() {
        return list;
    }

    public void setList(ArrayList<MessageBean> list) {
        this.list = list;
    }

    public interface OnItemClickListener{
        void onItem(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }

}
