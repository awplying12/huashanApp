package com.karazam.huashanapp.manage.details_fragment.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.ManageOpinionsbean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/29.
 */

public interface ManageOpinionsApi {
    @GET("/mobile/investment/opinions")
    Observable<BaseReturn<ManageOpinionsbean>> getManageOpinions(@Query("projectId") String projectId,@Header("X-Requested-With") String ID);
}
