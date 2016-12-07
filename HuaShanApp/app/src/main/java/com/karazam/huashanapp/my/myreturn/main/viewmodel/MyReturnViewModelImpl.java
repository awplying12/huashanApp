package com.karazam.huashanapp.my.myreturn.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.myreturn.main.model.databinding.MyReturnEntity;
import com.karazam.huashanapp.my.myreturn.main.view.MyReturnView;
import com.karazam.huashanapp.my.myreturn.main.view.activity.MyReturnActivity;

/**
 * Created by Administrator on 2016/12/7.
 */

public class MyReturnViewModelImpl extends MyReturnViewModel {

    private MyReturnEntity mEntity;
    private MyReturnView mView;
    private Context context;
    private MyReturnActivity activity;

    public MyReturnViewModelImpl(MyReturnEntity mEntity, MyReturnView mView, Context context, MyReturnActivity activity) {
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
