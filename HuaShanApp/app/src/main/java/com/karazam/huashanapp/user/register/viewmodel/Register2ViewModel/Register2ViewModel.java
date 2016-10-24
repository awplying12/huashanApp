package com.karazam.huashanapp.user.register.viewmodel.Register2ViewModel;

import android.view.View;

import com.example.utils.base.BaseViewModel;
import com.ogaclejapan.rx.binding.RxProperty;

/**
 * Created by Administrator on 2016/10/20.
 */

public abstract class Register2ViewModel extends BaseViewModel {

    public RxProperty<String> VerifyCode = RxProperty.create();

    public RxProperty<Integer> time = RxProperty.create();

    public abstract void reacQuire(View view);

    public abstract void onNextStep(View view);
}
