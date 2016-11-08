package com.karazam.huashanapp.manage.main.viewmodel;

import android.app.Activity;
import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.manage.main.model.databinding.ManageEntity;
import com.karazam.huashanapp.manage.main.view.ManageView;

/**
 * Created by Administrator on 2016/10/11.
 */

public class ManageViewModelImpl extends ManageViewModel {

    private ManageEntity mEntity;
    private ManageView mView;
    private Context context;
    private Activity activity;

    public ManageViewModelImpl(ManageEntity mEntity, ManageView mView, Context context, Activity activity) {
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
