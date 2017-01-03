package com.karazam.huashanapp.my.security.paymentpassword.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.security.paymentpassword.model.databinding.PaymentPost;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/3.
 */

public class PaymentDataSource extends BaseDataSource {

    PaymentApi service = retrofit1.create(PaymentApi.class);

    public Observable<BaseReturn> onPayment(String payPassword,String type){
        PaymentPost post = new PaymentPost();
        post.setPayPassword(payPassword);
        post.setType(type);
        return service.onPayment(post, HuaShanApplication.token,"XMLHttpRequest");
    }
}
