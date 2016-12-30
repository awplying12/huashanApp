package com.karazam.huashanapp.my.bankcard.bindcard.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.main.dialog.PromptDialog;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardEntity;
import com.karazam.huashanapp.my.bankcard.bindcard.view.BindcardView;
import com.karazam.huashanapp.my.bankcard.bindcard.view.activity.BindcardActivity;
import com.karazam.huashanapp.my.realname.view.activity.UnauthorizedActivity;

/**
 * Created by Administrator on 2016/12/30.
 */

public class BindcardViewModelImpl extends BindcardViewModel {

    private BindcardView mView;
    private BindcardEntity mEntity;
    private Context context;
    private BindcardActivity activity;




    public BindcardViewModelImpl(BindcardView mView, BindcardEntity mEntity, Context context, BindcardActivity activity) {
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
