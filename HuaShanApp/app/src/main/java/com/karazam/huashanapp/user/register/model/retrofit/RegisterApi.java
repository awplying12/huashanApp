package com.karazam.huashanapp.user.register.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;
import com.karazam.huashanapp.user.register.model.databinbing.RegisterBean;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/22.
 */

public interface RegisterApi {
    @POST("/mobile/regist")
    Observable<BaseReturn<TokenData>> onRegister(@Body RegisterBean bean, @Header("X-Requested-With") String ID);

//    Observable<BaseReturn<TokenData>> onRegister(@Body RegisterBean bean, @Header("X-Requested-With") String ID);
}
