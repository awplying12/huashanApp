package com.karazam.huashanapp.my.recharge.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.recharge.main.model.databinding.RechargeEntity;
import com.karazam.huashanapp.my.recharge.main.view.RechargeView;
import com.karazam.huashanapp.my.recharge.main.view.activity.RechargeActivity;

/**
 * Created by Administrator on 2016/12/1.
 */

public class RechargeViewModelImpl extends RechargeViewModel {

    private RechargeView mView;
    private RechargeEntity mEntity;
    private Context context;
    private RechargeActivity activity;

    public RechargeViewModelImpl(RechargeView mView, RechargeEntity mEntity, Context context, RechargeActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }
}
