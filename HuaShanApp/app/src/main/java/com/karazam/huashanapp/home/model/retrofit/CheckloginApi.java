package com.karazam.huashanapp.home.model.retrofit;

import com.karazam.huashanapp.home.model.databinding.CheckloginBean;
import com.karazam.huashanapp.home.model.databinding.CheckloginReturn;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/21.
 */

public interface CheckloginApi {
    @POST("/checkLoginStatus")
    Observable<BaseReturn<CheckloginReturn>> checkLoginStatus(@Body CheckloginBean check, @Header("sid") String token,@Header("X-Requested-With") String ID);

//    Observable<BaseReturn<CheckloginReturn>> checkLoginStatus(@Query("userId") String userId, @Query("userKey") String userKey, @Header("sid") String token,@Header("X-Requested-With") String ID);
}
