package com.karazam.huashanapp.today.main.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.home.view.activity.HomeActivity;
import com.karazam.huashanapp.today.main.model.databinding.TodayEntity;
import com.karazam.huashanapp.today.main.view.TodayView;

/**
 * Created by Administrator on 2016/11/2.
 */

public class TodayViewModelImpl extends TodayViewModel {

    private TodayEntity mEntity;
    private TodayView mView;
    private Context context;
    private Activity activity;

    public TodayViewModelImpl(TodayEntity mEntity, TodayView mView, Context context, Activity activity) {
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
     * 头像
     * @param view
     */
    @Override
    public void onHead(View view) {
        mView.showToast("onHead");
        HomeActivity act = (HomeActivity) activity;
        act.toLoginActivity();
    }

    /**
     * 产融货
     * @param view
     */
    @Override
    public void onIndustryAndFinance(View view) {
        mView.showToast("onIndustryAndFinance");

    }

    /**
     * 优企宝
     * @param view
     */
    @Override
    public void onExcellentprices(View view) {
        mView.showToast("onExcellentprices");
    }

    /**
     * 全部理财
     * @param view
     */
    @Override
    public void onAllFinance(View view) {
        mView.showToast("onAllFinance");
    }
}
