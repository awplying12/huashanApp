package com.karazam.huashanapp.my.realname.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/30.
 */

public interface RealnameApi {
    @POST("/uc/general_cert")
    Observable<BaseReturn> onRealname();
}
