package com.karazam.huashanapp.main.dialog;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.karazam.huashanapp.R;

/**
 * Created by Administrator on 2016/11/28.
 */

public class PaymentpasswrodView implements View.OnClickListener{

    private View view;
    private Context context;
    private ViewGroup layout;

    private TextView modify;
    private TextView forget;

    private View content_view;

    public PaymentpasswrodView(Context context) {
        this.context = context;
    }



    public interface OnPaymentpasswrodListener{
        void onModify();

        void onForget();
    }

    private OnPaymentpasswrodListener mOnPaymentpasswrodListener;

    public View setView(ViewGroup layout, OnPaymentpasswrodListener OnPaymentpasswrodListener){
        this.layout = layout;
        this.mOnPaymentpasswrodListener = OnPaymentpasswrodListener;
        view = LayoutInflater.from(context).inflate(R.layout.view_paymentpw,null);

        initView();
        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {
        modify = (TextView) view.findViewById(R.id.modify);
        forget = (TextView) view.findViewById(R.id.forget);
        content_view = view.findViewById(R.id.content_view);

        view.findViewById(R.id.cancel).setOnClickListener(this);
        modify.setOnClickListener(this);
        forget.setOnClickListener(this);
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
            case R.id.modify:
                if(mOnPaymentpasswrodListener == null){
                    return;
                }
                mOnPaymentpasswrodListener.onModify();
                break;
            case R.id.forget:
                if(mOnPaymentpasswrodListener == null){
                    return;
                }
                mOnPaymentpasswrodListener.onForget();
                break;
            case R.id.cancel:
                dismiss();
                break;
        }
    }
}
