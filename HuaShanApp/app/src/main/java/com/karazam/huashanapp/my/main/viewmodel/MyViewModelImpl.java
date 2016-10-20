package com.karazam.huashanapp.my.main.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.main.model.databinding.MyEntity;
import com.karazam.huashanapp.my.main.view.MyView;

/**
 * Created by Administrator on 2016/10/12.
 */

public class MyViewModelImpl extends MyViewModel {

    private MyEntity mEntity;
    private MyView mView;
    private Context context;
    private Activity activity;

    public MyViewModelImpl(MyEntity mEntity, MyView mView, Context context, Activity activity) {
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
     * 安全中心
     * @param view
     */
    @Override
    public void toSecurityCenter(View view) {
        mView.showToast("安全中心");
    }

    /**
     * 交易记录
     * @param view
     */
    @Override
    public void toTransactionRecord(View view) {
        mView.showToast("交易记录");
    }

    /**
     * 借款管理
     * @param view
     */
    @Override
    public void toLoanManagement(View view) {
        mView.showToast("借款管理");
    }

    /**
     * 投资管理
     * @param view
     */
    @Override
    public void toInvestmentManagement(View view) {
        mView.showToast("投资管理");
    }

    /**
     * 银行卡管理
     * @param view
     */
    @Override
    public void toBankcardManagement(View view) {
        mView.showToast("银行卡管理");
    }

    /**
     * 推荐管理
     * @param view
     */
    @Override
    public void toRecommendedManagement(View view) {
        mView.showToast("推荐管理");
    }

    /**
     * 我的优惠劵
     * @param view
     */
    @Override
    public void toDiscountCoupons(View view) {
        mView.showToast("我的优惠劵");
    }
}
