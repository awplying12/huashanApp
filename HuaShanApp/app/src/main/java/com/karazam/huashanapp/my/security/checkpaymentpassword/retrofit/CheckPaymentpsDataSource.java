package com.karazam.huashanapp.my.security.checkpaymentpassword.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import rx.Observable;

/**
 * Created by Administrator on 2017/2/10.
 */

public class CheckPaymentpsDataSource extends BaseDataSource {

    CheckPaymentpsApi service = retrofit1.create(CheckPaymentpsApi.class);

    public Observable<BaseReturn> checkPaymentps(){
        CheckPaymentpsPost post = new CheckPaymentpsPost();
        post.setUserId(HuaShanApplication.uuid);
        return service.checkPaymentps(post,HuaShanApplication.token,"XMLHttpRequest");
    }
}
