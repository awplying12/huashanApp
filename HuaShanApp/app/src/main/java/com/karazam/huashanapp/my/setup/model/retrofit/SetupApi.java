package com.karazam.huashanapp.my.setup.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupBean;
import com.karazam.huashanapp.my.setup.model.datanbinding.SetupPost;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/10.
 */

public interface SetupApi {
    @POST("/uc/setAvatarAndNickName")
    Observable<BaseReturn<SetupBean>> setup(@Body SetupPost post,@Header("sid") String token, @Header("X-Requested-With") String ID);
}
