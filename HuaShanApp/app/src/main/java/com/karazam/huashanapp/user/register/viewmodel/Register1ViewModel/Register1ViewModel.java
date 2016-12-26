package com.karazam.huashanapp.user.register.viewmodel.Register1ViewModel;

import android.view.View;
import android.widget.EditText;

import com.example.utils.base.BaseViewModel;
import com.ogaclejapan.rx.binding.RxProperty;

/**
 * Created by Administrator on 2016/10/20.
 */

public abstract class Register1ViewModel extends BaseViewModel{

    public RxProperty<String> phoneNum = RxProperty.create();

    public EditText account_register;

    public abstract void onNextStep(View view);

    public abstract void onIntroduction(View view);

    public abstract void onSure(View view);

    public abstract void checkMobile(String mobile);
}
