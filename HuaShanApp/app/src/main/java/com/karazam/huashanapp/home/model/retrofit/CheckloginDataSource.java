package com.karazam.huashanapp.home.model.retrofit;


import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.home.model.databinding.CheckloginBean;
import com.karazam.huashanapp.home.model.databinding.CheckloginReturn;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/21.
 */

public class CheckloginDataSource extends BaseDataSource {

    CheckloginApi service = retrofit1.create(CheckloginApi.class);

    public Observable<BaseReturn<CheckloginReturn>> checkLoginStatus(){
        CheckloginBean bean = new CheckloginBean();
        bean.setUserId(HuaShanApplication.uuid);
        bean.setUserKey(HuaShanApplication.userKey);
        bean.setCorp(HuaShanApplication.corp);
        return service.checkLoginStatus(bean,HuaShanApplication.token,"XMLHttpRequest");
//        return service.checkLoginStatus(HuaShanApplication.uuid,HuaShanApplication.userKey,HuaShanApplication.token,"XMLHttpRequest");
    }
}
