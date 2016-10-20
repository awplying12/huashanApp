package com.karazam.huashanapp.my.main.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/10/12.
 */

public abstract class MyViewModel extends BaseViewModel{

    public abstract void toSecurityCenter(View view);

    public abstract void toTransactionRecord(View view);

    public abstract void toLoanManagement(View view);

    public abstract void toInvestmentManagement(View view);

    public abstract void toBankcardManagement(View view);

    public abstract void toRecommendedManagement(View view);

    public abstract void toDiscountCoupons(View view);



}
