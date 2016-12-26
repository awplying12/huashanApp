package com.karazam.huashanapp.user.register.viewmodel.Register2ViewModel;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.base.BaseViewModel;
import com.ogaclejapan.rx.binding.RxProperty;

/**
 * Created by Administrator on 2016/10/20.
 */

public abstract class Register2ViewModel extends BaseViewModel {

    public RxProperty<String> VerifyCode = RxProperty.create();

    public RxProperty<Integer> time = RxProperty.create();

    public TextView tv_time;
    public EditText ed_verify_code;

    public String phonenum;

    public abstract void reacQuire(View view);

    public abstract void onNextStep(View view);

    public abstract void sendSms(String phonenum);

    public abstract void verifySms(String phonenum);
}
