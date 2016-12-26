package com.karazam.huashanapp.today.main.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;

import com.karazam.huashanapp.home.view.activity.HomeActivity;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.today.calendar.view.activity.CalendarActivity;
import com.karazam.huashanapp.today.main.model.databinding.TodayBean;
import com.karazam.huashanapp.today.main.model.databinding.TodayEntity;
import com.karazam.huashanapp.today.main.model.retrofit.TaodayDataSource;
import com.karazam.huashanapp.today.main.view.TodayView;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2016/11/2.
 */

public class TodayViewModelImpl extends TodayViewModel {

    private TodayEntity mEntity;
    private TodayView mView;
    private Context context;
    private Activity activity;

    private TaodayDataSource dataSource;

    public TodayViewModelImpl(TodayEntity mEntity, TodayView mView, Context context, Activity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;

        dataSource = new TaodayDataSource();

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
     * 日历界面
     * @param view
     */
    @Override
    public void toCalendar(View view) {
        mView.toOtherActivity(activity, CalendarActivity.class);
    }

    /**
     * 产融货
     * @param view
     */
    @Override
    public void onIndustryAndFinance(View view) {

        HomeActivity act = (HomeActivity) activity;
        act.setCurrentItem(1,0);
    }

    /**
     * 优企宝
     * @param view
     */
    @Override
    public void onExcellentprices(View view) {

        HomeActivity act = (HomeActivity) activity;
        act.setCurrentItem(1,1);
    }

    /**
     * 保理贷
     * @param view
     */
    @Override
    public void onFactoringloan(View view) {

        HomeActivity act = (HomeActivity) activity;
        act.setCurrentItem(1,2);
    }

    /**
     * 全部理财
     * @param view
     */
    @Override
    public void onAllFinance(View view) {

        HomeActivity act = (HomeActivity) activity;
        act.setCurrentItem(1,0);
    }

    /**
     * 每日精选详情
     * @param view
     */
    @Override
    public void onEverydayDetails(View view) {
        mView.showToast("每日精选详情");
    }

    /**
     * 每日精选立即购买
     * @param view
     */
    @Override
    public void onBuyImmediately(View view) {
        mView.showToast("每日精选立即购买");
    }



    /**
     * 积分商城更多
     * @param view
     */
    @Override
    public void onIntegralMore(View view) {
        mView.showToast("onIntegralMore");
    }


    /**
     * 获取数据
     */
    @Override
    public void getData() {

        dataSource.getTodayData().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<TodayBean>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("TodayData","e  :  "+e.toString());
                mView.getTodayDataFaile(e.toString());
            }

            @Override
            public void onNext(BaseReturn<TodayBean> todayBeanBaseReturn) {

                if(todayBeanBaseReturn.isSuccess()){
                    Log.i("TodayData",todayBeanBaseReturn.getData().toString());
                    mView.getTodayDataSuccess(todayBeanBaseReturn.getData());
                }else {
                    mView.getTodayDataFaile(todayBeanBaseReturn.getMessage());
                }

            }
        });
    }

}
