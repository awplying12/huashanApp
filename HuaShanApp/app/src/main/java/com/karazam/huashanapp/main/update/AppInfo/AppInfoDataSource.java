package com.karazam.huashanapp.main.update.AppInfo;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/18.
 */

public class AppInfoDataSource extends BaseDataSource {

    AppInfoApi service = retrofit1.create(AppInfoApi.class);

    public Observable<BaseReturn<AppInfoBean>> getAppInfo(){
        return service.getAppInfo(HuaShanApplication.token,"XMLHttpRequest");
    }
}
