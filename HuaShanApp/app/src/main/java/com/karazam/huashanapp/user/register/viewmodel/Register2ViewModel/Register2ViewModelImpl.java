package com.karazam.huashanapp.user.register.viewmodel.Register2ViewModel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.user.register.model.databinbing.Register2Entity;
import com.karazam.huashanapp.user.register.view.Register2View;
import com.karazam.huashanapp.user.register.view.activity.Register2Activity;

/**
 * Created by Administrator on 2016/10/20.
 */

public class Register2ViewModelImpl extends Register2ViewModel{

    private Register2View mView;
    private Register2Entity mEntity;
    private Context context;
    private Register2Activity activity;

    public Register2ViewModelImpl(Register2View mView, Register2Entity mEntity, Context context, Register2Activity activity) {
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
