package com.karazam.huashanapp.user.register.viewmodel.Register3ViewModel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.user.register.model.databinbing.Register3Entity;
import com.karazam.huashanapp.user.register.view.Register3View;
import com.karazam.huashanapp.user.register.view.activity.Register3Activity;
import com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel.Register1ViewModel;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register3ViewModelImpl extends Register3ViewModel {

    private Register3View mView;
    private Register3Entity mEntity;
    private Context context;
    private Register3Activity activity;

    public Register3ViewModelImpl(Register3View mView, Register3Entity mEntity, Context context, Register3Activity activity) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        activity.FinishActivity(activity);
    }
}
