package com.karazam.huashanapp.main.retrofit.user;

import com.karazam.huashanapp.main.Bean.MyAssets.MyAssetsBean;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/30.
 */

public interface MyAssetsApi {
    @GET("/mobile/uc/assets")
    Observable<BaseReturn<MyAssetsBean>> getMyAssets(@Query("userId") String userId, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
