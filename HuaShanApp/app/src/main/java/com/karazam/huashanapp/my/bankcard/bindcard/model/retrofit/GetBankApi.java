package com.karazam.huashanapp.my.bankcard.bindcard.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BankBean;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BankPost;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/4.
 */

public interface GetBankApi {
    @GET("/mobile/uc/bank_card/banks")
    Observable<BaseReturn<BankPost>> getBankData(@Query("currentPage") String currentPage,@Header("sid") String token, @Header("X-Requested-With") String ID);
}
