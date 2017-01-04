package com.karazam.huashanapp.my.security.paymentpassword2.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.security.paymentpassword2.model.databinding.VerifyidentityPost;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/4.
 */

public class VerifyidentityDataSource extends BaseDataSource {

    VerifyidentityApi service = retrofit1.create(VerifyidentityApi.class);

    public Observable<BaseReturn> Verifyidentity(String idNo,String cardNo,String type){
        VerifyidentityPost post = new VerifyidentityPost();
        post.setIdNo(idNo);
        post.setCardNo(cardNo);
        post.setType(type);
        return service.Verifyidentity(post, HuaShanApplication.token,"XMLHttpRequest");
    }
}
