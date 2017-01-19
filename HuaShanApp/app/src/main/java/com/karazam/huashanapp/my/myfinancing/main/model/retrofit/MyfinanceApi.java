package com.karazam.huashanapp.my.myfinancing.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.myfinancing.main.model.databinding.MyfinanceBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/11.
 */

public interface MyfinanceApi {
    @GET("/mobile/uc/investment/records")
    Observable<BaseReturn<MyfinanceBean>> getMyfinance(@Query("progress") String progress, @Query("currentPage") String currentPage, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
