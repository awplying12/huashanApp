package com.karazam.huashanapp.my.security.main.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;

/**
 * Created by Administrator on 2016/11/28.
 */

public abstract class SecurityViewModel extends BaseViewModel {

    public abstract void onPayment(View view);

    public abstract void onGesture(View view);
}
