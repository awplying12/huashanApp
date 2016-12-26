package com.karazam.huashanapp.manage.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface ManagedataApi {
    @GET("/investment/list")
    Observable<BaseReturn> getManagedata();
}
