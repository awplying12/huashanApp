package com.karazam.huashanapp.my.bankcard.bindcard.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.bankcard.bindcard.model.databinding.BankBean;

import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/4.
 */

public interface GetBankApi {
    @POST("/uc/bank_card/banks")
    Observable<BaseReturn<BankBean>> getBankData(@Header("sid") String token, @Header("X-Requested-With") String ID);
}
