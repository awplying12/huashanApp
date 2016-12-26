package com.karazam.huashanapp.user.findpassword.main.model.retrofit;

import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retorfitMain.DigestUtils;
import com.karazam.huashanapp.user.findpassword.main.model.databinding.FindpasswordBean;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/26.
 */

public class FindpasswordDataSource extends BaseDataSource{

    FindpasswordApi service = retrofit1.create(FindpasswordApi.class);

    public Observable<BaseReturn> findPassword(String mobile,String password,String smsCode){

        password = DigestUtils.encrypt(password);

        FindpasswordBean bean = new FindpasswordBean();
        bean.setMobile(mobile);
        bean.setPassword(password);
        bean.setSmsCode(smsCode);
        return service.findPassword(bean,"XMLHttpRequest");
    }
}
