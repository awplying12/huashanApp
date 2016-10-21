package com.karazam.huashanapp.user.register.viewmodel.Register2ViewModel;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.karazam.huashanapp.R;
import com.karazam.huashanapp.user.register.model.databinbing.Register2Entity;
import com.karazam.huashanapp.user.register.view.Register2View;
import com.karazam.huashanapp.user.register.view.activity.Register2Activity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

import java.util.Timer;
import java.util.TimerTask;

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

        ww();
    }

    @Override
    public void onBack(View view) {
        activity.FinishActivity(activity);
    }


    public void ww(){
        RxView.findById(activity, R.id.time).bind(time, new Rx.Action<View, Integer>() {
            @Override
            public void call(View target, Integer integer) {
                TextView view = (TextView) target;
                String time = s >=100? s+"" :"0"+s;
                        view.setText("("+time+")");
            }
        });
    }


    int s = 120;
    @Override
    public void w(View view){
        mView.showToast("w");
        s = 120;
        Timer timer = new Timer();
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

            }
        };
        timer.schedule(tk,1000,1000);

    }
}
