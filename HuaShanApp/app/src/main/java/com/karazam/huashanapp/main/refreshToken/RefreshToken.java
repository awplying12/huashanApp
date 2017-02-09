package com.karazam.huashanapp.main.refreshToken;

import android.util.Log;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.home.model.databinding.CheckloginReturn;
import com.karazam.huashanapp.home.model.retrofit.CheckloginDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Administrator on 2017/2/8.
 */

public class RefreshToken {

    private static CheckloginDataSource dataSource;

    public interface RefreshTokenInterface{
        void onSuccess(String s);

        void onFaile(String s);

        void onError(Throwable e);
    }


    public static void refresh(final RefreshTokenInterface refreshTokenInterface){

        dataSource = new CheckloginDataSource();
        dataSource.checkLoginStatus().observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.newThread()).subscribe(new Subscriber<BaseReturn<CheckloginReturn>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                Log.i("onChecklogin","e : "+e.toString());
//                mView.CheckloginError(e);
                if(refreshTokenInterface == null){
                    return;
                }
                refreshTokenInterface.onError(e);
            }

            @Override
            public void onNext(BaseReturn<CheckloginReturn> checkloginReturnBaseReturn) {



                if(checkloginReturnBaseReturn.isSuccess()){
                    CheckloginReturn checkloginReturn = checkloginReturnBaseReturn.getData();

                    Log.i("onChecklogin",checkloginReturn.toString());

                    HuaShanApplication.editor.putString("token",checkloginReturn.getSid()).commit();
                    HuaShanApplication.token = checkloginReturn.getSid();

                    HuaShanApplication.editor.putString("uuid",checkloginReturn.getUserId()).commit();
                    HuaShanApplication.uuid = checkloginReturn.getUserId();

                    HuaShanApplication.editor.putString("userKey",checkloginReturn.getUserKey()).commit();
                    HuaShanApplication.userKey = checkloginReturn.getUserKey();

                    //                    HuaShanApplication.editor.putInt("loginStatus",1).commit();
                    //                    HuaShanApplication.loginStatus = 1;
                    //                    HuaShanApplication.loginStatusRx.set(HuaShanApplication.loginStatus);


                    if(refreshTokenInterface == null){
                        return;
                    }
                    refreshTokenInterface.onSuccess(checkloginReturnBaseReturn.getMessage());

                }else {

                    if(refreshTokenInterface == null){
                        return;
                    }
                    refreshTokenInterface.onFaile(checkloginReturnBaseReturn.getMessage());
                }

            }
        });
    }
}
