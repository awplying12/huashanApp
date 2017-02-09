package com.karazam.huashanapp.my.transactiondetails.main.viewmodel;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/12/8.
 */

public abstract class TransactionViewModel extends BaseViewModel {

    public static int allpage = 1;

    public abstract void getTransaction(final int page,final boolean isfirst);
}
