package com.karazam.huashanapp.user.register.viewmodel.Register2ViewModel;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.user.register.model.databinbing.Register2Entity;
import com.karazam.huashanapp.user.register.view.Register2View;
import com.karazam.huashanapp.user.register.view.activity.Register2Activity;
import com.karazam.huashanapp.user.register.view.activity.Register3Activity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register2ViewModelImpl extends Register2ViewModel{

    private Register2View mView;
    private Register2Entity mEntity;
    private Context context;
    private Register2Activity activity;

    public Register2ViewModelImpl(Register2View mView, Register2Entity mEntity, Context context, Register2Activity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        checkText();
        reacQuire(null);
    }

    @Override
    public void onBack(View view) {
        activity.FinishActivity(activity);
    }

    /**
     * 检查信息
     */
    public void checkText(){

        tv_time = (TextView) mView.getView(R.id.time);

        RxView.of(tv_time).bind(time, new Rx.Action<View, Integer>() {
            @Override
            public void call(View target, Integer integer) {
                TextView view = (TextView) target;


                if(integer == 0 ){
                    view.setText("重新获取验证码");
                    view.setTextColor(Color.parseColor("#0894EC"));
                    target.setClickable(true);
                }else {
                    String time = integer >=100? integer+"" : (integer >= 10? "0"+integer: "00"+integer);
                    view.setText("重新发送 "+"("+time+")");
                    view.setTextColor(Color.parseColor("#d0d0d0"));
                    target.setClickable(false);
                }

            }
        });

        com.jakewharton.rxbinding.view.RxView.clicks(tv_time)
                .throttleFirst(300, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        reacQuire(null);
                    }
                });

    }

    /**
     * 获取验证码
     */
    int s = 120;
    @Override
    public void reacQuire(View view){

        s = 120;
        final Timer timer = new Timer();
         TimerTask tk = new TimerTask() {
            @Override
            public void run() {
              s--;
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        time.set(s);
                    }
                });

                if(s == 0){
                    timer.cancel();

                }

            }
        };
        timer.schedule(tk,1000,1000);

        mView.showToast("123");

    }

    /**
     * 下一步
     * @param view
     */
    @Override
    public void onNextStep(View view) {
        mView.showToast("onNextStep");
        mView.toOtherActivity(activity, Register3Activity.class);
    }
}
