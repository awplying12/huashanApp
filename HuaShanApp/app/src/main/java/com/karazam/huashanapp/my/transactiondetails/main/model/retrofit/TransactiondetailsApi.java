package com.karazam.huashanapp.my.transactiondetails.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactiondetailsBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/12.
 */

public interface TransactiondetailsApi {
    @GET("/uc/payment_order/detail")
    Observable<BaseReturn<TransactiondetailsBean>> getTransactiondetails(@Query("orderId") String orderNo,@Query("type") String type, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
