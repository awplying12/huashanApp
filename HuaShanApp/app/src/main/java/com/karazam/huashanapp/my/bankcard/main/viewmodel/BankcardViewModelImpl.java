package com.karazam.huashanapp.my.bankcard.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.bankcard.main.model.databinding.BankcardEntity;
import com.karazam.huashanapp.my.bankcard.main.view.BankcardView;
import com.karazam.huashanapp.my.bankcard.main.view.activity.BankcardActivity;

/**
 * Created by Administrator on 2016/12/26.
 */

public class BankcardViewModelImpl extends BankcardViewModel{

    private BankcardView mView;
    private BankcardEntity mEntity;
    private Context context;
    private BankcardActivity activity;

    public BankcardViewModelImpl(BankcardView mView, BankcardEntity mEntity, Context context, BankcardActivity activity) {
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
