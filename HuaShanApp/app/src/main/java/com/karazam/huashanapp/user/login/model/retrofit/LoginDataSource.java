package com.karazam.huashanapp.user.login.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retorfitMain.DigestUtils;
import com.karazam.huashanapp.user.login.model.databinding.LoginBean;
import com.karazam.huashanapp.user.login.model.databinding.LoginEntity;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;

import rx.Observable;

/**
 * Created by Administrator on 2016/10/20.
 */

public class LoginDataSource extends BaseDataSource {

    LoginApi service = retrofit.create(LoginApi.class);

    public Observable<BaseReturn<TokenData>> getToken( String loginName,String password,boolean corp){
        password = DigestUtils.encrypt(password);
        LoginBean bean = new LoginBean();
        bean.setUsername(loginName);
        bean.setPassword(password);
        bean.setCorp(corp);
        return service.getToken(bean,"android",corp,"XMLHttpRequest");

    }
}
