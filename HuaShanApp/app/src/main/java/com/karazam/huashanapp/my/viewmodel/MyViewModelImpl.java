package com.karazam.huashanapp.my.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.manage.model.databinding.ManageEntity;
import com.karazam.huashanapp.my.model.databinding.MyEntity;
import com.karazam.huashanapp.my.view.MyView;

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
}
