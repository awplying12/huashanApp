package com.karazam.huashanapp.manage.purchase.viewmodel;

import android.content.Context;
import android.view.View;

import com.example.paymentpassword.PasswordView;
import com.karazam.huashanapp.R;

import com.karazam.huashanapp.main.dialog.SMSauthenticationView;
import com.karazam.huashanapp.manage.paymentmod.view.activity.PaymentmodActivity;
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

    /**
     * 更多支付方式
     * @param view
     */
    @Override
    public void onPaymentMethod(View view) {
        mView.showToast("更多支付方式");
        mView.toOtherActivity(activity, PaymentmodActivity.class);
    }

    /**
     * 购买
     * @param view
     */

    @Override
    public void onPurchase(View view) {
        mView.showToast("购买");

        mView.addSMSView();
    }

    /**
     * 投资协议
     * @param view
     */
    @Override
    public void onAgreement(View view) {
        mView.showToast("投资协议");
    }


}