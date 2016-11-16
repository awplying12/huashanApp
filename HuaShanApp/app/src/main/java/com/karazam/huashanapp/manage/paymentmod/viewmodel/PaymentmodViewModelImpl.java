package com.karazam.huashanapp.manage.paymentmod.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.manage.paymentmod.model.databinding.PaymentmodEntity;
import com.karazam.huashanapp.manage.paymentmod.view.PaymentmodView;
import com.karazam.huashanapp.manage.paymentmod.view.activity.PaymentmodActivity;

/**
 * Created by Administrator on 2016/11/16.
 */

public class PaymentmodViewModelImpl extends PaymentmodViewModel{

    private PaymentmodEntity mEntity;
    private PaymentmodView mView;
    private Context context;
    private PaymentmodActivity activity;

    public PaymentmodViewModelImpl(PaymentmodEntity mEntity, PaymentmodView mView, Context context, PaymentmodActivity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }
}
