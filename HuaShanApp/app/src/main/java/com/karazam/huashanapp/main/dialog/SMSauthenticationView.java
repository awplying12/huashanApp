package com.karazam.huashanapp.main.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.utils.utils.StringUtil;
import com.jakewharton.rxbinding.widget.RxCheckedTextView;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.jakewharton.rxbinding.widget.TextViewTextChangeEvent;
import com.karazam.huashanapp.R;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxProperty;
import com.ogaclejapan.rx.binding.RxView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;


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

    private boolean status = false;

    private String code = "asdf";

    public RxProperty<Integer> time = RxProperty.create();

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
        initTime();
        reacQuire();


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
        sms_tv_3.setOnClickListener(this);
    }


    public void show(){
        if(view == null){
            return;
        }
        layout.addView(view,layout.getLayoutParams());

        sim = 60;
        if(thread.isAlive()){
            return;
        }
        reacQuire();
    }

    public void dismiss() {
        if(layout == null || view == null){
            return;
        }
        layout.removeView(view);
    }

    public void verification(){

        String ver = sms_ed.getText().toString();
        if(TextUtils.isEmpty(ver)){
            if(wrong.getVisibility() == View.GONE){
                wrong.setVisibility(View.VISIBLE);
            }
            wrong.setText("验证码不能为空");
            return;
        }else if(!status){
            if(wrong.getVisibility() == View.GONE){
                wrong.setVisibility(View.VISIBLE);
            }
            wrong.setText("验证码错误");
            return;
        }else {
            if(wrong.getVisibility() == View.VISIBLE){
                wrong.setVisibility(View.GONE);
            }
        }


        Timer time = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                Activity activity = (Activity) context;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mOnAuthenticationListener.onResult(status);
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
            case R.id.sms_tv_3:
                reacQuire();
                break;
            default:
                break;
        }
    }

    /**
     * 获取验证码
     */
    int sim = 120;
    private Thread thread;
    public void reacQuire(){

        sim = 60;
        thread = new Thread(new Runnable() {

            @Override
            public void run() {

                while (sim > 0) {
                    Activity activity = (Activity) context;
                    activity.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            time.set(sim);
                        }
                    });
                    sim--;
                    try {
                        Thread.sleep((long) (1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    /**
     *设置时间
     */
    private void initTime() {
        RxView.of(sms_tv_3).bind(time, new Rx.Action<TextView, Integer>() {
            @Override
            public void call(TextView target, Integer integer) {

                if(integer == 0 ){
                    target.setText("重新获取验证码");
                    target.setTextColor(Color.parseColor("#0894EC"));
                    target.setClickable(true);
                }else {
                    String time = integer >=100? integer+"" : (integer >= 10? "0"+integer: "00"+integer);
                    target.setText(time+"秒后重发");
                    target.setTextColor(Color.parseColor("#d0d0d0"));
                    target.setClickable(false);
                }
            }
        });

        RxTextView.textChangeEvents(sms_ed)
                .debounce(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<TextViewTextChangeEvent>() {
                    @Override
                    public void call(TextViewTextChangeEvent textViewTextChangeEvent) {
                        String str = textViewTextChangeEvent.text().toString().trim();

                        if(!TextUtils.isEmpty(str)){

                            if(str.equals(code)){
                                status = true;
                            }else {
                                status = false;
                            }
                        }
                    }
                });
    }

    /**
     * 设置支付金额text
     * @param text
     */
    public void setText1(String text){
        sms_tv_1.setText(StringUtil.interrupt(text,0,"未知"));
    }

    /**
     * 设置手机尾号text
     * @param text
     */
    public void setText2(String text){
        sms_tv_2_1.setText(StringUtil.interrupt(text,0,"未知"));
    }
}