package com.karazam.huashanapp.user.register.view;

import com.example.utils.base.BaseView;
import com.karazam.huashanapp.my.security.gesturepassword.model.databinding.GespwReturn;
import com.karazam.huashanapp.user.login.model.databinding.TokenData;

/**
 * Created by Administrator on 2016/10/20.
 */

public interface Register3View extends BaseView{

    void showUserAgreement();

    void stopUserAgreement();

    void registerSuccess(TokenData data);

    void registerFaile(Throwable e);


    void setGesPasswordSuccess(GespwReturn gespwReturn);

    void setGesPasswordFaile(String s);

    void setGesPasswordError(Throwable e);
}
