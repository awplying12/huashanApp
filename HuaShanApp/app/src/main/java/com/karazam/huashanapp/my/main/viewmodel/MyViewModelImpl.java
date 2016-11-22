package com.karazam.huashanapp.my.main.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.main.model.databinding.MyEntity;
import com.karazam.huashanapp.my.main.view.MyView;
import com.karazam.huashanapp.my.setup.view.activity.SetupActivity;

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


    @Override
    public void setUp(View view) {
            mView.toOtherActivity(activity, SetupActivity.class);
    }
}
