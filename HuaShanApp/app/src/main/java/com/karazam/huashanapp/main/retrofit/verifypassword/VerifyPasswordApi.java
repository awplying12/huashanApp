package com.karazam.huashanapp.main.retrofit.verifypassword;

import com.karazam.huashanapp.main.Bean.VerifyPasswordBean;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/24.
 */

public interface VerifyPasswordApi {
    @POST("/mobile/uc/verifyPassword")
    Observable<BaseReturn> verifyPassword(@Body VerifyPasswordBean bean, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
