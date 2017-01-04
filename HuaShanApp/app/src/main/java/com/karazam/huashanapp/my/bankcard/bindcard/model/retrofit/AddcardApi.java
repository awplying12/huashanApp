package com.karazam.huashanapp.my.bankcard.bindcard.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcarPost;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/4.
 */

public interface AddcardApi {
    @POST("/uc/bank_card/create")
    Observable<BaseReturn<BindcardBean>> getAddcard(@Body BindcarPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
