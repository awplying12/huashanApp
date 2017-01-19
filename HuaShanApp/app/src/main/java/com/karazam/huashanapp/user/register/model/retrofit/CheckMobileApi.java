package com.karazam.huashanapp.user.register.model.retrofit;

import com.karazam.huashanapp.home.model.databinding.CheckloginBean;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.user.register.model.databinbing.CheckMobileBean;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface CheckMobileApi {
    @POST("/mobile/regist/checkMobile")
    Observable<BaseReturn> checkMobile(@Body CheckMobileBean bean,@Header("X-Requested-With") String ID);
}
