package com.karazam.huashanapp.my.help.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.help.main.model.databinding.HelpEntity;
import com.karazam.huashanapp.my.help.main.view.HelpView;
import com.karazam.huashanapp.my.help.main.view.activity.HelpActivity;

/**
 * Created by Administrator on 2016/11/30.
 */

public class HelpViewModelImpl extends HelpViewModel{

    private HelpView mView;
    private HelpEntity mEntity;
    private Context context;
    private HelpActivity activity;

    public HelpViewModelImpl(HelpView mView, HelpEntity mEntity, Context context, HelpActivity activity) {
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
    public void getUrl() {
        mView.setWeb("https://www.huashanjinrong.com/upload/image/201606/a26a945d-2b79-4ce3-a7f6-993b3f24f1e0.png");
    }
}
