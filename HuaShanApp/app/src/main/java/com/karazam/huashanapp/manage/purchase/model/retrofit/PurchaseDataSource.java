package com.karazam.huashanapp.manage.purchase.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchasePost;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchaseRetureBean;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/10.
 */

public class PurchaseDataSource extends BaseDataSource {

    PurchaseApi service = retrofit1.create(PurchaseApi.class);

    public Observable<BaseReturn<PurchaseRetureBean>> Purchase(PurchasePost post){
        return service.Purchase(post, HuaShanApplication.token,"XMLHttpRequest");
    }
}
