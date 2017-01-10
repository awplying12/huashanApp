package com.karazam.huashanapp.manage.purchase.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchasSMSPost;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/10.
 */

public interface PurchasSMSApi {
    @POST("/investment/shortcut_msg")
    Observable<BaseReturn> sendSMS(@Body PurchasSMSPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
