package com.karazam.huashanapp.my.security.findgesturepassword.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.security.findgesturepassword.model.databinding.FindgesturepasswordEntity;
import com.karazam.huashanapp.my.security.findgesturepassword.view.FindgesturepasswordView;
import com.karazam.huashanapp.my.security.findgesturepassword.view.activity.FindgesturepasswordActivity;

/**
 * Created by Administrator on 2016/12/15.
 */

public class FindgesturepasswordViewModelImpl extends FindgesturepasswordViewModel {

    private FindgesturepasswordView mView;
    private FindgesturepasswordEntity mEntity;
    private Context context;
    private FindgesturepasswordActivity activity;

    public FindgesturepasswordViewModelImpl(FindgesturepasswordView mView, FindgesturepasswordEntity mEntity, Context context, FindgesturepasswordActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }
}
