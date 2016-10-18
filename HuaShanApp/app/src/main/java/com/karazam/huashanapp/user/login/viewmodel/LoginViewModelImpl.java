package com.karazam.huashanapp.user.login.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.user.login.model.databinding.LoginEntity;
import com.karazam.huashanapp.user.login.view.LoginView;
import com.karazam.huashanapp.user.login.view.activity.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2016/10/18.
 */

public class LoginViewModelImpl extends LoginViewModel {

    private LoginView mView;
    private LoginEntity mEntity;
    private Context context;
    private LoginActivity activity;

    public LoginViewModelImpl(LoginView mView, LoginEntity mEntity, Context context, LoginActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    @Override
    public void toFindpassword(View view) {
        mView.showToast("toFindpassword");
    }

    @Override
    public void toRegister(View view) {
        mView.showToast("toRegister");
    }

    @Override
    public void login(String account, String password) {
        Timer timer = new Timer();
        TimerTask tk = new TimerTask() {
            @Override
            public void run() {
                activity.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mView.loginSuccess();
                    }
                });

            }
        };timer.schedule(tk,3000);
    }
}
