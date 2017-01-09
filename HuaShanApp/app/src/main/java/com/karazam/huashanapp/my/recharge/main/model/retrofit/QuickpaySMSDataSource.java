package com.karazam.huashanapp.my.recharge.main.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.recharge.main.model.databinding.OrderBean;
import com.karazam.huashanapp.my.recharge.main.model.databinding.QuickpaySMSPost;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/9.
 */

public class QuickpaySMSDataSource extends BaseDataSource {

    QuickpaySMSApi service = retrofit1.create(QuickpaySMSApi.class);

    public Observable<BaseReturn<OrderBean>> sendQuickSMS(String amount, String bankCardId){
        QuickpaySMSPost post = new QuickpaySMSPost();
        post.setUserId(HuaShanApplication.uuid);
        post.setAmount(amount);
        post.setBankCardId(bankCardId);
        return service.sendQuickSMS(post,HuaShanApplication.token,"XMLHttpRequest");
    }
}
