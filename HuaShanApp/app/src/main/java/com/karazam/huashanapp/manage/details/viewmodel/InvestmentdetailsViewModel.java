package com.karazam.huashanapp.manage.details.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/11/8.
 */

public abstract class InvestmentdetailsViewModel extends BaseViewModel {

    public String borrowingId;

    public abstract void onInformation(View view);

    public abstract void onRecord(View view);

    public abstract void onSpeed(View view);

    public abstract void toPurchase(View view);

    public abstract void getManagedetailsData(String projectId);
}
