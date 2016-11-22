package com.karazam.huashanapp.my.mysettings.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.mysettings.model.databinding.MysettingsEntity;
import com.karazam.huashanapp.my.mysettings.view.MysettingsView;
import com.karazam.huashanapp.my.mysettings.view.activity.MysettingsActivity;

/**
 * Created by Administrator on 2016/11/22.
 */

public class MysettingsViewModelImpl extends MysettingsViewModel {

    private MysettingsView mView;
    private MysettingsEntity mEntity;
    private Context context;
    private MysettingsActivity activity;

    public MysettingsViewModelImpl(MysettingsView mView, MysettingsEntity mEntity, Context context, MysettingsActivity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    /**
     * 修改头像
     * @param view
     */
    @Override
    public void setupHeader(View view) {
            mView.showToast("setupHeader");
    }
}