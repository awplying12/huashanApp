package com.karazam.huashanapp.apply.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.apply.model.databinding.ApplyEntity;
import com.karazam.huashanapp.apply.view.ApplyView;

/**
 * Created by Administrator on 2016/10/11.
 */

public class ApplyViewModelImpl extends ApplyViewModel {

    private ApplyEntity mEntity;
    private ApplyView mView;
    private Context context;
    private Activity activity;

    public ApplyViewModelImpl(ApplyEntity mEntity, ApplyView mView, Context context, Activity activity) {
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
