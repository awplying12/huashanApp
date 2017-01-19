package com.karazam.huashanapp.main.retrofit.smsverification;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.Bean.SendSmsBean;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface SendSmsApi {
    @POST("/mobile/sms/sendSms")
    Observable<BaseReturn> sendSms(@Body SendSmsBean bean,@Header("X-Requested-With") String ID);
}
