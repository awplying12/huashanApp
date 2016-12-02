package com.karazam.huashanapp.my.transactiondetails.recharge.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.transactiondetails.recharge.model.datatbinding.RechargedetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.recharge.view.RechargedetailsView;
import com.karazam.huashanapp.my.transactiondetails.recharge.view.activity.RechargedetailsActivity;

/**
 * Created by Administrator on 2016/12/2.
 */

public class RechargedetailsViewModelImpl extends RechargedetailsViewModel {

    private RechargedetailsView mView;
    private RechargedetailsEntity mEntity;
    private Context context;
    private RechargedetailsActivity activity;

    public RechargedetailsViewModelImpl(RechargedetailsView mView, RechargedetailsEntity mEntity, Context context, RechargedetailsActivity activity) {
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
