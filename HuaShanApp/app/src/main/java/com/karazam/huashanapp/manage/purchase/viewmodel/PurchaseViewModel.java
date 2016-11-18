package com.karazam.huashanapp.manage.purchase.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/11/15.
 */

public abstract class PurchaseViewModel extends BaseViewModel {

    public abstract void onPaymentMethod(View view);

    public abstract void onPurchase(View view);

    public abstract void onAgreement(View view);


}
