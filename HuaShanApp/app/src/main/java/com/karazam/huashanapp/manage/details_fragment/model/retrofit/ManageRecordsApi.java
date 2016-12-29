package com.karazam.huashanapp.manage.details_fragment.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.details_fragment.model.databinding.ManageRecordsBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/28.
 */

public interface ManageRecordsApi {

    @GET("/investment/investmentRecords")
    Observable<BaseReturn<ManageRecordsBean>> getRecords(@Query("projectId") String projectId ,@Query("currentPage") String currentPage, @Header("X-Requested-With") String ID);
}
