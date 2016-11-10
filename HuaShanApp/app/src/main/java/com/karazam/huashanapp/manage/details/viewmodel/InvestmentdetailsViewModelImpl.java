package com.karazam.huashanapp.manage.details.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.manage.details.model.databinding.InvestmentdetailsEntity;

import com.karazam.huashanapp.manage.details.view.InvestmentdetailsView;
import com.karazam.huashanapp.manage.details.view.activity.InvestmentdetailsActivity;

/**
 * Created by Administrator on 2016/11/8.
 */

public class InvestmentdetailsViewModelImpl extends InvestmentdetailsViewModel {

    private InvestmentdetailsEntity mEntity;
    private InvestmentdetailsView mView;
    private Context context;
    private InvestmentdetailsActivity activity;

    public InvestmentdetailsViewModelImpl(InvestmentdetailsEntity mEntity, InvestmentdetailsView mView, Context context, InvestmentdetailsActivity activity) {
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
