package com.karazam.huashanapp.my.security.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.security.gesturepassword.view.activity.GesturepwActivity;
import com.karazam.huashanapp.my.security.main.model.databinding.SecurityEntity;
import com.karazam.huashanapp.my.security.main.view.SecurityView;
import com.karazam.huashanapp.my.security.main.view.activity.SecurityActivity;
import com.karazam.huashanapp.my.security.paymentpassword.view.activity.PaymentpwActivity;

/**
 * Created by Administrator on 2016/11/28.
 */

public class SecurityViewModelImpl extends SecurityViewModel {

    private SecurityView mView;
    private SecurityEntity mEntiyt;
    private Context context;
    private SecurityActivity activity;

    public SecurityViewModelImpl(SecurityView mView, SecurityEntity mEntiyt, Context context, SecurityActivity activity) {
        this.mView = mView;
        this.mEntiyt = mEntiyt;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 支付密码
     * @param view
     */
    @Override
    public void onPayment(View view) {
        mView.toOtherActivity(activity, PaymentpwActivity.class);
    }

    /**
     * 手势密码
     * @param view
     */
    @Override
    public void onGesture(View view) {
        mView.toOtherActivity(activity, GesturepwActivity.class);
    }
}
