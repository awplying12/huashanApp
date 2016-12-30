package com.karazam.huashanapp.main.retrofit.user;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.Bean.MyInformation.MyInformationBean;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/29.
 */

public class MyInformationDataSource extends BaseDataSource{

    MyInformationApi service = retrofit1.create(MyInformationApi.class);

    public Observable<BaseReturn<MyInformationBean>> getMyInformation(){
        String userId = HuaShanApplication.uuid;
        String token = HuaShanApplication.token;
        return service.getMyInformation(userId,token,"XMLHttpRequest");

    }
}
