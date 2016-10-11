package com.karazam.huashanapp.home.viewmodel;

import android.content.Context;
import android.view.View;

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
}
