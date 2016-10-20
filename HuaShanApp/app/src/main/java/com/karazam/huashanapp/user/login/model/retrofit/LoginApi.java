package com.karazam.huashanapp.user.login.model.retrofit;


import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.user.login.model.databinding.LoginEntity;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;

import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/10/20.
 */

public interface LoginApi {
    @POST("/login")
    Observable<BaseReturn<TokenData>> getToken(@Query("loginName") String loginName, @Query("password") String password);
}
