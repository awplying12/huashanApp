package com.karazam.huashanapp.my.transactiondetails.withdrawals.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.transactiondetails.withdrawals.model.databinding.WithdrawalsdetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.WithdrawalsdetailsView;
import com.karazam.huashanapp.my.transactiondetails.withdrawals.view.activity.WithdrawalsdetailsActivity;

/**
 * Created by Administrator on 2016/12/1.
 */

public class WithdrawalsdetailsViewModelImpl extends WithdrawalsdetailsViewModel {


    private WithdrawalsdetailsView mView;
    private WithdrawalsdetailsEntity mEntity;
    private Context context;
    private WithdrawalsdetailsActivity activity;

    public WithdrawalsdetailsViewModelImpl(WithdrawalsdetailsView mView, WithdrawalsdetailsEntity mEntity, Context context, WithdrawalsdetailsActivity activity) {
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
