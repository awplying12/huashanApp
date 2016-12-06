package com.karazam.huashanapp.my.myfinancing.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.financialproject.FinancialProject;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceEntity;
import com.karazam.huashanapp.my.myfinancing.main.view.MyfinanceView;
import com.karazam.huashanapp.my.myfinancing.main.view.activity.MyfinanceActivity;
import com.ogaclejapan.rx.binding.Rx;
import com.ogaclejapan.rx.binding.RxView;

/**
 * Created by Administrator on 2016/12/5.
 */

public class MyfinanceViewModelImpl extends MyfinanceViewModel {

    private MyfinanceEntity mEntity;
    private MyfinanceView mView;
    private Context context;
    private MyfinanceActivity activity;

    public MyfinanceViewModelImpl(MyfinanceEntity mEntity, MyfinanceView mView, Context context, MyfinanceActivity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }

    @Override
    public void Finanec(View view) {

        if(isEmpty){
            mView.showToast("立即前往购买");
        }else {
            mView.showToast("买入");
        }

    }
}
