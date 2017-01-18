package com.karazam.huashanapp.user.register.viewmodel.Register3ViewModel;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.MainActivity;
import com.karazam.huashanapp.main.registerMain.registerActivity;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GespwReturn;
import com.karazam.huashanapp.my.security.gesturepassword.model.retrofit.GespasswordDataSource;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;
import com.karazam.huashanapp.user.register.model.databinbing.Register3Entity;
import com.karazam.huashanapp.user.register.model.retrofit.RegisterDataSource;
import com.karazam.huashanapp.user.register.view.Register3View;
import com.karazam.huashanapp.user.register.view.activity.Register3Activity;
import com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel.Register1ViewModel;

import java.util.concurrent.TimeUnit;

import huashanapp.karazam.com.gesture_lock.GestureEditActivity;
import huashanapp.karazam.com.gesture_lock.GestureUtil;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register3ViewModelImpl extends Register3ViewModel {

    private Register3View mView;
    private Register3Entity mEntity;
    private Context context;
    private Register3Activity activity;

    private RegisterDataSource dataSource;

    private GespasswordDataSource gespasswordDataSource;

    public Register3ViewModelImpl(Register3View mView, Register3Entity mEntity, Context context, Register3Activity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;

        dataSource = new RegisterDataSource();
        gespasswordDataSource = new GespasswordDataSource();

    }

    @Override
    public void onBack(View view) {
        activity.FinishActivity(activity);
    }

    /**
     * 完成
     * @param view
     */
    @Override
    public void complete(View view) {

        mView.showToast("complete");

        Intent intent = new Intent(activity, GestureEditActivity.class);
        activity.startActivityForResult(intent, GestureUtil.GESTURELOCK_REQUESTCODE);

        onRegister();

    }

    /**
     * 用户协议
     * @param view
     */
    @Override
    public void userAgreement(View view) {

//        mView.showUserAgreement();
    }

    /**
     * 收起用户协议
     */
    @Override
    public void stopUserAgreement(View view) {

        mView.stopUserAgreement();
    }

    /**
     * 注册
     */
    @Override
    public void onRegister() {

        password = ed_password.getText().toString();

        dataSource.onRegister(mobilel,password,smsCode)
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn<TokenData>>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("onRegister", "e  :  "+e.toString());
                mView.registerFaile(e);

            }

            @Override
            public void onNext(BaseReturn<TokenData> tokenDataBaseReturn) {
                Log.i("onRegister", "ok");
                boolean success = tokenDataBaseReturn.isSuccess();
                if(success){    //注册成功
                    TokenData data = tokenDataBaseReturn.getData();

                    HuaShanApplication.editor.putString("token",data.getSid()).commit();
                    HuaShanApplication.token = data.getSid();

                    HuaShanApplication.editor.putString("uuid",data.getUserId()).commit();
                    HuaShanApplication.uuid = data.getUserId();

                    HuaShanApplication.editor.putString("userKey",data.getUserKey()).commit();
                    HuaShanApplication.userKey = data.getUserKey();

                    HuaShanApplication.editor.putString("account",mobilel).commit();
                    HuaShanApplication.account = mobilel;
                    mView.registerSuccess(data);

                }else {
                    mView.showToast(tokenDataBaseReturn.getMessage());
                    mView.registerFaile(new Throwable("Faile"));
                }
            }
        });

    }

    /**
     * 设置手势密码
     * @param gesPassword
     */
    @Override
    public void setGesPassword(String gesPassword) {
        gespasswordDataSource.setGesPassword(gesPassword).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<GespwReturn>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

                mView.setGesPasswordError(e);
                Log.i("GespwReturn","e  :  "+e.toString());
            }

            @Override
            public void onNext(BaseReturn<GespwReturn> gespwReturnBaseReturn) {

                if(gespwReturnBaseReturn.isSuccess()){
                    GespwReturn gespwReturn = gespwReturnBaseReturn.getData();
                    Log.i("GespwReturn",gespwReturn.toString());
                    mView.setGesPasswordSuccess(gespwReturn);
                } else {
                    mView.setGesPasswordFaile(gespwReturnBaseReturn.getMessage());
                }

            }
        });
    }


}
