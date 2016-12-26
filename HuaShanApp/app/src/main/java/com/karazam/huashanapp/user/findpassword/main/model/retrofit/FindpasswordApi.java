package com.karazam.huashanapp.user.findpassword.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.user.findpassword.main.model.databinding.FindpasswordBean;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public interface FindpasswordApi {
    @POST("/password/findByMobile")
    Observable<BaseReturn> findPassword(@Body FindpasswordBean bean,@Header("X-Requested-With") String ID);
}
