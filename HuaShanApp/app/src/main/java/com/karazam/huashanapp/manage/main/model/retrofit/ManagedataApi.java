package com.karazam.huashanapp.manage.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.main.model.databinding.ManagedataBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface ManagedataApi {
    @GET("/mobile/investment/list")
    Observable<BaseReturn<ManagedataBean>> getManagedata(@Query("borrowingType") String borrowingType, @Query("currentPage") String currentPage, @Header("X-Requested-With") String ID);
}
