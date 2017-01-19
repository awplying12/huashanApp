package com.karazam.huashanapp.my.message.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.message.main.model.databinding.MessagelistBean;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/16.
 */

public interface MessagelistApi {
    @GET("/mobile/uc/message/list")
    Observable<BaseReturn<MessagelistBean>> getMessagelist(@Query("userId") String userId, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
