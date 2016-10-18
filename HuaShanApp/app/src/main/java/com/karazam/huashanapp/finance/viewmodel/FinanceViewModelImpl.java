package com.karazam.huashanapp.finance.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.karazam.huashanapp.finance.model.databinding.FinanceEntity;
import com.karazam.huashanapp.finance.view.FinanceView;
import com.karazam.huashanapp.user.login.view.activity.LoginActivity;

/**
 * Created by Administrator on 2016/10/11.
 */

public class FinanceViewModelImpl extends FinanceViewModel {

    private FinanceEntity mEntity;
    private FinanceView mView;
    private Context context;
    private Activity activity;

    public FinanceViewModelImpl(FinanceEntity mEntity, FinanceView mView, Context context, Activity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
            mView.FinishActivity(activity);
    }

    /**
     * 投资计算器
     * @param view
     */
    @Override
    public void onCalculator(View view) {
        mView.showToast("onCalculator");
        Intent intent = new Intent(activity, LoginActivity.class);
        activity.startActivity(intent);

    }

    /**
     * 立即投资
     * @param view
     */
    @Override
    public void onInvestNow(View view) {
        mView.showToast("onInvestNow");
    }


}
