package com.karazam.huashanapp.today.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.today.main.model.databinding.TodayBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface TodayApi {
    @GET("/mobile/index")
    Observable<BaseReturn<TodayBean>> getTodayData(@Header("X-Requested-With") String ID,@Header("sid") String token);

}
