package com.karazam.huashanapp.my.withdrawals.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.withdrawals.main.model.databinding.WithdrawalsBean;
import com.karazam.huashanapp.my.withdrawals.main.model.databinding.WithdrawalsPost;

import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2017/1/9.
 */

public interface WithdrawalsApi {
    @POST("/mobile/uc/withdraw")
    Observable<BaseReturn<WithdrawalsBean>> Withdrawals(@Body WithdrawalsPost post, @Header("sid") String token, @Header("X-Requested-With") String ID);

}
