package com.karazam.huashanapp.my.transactiondetails.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.transactiondetails.main.model.databinding.TransactionBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/12.
 */

public interface TransactionApi {
    @GET("/uc/payment_order/records")
    Observable<BaseReturn<TransactionBean>> getTransactionData(@Query("currentPage") String currentPage, @Header("sid") String token, @Header("X-Requested-With") String ID);

}
