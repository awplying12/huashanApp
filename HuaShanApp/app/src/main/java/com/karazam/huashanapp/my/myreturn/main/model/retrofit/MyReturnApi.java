package com.karazam.huashanapp.my.myreturn.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.myreturn.main.model.databinding.MyReturnBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/12.
 */

public interface MyReturnApi {
    @GET("/uc/investment/recoverys")
    Observable<BaseReturn<MyReturnBean>> getMyReturnData(@Query("currentPage") String currentPage, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
