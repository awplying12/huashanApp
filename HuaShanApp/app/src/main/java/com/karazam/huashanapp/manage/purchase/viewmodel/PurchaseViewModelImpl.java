package com.karazam.huashanapp.manage.purchase.viewmodel;

import android.content.Context;
import android.view.View;

import com.karazam.huashanapp.manage.purchase.model.databinding.PurchaseEntity;
import com.karazam.huashanapp.manage.purchase.view.PurchaseView;
import com.karazam.huashanapp.manage.purchase.view.activity.PurchaseActivity;

/**
 * Created by Administrator on 2016/11/15.
 */

public class PurchaseViewModelImpl extends PurchaseViewModel {

    private PurchaseEntity mEntity;
    private PurchaseView mView;
    private Context context;
    private PurchaseActivity activity;

    public PurchaseViewModelImpl(PurchaseEntity mEntity, PurchaseView mView, Context context, PurchaseActivity activity) {
        this.mEntity = mEntity;
        this.mView = mView;
        this.context = context;
        this.activity = activity;
    }

    @Override
    public void onBack(View view) {
        mView.FinishActivity(activity);
    }
}
