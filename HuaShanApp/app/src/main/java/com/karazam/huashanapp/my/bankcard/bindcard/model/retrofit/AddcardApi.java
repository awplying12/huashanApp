package com.karazam.huashanapp.my.bankcard.bindcard.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardPost;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.UpDatecardPost;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/4.
 */

public interface AddcardApi {
    @POST("/mobile/uc/bank_card/create")
    Observable<BaseReturn<BindcardBean>> getAddcard(@Body BindcardPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);

    @POST("/mobile/uc/bank_card/update")
    Observable<BaseReturn<BindcardBean>> upDatecard(@Body UpDatecardPost post,@Header("sid") String token, @Header("X-Requested-With") String ID);

    @POST("/mobile/uc/bank_card/send_texting")
    Observable<BaseReturn> sendSMS(@Body BindcardPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);

}
