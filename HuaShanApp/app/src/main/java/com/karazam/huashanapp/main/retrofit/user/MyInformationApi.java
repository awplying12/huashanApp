package com.karazam.huashanapp.main.retrofit.user;

import com.karazam.huashanapp.main.Bean.MyInformation.MyInformationBean;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/29.
 */

public interface MyInformationApi {
    @GET("/mobile/uc/info")
    Observable<BaseReturn<MyInformationBean>> getMyInformation(@Query("userId") String userId,@Header("sid") String token,@Header("X-Requested-With") String ID);
}
