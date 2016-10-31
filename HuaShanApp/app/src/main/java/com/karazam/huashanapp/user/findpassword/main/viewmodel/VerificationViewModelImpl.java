package com.karazam.huashanapp.user.findpassword.main.viewmodel;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.example.utils.base.BaseViewModel;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.user.findpassword.main.model.databinding.VerificationEntity;
import com.karazam.huashanapp.user.findpassword.main.view.VerificationView;
import com.karazam.huashanapp.user.findpassword.main.view.activity.VerificationActivity;
import com.karazam.huashanapp.user.register.view.activity.Register3Activity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/31.
 */

public class VerificationViewModelImpl extends VerificationViewModel {

    private VerificationEntity mEntity;
    private VerificationView mView;
    private Context context;
    private VerificationActivity activity;

    public VerificationViewModelImpl(VerificationEntity mEntity, VerificationView mView, Context context, VerificationActivity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
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
        RxView.findById(activity, R.id.time).bind(time, new Rx.Action<View, Integer>() {
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



    }

    /**
     * 下一步
     * @param view
     */
    @Override
    public void onNextStep(View view) {
            mView.showToast("onNextStep");
    }
}
