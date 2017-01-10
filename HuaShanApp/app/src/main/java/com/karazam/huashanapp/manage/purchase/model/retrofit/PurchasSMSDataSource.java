package com.karazam.huashanapp.manage.purchase.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchasSMSPost;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/10.
 */

public class PurchasSMSDataSource extends BaseDataSource {

    PurchasSMSApi service = retrofit1.create(PurchasSMSApi.class);

    public Observable<BaseReturn> sendSMS(PurchasSMSPost post){
        return service.sendSMS(post, HuaShanApplication.token,"XMLHttpRequest");
    }
}
