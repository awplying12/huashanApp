package com.karazam.huashanapp.main.retrofit.smsverification;

import com.karazam.huashanapp.main.Bean.SendSmsBean;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public class SendSmsDataSource extends BaseDataSource {

    SendSmsApi service = retrofit1.create(SendSmsApi.class);

    public Observable<BaseReturn> sendSms(String mobile,String smsType){
        SendSmsBean bean = new SendSmsBean();
        bean.setMobile(mobile);
        bean.setSmsType(smsType);
        return service.sendSms(bean,"XMLHttpRequest");
    }
}
