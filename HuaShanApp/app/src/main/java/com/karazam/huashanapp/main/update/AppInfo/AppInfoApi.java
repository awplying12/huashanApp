package com.karazam.huashanapp.main.update.AppInfo;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/18.
 */

public interface AppInfoApi {
    @GET("/others/appInfo/json")
    Observable<BaseReturn<AppInfoBean>> getAppInfo(@Header("sid") String token, @Header("X-Requested-With") String ID);
}
