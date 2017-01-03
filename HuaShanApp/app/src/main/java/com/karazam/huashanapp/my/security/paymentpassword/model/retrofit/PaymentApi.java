package com.karazam.huashanapp.my.security.paymentpassword.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.security.paymentpassword.model.databinding.PaymentPost;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/3.
 */

public interface PaymentApi {
    @POST("/uc/update_pay_password")
    Observable<BaseReturn> onPayment(@Body PaymentPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
