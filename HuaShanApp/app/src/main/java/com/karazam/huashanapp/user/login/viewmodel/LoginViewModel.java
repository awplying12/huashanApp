package com.karazam.huashanapp.user.login.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/10/18.
 */

public abstract class LoginViewModel extends BaseViewModel{


//    public String corp = "0"; //默认为个人账户

    public abstract void toFindpassword(View view);

    public abstract void toRegister(View view);

    public abstract void login(String account,String password);
}
