package com.karazam.huashanapp.my.transactiondetails.main.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactionBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/12.
 */

public class TransactionDataSource extends BaseDataSource {

    TransactionApi service = retrofit1.create(TransactionApi.class);

    public Observable<BaseReturn<TransactionBean>> getTransactionData(String page){
        return service.getTransactionData(page, HuaShanApplication.token,"XMLHttpRequest");
    }
}
