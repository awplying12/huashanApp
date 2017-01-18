package com.karazam.huashanapp.my.about.main.viewmodel.AboutViewModel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.about.main.model.databinding.AboutEntity;
import com.karazam.huashanapp.my.about.main.view.AboutView;
import com.karazam.huashanapp.my.about.main.view.activity.AboutActivity;

/**
 * Created by Administrator on 2016/11/29.
 */

public class AboutViewModelImpl extends AboutViewModel {

    private AboutView mView;
    private AboutEntity mEntity;
    private Context context;
    private AboutActivity activity;

    public AboutViewModelImpl(AboutView mView, AboutEntity mEntity, Context context, AboutActivity activity) {
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
    public void updata(View view) {

        updata.checkNewestVersion();
    }
}
