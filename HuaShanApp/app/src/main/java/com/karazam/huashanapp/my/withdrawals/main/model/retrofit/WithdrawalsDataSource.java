package com.karazam.huashanapp.my.withdrawals.main.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.my.withdrawals.main.model.databinding.WithdrawalsBean;
import com.karazam.huashanapp.my.withdrawals.main.model.databinding.WithdrawalsPost;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/9.
 */

public class WithdrawalsDataSource extends BaseDataSource{

    WithdrawalsApi service = retrofit1.create(WithdrawalsApi.class);

    public Observable<BaseReturn<WithdrawalsBean>> Withdrawals(WithdrawalsPost post){
//        WithdrawalsPost post = new WithdrawalsPost();
        return service.Withdrawals(post,HuaShanApplication.token,"XMLHttpRequest");
    }
}
