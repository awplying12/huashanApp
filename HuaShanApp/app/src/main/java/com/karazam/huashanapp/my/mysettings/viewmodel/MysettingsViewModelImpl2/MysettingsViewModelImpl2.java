package com.karazam.huashanapp.my.mysettings.viewmodel.MysettingsViewModelImpl2;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.mysettings.model.databinding.MysettingsEntity;
import com.karazam.huashanapp.my.mysettings.view.MysettingsView2;
import com.karazam.huashanapp.my.mysettings.view.activity.MysettingsActivity2;

/**
 * Created by Administrator on 2016/11/23.
 */

public class MysettingsViewModelImpl2 extends MysettingsViewModel2 {

    private MysettingsView2 mView;
    private MysettingsEntity mEntity;
    private Context context;
    private MysettingsActivity2 activity;

    public MysettingsViewModelImpl2(MysettingsView2 mView, MysettingsEntity mEntity, Context context, MysettingsActivity2 activity) {
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
