package com.karazam.huashanapp.main.loading;

import android.content.Intent;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.R;
import com.karazam.huashanapp.home.view.activity.HomeActivity;
import com.karazam.huashanapp.my.security.findgesturepassword.view.activity.FindgesturepasswordActivity;
import com.karazam.huashanapp.user.findpassword.main.view.activity.FindpasswordActivity;
import com.karazam.huashanapp.user.login.view.activity.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

import huashanapp.karazam.com.gesture_lock.GestureEditActivity;
import huashanapp.karazam.com.gesture_lock.GestureUtil;
import huashanapp.karazam.com.gesture_lock.GestureVerifyActivity;

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

                loadingAfter();

//                overridePendingTransition(R.anim.fade, R.anim.hold);

            }
        };
        timer.schedule(tk,3000);


        GestureVerifyActivity.setOnGestureVerifyClickListener(new GestureVerifyActivity.onGestureVerifyClickListener() {
            @Override
            public void onForgetGesture() {

                Intent intent = new Intent(loadingActivity.this, FindgesturepasswordActivity.class);
                intent.putExtra("sign",2);
                startActivity(intent);
            }

            @Override
            public void onOtherAccount() {
//                showToast("onOtherAccount");
//                toOtherActivity(loadingActivity.this,LoginActivity.class);
                Intent intent =new Intent(loadingActivity.this,LoginActivity.class);
                startActivityForResult(intent,100);
            }
        });
    }

    public void loadingAfter(){

        if(HuaShanApplication.loginStatus != 1){
            Intent intent = new Intent(loadingActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        boolean isLock = HuaShanApplication.checkSecurityInformation(this);

//        if(HuaShanApplication.loginStatus != 1){
//            Intent intent = new Intent(loadingActivity.this, HomeActivity.class);
//            startActivity(intent);
//            finish();
//            return;
//        }


        if (isLock){
            Intent intent = new Intent(this, GestureVerifyActivity.class);
            intent.putExtra(GestureUtil.Password,HuaShanApplication.getGesturePassword());
            intent.putExtra(GestureUtil.PhoneNum,HuaShanApplication.account);
            intent.putExtra(GestureUtil.HeaderImg,HuaShanApplication.header);
            startActivityForResult(intent, GestureUtil.GESTURELOCK_REQUESTCODE);
        }else {
//            Intent intent = new Intent(this, GestureEditActivity.class);
//            startActivityForResult(intent, GestureUtil.GESTURELOCK_REQUESTCODE);

            Intent intent = new Intent(loadingActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Intent intent = new Intent(loadingActivity.this, HomeActivity.class);

        if(requestCode == GestureUtil.GESTURELOCK_REQUESTCODE){



            switch (resultCode){
                case GestureUtil.GESTURELOCK_EDIT_RESULTCODE:
                    String key = data.getStringExtra(GestureUtil.Password);
                    HuaShanApplication.editor.putString("gesture_lock",key).commit();

                    startActivity(intent);
                    finish();
                    break;
                case GestureUtil.GESTURELOCK_VERIFY_RESULTCODE:

                    startActivity(intent);
                    finish();

                    break;
                default:
                    finish();
                    break;
            }


        } else if(requestCode == 100){
            switch (resultCode){
                case 101:

                    startActivity(intent);
                    finish();
                    break;
                default:
                    finish();
                    break;
            }

        }

    }
}
