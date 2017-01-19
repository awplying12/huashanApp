package com.karazam.huashanapp.manage.details.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.details.model.databinding.ManagedetailsBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/28.
 */

public interface ManagedetailsApi {
    @GET("/mobile/investment/details")
    Observable<BaseReturn<ManagedetailsBean>> getDetails(@Query("projectId") String projectId,@Header("X-Requested-With") String ID);

}
