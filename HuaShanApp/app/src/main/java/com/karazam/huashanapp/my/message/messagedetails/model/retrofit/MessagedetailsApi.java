package com.karazam.huashanapp.my.message.messagedetails.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.message.messagedetails.model.databinding.MessagedetailsBean;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/16.
 */

public interface MessagedetailsApi {

    @GET("/mobile/uc/message/detail")
    Observable<BaseReturn<MessagedetailsBean>> getMessagedetails(@Query("userId") String userId, @Query("type") String type, @Query("currentPage") String currentPage, @Header("sid") String token, @Header("X-Requested-With") String ID);

}
