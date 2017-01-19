package com.karazam.huashanapp.my.bankcard.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BindcardBean;
import com.karazam.huashanapp.my.bankcard.main.model.databinding.DeletecardPost;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/6.
 */

public interface DeletecardApi {
    @POST("/mobile/uc/bank_card/delete")
    Observable<BaseReturn<BindcardBean>> deletacard(@Body DeletecardPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);
}
