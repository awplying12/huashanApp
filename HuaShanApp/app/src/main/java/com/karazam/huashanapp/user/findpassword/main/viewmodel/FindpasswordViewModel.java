package com.karazam.huashanapp.user.findpassword.main.viewmodel;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.utils.base.BaseViewModel;
import com.ogaclejapan.rx.binding.RxProperty;

/**
 * Created by Administrator on 2016/10/31.
 */

public abstract class FindpasswordViewModel extends BaseViewModel{

    public RxProperty<String> VerifyCode = RxProperty.create();

    public RxProperty<Integer> time = RxProperty.create();


    public EditText et_phonenum,ed_code,ed_password,ed_password_two;
    public TextView tv_time;

    public abstract void reacQuire(View view);

    public abstract void onNextStep(View view);
}
