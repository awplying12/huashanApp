package com.karazam.huashanapp.user.register.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retorfitMain.DigestUtils;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;
import com.karazam.huashanapp.user.register.model.databinbing.RegisterBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/22.
 */

public class RegisterDataSource extends BaseDataSource{

    RegisterApi service = retrofit1.create(RegisterApi.class);

    public Observable<BaseReturn<TokenData>> onRegister(String account,String password,String smsCode){
        password = DigestUtils.encrypt(password);
        RegisterBean bean = new RegisterBean();
        bean.setMobilel(account);
        bean.setPassword(password);
        bean.setSmsCode(smsCode);

        return service.onRegister(bean,"XMLHttpRequest");
//        return service.onRegister(bean,"XMLHttpRequest");
    }
}
