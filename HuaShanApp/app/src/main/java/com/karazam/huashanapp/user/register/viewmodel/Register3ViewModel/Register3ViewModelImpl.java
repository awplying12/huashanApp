package com.karazam.huashanapp.user.register.viewmodel.Register3ViewModel;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.example.utils.base.BaseActivity;
import com.karazam.huashanapp.main.MainActivity;
import com.karazam.huashanapp.main.registerMain.registerActivity;
import com.karazam.huashanapp.user.register.model.databinbing.Register3Entity;
import com.karazam.huashanapp.user.register.view.Register3View;
import com.karazam.huashanapp.user.register.view.activity.Register3Activity;
import com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel.Register1ViewModel;

import huashanapp.karazam.com.gesture_lock.GestureEditActivity;
import huashanapp.karazam.com.gesture_lock.GestureUtil;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register3ViewModelImpl extends Register3ViewModel {

    private Register3View mView;
    private Register3Entity mEntity;
    private Context context;
    private Register3Activity activity;

    public Register3ViewModelImpl(Register3View mView, Register3Entity mEntity, Context context, Register3Activity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
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



    }

    /**
     * 用户协议
     * @param view
     */
    @Override
    public void userAgreement(View view) {

        mView.showUserAgreement();
    }

    /**
     * 收起用户协议
     */
    @Override
    public void stopUserAgreement(View view) {

        mView.stopUserAgreement();
    }
}
