package com.karazam.huashanapp.main.retrofit.user;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/24.
 */

public interface LogoutApi {
    @POST("/mobile/uc/logout")
    Observable<BaseReturn> Logout(@Header("sid") String token, @Header("X-Requested-With") String ID);
}
