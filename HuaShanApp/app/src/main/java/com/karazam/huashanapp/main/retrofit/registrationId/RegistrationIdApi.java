package com.karazam.huashanapp.main.retrofit.registrationId;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/16.
 */

public interface RegistrationIdApi {
    @POST("/uc/registration")
    Observable<BaseReturn> setRegistrationId(@Body RegistrationIdPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
