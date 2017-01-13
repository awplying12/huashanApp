package com.karazam.huashanapp.my.transactiondetails.withdrawals.viewmodel;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/12/1.
 */

public abstract class WithdrawalsdetailsViewModel extends BaseViewModel{

    public String orderNo,orderId,type;

    public abstract void getWithdrawalsdetails();

}
