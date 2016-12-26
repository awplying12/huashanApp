package com.karazam.huashanapp.main.retrofit.smsverification;

import com.example.utils.base.BaseViewModel;
import com.karazam.huashanapp.main.Bean.VerifySmsBean;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public class VerifySmsDataSource extends BaseDataSource {

    VerifySmsApi service = retrofit1.create(VerifySmsApi.class);

    public Observable<BaseReturn> verifySms(String mobile,String smsCode,String smsType){
        VerifySmsBean bean = new VerifySmsBean();
        bean.setMobile(mobile);
        bean.setSmsCode(smsCode);
        bean.setSmsType(smsType);
        return service.verifySms(bean,"XMLHttpRequest");
    }
}
