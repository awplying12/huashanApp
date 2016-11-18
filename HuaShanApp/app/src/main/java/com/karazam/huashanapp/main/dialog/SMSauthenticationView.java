package com.karazam.huashanapp.main.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.karazam.huashanapp.R;

import java.util.Timer;
import java.util.TimerTask;


/**
 * Created by Administrator on 2016/11/18.
 */

public class SMSauthenticationView implements View.OnClickListener{

    private View view;
    private Context context;
    private ViewGroup layout;

    private TextView sms_tv_1;
    private TextView sms_tv_2_1;
    private TextView sms_tv_2_2;

    private EditText sms_ed;
    private TextView wrong;

    private TextView sms_tv_3;

    private TextView bt_sms_l;
    private TextView bt_sms_r;

    public SMSauthenticationView(Context context) {
        this.context = context;
    }

    private OnAuthenticationListener mOnAuthenticationListener;

    public interface OnAuthenticationListener{
        void onLeft(View view);

        void onRight(View view);

        void onHelp(View view);

            void onResult(boolean result);
    }



    public View setView(ViewGroup layout,OnAuthenticationListener onAuthenticationListener){
        this.layout = layout;
        this.mOnAuthenticationListener = onAuthenticationListener;
        view = LayoutInflater.from(context).inflate(R.layout.dialog_smsauthentication,null);

        initView();


        return view;
    }

    /**
     * 初始化View
     */
    private void initView() {
        sms_tv_1 = (TextView) view.findViewById(R.id.sms_tv_1);
        sms_tv_2_1 = (TextView) view.findViewById(R.id.sms_tv_2_1);
        sms_tv_2_2 = (TextView) view.findViewById(R.id.sms_tv_2_2);
        sms_ed = (EditText) view.findViewById(R.id.sms_ed);
        sms_tv_3 = (TextView) view.findViewById(R.id.sms_tv_3);
        wrong = (TextView) view.findViewById(R.id.wrong);

        bt_sms_l = (TextView) view.findViewById(R.id.bt_sms_l);
        bt_sms_r = (TextView) view.findViewById(R.id.bt_sms_r);

        bt_sms_l.setOnClickListener(this);
        bt_sms_r.setOnClickListener(this);
        sms_tv_2_2.setOnClickListener(this);

    }


    public void show(){
        if(view == null){
            return;
        }
        layout.addView(view,layout.getLayoutParams());
    }

    public void dismiss(){
        if(layout == null || view == null){
            return;
        }
        layout.removeView(view);
    }

    public void verification(){
        Timer time = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                Activity activity = (Activity) context;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mOnAuthenticationListener.onResult(true);
                    }
                });

            }
        };
        time.schedule(tk,3000);

    }

    @Override
    public void onClick(View view) {
        if(mOnAuthenticationListener == null){
            return;
        }

        switch (view.getId()){
            case R.id.bt_sms_l:
                mOnAuthenticationListener.onLeft(view);
                break;
            case R.id.bt_sms_r:
                mOnAuthenticationListener.onRight(view);
                break;
            case R.id.sms_tv_2_2:
                mOnAuthenticationListener.onHelp(view);
                break;
            default:
                break;
        }
    }
}
