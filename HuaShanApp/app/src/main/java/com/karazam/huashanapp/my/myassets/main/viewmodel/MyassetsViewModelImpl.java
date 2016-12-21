package com.karazam.huashanapp.my.myassets.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.myassets.main.model.databinding.MyassetsEntity;
import com.karazam.huashanapp.my.myassets.main.view.MyassetsView;
import com.karazam.huashanapp.my.myassets.main.view.activity.MyassetsActivity;

/**
 * Created by Administrator on 2016/12/20.
 */

public class MyassetsViewModelImpl extends MyassetsViewModel {

    private MyassetsView mView;
    private MyassetsEntity mEntity;
    private MyassetsActivity activity;
    private Context context;

    public MyassetsViewModelImpl(MyassetsView mView, MyassetsEntity mEntity, MyassetsActivity activity, Context context) {
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
