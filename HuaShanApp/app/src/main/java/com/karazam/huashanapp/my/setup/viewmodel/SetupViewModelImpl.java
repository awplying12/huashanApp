package com.karazam.huashanapp.my.setup.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.mysettings.view.activity.MysettingsActivity;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupEntity;
import com.karazam.huashanapp.my.setup.view.SetupView;
import com.karazam.huashanapp.my.setup.view.activity.SetupActivity;

/**
 * Created by Administrator on 2016/11/22.
 */

public class SetupViewModelImpl extends SetupViewModel {

    private SetupView mView;
    private SetupEntity mEntity;
    private Context context;
    private SetupActivity activity;

    public SetupViewModelImpl(SetupView mView, SetupEntity mEntity, Context context, SetupActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    @Override
    public void toMysettings(View view) {
        mView.toOtherActivity(activity, MysettingsActivity.class);
    }
}
