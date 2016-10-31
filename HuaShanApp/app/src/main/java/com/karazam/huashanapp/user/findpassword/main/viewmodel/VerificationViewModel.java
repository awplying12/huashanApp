package com.karazam.huashanapp.user.findpassword.main.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;
import com.ogaclejapan.rx.binding.RxProperty;

/**
 * Created by Administrator on 2016/10/31.
 */

public abstract class VerificationViewModel extends BaseViewModel{

    public RxProperty<String> VerifyCode = RxProperty.create();

    public RxProperty<Integer> time = RxProperty.create();

    public abstract void reacQuire(View view);

    public abstract void onNextStep(View view);
}
