package com.karazam.huashanapp.user.register.viewmodel.Register3ViewModel;

import android.view.View;
import android.widget.EditText;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/10/20.
 */

public abstract class Register3ViewModel extends BaseViewModel{

    public String mobilel;
    public String password;
    public String smsCode;


    public EditText ed_password;

    public abstract void complete(View view);

    public abstract void userAgreement(View view);

    public abstract void stopUserAgreement(View view);

    public abstract void onRegister();

    public abstract void setGesPassword(String gesPassword);
}
