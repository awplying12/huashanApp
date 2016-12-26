package com.karazam.huashanapp.user.register.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.user.register.model.databinbing.CheckMobileBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public class CheckMobileDataSource extends BaseDataSource {

    CheckMobileApi service = retrofit1.create(CheckMobileApi.class);

    public Observable<BaseReturn> checkMobile(String mobile){
        CheckMobileBean bean = new CheckMobileBean();
        bean.setMobile(mobile);
        return service.checkMobile(bean,"XMLHttpRequest");
    }
}
