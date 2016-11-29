package com.karazam.huashanapp.my.security.paymentpassword2.viewmodel.VerifyViewModel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.security.paymentpassword2.model.databinding.VerifyEntity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.VerifyView;
import com.karazam.huashanapp.my.security.paymentpassword2.view.activity.VerifyActivity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.activity.VerifyActivity2;

/**
 * Created by Administrator on 2016/11/28.
 */

public class VerifyViewModelImpl extends VerifyViewModel {

    private VerifyView mView;
    private VerifyEntity mEntity;
    private Context context;
    private VerifyActivity activity;

    public VerifyViewModelImpl(VerifyView mView, VerifyEntity mEntity, Context context, VerifyActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 手机验证
     * @param view
     */
    @Override
    public void phoneVerification(View view) {
        mView.addSMS();
    }

    /**
     * 身份验证
     * @param view
     */
    @Override
    public void IDVerification(View view) {
        mView.toOtherActivity(activity, VerifyActivity2.class);
    }
}
