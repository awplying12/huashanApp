package com.karazam.huashanapp.my.recharge.main.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.recharge.main.model.databinding.RechangPost;
import com.karazam.huashanapp.my.recharge.main.model.databinding.RechargeBean;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/9.
 */

public class RechargeDataSource extends BaseDataSource{

    RechargeApi service = retrofit1.create(RechargeApi.class);

    public Observable<BaseReturn<RechargeBean>> Recharge(String amount, String bankCardId, String orderNo, String smsCode){
        RechangPost post = new RechangPost();
        post.setUserId(HuaShanApplication.uuid);
        post.setAmount(amount);
        post.setBankCardId(bankCardId);
        post.setOrderNo(orderNo);
        post.setSmsCode(smsCode);
        return service.Recharge(post,HuaShanApplication.token,"XMLHttpRequest");
    }
}
