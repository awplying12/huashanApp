package com.karazam.huashanapp.my.transactiondetails.investment.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.transactiondetails.investment.model.databinding.InvestmentEntity;
import com.karazam.huashanapp.my.transactiondetails.investment.view.InvestmentView;
import com.karazam.huashanapp.my.transactiondetails.investment.view.activity.InvestmentActivity;

/**
 * Created by Administrator on 2016/12/9.
 */

public class InvestmentViewModelImpl extends InvestmentViewModel {

    private InvestmentView mView;
    private InvestmentEntity mEntity;
    private Context context;
    private InvestmentActivity activity;

    public InvestmentViewModelImpl(InvestmentView mView, InvestmentEntity mEntity, Context context, InvestmentActivity activity) {
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
