package com.karazam.huashanapp.main.retrofit.user;

import android.util.Log;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/1/24.
 */

public class LogoutDataSource extends BaseDataSource {

    LogoutApi service = retrofit1.create(LogoutApi.class);

    public void Logout(){
        service.Logout(HuaShanApplication.token,"XMLHttpRequest")
                .throttleFirst(2000, TimeUnit.MILLISECONDS)
                .observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread())
                .subscribe(new Subscriber<BaseReturn>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("Logout","  e  :  "+e.toString());
            }

            @Override
            public void onNext(BaseReturn baseReturn) {
                if(baseReturn.isSuccess()){

                } else {
                    Log.i("Logout","  baseReturn  :  "+ baseReturn.getMessage());
                }
            }
        });
    }
}
