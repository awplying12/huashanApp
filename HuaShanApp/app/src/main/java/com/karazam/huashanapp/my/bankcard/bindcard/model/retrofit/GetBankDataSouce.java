package com.karazam.huashanapp.my.bankcard.bindcard.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BankBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/4.
 */

public class GetBankDataSouce extends BaseDataSource{

    GetBankApi service = retrofit1.create(GetBankApi.class);

    public Observable<BaseReturn<BankBean>> getBankData(){
        return service.getBankData(HuaShanApplication.token,"XMLHttpRequest");
    }
}
