package com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.user.register.model.databinbing.Register1Entity;
import com.karazam.huashanapp.user.register.view.Register1View;
import com.karazam.huashanapp.user.register.view.activity.Register1Activity;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register1ViewModelImpl extends Register1ViewModel {

    private Register1Entity mEntity;
    private Register1View mView;
    private Context context;
    private Register1Activity activity;

    public Register1ViewModelImpl(Register1Entity mEntity, Register1View mView, Context context, Register1Activity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        activity.FinishActivity(activity);
    }
}
