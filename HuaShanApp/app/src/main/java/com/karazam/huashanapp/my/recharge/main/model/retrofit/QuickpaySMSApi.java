package com.karazam.huashanapp.my.recharge.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.recharge.main.model.databinding.OrderBean;
import com.karazam.huashanapp.my.recharge.main.model.databinding.QuickpaySMSPost;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface QuickpaySMSApi {
    @POST("/mobile/uc/recharge/quickpay/sms")
    Observable<BaseReturn<OrderBean>> sendQuickSMS(@Body QuickpaySMSPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
