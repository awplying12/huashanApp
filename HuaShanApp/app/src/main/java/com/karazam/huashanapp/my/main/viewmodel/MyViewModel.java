package com.karazam.huashanapp.my.main.viewmodel;

import android.view.View;

import com.example.utils.base.BaseViewModel;
import com.ogaclejapan.rx.binding.RxProperty;

/**
 * Created by Administrator on 2016/10/12.
 */

public abstract class MyViewModel extends BaseViewModel{



    public abstract void setUp(View view);

    public abstract void Rechargecash(View view);

    public abstract void Myfinance(View view);

    public abstract void Mytransfer(View view);

    public abstract void MyReturn(View view);

    public abstract void BankCard(View view);

    public abstract void Transaction(View view);

    public abstract void Message(View view);


}
