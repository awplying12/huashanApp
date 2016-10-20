package com.karazam.huashanapp.user.login.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.user.login.model.databinding.LoginEntity;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/20.
 */

public class LoginDataSource extends BaseDataSource {

    LoginApi service = retrofit.create(LoginApi.class);

    public Observable<BaseReturn<TokenData>> getToken( String loginName,String password){

        return service.getToken(loginName,password);

    }
}
