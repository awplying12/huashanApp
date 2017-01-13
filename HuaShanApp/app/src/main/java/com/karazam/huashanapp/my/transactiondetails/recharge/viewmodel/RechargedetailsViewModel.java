package com.karazam.huashanapp.my.transactiondetails.recharge.viewmodel;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/12/2.
 */

public abstract class RechargedetailsViewModel extends BaseViewModel{

    public String orderNo,orderId,type;

    public abstract void getRechargedetails();
}
