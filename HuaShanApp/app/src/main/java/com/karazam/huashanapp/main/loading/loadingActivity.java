package com.karazam.huashanapp.main.loading;

import android.content.Intent;
import android.view.animation.Animation;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.home.view.activity.HomeActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/12.
 */

public class loadingActivity extends BaseActivity {

    @Override
    public void setContentLayout() {
        setContentView(R.layout.activity_loading);
    }

    @Override
    public void dealLogicBeforeInitView() {

    }

    @Override
    public void initView() {

    }

    @Override
    public void dealLogicAfterInitView() {

        Timer timer = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                Intent intent = new Intent(loadingActivity.this, HomeActivity.class);
                startActivity(intent);

//                overridePendingTransition(R.anim.fade, R.anim.hold);
                finish();


            }
        };
        timer.schedule(tk,3000);

    }
}
