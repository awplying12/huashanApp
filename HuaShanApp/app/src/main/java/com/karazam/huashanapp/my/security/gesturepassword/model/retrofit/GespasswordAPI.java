package com.karazam.huashanapp.my.security.gesturepassword.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GespwBean;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GespwReturn;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface GespasswordAPI {

    @POST("/mobile/uc/setGesPassword")
    Observable<BaseReturn<GespwReturn>> setGespassword(@Body GespwBean bean, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
