package com.karazam.huashanapp.main.retrofit.registrationId;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/16.
 */

public class RegistrationIdDataSource extends BaseDataSource{

    RegistrationIdApi service = retrofit1.create(RegistrationIdApi.class);

    public Observable<BaseReturn> setRegistrationId(){
        RegistrationIdPost post = new RegistrationIdPost();
        post.setRegistrationId(HuaShanApplication.RegistrationID);
        post.setUserId(HuaShanApplication.uuid);
        post.setOsType("android");
        return service.setRegistrationId(post,HuaShanApplication.token,"XMLHttpRequest");
    }
}
