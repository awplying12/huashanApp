package com.karazam.huashanapp.main.retrofit.verifypassword;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.Bean.VerifyPasswordBean;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retorfitMain.DigestUtils;

import rx.Observable;

/**
 * Created by Administrator on 2017/1/24.
 */

public class VerifyPasswordDataSource extends BaseDataSource{

    VerifyPasswordApi service = retrofit1.create(VerifyPasswordApi.class);

    public Observable<BaseReturn> verifyPassword(String password){
        password = DigestUtils.encrypt(password);
        VerifyPasswordBean bean = new VerifyPasswordBean();
        bean.setPassword(password);
        bean.setUserId(HuaShanApplication.uuid);

        return service.verifyPassword(bean,HuaShanApplication.token,"XMLHttpRequest");
    }
}
