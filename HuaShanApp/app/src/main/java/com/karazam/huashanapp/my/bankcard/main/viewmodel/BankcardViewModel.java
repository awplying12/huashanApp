package com.karazam.huashanapp.my.bankcard.main.viewmodel;

import android.view.View;

import com.example.paymentpassword.PasswordView;
import com.example.utils.base.BaseViewModel;
import com.karazam.huashanapp.main.retorfitMain.BaseDataSource;

/**
 * Created by Administrator on 2016/12/26.
 */

public abstract class BankcardViewModel extends BaseViewModel{

    public int flag = 1;

    public String id = "-1";
    public PasswordView pwd_view;

    public abstract void toBindcard(View view);

    public abstract void toUnbundling(String payPassword);



}
