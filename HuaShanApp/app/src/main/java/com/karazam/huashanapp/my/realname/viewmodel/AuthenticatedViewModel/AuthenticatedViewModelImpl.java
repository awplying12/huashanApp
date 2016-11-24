package com.karazam.huashanapp.my.realname.viewmodel.AuthenticatedViewModel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.realname.model.databinding.RealnameEntity;
import com.karazam.huashanapp.my.realname.view.AuthenticatedView;
import com.karazam.huashanapp.my.realname.view.activity.AuthenticatedActivity;

/**
 * Created by Administrator on 2016/11/24.
 */

public class AuthenticatedViewModelImpl extends AuthenticatedViewModel {

    private AuthenticatedView mView;
    private RealnameEntity mEntity;
    private Context context;
    private AuthenticatedActivity activity;

    public AuthenticatedViewModelImpl(AuthenticatedView mView, RealnameEntity mEntity, Context context, AuthenticatedActivity activity) {
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
