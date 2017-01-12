package com.karazam.huashanapp.my.transactiondetails.main.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactiondetailsBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/12.
 */

public class TransactiondetailsDataSource extends BaseDataSource{

    TransactiondetailsApi service = retrofit1.create(TransactiondetailsApi.class);

    public Observable<BaseReturn<TransactiondetailsBean>> getTransactiondetails(String orderNo,String type){
        return service.getTransactiondetails(orderNo,type, HuaShanApplication.token,"XMLHttpRequest");
    }
}
