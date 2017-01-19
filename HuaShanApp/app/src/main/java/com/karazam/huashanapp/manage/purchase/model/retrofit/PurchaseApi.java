package com.karazam.huashanapp.manage.purchase.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchasePost;
import com.karazam.huashanapp.manage.purchase.model.databinding.PurchaseRetureBean;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/10.
 */

public interface PurchaseApi {
    @POST("/mobile/investment/invest")
    Observable<BaseReturn<PurchaseRetureBean>> Purchase(@Body PurchasePost post, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
