package com.karazam.huashanapp.main.retrofit.user;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/30.
 */

public class MyAssetsDataSource extends BaseDataSource{

    MyAssetsApi service = retrofit1.create(MyAssetsApi.class);

    public Observable<BaseReturn<MyAssetsBean>> getMyAssets(){
        String userId = HuaShanApplication.uuid;
        String token = HuaShanApplication.token;
        return service.getMyAssets(userId,token,"XMLHttpRequest");
    }
}
