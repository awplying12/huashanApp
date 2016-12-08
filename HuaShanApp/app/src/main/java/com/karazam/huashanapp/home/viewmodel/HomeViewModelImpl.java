package com.karazam.huashanapp.home.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.home.model.databinding.HomeEntity;
import com.karazam.huashanapp.home.view.HomeView;
import com.karazam.huashanapp.home.view.activity.HomeActivity;

/**
 * Created by Administrator on 2016/10/11.
 */

public class HomeViewModelImpl extends HomeViewModel {

    private HomeView mView;
    private HomeEntity mEntity;
    private HomeActivity activity;
    private Context context;

    public HomeViewModelImpl(HomeView mView, HomeEntity mEntity, HomeActivity activity, Context context) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 金融Fragmetn
     * @param view
     */
    @Override
    public void toFinanec(View view) {
//        mView.setViewPagerCurrentItem(0,"金融");
    }


    /**
     * 今日Fragmetn
     * @param view
     */
    @Override
    public void toToday(View view) {
        mView.setViewPagerCurrentItem(0,"今日");
    }

    /**
     * 理财Fragment
     * @param view
     */
    @Override
    public void toManage(View view) {
        mView.setViewPagerCurrentItem(1,"理财");
    }

    /**
     * 积分Fragment
     * @param view
     */
    @Override
    public void toApply(View view) {
//        mView.setViewPagerCurrentItem(2,"申请");
    }

    /**
     * 我的Fragment
     * @param view
     */
    @Override
    public void toMy(View view) {
//        switch (HuaShanApplication.loginStatus){
//            case 1:
//                mView.setViewPagerCurrentItem(3,"我的");
//                break;
//            default:
//                mView.toLoginActivity();
//                break;
//        }

        mView.setViewPagerCurrentItem(2,"我的");
    }

    @Override
    public void setUp(View view) {
        HuaShanApplication.editor.putInt("loginStatus",2).commit();
        HuaShanApplication.loginStatus = 2;
        mView.setViewPagerCurrentItem(0,"今日");
    }
}
