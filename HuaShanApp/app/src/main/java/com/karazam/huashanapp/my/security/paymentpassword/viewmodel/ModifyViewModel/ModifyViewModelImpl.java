package com.karazam.huashanapp.my.security.paymentpassword.viewmodel.ModifyViewModel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.security.paymentpassword.model.databinding.PaymentpwEntity;
import com.karazam.huashanapp.my.security.paymentpassword.view.ModifyView;
import com.karazam.huashanapp.my.security.paymentpassword.view.activity.ModifyActivity;
import com.karazam.huashanapp.my.security.paymentpassword2.view.activity.VerifyActivity;

/**
 * Created by Administrator on 2016/11/28.
 */

public class ModifyViewModelImpl extends ModifyViewModel {

    private ModifyView mView;
    private PaymentpwEntity mEntity;
    private Context context;
    private ModifyActivity activity;

    public ModifyViewModelImpl(ModifyView mView, PaymentpwEntity mEntity, Context context, ModifyActivity activity) {
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
