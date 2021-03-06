package com.karazam.huashanapp.user.login.viewmodel;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.user.findpassword.main.model.databinding.FindpasswordEntity;
import com.karazam.huashanapp.user.findpassword.main.view.activity.FindpasswordActivity;
import com.karazam.huashanapp.user.login.model.databinding.LoginEntity;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;
import com.karazam.huashanapp.user.login.model.retrofit.LoginDataSource;
import com.karazam.huashanapp.user.login.view.LoginView;
import com.karazam.huashanapp.user.login.view.activity.LoginActivity;
import com.karazam.huashanapp.user.register.view.activity.Register1Activity;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

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

    /**
     * 跳转到忘记密码页面
     * @param view
     */
    @Override
    public void toFindpassword(View view) {
//        mView.showToast("toFindpassword");
        activity.toOtherActivity(activity, FindpasswordActivity.class);
    }

    /**
     * 跳转到注册页面
     * @param view
     */
    @Override
    public void toRegister(View view) {
//        mView.showToast("toRegister");
        activity.toOtherActivity(activity, Register1Activity.class);
//        activity.finish();
    }


    /**
     * 登录方法
     * @param account
     * @param password
     */
    @Override
    public void login(final String account, String password) {


        dataSource.getToken(account,password,HuaShanApplication.corp)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<TokenData>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("login","e : "+e.toString());
                mView.loginError(e);
            }

            @Override
            public void onNext(BaseReturn<TokenData> s) {
//                Log.i("login",s.toString());

                String status = s.getStatus();
                if(status.equals("success")){

                    TokenData data = s.getData();

                    HuaShanApplication.editor.putString("token",data.getSid()).commit();
                    HuaShanApplication.token = data.getSid();

                    HuaShanApplication.editor.putString("uuid",data.getUserId()).commit();
                    HuaShanApplication.uuid = data.getUserId();

                    HuaShanApplication.editor.putString("userKey",data.getUserKey()).commit();
                    HuaShanApplication.userKey = data.getUserKey();

                    HuaShanApplication.editor.putString("account",account).commit();
                    HuaShanApplication.account = account;


                    mView.loginSuccess();
                    Log.i("TokenData",data.toString());
                }else {

                    mView.loginFaile(s.getMessage());
                }

            }
        });

//        mView.loginSuccess();
    }
}
