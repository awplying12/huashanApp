package com.karazam.huashanapp.my.main.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.main.model.databinding.MyEntity;
import com.karazam.huashanapp.my.main.view.MyView;
import com.karazam.huashanapp.my.recharge.main.view.activity.RechargeActivity;
import com.karazam.huashanapp.my.setup.view.activity.SetupActivity;
import com.karazam.huashanapp.my.withdrawals.main.view.activity.WithdrawalsActivity;

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
     * 设置
     * @param view
     */
    @Override
    public void setUp(View view) {
            mView.toOtherActivity(activity, SetupActivity.class);
    }

    /**
     * 提现
     * @param view
     */
    @Override
    public void Withdrawals(View view) {
        mView.toOtherActivity(activity, WithdrawalsActivity.class);
    }

    /**
     * 充值
     * @param view
     */
    @Override
    public void Recharge(View view) {
        mView.toOtherActivity(activity, RechargeActivity.class);
    }
}
