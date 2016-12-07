package com.karazam.huashanapp.my.mytransfer.main.view.view;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.main.financialproject.FinancialInformation;

import java.util.ArrayList;


/**
 * Created by Administrator on 2016/12/5.
 */

public class FinishedAdapter extends RecyclerView.Adapter<FinishedAdapter.ViewHolder> {

    private Context context;
    private ArrayList<FinancialInformation> list = new ArrayList<>();

    private RecyclerView rl;

    private FrameLayout fl;
    private ImageView open;

    public FinishedAdapter(Context context, ArrayList<FinancialInformation> list, RecyclerView rl) {
        this.context = context;
        this.list = list;
        this.rl = rl;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_transferfinish_item,null);
        view.setLayoutParams(new RelativeLayout.LayoutParams(rl.getWidth(), ViewGroup.LayoutParams.WRAP_CONTENT));
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

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
//        private TextView transfer_btn;

        public ViewHolder(View itemView) {
            super(itemView);

            fl1 = (FrameLayout) itemView.findViewById(R.id.fl_1);
            fl2 = (FrameLayout) itemView.findViewById(R.id.fl_2);
            open = (ImageView) itemView.findViewById(R.id.open);
            check_btn = (TextView) itemView.findViewById(R.id.check_btn);
            check_btn.setOnClickListener(this);
            download_btn = (TextView) itemView.findViewById(R.id.download_btn);
            download_btn.setOnClickListener(this);
//            transfer_btn = (TextView) itemView.findViewById(R.id.transfer_btn);
//            transfer_btn.setOnClickListener(this);
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
//                case R.id.transfer_btn:
//
//                    mOnItemClickListener.onTransfer(getPosition());
//                    break;
                default:
                    break;
            }
        }
    }


    public interface OnItemClickListener{

        void onCheck(int position);

        void onDownload(int position);

//        void onTransfer(int position);
    }

    private OnItemClickListener mOnItemClickListener;

    public void setmOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mOnItemClickListener = onItemClickListener;
    }
}


