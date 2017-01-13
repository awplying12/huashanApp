package com.karazam.huashanapp.my.transactiondetails.myreturn.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.transactiondetails.myreturn.mode.databinding.MyreturndetailsEntity;
import com.karazam.huashanapp.my.transactiondetails.myreturn.view.MyreturndetailsView;
import com.karazam.huashanapp.my.transactiondetails.myreturn.view.activity.MyreturndetailsActivity;

/**
 * Created by Administrator on 2017/1/13.
 */

public class MyreturndetailsViewModelImpl extends MyreturndetailsViewModel {

    private MyreturndetailsView mView;
    private MyreturndetailsEntity mEntity;
    private MyreturndetailsActivity activity;
    private Context context;

    public MyreturndetailsViewModelImpl(MyreturndetailsView mView, MyreturndetailsEntity mEntity, MyreturndetailsActivity activity, Context context) {
        this.mView = mView;
        this.mEntity = mEntity;
        this.activity = activity;
        this.context = context;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }


}
