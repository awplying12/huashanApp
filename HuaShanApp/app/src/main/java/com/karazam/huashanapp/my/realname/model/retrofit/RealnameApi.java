package com.karazam.huashanapp.my.realname.model.retrofit;

import com.karazam.huashanapp.main.Bean.MyInformation.BaseInfoBean;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.realname.model.databinding.RealnameBean;
import com.karazam.huashanapp.my.realname.model.databinding.RealnamePost;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/30.
 */

public interface RealnameApi {
    @POST("/uc/general_cert")
    Observable<BaseReturn<RealnameBean>> onRealname(@Body RealnamePost post, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
