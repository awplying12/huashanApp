package com.karazam.huashanapp.finance.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.finance.model.databinding.FinanceEntity;
import com.karazam.huashanapp.finance.view.FinanceView;

/**
 * Created by Administrator on 2016/10/11.
 */

public class FinanceViewModelImpl extends FinanceViewModel {

    private FinanceEntity mEntity;
    private FinanceView mView;
    private Context context;

    public FinanceViewModelImpl(FinanceEntity mEntity, FinanceView mView, Context context) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void onBack(View view) {

    }
}
