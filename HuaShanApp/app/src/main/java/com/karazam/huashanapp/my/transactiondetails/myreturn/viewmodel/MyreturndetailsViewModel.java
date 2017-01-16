package com.karazam.huashanapp.my.transactiondetails.myreturn.viewmodel;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2017/1/13.
 */

public abstract class MyreturndetailsViewModel extends BaseViewModel {

    public String orderNo,orderId,type;

    public abstract void getMyreturndetails();
}
