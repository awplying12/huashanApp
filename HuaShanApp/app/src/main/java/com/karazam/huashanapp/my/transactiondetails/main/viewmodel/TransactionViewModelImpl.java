package com.karazam.huashanapp.my.transactiondetails.main.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactionEntity;
import com.karazam.huashanapp.my.transactiondetails.main.view.TransactionView;
import com.karazam.huashanapp.my.transactiondetails.main.view.activity.TransactionActivity;

/**
 * Created by Administrator on 2016/12/8.
 */

public class TransactionViewModelImpl extends TransactionViewModel {

    private TransactionView mView;
    private TransactionEntity mEntity;
    private Context context;
    private TransactionActivity activity;

    public TransactionViewModelImpl(TransactionView mView, TransactionEntity mEntity, Context context, TransactionActivity activity) {
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
