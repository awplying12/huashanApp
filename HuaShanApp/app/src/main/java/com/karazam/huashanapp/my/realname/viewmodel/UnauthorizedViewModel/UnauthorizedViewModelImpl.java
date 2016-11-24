package com.karazam.huashanapp.my.realname.viewmodel.UnauthorizedViewModel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.realname.model.databinding.RealnameEntity;
import com.karazam.huashanapp.my.realname.view.UnauthorizedView;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;

/**
 * Created by Administrator on 2016/11/24.
 */

public class UnauthorizedViewModelImpl extends UnauthorizedViewModel {

    private UnauthorizedView mView;
    private RealnameEntity mEntity;
    private Context context;
    private UnauthorizedActivity activity;

    public UnauthorizedViewModelImpl(UnauthorizedView mView, RealnameEntity mEntity, Context context, UnauthorizedActivity activity) {
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
