package com.karazam.huashanapp.main.retrofit.smsverification;

import com.karazam.huashanapp.main.Bean.VerifySmsBean;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface VerifySmsApi {
    @POST("/mobile/sms/verify")
    Observable<BaseReturn> verifySms(@Body VerifySmsBean bean,@Header("X-Requested-With") String ID);
}
