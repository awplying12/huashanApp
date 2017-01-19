package com.karazam.huashanapp.my.security.paymentpassword2.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.security.paymentpassword2.model.databinding.VerifyidentityPost;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/4.
 */

public interface VerifyidentityApi {
    @POST("/mobile/uc/userVerify")
    Observable<BaseReturn> Verifyidentity(@Body VerifyidentityPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
