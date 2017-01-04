package com.karazam.huashanapp.my.security.paymentpassword3.viewmodel;

import android.view.View;

import com.example.paymentpassword.SetpasswordView;
import com.example.utils.base.BaseViewModel;
import com.ogaclejapan.rx.binding.RxProperty;

/**
 * Created by Administrator on 2016/11/28.
 */

public abstract class SetpaypsViewModel extends BaseViewModel {

    public RxProperty<String> pw = RxProperty.create();

    public String one;
    public String two;

    public String tag;

    public String type;
    public SetpasswordView spwd_view;

    public abstract void onConfirm(View view);

    public abstract void setUp();
}
