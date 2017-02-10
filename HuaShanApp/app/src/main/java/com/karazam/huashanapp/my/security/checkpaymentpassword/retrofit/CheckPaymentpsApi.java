package com.karazam.huashanapp.my.security.checkpaymentpassword.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/2/10.
 */

public interface CheckPaymentpsApi {
    @POST("/mobile/uc/isSetPayPassword")
    Observable<BaseReturn> checkPaymentps(@Body CheckPaymentpsPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
