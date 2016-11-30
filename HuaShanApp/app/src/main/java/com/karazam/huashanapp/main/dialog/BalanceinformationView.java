package com.karazam.huashanapp.main.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.example.utils.utils.StringUtil;
import com.karazam.huashanapp.R;

/**
 * Created by Administrator on 2016/11/30.
 */

public class BalanceinformationView implements View.OnClickListener{
    private View view;
    private Context context;
    private ViewGroup layout;

    private TextView arrival_amount;
    private TextView service_charge;

    private View content_view;

    public BalanceinformationView(Context context) {
        this.context = context;
    }



    public interface OnBalanceinformationListener{
        void onContinue();

        void onHelp();
    }

    private OnBalanceinformationListener mOnBalanceinformationListener;

    public View setView(ViewGroup layout, OnBalanceinformationListener OnBalanceinformationListener){
        this.layout = layout;
        this.mOnBalanceinformationListener = OnBalanceinformationListener;
        view = LayoutInflater.from(context).inflate(R.layout.view_balanceininf,null);

        initView();
        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {
        content_view = view.findViewById(R.id.content_view);

        view.findViewById(R.id.back).setOnClickListener(this);
        view.findViewById(R.id.help).setOnClickListener(this);
        view.findViewById(R.id.btn_continue).setOnClickListener(this);

        arrival_amount = (TextView) view.findViewById(R.id.arrival_amount);
        service_charge = (TextView) view.findViewById(R.id.service_charge);



    }

    public void setArrivalamount(String str){
        if(arrival_amount == null){
            return;
        }
        arrival_amount.setText(StringUtil.interrupt(str,0,"未知"));
    }

    public void setServicecharge(String str){
        if(service_charge == null){
            return;
        }
        service_charge.setText(StringUtil.interrupt(str,0,"未知"));
    }

    public void show(){
        if(view == null){
            return;
        }

        in();
    }

    public void dismiss() {
        if(layout == null || view == null){
            return;
        }
        out();
    }

    private boolean isShowing = false;
    private void in(){
        isShowing = true;
        layout.addView(view,layout.getLayoutParams());
        content_view.setVisibility(View.VISIBLE);
        Animation animation_up = AnimationUtils.loadAnimation(context,R.anim.anim_arrow_up);
        content_view.setAnimation(animation_up);
        animation_up.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isShowing = false;
                content_view.clearAnimation();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    private void out(){
        isShowing = true;
        content_view.clearAnimation();
        Animation animation_down = AnimationUtils.loadAnimation(context,R.anim.anim_arrow_down);
        content_view.setAnimation(animation_down);
        animation_down.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                isShowing = false;
                content_view.clearAnimation();
                content_view.setVisibility(View.GONE);
                layout.removeView(view);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.back:
                dismiss();
                break;
            case R.id.help:
                if(mOnBalanceinformationListener == null){
                    return;
                }
                mOnBalanceinformationListener.onHelp();
                break;
            case R.id.btn_continue:
                if(mOnBalanceinformationListener == null){
                    return;
                }
                mOnBalanceinformationListener.onContinue();
                break;
            default:
                break;
        }
    }
}
