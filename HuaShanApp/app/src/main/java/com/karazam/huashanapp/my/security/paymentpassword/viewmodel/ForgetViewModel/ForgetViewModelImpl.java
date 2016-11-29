package com.karazam.huashanapp.my.security.paymentpassword.viewmodel.ForgetViewModel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.security.paymentpassword.model.databinding.PaymentpwEntity;
import com.karazam.huashanapp.my.security.paymentpassword.view.ForgetView;
import com.karazam.huashanapp.my.security.paymentpassword.view.activity.ForgetActivity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.activity.VerifyActivity;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ForgetViewModelImpl extends ForgetViewModel{

    private ForgetView mView;
    private PaymentpwEntity mEntity;
    private Context context;
    private ForgetActivity activity;

    public ForgetViewModelImpl(ForgetView mView, PaymentpwEntity mEntity, Context context, ForgetActivity activity) {
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
     * 下一步
     * @param view
     */
    @Override
    public void onNextstep(View view) {
        mView.toOtherActivity(activity, VerifyActivity.class);
    }
}
