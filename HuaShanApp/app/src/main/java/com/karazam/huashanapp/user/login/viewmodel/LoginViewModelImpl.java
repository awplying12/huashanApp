package com.karazam.huashanapp.user.login.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.user.login.model.databinding.LoginEntity;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;
import com.karazam.huashanapp.user.login.model.retrofit.LoginDataSource;
import com.karazam.huashanapp.user.login.view.LoginView;
import com.karazam.huashanapp.user.login.view.activity.LoginActivity;

import java.util.Timer;
import java.util.TimerTask;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/18.
 */

public class LoginViewModelImpl extends LoginViewModel {

    private LoginView mView;
    private LoginEntity mEntity;
    private Context context;
    private LoginActivity activity;

    private LoginDataSource dataSource;

    public LoginViewModelImpl(LoginView mView, LoginEntity mEntity, Context context, LoginActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        dataSource = new LoginDataSource();
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
//        Timer timer = new Timer();
//        TimerTask tk = new TimerTask() {
//            @Override
//            public void run() {
//                activity.runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        mView.loginSuccess();
//                    }
//                });
//
//            }
//        };timer.schedule(tk,3000);

        dataSource.getToken(account,password).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<TokenData>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("login","e : "+e.toString());
                mView.loginFaile();
            }

            @Override
            public void onNext(BaseReturn<TokenData> s) {
                Log.i("login",s.toString());

                String status = s.getStatus();
                if(status.equals("success")){
                    mView.loginSuccess();
                    TokenData data = s.getData();
                }else {
                    mView.loginFaile();
                }


            }
        });
    }
}
