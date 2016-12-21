package com.karazam.huashanapp.my.security.gesturepassword.model.retrofit;

import com.karazam.huashanapp.HuaShanApplication;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;
import com.karazam.huashanapp.main.retorfitMain.BaseReturn;
import com.karazam.huashanapp.main.retorfitMain.DigestUtils;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GespwBean;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GespwReturn;

import rx.Observable;

/**
 * Created by Administrator on 2016/12/21.
 */

public class GespasswordDataSource extends BaseDataSource {

    GespasswordAPI service = retrofit1.create(GespasswordAPI.class);

    public Observable<BaseReturn<GespwReturn>> setGesPassword(String gespassword){

        gespassword = DigestUtils.encrypt(gespassword);
        GespwBean bean = new GespwBean();
        bean.setUserId(HuaShanApplication.uuid);
        bean.setGesPassword(gespassword);
        return service.setGespassword(bean,HuaShanApplication.token,"XMLHttpRequest");
    }
}
