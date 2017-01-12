package com.karazam.huashanapp.my.transactiondetails.main.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactionBean;

/**
 * Created by Administrator on 2016/12/8.
 */

public interface TransactionView extends BaseView{

    void getTransactionSuccess(TransactionBean bean);

    void getTransactionFail(String s);

    void getTransactionError(Throwable e);
}
